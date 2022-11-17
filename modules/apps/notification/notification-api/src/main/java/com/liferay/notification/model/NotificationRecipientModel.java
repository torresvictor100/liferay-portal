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

package com.liferay.notification.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the NotificationRecipient service. Represents a row in the &quot;NotificationRecipient&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.notification.model.impl.NotificationRecipientModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.notification.model.impl.NotificationRecipientImpl</code>.
 * </p>
 *
 * @author Gabriel Albuquerque
 * @see NotificationRecipient
 * @generated
 */
@ProviderType
public interface NotificationRecipientModel
	extends AttachedModel, BaseModel<NotificationRecipient>, MVCCModel,
			ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a notification recipient model instance should use the {@link NotificationRecipient} interface instead.
	 */

	/**
	 * Returns the primary key of this notification recipient.
	 *
	 * @return the primary key of this notification recipient
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this notification recipient.
	 *
	 * @param primaryKey the primary key of this notification recipient
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this notification recipient.
	 *
	 * @return the mvcc version of this notification recipient
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this notification recipient.
	 *
	 * @param mvccVersion the mvcc version of this notification recipient
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this notification recipient.
	 *
	 * @return the uuid of this notification recipient
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this notification recipient.
	 *
	 * @param uuid the uuid of this notification recipient
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the notification recipient ID of this notification recipient.
	 *
	 * @return the notification recipient ID of this notification recipient
	 */
	public long getNotificationRecipientId();

	/**
	 * Sets the notification recipient ID of this notification recipient.
	 *
	 * @param notificationRecipientId the notification recipient ID of this notification recipient
	 */
	public void setNotificationRecipientId(long notificationRecipientId);

	/**
	 * Returns the company ID of this notification recipient.
	 *
	 * @return the company ID of this notification recipient
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this notification recipient.
	 *
	 * @param companyId the company ID of this notification recipient
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this notification recipient.
	 *
	 * @return the user ID of this notification recipient
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this notification recipient.
	 *
	 * @param userId the user ID of this notification recipient
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this notification recipient.
	 *
	 * @return the user uuid of this notification recipient
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this notification recipient.
	 *
	 * @param userUuid the user uuid of this notification recipient
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this notification recipient.
	 *
	 * @return the user name of this notification recipient
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this notification recipient.
	 *
	 * @param userName the user name of this notification recipient
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this notification recipient.
	 *
	 * @return the create date of this notification recipient
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this notification recipient.
	 *
	 * @param createDate the create date of this notification recipient
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this notification recipient.
	 *
	 * @return the modified date of this notification recipient
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this notification recipient.
	 *
	 * @param modifiedDate the modified date of this notification recipient
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this notification recipient.
	 *
	 * @return the fully qualified class name of this notification recipient
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this notification recipient.
	 *
	 * @return the class name ID of this notification recipient
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this notification recipient.
	 *
	 * @param classNameId the class name ID of this notification recipient
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this notification recipient.
	 *
	 * @return the class pk of this notification recipient
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this notification recipient.
	 *
	 * @param classPK the class pk of this notification recipient
	 */
	@Override
	public void setClassPK(long classPK);

	@Override
	public NotificationRecipient cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}