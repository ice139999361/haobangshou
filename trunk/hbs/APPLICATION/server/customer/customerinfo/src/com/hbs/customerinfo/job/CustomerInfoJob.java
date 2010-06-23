package com.hbs.customerinfo.job;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerorder.job.CustOrderDetailReminderJob;
import com.hbs.customerorder.task.SendReminderTask;

public class CustomerInfoJob implements StatefulJob {
	private static Logger logger = Logger.getLogger(CustomerInfoJob.class);
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("开始启动启动客户订单明细，货未备齐/仓库发货/款到发货款未到/账期订单明细部分发货提醒处理线程：" + context.getJobDetail().getFullName());
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String reminderType = dataMap.getString("ReminderType");
		logger.info("处理的客户订单明细提醒类型为：" + reminderType);
		SendReminderTask pTask = (SendReminderTask)BeanLocator.getInstance().getBean(reminderType);
		pTask.reminder();
		logger.info("完成客户订单明细提醒定时任务!");

	}

}
