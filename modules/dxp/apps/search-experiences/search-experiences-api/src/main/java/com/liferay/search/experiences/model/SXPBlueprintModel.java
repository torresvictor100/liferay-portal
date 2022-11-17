/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.search.experiences.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the SXPBlueprint service. Represents a row in the &quot;SXPBlueprint&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.search.experiences.model.impl.SXPBlueprintModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.search.experiences.model.impl.SXPBlueprintImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SXPBlueprint
 * @generated
 */
@ProviderType
public interface SXPBlueprintModel
	extends BaseModel<SXPBlueprint>, LocalizedModel, MVCCModel, ShardedModel,
			StagedAuditedModel, WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a sxp blueprint model instance should use the {@link SXPBlueprint} interface instead.
	 */

	/**
	 * Returns the primary key of this sxp blueprint.
	 *
	 * @return the primary key of this sxp blueprint
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this sxp blueprint.
	 *
	 * @param primaryKey the primary key of this sxp blueprint
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this sxp blueprint.
	 *
	 * @return the mvcc version of this sxp blueprint
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this sxp blueprint.
	 *
	 * @param mvccVersion the mvcc version of this sxp blueprint
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this sxp blueprint.
	 *
	 * @return the uuid of this sxp blueprint
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this sxp blueprint.
	 *
	 * @param uuid the uuid of this sxp blueprint
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this sxp blueprint.
	 *
	 * @return the external reference code of this sxp blueprint
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this sxp blueprint.
	 *
	 * @param externalReferenceCode the external reference code of this sxp blueprint
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the sxp blueprint ID of this sxp blueprint.
	 *
	 * @return the sxp blueprint ID of this sxp blueprint
	 */
	public long getSXPBlueprintId();

	/**
	 * Sets the sxp blueprint ID of this sxp blueprint.
	 *
	 * @param sxpBlueprintId the sxp blueprint ID of this sxp blueprint
	 */
	public void setSXPBlueprintId(long sxpBlueprintId);

	/**
	 * Returns the company ID of this sxp blueprint.
	 *
	 * @return the company ID of this sxp blueprint
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this sxp blueprint.
	 *
	 * @param companyId the company ID of this sxp blueprint
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this sxp blueprint.
	 *
	 * @return the user ID of this sxp blueprint
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this sxp blueprint.
	 *
	 * @param userId the user ID of this sxp blueprint
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this sxp blueprint.
	 *
	 * @return the user uuid of this sxp blueprint
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this sxp blueprint.
	 *
	 * @param userUuid the user uuid of this sxp blueprint
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this sxp blueprint.
	 *
	 * @return the user name of this sxp blueprint
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this sxp blueprint.
	 *
	 * @param userName the user name of this sxp blueprint
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this sxp blueprint.
	 *
	 * @return the create date of this sxp blueprint
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this sxp blueprint.
	 *
	 * @param createDate the create date of this sxp blueprint
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this sxp blueprint.
	 *
	 * @return the modified date of this sxp blueprint
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this sxp blueprint.
	 *
	 * @param modifiedDate the modified date of this sxp blueprint
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the configuration json of this sxp blueprint.
	 *
	 * @return the configuration json of this sxp blueprint
	 */
	@AutoEscape
	public String getConfigurationJSON();

	/**
	 * Sets the configuration json of this sxp blueprint.
	 *
	 * @param configurationJSON the configuration json of this sxp blueprint
	 */
	public void setConfigurationJSON(String configurationJSON);

	/**
	 * Returns the description of this sxp blueprint.
	 *
	 * @return the description of this sxp blueprint
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this sxp blueprint in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this sxp blueprint
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this sxp blueprint in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this sxp blueprint. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this sxp blueprint in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this sxp blueprint
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this sxp blueprint in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this sxp blueprint
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this sxp blueprint.
	 *
	 * @return the locales and localized descriptions of this sxp blueprint
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this sxp blueprint.
	 *
	 * @param description the description of this sxp blueprint
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this sxp blueprint in the language.
	 *
	 * @param description the localized description of this sxp blueprint
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this sxp blueprint in the language, and sets the default locale.
	 *
	 * @param description the localized description of this sxp blueprint
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(
		String description, Locale locale, Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this sxp blueprint from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this sxp blueprint
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this sxp blueprint from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this sxp blueprint
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale);

	/**
	 * Returns the element instances json of this sxp blueprint.
	 *
	 * @return the element instances json of this sxp blueprint
	 */
	@AutoEscape
	public String getElementInstancesJSON();

	/**
	 * Sets the element instances json of this sxp blueprint.
	 *
	 * @param elementInstancesJSON the element instances json of this sxp blueprint
	 */
	public void setElementInstancesJSON(String elementInstancesJSON);

	/**
	 * Returns the schema version of this sxp blueprint.
	 *
	 * @return the schema version of this sxp blueprint
	 */
	@AutoEscape
	public String getSchemaVersion();

	/**
	 * Sets the schema version of this sxp blueprint.
	 *
	 * @param schemaVersion the schema version of this sxp blueprint
	 */
	public void setSchemaVersion(String schemaVersion);

	/**
	 * Returns the title of this sxp blueprint.
	 *
	 * @return the title of this sxp blueprint
	 */
	public String getTitle();

	/**
	 * Returns the localized title of this sxp blueprint in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this sxp blueprint
	 */
	@AutoEscape
	public String getTitle(Locale locale);

	/**
	 * Returns the localized title of this sxp blueprint in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this sxp blueprint. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized title of this sxp blueprint in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this sxp blueprint
	 */
	@AutoEscape
	public String getTitle(String languageId);

	/**
	 * Returns the localized title of this sxp blueprint in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this sxp blueprint
	 */
	@AutoEscape
	public String getTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getTitleCurrentLanguageId();

	@AutoEscape
	public String getTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized titles of this sxp blueprint.
	 *
	 * @return the locales and localized titles of this sxp blueprint
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this sxp blueprint.
	 *
	 * @param title the title of this sxp blueprint
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this sxp blueprint in the language.
	 *
	 * @param title the localized title of this sxp blueprint
	 * @param locale the locale of the language
	 */
	public void setTitle(String title, Locale locale);

	/**
	 * Sets the localized title of this sxp blueprint in the language, and sets the default locale.
	 *
	 * @param title the localized title of this sxp blueprint
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTitle(String title, Locale locale, Locale defaultLocale);

	public void setTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized titles of this sxp blueprint from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this sxp blueprint
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Sets the localized titles of this sxp blueprint from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this sxp blueprint
	 * @param defaultLocale the default locale
	 */
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Returns the version of this sxp blueprint.
	 *
	 * @return the version of this sxp blueprint
	 */
	@AutoEscape
	public String getVersion();

	/**
	 * Sets the version of this sxp blueprint.
	 *
	 * @param version the version of this sxp blueprint
	 */
	public void setVersion(String version);

	/**
	 * Returns the status of this sxp blueprint.
	 *
	 * @return the status of this sxp blueprint
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this sxp blueprint.
	 *
	 * @param status the status of this sxp blueprint
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this sxp blueprint.
	 *
	 * @return the status by user ID of this sxp blueprint
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this sxp blueprint.
	 *
	 * @param statusByUserId the status by user ID of this sxp blueprint
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this sxp blueprint.
	 *
	 * @return the status by user uuid of this sxp blueprint
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this sxp blueprint.
	 *
	 * @param statusByUserUuid the status by user uuid of this sxp blueprint
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this sxp blueprint.
	 *
	 * @return the status by user name of this sxp blueprint
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this sxp blueprint.
	 *
	 * @param statusByUserName the status by user name of this sxp blueprint
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this sxp blueprint.
	 *
	 * @return the status date of this sxp blueprint
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this sxp blueprint.
	 *
	 * @param statusDate the status date of this sxp blueprint
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns <code>true</code> if this sxp blueprint is approved.
	 *
	 * @return <code>true</code> if this sxp blueprint is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this sxp blueprint is denied.
	 *
	 * @return <code>true</code> if this sxp blueprint is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this sxp blueprint is a draft.
	 *
	 * @return <code>true</code> if this sxp blueprint is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this sxp blueprint is expired.
	 *
	 * @return <code>true</code> if this sxp blueprint is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this sxp blueprint is inactive.
	 *
	 * @return <code>true</code> if this sxp blueprint is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this sxp blueprint is incomplete.
	 *
	 * @return <code>true</code> if this sxp blueprint is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this sxp blueprint is pending.
	 *
	 * @return <code>true</code> if this sxp blueprint is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this sxp blueprint is scheduled.
	 *
	 * @return <code>true</code> if this sxp blueprint is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

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
	public SXPBlueprint cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}