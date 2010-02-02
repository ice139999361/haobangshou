/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehouse.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.warehouse.dao.WareHouseInfoDao;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;

/**
 * @author Administrator
 *
 */
public class WarehouseMgr {

	private static final Logger logger = Logger.getLogger(WarehouseMgr.class);
	
	/**
	 * 根据传入的业务主键查询仓库信息
	 * @param wInfo  业务主键包括：houseType 
	 * houseUse vendorCode partNo cpartNo custCode state
	 * @return  null 或 单条仓库信息 ，如果查询条件过于简单则会抛出多条的异常
	 * @throws Exception
	 */
	public WareHouseInfo getWareHouseInfoByBizKey(WareHouseInfo wInfo) throws Exception{
		return findWareHouseInfoByBizKey(null,wInfo);
	}
	
	/**
	 * 根据主键查询仓库信息
	 * @param houseSeqId
	 * @return  null 或 单条仓库信息
	 * @throws Exception
	 */
	public WareHouseInfo getWareHouseInfoById(String houseSeqId) throws Exception{
		WareHouseInfo retInfo = null;
		logger.debug("根据传入的主键查询仓库信息，主键为：" + houseSeqId);
		WareHouseInfoDao whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
		retInfo = whInfoDao.findWareHouseInfoById(houseSeqId);
		return retInfo;
	}
	
	/**
	 * 入库库存信息保存，先查询库存，如果存在则update操作，否则insert操作
	 * 操作前先判断 库存总数  = 锁定数量 + 可用数量 ，不相等，则抛出异常
	 * @param wInfo
	 * @return
	 * @throws Exception
	 */
	public int saveInWareHouseInfo(WareHouseInfo wInfo) throws Exception{
		int ret =0;
		logger.debug("保存入库库存信息传入的参数为：" + wInfo.toString());
		
		int wInfoTotalAmount = (wInfo.getTotalAmount() == null ? 0 : wInfo.getTotalAmount());
		int wInfoLockAmount = (wInfo.getLockAmount()== null ? 0 : wInfo.getLockAmount());
		int wInfoUseAmount = (wInfo.getUseAmount()== null ? 0 : wInfo.getUseAmount());
		
		if(wInfoLockAmount + wInfoUseAmount !=  wInfoTotalAmount){			
			StringBuilder sb = new StringBuilder("-1 保存的库存总数  != 锁定数量 + 可用数量  错误!");
			sb.append("保存的库存总数=").append(wInfoTotalAmount);
			sb.append("锁定数量=").append(wInfoLockAmount);
			sb.append("可用数量=").append(wInfoUseAmount);
			logger.debug(sb.toString());
			throw new Exception(sb.toString());
		}else{
			//查找库中是否存在业务主键的库存信息
			WareHouseInfoDao whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
			WareHouseInfo existWInfo = findWareHouseInfoByBizKey(whInfoDao,wInfo);
			
			if(null == existWInfo){//库中不存在需要保持的库存信息
				logger.debug("库中不存在需要保持的库存信息,做添加操作！");			
				whInfoDao.insertWareHouseInfo(wInfo);
				
			}else{//存在相同库存信息，做update操作
				
				existWInfo.setTotalAmount(existWInfo.getTotalAmount().intValue() + wInfoTotalAmount);
				existWInfo.setLockAmount(existWInfo.getLockAmount().intValue() + wInfoLockAmount);
				existWInfo.setUseAmount(existWInfo.getUseAmount().intValue() + wInfoUseAmount);
				if(existWInfo.getUseAmount().intValue() == 0){//可用库存为0，库存状态为不可用，否则可用
					existWInfo.setState(WareHouseConstants.WAREHOUSE_INFO_STATE_1);				
				}else{
					existWInfo.setState(WareHouseConstants.WAREHOUSE_INFO_STATE_0);
				}
				whInfoDao.updateWareHouseInfo(existWInfo);
			}
		}
		return ret;
	}
	/**
	 * 出库库存信息保存， 对总数量和锁定数量做减操作
	 * 先查询仓库库存中是否存在，如果不存在，则抛出异常
	 * 保存前判断 库存总数  = 锁定数量 + 可用数量 ，不相等，则抛出异常
	 * 同时判断 总数量和锁定数量做减操作后的数量 >=0
	 * @param wInfo
	 * @return
	 * @throws Exception
	 */
	public int saveOutWareHouseInfo(WareHouseInfo wInfo) throws Exception{
		int ret =0;
		logger.debug("保存出库库存信息传入的参数为：" + wInfo.toString());
		int wInfoTotalAmount = (wInfo.getTotalAmount() == null ? 0 : wInfo.getTotalAmount());
		int wInfoLockAmount = (wInfo.getLockAmount()== null ? 0 : wInfo.getLockAmount());
		//根据业务主键查询库存中是否存在
		WareHouseInfoDao whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
		WareHouseInfo existWInfo = findWareHouseInfoByBizKey(whInfoDao,wInfo);
		if(null != existWInfo){//仓库存在库存信息
			int newTotalAmount = wInfo.getTotalAmount().intValue() - wInfoTotalAmount;
			int newLockAmount = wInfo.getLockAmount().intValue() - wInfoLockAmount;
			int existUseAmount = wInfo.getUseAmount().intValue();
			if(newTotalAmount < 0 || newLockAmount <0 ||
					(newTotalAmount != (newLockAmount + existUseAmount))){//更新的数据不正确，抛出异常
				throw new Exception("库存存在的信息为：" + existWInfo.toString() + "数据变更后导致不正确！无法保存");
			}else{
				existWInfo.setTotalAmount(newTotalAmount);
				existWInfo.setLockAmount(newLockAmount);
			}
			whInfoDao.updateWareHouseInfo(existWInfo);
			
		}else{//仓库中不存在库存信息
			throw new Exception("仓库中不存在需要出库的库存信息,无法做出库操作！信息为：" + wInfo.toString());
		}
		return ret;
	}
	
