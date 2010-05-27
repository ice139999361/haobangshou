package com.hbs.customerorder.action.detail;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.customerorder.manager.CustOrderDetailMgr;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;


@SuppressWarnings("serial")
public class CustOrderDetailQueryAction extends BaseAction {
	public static final String custOrderDetailMgrName = "custOrderDetailMgr";

	/**
	 * logger.
	 */
	protected static Logger logger = Logger.getLogger(CustOrderDetailQueryAction.class);
	
	CustOrderDetail corderDetail;

	public CustOrderDetail getCorderDetail() {
		return corderDetail;
	}

	public void setCorderDetail(CustOrderDetail corderDetail) {
		this.corderDetail = corderDetail;
	}
	
	
	/**
	 * 未发货订单明细查询（销售或助理使用）
	 * @action.input vorderDetail.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doQueryList() {
		try {
			logger.debug("begin doQueryList");
			if(corderDetail == null){
				corderDetail = new CustOrderDetail();
			}
			//组装必要的隐含的查询条件
			//用户信息
			String staffId = getLoginStaff().getStaffId().toString();
			if(StringUtils.isEmpty(staffId)){
				logger.error("没有登录系统，请登录!");
				setErrorReason("没有登录系统，请登录!");
				return ERROR;
			}else{
				corderDetail.setField("queryStaffId", staffId);
			}
			//订货数量！= 已发货数量
			corderDetail.setField("notComplete", "notComplete");
			//订单
			corderDetail.setPoNoType("0");
			corderDetail.setActiveState("ACTIVE");
			corderDetail.setField("noState", "'01','03'");
			corderDetail.setSort("VER_DELIVERY_DATE DESC");
			setPagination(corderDetail);
			
			
			CustOrderDetailMgr mgr = (CustOrderDetailMgr)getBean(custOrderDetailMgrName);
			setResult("list", mgr.listCustOrderDetail(corderDetail));
			setTotalCount(mgr.listCustOrderDetailCount(corderDetail));
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
