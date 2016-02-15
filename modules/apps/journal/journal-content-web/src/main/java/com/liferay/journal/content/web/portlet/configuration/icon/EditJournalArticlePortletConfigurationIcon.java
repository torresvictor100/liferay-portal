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

package com.liferay.journal.content.web.portlet.configuration.icon;

import com.liferay.journal.content.web.configuration.JournalContentPortletInstanceConfiguration;
import com.liferay.journal.content.web.display.context.JournalContentDisplayContext;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Pavel Savinov
 */
public class EditJournalArticlePortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	public EditJournalArticlePortletConfigurationIcon(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		super(portletRequest);

		createJournalContentDisplayContext(portletRequest, portletResponse);
	}

	@Override
	public String getMessage(PortletRequest portletRequest) {
		return "edit-web-content";
	}

	@Override
	public String getOnClick(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		StringBundler sb = new StringBundler(14);

		JournalArticle article = _journalContentDisplayContext.getArticle();

		if (article == null) {
			return StringPool.BLANK;
		}

		sb.append("Liferay.Util.openWindow({bodyCssClass: ");
		sb.append("'dialog-with-footer', destroyOnHide: true, id: '");

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		sb.append(HtmlUtil.escape(portletDisplay.getNamespace()));
		sb.append("editAsset', namespace: '");
		sb.append(portletDisplay.getNamespace());
		sb.append("', portlet: '#p_p_id_");
		sb.append(portletDisplay.getId());
		sb.append("_', portletId: '");
		sb.append(portletDisplay.getId());
		sb.append("', title: '");
		sb.append(article.getTitle(themeDisplay.getLocale()));
		sb.append("', uri: '");
		sb.append(
			HtmlUtil.escapeJS(_journalContentDisplayContext.getURLEdit()));
		sb.append("'}); return false;");

		return sb.toString();
	}

	@Override
	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return _journalContentDisplayContext.getURLEdit();
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		if (_journalContentDisplayContext.isShowEditArticleIcon()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isToolTip() {
		return false;
	}

	protected void createJournalContentDisplayContext(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		try {
			JournalContentPortletInstanceConfiguration
				journalContentPortletInstanceConfiguration =
					portletDisplay.getPortletInstanceConfiguration(
						JournalContentPortletInstanceConfiguration.class);

			_journalContentDisplayContext = new JournalContentDisplayContext(
				portletRequest, portletResponse,
				journalContentPortletInstanceConfiguration);
		}
		catch (Exception e) {
			_log.error("Unable to create display context", e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditJournalArticlePortletConfigurationIcon.class);

	private JournalContentDisplayContext _journalContentDisplayContext;

}