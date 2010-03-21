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
	
	WarehouseRecInfo warehouseRec;

	public WarehouseRecInfo getWarehouseRec() {
		return warehouseRec;
	}

	public void setWarehouseRec(WarehouseRecInfo warehouseRec) {
		this.warehouseRec = warehouseRec;
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
	 * 获取入库单信息
	 * @action.input warehouseRec.recPoNo + warehouseRec.vendorCode
	 * @action.result warehouseRec.*
	 * @return
	 */
	public String doGetInfo() {
		try{
			logger.debug("begin doGetInfo");
			if(WarehouseRecUtil.checkKeyFields(warehouseRec)) {
				logger.debug("参数为空！");
				setErrorReason("参数为空！");
				return ERROR;
			}
			WareHouseRecMgr mgr = getMgr();
			
			warehouseRec = mgr.getWarehouseRecInfo(warehouseRec, true);
			if(warehouseRec == null || StringUtils.isEmpty(warehouseRec.getOperId())) {
				logger.debug("没有找到");
				setErrorReason("没有找到");
				return ERROR;
			}else if(!getIsManager() && !warehouseRec.getOperId().equals(getLoginStaff().getStaffId().toString())) {
				logger.debug("权限错误");
				setErrorReason("权限错误");
				return ERROR;
			}
			setResult("warehouseRec", warehouseRec);
			logger.debug("end doGetInfo");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	protected void setMyId(boolean setName) throws Exception {
		warehouseRec.setOperId(getLoginStaff().getStaffId().toString());
		if(setName)
			warehouseRec.setOperStaff(getLoginStaff().getStaffName());
	}
}
