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

import com.hbs.customer.common.utils.CustLogUtils;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.manager.helper.CustOrderState;

import com.hbs.customerorder.utils.CustOrderUtils;

import com.hbs.domain.customer.order.dao.CustomerOrderDao;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.customer.order.pojo.CustomerOrder;
import com.hbs.domain.vendor.vendorinfo.dao.VendorInfoDao;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;

/**
 * @author Administrator
 *
 */
public class CustOrderMgr {
	private static final Logger logger = Logger.getLogger(CustOrderMgr.class);
	private static final String VENDORINFO_DAO ="vendorInfoDao";
	/**
	 * SPRING配置项的前缀
	 */
	public static final String PRE_SPRING ="custOrder";
	
	/**
	 * 操作者为业务助理
	 * 保存客户订单的临时数据，数据的状态为
	 * 01---包括客户订单临时数据，包括订单数据和订单明细数据
	 * 
	 * 账期的字段的值，需要根据结算类型来设置，预付费没有账期概念
	 * 客户订单的账期主要是判断账期的最大金额使用，如果用户没有输入账期
	 * 则系统在后台自动计算账期
	 * 
	 * 对预付费的情况，不存在账期
	 * 这里同时存在订单和退货单
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public int saveTempCustomerOrder(CustomerOrder cOrder) throws Exception{
		logger.debug("保存客户订单的临时数据,输入的参数为：" + cOrder.toString());
		String period = cOrder.getPeriod();
		if(null == period){
			CustOrderState orderState =(CustOrderState)BeanLocator.getInstance().getBean(PRE_SPRING + cOrder.getPoNoType() + cOrder.getSettlementType());
			cOrder.setPeriod(orderState.getPeriod(cOrder));			
		}
		cOrder.setState(CustOrderConstants.ORDER_STATE_01);
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		cOrderDao.insertCustomerOrder(cOrder);
		List<CustOrderDetail> orderDetailList = cOrder.getOrderDetailList();
		if(null != orderDetailList){
			for(CustOrderDetail orderDetail : orderDetailList){
				orderDetail.setPeriod(period);				
			}
			CustOrderDetailMgr detailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
			detailMgr.saveTempOrderDetailList(orderDetailList, null, null);
		}
		//log
		CustLogUtils.operLog(cOrder.getStaffId(), cOrder.getStaffName(), "新增", "客户订单", cOrder.getBizKey(), null, null);
		return 0;
	}
	/**
	 * 
	 * 修改客户订单
	 * 客户订单的修改操作只能是业务助理
	 * 
	 * 订单在提交正式流程前可以修改，提及后不能修改
	 * 对账期订单，经理审批最大金额不通过，也可以修改
	 * 
	 * 如果需要可替代物品，则采购打回，不在修改范围
	 * 可替代物品需要客户认可
	 * 可替代品的操作流程为，先取消，再新增，统一流程
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public int updateTempCustomerOrder(CustomerOrder cOrder) throws Exception{
		logger.debug("修改客户订单,输入的参数为：" + cOrder.toString());
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		cOrderDao.updateCustomerOrder(cOrder);
		List<CustOrderDetail> orderDetailList = cOrder.getOrderDetailList();
		if(null != orderDetailList){			
			CustOrderDetailMgr detailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
			detailMgr.updateTempOrderDetailList(orderDetailList, null);
		}
		//log
		CustLogUtils.operLog(cOrder.getStaffId(), cOrder.getStaffName(), "修改", "客户订单", cOrder.getBizKey(), null, null);
		
		return 0;
	}
	
	/**
	 * 提交客户订单，走正式流程，流程分为：
	 * 订单：
	 * 账期订单  -- 判断是否超出账期金额，流向经理或采购
	 *               超出最大金额，尽量审批  state="50",待经理审批
	 *               否则                   state="20"，待采购处理       
	 * 预付费，货到付款 --流向财务部，等待财务确认预付费
	 *                预付0  ，state="20"，待采购处理
	 *                预付费0  state="30"，待财务确认预付款 
	 * 预付费，款到发货 --流向财务部 ，等待财务确认预付费
	 *                预付0  ，state="20"，待采购处理
	 *                预付费0  state="30"，待财务确认预付款
	 * 
	 * 退货单：
	 *   流向仓库，收货
	 *      state =?
	 *  依据不同的流程，发出不同的待办
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public int commitCustomerOrder(CustomerOrder cOrder) throws Exception{
		int ret =0;
		logger.debug("提交客户订单,输入的参数为：" + cOrder.toString());
		CustOrderState orderState =(CustOrderState)BeanLocator.getInstance().getBean(PRE_SPRING + cOrder.getPoNoType() + cOrder.getSettlementType());
		String state = orderState.commitCustomerOrder(cOrder);
		cOrder.setState(state);
		ret = updateCustCustomerState(cOrder, true);
		//waittask
		WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
		if(state.equals(CustOrderConstants.ORDER_STATE_20)){
			waitTaskInfo.setStaffId(getVendorStaffId(cOrder.getVendorCode()));//这里需要根据供应商查找对应的采购员
		}
		Map<String , String> hmParam = new HashMap<String,String>();
		hmParam.put("$staffName", cOrder.getStaffName());
		hmParam.put("$businessKey", cOrder.getWaitTaskBizKey());
		waitTaskInfo.setHmParam(hmParam);		
		CustOrderUtils.processCreateWaitTask(null,state, waitTaskInfo);
		//log
		CustLogUtils.operLog(cOrder.getStaffId(),cOrder.getStaffName(), "提交","客户订单", cOrder.getLogBizKey(),null,null);
		return ret;
		
	}
	
	/**
	 * 取消客户订单，同时取消该订单下所有的明细
	 * 取消状态为：03
	 * 如果存在待办，将清除所有待办
	 * @param cOrder
	 * @param cancelContent  取消原因，记录在日志中
	 * @return
	 * @throws Exception
	 */
	public int cancelCustOrder(CustomerOrder cOrder , String cancelContent) throws Exception{
		int ret =0;
		logger.debug("取消客户订单,输入的参数为：" + cOrder.toString());
		cOrder.setState(CustOrderConstants.ORDER_STATE_03);
		ret = updateCustCustomerState(cOrder, true);
		//waittask
		CustOrderUtils.processDeleteWaitTask(cOrder.getBizKey());
		//log
		CustLogUtils.operLog(cOrder.getStaffId(),cOrder.getStaffName(), "取消","客户订单", cOrder.getLogBizKey(),null,cancelContent);
		
		return ret;
	}
		
