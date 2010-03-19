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
public class CustOrderDetailScNormalAction extends CustOrderDetailBaseAction {
	/**
	 * logger.
	 */
	protected static Logger logger = Logger.getLogger(CustOrderDetailScNormalAction.class);

	/**
	 * ȡ���ö�����ϸ
	 * @action.input orderDetail.*
	 * @action.input memo ȡ��ԭ��
	 * @return
	 */
	public String doCancel() {
		try {
			if(!checkKeyFields()) {
				setErrorReason("��������");
				return ERROR;
			}
			int i = mgr.cancelOrderDetail(orderDetail, getMemo());
			if(i != 0) {
				logger.error("ȡ������ ret = " + i);
				setErrorReason("ȡ������");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doCancel", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ҵ���ύ�����Ľ��ڣ��ύ���ɹ�����
	 * @action.input orderDetail.*
	 * @action.input memo
	 * @return
	 */
	public String doConfirmDelivery() {
		try {
			if(!checkKeyFields()) {
				setErrorReason("��������");
				return ERROR;
			}
			int i = mgr.salesConfirmDetailDelivery(orderDetail, getMemo());
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmDelivery", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * �л�ActiveState
	 * @action.input orderDetail.*
	 * @action.input memo
	 * @return
	 */
	public String doControlActiveState() {
		try {
			if(!checkKeyFields()) {
				setErrorReason("��������");
				return ERROR;
			}
			int i = mgr.controlActiveState(orderDetail, getMemo());
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doControlActiveState", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
}
