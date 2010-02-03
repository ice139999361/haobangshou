/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousereceive.manager;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.warehouse.dao.WarehouseRecDetailDao;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;
import com.hbs.warehouse.common.constants.WareHouseConstants;

/**
 * @author Administrator
 *
 */
public class WareHouseRecDetailMgr {

	
	public int saveWareHouseRecDetail(WarehouseRecDetail detail) throws Exception{
		int ret =0;
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		WarehouseRecDetail existDetail = detailDao.findWarehouseRecDetailByBizKey(detail);
		
		return ret;
	}
}
