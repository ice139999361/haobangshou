package com.hbs.customerinfo.action;

import com.hbs.common.action.base.BaseAction;
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
	 * 待审批状态。state需要字符串，而Constants的类型不匹配。并且也不直观。
	 */
	static String stateForAudit = "1";
	
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
			CustomerInfoMgr mgr = new CustomerInfoMgr();
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
			return SUCCESS;
		}
		catch(Exception e)
		{
			this.setErrorReason(e.getMessage(), e);
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
			CustomerInfoMgr mgr = new CustomerInfoMgr();
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
			return SUCCESS;
		}
		catch(Exception e)
		{
			this.setErrorReason(e.getMessage(), e);
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
			CustomerInfoMgr mgr = new CustomerInfoMgr();
			setPagination(custInfo);
			setResult("list", mgr.getCustomerInfoList(custInfo));
			setTotalCount(mgr.getCustomerInfoCount(custInfo));
			setResult("count", getTotalCount());
			return SUCCESS;
		}
		catch(Exception e)
		{
			setErrorReason(e.getMessage(), e);
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
			CustomerInfoMgr mgr = new CustomerInfoMgr();
			custInfo = new CustomerInfo();
			custInfo.setState(stateForAudit);
			setPagination(custInfo);
			setResult("list", mgr.getCustomerInfoList(custInfo));
			setTotalCount(mgr.getCustomerInfoCount(custInfo));
			setResult("count", getTotalCount());
			return SUCCESS;
		}
		catch(Exception e)
		{
			setErrorReason(e.getMessage(), e);
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
			CustomerInfoMgr mgr = new CustomerInfoMgr();
			getCustInfoValue(mgr);
			this.setResult("custInfo", custInfo);
			return SUCCESS;
		}
		catch(Exception e)
		{
			setErrorReason(e.getMessage(), e);
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
