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
public class CustOrderDetailCwNormalAction extends CustOrderDetailBaseAction {
	/**
	 * logger.
	 */
	protected static Logger logger = Logger.getLogger(CustOrderDetailCgNormalAction.class);

	/**
	 * 财务确认订单明细的预付x%，款到发货
	 * @action.input orderDetail.*
	 * @action.input memo 取消原因
	 * @return
	 */
	public String doConfirmDetailFee() {
		try {
			if(!findOrderDetail()) {
				return ERROR;
			}
			int i = mgr.financeConfirmDetailFee(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				setErrorReason("提交出错！");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmDetailFee", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 财务申请订单明细的预付x%，款到发货，款未到发货
	 * @action.input orderDetail.*
	 * @action.input memo 取消原因
	 * @return
	 */
	public String doApplyDetailFee() {
		try {
			if(!findOrderDetail()) {
				return ERROR;
			}
			int i = mgr.financeApplyDetailFee(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				setErrorReason("提交出错！");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doApplyDetailFee", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
}
