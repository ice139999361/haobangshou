<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<script type="text/javascript" src="<%=contextPath %>/warehouse/component/warehouseincommonpro.js"></script>
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
				    		<listpanel frame="true" title="入库基本信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<textfield    fieldLabel="入库单号"       name=""        labelStyle="width:150"     emptyText="供应商的送货单号" />
				    				<datefield    fieldLabel="供应商单据日期" name=""        labelStyle="width:150"     emptyText="送货单的日期" format="Y-m-d" />
				    				<autocomplete fieldLabel="供应商" url="/customerInfo/customerInfo!list.action"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"     name="custInfo.commCode"       labelStyle="width:150" allowBlank="false" id="acCommCode" />
				    					 
				    				<dictcombo    fieldLabel="仓库"           hiddenName=""  labelStyle="width:150"     paramsValue="COMPANY_BRANCH"     emptyText="请选择" />			
				    				<datefield    fieldLabel="到货日期"  	    name=""        labelStyle="width:150"     format="Y-m-d" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<textarea     fieldLabel="备注"           name=""        labelStyle="width:150" width="600" />
				    			</layoutpanel>	
				    		</listpanel>
			    		</items></form>
			    		
			    		<complexgrid id="warehousegrid" frame="true" height="200" deftbar="true" url="1" title="入库详情" itemsFun="warehousegridFun" />
			    		
			    		<panel buttonAlign="center">
			    			<buttons>
			    				<button text="提交" id="submitBtn" />
			    				<button text="保存" id="saveBtn"   />
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
<script type="text/javascript" src="<%=contextPath %>/warehouse/editorwarehousein.js"></script>