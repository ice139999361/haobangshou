package com.hbs.customerinfo.action;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.common.utils.StaffUtil;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.auth.pojo.Staff;
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
	
	public static final String roleName = "scmanager";
	
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
	 * 审批
	 * @action.input 
	 *	custInfo.baseSeqId 或 (custInfo.commCode + custInfo.state)
	 * @action.input audit	审批结果 0：审批不通过；1：审批通过
	 * @action.input	auditDesc 审批意见
	 * @return
	 */
	public String doAudit() {
		try {
			String audit = this.getHttpServletRequest().getParameter("audit");
			if(audit == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			if(audit.equals("0")) {
				return doAuditDisAgree();
			} else {
				return doAuditAgree();
			}
		} catch(Exception e) {
			logger.error("catch Exception in doAudit", e);
			setErrorReason("内部错误");
            return ERROR;
		}
	}
	
	/**
	 * 审批同意
	 * @action.input 
	 *	custInfo.baseSeqId 或 (custInfo.commCode + custInfo.state)
	 * @action.input	auditDesc 审批意见
	 * @return
	 */
	public String doAuditAgree()
	{
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doAuditAgree");
			
			if(!CustomerInfoUtil.checkKeyFields(custInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(custInfoMgrName);
			getCustInfoValue(mgr);
			int ret = mgr.auditAgreeCustomerInfo(custInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), auditDesc);
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
					logger.info(s + " ret=" + ret);
					break;
				}
				setErrorReason(s);
				return ERROR;
			}
			if (logger.isDebugEnabled())    logger.debug("end doAuditAgree");
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
			if (logger.isDebugEnabled())    logger.debug("begin doAuditDisAgree");
			if(!CustomerInfoUtil.checkKeyFields(custInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(custInfoMgrName);
			getCustInfoValue(mgr);
			int ret = mgr.auditDisAgreeCustomerInfo(custInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), auditDesc);
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
					logger.info(s + " ret=" + ret);
					break;
				}
				setErrorReason(s);
				return ERROR;
			}
			if (logger.isDebugEnabled())    logger.debug("end doAuditDisAgree");
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
			if (logger.isDebugEnabled())    logger.debug("begin doList");
			if (custInfo == null) {
				custInfo = new CustomerInfo();
			}
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(custInfoMgrName);
			custInfo.setField("noState01", true);
			setPagination(custInfo);
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeCustState, roleName));
			setResult("list", mgr.getCustomerInfoList(custInfo));
			setTotalCount(mgr.getCustomerInfoCount(custInfo));
			setResult("count", getTotalCount());
			if (logger.isDebugEnabled())    logger.debug("end doList");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doList", e);
			setErrorReason("内部错误");
            return ERROR;
		}
	}
	
	/** autocomplete 填充使用  
	 * 
	 * @return
	 */
	public String doListDict()
	{
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doListDict");
			if (custInfo == null) {
				custInfo = new CustomerInfo();
				custInfo.setState("0");
			}else{
				custInfo.setState("0");
			}
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(custInfoMgrName);
			
			setResult("list", mgr.getCustomerInfoList(custInfo));
			
			if (logger.isDebugEnabled())    logger.debug("end doListDict");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doListDict", e);
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
			if (logger.isDebugEnabled())    logger.debug("begin doListForAudit");
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(custInfoMgrName);
			if(custInfo == null)
				custInfo = new CustomerInfo();
			custInfo.setState(stateForAudit);
			setPagination(custInfo);
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeCustState, roleName));
			setResult("list", mgr.getCustomerInfoList(custInfo));
			setTotalCount(mgr.getCustomerInfoCount(custInfo));
			setResult("count", getTotalCount());
			if (logger.isDebugEnabled())    logger.debug("end doListForAudit");
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
			if (logger.isDebugEnabled())    logger.debug("begin doGetInfo");
			if(!CustomerInfoUtil.checkKeyFields(custInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(custInfoMgrName);
			getCustInfoValue(mgr);
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
	
	public String doUpdateSales(){
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doUpdateSales");
			
			if(!CustomerInfoUtil.checkKeyFields(custInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			String staffId = custInfo.getStaffId();
			String assStaffId = custInfo.getAssStaffId();
			if(StringUtils.isEmpty(staffId) && StringUtils.isEmpty(assStaffId)){
				logger.error("传入的销售人员和业务助理都为空！");
				setErrorReason("传入的销售人员和业务助理都为空，请选择！");
				return ERROR;
			}
			if(StringUtils.isNotEmpty(staffId)){				
				Staff u = StaffUtil.getStaffById(staffId);
				if(u != null){
					custInfo.setStaffName(u.getStaffName());
				}else{
					logger.error("无法找到对应的销售人员！");
					setErrorReason("无法找到对应的销售人员！");
					return ERROR;
				}					
			}
			
			if(StringUtils.isNotEmpty(assStaffId)){				
				Staff u = StaffUtil.getStaffById(assStaffId);
				if(u != null){
					custInfo.setAssStaffName(u.getStaffName());
				}else{
					logger.error("无法找到对应的业务助理！");
					setErrorReason("无法找到对应的业务助理！");
					return ERROR;
				}					
			}
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(custInfoMgrName);
			
			mgr.updateCustSalesInfo(custInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), null);
			
			if (logger.isDebugEnabled())    logger.debug("end doUpdateSales");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doAuditAgree", e);
			setErrorReason("修改客户信息对应的销售或业务助理错误!");
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
		custInfo = CustomerInfoUtil.getCustomerInfo(mgr, custInfo);
	}
}
