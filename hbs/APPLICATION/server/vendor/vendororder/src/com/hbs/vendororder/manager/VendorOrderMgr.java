/**
 * system ：hbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendororder.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.manager.syssequence.SysSequenceMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerorder.constants.CustOrderConstants;

import com.hbs.domain.vendor.order.dao.VendorOrderDao;
import com.hbs.domain.vendor.order.dao.VendorOrderDetailDao;
import com.hbs.domain.vendor.order.pojo.VendorOrder;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;
import com.hbs.vendor.common.utils.VendorLogUtils;
import com.hbs.vendororder.constants.VendorOrderConstants;
import com.hbs.vendororder.manager.helper.VendorOrderState;

public class VendorOrderMgr {

	private static final Logger logger = Logger.getLogger(VendorOrderMgr.class);
	
	/**
	 * 保存新增临时供应商订单 状态为01
	 * 判断PONO，如果不存在PONO，说明是新的供应商订单，insert
	 * 否则为存在的临时供应商订单做修改
	 * 如果存在订单明细，则同时对订单明细做处理
	 * @param vOrder
	 * @param content 操作说明
	 * @return
	 * @throws Exception
	 */
	public int saveTempVendorOrder(VendorOrder vOrder ,String content) throws Exception{
		int ret =0;
		logger.debug("保存新增临时供应商订单 ,输入的参数为：" + vOrder.toString());
		String poNo = vOrder.getPoNo();
		VendorOrderDao vOrderDao =(VendorOrderDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DAO);
		if(StringUtils.isEmpty(poNo)){//新的供应商订单，insert操作
			//设置系统产生的pono
			vOrder.setPoNo(SysSequenceMgr.getPoNo(SysSequenceMgr.V_ORDER_PONO));
			String period = vOrder.getPeriod();
			//新订单，计算账期
			if(StringUtils.isEmpty(period)){
				VendorOrderState orderState =(VendorOrderState)BeanLocator.getInstance().getBean(VendorOrderConstants.PRE_SPRING + vOrder.getPoNoType() + vOrder.getSettlementType());
				vOrder.setPeriod(orderState.getPeriod(vOrder));			
			}
			//设置订单状态为采购临时保存状态
			vOrder.setState(VendorOrderConstants.VENDOR_ORDER_STATE_01);
			
			vOrderDao.insertVendorOrder(vOrder);
			
			VendorLogUtils.operLog(vOrder.getStaffId(), vOrder.getStaffName(), "新增" , "供应商订单", vOrder.getLogKey(), null, content);
			
		}else{//已经存在的供应商订单，做update操作
			vOrderDao.updateVendorOrder(vOrder);			
			VendorLogUtils.operLog(vOrder.getStaffId(), vOrder.getStaffName(), "修改" , "供应商订单", vOrder.getLogKey(), null, content);
		}
		//订单明细处理
		List<VendorOrderDetail> detailList = vOrder.getVendorOrderDetailList();
		if(null != detailList && detailList.size() > 0){//存在订单明细
			for(VendorOrderDetail detail : detailList){
				detail.setPeriod(vOrder.getPeriod());
				detail.setPoNo(vOrder.getPoNo());
			}
			//调用订单明细管理类，保存订单明细
			VendorOrderDetailMgr detailMgr =(VendorOrderDetailMgr)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_MGR);
			detailMgr.saveTempOrderDetailList(detailList, true);
		}
		return ret;
	}
	
	/**
	 * 修改供应商临时订单，状态为01
	 * 流程同保存新增订单，判断是否存在PONO，存在update ,不存在insert
	 * 订单在提交正式流程前可以修改，提及后不能修改	
	 * @param vOrder
	 * @param content 修改说明，记录日志
	 * @return
	 * @throws Exception
	 */
	public int updateTempVendorOrder(VendorOrder vOrder,String content) throws Exception{
		logger.debug("修改供应商临时订单 ,输入的参数为：" + vOrder.toString());
		return saveTempVendorOrder(vOrder,content);
	}
	
	/**
	 * 取消订单，状态变为03
	 * 该订单下的所有明细状态全部为03取消状态
	 * @param vOrder
	 * @param content 取消说明，记录日志
	 * @return
	 * @throws Exception
	 */
	public int cancelVendorOrder(VendorOrder vOrder ,String content) throws Exception{
		logger.debug("取消供应商临时订单 ,输入的参数为：" + vOrder.toString());
		vOrder.setState(VendorOrderConstants.VENDOR_ORDER_STATE_03);
		updateVendorOrderByState(vOrder);
		//处理订单明细
		VendorOrderDetailMgr vDetailMgr = (VendorOrderDetailMgr)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_MGR);
		VendorOrderDetail vDetail = new VendorOrderDetail();
		vDetail.setCommCode(vOrder.getCommCode());
		vDetail.setPoNo(vOrder.getPoNo());
		vDetail.setState(vOrder.getState());
		Map<String,String> hm = new HashMap<String,String>();
		hm.put("state", VendorOrderConstants.VENDOR_ORDER_STATE_03);
		//调用订单明细服务，取消所有订单明细
		vDetailMgr.cancelOrderDetail(vDetail, true, content);
		VendorLogUtils.operLog(vOrder.getStaffId(), vOrder.getStaffName(), "取消" , "供应商订单", vOrder.getLogKey(), null, content);
		return 0;
	}
	/**
	 * 提交订单，
	 * 对应账期订单，订单状态为04 待确认交期
	 * 对应预付费订单，订单状态为02 ，确认订单   待收货入库
	 * @param vOrder
	 * @param content  提交说明 ，记录日志
	 * @return
	 * @throws Exception
	 */
	public int commitVendorOrder(VendorOrder vOrder ,String content) throws Exception{
		int ret =0;
		logger.debug("提交供应商临时订单 ,输入的参数为：" + vOrder.toString());
		VendorOrderState orderState =(VendorOrderState)BeanLocator.getInstance().getBean(VendorOrderConstants.PRE_SPRING + vOrder.getPoNoType() + vOrder.getSettlementType());
		String state = orderState.getCommitState(/*vOrder*/);
		vOrder.setState(state);		
		updateVendorOrderByState(vOrder);
		//处理订单明细
		List<VendorOrderDetail> detailList = vOrder.getVendorOrderDetailList();
		if(null != detailList && detailList.size() >0){
			VendorOrderDetailMgr vDetailMgr = (VendorOrderDetailMgr)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_MGR);
			for(VendorOrderDetail vDetail : detailList){
				vDetail.setState(vOrder.getState());
				if(vDetail.getVerDeliveryDate() == null){
					vDetail.setVerDeliveryDate(vDetail.getOrgDeliveryDate());
				}
				vDetailMgr.commitOrderDetail(vDetail, true, null);
			}
		}
