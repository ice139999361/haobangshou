/**
 * system ��hbs
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
	 * ������ⵥ��ȡ������������
	 * @param vOrder
	 * @return
	 * @throws Exception
	 */
	public String getPeriod(WarehouseRecInfo whrInfo)throws Exception;
}