	/**
	 * 经理审批通过超过账期最大金额的客户订单，审批结果为通过
	 * 由状态50（待经理审批）变为20（待采购处理）
	 * 需要发待办给采购
	 * @param cOrder  客户订单，可以不包括订单明细，系统自动更新明细状态
	 * @param auditId 审批人ID
	 * @param auditName  审批人姓名
	 * @param auditContents  审批意见
	 * @return
	 * @throws Exception
	 */
	public int auditAgreeCustOrder(CustomerOrder cOrder,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("经理审批通过超过账期最大金额的客户订单,输入的参数为：" + cOrder.toString());
		if(cOrder.getState().equals(CustOrderConstants.ORDER_STATE_50)){
			cOrder.setState(CustOrderConstants.ORDER_STATE_20);
			ret = updateCustCustomerState(cOrder, true);
			//waittask			
			WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
			if(cOrder.getState().equals(CustOrderConstants.ORDER_STATE_20)){
				waitTaskInfo.setStaffId(getVendorStaffId(cOrder.getVendorCode()));//这里需要根据供应商查找对应的采购员
			}
			Map<String , String> hmParam = new HashMap<String,String>();
			hmParam.put("$staffName", auditName);
			hmParam.put("$businessKey", cOrder.getWaitTaskBizKey());
			waitTaskInfo.setHmParam(hmParam);		
			CustOrderUtils.processCreateWaitTask("CUST_ORDER_003",null, waitTaskInfo);
			//log
			CustLogUtils.operLog(auditId,auditName, "审批通过","客户订单", cOrder.getLogBizKey(),null,auditContents);
			
		}else{
			//ret =2;
			throw new Exception("输入的订单状态为非待审批，无法执行操作！");
		}
		return ret;
	}
	
