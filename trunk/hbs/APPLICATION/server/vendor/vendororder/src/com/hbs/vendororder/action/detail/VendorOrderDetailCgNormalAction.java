package com.hbs.vendororder.action.detail;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class VendorOrderDetailCgNormalAction extends VendorOrderDetailBaseAction {
	protected static Logger logger = Logger.getLogger(VendorOrderDetailCgNormalAction.class);

	/**
	 * 确认账期订单明细的交期，只对账期订单明细有效
	 * @action.input orderDetail.*
	 * @action.input memo 原因
	 * @return
	 */
	public String doConfirmDelivery() {
		try {
			if(!checkKeyFields()) {
				logger.debug("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			int i = mgr.confirmOrderDetailDelivery(orderDetail, false, getMemo());
			if(i != 0) {
				logger.error("提交出错！ ret = " + i);
				setErrorReason("提交出错！");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmDelivery", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 切换ActiveState
	 * @action.input vendorOrder.*
	 * @action.input memo
	 * @return
	 */
	public String doControlActiveState() {
		try {
			if(!checkKeyFields()) {
				logger.debug("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			int i = mgr.controlActiveState(orderDetail, false, getMemo());
			if(i != 0) {
				logger.error("提交出错！ ret = " + i);
				setErrorReason("提交出错！");
				return ERROR;
			}
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doControlActiveState", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
}
