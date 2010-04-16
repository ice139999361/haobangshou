/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousesend.action;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;
import com.hbs.domain.warehouse.pojo.WarehouseSendInfo;
import com.hbs.warehousesend.manager.WareHouseSendMgr;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class WareHouseSendPrintAction extends BaseAction {
	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(WareHouseSendPrintAction.class);
	
	public static final String WAREHOUSE_SEND_MGR = "wareHouseSendMgr";
	
	WarehouseSendInfo warehouseSend;

	
	protected WareHouseSendMgr getMgr() {
		return (WareHouseSendMgr)getBean(WAREHOUSE_SEND_MGR);
	}

	public WarehouseSendInfo getWarehouseSend() {
		return warehouseSend;
	}

	public void setWarehouseSend(WarehouseSendInfo warehouseSend) {
		this.warehouseSend = warehouseSend;
	}
	
	/**
	 * 获取出库单信息
	 * @action.input warehouseSend.sendPoNo + warehouseSend.custCode
	 * @action.result warehouseSend.*
	 * @return
	 */
	public String doGetInfoPrint() {
		logger.debug("begin doGetInfoPrint");
		String ret =ERROR;
		try{
			
			WarehouseSendInfo sendInfo = getWarehouseSendByKey(true);
			if(null != sendInfo){
				setResult("warehouseSend", sendInfo);
				CustomerInfo custInfo = getCustomerInfo(sendInfo.getCustCode());
				if(null != custInfo){
					setResult("isShowPrice" , custInfo.getIsShowPrice());
					setResult("isShowPriceDesc" , custInfo.getIsShowPriceDesc());
					setResult("sales" ,custInfo.getStaffName());
					setResult("custName" ,custInfo.getAllName());
					setResult("currency" ,custInfo.getCurrencyDesc());
					setResult("department","市场部");
					ret = SUCCESS;
				}
			}
		}catch(Exception e) {
			logger.error("catch Exception in doGetInfoPrint", e);
			setErrorReason("内部错误");			
		}
		logger.debug("end doGetInfoPrint()");
		return ret;
	}
	
	/**
	 * 根据关键字段获取信息
	 * @throws Exception
	 */
	private WarehouseSendInfo getWarehouseSendByKey(boolean isDetail) throws Exception {
		boolean isTrue = WarehouseSendUtil.checkKeyFields(warehouseSend);
		logger.debug("isTrue =" + isTrue);
		if(!isTrue) {
			logger.debug(warehouseSend.toString());
			logger.debug("参数为空！");
			setErrorReason("参数为空！");
			return null;
		}else{
			warehouseSend.setState("02");
			warehouseSend.setActiveState("ACTIVE");
			warehouseSend.setSort("SEND_DETAIL_SEQID");
		}
		WareHouseSendMgr mgr = getMgr();
		
		WarehouseSendInfo warehouseSendf = mgr.getWarehouseSendInfo(warehouseSend, isDetail);
		if(null != warehouseSendf){
			List<WarehouseSendDetail> detailList = new ArrayList<WarehouseSendDetail>();
			List<WarehouseSendDetail> existList = warehouseSendf.getDetailList();
			if(null != existList && existList.size()>0){
				int seqid =1;
				for(WarehouseSendDetail detail : existList){
					String state = detail.getState();
					if(null != state && state.equals("02")){
						detail.setSendSeqId(seqid);
						detailList.add(detail);
						seqid++;
					}
				}
				if(detailList.size() >0){
					warehouseSendf.setDetailList(detailList);
				}else{
					warehouseSendf.setDetailList(null);
				}
			}
		}
		return warehouseSendf;
	}
	
	private CustomerInfo getCustomerInfo(String custCode) throws Exception{
		CustomerInfoMgr mgr = (CustomerInfoMgr)getBean("customerInfoMgr");
		CustomerInfo fInfo = new CustomerInfo();
		fInfo.setCommCode(custCode);
		fInfo.setState("0");
		return mgr.getCustomerInfo(fInfo, false);
	}
}