	/**
	 * 财务确认了客户订单的预付款，待采购处理
	 * 状态有30（待财务确认）变为 20（待采购处理）
	 * @param cOrder  客户订单
	 * @param auditId   财务ID
	 * @param auditName  财务姓名
	 * @param auditContents  确认意见
	 * @return
	 * @throws Exception
	 */
	public int financeAgreeCustOrder(CustomerOrder cOrder,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("财务确认了客户订单的预付款,输入的参数为：" + cOrder.toString());
		if(cOrder.getState().equals(CustOrderConstants.ORDER_STATE_30)){
			cOrder.setState(CustOrderConstants.ORDER_STATE_20);
			ret = updateCustCustomerState(cOrder, true);
			//waittask			
			WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
			if(cOrder.getState().equals(CustOrderConstants.ORDER_STATE_20)){
				waitTaskInfo.setStaffId(getVendorStaffId(cOrder.getVendorCode()));//这里需要根据供应商查找对应的采购员
			}
			Map<String , String> hmParam = new HashMap<String,String>();
			hmParam.put("$staffName", auditName);
			hmParam.put("$businessKey", cOrder.getWaitTaskBizKey());
			waitTaskInfo.setHmParam(hmParam);		
			CustOrderUtils.processCreateWaitTask("CUST_ORDER_007",null, waitTaskInfo);
			//log
			CustLogUtils.operLog(auditId,auditName, "确认预付款","客户订单", cOrder.getLogBizKey(),null,auditContents);
			
		}else{
			//ret =2;
			throw new Exception("输入的订单状态为非待财务确认，无法执行操作！");
		}
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
	 * 财务退回待财务预付款确认客户订单，待业务助理处理
	 * 状态有30（待财务确认）变为 39（财务退单）
	 * 系统有可能有这种场景，已经下单，但长期不付款，导致订单失效
	 * 财务退回后，业务助理可以取消客户订单
	 * @param cOrder  客户订单
	 * @param auditId   财务ID
	 * @param auditName  财务姓名
	 * @param auditContents  退单意见
	 * @return
	 * @throws Exception
	 */
	public int financeDisAgreeCustOrder(CustomerOrder cOrder,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("财务退回待财务预付款确认客户订单,输入的参数为：" + cOrder.toString());
		if(cOrder.getState().equals(CustOrderConstants.ORDER_STATE_30)){
			cOrder.setState(CustOrderConstants.ORDER_STATE_39);
			ret = updateCustCustomerState(cOrder, true);
			//waittask			
			WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
			waitTaskInfo.setStaffId(cOrder.getStaffId());//这里需要根据客户查找对应的业务助理
			Map<String , String> hmParam = new HashMap<String,String>();
			hmParam.put("$staffName", auditName);
			hmParam.put("$businessKey", cOrder.getWaitTaskBizKey());
			waitTaskInfo.setHmParam(hmParam);		
			CustOrderUtils.processCreateWaitTask("CUST_ORDER_009",null, waitTaskInfo);
			//log
			CustLogUtils.operLog(auditId,auditName, "财务退单","客户订单", cOrder.getLogBizKey(),null,auditContents);
			
		}else{
			//ret =2;
			throw new Exception("输入的订单状态为非待财务确认，无法执行操作！");
		}
		return ret;
	}
	
	/**
	 * 经理审批不通过超过账期最大金额的客户订单，审批结果为不通过
	 * 由状态50（待经理审批）变为52（经理审批不通过） 
	 * 需要发待办给业务助理
	 * @param cOrder 客户订单，可以不包括订单明细，系统自动更新明细状态
	 * @param auditId  审批人ID
	 * @param auditName 审批人姓名
	 * @param auditContents 审批不通过原因
	 * @return
	 * @throws Exception
	 */
	public int auditDisAgreeCustOrder(CustomerOrder cOrder,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("经理审批不通过超过账期最大金额的客户订单,输入的参数为：" + cOrder.toString());
		if(cOrder.getState().equals(CustOrderConstants.ORDER_STATE_50)){
			cOrder.setState(CustOrderConstants.ORDER_STATE_52);
			ret = updateCustCustomerState(cOrder, true);
			
			//waittask			
			WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
			waitTaskInfo.setStaffId(cOrder.getStaffId());//这里需要根据客户查找对应的业务员
			Map<String , String> hmParam = new HashMap<String,String>();
			hmParam.put("$staffName", auditName);
			hmParam.put("$businessKey", cOrder.getWaitTaskBizKey());
			waitTaskInfo.setHmParam(hmParam);		
			CustOrderUtils.processCreateWaitTask("CUST_ORDER_004",null, waitTaskInfo);
			
			//log
			CustLogUtils.operLog(auditId,auditName, "审批不通过","客户订单", cOrder.getLogBizKey(),null,auditContents);
			
		}else{
			//ret =2;
			throw new Exception("输入的订单状态为非待经理审批，无法执行操作！");
		}
		return ret;
	}
	
	
	/**
	 * 订单的活动状态控制，操作人为业务助理
	 * 前台调用时不需要改变状态，由后台判断改变
	 * 当订单状态变为暂停时，取消所有待办
	 * @param cOrder
	 * @param operContents  操作说明，记录日志
	 * @return
	 * @throws Exception
	 */
	public int controlActiveState(CustomerOrder cOrder,String operContents) throws Exception{
		int ret =0;
		logger.debug("订单的活动状态控制,输入的参数为：" + cOrder.toString());
		String activeState = cOrder.getActiveState();
		if((CustOrderConstants.ORDER_ACTIVE_STATE).equals(activeState)){
			cOrder.setActiveState(CustOrderConstants.ORDER_PAUSE_STATE);
		}else{
			cOrder.setActiveState(CustOrderConstants.ORDER_ACTIVE_STATE);
		}
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		cOrderDao.updateCustomerOrderByActiveState(cOrder);
		List<CustOrderDetail> orderDetailList = cOrder.getOrderDetailList();
		if(null != orderDetailList){
			for(CustOrderDetail orderDetail : orderDetailList){
				orderDetail.setActiveState(cOrder.getActiveState());
			}
			CustOrderDetailMgr detailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
			detailMgr.controlActiveStateList(orderDetailList, null);
		}
		//waittask
		if((CustOrderConstants.ORDER_PAUSE_STATE).equals(activeState)){
			CustOrderUtils.processDeleteWaitTask(cOrder.getBizKey());
		}
		//log
		CustLogUtils.operLog(cOrder.getStaffId(),cOrder.getStaffName(), ((cOrder.getActiveState()).equals(CustOrderConstants.ORDER_ACTIVE_STATE) ? "激活" : "暂停"),"客户订单", cOrder.getLogBizKey(),null,operContents);
		
		return ret;
	}
	
	/**
	 * 根据业务主键查询客户订单，boolean决定是否同时查询出订单明细
	 * @param cOrder 订单查询条件（comCode ， poNo）
	 * @param isDetail  是否查询出订单明细  true 查询  false 不查询
	 * @return
	 * @throws Exception
	 */
	public CustomerOrder findCustomerOrderByBizKey(CustomerOrder cOrder, boolean isDetail)throws Exception{
		CustomerOrder retOrder = null;
		logger.debug("根据业务主键查询客户订单,输入的参数为：" + cOrder.toString());
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		retOrder = cOrderDao.findCustomerOrder(cOrder);
		if(isDetail){
			CustOrderDetailMgr detailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
			CustOrderDetail orderDetail = new CustOrderDetail();
			orderDetail.setCommCode(cOrder.getCommCode());
			orderDetail.setPoNo(cOrder.getPoNo());
			retOrder.setOrderDetailList(detailMgr.listCustOrderDetail(orderDetail));
		}
		return retOrder;
	}
	/**
	 * 根据查询条件，查询订单列表，支持分页查询
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public List<CustomerOrder> listCustomerOrder(CustomerOrder cOrder) throws Exception{
		List<CustomerOrder> retList = null;
		logger.debug("根据查询条件，查询订单列表,输入的参数为：" + cOrder.toString());
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		retList = cOrderDao.listCustomerOrder(cOrder);
		return retList;
	}
	
	/**
	 * 根据查询条件，查询符合条件的订单数量
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public Integer listCustomerOrderCount(CustomerOrder cOrder) throws Exception{
		Integer retI = null;
		logger.debug("根据查询条件，查询符合条件的订单数量,输入的参数为：" + cOrder.toString());
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		retI = cOrderDao.listCustomerOrderCount(cOrder);
		return retI;
	}
	/**
	 * 更新客户订单状态，订单明细状态需要根据boolean参数判断是否更新
	 * 本方法只更新客户订单状态，其他属性不更新
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	private int updateCustCustomerState(CustomerOrder cOrder, boolean isDetail) throws Exception{
		int ret =0;
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		cOrderDao.updateCustomerOrderByState(cOrder);
		if(isDetail){
			CustOrderDetailMgr detailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
			CustOrderDetail orderDetail = new CustOrderDetail();
			orderDetail.setCommCode(cOrder.getCommCode());
			orderDetail.setPoNo(cOrder.getPoNo());
			orderDetail.setState(cOrder.getState());
			Map<String,Object> hm = new HashMap<String,Object>();
			hm.put("state", CustOrderConstants.ORDER_STATE_03);
			orderDetail.setDynamicFields(hm);
			detailMgr.updateCustDetailByState(orderDetail);
		}
		return ret;
	}
	
}
