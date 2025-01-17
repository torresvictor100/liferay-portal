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

import {CommerceFrontendUtils} from 'commerce-frontend-js';

import {updateProductFields} from './util/index';

const {Events, FormUtils} = CommerceFrontendUtils;
const {DDMFormHandler} = FormUtils;

export default function ({
	accountId,
	channelId,
	cpDefinitionId,
	namespace,
	productId,
	quantity,
}) {
	Liferay.componentReady('ProductOptions' + cpDefinitionId).then(
		(DDMFormInstance) => {
			if (DDMFormInstance) {
				new DDMFormHandler({
					DDMFormInstance,
					accountId,
					channelId,
					namespace,
					productId,
					quantity,
				});

				Liferay.on(
					`${namespace}${Events.CP_INSTANCE_CHANGED}`,
					updateProductFields
				);
			}
		}
	);
}
