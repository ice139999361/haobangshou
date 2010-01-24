/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.utils;


import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.common.dao.baseinfo.OperLogDao;
import com.hbs.domain.common.pojo.baseinfo.OperLog;

/**
 * @author Administrator
 *
 */
public class LogUtils {	
	
	/**
	 * ��¼������־
	 * @param log	
	 */
	public static void operLog(String logDaoName ,OperLog log){		
		OperLogDao logDao = (OperLogDao)BeanLocator.getInstance().getBean(logDaoName);
		logDao.insertOperLog(log);
	}	

}
