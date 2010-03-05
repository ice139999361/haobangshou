package com.hbs.vendorinfo.action;

import org.apache.log4j.Logger;

import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.vendorinfo.manager.VendorInfoMgr;

/**
 * 经理角色供应商信息Action
 * @author xyf
 * @actions doAuditAgree doAuditDisAgree doList doGetInfo
 */
@SuppressWarnings("serial")
public class VendorInfoManagerAction extends BaseAction {
	
	/**
	 * Manager名
	 */
	static final String vendorInfoMgrName = "vendorInfoMgr";
		
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorInfoManagerAction.class);

    /**
	 * 待审批状态。state需要字符串，而Constants的类型不匹配。并且也不直观。
	 */
	static final String stateForAudit = "2";
	
	public static final String roleName = "cgmanager";
	
	VendorInfo vendorInfo;
	
	/**
	 * 获取供应商信息
	 * @return 供应商信息
	 */
	public VendorInfo getVendorInfo(){ return vendorInfo;}
	
	/**
	 * 设置供应商信息
	 * @param a 供应商信息
	 */
	public void setVendorInfo(VendorInfo a) { this.vendorInfo = a; }
	
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
	 *	vendorInfo.baseSeqId 或 (vendorInfo.commCode + vendorInfo.state)
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
	 *	vendorInfo.baseSeqId 或 (vendorInfo.commCode + vendorInfo.state)
	 * @action.input
	 *	auditDesc
	 * @return
	 */
	public String doAuditAgree()
	{
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doAuditAgree");
			
			if(!VendorInfoUtil.checkKeyFields(vendorInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(vendorInfoMgrName);
			getCustInfoValue(mgr);
			int ret = mgr.auditAgreeVendorInfo(vendorInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), auditDesc);
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
	 *	vendorInfo.baseSeqId 或 (vendorInfo.commCode + vendorInfo.state)
	 * @action.input
	 *	auditDesc
	 * @return
	 */
	public String doAuditDisAgree()
	{
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doAuditDisAgree");
			if(!VendorInfoUtil.checkKeyFields(vendorInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(vendorInfoMgrName);
			getCustInfoValue(mgr);
			int ret = mgr.auditDisAgreeVendorInfo(vendorInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), auditDesc);
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
	 * @action.input vendorInfo.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doList()
	{
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doList");
			if (vendorInfo == null) {
				vendorInfo = new VendorInfo();
			}
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(vendorInfoMgrName);
			setPagination(vendorInfo);
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeCustState, roleName));
			setResult("list", mgr.getVendorInfoList(vendorInfo));
			setTotalCount(mgr.getCustomerInfoCount(vendorInfo));
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
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(vendorInfoMgrName);
			vendorInfo = new VendorInfo();
			vendorInfo.setState(stateForAudit);
			setPagination(vendorInfo);
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeCustState, roleName));
			setResult("list", mgr.getVendorInfoList(vendorInfo));
			setTotalCount(mgr.getCustomerInfoCount(vendorInfo));
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
	 * 获取供应商详细信息
	 * @action.input 
	 *	vendorInfo.baseSeqId 或 (vendorInfo.commCode + vendorInfo.state)
	 * @action.result vendorInfo.*
	 * @return
	 */
	public String doGetInfo()
	{
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doGetInfo");
			if(!VendorInfoUtil.checkKeyFields(vendorInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(vendorInfoMgrName);
			getCustInfoValue(mgr);
			this.setResult("vendorInfo", vendorInfo);
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
	 * 根据vendorInfo的部分信息获取全部信息
	 * @param mgr
	 * @throws Exception
	 */
	protected void getCustInfoValue(VendorInfoMgr mgr) throws Exception
	{
		vendorInfo = VendorInfoUtil.getVendorInfo(mgr, vendorInfo);
	}
}
