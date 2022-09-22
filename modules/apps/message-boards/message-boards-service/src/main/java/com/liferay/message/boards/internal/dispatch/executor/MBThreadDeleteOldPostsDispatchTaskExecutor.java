package com.liferay.message.boards.internal.dispatch.executor;

import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.message.boards.moderation.configuration.MBThreadAutomaticDeletionConfiguration;
import com.liferay.message.boards.service.MBThreadService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.service.CompanyService;
import com.liferay.portal.kernel.service.GroupService;
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

		getMBTherdDeleteForMonthConfiguration(dispatchTrigger);

	}

	private void getMBTherdDeleteForMonthConfiguration(DispatchTrigger dispatchTrigger)
		throws PortalException {

			Company company =
				_companyService.getCompanyById(dispatchTrigger.getCompanyId());

			MBThreadAutomaticDeletionConfiguration
				mbThreadAutomaticDeletionConfiguration =
				ConfigurationProviderUtil.getCompanyConfiguration(
					MBThreadAutomaticDeletionConfiguration.class,
					dispatchTrigger.getCompanyId());


			int month =
				mbThreadAutomaticDeletionConfiguration.monthStartToTheDelete();
			boolean enableDeleteAllOldPost =
				mbThreadAutomaticDeletionConfiguration.enableDeleteAllOldPost();
			boolean enableDeleteOldMessageNoAnswer =
				mbThreadAutomaticDeletionConfiguration.enableDeleteOldMessageNoAnswer();
			String confimationStartToTheDelete =
				mbThreadAutomaticDeletionConfiguration.confimationStartToTheDelete();


			if (enableDeleteAllOldPost == true &&
				enableDeleteOldMessageNoAnswer == false &&
				confimationStartToTheDelete.equals("accepted")) {

				_mbThreadService.deleteByThreadForDate(month,
					company.getGroupId(), dispatchTrigger.getCompanyId());

			}
			else if (enableDeleteAllOldPost == true &&
					 enableDeleteOldMessageNoAnswer == true &&
					 confimationStartToTheDelete.equals("accepted")) {

				_mbThreadService.deleteByThreadForDateNoAnswer(month,
					company.getGroupId(), dispatchTrigger.getCompanyId());

			}
	}

	@Override
	public String getName() {
		return "MBThreadDeleteOldPostsDispatchTaskExecutor";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MBThreadDeleteOldPostsDispatchTaskExecutor.class);

	@Reference
	CompanyService _companyService;

	@Reference
	MBThreadService _mbThreadService;
}
