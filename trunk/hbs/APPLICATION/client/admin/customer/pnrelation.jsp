<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<script type="text/javascript" src="<%=contextPath %>/customer/common/CommonPro.js"></script>
</head>

<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
			    	
			    		<form id="form"><items>
				    		<listpanel frame="true" title="客户基本信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
								    <autocomplete fieldLabel="客户简称" url="/customerInfo/customerInfoMgr!listDict.action?custInfo.state=0"  displayField="shortName"  valueField="shortName" queryParam="custInfo.shortName"     name="custPartNoInfo.shortName"       labelStyle="width:150" allowBlank="false" id="acShortName" />
				    				<autocomplete fieldLabel="客户编码" url="/customerInfo/customerInfoMgr!listDict.action?custInfo.state=0"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"     name="custPartNoInfo.commCode"       labelStyle="width:150" allowBlank="false" id="acCommCode" />
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="1">
								    <!--
				    				<label fieldLabel="客户简称"               name="custInfo.shortName"   id="acShortName"    labelStyle="width:150" allowBlank="false"/>
									-->
				    				<label fieldLabel="客户结算币种"           name="custInfo.currencyDesc"                      id="acCurrencyDesc"   labelStyle="width:150" paramsValue="CURRENCY"        emptyText="请选择" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<hidden name="custPartNoInfo.seqId" />
				    				<hidden name="custPartNoInfo.state" />
				    				<hidden name="custPartNoInfo.pnDesc" id="acPnDescHidden" />
								</layoutpanel>
				    		</listpanel>
				    		
				    		<listpanel frame="true" title="P/N对照信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<textfield fieldLabel="客户P/N" 	name="custPartNoInfo.custPartNo"     id="acCustPartNo"         labelStyle="width:150" allowBlank="false" />
				    				<autocomplete fieldLabel="本公司P/N" url="/partNo/partNo!list.action" displayField="partNo" valueField="partNo" queryParam="partNo.partNo"	name="custPartNoInfo.partNo"                  labelStyle="width:150" allowBlank="false" id="acPartNo" />
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="1">
				    				<label fieldLabel="描述"  id="acPnDesc"   labelStyle="width:150" allowBlank="false"/>
				    			</layoutpanel>
				    		</listpanel>
				    		
				    		<listpanel frame="true" title="关联信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<textfield fieldLabel="单价"               name="custPartNoInfo.price"                   labelStyle="width:150"  id="idPrice"/>
				    				<!--textfield fieldLabel="最小包装" 			     name=""                        labelStyle="width:150" value="1" /-->
				    				<textfield fieldLabel="样品编码" 					 name="custPartNoInfo.sampleCode"              labelStyle="width:150" />
				    				<!--
				    				<textfield fieldLabel="税率"               name="custPartNoInfo.priceTax"                labelStyle="width:150" />				    	-->	
									<textfield fieldLabel="税率"               name="custPartNoInfo.priceTax"                labelStyle="width:150" value="0.00" id="idPriceTax" readOnly="true" minValue="0.00" maxValue="1"/>
				    				<textfield fieldLabel="最小订单量" 				 name="custPartNoInfo.minAmount"               labelStyle="width:150" value="0" />
									<autocomplete fieldLabel="供应商编码"             name="custPartNoInfo.vendorCode"           labelStyle="width:150"  url="/vendorInfo/vendorInfo!listDict.action"  displayField="commCode"  valueField="commCode" queryParam="vendorInfo.commCode"  allowBlank="false"/>
				    			</layoutpanel>
								<layoutpanel columnNum="1">
				    				<label style="color:red" text="注意：单价和税率的关系，税率为0.00，单价为不含税，税率不为0.00，单价为含税" />
				    			</layoutpanel>
				    		</listpanel>
			    		</items></form>
			    		
			    		<panel buttonAlign="center">
			    			<buttons>
			    				<button text="提交审批" id="submitBtn" />
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
<script type="text/javascript" src="<%=contextPath %>/customer/pnrelation.js"></script>