var queryformFun = function() {
	// 存放控件对象集合
	var cmpobj = {};
			    				
	// 申请时间
	cmpobj["khlrsj"] = [
		 {xtype: "label"    , fieldLabel: "申请时间"}
		,{xtype: "label"    , fieldLabel: "从"           , labelSeparator: ""}
		,{xtype: "datefield", hideLabel: true            , name: "custInfo.dynamicFields.likeBegainTime", format: "Y-m-d", width: 120}
		,{xtype: "label"    , fieldLabel: "到"           , labelSeparator: ""}
		,{xtype: "datefield", hideLabel: true            , name: "custInfo.dynamicFields.likeEndTime"   , format: "Y-m-d", width: 120}
	];
	// 客户编码
	cmpobj["khbm"] = [
		 {xtype: "label"    , fieldLabel: "客户编码"}
		,{xtype: "label"    , fieldLabel: "从"           , labelSeparator: ""}
		,{xtype: "datefield", hideLabel: true            , name: "custInfo.dynamicFields.likeBegainTime", format: "Y-m-d", width: 120}
		,{xtype: "label"    , fieldLabel: "到"           , labelSeparator: ""}
		,{xtype: "datefield", hideLabel: true            , name: "custInfo.dynamicFields.likeEndTime"   , format: "Y-m-d", width: 120}
	];
	
	cmpobj["gswlbm"] = {xtype: "textfield", fieldLabel: "公司物料编码"     , name: "custInfo.commCode"};
	cmpobj["sqr"]    = {xtype: "dictcombo", fieldLabel: "申请人"       , hiddenName: "", paramsValue: "IMPORTANT_CODE"};
	
	return eval((urlPs.roleType + "Layout")).call(this, cmpobj);
};

// 市场业务员布局
var sccustomersLayout = function(cmpobj) {
	var cph = new ColumnPanelHelper;
		
	var p1 = cph.createLayoutPanel("6:.11,.05,.15,.05,.18,.3");
	ExtConvertHelper.copyArrayToArray(cmpobj["khbm"], p1);
	p1.push(cmpobj["gswlbm"]);
	
	var p2 = cph.createLayoutPanel("5:.11,.05,.15,.05,.3");
	ExtConvertHelper.copyArrayToArray(cmpobj["khlrsj"], p2);
	
	return cph;
};

// 市场经理
var scmanagerLayout = function(cmpobj) {	
	var cph = new ColumnPanelHelper;
		
	var p1 = cph.createLayoutPanel("6:.33,.11,.05,.15,.05,.3");
	p1.push(cmpobj["gswlbm"]);
	ExtConvertHelper.copyArrayToArray(cmpobj["khbm"], p1);
	
	var p2 = cph.createLayoutPanel("6:.33,.11,.05,.15,.05,.3");
	p2.push(cmpobj["sqr"]);
	ExtConvertHelper.copyArrayToArray(cmpobj["khlrsj"], p2);

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
	cgh.appendField("l7");
	cgh.appendField("l8");
	cgh.appendField("l9");
	cgh.appendField("l10");

			    							
	cgh.appendColumn({header: "申请人"      , dataIndex: "l1"     , id: "commCode"});
	cgh.appendColumn({header: "申请日期"    , dataIndex: "l2"});
	cgh.appendColumn({header: "GLE P/N"     , dataIndex: "l3"});
	cgh.appendColumn({header: "从 客户"     , dataIndex: "l4"});
	cgh.appendColumn({header: "到 客户"     , dataIndex: "l5"});
	cgh.appendColumn({header: "申请数量"    , dataIndex: "l6"});
	cgh.appendColumn({header: "审批人"      , dataIndex: "l8"});
	cgh.appendColumn({header: "审批意见"    , dataIndex: "l9"});
	cgh.appendColumn({header: "审批时间"    , dataIndex: "l10"});
	
	switch(urlPs.roleType) {
		case "scmanager":
			cgh.appendColumn({header: "操作"      , dataIndex: ""     , id: "operator"});
			break;
	}
	return cgh;
};