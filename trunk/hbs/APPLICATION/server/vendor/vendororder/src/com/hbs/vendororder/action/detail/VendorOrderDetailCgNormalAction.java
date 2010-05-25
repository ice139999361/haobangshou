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
	 * 确认账期订单明细的交期，只对账期订单明细有效
	 * @action.input orderDetail.*
	 * @action.input memo 原因
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
			// DONE: 处理doConfirmDelivery的参数
			Date d = null;
			if(StringUtils.isNotEmpty(s)){	
				try {
					d = ListDataUtil.parseDate(s);
				} catch (Exception e) {
					String str = "日期格式错误！";
					logger.debug(str + " " + s);
					setErrorReason(str);
					return ERROR;
				}
			}
			if(d != null)
				orderDetail.setVerDeliveryDate(d);
			int i = mgr.confirmOrderDetailDelivery(orderDetail, false, getMemo());
			if(i != 0) {
				logger.error("提交出错！ ret = " + i);
				setErrorReason("提交出错！");
				return ERROR;
			}
			checkOrderState02(orderDetail.getCommCode(), orderDetail.getPoNo());
			logger.debug("end doConfirmDelivery");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmDelivery", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 检查供应商订单的所有明细，看看是否可以将供应商订单的状态从04改为02
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
	 * 取消订单明细，状态变为03
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
				logger.error("提交出错！ ret = " + i);
				setErrorReason("提交出错！");
				return ERROR;
			}
			return SUCCESS;
		}catch(Exception e){
			logger.error("catch Exception in doCancel", e);
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
			if(!findOrderDetail()) {
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
