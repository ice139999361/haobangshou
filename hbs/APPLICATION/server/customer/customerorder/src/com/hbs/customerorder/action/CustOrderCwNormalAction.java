/**
 * 
 */
package com.hbs.customerorder.action;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.manager.CustOrderMgr;
import com.hbs.domain.customer.order.pojo.CustomerOrder;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class CustOrderCwNormalAction extends BaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(CustOrderCwNormalAction.class);

	public static final String roleName = "cwnormal";

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
	 * ��ѯ
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
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			setResult("custOrder", mgr.findCustomerOrderByBizKey(custOrder, true));
			logger.debug("end doGetInfo");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ����ȷ���˿ͻ�������Ԥ������ɹ�����
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input memo	����˵��
	 * @action.result
	 * @return
	 */
	public String doFinanceAgree() {
		try{
			logger.debug("begin doAuditAgree");
			if(custOrder == null
					|| StringUtils.isEmpty(custOrder.getCommCode()) 
					|| StringUtils.isEmpty(custOrder.getPoNo())) {
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			String memo = this.getHttpServletRequest().getParameter("memo");
			int i = mgr.financeAgreeCustOrder(custOrder, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), memo);
			if(i != 0) {
				logger.info("����������");
				setErrorReason("����������");
				return ERROR;
			}
			logger.debug("end doAuditAgree");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doAuditAgree", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * �����˻ش�����Ԥ����ȷ�Ͽͻ���������ҵ��������
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input memo	����˵��
	 * @action.result
	 * @return
	 */
	public String doFinanceDisAgree() {
		try{
			logger.debug("begin doAuditAgree");
			if(custOrder == null
					|| StringUtils.isEmpty(custOrder.getCommCode()) 
					|| StringUtils.isEmpty(custOrder.getPoNo())) {
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			String memo = this.getHttpServletRequest().getParameter("memo");
			int i = mgr.financeDisAgreeCustOrder(custOrder, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), memo);
			if(i != 0) {
				logger.info("����������");
				setErrorReason("����������");
				return ERROR;
			}
			logger.debug("end doAuditAgree");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doAuditAgree", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}}
