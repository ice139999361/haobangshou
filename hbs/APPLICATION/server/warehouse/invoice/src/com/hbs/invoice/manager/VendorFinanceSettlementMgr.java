package com.hbs.invoice.manager;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.invoice.pojo.FinanceSettlement;
import com.hbs.domain.warehouse.dao.WarehouseRecDetailDao;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;


public class VendorFinanceSettlementMgr extends FinanceSettlementMgr {

	private static final String WAREHOUSE_REC_DETAIL_DAO = "warehouseRecDetailDao";
	@Override
	protected String getRealBean() {
		// TODO Auto-generated method stub
		return "vendorFinanceSettlementDao";
	}

	@Override
	protected void processWarehouse(FinanceSettlement settlement)
			throws Exception {
		String financeState = settlement.getFinanceState();
		String settlementType = settlement.getSettlementType();
		if(null != financeState &&(financeState.equals("2") || financeState.equals("3"))){
			WarehouseRecDetail recInfo = new WarehouseRecDetail();
			recInfo.setFinanceState(financeState);
			recInfo.setVendorCode(settlement.getCommCode());
			if(settlementType.equals("1")){
				recInfo.setFinancePeriod(settlement.getSummery());
			}else{
				recInfo.setRecPoNo(settlement.getSummery());
			}
			WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WAREHOUSE_REC_DETAIL_DAO);
			detailDao.updateWarehouseRecDetailByFinanceStateSettlement(recInfo);
		}

	}

}
