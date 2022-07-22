package com.liferay.message.boards.internal.dispatch.executor;

import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"dispatch.task.executor.name=" + MBThreadDispatchTaskExecutor.MBTHEAD ,
		"dispatch.task.executor.type=" + MBThreadDispatchTaskExecutor.MBTHEAD
	},
	service = DispatchTaskExecutor.class
)
public class MBThreadDispatchTaskExecutor extends BaseDispatchTaskExecutor {

	public static final String MBTHEAD = "MBThreadDispatch";
	@Override
	public void doExecute(
		DispatchTrigger dispatchTrigger,
		DispatchTaskExecutorOutput dispatchTaskExecutorOutput)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info(
				"Invoking #doExecute(DispatchTrigger, " +
				"DispatchTaskExecutorOutput)");
		}


		System.out.println("novonovo");


	}


	@Override
	public String getName() {
		return "MBThread";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MBThreadDispatchTaskExecutor.class);
}
