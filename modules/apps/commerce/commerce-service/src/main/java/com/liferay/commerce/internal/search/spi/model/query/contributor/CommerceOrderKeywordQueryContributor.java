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

package com.liferay.commerce.internal.search.spi.model.query.contributor;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.search.generic.WildcardQueryImpl;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.query.QueryHelper;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Danny Situ
 */
@Component(
	enabled = false, immediate = true,
	property = "indexer.class.name=com.liferay.commerce.model.CommerceOrder",
	service = KeywordQueryContributor.class
)
public class CommerceOrderKeywordQueryContributor
	implements KeywordQueryContributor {

	@Override
	public void contribute(
		String keywords, BooleanQuery booleanQuery,
		KeywordQueryContributorHelper keywordQueryContributorHelper) {

		SearchContext searchContext =
			keywordQueryContributorHelper.getSearchContext();

		_queryHelper.addSearchTerm(
			booleanQuery, searchContext, Field.ENTRY_CLASS_PK, false);
		_queryHelper.addSearchTerm(
			booleanQuery, searchContext, "accountName", false);
		_queryHelper.addSearchTerm(
			booleanQuery, searchContext, "purchaseOrderNumber", false);
		_queryHelper.addSearchTerm(
			booleanQuery, searchContext, "externalReferenceCode", false);

		if (Validator.isNotNull(keywords)) {
			try {
				keywords = StringUtil.toLowerCase(keywords);

				booleanQuery.add(
					_getTrailingWildcardQuery(Field.ENTRY_CLASS_PK, keywords),
					BooleanClauseOccur.SHOULD);
				booleanQuery.add(
					_getTrailingWildcardQuery("accountName", keywords),
					BooleanClauseOccur.SHOULD);
			}
			catch (ParseException parseException) {
				throw new SystemException(parseException);
			}
		}
	}

	private WildcardQuery _getTrailingWildcardQuery(
		String field, String value) {

		return new WildcardQueryImpl(field, value + StringPool.STAR);
	}

	@Reference
	private QueryHelper _queryHelper;

}