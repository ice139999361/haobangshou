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
		logger.info("��ʼ���������ͻ�������ϸ����δ����/�ֿⷢ��/�������δ��/���ڶ�����ϸ���ַ������Ѵ����̣߳�" + context.getJobDetail().getFullName());
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String reminderType = dataMap.getString("ReminderType");
		logger.info("����Ŀͻ�������ϸ��������Ϊ��" + reminderType);
		SendReminderTask pTask = (SendReminderTask)BeanLocator.getInstance().getBean(reminderType);
		pTask.reminder();
		logger.info("��ɿͻ�������ϸ���Ѷ�ʱ����!");

	}

}
