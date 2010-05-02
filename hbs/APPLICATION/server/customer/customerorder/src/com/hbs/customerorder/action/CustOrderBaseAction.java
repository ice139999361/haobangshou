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
	 * ��ѯ
	 * @action.input custOrder.��ѯ����
	 * @action.result list���б� count������
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
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ��ȡ�ͻ�������Ϣ
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
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * ����key��ȡ�ͻ�������Ϣ
	 * @return �����������false�����򷵻�true�����û�ҵ���Ҳ����true
	 * @throws Exception
	 */
	protected boolean getCustOrderByBizKey() throws Exception{
		if(custOrder == null
				|| StringUtils.isEmpty(custOrder.getCommCode()) 
				|| StringUtils.isEmpty(custOrder.getPoNo())) {
			setErrorReason("����Ϊ�գ�");
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
