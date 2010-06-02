package com.hbs.invoice.manager;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.invoice.pojo.FinanceSettlement;

import com.hbs.domain.warehouse.dao.WarehouseSendDetailDao;

import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;


public class CustFinanceSettlementMgr extends FinanceSettlementMgr {

	private static final String WAREHOUSE_SEND_DETAIL_DAO = "warehouseSendDetailDao";
	@Override
	protected String getRealBean() {
		// TODO Auto-generated method stub
		return "custFinanceSettlementDao";
	}

	@Override
	protected void processWarehouse(FinanceSettlement settlement)
			throws Exception {
		String financeState = settlement.getFinanceState();
		String settlementType = settlement.getSettlementType();
		if(null != financeState &&(financeState.equals("2") || financeState.equals("3"))){
			WarehouseSendDetail recInfo = new WarehouseSendDetail();
			recInfo.setFinanceState(financeState);
			recInfo.setVendorCode(settlement.getCommCode());
			if(settlementType.equals("1")){
				recInfo.setFinancePeriod(settlement.getSummery());
			}else{
				recInfo.setSendPoNo(settlement.getSummery());
			}
			WarehouseSendDetailDao detailDao = (WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WAREHOUSE_SEND_DETAIL_DAO);
			detailDao.updateWarehouseSendDetailByFinanceStateSettlement(recInfo);
		}


	}

}
