var queryformFun = function() {	
	var cph = new ColumnPanelHelper;
		
	var p1 = cph.createLayoutPanel(3);
	p1.push({fieldLabel: "供应商编码", name: "", xtype: "textfield", labelStyle: "width:150"});
	p1.push({fieldLabel: "供应商简称", name: "", xtype: "textfield", labelStyle: "width:150"});
	p1.push({fieldLabel: "送货单号"  , name: "", xtype: "textfield", labelStyle: "width:150"});


	var p2 = cph.createLayoutPanel("6:.3333,.11,.05,.15,.05,.3");
	p2.push({fieldLabel: "供应商编码", name: "", xtype: "textfield", labelStyle: "width:150"});
	p2.push({fieldLabel: "录入时间"            , xtype: "label"});
	p2.push({fieldLabel: "从"                  , xtype: "label"    , labelSeparator: ""});
	p2.push({fieldLabel: "起始时间"  , name: "", xtype: "datefield", hideLabel: true, format: "Y-m-d"});
	p2.push({fieldLabel: "到"                  , xtype: "label"    , labelSeparator: ""});
	p2.push({fieldLabel: "结束时间"  , name: "", xtype: "datefield", hideLabel: true, format: "Y-m-d"});

	return cph;
};


var querygridFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("l1");
	cgh.appendField("l2");
	cgh.appendField("l3");
	cgh.appendField("l4");
	cgh.appendField("l5");
	cgh.appendField("l6");

			    							
	cgh.appendColumn({header: "编码"      , dataIndex: "l1"});
	cgh.appendColumn({header: "日期"      , dataIndex: "l2"});
	cgh.appendColumn({header: "送货单号"  , dataIndex: "l3"});
	cgh.appendColumn({header: "物料编号"  , dataIndex: "l4"});
	cgh.appendColumn({header: "已开票金额", dataIndex: "l5"});
	cgh.appendColumn({header: "发票号"    , dataIndex: "l6"});
	return cgh;
};