/**
 * 
 */
package com.hbs.customerinfo.action;

import org.apache.log4j.Logger;

import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.domain.invoice.pojo.PeriodSpecMemo;
import com.hbs.invoice.manager.PeriodSpecMemoMgr;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class CustomerInfoCwNormalAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(CustomerInfoCwNormalAction.class);
	
	public static final String roleName = "cwnormal";
	
	CustomerInfo custInfo;
	
	public CustomerInfo getCusInfo() {
		return custInfo;
	}
	
	public void setCustInfo(CustomerInfo a) {
		this.custInfo = a;
	}
	
	/**
	 * 查询
	 * @action.input custInfo.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doList() {
		try {
			if (logger.isDebugEnabled())
				logger.debug("begin doList");
			if (custInfo == null) {
				custInfo = new CustomerInfo();
			}
			setPagination(custInfo);
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(CustomerInfoNormalAction.custInfoMgrName);
			custInfo.setField("noState01", true);
			setResult("list", mgr.getCustomerInfoList(custInfo));
			setTotalCount(mgr.getCustomerInfoCount(custInfo));
			setResult("count", getTotalCount());
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeCustState, roleName));
			if (logger.isDebugEnabled())
				logger.debug("end doList");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doList.", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 获取供应商详细信息
	 * @action.input 
	 *	custInfo.baseSeqId 或 (custInfo.commCode + custInfo.state)
	 * @action.result custInfo.*
	 * @return
	 */
	public String doGetInfo()
	{
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doGetInfo");
			if(!CustomerInfoUtil.checkKeyFields(custInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			custInfo.setState("0");
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(CustomerInfoNormalAction.custInfoMgrName);
			getCustomerInfoValue(mgr);
			if(!"0".equals(custInfo.getState())){
				logger.info("状态错误！");
				setErrorReason("状态错误！");
				return ERROR;
			}
			this.setResult("custInfo", custInfo);
			if (logger.isDebugEnabled())    logger.debug("end doGetInfo");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
            return ERROR;
		}
	}
	
	/**
	 * 财务锁定
	 * @action.input 
	 *	custInfo.baseSeqId 或 (custInfo.commCode + custInfo.state)
	 * @action.input memo 说明
	 * @return
	 */
	public String doLock(){
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doLock");
			if(!CustomerInfoUtil.checkKeyFields(custInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(CustomerInfoNormalAction.custInfoMgrName);
			int i = mgr.lockCustomerInfo(custInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				logger.error("提交错误！ ret = " + i);
				setErrorReason("提交错误！");
				return ERROR;
			}
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doLock", e);
			setErrorReason("内部错误");
            return ERROR;
		}
	}

	/**
	 * 财务解锁
	 * @action.input 
	 *	custInfo.baseSeqId 或 (custInfo.commCode + custInfo.state)
	 * @action.input memo 说明
	 * @return
	 */
	public String doUnlock(){
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doUnlock");
			if(!CustomerInfoUtil.checkKeyFields(custInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(CustomerInfoNormalAction.custInfoMgrName);
			int i = mgr.unlockCustomerInfo(custInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				logger.error("提交错误！ ret = " + i);
				setErrorReason("提交错误！");
				return ERROR;
			}
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doUnlock", e);
			setErrorReason("内部错误");
            return ERROR;
		}
	}

	protected String getMemo() {
		return this.getHttpServletRequest().getParameter("memo");
	}
	
	/**
	 * 根据custInfo的部分信息获取全部信息
	 * @param mgr
	 * @throws Exception
	 */
	protected void getCustomerInfoValue(CustomerInfoMgr mgr) throws Exception
	{
		custInfo = CustomerInfoUtil.getCustomerInfo(mgr, custInfo);
		if(null != custInfo){
			PeriodSpecMemoMgr specMemoMgr =(PeriodSpecMemoMgr)BeanLocator.getInstance().getBean("periodSpecMemoMgr");
			
			PeriodSpecMemo memo = specMemoMgr.getSepcMemo(custInfo.getCommCode());
			if(null !=memo){
				custInfo.setSpecMemo(memo.getMemo());
			}
			
		}
	}
}
