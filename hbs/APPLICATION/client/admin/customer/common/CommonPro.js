/**
 * 联系人及收货人表格组件
 */

var contactFun = function() {
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
	
	cgh.appendColumn({dataIndex: "l1"	, isCheck: true});
	cgh.appendColumn({header: "姓名"					, dataIndex: "l1"	, xtype: "textfield"});
	cgh.appendColumn({header: "职务"					, dataIndex: "l2"	, xtype: "textfield"});
	cgh.appendColumn({header: "固定电话"			, dataIndex: "l3"	, xtype: "textfield"});
	cgh.appendColumn({header: "移动电话"			, dataIndex: "l4"	, xtype: "textfield"});
	cgh.appendColumn({header: "传真"					, dataIndex: "l5"	, xtype: "textfield"});
	cgh.appendColumn({header: "邮箱"					, dataIndex: "l6"	, xtype: "textfield"});
	cgh.appendColumn({header: "QQ"						, dataIndex: "l7"	, xtype: "textfield"});
	cgh.appendColumn({header: "MSN"						, dataIndex: "l8"	, xtype: "textfield"});
	cgh.appendColumn({header: "其他信息"			, dataIndex: "l9"	, xtype: "textfield"});
	
	return cgh;
}

/**
 * 客户银行信息表格组件
 */
var custbankFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("l1");
	cgh.appendField("l2");
	cgh.appendField("l3");
	
	cgh.appendColumn({dataIndex: "l1"	, isCheck: true});
	cgh.appendColumn({header: "开户户名" , dataIndex: "l1"	, xtype: "textfield", width: 150});
	cgh.appendColumn({header: "开户行" 	 , dataIndex: "l2"	, xtype: "textfield", width: 200});
	cgh.appendColumn({header: "帐号" 		 , dataIndex: "l3"	, xtype: "textfield"});
	
	cgh.setSubmitFields("l1,l2,l3");
	return cgh;
}