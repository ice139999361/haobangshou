/**
 * 
 */
package com.hbs.vendorinfo.action;

import java.util.List;

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
			
			VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)BeanLocator.getInstance().getBean(vendorPartNoInfoMgrName);
			int i = mgr.auditAgreeCustPartNoInfo(vendorPartNoInfo, getLoginStaff().getStaffId(), getLoginStaff().getStaffName(), auditDesc);
			if(i != 0)
			{
				logger.info("����������");
				setErrorReason("����������");
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
			
			VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)BeanLocator.getInstance().getBean(vendorPartNoInfoMgrName);
			int i = mgr.auditDisAgreeCustPartNoInfo(vendorPartNoInfo, getLoginStaff().getStaffId(), getLoginStaff().getStaffName(), auditDesc);
			if(i != 0)
			{
				logger.info("����������");
				setErrorReason("����������");
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
			
			String commCode = vendorPartNoInfo.getCommCode();
			if(commCode == null || commCode.length() == 0)
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
}