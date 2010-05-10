/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendororder.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.vendor.order.pojo.VendorOrder;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;
import com.hbs.vendororder.manager.VendorOrderMgr;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class VendorOrderPrintAction extends BaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(VendorOrderPrintAction.class);
	
	
	public static final String VENDOR_ORDER_MGR = "vendorOrderMgr";
	
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
	
	
	VendorOrder vendorOrder;
	/**
	 * ��ȡ��Ӧ�̶�����Ϣ
	 * @action.input vendorOrder.commCode + vendorOrder.poNo
	 * @action.result vendorOrder.*
	 * @return
	 */
	public String doGetInfoPrint() {
		try{
			logger.debug("begin doGetInfoPrint");
			if(vendorOrder == null
					|| StringUtils.isEmpty(vendorOrder.getCommCode()) 
					|| StringUtils.isEmpty(vendorOrder.getPoNo())) {
				logger.debug("����Ϊ�գ�");
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}else{
				//��״̬
				vendorOrder.setState("02");
				vendorOrder.setActiveState("ACTIVE");
			}
			VendorOrderMgr mgr = (VendorOrderMgr)getBean(VENDOR_ORDER_MGR);
			VendorOrder vendorOrder2 = mgr.getVendorOrder(vendorOrder.getCommCode(), vendorOrder.getPoNo(), true);
			if(vendorOrder2 == null) {
				logger.debug("�޹�Ӧ��(" + vendorOrder.getCommCode() +")�Ķ���(" + vendorOrder.getPoNo() + ")");
				setErrorReason("�޹�Ӧ��(" + vendorOrder.getCommCode() +")�Ķ���(" + vendorOrder.getPoNo() + ")");
				return ERROR;
			}else  {
				List<VendorOrderDetail> detailList = vendorOrder2.getVendorOrderDetailList();
				List<VendorOrderDetail> printList = new ArrayList<VendorOrderDetail>();
				if(null != detailList && detailList.size() >0){
					
					for(VendorOrderDetail detail : detailList){
						String state = detail.getState();
						
						if(null != state && state.equals("02")){
							printList.add(detail);
						}
					}
				}
				if(printList.size() >0){
					vendorOrder2.setVendorOrderDetailList(printList);
				}else{
					vendorOrder2.setVendorOrderDetailList(null);
				}
			}
			setResult("vendorOrder", vendorOrder2);
			logger.debug("end doGetInfoPrint");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doGetInfoPrint", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
}
