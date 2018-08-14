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

package com.liferay.message.boards.uad.anonymizer;

import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.service.MBMessageLocalService;
import com.liferay.message.boards.uad.constants.MBUADConstants;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the message-boards message UAD anonymizer.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link MBMessageUADAnonymizer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseMBMessageUADAnonymizer
	extends DynamicQueryUADAnonymizer<MBMessage> {
	@Override
	public void autoAnonymize(MBMessage mbMessage, long userId,
		User anonymousUser) throws PortalException {
		if (mbMessage.getUserId() == userId) {
			mbMessage.setUserId(anonymousUser.getUserId());
			mbMessage.setUserName(anonymousUser.getFullName());
		}

		if (mbMessage.getStatusByUserId() == userId) {
			mbMessage.setStatusByUserId(anonymousUser.getUserId());
			mbMessage.setStatusByUserName(anonymousUser.getFullName());
		}

		mbMessageLocalService.updateMBMessage(mbMessage);
	}

	@Override
	public void delete(MBMessage mbMessage) throws PortalException {
		mbMessageLocalService.deleteMessage(mbMessage);
	}

	@Override
	public Class<MBMessage> getTypeClass() {
		return MBMessage.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return mbMessageLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return MBUADConstants.USER_ID_FIELD_NAMES_MB_MESSAGE;
	}

	@Reference
	protected MBMessageLocalService mbMessageLocalService;
}