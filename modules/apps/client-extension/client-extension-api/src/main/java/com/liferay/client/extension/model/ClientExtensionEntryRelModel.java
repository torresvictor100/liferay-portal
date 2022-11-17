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

package com.liferay.client.extension.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ContainerModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ClientExtensionEntryRel service. Represents a row in the &quot;ClientExtensionEntryRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.client.extension.model.impl.ClientExtensionEntryRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.client.extension.model.impl.ClientExtensionEntryRelImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ClientExtensionEntryRel
 * @generated
 */
@ProviderType
public interface ClientExtensionEntryRelModel
	extends AttachedModel, BaseModel<ClientExtensionEntryRel>, ContainerModel,
			CTModel<ClientExtensionEntryRel>, MVCCModel, ShardedModel,
			StagedGroupedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a client extension entry rel model instance should use the {@link ClientExtensionEntryRel} interface instead.
	 */

	/**
	 * Returns the primary key of this client extension entry rel.
	 *
	 * @return the primary key of this client extension entry rel
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this client extension entry rel.
	 *
	 * @param primaryKey the primary key of this client extension entry rel
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this client extension entry rel.
	 *
	 * @return the mvcc version of this client extension entry rel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this client extension entry rel.
	 *
	 * @param mvccVersion the mvcc version of this client extension entry rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this client extension entry rel.
	 *
	 * @return the ct collection ID of this client extension entry rel
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this client extension entry rel.
	 *
	 * @param ctCollectionId the ct collection ID of this client extension entry rel
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this client extension entry rel.
	 *
	 * @return the uuid of this client extension entry rel
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this client extension entry rel.
	 *
	 * @param uuid the uuid of this client extension entry rel
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this client extension entry rel.
	 *
	 * @return the external reference code of this client extension entry rel
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this client extension entry rel.
	 *
	 * @param externalReferenceCode the external reference code of this client extension entry rel
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the client extension entry rel ID of this client extension entry rel.
	 *
	 * @return the client extension entry rel ID of this client extension entry rel
	 */
	public long getClientExtensionEntryRelId();

	/**
	 * Sets the client extension entry rel ID of this client extension entry rel.
	 *
	 * @param clientExtensionEntryRelId the client extension entry rel ID of this client extension entry rel
	 */
	public void setClientExtensionEntryRelId(long clientExtensionEntryRelId);

	/**
	 * Returns the group ID of this client extension entry rel.
	 *
	 * @return the group ID of this client extension entry rel
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this client extension entry rel.
	 *
	 * @param groupId the group ID of this client extension entry rel
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this client extension entry rel.
	 *
	 * @return the company ID of this client extension entry rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this client extension entry rel.
	 *
	 * @param companyId the company ID of this client extension entry rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this client extension entry rel.
	 *
	 * @return the user ID of this client extension entry rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this client extension entry rel.
	 *
	 * @param userId the user ID of this client extension entry rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this client extension entry rel.
	 *
	 * @return the user uuid of this client extension entry rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this client extension entry rel.
	 *
	 * @param userUuid the user uuid of this client extension entry rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this client extension entry rel.
	 *
	 * @return the user name of this client extension entry rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this client extension entry rel.
	 *
	 * @param userName the user name of this client extension entry rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this client extension entry rel.
	 *
	 * @return the create date of this client extension entry rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this client extension entry rel.
	 *
	 * @param createDate the create date of this client extension entry rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this client extension entry rel.
	 *
	 * @return the modified date of this client extension entry rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this client extension entry rel.
	 *
	 * @param modifiedDate the modified date of this client extension entry rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this client extension entry rel.
	 *
	 * @return the fully qualified class name of this client extension entry rel
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this client extension entry rel.
	 *
	 * @return the class name ID of this client extension entry rel
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this client extension entry rel.
	 *
	 * @param classNameId the class name ID of this client extension entry rel
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this client extension entry rel.
	 *
	 * @return the class pk of this client extension entry rel
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this client extension entry rel.
	 *
	 * @param classPK the class pk of this client extension entry rel
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the cet external reference code of this client extension entry rel.
	 *
	 * @return the cet external reference code of this client extension entry rel
	 */
	@AutoEscape
	public String getCETExternalReferenceCode();

	/**
	 * Sets the cet external reference code of this client extension entry rel.
	 *
	 * @param cetExternalReferenceCode the cet external reference code of this client extension entry rel
	 */
	public void setCETExternalReferenceCode(String cetExternalReferenceCode);

	/**
	 * Returns the type of this client extension entry rel.
	 *
	 * @return the type of this client extension entry rel
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this client extension entry rel.
	 *
	 * @param type the type of this client extension entry rel
	 */
	public void setType(String type);

	/**
	 * Returns the type settings of this client extension entry rel.
	 *
	 * @return the type settings of this client extension entry rel
	 */
	@AutoEscape
	public String getTypeSettings();

	/**
	 * Sets the type settings of this client extension entry rel.
	 *
	 * @param typeSettings the type settings of this client extension entry rel
	 */
	public void setTypeSettings(String typeSettings);

	/**
	 * Returns the last publish date of this client extension entry rel.
	 *
	 * @return the last publish date of this client extension entry rel
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this client extension entry rel.
	 *
	 * @param lastPublishDate the last publish date of this client extension entry rel
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the container model ID of this client extension entry rel.
	 *
	 * @return the container model ID of this client extension entry rel
	 */
	@Override
	public long getContainerModelId();

	/**
	 * Sets the container model ID of this client extension entry rel.
	 *
	 * @param containerModelId the container model ID of this client extension entry rel
	 */
	@Override
	public void setContainerModelId(long containerModelId);

	/**
	 * Returns the container name of this client extension entry rel.
	 *
	 * @return the container name of this client extension entry rel
	 */
	@Override
	public String getContainerModelName();

	/**
	 * Returns the parent container model ID of this client extension entry rel.
	 *
	 * @return the parent container model ID of this client extension entry rel
	 */
	@Override
	public long getParentContainerModelId();

	/**
	 * Sets the parent container model ID of this client extension entry rel.
	 *
	 * @param parentContainerModelId the parent container model ID of this client extension entry rel
	 */
	@Override
	public void setParentContainerModelId(long parentContainerModelId);

	@Override
	public ClientExtensionEntryRel cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}