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
	 * ��鶩������Ĺؼ��ֶ��Ƿ���д
	 * @return
	 */
	protected boolean checkKeyFields() {
		try {
			if(orderDetail == null)
				return false;
			if(null == orderDetail.getOperSeqId()) {
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
		VendorOrderDetail orderDetail2 = mgr.getVendorOrderDetailById(orderDetail.getOperSeqId().toString());
		if(orderDetail2 == null) {
			logger.info("�������� getVendorOrderDetailById ����null");
			setErrorReason("��������");
			return false;
		} else{
			orderDetail = orderDetail2;
			return true;
		}
	}
	
	/**
	 * ��ȡ��Ӧ�̶��������б�����ⵥѡ��Ӧ�̶�������ʹ��
	 * @return
	 */
	public String doListDetail(){
		try {
			logger.debug("begin doListDetail");
			if(orderDetail == null){
				logger.error("�޷���ѯ�����������Ϊ�գ�");
				setErrorReason("�޷���ѯ�����������Ϊ�գ�");
				return ERROR;
			}else{
				//��Ҫ׷��״̬����
				orderDetail.setActiveState("ACTIVE");
				orderDetail.setField("state", "'02','60'");
				orderDetail.setField("notPoNoType", "'1'");
				logger.debug("doListDetail ���������Ϊ" + orderDetail.toString());
			}			
			
			setResult("list", mgr.getVendorOrderDetailList(orderDetail));
			setTotalCount(mgr.getVendorOrderDetailCount(orderDetail));
			setResult("count", getTotalCount());
			
			logger.debug("end doListDetail");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
}
