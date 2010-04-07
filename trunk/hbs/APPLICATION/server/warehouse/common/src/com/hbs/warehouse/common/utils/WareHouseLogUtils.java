/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehouse.common.utils;

import java.util.Date;

import com.hbs.common.utils.LogUtils;
import com.hbs.domain.common.pojo.baseinfo.OperLog;

/**
 * @author Administrator
 *
 */
public class WareHouseLogUtils {
	private static final String WAREHOUSE_OPERLOG_DAO ="warehouseOperLogDao";
	/**
	 * 记录操作日志
	 * @param log	
	 */
	public static void operLog(OperLog log)throws Exception{
		LogUtils.operLog(WAREHOUSE_OPERLOG_DAO, log);
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
	public static void operLog(String staffId,String staffName,String operType,String OperObject,String operKey,String operContent,String memo)throws Exception{
		OperLog log = new OperLog();
		log.setStaffId(staffId);
		log.setStaffName(staffName);
		log.setOperTime(new Date());
		log.setOperObject(OperObject);
		log.setOperKey(operKey);
		log.setOperType(operType);
		log.setMemo(memo);
		log.setOperContent(operContent);		
		LogUtils.operLog(WAREHOUSE_OPERLOG_DAO, log);
	}
}
