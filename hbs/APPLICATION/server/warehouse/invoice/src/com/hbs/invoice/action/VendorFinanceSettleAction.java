package com.hbs.invoice.action;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;

import com.hbs.domain.invoice.pojo.FinanceSettlement;
import com.hbs.invoice.manager.VendorFinanceSettlementMgr;

@SuppressWarnings("serial")
public class VendorFinanceSettleAction extends BaseAction {

	
	protected static final Logger logger = Logger.getLogger(VendorFinanceSettleAction.class);
	
	private FinanceSettlement fSettlement;

	public FinanceSettlement getFSettlement() {
		return fSettlement;
	}

	public void setFSettlement(FinanceSettlement settlement) {
		fSettlement = settlement;
	}
	
	/**
	 * get the vendor settlement list 
	 * for cw
	 * @return
	 */
	public String doList(){
		try {
			logger.debug("begin list the vendor settlement list the function is doList()");
			if(fSettlement == null)
				fSettlement = new FinanceSettlement();
			
			setPagination(fSettlement);
			setResult("list", listFinanceSettlement(fSettlement));
			setTotalCount(listFinanceSettlementCount(fSettlement));
			
			
			logger.debug("end list the vendor settlement list the function is doList()");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in vendor settlement list doList", e);
			setErrorReason("系统内部错误");
			return ERROR;
		}
	}
	
	
	public String doSaveSettlement(){
		logger.debug("begin to save the vendor settlement ,the input parameter is :" + fSettlement);
		try{
			if(fSettlement == null){
				logger.debug("the input vendor settlement info is null , can not to save!");
				setErrorReason("您没有输入供应商结算信息，不能保存!");
				return ERROR;
			}else{
				String financeState = fSettlement.getFinanceState();
				if(financeState == null || financeState.equals("3")){
					logger.debug("the input vendor settlement info is settled , can not to save!");
					setErrorReason("您输入供应商结算信息已经结算完毕，不能再次结算!");
					return ERROR;
				}
				//the settlement number detail
				BigDecimal totalMoney = fSettlement.getTotalMoney();
				BigDecimal needMoney = fSettlement.getNeedMoney();
				BigDecimal dealMoney = fSettlement.getDealMoney();
				BigDecimal curMoney = fSettlement.getCurMoney();
				if(curMoney == null || curMoney.compareTo(new BigDecimal(0))== 0){
					logger.debug("the input vendor settlement info curMoney is null or 0 , can not to save!");
					setErrorReason("您没有输入供应商的本次结算金额，不能执行结算操作!");
					return ERROR;
				}
				
				if(curMoney.compareTo(needMoney)>0){
					logger.debug("the input vendor settlement info curMoney is too much , can not to save!");
					setErrorReason("您输入供应商的本次结算金额超过了待结算金额，不能执行结算操作!");
					return ERROR;
				}else{
					needMoney = needMoney.subtract(curMoney);
					fSettlement.setNeedMoney(needMoney);
					dealMoney = dealMoney.add(curMoney);
					fSettlement.setDealMoney(curMoney);
					if(dealMoney.compareTo(totalMoney) == 0){
						fSettlement.setFinanceState("3");
					}else{
						fSettlement.setFinanceState("2");
					}
					VendorFinanceSettlementMgr mgr = (VendorFinanceSettlementMgr)BeanLocator.getInstance().getBean("vendorFinanceSettlementMgr");
					mgr.saveSettlement(fSettlement);					
				}
			}
			setErrorReason("保存供应商结算信息成功!");
			return SUCCESS;
		}catch (Exception e){
			
			return ERROR;
		}
		
	}
	
	
	
	
	private List<FinanceSettlement> listFinanceSettlement(FinanceSettlement fSettlement) throws Exception{
		logger.debug("get the vendor finance settlement info ,the input parameteris :" + fSettlement);
		VendorFinanceSettlementMgr mgr = (VendorFinanceSettlementMgr)BeanLocator.getInstance().getBean("vendorFinanceSettlementMgr");
		List<FinanceSettlement> listSettle = mgr.listFinanceSettlement(fSettlement);
		return listSettle;
	}
	
	private Integer listFinanceSettlementCount(FinanceSettlement fSettlement) throws Exception{
		logger.debug("get the vendor finance settlement count ,the input parameteris :" + fSettlement);
		VendorFinanceSettlementMgr mgr = (VendorFinanceSettlementMgr)BeanLocator.getInstance().getBean("vendorFinanceSettlementMgr");
		Integer iCount = mgr.listFinanceSettlementCount(fSettlement);
		return iCount;
	}
}
