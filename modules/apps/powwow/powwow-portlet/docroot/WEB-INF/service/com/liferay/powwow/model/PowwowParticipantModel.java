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

package com.liferay.powwow.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the PowwowParticipant service. Represents a row in the &quot;PowwowParticipant&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.powwow.model.impl.PowwowParticipantModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.powwow.model.impl.PowwowParticipantImpl</code>.
 * </p>
 *
 * @author Shinn Lok
 * @see PowwowParticipant
 * @generated
 */
public interface PowwowParticipantModel
	extends BaseModel<PowwowParticipant>, GroupedModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a powwow participant model instance should use the {@link PowwowParticipant} interface instead.
	 */

	/**
	 * Returns the primary key of this powwow participant.
	 *
	 * @return the primary key of this powwow participant
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this powwow participant.
	 *
	 * @param primaryKey the primary key of this powwow participant
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the powwow participant ID of this powwow participant.
	 *
	 * @return the powwow participant ID of this powwow participant
	 */
	public long getPowwowParticipantId();

	/**
	 * Sets the powwow participant ID of this powwow participant.
	 *
	 * @param powwowParticipantId the powwow participant ID of this powwow participant
	 */
	public void setPowwowParticipantId(long powwowParticipantId);

	/**
	 * Returns the group ID of this powwow participant.
	 *
	 * @return the group ID of this powwow participant
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this powwow participant.
	 *
	 * @param groupId the group ID of this powwow participant
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this powwow participant.
	 *
	 * @return the company ID of this powwow participant
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this powwow participant.
	 *
	 * @param companyId the company ID of this powwow participant
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this powwow participant.
	 *
	 * @return the user ID of this powwow participant
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this powwow participant.
	 *
	 * @param userId the user ID of this powwow participant
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this powwow participant.
	 *
	 * @return the user uuid of this powwow participant
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this powwow participant.
	 *
	 * @param userUuid the user uuid of this powwow participant
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this powwow participant.
	 *
	 * @return the user name of this powwow participant
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this powwow participant.
	 *
	 * @param userName the user name of this powwow participant
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this powwow participant.
	 *
	 * @return the create date of this powwow participant
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this powwow participant.
	 *
	 * @param createDate the create date of this powwow participant
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this powwow participant.
	 *
	 * @return the modified date of this powwow participant
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this powwow participant.
	 *
	 * @param modifiedDate the modified date of this powwow participant
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the powwow meeting ID of this powwow participant.
	 *
	 * @return the powwow meeting ID of this powwow participant
	 */
	public long getPowwowMeetingId();

	/**
	 * Sets the powwow meeting ID of this powwow participant.
	 *
	 * @param powwowMeetingId the powwow meeting ID of this powwow participant
	 */
	public void setPowwowMeetingId(long powwowMeetingId);

	/**
	 * Returns the name of this powwow participant.
	 *
	 * @return the name of this powwow participant
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this powwow participant.
	 *
	 * @param name the name of this powwow participant
	 */
	public void setName(String name);

	/**
	 * Returns the participant user ID of this powwow participant.
	 *
	 * @return the participant user ID of this powwow participant
	 */
	public long getParticipantUserId();

	/**
	 * Sets the participant user ID of this powwow participant.
	 *
	 * @param participantUserId the participant user ID of this powwow participant
	 */
	public void setParticipantUserId(long participantUserId);

	/**
	 * Returns the participant user uuid of this powwow participant.
	 *
	 * @return the participant user uuid of this powwow participant
	 */
	public String getParticipantUserUuid();

	/**
	 * Sets the participant user uuid of this powwow participant.
	 *
	 * @param participantUserUuid the participant user uuid of this powwow participant
	 */
	public void setParticipantUserUuid(String participantUserUuid);

	/**
	 * Returns the email address of this powwow participant.
	 *
	 * @return the email address of this powwow participant
	 */
	@AutoEscape
	public String getEmailAddress();

	/**
	 * Sets the email address of this powwow participant.
	 *
	 * @param emailAddress the email address of this powwow participant
	 */
	public void setEmailAddress(String emailAddress);

	/**
	 * Returns the type of this powwow participant.
	 *
	 * @return the type of this powwow participant
	 */
	public int getType();

	/**
	 * Sets the type of this powwow participant.
	 *
	 * @param type the type of this powwow participant
	 */
	public void setType(int type);

	/**
	 * Returns the status of this powwow participant.
	 *
	 * @return the status of this powwow participant
	 */
	public int getStatus();

	/**
	 * Sets the status of this powwow participant.
	 *
	 * @param status the status of this powwow participant
	 */
	public void setStatus(int status);

}