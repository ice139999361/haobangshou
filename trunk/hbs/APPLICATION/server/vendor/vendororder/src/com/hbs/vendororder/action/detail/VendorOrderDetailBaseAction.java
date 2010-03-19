/**
 * 
 */
package com.hbs.vendororder.action.detail;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;
import com.hbs.vendororder.manager.VendorOrderDetailMgr;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class VendorOrderDetailBaseAction extends BaseAction {

	public static final String vendorOrderDetailMgrName = "vendorOrderDetailMgr";

	/**
	 * logger.
	 */
	protected static Logger logger = Logger.getLogger(VendorOrderDetailBaseAction.class);
	
	VendorOrderDetail orderDetail;

	/**
	 * @return the orderDetail
	 */
	public VendorOrderDetail getOrderDetail() {
		return orderDetail;
	}

	/**
	 * @param orderDetail the orderDetail to set
	 */
	public void setOrderDetail(VendorOrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
	
	/**
	 * 检查订单详情的关键字段是否填写
	 * @return
	 */
	protected boolean checkKeyFields() {
		try {
			if(orderDetail == null)
				return false;
			if(null == orderDetail.getOperSeqId()) {
				if(StringUtils.isEmpty(orderDetail.getCommCode()) 
						|| StringUtils.isEmpty(orderDetail.getCpartNo()) ) {
					logger.info("参数错误:" + orderDetail);
					setErrorReason("参数错误");
					return false;
				}
			}
		}catch(Exception e) {
			logger.error("catch Exception in checkKeyFields", e);
		}
		return true;
	}
	
	protected VendorOrderDetailMgr mgr = (VendorOrderDetailMgr)getBean(vendorOrderDetailMgrName);

	protected String getMemo() {
		String memo = null;
		try {
			memo = getHttpServletRequest().getParameter("memo");
		}catch(Exception e) {
			logger.error("catch Exception in getMemo", e);
		}
		return memo;
	}
	
	protected boolean findOrderDetail() throws Exception{
		if(!checkKeyFields())
			return false;
		orderDetail = mgr.getVendorOrderDetailById(orderDetail.getOperSeqId().toString());
		if(orderDetail == null) {
			logger.info("参数错误 getVendorOrderDetailById 返回null");
			setErrorReason("参数错误");
			return false;
		} else
			return true;
	}
}
