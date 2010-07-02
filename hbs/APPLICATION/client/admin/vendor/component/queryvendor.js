var queryformFun = function() {
	// 存放控件对象集合
	var cmpobj = {};
			    				
	// 供应商简称
	cmpobj["gysjc"] = {xtype: "textfield", fieldLabel: "供应商简称"  , name: "vendorInfo.shortName"};
	// 供应商编码
	cmpobj["gysbm"] = {xtype: "textfield", fieldLabel: "供应商编码"  , name: "vendorInfo.commCode"};
	// 业务员
	cmpobj["cgy"]   = {xtype: "dictcombo", fieldLabel: "采购员"      , hiddenName: "vendorInfo.staffId",  emptyText: "请选择" ,root: "data.list", displayField: "staffName" , valueField: "staffId", url: "/auth/user!listByRoleId.action?roleId=9"};
	cmpobj["sl"]  = {xtype: "dictcombo", fieldLabel: "税率"       , hiddenName: "vendorInfo.taxRate", emptyText: "请选择" ,paramsValue:"TAX_RATE" };
	cmpobj["st"]  = {xtype: "dictcombo", fieldLabel: "结算方式"       , hiddenName: "vendorInfo.settlementType", emptyText: "请选择" ,paramsValue:"SETTLEMENT_TYPE" , id:"isettlementType"};
	cmpobj["sd"]  = {xtype: "dictcombo", fieldLabel: "月结"       , hiddenName: "vendorInfo.settlementDay", emptyText: "请选择" ,paramsValue:"SETTLEMENT_DAY" , id:"isettlementDay"};
	// 客户录入时间
	cmpobj["gyslrsj"] = [
		 {xtype: "label"    , fieldLabel: "供应商录入时间"}
		,{xtype: "label"    , fieldLabel: "从"           , labelSeparator: ""}
		,{xtype: "datefield", hideLabel: true            , name: "vendorInfo.dynamicFields.likeBegainTime", format: "Y-m-d", width: 120}
		,{xtype: "label"    , fieldLabel: "到"           , labelSeparator: ""}
		,{xtype: "datefield", hideLabel: true            , name: "vendorInfo.dynamicFields.likeEndTime"   , format: "Y-m-d", width: 120}
	];
	
	
	return eval((urlPs.roleType + "Layout")).call(this, cmpobj);
};

// 采购部采购员
var cgyLayout = function(cmpobj) {
	var cph = new ColumnPanelHelper;
		
	var p1 = cph.createLayoutPanel(3);
	p1.push(cmpobj["gysjc"]);
	p1.push(cmpobj["gysbm"]);
	
	p1.push(cmpobj["st"]);
	p1.push(cmpobj["sl"]);	
	p1.push(cmpobj["sd"]);	
	var p2 = cph.createLayoutPanel("5:.10,.05,.15,.05,.3");
	ExtConvertHelper.copyArrayToArray(cmpobj["gyslrsj"], p2);

	return cph;
};

// 采购部经理
var cgmLayout = function(cmpobj) {	
	var cph = new ColumnPanelHelper;
		
	var p1 = cph.createLayoutPanel(3);
	p1.push(cmpobj["gysjc"]);
	p1.push(cmpobj["gysbm"]);
	
	p1.push(cmpobj["sl"]);
	
	p1.push(cmpobj["st"]);
	p1.push(cmpobj["sd"]);
	p1.push(cmpobj["cgy"]);
	var p2 = cph.createLayoutPanel("5:.10,.05,.15,.05,.3");
	ExtConvertHelper.copyArrayToArray(cmpobj["gyslrsj"], p2);

	return cph;
};

// 财务
var cwLayout = function(cmpobj) {	
	var cph = new ColumnPanelHelper;
		
	var p1 = cph.createLayoutPanel(3);
	p1.push(cmpobj["gysjc"]);
	p1.push(cmpobj["gysbm"]);
	
	p1.push(cmpobj["sl"]);
	p1.push(cmpobj["st"]);
	p1.push(cmpobj["sd"]);
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
	cgh.appendField("staffName");
	cgh.appendField("taxRate");
	cgh.appendField("settlementDayDesc");
	
	cgh.appendField("baseSeqId");
	cgh.appendField("state");
	
	cgh.appendColumn({header: "供应商编码"				, dataIndex: "commCode"});
	cgh.appendColumn({header: "供应商简称"				, dataIndex: "shortName"     , id: "shortName"});
	
	cgh.appendColumn({header: "采购员"		, dataIndex: "staffName"});
	cgh.appendColumn({header: "税率"		, dataIndex: "taxRate"});
	cgh.appendColumn({header: "结算方式"			, dataIndex: "settlementDayDesc"});
	
	cgh.appendColumn({header: "状态"						, dataIndex: "stateDesc"});
	cgh.appendColumn({header: "操作"						, dataIndex: ""              , id: "operator", width: 170});


	return cgh;
};