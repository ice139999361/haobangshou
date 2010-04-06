/**
 * 
 */
package com.hbs.customerorder.action;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.customerorder.action.detail.CustOrderDetailBaseAction;
import com.hbs.customerorder.manager.CustOrderDetailMgr;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.customer.order.pojo.CustomerOrder;

/**
 * �ɹ���ͨ�û��Կͻ������Ĳ���
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class CustOrderCgNormalAction extends BaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(CustOrderCgNormalAction.class);

	public static final String roleName = "cgnormal";

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

	/**
	 * ���ݹ�Ӧ�̱����ѯ�豸���Ŀͻ���������
	 * @action.input custOrder.vendorCode
	 * @action.result list List<CustOrderDetail>
	 * @return
	 */
	@Deprecated
	public String doListStockupByVendor() {
		try {
			if(custOrder == null || StringUtils.isEmpty(custOrder.getVendorCode())){
				logger.debug("����Ϊ�գ�");
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			CustOrderDetailMgr mgr = (CustOrderDetailMgr)getBean(CustOrderDetailBaseAction.custOrderDetailMgrName);
			CustOrderDetail orderDetail = new CustOrderDetail();
			orderDetail.setVendorCode(custOrder.getVendorCode());
			List<String> stateList = new Vector<String>();
			stateList.add("20");
			stateList.add("21");
			orderDetail.setField("stateList", stateList);
			List<CustOrderDetail> list = mgr.listCustOrderDetail(orderDetail); 
			for(Iterator<CustOrderDetail> it = list.iterator(); it.hasNext(); ) {
				CustOrderDetail o = it.next();
				if((o.getAmount() - isNull(o.getLockAmount(),0) - isNull(o.getDeliveryAmount(),0)) <= (0))
					it.remove();
			}
			setResult("list", list);
			// DONE:CustOrderCgNormalAction.doListByVendor
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doListStockupByVendor", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * ���iΪnull���򷵻�v�����򷵻�i
	 * @param i	���жϵ�Integer
	 * @param v ȱʡ����ֵ
	 * @return ���iΪnull���򷵻�v�����򷵻�i
	 */
	private static Integer isNull(Integer i, int v) {
		if(i == null)
			return v;
		else
			return i;
	}
}
