/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.web.internal.facet.display.context;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.web.internal.facet.display.context.builder.AssetTagsSearchFacetDisplayContextBuilder;
import com.liferay.portal.search.web.internal.tag.facet.configuration.TagFacetPortletInstanceConfiguration;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.portlet.RenderRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author André de Oliveira
 */
public class AssetTagsSearchFacetDisplayContextTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		Mockito.doReturn(
			_facetCollector
		).when(
			_facet
		).getFacetCollector();
	}

	@Test
	public void testEmptySearchResults() throws Exception {
		String facetParam = "";

		AssetTagsSearchFacetDisplayContext assetTagsSearchFacetDisplayContext =
			createDisplayContext(facetParam);

		List<BucketDisplayContext> bucketDisplayContexts =
			assetTagsSearchFacetDisplayContext.getBucketDisplayContexts();

		Assert.assertEquals(
			bucketDisplayContexts.toString(), 0, bucketDisplayContexts.size());

		Assert.assertEquals(
			facetParam, assetTagsSearchFacetDisplayContext.getParameterValue());
		Assert.assertTrue(
			assetTagsSearchFacetDisplayContext.isNothingSelected());
		Assert.assertTrue(assetTagsSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testEmptySearchResultsWithPreviousSelection() throws Exception {
		String term = RandomTestUtil.randomString();

		String facetParam = term;

		AssetTagsSearchFacetDisplayContext assetTagsSearchFacetDisplayContext =
			createDisplayContext(facetParam);

		List<BucketDisplayContext> bucketDisplayContexts =
			assetTagsSearchFacetDisplayContext.getBucketDisplayContexts();

		Assert.assertEquals(
			bucketDisplayContexts.toString(), 1, bucketDisplayContexts.size());

		BucketDisplayContext bucketDisplayContext = bucketDisplayContexts.get(
			0);

		Assert.assertEquals(term, bucketDisplayContext.getBucketText());
		Assert.assertEquals(term, bucketDisplayContext.getFilterValue());
		Assert.assertEquals(0, bucketDisplayContext.getFrequency());
		Assert.assertTrue(bucketDisplayContext.isSelected());
		Assert.assertTrue(bucketDisplayContext.isFrequencyVisible());

		Assert.assertEquals(
			facetParam, assetTagsSearchFacetDisplayContext.getParameterValue());
		Assert.assertFalse(
			assetTagsSearchFacetDisplayContext.isNothingSelected());
		Assert.assertFalse(
			assetTagsSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testOneTerm() throws Exception {
		String term = RandomTestUtil.randomString();
		int frequency = RandomTestUtil.randomInt();

		setUpOneTermCollector(term, frequency);

		String facetParam = StringPool.BLANK;

		AssetTagsSearchFacetDisplayContext assetTagsSearchFacetDisplayContext =
			createDisplayContext(facetParam);

		List<BucketDisplayContext> bucketDisplayContexts =
			assetTagsSearchFacetDisplayContext.getBucketDisplayContexts();

		Assert.assertEquals(
			bucketDisplayContexts.toString(), 1, bucketDisplayContexts.size());

		BucketDisplayContext bucketDisplayContext = bucketDisplayContexts.get(
			0);

		Assert.assertEquals(term, bucketDisplayContext.getBucketText());
		Assert.assertEquals(term, bucketDisplayContext.getFilterValue());
		Assert.assertEquals(frequency, bucketDisplayContext.getFrequency());
		Assert.assertFalse(bucketDisplayContext.isSelected());
		Assert.assertTrue(bucketDisplayContext.isFrequencyVisible());

		Assert.assertEquals(
			facetParam, assetTagsSearchFacetDisplayContext.getParameterValue());
		Assert.assertTrue(
			assetTagsSearchFacetDisplayContext.isNothingSelected());
		Assert.assertFalse(
			assetTagsSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testOneTermWithPreviousSelection() throws Exception {
		String term = RandomTestUtil.randomString();
		int frequency = RandomTestUtil.randomInt();

		setUpOneTermCollector(term, frequency);

		String facetParam = term;

		AssetTagsSearchFacetDisplayContext assetTagsSearchFacetDisplayContext =
			createDisplayContext(facetParam);

		List<BucketDisplayContext> bucketDisplayContexts =
			assetTagsSearchFacetDisplayContext.getBucketDisplayContexts();

		Assert.assertEquals(
			bucketDisplayContexts.toString(), 1, bucketDisplayContexts.size());

		BucketDisplayContext bucketDisplayContext = bucketDisplayContexts.get(
			0);

		Assert.assertEquals(term, bucketDisplayContext.getBucketText());
		Assert.assertEquals(term, bucketDisplayContext.getFilterValue());
		Assert.assertEquals(frequency, bucketDisplayContext.getFrequency());
		Assert.assertTrue(bucketDisplayContext.isSelected());
		Assert.assertTrue(bucketDisplayContext.isFrequencyVisible());

		Assert.assertEquals(
			facetParam, assetTagsSearchFacetDisplayContext.getParameterValue());
		Assert.assertFalse(
			assetTagsSearchFacetDisplayContext.isNothingSelected());
		Assert.assertFalse(
			assetTagsSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testOrderByTermFrequencyAscending() throws Exception {
		List<TermCollector> termCollectors = _addTagsAndCreateTermCollectors(
			new String[] {"alpha", "charlie", "bravo", "delta"},
			new int[] {4, 5, 5, 6});

		_setUpMultipleTermCollectors(termCollectors);

		AssetTagsSearchFacetDisplayContext assetTagsSearchFacetDisplayContext =
			createDisplayContext(StringPool.BLANK, "count:asc");

		List<BucketDisplayContext> bucketDisplayContexts =
			assetTagsSearchFacetDisplayContext.getBucketDisplayContexts();

		String nameFrequencyString = _buildNameFrequencyString(
			bucketDisplayContexts);

		Assert.assertEquals(
			bucketDisplayContexts.toString(),
			"alpha:4|bravo:5|charlie:5|delta:6", nameFrequencyString);
	}

	@Test
	public void testOrderByTermFrequencyDescending() throws Exception {
		List<TermCollector> termCollectors = _addTagsAndCreateTermCollectors(
			new String[] {"alpha", "charlie", "bravo", "delta"},
			new int[] {4, 5, 5, 6});

		_setUpMultipleTermCollectors(termCollectors);

		AssetTagsSearchFacetDisplayContext assetTagsSearchFacetDisplayContext =
			createDisplayContext(StringPool.BLANK, "count:desc");

		List<BucketDisplayContext> bucketDisplayContexts =
			assetTagsSearchFacetDisplayContext.getBucketDisplayContexts();

		String nameFrequencyString = _buildNameFrequencyString(
			bucketDisplayContexts);

		Assert.assertEquals(
			bucketDisplayContexts.toString(),
			"delta:6|bravo:5|charlie:5|alpha:4", nameFrequencyString);
	}

	@Test
	public void testOrderByTermValueAscending() throws Exception {
		List<TermCollector> termCollectors = _addTagsAndCreateTermCollectors(
			"alpha", "bravo", "charlie", "bravo");

		_setUpMultipleTermCollectors(termCollectors);

		AssetTagsSearchFacetDisplayContext assetTagsSearchFacetDisplayContext =
			createDisplayContext(StringPool.BLANK, "key:asc");

		List<BucketDisplayContext> bucketDisplayContexts =
			assetTagsSearchFacetDisplayContext.getBucketDisplayContexts();

		String nameFrequencyString = _buildNameFrequencyString(
			bucketDisplayContexts);

		Assert.assertEquals(
			bucketDisplayContexts.toString(),
			"alpha:1|bravo:4|bravo:2|charlie:3", nameFrequencyString);
	}

	@Test
	public void testOrderByTermValueDescending() throws Exception {
		List<TermCollector> termCollectors = _addTagsAndCreateTermCollectors(
			"alpha", "bravo", "charlie", "bravo");

		_setUpMultipleTermCollectors(termCollectors);

		AssetTagsSearchFacetDisplayContext assetTagsSearchFacetDisplayContext =
			createDisplayContext(StringPool.BLANK, "key:desc");

		List<BucketDisplayContext> bucketDisplayContexts =
			assetTagsSearchFacetDisplayContext.getBucketDisplayContexts();

		String nameFrequencyString = _buildNameFrequencyString(
			bucketDisplayContexts);

		Assert.assertEquals(
			bucketDisplayContexts.toString(),
			"charlie:3|bravo:4|bravo:2|alpha:1", nameFrequencyString);
	}

	protected AssetTagsSearchFacetDisplayContext createDisplayContext(
			String facetParam)
		throws Exception {

		return createDisplayContext(facetParam, "count:desc");
	}

	protected AssetTagsSearchFacetDisplayContext createDisplayContext(
			String facetParam, String order)
		throws ConfigurationException {

		AssetTagsSearchFacetDisplayContextBuilder
			assetTagsSearchFacetDisplayContextBuilder =
				new AssetTagsSearchFacetDisplayContextBuilder(
					getRenderRequest());

		assetTagsSearchFacetDisplayContextBuilder.setDisplayStyle("cloud");
		assetTagsSearchFacetDisplayContextBuilder.setFacet(_facet);
		assetTagsSearchFacetDisplayContextBuilder.setFrequenciesVisible(true);
		assetTagsSearchFacetDisplayContextBuilder.setFrequencyThreshold(0);
		assetTagsSearchFacetDisplayContextBuilder.setMaxTerms(0);
		assetTagsSearchFacetDisplayContextBuilder.setOrder(order);
		assetTagsSearchFacetDisplayContextBuilder.setParameterName(
			_facet.getFieldId());
		assetTagsSearchFacetDisplayContextBuilder.setParameterValue(facetParam);

		return assetTagsSearchFacetDisplayContextBuilder.build();
	}

	protected TermCollector createTermCollector(String term, int frequency) {
		TermCollector termCollector = Mockito.mock(TermCollector.class);

		Mockito.doReturn(
			frequency
		).when(
			termCollector
		).getFrequency();

		Mockito.doReturn(
			term
		).when(
			termCollector
		).getTerm();

		return termCollector;
	}

	protected PortletDisplay getPortletDisplay() throws ConfigurationException {
		PortletDisplay portletDisplay = Mockito.mock(PortletDisplay.class);

		Mockito.doReturn(
			Mockito.mock(TagFacetPortletInstanceConfiguration.class)
		).when(
			portletDisplay
		).getPortletInstanceConfiguration(
			Mockito.any()
		);

		return portletDisplay;
	}

	protected RenderRequest getRenderRequest() throws ConfigurationException {
		RenderRequest renderRequest = Mockito.mock(RenderRequest.class);

		Mockito.doReturn(
			getThemeDisplay()
		).when(
			renderRequest
		).getAttribute(
			WebKeys.THEME_DISPLAY
		);

		return renderRequest;
	}

	protected ThemeDisplay getThemeDisplay() throws ConfigurationException {
		ThemeDisplay themeDisplay = Mockito.mock(ThemeDisplay.class);

		Mockito.doReturn(
			getPortletDisplay()
		).when(
			themeDisplay
		).getPortletDisplay();

		return themeDisplay;
	}

	protected void setUpOneTermCollector(String facetParam, int frequency) {
		Mockito.doReturn(
			Collections.singletonList(
				createTermCollector(facetParam, frequency))
		).when(
			_facetCollector
		).getTermCollectors();
	}

	private List<TermCollector> _addTagsAndCreateTermCollectors(String... terms)
		throws Exception {

		List<TermCollector> termCollectors = new ArrayList<>();

		int frequency = 1;

		for (String term : terms) {
			termCollectors.add(createTermCollector(term, frequency));

			frequency++;
		}

		return termCollectors;
	}

	private List<TermCollector> _addTagsAndCreateTermCollectors(
			String[] term, int[] frequencies)
		throws Exception {

		List<TermCollector> termCollectors = new ArrayList<>();

		for (int i = 1; i <= term.length; i++) {
			termCollectors.add(
				createTermCollector(term[i - 1], frequencies[i - 1]));
		}

		return termCollectors;
	}

	private String _buildNameFrequencyString(
			List<BucketDisplayContext> bucketDisplayContexts)
		throws Exception {

		StringBundler sb = new StringBundler(bucketDisplayContexts.size() * 4);

		for (BucketDisplayContext bucketDisplayContext :
				bucketDisplayContexts) {

			sb.append(bucketDisplayContext.getBucketText());
			sb.append(StringPool.COLON);
			sb.append(bucketDisplayContext.getFrequency());
			sb.append(StringPool.PIPE);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	private void _setUpMultipleTermCollectors(
		List<TermCollector> termCollectors) {

		Mockito.doReturn(
			termCollectors
		).when(
			_facetCollector
		).getTermCollectors();
	}

	private final Facet _facet = Mockito.mock(Facet.class);
	private final FacetCollector _facetCollector = Mockito.mock(
		FacetCollector.class);

}