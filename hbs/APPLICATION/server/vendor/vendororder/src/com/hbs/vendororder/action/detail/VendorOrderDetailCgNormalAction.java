package com.hbs.vendororder.action.detail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.manager.systemconfig.SystemConfigMgr;
import com.hbs.common.utils.ListDataUtil;
import com.hbs.customerorder.action.detail.CustOrderDetailBaseAction;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.manager.CustOrderDetailMgr;
import com.hbs.domain.common.pojo.SystemConfig;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
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
			if(d == null){
				d = orderDetail.getOrgDeliveryDate();
			}
			orderDetail.setVerDeliveryDate(d);
			
			int i = mgr.confirmOrderDetailDelivery(orderDetail, false, getMemo());
			if(i != 0) {
				logger.error("提交出错！ ret = " + i);
				setErrorReason("提交出错！");
				return ERROR;
			}
			checkOrderState02(orderDetail.getCommCode(), orderDetail.getPoNo());
			try{
				checkCustOrderDeliveryDate(orderDetail, d);
			}catch(Exception e){
				String ss = "自动提交客户订单交期失败，请手工确认对应的客户订单详情的交期！";
				logger.debug(ss + orderDetail.toString(), e);
				this.setAlertMsg(ss);
			}
			logger.debug("end doConfirmDelivery");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmDelivery", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	private void checkCustOrderDeliveryDate(VendorOrderDetail orderDetail, Date d) throws Exception {
		final String cfgName = "VendorDate2CustDate";
		if(StringUtils.isEmpty(orderDetail.getRltOrderPoNo()))
			return;
		SystemConfig cfg = SystemConfigMgr.findSystemConfig(cfgName);
		if(cfg == null){
			logger.debug("findSystemConfig failed:" + cfgName);
			return;
		}

		CustOrderDetail cod1 = new CustOrderDetail();
		CustOrderDetailMgr codMgr = (CustOrderDetailMgr)getBean(CustOrderDetailBaseAction.custOrderDetailMgrName);
		cod1.setCommCode(orderDetail.getCustCcode());
		cod1.setPoNo(orderDetail.getRltOrderPoNo());
		cod1.setCpartNo(orderDetail.getCpartNo());
		cod1.setPartNo(orderDetail.getPartNo());
		cod1.setSpecDesc(orderDetail.getSpecDesc());
		CustOrderDetail cod = codMgr.findCustOrderDetailByBizKey(cod1);
		if(cod == null)
			throw new Exception("findCustOrderDetailByBizKey failed!");
		if(!CustOrderConstants.ORDER_STATE_20.equals(cod.getState())){
			logger.debug("CustOrderDetail.state != 20");
			return;
		}
		int ret;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(cfg.getConfigValue()));
		Date newdate = cal.getTime();
		Date predate = cod.getPreDeliveryDate();
		if(predate == null)
			predate = cod.getOrgDeliveryDate();
		if(predate.before(newdate)){
			// 超期
			cod.setPreDeliveryDate(newdate);
			logger.debug("purchaseRefuseDetailDelivery");
			ret = codMgr.purchaseRefuseDetailDelivery(cod, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), null);
		}else{
			// 在期限内
			logger.debug("purchaseConfirmDetailDelivery");
			newdate = predate;
			cod.setPreDeliveryDate(newdate);
			ret = codMgr.purchaseConfirmDetailDelivery(cod, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), null);
		}
		if(ret != 0){
			throw new Exception("purchaseXXXDetailDelivery failed! ret=" + ret);
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
