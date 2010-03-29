var amountRenderer = function(value, metadata, record) {
	if(value) {
		
		record.set("money", Math.FloatMul(value, record.get("cprice")));
	}
	return value;
}

var ordergridFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("operSeqId");
	cgh.appendField("pnName");
	cgh.appendField("cpartNo");
	cgh.appendField("partNo");
	cgh.appendField("pnDesc");
	cgh.appendField("cprice");
	cgh.appendField("cpriceTax");
	cgh.appendField("isTax");
	cgh.appendField("amount");
	cgh.appendField("money");
	cgh.appendField("orgDeliveryDate");
	cgh.appendField("specDesc");
	cgh.appendField("commDesc");
	
	cgh.appendColumn({dataIndex: "operSeqId"	, isCheck: true});
	cgh.appendColumn({header: "客户订单号<br />特定备货的客户编码"	, xtype: "textfield", dataIndex: "pnName", width: 120});
	cgh.appendColumn({header: "GLE型号"		  , dataIndex: "partNo", xtype: "autocomplete", id : "cPartNo", queryParam: "aaaa"});
	cgh.appendColumn({header: "供应商型号"	, dataIndex: "cpartNo"});
	cgh.appendColumn({header: "货品名称"	  , dataIndex: "pnDesc"});
	cgh.appendColumn({header: "描述"	      , dataIndex: "pnDesc"});
	cgh.appendColumn({header: "单价"			  , dataIndex: "cprice"});
	cgh.appendColumn({header: "税率"	      , dataIndex: "cpriceTax"});
	cgh.appendColumn({header: "是否含税交易", dataIndex: "isTax" , xtype: "dictcombo"  , paramsValue: "IS_TAX"});
	cgh.appendColumn({header: "数量"	      , dataIndex: "amount", xtype: "numberfield", renderer: "amountRenderer"});
	cgh.appendColumn({header: "金额"        , dataIndex: "money"});
	cgh.appendColumn({header: "交货日期"    , dataIndex: "orgDeliveryDate", xtype: "datefield", format: "Y-m-d"});
	cgh.appendColumn({header: "备注"	    	, dataIndex: "commDesc", xtype: "textfield"});
	cgh.appendColumn({header: "特殊备注"		, dataIndex: "specDesc", xtype: "textfield"});
	

	cgh.setSubmitFields("operSeqId,pnName,cpartNo,partNo,pnDesc,cprice,cpriceTax,isTax,amount,money,orgDeliveryDate,specDesc,commDesc");
	return cgh;
};

var orderquerygridFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("operSeqId");
	cgh.appendField("pnName");
	cgh.appendField("cpartNo");
	cgh.appendField("partNo");
	cgh.appendField("pnDesc");
	cgh.appendField("cprice");
	cgh.appendField("cpriceTax");
	cgh.appendField("isTax");
	cgh.appendField("amount");
	cgh.appendField("money");
	cgh.appendField("orgDeliveryDate");
	cgh.appendField("specDesc");
	cgh.appendField("commDesc");
			    							
	cgh.appendColumn({dataIndex: "operSeqId"	, isCheck: true});
	cgh.appendColumn({header: "货品名称"	    , dataIndex: "pnName"});
	cgh.appendColumn({header: "客户型号"	    , dataIndex: "cpartNo"});
	cgh.appendColumn({header: "GLE型号"		    , dataIndex: "partNo"});
	cgh.appendColumn({header: "描述"        	, dataIndex: "pnDesc"});
	cgh.appendColumn({header: "单价"		    	, dataIndex: "cprice"});
	cgh.appendColumn({header: "税率"	        , dataIndex: "cpriceTax"});
	cgh.appendColumn({header: "是否含税交易"  , dataIndex: "isTax"});
	cgh.appendColumn({header: "数量"	        , dataIndex: "amount"});
	cgh.appendColumn({header: "金额"          , dataIndex: "money"});
	cgh.appendColumn({header: "交货日期"      , dataIndex: "orgDeliveryDate"});
	cgh.appendColumn({header: "特殊备注"	  	, dataIndex: "specDesc"});
	cgh.appendColumn({header: "备注"		      , dataIndex: "commDesc"});
	
	return cgh;
};