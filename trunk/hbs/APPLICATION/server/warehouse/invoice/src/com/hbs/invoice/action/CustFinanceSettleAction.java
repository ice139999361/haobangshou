package com.hbs.invoice.action;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;

import com.hbs.domain.invoice.pojo.FinanceSettlement;
import com.hbs.invoice.manager.CustFinanceSettlementMgr;


@SuppressWarnings("serial")
public class CustFinanceSettleAction extends BaseAction {

	
	protected static final Logger logger = Logger.getLogger(CustFinanceSettleAction.class);
	
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
			logger.debug("begin list the customer settlement list the function is doList()");
			if(fSettlement == null)
				fSettlement = new FinanceSettlement();
			
			setPagination(fSettlement);
			setResult("list", listFinanceSettlement(fSettlement));
			setTotalCount(listFinanceSettlementCount(fSettlement));
			
			
			logger.debug("end list the customer settlement list the function is doList()");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in customer settlement list doList", e);
			setErrorReason("ϵͳ�ڲ�����");
			return ERROR;
		}
	}
	
	
	public String doSaveSettlement(){
		logger.debug("begin to save the customer settlement ,the input parameter is :" + fSettlement);
		try{
			if(fSettlement == null){
				logger.debug("the input customer settlement info is null , can not to save!");
				setErrorReason("��û������ͻ�������Ϣ�����ܱ���!");
				return ERROR;
			}else{
				String financeState = fSettlement.getFinanceState();
				if(financeState == null || financeState.equals("3")){
					logger.debug("the input customer settlement info is settled , can not to save!");
					setErrorReason("������ͻ�������Ϣ�Ѿ�������ϣ������ٴν���!");
					return ERROR;
				}
				//the settlement number detail
				BigDecimal totalMoney = fSettlement.getTotalMoney();
				BigDecimal needMoney = fSettlement.getNeedMoney();
				BigDecimal dealMoney = fSettlement.getDealMoney();
				BigDecimal curMoney = fSettlement.getCurMoney();
				if(curMoney == null || curMoney.compareTo(new BigDecimal(0))== 0){
					logger.debug("the input customer settlement info curMoney is null or 0 , can not to save!");
					setErrorReason("��û������ͻ��ı��ν��������ִ�н������!");
					return ERROR;
				}
				
				if(curMoney.compareTo(needMoney)>0){
					logger.debug("the input customer settlement info curMoney is too much , can not to save!");
					setErrorReason("������ͻ��ı��ν�������˴����������ִ�н������!");
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
					CustFinanceSettlementMgr mgr = (CustFinanceSettlementMgr)BeanLocator.getInstance().getBean("custFinanceSettlementMgr");
					mgr.saveSettlement(fSettlement);					
				}
			}
			setErrorReason("����ͻ�������Ϣ�ɹ�!");
			return SUCCESS;
		}catch (Exception e){
			
			return ERROR;
		}
		
	}
	
	
	
	
	private List<FinanceSettlement> listFinanceSettlement(FinanceSettlement fSettlement) throws Exception{
		logger.debug("get the customer finance settlement info ,the input parameteris :" + fSettlement);
		CustFinanceSettlementMgr mgr = (CustFinanceSettlementMgr)BeanLocator.getInstance().getBean("custFinanceSettlementMgr");
		List<FinanceSettlement> listSettle = mgr.listFinanceSettlement(fSettlement);
		return listSettle;
	}
	
	private Integer listFinanceSettlementCount(FinanceSettlement fSettlement) throws Exception{
		logger.debug("get the customer finance settlement count ,the input parameteris :" + fSettlement);
		CustFinanceSettlementMgr mgr = (CustFinanceSettlementMgr)BeanLocator.getInstance().getBean("custFinanceSettlementMgr");
		Integer iCount = mgr.listFinanceSettlementCount(fSettlement);
		return iCount;
	}
}
