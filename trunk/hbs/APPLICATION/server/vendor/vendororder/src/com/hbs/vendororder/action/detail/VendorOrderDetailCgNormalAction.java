package com.hbs.vendororder.action.detail;

import org.apache.log4j.Logger;

import com.hbs.customerorder.action.detail.CustOrderDetailCgNormalAction;

@SuppressWarnings("serial")
public class VendorOrderDetailCgNormalAction extends VendorOrderDetailBaseAction {
	protected static Logger logger = Logger.getLogger(VendorOrderDetailCgNormalAction.class);

	/**
	 * ȷ�����ڶ�����ϸ�Ľ��ڣ�ֻ�����ڶ�����ϸ��Ч
	 * @action.input orderDetail.*
	 * @action.input memo ԭ��
	 * @return
	 */
	public String doConfirmDelivery() {
		try {
			if(!checkKeyFields()) {
				setErrorReason("��������");
				return ERROR;
			}
			int i = mgr.confirmOrderDetailDelivery(orderDetail, false, getMemo());
			if(i != 0) {
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

}
