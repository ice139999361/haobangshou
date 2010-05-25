package com.hbs.vendororder.action.detail;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.utils.ListDataUtil;

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
			String s = this.getHttpServletRequest().getParameter("cgjq");
			logger.debug("begin doConfirmDelivery, " + ((orderDetail == null) ? "" : orderDetail.toString()) + " " + s);
			if(!findOrderDetail()) {
				return ERROR;
			}
			setMyId(true);
			// DONE: ����doConfirmDelivery�Ĳ���
			Date d = null;
			if(StringUtils.isNotEmpty(s)){	
				try {
					d = ListDataUtil.parseDate(s);
				} catch (Exception e) {
					String str = "���ڸ�ʽ����";
					logger.debug(str + " " + s);
					setErrorReason(str);
					return ERROR;
				}
			}
			if(d != null)
				orderDetail.setVerDeliveryDate(d);
			int i = mgr.confirmOrderDetailDelivery(orderDetail, false, getMemo());
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			logger.debug("end doConfirmDelivery");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmDelivery", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ȡ��������ϸ��״̬��Ϊ03
	 * @action.input vendorOrder.*
	 * @action.input memo
	 * @return
	 */
	public String doCancel(){
		try{
			if(!findOrderDetail()) {
				return ERROR;
			}
			setMyId(true);
			int i = mgr.cancelOrderDetail(orderDetail, false, getMemo());
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		}catch(Exception e){
			logger.error("catch Exception in doCancel", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * �л�ActiveState
	 * @action.input vendorOrder.*
	 * @action.input memo
	 * @return
	 */
	public String doControlActiveState() {
		try {
			if(!findOrderDetail()) {
				return ERROR;
			}
			int i = mgr.controlActiveState(orderDetail, false, getMemo());
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
