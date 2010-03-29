var complexgridFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("poNo");
	cgh.appendField("commCode");
	cgh.appendField("shortName");
	cgh.appendField("stateDesc");
	cgh.appendField("state");
	cgh.appendField("activeState");
	cgh.appendField("poNoType");

	cgh.appendColumn({header: "供应商订单号", dataIndex: "poNo"     , id: "poNo"});
	cgh.appendColumn({header: "供应商编码"	, dataIndex: "commCode"  });
	cgh.appendColumn({header: "供应商简称"	, dataIndex: "shortName" });
	cgh.appendColumn({header: "状态"		  , dataIndex: "stateDesc" });
	
	switch(urlPs.roleType) {
		case "cgy":
			cgh.appendColumn({header: "已锁定本客户库存", dataIndex: ""});
			cgh.appendColumn({header: "已锁定通用库存"  , dataIndex: ""});
			cgh.appendColumn({header: "需备货数量"      , dataIndex: ""});
			cgh.appendColumn({header: "已发货数量"      , dataIndex: ""});
			break;
	}
	
	cgh.appendColumn({header: "操作"			, dataIndex: ""         , id: "operator", width: 250});
	
	return cgh;
};