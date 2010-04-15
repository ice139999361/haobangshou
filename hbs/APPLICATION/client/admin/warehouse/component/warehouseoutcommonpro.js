var warehousegridFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("activeState");
	cgh.appendField("amount");
	cgh.appendField("commAmount");
	cgh.appendField("commDesc");
	cgh.appendField("contactFee");
	cgh.appendField("createTime");
	cgh.appendField("curMoney");
	cgh.appendField("custCode");
	cgh.appendField("custPartNo");
	cgh.appendField("financePeriod");
	cgh.appendField("financeState");
	cgh.appendField("houseType");
	cgh.appendField("isShowPrice");
	cgh.appendField("isShowPriceDesc");
	cgh.appendField("isTax");
	cgh.appendField("partNo");
	cgh.appendField("period");
	cgh.appendField("pnDesc");
	cgh.appendField("poNoType");
	cgh.appendField("price");
	cgh.appendField("priceTax");
	cgh.appendField("rltPoNo");
	cgh.appendField("selfAmount");
	cgh.appendField("sendPoNo");
	cgh.appendField("sendSeqId");
	cgh.appendField("settlementType");
	cgh.appendField("settlementTypeDesc");
	cgh.appendField("shortName");
	cgh.appendField("specDesc");
	cgh.appendField("staffId");
	cgh.appendField("staffName");
	cgh.appendField("state");
	cgh.appendField("stateDesc");
	cgh.appendField("taxRate");
	cgh.appendField("vendorCode");
	cgh.appendField("vendorPoNo");
			    							
	cgh.appendColumn({header: "订单号"            , dataIndex: "rltPoNo"});
	cgh.appendColumn({header: "公司 P/N"		      , dataIndex: "partNo"});
	cgh.appendColumn({header: "客户 P/N"	      	, dataIndex: "custPartNo"});
	cgh.appendColumn({header: "物料描述"	        , dataIndex: "pnDesc"});
	cgh.appendColumn({header: "特殊备注（批次）"  , dataIndex: "specDesc"});
	//cgh.appendColumn({header: "订单数量"          , dataIndex: "l6"});
	//cgh.appendColumn({header: "已出库数量"        , dataIndex: "l7"});
	cgh.appendColumn({header: "本次出库数量"      , dataIndex: "amount", xtype: "textfield"});
	cgh.appendColumn({header: "出库备注"          , dataIndex: "commDesc", xtype: "textfield"});
	cgh.appendColumn({header: "明细状态"  , dataIndex: "stateDesc"});
	if(urlPs.pageType != "query") cgh.appendColumn({header: "操作"              , dataIndex: ""  , id: "operator"});


	cgh.setSubmitFields("activeState,amount,commAmount,contactFee,createTime,custCode,custPartNo,financeState,houseType,isShowPrice,isTax,partNo,period,pnDesc,poNoType,price,priceTax,rltPoNo,selfAmount,sendPoNo,sendSeqId,settlementType,shortName,specDesc,staffId,staffName,state,taxRate,vendorCode,vendorPoNo");
	
	return cgh;
};