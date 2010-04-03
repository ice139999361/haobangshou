/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendor.common.utils;

import java.util.Date;
import java.util.List;

import com.hbs.common.utils.LogUtils;
import com.hbs.domain.common.pojo.baseinfo.OperLog;

/**
 * @author Administrator
 *
 */
public class VendorLogUtils {
	private static final String VENDOR_OPERLOG_DAO ="vendorOperLogDao";
	/**
	 * 记录操作日志
	 * @param log	
	 */
	public static void operLog(OperLog log){
		LogUtils.operLog(VENDOR_OPERLOG_DAO, log);
	}
	
	/**
	 * 记录操作日志
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
		LogUtils.operLog(VENDOR_OPERLOG_DAO, log);
	}
	
	/**
	 * 获取业务关键字的日志
	 * @param logBizKey
	 * @return
	 */
	public static List<OperLog> getLogList(OperLog logBizKey){
		return LogUtils.getlogList(VENDOR_OPERLOG_DAO, logBizKey);
	}
}
