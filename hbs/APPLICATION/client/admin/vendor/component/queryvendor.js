var queryformFun = function() {
	// 存放控件对象集合
	var cmpobj = {};
			    				
	// 供应商简称
	cmpobj["gysjc"] = {xtype: "textfield", fieldLabel: "供应商简称"  , name: "custInfo.shortName"};
	// 供应商编码
	cmpobj["gysbm"] = {xtype: "textfield", fieldLabel: "供应商编码"  , name: "custInfo.commCode"};
	// 业务员
	cmpobj["cgy"]   = {xtype: "dictcombo", fieldLabel: "采购员"      , hiddenName: "", paramsValue: "IMPORTANT_CODE"};
	// 客户录入时间
	cmpobj["gyslrsj"] = [
		 {xtype: "label"    , fieldLabel: "供应商录入时间"}
		,{xtype: "label"    , fieldLabel: "从"           , labelSeparator: ""}
		,{xtype: "datefield", hideLabel: true            , name: "custInfo.dynamicFields.likeBegainTime", format: "Y-m-d", width: 120}
		,{xtype: "label"    , fieldLabel: "到"           , labelSeparator: ""}
		,{xtype: "datefield", hideLabel: true            , name: "custInfo.dynamicFields.likeEndTime"   , format: "Y-m-d", width: 120}
	];
	
	
	return eval((urlPs.roleType + "Layout")).call(this, cmpobj);
};

// 采购部采购员
var cgyLayout = function(cmpobj) {
	var cph = new ColumnPanelHelper;
		
	var p1 = cph.createLayoutPanel("6:.3,.11,.05,.15,.05,.3");
	p1.push(cmpobj["gysjc"]);
	ExtConvertHelper.copyArrayToArray(cmpobj["gyslrsj"], p1);
	
	var p2 = cph.createLayoutPanel("1:.3");
	p2.push(cmpobj["gysbm"]);
	
	return cph;
};

// 采购部经理
var cgmLayout = function(cmpobj) {	
	var cph = new ColumnPanelHelper;
		
	var p1 = cph.createLayoutPanel(3);
	p1.push(cmpobj["gysjc"]);
	p1.push(cmpobj["gysbm"]);
	p1.push(cmpobj["cgy"]);


	var p2 = cph.createLayoutPanel("5:.10,.05,.15,.05,.3");
	ExtConvertHelper.copyArrayToArray(cmpobj["gyslrsj"], p2);

	return cph;
};

// 审批页面
var auditLayout = cgyLayout;


var complexgridFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("shortName");
	cgh.appendField("commCode");
	cgh.appendField("allName");
	cgh.appendField("address");
	cgh.appendField("creditDesc");
	cgh.appendField("importantDesc");
	cgh.appendField("stateDesc");
	
	cgh.appendField("baseSeqId");
	cgh.appendField("state");
	
	
	cgh.appendColumn({header: "供应商简称"				, dataIndex: "shortName"     , id: "shortName"});
	cgh.appendColumn({header: "供应商编码"				, dataIndex: "commCode"});
	cgh.appendColumn({header: "供应商中文名称"		, dataIndex: "allName"});
	cgh.appendColumn({header: "供应商地址"		, dataIndex: "address"});
	cgh.appendColumn({header: "供应商信用度"			, dataIndex: "creditDesc"});
	cgh.appendColumn({header: "供应商重要程度"	, dataIndex: "importantDesc"});
	cgh.appendColumn({header: "状态"						, dataIndex: "stateDesc"});
	cgh.appendColumn({header: "操作"						, dataIndex: ""              , id: "operator", width: 170});


	return cgh;
};