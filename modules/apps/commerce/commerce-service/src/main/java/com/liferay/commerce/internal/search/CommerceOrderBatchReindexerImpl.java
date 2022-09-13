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
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.indexer.IndexerDocumentBuilder;
import com.liferay.portal.search.indexer.IndexerWriter;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Danny Situ
 */
@Component(
	enabled = false, immediate = true,
	service = CommerceOrderBatchReindexer.class
)
public class CommerceOrderBatchReindexerImpl
	implements CommerceOrderBatchReindexer {

	@Override
	public void reindex(long commerceAccountId, long companyId) {
		BatchIndexingActionable batchIndexingActionable =
			indexerWriter.getBatchIndexingActionable();

		batchIndexingActionable.setAddCriteriaMethod(
			dynamicQuery -> {
				Property commerceAccountIdProperty =
					PropertyFactoryUtil.forName("commerceAccountId");

				dynamicQuery.add(
					commerceAccountIdProperty.eq(commerceAccountId));
			});
		batchIndexingActionable.setCompanyId(companyId);
		batchIndexingActionable.setPerformActionMethod(
			(CommerceOrder commerceOrder) ->
				batchIndexingActionable.addDocuments(
					indexerDocumentBuilder.getDocument(commerceOrder)));

		batchIndexingActionable.performActions();
	}

	@Reference
	protected CommerceOrderLocalService commerceOrderLocalService;

	@Reference(
		target = "(indexer.class.name=com.liferay.commerce.model.CommerceOrder)"
	)
	protected IndexerDocumentBuilder indexerDocumentBuilder;

	@Reference(
		target = "(indexer.class.name=com.liferay.commerce.model.CommerceOrder)"
	)
	protected IndexerWriter<CommerceOrder> indexerWriter;

}