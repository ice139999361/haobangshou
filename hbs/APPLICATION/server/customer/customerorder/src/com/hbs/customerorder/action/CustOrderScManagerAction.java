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
public class CustOrderScManagerAction extends BaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(CustOrderScManagerAction.class);

	public static final String roleName = "scmanager";

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
	 * 查询
	 * @action.input custOrder.查询条件
	 * @action.result list：列表 count：总数
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
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 获取客户订单信息
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
				setErrorReason("参数为空！");
				return ERROR;
			}
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			setResult("custOrder", mgr.findCustomerOrderByBizKey(custOrder, true));
			logger.debug("end doGetInfo");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 经理审批通过超过账期最大金额的客户订单，审批结果为通过
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input memo	审批说明
	 * @action.result
	 * @return
	 */
	public String doAuditAgree() {
		try{
			logger.debug("begin doAuditAgree");
			if(custOrder == null
					|| StringUtils.isEmpty(custOrder.getCommCode()) 
					|| StringUtils.isEmpty(custOrder.getPoNo())) {
				setErrorReason("参数为空！");
				return ERROR;
			}
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			String memo = this.getHttpServletRequest().getParameter("memo");
			int i = mgr.auditAgreeCustOrder(custOrder, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), memo);
			if(i != 0) {
				logger.info("审批订单出错！");
				setErrorReason("审批订单出错！");
				return ERROR;
			}
			logger.debug("end doAuditAgree");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doAuditAgree", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 经理审批不通过超过账期最大金额的客户订单，审批结果为不通过
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input memo	审批说明
	 * @action.result
	 * @return
	 */
	public String doAuditDisAgree() {
		try{
			logger.debug("begin doAuditAgree");
			if(custOrder == null
					|| StringUtils.isEmpty(custOrder.getCommCode()) 
					|| StringUtils.isEmpty(custOrder.getPoNo())) {
				setErrorReason("参数为空！");
				return ERROR;
			}
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			String memo = this.getHttpServletRequest().getParameter("memo");
			int i = mgr.auditDisAgreeCustOrder(custOrder, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), memo);
			if(i != 0) {
				logger.info("审批订单出错！");
				setErrorReason("审批订单出错！");
				return ERROR;
			}
			logger.debug("end doAuditAgree");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doAuditAgree", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
}
