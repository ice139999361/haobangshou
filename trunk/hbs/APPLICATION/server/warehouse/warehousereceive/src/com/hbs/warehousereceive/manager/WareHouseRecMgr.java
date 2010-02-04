/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousereceive.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.warehouse.dao.WarehouseRecInfoDao;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;
import com.hbs.domain.warehouse.pojo.WarehouseRecInfo;

import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;
import com.hbs.warehousereceive.manager.helper.WarehouseHelper;

/**
 * @author Administrator
 *
 */
public class WareHouseRecMgr {
	private static final Logger logger = Logger.getLogger(WareHouseRecMgr.class);
	
	/**
	 * 保存入库单，包括明细，
	 * 首先判断是否存在，不存在，做insert操作
	 * 否则update操作，如果状态不正确，则返回-1
	 * @param whrInfo
	 * @param content
	 * @return 0  成功  -1 状态不正确，不允许保存
	 * @throws Exception
	 */
	public int saveWareHouseRecInfo(WarehouseRecInfo whrInfo,String content) throws Exception{
		int ret =0;
		logger.debug("保存入库单信息，传入的参数为：" + whrInfo.toString());
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		//根据传入的参数按照业务主键查询是否存在入库单
		WarehouseRecInfo existInfo = whrInfoDao.findWarehouseRecInfo(whrInfo);
		if(null == existInfo){//不存在，需要insert操作
			//设置账期，以物料到达时间为基准计算
			WarehouseHelper helper =(WarehouseHelper)BeanLocator.getInstance().getBean(WareHouseConstants.PRE_SPRING + whrInfo.getPoNoType() + whrInfo.getSettlementType());
			whrInfo.setPeriod(helper.getPeriod(whrInfo));
			whrInfo.setState(WareHouseConstants.WAREHOUSE_REC_INFO_01);
			whrInfoDao.insertWarehouseRecInfo(whrInfo);
			WareHouseLogUtils.operLog(whrInfo.getOperId(), whrInfo.getOperStaff(), "新增", "供应商物料入库", whrInfo.getLogKey(), null, content);

			
		}else{//存在,做修改操作
			//判断入库单状态
			String state = existInfo.getState();
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//可以修改
				whrInfoDao.updateWarehouseRecInfo(whrInfo);
				WareHouseLogUtils.operLog(whrInfo.getOperId(), whrInfo.getOperStaff(), "修改", "供应商物料入库", whrInfo.getLogKey(), null, content);
			}else{//状态不正确，不能修改
				ret = -1;
			}
		}
		if(ret ==0){//处理入库单明细
			List<WarehouseRecDetail> detailList = whrInfo.getDetailList();
			if(null != detailList){//入库单明细处理
				logger.debug("保存入库单信息，入库单下存在入库单明细，保存入库单明细，数量为：" + detailList.size());
				WareHouseRecDetailMgr detailMgr = (WareHouseRecDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAILMGR);
				detailMgr.saveWareHouseRecDetailList(detailList, true, content);
			}
		}
		
		return ret;
	}
	
	public int cancelWareHouseRecInfo(WarehouseRecInfo whrInfo,String content) throws Exception{
		int ret =0;
		logger.debug("取消入库单信息，传入的参数为：" + whrInfo.toString());
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		//根据传入的参数按照业务主键查询是否存在入库单
		WarehouseRecInfo existInfo = whrInfoDao.findWarehouseRecInfo(whrInfo);
		if(null != existInfo){//存在
			String state = existInfo.getState();
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//可以取消
				whrInfo.setState(WareHouseConstants.WAREHOUSE_REC_INFO_03);
				whrInfoDao.updateWarehouseRecInfoByState(whrInfo);
				WareHouseLogUtils.operLog(whrInfo.getOperId(), whrInfo.getOperStaff(), "取消", "供应商物料入库", whrInfo.getLogKey(), null, content);
				
				//入库单明细
				List<WarehouseRecDetail> detailList = whrInfo.getDetailList();
				if(null != detailList){//入库单明细处理
					
				}
			}else{//状态不正确，不能取消
				logger.debug("取消入库单信息,存在的入库单状态为：" + existInfo.getState() +"无法做取消操作");
				ret =-1;
			}
			
		}else{//要取消的入库单不存在
			logger.debug("取消入库单信息,根据传入的参数，无法找到对应的入库单，无法取消！");
			ret = -2;
		}
		
		return ret;
	}
	
}
