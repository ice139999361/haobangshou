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
 * ��Ӧ�̶������࣬ʵ��doList��doGetInfo
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
	 * ���ؽ�ɫ��
	 * @return
	 */
	public abstract String getRoleName();
	
	/**
	 * �����Ƿ����ڲ�ʹ�á�true���鿴���е����ύ���ݣ���������ʱ���ݣ���false���鿴�Լ������ݣ�����staffId��
	 * @return
	 */
	protected abstract boolean getIsManager();
	
	/**
	 * ��ѯ
	 * @action.input custOrder.��ѯ����
	 * @action.result list���б� count������
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
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * ��ȡ��Ӧ�̶�����Ϣ
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
				logger.debug("����Ϊ�գ�");
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			VendorOrderMgr mgr = (VendorOrderMgr)getBean(VENDOR_ORDER_MGR);
			VendorOrder vendorOrder2 = mgr.getVendorOrder(vendorOrder.getCommCode(), vendorOrder.getPoNo(), true);
			if(vendorOrder2 == null || vendorOrder2.getStaffId() == null) {
				logger.debug("û���ҵ�");
				setErrorReason("û���ҵ�");
				return ERROR;
			}else if(!getIsManager() && !vendorOrder2.getStaffId().equals(getLoginStaff().getStaffId().toString())) {
				logger.debug("Ȩ�޴���");
				setErrorReason("Ȩ�޴���");
				return ERROR;
			}
			setResult("vendorOrder", vendorOrder2);
			logger.debug("end doGetInfo");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	protected void setMyId(boolean setName) throws Exception {
		vendorOrder.setStaffId(getLoginStaff().getStaffId().toString());
		if(setName)
			vendorOrder.setStaffName(getLoginStaff().getStaffName());
	}
}
