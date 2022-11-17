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

package com.liferay.layout.seo.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the LayoutSEOSite service. Represents a row in the &quot;LayoutSEOSite&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.layout.seo.model.impl.LayoutSEOSiteModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.layout.seo.model.impl.LayoutSEOSiteImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSEOSite
 * @generated
 */
@ProviderType
public interface LayoutSEOSiteModel
	extends BaseModel<LayoutSEOSite>, CTModel<LayoutSEOSite>, GroupedModel,
			LocalizedModel, MVCCModel, ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a layout seo site model instance should use the {@link LayoutSEOSite} interface instead.
	 */

	/**
	 * Returns the primary key of this layout seo site.
	 *
	 * @return the primary key of this layout seo site
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this layout seo site.
	 *
	 * @param primaryKey the primary key of this layout seo site
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this layout seo site.
	 *
	 * @return the mvcc version of this layout seo site
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this layout seo site.
	 *
	 * @param mvccVersion the mvcc version of this layout seo site
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this layout seo site.
	 *
	 * @return the ct collection ID of this layout seo site
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this layout seo site.
	 *
	 * @param ctCollectionId the ct collection ID of this layout seo site
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this layout seo site.
	 *
	 * @return the uuid of this layout seo site
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this layout seo site.
	 *
	 * @param uuid the uuid of this layout seo site
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the layout seo site ID of this layout seo site.
	 *
	 * @return the layout seo site ID of this layout seo site
	 */
	public long getLayoutSEOSiteId();

	/**
	 * Sets the layout seo site ID of this layout seo site.
	 *
	 * @param layoutSEOSiteId the layout seo site ID of this layout seo site
	 */
	public void setLayoutSEOSiteId(long layoutSEOSiteId);

	/**
	 * Returns the group ID of this layout seo site.
	 *
	 * @return the group ID of this layout seo site
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this layout seo site.
	 *
	 * @param groupId the group ID of this layout seo site
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this layout seo site.
	 *
	 * @return the company ID of this layout seo site
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this layout seo site.
	 *
	 * @param companyId the company ID of this layout seo site
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this layout seo site.
	 *
	 * @return the user ID of this layout seo site
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this layout seo site.
	 *
	 * @param userId the user ID of this layout seo site
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this layout seo site.
	 *
	 * @return the user uuid of this layout seo site
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this layout seo site.
	 *
	 * @param userUuid the user uuid of this layout seo site
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this layout seo site.
	 *
	 * @return the user name of this layout seo site
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this layout seo site.
	 *
	 * @param userName the user name of this layout seo site
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this layout seo site.
	 *
	 * @return the create date of this layout seo site
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this layout seo site.
	 *
	 * @param createDate the create date of this layout seo site
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this layout seo site.
	 *
	 * @return the modified date of this layout seo site
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this layout seo site.
	 *
	 * @param modifiedDate the modified date of this layout seo site
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the open graph enabled of this layout seo site.
	 *
	 * @return the open graph enabled of this layout seo site
	 */
	public boolean getOpenGraphEnabled();

	/**
	 * Returns <code>true</code> if this layout seo site is open graph enabled.
	 *
	 * @return <code>true</code> if this layout seo site is open graph enabled; <code>false</code> otherwise
	 */
	public boolean isOpenGraphEnabled();

	/**
	 * Sets whether this layout seo site is open graph enabled.
	 *
	 * @param openGraphEnabled the open graph enabled of this layout seo site
	 */
	public void setOpenGraphEnabled(boolean openGraphEnabled);

	/**
	 * Returns the open graph image alt of this layout seo site.
	 *
	 * @return the open graph image alt of this layout seo site
	 */
	public String getOpenGraphImageAlt();

	/**
	 * Returns the localized open graph image alt of this layout seo site in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized open graph image alt of this layout seo site
	 */
	@AutoEscape
	public String getOpenGraphImageAlt(Locale locale);

	/**
	 * Returns the localized open graph image alt of this layout seo site in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized open graph image alt of this layout seo site. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getOpenGraphImageAlt(Locale locale, boolean useDefault);

	/**
	 * Returns the localized open graph image alt of this layout seo site in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized open graph image alt of this layout seo site
	 */
	@AutoEscape
	public String getOpenGraphImageAlt(String languageId);

	/**
	 * Returns the localized open graph image alt of this layout seo site in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized open graph image alt of this layout seo site
	 */
	@AutoEscape
	public String getOpenGraphImageAlt(String languageId, boolean useDefault);

	@AutoEscape
	public String getOpenGraphImageAltCurrentLanguageId();

	@AutoEscape
	public String getOpenGraphImageAltCurrentValue();

	/**
	 * Returns a map of the locales and localized open graph image alts of this layout seo site.
	 *
	 * @return the locales and localized open graph image alts of this layout seo site
	 */
	public Map<Locale, String> getOpenGraphImageAltMap();

	/**
	 * Sets the open graph image alt of this layout seo site.
	 *
	 * @param openGraphImageAlt the open graph image alt of this layout seo site
	 */
	public void setOpenGraphImageAlt(String openGraphImageAlt);

	/**
	 * Sets the localized open graph image alt of this layout seo site in the language.
	 *
	 * @param openGraphImageAlt the localized open graph image alt of this layout seo site
	 * @param locale the locale of the language
	 */
	public void setOpenGraphImageAlt(String openGraphImageAlt, Locale locale);

	/**
	 * Sets the localized open graph image alt of this layout seo site in the language, and sets the default locale.
	 *
	 * @param openGraphImageAlt the localized open graph image alt of this layout seo site
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setOpenGraphImageAlt(
		String openGraphImageAlt, Locale locale, Locale defaultLocale);

	public void setOpenGraphImageAltCurrentLanguageId(String languageId);

	/**
	 * Sets the localized open graph image alts of this layout seo site from the map of locales and localized open graph image alts.
	 *
	 * @param openGraphImageAltMap the locales and localized open graph image alts of this layout seo site
	 */
	public void setOpenGraphImageAltMap(
		Map<Locale, String> openGraphImageAltMap);

	/**
	 * Sets the localized open graph image alts of this layout seo site from the map of locales and localized open graph image alts, and sets the default locale.
	 *
	 * @param openGraphImageAltMap the locales and localized open graph image alts of this layout seo site
	 * @param defaultLocale the default locale
	 */
	public void setOpenGraphImageAltMap(
		Map<Locale, String> openGraphImageAltMap, Locale defaultLocale);

	/**
	 * Returns the open graph image file entry ID of this layout seo site.
	 *
	 * @return the open graph image file entry ID of this layout seo site
	 */
	public long getOpenGraphImageFileEntryId();

	/**
	 * Sets the open graph image file entry ID of this layout seo site.
	 *
	 * @param openGraphImageFileEntryId the open graph image file entry ID of this layout seo site
	 */
	public void setOpenGraphImageFileEntryId(long openGraphImageFileEntryId);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public LayoutSEOSite cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}