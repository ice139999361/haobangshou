/**
 * system ：hbs
 * desc:    联系人信息管理类
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.manager.baseinfo;


import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customer.common.constants.StateConstants;
import com.hbs.domain.common.dao.baseinfo.ContactInfoDao;

import com.hbs.domain.common.pojo.baseinfo.ContactInfo;

/**
 * 本类及实现的子类供客户基本信息和供应商基本信息调用
 * action一层不需要调用，action面对的只是客户基本信息
 * 服务类和供应商基本信息服务类
 */
public abstract class ContactMgr {
	
	
	/**
	 * 抽象方法，子类实现
	 * @return
	 */
	public abstract String getContactInfoDao();
	
	/**
	 *  抽象方法，子类实现
	 * @return
	 */
	public abstract String getLogDao();
	
	/**
	 * 抽象方法，获取子类的日志输出
	 * @return
	 */
	public abstract Logger getLogger();
	/**
	 * 插入联系人信息,银行信息的插入，如果是单独插入，则状态为待审批，直接提交审批，
	 * 如果是跟客户信息同时提交，则跟随客户信息的状态	 
	 * @param bankInfo
	 * @param state
	 * @param staffId
	 * @param staffName
	 * @throws Exception
	 */
	public int insertContactInfo(ContactInfo contactInfo) throws Exception{
		int ret =0;
		getLogger().debug("新增联系人信息，输入的参数为：" + contactInfo.toString());
		ContactInfoDao contactInfoDao =(ContactInfoDao)BeanLocator.getInstance().getBean(getContactInfoDao());
		ContactInfo cInfo = contactInfoDao.findContactInfo(contactInfo);
		if(null == cInfo){
		contactInfoDao.insertContactInfo(contactInfo);
		}else{
			throw new Exception("新增联系人信息,新增的信息已存在，不能新增！");
		}
		
		return ret;
	}
	
	/**
	 * 批量插入
	 * @param contactInfoList
	 * @param state
	 * @param staffId
	 * @param staffName
	 * @return
	 * @throws Exception
	 */
	public int insertContactInfoList(List<ContactInfo> contactInfoList) throws Exception{
		int ret =0;
		getLogger().debug("批量新增联系人信息，批量数量为：" + contactInfoList.size());
		for(ContactInfo contactInfo : contactInfoList){
			insertContactInfo( contactInfo);
		}
		return ret;
	}
	/**
	 * 删除正式信息及临时信息，供基本信息调用
	 * @param contactInfo
	 * @param isDelCurrent  是否同时删除本次操作的数据
	 * @throws Exception
	 */
	public void deleteContactInfo(ContactInfo contactInfo,boolean isDelCurrent)throws Exception{
		getLogger().debug("删除联系人信息,输入的参数为：" + contactInfo.toString());
		ContactInfoDao contactInfoDao =(ContactInfoDao)BeanLocator.getInstance().getBean(getContactInfoDao());
		contactInfoDao.deleteContactInfo(contactInfo);
		if(isDelCurrent){
			contactInfoDao.deleteContactInfoByID(contactInfo.getSeqId());
		}
	}
	
