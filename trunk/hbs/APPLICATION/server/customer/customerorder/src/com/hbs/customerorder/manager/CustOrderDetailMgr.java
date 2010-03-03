/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;



import com.hbs.common.springhelper.BeanLocator;

import com.hbs.common.utils.OrderCalUtils;
import com.hbs.customer.common.utils.CustLogUtils;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.manager.helper.CustOrderState;
import com.hbs.customerorder.utils.CustOrderUtils;
import com.hbs.domain.customer.order.dao.CustOrderDetailDao;
import com.hbs.domain.customer.order.dao.CustomerOrderDao;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.customer.order.pojo.CustomerOrder;
import com.hbs.domain.vendor.vendorinfo.dao.VendorInfoDao;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.warehouse.manager.WarehouseMgr;

/**
 * @author Administrator
 *
 */
public class CustOrderDetailMgr {
	private static final Logger logger = Logger.getLogger(CustOrderDetailMgr.class);
	private static final String VENDORINFO_DAO ="vendorInfoDao";
	/**
	 * SPRING配置项的前缀
	 */
	public static final String PRE_SPRING ="custOrder";
	/**
	 * 操作者为业务助理
	 * 保存客户订单明细的临时数据，数据的状态为
	 * 01---包括订单明细临时数据
	 * 本方法如果是客户订单调用，则操作日志，否则记录操作日志
	 * @param orderDetail
	 * @param staffId
	 * @param staffName
	 * @return
	 * @throws Exception
	 */
	public int saveTempOrderDetail(CustOrderDetail orderDetail,String staffId, String staffName) throws Exception{
		int ret =0;
		logger.debug("保存客户订单明细的临时数据，输入的参数为：" + orderDetail.toString());
		String period = orderDetail.getPeriod();
		if(null == period){
			CustOrderState orderState =(CustOrderState)BeanLocator.getInstance().getBean(PRE_SPRING + orderDetail.getPoNoType() + orderDetail.getSettlementType());
			orderDetail.setPeriod(orderState.getDetailPeriod(orderDetail));
		}
		//
		orderDetail.setMoney(OrderCalUtils.calOrderMoney(orderDetail.getCprice(), orderDetail.getIsTax(),orderDetail.getTaxRate(), orderDetail.getCpriceTax(),orderDetail.getContactFee(),orderDetail.getAmount()));
		orderDetail.setState(CustOrderConstants.ORDER_STATE_01);
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		cDetailDao.insertCustOrderDetail(orderDetail);
		//记录操作日志
		if(null != staffId){			
			CustLogUtils.operLog(orderDetail.getStaffId(), orderDetail.getStaffName(), "新增", "客户订单明细", orderDetail.getLogBizKey(), null, null);
		}
		return ret;
	}
	
