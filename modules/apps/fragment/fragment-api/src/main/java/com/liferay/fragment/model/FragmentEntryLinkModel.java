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

package com.liferay.fragment.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the FragmentEntryLink service. Represents a row in the &quot;FragmentEntryLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.fragment.model.impl.FragmentEntryLinkModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.fragment.model.impl.FragmentEntryLinkImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FragmentEntryLink
 * @generated
 */
@ProviderType
public interface FragmentEntryLinkModel
	extends AttachedModel, BaseModel<FragmentEntryLink>,
			CTModel<FragmentEntryLink>, MVCCModel, ShardedModel,
			StagedGroupedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a fragment entry link model instance should use the {@link FragmentEntryLink} interface instead.
	 */

	/**
	 * Returns the primary key of this fragment entry link.
	 *
	 * @return the primary key of this fragment entry link
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this fragment entry link.
	 *
	 * @param primaryKey the primary key of this fragment entry link
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this fragment entry link.
	 *
	 * @return the mvcc version of this fragment entry link
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this fragment entry link.
	 *
	 * @param mvccVersion the mvcc version of this fragment entry link
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this fragment entry link.
	 *
	 * @return the ct collection ID of this fragment entry link
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this fragment entry link.
	 *
	 * @param ctCollectionId the ct collection ID of this fragment entry link
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this fragment entry link.
	 *
	 * @return the uuid of this fragment entry link
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this fragment entry link.
	 *
	 * @param uuid the uuid of this fragment entry link
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the fragment entry link ID of this fragment entry link.
	 *
	 * @return the fragment entry link ID of this fragment entry link
	 */
	public long getFragmentEntryLinkId();

	/**
	 * Sets the fragment entry link ID of this fragment entry link.
	 *
	 * @param fragmentEntryLinkId the fragment entry link ID of this fragment entry link
	 */
	public void setFragmentEntryLinkId(long fragmentEntryLinkId);

	/**
	 * Returns the group ID of this fragment entry link.
	 *
	 * @return the group ID of this fragment entry link
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this fragment entry link.
	 *
	 * @param groupId the group ID of this fragment entry link
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this fragment entry link.
	 *
	 * @return the company ID of this fragment entry link
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this fragment entry link.
	 *
	 * @param companyId the company ID of this fragment entry link
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this fragment entry link.
	 *
	 * @return the user ID of this fragment entry link
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this fragment entry link.
	 *
	 * @param userId the user ID of this fragment entry link
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this fragment entry link.
	 *
	 * @return the user uuid of this fragment entry link
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this fragment entry link.
	 *
	 * @param userUuid the user uuid of this fragment entry link
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this fragment entry link.
	 *
	 * @return the user name of this fragment entry link
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this fragment entry link.
	 *
	 * @param userName the user name of this fragment entry link
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this fragment entry link.
	 *
	 * @return the create date of this fragment entry link
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this fragment entry link.
	 *
	 * @param createDate the create date of this fragment entry link
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this fragment entry link.
	 *
	 * @return the modified date of this fragment entry link
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this fragment entry link.
	 *
	 * @param modifiedDate the modified date of this fragment entry link
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the original fragment entry link ID of this fragment entry link.
	 *
	 * @return the original fragment entry link ID of this fragment entry link
	 */
	public long getOriginalFragmentEntryLinkId();

	/**
	 * Sets the original fragment entry link ID of this fragment entry link.
	 *
	 * @param originalFragmentEntryLinkId the original fragment entry link ID of this fragment entry link
	 */
	public void setOriginalFragmentEntryLinkId(
		long originalFragmentEntryLinkId);

	/**
	 * Returns the fragment entry ID of this fragment entry link.
	 *
	 * @return the fragment entry ID of this fragment entry link
	 */
	public long getFragmentEntryId();

	/**
	 * Sets the fragment entry ID of this fragment entry link.
	 *
	 * @param fragmentEntryId the fragment entry ID of this fragment entry link
	 */
	public void setFragmentEntryId(long fragmentEntryId);

	/**
	 * Returns the segments experience ID of this fragment entry link.
	 *
	 * @return the segments experience ID of this fragment entry link
	 */
	public long getSegmentsExperienceId();

	/**
	 * Sets the segments experience ID of this fragment entry link.
	 *
	 * @param segmentsExperienceId the segments experience ID of this fragment entry link
	 */
	public void setSegmentsExperienceId(long segmentsExperienceId);

	/**
	 * Returns the fully qualified class name of this fragment entry link.
	 *
	 * @return the fully qualified class name of this fragment entry link
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this fragment entry link.
	 *
	 * @return the class name ID of this fragment entry link
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this fragment entry link.
	 *
	 * @param classNameId the class name ID of this fragment entry link
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this fragment entry link.
	 *
	 * @return the class pk of this fragment entry link
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this fragment entry link.
	 *
	 * @param classPK the class pk of this fragment entry link
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the plid of this fragment entry link.
	 *
	 * @return the plid of this fragment entry link
	 */
	public long getPlid();

	/**
	 * Sets the plid of this fragment entry link.
	 *
	 * @param plid the plid of this fragment entry link
	 */
	public void setPlid(long plid);

	/**
	 * Returns the css of this fragment entry link.
	 *
	 * @return the css of this fragment entry link
	 */
	@AutoEscape
	public String getCss();

	/**
	 * Sets the css of this fragment entry link.
	 *
	 * @param css the css of this fragment entry link
	 */
	public void setCss(String css);

	/**
	 * Returns the html of this fragment entry link.
	 *
	 * @return the html of this fragment entry link
	 */
	@AutoEscape
	public String getHtml();

	/**
	 * Sets the html of this fragment entry link.
	 *
	 * @param html the html of this fragment entry link
	 */
	public void setHtml(String html);

	/**
	 * Returns the js of this fragment entry link.
	 *
	 * @return the js of this fragment entry link
	 */
	@AutoEscape
	public String getJs();

	/**
	 * Sets the js of this fragment entry link.
	 *
	 * @param js the js of this fragment entry link
	 */
	public void setJs(String js);

	/**
	 * Returns the configuration of this fragment entry link.
	 *
	 * @return the configuration of this fragment entry link
	 */
	@AutoEscape
	public String getConfiguration();

	/**
	 * Sets the configuration of this fragment entry link.
	 *
	 * @param configuration the configuration of this fragment entry link
	 */
	public void setConfiguration(String configuration);

	/**
	 * Returns the editable values of this fragment entry link.
	 *
	 * @return the editable values of this fragment entry link
	 */
	@AutoEscape
	public String getEditableValues();

	/**
	 * Sets the editable values of this fragment entry link.
	 *
	 * @param editableValues the editable values of this fragment entry link
	 */
	public void setEditableValues(String editableValues);

	/**
	 * Returns the namespace of this fragment entry link.
	 *
	 * @return the namespace of this fragment entry link
	 */
	@AutoEscape
	public String getNamespace();

	/**
	 * Sets the namespace of this fragment entry link.
	 *
	 * @param namespace the namespace of this fragment entry link
	 */
	public void setNamespace(String namespace);

	/**
	 * Returns the position of this fragment entry link.
	 *
	 * @return the position of this fragment entry link
	 */
	public int getPosition();

	/**
	 * Sets the position of this fragment entry link.
	 *
	 * @param position the position of this fragment entry link
	 */
	public void setPosition(int position);

	/**
	 * Returns the renderer key of this fragment entry link.
	 *
	 * @return the renderer key of this fragment entry link
	 */
	@AutoEscape
	public String getRendererKey();

	/**
	 * Sets the renderer key of this fragment entry link.
	 *
	 * @param rendererKey the renderer key of this fragment entry link
	 */
	public void setRendererKey(String rendererKey);

	/**
	 * Returns the type of this fragment entry link.
	 *
	 * @return the type of this fragment entry link
	 */
	public int getType();

	/**
	 * Sets the type of this fragment entry link.
	 *
	 * @param type the type of this fragment entry link
	 */
	public void setType(int type);

	/**
	 * Returns the deleted of this fragment entry link.
	 *
	 * @return the deleted of this fragment entry link
	 */
	public Boolean getDeleted();

	/**
	 * Sets the deleted of this fragment entry link.
	 *
	 * @param deleted the deleted of this fragment entry link
	 */
	public void setDeleted(Boolean deleted);

	/**
	 * Returns the last propagation date of this fragment entry link.
	 *
	 * @return the last propagation date of this fragment entry link
	 */
	public Date getLastPropagationDate();

	/**
	 * Sets the last propagation date of this fragment entry link.
	 *
	 * @param lastPropagationDate the last propagation date of this fragment entry link
	 */
	public void setLastPropagationDate(Date lastPropagationDate);

	/**
	 * Returns the last publish date of this fragment entry link.
	 *
	 * @return the last publish date of this fragment entry link
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this fragment entry link.
	 *
	 * @param lastPublishDate the last publish date of this fragment entry link
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	@Override
	public FragmentEntryLink cloneWithOriginalValues();

}