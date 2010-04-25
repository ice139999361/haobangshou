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
	 * ����ȷ�϶�����ϸ��Ԥ��x%�������
	 * @action.input orderDetail.*
	 * @action.input memo ȡ��ԭ��
	 * @return
	 */
	public String doConfirmDetailFee() {
		try {
			if(!findOrderDetail()) {
				return ERROR;
			}
			int i = mgr.financeConfirmDetailFee(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmDetailFee", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * �������붩����ϸ��Ԥ��x%�����������δ������
	 * @action.input orderDetail.*
	 * @action.input memo ȡ��ԭ��
	 * @return
	 */
	public String doApplyDetailFee() {
		try {
			if(!findOrderDetail()) {
				return ERROR;
			}
			int i = mgr.financeApplyDetailFee(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doApplyDetailFee", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
}
