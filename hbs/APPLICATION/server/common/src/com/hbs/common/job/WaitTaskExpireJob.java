/**
 * system ��hbs
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
		logger.debug("��ʼ������������ڵ���������촦���̣߳�");
		WaitTaskMgr.deleteWaitTaskByExpireTime();
		
		logger.debug("���������ڵ���������춨ʱ����");

	}

}
