/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.manager;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import com.hbs.common.springhelper.BeanLocator;

import com.hbs.common.utils.ExpireTimeUtil;
import com.hbs.customer.common.utils.CustLogUtils;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.utils.CustOrderUtils;
import com.hbs.domain.adjust.dao.AdjustInfoDao;
import com.hbs.domain.adjust.pojo.AdjustInfo;

import com.hbs.domain.waittask.pojo.WaitTaskInfo;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.warehouse.manager.WarehouseMgr;


public class AdjustMgr {

	private static final Logger logger = Logger.getLogger(AdjustMgr.class);
	
	
	/**
	 * 提交调货申请，包括新提交和修改后提交，区别在于：
	 * 修改后提交有sequence，新提交没有sequence
	 * @param adjustInfo
	 * @return > 0 提交成功，-1 库存不存在申请的调货物料 -2 库存中的调货物料不满足调货
	 * @throws Exception
	 */
	public int saveAdjustInfo(AdjustInfo adjustInfo) throws Exception{
		int ret =0;
		logger.debug("调货操作保存！输入的信息为：" + adjustInfo.toString());
		//获取申请的调货数量		
		int iMount = adjustInfo.getApplyAmount();
		//组合库存信息
		WarehouseMgr whMgr = (WarehouseMgr)BeanLocator.getInstance().getBean(CustOrderConstants.WAREHOUSE_INFO_MGR);
		WareHouseInfo wObject = createFromWInfoObject(adjustInfo , true);
		logger.debug("查询库存信息：输入的查询参数为：" + wObject.toString());
		//从仓库中获取被调货客户的库存数量，判断是否满足调货
		WareHouseInfo wInfo = getWareHouseInfo(whMgr , wObject);
		if(wInfo != null){
			int iUseMount = wInfo.getUseAmount();
			if(iMount > iUseMount){
				logger.debug("库存中的被调货库存物料数量小于需要的调货数量，无法执行调货！");
				ret = -2;
			}else{//先锁定库存调货的数码，再保存调货信息，供领导审批
				wObject.setHouseSeqId(wInfo.getHouseSeqId());
				wObject.setLockAmount(iMount);
				wObject.setUseAmount(-iMount);
				lockWareHouseInfo(whMgr,wObject);
				
				//保存调货信息，等待审批
				AdjustInfoDao justDao =(AdjustInfoDao)BeanLocator.getInstance().getBean(CustOrderConstants.ADJUST_INFO_DAO);
				if(adjustInfo.getApplySeqId() != null){//修改调货
					adjustInfo.setAuditAgree(null);
					adjustInfo.setAuditContent(null);
					adjustInfo.setAuditStaffId(null);
					adjustInfo.setAuditStaffName(null);
					justDao.updateAdjustInfo(adjustInfo);
					ret = adjustInfo.getApplySeqId().intValue();
				}else{//新增调货
					ret = justDao.insertAdjustInfo(adjustInfo);
				}
				adjustInfo.setApplySeqId(ret);
				//处理待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();				
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", adjustInfo.getStaffName());
				hmParam.put("$partNo", adjustInfo.getPartNo());
				waitTaskInfo.setHmParam(hmParam);	
				waitTaskInfo.setBusinessKey(adjustInfo.getBizKey());
				CustOrderUtils.processCreateWaitTask("ADJUSTMENT_001",null, waitTaskInfo);
				//处理日志
				CustLogUtils.operLog(adjustInfo.getStaffId(), adjustInfo.getStaffName(), "提交", "调货申请", adjustInfo.getLogBizKey(), null, adjustInfo.getApplyContent());
			}
		}else{
			logger.debug("库存中不存在能够被调货的库存信息！无法执行调货!");
			ret = -1;
		}
		return ret;
	}
	/**
	 * 审批同意，先从被调方出库，再调方入库，最后保存审批信息。
	 * @param adjustInfo
	 * @return 0 成功
	 * @throws Exception
	 */
	public int agreeAdjustInfo(AdjustInfo adjustInfo) throws Exception{
		int ret =0;
		logger.debug("审批同意调货，输入的参数为：" + adjustInfo.toString());
		//组合库存信息
		WarehouseMgr whMgr = (WarehouseMgr)BeanLocator.getInstance().getBean(CustOrderConstants.WAREHOUSE_INFO_MGR);
		
		//从被调方库存出货
		WareHouseInfo wFromObject = createFromWInfoObject(adjustInfo , true);
		wFromObject.setLockAmount(adjustInfo.getApplyAmount().intValue());
		wFromObject.setTotalAmount(adjustInfo.getApplyAmount().intValue());
		whMgr.saveOutWareHouseInfo(wFromObject);		
		//调方入库
		WareHouseInfo wToObject = createFromWInfoObject(adjustInfo , false);
		wToObject.setTotalAmount(adjustInfo.getApplyAmount().intValue());
		wToObject.setUseAmount(adjustInfo.getApplyAmount().intValue());
		whMgr.saveInWareHouseInfo(wToObject);
		//保存调货信息
		AdjustInfoDao justDao =(AdjustInfoDao)BeanLocator.getInstance().getBean(CustOrderConstants.ADJUST_INFO_DAO);
		justDao.updateAdjustInfo(adjustInfo);
		//待办
		WaitTaskInfo waitTaskInfo = new WaitTaskInfo();				
		Map<String , String> hmParam = new HashMap<String,String>();
		waitTaskInfo.setStaffId(adjustInfo.getStaffId());
		hmParam.put("$staffName", adjustInfo.getAuditStaffName());
		hmParam.put("$partNo", adjustInfo.getPartNo());
		waitTaskInfo.setHmParam(hmParam);
		waitTaskInfo.setBusinessKey(adjustInfo.getBizKey());
		waitTaskInfo.setExpireTime(ExpireTimeUtil.getExpireTime("ADJUST_REMINDER_DAY"));
		CustOrderUtils.processCreateWaitTask("ADJUSTMENT_002",null, waitTaskInfo);
		//日志
		CustLogUtils.operLog(adjustInfo.getAuditStaffId(), adjustInfo.getAuditStaffName(), "审批同意", "调货申请", adjustInfo.getLogBizKey(), null, adjustInfo.getAuditContent());
		return ret;
	}
	/**
	 * 审批不同意调货，对锁定的库存解锁
	 * @param adjustInfo
	 * @return >0 成功
	 * @throws Exception
	 */
	public int disAgreeAdjustInfo(AdjustInfo adjustInfo) throws Exception{
		int ret =0;
		logger.debug("审批不同意调货，输入的参数为：" + adjustInfo.toString());
		//组合库存信息
		WarehouseMgr whMgr = (WarehouseMgr)BeanLocator.getInstance().getBean(CustOrderConstants.WAREHOUSE_INFO_MGR);
		
		//从被调方库存出货
		WareHouseInfo wFromObject = createFromWInfoObject(adjustInfo , true);
		wFromObject.setLockAmount(- adjustInfo.getApplyAmount().intValue());
		wFromObject.setUseAmount(adjustInfo.getApplyAmount().intValue());
		whMgr.saveLockWareHouseInfo(wFromObject, null, null, null);
		
		//保存调货信息
		AdjustInfoDao justDao =(AdjustInfoDao)BeanLocator.getInstance().getBean(CustOrderConstants.ADJUST_INFO_DAO);
		justDao.updateAdjustInfo(adjustInfo);
		ret = adjustInfo.getApplySeqId();
		//待办
		WaitTaskInfo waitTaskInfo = new WaitTaskInfo();				
		Map<String , String> hmParam = new HashMap<String,String>();
		waitTaskInfo.setStaffId(adjustInfo.getStaffId());
		hmParam.put("$staffName", adjustInfo.getAuditStaffName());
		hmParam.put("$partNo", adjustInfo.getPartNo());
		waitTaskInfo.setHmParam(hmParam);	
		waitTaskInfo.setBusinessKey(adjustInfo.getBizKey());
		waitTaskInfo.setExpireTime(ExpireTimeUtil.getExpireTime("ADJUST_REMINDER_DAY"));
		CustOrderUtils.processCreateWaitTask("ADJUSTMENT_003",null, waitTaskInfo);
		//日志
		CustLogUtils.operLog(adjustInfo.getAuditStaffId(), adjustInfo.getAuditStaffName(), "审批不同意", "调货申请", adjustInfo.getLogBizKey(), null, adjustInfo.getAuditContent());
		return ret;
	}
	/**
	 * 根据sequence主键,查询调货信息
	 * @param id  主键
	 * @return
	 * @throws Exception
	 */
	public AdjustInfo getAdjustInfo(String id) throws Exception{
		AdjustInfoDao justDao =(AdjustInfoDao)BeanLocator.getInstance().getBean(CustOrderConstants.ADJUST_INFO_DAO);
		return justDao.findAdjustInfo(id);
	}
	/**
	 * 查询符合条件的调货 ,支持分页
	 * @param adjustInfo
	 * @return
	 * @throws Exception
	 */
	public List<AdjustInfo> getAdjustInfoList(AdjustInfo adjustInfo) throws Exception{
		AdjustInfoDao justDao =(AdjustInfoDao)BeanLocator.getInstance().getBean(CustOrderConstants.ADJUST_INFO_DAO);
		return justDao.listAdjustInfo(adjustInfo);
	}
	
