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
 * 仓库入库单基类，实现doList、doGetInfo
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
	 * 返回角色名
	 * @return
	 */
	public abstract String getRoleName();
	
	/**
	 * 返回是否经理，内部使用。true：查看所有的已提交数据（不包括临时数据）；false：查看自己的数据（根据staffId）
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
	 * 查询
	 * @action.input warehouseRec.查询条件
	 * @action.result list：列表 count：总数
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
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 供财务管理发票使用，新增发票时选择入货单
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
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	/**
	 * 获取入库单信息
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
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 根据关键字段获取信息
	 * @throws Exception
	 */
	protected boolean getWarehouseRecByKey(boolean isDetail) throws Exception {
		boolean isTrue = WarehouseRecUtil.checkKeyFields(warehouseRec);
		logger.debug("isTrue =" + isTrue);
		if(!isTrue) {
			logger.debug(warehouseRec.toString());
			logger.debug("参数为空！");
			setErrorReason("参数为空！");
			return false;
		}
		WareHouseRecMgr mgr = getMgr();
		
		warehouseRec = mgr.getWarehouseRecInfo(warehouseRec, isDetail);
		if(warehouseRec == null || StringUtils.isEmpty(warehouseRec.getOperId())) {
			logger.debug("没有找到");
			setErrorReason("没有查询到相应的供应商订单记录！");
			return false;
		}else if(!getIsManager() && !warehouseRec.getOperId().equals(getLoginStaff().getStaffId().toString())) {
			logger.debug("权限错误");
			setErrorReason("权限错误");
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
