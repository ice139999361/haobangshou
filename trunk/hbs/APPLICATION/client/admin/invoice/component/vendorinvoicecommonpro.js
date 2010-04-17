var queryformFun = function() {	
	var cph = new ColumnPanelHelper;
		
	var p1 = cph.createLayoutPanel(2);
	p1.push({fieldLabel: "供应商编码", name: "invoice.ccode", xtype: "textfield", labelStyle: "width:150"});
	p1.push({fieldLabel: "供应商简称", name: "invoice.shortName", xtype: "textfield", labelStyle: "width:150"});
	//p1.push({fieldLabel: "送货单号"  , name: "", xtype: "textfield", labelStyle: "width:150"});


	var p2 = cph.createLayoutPanel("6:.3333,.11,.05,.15,.05,.3");
	p2.push({fieldLabel: "送货单号", name: "invoice.poNo", xtype: "textfield", labelStyle: "width:150"});
	p2.push({fieldLabel: "录入时间"            , xtype: "label"});
	p2.push({fieldLabel: "从"                  , xtype: "label"    , labelSeparator: ""});
	p2.push({fieldLabel: "起始时间"  , name: "invoice.dynamicFields.likeBegainTime", xtype: "datefield", hideLabel: true, format: "Y-m-d"});
	p2.push({fieldLabel: "到"                  , xtype: "label"    , labelSeparator: ""});
	p2.push({fieldLabel: "结束时间"  , name: "invoice.dynamicFields.likeEndTime", xtype: "datefield", hideLabel: true, format: "Y-m-d"});

	return cph;
};


var addQueryformFun = function() {	
	var cph = new ColumnPanelHelper;
		
	var p1 = cph.createLayoutPanel(2);
	p1.push({fieldLabel: "供应商编码", name: "warehouseRecDetail.vendorCode", xtype: "textfield", labelStyle: "width:150"});
	p1.push({fieldLabel: "供应商简称", name: "warehouseRecDetail.shortName", xtype: "textfield", labelStyle: "width:150"});
	//p1.push({fieldLabel: "送货单号"  , name: "", xtype: "textfield", labelStyle: "width:150"});


	var p2 = cph.createLayoutPanel("6:.3333,.11,.05,.15,.05,.3");
	p2.push({fieldLabel: "送货单号", name: "warehouseRecDetail.recPoNo", xtype: "textfield", labelStyle: "width:150"});
	p2.push({fieldLabel: "录入时间"            , xtype: "label"});
	p2.push({fieldLabel: "从"                  , xtype: "label"    , labelSeparator: ""});
	p2.push({fieldLabel: "起始时间"  , name: "warehouseRecDetail.dynamicFields.likeBegainTime", xtype: "datefield", hideLabel: true, format: "Y-m-d"});
	p2.push({fieldLabel: "到"                  , xtype: "label"    , labelSeparator: ""});
	p2.push({fieldLabel: "结束时间"  , name: "warehouseRecDetail.dynamicFields.likeEndTime", xtype: "datefield", hideLabel: true, format: "Y-m-d"});

	return cph;
};

var querygridFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("invoiceSeqId");
	cgh.appendField("staffId");
	cgh.appendField("staffName");
	cgh.appendField("createTime");
	cgh.appendField("poNo");
	cgh.appendField("poNoDate");
	cgh.appendField("ccode");
	cgh.appendField("shortName");
	cgh.appendField("partNo");
	cgh.appendField("cpartNo");
	cgh.appendField("pnDesc");
	cgh.appendField("amount");
	cgh.appendField("allMoney");
	cgh.appendField("currMoney");
	cgh.appendField("leftMoney");
	cgh.appendField("invoiceDesc");

			    							
	cgh.appendColumn({header: "供应商编码"      , dataIndex: "ccode"     , id: "commCode"});
	cgh.appendColumn({header: "日期"      , dataIndex: "createTime"});
	cgh.appendColumn({header: "送货单号"  , dataIndex: "poNo"});
	cgh.appendColumn({header: "物料编号"  , dataIndex: "partNo"});
	cgh.appendColumn({header: "已开票金额", dataIndex: "currMoney"});
	cgh.appendColumn({header: "发票号"    , dataIndex: "invoiceDesc"});
	return cgh;
};