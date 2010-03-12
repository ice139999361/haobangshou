/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.job;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hbs.common.springhelper.BeanLocator;

import com.hbs.customerorder.task.SendReminderTask;



/**
 * @author Administrator
 *
 */
public class CustOrderDetailReminderJob implements StatefulJob {
	private static Logger logger = Logger.getLogger(CustOrderDetailReminderJob.class);
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("��ʼ���������ͻ�������ϸ����δ����/�ֿⷢ��/�������δ��/���ڶ�����ϸ���ַ������Ѵ����̣߳�" + context.getJobDetail().getFullName());
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String reminderType = dataMap.getString("ReminderType");
		logger.info("����Ŀͻ�������ϸ��������Ϊ��" + reminderType);
		SendReminderTask pTask = (SendReminderTask)BeanLocator.getInstance().getBean(reminderType);
		pTask.reminder();
		logger.info("��ɿͻ�������ϸ���Ѷ�ʱ����!");

	}

}