	/**
	 * 批量保存客户订单明细的临时数据
	 * @param orderDetailList
	 * @param staffId
	 * @param staffName
	 * @return
	 * @throws Exception
	 */
	public int saveTempOrderDetailList(List<CustOrderDetail> orderDetailList,String staffId, String staffName) throws Exception{
		int ret = 0;
		logger.debug("批量保存客户订单明细的临时数据，数量为：" + orderDetailList.size());
		for(CustOrderDetail orderDetail : orderDetailList){
			saveTempOrderDetail(orderDetail,staffId, staffName);
		}
		return ret;
	}
	/**
	 * 取消该订单明细
	 * 取消的状态为：03
	 * 如果存在待办，将清除所有待办
	 * @param orderDetail  订单明细
	 * @param cancelContent  取消原有
	 * @return
	 * @throws Exception
	 */
	public int cancelOrderDetail(CustOrderDetail orderDetail, String cancelContent) throws Exception{
		int ret =0;
		logger.debug("取消该订单明细，输入的参数为：" + orderDetail.toString());
		orderDetail.setState(CustOrderConstants.ORDER_STATE_03);
		Map<String,Object> hm = new HashMap<String,Object>();
		hm.put("state", CustOrderConstants.ORDER_STATE_03);
		orderDetail.setDynamicFields(hm);
		updateCustDetailByState(orderDetail);
		//waittask
		CustOrderUtils.processDeleteWaitTask(orderDetail.getBizKey());
		//log
		CustLogUtils.operLog(orderDetail.getStaffId(), orderDetail.getStaffName(), "取消", "客户订单明细", orderDetail.getLogBizKey(), null, cancelContent);
		
		
		return ret;
	}
	/**
	 * 采购确认订单明细的交期
	 * 状态为21
	 * 查询客户订单的所有明细，如果所有订单明细交期都确认，
	 * 则修改客户订单状态为21
	 * 交期确认，删除待办
	 * @param orderDetail 订单明细
	 * @param confirmId  确认人ID
	 * @param confirmName  确认人姓名
	 * @param content  确认意见
	 * @return
	 * @throws Exception
	 */
	public int purchaseConfirmDetailDelivery(CustOrderDetail orderDetail,String confirmId,String confirmName, String content) throws Exception{
		int ret =0;
		logger.debug("采购确认订单明细的交期，输入的参数为：" + orderDetail.toString());
		Date deliveryDate = orderDetail.getVerDeliveryDate();
		if(null == deliveryDate){
			orderDetail.setVerDeliveryDate(orderDetail.getPreDeliveryDate());
		}
		orderDetail.setState(CustOrderConstants.ORDER_STATE_21);
		Map<String,Object> hm = new HashMap<String,Object>();
		hm.put("state", CustOrderConstants.ORDER_STATE_03);
		orderDetail.setDynamicFields(hm);
		updateCustDetailByState(orderDetail);
		//waittask
		CustOrderUtils.processDeleteWaitTask(orderDetail.getBizKey());
		//log
		CustLogUtils.operLog(confirmId, confirmName, "采购确认交期", "客户订单明细", orderDetail.getLogBizKey(), null, content);
		
		//处理客户订单的状态
		boolean isOrderStateChange = true;
		List<CustOrderDetail> listDetail = getDetailState(orderDetail);
		if(null != listDetail && listDetail.size() >0){
			for(CustOrderDetail detail : listDetail){
				String state = detail.getState();
				if( !(state.equals(CustOrderConstants.ORDER_STATE_21))){
					isOrderStateChange = false;
					break;
				}
			}
		}
		if(isOrderStateChange){
			CustomerOrder cOrder = new CustomerOrder();
			cOrder.setCommCode(orderDetail.getCommCode());
			cOrder.setPoNo(orderDetail.getPoNo());
			cOrder.setPoNoType(orderDetail.getPoNoType());
			cOrder.setState(orderDetail.getState());
			CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
			cOrderDao.updateCustomerOrderByState(cOrder);
		}
		return ret;
	}

