package com.liferay.message.boards.internal.dispatch.executor;

import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import org.osgi.service.component.annotations.Component;

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

	}

	@Override
	public String getName() {
		return null;
	}
}
