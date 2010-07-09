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
			logger.debug("开始备份数据库！");
			logger.debug("开始备份数据库---数据结构！");
			MySqlBackup.backupstruct();
			logger.debug("开始备份数据库---数据！");
			MySqlBackup.backupdata();
			
			
			
			logger.debug("备份数据库完毕！");

		}

	

}
