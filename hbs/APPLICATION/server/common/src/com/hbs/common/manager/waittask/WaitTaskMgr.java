/**
 * system ��hbs
 * desc:   ������������
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
	 * ��������
	 * 
	 * @param taskCfgID
	 *            ��Ӧ�����ñ�Ĵ���ID
	 * @param waitTaskInfo
	 *            ���������Ϣ������String binessKey,String roleID, String staffID,String
	 *            param��Щ��Ϣ
	 * @return �ɹ� ʧ��
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
				logger.error("�޷���ȡ�����������Ŀ����鿴���ݿ�����! taskCfgID=" + taskCfgID);
				ret = false;
			}
		} catch (Exception e) {
			logger.error("��������ʧ��! ", e);
			ret = false;
		}
		return ret;
	}

	/**
	 * ȡ������
	 * 
	 * @param binessKey
	 *            ȡ�������ҵ��ؼ���
	 * @return �ɹ� ʧ��
	 */
	public static boolean deleteWaitTask(String binessKey) {
		boolean ret = true;
		try {
			WaitTaskInfoDao waitTaskInfoDao = (WaitTaskInfoDao) BeanLocator
					.getInstance().getBean(WAITTASKINFODAO);
			waitTaskInfoDao.deleteWaitTaskInfo(binessKey);
		} catch (Exception e) {
			logger.error("ȡ������ʧ��! ", e);
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
			logger.error("ȡ�����Ѵ���ʧ��! ", e);			
		}
	}
	/**
	 * ��ѯ���������Ĵ���
	 * 
	 * @param roleID
	 *            ��ɫID������ΪNULL
	 * @param staffID
	 *            Ա��ID������ΪNULL
	 * @param taskType
	 *            �������ͣ�����ΪNULL
	 * @param binessKey
	 *            ҵ��ؼ��֣�����ΪNULL
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
			logger.error("��ѯ����ʧ��! ", e);
		}
		return list;
	}
}
