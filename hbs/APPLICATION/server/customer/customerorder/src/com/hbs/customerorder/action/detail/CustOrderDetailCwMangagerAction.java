/**
 * 
 */
package com.hbs.customerorder.action.detail;

import org.apache.log4j.Logger;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class CustOrderDetailCwMangagerAction extends CustOrderDetailBaseAction {
	/**
	 * logger.
	 */
	protected static Logger logger = Logger.getLogger(CustOrderDetailCgNormalAction.class);

	/**
	 * 领导审批同意订单明细的预付x%，款到发货，款未到发货
	 * @action.input orderDetail.*
	 * @action.input memo 原因
	 * @return
	 */
	public String doAgreeDetailFee() {
		try {
			if(!checkKeyFields()) {
				setErrorReason("参数错误！");
				return ERROR;
			}
			int i = mgr.auditAgreeDetailFee(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				setErrorReason("提交出错！");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doAgreeDetailFee", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 领导审批不同意订单明细的预付x%，款到发货，款未到发货
	 * @action.input orderDetail.*
	 * @action.input memo 原因
	 * @return
	 */
	public String doDisAgreeDetailFee() {
		try {
			if(!checkKeyFields()) {
				setErrorReason("参数错误！");
				return ERROR;
			}
			int i = mgr.auditDisAgreeDetailFee(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				setErrorReason("提交出错！");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doDisAgreeDetailFee", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

}
