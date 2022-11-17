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

package com.liferay.commerce.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommerceOrderPayment service. Represents a row in the &quot;CommerceOrderPayment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.model.impl.CommerceOrderPaymentModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.model.impl.CommerceOrderPaymentImpl</code>.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderPayment
 * @generated
 */
@ProviderType
public interface CommerceOrderPaymentModel
	extends BaseModel<CommerceOrderPayment>, GroupedModel, MVCCModel,
			ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce order payment model instance should use the {@link CommerceOrderPayment} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce order payment.
	 *
	 * @return the primary key of this commerce order payment
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce order payment.
	 *
	 * @param primaryKey the primary key of this commerce order payment
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce order payment.
	 *
	 * @return the mvcc version of this commerce order payment
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce order payment.
	 *
	 * @param mvccVersion the mvcc version of this commerce order payment
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the commerce order payment ID of this commerce order payment.
	 *
	 * @return the commerce order payment ID of this commerce order payment
	 */
	public long getCommerceOrderPaymentId();

	/**
	 * Sets the commerce order payment ID of this commerce order payment.
	 *
	 * @param commerceOrderPaymentId the commerce order payment ID of this commerce order payment
	 */
	public void setCommerceOrderPaymentId(long commerceOrderPaymentId);

	/**
	 * Returns the group ID of this commerce order payment.
	 *
	 * @return the group ID of this commerce order payment
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this commerce order payment.
	 *
	 * @param groupId the group ID of this commerce order payment
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this commerce order payment.
	 *
	 * @return the company ID of this commerce order payment
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce order payment.
	 *
	 * @param companyId the company ID of this commerce order payment
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce order payment.
	 *
	 * @return the user ID of this commerce order payment
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce order payment.
	 *
	 * @param userId the user ID of this commerce order payment
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce order payment.
	 *
	 * @return the user uuid of this commerce order payment
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce order payment.
	 *
	 * @param userUuid the user uuid of this commerce order payment
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce order payment.
	 *
	 * @return the user name of this commerce order payment
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce order payment.
	 *
	 * @param userName the user name of this commerce order payment
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce order payment.
	 *
	 * @return the create date of this commerce order payment
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce order payment.
	 *
	 * @param createDate the create date of this commerce order payment
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce order payment.
	 *
	 * @return the modified date of this commerce order payment
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce order payment.
	 *
	 * @param modifiedDate the modified date of this commerce order payment
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the commerce order ID of this commerce order payment.
	 *
	 * @return the commerce order ID of this commerce order payment
	 */
	public long getCommerceOrderId();

	/**
	 * Sets the commerce order ID of this commerce order payment.
	 *
	 * @param commerceOrderId the commerce order ID of this commerce order payment
	 */
	public void setCommerceOrderId(long commerceOrderId);

	/**
	 * Returns the commerce payment method key of this commerce order payment.
	 *
	 * @return the commerce payment method key of this commerce order payment
	 */
	@AutoEscape
	public String getCommercePaymentMethodKey();

	/**
	 * Sets the commerce payment method key of this commerce order payment.
	 *
	 * @param commercePaymentMethodKey the commerce payment method key of this commerce order payment
	 */
	public void setCommercePaymentMethodKey(String commercePaymentMethodKey);

	/**
	 * Returns the content of this commerce order payment.
	 *
	 * @return the content of this commerce order payment
	 */
	@AutoEscape
	public String getContent();

	/**
	 * Sets the content of this commerce order payment.
	 *
	 * @param content the content of this commerce order payment
	 */
	public void setContent(String content);

	/**
	 * Returns the status of this commerce order payment.
	 *
	 * @return the status of this commerce order payment
	 */
	public int getStatus();

	/**
	 * Sets the status of this commerce order payment.
	 *
	 * @param status the status of this commerce order payment
	 */
	public void setStatus(int status);

	@Override
	public CommerceOrderPayment cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}