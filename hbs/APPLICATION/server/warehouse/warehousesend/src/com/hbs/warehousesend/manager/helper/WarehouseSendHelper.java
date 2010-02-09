/**
 * system ��hbs
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
	 * ���ݳ��ⵥ��ȡ������������
	 * @param vOrder
	 * @return
	 * @throws Exception
	 */
	public String getPeriod(WarehouseSendInfo whrInfo)throws Exception;
	
	/**
	 * ���ݳ��ⵥ��ϸ��ȡ������ϸ��������
	 * @param whrInfoDetail
	 * @return
	 * @throws Exception
	 */
	public String getPeriod(WarehouseSendDetail whrInfoDetail)throws Exception;
}
