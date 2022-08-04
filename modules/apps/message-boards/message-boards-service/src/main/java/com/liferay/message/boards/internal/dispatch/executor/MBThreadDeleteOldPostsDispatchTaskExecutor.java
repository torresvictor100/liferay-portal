package com.liferay.message.boards.internal.dispatch.executor;

import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.message.boards.moderation.configuration.MBThreadAutomaticDeletionConfiguration;
import com.liferay.message.boards.service.MBThreadService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyService;
import com.liferay.portal.kernel.service.GroupLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(
	immediate = true,
	property = {
		"dispatch.task.executor.name=" + MBThreadDeleteOldPostsDispatchTaskExecutor.MBTHEAD ,
		"dispatch.task.executor.type=" + MBThreadDeleteOldPostsDispatchTaskExecutor.MBTHEAD
	},
	service = DispatchTaskExecutor.class
)
public class MBThreadDeleteOldPostsDispatchTaskExecutor extends
	BaseDispatchTaskExecutor {

	public static final String MBTHEAD = "Delete Old Posts in MBThread";

	@Override
	public void doExecute(
		DispatchTrigger dispatchTrigger,
		DispatchTaskExecutorOutput dispatchTaskExecutorOutput)
		throws Exception {

		int month = _mbThreadAutomaticDeletionConfiguration.monthStartToTheDelete();
		boolean  enableDeleteAllOldPost = _mbThreadAutomaticDeletionConfiguration.enableDeleteAllOldPost();
		boolean enableDeleteOldMessageNoAnswer = _mbThreadAutomaticDeletionConfiguration.enableDeleteOldMessageNoAnswer();
		String confimationStartToTheDelete = _mbThreadAutomaticDeletionConfiguration.confimationStartToTheDelete();

		Group group = _groupLocalService.getCompanyGroup(dispatchTrigger.getCompanyId());

		if(enableDeleteAllOldPost == true && enableDeleteOldMessageNoAnswer == false &&  confimationStartToTheDelete.equals("accepted")){

			_mbThreadService.deleteByThreadForDate(month,group.getGroupId(),dispatchTrigger.getCompanyId());

		}else if(enableDeleteAllOldPost == true && enableDeleteOldMessageNoAnswer == true &&  confimationStartToTheDelete.equals("accepted")) {

			_mbThreadService.deleteByThreadForDateNoAnswer(month,group.getGroupId(),dispatchTrigger.getCompanyId());

		}

	}

	@Override
	public String getName() {
		return null;
	}

	@Reference
	MBThreadAutomaticDeletionConfiguration _mbThreadAutomaticDeletionConfiguration;

	@Reference
	GroupLocalService _groupLocalService;


	@Reference
	MBThreadService _mbThreadService;
}
