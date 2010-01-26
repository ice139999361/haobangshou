/**
 * system ：hbs
 * desc:    账期的抽象类，定义统一方法供客户和供应商使用
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.manager.baseinfo;


import java.util.List;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customer.common.constants.StateConstants;

import com.hbs.domain.common.dao.baseinfo.AccountPreiodDao;

import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;


/**
 * 本类及实现的子类供客户基本信息和供应商基本信息调用
 * action一层不需要调用，action面对的只是客户基本信息
 * 服务类和供应商基本信息服务类
 */
public abstract class AccountPreiodMgr {

	/**
	 * 抽象方法，子类实现
	 * @return
	 */
	public abstract String getAccountPreiodDao();
	
	/**
	 *  抽象方法，子类实现
	 * @return
	 */
	public abstract String getLogDao();
	/**
	 * 插入账期信息,账期信息的插入 如果是跟客户信息同时提交，则跟随客户信息的状态
	 * 
	 * @param accountPreiod
	 * @throws Exception
	 * @return 0--成功 1---数据库已经存在重复数据
	 */

	public int insertAccountPreiod(AccountPreiod accountPreiod)
			throws Exception {

		int ret = 0;
		AccountPreiodDao aPreiodDao = (AccountPreiodDao) BeanLocator
				.getInstance().getBean(
						getAccountPreiodDao());
		AccountPreiod tempPreiod = aPreiodDao.findAccountPreiod(accountPreiod);
		if (null == tempPreiod) {
			aPreiodDao.insertAccountPreiod(accountPreiod);
		} else {
			ret = 1;
		}
		return ret;
	}
/**
 * 删除正式信息及临时信息，供基本信息调用
 * @param accountPreiod
 * @param isDelCurrent  是否同时删除本次操作的数据
 * @throws Exception
 */
	public void deleteAccountPreiod(AccountPreiod accountPreiod,boolean isDelCurrent) throws Exception{
		AccountPreiodDao aPreiodDao = (AccountPreiodDao)BeanLocator.getInstance().getBean(getAccountPreiodDao());
		aPreiodDao.deleteAccountPreiod(accountPreiod);
		if(isDelCurrent){
			aPreiodDao.deleteAccountPreiodByID(accountPreiod.getSeqId());
		}
	}
	/**
	 * 更新账期信息
	 * 
	 * @param accountPreiod
	 * @param staffId
	 * @param staffName
	 * @throws Exception
	 */
	public int updateAccountPreiod(AccountPreiod accountPreiod) throws Exception {
		int ret =0;
		int state = Integer.parseInt(accountPreiod.getState());
		AccountPreiodDao aPreiodDao = (AccountPreiodDao)BeanLocator.getInstance().getBean(getAccountPreiodDao());
		//String strLogType = null;
		switch (state){
		case 0:  //审批通过,先删除后插入,同时删除待审批数据
			     //删除操作由基本信息类删除
			//aPreiodDao.deleteAccountPreiod(accountPreiod);
			aPreiodDao.insertAccountPreiod(accountPreiod);			
			//aPreiodDao.deleteAccountPreiodByID(accountPreiod.getSeqId());
			//strLogType = "审批数据";
			break;
		case 1://没有提交的数据修改		
			aPreiodDao.updateAccountPreiod(accountPreiod);
			//strLogType = "修改临时数据";
			break;
		case 2://提交数据修改
			aPreiodDao.updateAccountPreiod(accountPreiod);
			//strLogType = "提交临时数据";
			break;
		case 3://审批不通过数据只修改状态
			aPreiodDao.updateAccountPreiodByState(accountPreiod);
			//strLogType = "审批不通过数据";
			break;
		case 4://废弃数据，把已经废弃的数据清除掉，同时如果把本次废弃的数据状态置为废弃
			//删除废弃数据由基本信息操作
			//aPreiodDao.deleteAccountPreiod(accountPreiod);
			aPreiodDao.updateAccountPreiodByState(accountPreiod);
			//strLogType = "废弃数据";
			break;
		case 5://锁定数据只修改状态
			aPreiodDao.updateAccountPreiodByState(accountPreiod);
			//strLogType = "锁定数据";
			break;
		case 6 ://解锁数据，只修改状态
			
			accountPreiod.setState(new Integer(StateConstants.STATE_0).toString());
			
			aPreiodDao.updateAccountPreiodByState(accountPreiod);
			//strLogType = "解锁数据";
		default:
			ret =1;
		}
		
//		if(null != staffId){//不是随主记录操作，需要记录操作日志
//			OperLogDao logDao = (OperLogDao)BeanLocator.getInstance().getBean(getLogDao());
//			OperLog log = new OperLog();
//			log.setStaffId(staffId);
//			log.setStaffName(staffName);
//			log.setOperTime(new Date());
//			log.setOperObject("结算信息");
//			log.setOperKey(accountPreiod.getBaseSeqId());
//			log.setOperType(strLogType);
//			if(otherInfo != null){
//				log.setOperContent(otherInfo);
//			}
//			logDao.insertOperLog(log);
//		}
		return ret;
	}
	/**
	 * 获取单个账期信息，可以通过commcode,state 来唯一定位 
	 * @param accountPreiod
	 * @return
	 * @throws Exception
	 */
	public AccountPreiod getAccountPreiod(AccountPreiod accountPreiod)throws Exception{
		AccountPreiodDao aPreiodDao = (AccountPreiodDao)BeanLocator.getInstance().getBean(getAccountPreiodDao());
		return aPreiodDao.findAccountPreiod(accountPreiod);
	}
	
	/**
	 * 获取账期列表信息，一个客户可能会查询出多个账期信息，主要的区别在于状态
	 * @param accountPreiod
	 * @return
	 * @throws Exception
	 */
	public List<AccountPreiod> listAccountPreiod(AccountPreiod accountPreiod) throws Exception{
		AccountPreiodDao aPreiodDao = (AccountPreiodDao)BeanLocator.getInstance().getBean(getAccountPreiodDao());
		return aPreiodDao.listAccountPreiod(accountPreiod);
	}
}