	/**
	 * 采购不同意订单明细交期，提交给业务助理处理
	 * 状态变为04   待业务处理交期
	 * 同时修改客户订单状态为：
	 * 04   待业务处理交期
	 * 向业务发出待办
	 * @param orderDetail
	 * @param staffId
	 * @param staffName
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int purchaseRefuseDetailDelivery(CustOrderDetail orderDetail,String confirmId, String confirmName, String content) throws Exception{
		int ret =0;
		logger.debug("采购不同意订单明细交期，输入的参数为：" + orderDetail.toString());
		orderDetail.setState(CustOrderConstants.ORDER_STATE_04);
		Map<String,Object> hm = new HashMap<String,Object>();
		hm.put("state", CustOrderConstants.ORDER_STATE_03);
		orderDetail.setDynamicFields(hm);
		updateCustDetailByState(orderDetail);
		//处理客户订单状态
		CustomerOrder cOrder = new CustomerOrder();
		cOrder.setCommCode(orderDetail.getCommCode());
		cOrder.setPoNo(orderDetail.getPoNo());
		cOrder.setPoNoType(orderDetail.getPoNoType());
		cOrder.setState(orderDetail.getState());
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		cOrderDao.updateCustomerOrderByState(cOrder);
		//log			
		CustLogUtils.operLog(confirmId, confirmName, "采购拒绝交期", "客户订单明细", orderDetail.getLogBizKey(), null, content);
		
		//waittask			
		WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
		waitTaskInfo.setStaffId(orderDetail.getStaffId());//这里需要根据客户查找对应的业务助理
		Map<String , String> hmParam = new HashMap<String,String>();
		hmParam.put("$staffName", confirmName);
		hmParam.put("$businessKey", cOrder.getWaitTaskBizKey());
		waitTaskInfo.setHmParam(hmParam);		
		CustOrderUtils.processCreateWaitTask("CUST_ORDER_005",null, waitTaskInfo);		
		return ret;
	}
	
	/**
	 * 业务提交变更后的交期，提交给采购处理
	 * 状态变为20  待采购处理
	 * 向采购发待办
	 * @param orderDetail
	 * @param staffId
	 * @param staffName
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int salesConfirmDetailDelivery(CustOrderDetail orderDetail, String content) throws Exception{
		int ret =0;
		logger.debug("业务提交变更后的交期，输入的参数为：" + orderDetail.toString());
		orderDetail.setState(CustOrderConstants.ORDER_STATE_20);
		Map<String,Object> hm = new HashMap<String,Object>();
		hm.put("state", CustOrderConstants.ORDER_STATE_03);
		orderDetail.setDynamicFields(hm);
		updateCustDetailByState(orderDetail);
		//order state deal
		CustomerOrder cOrder = new CustomerOrder();
		cOrder.setCommCode(orderDetail.getCommCode());
		cOrder.setPoNo(orderDetail.getPoNo());
		cOrder.setPoNoType(orderDetail.getPoNoType());
		cOrder.setState(orderDetail.getState());
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		cOrderDao.updateCustomerOrderByState(cOrder);
		//log
		CustLogUtils.operLog(orderDetail.getStaffId(), orderDetail.getStaffName(), "业务提交交期", "客户订单明细", orderDetail.getLogBizKey(), null, content);
			
		//waittask
		WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
		waitTaskInfo.setStaffId(getVendorStaffId(orderDetail.getCommCode()));//这里需要查找采购人员ID
		Map<String , String> hmParam = new HashMap<String,String>();
		hmParam.put("$staffName", orderDetail.getStaffName());
		hmParam.put("$businessKey", orderDetail.getWaitTaskBizKey());
		waitTaskInfo.setHmParam(hmParam);		
		CustOrderUtils.processCreateWaitTask("CUST_ORDER_006",null, waitTaskInfo);			
		return ret;
	}
	/**
	 * 根据客户信息中的供应商编码，查询供应商对应的本公司的采购员
	 * @param vendorCode
	 * @return
	 * @throws Exception
	 */
	private String getVendorStaffId(String vendorCode) throws Exception{
		String retStr = null;
		VendorInfoDao vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		VendorInfo vInfo = new VendorInfo();
		vInfo.setCommCode(vendorCode);
		vInfo.setState("0");
		VendorInfo retInfo = vInfoDao.findVendorInfoByBase(vInfo);
		if(null != retInfo){
			retStr = retInfo.getStaffId();
		}else{
			throw new Exception("无法查询到供应商编码为：" + vendorCode +"信息！");
		}
		return retStr;
	}
	/**
	 * 锁定订单明细的库存，除在订单明细表中添加锁定数量外，
	 * 还有一部分就是对仓库库存的锁定变更，仓库库存的锁定在仓库部分调用DAO实现
	 * 本操作可能是人工锁定操作，也可能是采购入库时，根据关联的客户订单，自动锁定到客户订单中
	 * 根据锁定的数量和总数量比较，如果数量相同，则状态为70货备齐
	 * 否则状态为71部分备货
	 * 订单的状态如果有锁定数量，状态为21备货中	 
	 * @param orderDetail  锁定的数量不需要前台计算，由后台计算处理
	 * @param staffId
	 * @param staffName
	 * @param content
	 * @return 0  成功  -1 无法找到客户订单明细 -2 锁定数量大于订单明细订货数量
	 * @throws Exception
	 */
	public int lockOrderDetailAmount(CustOrderDetail orderDetail,String staffId, String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("客户订单明细锁定物料锁定操作！ 传入的参数为：" + orderDetail.toString());
		//根据业务主键查询客户订单明细
		CustOrderDetail  existDetail = findCustOrderDetailByBizKey(orderDetail);
		if(existDetail != null){//客户订单明细存在，执行库存锁定操作
			logger.debug("客户订单明细锁定物料锁定操作,查询客户订单明细为：" + existDetail.toString());
			int iAmount = existDetail.getAmount();  //订单数量
			int iLockAmount = existDetail.getLockAmount(); //锁定总数量
			int iselLock = existDetail.getSelfLockAmount(); //客户备货锁定数量
			int icommLock = existDetail.getCommLockAmount();//常规备货锁定数量
			int idelivery = existDetail.getDeliveryAmount();//已经发货的数量
			//本次本客户库存锁定数量
			int oselfLock = (orderDetail.getSelfLockAmount() == null ? 0 : orderDetail.getSelfLockAmount().intValue());
			//本次通用库存锁定数量
			int ocommLock = (orderDetail.getCommLockAmount()== null ? 0 :orderDetail.getCommLockAmount().intValue());
			
			int isavelockAmount = iLockAmount + oselfLock + ocommLock;
			if(isavelockAmount + idelivery > iAmount){//锁定的数量  + 已发货数量大于订单明细订货数量
				logger.debug("输入的锁定数量+已存在的锁定数量 + 已发货数量 ，大于订单明细订货数量，不能执行锁定操作！");
				//ret = -2;
				throw new Exception("输入的锁定数量+已存在的锁定数量 + 已发货数量 ，大于订单明细订货数量，不能执行锁定操作！");
				
			}else{
				logger.debug("执行锁定操作！");
				if(isavelockAmount == iAmount){//货已备齐
					//根据订单明细的结算方式，来处理订单明细的状态
					String settlementType = orderDetail.getSettlementType();
					int isType = Integer.parseInt(settlementType);
					String state = CustOrderConstants.ORDER_STATE_70;
					switch(isType){
					case 1: //账期结算，状态为货已备齐
					case 2: //预付X%,剩余货到付款
						 state = CustOrderConstants.ORDER_STATE_70;
						 break;
					case 3://预付X%剩余款到发货
						state = CustOrderConstants.ORDER_STATE_31;
						//此处差财务待办
						break;
					}
					orderDetail.setState(state);
				}else{//部分备货
					orderDetail.setState(CustOrderConstants.ORDER_STATE_71);
				}
				orderDetail.setOperSeqId(existDetail.getOperSeqId());
				orderDetail.setLockAmount(isavelockAmount);
				orderDetail.setSelfLockAmount(iselLock + oselfLock);
				orderDetail.setCommLockAmount(ocommLock + icommLock);
				ret = updateCustDetailAmount(orderDetail);
			}
		}else{//客户订单明细不存在 ，返回-1
			logger.debug("根据输入的参数无法找到对应的客户订单明细，无法执行锁定操作！");
			//ret = -1;
			throw new Exception("根据输入的参数无法找到对应的客户订单明细，无法执行锁定操作！");
		}
		//处理仓库部分的锁定情况
		if(staffId != null && ret == 0){
			logger.debug("处理仓库锁定操作！由操作人员发起，发起人 " + staffId + staffName );
			WarehouseMgr whMgr =(WarehouseMgr)BeanLocator.getInstance().getBean(CustOrderConstants.WAREHOUSE_INFO_MGR);
			int detailSelock = (orderDetail.getSelfLockAmount() == null ? 0 :orderDetail.getSelfLockAmount().intValue());
			if( detailSelock != 0){
				logger.debug("存在特定客户备货锁定，锁定数量为：" + detailSelock);
				WareHouseInfo whrInfo = new WareHouseInfo();
				//设置仓库类型
				whrInfo.setHouseType(orderDetail.getDeliveryHouseType());
				//设置备货类型
				whrInfo.setHouseUse(CustOrderConstants.WAREHOUSE_INFO_HOUSE_USE_1);
				//设置供应商编码
				whrInfo.setVendorCode(orderDetail.getVendorCode());
				//设置公司物料编码
				whrInfo.setPartNo(orderDetail.getPartNo());
				whrInfo.setCustCode(orderDetail.getCommCode());
				//设置锁定数量
				whrInfo.setLockAmount(detailSelock);
				whrInfo.setUseAmount(-detailSelock);
				whMgr.saveLockWareHouseInfo(whrInfo, null, null, null);
			}
			int commLock = ( orderDetail.getCommLockAmount() == null ? 0 : orderDetail.getCommLockAmount().intValue());
			if(commLock != 0){
				logger.debug("存在常规备货锁定，锁定数量为：" + commLock);
				WareHouseInfo whrInfo = new WareHouseInfo();
				//设置仓库类型
				whrInfo.setHouseType(orderDetail.getDeliveryHouseType());
				//设置备货类型
				whrInfo.setHouseUse(CustOrderConstants.WAREHOUSE_INFO_HOUSE_USE_2);
				//设置供应商编码
				whrInfo.setVendorCode(orderDetail.getVendorCode());
				//设置公司物料编码
				whrInfo.setPartNo(orderDetail.getPartNo());				
				//设置锁定数量
				whrInfo.setLockAmount(commLock);
				whrInfo.setUseAmount(-commLock);
				whMgr.saveLockWareHouseInfo(whrInfo, null, null, null);
			}
		}
		//log
		if(staffId != null){
		CustLogUtils.operLog(orderDetail.getStaffId(), orderDetail.getStaffName(), "锁定库存", "客户订单明细", orderDetail.getLogBizKey(), null, content);
		}
		return ret;
	}
	/**
	 * 财务确认订单明细的预付x%，款到发货
	 * 状态有待财务确认31 改变为70货已备齐
	 * @param orderDetail
	 * @param auditId
	 * @param auditName
	 * @param auditContents
	 * @return
	 * @throws Exception
	 */
	public int financeConfirmDetailFee(CustOrderDetail orderDetail,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("财务确认订单明细的预付x%，款到发货，输入的参数为：" + orderDetail.toString());
		String state = orderDetail.getState();
		if((CustOrderConstants.ORDER_STATE_31).equals(state)){
			orderDetail.setState(CustOrderConstants.ORDER_STATE_70);
			ret = updateCustDetailByState(orderDetail);
			//此处差操作日志
		}else{
			logger.debug("此订单明细的状态不正确，无法确认预付x%，款到发货！");
			throw new Exception("此订单明细的状态不正确，无法确认预付x%，款到发货！");
		}
		return ret;
	}
	/**
	 * 财务申请订单明细的预付x%，款到发货，款未到发货
	 * 状态有待财务确认31 改变为33 待领导审批
	 * @param orderDetail
	 * @param auditId
	 * @param auditName
	 * @param auditContents
	 * @return
	 * @throws Exception
	 */
	public int financeApplyDetailFee(CustOrderDetail orderDetail,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("财务申请订单明细的预付x%，款到发货，款未到发货，输入的参数为：" + orderDetail.toString());
		String state = orderDetail.getState();
		if((CustOrderConstants.ORDER_STATE_31).equals(state)){
			orderDetail.setState(CustOrderConstants.ORDER_STATE_33);
			ret = updateCustDetailByState(orderDetail);
			//此处差操作日志
			//此处差待办
		}else{
			logger.debug("此订单明细的状态不正确，无法申请预付x%，款到发货，款未到发货！");
			throw new Exception("此订单明细的状态不正确，无法申请预付x%，款到发货，款未到发货！");
		}
		return ret;
	}
	/**
	 * 领导审批同意订单明细的预付x%，款到发货，款未到发货
	 * 状态有待领导审批33 改变为70 货已备齐
	 * @param orderDetail
	 * @param auditId
	 * @param auditName
	 * @param auditContents
	 * @return
	 * @throws Exception
	 */
	public int auditAgreeDetailFee(CustOrderDetail orderDetail,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("领导审批同意订单明细的预付x%，款到发货，款未到发货，输入的参数为：" + orderDetail.toString());
		String state = orderDetail.getState();
		if((CustOrderConstants.ORDER_STATE_33).equals(state)){
			orderDetail.setState(CustOrderConstants.ORDER_STATE_70);
			ret = updateCustDetailByState(orderDetail);
			//此处差操作日志
			//此处差待办
		}else{
			logger.debug("此订单明细的状态不正确，无法执行领导审批同意预付x%，款到发货，款未到发货！");
			throw new Exception("此订单明细的状态不正确，无法执行领导审批同意预付x%，款到发货，款未到发货！");
		}
		return ret;
	}
	/**
	 * 领导审批同意订单明细的预付x%，款到发货，款未到发货
	 * 状态有待领导审批33 改变为31 待财务确认 
	 * @param orderDetail
	 * @param auditId
	 * @param auditName
	 * @param auditContents
	 * @return
	 * @throws Exception
	 */
	public int auditDisAgreeDetailFee(CustOrderDetail orderDetail,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("领导审批不同意订单明细的预付x%，款到发货，款未到发货，输入的参数为：" + orderDetail.toString());
		String state = orderDetail.getState();
		if((CustOrderConstants.ORDER_STATE_33).equals(state)){
			orderDetail.setState(CustOrderConstants.ORDER_STATE_31);
			ret = updateCustDetailByState(orderDetail);
			//此处差操作日志
			//此处差待办
		}else{
			logger.debug("此订单明细的状态不正确，无法执行领导审批不同意预付x%，款到发货，款未到发货！");
			throw new Exception("此订单明细的状态不正确，无法执行领导审批不同意预付x%，款到发货，款未到发货！");
		}
		return ret;
	}
	
