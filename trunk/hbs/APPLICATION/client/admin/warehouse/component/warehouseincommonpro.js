var warehousegridFun = function() {
	var cgh = new ComplexGridHelper;

	cgh.appendField("activeState");
	cgh.appendField("amount");
	cgh.appendField("applyDate");
	cgh.appendField("cpartNo");
	cgh.appendField("currMoney");
	cgh.appendField("financePeriod");
	cgh.appendField("financeState");
	cgh.appendField("isTax");
	cgh.appendField("operTime");
	cgh.appendField("partNo");
	cgh.appendField("period");
	cgh.appendField("pnDesc");
	cgh.appendField("poNoDate");
	cgh.appendField("poNoType");
	cgh.appendField("price");
	cgh.appendField("priceTax");
	cgh.appendField("recDetailSeqId");
	cgh.appendField("recPoNo");
	cgh.appendField("rltPoNo");
	cgh.appendField("settlementType");
	cgh.appendField("settlementTypeDesc");
	cgh.appendField("shortName");
	cgh.appendField("specDesc");
	cgh.appendField("staffId");
	cgh.appendField("staffName");
	cgh.appendField("taxRate");
	cgh.appendField("vendorCode");
	cgh.appendField("state");
	cgh.appendField("stateDesc");
	cgh.appendField("orderSeqId");


	cgh.appendColumn({header: "采购单号"         , dataIndex: "rltPoNo"});
	cgh.appendColumn({header: "公司 P/N"		     , dataIndex: "partNo"});
	cgh.appendColumn({header: "供应商 P/N"       , dataIndex: "cpartNo"});
	cgh.appendColumn({header: "物料描述"	       , dataIndex: "pnDesc"});
	cgh.appendColumn({header: "特殊备注（批次）" , dataIndex: "specDesc"});
	//cgh.appendColumn({header: "采购数量"         , dataIndex: "amount"});
	//cgh.appendColumn({header: "已入库数量"       , dataIndex: "l6"});
	cgh.appendColumn({header: "本次入库数量"     , dataIndex: "amount", xtype: "textfield"});
	cgh.appendColumn({header: "明细状态"     , dataIndex: "stateDesc"});
	if(urlPs.pageType != "query") cgh.appendColumn({header: "操作"             , dataIndex: ""  , id: "operator"});
	//cgh.appendColumn({header: "orderSeqId"     , dataIndex: "orderSeqId"});

	cgh.setSubmitFields("recPoNo,partNo,cpartNo,pnDesc,specDesc,amount,taxRate,isTax,price,priceTax,rltPoNo,settlementType,recDetailSeqId,poNoType,activeState,state,orderSeqId");
	return cgh;
};