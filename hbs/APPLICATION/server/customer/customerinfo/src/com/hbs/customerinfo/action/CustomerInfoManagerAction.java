package com.hbs.customerinfo.action;

import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;

/**
 * 经理角色客户信息Action
 * @author xyf
 * @actions doAuditAgree doAuditDisAgree doList doGetInfo
 */
@SuppressWarnings("serial")
public class CustomerInfoManagerAction extends BaseAction {
	
	/**
	 * Manager名
	 */
	static final String custInfoMgrName = "customerInfoMgr";
		
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustomerInfoManagerAction.class);

    /**
	 * 待审批状态。state需要字符串，而Constants的类型不匹配。并且也不直观。
	 */
	static final String stateForAudit = "2";
	
	CustomerInfo custInfo;
	
	/**
	 * 获取客户信息
	 * @return 客户信息
	 */
	public CustomerInfo getCustInfo(){ return custInfo;}
	
	/**
	 * 设置客户信息
	 * @param a 客户信息
	 */
	public void setCustInfo(CustomerInfo a) { this.custInfo = a; }
	
	String auditDesc;
	
	/**
	 * 获取审批意见
	 * @return 审批意见
	 */
	public String getAuditDesc() { return auditDesc; }
	
	/**
	 * 设置审批意见
	 * @param a 审批意见
	 */
	public void setAuditDesc(String a) { auditDesc = a; }
	
	/**
	 * 审批同意
	 * @action.input 
	 *	custInfo.baseSeqId 或 (custInfo.commCode + custInfo.state)
	 * @action.input
	 *	auditDesc
	 * @return
	 */
	public String doAuditAgree()
	{
		try
		{
			logger.debug("begin doAuditAgree");
			
			if(!CustomerInfoUtil.checkKeyFields(custInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			CustomerInfoMgr mgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean(custInfoMgrName);
			getCustInfoValue(mgr);
			int ret = mgr.auditAgreeCustomerInfo(custInfo, getLoginStaff().getStaffId(), getLoginStaff().getStaffName(), auditDesc);
			if(ret != 0)
			{
				String s;
				switch(ret)
				{
				case 1:
					s = "无此状态！";
					break;
				case 2:
					s = "状态不正确！";
					break;
				default:
					s = "保存出错！";
				}
				setErrorReason(s);
				return ERROR;
			}
			logger.debug("end doAuditAgree");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doAuditAgree", e);
			setErrorReason("内部错误");
            return ERROR;
		}
	}
	
	/**
	 * 审批不同意
	 * @action.input 
	 *	custInfo.baseSeqId 或 (custInfo.commCode + custInfo.state)
	 * @action.input
	 *	auditDesc
	 * @return
	 */
	public String doAuditDisAgree()
	{
		try
		{
			logger.debug("begin doAuditDisAgree");
			if(!CustomerInfoUtil.checkKeyFields(custInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			CustomerInfoMgr mgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean(custInfoMgrName);
			getCustInfoValue(mgr);
			int ret = mgr.auditDisAgreeCustomerInfo(custInfo, getLoginStaff().getStaffId(), getLoginStaff().getStaffName(), auditDesc);
			if(ret != 0)
			{
				String s;
				switch(ret)
				{
				case 1:
					s = "无此状态！";
					break;
				case 2:
					s = "状态不正确！";
					break;
				default:
					s = "保存出错！";
				}
				setErrorReason(s);
				return ERROR;
			}
			logger.debug("end doAuditDisAgree");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doAuditDisAgree", e);
			setErrorReason("内部错误");
            return ERROR;
		}
	}

	/**
	 * 查询，调用mgr.getCustomerInfoList。
	 * @action.input custInfo.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doList()
	{
		try
		{
			logger.debug("begin doList");
			CustomerInfoMgr mgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean(custInfoMgrName);
			setPagination(custInfo);
			setResult("list", mgr.getCustomerInfoList(custInfo));
			setTotalCount(mgr.getCustomerInfoCount(custInfo));
			setResult("count", getTotalCount());
			logger.debug("end doList");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doList", e);
			setErrorReason("内部错误");
            return ERROR;
		}
	}
	
	/**
	 * 查询待审批数据
	 * @action.input 无
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doListForAudit()
	{
		try
		{
			logger.debug("begin doListForAudit");
			CustomerInfoMgr mgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean(custInfoMgrName);
			custInfo = new CustomerInfo();
			custInfo.setState(stateForAudit);
			setPagination(custInfo);
			setResult("list", mgr.getCustomerInfoList(custInfo));
			setTotalCount(mgr.getCustomerInfoCount(custInfo));
			setResult("count", getTotalCount());
			logger.debug("end doListForAudit");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doListForAudit", e);
			setErrorReason("内部错误");
            return ERROR;
		}
	}
	
	/**
	 * 获取客户详细信息
	 * @action.input 
	 *	custInfo.baseSeqId 或 (custInfo.commCode + custInfo.state)
	 * @action.result custInfo.*
	 * @return
	 */
	public String doGetInfo()
	{
		try
		{
			logger.debug("begin doGetInfo");
			if(!CustomerInfoUtil.checkKeyFields(custInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			CustomerInfoMgr mgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean(custInfoMgrName);
			getCustInfoValue(mgr);
			this.setResult("custInfo", custInfo);
			logger.debug("end doGetInfo");
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
	 * 根据custInfo的部分信息获取全部信息
	 * @param mgr
	 * @throws Exception
	 */
	protected void getCustInfoValue(CustomerInfoMgr mgr) throws Exception
	{
		custInfo = mgr.getCustomerInfo(custInfo, true);
	}
}
