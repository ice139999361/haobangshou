<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>供应商信息查询</title>
	<script type="text/javascript" src="<%=contextPath %>/vendor/common/CommonPro.js"></script>
</head>

<body>
	<xmp id="config"  style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
			    	
			    		<form id="form"><items>
				    		<listpanel frame="true" title="供应商基本信息" collapsible="true" titleCollapse="true">
								<layoutpanel columnNum="2">
				    				<label fieldLabel="供应商编码"             name="vendorInfo.commCode"           labelStyle="width:150" />
				    				<label fieldLabel="法人代表"               name="vendorInfo.representative"       labelStyle="width:150" />
								</layoutpanel>
								<layoutpanel columnNum="2">	
				    				<label fieldLabel="供应商简称"             name="vendorInfo.shortName"            labelStyle="width:150" allowBlank="false"/>
									<label fieldLabel="英文简称"               name="vendorInfo.enShortName"          labelStyle="width:150" />
								</layoutpanel>
								<layoutpanel columnNum="2">	
				    				<label fieldLabel="信用度"                 name="vendorInfo.creditDesc"     labelStyle="width:150" paramsValue="CREDIT_RATE"     emptyText="请选择" />
									<label fieldLabel="重要程度"               name="vendorInfo.importantDesc"  labelStyle="width:150" paramsValue="IMPORTANT_CODE"  emptyText="请选择" />
								</layoutpanel>
								<layoutpanel columnNum="2">	
				    				<label fieldLabel="性质"                   name="vendorInfo.commType"             labelStyle="width:150" />
									<label fieldLabel="规模"                   name="vendorInfo.commScale"            labelStyle="width:150" />
								</layoutpanel>
								<layoutpanel columnNum="1">
									<label fieldLabel="中文名称"               name="vendorInfo.allName"              labelStyle="width:150"   width="600" />
									<label fieldLabel="公司地址"               name="vendorInfo.address"              labelStyle="width:150"   width="600" />
									<label fieldLabel="英文名称"               name="vendorInfo.enName"               labelStyle="width:150"   width="600" />
				    				<label fieldLabel="英文地址"               name="vendorInfo.enAddress"            labelStyle="width:150"   width="600" />		
									<label fieldLabel="纳税人识别号"           name="vendorInfo.taxCode"              labelStyle="width:150"   width="600" />
									<label fieldLabel="公司网址"               name="vendorInfo.webSite"              labelStyle="width:150"   width="600" />
								</layoutpanel>
				    			<layoutpanel columnNum="2">
				    				<label fieldLabel="对应的分公司或分支机构" name="vendorInfo.companyBranchDesc"  labelStyle="width:150" paramsValue="COMPANY_BRANCH"  emptyText="请选择" />
				    				<label fieldLabel="操作人员名字"           name="vendorInfo.staffName"            labelStyle="width:150" />
				    				<!--
									<label fieldLabel="是否显示单价"           name="vendorInfo.isShowPriceDesc"    labelStyle="width:150" paramsValue="IS_SHOW_PRICE"   emptyText="请选择" />
									-->
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="1">
				    				<label fieldLabel="备注"                    name="vendorInfo.commDesc"             labelStyle="width:150"/>
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="1">
				    				<hidden name="vendorInfo.baseSeqId" />
				    				<hidden name="vendorInfo.state" />
				    				<hidden name="vendorInfo.staffId" />
									<hidden name="vendorInfo.isShowPrice" />
				    			</layoutpanel>
				    		</listpanel>
				    		
				    		<listpanel frame="true" title="供应商结算信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<label fieldLabel="结算类型"               name="vendorInfo.settlementDesc"            labelStyle="width:150"  />
				    				<label fieldLabel="结算币种"               name="vendorInfo.currencyDesc"                  labelStyle="width:150" paramsValue="CURRENCY"        emptyText="请选择" />	
				    				<label fieldLabel="合同费"                 name="vendorInfo.contactFee"                      labelStyle="width:150" />
				    				<!--
									<label fieldLabel="账期类型" 			         name="vendorInfo.accountPreiod.accountTypeDesc" labelStyle="width:150" paramsValue="ACCOUNT_TYPE" emptyText="请选择" />
									-->
				    				<label fieldLabel="账期的起始日" 					 name="vendorInfo.accountPreiod.periodStart"    id="vaPeriodStart"   labelStyle="width:150" />
				    				<!--
									<label fieldLabel="客户账期的最大交易金额" name="vendorInfo.accountPreiod.maxMoney"          labelStyle="width:150" />
									-->
				    				<label fieldLabel="账期提醒设置" 							 name="vendorInfo.accountPreiod.reminderDay"  id="vaReminderDay"     labelStyle="width:150" />
				    				
				    				<label fieldLabel="税率"                   name="vendorInfo.taxRate"                         labelStyle="width:150" />	
				    				<!--
									<label fieldLabel="供应商的账期设置" 		   name="vendorInfo.accountPreiod.accountPeriod"     labelStyle="width:150" />	
									-->
				    				<label fieldLabel="结算方式" 					 name="vendorInfo.accountPreiod.settlementDayDesc"   id="vaSettlementDay"  labelStyle="width:150" />	
				    				<label fieldLabel="预付百分比" 						 name="vendorInfo.prePaidInfo.prePaid"     id="vpPrePaid"        labelStyle="width:150" />
				    				<!--
									<label fieldLabel="催款提醒" 			           name="vendorInfo.prePaidInfo.reminderDay"         labelStyle="width:150" />
									-->
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="1">
				    				<hidden name="vendorInfo.accountPreiod.seqId" />
				    				<hidden name="vendorInfo.prePaidInfo.seqId" />
				    			</layoutpanel>
				    		</listpanel>
				    		
				    		<complexgrid id="contactgrid" title="供应商联系人信息" itemsFun="contactFun" itemsAlign="after" frame="true" height="200" url="1" editorFlag="false">
				    			<fields>
				    				<field name="isPrimary" />
				    			</fields>
				    			
				    			<columns>
				    				<column header="是否是主联系人" dataIndex="isPrimary" xtype="dictcombo" paramsValue="IS_PRIMARY" />
				    			</columns>
				    			
				    			<submitFields value="seqId,conName,conDuty,conTel,conMobile,conFax,conMail,conQq,conMsn,conOther,isPrimary" />
				    		</complexgrid>
				    		
				    		<complexgrid id="consigneegrid" title="供应商收货人信息" itemsFun="contactFun" itemsAlign="after" frame="true" height="200" url="1" editorFlag="false">
				    			<fields>
				    				<field name="conAddress" />
				    				<field name="conZip" />
				    				<field name="isPrimary" />
				    			</fields>
				    			
				    			<columns>
				    				<column header="收货地址" 			dataIndex="conAddress" xtype="textfield" />
				    				<column header="收货邮编" 			dataIndex="conZip"     xtype="textfield" />
				    				<column header="是否是主收货人" dataIndex="isPrimary"  xtype="dictcombo" paramsValue="IS_PRIMARY" />
				    			</columns>
				    			
				    			<submitFields value="seqId,conName,conDuty,conTel,conMobile,conFax,conMail,conQq,conMsn,conOther,conAddress,conZip,isPrimary" />
				    		</complexgrid>
				    		
				    		<complexgrid id="custbankgrid" title="供应商银行信息" itemsFun="custbankFun" frame="true" height="200" url="1" editorFlag="false"/>
				    			
				    		<auditpanel id="auditPanel" />
				    			
			    		</items></form>
			    		
			    		
			    		
			    		<panel buttonAlign="center">
			    			<buttons>
			    				<button text="提交" id="submitBtn" />
			    				<button text="取消" id="backBtn"   />
			    			</buttons>
			    		</panel>
			    		
			    	<!-- service ext ui.  end. -->
			    	</items>
			    </panel>
			  </items>
			</viewport>
		</application>
	</xmp>
</body>
</html>
<script type="text/javascript" src="<%=contextPath %>/vendor/detailvendor.js"></script>