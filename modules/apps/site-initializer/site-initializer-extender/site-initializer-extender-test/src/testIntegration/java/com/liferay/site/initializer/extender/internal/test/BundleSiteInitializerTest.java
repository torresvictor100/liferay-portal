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

package com.liferay.site.initializer.extender.internal.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.service.AssetListEntryLocalService;
import com.liferay.client.extension.model.ClientExtensionEntry;
import com.liferay.client.extension.service.ClientExtensionEntryLocalService;
import com.liferay.client.extension.type.CustomElementCET;
import com.liferay.client.extension.type.factory.CETFactory;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseLocalService;
import com.liferay.commerce.notification.model.CommerceNotificationTemplate;
import com.liferay.commerce.notification.service.CommerceNotificationTemplateLocalService;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.service.CPOptionLocalService;
import com.liferay.commerce.product.service.CPSpecificationOptionLocalService;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.headless.admin.list.type.dto.v1_0.ListTypeDefinition;
import com.liferay.headless.admin.list.type.dto.v1_0.ListTypeEntry;
import com.liferay.headless.admin.list.type.resource.v1_0.ListTypeDefinitionResource;
import com.liferay.headless.admin.user.dto.v1_0.Account;
import com.liferay.headless.admin.user.dto.v1_0.Organization;
import com.liferay.headless.admin.user.dto.v1_0.UserAccount;
import com.liferay.headless.admin.user.resource.v1_0.AccountResource;
import com.liferay.headless.admin.user.resource.v1_0.OrganizationResource;
import com.liferay.headless.admin.user.resource.v1_0.UserAccountResource;
import com.liferay.headless.admin.workflow.dto.v1_0.WorkflowDefinition;
import com.liferay.headless.admin.workflow.resource.v1_0.WorkflowDefinitionResource;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSpecification;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductSpecificationResource;
import com.liferay.headless.delivery.dto.v1_0.SitePage;
import com.liferay.headless.delivery.resource.v1_0.SitePageResource;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalFolderService;
import com.liferay.knowledge.base.constants.KBFolderConstants;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.model.KBFolder;
import com.liferay.knowledge.base.service.KBArticleLocalService;
import com.liferay.knowledge.base.service.KBFolderLocalService;
import com.liferay.knowledge.base.util.comparator.KBArticlePriorityComparator;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.layout.utility.page.kernel.constants.LayoutUtilityPageEntryConstants;
import com.liferay.layout.utility.page.model.LayoutUtilityPageEntry;
import com.liferay.layout.utility.page.service.LayoutUtilityPageEntryLocalService;
import com.liferay.notification.rest.dto.v1_0.NotificationTemplate;
import com.liferay.notification.rest.resource.v1_0.NotificationTemplateResource;
import com.liferay.object.admin.rest.dto.v1_0.ObjectRelationship;
import com.liferay.object.admin.rest.resource.v1_0.ObjectRelationshipResource;
import com.liferay.object.constants.ObjectActionExecutorConstants;
import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.model.ObjectAction;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectActionLocalService;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.petra.io.StreamUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.Theme;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserGroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DataGuard;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.service.access.policy.model.SAPEntry;
import com.liferay.portal.security.service.access.policy.service.SAPEntryLocalService;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.service.SegmentsEntryLocalService;
import com.liferay.segments.service.SegmentsExperienceLocalService;
import com.liferay.site.initializer.SiteInitializer;
import com.liferay.site.initializer.SiteInitializerFactory;
import com.liferay.site.initializer.SiteInitializerRegistry;
import com.liferay.site.navigation.menu.item.layout.constants.SiteNavigationMenuItemTypeConstants;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.service.SiteNavigationMenuItemLocalService;
import com.liferay.site.navigation.service.SiteNavigationMenuLocalService;
import com.liferay.style.book.model.StyleBookEntry;
import com.liferay.style.book.service.StyleBookEntryLocalService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletContext;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
@DataGuard(scope = DataGuard.Scope.METHOD)
@RunWith(Arquillian.class)
public class BundleSiteInitializerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			_group.getGroupId(), TestPropsValues.getUserId());

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setAttribute(
			WebKeys.USER, TestPropsValues.getUser());
		mockHttpServletRequest.setParameter(
			"currentURL", "http://www.liferay.com");

		_serviceContext.setRequest(mockHttpServletRequest);

		ServiceContextThreadLocal.pushServiceContext(_serviceContext);
	}

	@After
	public void tearDown() throws Exception {
		ServiceContextThreadLocal.popServiceContext();

		GroupLocalServiceUtil.deleteGroup(_group);

		// TODO We should not need to delete the object definition manually
		// because of DataGuardTestRule. However,
		// ObjectDefinitionLocalServiceImpl#deleteObjectDefinition checks
		// for PortalRunMode#isTestMode which is not returning true when the
		// DataGuardTestRule runs.

		ObjectDefinition objectDefinition1 =
			_objectDefinitionLocalService.fetchObjectDefinition(
				_serviceContext.getCompanyId(), "C_TestObjectDefinition1");

		if (objectDefinition1 != null) {
			_objectDefinitionLocalService.deleteObjectDefinition(
				objectDefinition1.getObjectDefinitionId());
		}

		ObjectDefinition objectDefinition2 =
			_objectDefinitionLocalService.fetchObjectDefinition(
				_serviceContext.getCompanyId(), "C_TestObjectDefinition2");

		if (objectDefinition2 != null) {
			_objectDefinitionLocalService.deleteObjectDefinition(
				objectDefinition2.getObjectDefinitionId());
		}

		ObjectDefinition objectDefinition3 =
			_objectDefinitionLocalService.fetchObjectDefinition(
				_serviceContext.getCompanyId(), "C_TestObjectDefinition3");

		if (objectDefinition3 != null) {
			_objectDefinitionLocalService.deleteObjectDefinition(
				objectDefinition3.getObjectDefinitionId());
		}
	}

	@Test
	public void testInitializeFromBundle() throws Exception {
		Bundle bundle1 = _getBundle(
			"/com.liferay.site.initializer.extender.test.bundle.1.jar");
		Bundle bundle2 = _getBundle(
			"/com.liferay.site.initializer.extender.test.bundle.2.jar");

		try {
			_test1(
				_siteInitializerRegistry.getSiteInitializer(
					bundle1.getSymbolicName()));
			_test2(
				_siteInitializerRegistry.getSiteInitializer(
					bundle2.getSymbolicName()));
		}
		finally {
			bundle1.uninstall();
			bundle2.uninstall();
		}
	}

	@Test
	public void testInitializeFromFile() throws Exception {
		File tempDir1 = _getTempDir(
			"/com.liferay.site.initializer.extender.test.bundle.1.jar");
		File tempDir2 = _getTempDir(
			"/com.liferay.site.initializer.extender.test.bundle.2.jar");

		try {
			_test1(
				_siteInitializerFactory.create(
					new File(tempDir1, "site-initializer"), null));
			_test2(
				_siteInitializerFactory.create(
					new File(tempDir2, "site-initializer"), null));
		}
		finally {
			FileUtil.deltree(tempDir1);
			FileUtil.deltree(tempDir2);
		}
	}

	private void _assertAccounts1() throws Exception {
		AccountResource.Builder accountResourceBuilder =
			_accountResourceFactory.create();

		AccountResource accountResource = accountResourceBuilder.user(
			_serviceContext.fetchUser()
		).build();

		UserAccountResource.Builder userAccountResourceBuilder =
			_userAccountResourceFactory.create();

		UserAccountResource userAccountResource =
			userAccountResourceBuilder.user(
				_serviceContext.fetchUser()
			).build();

		Account account1 = accountResource.getAccountByExternalReferenceCode(
			"TESTACC0001");

		Assert.assertNotNull(account1);
		Assert.assertEquals("Test Account 1", account1.getName());
		Assert.assertEquals("business", account1.getTypeAsString());

		_assertUserAccounts(account1.getId(), 1, userAccountResource);

		Account account2 = accountResource.getAccountByExternalReferenceCode(
			"TESTACC0002");

		Assert.assertNotNull(account2);
		Assert.assertEquals("Test Account 2", account2.getName());
		Assert.assertEquals("guest", account2.getTypeAsString());

		_assertUserAccounts(account2.getId(), 1, userAccountResource);

		Account account3 = accountResource.getAccountByExternalReferenceCode(
			"TESTACC0003");

		Assert.assertNotNull(account3);
		Assert.assertEquals("Test Account 3", account3.getName());
		Assert.assertEquals("person", account3.getTypeAsString());

		_assertUserAccounts(account3.getId(), 0, userAccountResource);
	}

	private void _assertAccounts2() throws Exception {
		AccountResource.Builder accountResourceBuilder =
			_accountResourceFactory.create();

		AccountResource accountResource = accountResourceBuilder.user(
			_serviceContext.fetchUser()
		).build();

		UserAccountResource.Builder userAccountResourceBuilder =
			_userAccountResourceFactory.create();

		UserAccountResource userAccountResource =
			userAccountResourceBuilder.user(
				_serviceContext.fetchUser()
			).build();

		Account account1 = accountResource.getAccountByExternalReferenceCode(
			"TESTACC0001");

		Assert.assertNotNull(account1);
		Assert.assertEquals("Test Account 1", account1.getName());
		Assert.assertEquals("business", account1.getTypeAsString());

		_assertUserAccounts(account1.getId(), 1, userAccountResource);

		Account account2 = accountResource.getAccountByExternalReferenceCode(
			"TESTACC0002");

		Assert.assertNotNull(account2);
		Assert.assertEquals("Test Account Guest", account2.getName());
		Assert.assertEquals("guest", account2.getTypeAsString());

		_assertUserAccounts(account2.getId(), 1, userAccountResource);

		Account account3 = accountResource.getAccountByExternalReferenceCode(
			"TESTACC0003");

		Assert.assertNotNull(account3);
		Assert.assertEquals("Test Account 3", account3.getName());
		Assert.assertEquals("person", account3.getTypeAsString());

		_assertUserAccounts(account3.getId(), 0, userAccountResource);

		Account account4 = accountResource.getAccountByExternalReferenceCode(
			"TESTACC0004");

		Assert.assertNotNull(account4);
		Assert.assertEquals("Test Account 4", account4.getName());
		Assert.assertEquals("person", account4.getTypeAsString());

		_assertUserAccounts(account4.getId(), 0, userAccountResource);
	}

	private void _assertAssetCategories() throws Exception {
		Group companyGroup = _groupLocalService.getCompanyGroup(
			_group.getCompanyId());

		AssetCategory testAssetCategory1 =
			_assetCategoryLocalService.
				fetchAssetCategoryByExternalReferenceCode(
					"TESTCAT0001", companyGroup.getGroupId());

		Assert.assertNotNull(testAssetCategory1);
		Assert.assertEquals(
			"Test Asset Category 1", testAssetCategory1.getName());

		AssetCategory testAssetCategory2 =
			_assetCategoryLocalService.fetchCategory(
				companyGroup.getGroupId(), testAssetCategory1.getCategoryId(),
				"Test Asset Category 2", testAssetCategory1.getVocabularyId());

		Assert.assertNotNull(testAssetCategory2);
		Assert.assertEquals(
			"TESTCAT0002", testAssetCategory2.getExternalReferenceCode());

		AssetCategory testAssetCategory3 =
			_assetCategoryLocalService.
				fetchAssetCategoryByExternalReferenceCode(
					"TESTCAT0003", _group.getGroupId());

		Assert.assertNotNull(testAssetCategory3);
		Assert.assertEquals(
			"Test Asset Category 3", testAssetCategory3.getName());

		AssetCategory testAssetCategory4 =
			_assetCategoryLocalService.fetchCategory(
				_group.getGroupId(), testAssetCategory3.getCategoryId(),
				"Test Asset Category 4", testAssetCategory3.getVocabularyId());

		Assert.assertNotNull(testAssetCategory4);
		Assert.assertEquals(
			"TESTCAT0004", testAssetCategory4.getExternalReferenceCode());
	}

	private void _assertAssetListEntries() {
		List<AssetListEntry> assetListEntries =
			_assetListEntryLocalService.getAssetListEntries(
				_group.getGroupId());

		Assert.assertEquals(
			assetListEntries.toString(), 2, assetListEntries.size());

		AssetListEntry assetListEntry1 = assetListEntries.get(0);

		Assert.assertEquals(
			"Test Asset List Entry 1", assetListEntry1.getTitle());
		Assert.assertEquals(
			"com.liferay.journal.model.JournalArticle",
			assetListEntry1.getAssetEntryType());

		AssetListEntry assetListEntry2 = assetListEntries.get(1);

		Assert.assertEquals(
			"Test Asset List Entry 2", assetListEntry2.getTitle());
		Assert.assertEquals(
			"com.liferay.journal.model.JournalArticle",
			assetListEntry2.getAssetEntryType());
	}

	private void _assertAssetVocabularies() throws Exception {
		Group companyGroup = _groupLocalService.getCompanyGroup(
			_group.getCompanyId());

		AssetVocabulary testAssetVocabulary1 =
			_assetVocabularyLocalService.fetchGroupVocabulary(
				companyGroup.getGroupId(), "Test Asset Vocabulary 1");

		Assert.assertNotNull(testAssetVocabulary1);
		Assert.assertEquals(
			"TESTVOC0001", testAssetVocabulary1.getExternalReferenceCode());

		AssetVocabulary testAssetVocabulary2 =
			_assetVocabularyLocalService.fetchGroupVocabulary(
				_group.getGroupId(), "Test Asset Vocabulary 2");

		Assert.assertNotNull(testAssetVocabulary2);
		Assert.assertEquals(
			"TESTVOC0002", testAssetVocabulary2.getExternalReferenceCode());

		_assertAssetCategories();
	}

	private void _assertClientExtension() throws Exception {
		ClientExtensionEntry clientExtensionEntry =
			_clientExtensionEntryLocalService.
				fetchClientExtensionEntryByExternalReferenceCode(
					"ERC001", _group.getCompanyId());

		Assert.assertNotNull(clientExtensionEntry);

		CustomElementCET customElementCET =
			(CustomElementCET)_cetFactory.create(clientExtensionEntry);

		Assert.assertEquals(
			"liferay-test-remote-app", customElementCET.getHTMLElementName());
		Assert.assertEquals(
			"category.client-extensions",
			customElementCET.getPortletCategoryName());
	}

	private void _assertCommerceCatalogs() throws Exception {
		CommerceCatalog commerceCatalog1 =
			_commerceCatalogLocalService.
				fetchCommerceCatalogByExternalReferenceCode(
					"TESTCATG0001", _group.getCompanyId());

		Assert.assertNotNull(commerceCatalog1);
		Assert.assertEquals(
			"Test Commerce Catalog 1", commerceCatalog1.getName());

		CommerceCatalog commerceCatalog2 =
			_commerceCatalogLocalService.
				fetchCommerceCatalogByExternalReferenceCode(
					"TESTCATG0002", _group.getCompanyId());

		Assert.assertNotNull(commerceCatalog2);
		Assert.assertEquals(
			"Test Commerce Catalog 2", commerceCatalog2.getName());

		_assertCPDefinition();
		_assertCPOption();
	}

	private void _assertCommerceChannel() throws Exception {
		CommerceChannel commerceChannel =
			_commerceChannelLocalService.fetchCommerceChannelBySiteGroupId(
				_group.getGroupId());

		Assert.assertNotNull(commerceChannel);
		Assert.assertEquals(
			"TESTVOC0001", commerceChannel.getExternalReferenceCode());
		Assert.assertEquals("Test Commerce Channel", commerceChannel.getName());
		Assert.assertEquals("site", commerceChannel.getType());

		_assertCommerceNotificationTemplate(commerceChannel);
		_assertDefaultCPDisplayLayout(commerceChannel);
	}

	private void _assertCommerceInventoryWarehouse() {
		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_commerceInventoryWarehouseLocalService.
				fetchCommerceInventoryWarehouseByExternalReferenceCode(
					"TESTWARE0001", _group.getCompanyId());

		Assert.assertNotNull(commerceInventoryWarehouse);
		Assert.assertEquals(
			"Test Commerce Warehouse",
			commerceInventoryWarehouse.getName(LocaleUtil.getSiteDefault()));
	}

	private void _assertCommerceNotificationTemplate(
			CommerceChannel commerceChannel)
		throws Exception {

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.fetchObjectDefinition(
				_group.getCompanyId(), "C_TestObjectDefinition1");

		Assert.assertNotNull(objectDefinition);

		List<CommerceNotificationTemplate> commerceNotificationTemplates =
			_commerceNotificationTemplateLocalService.
				getCommerceNotificationTemplates(
					commerceChannel.getGroupId(),
					"com.liferay.object.model.ObjectDefinition#" +
						objectDefinition.getObjectDefinitionId() + "#create",
					true);

		CommerceNotificationTemplate commerceNotificationTemplate =
			commerceNotificationTemplates.get(0);

		Assert.assertNotNull(commerceNotificationTemplate);
		Assert.assertEquals(
			"Test Commerce Notification Template",
			commerceNotificationTemplate.getName());
	}

	private void _assertCommerceSpecificationProducts() throws Exception {
		CPSpecificationOption cpSpecificationOption =
			_cpSpecificationOptionLocalService.fetchCPSpecificationOption(
				_serviceContext.getCompanyId(), "test-product-specification-1");

		Assert.assertNotNull(cpSpecificationOption);

		CPDefinition cpDefinition =
			_cpDefinitionLocalService.
				fetchCPDefinitionByCProductExternalReferenceCode(
					"TESTPROD001", _serviceContext.getCompanyId());

		Assert.assertNotNull(cpDefinition);

		ProductSpecificationResource.Builder
			productSpecificationResourceBuilder =
				_productSpecificationResourceFactory.create();

		ProductSpecificationResource productSpecificationResource =
			productSpecificationResourceBuilder.user(
				_serviceContext.fetchUser()
			).build();

		Page<ProductSpecification> productSpecificationPage =
			productSpecificationResource.getProductIdProductSpecificationsPage(
				cpDefinition.getCProductId(), Pagination.of(1, 10));

		ProductSpecification productSpecification =
			productSpecificationPage.fetchFirstItem();

		Assert.assertNotNull(productSpecification);
		Assert.assertEquals(
			"test-product-specification-1",
			productSpecification.getSpecificationKey());
	}

	private void _assertCPDefinition() throws Exception {
		CPDefinition cpDefinition =
			_cpDefinitionLocalService.
				fetchCPDefinitionByCProductExternalReferenceCode(
					"TESTPROD001", _group.getCompanyId());

		Assert.assertNotNull(cpDefinition);
		Assert.assertEquals("Test Commerce Product", cpDefinition.getName());

		CPAttachmentFileEntry cpAttachmentFileEntry =
			_cpDefinitionLocalService.getDefaultImageCPAttachmentFileEntry(
				cpDefinition.getCPDefinitionId());

		Assert.assertNotNull(cpAttachmentFileEntry);

		FileEntry fileEntry = cpAttachmentFileEntry.fetchFileEntry();

		Assert.assertEquals(
			"test_commerce_product.png", fileEntry.getFileName());
	}

	private void _assertCPInstanceProperties() throws Exception {
		CPDefinition cpDefinition =
			_cpDefinitionLocalService.
				fetchCPDefinitionByCProductExternalReferenceCode(
					"TESTPROD001", _group.getCompanyId());

		CPInstance cpInstance1 = _cpInstanceLocalService.getCPInstance(
			cpDefinition.getCPDefinitionId(), "TEST VALUE 1");

		Assert.assertNotNull(cpInstance1);

		BigDecimal actualPrice = cpInstance1.getPrice();

		Assert.assertEquals(60.0, actualPrice.doubleValue(), 0.0001);

		BigDecimal actualPromoPrice = cpInstance1.getPromoPrice();

		Assert.assertEquals(25.0, actualPromoPrice.doubleValue(), 0.0001);

		CPInstance cpInstance2 = _cpInstanceLocalService.getCPInstance(
			cpDefinition.getCPDefinitionId(), "TEST VALUE 2");

		Assert.assertNotNull(cpInstance2);
		Assert.assertTrue(cpInstance2.isSubscriptionEnabled());
	}

	private void _assertCPOption() throws Exception {
		CPOption cpOption1 = _cpOptionLocalService.fetchCPOption(
			_group.getCompanyId(), "test-option-1");

		Assert.assertNotNull(cpOption1);
		Assert.assertEquals(
			"Test Option 1", cpOption1.getName(LocaleUtil.getSiteDefault()));

		CPOption cpOption2 = _cpOptionLocalService.fetchCPOption(
			_group.getCompanyId(), "test-option-2");

		Assert.assertNotNull(cpOption2);
		Assert.assertEquals(
			"Test Option 2", cpOption2.getName(LocaleUtil.getSiteDefault()));

		CPDefinition cpDefinition =
			_cpDefinitionLocalService.
				fetchCPDefinitionByCProductExternalReferenceCode(
					"TESTPROD001", _group.getCompanyId());

		Assert.assertNotNull(cpDefinition);

		List<CPDefinitionOptionRel> cpDefinitionOptionRels =
			cpDefinition.getCPDefinitionOptionRels();

		Assert.assertEquals(
			cpDefinitionOptionRels.toString(), 2,
			cpDefinitionOptionRels.size());
	}

	private void _assertDDMStructure() {
		DDMStructure ddmStructure = _ddmStructureLocalService.fetchStructure(
			_group.getGroupId(),
			_portal.getClassNameId(JournalArticle.class.getName()),
			"TEST DDM STRUCTURE NAME");

		Assert.assertNotNull(ddmStructure);
		Assert.assertTrue(ddmStructure.hasField("aField"));
	}

	private void _assertDDMTemplate() {
		DDMTemplate ddmTemplate = _ddmTemplateLocalService.fetchTemplate(
			_group.getGroupId(),
			_portal.getClassNameId(DDMStructure.class.getName()),
			"TEST DDM TEMPLATE KEY");

		Assert.assertNotNull(ddmTemplate);
		Assert.assertEquals("${aField.getData()}", ddmTemplate.getScript());
	}

	private void _assertDefaultCPDisplayLayout(CommerceChannel commerceChannel)
		throws Exception {

		Settings settings = _settingsFactory.getSettings(
			new GroupServiceSettingsLocator(
				commerceChannel.getGroupId(),
				CPConstants.RESOURCE_NAME_CP_DISPLAY_LAYOUT));

		ModifiableSettings modifiableSettings =
			settings.getModifiableSettings();

		String productLayoutUuid = modifiableSettings.getValue(
			"productLayoutUuid", null);

		Assert.assertNotNull(productLayoutUuid);

		Layout publicLayout = _layoutLocalService.getLayoutByFriendlyURL(
			_group.getGroupId(), false, "/test-public-layout");

		Assert.assertEquals(productLayoutUuid, publicLayout.getUuid());
	}

	private void _assertDLFileEntry() throws Exception {
		DLFileEntry dlFileEntry = _dlFileEntryLocalService.getFileEntry(
			_group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			"Table of Contents.markdown");

		String string = new String(
			StreamUtil.toByteArray(
				_dlFileEntryLocalService.getFileAsStream(
					dlFileEntry.getFileEntryId(), dlFileEntry.getVersion())));

		Assert.assertTrue(string.contains("## Old Testament"));
		Assert.assertTrue(string.contains("1. Genesis"));
		Assert.assertTrue(string.contains("## New Testament"));
		Assert.assertTrue(string.contains("1. Revelation"));
	}

	private void _assertExpandoColumns() {
		ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(
			_serviceContext.getCompanyId(),
			"com.liferay.commerce.product.model.CPDefinition");

		Assert.assertNotNull(expandoBridge);
		Assert.assertEquals(
			expandoBridge.getAttribute("Test Expando Column 1"), 0.1);
		Assert.assertEquals(
			"Test Expando Column Value 2",
			expandoBridge.getAttribute("Test Expando Column 2"));
		Assert.assertNull(expandoBridge.getAttribute("Test Expando Column 3"));
	}

	private void _assertFragmentEntries() throws Exception {
		Group companyGroup = _groupLocalService.getCompanyGroup(
			_serviceContext.getCompanyId());

		FragmentEntry testFragmentEntry1 =
			_fragmentEntryLocalService.fetchFragmentEntry(
				companyGroup.getGroupId(), "test-fragment-entry-1");

		Assert.assertNotNull(testFragmentEntry1);
		Assert.assertEquals(
			"Test Fragment Entry 1", testFragmentEntry1.getName());

		FragmentEntry testFragmentEntry2 =
			_fragmentEntryLocalService.fetchFragmentEntry(
				_group.getGroupId(), "test-fragment-entry-2");

		Assert.assertNotNull(testFragmentEntry2);
		Assert.assertEquals(
			"Test Fragment Entry 2", testFragmentEntry2.getName());
	}

	private void _assertJournalArticles() throws Exception {
		JournalArticle journalArticle1 =
			_journalArticleLocalService.fetchArticle(
				_group.getGroupId(), "test-journal-article-1");

		Assert.assertNotNull(journalArticle1);
		Assert.assertEquals(
			"TEST DDM TEMPLATE KEY", journalArticle1.getDDMTemplateKey());
		Assert.assertEquals(
			"Test Journal Article 1", journalArticle1.getTitle());

		JournalArticle journalArticle2 =
			_journalArticleLocalService.fetchArticle(
				_group.getGroupId(), "test-journal-article-2");

		Assert.assertNotNull(journalArticle2);
		Assert.assertEquals(
			"TEST DDM TEMPLATE KEY", journalArticle2.getDDMTemplateKey());
		Assert.assertEquals(
			"Test Journal Article 2", journalArticle2.getTitle());

		List<JournalFolder> journalFolders = _journalFolderService.getFolders(
			_group.getGroupId());

		Assert.assertEquals(
			journalFolders.toString(), 2, journalFolders.size());

		JournalFolder journalFolder1 = journalFolders.get(0);

		Assert.assertEquals(
			"JOURNALFOLDER001", journalFolder1.getExternalReferenceCode());
		Assert.assertEquals("Test Journal Folder 1", journalFolder1.getName());

		JournalFolder journalFolder2 = journalFolders.get(1);

		Assert.assertEquals(
			"JOURNALFOLDER002", journalFolder2.getExternalReferenceCode());
		Assert.assertEquals("Test Journal Folder 2", journalFolder2.getName());
	}

	private void _assertKBArticles() throws Exception {
		KBFolder kbFolder = _kbFolderLocalService.getKBFolderByUrlTitle(
			_group.getGroupId(), KBFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			"test-kb-folder-name");

		Assert.assertEquals(
			"TESTKBFOLDER1", kbFolder.getExternalReferenceCode());
		Assert.assertEquals("Test KB Folder Name", kbFolder.getName());

		List<KBArticle> kbFolderKBArticles =
			_kbArticleLocalService.getKBArticles(
				_group.getGroupId(), kbFolder.getKbFolderId(),
				WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new KBArticlePriorityComparator(true));

		Assert.assertEquals(
			kbFolderKBArticles.toString(), 1, kbFolderKBArticles.size());

		KBArticle kbArticle1 = kbFolderKBArticles.get(0);

		Assert.assertEquals(
			"TESTKBARTICLE1", kbArticle1.getExternalReferenceCode());
		Assert.assertEquals("Test KB Article 1 Title", kbArticle1.getTitle());
		Assert.assertEquals(
			"This is the body for Test KB Article 1.", kbArticle1.getContent());

		List<KBArticle> kbArticleKBArticles =
			_kbArticleLocalService.getKBArticles(
				_group.getGroupId(), kbArticle1.getResourcePrimKey(),
				WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new KBArticlePriorityComparator(true));

		Assert.assertEquals(
			kbArticleKBArticles.toString(), 2, kbArticleKBArticles.size());

		KBArticle kbArticle2 = kbArticleKBArticles.get(0);

		Assert.assertEquals(
			"TESTKBARTICLE2", kbArticle2.getExternalReferenceCode());
		Assert.assertEquals("Test KB Article 2 Title", kbArticle2.getTitle());
		Assert.assertEquals(
			"This is the body for Test KB Article 2.", kbArticle2.getContent());

		KBArticle kbArticle3 = kbArticleKBArticles.get(1);

		Assert.assertEquals(
			"TESTKBARTICLE3", kbArticle3.getExternalReferenceCode());
		Assert.assertEquals("Test KB Article 3 Title", kbArticle3.getTitle());
		Assert.assertEquals(
			"This is the body for Test KB Article 3.", kbArticle3.getContent());
	}

	private void _assertLayoutPageTemplateEntries() throws Exception {

		// Test Display Page Template

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.fetchLayoutPageTemplateEntry(
				_group.getGroupId(), "Test Display Page Template",
				LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE);

		Assert.assertNotNull(layoutPageTemplateEntry);
		Assert.assertEquals(
			"Test Display Page Template", layoutPageTemplateEntry.getName());

		List<FragmentEntryLink> fragmentEntryLinks =
			_fragmentEntryLinkLocalService.getFragmentEntryLinksByPlid(
				_group.getGroupId(), layoutPageTemplateEntry.getPlid());

		Assert.assertEquals(
			fragmentEntryLinks.toString(), 1, fragmentEntryLinks.size());

		FragmentEntryLink fragmentEntryLink = fragmentEntryLinks.get(0);

		JSONObject editableValuesJSONObject = JSONFactoryUtil.createJSONObject(
			fragmentEntryLink.getEditableValues());

		Assert.assertNotNull(editableValuesJSONObject);

		JSONObject editableFragmentEntryProcessorJSONObject =
			editableValuesJSONObject.getJSONObject(
				"com.liferay.fragment.entry.processor.editable." +
					"EditableFragmentEntryProcessor");

		Assert.assertNotNull(editableFragmentEntryProcessorJSONObject);

		JSONObject linkJSONObject =
			editableFragmentEntryProcessorJSONObject.getJSONObject("link");

		Assert.assertNotNull(linkJSONObject);

		JSONObject configJSONObject = linkJSONObject.getJSONObject("config");

		Assert.assertNotNull(configJSONObject);

		JSONObject layoutJSONObject = configJSONObject.getJSONObject("layout");

		Assert.assertNotNull(layoutJSONObject);
		Assert.assertEquals(
			_group.getGroupId(), layoutJSONObject.getLong("groupId"));
		Assert.assertFalse(layoutJSONObject.getBoolean("privateLayout"));
		Assert.assertEquals("/home", layoutJSONObject.getString("value"));

		// Test Master Page

		layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.fetchLayoutPageTemplateEntry(
				_group.getGroupId(), "Test Master Page",
				LayoutPageTemplateEntryTypeConstants.TYPE_MASTER_LAYOUT);

		Assert.assertNotNull(layoutPageTemplateEntry);
		Assert.assertEquals(
			"Test Master Page", layoutPageTemplateEntry.getName());
	}

	private void _assertLayouts() throws Exception {
		_assertPrivateLayouts();
		_assertPublicLayouts();
	}

	private void _assertLayoutSets() throws Exception {
		LayoutSet privateLayoutSet = _layoutSetLocalService.fetchLayoutSet(
			_group.getGroupId(), true);

		Assert.assertNotNull(privateLayoutSet);

		Theme privateTheme = privateLayoutSet.getTheme();

		Assert.assertEquals("Dialect", privateTheme.getName());

		UnicodeProperties privateLayoutSetUnicodeProperties =
			privateLayoutSet.getSettingsProperties();

		Assert.assertTrue(
			GetterUtil.getBoolean(
				privateLayoutSetUnicodeProperties.getProperty(
					"lfr-theme:regular:show-footer")));
		Assert.assertFalse(
			GetterUtil.getBoolean(
				privateLayoutSetUnicodeProperties.getProperty(
					"lfr-theme:regular:show-header")));

		LayoutSet publicLayoutSet = _layoutSetLocalService.fetchLayoutSet(
			_group.getGroupId(), false);

		Assert.assertNotNull(publicLayoutSet);

		Theme publicTheme = publicLayoutSet.getTheme();

		Assert.assertEquals("Dialect", publicTheme.getName());

		UnicodeProperties publicLayoutSetUnicodeProperties =
			publicLayoutSet.getSettingsProperties();

		Assert.assertFalse(
			GetterUtil.getBoolean(
				publicLayoutSetUnicodeProperties.getProperty(
					"lfr-theme:regular:show-footer")));
		Assert.assertTrue(
			GetterUtil.getBoolean(
				publicLayoutSetUnicodeProperties.getProperty(
					"lfr-theme:regular:show-header")));
	}

	private void _assertLayoutStructureItems(
		LayoutPageTemplateStructure layoutPageTemplateStructure,
		int layoutStructureItemsCount, long segmentsExperienceId) {

		LayoutStructure layoutStructure = LayoutStructure.of(
			layoutPageTemplateStructure.getData(segmentsExperienceId));

		List<LayoutStructureItem> layoutStructureItems =
			layoutStructure.getLayoutStructureItems();

		Assert.assertEquals(
			layoutStructureItems.toString(), layoutStructureItemsCount,
			layoutStructureItems.size());
	}

	private void _assertLayoutUtilityPageEntries() {
		if (!FeatureFlagManagerUtil.isEnabled("LPS-162765")) {
			return;
		}

		LayoutUtilityPageEntry defaultLayoutUtilityPageEntry =
			_layoutUtilityPageEntryLocalService.
				fetchDefaultLayoutUtilityPageEntry(
					_group.getGroupId(),
					LayoutUtilityPageEntryConstants.TYPE_SC_NOT_FOUND);

		Assert.assertNotNull(defaultLayoutUtilityPageEntry);
		Assert.assertEquals(
			"Test Default Layout Utility Page Entry",
			defaultLayoutUtilityPageEntry.getName());

		LayoutUtilityPageEntry layoutUtilityPageEntry =
			_layoutUtilityPageEntryLocalService.
				fetchLayoutUtilityPageEntryByExternalReferenceCode(
					"test-layout-utility-page-entry", _group.getGroupId());

		Assert.assertNotNull(layoutUtilityPageEntry);
		Assert.assertEquals(
			"Test Layout Utility Page Entry", layoutUtilityPageEntry.getName());
		Assert.assertEquals(
			LayoutUtilityPageEntryConstants.TYPE_SC_NOT_FOUND,
			layoutUtilityPageEntry.getType());
		Assert.assertFalse(
			layoutUtilityPageEntry.isDefaultLayoutUtilityPageEntry());
	}

	private void _assertListTypeDefinitions() throws Exception {
		ListTypeDefinitionResource.Builder listTypeDefinitionResourceBuilder =
			_listTypeDefinitionResourceFactory.create();

		ListTypeDefinitionResource listTypeDefinitionResource =
			listTypeDefinitionResourceBuilder.user(
				_serviceContext.fetchUser()
			).build();

		Page<ListTypeDefinition> listTypeDefinitionsPage =
			listTypeDefinitionResource.getListTypeDefinitionsPage(
				null, null,
				listTypeDefinitionResource.toFilter(
					"name eq 'Test List Type Definition'"),
				null, null);

		ListTypeDefinition listTypeDefinition =
			listTypeDefinitionsPage.fetchFirstItem();

		Assert.assertNotNull(listTypeDefinition);

		ListTypeEntry[] listTypeEntries =
			listTypeDefinition.getListTypeEntries();

		ListTypeEntry listTypeEntry1 = listTypeEntries[0];

		Assert.assertNotNull(listTypeEntry1);
		Assert.assertEquals("testlisttypeentry1", listTypeEntry1.getKey());

		ListTypeEntry listTypeEntry2 = listTypeEntries[1];

		Assert.assertNotNull(listTypeEntry2);
		Assert.assertEquals("testlisttypeentry2", listTypeEntry2.getKey());
	}

	private void _assertNotificationTemplate() throws Exception {
		NotificationTemplateResource.Builder
			notificationTemplateResourceBuilder =
				_notificationTemplateResourceFactory.create();

		NotificationTemplateResource notificationTemplateResource =
			notificationTemplateResourceBuilder.user(
				_serviceContext.fetchUser()
			).build();

		Page<NotificationTemplate> notificationTemplatesPage =
			notificationTemplateResource.getNotificationTemplatesPage(
				null, null, null, null, null);

		Assert.assertEquals(
			notificationTemplatesPage.toString(), 1,
			notificationTemplatesPage.getTotalCount());

		NotificationTemplate notificationTemplate =
			notificationTemplatesPage.fetchFirstItem();

		Assert.assertEquals(
			"Test Notification Template", notificationTemplate.getName());

		Map<String, String> subjectMap = notificationTemplate.getSubject();

		Assert.assertNotNull(subjectMap);

		String subject = subjectMap.get("en_US");

		Assert.assertTrue(
			Objects.equals(
				subject, StringUtil.getTitleCase(subject, true, "DXP")));
	}

	private void _assertObjectActions(
		int objectActionsCount, ObjectDefinition objectDefinition) {

		List<ObjectAction> objectActions =
			_objectActionLocalService.getObjectActions(
				objectDefinition.getObjectDefinitionId());

		Assert.assertEquals(
			objectActions.toString(), objectActionsCount, objectActions.size());

		for (ObjectAction objectAction : objectActions) {
			String objectActionExecutorKey =
				objectAction.getObjectActionExecutorKey();

			UnicodeProperties parametersUnicodeProperties =
				objectAction.getParametersUnicodeProperties();

			if (Objects.equals(
					objectActionExecutorKey,
					ObjectActionExecutorConstants.KEY_GROOVY)) {

				String script = parametersUnicodeProperties.get("script");

				Assert.assertNotNull(script);
			}
			else if (Objects.equals(
						objectActionExecutorKey,
						ObjectActionExecutorConstants.KEY_WEBHOOK)) {

				String secret = parametersUnicodeProperties.get("secret");
				String url = parametersUnicodeProperties.get("url");

				Assert.assertNotNull(secret);
				Assert.assertNotNull(url);
			}
		}
	}

	private void _assertObjectDefinitions() throws Exception {
		ObjectDefinition objectDefinition1 =
			_objectDefinitionLocalService.fetchObjectDefinition(
				_group.getCompanyId(), "C_TestObjectDefinition1");

		Assert.assertEquals(objectDefinition1.isSystem(), false);
		Assert.assertEquals(
			objectDefinition1.getStatus(), WorkflowConstants.STATUS_APPROVED);

		_assertObjectActions(3, objectDefinition1);
		_assertObjectEntries(_group.getGroupId(), objectDefinition1, 0);
		_assertObjectFields(objectDefinition1, 8);
		_assertObjectRelationships(objectDefinition1, _serviceContext);

		ObjectDefinition objectDefinition2 =
			_objectDefinitionLocalService.fetchObjectDefinition(
				_group.getCompanyId(), "C_TestObjectDefinition2");

		Assert.assertEquals(objectDefinition2.isSystem(), false);
		Assert.assertEquals(
			objectDefinition2.getStatus(), WorkflowConstants.STATUS_APPROVED);

		_assertObjectActions(2, objectDefinition2);
		_assertObjectEntries(_group.getGroupId(), objectDefinition2, 0);
		_assertObjectFields(objectDefinition2, 9);

		ObjectDefinition objectDefinition3 =
			_objectDefinitionLocalService.fetchObjectDefinition(
				_group.getCompanyId(), "C_TestObjectDefinition3");

		Assert.assertEquals(objectDefinition3.isSystem(), false);
		Assert.assertEquals(
			objectDefinition3.getScope(),
			ObjectDefinitionConstants.SCOPE_COMPANY);
		Assert.assertEquals(
			objectDefinition3.getStatus(), WorkflowConstants.STATUS_APPROVED);

		_assertObjectActions(0, objectDefinition3);
		_assertObjectEntries(0, objectDefinition3, 5);
		_assertObjectFields(objectDefinition3, 7);
	}

	private void _assertObjectEntries(
			long groupId, ObjectDefinition objectDefinition,
			int objectEntriesCount)
		throws Exception {

		List<ObjectEntry> objectEntries =
			_objectEntryLocalService.getObjectEntries(
				groupId, objectDefinition.getObjectDefinitionId(), -1, -1);

		Assert.assertNotNull(objectEntries);
		Assert.assertEquals(
			objectEntries.toString(), objectEntriesCount, objectEntries.size());

		for (ObjectEntry objectEntry : objectEntries) {
			Map<String, Serializable> objectEntryValues =
				objectEntry.getValues();

			for (Map.Entry<String, Serializable> entry :
					objectEntryValues.entrySet()) {

				Assert.assertTrue(Validator.isNotNull(entry.getValue()));
			}
		}
	}

	private void _assertObjectFields(
		ObjectDefinition objectDefinition, int objectFieldsCount) {

		Assert.assertEquals(
			objectFieldsCount,
			_objectFieldLocalService.getObjectFieldsCount(
				objectDefinition.getObjectDefinitionId()));
	}

	private void _assertObjectRelationships(
			ObjectDefinition objectDefinition, ServiceContext serviceContext)
		throws Exception {

		ObjectRelationshipResource.Builder objectRelationshipResourceBuilder =
			_objectRelationshipResourceFactory.create();

		ObjectRelationshipResource objectRelationshipResource =
			objectRelationshipResourceBuilder.user(
				serviceContext.fetchUser()
			).build();

		ObjectDefinition objectDefinition1 =
			_objectDefinitionLocalService.fetchSystemObjectDefinition("User");

		Page<ObjectRelationship> page1 =
			objectRelationshipResource.
				getObjectDefinitionObjectRelationshipsPage(
					objectDefinition1.getObjectDefinitionId(), null,
					objectRelationshipResource.toFilter("name eq 'testOR1'"),
					null);

		Assert.assertNotNull(page1);

		ObjectRelationship existingObjectRelationship1 = page1.fetchFirstItem();

		Assert.assertEquals(
			"TestObjectDefinition1",
			existingObjectRelationship1.getObjectDefinitionName2());

		ObjectRelationship.Type objectRelationshipType1 =
			existingObjectRelationship1.getType();

		Assert.assertEquals("oneToMany", objectRelationshipType1.toString());

		Page<ObjectRelationship> page2 =
			objectRelationshipResource.
				getObjectDefinitionObjectRelationshipsPage(
					objectDefinition.getObjectDefinitionId(), null,
					objectRelationshipResource.toFilter("name eq 'testOR2'"),
					null);

		Assert.assertNotNull(page2);

		ObjectRelationship existingObjectRelationship2 = page2.fetchFirstItem();

		Assert.assertEquals(
			"TestObjectDefinition2",
			existingObjectRelationship2.getObjectDefinitionName2());

		ObjectRelationship.Type objectRelationshipType2 =
			existingObjectRelationship2.getType();

		Assert.assertEquals("manyToMany", objectRelationshipType2.toString());
	}

	private void _assertOrganizations() throws Exception {
		OrganizationResource.Builder organizationResourceBuilder =
			_organizationResourceFactory.create();

		OrganizationResource organizationResource =
			organizationResourceBuilder.user(
				_serviceContext.fetchUser()
			).build();

		Page<Organization> organizationsPage1 =
			organizationResource.getOrganizationsPage(
				null, null,
				organizationResource.toFilter("name eq 'Test Organization 1'"),
				null, null);

		Organization organization1 = organizationsPage1.fetchFirstItem();

		Assert.assertNotNull(organization1);

		UserAccountResource.Builder userAccountResourceBuilder =
			_userAccountResourceFactory.create();

		UserAccountResource userAccountResource =
			userAccountResourceBuilder.user(
				_serviceContext.fetchUser()
			).build();

		_assertUserOrganizations(organization1.getId(), 1, userAccountResource);

		Page<Organization> organizationsPage2 =
			organizationResource.getOrganizationsPage(
				null, null,
				organizationResource.toFilter("name eq 'Test Organization 2'"),
				null, null);

		Organization organization2 = organizationsPage2.fetchFirstItem();

		Assert.assertNotNull(organization2);

		Assert.assertEquals(1, organizationsPage2.getTotalCount());

		_assertUserOrganizations(organization2.getId(), 1, userAccountResource);

		Page<Organization> organizationsPage3 =
			organizationResource.getOrganizationChildOrganizationsPage(
				organization2.getId(), null, null, null, null, null);

		Organization organization3 = organizationsPage3.fetchFirstItem();

		Assert.assertNotNull(organization3);
		Assert.assertEquals("Test Organization 3", organization3.getName());

		_assertUserOrganizations(organization3.getId(), 0, userAccountResource);
	}

	private void _assertPermissions() throws Exception {
		_assertRoles();

		_assertResourcePermission1();
	}

	private void _assertPortletSettings() {
		DDMTemplate ddmTemplate = _ddmTemplateLocalService.fetchTemplate(
			_group.getGroupId(),
			_portal.getClassNameId("com.liferay.portal.kernel.theme.NavItem"),
			"TEST-PORTLET-SETTINGS-1");

		Assert.assertNotNull(ddmTemplate);
		Assert.assertEquals(
			"TEST PORTLET SETTINGS 1",
			ddmTemplate.getName(LocaleUtil.getSiteDefault()));
		Assert.assertEquals("${aField.getData()}", ddmTemplate.getScript());
	}

	private void _assertPrivateLayouts() {
		List<Layout> privateLayouts = _layoutLocalService.getLayouts(
			_group.getGroupId(), true,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		Assert.assertEquals(
			privateLayouts.toString(), 1, privateLayouts.size());

		Layout privateLayout = privateLayouts.get(0);

		Assert.assertEquals(
			"Test Private Layout",
			privateLayout.getName(LocaleUtil.getSiteDefault()));
		Assert.assertEquals("content", privateLayout.getType());
		Assert.assertTrue(privateLayout.isHidden());

		List<Layout> privateChildLayouts = privateLayout.getAllChildren();

		Assert.assertEquals(
			privateChildLayouts.toString(), 1, privateChildLayouts.size());

		Layout privateChildLayout = privateChildLayouts.get(0);

		Assert.assertEquals(
			"Test Private Child Layout",
			privateChildLayout.getName(LocaleUtil.getSiteDefault()));
	}

	private void _assertPublicLayouts() throws Exception {
		int publicLayoutsCount = _layoutLocalService.getLayoutsCount(
			_group, false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		Assert.assertEquals(5, publicLayoutsCount);

		Layout layout = _layoutLocalService.getLayoutByFriendlyURL(
			_group.getGroupId(), false, "/test-public-layout");

		Assert.assertEquals(
			"Test Public Layout", layout.getName(LocaleUtil.getSiteDefault()));
		Assert.assertEquals("content", layout.getType());
		Assert.assertFalse(layout.isHidden());

		List<Layout> layouts = layout.getAllChildren();

		Assert.assertEquals(layouts.toString(), 1, layouts.size());

		layout = layouts.get(0);

		Assert.assertEquals(
			"Test Public Child Layout",
			layout.getName(LocaleUtil.getSiteDefault()));

		layout = _layoutLocalService.getLayoutByFriendlyURL(
			_group.getGroupId(), false, "/test-public-permissions-layout");

		Role role = _roleLocalService.getRole(
			layout.getCompanyId(), RoleConstants.GUEST);

		boolean hasGuestViewPermission =
			_resourcePermissionLocalService.hasResourcePermission(
				layout.getCompanyId(), layout.getModelClassName(),
				ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(layout.getPlid()), role.getRoleId(),
				ActionKeys.VIEW);

		Assert.assertFalse(hasGuestViewPermission);

		role = _roleLocalService.getRole(
			layout.getCompanyId(), RoleConstants.SITE_MEMBER);

		boolean hasSiteMemberViewPermission =
			_resourcePermissionLocalService.hasResourcePermission(
				layout.getCompanyId(), layout.getModelClassName(),
				ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(layout.getPlid()), role.getRoleId(),
				ActionKeys.VIEW);

		Assert.assertTrue(hasSiteMemberViewPermission);

		boolean hasSiteMemberUpdateLayoutContentPermission =
			_resourcePermissionLocalService.hasResourcePermission(
				layout.getCompanyId(), layout.getModelClassName(),
				ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(layout.getPlid()), role.getRoleId(),
				ActionKeys.UPDATE_LAYOUT_CONTENT);

		Assert.assertTrue(hasSiteMemberUpdateLayoutContentPermission);

		role = _roleLocalService.getRole(layout.getCompanyId(), "Test Role 4");

		boolean hasTestRole4ViewPermission =
			_resourcePermissionLocalService.hasResourcePermission(
				layout.getCompanyId(), layout.getModelClassName(),
				ResourceConstants.SCOPE_GROUP_TEMPLATE,
				String.valueOf(layout.getPlid()), role.getRoleId(),
				ActionKeys.VIEW);

		Assert.assertTrue(hasTestRole4ViewPermission);

		layout = _layoutLocalService.getLayoutByFriendlyURL(
			_group.getGroupId(), false, "/home");

		Assert.assertEquals(
			PropsUtil.get("default.guest.public.layout.name"),
			layout.getName(LocaleUtil.getSiteDefault()));
		Assert.assertNotEquals(
			PropsUtil.get("default.user.private.layout.name"),
			layout.getName(LocaleUtil.SPAIN));
		Assert.assertEquals(
			PropsUtil.get("default.guest.public.layout.friendly.url"),
			layout.getFriendlyURL(LocaleUtil.getSiteDefault()));

		SitePageResource.Builder sitePageResourceBuilder =
			_sitePageResourceFactory.create();

		SitePageResource sitePageResource =
			sitePageResourceBuilder.httpServletRequest(
				_serviceContext.getRequest()
			).httpServletResponse(
				new MockHttpServletResponse()
			).user(
				_serviceContext.fetchUser()
			).build();

		SitePage sitePage = sitePageResource.getSiteSitePage(
			_group.getGroupId(), "test-objects-layout");

		String pageDefinitionString = String.valueOf(
			sitePage.getPageDefinition());

		Assert.assertFalse(
			pageDefinitionString.contains(
				"[$TestObjectDefinition3#Test_Object_Entry_1$]"));
		Assert.assertFalse(
			pageDefinitionString.contains(
				"[$OBJECT_DEFINITION_ID:TestObjectDefinition3$]"));

		layout = _layoutLocalService.getLayoutByFriendlyURL(
			_group.getGroupId(), false, "/test-url-layout");

		Assert.assertEquals(
			"Test URL Layout", layout.getName(LocaleUtil.getSiteDefault()));
		Assert.assertEquals("url", layout.getType());
		Assert.assertEquals(
			"url=/test-public-layout\n", layout.getTypeSettings());
		Assert.assertFalse(layout.isHidden());
	}

	private void _assertResourceAction(
			String[] actionIds, ResourcePermission resourcePermission)
		throws Exception {

		long resourceActionBitwiseValue = 0;

		for (String actionId : actionIds) {
			ResourceAction resourceAction =
				_resourceActionLocalService.getResourceAction(
					resourcePermission.getName(), actionId);

			Assert.assertNotNull(resourceAction);

			resourceActionBitwiseValue += resourceAction.getBitwiseValue();
		}

		Assert.assertEquals(
			resourceActionBitwiseValue, resourcePermission.getActionIds());
	}

	private void _assertResourcePermission1() throws Exception {
		Role role = _roleLocalService.fetchRole(
			_group.getCompanyId(), "Test Role 1");

		ResourcePermission resourcePermission =
			_resourcePermissionLocalService.fetchResourcePermission(
				_group.getCompanyId(), "com.liferay.commerce.product",
				ResourceConstants.SCOPE_COMPANY,
				String.valueOf(_group.getCompanyId()), role.getRoleId());

		Assert.assertNotNull(resourcePermission);

		_assertResourceAction(new String[] {"VIEW_PRICE"}, resourcePermission);

		role = _roleLocalService.fetchRole(
			_group.getCompanyId(), "Test Role 2");

		resourcePermission =
			_resourcePermissionLocalService.fetchResourcePermission(
				_group.getCompanyId(), "com.liferay.commerce.product",
				ResourceConstants.SCOPE_GROUP,
				String.valueOf(_group.getGroupId()), role.getRoleId());

		Assert.assertNotNull(resourcePermission);

		_assertResourceAction(new String[] {"VIEW_PRICE"}, resourcePermission);

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.fetchObjectDefinition(
				_group.getCompanyId(), "C_TestObjectDefinition3");

		resourcePermission =
			_resourcePermissionLocalService.fetchResourcePermission(
				_group.getCompanyId(),
				"com.liferay.object.model.ObjectDefinition#" +
					objectDefinition.getObjectDefinitionId(),
				ResourceConstants.SCOPE_COMPANY,
				String.valueOf(_group.getCompanyId()), role.getRoleId());

		Assert.assertNotNull(resourcePermission);

		_assertResourceAction(
			new String[] {"DELETE", "UPDATE", "VIEW"}, resourcePermission);
	}

	private void _assertResourcePermission2() throws Exception {
		Role role = _roleLocalService.fetchRole(
			_group.getCompanyId(), "Test Role 1");

		ResourcePermission resourcePermission =
			_resourcePermissionLocalService.fetchResourcePermission(
				_group.getCompanyId(), "com.liferay.commerce.product",
				ResourceConstants.SCOPE_COMPANY,
				String.valueOf(_group.getCompanyId()), role.getRoleId());

		Assert.assertNotNull(resourcePermission);

		_assertResourceAction(
			new String[] {"PERMISSIONS", "VIEW_PRICE"}, resourcePermission);

		role = _roleLocalService.fetchRole(
			_group.getCompanyId(), "Test Role 2");

		resourcePermission =
			_resourcePermissionLocalService.fetchResourcePermission(
				_group.getCompanyId(), "com.liferay.commerce.product",
				ResourceConstants.SCOPE_GROUP,
				String.valueOf(_group.getGroupId()), role.getRoleId());

		Assert.assertNotNull(resourcePermission);

		_assertResourceAction(new String[] {"VIEW_PRICE"}, resourcePermission);

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.fetchObjectDefinition(
				_group.getCompanyId(), "C_TestObjectDefinition3");

		resourcePermission =
			_resourcePermissionLocalService.fetchResourcePermission(
				_group.getCompanyId(),
				"com.liferay.object.model.ObjectDefinition#" +
					objectDefinition.getObjectDefinitionId(),
				ResourceConstants.SCOPE_COMPANY,
				String.valueOf(_group.getCompanyId()), role.getRoleId());

		Assert.assertNotNull(resourcePermission);
		Assert.assertFalse(resourcePermission.isViewActionId());

		_assertResourceAction(
			new String[] {"DELETE", "UPDATE"}, resourcePermission);

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.fetchLayoutPageTemplateEntry(
				_group.getGroupId(), "Test Display Page Template",
				LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE);

		String className =
			"com.liferay.layout.page.template.model.LayoutPageTemplateEntry";

		resourcePermission =
			_resourcePermissionLocalService.fetchResourcePermission(
				_group.getCompanyId(), className,
				ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(layoutPageTemplateEntry.getPrimaryKey()),
				role.getRoleId());

		Assert.assertNotNull(resourcePermission);

		_assertResourceAction(
			new String[] {"VIEW", "UPDATE"}, resourcePermission);
	}

	private void _assertRoles() {
		Role role1 = _roleLocalService.fetchRole(
			_group.getCompanyId(), "Test Role 1");

		Assert.assertNotNull(role1);
		Assert.assertEquals(1, role1.getType());

		_assertRolesAssignments(3, role1.getRoleId());

		Role role2 = _roleLocalService.fetchRole(
			_group.getCompanyId(), "Test Role 2");

		Assert.assertNotNull(role2);
		Assert.assertEquals(6, role2.getType());

		_assertRolesAssignments(2, role2.getRoleId());

		ResourcePermission resourcePermission =
			_resourcePermissionLocalService.fetchResourcePermission(
				_group.getCompanyId(), "com.liferay.portal.kernel.model.Role",
				1, String.valueOf(_group.getCompanyId()), role2.getRoleId());

		Assert.assertEquals(1, resourcePermission.getScope());
		Assert.assertTrue(resourcePermission.isViewActionId());

		Role role3 = _roleLocalService.fetchRole(
			_group.getCompanyId(), "Test Role 3");

		Assert.assertNotNull(role3);
		Assert.assertEquals(1, role3.getType());

		_assertRolesAssignments(1, role3.getRoleId());

		Role role4 = _roleLocalService.fetchRole(
			_group.getCompanyId(), "Test Role 4");

		Assert.assertNotNull(role4);
		Assert.assertEquals(2, role4.getType());

		_assertRolesAssignments(0, role4.getRoleId());
	}

	private void _assertRolesAssignments(
		int roleAssignmentsCount, long roleId) {

		List<Group> groups = _groupLocalService.getRoleGroups(roleId);

		Assert.assertNotNull(groups);
		Assert.assertEquals(
			groups.toString(), roleAssignmentsCount, groups.size());
	}

	private void _assertSAPEntries() {
		SAPEntry sapEntry1 = _sapEntryLocalService.fetchSAPEntry(
			_group.getCompanyId(), "TEST_SAP_ENTRY_1");

		Assert.assertNotNull(sapEntry1);
		Assert.assertTrue(sapEntry1.isDefaultSAPEntry());
		Assert.assertTrue(sapEntry1.isEnabled());

		List<String> allowedServiceSignatures1 =
			sapEntry1.getAllowedServiceSignaturesList();

		Assert.assertEquals(
			allowedServiceSignatures1.toString(), 3,
			allowedServiceSignatures1.size());

		SAPEntry sapEntry2 = _sapEntryLocalService.fetchSAPEntry(
			_group.getCompanyId(), "TEST_SAP_ENTRY_2");

		Assert.assertNotNull(sapEntry2);
		Assert.assertFalse(sapEntry2.isDefaultSAPEntry());
		Assert.assertTrue(sapEntry2.isEnabled());

		List<String> allowedServiceSignatures2 =
			sapEntry2.getAllowedServiceSignaturesList();

		Assert.assertEquals(
			allowedServiceSignatures2.toString(), 5,
			allowedServiceSignatures2.size());
	}

	private void _assertSegmentsEntries() {
		Assert.assertEquals(
			2,
			_segmentsEntryLocalService.getSegmentsEntriesCount(
				_group.getGroupId(), true));

		SegmentsEntry segmentsEntry1 =
			_segmentsEntryLocalService.fetchSegmentsEntry(
				_group.getGroupId(), "TEST-SEGMENTS-ENTRY-1", true);

		Assert.assertNotNull(segmentsEntry1);
		Assert.assertTrue(segmentsEntry1.isActive());
		Assert.assertEquals(
			"Test Segments Entry 1",
			segmentsEntry1.getName(LocaleUtil.getSiteDefault()));
		Assert.assertEquals(
			"com.liferay.portal.kernel.model.User", segmentsEntry1.getType());

		SegmentsEntry segmentsEntry2 =
			_segmentsEntryLocalService.fetchSegmentsEntry(
				_group.getGroupId(), "TEST-SEGMENTS-ENTRY-2", true);

		Assert.assertNotNull(segmentsEntry2);
		Assert.assertFalse(segmentsEntry2.isActive());
		Assert.assertEquals(
			"Test Segments Entry 2",
			segmentsEntry2.getName(LocaleUtil.getSiteDefault()));
		Assert.assertEquals(
			"com.liferay.portal.kernel.model.User", segmentsEntry2.getType());

		Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(
			_group.getGroupId(), false, "/test-public-layout");

		Layout draftLayout = layout.fetchDraftLayout();

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					draftLayout.getGroupId(), draftLayout.getPlid());

		List<SegmentsExperience> segmentsExperiences =
			_segmentsExperienceLocalService.getSegmentsExperiences(
				_group.getGroupId(),
				new long[] {
					segmentsEntry1.getSegmentsEntryId(),
					segmentsEntry2.getSegmentsEntryId()
				},
				_portal.getClassNameId(Layout.class), draftLayout.getClassPK(),
				true);

		Assert.assertEquals(
			segmentsExperiences.toString(), 2, segmentsExperiences.size());

		SegmentsExperience segmentsExperience1 = segmentsExperiences.get(0);

		_assertLayoutStructureItems(
			layoutPageTemplateStructure, 3,
			segmentsExperience1.getSegmentsExperienceId());

		SegmentsExperience segmentsExperience2 = segmentsExperiences.get(1);

		_assertLayoutStructureItems(
			layoutPageTemplateStructure, 3,
			segmentsExperience2.getSegmentsExperienceId());
	}

	private void _assertSiteConfiguration() {
		Assert.assertEquals(
			GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION,
			_group.getMembershipRestriction());
		Assert.assertEquals(GroupConstants.TYPE_SITE_OPEN, _group.getType());
		Assert.assertTrue(_group.isManualMembership());
	}

	private void _assertSiteNavigationMenu() {
		SiteNavigationMenu siteNavigationMenu =
			_siteNavigationMenuLocalService.fetchSiteNavigationMenuByName(
				_group.getGroupId(), "Test Site Navigation Menu");

		Assert.assertNotNull(siteNavigationMenu);

		List<SiteNavigationMenuItem> siteNavigationMenuItems =
			_siteNavigationMenuItemLocalService.getSiteNavigationMenuItems(
				siteNavigationMenu.getSiteNavigationMenuId(), 0);

		Assert.assertEquals(
			siteNavigationMenuItems.toString(), 7,
			siteNavigationMenuItems.size());

		SiteNavigationMenuItem siteNavigationMenuItem1 =
			siteNavigationMenuItems.get(0);

		Assert.assertEquals(
			SiteNavigationMenuItemTypeConstants.LAYOUT,
			siteNavigationMenuItem1.getType());

		SiteNavigationMenuItem siteNavigationMenuItem2 =
			siteNavigationMenuItems.get(1);

		Assert.assertEquals("Test URL", siteNavigationMenuItem2.getName());
		Assert.assertEquals(
			SiteNavigationMenuItemTypeConstants.URL,
			siteNavigationMenuItem2.getType());

		SiteNavigationMenuItem siteNavigationMenuItem3 =
			siteNavigationMenuItems.get(2);

		Assert.assertEquals("Other Links", siteNavigationMenuItem3.getName());
		Assert.assertEquals(
			SiteNavigationMenuItemTypeConstants.NODE,
			siteNavigationMenuItem3.getType());

		SiteNavigationMenuItem siteNavigationMenuItem4 =
			siteNavigationMenuItems.get(3);

		Assert.assertEquals(
			AssetCategory.class.getName(), siteNavigationMenuItem4.getType());

		SiteNavigationMenuItem siteNavigationMenuItem5 =
			siteNavigationMenuItems.get(4);

		Assert.assertEquals(
			JournalArticle.class.getName(), siteNavigationMenuItem5.getType());

		SiteNavigationMenuItem siteNavigationMenuItem6 =
			siteNavigationMenuItems.get(5);

		Assert.assertEquals(
			FileEntry.class.getName(), siteNavigationMenuItem6.getType());

		SiteNavigationMenuItem siteNavigationMenuItem7 =
			siteNavigationMenuItems.get(6);

		String type = siteNavigationMenuItem7.getType();

		Assert.assertTrue(
			type.startsWith("com.liferay.object.model.ObjectDefinition#"));
	}

	private void _assertSiteSettings() throws Exception {
		Configuration configuration = _getFactoryConfiguration(
			"test.pid.scoped", ExtendedObjectClassDefinition.Scope.GROUP,
			_group.getGroupId());

		Assert.assertNotNull(configuration);

		Dictionary<String, Object> properties = configuration.getProperties();

		Assert.assertNotNull(properties);
		Assert.assertEquals("value1", properties.get("key1"));
		Assert.assertEquals("value2", properties.get("key2"));
	}

	private void _assertStyleBookEntry() {
		StyleBookEntry styleBookEntry =
			_styleBookEntryLocalService.fetchStyleBookEntry(
				_group.getGroupId(), "test-style-book");

		Assert.assertNotNull(styleBookEntry);

		String frontendTokensValues = styleBookEntry.getFrontendTokensValues();

		Assert.assertTrue(
			frontendTokensValues.contains("blockquote-small-color"));
	}

	private void _assertUserAccounts(
			Long accountId, int totalCount,
			UserAccountResource userAccountResource)
		throws Exception {

		Page<UserAccount> page = userAccountResource.getAccountUserAccountsPage(
			accountId, null, null, null, null);

		Assert.assertNotNull(page);
		Assert.assertEquals(totalCount, page.getTotalCount());
	}

	private void _assertUserGroups() {
		List<UserGroup> userGroups = _userGroupLocalService.getGroupUserGroups(
			_group.getGroupId());

		Assert.assertEquals(userGroups.toString(), 2, userGroups.size());

		UserGroup userGroup1 =
			_userGroupLocalService.fetchUserGroupByExternalReferenceCode(
				"TESTUSERGROUP1", _group.getCompanyId());

		Assert.assertNotNull(userGroup1);
		Assert.assertTrue(userGroups.contains(userGroup1));

		UserGroup userGroup2 =
			_userGroupLocalService.fetchUserGroupByExternalReferenceCode(
				"TESTUSERGROUP2", _group.getCompanyId());

		Assert.assertNotNull(userGroup2);
		Assert.assertTrue(userGroups.contains(userGroup2));
	}

	private void _assertUserOrganizations(
			String organizationId, int totalCount,
			UserAccountResource userAccountResource)
		throws Exception {

		Page<UserAccount> page =
			userAccountResource.getOrganizationUserAccountsPage(
				organizationId, null, null, null, null);

		Assert.assertNotNull(page);
		Assert.assertEquals(totalCount, page.getTotalCount());
	}

	private void _assertUserRoles() throws Exception {
		User user = _userLocalService.fetchUserByEmailAddress(
			_group.getCompanyId(), "test.user1@liferay.com");

		List<Role> roles = user.getRoles();

		Assert.assertEquals(roles.toString(), 3, roles.size());

		Role role = roles.get(0);

		Assert.assertEquals(RoleConstants.USER, role.getName());

		role = roles.get(1);

		Assert.assertEquals("Test Role 1", role.getName());

		role = roles.get(2);

		Assert.assertEquals("Test Role 2", role.getName());

		user = _userLocalService.fetchUserByEmailAddress(
			_group.getCompanyId(), "test.user2@liferay.com");

		roles = user.getRoles();

		Assert.assertEquals(roles.toString(), 2, roles.size());

		role = roles.get(0);

		Assert.assertEquals(RoleConstants.USER, role.getName());

		role = roles.get(1);

		Assert.assertEquals("Test Role 3", role.getName());
	}

	private void _assertWorkflowDefinitions() throws Exception {
		WorkflowDefinitionResource.Builder workflowDefinitionResourceBuilder =
			_workflowDefinitionResourceFactory.create();

		WorkflowDefinitionResource workflowDefinitionResource =
			workflowDefinitionResourceBuilder.user(
				_serviceContext.fetchUser()
			).build();

		WorkflowDefinition workflowDefinitionTest1 =
			workflowDefinitionResource.getWorkflowDefinitionByName(
				"Test Workflow Definition 1", 1);

		Assert.assertNotNull(workflowDefinitionTest1);
		Assert.assertEquals(
			"Test Workflow Definition 1", workflowDefinitionTest1.getName());
		Assert.assertEquals(
			"Test Workflow Definition 1", workflowDefinitionTest1.getTitle());
		Assert.assertEquals(
			"This is the description for Test Workflow Definition 1.",
			workflowDefinitionTest1.getDescription());

		WorkflowDefinitionLink workflowDefinitionLink1 =
			_workflowDefinitionLinkLocalService.getWorkflowDefinitionLink(
				_group.getCompanyId(), 0, "com.liferay.blogs.model.BlogsEntry",
				0, 0);

		Assert.assertNotNull(workflowDefinitionLink1);
		Assert.assertEquals(
			"Test Workflow Definition 1",
			workflowDefinitionLink1.getWorkflowDefinitionName());

		WorkflowDefinition workflowDefinitionTest2 =
			workflowDefinitionResource.getWorkflowDefinitionByName(
				"Test Workflow Definition 2", 1);

		Assert.assertNotNull(workflowDefinitionTest2);
		Assert.assertEquals(
			"Test Workflow Definition 2", workflowDefinitionTest2.getName());
		Assert.assertEquals(
			"Test Workflow Definition 2", workflowDefinitionTest2.getTitle());
		Assert.assertEquals(
			"This is the description for Test Workflow Definition 2.",
			workflowDefinitionTest2.getDescription());

		WorkflowDefinitionLink workflowDefinitionLink2 =
			_workflowDefinitionLinkLocalService.getWorkflowDefinitionLink(
				_group.getCompanyId(), _group.getGroupId(),
				"com.liferay.search.experiences.model.SXPBlueprint", 0, 0);

		Assert.assertNotNull(workflowDefinitionLink2);
		Assert.assertEquals(
			"Test Workflow Definition 2",
			workflowDefinitionLink2.getWorkflowDefinitionName());
	}

	private Bundle _getBundle(String location) throws Exception {
		Bundle bundle = FrameworkUtil.getBundle(
			BundleSiteInitializerTest.class);

		try (InputStream inputStream =
				BundleSiteInitializerTest.class.getResourceAsStream(location)) {

			BundleContext bundleContext = bundle.getBundleContext();

			bundle = bundleContext.installBundle(location, inputStream);
		}

		bundle.start();

		return bundle;
	}

	private Configuration _getFactoryConfiguration(
			String factoryPid, ExtendedObjectClassDefinition.Scope scope,
			Serializable scopePK)
		throws Exception {

		try {
			String filterString = StringBundler.concat(
				"(&(service.factoryPid=", factoryPid, ")(",
				scope.getPropertyKey(), "=", scopePK, "))");

			Configuration[] configurations =
				_configurationAdmin.listConfigurations(filterString);

			if (configurations != null) {
				return configurations[0];
			}

			return null;
		}
		catch (InvalidSyntaxException | IOException exception) {
			throw new ConfigurationException(
				"Unable to retrieve factory configuration " + factoryPid,
				exception);
		}
	}

	private File _getTempDir(String location) throws Exception {
		File tempFile = FileUtil.createTempFile();

		FileUtil.write(
			tempFile,
			BundleSiteInitializerTest.class.getResourceAsStream(location));

		File tempDir1 = FileUtil.createTempFolder();

		FileUtil.unzip(tempFile, tempDir1);

		tempFile.delete();

		return tempDir1;
	}

	private void _test1(SiteInitializer siteInitializer) throws Exception {
		siteInitializer.initialize(_group.getGroupId());

		_assertAccounts1();
		_assertAssetListEntries();
		_assertAssetVocabularies();
		_assertClientExtension();
		_assertCommerceCatalogs();
		_assertCommerceChannel();
		_assertCommerceInventoryWarehouse();
		_assertCommerceSpecificationProducts();
		_assertCPDefinition();
		_assertCPInstanceProperties();
		_assertDDMStructure();
		_assertDDMTemplate();
		_assertDLFileEntry();
		_assertExpandoColumns();
		_assertFragmentEntries();
		_assertJournalArticles();
		_assertKBArticles();
		_assertLayoutPageTemplateEntries();
		_assertLayoutSets();
		_assertLayouts();
		_assertLayoutUtilityPageEntries();
		_assertListTypeDefinitions();
		_assertNotificationTemplate();
		_assertObjectDefinitions();
		_assertOrganizations();
		_assertPermissions();
		_assertPortletSettings();
		_assertSAPEntries();
		_assertSegmentsEntries();
		_assertSiteConfiguration();
		_assertSiteSettings();
		_assertSiteNavigationMenu();
		_assertStyleBookEntry();
		_assertUserGroups();
		_assertUserRoles();
		_assertWorkflowDefinitions();
	}

	private void _test2(SiteInitializer siteInitializer) throws Exception {
		siteInitializer.initialize(_group.getGroupId());

		_assertAccounts2();
		_assertResourcePermission2();
	}

	@Inject
	private static ConfigurationAdmin _configurationAdmin;

	@Inject
	private AccountResource.Factory _accountResourceFactory;

	@Inject
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Inject
	private AssetListEntryLocalService _assetListEntryLocalService;

	@Inject
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Inject
	private CETFactory _cetFactory;

	@Inject
	private ClientExtensionEntryLocalService _clientExtensionEntryLocalService;

	@Inject
	private CommerceCatalogLocalService _commerceCatalogLocalService;

	@Inject
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Inject
	private CommerceInventoryWarehouseLocalService
		_commerceInventoryWarehouseLocalService;

	@Inject
	private CommerceNotificationTemplateLocalService
		_commerceNotificationTemplateLocalService;

	@Inject
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Inject
	private CPInstanceLocalService _cpInstanceLocalService;

	@Inject
	private CPOptionLocalService _cpOptionLocalService;

	@Inject
	private CPSpecificationOptionLocalService
		_cpSpecificationOptionLocalService;

	@Inject
	private DDMStructureLocalService _ddmStructureLocalService;

	@Inject
	private DDMTemplateLocalService _ddmTemplateLocalService;

	@Inject
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Inject
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Inject
	private FragmentEntryLocalService _fragmentEntryLocalService;

	private Group _group;

	@Inject
	private GroupLocalService _groupLocalService;

	@Inject
	private JournalArticleLocalService _journalArticleLocalService;

	@Inject
	private JournalFolderService _journalFolderService;

	@Inject
	private KBArticleLocalService _kbArticleLocalService;

	@Inject
	private KBFolderLocalService _kbFolderLocalService;

	@Inject
	private LayoutLocalService _layoutLocalService;

	@Inject
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

	@Inject
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Inject
	private LayoutSetLocalService _layoutSetLocalService;

	@Inject
	private LayoutUtilityPageEntryLocalService
		_layoutUtilityPageEntryLocalService;

	@Inject
	private ListTypeDefinitionResource.Factory
		_listTypeDefinitionResourceFactory;

	@Inject
	private NotificationTemplateResource.Factory
		_notificationTemplateResourceFactory;

	@Inject
	private ObjectActionLocalService _objectActionLocalService;

	@Inject
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Inject
	private ObjectEntryLocalService _objectEntryLocalService;

	@Inject
	private ObjectFieldLocalService _objectFieldLocalService;

	@Inject
	private ObjectRelationshipResource.Factory
		_objectRelationshipResourceFactory;

	@Inject
	private OrganizationResource.Factory _organizationResourceFactory;

	@Inject
	private Portal _portal;

	@Inject
	private ProductSpecificationResource.Factory
		_productSpecificationResourceFactory;

	@Inject
	private ResourceActionLocalService _resourceActionLocalService;

	@Inject
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Inject
	private RoleLocalService _roleLocalService;

	@Inject
	private SAPEntryLocalService _sapEntryLocalService;

	@Inject
	private SegmentsEntryLocalService _segmentsEntryLocalService;

	@Inject
	private SegmentsExperienceLocalService _segmentsExperienceLocalService;

	private ServiceContext _serviceContext;

	@Inject
	private ServletContext _servletContext;

	@Inject
	private SettingsFactory _settingsFactory;

	@Inject
	private SiteInitializerFactory _siteInitializerFactory;

	@Inject
	private SiteInitializerRegistry _siteInitializerRegistry;

	@Inject
	private SiteNavigationMenuItemLocalService
		_siteNavigationMenuItemLocalService;

	@Inject
	private SiteNavigationMenuLocalService _siteNavigationMenuLocalService;

	@Inject
	private SitePageResource.Factory _sitePageResourceFactory;

	@Inject
	private StyleBookEntryLocalService _styleBookEntryLocalService;

	@Inject
	private UserAccountResource.Factory _userAccountResourceFactory;

	@Inject
	private UserGroupLocalService _userGroupLocalService;

	@Inject
	private UserLocalService _userLocalService;

	@Inject
	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

	@Inject
	private WorkflowDefinitionResource.Factory
		_workflowDefinitionResourceFactory;

}