	/**
	 * 修改订单明细的已发货数量，对于已发货的数量，同时把锁定数量减少
	 
	 * 如果发货数量与总数量相同，则订单明细为61全部发货，否则为60部分发货
	 * 订单的状态为部分发货和全部发货
	 
	 * @param orderDetail  发货数量的计算由后台处理
	 * @param staffId
	 * @param staffName
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int deliveryOrderDetailAmount(CustOrderDetail orderDetail,String staffId, String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("修改订单明细的已发货数量操作 ,输入的参数为：" + orderDetail.toString());
		//本次客户库存发货数量
		int iselfdeliveryAmount = (orderDetail.getSelfDeliveryAmount() == null ? 0 : orderDetail.getSelfDeliveryAmount().intValue());
		//本次通用库存发货数量
		int icommdeliveryAmount =(orderDetail.getCommDeliveryAmount() == null ? 0 : orderDetail.getCommDeliveryAmount().intValue());
		//本次总发货数量
		int ideliveryAmount = iselfdeliveryAmount + icommdeliveryAmount;
		
		//判断发货数量 == 本客户发货数量+ 通用库存发货数量
		CustOrderDetail  existDetail = findCustOrderDetailByBizKey(orderDetail);
		if(null != existDetail){//订单明细存在
			logger.debug("修改订单明细的已发货数量操作,查询客户订单明细为：" + existDetail.toString());
			orderDetail.setOperSeqId(existDetail.getOperSeqId());
			int ieAmount =(existDetail.getDeliveryAmount() == null ? 0 : existDetail.getDeliveryAmount().intValue());
			int ieselfAmount = (existDetail.getSelfDeliveryAmount() == null ? 0 : existDetail.getSelfDeliveryAmount().intValue());
			int iecommAmount =(existDetail.getCommDeliveryAmount() == null ? 0 : existDetail.getCommDeliveryAmount().intValue());
			int ieLockAmount = (existDetail.getLockAmount() == null ? 0 : existDetail.getLockAmount().intValue());
			int ieselfLock =(existDetail.getSelfLockAmount() == null ? 0 : existDetail.getSelfLockAmount().intValue());
			int iecommLock =(existDetail.getCommLockAmount() == null ? 0 : existDetail.getCommLockAmount().intValue());
			if( iselfdeliveryAmount > ieselfLock){//客户本客户库存发货数  > 本客户锁定数量
				throw new Exception("客户本客户库存发货数  > 本客户锁定数量,无法执行操作！");
			}
			if(icommdeliveryAmount > iecommLock){//通用库存发货数  > 锁定的通用库存数
				throw new Exception("通用库存发货数  > 锁定的通用库存数,无法执行操作！");
			}
			int  tempAmount = ieAmount + ideliveryAmount;
			if(tempAmount <=  existDetail.getAmount()){
				if(tempAmount == existDetail.getAmount()){
					orderDetail.setState(CustOrderConstants.ORDER_STATE_61);
				}else{
					orderDetail.setState(CustOrderConstants.ORDER_STATE_60);
				}
				//处理数量
				orderDetail.setDeliveryAmount(tempAmount);
				orderDetail.setSelfDeliveryAmount(iselfdeliveryAmount + ieselfAmount);
				orderDetail.setCommDeliveryAmount(icommdeliveryAmount + iecommAmount);
				//锁定数量减去发货数
				orderDetail.setLockAmount(ieLockAmount - ideliveryAmount);
				orderDetail.setSelfLockAmount(ieselfLock - iselfdeliveryAmount);
				orderDetail.setCommLockAmount(iecommLock - icommdeliveryAmount);
				ret = updateCustDetailAmount(orderDetail);
			}else{//发货数量大于订货数量
				throw new Exception("修改订单明细的已发货数量操作，所操作的订单明细发货数量大于订货数量，无法执行！");
			}
		}else{//订单明细不存在
			throw new Exception("修改订单明细的已发货数量操作，所操作的订单明细不存在！");
		}
		//log
		//operLog(staffId,staffName, "订单发货数量", orderDetail.getBizKey(),content);
		return ret;
	}
	
	/**
	 * 修改临时状态的订单明细，由业务助理修改
	 * @param orderDetail	
	 * @param content  修改说明
	 * @return
	 * @throws Exception
	 */
	public int updateTempOrderDetail(CustOrderDetail orderDetail, String content) throws Exception{
		int ret =0;
		logger.debug("修改临时状态的订单明细 ,输入的参数为：" + orderDetail.toString());
		orderDetail.setMoney(OrderCalUtils.calOrderMoney(orderDetail.getCprice(), orderDetail.getIsTax(),orderDetail.getTaxRate(), orderDetail.getCpriceTax(),orderDetail.getContactFee(), orderDetail.getAmount()));
		orderDetail.setState(CustOrderConstants.ORDER_STATE_01);
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		cDetailDao.updateCustOrderDetail(orderDetail);
		//记录操作日志
		
			CustLogUtils.operLog(orderDetail.getStaffId(), orderDetail.getStaffName(), "修改", "客户订单明细", orderDetail.getLogBizKey(), null, content);
		
		return ret;
	}
	/**
	 * 批量修改订单明细，由业务助理修改
	 * @param orderDetailList
	 * @param content  修改说明
	 * @return
	 * @throws Exception
	 */
	public int updateTempOrderDetailList(List<CustOrderDetail> orderDetailList,String content) throws Exception{
		int ret = 0;
		logger.debug(" 批量修改订单明细 ,数量为：" + orderDetailList.size());
		for(CustOrderDetail orderDetail : orderDetailList){
			updateTempOrderDetail(orderDetail, content);
		}
		return ret;
	}
	