	/**
	 * 对仓库的库存信息做锁操作，对锁定数量做加操作，对可用数量做减操作
	 * 先查询仓库库存中是否存在，如果不存在，则抛出异常
	 * 保存前判断 库存总数  = 锁定数量 + 可用数量 ，不相等，则抛出异常
	 * 同时判断 可用数量 >=0
	 * @param wInfo
	 * @param staffId 操作人ID
	 * @param staffName 操作人姓名
	 * @param content  操作说明
	 * @return
	 * @throws Exception
	 */
	public int saveLockWareHouseInfo(WareHouseInfo wInfo , String staffId,String staffName ,String content) throws Exception{
		int ret =0;
		logger.debug("保存仓库锁定库存信息传入的参数为：" + wInfo.toString());		
		
		int wInfoLockAmount = (wInfo.getLockAmount()== null ? 0 : wInfo.getLockAmount());
		int wInfoUseAmount = (wInfo.getUseAmount()== null ? 0 : wInfo.getUseAmount());
		
		//根据业务主键查询库存中是否存在
		WareHouseInfoDao whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
		WareHouseInfo existWInfo = findWareHouseInfoByBizKey(whInfoDao,wInfo);
		if(null != existWInfo){//仓库存在库存信息
			int existTotalAmount = existWInfo.getTotalAmount().intValue();
			int newLockAmount = existWInfo.getLockAmount().intValue() + wInfoLockAmount;
			int newUseAmount = existWInfo.getUseAmount().intValue() - wInfoUseAmount;
			if(newUseAmount < 0 || 
					(existTotalAmount != (newLockAmount + newUseAmount))){//更新的数据不正确，抛出异常
				throw new Exception("库存存在的信息为：" + existWInfo.toString() + "数据变更后导致不正确！无法保存");
			}else{				
				existWInfo.setLockAmount(newLockAmount);
				existWInfo.setUseAmount(newUseAmount);
				if(newUseAmount == 0){
					existWInfo.setState(WareHouseConstants.WAREHOUSE_INFO_STATE_1);
				}
				
			}
			
			whInfoDao.updateWareHouseInfo(existWInfo);
			//记录操作日志
			if(null != staffId){
				WareHouseLogUtils.operLog(staffId, staffName, "锁定库存", "仓库信息", wInfo.getLogKey(), wInfo.toString(), content);
			}
		}else{//仓库中不存在库存信息
			throw new Exception("仓库中不存在需要锁定的库存信息,无法做锁定操作！信息为：" + wInfo.toString());
		}
		return ret;
	}
	/**
	 * 设置库存信息的最大最小值，供库存告警使用
	 * @param wInfo
	 * @param staffId  操作人ID
	 * @param staffName  操作人姓名
	 * @param content  操作说明，供记录日志使用
	 * @return
	 * @throws Exception
	 */
	public int setMinMaxWareHouseInfo(WareHouseInfo wInfo ,String  staffId,String staffName,String content) throws Exception{
		int ret =0;
		logger.debug("设置仓库库存最大最小库存信息传入的参数为：" + wInfo.toString());	
		//根据业务主键查询库存中是否存在
		WareHouseInfoDao whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
		WareHouseInfo existWInfo = findWareHouseInfoByBizKey(whInfoDao,wInfo);
		if(null != existWInfo){//仓库存在库存信息			
			existWInfo.setMaxAmount(wInfo.getMaxAmount());
			existWInfo.setMinAmount(wInfo.getMinAmount());
			whInfoDao.updateWareHouseInfo(existWInfo);
			//记录操作日志
			if(null != staffId){
				WareHouseLogUtils.operLog(staffId, staffName, "阀值设置", "仓库信息", wInfo.getLogKey(), wInfo.toString(), content);
			}
		}else{//仓库中不存在库存信息
			throw new Exception("仓库中不存在需要设置库存最大最小的库存信息,无法做设置操作！信息为：" + wInfo.toString());
		}
		return ret;
	}
	
	public List<WareHouseInfo> listWareHouseInfo(WareHouseInfo wInfo) throws Exception{
		WareHouseInfoDao whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
		return whInfoDao.listWareHouseInfo(wInfo);
	}
	
	public Integer listWareHouseInfoCount(WareHouseInfo wInfo) throws Exception{
		WareHouseInfoDao whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
		return whInfoDao.listWareHouseInfoCount(wInfo);
	}
	/**
	 * 根据传入的业务主键查询仓库信息
	 * @param wInfo  业务主键包括：houseType 
	 * houseUse vendorCode partNo cpartNo custCode state
	 *    
	 * @return  null 或 单条仓库信息 ，如果查询条件过于简单则会抛出多条的异常
	 * @throws Exception
	 */
	private WareHouseInfo findWareHouseInfoByBizKey(WareHouseInfoDao whInfoDao ,WareHouseInfo wInfo) throws Exception{
		WareHouseInfo retInfo = null;
		logger.debug("根据传入的参数查询仓库信息，参数为：" + wInfo.toString());
		if(null == whInfoDao){
			whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
		}
		retInfo = whInfoDao.findWareHouseInfoByBizKey(wInfo);
		return retInfo;
	}
}
