package com.liferay.portal.settings.web.internal.portal.settings.configuration.admin.category;

import com.liferay.configuration.admin.category.ConfigurationCategory;

public class DefaultPortletDecoratorConfigurationCategory implements
	ConfigurationCategory {

	@Override
	public String getCategoryIcon() {
		return "format";
	}

	@Override
	public String getCategoryKey() {
		return "default-portlet-decorator";
	}

	@Override
	public String getCategorySection() {
		return "platform";
	}
}
