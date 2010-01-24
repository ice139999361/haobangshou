/**
 * system ：hbs
 * desc:    银行信息的接口类，定义统一方法供客户和供应商使用
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.manager.baseinfo;

import java.util.Date;
import java.util.List;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customer.common.constants.StateConstants;

import com.hbs.domain.common.dao.baseinfo.BankInfoDao;
import com.hbs.domain.common.dao.baseinfo.OperLogDao;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.pojo.baseinfo.OperLog;

/**
 * @author yangzj
 *
 */
public abstract class BankInfoMgr {

	/**
	 * 抽象方法，子类实现
	 * @return
	 */
	public abstract String getBankInfoDao();
	
	/**
	 *  抽象方法，子类实现
	 * @return
	 */
	public abstract String getLogDao();
	/**
	 * 插入银行信息,银行信息的插入，如果是单独插入，则状态为待审批，直接提交审批，
	 * 如果是跟客户信息同时提交，则跟随客户信息的状态	 
	 * @param bankInfo
	 * @param staffId
	 * @param staffName
	 * @throws Exception
	 */
	public int insertBankInfo(BankInfo bankInfo,String staffId,String staffName) throws Exception{
		int ret =0;
		BankInfoDao bankInfoDao =(BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		bankInfoDao.insertBankInfo(bankInfo);
		if(null != staffId){//说明银行信息是单独处理,需要单独记录操作日志
//			int state = Integer.parseInt(bankInfo.getState());
//			switch (state){
//			case 1://插入临时数据，还没有提交
//			case 2://插入提交的数据
//			}
			operLog(staffId,staffName, "新增", bankInfo.getBaseSeqId(),null);
		}
		return ret;
	}
	/**
	 * 批量插入银行信息
	 * @param bankInfoList
	 * @param staffId
	 * @param staffName
	 * @return
	 * @throws Exception
	 */
	public int insertBankInfoList(List<BankInfo> bankInfoList ,String staffId,String staffName) throws Exception{
		int ret =0;
		for(BankInfo bankInfo : bankInfoList){
			insertBankInfo(bankInfo, staffId, staffName);
		}
		return ret;
	}
	/**
	 * 更新银行信息
	 * @param bankInfo
	 * @param state
	 * @param staffId
	 * @param staffName
	 * @throws Exception
	 */
	public int updateBankInfo(BankInfo bankInfo, String staffId,String staffName,String otherInfo)throws Exception{
		int ret =0;
		int state = Integer.parseInt(bankInfo.getState());
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		String strLogType = null;
		switch (state){
		case 0:  //审批通过,先删除后插入,同时删除待审批数据,待办未做
			bankInfoDao.deleteBankInfo(bankInfo);
			bankInfoDao.insertBankInfo(bankInfo);			
			bankInfoDao.deleteBankInfoByID(bankInfo.getSeqId());
			strLogType = "审批数据";
			break;
		case 1://没有提交的数据修改		
			bankInfoDao.updateBankInfo(bankInfo);
			strLogType = "修改临时数据";
			break;
		case 2://提交数据只修改状态
			bankInfoDao.updateBankInfo(bankInfo);
			strLogType = "提交临时数据";
			break;
		case 3://审批不通过数据只修改状态
			bankInfoDao.updateBankInfoByState(bankInfo);
			strLogType = "审批不通过数据";
			break;
		case 4://废弃数据只修改状态
			bankInfoDao.deleteBankInfo(bankInfo);
			bankInfoDao.updateBankInfoByState(bankInfo);
			strLogType = "废弃数据";
			break;
		case 5://锁定数据只修改状态
			bankInfoDao.updateBankInfoByState(bankInfo);
			strLogType = "锁定数据";
			break;
			
		case 6 ://解锁数据，只修改状态
			
			bankInfo.setState(new Integer(StateConstants.STATE_0).toString());
			
			bankInfoDao.updateBankInfoByState(bankInfo);
			strLogType = "解锁数据";
		default:
			ret =1;
		}
		if(null != staffName){
			operLog( staffId, staffName, strLogType, bankInfo.getBaseSeqId(), otherInfo);
		}
		return ret;
	}
	
	/**
	 * 批量更新银行信息
	 * @param bankInfoList
	 * @param staffId
	 * @param staffName
	 * @param otherInfo
	 * @return
	 * @throws Exception
	 */
	public int updateBankInfoList(List<BankInfo> bankInfoList ,String staffId,String staffName,String otherInfo) throws Exception{
		int ret =0;
		for(BankInfo bankInfo : bankInfoList){
			updateBankInfo( bankInfo,  staffId, staffName, otherInfo);
		}
		return ret;
	}
	
	/**
	 * 记录操作日志
	 * @param staffId
	 * @param staffName
	 * @param logType
	 * @param operKey
	 * @param otherInfo
	 */
	private void operLog(String staffId,String staffName,String logType,String operKey,String otherInfo){
		OperLogDao logDao = (OperLogDao)BeanLocator.getInstance().getBean(getLogDao());
		OperLog log = new OperLog();
		log.setStaffId(staffId);
		log.setStaffName(staffName);
		log.setOperTime(new Date());
		log.setOperObject("银行信息");
		log.setOperKey(operKey);
		log.setOperType(logType);
		if(otherInfo != null){
			log.setOperContent(otherInfo);
		}
		logDao.insertOperLog(log);
	}
	
	/**
	 * 查询银行信息，以主键查询
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	public BankInfo getBankInfo(String pk) throws Exception{
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		return bankInfoDao.findBankInfo(pk);
	}
	
	/**
	 * 查询银行信息列表
	 * @param bankInfo
	 * @return
	 * @throws Exception
	 */
	public List<BankInfo> listBankInfo(BankInfo bankInfo) throws Exception{
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		return bankInfoDao.listBankInfo(bankInfo);
	}
}
