var queryformFun = function() {
	// 存放控件对象集合
	var cmpobj = {};
			    				
	// 客户简称
	cmpobj["khjc"] = {xtype: "textfield", fieldLabel: "客户简称"     , name: "custInfo.shortName"};
	// 客户录入时间
	cmpobj["khlrsj"] = [
		 {xtype: "label"    , fieldLabel: "客户录入时间"}
		,{xtype: "label"    , fieldLabel: "从"           , labelSeparator: ""}
		,{xtype: "datefield", hideLabel: true            , name: "custInfo.dynamicFields.likeBegainTime", format: "Y-m-d", width: 120}
		,{xtype: "label"    , fieldLabel: "到"           , labelSeparator: ""}
		,{xtype: "datefield", hideLabel: true            , name: "custInfo.dynamicFields.likeEndTime"   , format: "Y-m-d", width: 120}
	];
	// 精确查找
	//cmpobj["jqcz"] = {xtype: "checkbox" , labelSeparator: ""         , name: "", boxLabel: "精确查找"};
	cmpobj["khbm"] = {xtype: "textfield", fieldLabel: "客户编码"     , name: "custInfo.commCode"};
	cmpobj["ywy"]  = {xtype: "dictcombo", fieldLabel: "业务员"       , hiddenName: "custInfo.staffId", emptyText: "请选择" ,root: "data.list", displayField: "staffName" , valueField: "staffId", url: "/auth/user!listByRoleId.action?roleId=7"};
	//cmpobj["ywzl"] = {xtype: "dictcombo", fieldLabel: "业务助理"     , hiddenName: "", paramsValue: "IMPORTANT_CODE"};
	cmpobj["sl"]  = {xtype: "dictcombo", fieldLabel: "税率"       , hiddenName: "custInfo.taxRate", emptyText: "请选择" ,paramsValue:"TAX_RATE" };
	cmpobj["st"]  = {xtype: "dictcombo", fieldLabel: "结算方式"       , hiddenName: "custInfo.settlementType", emptyText: "请选择" ,paramsValue:"SETTLEMENT_TYPE" , id:"isettlementType"};
	cmpobj["sd"]  = {xtype: "dictcombo", fieldLabel: "月结"       , hiddenName: "custInfo.settlementDay", emptyText: "请选择" ,paramsValue:"SETTLEMENT_DAY" , id:"isettlementDay"};
	return eval((urlPs.roleType + "Layout")).call(this, cmpobj);
};

// 市场业务员布局
var sccustomersLayout = function(cmpobj) {
	var cph = new ColumnPanelHelper;
		
	var p1 = cph.createLayoutPanel("6:.3,.11,.05,.15,.05,.3");
	p1.push(cmpobj["khjc"]);
	ExtConvertHelper.copyArrayToArray(cmpobj["khlrsj"], p1);
	
	var p2 = cph.createLayoutPanel("2:.3,.3");
	//p2.push(cmpobj["jqcz"]);
	p2.push(cmpobj["khbm"]);
	p2.push(cmpobj["sl"]);
	p2.push(cmpobj["st"]);
	p2.push(cmpobj["sd"]);
	return cph;
};

// 市场经理
var scmanagerLayout = function(cmpobj) {	
	var cph = new ColumnPanelHelper;
		
	var p1 = cph.createLayoutPanel(3);
	p1.push(cmpobj["khjc"]);
	//p1.push(cmpobj["jqcz"]);
	
	p1.push(cmpobj["khbm"]);
	//p1.push(cmpobj["ywzl"]);
	
	p1.push(cmpobj["ywy"]);
	p1.push(cmpobj["sl"]);
	p1.push(cmpobj["st"]);
	p1.push(cmpobj["sd"]);

	var p2 = cph.createLayoutPanel("5:.10,.05,.15,.05,.3");
	ExtConvertHelper.copyArrayToArray(cmpobj["khlrsj"], p2);
	
	return cph;
};

// 财务
var cwLayout = function(cmpobj) {	
	var cph = new ColumnPanelHelper;
		
	var p1 = cph.createLayoutPanel(3);
	p1.push(cmpobj["khjc"]);
	//p1.push(cmpobj["jqcz"]);
	
	p1.push(cmpobj["khbm"]);
	//p1.push(cmpobj["ywzl"]);
	
	p1.push(cmpobj["ywy"]);
	p1.push(cmpobj["sl"]);
	p1.push(cmpobj["st"]);
	p1.push(cmpobj["sd"]);
	var p2 = cph.createLayoutPanel("5:.10,.05,.15,.05,.3");
	ExtConvertHelper.copyArrayToArray(cmpobj["khlrsj"], p2);

	return cph;
};

// 审批页面
var auditLayout = sccustomersLayout;


var complexgridFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("shortName");
	cgh.appendField("commCode");
	cgh.appendField("staffName");
	cgh.appendField("assStaffName");
	cgh.appendField("taxRate");
	cgh.appendField("settlementDayDesc");
	
	cgh.appendField("creditDesc");
	
	cgh.appendField("stateDesc");
	
	cgh.appendField("baseSeqId");
	cgh.appendField("state");
	
	if(urlPs.roleType == "audit") cgh.appendColumn({dataIndex: "baseSeqId"	    , isCheck: true});
	cgh.appendColumn({header: "客户编码"				, dataIndex: "commCode"});
	cgh.appendColumn({header: "客户简称"				, dataIndex: "shortName"     , id: "shortName"});
	cgh.appendColumn({header: "业务员"				, dataIndex: "staffName"     , id: "staffName"});
	cgh.appendColumn({header: "业务助理"				, dataIndex: "assStaffName"     , id: "assStaffName"});
	cgh.appendColumn({header: "税率"				, dataIndex: "taxRate"     , id: "taxRate"});
	cgh.appendColumn({header: "结算方式"				, dataIndex: "settlementDayDesc"     , id: "settlementDayDesc"});
	cgh.appendColumn({header: "客户信用度"			, dataIndex: "creditDesc"});	
	cgh.appendColumn({header: "状态"						, dataIndex: "stateDesc"});
	cgh.appendColumn({header: "操作"						, dataIndex: ""              , id: "operator", width: 170});
	


	return cgh;
};