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
 * �����ɫ�ͻ���ϢAction
 * @author xyf
 * @actions doAuditAgree doAuditDisAgree doList doGetInfo
 */
@SuppressWarnings("serial")
public class CustomerInfoManagerAction extends BaseAction {
	
	/**
	 * Manager��
	 */
	static final String custInfoMgrName = "customerInfoMgr";
		
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustomerInfoManagerAction.class);

    /**
	 * ������״̬��state��Ҫ�ַ�������Constants�����Ͳ�ƥ�䡣����Ҳ��ֱ�ۡ�
	 */
	static final String stateForAudit = "2";
	
	public static final String roleName = "scmanager";
	
	CustomerInfo custInfo;
	
	/**
	 * ��ȡ�ͻ���Ϣ
	 * @return �ͻ���Ϣ
	 */
	public CustomerInfo getCustInfo(){ return custInfo;}
	
	/**
	 * ���ÿͻ���Ϣ
	 * @param a �ͻ���Ϣ
	 */
	public void setCustInfo(CustomerInfo a) { this.custInfo = a; }
	
	String auditDesc;
	
	/**
	 * ��ȡ�������
	 * @return �������
	 */
	public String getAuditDesc() { return auditDesc; }
	
	/**
	 * �����������
	 * @param a �������
	 */
	public void setAuditDesc(String a) { auditDesc = a; }
	
	/**
	 * ����
	 * @action.input 
	 *	custInfo.baseSeqId �� (custInfo.commCode + custInfo.state)
	 * @action.input audit	������� 0��������ͨ����1������ͨ��
	 * @action.input	auditDesc �������
	 * @return
	 */
	public String doAudit() {
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
	 * ����ͬ��
	 * @action.input 
	 *	custInfo.baseSeqId �� (custInfo.commCode + custInfo.state)
	 * @action.input	auditDesc �������
	 * @return
	 */
	public String doAuditAgree()
	{
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doAuditAgree");
			
			if(!CustomerInfoUtil.checkKeyFields(custInfo))
			{
				logger.info("��������");
				setErrorReason("��������");
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
					s = "�޴�״̬��";
					break;
				case 2:
					s = "״̬����ȷ��";
					break;
				default:
					s = "�������";
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
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}
	
	/**
	 * ������ͬ��
	 * @action.input 
	 *	custInfo.baseSeqId �� (custInfo.commCode + custInfo.state)
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
				logger.info("��������");
				setErrorReason("��������");
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
					s = "�޴�״̬��";
					break;
				case 2:
					s = "״̬����ȷ��";
					break;
				default:
					s = "�������";
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
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}

	/**
	 * ��ѯ������mgr.getCustomerInfoList��
	 * @action.input custInfo.��ѯ����
	 * @action.result list���б� count������
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
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}
	
	/** autocomplete ���ʹ��  
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
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}
	
	/**
	 * ��ѯ����������
	 * @action.input ��
	 * @action.result list���б� count������
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
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}
	
	/**
	 * ��ȡ�ͻ���ϸ��Ϣ
	 * @action.input 
	 *	custInfo.baseSeqId �� (custInfo.commCode + custInfo.state)
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
				logger.info("��������");
				setErrorReason("��������");
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
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}
	
	public String doUpdateSales(){
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doUpdateSales");
			
			if(!CustomerInfoUtil.checkKeyFields(custInfo))
			{
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			String staffId = custInfo.getStaffId();
			String assStaffId = custInfo.getAssStaffId();
			if(StringUtils.isEmpty(staffId) && StringUtils.isEmpty(assStaffId)){
				logger.error("�����������Ա��ҵ������Ϊ�գ�");
				setErrorReason("�����������Ա��ҵ������Ϊ�գ���ѡ��");
				return ERROR;
			}
			if(StringUtils.isNotEmpty(staffId)){				
				Staff u = StaffUtil.getStaffById(staffId);
				if(u != null){
					custInfo.setStaffName(u.getStaffName());
				}else{
					logger.error("�޷��ҵ���Ӧ��������Ա��");
					setErrorReason("�޷��ҵ���Ӧ��������Ա��");
					return ERROR;
				}					
			}
			
			if(StringUtils.isNotEmpty(assStaffId)){				
				Staff u = StaffUtil.getStaffById(assStaffId);
				if(u != null){
					custInfo.setAssStaffName(u.getStaffName());
				}else{
					logger.error("�޷��ҵ���Ӧ��ҵ������");
					setErrorReason("�޷��ҵ���Ӧ��ҵ������");
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
			setErrorReason("�޸Ŀͻ���Ϣ��Ӧ�����ۻ�ҵ���������!");
            return ERROR;
		}
	}
	
	/**
	 * ����custInfo�Ĳ�����Ϣ��ȡȫ����Ϣ
	 * @param mgr
	 * @throws Exception
	 */
	protected void getCustInfoValue(CustomerInfoMgr mgr) throws Exception
	{
		custInfo = CustomerInfoUtil.getCustomerInfo(mgr, custInfo);
	}
}
