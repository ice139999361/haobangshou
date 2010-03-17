/**
 * system ：hbs
 * desc:   待办管理服务类
 * version: 1.0
 
 */
package com.hbs.common.manager.waittask;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.DateUtils;
import com.hbs.domain.waittask.dao.WaitTaskInfoDao;
import com.hbs.domain.waittask.pojo.WaitTaskConfigInfo;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;

/**
 * @author yangzj
 * 
 */
public class WaitTaskMgr {

	private static final Logger logger = Logger.getLogger(WaitTaskMgr.class);

	private static final String WAITTASKINFODAO = "waitTaskInfoDao";
	
	private static final String PREFIX="\\";
//	
//	private static final String RET_BIZKEY ="$businessKey";

	/**
	 * 创建待办
	 * 
	 * @param taskCfgID
	 *            对应的配置表的待办ID
	 * @param waitTaskInfo
	 *            待办具体信息，包括String binessKey,String roleID, String staffID,String
	 *            param这些信息
	 * @return 成功 失败
	 */
	public static boolean createWaitTask(String taskCfgID,
			WaitTaskInfo waitTaskInfo) {
		boolean ret = true;
		try {
			WaitTaskConfigInfo waitTaskConfigInfo = WaitTaskConfigInit
					.getWaitTaskConfigInfo(taskCfgID);
			if (null != waitTaskConfigInfo) {
				String commDesc = waitTaskConfigInfo.getComments();
				Map<String, String> hmParam = waitTaskInfo.getHmParam();
				if(null != hmParam){
					Iterator<Map.Entry<String,String>> iter = hmParam.entrySet().iterator(); 
					while (iter.hasNext()) { 
						Map.Entry<String,String> entry = iter.next(); 
					    String key = entry.getKey(); 
					    String val = entry.getValue(); 
					    commDesc = commDesc.replaceFirst(PREFIX + key, val);
					}
				}
//				commDesc.replaceAll(RET_STAFFNAME,StringUtils.isEmpty(waitTaskInfo.getStaffName()) ? "" : waitTaskInfo.getStaffName());
//				commDesc.replaceAll(RET_BIZKEY, StringUtils.isEmpty(waitTaskInfo.getBusinessKey()) ? "" : waitTaskInfo.getBusinessKey());
				waitTaskConfigInfo.setComments(commDesc);
				BeanUtils.copyProperties(waitTaskInfo, waitTaskConfigInfo);
				waitTaskInfo.setCreateTime(new Date());
				WaitTaskInfoDao waitTaskInfoDao = (WaitTaskInfoDao) BeanLocator
						.getInstance().getBean(WAITTASKINFODAO);
				waitTaskInfoDao.insertWaitTaskInfo(waitTaskInfo);
			} else {
				logger.error("无法获取待办的配置项目，请查看数据库配置! taskCfgID=" + taskCfgID);
				ret = false;
			}
		} catch (Exception e) {
			logger.error("创建待办失败! ", e);
			ret = false;
		}
		return ret;
	}

	/**
	 * 取消待办
	 * 
	 * @param binessKey
	 *            取消待办的业务关键字
	 * @return 成功 失败
	 */
	public static boolean deleteWaitTask(String binessKey) {
		boolean ret = true;
		try {
			WaitTaskInfoDao waitTaskInfoDao = (WaitTaskInfoDao) BeanLocator
					.getInstance().getBean(WAITTASKINFODAO);
			waitTaskInfoDao.deleteWaitTaskInfo(binessKey);
		} catch (Exception e) {
			logger.error("取消待办失败! ", e);
			ret = false;
		}
		return ret;
	}
	public static void deleteWaitTaskByExpireTime(){
		String curDate = DateUtils.getCurFormatDate("yyyy-MM-dd");
		try {
			WaitTaskInfoDao waitTaskInfoDao = (WaitTaskInfoDao) BeanLocator
					.getInstance().getBean(WAITTASKINFODAO);
			waitTaskInfoDao.deleteWaitTaskInfoByExpireTime(curDate);
		} catch (Exception e) {
			logger.error("取消提醒待办失败! ", e);			
		}
	}
	/**
	 * 查询所属条件的待办
	 * 
	 * @param roleID
	 *            角色ID，允许为NULL
	 * @param staffID
	 *            员工ID，允许为NULL
	 * @param taskType
	 *            待办类型，允许为NULL
	 * @param binessKey
	 *            业务关键字，允许为NULL
	 * @return List<WaitTaskInfo>
	 */
	@SuppressWarnings("unchecked")
	public static List<WaitTaskInfo> listWaitTask(String roleID,
			String staffID, String taskType, String binessKey) {
		List<WaitTaskInfo> list = null;
		try {
			WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
			waitTaskInfo.setRoleId(roleID);
			waitTaskInfo.setStaffId(staffID);
			waitTaskInfo.setTaskType(taskType);
			waitTaskInfo.setBusinessKey(binessKey);
			WaitTaskInfoDao waitTaskInfoDao = (WaitTaskInfoDao) BeanLocator
					.getInstance().getBean(WAITTASKINFODAO);
			list = waitTaskInfoDao.listWaitTaskInfo(waitTaskInfo);
		} catch (Exception e) {
			logger.error("查询待办失败! ", e);
		}
		return list;
	}
}
