package com.hbs.warehouse.action;

import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.warehouse.manager.WarehouseMgr;

@SuppressWarnings("serial")
public class WarehouseAction extends BaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(WarehouseAction.class);

	public static final String WAREHOUSEMGR = "warehouseMgr";
	
	WareHouseInfo wInfo;

	/**
	 * @return the wInfo
	 */
	public WareHouseInfo getWInfo() {
		return wInfo;
	}

	/**
	 * @param info the wInfo to set
	 */
	public void setWInfo(WareHouseInfo info) {
		wInfo = info;
	}
	
	public String doList() {
		try {
			logger.debug("begin doList");
			if(wInfo == null)
				wInfo = new WareHouseInfo();
			setPagination(wInfo);
			WarehouseMgr mgr = (WarehouseMgr)getBean(WAREHOUSEMGR);
			setResult("list", mgr.listWareHouseInfo(wInfo));
			setTotalCount(mgr.listWareHouseInfoCount(wInfo));
			setResult("count", getTotalCount());
			logger.debug("end doList");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("ÄÚ²¿´íÎó");
			return ERROR;
		}

	}
}
