/**
 * 
 */
package com.hbs.customerorder.action.detail;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.customerorder.manager.CustOrderDetailMgr;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class CustOrderDetailBaseAction extends BaseAction {

	public static final String custOrderDetailMgrName = "custOrderDetailMgr";

	/**
	 * logger.
	 */
	protected static Logger logger = Logger.getLogger(CustOrderDetailBaseAction.class);
	
	CustOrderDetail orderDetail;

	/**
	 * @return the orderDetail
	 */
	public CustOrderDetail getOrderDetail() {
		return orderDetail;
	}

	/**
	 * @param orderDetail the orderDetail to set
	 */
	public void setOrderDetail(CustOrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
	
	protected void setMyId(boolean setName) throws Exception {
		orderDetail.setStaffId(this.getLoginStaff().getStaffId().toString());
		if(setName)
			orderDetail.setStaffName(this.getLoginStaff().getStaffName());
	}
	
	/**
	 * 检查订单详情的关键字段是否填写
	 * @return
	 */
	protected boolean checkKeyFields() {
		try {
			if(orderDetail == null){
				logger.info("参数错误");
				setErrorReason("参数错误");
				return false;
			}
			if(StringUtils.isEmpty(orderDetail.getOperSeqId())) {
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
	
	protected CustOrderDetailMgr mgr = (CustOrderDetailMgr)getBean(custOrderDetailMgrName);

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
		orderDetail = mgr.findCustOrderDetailById(orderDetail.getOperSeqId());
		if(orderDetail == null) {
			logger.info("参数错误 findCustOrderDetailById 返回null");
			setErrorReason("参数错误");
			return false;
		} else
			return true;
	}
	
	/**
	 * 查询客户订单详情
	 * @action.input orderDetail.*
	 * @action.result list List<CustOrderDetail>
	 * @action.result count 总数
	 * @return
	 */
	public String doList() {
		try{
			if(orderDetail == null)
				orderDetail = new CustOrderDetail();
			setPagination(orderDetail);
			setTotalCount(mgr.listCustOrderDetailCount(orderDetail));
			setResult("list", mgr.listCustOrderDetail(orderDetail));
			setResult("count", getTotalCount());
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	public String doGetInfo() {
		try{
			if(orderDetail == null){
				String s = "参数错误！";
				setErrorReason(s);
				logger.info(s);
				return ERROR;
			}
			setPagination(orderDetail);

			List<CustOrderDetail> list = mgr.listCustOrderDetail(orderDetail);
			if(list == null || list.size() <= 0){
				String s = "无法找到相应数据！";
				setErrorReason(s);
				logger.info(s);
				return ERROR;
			}
			setResult("orderDetail", list.get(0));
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 获取客户订单详情列表，供出库库单选择客户订单详情使用
	 * @return
	 */
	public String doListDetail(){
		try {
			logger.debug("begin doListDetail");
			if(orderDetail == null){
				logger.error("无法查询，输入的条件为空！");
				setErrorReason("无法查询，输入的条件为空！");
				return ERROR;
			}else{
				//需要追加状态条件
				orderDetail.setActiveState("ACTIVE");
			
				orderDetail.setField("state", "'71','70','61'");//或备齐或部分发货
				orderDetail.setField("notPoNoType", "'1'");
				logger.debug("doListDetail 输入的条件为" + orderDetail.toString());
			}			
			
			setResult("list", mgr.listCustOrderDetail(orderDetail));
			setTotalCount(mgr.listCustOrderDetailCount(orderDetail));
			setResult("count", getTotalCount());
			
			logger.debug("end doListDetail");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
}
