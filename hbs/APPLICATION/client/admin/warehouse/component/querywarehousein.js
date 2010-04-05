var querygridFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("l1");
	cgh.appendField("l2");
	cgh.appendField("l3");
	cgh.appendField("l4");
	cgh.appendField("l5");
	cgh.appendField("l6");
	cgh.appendField("l7");
	cgh.appendField("l8");
	
	cgh.appendColumn({header: "入库单号", dataIndex: "l1", id: "crecpono"});
	cgh.appendColumn({header: "供应商"	, dataIndex: "l2"});
	cgh.appendColumn({header: "入库日期", dataIndex: "l2"});
	cgh.appendColumn({header: "入库仓库", dataIndex: "l4"});
	cgh.appendColumn({header: "操作人"  , dataIndex: "l5"});
	cgh.appendColumn({header: "结算方式", dataIndex: "l6"});
	cgh.appendColumn({header: "入库备注", dataIndex: "l7"});
	cgh.appendColumn({header: "状态"    , dataIndex: "l8"});
	if(urlPs.roleType == "warehouse") cgh.appendColumn({header: "操作"    , dataIndex: ""  , id: "operator", width: 180});

	return cgh;
};
