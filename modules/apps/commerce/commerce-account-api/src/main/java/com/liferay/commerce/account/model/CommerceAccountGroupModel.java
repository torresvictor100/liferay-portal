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

package com.liferay.commerce.account.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommerceAccountGroup service. Represents a row in the &quot;CommerceAccountGroup&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.account.model.impl.CommerceAccountGroupModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.account.model.impl.CommerceAccountGroupImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceAccountGroup
 * @generated
 */
@ProviderType
public interface CommerceAccountGroupModel
	extends AuditedModel, BaseModel<CommerceAccountGroup>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce account group model instance should use the {@link CommerceAccountGroup} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce account group.
	 *
	 * @return the primary key of this commerce account group
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce account group.
	 *
	 * @param primaryKey the primary key of this commerce account group
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the external reference code of this commerce account group.
	 *
	 * @return the external reference code of this commerce account group
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this commerce account group.
	 *
	 * @param externalReferenceCode the external reference code of this commerce account group
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the commerce account group ID of this commerce account group.
	 *
	 * @return the commerce account group ID of this commerce account group
	 */
	public long getCommerceAccountGroupId();

	/**
	 * Sets the commerce account group ID of this commerce account group.
	 *
	 * @param commerceAccountGroupId the commerce account group ID of this commerce account group
	 */
	public void setCommerceAccountGroupId(long commerceAccountGroupId);

	/**
	 * Returns the company ID of this commerce account group.
	 *
	 * @return the company ID of this commerce account group
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce account group.
	 *
	 * @param companyId the company ID of this commerce account group
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce account group.
	 *
	 * @return the user ID of this commerce account group
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce account group.
	 *
	 * @param userId the user ID of this commerce account group
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce account group.
	 *
	 * @return the user uuid of this commerce account group
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce account group.
	 *
	 * @param userUuid the user uuid of this commerce account group
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce account group.
	 *
	 * @return the user name of this commerce account group
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce account group.
	 *
	 * @param userName the user name of this commerce account group
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce account group.
	 *
	 * @return the create date of this commerce account group
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce account group.
	 *
	 * @param createDate the create date of this commerce account group
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce account group.
	 *
	 * @return the modified date of this commerce account group
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce account group.
	 *
	 * @param modifiedDate the modified date of this commerce account group
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this commerce account group.
	 *
	 * @return the name of this commerce account group
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this commerce account group.
	 *
	 * @param name the name of this commerce account group
	 */
	public void setName(String name);

	/**
	 * Returns the type of this commerce account group.
	 *
	 * @return the type of this commerce account group
	 */
	public int getType();

	/**
	 * Sets the type of this commerce account group.
	 *
	 * @param type the type of this commerce account group
	 */
	public void setType(int type);

	/**
	 * Returns the system of this commerce account group.
	 *
	 * @return the system of this commerce account group
	 */
	public boolean getSystem();

	/**
	 * Returns <code>true</code> if this commerce account group is system.
	 *
	 * @return <code>true</code> if this commerce account group is system; <code>false</code> otherwise
	 */
	public boolean isSystem();

	/**
	 * Sets whether this commerce account group is system.
	 *
	 * @param system the system of this commerce account group
	 */
	public void setSystem(boolean system);

	@Override
	public CommerceAccountGroup cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}