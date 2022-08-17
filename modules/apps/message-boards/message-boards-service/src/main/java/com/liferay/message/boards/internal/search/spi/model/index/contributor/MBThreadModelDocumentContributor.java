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

package com.liferay.message.boards.internal.search.spi.model.index.contributor;

import com.liferay.message.boards.model.MBDiscussion;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.model.MBThread;
import com.liferay.message.boards.service.MBDiscussionLocalService;
import com.liferay.message.boards.service.MBThreadLocalService;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.liferay.ratings.kernel.model.RatingsStats;
import com.liferay.ratings.kernel.service.RatingsStatsLocalService;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luan Maoski
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.message.boards.model.MBThread",
	service = ModelDocumentContributor.class
)
public class MBThreadModelDocumentContributor
	implements ModelDocumentContributor<MBThread> {

	@Override
	public void contribute(Document document, MBThread mbThread) {
		MBDiscussion discussion =
			mbDiscussionLocalService.fetchThreadDiscussion(
				mbThread.getThreadId());

		if (discussion == null) {
			document.addKeyword("discussion", false);
		}
		else {
			document.addKeyword("discussion", true);
		}

		Date lastPostDate = mbThread.getLastPostDate();

		document.addKeyword("lastPostDate", lastPostDate.getTime());

		document.addKeyword(
			"hasValidAnswer",
			mbThreadLocalService.hasAnswerMessage(mbThread.getThreadId()));

		document.addKeyword(
			"numberOfMessageBoardMessages",
			mbThreadLocalService.getMessageCount(mbThread.getThreadId(), 0));

		document.addKeyword(
			"participantUserIds", mbThread.getParticipantUserIds());

		document.addKeyword("viewCount", mbThread.getViewCount());

		RatingsStats ratingsStats = ratingsStatsLocalService.fetchStats(
			MBMessage.class.getName(), mbThread.getRootMessageId());

		document.addKeyword("totalScore", ratingsStats.getTotalScore());

	}

	@Reference
	protected MBDiscussionLocalService mbDiscussionLocalService;

	@Reference
	protected MBThreadLocalService mbThreadLocalService;

	@Reference
	protected RatingsStatsLocalService ratingsStatsLocalService;

}