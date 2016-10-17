package com.egova.client.timertask;

import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.egova.client.dao.ClientManager;

/**
 * 定时从数据库获取处于雨花区专业部门阶段的问题
 * @author yinzhinyy
 * @date 2016年9月27日 下午4:19:45
 */
public class CollectionJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext jobCtx) throws JobExecutionException {
		Map dataMap = jobCtx.getJobDetail().getJobDataMap();
		ApplicationContext ctx = (ApplicationContext)dataMap.get("applicationContext");
		ClientManager clientManager = (ClientManager)ctx.getBean("clientManager");
		clientManager.collect();
	}

}
