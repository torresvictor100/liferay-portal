package com.liferay.message.boards.internal.dispatch.executor;

import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;

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

	}

	@Override
	public String getName() {
		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MBThreadRemoveDispatchTaskExecutor.class);
}
