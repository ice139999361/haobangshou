/**
 * 
 */
package com.hbs.customerorder.action;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.manager.CustOrderMgr;
import com.hbs.domain.customer.order.pojo.CustomerOrder;

/**
 * @author xyf
 *
 */
public abstract class CustOrderBaseAction extends BaseAction {

	/**
	 * logger.
	 */
	protected abstract Logger getLogger();

	public abstract String getRoleName();

	CustomerOrder custOrder;

	/**
	 * @return the custOrder
	 */
	public CustomerOrder getCustOrder() {
		return custOrder;
	}

	/**
	 * @param custOrder the custOrder to set
	 */
	public void setCustOrder(CustomerOrder custOrder) {
		this.custOrder = custOrder;
	}

	String audit;
	
	String auditDesc;
	
	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	public String getAuditDesc() {
		return auditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}

	protected CustOrderMgr getMgr(){
		return (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
	}
	
	/**
	 * 查询
	 * @action.input custOrder.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doList() {
		try {
			getLogger().debug("begin doList");
			if(custOrder == null)
				custOrder = new CustomerOrder();
			custOrder.setField("noState01", true);
			setPagination(custOrder);
			CustOrderMgr mgr = getMgr();
			setResult("list", mgr.listCustomerOrder(custOrder));
			setTotalCount(mgr.listCustomerOrderCount(custOrder));
			setResult("count", getTotalCount());
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeCustOrderState, getRoleName()));
			getLogger().debug("end doList");
			return SUCCESS;
		} catch(Exception e) {
			getLogger().error("catch Exception in doList", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 获取客户订单信息
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.result custOrder.*
	 * @return
	 */
	public String doGetInfo() {
		try{
			getLogger().debug("begin doGetInfo");
			if(!getCustOrderByBizKey())
				return ERROR;
			setResult("custOrder", custOrder);
			getLogger().debug("end doGetInfo");
			return SUCCESS;
		}catch(Exception e) {
			getLogger().error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 根据key获取客户订单信息
	 * @return 如果出错，返回false，否则返回true。如果没找到，也返回true
	 * @throws Exception
	 */
	protected boolean getCustOrderByBizKey() throws Exception{
		if(custOrder == null
				|| StringUtils.isEmpty(custOrder.getCommCode()) 
				|| StringUtils.isEmpty(custOrder.getPoNo())) {
			setErrorReason("参数为空！");
			return false;
		}
		custOrder = getMgr().findCustomerOrderByBizKey(custOrder, true);
		return true;
	}

	protected void setMyId(boolean setName) throws Exception {
		custOrder.setStaffId(getLoginStaff().getStaffId().toString());
		if(setName)
			custOrder.setStaffName(getLoginStaff().getStaffName());
	}
}
