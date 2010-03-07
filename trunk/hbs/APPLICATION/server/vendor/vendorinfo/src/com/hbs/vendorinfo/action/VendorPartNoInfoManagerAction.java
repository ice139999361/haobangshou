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
 * ��Ӧ�����Ϲ�ϵ�����û�Action
 * @author xyf
 * @actions doAuditAgree doAuditDisAgree doList
 */
@SuppressWarnings("serial")
public class VendorPartNoInfoManagerAction extends BaseAction {

	/**
	 * Manager��
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
     * ��ѯ��Ӧ�����Ϲ�ϵ
 	 * @action.input vendorPartNoInfo.commCode + vendorPartNoInfo.��ѯ����
	 * @action.result list���б� count������
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
			setErrorReason("�ڲ�����");
			return ERROR;
    	}
    }

    /**
     * ����ͬ��
     * @action.input 	vendorPartNoInfo.*
	 * @action.input	auditDesc	�������
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
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
    		vendorPartNoInfo = mgr.getVendorPartNoInfoByID(vendorPartNoInfo.getSeqId().toString());
			if(vendorPartNoInfo == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			int i = mgr.auditAgreeCustPartNoInfo(vendorPartNoInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), auditDesc);
			if(i != 0)
			{
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
		
    		if (logger.isDebugEnabled())    logger.debug("end doAuditAgree");
    		return SUCCESS;
    	}catch(Exception e){
    		logger.error("catch Exception in doAuditAgree.", e);
			setErrorReason("�ڲ�����");
			return ERROR;
    	}
    }
    
    /**
     * ������ͬ��
     * @action.input 	vendorPartNoInfo.*
	 * @action.input	auditDesc	�������
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
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
    		vendorPartNoInfo = mgr.getVendorPartNoInfoByID(vendorPartNoInfo.getSeqId().toString());
			if(vendorPartNoInfo == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			
			int i = mgr.auditDisAgreeCustPartNoInfo(vendorPartNoInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), auditDesc);
			if(i != 0)
			{
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
		
    		if (logger.isDebugEnabled())    logger.debug("end doAuditDisAgree");
    		return SUCCESS;
    	}catch(Exception e){
    		logger.error("catch Exception in doAuditDisAgree.", e);
			setErrorReason("�ڲ�����");
			return ERROR;
    	}
    }
    
    /**
     * ����
     * @action.input 	custPartNoInfo.seqId
	 * @action.input audit	������� 0��������ͨ����1������ͨ��
	 * @action.input	auditDesc	�������
     * @return
     */
    public String doAudit()
    {
		try {
			String audit = this.getHttpServletRequest().getParameter("audit");
			if(audit == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			if(audit.equals("0")) {
				return doAuditDisAgree();
			} else {
				return doAuditAgree();
			}
		} catch(Exception e) {
			logger.error("catch Exception in doAudit", e);
			setErrorReason("�ڲ�����");
            return ERROR;
		}
    }
    
   /**
     * ��ȡ�ͻ����Ϲ�ϵ
     * @action.input	vendorPartNoInfo.seqId
     * @action.result	vendorPartNoInfo.*
     * @return
     */
   public String doGetInfo() {
    	try {
    		VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
    		if(vendorPartNoInfo == null || vendorPartNoInfo.getSeqId() == null) {
    			logger.info("��������");
				setErrorReason("��������");
				return ERROR;
    		}
    		setResult("vendorPartNoInfo", mgr.getVendorPartNoInfoByID(vendorPartNoInfo.getSeqId().toString()));
    		return SUCCESS;
    	} catch(Exception e) {
    		logger.error("catch Exception in doGetInfo.", e);
			setErrorReason("�ڲ�����");
			return ERROR;
    	}
    }
    
	/**
	 * ��鹩Ӧ�̱����Ƿ���д������������⣬��������������ErrorReaseon��
	 * @return
	 */
	protected boolean checkCommonFields()
	{
		try{
			if(vendorPartNoInfo == null)
			{
				logger.info("��������");
				setErrorReason("��������");
				return false;
			}
			
			if(StringUtils.isEmpty(vendorPartNoInfo.getCommCode()))
			{
				logger.info("��Ӧ�̱���û����д��");
				setErrorReason("��Ӧ�̱���û����д��");
				return false;
			}
			
			return true;
		}catch(Exception e){
			logger.error("catch Exception in checkCommonFields", e);
			setErrorReason("�ڲ�����");
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