	/**
	 * 获取客户当期订单账期的最大金额
	 * @param orderDetail
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getTotalMoneyByPeriod(CustOrderDetail orderDetail) throws Exception{
		BigDecimal ret = new BigDecimal(0);
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		List<CustOrderDetail> listDetail = cDetailDao.listtotalMoneyByPeriod(orderDetail);
		if(null != listDetail  && listDetail.size() >0){
			for(CustOrderDetail detail : listDetail){
				int ipoNoType = Integer.parseInt(detail.getPoNoType());
				switch(ipoNoType){
					case 0:
						ret.add(detail.getMoney());
						break;
					case 1:
						ret.subtract(detail.getMoney());
				}
			}
		}
		return ret;
	}
	
	public int updateCustDetailByState(CustOrderDetail orderDetail) throws Exception{
		int ret =0;
		logger.debug("修改订单明细 ,输入的参数为：" + orderDetail.toString());
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		cDetailDao.updateCustOrderDetailByState(orderDetail);
		return ret;
	}
	
	public int updateCustDetailAmount(CustOrderDetail orderDetail) throws Exception{
		int ret =0;
		logger.debug("修改订单明细订单数量 ,输入的参数为：" + orderDetail.toString());
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		cDetailDao.updateCustOrderDetailAmount(orderDetail);
		return ret;
	}
	
	public CustOrderDetail findCustOrderDetailById(String id) throws Exception{
		logger.debug("根据主键查询订单明细 ,输入的参数为：" + id);
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		return cDetailDao.findCustOrderDetailById(id);
	}
	
	public CustOrderDetail findCustOrderDetailByBizKey(CustOrderDetail orderDetail) throws Exception{
		logger.debug("根据业务主键查询订单明细 ,输入的参数为：" + orderDetail.toString());
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		return cDetailDao.findCustOrderDetailByBizKey(orderDetail);
	}
	
	public int controlActiveState(CustOrderDetail orderDetail,String content) throws Exception{
		int ret =0;
		logger.debug("订单明细活动状态 ,输入的参数为：" + orderDetail.toString());
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		cDetailDao.updateCustOrderDetailByActiveState(orderDetail);
					
			CustLogUtils.operLog(orderDetail.getStaffId(), orderDetail.getStaffName(), ((orderDetail.getActiveState()).equals(CustOrderConstants.ORDER_ACTIVE_STATE) ? "激活" : "暂停"), "客户订单明细", orderDetail.getLogBizKey(), null, content);
		
		return ret;
	}
	
	public int controlActiveStateList(List<CustOrderDetail> orderDetailList,String content) throws Exception{
		int ret =0;
		logger.debug("订单明细活动状态 ,输入的参数为：" + orderDetailList.size());
		for(CustOrderDetail orderDetail : orderDetailList){
			controlActiveState(orderDetail,content);
		}
		return ret;
	}
	
	public List<CustOrderDetail> listCustOrderDetail(CustOrderDetail orderDetail) throws Exception{
		List<CustOrderDetail> retList = null;
		logger.debug("根据条件查询订单明细 ,输入的参数为：" + orderDetail.toString());
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		retList = cDetailDao.listCustOrderDetail(orderDetail);
		return retList;
		
	}
	
	public Integer listCustOrderDetailCount(CustOrderDetail orderDetail) throws Exception{
		Integer iRet = 0;
		logger.debug("根据条件查询订单明细数量 ,输入的参数为：" + orderDetail.toString());
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		iRet = cDetailDao.listCustOrderDetailCount(orderDetail);
		return iRet;
	}
	
	private List<CustOrderDetail> getDetailState(CustOrderDetail orderDetail) throws Exception{
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		return cDetailDao.listCustOrderDetailState(orderDetail);
	}
	
}
