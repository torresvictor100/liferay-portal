package com.liferay.message.boards.internal.dispatch.executor;

import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.message.boards.moderation.configuration.MBTherdDeleteForMonthConfiguration;
import com.liferay.message.boards.service.MBThreadService;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapDictionary;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.List;

@Component(
	immediate = true,
	property = {
		"dispatch.task.executor.name=" + MBThreadRemoveDispatchTaskExecutor.MBTHEAD ,
		"dispatch.task.executor.type=" + MBThreadRemoveDispatchTaskExecutor.MBTHEAD
	},
	service = DispatchTaskExecutor.class
)
public class MBThreadRemoveDispatchTaskExecutor extends
	BaseDispatchTaskExecutor {

	public static final String MBTHEAD = "Remove Thread For Time";


	@Override
	public void doExecute(
		DispatchTrigger dispatchTrigger,
		DispatchTaskExecutorOutput dispatchTaskExecutorOutput)
		throws Exception {


		getMBTherdDeleteForMonthConfiguration(dispatchTrigger);



	}


	private void getMBTherdDeleteForMonthConfiguration(DispatchTrigger dispatchTrigger) {


		try {

			long companyId = dispatchTrigger.getCompanyId();

			List<Long> groups =
				_groupLocalService.getGroupIds(companyId, true);

			for (Long idgrups : groups) {

				MBTherdDeleteForMonthConfiguration
					mBTherdDeleteForMonthConfiguration =
					ConfigurationProviderUtil.getGroupConfiguration(
						MBTherdDeleteForMonthConfiguration.class, idgrups);

				int messageBoardsDeletaPeriodoSemResposta =
					mBTherdDeleteForMonthConfiguration.messageBoardsDeletaPeriodoSemResposta();

				boolean enableMessageBoardsDeletaAutomatico =
					mBTherdDeleteForMonthConfiguration.enableMessageBoardsDeletaAutomatico();

				boolean enableMessageBoardsDeletaOnlyNoAnswer =
					mBTherdDeleteForMonthConfiguration.enableMessageBoardsDeletaOnlyNoAnswer();

				if (enableMessageBoardsDeletaAutomatico == true && enableMessageBoardsDeletaOnlyNoAnswer == false) {

					_mbThreadService.deleteForMonth(messageBoardsDeletaPeriodoSemResposta, idgrups);

				}else if (enableMessageBoardsDeletaAutomatico == true && enableMessageBoardsDeletaOnlyNoAnswer == true) {

					_mbThreadService.deleteForMonthThreadsNoAnswer(messageBoardsDeletaPeriodoSemResposta, idgrups);

				}

			}

		}
		catch (PortalException configurationException) {
			 ReflectionUtil.throwException(configurationException);
		}


	}

	@Override
	public String getName() {
		return "MBThreadRemoveDispatchTaskExecutor";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MBThreadRemoveDispatchTaskExecutor.class);
	@Reference
	CompanyLocalService  _companyLocalService;
	@Reference
	GroupLocalService _groupLocalService;

	@Reference
	MBThreadService _mbThreadService;
}
