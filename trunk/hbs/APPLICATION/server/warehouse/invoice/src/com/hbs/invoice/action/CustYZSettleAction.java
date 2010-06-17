package com.hbs.invoice.action;


import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;

import com.hbs.domain.auth.pojo.Staff;
import com.hbs.domain.invoice.pojo.FinanceSettlement;
import com.hbs.invoice.manager.CustFinanceSettlementMgr;


@SuppressWarnings("serial")
public class CustYZSettleAction extends BaseAction {

	
	protected static final Logger logger = Logger.getLogger(CustYZSettleAction.class);
	
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
			Staff user = getLoginStaff();
			fSettlement.setAssId(user.getStaffId().toString());
			setPagination(fSettlement);
			setResult("list", listFinanceSettlement(fSettlement));
			setTotalCount(listFinanceSettlementCount(fSettlement));
			
			
			logger.debug("end list the customer settlement list the function is doList()");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in customer settlement list doList", e);
			setErrorReason("系统内部错误");
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
