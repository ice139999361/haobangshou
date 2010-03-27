/**
 * system ：hbs
 * desc:    银行信息的接口类，定义统一方法供客户和供应商使用
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.manager.baseinfo;


import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customer.common.constants.StateConstants;

import com.hbs.domain.common.dao.baseinfo.BankInfoDao;

import com.hbs.domain.common.pojo.baseinfo.BankInfo;


/**
 * 本类及实现的子类供客户基本信息和供应商基本信息调用
 * action一层不需要调用，action面对的只是客户基本信息
 * 服务类和供应商基本信息服务类
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
	 * 抽象方法，获取子类的日志输出
	 * @return
	 */
	public abstract Logger getLogger();
	/**
	 * 插入银行信息,银行信息的插入，
	 * 客户信息同时提交，则跟随客户信息的状态	 
	 * @param bankInfo	 
	 * @return  0--成功 
	 * @throws Exception
	 */
	public int insertBankInfo(BankInfo bankInfo) throws Exception{
		int ret =0;
		getLogger().debug("新增银行信息，输入的参数为：" + bankInfo.toString());
		BankInfoDao bankInfoDao =(BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		BankInfo bInfo = bankInfoDao.findBankInfo(bankInfo);
		if(null == bInfo){
			bankInfoDao.insertBankInfo(bankInfo);
		}else{
			//ret = 1;
			throw new Exception("新增银行信息,新增的信息已存在，不能新增！");
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
	public int insertBankInfoList(List<BankInfo> bankInfoList) throws Exception{
		int ret =0;
		getLogger().debug("批量新增银行信息，批量数量为：" + bankInfoList.size());
		for(BankInfo bankInfo : bankInfoList){
			insertBankInfo(bankInfo);
		}
		return ret;
	}
	/**
	 * 删除正式信息及临时信息，供基本信息调用
	 * @param bankInfo
	 * @param isDelCurrent  是否同时删除本次操作的数据
	 * @throws Exception
	 */
	public void deleteBankInfo(BankInfo bankInfo,boolean isDelCurrent)throws Exception{
		getLogger().debug("删除银行信息,输入的参数为：" + bankInfo.toString());
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		bankInfoDao.deleteBankInfo(bankInfo);
		if(isDelCurrent){
			bankInfoDao.deleteBankInfoByID(bankInfo.getBaseSeqId());
		}		
	}
	/**
	 * 更新银行信息
	 * @param bankInfo
	 * @param state
	 * @param staffId
	 * @param staffName
	 * @throws Exception
	 */
	private int updateBankInfo(BankInfo bankInfo)throws Exception{
		int ret =0;
		getLogger().debug("更新银行信息,输入的参数为：" + bankInfo.toString());
		int state = Integer.parseInt(bankInfo.getState());
		getLogger().debug("更新银行信息,状态为：" + state);
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		//String strLogType = null;
		switch (state){
		case 0:  //审批通过,先删除后插入,同时删除待审批数据
		         //删除操作由基本信息类删除
			//bankInfoDao.deleteBankInfo(bankInfo);
			bankInfoDao.insertBankInfo(bankInfo);			
			//bankInfoDao.deleteBankInfoByID(bankInfo.getSeqId());
			//strLogType = "审批数据";
			break;
		case 1://没有提交的数据修改	
			//为保证列表数据变化能够实现，先删除所有的，再重新插入，删除动作在调用者中删除
			//bankInfoDao.deleteBankInfoByID(bankInfo.getBaseSeqId());
			bankInfoDao.insertBankInfo(bankInfo);
			//bankInfoDao.updateBankInfo(bankInfo);
			//strLogType = "修改临时数据";
			break;
		case 2://提交数据只修改状态
			//为保证列表数据变化能够实现，先删除所有的，再重新插入 ,删除动作在调用者中删除
			//bankInfoDao.deleteBankInfoByID(bankInfo.getBaseSeqId());
			bankInfoDao.insertBankInfo(bankInfo);
			//bankInfoDao.updateBankInfo(bankInfo);
			//strLogType = "提交临时数据";
			break;
		case 3://审批不通过数据只修改状态
			bankInfoDao.updateBankInfoByState(bankInfo);
			//strLogType = "审批不通过数据";
			break;
		case 4://废弃数据，把已经废弃的数据清除掉，同时如果把本次废弃的数据状态置为废弃
			//删除废弃数据由基本信息操作
			//bankInfoDao.deleteBankInfo(bankInfo);
			bankInfoDao.updateBankInfoByState(bankInfo);
			//strLogType = "废弃数据";
			break;
		case 5://锁定数据只修改状态
			bankInfoDao.updateBankInfoByState(bankInfo);
			//strLogType = "锁定数据";
			break;
			
		case 6 ://解锁数据，只修改状态
			
			bankInfo.setState(new Integer(StateConstants.STATE_0).toString());
			
			bankInfoDao.updateBankInfoByState(bankInfo);
			//strLogType = "解锁数据";
		default:
			throw new Exception("更新银行信息 ,无此类型的状态，无法进行更新！");
		}
//		if(null != staffName){
//			operLog( staffId, staffName, strLogType, bankInfo.getBaseSeqId(), otherInfo);
//		}
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
	public int updateBankInfoList(List<BankInfo> bankInfoList ) throws Exception{
		int ret =0;
		getLogger().debug("批量更新银行信息，批量数量为：" + bankInfoList.size());
		boolean isDel = true;
		for(BankInfo bankInfo : bankInfoList){
			if(isDel){
				int state = Integer.parseInt(bankInfo.getState());
				switch(state){
				case 1:
				case 2:
					BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
					bankInfoDao.deleteBankInfoByID(bankInfo.getBaseSeqId());
					isDel = false;
					break;
					
				}
			}
			updateBankInfo( bankInfo);
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
//	private void operLog(String staffId,String staffName,String logType,String operKey,String otherInfo){
//		OperLogDao logDao = (OperLogDao)BeanLocator.getInstance().getBean(getLogDao());
//		OperLog log = new OperLog();
//		log.setStaffId(staffId);
//		log.setStaffName(staffName);
//		log.setOperTime(new Date());
//		log.setOperObject("银行信息");
//		log.setOperKey(operKey);
//		log.setOperType(logType);
//		if(otherInfo != null){
//			log.setOperContent(otherInfo);
//		}
//		logDao.insertOperLog(log);
//	}
	
	/**
	 * 查询银行信息，以主键查询
	 * @param bankInfo 
	 * @return
	 * @throws Exception
	 */
	public BankInfo getBankInfo(BankInfo bankInfo) throws Exception{
		getLogger().debug("查询单条银行信息,输入的参数为：" + bankInfo.toString());
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		return bankInfoDao.findBankInfo(bankInfo);
	}
	
	public BankInfo getBankInfoById(String seqId) throws Exception{
		getLogger().debug("根据主键查询单条银行信息,输入的参数为：" + seqId);
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		return bankInfoDao.findBankInfoById(seqId);
	}
	/**
	 * 查询银行信息列表
	 * @param bankInfo
	 * @return
	 * @throws Exception
	 */
	public List<BankInfo> listBankInfo(BankInfo bankInfo) throws Exception{
		getLogger().debug("查询银行信息,输入的参数为：" + bankInfo.toString());
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		return bankInfoDao.listBankInfo(bankInfo);
	}
}
