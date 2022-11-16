package com.liferay.portal.settings.web.internal.portal.settings.configuration.admin.display;

import com.liferay.portal.settings.configuration.admin.display.PortalSettingsConfigurationScreenContributor;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
