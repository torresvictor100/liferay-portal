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

package com.liferay.portlet.configuration.css.web.internal.display;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.settings.configuration.admin.display.PortalSettingsConfigurationScreenContributor;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.configuration.css.web.internal.decorator.configuration.DecoratorConfiguration;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author João Victor Torres Araujo
 */
@Component(service = PortalSettingsConfigurationScreenContributor.class)
public class DecoratorPortalSettingsConfigurationScreenContributor
	implements PortalSettingsConfigurationScreenContributor {

	@Override
	public String getCategoryKey() {
		return "decorator-portlet";
	}

	@Override
	public String getJspPath() {
		return "/portal_settings/decorator_configuration.jsp";
	}

	@Override
	public String getKey() {
		return "decorator-configuration";
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return _language.get(resourceBundle, "decorator-configuration");
	}

	@Override
	public String getSaveMVCActionCommandName() {
		return "/configuration_admin/save_decorator";
	}

	@Override
	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Override
	public void setAttributes(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		DecoratorConfiguration decoratorConfiguration = null;

		try {
			decoratorConfiguration =
				_configurationProvider.getCompanyConfiguration(
					DecoratorConfiguration.class,
					CompanyThreadLocal.getCompanyId());
		}
		catch (PortalException portalException) {
			ReflectionUtil.throwException(portalException);
		}

		if (Validator.isNotNull(
				decoratorConfiguration.applicationDecorators())) {

			PropsUtil.set(
				PropsKeys.DEFAULT_PORTLET_DECORATOR_ID,
				decoratorConfiguration.applicationDecorators());

			PropsUtil.set(
				PropsValues.DEFAULT_PORTLET_DECORATOR_ID,
				PropsUtil.get(PropsKeys.DEFAULT_PORTLET_DECORATOR_ID));
		}

		httpServletRequest.setAttribute(
			DecoratorConfiguration.class.getName(), decoratorConfiguration);
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private Language _language;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.portlet.configuration.css.web)",
		unbind = "-"
	)
	private ServletContext _servletContext;

}