package com.liferay.site.navigation.language.web.internal.configuration.settings.definition;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import com.liferay.site.navigation.language.web.internal.configuration.SiteNavigationLocaleFriendlyUrlConfiguration;
import org.osgi.service.component.annotations.Component;

@Component(service = ConfigurationBeanDeclaration.class)
public class LocaleFriendlyUrlStyleConfigurationBeanDeclaration
	implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return SiteNavigationLocaleFriendlyUrlConfiguration.class;
	}
}
