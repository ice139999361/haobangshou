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

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.OrderCalUtils;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.customerorder.manager.CustOrderDetailMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;

import com.hbs.domain.warehouse.dao.WarehouseSendDetailDao;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;

import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;
import com.hbs.warehouse.manager.WarehouseMgr;
import com.hbs.warehousesend.manager.helper.WarehouseSendHelper;

/**
 * @author Administrator
 *
 */
public class WareHouseSendDetailMgr {
	private static final Logger logger = Logger.getLogger(WareHouseSendDetailMgr.class);
	
	/**
	 * 保存出库单明细，保存时先判断库中是否存在，不存在insert操作
	 * 否则 update操作，update操作的状态必须是 01 临时状态才能保存修改
	 * 如果状态为确认，则
	 * 如果是确认操作，则还需要执行如下操作
		 * 判断出库的物料明细对于的客户订单编号，
		 * 查询出客户订单明细，重点关注本次发货的特定客户库存和通用库存的变化
		 * 更新客户订单明细中的已发货数量和发货单编号，
		 * 更新对应的库存的总数量和锁定数量
	 * @param detail
	 * @param isflowRec 是否跟随主出库单同时保存
	 * @param content   保存意见
	 * @return  >= 0 成功  -1 此状态的明细不允许修改
	 * @throws Exception
	 */
	public int saveWareHouseSendDetail(WarehouseSendDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		String st =detail.getState();
		if(st.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_01)){
			logger.debug("保存客户出库库物料明细，传入的参数为:" + detail.toString());
		}else if(st.equals(WareHouseConstants.WAREHOUSE_REC_INFO_02)){
			logger.debug("确认客户出库物料明细，传入的参数为:" + detail.toString());
		}else{
			logger.debug("确认供应商出库物料明细,状态为取消，不做处理，传入的参数为:" + detail.toString());
			return ret;
		}
		//计算明细的出库金额
		detail.setCurMoney(OrderCalUtils.calOrderMoney(detail.getPrice(), detail.getIsTax(), detail.getTaxRate(),detail.getPriceTax(),detail.getContactFee(), detail.getAmount()));
		//设置账期
		String period = detail.getPeriod();
		if(StringUtils.isEmpty(period)){
			WarehouseSendHelper helper =(WarehouseSendHelper)BeanLocator.getInstance().getBean(WareHouseConstants.PRE_SPRING_SEND + detail.getPoNoType() + detail.getSettlementType());
			detail.setPeriod(helper.getPeriod(detail));
			detail.setFinancePeriod(detail.getPeriod());				
		}
		if(StringUtils.isEmpty(detail.getIsShowPrice())){
			CustomerInfoMgr custMgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean("customerInfoMgr");
			CustomerInfo cInfo = new CustomerInfo();
			cInfo.setCommCode(detail.getCustCode());
			cInfo.setState("0");
			cInfo = custMgr.getCustomerInfo(cInfo, false);
			if(cInfo != null){
				detail.setIsShowPrice(cInfo.getIsShowPrice());
			}
		}
		WarehouseSendDetailDao detailDao = (WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
		//查询出库单明细,判断是否存在
		WarehouseSendDetail existDetail;
		if(detail.getSendSeqId() == null)
			existDetail = null;
		else
			//existDetail = detailDao.findWarehouseSendDetailByBizKey(detail);
			existDetail = detailDao.findWarehouseSendDetail(detail.getSendSeqId().toString());
		if(null == existDetail){//不存在，insert操作，
			logger.debug("数据库中无此出库单明细，insert操作！");
			ret = detailDao.insertWarehouseSendDetail(detail);
			detail.setSendSeqId(ret);
			WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), (detail.getState().equals(WareHouseConstants.WAREHOUSE_SEND_INFO_02) ? "确认" : "新增"), "出库单明细", detail.getLogKey(), null, content);
		}else{//存在出库单明细，
			//判断状态
			String state = existDetail.getState();
			logger.debug("数据库中存在出库单明细，update操作！明细状态为：" + state);
			if(state.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_01)){//状态为临时，可以修改
				
				detail.setSendSeqId(existDetail.getSendSeqId());
				detailDao.updateWarehouseSendDetail(detail);
				ret = detail.getSendSeqId();
				WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), (detail.getState().equals(WareHouseConstants.WAREHOUSE_SEND_INFO_02) ? "确认" : "修改"), "入库单物料明细", detail.getLogKey(), null, content);
			}else{//状态不正确，不允许修改
				logger.debug("数据库中存在出库单明细，update操作！状态不正确，不允许修改！");
				ret = -1;
			}
		}
		/**
		 * 如果是确认操作，则还需要执行如下操作
		 * 判断出库的物料明细对于的客户订单明细，
		 * 查询出客户订单明细，重点关注本次发货的特定客户库存和通用库存的变化
		 * 更新客户订单明细中的已发货数量和发货单编号，
		 * 更新对应的库存的总数量和锁定数量
		 */
		if(st.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_02)){
			logger.debug("出库单明细出库，处理对应的客户订单！");
			processCustOrderDetail(detail);
			logger.debug("出库单明细出库，处理对应的库存信息！");
			processWarehouseInfo(detail);
		}
		return ret;
	}
	/**
	 * 根据发货单明细，创建客户订单关键字
	 * @param detail
	 * @return
	 */
	private CustOrderDetail createCustOrderDetail(WarehouseSendDetail detail){
		CustOrderDetail orderDetail = new CustOrderDetail();
		//设置本次发送数量
		orderDetail.setDeliveryAmount(detail.getAmount());
		//特定客户库存发货数量
		orderDetail.setSelfDeliveryAmount(detail.getSelfAmount());
		//通用库存发货数量
		orderDetail.setCommDeliveryAmount(detail.getCommAmount());
		//设置本次发送的送货单号
		orderDetail.setRltSendPoNo(detail.getSendPoNo());
		//设置客户编码
		orderDetail.setCommCode(detail.getCustCode());
		//客户的订单号
		orderDetail.setPoNo(detail.getRltPoNo());
		//设置物料编码
		orderDetail.setPartNo(detail.getPartNo());
		orderDetail.setSpecDesc(detail.getSpecDesc());
		
		orderDetail.setDeliveryHouseType(detail.getHouseType());
		
		return orderDetail;
	}
	/**
	 * 处理客户订单中的发货数量，包括本客户库存发货和通用库存
	 * 同时处理客户订单中本客户库存锁定和通用库存锁定
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	private int processCustOrderDetail(WarehouseSendDetail detail) throws Exception{
		int ret =0;
		CustOrderDetail orderDetail = createCustOrderDetail(detail);		
		CustOrderDetailMgr orderDetailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.CUST_ORDER_DETAILMGR);
		ret = orderDetailMgr.deliveryOrderDetailAmount(orderDetail, null, null, null);
		return ret;
	}
	/**
	 * 处理仓库中的库存锁定数量，包括特定客户库存和通用库存
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	private int processWarehouseInfo(WarehouseSendDetail detail) throws Exception{
		int ret =0;
		
		CustOrderDetail orderDetail = createCustOrderDetail(detail);		
		CustOrderDetailMgr orderDetailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.CUST_ORDER_DETAILMGR);
		CustOrderDetail existOrderDetail = orderDetailMgr.findCustOrderDetailByBizKey(orderDetail);
		if(null != existOrderDetail){
			//发货的数量
			int iSelfLock = (detail.getSelfAmount() == null ? 0 : detail.getSelfAmount().intValue());
			int iCommLock = (detail.getCommAmount() == null ? 0 : detail.getCommAmount().intValue());
			//特定客户库存发货数量
			if(iSelfLock != 0){//处理特定客户库存锁定的情况
				WareHouseInfo wInfo = new WareHouseInfo();
				//设置仓库类型 总库还是其他仓库
				wInfo.setHouseType(orderDetail.getDeliveryHouseType());
				//设置仓库用途 常规备货还是特定备货
				wInfo.setHouseUse(WareHouseConstants.WAREHOUSE_USE_TYPE_1);
				//设置供应商编码
				wInfo.setVendorCode(existOrderDetail.getVendorCode());
				//设置物料编码
				wInfo.setPartNo(existOrderDetail.getPartNo());
				//设置锁定的数目(特定客户库存）
				wInfo.setTotalAmount(iSelfLock);
				wInfo.setLockAmount(iSelfLock);
				//设置客户编码
				wInfo.setCustCode(existOrderDetail.getCommCode());
				WarehouseMgr whMgr = (WarehouseMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_MGR);
				whMgr.saveOutWareHouseInfo(wInfo);				
			}
			//处理通用库存锁定的情况
			if(iCommLock != 0){//处理通用库存锁定的情况
				WareHouseInfo wInfo = new WareHouseInfo();
				//设置仓库类型 总库还是其他仓库
				wInfo.setHouseType(orderDetail.getDeliveryHouseType());
				//设置仓库用途 常规备货还是特定备货
				wInfo.setHouseUse(WareHouseConstants.WAREHOUSE_USE_TYPE_2);
				//设置供应商编码
				wInfo.setVendorCode(existOrderDetail.getVendorCode());
				//设置物料编码
				wInfo.setPartNo(existOrderDetail.getPartNo());
				//设置锁定的数目(特定客户库存）
				wInfo.setTotalAmount(iCommLock);
				wInfo.setLockAmount(iCommLock);
				
				WarehouseMgr whMgr = (WarehouseMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_MGR);
				whMgr.saveOutWareHouseInfo(wInfo);				
			}
		}else{
			throw new Exception("出库单明细出库，处理对应的库存信息! 无法找到客户订单！");
		}
		return ret;
	}
	
	/**
	 * 批量保存出库单明细，调用单条保存方法
	 * @param detailList
	 * @param isflowRec  是否跟随主订单保存
	 * @param content  备注，记录日志
	 * @return  0 成功 
	 * @throws Exception
	 */
	public int saveWareHouseSendDetailList(List<WarehouseSendDetail> detailList,boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("批量保存出库单明细，明细的数量为:" + detailList.size());
		for(WarehouseSendDetail detail : detailList){
			Integer i = saveWareHouseSendDetail(detail,isflowRec,content);
			logger.debug("批量保存出库单明细,保存的结果为：" + i);
		}
		return ret;
	}
	/**
	 * 取消出库单明细，先判断取消的出库单明细是否存在，不存在，返回-2
	 * 存在，判断状态是否为临时状态，其他状态不允许取消。
	 * @param detail
	 * @param isflowRec 是否跟随主记录
	 * @param content 取消意见，记录日志
	 * @return 0--成功  -1 状态不正确，不能取消 -2 取消的明细不存在
	 * @throws Exception
	 */
	public int cancelWareHouseSendDetail(WarehouseSendDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("取消出库单明细信息,传入的参数为：" + detail.toString());
		WarehouseSendDetailDao detailDao = (WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
		WarehouseSendDetail existDetail = detailDao.findWarehouseSendDetailByBizKey(detail);
		if(null != existDetail){//存在出库单明细
			String state = existDetail.getState();
			logger.debug("数据库中存在出库单明细，明细状态为：" + state);
			if(state.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_01)){//为临时状态，可以取消
				existDetail.setState(WareHouseConstants.WAREHOUSE_SEND_INFO_03);
				detailDao.updateWarehouseSendDetailByState(existDetail);
				WareHouseLogUtils.operLog(existDetail.getStaffId(), existDetail.getStaffName(), "取消", "出库单明细", existDetail.getLogKey(), null, content);
			}else{//非临时状态，不能取消
				logger.debug("数据库中存在出库单明细，明细状态已经为非临时状态，不能做取消操作！");
				ret =-1;
			}
		}else{//不存在入库单明细，无法做取消
			ret = -2;
		}
		return ret;
	}
	
	/**
	 * 批量取消出库单明细，调用单条取消方法
	 * @param detailList
	 * @param isflowRec 是否跟随出库单同时操作
	 * @param content  取消说明，记录日志
	 * @return
	 * @throws Exception
	 */
	public int cancelWareHouseSendDetailList(List<WarehouseSendDetail> detailList,boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("批量取消出库单明细，明细的数量为:" + detailList.size());
		for(WarehouseSendDetail detail : detailList){
			cancelWareHouseSendDetail(detail,isflowRec,content);			
		}
		return ret;
	}
	
	/**
	 * 暂停/激活供应商出库单明细，传入的参数不需要修改原始状态
	 * 由系统做反向操作
	 * @param detail
	 * @param isflowRec
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int controlActiveState(WarehouseSendDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("供应商出库单明细暂停/激活操作，传入的参数为：" + detail.toString());
		String activeState = detail.getActiveState();
		if(activeState.equals(WareHouseConstants.WAREHOUSE_SEND_ACTIVE)){
			detail.setActiveState(WareHouseConstants.WAREHOUSE_SEND_PAUSE);
		}else{
			detail.setActiveState(WareHouseConstants.WAREHOUSE_SEND_ACTIVE);
		}
		WarehouseSendDetailDao detailDao = (WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
		detailDao.updateWarehouseSendDetailByActiveState(detail);
		WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), (activeState.equals(WareHouseConstants.WAREHOUSE_SEND_ACTIVE) ? "激活" : "暂停"), "客户出库单", detail.getLogKey(), null, content);
		return ret;
	}
	/**
	  * 批量暂停/激活客户出库单明细，传入的参数不需要修改原始状态
	 * 由系统做反向操作
	 * @param detailList
	 * @param isflowRec
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int controlActiveStateList(List<WarehouseSendDetail> detailList,boolean isflowRec,String content) throws Exception{
		int ret =0;
		for(WarehouseSendDetail detail : detailList){
			controlActiveState(detail ,  isflowRec, content);
		}
		return ret;
	}
	
	/**
	 * 财务调整账期，前台需要控制2个条件
	 * 1。结算类型为账期结算
	 * 2.财务状态为未对账
	 * 其他的不允许调整账期
	 * @param detail
	 * @param staffId  财务ID
	 * @param staffName  财务姓名
	 * @param content   说明，记录日志
	 * @return
	 * @throws Exception
	 */
	public int adjustFinancePeriod(WarehouseSendDetail detail,String staffId,String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("财务调整供应商出库单明细账期，传入的参数为：" + detail.toString());
		WarehouseSendDetailDao detailDao = (WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
		detailDao.updateWarehouseSendDetailByFinancePeriod(detail);
		WareHouseLogUtils.operLog(staffId, staffName, "调整账期", "客户出库单明细", detail.getLogKey(), null, content);
		return ret;
	}
	/**
	 * 财务批量调整账期，前台需要控制2个条件
	 * 1。结算类型为账期结算
	 * 2.财务状态为未对账
	 * 其他的不允许调整账期
	 * @param detailList
	 * @param staffId  财务ID
	 * @param staffName  财务姓名
	 * @param content   说明，记录日志
	 * @return
	 * @throws Exception
	 */
	public int adjustFinancePeriodList(List<WarehouseSendDetail> detailList,String staffId,String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("财务调整供应商入库单明细列表账期，数目为：" + detailList.size());
		for(WarehouseSendDetail detail : detailList){
			adjustFinancePeriod( detail, staffId, staffName,  content);
		}
		return ret;
	}
	
	
	/**
	 * 财务设置已对账,针对出库单明细，
	 * 财务已对账对出库单建议不设置
	 * 
	 * @param detail
	 * @param staffId
	 * @param staffName
	 * @param content
	 * @return 0 成功  -1 财务已经对账，不需要再对
	 * @throws Exception
	 */
	public int setFinanceState(WarehouseSendDetail detail ,String staffId,String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("财务设置已对账，传入的参数为：" + detail.toString());
		String financeState = detail.getFinanceState();
		if(financeState.equals(WareHouseConstants.WAREHOUSE_SEND_FINANCE_STATE_1)){
			ret =-1;
			logger.debug("本条供应商出库单明细已经对账，不需要再处理!");
		}else{
			WarehouseSendDetailDao detailDao =(WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
			detail.setFinanceState(WareHouseConstants.WAREHOUSE_SEND_FINANCE_STATE_1);
			detailDao.updateWarehouseSendDetailByFinanceState(detail);
			WareHouseLogUtils.operLog(staffId, staffName, "财务对账", "客户出库单明细", detail.getLogKey(), null, content);
		}
		return ret;
	}
	/**	 
	 * 查询单条出库单明细，条件可以是主键的sequence
	 * 也可以是组合业务关键字：SEND_PO_NO C_CODE SETTLEMENT_TYPE PART_NO C_PART_NO PO_NO_TYPE SPEC_DESC
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public WarehouseSendDetail getWarehouseSendDetail(WarehouseSendDetail detail) throws Exception{
		WarehouseSendDetail retDetail = null;
		logger.debug("查询单条出库单明细，输入的参数为：" + detail.toString());
		WarehouseSendDetailDao detailDao =(WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
		retDetail = detailDao.findWarehouseSendDetailByBizKey(detail);
		return retDetail;
	}
	/**
	 * 根据输入的条件查询出库单明细列表
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public List<WarehouseSendDetail> listWarehouseSendDetail(WarehouseSendDetail detail) throws Exception{
		List<WarehouseSendDetail> detailList = null;
		logger.debug("查询出库单明细列表，输入的参数为：" + detail.toString());
		WarehouseSendDetailDao detailDao =(WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
		detailList = detailDao.listWarehouseSendDetail(detail);
		return detailList;
	}
	/**
	 * 根据输入的条件，查询满足条件的出货单明细的总数目
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public Integer listWarehouseSendDetailCount(WarehouseSendDetail detail) throws Exception{
		Integer retCount = 0;
		logger.debug("查询出库单明细列表数量，输入的参数为：" + detail.toString());
		WarehouseSendDetailDao detailDao =(WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
		retCount = detailDao.listWarehouseSendDetailCount(detail);
		return retCount;
	}
}
