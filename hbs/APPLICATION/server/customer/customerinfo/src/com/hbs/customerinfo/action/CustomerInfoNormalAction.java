/**
 * �ͻ���ϢAction
 */
package com.hbs.customerinfo.action;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.common.josn.JSONException;
import com.hbs.common.josn.JSONUtil;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerinfo.action.FieldErr;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;

/**
 * ��ͨ��ɫ�ͻ���ϢAction
 * @author xyf
 * @actions doList doGetInfo doSaveTemp doSave
 */
@SuppressWarnings("serial")
public class CustomerInfoNormalAction extends BaseAction {

	/**
	 * Manager��
	 */
	static final String custInfoMgrName = "customerInfoMgr";
	
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
	
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustomerInfoNormalAction.class);

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
		if(true)
			return;
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
			logger.debug("begin doList");
			if(custInfo == null)
			{
				custInfo = new CustomerInfo();
			}
			setPagination(custInfo);
			setMyId(false);
			CustomerInfoMgr mgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean(custInfoMgrName);
			setResult("list", mgr.getCustomerInfoList(custInfo));
			setTotalCount(mgr.getCustomerInfoCount(custInfo));
			setResult("count", getTotalCount());
			logger.debug("end doList");
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
	 * ��ʱ�����û���Ϣ
	 * @action.input custInfo.*
	 * @return
	 */
	public String doSaveTemp()
	{
		try
		{
			logger.debug("begin doSaveTemp");
			
			if(custInfo == null)
			{
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;				
			}

			custInfo.setState("1");
			if(CustomerInfoUtil.checkSetStaffId(custInfo))
				setMyId(true);
			processListData();
			List<FieldErr> errs = CustomerInfoUtil.checkInputFields(custInfo);
			if(!errs.isEmpty())
			{
				String s = CustomerInfoUtil.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			
			CustomerInfoMgr mgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean(custInfoMgrName);
			
			CustomerInfo info2 = mgr.getCustomerInfo(custInfo, false);
			int ret;
			if(info2 != null)
				ret = mgr.updateCustomerInfo(custInfo, getLoginStaff().getStaffId(), getLoginStaff().getStaffName());
			else
				ret = mgr.saveTempCustomerInfo(custInfo);
			if(ret != 0)
			{
				logger.info("��ʱ�������");
				setErrorReason("��ʱ�������");
				return ERROR;
			}
			logger.debug("end doSaveTemp");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doSaveTemp", e);
			setErrorReason("�ڲ�����");
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
			logger.debug("begin doSave");
			
			if(custInfo == null)
			{
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;				
			}

			if(custInfo.getState() == null || custInfo.getState() == "")
				custInfo.setState("2");
			if(CustomerInfoUtil.checkSetStaffId(custInfo))
				setMyId(true);
			processListData();			
			List<FieldErr> errs = CustomerInfoUtil.checkInputFields(custInfo);
			if(!errs.isEmpty())
			{
				String s = CustomerInfoUtil.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			
			CustomerInfoMgr mgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean(custInfoMgrName);
			
			CustomerInfo info2 = mgr.getCustomerInfo(custInfo, false);
			int ret;
			if(info2 != null)
				ret = mgr.updateCustomerInfo(custInfo, getLoginStaff().getStaffId(), getLoginStaff().getStaffName());
			else
			{
				custInfo.setState("1");
				ret = mgr.commitCustomerInfo(custInfo, getLoginStaff().getStaffId(), getLoginStaff().getStaffName());
			}
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
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			logger.debug("end doSave");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doSave", e);
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
			logger.debug("begin doSave");
			if(!CustomerInfoUtil.checkKeyFields(custInfo))
			{
				logger.info("����Ϊ�գ�");
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			setMyId(false);
			CustomerInfoMgr mgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean(custInfoMgrName);
			custInfo = mgr.getCustomerInfo(custInfo, true);
			this.setResult("custInfo", custInfo);
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("�ڲ�����");
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
