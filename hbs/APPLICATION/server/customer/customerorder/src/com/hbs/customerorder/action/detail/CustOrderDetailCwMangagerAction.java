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
	 * �쵼����ͬ�ⶩ����ϸ��Ԥ��x%�����������δ������
	 * @action.input orderDetail.*
	 * @action.input memo ԭ��
	 * @return
	 */
	public String doAgreeDetailFee() {
		try {
			if(!checkKeyFields()) {
				setErrorReason("��������");
				return ERROR;
			}
			int i = mgr.auditAgreeDetailFee(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doAgreeDetailFee", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * �쵼������ͬ�ⶩ����ϸ��Ԥ��x%�����������δ������
	 * @action.input orderDetail.*
	 * @action.input memo ԭ��
	 * @return
	 */
	public String doDisAgreeDetailFee() {
		try {
			if(!checkKeyFields()) {
				setErrorReason("��������");
				return ERROR;
			}
			int i = mgr.auditDisAgreeDetailFee(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doDisAgreeDetailFee", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

}
