package com.hbs.vendororder.action.detail;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.utils.ListDataUtil;
import com.hbs.domain.vendor.order.dao.VendorOrderDao;
import com.hbs.domain.vendor.order.pojo.VendorOrder;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;
import com.hbs.vendororder.action.VendorOrderBaseAction;
import com.hbs.vendororder.constants.VendorOrderConstants;
import com.hbs.vendororder.manager.VendorOrderMgr;

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
			checkOrderState02(orderDetail.getCommCode(), orderDetail.getPoNo());
			logger.debug("end doConfirmDelivery");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmDelivery", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ��鹩Ӧ�̶�����������ϸ�������Ƿ���Խ���Ӧ�̶�����״̬��04��Ϊ02
	 * @param commcode
	 * @param pono
	 * @throws Exception
	 */
	public void checkOrderState02(String commcode, String pono) throws Exception {
		VendorOrderMgr omgr = (VendorOrderMgr)getBean(VendorOrderBaseAction.VENDOR_ORDER_MGR);
		VendorOrder order = omgr.getVendorOrder(commcode, pono, true);
		if(order == null)
			return;
		if(!VendorOrderConstants.VENDOR_ORDER_STATE_04.equals(order.getState()))
			return;
		List<VendorOrderDetail> list = order.getVendorOrderDetailList();
		boolean change = true;
		for(VendorOrderDetail od : list){
			String state = od.getState();
			if(!VendorOrderConstants.VENDOR_ORDER_STATE_02.equals(state)
					&& !VendorOrderConstants.VENDOR_ORDER_STATE_60.equals(state)
					&& !VendorOrderConstants.VENDOR_ORDER_STATE_61.equals(state)){
				change = false;
			}
		}
		if(change){
			order.setState(VendorOrderConstants.VENDOR_ORDER_STATE_02);
			VendorOrderDao vOrderDao =(VendorOrderDao)getBean(VendorOrderConstants.VENDOR_ORDER_DAO);
			vOrderDao.updateVendorOrderByState(order);
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
