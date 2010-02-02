/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousereceive.manager;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.warehouse.dao.WarehouseRecInfoDao;
import com.hbs.domain.warehouse.pojo.WarehouseRecInfo;
import com.hbs.warehouse.common.constants.WareHouseConstants;

/**
 * @author Administrator
 *
 */
public class WareHouseRecMgr {
	private static final Logger logger = Logger.getLogger(WareHouseRecMgr.class);
	
	
	public int saveWareHouseRecInfo(WarehouseRecInfo whrInfo) throws Exception{
		int ret =0;
		logger.debug("保存入库单信息，传入的参数为：" + whrInfo.toString());
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		//根据传入的参数按照业务主键查询是否存在入库单
		WarehouseRecInfo existInfo = whrInfoDao.findWarehouseRecInfo(whrInfo);
		if(null == existInfo){//不存在，需要insert操作
			
		}else{//存在,做修改操作
			//判断入库单状态
			String state = existInfo.getState();
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//可以修改
				
			}else{//状态不正确，不能修改
				ret = -1;
			}
		}
		
		return ret;
	}
	
}
