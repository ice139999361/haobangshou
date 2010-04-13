var querygridFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("activeState");
	cgh.appendField("companyBranch");
	cgh.appendField("companyBranchDesc");
	cgh.appendField("conFax");
	cgh.appendField("conTel");
	cgh.appendField("createDate");
	cgh.appendField("custCode");
	cgh.appendField("financeState");
	cgh.appendField("houseType");
	cgh.appendField("houseTypeDesc");	
	cgh.appendField("operId");
	cgh.appendField("operStaff");
	cgh.appendField("period");
	cgh.appendField("poNoType");
	cgh.appendField("receiveAddress");
	cgh.appendField("receiveName");
	cgh.appendField("receiveZip");
	cgh.appendField("sendDesc");
	cgh.appendField("sendPoNo");
	cgh.appendField("settlementType");
	cgh.appendField("settlementTypeDesc");
	cgh.appendField("shortName");
	cgh.appendField("state");
	cgh.appendField("stateDesc");
		
	cgh.appendColumn({header: "出库单号", dataIndex: "sendPoNo", id: "csendpono"});
	cgh.appendColumn({header: "客户"	  , dataIndex: "custCode"});
	cgh.appendColumn({header: "出库日期", dataIndex: "createDate", renderer: FormatUtil.dateRenderer});
	cgh.appendColumn({header: "出库仓库", dataIndex: "houseTypeDesc"});
	cgh.appendColumn({header: "操作人"  , dataIndex: "operStaff"});
	cgh.appendColumn({header: "结算方式", dataIndex: "settlementTypeDesc"});
	cgh.appendColumn({header: "出库备注", dataIndex: "sendDesc"});
	cgh.appendColumn({header: "状态"    , dataIndex: "stateDesc"});
	if(urlPs.roleType == "warehouse") cgh.appendColumn({header: "操作"    , dataIndex: ""  , id: "operator", width: 180});

	return cgh;
};
