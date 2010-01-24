/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerinfo.manager;

import java.util.Date;
import java.util.List;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerinfo.constants.CustInfoConstants;

import com.hbs.domain.common.dao.baseinfo.OperLogDao;
import com.hbs.domain.common.pojo.baseinfo.OperLog;
import com.hbs.domain.customer.customerinfo.dao.CustPartNoInfoDao;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;


public class CustPartNoInfoMgr {

	private static final String CUSTOMERPARTNOINFODAO ="customerPartNoInfoDao";
	
	/**
	 * 新增物料关系，如果随基本信息新增，则不输入用户，如果不随基本信息新增，则需要提供用户
	 * @param custPartNoInfo
	 * @param staffId
	 * @param staffName
	 * @return 0--成功  1--存在重复数据
	 * @throws Exception
	 */
	public int insertCustPartNoInfo(CustPartNoInfo custPartNoInfo,String staffId,String staffName) throws Exception{
		int ret =0;
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		//查询物料关系，判断插入的数据是否已经存在
		CustPartNoInfo tempInfo = new CustPartNoInfo();
		tempInfo.setCommCode(custPartNoInfo.getCommCode());
		tempInfo.setCustPartNo(custPartNoInfo.getCustPartNo());
		tempInfo.setPartNo(custPartNoInfo.getPartNo());
		tempInfo.setState(custPartNoInfo.getState());
		
		tempInfo = custPartNoInfoDao.findCustPartNoInfoByBizKey(tempInfo);
		Integer seqID =0;
		if(null != tempInfo){//不存在
			seqID = custPartNoInfoDao.insertCustPartNoInfo(custPartNoInfo);
		}else{
			ret = 1;
		}
		if(null != staffId){//独立操作，记录日志
			operLog( staffId, staffName, "新增", seqID.toString(), null);
		}
		return ret;
	}
	/**
	 * 批量插入信息
	 * @param custPartNoInfoList
	 * @param staffId
	 * @param staffName
	 * @return
	 * @throws Exception
	 */
	public int insertCustPartNoInfoList(List<CustPartNoInfo>  custPartNoInfoList,String staffId,String staffName) throws Exception{
		int ret =0;
		for(CustPartNoInfo custPartNoInfo : custPartNoInfoList){
			insertCustPartNoInfo( custPartNoInfo, staffId, staffName);
		}
		return ret;
	}
	/**
	 * 更新信息
	 * @param custPartNoInfo
	 * @param staffId
	 * @param staffName
	 * @param otherInfo
	 * @return
	 * @throws Exception
	 */
	public int updateCustPartNoInfo(CustPartNoInfo custPartNoInfo,String staffId,String staffName,String otherInfo)throws Exception{
		int ret =0;
		int state = Integer.parseInt(custPartNoInfo.getState());
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		String strLogType = null;
		switch (state){
		case 0:  //审批通过,先删除后插入,同时删除待审批数据,待办未做
			custPartNoInfoDao.deleteCustPartNoInfoByBizKey(custPartNoInfo);
			custPartNoInfoDao.insertCustPartNoInfo(custPartNoInfo);			
			custPartNoInfoDao.deleteCustPartNoInfoByID(custPartNoInfo.getSeqId().toString());
			strLogType = "审批数据";
			break;
		case 1://没有提交的数据修改		
			custPartNoInfoDao.updateCustPartNoInfo(custPartNoInfo);
			strLogType = "修改临时数据";
			break;
		case 2://提交数据只修改状态
			custPartNoInfoDao.updateCustPartNoInfo(custPartNoInfo);
			strLogType = "提交临时数据";
			break;
		case 3://审批不通过数据只修改状态
			custPartNoInfoDao.updateCustPartNoInfoByState(custPartNoInfo);
			strLogType = "审批不通过数据";
			break;
		case 4://废弃数据只修改状态
			custPartNoInfoDao.updateCustPartNoInfoByState(custPartNoInfo);
			strLogType = "废弃数据";
			break;
		case 5://锁定数据只修改状态
			custPartNoInfoDao.updateCustPartNoInfoByState(custPartNoInfo);
			strLogType = "锁定数据";
			break;
		default:
			ret =1;
		}
		if(null != staffName){
			operLog( staffId, staffName, strLogType, custPartNoInfo.getSeqId().toString(), otherInfo);
		}
		return ret;
	}
	
	/**
	 * 批量更新
	 * @param custPartNoInfoList
	 * @param staffId
	 * @param staffName
	 * @param otherInfo
	 * @return
	 * @throws Exception
	 */
	public int updateCustPartNoInfoList(List<CustPartNoInfo> custPartNoInfoList,String staffId,String staffName,String otherInfo)throws Exception{
		int ret =0;
		for(CustPartNoInfo custPartNoInfo : custPartNoInfoList){
			updateCustPartNoInfo( custPartNoInfo, staffId, staffName, otherInfo);
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
	private void operLog(String staffId,String staffName,String logType,String operKey,String otherInfo){
		OperLogDao logDao = (OperLogDao)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTOMEROPERLOGDAO);
		OperLog log = new OperLog();
		log.setStaffId(staffId);
		log.setStaffName(staffName);
		log.setOperTime(new Date());
		log.setOperObject("物料对照关系信息");
		log.setOperKey(operKey);
		log.setOperType(logType);
		if(otherInfo != null){
			log.setOperContent(otherInfo);
		}
		logDao.insertOperLog(log);
	}
	
	/**
	 * 根据数据id获取信息
	 * @param seqId
	 * @return
	 * @throws Exception
	 */
	public CustPartNoInfo getCustPartNoInfoByID(String seqId)throws Exception{
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		return custPartNoInfoDao.findCustPartNoInfoByID(seqId);
	}
	
	/**
	 * 根据业务主键获取信息
	 * @param custPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public CustPartNoInfo getCustPartNoInfoByBizKey(CustPartNoInfo custPartNoInfo) throws Exception{
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		return custPartNoInfoDao.findCustPartNoInfoByBizKey(custPartNoInfo);
	}
	
	/**
	 * 获取列表信息
	 * @param custPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public List<CustPartNoInfo> listCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws Exception{
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		return custPartNoInfoDao.listCustPartNoInfo(custPartNoInfo);
	}
	
	/**
	 * 获取总记录数
	 * @param custPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public Integer listCustPartNoInfoCount(CustPartNoInfo custPartNoInfo) throws Exception{
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		return custPartNoInfoDao.listCustPartNoInfoCount(custPartNoInfo);
	}

}