	/**
	 * 查询符合条件的调货数量
	 * @param adjustInfo
	 * @return
	 * @throws Exception
	 */
	public Integer getAdjustInfoListCount(AdjustInfo adjustInfo) throws Exception{
		AdjustInfoDao justDao =(AdjustInfoDao)BeanLocator.getInstance().getBean(CustOrderConstants.ADJUST_INFO_DAO);
		return justDao.listAdjustInfoCount(adjustInfo);
	}
	/**
	 * 根据调货信息，查询库存情况
	 * @param wObject
	 * @return
	 * @throws Exception
	 */
	private WareHouseInfo getWareHouseInfo(WarehouseMgr whMgr,WareHouseInfo wObject) throws Exception{
		WareHouseInfo ret = null;		
		ret = whMgr.getWareHouseInfoByBizKey(wObject);
		logger.debug("查询库存的结果为：" + ret == null ? null : ret.toString());
		return ret;
		
	}
	
	private void lockWareHouseInfo(WarehouseMgr whMgr,WareHouseInfo wObject)throws Exception{
		whMgr.saveLockWareHouseInfo(wObject, null, null, null);
	}
	
	/**
	 * 根据调货信息，创建库存信息
	 * @param adjustInfo
	 * @param isFrom true 被调货货方  false  调货方
	 * @return
	 */
	private WareHouseInfo createFromWInfoObject(AdjustInfo adjustInfo , boolean isFrom){
		WareHouseInfo wInfo = new WareHouseInfo();
		//仓库位置
		wInfo.setHouseType(adjustInfo.getHouseType());
		//仓库类型
		wInfo.setHouseUse(CustOrderConstants.WAREHOUSE_INFO_HOUSE_USE_1);
		//设置供应商代码
		wInfo.setVendorCode(adjustInfo.getVendorCode());
		//设置物料编号
		wInfo.setPartNo(adjustInfo.getPartNo());
		//设置客户
		if(isFrom){
			wInfo.setCustCode(adjustInfo.getFromCustCode());
		}else{
			wInfo.setCustCode(adjustInfo.getToCustCode());
		}
		return wInfo;
	}
	
	
}
