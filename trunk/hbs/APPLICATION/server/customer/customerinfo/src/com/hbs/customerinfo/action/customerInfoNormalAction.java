/**
 * �ͻ���ϢAction
 */
package com.hbs.customerinfo.action;

import java.lang.reflect.Array;
import java.util.Map;

import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.common.josn.JSONException;
import com.hbs.common.josn.JSONUtil;

/**
 * ��ͨ��ɫ�ͻ���ϢAction
 * @author xyf
 * @actions doList doGetInfo doSaveTemp doSave
 */
@SuppressWarnings("serial")
public class customerInfoNormalAction extends BaseAction {

	/**
	 * ��ϵ���б��ַ���������
	 */
	static final String contactListName1 = "contactList1";
	/**
	 * �ջ����б��ַ���������
	 */
	static final String contactListName2 = "contactList2";
	/**
	 * �����б��ַ���������
	 */
	static final String bankListName = "bankList";
	
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
	
	/**
	 * �����ϴ���List���ݡ���String����ת��ΪList
	 */
	protected void processListData() throws Exception
	{
		// TODO: �����ϴ���List����
		try
		{
			String s = null;
			@SuppressWarnings("unused")
			Object o = null;
			
			Object aa = custInfo.getListContactInfo();
			s = this.getHttpServletRequest().getParameter(contactListName1);
			o = JSONUtil.deserialize(s);
			for(int i = 0; i < Array.getLength(o); i++)
			{
				Map item = (Map)Array.get(o, i);
				
			}
		}
		catch(JSONException e)
		{
			throw new Exception("�б����ݸ�ʽ����", e);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * ��ѯ���޶��Լ��ܹ���Ŀͻ���Ϣ������mgr.getCustomerInfoListByUser��
	 * @action.input custInfo.��ѯ����
	 * @action.result list���б� count������
	 * @return
	 */
	public String doList()
	{
		try
		{
			setPagination(custInfo);
			setMyId(false);
			CustomerInfoMgr mgr = new CustomerInfoMgr();
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
	 * ��ʱ�����û���Ϣ
	 * @action.input custInfo.*
	 * @return
	 */
	public String doSaveTemp()
	{
		try
		{
			//processListData();
			int userid = 0;
			try
			{
				String s = custInfo.getStaffId();
				userid = Integer.parseInt(s);
			}
			catch(NumberFormatException e)
			{
				userid = 0;
			}
			if(userid == 0)
				setMyId(true);
			CustomerInfoMgr mgr = new CustomerInfoMgr();
			int ret = mgr.saveTempCustomerInfo(custInfo);
			if(ret != 0)
			{
				setErrorReason("��ʱ�������");
				return ERROR;
			}
			return SUCCESS;
		}
		catch(Exception e)
		{
			setErrorReason(e.getMessage(), e);
            return ERROR;
		}
	}
	
	/**
	 * �����û���Ϣ�����ڲ�ͬ��״̬�����в�ͬ�Ĳ���
	 * @action.input custInfo.*
	 * @return
	 */
	public String doSave()
	{
		try
		{
			//processListData();
			CustomerInfoMgr mgr = new CustomerInfoMgr();
			int userid = 0;
			try
			{
				String s = custInfo.getStaffId();
				userid = Integer.parseInt(s);
			}
			catch(NumberFormatException e)
			{
				userid = 0;
			}
			if(userid == 0)
				setMyId(true);
			int ret = mgr.updateCustomerInfo(custInfo, getLoginStaff().getStaffId(), getLoginStaff().getStaffName());
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
			setMyId(false);
			CustomerInfoMgr mgr = new CustomerInfoMgr();
			custInfo = mgr.getCustomerInfo(custInfo, true);
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
	 * ����STAFF��ϢΪ��ǰ�û���Ϣ
	 * @param setName �Ƿ������û�����Ϊtrueʱ����staffNameΪ��ǰ�û���staffName��Ϊfalseʱ����staffNameΪnull��
	 * �ڲ�ѯʱ��Ϊfalse����������ʱ��Ϊtrue��
	 * @throws Exception
	 */
	protected void setMyId(boolean setName) throws Exception
	{
		custInfo.setStaffId(getLoginStaff().getStaffId());
		custInfo.setStaffName(setName ? getLoginStaff().getStaffName() : null);
	}
	
}
