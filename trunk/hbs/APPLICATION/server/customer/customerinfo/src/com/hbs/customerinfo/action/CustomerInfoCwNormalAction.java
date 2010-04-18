/**
 * 
 */
package com.hbs.customerinfo.action;

import org.apache.log4j.Logger;

import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.domain.invoice.pojo.PeriodSpecMemo;
import com.hbs.invoice.manager.PeriodSpecMemoMgr;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class CustomerInfoCwNormalAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(CustomerInfoCwNormalAction.class);
	
	public static final String roleName = "cwnormal";
	
	CustomerInfo custInfo;
	
	public CustomerInfo getCusInfo() {
		return custInfo;
	}
	
	public void setCustInfo(CustomerInfo a) {
		this.custInfo = a;
	}
	
	/**
	 * ��ѯ
	 * @action.input custInfo.��ѯ����
	 * @action.result list���б� count������
	 * @return
	 */
	public String doList() {
		try {
			if (logger.isDebugEnabled())
				logger.debug("begin doList");
			if (custInfo == null) {
				custInfo = new CustomerInfo();
			}
			setPagination(custInfo);
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(CustomerInfoNormalAction.custInfoMgrName);
			custInfo.setField("noState01", true);
			setResult("list", mgr.getCustomerInfoList(custInfo));
			setTotalCount(mgr.getCustomerInfoCount(custInfo));
			setResult("count", getTotalCount());
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeCustState, roleName));
			if (logger.isDebugEnabled())
				logger.debug("end doList");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doList.", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ��ȡ��Ӧ����ϸ��Ϣ
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
			custInfo.setState("0");
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(CustomerInfoNormalAction.custInfoMgrName);
			getCustomerInfoValue(mgr);
			if(!"0".equals(custInfo.getState())){
				logger.info("״̬����");
				setErrorReason("״̬����");
				return ERROR;
			}
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
	
	/**
	 * ��������
	 * @action.input 
	 *	custInfo.baseSeqId �� (custInfo.commCode + custInfo.state)
	 * @action.input memo ˵��
	 * @return
	 */
	public String doLock(){
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doLock");
			if(!CustomerInfoUtil.checkKeyFields(custInfo))
			{
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(CustomerInfoNormalAction.custInfoMgrName);
			int i = mgr.lockCustomerInfo(custInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doLock", e);
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}

	/**
	 * �������
	 * @action.input 
	 *	custInfo.baseSeqId �� (custInfo.commCode + custInfo.state)
	 * @action.input memo ˵��
	 * @return
	 */
	public String doUnlock(){
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doUnlock");
			if(!CustomerInfoUtil.checkKeyFields(custInfo))
			{
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(CustomerInfoNormalAction.custInfoMgrName);
			int i = mgr.unlockCustomerInfo(custInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doUnlock", e);
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}

	protected String getMemo() {
		return this.getHttpServletRequest().getParameter("memo");
	}
	
	/**
	 * ����custInfo�Ĳ�����Ϣ��ȡȫ����Ϣ
	 * @param mgr
	 * @throws Exception
	 */
	protected void getCustomerInfoValue(CustomerInfoMgr mgr) throws Exception
	{
		custInfo = CustomerInfoUtil.getCustomerInfo(mgr, custInfo);
		if(null != custInfo){
			PeriodSpecMemoMgr specMemoMgr =(PeriodSpecMemoMgr)BeanLocator.getInstance().getBean("periodSpecMemoMgr");
			
			PeriodSpecMemo memo = specMemoMgr.getSepcMemo(custInfo.getCommCode());
			if(null !=memo){
				custInfo.setSpecMemo(memo.getMemo());
			}
			
		}
	}
}
