/**
 * 
 */
package com.hbs.vendororder.action;



import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;

import com.hbs.domain.vendor.order.pojo.VendorOrder;

import com.hbs.vendororder.manager.VendorOrderMgr;

/**
 * 供应商订单基类，实现doList、doGetInfo
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public abstract class VendorOrderBaseAction extends BaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(VendorOrderBaseAction.class);

	public static final String VENDOR_ORDER_MGR = "vendorOrderMgr";
	
	VendorOrder vendorOrder;
	
	

	/**
	 * @return the vendorOrder
	 */
	public VendorOrder getVendorOrder() {
		return vendorOrder;
	}

	/**
	 * @param vendorOrder the vendorOrder to set
	 */
	public void setVendorOrder(VendorOrder vendorOrder) {
		this.vendorOrder = vendorOrder;
	}

	/**
	 * 返回角色名
	 * @return
	 */
	public abstract String getRoleName();
	
	/**
	 * 返回是否经理，内部使用。true：查看所有的已提交数据（不包括临时数据）；false：查看自己的数据（根据staffId）
	 * @return
	 */
	protected abstract boolean getIsManager();
	
	/**
	 * 查询
	 * @action.input custOrder.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doList() {
		try {
			logger.debug("begin doList");
			if(vendorOrder == null)
				vendorOrder = new VendorOrder();
			if(getIsManager())
				vendorOrder.setField("noState01", true);
			else
				setMyId(false);
			setPagination(vendorOrder);
			VendorOrderMgr mgr = (VendorOrderMgr)getBean(VENDOR_ORDER_MGR);
			setResult("list", mgr.getVendorOrderList(vendorOrder));
			setTotalCount(mgr.getVendorOrderCount(vendorOrder));
			setResult("count", getTotalCount());
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeVendorOrderState, getRoleName()));
			logger.debug("end doList");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 获取供应商订单信息
	 * @action.input vendorOrder.commCode + vendorOrder.poNo
	 * @action.result vendorOrder.*
	 * @return
	 */
	public String doGetInfo() {
		try{
			logger.debug("begin doGetInfo");
			if(vendorOrder == null
					|| StringUtils.isEmpty(vendorOrder.getCommCode()) 
					|| StringUtils.isEmpty(vendorOrder.getPoNo())) {
				logger.debug("参数为空！");
				setErrorReason("参数为空！");
				return ERROR;
			}
			VendorOrderMgr mgr = (VendorOrderMgr)getBean(VENDOR_ORDER_MGR);
			VendorOrder vendorOrder2 = mgr.getVendorOrder(vendorOrder.getCommCode(), vendorOrder.getPoNo(), true);
			if(vendorOrder2 == null || vendorOrder2.getStaffId() == null) {
				logger.debug("没有找到");
				setErrorReason("没有找到");
				return ERROR;
			}else if(!getIsManager() && !vendorOrder2.getStaffId().equals(getLoginStaff().getStaffId().toString())) {
				logger.debug("权限错误");
				setErrorReason("权限错误");
				return ERROR;
			}
			setResult("vendorOrder", vendorOrder2);
			logger.debug("end doGetInfo");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	protected void setMyId(boolean setName) throws Exception {
		vendorOrder.setStaffId(getLoginStaff().getStaffId().toString());
		if(setName)
			vendorOrder.setStaffName(getLoginStaff().getStaffName());
	}
}
