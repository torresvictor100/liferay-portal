package com.liferay.message.boards.moderation.internal.settings.definition;

import com.liferay.message.boards.moderation.configuration.MBTherdDeleteForMonthConfiguration;
import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import org.osgi.service.component.annotations.Component;

@Component(service = ConfigurationBeanDeclaration.class)
public class MBTherdDeleteForMonthConfigurationBeanDeclaration
	implements ConfigurationBeanDeclaration {
	@Override
	public Class<?> getConfigurationBeanClass() {
		return MBTherdDeleteForMonthConfiguration.class;
	}
}
