/**
 * 
 */
package com.hbs.vendorinfo.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorPartNoInfo;
import com.hbs.vendorinfo.manager.VendorPartNoInfoMgr;

/**
 * 供应商物料关系经理用户Action
 * @author xyf
 * @actions doAuditAgree doAuditDisAgree doList
 */
@SuppressWarnings("serial")
public class VendorPartNoInfoManagerAction extends BaseAction {

	/**
	 * Manager名
	 */
	static final String vendorPartNoInfoMgrName = "vendorPartNoInfoMgr";
		
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorPartNoInfoNormalAction.class);

    VendorPartNoInfo vendorPartNoInfo;
    public VendorPartNoInfo getVendorPartNoInfo() { return vendorPartNoInfo; }
    public void setVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) { this.vendorPartNoInfo = vendorPartNoInfo; }
    
	String auditDesc;
	public String getAuditDesc() { return auditDesc; }
	public void setAuditDesc(String a) { auditDesc = a; }

    /**
     * 查询供应商物料关系
 	 * @action.input vendorPartNoInfo.commCode + vendorPartNoInfo.查询条件
	 * @action.result list：列表 count：总数
     * @return
     */
    public String doList()
    {
    	try
    	{
			if (logger.isDebugEnabled())    logger.debug("begin doList");

			if(vendorPartNoInfo == null)
				vendorPartNoInfo = new VendorPartNoInfo();

			if(!checkCommonFields())
				return ERROR;
			
			setPagination(vendorPartNoInfo);
			VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)BeanLocator.getInstance().getBean(vendorPartNoInfoMgrName);
			setResult("list", mgr.listVendorPartNoInfo(vendorPartNoInfo));
			setTotalCount(mgr.listVendorPartNoInfoCount(vendorPartNoInfo));
			setResult("count", getTotalCount());
			if (logger.isDebugEnabled())    logger.debug("end doList");
    		return SUCCESS;
    	}
    	catch(Exception e)
    	{
    		logger.error("catch Exception in doList.", e);
			setErrorReason("内部错误");
			return ERROR;
    	}
    }

    /**
     * 审批同意
     * @action.input 	vendorPartNoInfo.*
	 * @action.input	auditDesc	审批意见
     * @return
     */
    public String doAuditAgree()
    {
    	try{
    		if (logger.isDebugEnabled())    logger.debug("begin doAuditAgree");
    		
			/*
			if(!checkCommonFields())
				return ERROR;
			List<FieldErr> errs = VendorPartNoInfoUtil.checkInputFields(vendorPartNoInfo);
			if(errs.isEmpty())
			{
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			
			*/
			VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)BeanLocator.getInstance().getBean(vendorPartNoInfoMgrName);

			if(vendorPartNoInfo == null || vendorPartNoInfo.getSeqId() == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
    		vendorPartNoInfo = mgr.getVendorPartNoInfoByID(vendorPartNoInfo.getSeqId().toString());
			if(vendorPartNoInfo == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			int i = mgr.auditAgreeCustPartNoInfo(vendorPartNoInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), auditDesc);
			if(i != 0)
			{
				logger.info("审批出错！");
				setErrorReason("审批出错！");
				return ERROR;
			}
		
    		if (logger.isDebugEnabled())    logger.debug("end doAuditAgree");
    		return SUCCESS;
    	}catch(Exception e){
    		logger.error("catch Exception in doAuditAgree.", e);
			setErrorReason("内部错误");
			return ERROR;
    	}
    }
    
    /**
     * 审批不同意
     * @action.input 	vendorPartNoInfo.*
	 * @action.input	auditDesc	审批意见
     * @return
     */
    public String doAuditDisAgree()
    {
    	try{
    		if (logger.isDebugEnabled())    logger.debug("begin doAuditDisAgree");
    		
    		/*
			if(!checkCommonFields())
				return ERROR;
			List<FieldErr> errs = VendorPartNoInfoUtil.checkInputFields(vendorPartNoInfo);
			if(errs.isEmpty())
			{
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			*/
			VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)BeanLocator.getInstance().getBean(vendorPartNoInfoMgrName);
			if(vendorPartNoInfo == null || vendorPartNoInfo.getSeqId() == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
    		vendorPartNoInfo = mgr.getVendorPartNoInfoByID(vendorPartNoInfo.getSeqId().toString());
			if(vendorPartNoInfo == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			
			int i = mgr.auditDisAgreeCustPartNoInfo(vendorPartNoInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), auditDesc);
			if(i != 0)
			{
				logger.info("审批出错！");
				setErrorReason("审批出错！");
				return ERROR;
			}
		
    		if (logger.isDebugEnabled())    logger.debug("end doAuditDisAgree");
    		return SUCCESS;
    	}catch(Exception e){
    		logger.error("catch Exception in doAuditDisAgree.", e);
			setErrorReason("内部错误");
			return ERROR;
    	}
    }
    
    /**
     * 审批
     * @action.input 	custPartNoInfo.seqId
	 * @action.input audit	审批结果 0：审批不通过；1：审批通过
	 * @action.input	auditDesc	审批意见
     * @return
     */
    public String doAudit()
    {
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
     * 获取客户物料关系
     * @action.input	vendorPartNoInfo.seqId
     * @action.result	vendorPartNoInfo.*
     * @return
     */
   public String doGetInfo() {
    	try {
    		VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
    		if(vendorPartNoInfo == null || vendorPartNoInfo.getSeqId() == null) {
    			logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
    		}
    		setResult("vendorPartNoInfo", mgr.getVendorPartNoInfoByID(vendorPartNoInfo.getSeqId().toString()));
    		return SUCCESS;
    	} catch(Exception e) {
    		logger.error("catch Exception in doGetInfo.", e);
			setErrorReason("内部错误");
			return ERROR;
    	}
    }
    
	/**
	 * 检查供应商编码是否填写。如果出现问题，本函数内设置了ErrorReaseon。
	 * @return
	 */
	protected boolean checkCommonFields()
	{
		try{
			if(vendorPartNoInfo == null)
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return false;
			}
			
			if(StringUtils.isEmpty(vendorPartNoInfo.getCommCode()))
			{
				logger.info("供应商编码没有填写！");
				setErrorReason("供应商编码没有填写！");
				return false;
			}
			
			return true;
		}catch(Exception e){
			logger.error("catch Exception in checkCommonFields", e);
			setErrorReason("内部错误");
			return false;
		}
	}

	protected void fixCommCode()
	{
		try {
			if(vendorPartNoInfo == null)
				return;
			if(StringUtils.isEmpty(vendorPartNoInfo.getCommCode()))
			{
				String s = this.getHttpServletRequest().getParameter("vendorInfo.commCode");
				if(StringUtils.isNotEmpty(s))
				{
					vendorPartNoInfo.setCommCode(s);
				}
			}
		} catch (Exception e) {
			logger.error("catch Exception in fixCommCode", e);
		}
	}
}
