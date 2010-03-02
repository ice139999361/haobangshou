/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehouse.common.job;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.task.PeriodReminderTask;

/**
 * @author Administrator
 *
 */
public class PeriodReminderJob implements StatefulJob {
	private static Logger logger = Logger.getLogger(PeriodReminderJob.class);
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.debug("��ʼ���������ͻ�����Ӧ�� ������/���������Ѵ����̣߳�" + context.getJobDetail().getFullName());
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String reminderType = dataMap.getString("ReminderType");
		logger.debug("����Ķ�����/��������������Ϊ��" + reminderType);
		PeriodReminderTask pTask = (PeriodReminderTask)BeanLocator.getInstance().getBean(reminderType);
		pTask.processReminder();
		logger.debug("��ɿͻ�����Ӧ�� ������/���������Ѷ�ʱ����");

	}

}
