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

package com.liferay.client.extension.type;

import com.liferay.client.extension.type.annotation.CETProperty;
import com.liferay.client.extension.type.annotation.CETType;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 */
@CETType(
	description = "This is a customElement description", name = "customElement"
)
@ProviderType
public interface CETCustomElement extends CET {

	@CETProperty(
		defaultValue = "custom-element.css", name = "cssURLs", type = "list"
	)
	public String getCSSURLs();

	@CETProperty(
		defaultValue = "custom-element", name = "friendlyURLMapping",
		type = "string"
	)
	public String getFriendlyURLMapping();

	@CETProperty(
		defaultValue = "custom-element", name = "htmlElementName",
		type = "string"
	)
	public String getHTMLElementName();

	@CETProperty(
		defaultValue = "category.remote-apps", name = "portletCategoryName",
		type = "string"
	)
	public String getPortletCategoryName();

	@CETProperty(defaultValue = "index.js", name = "urls", type = "list")
	public String getURLs();

	@CETProperty(
		defaultValue = "false", name = "instanceable", type = "boolean"
	)
	public boolean isInstanceable();

	@CETProperty(defaultValue = "false", name = "useESM", type = "boolean")
	public boolean isUseESM();

}