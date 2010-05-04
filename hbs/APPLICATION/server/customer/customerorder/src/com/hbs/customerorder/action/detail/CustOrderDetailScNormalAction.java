/**
 * 
 */
package com.hbs.customerorder.action.detail;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.hbs.common.utils.ListDataUtil;
import com.hbs.customerorder.constants.CustOrderConstants;

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
			if(!findOrderDetail()) {
				return ERROR;
			}
			setMyId(true);
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
			if(!findOrderDetail()) {
				return ERROR;
			}
			setMyId(true);
			// ��ȡ�ͻ�ָ�����ڣ����==null����ʹ����һ������
			Date d = null;
			String s = this.getHttpServletRequest().getParameter("custDate");
			if(StringUtils.isNotEmpty(s)){	
				try {
					DateTimeFormatter fmt = DateTimeFormat.forPattern(ListDataUtil.DATEFORMAT);
					DateTime dt = fmt.parseDateTime(s);
					Calendar c = Calendar.getInstance();
					c.set(dt.getYear(), dt.getMonthOfYear() - 1, dt.getDayOfMonth());
					d = c.getTime();
				} catch (Exception e) {
					String str = "���ڸ�ʽ����";
					logger.debug(str + " " + s);
					setErrorReason(str);
					return ERROR;
				}
			}
			if(d != null)
				orderDetail.setPreDeliveryDate(d);
			else{
				// DONE: ʹ����һ������
				//orderDetail.setVerDeliveryDate(orderDetail.getPreDeliveryDate());
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
			if(!findOrderDetail()) {
				return ERROR;
			}
			setMyId(true);
			if(CustOrderConstants.ORDER_ACTIVE_STATE.equals(orderDetail.getActiveState()))
				orderDetail.setActiveState(CustOrderConstants.ORDER_PAUSE_STATE);
			else
				orderDetail.setActiveState(CustOrderConstants.ORDER_ACTIVE_STATE);
			
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
	
	/**
	 * ���ڿͻ�������ϸ���ڽ���������δ���룬ҵ������������ַ���
	 * ״̬��05----���ڵ�����ҵ��ȷ�Ϸ�������δ���룩 תΪԭ��״̬70
	 * @action.input orderDetail.*
	 * @action.input memo
	 * @return
	 */
	public String doConfirmSend() {
		try {
			if(!findOrderDetail()) {
				return ERROR;
			}
			setMyId(true);
			int i = mgr.salesConfirmSendDetail(orderDetail, getMemo());
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmSend", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ���ڿͻ�������ϸ���ڽ���������δ���룬ҵ��������������ַ���
	 * ״̬��05----���ڵ�����ҵ��ȷ�Ϸ�������δ���룩 תΪԭ��״̬71
	 * @action.input orderDetail.*
	 * @action.input memo
	 * @return
	 */
	public String doConfirmNotSend() {
		try {
			if(!findOrderDetail()) {
				return ERROR;
			}
			setMyId(true);
			int i = mgr.salesConfirmNotSendDetail(orderDetail, getMemo());
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmSend", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
}
