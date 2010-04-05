var querygridFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("activeState");
	cgh.appendField("applyDate");
	cgh.appendField("financeState");
	cgh.appendField("houseType");
	cgh.appendField("houseTypeDesc");
	cgh.appendField("operId");
	cgh.appendField("operStaff");
	cgh.appendField("operTime");
	cgh.appendField("poNoDate");
	cgh.appendField("poNoType");
	cgh.appendField("recPoNo");
	cgh.appendField("receiveDesc");
	cgh.appendField("settlementType");
	cgh.appendField("settlementTypeDesc");
	cgh.appendField("shortName");
	cgh.appendField("state");
	cgh.appendField("stateDesc");
	cgh.appendField("vendorCode");
	
	
	cgh.appendColumn({header: "入库单号", dataIndex: "recPoNo", id: "crecpono"});
	cgh.appendColumn({header: "供应商"	, dataIndex: "vendorCode"});
	cgh.appendColumn({header: "入库日期", dataIndex: "applyDate"});
	cgh.appendColumn({header: "入库仓库", dataIndex: "houseTypeDesc"});
	cgh.appendColumn({header: "操作人"  , dataIndex: "operStaff"});
	cgh.appendColumn({header: "结算方式", dataIndex: "settlementTypeDesc"});
	cgh.appendColumn({header: "入库备注", dataIndex: "receiveDesc"});
	cgh.appendColumn({header: "状态"    , dataIndex: "stateDesc"});
	if(urlPs.roleType == "warehouse") cgh.appendColumn({header: "操作"    , dataIndex: ""  , id: "operator", width: 180});

	return cgh;
};
