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
		String st = whrInfo.getState();
		if(st.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){
			logger.debug("保存入库单信息，传入的参数为：" + whrInfo.toString());
		}else{
			logger.debug("确认入库单信息，传入的参数为：" + whrInfo.toString());
		}
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		//设置账期，以物料到达时间为基准计算
		WarehouseHelper helper =(WarehouseHelper)BeanLocator.getInstance().getBean(WareHouseConstants.PRE_SPRING + whrInfo.getPoNoType() + whrInfo.getSettlementType());
		whrInfo.setPeriod(helper.getPeriod(whrInfo));
		//根据传入的参数按照业务主键查询是否存在入库单
		WarehouseRecInfo existInfo = whrInfoDao.findWarehouseRecInfo(whrInfo);
		if(null == existInfo){//不存在，需要insert操作			
			//whrInfo.setState(WareHouseConstants.WAREHOUSE_REC_INFO_01);
			whrInfoDao.insertWarehouseRecInfo(whrInfo);
			WareHouseLogUtils.operLog(whrInfo.getOperId(), whrInfo.getOperStaff(), (whrInfo.getState().equals(WareHouseConstants.WAREHOUSE_REC_INFO_02) ? "确认" : "新增"), "供应商物料入库", whrInfo.getLogKey(), null, content);

		}else{//存在,做修改操作
			//判断入库单状态
			String state = existInfo.getState();
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//可以修改
				whrInfoDao.updateWarehouseRecInfo(whrInfo);
				WareHouseLogUtils.operLog(whrInfo.getOperId(), whrInfo.getOperStaff(), (whrInfo.getState().equals(WareHouseConstants.WAREHOUSE_REC_INFO_02) ? "确认" : "修改"), "供应商物料入库", whrInfo.getLogKey(), null, content);
			}else{//状态不正确，不能修改
				ret = -1;
			}
		}
		if(ret ==0){//处理入库单明细
			List<WarehouseRecDetail> detailList = whrInfo.getDetailList();
			if(null != detailList){//入库单明细处理
				if(st.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){
					logger.debug("保存入库单信息，入库单下存在入库单明细，保存入库单明细，数量为：" + detailList.size());
				}else{
					logger.debug("确认入库单信息，入库单下存在入库单明细，确认入库单明细，数量为：" + detailList.size());
				}
				for(WarehouseRecDetail detail : detailList){
					detail.setState(whrInfo.getState());
				}
				WareHouseRecDetailMgr detailMgr = (WareHouseRecDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAILMGR);
				detailMgr.saveWareHouseRecDetailList(detailList, true, content);
			}
		}
		
		return ret;
	}
	/**
	 * 取消入库单，同时取消该入库单下的所有入库单明细
	 * 首先判断入库单是否存在，不存在，返回-2
	 * 否则 判断状态是否为临时状态，不是临时状态，不允许取消
	 * 是临时状态，取消入库单
	 * @param whrInfo
	 * @param content  取消意见
	 * @return 0--成功  -1 --状态不正确 -2 --入库单不存在
	 * @throws Exception
	 */
	public int cancelWareHouseRecInfo(WarehouseRecInfo whrInfo,String content) throws Exception{
		int ret =0;
		logger.debug("取消入库单信息，传入的参数为：" + whrInfo.toString());
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		//根据传入的参数按照业务主键查询是否存在入库单
		WarehouseRecInfo existInfo = whrInfoDao.findWarehouseRecInfo(whrInfo);
		if(null != existInfo){//存在
			String state = existInfo.getState();
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//可以取消
				existInfo.setState(WareHouseConstants.WAREHOUSE_REC_INFO_03);
				existInfo.setOperId(whrInfo.getOperId());
				existInfo.setOperStaff(whrInfo.getOperStaff());
				whrInfoDao.updateWarehouseRecInfoByState(existInfo);
				WareHouseLogUtils.operLog(existInfo.getOperId(), existInfo.getOperStaff(), "取消", "供应商物料入库", existInfo.getLogKey(), null, content);
				
				//入库单明细
				List<WarehouseRecDetail> detailList = whrInfo.getDetailList();
				WareHouseRecDetailMgr detailMgr = (WareHouseRecDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAILMGR);
				if(null == detailList){//入库单明细处理，前台有可能没有传列表数据，获取
					WarehouseRecDetail detail = new WarehouseRecDetail();
					detail.setRecPoNo(whrInfo.getRecPoNo());
					detail.setVendorCode(whrInfo.getVendorCode());
					detailList = detailMgr.getWarehouseRecDetailList(detail);
				}
				if(null != detailList && detailList.size() >0){//入库单明细处理
					logger.debug("取消入库单信息，入库单下存在入库单明细，取消入库单明细，数量为：" + detailList.size());					
					detailMgr.cancelWareHouseRecDetailList(detailList, true, content);
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
	
	/**
	 * 确认供应商入库单，同时确认入库单明细
	 * 对于确认来说，入库单明细不能独立操作，必须跟随入库单同时操作
	 * @param whrInfo
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int corfirmWareHouseRecInfo(WarehouseRecInfo whrInfo,String content) throws Exception{

		//设置状态为确认
		whrInfo.setState(WareHouseConstants.WAREHOUSE_REC_INFO_02);
		
		return saveWareHouseRecInfo(whrInfo,content);
	}
	/**
	 * 入库单的暂停/激活操作，传入的参数不需要修改原始状态
	 * 由系统做反向操作
	 * @param whrInfo
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int controlActiveState(WarehouseRecInfo whrInfo,String content) throws Exception{
		int ret =0;
		logger.debug("供应商入库单暂停/激活操作，传入的参数为：" + whrInfo.toString());
		String activeState = whrInfo.getActiveState();
		if(activeState.equals(WareHouseConstants.WAREHOUSE_REC_ACTIVE)){
			whrInfo.setActiveState(WareHouseConstants.WAREHOUSE_REC_PAUSE);
		}else{
			whrInfo.setActiveState(WareHouseConstants.WAREHOUSE_REC_ACTIVE);
		}
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		whrInfoDao.updateWarehouseRecInfoByActiveState(whrInfo);
		WareHouseLogUtils.operLog(whrInfo.getOperId(), whrInfo.getOperStaff(), (activeState.equals(WareHouseConstants.WAREHOUSE_REC_ACTIVE) ? "激活" : "暂停"), "供应商物料入库", whrInfo.getLogKey(), null, content);
		//入库单明细
		List<WarehouseRecDetail> detailList = whrInfo.getDetailList();
		if(null != detailList && detailList.size() >0){//入库单明细处理
			logger.debug("供应商入库单暂停/激活操作! 入库单明细数量为：" + detailList.size());
			WareHouseRecDetailMgr detailMgr = (WareHouseRecDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAILMGR);
			detailMgr.cancelWareHouseRecDetailList(detailList, true, content);
		}
		return ret;
	}
	/**
	 * 获取单条入库单信息  REC_PO_NO = #recPoNo# AND C_CODE=#vendorCode#
	 * @param whrInfo
	 * @param isDetail 是否需要明细
	 * @return
	 * @throws Exception
	 */
	public WarehouseRecInfo getWarehouseRecInfo(WarehouseRecInfo whrInfo , boolean isDetail) throws Exception{
		WarehouseRecInfo retInfo = null;
		logger.debug("查询单条入库单，输入的参数为：" + whrInfo.toString());
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		retInfo = whrInfoDao.findWarehouseRecInfo(whrInfo);
		if(isDetail && retInfo != null){
			logger.debug("需要查询入库单明细！");
			WareHouseRecDetailMgr detailMgr = (WareHouseRecDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAILMGR);
			WarehouseRecDetail detail = new WarehouseRecDetail();
			detail.setRecPoNo(retInfo.getRecPoNo());
			detail.setVendorCode(retInfo.getVendorCode());
			detail.setSettlementType(retInfo.getSettlementType());
			detail.setPoNoType(retInfo.getPoNoType());
			retInfo.setDetailList(detailMgr.getWarehouseRecDetailList(detail));
		}
		return retInfo;
	}
	
	/**
	 * 根据输入的条件查询入库单列表
	 * @param whrInfo
	 * @return
	 * @throws Exception
	 */
	public List<WarehouseRecInfo> listWarehouseRecInfo(WarehouseRecInfo whrInfo) throws Exception{
		logger.debug("查询入库单列表，输入的参数为：" + whrInfo.toString());
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		return whrInfoDao.listWarehouseRecInfo(whrInfo);
	}
	/**
	 * 根据输入的条件，查询满足条件的入库单数目
	 * @param whrInfo
	 * @return
	 * @throws Exception
	 */
	public Integer listWarehouseRecInfoCount(WarehouseRecInfo whrInfo) throws Exception{
		logger.debug("查询入库单列表总数，输入的参数为：" + whrInfo.toString());
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		return whrInfoDao.listWarehouseRecInfoCount(whrInfo);
	}
}
