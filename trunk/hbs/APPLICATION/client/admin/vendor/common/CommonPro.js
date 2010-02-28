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
}

/**
 * 客户银行信息表格组件
 */
var custbankFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("seqId");
	cgh.appendField("accountName");
	cgh.appendField("accountBank");
	cgh.appendField("account");
	
	if(this.deftbar) cgh.appendColumn({dataIndex: "seqId"	, isCheck: true});
	cgh.appendColumn({header: "开户户名" , dataIndex: "accountName"	, xtype: "textfield", width: 150});
	cgh.appendColumn({header: "开户行" 	 , dataIndex: "accountBank"	, xtype: "textfield", width: 200});
	cgh.appendColumn({header: "帐号" 		 , dataIndex: "account"	    , xtype: "textfield"});
	
	cgh.setSubmitFields("seqId,accountName,accountBank,account");

	return cgh;
}