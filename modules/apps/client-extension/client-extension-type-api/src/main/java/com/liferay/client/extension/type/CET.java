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

import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public interface CET {

	public String getBaseURL();

	public long getCompanyId();

	public Date getCreateDate();

	@CETProperty(name = "description", type = CETProperty.Type.String)
	public String getDescription();

	public String getEditJSP();

	public String getExternalReferenceCode();

	public Date getModifiedDate();

	@CETProperty(name = "name", type = CETProperty.Type.String)
	public String getName(Locale locale);

	public Properties getProperties();

	@CETProperty(
		defaultValue = "https://www.liferay.com", name = "sourceCodeURL",
		type = CETProperty.Type.String
	)
	public String getSourceCodeURL();

	public int getStatus();

	@CETProperty(name = "type", type = CETProperty.Type.String)
	public String getType();

	public String getTypeSettings();

	public default String getViewJSP() {
		return null;
	}

	public boolean hasProperties();

	public boolean isReadOnly();

}