	/**
	 * 更新联系人信息
	 * @param bankInfo
	 * @param state
	 * @param staffId
	 * @param staffName
	 * @throws Exception
	 */
	public int updateContactInfo(ContactInfo contactInfo)throws Exception{
		int ret =0;
		getLogger().debug("更新联系人信息,输入的参数为：" + contactInfo.toString());
		int state = Integer.parseInt(contactInfo.getState());
		getLogger().debug("更新银行信息,状态为：" + state);
		ContactInfoDao contactInfoDao =(ContactInfoDao)BeanLocator.getInstance().getBean(getContactInfoDao());
		//String strLogType = null;
		switch (state){
		case 0:  //审批通过,先删除后插入,同时删除待审批数据
		     //删除操作由基本信息类删除
			//contactInfoDao.deleteContactInfo(contactInfo);
			contactInfoDao.insertContactInfo(contactInfo);			
			//contactInfoDao.deleteContactInfoByID(contactInfo.getSeqId());
			//strLogType = "审批数据";
			break;
		case 1://没有提交的数据修改		
			contactInfoDao.updateContactInfo(contactInfo);
			//strLogType = "修改临时数据";
			break;
		case 2://提交数据修改
			contactInfoDao.updateContactInfo(contactInfo);
			//strLogType = "提交临时数据";
			break;
		case 3://审批不通过数据只修改状态
			contactInfoDao.updateContactInfoByState(contactInfo);
			//strLogType = "审批不通过数据";
			break;
		case 4://废弃数据，把已经废弃的数据清除掉，同时如果把本次废弃的数据状态置为废弃
			//删除废弃数据由基本信息操作
			//contactInfoDao.deleteContactInfo(contactInfo);
			contactInfoDao.updateContactInfoByState(contactInfo);
			//strLogType = "废弃数据";
			break;
		case 5://锁定数据只修改状态
			contactInfoDao.updateContactInfoByState(contactInfo);
			//strLogType = "锁定数据";
			break;
			
		case 6 ://解锁数据，只修改状态
			
			contactInfo.setState(new Integer(StateConstants.STATE_0).toString());
			
			contactInfoDao.updateContactInfoByState(contactInfo);
			//strLogType = "解锁数据";
		default:
			throw new Exception("更新联系人信息 ,无此类型的状态，无法进行更新！");
		}
		
		return ret;
	}
	/**
	 * 批量更新
	 * @param contactInfoList
	 * @param staffId
	 * @param staffName
	 * @param otherInfo
	 * @return
	 * @throws Exception
	 */
	public int updateContactInfoList(List<ContactInfo> contactInfoList, String staffId,String staffName,String otherInfo)throws Exception{
		int ret =0 ;
		getLogger().debug("批量更新联系人信息，批量数量为：" + contactInfoList.size());
		for(ContactInfo contactInfo : contactInfoList){
			updateContactInfo( contactInfo);
		}
		return ret;
	}
	/**
	 * 操作日志
	 * @param staffId
	 * @param staffName
	 * @param logType
	 * @param operKey
	 * @param otherInfo
	 */
//	private void operLog(String staffId,String staffName,String logType,String operKey,String otherInfo){
//		OperLogDao logDao = (OperLogDao)BeanLocator.getInstance().getBean(getLogDao());
//		OperLog log = new OperLog();
//		log.setStaffId(staffId);
//		log.setStaffName(staffName);
//		log.setOperTime(new Date());
//		log.setOperObject("联系人信息");
//		log.setOperKey(operKey);
//		log.setOperType(logType);
//		if(otherInfo != null){
//			log.setOperContent(otherInfo);
//		}
//		logDao.insertOperLog(log);
//	}
	
	/**
	 * 根据主键查询联系人
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	public ContactInfo getContactInfo(ContactInfo contactInfo) throws Exception{
		getLogger().debug("根据业务主键查询联系人信息,输入的参数为：" + contactInfo.toString());
		ContactInfoDao contactInfoDao =(ContactInfoDao)BeanLocator.getInstance().getBean(getContactInfoDao());
		return contactInfoDao.findContactInfo(contactInfo);
	}
	
	public ContactInfo getContactInfo(String id) throws Exception{
		getLogger().debug("根据主键ID查询联系人信息,输入的参数为：" + id);
		ContactInfoDao contactInfoDao =(ContactInfoDao)BeanLocator.getInstance().getBean(getContactInfoDao());
		return contactInfoDao.findContactInfoById(id);
	}
	/**
	 * 查询联系人信息列表
	 * @param contactInfo
	 * @return
	 * @throws Exception
	 */
	public List<ContactInfo> listContactInfo(ContactInfo contactInfo) throws Exception{
		getLogger().debug("查询联系人信息列表,输入的参数为：" + contactInfo.toString());
		ContactInfoDao contactInfoDao =(ContactInfoDao)BeanLocator.getInstance().getBean(getContactInfoDao());
		return contactInfoDao.listContactInfo(contactInfo);
	}
}
