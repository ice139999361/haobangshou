/**
 * 
 */
package com.hbs.customerorder.action.detail;

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
	
	/**
	 * ��鶩������Ĺؼ��ֶ��Ƿ���д
	 * @return
	 */
	protected boolean checkKeyFields() {
		try {
			if(orderDetail == null)
				return false;
			if(StringUtils.isEmpty(orderDetail.getOperSeqId())) {
				if(StringUtils.isEmpty(orderDetail.getCommCode()) 
						|| StringUtils.isEmpty(orderDetail.getCpartNo()) ) {
					logger.info("��������:" + orderDetail);
					setErrorReason("��������");
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
			logger.info("�������� findCustOrderDetailById ����null");
			setErrorReason("��������");
			return false;
		} else
			return true;
	}
	
	/**
	 * ��ѯ�ͻ���������
	 * @action.input orderDetail.*
	 * @action.result list List<CustOrderDetail>
	 * @action.result count ����
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
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
}
