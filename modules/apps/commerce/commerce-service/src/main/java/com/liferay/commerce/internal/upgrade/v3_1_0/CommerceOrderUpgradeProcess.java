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

package com.liferay.commerce.internal.upgrade.v3_1_0;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.model.impl.CommerceOrderModelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeProcessFactory;
import com.liferay.portal.kernel.upgrade.UpgradeStep;

/**
 * @author Marco Leo
 */
public class CommerceOrderUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasColumn(
				CommerceOrderModelImpl.TABLE_NAME, "lastPriceUpdateDate")) {

			String sql =
				"UPDATE %s SET lastPriceUpdateDate = createDate WHERE " +
					"orderStatus = %s";

			runSQL(
				String.format(
					sql, CommerceOrderModelImpl.TABLE_NAME,
					CommerceOrderConstants.ORDER_STATUS_OPEN));
		}
	}

	@Override
	protected UpgradeStep[] getPreUpgradeSteps() {
		return new UpgradeStep[] {
			UpgradeProcessFactory.addColumns(
				"CommerceOrder", "couponCode VARCHAR(75)",
				"lastPriceUpdateDate DATE")
		};
	}

}