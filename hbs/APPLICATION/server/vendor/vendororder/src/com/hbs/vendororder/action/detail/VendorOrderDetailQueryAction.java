package com.hbs.vendororder.action.detail;
/**
 * add by yangzj 2010-05-27
 */
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


import com.hbs.common.action.base.BaseAction;

import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;
import com.hbs.vendororder.manager.VendorOrderDetailMgr;


@SuppressWarnings("serial")
public class VendorOrderDetailQueryAction extends BaseAction {
	public static final String vendorOrderDetailMgrName = "vendorOrderDetailMgr";

	/**
	 * logger.
	 */
	protected static Logger logger = Logger.getLogger(VendorOrderDetailQueryAction.class);
	
	VendorOrderDetail vorderDetail;

	
	
	public VendorOrderDetail getVorderDetail() {
		return vorderDetail;
	}



	public void setVorderDetail(VendorOrderDetail vorderDetail) {
		this.vorderDetail = vorderDetail;
	}



	/**
	 * 未交货订单明细查询（采购使用）
	 * @action.input vorderDetail.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doQueryList() {
		try {
			logger.debug("begin doQueryList");
			if(vorderDetail == null){
				vorderDetail = new VendorOrderDetail();
			}
			//组装必要的隐含的查询条件
			//用户信息
			String staffId = getLoginStaff().getStaffId().toString();
			if(StringUtils.isEmpty(staffId)){
				logger.error("没有登录系统，请登录!");
				setErrorReason("没有登录系统，请登录!");
				return ERROR;
			}else{
				vorderDetail.setStaffId(staffId);
			}
			//订货数量！= 已收货数量
			vorderDetail.setField("notComplete", "notComplete");
			//订单
			vorderDetail.setPoNoType("0");
			vorderDetail.setActiveState("ACTIVE");
			vorderDetail.setField("state", "'02','04','60','61'");
			vorderDetail.setSort("VER_DELIVERY_DATE DESC");
			setPagination(vorderDetail);
			
			
			VendorOrderDetailMgr mgr = (VendorOrderDetailMgr)getBean(vendorOrderDetailMgrName);
			setResult("list", mgr.getVendorOrderDetailList(vorderDetail));
			setTotalCount(mgr.getVendorOrderDetailCount(vorderDetail));
			setResult("count", getTotalCount());
			
			logger.debug("end doQueryList");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doQueryList", e);
			setErrorReason("查询失败，请稍候再试!");
			return ERROR;
		}
	}
	
	

}
