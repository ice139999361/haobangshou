/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousesend.manager;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.warehouse.dao.WarehouseSendInfoDao;
import com.hbs.domain.warehouse.pojo.WarehouseSendInfo;
import com.hbs.warehouse.common.constants.WareHouseConstants;

/**
 * @author Administrator
 *
 */
public class WareHouseSendMgr {
	private static final Logger logger = Logger.getLogger(WareHouseSendMgr.class);
	
	public int saveWareHouseSendInfo(WarehouseSendInfo sendInfo,String content) throws Exception{
		int ret =0;
		String st = sendInfo.getState();
		String sendPoNo = sendInfo.getSendPoNo();
		if(st.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_01)){
			logger.debug("保存出库单信息，传入的参数为：" + sendInfo.toString());
		}else{
			logger.debug("确认出库单信息，传入的参数为：" + sendInfo.toString());
		}
		WarehouseSendInfoDao whInfoDao = (WarehouseSendInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_INFO_DAO);
		
		if(null == sendPoNo){//不存在出货单的单号
			
		}else{//存在出货单的单号
			//查询出货单信息
			WarehouseSendInfo existInfo = whInfoDao.findWarehouseSendInfo(sendInfo);
			if(null == existInfo){//无此出货单信息
				logger.debug("根据传入的出库单信息，在库中无法查询到，不能执行操作!");
				ret = -2;
			}else{
				String state = existInfo.getState();
				if(state.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_01)){//状态为临时状态，可以修改
					
				}else{//状态为非临时状态，不能修改
					logger.debug("根据传入的出库单信息，查询的状态为：" + state +"不能做修改操作！");
					ret =-1;
				}
			}
			
		}
		return ret;
	}
}
