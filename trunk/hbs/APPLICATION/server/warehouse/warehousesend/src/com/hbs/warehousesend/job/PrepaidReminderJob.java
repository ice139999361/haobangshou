/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousesend.job;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.warehousesend.task.CustPrepaidReminderTask;

/**
 * @author Administrator
 *
 */
public class PrepaidReminderJob implements StatefulJob {
	private static Logger logger = Logger.getLogger(PrepaidReminderJob.class);
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.debug("开始启动启动客户催款提醒处理线程：" + context.getJobDetail().getFullName());
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String reminderType = dataMap.getString("ReminderType");
		logger.debug("处理的客户催款提醒类型为：" + reminderType);
		CustPrepaidReminderTask pTask = (CustPrepaidReminderTask)BeanLocator.getInstance().getBean(reminderType);
		pTask.processPrePaidReminder();
		logger.debug("完成客户催款提醒定时任务");

	}

}
