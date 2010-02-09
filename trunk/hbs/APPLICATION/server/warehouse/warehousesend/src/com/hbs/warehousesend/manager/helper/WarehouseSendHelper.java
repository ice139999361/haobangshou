/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousesend.manager.helper;


import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;
import com.hbs.domain.warehouse.pojo.WarehouseSendInfo;

/**
 * @author Administrator
 *
 */
public interface WarehouseSendHelper {

	/**
	 * 根据出库单获取单据所属账期
	 * @param vOrder
	 * @return
	 * @throws Exception
	 */
	public String getPeriod(WarehouseSendInfo whrInfo)throws Exception;
	
	/**
	 * 根据出库单明细获取单据明细所属账期
	 * @param whrInfoDetail
	 * @return
	 * @throws Exception
	 */
	public String getPeriod(WarehouseSendDetail whrInfoDetail)throws Exception;
}
