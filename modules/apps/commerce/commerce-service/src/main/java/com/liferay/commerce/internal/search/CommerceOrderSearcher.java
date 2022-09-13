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

package com.liferay.commerce.internal.search;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.portal.kernel.search.BaseSearcher;

import org.osgi.service.component.annotations.Component;

/**
 * @author Danny Situ
 */
@Component(
	enabled = false, immediate = true,
	property = "model.class.name=com.liferay.commerce.model.CommerceOrder",
	service = BaseSearcher.class
)
public class CommerceOrderSearcher extends BaseSearcher {

	public CommerceOrderSearcher() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return _CLASS_NAME;
	}

	private static final String _CLASS_NAME = CommerceOrder.class.getName();

}