package com.liferay.message.boards.internal.dispatch.executor;

import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.message.boards.moderation.configuration.MBModerationGroupConfiguration;
import com.liferay.message.boards.moderation.configuration.MBTherdDeleteForMonthConfiguration;
import com.liferay.message.boards.service.MBThreadLocalService;
import com.liferay.message.boards.service.MBThreadService;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

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

		int month;
		long groupId = 0;
		long categoryId;

//		month = Integer.parseInt(unicodeProperties.getProperty("month"));
//		groupId = Long.parseLong(unicodeProperties.getProperty("groupId"));
//		categoryId = Long.parseLong(unicodeProperties.getProperty("categoryId"));

		MBTherdDeleteForMonthConfiguration MBTherdDeleteForMonthConfiguration =
			ConfigurableUtil.createConfigurable(
				MBTherdDeleteForMonthConfiguration.class,
				new HashMapDictionary<>());



		System.out.println(getMBTherdDeleteForMonthConfiguration(dispatchTrigger));

//		_mbThreadService.deleteForMonth(month, groupId, categoryId);

	}


	private Integer getMBTherdDeleteForMonthConfiguration(DispatchTrigger dispatchTrigger){


		try {

			long companyId =  dispatchTrigger.getCompanyId();



			MBTherdDeleteForMonthConfiguration
				mBTherdDeleteForMonthConfiguration  =
				ConfigurationProviderUtil.getCompanyConfiguration(
					MBTherdDeleteForMonthConfiguration.class,companyId );

			String[] ids = mBTherdDeleteForMonthConfiguration.grupsIds();


			for(String id :ids ){
				long idgrup = Long.parseLong(id);
				System.out.println(idgrup);
			}

			int messageBoardsDeletaPeriodoSemResposta = mBTherdDeleteForMonthConfiguration.messageBoardsDeletaPeriodoSemResposta();

			boolean enableMessageBoardsDeletaAutomatico = mBTherdDeleteForMonthConfiguration.enableMessageBoardsDeletaAutomatico();

			if (enableMessageBoardsDeletaAutomatico == true) {
				return messageBoardsDeletaPeriodoSemResposta;
			}
			return null;
			
		}
		catch (ConfigurationException configurationException) {
			return ReflectionUtil.throwException(configurationException);
		}

	}

	@Override
	public String getName() {
		return "MBThreadRemoveDispatchTaskExecutor";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MBThreadRemoveDispatchTaskExecutor.class);


	@Reference
	MBThreadService _mbThreadService;
}
