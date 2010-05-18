<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<script type="text/javascript" src="<%=contextPath %>/vendor/common/CommonPro.js"></script>
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
				    		<listpanel frame="true" title="供应商基本信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
									<autocomplete fieldLabel="供应商简称" url="/vendorInfo/vendorInfo!listDict.action?vendorInfo.state=0"  displayField="shortName"  valueField="shortName" queryParam="vendorInfo.shortName"     name="vendorPartNoInfo.shortName"       labelStyle="width:150" allowBlank="false" id="acShortName"/>
				    				<autocomplete fieldLabel="供应商编码" url="/vendorInfo/vendorInfo!listDict.action?vendorInfo.state=0"  displayField="commCode"  valueField="commCode" queryParam="vendorInfo.commCode"     name="vendorPartNoInfo.commCode"       labelStyle="width:150" allowBlank="false" id="acCommCode"/>
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="1">
				    				<!--
									<label fieldLabel="供应商简称"               name="vendorInfo.shortName"  	id="acShortName"      labelStyle="width:150" allowBlank="false"/>
									-->
				    				<label fieldLabel="供应商结算币种"           name="vendorInfo.currencyDesc" id="acCurrencyDesc"   labelStyle="width:150" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<hidden name="vendorPartNoInfo.seqId" />
				    				<hidden name="vendorPartNoInfo.pnDesc" id="acPnDescHidden" />
				    				<hidden name="vendorPartNoInfo.state" />
								</layoutpanel>
				    		</listpanel>
				    		
				    		<listpanel frame="true" title="P/N对照信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<textfield    fieldLabel="供应商P/N"       id="acCustPartNo"        name="vendorPartNoInfo.custPartNo"              labelStyle="width:150" allowBlank="false"/>
				    				<autocomplete fieldLabel="本公司P/N" url="/partNo/partNo!list.action" displayField="partNo" valueField="partNo" queryParam="partNo.partNo"	name="vendorPartNoInfo.partNo"                  labelStyle="width:150" allowBlank="false" id="acPartNo" />
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="1">
				    				<label fieldLabel="描述"  id="acPnDesc"                   name="vendorPartNoInfo.pnDesc"                  labelStyle="width:150" allowBlank="false"/>
				    			</layoutpanel>
				    		</listpanel>
				    		
				    		<listpanel frame="true" title="关联信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<textfield   fieldLabel="单价"               name="vendorPartNoInfo.price"                   labelStyle="width:150" />
				    				<textfield   fieldLabel="最小包装" 			     name="vendorPartNoInfo.minPackage"                        labelStyle="width:150" value="1" />
				    				<textfield   fieldLabel="样品编码" 					 name="vendorPartNoInfo.sampleCode"              labelStyle="width:150" />
				    				
				    				<numberfield fieldLabel="税率"               name="vendorPartNoInfo.priceTax"                labelStyle="width:150" value="0.00" minValue="0.00" maxValue="1"/>
				    				<textfield   fieldLabel="最小订单量" 				 name="vendorPartNoInfo.minAmount"               labelStyle="width:150" value="0" />
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
<script type="text/javascript" src="<%=contextPath %>/vendor/pnrelation.js"></script>