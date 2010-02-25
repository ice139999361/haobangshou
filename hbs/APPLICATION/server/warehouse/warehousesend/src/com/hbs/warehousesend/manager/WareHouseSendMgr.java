/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousesend.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.manager.syssequence.SysSequenceMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.warehouse.dao.WarehouseSendInfoDao;

import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;
import com.hbs.domain.warehouse.pojo.WarehouseSendInfo;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;


import com.hbs.warehousesend.manager.helper.WarehouseSendHelper;

/**
 * @author Administrator
 *
 */
public class WareHouseSendMgr {
	private static final Logger logger = Logger.getLogger(WareHouseSendMgr.class);
	/**
	 * 保存客户出货信息，分为2种情况，
	 * 状态为01 说明是保存临时出货信息
	 *          判断库中是否存在，存在，做update操作
	 *                          不存在，做insert操作
	 *  状态为02 ，说明用户是没有保存临时出货信息，直接提交出货信息
	 *  如果包括客户出货明细，则还需要处理客户明细信息
	 *  如果存在出库单明细，特别注意 本客户特定库存出货数量和通用库存出货数量
	 * @param sendInfo  出货信息
	 * @param content   说明信息，做日志用
	 * @return
	 * @throws Exception
	 */
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
		//设置账期，以物料出货时间为基准计算
		WarehouseSendHelper helper =(WarehouseSendHelper)BeanLocator.getInstance().getBean(WareHouseConstants.PRE_SPRING_SEND + sendInfo.getPoNoType() + sendInfo.getSettlementType());
		sendInfo.setPeriod(helper.getPeriod(sendInfo));
		if(StringUtils.isEmpty(sendPoNo) ){//不存在出货单的单号,保存新增出货单
			//设置系统产生的出货单号
			sendInfo.setSendPoNo(SysSequenceMgr.getPoNo(WareHouseConstants.CUST_SEND_PONO));
			whInfoDao.insertWarehouseSendInfo(sendInfo);
			WareHouseLogUtils.operLog(sendInfo.getOperId(), sendInfo.getOperStaff(), (sendInfo.getState().equals(WareHouseConstants.WAREHOUSE_SEND_INFO_02) ? "确认" : "新增"), "客户物料出库", sendInfo.getLogKey(), null, content);
		}else{//存在出货单的单号
			//查询出货单信息
			WarehouseSendInfo existInfo = whInfoDao.findWarehouseSendInfo(sendInfo);
			if(null == existInfo){//无此出货单信息
				logger.debug("根据传入的出库单信息，在库中无法查询到，不能执行操作!");
				ret = -2;
			}else{
				String state = existInfo.getState();
				if(state.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_01)){//状态为临时状态，可以修改
					whInfoDao.updateWarehouseSendInfo(sendInfo);
					WareHouseLogUtils.operLog(sendInfo.getOperId(), sendInfo.getOperStaff(), (sendInfo.getState().equals(WareHouseConstants.WAREHOUSE_SEND_INFO_02) ? "确认" : "修改"), "供应商物料入库", sendInfo.getLogKey(), null, content);
				}else{//状态为非临时状态，不能修改
					logger.debug("根据传入的出库单信息，查询的状态为：" + state +"不能做修改操作！");
					ret =-1;
				}
			}
			
		}
		//处理出货单明细
		if(ret ==0){
			List<WarehouseSendDetail> detailList = sendInfo.getDetailList();
			if(null != detailList && detailList.size() >0){
				if(st.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){
					logger.debug("保存出库单信息，出库单下存在出库单明细，保存出库单明细，数量为：" + detailList.size());
				}else{
					logger.debug("确认出库单信息，出库单下存在出库单明细，确认出库单明细，数量为：" + detailList.size());
				}
				for(WarehouseSendDetail detail : detailList){
					detail.setState(sendInfo.getState());
					if(StringUtils.isEmpty(detail.getSendPoNo())){
						detail.setSendPoNo(sendInfo.getSendPoNo());
					}
				}
				WareHouseSendDetailMgr detailMgr = (WareHouseSendDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAILMGR);
				detailMgr.saveWareHouseSendDetailList(detailList, true, content);
			}
		}
		return ret;
	}
	/**
	 * 取消出库单，同时取消该出库单下的所有明细
	 * 首先判断该出库单是否存在，不存在返回-2 表示要取消的入库单不存在
	 * 存在该出库单，判断出库单的状态，状态为临时，可以取消
	 * 否则不允许取消出库单
	 * @param sendInfo
	 * @param content 取消意见
	 * @return
	 * @throws Exception
	 */
	public int cancelWarehouseSendInfo(WarehouseSendInfo sendInfo,String content) throws Exception{
		int ret =0;
		logger.debug("取消出库单操作，传入的参数为：" + sendInfo.toString());
		//根据传入的参数，查询出库单
		WarehouseSendInfoDao whInfoDao = (WarehouseSendInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_INFO_DAO);
		WarehouseSendInfo existInfo = whInfoDao.findWarehouseSendInfo(sendInfo);
		if(null != existInfo){//存在出库单
			String state = existInfo.getState();
			if(state.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_01)){//临时状态可以取消
				sendInfo.setState(WareHouseConstants.WAREHOUSE_SEND_INFO_03);
				whInfoDao.updateWarehouseSendInfoByState(sendInfo);
				WareHouseLogUtils.operLog(sendInfo.getOperId(), sendInfo.getOperStaff(),"取消", "客户物料出库", sendInfo.getLogKey(), null, content);
				//处理该出库单下的明细
				List<WarehouseSendDetail> detailList = sendInfo.getDetailList();
				if(null != detailList && detailList.size() >0){
					logger.debug("取消出库单信息，出库单下存在出库单明细，取消出库单明细，数量为：" + detailList.size());
					WareHouseSendDetailMgr detailMgr = (WareHouseSendDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAILMGR);
					detailMgr.cancelWareHouseSendDetailList(detailList, true, content);
				}
			}else{//非临时状态，不能取消
				logger.debug("取消出库单信息,存在的出库单状态为：" + existInfo.getState() +"无法做取消操作");
				ret =-1;
			}
		}else{//出库单不存在，无法执行取消操作
			logger.debug("取消出库单信息,根据传入的参数，无法找到对应的出库单，无法取消！");
			ret = -2;
		}
		return ret;
	}
	/**
	 * 确认出库单，同时确认该出库单下的所有明细
	 * 对应确认操作来说，出库单明细不能单独操作，必须随出库单操作
	 * @param sendInfo
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int corfirmWarehouseSendInfo(WarehouseSendInfo sendInfo,String content) throws Exception{
		int ret =0;
		//设置出库单的状态为确认，调用保存方法执行
		logger.debug("确认出库单操作设置出库单的状态为确认，调用保存方法执行，参数为：" + sendInfo.toString());
		sendInfo.setState(WareHouseConstants.WAREHOUSE_SEND_INFO_02);
		ret = saveWareHouseSendInfo(sendInfo,content);
		return ret;
	}
	/**
	 * 出库单的暂停/激活操作，传入的参数不需要修改原始状态
	   暂停/激活 只针对出库单 ，同时包括所有该出库单名下的所有明细
	 * 由系统做反向操作 
	 * @param sendInfo
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int controlActiveState(WarehouseSendInfo sendInfo,String content) throws Exception{
		int ret =0;
		logger.debug("供应商出库单暂停/激活操作，传入的参数为：" + sendInfo.toString());
		String activeState = sendInfo.getActiveState();
		if(activeState.equals(WareHouseConstants.WAREHOUSE_SEND_ACTIVE)){
			sendInfo.setActiveState(WareHouseConstants.WAREHOUSE_SEND_PAUSE);
		}else{
			sendInfo.setActiveState(WareHouseConstants.WAREHOUSE_SEND_ACTIVE);
		}
		WarehouseSendInfoDao whInfoDao = (WarehouseSendInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_INFO_DAO);
		whInfoDao.updateWarehouseSendInfoByActiveState(sendInfo);
		//记录操作日志
		WareHouseLogUtils.operLog(sendInfo.getOperId(), sendInfo.getOperStaff(), (activeState.equals(WareHouseConstants.WAREHOUSE_SEND_ACTIVE) ? "激活" : "暂停"), "客户物料出库", sendInfo.getLogKey(), null, content);
		//出库单明细处理
		List<WarehouseSendDetail> detailList = sendInfo.getDetailList();
		if(null != detailList && detailList.size()>0){
			logger.debug("该出库单下包括的明细数目为：" + detailList.size());
			WareHouseSendDetailMgr detailMgr = (WareHouseSendDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAILMGR);
			detailMgr.cancelWareHouseSendDetailList(detailList, true, content);
		}
		return ret;
	}
	/**
	 * 查询单条出库单信息，条件为：SEND_PO_NO = #sendPoNo# AND C_CODE=#custCode#
	 * @param sendInfo
	 * @param isDetail  是否同时查询出出库单明细 true 查  false 不查
	 * @return
	 * @throws Exception
	 */
	public WarehouseSendInfo getWarehouseSendInfo(WarehouseSendInfo sendInfo ,boolean isDetail) throws Exception{
		WarehouseSendInfo retInfo = null;
		logger.debug("查询单条出库单，输入的参数为：" + sendInfo.toString());
		WarehouseSendInfoDao whInfoDao = (WarehouseSendInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_INFO_DAO);
		retInfo = whInfoDao.findWarehouseSendInfo(sendInfo);
		//处理出库单明细
		if(null != retInfo && isDetail){
			logger.debug("需要查询出库单明细！");
			WareHouseSendDetailMgr detailMgr = (WareHouseSendDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAILMGR);
			WarehouseSendDetail detail = new WarehouseSendDetail();
			detail.setSendPoNo(sendInfo.getSendPoNo());
			detail.setCustCode(sendInfo.getCustCode());
			detail.setSettlementType(sendInfo.getSettlementType());
			detail.setPoNoType(sendInfo.getPoNoType());
			retInfo.setDetailList(detailMgr.listWarehouseSendDetail(detail));
		}
		return retInfo;
	}
	/**
	 * 根据输入的条件查询出库库单列表
	 * @param sendInfo
	 * @return
	 * @throws Exception
	 */
	public List<WarehouseSendInfo> listWarehouseSendInfo(WarehouseSendInfo sendInfo ) throws Exception{
		logger.debug("查询出库单列表，输入的参数为：" + sendInfo.toString());
		WarehouseSendInfoDao whInfoDao = (WarehouseSendInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_INFO_DAO);
		return whInfoDao.listWarehouseSendInfo(sendInfo);
	}
	/**
	 * 根据输入的条件查询出库库单列表数目
	 * @param sendInfo
	 * @param isDetail
	 * @return
	 * @throws Exception
	 */
	public Integer listWarehouseSendInfoCount(WarehouseSendInfo sendInfo ,boolean isDetail) throws Exception{
		logger.debug("查询出库单列表数量，输入的参数为：" + sendInfo.toString());
		WarehouseSendInfoDao whInfoDao = (WarehouseSendInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_INFO_DAO);
		return whInfoDao.listWarehouseSendInfoCount(sendInfo);
	}
}