//		VendorOrderDetailMgr vDetailMgr = (VendorOrderDetailMgr)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_MGR);
//		VendorOrderDetail vDetail = new VendorOrderDetail();
//		vDetail.setCommCode(vOrder.getCommCode());
//		vDetail.setPoNo(vOrder.getPoNo());
//		vDetail.setState(vOrder.getState());
//		Map<String,String> hm = new HashMap<String,String>();
//		hm.put("state", VendorOrderConstants.VENDOR_ORDER_STATE_03);
//		//调用订单明细服务，订单明细
//		vDetailMgr.commitOrderDetail(vDetail, true, content);
		VendorLogUtils.operLog(vOrder.getStaffId(), vOrder.getStaffName(), "提交" , "供应商订单", vOrder.getLogKey(), null, content);
		return ret;
	}
	/**
	 *确认账期订单的交期，只对账期订单有效
	 *账期订单的状态由04（待交期确认）变为 02确认订单，待入库
	 * @param vOrder
	 * @return   0  成功  
	 * @throws Exception
	 */
	public int confirmOrderDelivery(VendorOrder vOrder,String content) throws Exception{
		int ret =0;
		logger.debug("确认账期订单的交期 ,输入的参数为：" + vOrder.toString());
		String state = vOrder.getState();
		if(VendorOrderConstants.VENDOR_ORDER_STATE_04.equals(state)){
			vOrder.setState(VendorOrderConstants.VENDOR_ORDER_STATE_02);
			updateVendorOrderByState(vOrder);
			//处理订单明细
			List<VendorOrderDetail> detailList = vOrder.getVendorOrderDetailList();
			if(null != detailList && detailList.size() >0){
				VendorOrderDetailMgr vDetailMgr = (VendorOrderDetailMgr)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_MGR);
				for(VendorOrderDetail vDetail : detailList){
					vDetail.setState(vOrder.getState());
					if(vDetail.getVerDeliveryDate() == null){
						vDetail.setVerDeliveryDate(vDetail.getOrgDeliveryDate());
					}
					vDetailMgr.confirmOrderDetailDelivery(vDetail,true,null);
				}
				
			}
			VendorLogUtils.operLog(vOrder.getStaffId(), vOrder.getStaffName(), "交期确认" , "供应商订单", vOrder.getLogKey(), null, content);
		}else{
			//ret =1;
			throw new Exception("确认账期订单的交期,状态不正确，无法确认交期！");
		}
		return ret;
	}
	
	/**
	  * 订单的活动状态控制，操作人为采购人员
	 * 前台调用时不需要改变状态，由后台判断改变
	 * @param vOrder
	 * @param content  暂停 激活原因 记录日志
	 * @return
	 * @throws Exception
	 */
	public int controlActiveState(VendorOrder vOrder ,String content)throws Exception{
		int ret =0;
		logger.debug("订单的活动状态控制 ,输入的参数为：" + vOrder.toString());
		String activeState = vOrder.getActiveState();
		if(VendorOrderConstants.VENDOR_ORDER_ACTIVE_STATE.equals(activeState)){
			vOrder.setActiveState(VendorOrderConstants.VENDOR_ORDER_PAUSE_STATE);
		}else{
			vOrder.setActiveState(VendorOrderConstants.VENDOR_ORDER_ACTIVE_STATE);
		}
		VendorOrderDao vOrderDao =(VendorOrderDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DAO);
		vOrderDao.updateVendorOrderByActiveState(vOrder);
		//处理订单明细
		VendorOrderDetailMgr vDetailMgr = (VendorOrderDetailMgr)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_MGR);
		VendorOrderDetail vDetail = new VendorOrderDetail();
		vDetail.setCommCode(vOrder.getCommCode());
		vDetail.setPoNo(vOrder.getPoNo());
		vDetail.setActiveState(vOrder.getActiveState());
		
		//调用订单明细服务，取消所有订单明细
		vDetailMgr.controlActiveState(vDetail, true, content);
		VendorLogUtils.operLog(vOrder.getStaffId(), vOrder.getStaffName(), ((vOrder.getActiveState()).equals(CustOrderConstants.ORDER_ACTIVE_STATE) ? "激活" : "暂停") , "供应商订单", vOrder.getLogKey(), null, content);
		return ret;
	}
	/**
	 * 根据主键查询供应商订单
	 * @param commCode  供应商编码
	 * @param poNo  订单号
	 * @param isDetail  是否包含订单明细
	 * @return
	 * @throws Exception
	 */
	public VendorOrder getVendorOrder(String commCode,String poNo,boolean isDetail) throws Exception{
		logger.debug("根据主键查询供应商订单 ,输入的参数为：" + commCode +" ;" + poNo);
		VendorOrder vOrder = new VendorOrder();
		VendorOrderDao vOrderDao =(VendorOrderDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DAO);
		vOrder.setCommCode(commCode);
		vOrder.setPoNo(poNo);
		vOrder = vOrderDao.findVendorOrder(vOrder);
		if(null != vOrder && isDetail){
			VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
			VendorOrderDetail vDetail = new VendorOrderDetail();
			vDetail.setCommCode(commCode);
			vDetail.setPoNo(poNo);
			vOrder.setVendorOrderDetailList(vDetailDao.listVendorOrderDetail(vDetail));
		}
		return vOrder;
	}
	
	/**
	 * 根据查询条件，查询供应商订单列表
	 * @param vOrder
	 * @return
	 * @throws Exception
	 */
	public List<VendorOrder> getVendorOrderList(VendorOrder vOrder) throws Exception{
		logger.debug("根据查询条件，查询供应商订单列表 ,输入的参数为：" + vOrder.toString());
		VendorOrderDao vOrderDao =(VendorOrderDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DAO);
		return vOrderDao.listVendorOrder(vOrder);
	}
	/**
	 * 根据查询条件，查询供应商订单数目
	 * @param vOrder
	 * @return
	 * @throws Exception
	 */
	public Integer getVendorOrderCount(VendorOrder vOrder) throws Exception{
		logger.debug("根据查询条件，查询供应商订单数目 ,输入的参数为：" + vOrder.toString());
		VendorOrderDao vOrderDao =(VendorOrderDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DAO);
		return vOrderDao.listVendorOrderCount(vOrder);
	}
	/**
	 * 私有函数，更新订单状态
	 * @param vOrder
	 * @return
	 * @throws Exception
	 */
	private int updateVendorOrderByState(VendorOrder vOrder) throws Exception{
		int ret =0;
		VendorOrderDao vOrderDao =(VendorOrderDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DAO);
		vOrderDao.updateVendorOrderByState(vOrder);		
		return ret;
	}
}
