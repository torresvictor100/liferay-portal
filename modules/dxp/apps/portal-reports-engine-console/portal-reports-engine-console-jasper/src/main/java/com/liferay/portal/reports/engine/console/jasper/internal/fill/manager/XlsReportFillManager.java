/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.reports.engine.console.jasper.internal.fill.manager;

import com.liferay.portal.reports.engine.ReportRequest;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.XlsDataSource;

import org.osgi.service.component.annotations.Component;

/**
 * @author Gavin Wan
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "reportDataSourceType=xls", service = ReportFillManager.class
)
public class XlsReportFillManager extends BaseReportFillManager {

	@Override
	protected JRDataSource getJRDataSource(ReportRequest reportRequest)
		throws Exception {

		XlsDataSource xlsDataSource = new XlsDataSource(
			getDataSourceByteArrayInputStream(reportRequest));

		String[] dataSourceColumnNames = getDataSourceColumnNames(
			reportRequest);

		if (dataSourceColumnNames != null) {
			xlsDataSource.setColumnNames(dataSourceColumnNames);
		}
		else {
			xlsDataSource.setUseFirstRowAsHeader(true);
		}

		return xlsDataSource;
	}

}