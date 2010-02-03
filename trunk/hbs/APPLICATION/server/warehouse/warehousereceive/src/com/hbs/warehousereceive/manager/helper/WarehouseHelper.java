/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousereceive.manager.helper;


import com.hbs.domain.warehouse.pojo.WarehouseRecInfo;

/**
 * @author Administrator
 *
 */
public interface WarehouseHelper {

	
	/**
	 * 根据入库单获取单据所属账期
	 * @param vOrder
	 * @return
	 * @throws Exception
	 */
	public String getPeriod(WarehouseRecInfo whrInfo)throws Exception;
}
