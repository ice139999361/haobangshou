/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.utils;


import java.util.List;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.common.dao.baseinfo.OperLogDao;
import com.hbs.domain.common.pojo.baseinfo.OperLog;

/**
 * @author Administrator
 *
 */
public class LogUtils {	
	
	/**
	 * 记录操作日志
	 * @param log	
	 */
	public static void operLog(String logDaoName ,OperLog log){		
		OperLogDao logDao = (OperLogDao)BeanLocator.getInstance().getBean(logDaoName);
		logDao.insertOperLog(log);
	}	
	/**
	 * 获取业务关键字的所有日志
	 * @param logDaoName
	 * @param logBizKey
	 * @return
	 */
	public static List<OperLog> getlogList(String logDaoName ,String logBizKey){
		OperLogDao logDao = (OperLogDao)BeanLocator.getInstance().getBean(logDaoName);
		return logDao.listOperLog(logBizKey);
	}

}
