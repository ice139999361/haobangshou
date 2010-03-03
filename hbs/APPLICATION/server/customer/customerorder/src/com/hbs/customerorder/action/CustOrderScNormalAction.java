/**
 * 
 */
package com.hbs.customerorder.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.manager.CustOrderMgr;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.customer.order.pojo.CustomerOrder;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class CustOrderScNormalAction extends BaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(CustOrderScNormalAction.class);

	public static final String roleName = "scnormal";

	CustomerOrder custOrder;

	/**
	 * @return the custOrder
	 */
	public CustomerOrder getCustOrder() {
		return custOrder;
	}

	/**
	 * @param custOrder the custOrder to set
	 */
	public void setCustOrder(CustomerOrder custOrder) {
		this.custOrder = custOrder;
	}
	
	/**
	 * ��ʱ����
	 * @action.input custOrder.*
	 * @action.result 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String doSaveTemp()
	{
		try {
			logger.debug("begin doSaveTemp " + custOrder);

			if (custOrder == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			
			if(StringUtils.isEmpty(custOrder.getPoNoType()))
				custOrder.setPoNoType("0");
			
			custOrder.setState("1");
			if(custOrder.getFristCreateTime() == null)
				custOrder.setFristCreateTime(new Date());
			if(StringUtils.isEmpty(custOrder.getStaffId()))
			{
				custOrder.setStaffId(getLoginStaff().getStaffId().toString());
				custOrder.setStaffName(getLoginStaff().getStaffName());
			}
			
			// DONE:listdata�����
			Map otherData = new HashMap();
			if(!CustOrderUtil.checkCommCode(custOrder, otherData)) {
				logger.info("�ͻ��������");
				setErrorReason("�ͻ��������");
				return ERROR;
			}
			CustOrderUtil.processListData(custOrder, this.getHttpServletRequest(), otherData);
			List<FieldErr> errs = CustOrderUtil.checkInputFields(custOrder, otherData);
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			CustomerOrder custOrder2 = null;
			try {
				custOrder2 = mgr.findCustomerOrderByBizKey(custOrder, false);
			} catch(Exception e) {
				
			}
			int ret = -1;
			try {
				for(CustOrderDetail info : custOrder.getOrderDetailList()) {
					info.setDeliveryAmount(0);
					info.setSelfDeliveryAmount(0);
					info.setCommDeliveryAmount(0);
				}
				if(custOrder2 == null)
				{
					ret = mgr.saveTempCustomerOrder(custOrder);
				}
				else
					ret = mgr.updateTempCustomerOrder(custOrder);
			} catch (Exception e) {
				logger.error("catch Exception in doSaveTemp", e);
			}
			
			if (ret != 0) {
				logger.info("��ʱ�������");
				setErrorReason("��ʱ�������");
				return ERROR;
			}
			setResult("state", "1");
			this.setAlertMsg("��ʱ����ɹ���");
			if (logger.isDebugEnabled())
				logger.debug("end doSaveTemp");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doSaveTemp", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	

	/**
	 * ��ʽ�ύ
	 * @action.input custOrder.*
	 * @action.result 
	 * @return
	 */
	public String doCommit() {
		try{
			logger.debug("begin doCommit");
			String ret = doSaveTemp();
			if(ret.equals(SUCCESS)) {
				CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
				int i = mgr.commitCustomerOrder(custOrder);
				if(i != 0) {
					logger.info("�ύ����");
					setErrorReason("�ύ����");
					return ERROR;
				}
			}
			logger.debug("end doCommit");
			return ret;
		}catch(Exception e) {
			logger.error("catch Exception in doCommit", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}		
	}
	
	/**
	 * ��ѯ���޶��Լ��ܹ���Ŀͻ���Χ��
	 * @action.input custOrder.��ѯ����
	 * @action.result list���б� count������
	 * @return
	 */
	public String doList() {
		try {
			logger.debug("begin doList");
			if(custOrder == null)
				custOrder = new CustomerOrder();
			setPagination(custOrder);
			setMyId();
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			setResult("list", mgr.listCustomerOrder(custOrder));
			setTotalCount(mgr.listCustomerOrderCount(custOrder));
			setResult("count", getTotalCount());
			logger.debug("end doList");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ��ȡ�ͻ�������Ϣ
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.result custOrder.*
	 * @return
	 */
	public String doGetInfo() {
		try{
			logger.debug("begin doGetInfo");
			if(custOrder == null
					|| StringUtils.isEmpty(custOrder.getCommCode()) 
					|| StringUtils.isEmpty(custOrder.getPoNo())) {
				logger.debug("����Ϊ�գ�");
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			CustomerOrder custOrder2 = mgr.findCustomerOrderByBizKey(custOrder, true);
			if(custOrder2 == null) {
				logger.debug("û���ҵ�");
				setErrorReason("û���ҵ�");
				return ERROR;
			} else if(custOrder2.getStaffId().equals(getLoginStaff().getStaffId().toString())) {
				setResult("custOrder", custOrder2);
				logger.debug("end doGetInfo");
				return SUCCESS;
			}
			logger.debug("Ȩ�޴���");
			setErrorReason("Ȩ�޴���");
			return ERROR;
		}catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * ȡ���ͻ�����
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input memo	ȡ��˵��
	 * @action.result custOrder.*
	 * @return
	 */
	public String doCancel() {
		try{
			logger.debug("begin doCancel");
			if(custOrder == null
					|| StringUtils.isEmpty(custOrder.getCommCode()) 
					|| StringUtils.isEmpty(custOrder.getPoNo())) {
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			String cancelContent = this.getHttpServletRequest().getParameter("memo");
			int i = mgr.cancelCustOrder(custOrder, cancelContent);
			if(i != 0) {
				logger.info("ȡ����������");
				setErrorReason("ȡ����������");
				return ERROR;
			}
			logger.debug("end doCancel");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doCancel", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	private void setMyId() throws Exception {
		custOrder.setStaffId(getLoginStaff().getStaffId().toString());
	}
}
