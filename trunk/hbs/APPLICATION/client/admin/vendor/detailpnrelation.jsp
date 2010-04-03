<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>供应商P/N对照查看</title>
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
				    			<layoutpanel columnNum="1">
				    				<hidden name="vendorPartNoInfo.seqId" />
				    				<hidden name="vendorPartNoInfo.state" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<label fieldLabel="供应商编码"           name="vendorPartNoInfo.commCode"           id="commCode"    labelStyle="width:150" allowBlank="false"/>
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="2">
				    				<label fieldLabel="供应商简称"               name="vendorInfo.shortName"      labelStyle="width:150" allowBlank="false"/>
				    				<label fieldLabel="供应商结算币种"           name="vendorInfo.currencyDesc"   labelStyle="width:150" paramsValue="CURRENCY"        emptyText="请选择" />
				    			</layoutpanel>
				    		</listpanel>
				    		
				    		<listpanel frame="true" title="P/N对照信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<label fieldLabel="供应商"               name="vendorPartNoInfo.custPartNo"         id="custPartNo"             labelStyle="width:150" allowBlank="false"/>
				    				<label fieldLabel="本公司"             name="vendorPartNoInfo.partNo"               id="partNo"           labelStyle="width:150" allowBlank="false"/>
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="1">
				    				<label fieldLabel="描述"                   name="vendorPartNoInfo.pnDesc"                  labelStyle="width:150" allowBlank="false"/>
				    			</layoutpanel>
				    		</listpanel>
				    		
				    		<listpanel frame="true" title="关联信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<label fieldLabel="单价"               name="vendorPartNoInfo.price"                   labelStyle="width:150" />
				    				<label fieldLabel="最小包装" 			     name=""                        labelStyle="width:150" value="1" />
				    				<label fieldLabel="样品编码" 					 name="vendorPartNoInfo.sampleCode"              labelStyle="width:150" />
				    				
				    				<label fieldLabel="税率"               name="vendorPartNoInfo.priceTax"                labelStyle="width:150" />				    				
				    				<label fieldLabel="最小订单量" 				 name="vendorPartNoInfo.minAmount"               labelStyle="width:150" value="0" />
				    			</layoutpanel>
				    		</listpanel>
				    		
				    		<auditpanel id="auditPanel" />
				    			
			    		</items></form>
			    		
			    		<panel buttonAlign="center">
			    			<buttons>
			    				<button text="提交" id="submitBtn" />
			    				<button text="取消" id="backBtn"   />
			    				<button text="历史变更查看" id="historyBtn"   />
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
<script type="text/javascript" src="<%=contextPath %>/vendor/detailpnrelation.js"></script>