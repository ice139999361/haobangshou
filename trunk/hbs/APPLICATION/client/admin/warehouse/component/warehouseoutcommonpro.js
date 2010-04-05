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
			    							
	cgh.appendColumn({header: "订单号"            , dataIndex: "l1"});
	cgh.appendColumn({header: "公司 P/N"		      , dataIndex: "l2"});
	cgh.appendColumn({header: "客户 P/N"	      	, dataIndex: "l2"});
	cgh.appendColumn({header: "物料描述"	        , dataIndex: "l4"});
	cgh.appendColumn({header: "特殊备注（批次）"  , dataIndex: "l5"});
	cgh.appendColumn({header: "订单数量"          , dataIndex: "l6"});
	cgh.appendColumn({header: "已出库数量"        , dataIndex: "l7"});
	cgh.appendColumn({header: "本次出库数量"      , dataIndex: "l7", xtype: "textfield"});
	cgh.appendColumn({header: "出库备注"          , dataIndex: "l8", xtype: "textfield"});
	if(urlPs.pageType != "query") cgh.appendColumn({header: "操作"              , dataIndex: ""  , id: "operator"});


	cgh.setSubmitFields("l1,l2,l3,l4,l5,l6,l7,l8");
	return cgh;
};