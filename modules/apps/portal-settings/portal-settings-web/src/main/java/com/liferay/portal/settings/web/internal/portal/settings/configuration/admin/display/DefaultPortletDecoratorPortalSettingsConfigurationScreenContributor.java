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

package com.liferay.portal.settings.web.internal.portal.settings.configuration.admin.display;


import com.liferay.portal.settings.configuration.admin.display.PortalSettingsConfigurationScreenContributor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author João Torres
 */
@Component(service = PortalSettingsConfigurationScreenContributor.class)
public class DefaultPortletDecoratorPortalSettingsConfigurationScreenContributor
	extends BaseEditCompanyPortalSettingsConfigurationScreenContributor {

	@Override
	public String getCategoryKey() {
		return "default-portlet-decorator";
	}

	@Override
	public String getJspPath() {
		return "/portal_settings/default_portlet_decorator_configuration.jsp";
	}

	@Override
	public String getKey() {
		return "default_portlet_decorator_configuration";
	}


	@Override
	public void setAttributes(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {




	}



}