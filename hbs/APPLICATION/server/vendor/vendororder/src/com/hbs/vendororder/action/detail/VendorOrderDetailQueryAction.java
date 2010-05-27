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
	 * δ����������ϸ��ѯ���ɹ�ʹ�ã�
	 * @action.input vorderDetail.��ѯ����
	 * @action.result list���б� count������
	 * @return
	 */
	public String doQueryList() {
		try {
			logger.debug("begin doQueryList");
			if(vorderDetail == null){
				vorderDetail = new VendorOrderDetail();
			}
			//��װ��Ҫ�������Ĳ�ѯ����
			//�û���Ϣ
			String staffId = getLoginStaff().getStaffId().toString();
			if(StringUtils.isEmpty(staffId)){
				logger.error("û�е�¼ϵͳ�����¼!");
				setErrorReason("û�е�¼ϵͳ�����¼!");
				return ERROR;
			}else{
				vorderDetail.setStaffId(staffId);
			}
			//����������= ���ջ�����
			vorderDetail.setField("notComplete", "notComplete");
			//����
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
			setErrorReason("��ѯʧ�ܣ����Ժ�����!");
			return ERROR;
		}
	}
	
	

}
