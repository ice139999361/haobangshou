/**
 * 联系人及收货人表格组件
 */

var contactFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("seqId");
	cgh.appendField("conName");
	cgh.appendField("conDuty");
	cgh.appendField("conTel");
	cgh.appendField("conMobile");
	cgh.appendField("conFax");
	cgh.appendField("conMail");
	cgh.appendField("conQq");
	cgh.appendField("conMsn");
	cgh.appendField("conOther");
	
	
	if(this.deftbar) cgh.appendColumn({dataIndex: "seqId"	    , isCheck: true});
	cgh.appendColumn({header: "姓名"					, dataIndex: "conName"	 , xtype: "textfield"});
	cgh.appendColumn({header: "职务"					, dataIndex: "conDuty"	 , xtype: "textfield"});
	cgh.appendColumn({header: "固定电话"			, dataIndex: "conTel"	   , xtype: "textfield"});
	cgh.appendColumn({header: "移动电话"			, dataIndex: "conMobile" , xtype: "textfield"});
	cgh.appendColumn({header: "传真"					, dataIndex: "conFax"	   , xtype: "textfield"});
	cgh.appendColumn({header: "邮箱"					, dataIndex: "conMail"	 , xtype: "textfield"});
	cgh.appendColumn({header: "QQ"						, dataIndex: "conQq"	   , xtype: "textfield"});
	cgh.appendColumn({header: "MSN"						, dataIndex: "conMsn"	   , xtype: "textfield"});
	cgh.appendColumn({header: "其他信息"			, dataIndex: "conOther"	 , xtype: "textfield"});
	
	return cgh;
};

/**
 * 客户银行信息表格组件
 */
var custbankFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("seqId");
	cgh.appendField("accountName");
	cgh.appendField("accountBank");
	cgh.appendField("account");
	
	if(this.deftbar) cgh.appendColumn({dataIndex: "seqId" , isCheck: true});
	cgh.appendColumn({header: "开户户名" , dataIndex: "accountName"	, xtype: "textfield", width: 150});
	cgh.appendColumn({header: "开户行" 	 , dataIndex: "accountBank"	, xtype: "textfield", width: 200});
	cgh.appendColumn({header: "帐号" 		 , dataIndex: "account"	    , xtype: "textfield"});
	
	cgh.setSubmitFields("seqId,accountName,accountBank,account");
	
	return cgh;
};

/**
 * 产品目录信息表格组件
 */
var productdirFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("seqId");
	cgh.appendField("accountName");
	cgh.appendField("accountBank");
	cgh.appendField("account");
	
	
	cgh.appendColumn({header: "名称"         , dataIndex: "l1"	, xtype: "textfield"});
	cgh.appendColumn({header: "供应商编码" 	 , dataIndex: "l2"	, xtype: "textfield"});
	cgh.appendColumn({header: "GLE编码" 		 , dataIndex: "l3"	, xtype: "textfield"});
	cgh.appendColumn({header: "描述"         , dataIndex: "l4"	, xtype: "textfield"});
	cgh.appendColumn({header: "币别" 	       , dataIndex: "l5"	, xtype: "textfield"});
	cgh.appendColumn({header: "单价" 		     , dataIndex: "l6"	, xtype: "textfield"});
	cgh.appendColumn({header: "税率"         , dataIndex: "l7"	, xtype: "textfield"});
	cgh.appendColumn({header: "最小包装" 	   , dataIndex: "l8"	, xtype: "textfield"});
	cgh.appendColumn({header: "最小订货量" 	 , dataIndex: "l9"	, xtype: "textfield"});
	cgh.appendColumn({header: "样品编码" 		 , dataIndex: "l0"	, xtype: "textfield"});
	
	return cgh;
};

/**
 * 采购单信息表格组件
 */
var purchaseFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("seqId");
	cgh.appendField("accountName");
	cgh.appendField("accountBank");
	cgh.appendField("account");
	
	
	cgh.appendColumn({header: "采购单号"   , dataIndex: "l1"	, xtype: "textfield"});
	cgh.appendColumn({header: "开单日期" 	 , dataIndex: "l2"	, xtype: "textfield"});
	cgh.appendColumn({header: "GLE编码" 	 , dataIndex: "l3"	, xtype: "textfield"});
	cgh.appendColumn({header: "描述"       , dataIndex: "l4"	, xtype: "textfield"});
	cgh.appendColumn({header: "币别" 	     , dataIndex: "l5"	, xtype: "textfield"});
	cgh.appendColumn({header: "单价" 		   , dataIndex: "l6"	, xtype: "textfield"});
	cgh.appendColumn({header: "税率"       , dataIndex: "l7"	, xtype: "textfield"});
	cgh.appendColumn({header: "数量" 	     , dataIndex: "l8"	, xtype: "textfield"});
	cgh.appendColumn({header: "金额" 	     , dataIndex: "l9"	, xtype: "textfield"});
	cgh.appendColumn({header: "交货日期" 	 , dataIndex: "l0"	, xtype: "textfield"});
	cgh.appendColumn({header: "备注" 	     , dataIndex: "la"	, xtype: "textfield"});
	cgh.appendColumn({header: "状态" 		   , dataIndex: "lb"	, xtype: "textfield"});
	   
	return cgh;
};


/**
 * 结算信息组件
 */
var balanceFun = function(cmpobj) {	
	var cph = new ColumnPanelHelper;
		
	var p1 = cph.createLayoutPanel(2);
	p1.push({xtype: "label", fieldLabel: "重要程度"       , name: ""});
	p1.push({xtype: "label", fieldLabel: "结算类型"       , name: ""});
	p1.push({xtype: "label", fieldLabel: "预付"           , name: ""});
	p1.push({xtype: "label", fieldLabel: "结算币种"       , name: ""});
	p1.push({xtype: "label", fieldLabel: "合同费"         , name: ""});
	p1.push({xtype: "label", fieldLabel: "显示单价"       , name: ""});
	p1.push({xtype: "label", fieldLabel: "是否含税"       , name: ""});
	p1.push({xtype: "label", fieldLabel: "税率"           , name: ""});
	
	p1.push({xtype: "label", fieldLabel: "帐期类型"       , name: ""});
	p1.push({xtype: "label", fieldLabel: "帐期设置"       , name: ""});
	p1.push({xtype: "label", fieldLabel: "帐期起始日"     , name: ""});
	p1.push({xtype: "label", fieldLabel: "对帐日"         , name: ""});
	p1.push({xtype: "label", fieldLabel: "结算日"         , name: ""});
	p1.push({xtype: "label", fieldLabel: "最大交易金额"   , name: ""});
	p1.push({xtype: "label", fieldLabel: "提醒提前天数"   , name: ""});
	p1.push({xtype: "label", fieldLabel: "对帐单注意事项" , name: ""});

	return cph;
};