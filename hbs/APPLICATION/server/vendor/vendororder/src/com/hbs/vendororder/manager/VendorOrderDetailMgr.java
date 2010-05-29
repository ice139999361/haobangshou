/**
 * system ：hbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendororder.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.IntegerUtils;
import com.hbs.common.utils.OrderCalUtils;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.domain.customer.order.dao.CustOrderDetailDao;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.vendor.order.dao.VendorOrderDao;
import com.hbs.domain.vendor.order.dao.VendorOrderDetailDao;

import com.hbs.domain.vendor.order.pojo.VendorOrder;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;
import com.hbs.vendor.common.utils.VendorLogUtils;
import com.hbs.vendororder.constants.VendorOrderConstants;
import com.hbs.vendororder.manager.helper.VendorOrderState;

public class VendorOrderDetailMgr {
	
	private static final Logger logger = Logger.getLogger(VendorOrderDetailMgr.class);
	
	/**
	 * 保存新增的订单明细，有采购操作
	 * @param detail  订单明细
	 * @param isflowOrder 是否是跟随主订单一起
	 * @return >0 成功
	 * @throws Exception
	 */
	public int saveTempOrderDetail(VendorOrderDetail detail,boolean isflowOrder) throws Exception{
		int ret =0;
		logger.debug("保存订单明细：输入的参数为：" + detail.toString());
		Integer operSeqId = detail.getOperSeqId();
		detail.setMoney(OrderCalUtils.calOrderMoney(detail.getCprice(), detail.getIsTax(), detail.getTaxRate(), detail.getCpriceTax(),null,detail.getAmount()));
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		if(null == operSeqId){//不存在序列号，表示是新增
			detail.setState(VendorOrderConstants.VENDOR_ORDER_STATE_01);
			operSeqId = vDetailDao.insertVendorOrderDetail(detail);
			detail.setOperSeqId(operSeqId);
			ret = operSeqId;
			logger.debug("保存订单明细：不存在operSeqId，做新增操作！新增的operSeqId=" + ret);
			if(!isflowOrder){//不是更随主订单提交，记录操作日志
				VendorLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), "新增" , "供应商订单明细", detail.getLogKey(), null, null);
			}
		}else{//存在序列号，需要做修改处理
			logger.debug("保存订单明细：存在operSeqId , 修改操作!");
			updateTempOrderDetail(detail,isflowOrder,null);
			ret = detail.getOperSeqId();
		}
		return ret;
	}
	
	/**
	 * 批量保持订单明细
	 * @param detailList  明细列表
	 * @param isflowOrder  是否是跟随主订单一起
	 * @return
	 * @throws Exception
	 */
	public int saveTempOrderDetailList(List<VendorOrderDetail> detailList , boolean isflowOrder) throws Exception{
		int ret =0;
		logger.debug("保存订单明细列表：列表的数量为：" + detailList.size());
		for(VendorOrderDetail vDetail : detailList){
			saveTempOrderDetail(vDetail,isflowOrder);
		}
		return ret;
	}
	/**
	 * 修改临时订单明细，由采购操作
	 * @param detail  订单明细
	 * @param isflowOrder  是否是跟随主订单一起修改
	 * @param content  修改说明，记录日志
	 * @return
	 * @throws Exception
	 */
	public int updateTempOrderDetail(VendorOrderDetail detail,boolean isflowOrder , String content) throws Exception{
		int ret =0;
		logger.debug("修改临时订单明细，输入的参数为：" + detail.toString());
		detail.setMoney(OrderCalUtils.calOrderMoney(detail.getCprice(), detail.getIsTax(), detail.getTaxRate(),detail.getCprice(),null, detail.getAmount()));
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		vDetailDao.updateVendorOrderDetail(detail);
		if(!isflowOrder){//不是更随主订单提交，记录操作日志
			VendorLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), "修改" , "供应商订单明细", detail.getLogKey(), null, content);
		}
		return ret;
	}
	/**
	 * 批量修改临时订单明细，由采购操作
	 * @param detailList 订单明细列表
	 * @param isflowOrder 是否是跟随主订单一起
	 * @param content  修改说明，记录日志
	 * @return
	 * @throws Exception
	 */
	public int updateTempOrderDetailList(List<VendorOrderDetail> detailList , boolean isflowOrder,String content) throws Exception{
		int ret =0;
		logger.debug("批量修改订单明细列表：列表的数量为：" + detailList.size());
		for(VendorOrderDetail vDetail : detailList){
			updateTempOrderDetail(vDetail,isflowOrder,content);
		}
		return ret;
	}
	/**
	 * 取消订单明细，状态变为03
	 ** 如果只传operSeqid，则是单条更新
	 * 如果传入commCode，poNo 则是所有订单明细都更新状态
	 * @param detail
	 * @param isflowOrder 是否跟随主订单同时取消
	 * @param content  取消说明,记录日志
	 * @return
	 * @throws Exception
	 */
	public int cancelOrderDetail(VendorOrderDetail detail,boolean isflowOrder , String content) throws Exception{
		int ret =0;
		logger.debug("取消订单明细，输入的参数为：" + detail.toString());
			if(!isflowOrder){//如果不是随主订单，则需要处理状态为03
				detail.setState(VendorOrderConstants.VENDOR_ORDER_STATE_03);
			}
			ret = updateOrderDetailByState(detail);
			//处理操作日志
			if(!isflowOrder){//不随主订单，则需要记录操作日志
				VendorLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), "取消" , "供应商订单明细", detail.getLogKey(), null, content);
			}
		return ret;
	}
	/**
	 * 取消订单明细列表，状态变为03
	 * @param detailList
	 * @param isflowOrder 是否跟随主订单同时取消
	 * @param content 取消说明,记录日志
	 * @return
	 * @throws Exception
	 */
	public int cancelOrderDetailList(List<VendorOrderDetail> detailList , boolean isflowOrder,String content) throws Exception{
		logger.debug("批量取消订单明细列表：列表的数量为：" + (detailList == null ? 0 : detailList.size()));
		if(null != detailList && detailList.size() >0){
			
			for(VendorOrderDetail detail : detailList){
				cancelOrderDetail(detail,isflowOrder,content);
			}
		}
		return 0;
	}
	/**
	  * 提交订单明细，
	 * 对应账期订单明细，订单状态为04 待确认交期
	 * 对应预付费订单明细，订单状态为02 ，确认订单   待收货入库
	 * 对于提交的供应商订单，如果是来自客户订单，则需要关联到客户
	 * 订单，更新关联的采购单号
	 * @param detail
	 * @param isflowOrder 是否跟随主订单同时提交
	 * @param content  提交说明，记录日志
	 * @return
	 * @throws Exception
	 */
	public int commitOrderDetail(VendorOrderDetail detail,boolean isflowOrder , String content) throws Exception{
		int ret =0;
		logger.debug("提交订单明细，输入的参数为：" + detail.toString());
		if(!isflowOrder){//如果不是随主订单，则需要处理状态为02
			//detail.setState(VendorOrderConstants.VENDOR_ORDER_STATE_02);
			VendorOrderState orderState =(VendorOrderState)BeanLocator.getInstance().getBean(VendorOrderConstants.PRE_SPRING + detail.getPoNoType() + detail.getSettlementType());
			String state = orderState.getCommitState();
			detail.setState(state);
		}
		ret = updateOrderDetailByState(detail);
		//处理客户订单关联的采购单号
		logger.debug("提交订单明细，处理客户订单关联的采购单号！");
		String poNoType = detail.getPoNoType();
		if(null != poNoType && poNoType.equals(VendorOrderConstants.VENDOR_PO_NO_TYPE_0)){//采购单关联客户订单
			CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.CUST_ORDER_DETAIL_DAO);
			String[] strPoNo = detail.getRltOrderPoNo().split(",");
			if(null != strPoNo && strPoNo.length >0){
				for(String pono  : strPoNo){
					CustOrderDetail cDetail = new CustOrderDetail();
					cDetail.setCommCode(detail.getCustCcode());
					cDetail.setPoNo(pono);
					cDetail.setPartNo(detail.getPartNo());
					cDetail.setSpecDesc(detail.getSpecDesc());
//					cDetail = cDetailDao.findCustOrderDetailByBizKey(cDetail);
//					if(null != cDetail){
//						String rltPoNo = cDetail.getRltOrderPoNo();
//						if(null == rltPoNo){
							cDetail.setRltOrderPoNo(pono);
//						}else{
//							cDetail.setRltOrderPoNo(rltPoNo +"," + pono);
//						}
						cDetailDao.updateCustOrderDetailByRltPoNo(cDetail);
//					}
				}
			}
		}
		//处理操作日志
		if(!isflowOrder){//不随主订单，则需要记录操作日志
			VendorLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), "提交" , "供应商订单明细", detail.getLogKey(), null, content);
		}
		return ret;
	}
	/**
	 * 采购的物料入库，更新相关状态
	 * 采购明细更新到货数量和状态（部分入库60和全部入库61）
	 * 同时判断采购单的状态（部分入库60和全部入库61） 
	 * @param detail
	 * @return 0--成功  2--更新的数目不正确  1 无此采购单明细
	 * @throws Exception
	 */
	public int updateOrderDetailByAmount(VendorOrderDetail detail) throws Exception{
		logger.debug("提交订单明细，采购的物料入库，更新相关状态，输入的参数为：" + detail.toString());
		int ret =0;
		int delmount = IntegerUtils.intValue(detail.getDeliveryAmount());
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		VendorOrderDao vDao =(VendorOrderDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DAO);
		VendorOrderDetail tempDetail = vDetailDao.findVendorOrderDetailByBizKey(detail);
		if(null != tempDetail){
			int iMount = IntegerUtils.intValue(tempDetail.getAmount());
			int iDelivMount = IntegerUtils.intValue(tempDetail.getDeliveryAmount());
			int updateAmount = delmount + iDelivMount;
			logger.debug("提交订单明细,订单总数量为：" + iMount +"已经入库数量为：" + iDelivMount + "本次入库数量为：" + IntegerUtils.intValue(detail.getDeliveryAmount()));
			if(updateAmount > iMount){//更新的数目不对
				//ret =2;
				logger.debug("提交订单明细,更新的数码不正确，订单总数量 < 已经入库数量 + 本次入库数量");
				throw new Exception("提交订单明细,更新的数码不正确，订单总数量 < 已经入库数量 + 本次入库数量");
			}else if(updateAmount < iMount){//部分入库
				logger.debug("提交订单明细,更新的数码，订单总数量 < 已经入库数量 + 本次入库数量 ,部分入库！");
				tempDetail.setState(VendorOrderConstants.VENDOR_ORDER_STATE_60);
				tempDetail.setDeliveryAmount(updateAmount);
				vDetailDao.updateVendorOrderDetailAmount(tempDetail);
				//更新采购单状态为部分入库
				VendorOrder vOrder = new VendorOrder();
				vOrder.setCommCode(detail.getCommCode());
				vOrder.setPoNo(detail.getPoNo());
				vOrder.setState(VendorOrderConstants.VENDOR_ORDER_STATE_60);
				vDao.updateVendorOrderByState(vOrder);
			}else{//全部入库，除更新采购单明细外，还需要更新采购单
				logger.debug("提交订单明细,更新的数码，订单总数量 = 已经入库数量 + 本次入库数量，全部入库！");
				tempDetail.setState(VendorOrderConstants.VENDOR_ORDER_STATE_61);
				tempDetail.setDeliveryAmount(updateAmount);
				vDetailDao.updateVendorOrderDetailAmount(tempDetail);
				
				logger.debug("提交订单明细,更新订单状态");
				List<VendorOrderDetail> detailList = vDetailDao.listVendorOrderDetail(detail);
				boolean isAll = true;
				if(null != detailList && detailList.size()>0){
					VendorOrder vOrder = new VendorOrder();
					vOrder.setCommCode(detail.getCommCode());
					vOrder.setPoNo(detail.getPoNo());
					for(VendorOrderDetail vDetail : detailList){
						String state = vDetail.getState();
						if(state.equals(VendorOrderConstants.VENDOR_ORDER_STATE_60)){
							isAll =false;
							break;
						}
					}
					vOrder.setState( isAll ? VendorOrderConstants.VENDOR_ORDER_STATE_61 :VendorOrderConstants.VENDOR_ORDER_STATE_60);
					vDao.updateVendorOrderByState(vOrder);
				}
			}
		}else{//无此采购订单明细信息
			logger.debug("提交订单明细,库中无此订单明细信息，不能更新！");
			//ret =1;
			throw new Exception("提交订单明细,库中无此订单明细信息，不能更新！");
		}
		
		
		return ret;
	}
	
	/**
	 *确认账期订单明细的交期，只对账期订单明细有效
	 *账期订单的状态由04（待交期确认）变为 02确认订单，待入库
	 * @param vDetail
	* @param isflowOrder 是否跟随主订单同时提交
	 * @param content  说明，记录日志
	 * @return 0  成功   1  状态不正确
	 * @throws Exception
	 */
	public int confirmOrderDetailDelivery(VendorOrderDetail vDetail,
			boolean isflowOrder, String content) throws Exception {
		int ret = 0;
		logger.debug("确认账期订单明细的交期,输入的参数为：" + vDetail.toString());
		String state = vDetail.getState();
		if (VendorOrderConstants.VENDOR_ORDER_STATE_04.equals(state)) {
			vDetail.setState(VendorOrderConstants.VENDOR_ORDER_STATE_02);

			if (vDetail.getVerDeliveryDate() == null) {
				vDetail.setVerDeliveryDate(vDetail.getOrgDeliveryDate());
			}
			updateOrderDetailByState(vDetail);
		}else{
			//ret =1;
			throw new Exception("确认账期订单明细的交期,状态不正确！");
		}
		//处理操作日志
		if(!isflowOrder){//不随主订单，则需要记录操作日志
			VendorLogUtils.operLog(vDetail.getStaffId(), vDetail.getStaffName(), "交期确认" , "供应商订单明细", vDetail.getLogKey(), null, content);
		}
		return ret;
	}
	/**
	 * 订单的活动状态控制，操作人为采购人员
	 * 前台调用时不需要改变状态，由后台判断改变
	 * @param detail
	 * @param isflowOrder 是否跟随主订单同时操作
	 * @param content  暂停 激活原因，记录日志
	 * @return
	 * @throws Exception
	 */
	public int controlActiveState(VendorOrderDetail detail,boolean isflowOrder,String content) throws Exception{
		int ret =0;
		logger.debug("订单的活动状态控制,输入的参数为：" + detail.toString());
		if(!isflowOrder){
			String activeState = detail.getActiveState();
			if(VendorOrderConstants.VENDOR_ORDER_ACTIVE_STATE.equals(activeState)){
				detail.setActiveState(VendorOrderConstants.VENDOR_ORDER_PAUSE_STATE);
			}else{
				detail.setActiveState(VendorOrderConstants.VENDOR_ORDER_ACTIVE_STATE);
			}
		}
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		vDetailDao.updateVendorOrderDetailByActiveState(detail);
		//记录操作日志
		if(!isflowOrder){
			VendorLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), ((detail.getActiveState()).equals(CustOrderConstants.ORDER_ACTIVE_STATE) ? "激活" : "暂停") , "供应商订单明细", detail.getLogKey(), null, content);
		}
		return ret;
	}
	
	/**
	 * 根据订单明细主键查询订单明细
	 * @param operSeqId
	 * @return
	 * @throws Exception
	 */
	public VendorOrderDetail getVendorOrderDetailById(String operSeqId) throws Exception{
		logger.debug("根据订单明细主键查询订单明细,输入的参数为：" + operSeqId);
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		return vDetailDao.findVendorOrderDetailById(operSeqId);
	}
	/**
	 *根据业务主键查询订单明细
	 *业务主键包括：
	 *  commCode poNo cpartNo partNo specDesc
	 * @param vendorOrderDetail
	 * @return
	 * @throws Exception
	 */
	public VendorOrderDetail getVendorOrderDetailByBizKey(VendorOrderDetail vendorOrderDetail) throws Exception{
		logger.debug("根据业务主键查询订单明细,输入的参数为：" + vendorOrderDetail.toString());
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		return vDetailDao.findVendorOrderDetailByBizKey(vendorOrderDetail);
	}
	
	/**
	 * 根据查询条件，查询出所有符合条件的订单明细列表，支持分页
	 * @param vendorOrderDetail
	 * @return
	 * @throws Exception
	 */
	public List<VendorOrderDetail> getVendorOrderDetailList(VendorOrderDetail vendorOrderDetail) throws Exception{
		logger.debug("根据查询条件，查询出所有符合条件的订单明细列表,输入的参数为：" + vendorOrderDetail.toString());
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		return vDetailDao.listVendorOrderDetail(vendorOrderDetail);
	}
	
	/**
	 * 根据查询条件，查询出所有符合条件的订单明细条数
	 * @param vendorOrderDetail
	 * @return
	 * @throws Exception
	 */
	public Integer getVendorOrderDetailCount(VendorOrderDetail vendorOrderDetail) throws Exception{
		logger.debug("根据查询条件，查询出所有符合条件的订单明细条数,输入的参数为：" + vendorOrderDetail.toString());
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		return vDetailDao.listVendorOrderDetailCount(vendorOrderDetail);
	}
	/**
	 * 私有函数，更新订单明细状态
	 * 如果只传operSeqid，则是单条更新
	 * 如果传入commCode，poNo 则是所有订单明细都更新状态
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	private int updateOrderDetailByState(VendorOrderDetail detail) throws Exception{
		int ret =0;
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		vDetailDao.updateVendorOrderDetailByState(detail);
		return ret;
	}
}
