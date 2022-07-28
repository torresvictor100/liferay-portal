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

//		UnicodeProperties unicodeProperties =
//			dispatchTrigger.getDispatchTaskSettingsUnicodeProperties();

		int month;
		long groupId;
		long categoryId;

//		month = Integer.parseInt(unicodeProperties.getProperty("month"));
//		groupId = Long.parseLong(unicodeProperties.getProperty("groupId"));
//		categoryId = Long.parseLong(unicodeProperties.getProperty("categoryId"));


		System.out.println(getMBTherdDeleteForMonthConfiguration());

//		_mbThreadService.deleteForMonth(month, groupId, categoryId);

	}


	private Integer getMBTherdDeleteForMonthConfiguration(){


		try {
			MBTherdDeleteForMonthConfiguration
				mBTherdDeleteForMonthConfiguration  =
				ConfigurationProviderUtil.getSystemConfiguration(
					MBTherdDeleteForMonthConfiguration.class);

			int messageBoardsDeletaPeriodoSemResposta = mBTherdDeleteForMonthConfiguration.messageBoardsDeletaPeriodoSemResposta();

			boolean enableMessageBoardsDeletaAutomatico = mBTherdDeleteForMonthConfiguration.enableMessageBoardsDeletaAutomatico();

			if (enableMessageBoardsDeletaAutomatico == true) {
				return messageBoardsDeletaPeriodoSemResposta;
			}
			return null;


//			MBTherdDeleteForMonthConfiguration mbModerationGroupConfiguration =
//				ConfigurableUtil.createConfigurable(
//					MBTherdDeleteForMonthConfiguration.class,
//					new HashMapDictionary<>());
//		int messageBoardsDeletaPeriodoSemResposta = mbModerationGroupConfiguration.messageBoardsDeletaPeriodoSemResposta();
//		boolean enableMessageBoardsDeletaAutomatico = mbModerationGroupConfiguration.enableMessageBoardsDeletaAutomatico();
//
//				return messageBoardsDeletaPeriodoSemResposta;
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
