var amountRenderer = function(value, metadata, record) {
	if(value) {
		
		record.set("money", Math.FloatMul(value, record.get("cprice")));
	}
	return value;
}

var ordergridFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("seqId");
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
			    							
	cgh.appendColumn({dataIndex: "seqId"	, isCheck: true});
	cgh.appendColumn({header: "货品名称"	, dataIndex: "pnName"});
	cgh.appendColumn({header: "客户型号<font color=red>*</font>"	, dataIndex: "cpartNo", xtype: "autocomplete", id: "cCpartNo"});
	cgh.appendColumn({header: "GLE型号<font color=red>*</font>"		, dataIndex: "partNo", xtype: "autocomplete", id : "cPartNo"});
	cgh.appendColumn({header: "描述"	, dataIndex: "pnDesc"});
	cgh.appendColumn({header: "单价"			, dataIndex: "cprice"});
	cgh.appendColumn({header: "税率"	    , dataIndex: "cpriceTax"});
	cgh.appendColumn({header: "是否含税交易<font color=red>*</font>", dataIndex: "isTax" , xtype: "dictcombo"  , paramsValue: "IS_TAX"});
	cgh.appendColumn({header: "数量<font color=red>*</font>"	      , dataIndex: "amount", xtype: "numberfield", renderer: "amountRenderer"});
	cgh.appendColumn({header: "金额", dataIndex: "money"});
	cgh.appendColumn({header: "交货日期<font color=red>*</font>", dataIndex: "orgDeliveryDate", xtype: "datefield", format: "Y-m-d"});
	cgh.appendColumn({header: "特殊备注<font color=red>*</font>"		, dataIndex: "specDesc", xtype: "textfield"});
	cgh.appendColumn({header: "备注<font color=red>*</font>"		, dataIndex: "commDesc", xtype: "textfield"});

	cgh.setSubmitFields("seqId,pnName,cpartNo,partNo,pnDesc,cprice,cpriceTax,isTax,amount,money,orgDeliveryDate,specDesc,commDesc");
	return cgh;
};