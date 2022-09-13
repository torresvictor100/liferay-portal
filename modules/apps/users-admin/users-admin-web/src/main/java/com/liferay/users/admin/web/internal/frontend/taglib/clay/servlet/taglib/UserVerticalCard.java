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

package com.liferay.users.admin.web.internal.frontend.taglib.clay.servlet.taglib;

import com.liferay.frontend.taglib.clay.servlet.taglib.BaseUserCard;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.model.User;
import com.liferay.taglib.util.LexiconUtil;
import com.liferay.users.admin.web.internal.servlet.taglib.util.UserActionDropdownItems;

import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Eudaldo Alonso
 */
public class UserVerticalCard extends BaseUserCard {

	public UserVerticalCard(
		RenderRequest renderRequest, RenderResponse renderResponse,
		RowChecker rowChecker, boolean showActions, User user) {

		super(user, renderRequest, rowChecker);

		_renderResponse = renderResponse;
		_showActions = showActions;
	}

	public UserVerticalCard(
		RenderRequest renderRequest, RowChecker rowChecker, User user) {

		super(user, renderRequest, rowChecker);

		_renderResponse = null;
		_showActions = false;
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() {
		if (!_showActions) {
			return null;
		}

		UserActionDropdownItems userActionDropdownItems =
			new UserActionDropdownItems(renderRequest, _renderResponse, user);

		return userActionDropdownItems.getActionDropdownItems();
	}

	@Override
	public String getUserColorClass() {
		return "sticker-user-icon " + LexiconUtil.getUserColorCssClass(user);
	}

	private final RenderResponse _renderResponse;
	private final boolean _showActions;

}