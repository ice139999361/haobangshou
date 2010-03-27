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
			    							
			    							//       描述     备注 
	cgh.appendColumn({dataIndex: "seqId"	, isCheck: true});
	cgh.appendColumn({header: "客户订单号", dataIndex: "l1"});
	cgh.appendColumn({header: "客户PN"		, dataIndex: "l2"});
	cgh.appendColumn({header: "GLE PN"    , dataIndex: "l3"});
	cgh.appendColumn({header: "描述"	    , dataIndex: "l4"});
	cgh.appendColumn({header: "特殊备注"  , dataIndex: "l5"});
	cgh.appendColumn({header: "订单数量"  , dataIndex: "l6"});
	cgh.appendColumn({header: "已发货数量", dataIndex: "l7"});
	cgh.appendColumn({header: "出货数量"  , dataIndex: "l8", xtype: "textfield"});
	cgh.appendColumn({header: "备注"      , dataIndex: "l8", xtype: "textfield"});


	cgh.setSubmitFields("l1,l2,l3,l4,l5,l6,l7,l8");
	return cgh;
};