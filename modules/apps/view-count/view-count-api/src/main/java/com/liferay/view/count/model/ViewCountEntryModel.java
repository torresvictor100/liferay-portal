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

package com.liferay.view.count.model;

import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.view.count.service.persistence.ViewCountEntryPK;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ViewCountEntry service. Represents a row in the &quot;ViewCountEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.view.count.model.impl.ViewCountEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.view.count.model.impl.ViewCountEntryImpl</code>.
 * </p>
 *
 * @author Preston Crary
 * @see ViewCountEntry
 * @generated
 */
@ProviderType
public interface ViewCountEntryModel
	extends AttachedModel, BaseModel<ViewCountEntry>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a view count entry model instance should use the {@link ViewCountEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this view count entry.
	 *
	 * @return the primary key of this view count entry
	 */
	public ViewCountEntryPK getPrimaryKey();

	/**
	 * Sets the primary key of this view count entry.
	 *
	 * @param primaryKey the primary key of this view count entry
	 */
	public void setPrimaryKey(ViewCountEntryPK primaryKey);

	/**
	 * Returns the company ID of this view count entry.
	 *
	 * @return the company ID of this view count entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this view count entry.
	 *
	 * @param companyId the company ID of this view count entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the fully qualified class name of this view count entry.
	 *
	 * @return the fully qualified class name of this view count entry
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this view count entry.
	 *
	 * @return the class name ID of this view count entry
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this view count entry.
	 *
	 * @param classNameId the class name ID of this view count entry
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this view count entry.
	 *
	 * @return the class pk of this view count entry
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this view count entry.
	 *
	 * @param classPK the class pk of this view count entry
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the view count of this view count entry.
	 *
	 * @return the view count of this view count entry
	 */
	public long getViewCount();

	/**
	 * Sets the view count of this view count entry.
	 *
	 * @param viewCount the view count of this view count entry
	 */
	public void setViewCount(long viewCount);

	@Override
	public ViewCountEntry cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}