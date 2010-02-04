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
import com.hbs.common.utils.OrderCalUtils;
import com.hbs.domain.warehouse.dao.WarehouseRecDetailDao;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;
import com.hbs.warehousereceive.manager.helper.WarehouseHelper;

/**
 * @author Administrator
 *
 */
public class WareHouseRecDetailMgr {
	private static final Logger logger = Logger.getLogger(WareHouseRecDetailMgr.class);
	
	/**
	 * 保存入库单明细，保存时先判断库中是否存在，不存在insert操作
	 * 否则 update操作，update操作的状态必须是 01 临时状态才能保存修改
	 * @param detail
	 * @param isflowRec 是否跟随主入库单同时保存
	 * @param content   保存意见
	 * @return  >= 0 成功  -1 此状态的明细不允许修改
	 * @throws Exception
	 */
	public int saveWareHouseRecDetail(WarehouseRecDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("保存物料明细，传入的参数为:" + detail.toString());
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		WarehouseRecDetail existDetail = detailDao.findWarehouseRecDetailByBizKey(detail);
		if(null == existDetail){//不存在，insert操作，
			logger.debug("数据库中无此入库单明细，insert操作！");
			//计算金额
			detail.setCurrMoney(OrderCalUtils.calOrderMoney(detail.getPrice(), detail.getIsTax(), detail.getTaxRate(), detail.getAmount()));
			//设置账期
			String period = detail.getPeriod();
			if(period == null){
				WarehouseHelper helper =(WarehouseHelper)BeanLocator.getInstance().getBean(WareHouseConstants.PRE_SPRING + detail.getPoNoType() + detail.getSettlementType());
				detail.setPeriod(helper.getPeriod(detail));
				detail.setFinancePeriod(detail.getPeriod());				
			}
			Integer id = detailDao.insertWarehouseRecDetail(detail);
			detail.setRecDetailSeqId(id);
			ret = id;
			WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), "新增", "入库单明细", detail.getLogKey(), null, content);
		}else{//做update操作
			String state = existDetail.getState();
			logger.debug("数据库中存在入库单明细，update操作！明细状态为：" + state);
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//状态为临时，可以修改
				//计算金额
				detail.setCurrMoney(OrderCalUtils.calOrderMoney(detail.getPrice(), detail.getIsTax(), detail.getTaxRate(), detail.getAmount()));
				detail.setRecDetailSeqId(existDetail.getRecDetailSeqId());
				detailDao.updateWarehouseRecDetail(detail);
				WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), "修改", "入库单明细", detail.getLogKey(), null, content);
			}else{//状态不正确，不允许修改
				logger.debug("数据库中存在入库单明细，update操作！状态不正确，不允许修改！");
				ret = -1;
			}
			ret = detail.getRecDetailSeqId();
		}
		return ret;
	}
	
	/**
	 * 批量保存入库单明细，调用单条保存方法
	 * @param detailList
	 * @param isflowRec  是否跟随主订单保存
	 * @param content  备注，记录日志
	 * @return  0 成功 
	 * @throws Exception
	 */
	public int saveWareHouseRecDetailList(List<WarehouseRecDetail> detailList,boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("批量保存入库单明细，明细的数量为:" + detailList.size());
		for(WarehouseRecDetail detail : detailList){
			Integer i = saveWareHouseRecDetail(detail,isflowRec,content);
			logger.debug("批量保存入库单明细,保存的结果为：" + i);
		}
		return ret;
	}
	
	
	public int cancelWareHouseRecDetail(WarehouseRecDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("取消入库单明细信息,传入的参数为：" + detail.toString());
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		WarehouseRecDetail existDetail = detailDao.findWarehouseRecDetailByBizKey(detail);
		if(null != existDetail){//存在入库单明细
			
		}else{
			ret = -2;
		}
		return ret;
	}
}
