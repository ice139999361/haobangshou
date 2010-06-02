package com.hbs.invoice.manager;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.invoice.dao.FinanceSettlementDao;
import com.hbs.domain.invoice.pojo.FinanceSettlement;

public abstract class FinanceSettlementMgr {

	protected static final Logger logger = Logger.getLogger(FinanceSettlementMgr.class);
	
	
	protected abstract String getRealBean();
	protected abstract void processWarehouse(FinanceSettlement settlement) throws Exception;
	
	public void saveSettlement(FinanceSettlement settlement) throws Exception{
		logger.debug("save the settlement , the input param is " + settlement.toString());
		
		processSettlement(settlement);
		//find
		FinanceSettlementDao  sDao = (FinanceSettlementDao)BeanLocator.getInstance().getBean(getRealBean());
		FinanceSettlement exist = sDao.findFinanceSettlement(settlement);
		if(null == exist){//not exist
			sDao.insertFinanceSettlement(settlement);
			processWarehouse(settlement);
		}else{//exist
			sDao.updateFinanceSettlement(settlement);
			processWarehouse(settlement);
		}
	}
	
	public List<FinanceSettlement> listFinanceSettlement(FinanceSettlement settlement) throws Exception{
		logger.debug("list the settlement , the input param is " + settlement.toString());
		FinanceSettlementDao  sDao = (FinanceSettlementDao)BeanLocator.getInstance().getBean(getRealBean());
		return sDao.listFinanceSettlement(settlement);
	}
	
	public Integer listFinanceSettlementCount(FinanceSettlement settlement) throws Exception{
		logger.debug("list the settlement , the input param is " + settlement.toString());
		FinanceSettlementDao  sDao = (FinanceSettlementDao)BeanLocator.getInstance().getBean(getRealBean());
		return sDao.listFinanceSettlementCount(settlement);
	}
	
	private void processSettlement(FinanceSettlement settlement)throws Exception{
		BigDecimal totalMoney = settlement.getTotalMoney();
		BigDecimal needMoney = settlement.getNeedMoney();
		BigDecimal dealMoney = settlement.getDealMoney();
		BigDecimal curMoney = settlement.getCurMoney();
		if(null != curMoney){
			needMoney = needMoney.subtract(curMoney);
			if(null == dealMoney){
				dealMoney = new BigDecimal(0);
			}
			dealMoney = dealMoney.add(curMoney);
			settlement.setNeedMoney(needMoney);
			settlement.setDealMoney(dealMoney);
			if(totalMoney.compareTo(dealMoney) == 0){
				settlement.setFinanceState("3");
			}else if (dealMoney.compareTo(new BigDecimal(0)) > 0){
				settlement.setFinanceState("2");
			}else{
				settlement.setFinanceState("1");
			}
		}
	}
}
