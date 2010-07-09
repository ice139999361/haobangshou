package com.hbs.common.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hbs.common.db.MySqlBackup;


public class DataBaseBackUp implements StatefulJob {
	
		private static Logger logger = Logger.getLogger(DataBaseBackUp.class);
		/* (non-Javadoc)
		 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
		 */
		public void execute(JobExecutionContext arg0) throws JobExecutionException {
			logger.debug("��ʼ�������ݿ⣡");
			logger.debug("��ʼ�������ݿ�---���ݽṹ��");
			MySqlBackup.backupstruct();
			logger.debug("��ʼ�������ݿ�---���ݣ�");
			MySqlBackup.backupdata();
			
			
			
			logger.debug("�������ݿ���ϣ�");

		}

	

}
