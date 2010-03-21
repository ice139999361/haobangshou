/**
 * 
 */
package com.hbs.warehousereceive.action;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.warehouse.pojo.WarehouseRecInfo;
import com.hbs.warehousereceive.manager.WareHouseRecMgr;

/**
 * �ֿ���ⵥ���࣬ʵ��doList��doGetInfo
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public abstract class WarehouseRecBaseAction extends BaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(WarehouseRecBaseAction.class);

	public static final String WAREHOUSE_REC_MGR = "wareHouseRecMgr";
	
	WarehouseRecInfo warehouseRec;

	public WarehouseRecInfo getWarehouseRec() {
		return warehouseRec;
	}

	public void setWarehouseRec(WarehouseRecInfo warehouseRec) {
		this.warehouseRec = warehouseRec;
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
	
	protected WareHouseRecMgr getMgr() {
		return (WareHouseRecMgr)getBean(WAREHOUSE_REC_MGR);
	}
	
	/**
	 * ��ѯ
	 * @action.input warehouseRec.��ѯ����
	 * @action.result list���б� count������
	 * @return
	 */
	public String doList() {
		try {
			logger.debug("begin doList");
			if(warehouseRec == null)
				warehouseRec = new WarehouseRecInfo();
			if(getIsManager())
				warehouseRec.setField("noState01", true);
			else
				setMyId(false);
			setPagination(warehouseRec);
			WareHouseRecMgr mgr = getMgr();
			setResult("list", mgr.listWarehouseRecInfo(warehouseRec));
			setTotalCount(mgr.listWarehouseRecInfoCount(warehouseRec));
			setResult("count", getTotalCount());
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeWarehouseRecState, getRoleName()));
			logger.debug("end doList");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ��ȡ��ⵥ��Ϣ
	 * @action.input warehouseRec.recPoNo + warehouseRec.vendorCode
	 * @action.result warehouseRec.*
	 * @return
	 */
	public String doGetInfo() {
		try{
			logger.debug("begin doGetInfo");
			if(WarehouseRecUtil.checkKeyFields(warehouseRec)) {
				logger.debug("����Ϊ�գ�");
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			WareHouseRecMgr mgr = getMgr();
			
			warehouseRec = mgr.getWarehouseRecInfo(warehouseRec, true);
			if(warehouseRec == null || StringUtils.isEmpty(warehouseRec.getOperId())) {
				logger.debug("û���ҵ�");
				setErrorReason("û���ҵ�");
				return ERROR;
			}else if(!getIsManager() && !warehouseRec.getOperId().equals(getLoginStaff().getStaffId().toString())) {
				logger.debug("Ȩ�޴���");
				setErrorReason("Ȩ�޴���");
				return ERROR;
			}
			setResult("warehouseRec", warehouseRec);
			logger.debug("end doGetInfo");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	protected void setMyId(boolean setName) throws Exception {
		warehouseRec.setOperId(getLoginStaff().getStaffId().toString());
		if(setName)
			warehouseRec.setOperStaff(getLoginStaff().getStaffName());
	}
}
