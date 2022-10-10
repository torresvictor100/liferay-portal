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

package com.liferay.portlet.configuration.css.web.internal.decorator.configuration;

import com.liferay.configuration.admin.category.ConfigurationCategory;

import org.osgi.service.component.annotations.Component;

/**
 * @author João Victor Torres Araujo
 */
@Component(service = ConfigurationCategory.class)
public class DecoratorConfigurationCategory implements ConfigurationCategory {

	@Override
	public String getCategoryIcon() {
		return "cog";
	}

	@Override
	public String getCategoryKey() {
		return "decorator-portlet";
	}

	@Override
	public String getCategorySection() {
		return "other";
	}

}