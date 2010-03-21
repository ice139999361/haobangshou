/**
 * 
 */
package com.hbs.customerinfo.action;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.customerinfo.manager.CustPartNoInfoMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;

/**
 * �ͻ����Ϲ�ϵ�����û�Action
 * @author xyf
 * @actions doAuditAgree doAuditDisAgree doList
 */
@SuppressWarnings("serial")
public class CustPartNoInfoManagerAction extends BaseAction {

	/**
	 * Manager��
	 */
	static final String custPartNoInfoMgrName = "custPartNoInfoMgr";
		
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustPartNoInfoNormalAction.class);

    CustPartNoInfo custPartNoInfo;
    public CustPartNoInfo getCustPartNoInfo() { return custPartNoInfo; }
    public void setCustPartNoInfo(CustPartNoInfo custPartNoInfo) { this.custPartNoInfo = custPartNoInfo; }
    
	String auditDesc;
	public String getAuditDesc() { return auditDesc; }
	public void setAuditDesc(String a) { auditDesc = a; }

    /**
     * ��ѯ�ͻ����Ϲ�ϵ
 	 * @action.input custPartNoInfo.commCode + custPartNoInfo.��ѯ����
	 * @action.result list���б� count������
     * @return
     */
    public String doList()
    {
    	try
    	{
			if (logger.isDebugEnabled())    logger.debug("begin doList");

			if(custPartNoInfo == null)
				custPartNoInfo = new CustPartNoInfo();

			/*
			// ȱʡ��ѯ��ʽ����
			if(StringUtils.isEmpty(custPartNoInfo.getState()))
				custPartNoInfo.setState("0");
			*/
			
			if(!checkCommonFields())
				return ERROR;
			
			setPagination(custPartNoInfo);
			CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
			setResult("list", mgr.listCustPartNoInfo(custPartNoInfo));
			setTotalCount(mgr.listCustPartNoInfoCount(custPartNoInfo));
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
     * @action.input 	custPartNoInfo.*
	 * @action.input	auditDesc	�������
     * @return
     */
    public String doAuditAgree()
    {
    	try{
    		if (logger.isDebugEnabled())    logger.debug("begin doAuditAgree");
    		
			CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
    		
			/*
			if(!checkCommonFields())
				return ERROR;
			List<FieldErr> errs = CustPartNoInfoUtil.checkInputFields(custPartNoInfo);
			if(errs.isEmpty())
			{
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			
			*/
			if(custPartNoInfo == null || custPartNoInfo.getSeqId() == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
    		custPartNoInfo = mgr.getCustPartNoInfoByID(custPartNoInfo.getSeqId().toString());
			if(custPartNoInfo == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			
			int i = mgr.auditAgreeCustPartNoInfo(custPartNoInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), auditDesc);
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
     * @action.input 	custPartNoInfo.*
	 * @action.input	auditDesc	�������
     * @return
     */
    public String doAuditDisAgree()
    {
    	try{
    		if (logger.isDebugEnabled())    logger.debug("begin doAuditDisAgree");
    		
			CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
			
    		/*
    		if(!checkCommonFields())
				return ERROR;
			List<FieldErr> errs = CustPartNoInfoUtil.checkInputFields(custPartNoInfo);
			if(errs.isEmpty())
			{
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			*/
    		
			if(custPartNoInfo == null || custPartNoInfo.getSeqId() == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
    		custPartNoInfo = mgr.getCustPartNoInfoByID(custPartNoInfo.getSeqId().toString());
			if(custPartNoInfo == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			
			int i = mgr.auditDisAgreeCustPartNoInfo(custPartNoInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), auditDesc);
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
     * @action.input	custPartNoInfo.seqId
     * @action.result	custPartNoInfo.*
     * @return
     */
   public String doGetInfo() {
    	try {
    		CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
    		if(custPartNoInfo == null || custPartNoInfo.getSeqId() == null) {
    			logger.info("��������");
				setErrorReason("��������");
				return ERROR;
    		}
    		setResult("custPartNoInfo", mgr.getCustPartNoInfoByID(custPartNoInfo.getSeqId().toString()));
    		return SUCCESS;
    	} catch(Exception e) {
    		logger.error("catch Exception in doGetInfo.", e);
			setErrorReason("�ڲ�����");
			return ERROR;
    	}
    }
    
	/**
	 * ���ͻ������Ƿ���д������������⣬��������������ErrorReaseon��
	 * @return
	 */
	protected boolean checkCommonFields()
	{
		try{
			if(custPartNoInfo == null)
			{
				logger.info("��������");
				setErrorReason("��������");
				return false;
			}
			
			if(StringUtils.isEmpty(custPartNoInfo.getCommCode()))
			{
				logger.info("�ͻ�����û����д��");
				setErrorReason("�ͻ�����û����д��");
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
			if(custPartNoInfo == null)
				return;
			if(StringUtils.isEmpty(custPartNoInfo.getCommCode()))
			{
				String s = this.getHttpServletRequest().getParameter("custInfo.commCode");
				if(StringUtils.isNotEmpty(s))
				{
					custPartNoInfo.setCommCode(s);
				}
			}
		} catch (Exception e) {
			logger.error("catch Exception in fixCommCode", e);
		}
	}
}
