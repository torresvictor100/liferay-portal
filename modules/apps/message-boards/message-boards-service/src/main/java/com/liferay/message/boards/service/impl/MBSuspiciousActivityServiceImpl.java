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

package com.liferay.message.boards.service.impl;

import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.model.MBSuspiciousActivity;
import com.liferay.message.boards.service.base.MBSuspiciousActivityServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import com.liferay.portal.kernel.security.auth.GuestOrUserUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=mb",
		"json.web.service.context.path=MBSuspiciousActivity"
	},
	service = AopService.class
)
public class MBSuspiciousActivityServiceImpl
	extends MBSuspiciousActivityServiceBaseImpl {

	@Override
	public MBSuspiciousActivity addOrUpdateMessageSuspiciousActivity(
			long messageId, String reason)
		throws PortalException {

		_MBSuspiciousActivityResourcePermission.check(getPermissionChecker(),
			messageId, ActionKeys.SUBMIT );

		return mbSuspiciousActivityLocalService.
			addOrUpdateMessageSuspiciousActivity(
				getUserId(), messageId, reason);
	}

	@Override
	public MBSuspiciousActivity addOrUpdateThreadSuspiciousActivity(
			long threadId, String reason)
		throws PortalException {

		_MBSuspiciousActivityResourcePermission.check(getPermissionChecker(),
			threadId, ActionKeys.SUBMIT );

		return mbSuspiciousActivityLocalService.
			addOrUpdateThreadSuspiciousActivity(getUserId(), threadId, reason);
	}

	@Override
	public MBSuspiciousActivity deleteSuspiciousActivity(
			long suspiciousActivityId)
		throws PortalException {

		_MBSuspiciousActivityResourcePermission.check(getPermissionChecker(),
			suspiciousActivityId, ActionKeys.DELETE );

		// TODO Add permission checks for remote methods

		return mbSuspiciousActivityLocalService.deleteSuspiciousActivity(
			suspiciousActivityId);
	}

	@Override
	public List<MBSuspiciousActivity> getMessageSuspiciousActivities(
		long messageId) throws PortalException {

		_MBSuspiciousActivityResourcePermission.check(getPermissionChecker(),
			messageId, ActionKeys.VIEW_CONTROL_PANEL );

		return mbSuspiciousActivityLocalService.getMessageSuspiciousActivities(
			messageId);
	}

	@Override
	public MBSuspiciousActivity getSuspiciousActivity(long suspiciousActivityId)
		throws PortalException {

		_MBSuspiciousActivityResourcePermission.check(getPermissionChecker(),
			suspiciousActivityId, ActionKeys.VIEW_CONTROL_PANEL);

		return mbSuspiciousActivityLocalService.getSuspiciousActivity(
			suspiciousActivityId);
	}

	@Override
	public List<MBSuspiciousActivity> getThreadSuspiciousActivities(
		long threadId) throws PortalException {

		_MBSuspiciousActivityResourcePermission.check(getPermissionChecker(),
			threadId, ActionKeys.VIEW_CONTROL_PANEL );

		return mbSuspiciousActivityLocalService.getThreadSuspiciousActivities(
			threadId);
	}

	@Override
	public MBSuspiciousActivity updateValidated(long suspiciousActivityId)
		throws PortalException {

		_MBSuspiciousActivityResourcePermission.check(getPermissionChecker(),
			suspiciousActivityId, ActionKeys.UPDATE );

		return mbSuspiciousActivityLocalService.updateValidated(
			suspiciousActivityId);
	}

	public PermissionChecker getPermissionChecker() throws PrincipalException {
		return GuestOrUserUtil.getPermissionChecker();
	}

	private ModelResourcePermission<MBSuspiciousActivity> _MBSuspiciousActivityResourcePermission;

}