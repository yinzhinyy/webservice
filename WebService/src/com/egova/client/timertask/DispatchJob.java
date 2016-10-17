package com.egova.client.timertask;

import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.egova.client.dao.ClientService;

/**
 * 定时调用派遣接口
 * @author yinzhinyy
 * @date 2016年9月22日 上午10:07:26
 */
public class DispatchJob extends QuartzJobBean {
	
	@Override
	protected void executeInternal(JobExecutionContext jobCtx) throws JobExecutionException {
		Map dataMap = jobCtx.getJobDetail().getJobDataMap();
		ApplicationContext ctx = (ApplicationContext)dataMap.get("applicationContext");
		ClientService clientService = (ClientService)ctx.getBean("clientService");
		clientService.process();
	}

}
