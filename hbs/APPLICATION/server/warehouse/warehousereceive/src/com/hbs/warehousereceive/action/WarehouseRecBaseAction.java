/**
 * 
 */
package com.hbs.warehousereceive.action;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;
import com.hbs.domain.warehouse.pojo.WarehouseRecInfo;

import com.hbs.warehousereceive.manager.WareHouseRecDetailMgr;
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
	
	public static final String WAREHOUSE_REC_DETAIL_MGR ="wareHouseRecDetailMgr";
	
	WarehouseRecInfo warehouseRec;
	
	WarehouseRecDetail warehouseRecDetail;

	public WarehouseRecInfo getWarehouseRec() {
		return warehouseRec;
	}

	public void setWarehouseRec(WarehouseRecInfo warehouseRec) {
		this.warehouseRec = warehouseRec;
	}

	
	
	public WarehouseRecDetail getWarehouseRecDetail() {
		return warehouseRecDetail;
	}

	public void setWarehouseRecDetail(WarehouseRecDetail warehouseRecDetail) {
		this.warehouseRecDetail = warehouseRecDetail;
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
	
	WareHouseRecDetailMgr getMgrDetail(){
		return (WareHouseRecDetailMgr)getBean(WAREHOUSE_REC_DETAIL_MGR);
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
	 * ���������Ʊʹ�ã�������Ʊʱѡ�������
	 * @return
	 */
	public String doListDetail() {
		try {
			logger.debug("begin doListDetail");
			if(warehouseRecDetail == null){
				warehouseRecDetail = new WarehouseRecDetail();
			}
			warehouseRecDetail.setField("notInState", "'01','03'");			
			setPagination(warehouseRecDetail);
			WareHouseRecDetailMgr mgr = getMgrDetail();
			setResult("list", mgr.getWarehouseRecDetailList(warehouseRecDetail));
			setTotalCount(mgr.getWarehouseRecDetailCount(warehouseRecDetail));
			setResult("count", getTotalCount());			
			logger.debug("end doListDetail");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doListDetail", e);
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
			boolean success = getWarehouseRecByKey(true);
			setResult("warehouseRec", warehouseRec);
			logger.debug("end doGetInfo");
			return success ? SUCCESS : ERROR;
		}catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ���ݹؼ��ֶλ�ȡ��Ϣ
	 * @throws Exception
	 */
	protected boolean getWarehouseRecByKey(boolean isDetail) throws Exception {
		boolean isTrue = WarehouseRecUtil.checkKeyFields(warehouseRec);
		logger.debug("isTrue =" + isTrue);
		if(!isTrue) {
			logger.debug(warehouseRec.toString());
			logger.debug("����Ϊ�գ�");
			setErrorReason("����Ϊ�գ�");
			return false;
		}
		WareHouseRecMgr mgr = getMgr();
		
		warehouseRec = mgr.getWarehouseRecInfo(warehouseRec, isDetail);
		if(warehouseRec == null || StringUtils.isEmpty(warehouseRec.getOperId())) {
			logger.debug("û���ҵ�");
			setErrorReason("û�в�ѯ����Ӧ�Ĺ�Ӧ�̶�����¼��");
			return false;
		}else if(!getIsManager() && !warehouseRec.getOperId().equals(getLoginStaff().getStaffId().toString())) {
			logger.debug("Ȩ�޴���");
			setErrorReason("Ȩ�޴���");
			return false;
		}
		return true;
	}

	protected void setMyId(boolean setName) throws Exception {
		warehouseRec.setOperId(getLoginStaff().getStaffId().toString());
		if(setName)
			warehouseRec.setOperStaff(getLoginStaff().getStaffName());
	}
}
