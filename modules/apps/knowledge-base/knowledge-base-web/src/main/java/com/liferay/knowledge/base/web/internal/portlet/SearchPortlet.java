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

package com.liferay.knowledge.base.web.internal.portlet;

import com.liferay.knowledge.base.constants.KBPortletKeys;
import com.liferay.knowledge.base.exception.NoSuchArticleException;
import com.liferay.knowledge.base.exception.NoSuchCommentException;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.web.internal.constants.KBWebKeys;
import com.liferay.portal.kernel.exception.NoSuchSubscriptionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=knowledge-base-portlet knowledge-base-portlet-search",
		"com.liferay.portlet.display-category=category.cms",
		"com.liferay.portlet.header-portlet-css=/admin/css/common.css",
		"com.liferay.portlet.icon=/icons/search.png",
		"com.liferay.portlet.restore-current-view=false",
		"com.liferay.portlet.scopeable=true",
		"javax.portlet.display-name=Knowledge Base Search",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.always-send-redirect=true",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/knowledge_base/view",
		"javax.portlet.name=" + KBPortletKeys.KNOWLEDGE_BASE_SEARCH,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=categoryId",
		"javax.portlet.supported-public-render-parameter=tag"
	},
	service = Portlet.class
)
public class SearchPortlet extends BaseKBPortlet {

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (SessionErrors.contains(
				renderRequest, NoSuchArticleException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, NoSuchCommentException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, NoSuchSubscriptionException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, PrincipalException.getNestedClasses())) {

			include("/admin/common/error.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	@Override
	protected void doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			renderRequest.setAttribute(
				KBWebKeys.KNOWLEDGE_BASE_KB_ARTICLE,
				_getKBArticle(renderRequest));

			renderRequest.setAttribute(
				KBWebKeys.KNOWLEDGE_BASE_STATUS,
				WorkflowConstants.STATUS_APPROVED);
		}
		catch (NoSuchArticleException | PrincipalException exception) {
			SessionErrors.add(renderRequest, exception.getClass());
		}
		catch (PortalException portalException) {
			throw new PortletException(portalException);
		}
	}

	private KBArticle _getKBArticle(RenderRequest renderRequest)
		throws PortalException {

		long resourcePrimKey = ParamUtil.getLong(
			renderRequest, "resourcePrimKey");

		if (resourcePrimKey > 0) {
			return kbArticleService.getLatestKBArticle(
				resourcePrimKey, WorkflowConstants.STATUS_APPROVED);
		}

		return null;
	}

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.knowledge.base.web)(&(release.schema.version>=1.2.0)(!(release.schema.version>=2.0.0))))"
	)
	private Release _release;

}