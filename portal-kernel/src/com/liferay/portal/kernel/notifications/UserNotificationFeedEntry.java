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

package com.liferay.portal.kernel.notifications;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Jonathan Lee
 */
public class UserNotificationFeedEntry {

	public UserNotificationFeedEntry(
		boolean actionable, String body, String link, boolean applicable,
		String title) {

		setActionable(actionable);
		setApplicable(applicable);
		setBody(body);
		setLink(link);
		setTitle(title);
	}

	public String getBody() {
		return _body;
	}

	public String getLink() {
		return _link;
	}

	public String getPortletId() {
		return _portletId;
	}

	public String getTitle() {
		return _title;
	}

	public boolean isActionable() {
		return _actionable;
	}

	public boolean isApplicable() {
		return _applicable;
	}

	public boolean isOpenDialog() {
		return _openDialog;
	}

	public void setActionable(boolean actionable) {
		_actionable = actionable;
	}

	public void setApplicable(boolean applicable) {
		_applicable = applicable;
	}

	public void setBody(String body) {
		_body = GetterUtil.getString(body);
	}

	public void setLink(String link) {
		_link = GetterUtil.getString(link);
	}

	public void setOpenDialog(boolean openDialog) {
		_openDialog = openDialog;
	}

	public void setPortletId(String portletId) {
		_portletId = GetterUtil.getString(portletId);
	}

	public void setTitle(String title) {
		_title = title;
	}

	private boolean _actionable;
	private boolean _applicable = true;
	private String _body;
	private String _link;
	private boolean _openDialog;
	private String _portletId = StringPool.BLANK;
	private String _title;

}