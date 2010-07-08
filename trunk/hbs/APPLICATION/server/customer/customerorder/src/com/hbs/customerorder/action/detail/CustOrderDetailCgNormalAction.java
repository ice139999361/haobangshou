package com.hbs.customerorder.action.detail;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.hbs.common.utils.HumanReadableException;
import com.hbs.common.utils.ListDataUtil;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorPartNoInfo;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.vendorinfo.action.VendorPartNoInfoNormalAction;
import com.hbs.vendorinfo.manager.VendorPartNoInfoMgr;
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
			if(!this.findOrderDetail()) {
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
	 * @action.input vendorDate
	 * @action.input memo 取消原因
	 * @return
	 */
	public String doRefuseDelivery() {
		try {
			if(!findOrderDetail()) {
				return ERROR;
			}
			
			Date d = null;
			String s = this.getHttpServletRequest().getParameter("vendorDate");
			if(StringUtils.isNotEmpty(s)){	
				try {
					DateTimeFormatter fmt = DateTimeFormat.forPattern(ListDataUtil.DATEFORMAT);
					DateTime dt = fmt.parseDateTime(s);
					Calendar c = Calendar.getInstance();
					c.set(dt.getYear(), dt.getMonthOfYear() - 1, dt.getDayOfMonth());
					d = c.getTime();
				} catch (Exception e) {
					String str = "日期格式错误！";
					logger.debug(str + " " + s);
					setErrorReason(str);
					return ERROR;
				}
			}
			if(d != null)
				orderDetail.setPreDeliveryDate(d);
			else{
				// DONE: 使用上一个交期
				//orderDetail.setVerDeliveryDate(orderDetail.getPreDeliveryDate());
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
				if(wInfo2 == null)
					continue;
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
			// 设置为非null，避免json时抛出异常
			wInfo.setHouseSeqId(0);
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
	 * 该功能已暂时取消
	 * 下供应商订单时，用于将客户订单详情按照供应商编码分割
	 * @action.input	operSeqId	待分割的客户订单详情id，以,分割
	 * @action.result	commCode	供应商编码，可能不出现
	 * @action.result	list	客户订单详情列表 List<CustOrderDetail>
	 * @action.result	leftOperSeqId	剩余的客户订单详情id List<String>
	 * @return
	 */
	@Deprecated
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
	 * 并将commCode、cpartno、单价、税率换成供应商的物料信息，以便前台显示成正确的供应商订单信息
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
			VendorPartNoInfoMgr vpnMgr = (VendorPartNoInfoMgr)getBean(VendorPartNoInfoNormalAction.vendorPartNoInfoMgrName);
			for(Iterator<CustOrderDetail> it = list.iterator(); it.hasNext();) {
				CustOrderDetail o = it.next();
				int needAmount = o.getAmount() - isNull(o.getLockAmount(),0) - isNull(o.getDeliveryAmount(),0) - isNull(o.getOrderAmount(), 0);
				if(needAmount <= 0)
					it.remove();
				o.setAmount(needAmount);
				//将commCode、cpartno、单价、税率、金额换成供应商的物料信息
				VendorPartNoInfo vpn = new VendorPartNoInfo();
				vpn.setCommCode(o.getVendorCode());
				vpn.setPartNo(o.getPartNo());
				vpn.setState("0");
				vpn = vpnMgr.getVendorPartNoInfoByBizKey(vpn);
				if(vpn == null)
					throw new HumanReadableException("供应商物料不存在：供应商"+o.getVendorCode()+", 本公司物料编码"+o.getPartNo());
				o.setCommCode(vpn.getCommCode());
				o.setCpartNo(vpn.getCustPartNo());
				o.setPnDesc(vpn.getPnDesc());
				o.setCprice(vpn.getPrice());
				o.setCpriceTax(vpn.getPriceTax());
				o.setTaxRate(vpn.getPriceTax());
				o.setMoney(vpn.getPrice().multiply(new BigDecimal(needAmount)));
				
			}
			setResult("list", list);
			// DONE:CustOrderCgNormalAction.doListByVendor
			return SUCCESS;
		}catch(HumanReadableException hre){
			logger.error("catch HumanReadableException in doListStockupByVendor " + hre.getMessage());
			setErrorReason(hre.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("catch Exception in doListStockupByVendor", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 锁定库存
	 * @action.input orderDetail.* key
	 * @action.input self.lockAmount	本次操作本客户库存锁定数量
	 * @action.input common.lockAmount	本次操作通用库存锁定数量
	 * @return
	 */
	public String doLockAmount(){
		try {
			logger.debug("begin doLockAmount");
			if(!findOrderDetail())
				return ERROR;
			
			if(CustOrderConstants.ORDER_STATE_20.equals(orderDetail.getState())){
				String s = "状态错误";
				setErrorReason(s);
				logger.debug(s + orderDetail.getState());
				return ERROR;
			}
				
			try{
				orderDetail.setSelfLockAmount(Integer.parseInt(this.getHttpServletRequest().getParameter("self.lockAmount")));
			}catch(Exception e){};
			try{
				orderDetail.setCommLockAmount(Integer.parseInt(this.getHttpServletRequest().getParameter("common.lockAmount")));
			}catch(Exception e){};
			int i = mgr.lockOrderDetailAmount(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), null);
			if(i != 0){
				String s;
				switch(i){
				case -1:
					s = "无法找到客户订单明细";
					break;
				case -2:
					s = "锁定数量大于订单明细订货数量";
					break;
				default:
					s = "锁定错误！";
					break;
				}
				setErrorReason(s);
				logger.error(s + " ret = " + i);
				return ERROR;
			}
			logger.debug("end doLockAmount");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doLockAmount", e);
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
