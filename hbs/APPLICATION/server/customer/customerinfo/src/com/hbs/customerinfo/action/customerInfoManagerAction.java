package com.hbs.customerinfo.action;

import com.hbs.common.action.base.BaseAction;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;

/**
 * �����ɫ�ͻ���ϢAction
 * @author xyf
 * @actions doAuditAgree doAuditDisAgree doList doGetInfo
 */
@SuppressWarnings("serial")
public class CustomerInfoManagerAction extends BaseAction {
	
	/**
	 * ������״̬��state��Ҫ�ַ�������Constants�����Ͳ�ƥ�䡣����Ҳ��ֱ�ۡ�
	 */
	static String stateForAudit = "1";
	
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
	 * ����ͬ��
	 * @action.input 
	 *	custInfo.baseSeqId �� (custInfo.commCode + custInfo.state)
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
					s = "�޴�״̬��";
					break;
				case 2:
					s = "״̬����ȷ��";
					break;
				default:
					s = "�������";
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
			CustomerInfoMgr mgr = new CustomerInfoMgr();
			getCustInfoValue(mgr);
			int ret = mgr.auditDisAgreeCustomerInfo(custInfo, getLoginStaff().getStaffId(), getLoginStaff().getStaffName(), auditDesc);
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
	 * ��ѯ������mgr.getCustomerInfoList��
	 * @action.input custInfo.��ѯ����
	 * @action.result list���б� count������
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
	 * ��ѯ����������
	 * @action.input ��
	 * @action.result list���б� count������
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
	 * ����custInfo�Ĳ�����Ϣ��ȡȫ����Ϣ
	 * @param mgr
	 * @throws Exception
	 */
	protected void getCustInfoValue(CustomerInfoMgr mgr) throws Exception
	{
		custInfo = mgr.getCustomerInfo(custInfo, true);
	}
}
