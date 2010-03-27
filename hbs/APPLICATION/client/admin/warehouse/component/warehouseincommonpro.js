var warehousegridFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("l1");
	cgh.appendField("l2");
	cgh.appendField("l3");
	cgh.appendField("l4");
	cgh.appendField("l5");
	cgh.appendField("l6");
	cgh.appendField("l7");
	cgh.appendField("l8");
			    							
	cgh.appendColumn({dataIndex: "seqId"	, isCheck: true});
	cgh.appendColumn({header: "采购单号"	, dataIndex: "l1", xtype: "textfield"});
	cgh.appendColumn({header: "GLE编码"		, dataIndex: "l2", xtype: "autocomplete", id: "cglecode"});
	cgh.appendColumn({header: "供应商编码", dataIndex: "l3", xtype: "autocomplete", id: "ccommcode"});
	cgh.appendColumn({header: "描述"	    , dataIndex: "l4"});
	cgh.appendColumn({header: "订单数量"  , dataIndex: "l5"});
	cgh.appendColumn({header: "入库数量"  , dataIndex: "l6", xtype: "textfield"});
	cgh.appendColumn({header: "剩余数量"  , dataIndex: "l7", xtype: "textfield"});
	cgh.appendColumn({header: "备注"      , dataIndex: "l8", xtype: "textfield"});


	cgh.setSubmitFields("l1,l2,l3,l4,l5,l6,l7,l8");
	return cgh;
};