/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.job;

import org.apache.log4j.Logger;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hbs.common.manager.waittask.WaitTaskMgr;


/**
 * @author Administrator
 *
 */
public class WaitTaskExpireJob implements StatefulJob {
	private static Logger logger = Logger.getLogger(WaitTaskExpireJob.class);
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.debug("开始启动启清除过期的提醒类待办处理线程！");
		WaitTaskMgr.deleteWaitTaskByExpireTime();
		
		logger.debug("完成清除过期的提醒类待办定时任务");

	}

}
