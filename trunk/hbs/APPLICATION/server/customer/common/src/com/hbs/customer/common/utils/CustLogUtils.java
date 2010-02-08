/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customer.common.utils;

import java.util.Date;
import java.util.List;


import com.hbs.common.utils.LogUtils;
import com.hbs.domain.common.pojo.baseinfo.OperLog;

/**
 * @author Administrator
 *
 */
public class CustLogUtils {
	
	private static final String CUST_OPERLOG_DAO ="customerOperLogDao";
	/**
	 * ��¼������־
	 * @param log	
	 */
	public static void operLog(OperLog log){
		LogUtils.operLog(CUST_OPERLOG_DAO, log);
	}
	
	/**
	 * ��¼������־
	 * @param staffId
	 * @param staffName
	 * @param operType
	 * @param OperObject
	 * @param operKey
	 * @param operContent
	 * @param memo
	 */
	public static void operLog(String staffId,String staffName,String operType,String OperObject,String operKey,String operContent,String memo){
		OperLog log = new OperLog();
		log.setStaffId(staffId);
		log.setStaffName(staffName);
		log.setOperTime(new Date());
		log.setOperObject(OperObject);
		log.setOperKey(operKey);
		log.setOperType(operType);
		log.setMemo(memo);
		log.setOperContent(operContent);		
		LogUtils.operLog(CUST_OPERLOG_DAO, log);
	}
	
	/**
	 * ��ȡҵ��ؼ��ֵ���־
	 * @param logBizKey
	 * @return
	 */
	public static List<OperLog> getLogList(String logBizKey){
		return LogUtils.getlogList(CUST_OPERLOG_DAO, logBizKey);
	}
}
