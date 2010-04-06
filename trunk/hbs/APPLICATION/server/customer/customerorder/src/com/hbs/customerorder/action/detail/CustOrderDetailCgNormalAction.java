package com.hbs.customerorder.action.detail;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.manager.WarehouseMgr;

@SuppressWarnings("serial")
public class CustOrderDetailCgNormalAction extends CustOrderDetailBaseAction {
	/**
	 * logger.
	 */
	protected static Logger logger = Logger.getLogger(CustOrderDetailCgNormalAction.class);

	/**
	 * 采购确认订单明细的交期
	 * @action.input orderDetail.*
	 * @action.input memo 取消原因
	 * @return
	 */
	public String doConfirmDelivery() {
		try {
			if(!checkKeyFields()) {
				setErrorReason("参数错误！");
				return ERROR;
			}
			int i = mgr.purchaseConfirmDetailDelivery(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				setErrorReason("提交出错！");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmDelivery", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 采购不同意订单明细交期，提交给业务助理处理
	 * @action.input orderDetail.*
	 * @action.input memo 取消原因
	 * @return
	 */
	public String doRefuseDelivery() {
		try {
			if(!checkKeyFields()) {
				setErrorReason("参数错误！");
				return ERROR;
			}
			int i = mgr.purchaseRefuseDetailDelivery(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				setErrorReason("提交出错！");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doRefuseDelivery", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 采购员查看物料库存
	 * @action.input orderDetail.operSeqId
	 * @action.result self.* 本客户备货库存信息 WareHouseInfo
	 * @action.result common.* 常规备货库存信息 WareHouseInfo
	 * @action.result otherList 其他客户备货库存信息列表 List<WareHouseInfo>
	 * @return
	 */
	public String doGetStockInfo() {
		try {
			if(!findOrderDetail())
				return ERROR;

			WareHouseInfo wInfo = new WareHouseInfo();
			WarehouseMgr wmgr = (WarehouseMgr)getBean(WareHouseConstants.WAREHOUSE_INFO_MGR);
			//wInfo.setCpartNo(orderDetail.getCpartNo());
			wInfo.setPartNo(orderDetail.getPartNo());
			String commCode = orderDetail.getCommCode();
			boolean foundSelf = false;
			boolean foundCommon = false;
			List<WareHouseInfo> list = new Vector<WareHouseInfo>();
			for(WareHouseInfo wInfo2 : wmgr.listWareHouseInfo(wInfo)) {
				try{
					if(!WareHouseConstants.WAREHOUSE_INFO_STATE_0.equals(wInfo2.getState())
							||wInfo2.getUseAmount().intValue() <= 0
							)
						continue;
					if(WareHouseConstants.WAREHOUSE_USE_TYPE_1.equals(wInfo2.getHouseUse())) {
						// 特定客户备货
						if(commCode.equals(wInfo2.getCustCode())) {
							// 本客户备货
							setResult("self", wInfo2);
							foundSelf = true;
						} else {
							// 其他客户备货
							list.add(wInfo2);
						}
					} else {
						// 常规备货
						setResult("common", wInfo2);
						foundCommon = true;
					}
				} catch(Exception e) {
					logger.info("catch Exception in doGetStockInfo when check stock:" + wInfo2, e);
				}
			}
			setResult("otherList", list);
			
			//如果没有库存，则设置可用库存为0
			wInfo.setUseAmount(0);
			wInfo.setTotalAmount(0);
			wInfo.setLockAmount(0);
			if(!foundSelf)
				setResult("self", wInfo);
			if(!foundCommon)
				setResult("common", wInfo);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doGetStockInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 下供应商订单时，用于将客户订单详情按照供应商编码分割
	 * @action.input	operSeqId	待分割的客户订单详情id，以,分割
	 * @action.result	commCode	供应商编码，可能不出现
	 * @action.result	list	客户订单详情列表 List<CustOrderDetail>
	 * @action.result	leftOperSeqId	剩余的客户订单详情id List<String>
	 * @return
	 */
	public String doCheckOperSeqId() {
		try {
			String commCode = null;
			List<String> left = new Vector<String>();
			List<CustOrderDetail> details = new Vector<CustOrderDetail>();
			String[] ids = this.getHttpServletRequest().getParameter("operSeqId").split(",");
			CustOrderDetail orderDetail2;
			for(String id : ids) {
				if(StringUtils.isEmpty(id))
					continue;
				try {
					orderDetail2 = mgr.findCustOrderDetailById(id);
					if(orderDetail2 == null)
						continue;
					
					// 判断客户订单详情是否能下单
					String state = orderDetail2.getState();
					if(!CustOrderConstants.ORDER_STATE_21.equals(state))
						continue;
					Integer i = orderDetail2.getAmount();
					if(i == null)
						continue;
					i -= isNull(orderDetail2.getDeliveryAmount(), 0);
					i -= isNull(orderDetail2.getLockAmount(), 0);
					if(i == null || i.compareTo(0) <= 0)
						continue;
					
					if(StringUtils.isEmpty(commCode)) {
						// 还没有确定供应商
						commCode = orderDetail2.getVendorCode();
					} else {
						// 已经确定了供应商
						if(!commCode.equals(orderDetail2.getVendorCode())) {
							left.add(id);
							continue;
						}
					}
					details.add(orderDetail2);
				} catch(Exception e) {
					logger.error("catch Exception in doCheckOperSeqId when checking " + id, e);
				}
			}
			setResult("list", details);
			setResult("leftOperSeqId", left);
			if(commCode != null)
				setResult("commCode", commCode);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doCheckOperSeqId", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 根据供应商编码查询需备货的客户订单详情
	 * @action.input orderDetail.vendorCode
	 * @action.input orderDetail.*
	 * @action.result list List<CustOrderDetail>
	 * @return
	 */
	public String doListStockupByVendor() {
		try {
			if(orderDetail == null || StringUtils.isEmpty(orderDetail.getVendorCode())){
				logger.debug("参数为空！");
				setErrorReason("参数为空！");
				return ERROR;
			}
			orderDetail.setField("stateList", "'20','21'");
			List<CustOrderDetail> list = mgr.listCustOrderDetail(orderDetail); 
			
			for(Iterator<CustOrderDetail> it = list.iterator(); it.hasNext();) {
				CustOrderDetail o = it.next();
				if((o.getAmount() - isNull(o.getLockAmount(),0) - isNull(o.getDeliveryAmount(),0)) <= (0))
					it.remove();
			}
			setResult("list", list);
			// DONE:CustOrderCgNormalAction.doListByVendor
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doListStockupByVendor", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	

	/**
	 * 如果i为null，则返回v；否则返回i
	 * @param i	带判断的Integer
	 * @param v 缺省返回值
	 * @return 如果i为null，则返回v；否则返回i
	 */
	private static Integer isNull(Integer i, int v) {
		if(i == null)
			return v;
		else
			return i;
	}
}
