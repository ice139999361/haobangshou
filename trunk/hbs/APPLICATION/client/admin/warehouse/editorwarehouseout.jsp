<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<script type="text/javascript" src="<%=contextPath %>/warehouse/component/warehouseoutcommonpro.js"></script>
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
				    		<listpanel frame="true" title="出库基本信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="3:.3,.2,.5">
				    				<autocomplete fieldLabel="客户" url="/customerInfo/customerInfo!list.action"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"     name=""       labelStyle="width:150" allowBlank="false" />
				    				<button text="查询订单" />
				    				<label    fieldLabel="对应分公司" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="2">
				    				<autocomplete fieldLabel="选择收货人" url="/customerInfo/customerInfo!list.action"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"     name=""       labelStyle="width:150" allowBlank="false" />
				    				<textfield    fieldLabel="联系电话"     name=""        labelStyle="width:150" />
				    				<textfield    fieldLabel="地址"       name=""        labelStyle="width:150" />
				    				<dictcombo    fieldLabel="结算方式"         hiddenName=""  labelStyle="width:150"     paramsValue="COMPANY_BRANCH"     emptyText="请选择" />				
				    					
				    				<textfield    fieldLabel="联系人"     name=""        labelStyle="width:150" />
				    				<textfield    fieldLabel="传真"     name=""        labelStyle="width:150" />
				    				<textfield    fieldLabel="邮编"     name=""        labelStyle="width:150" />
				    				<dictcombo    fieldLabel="仓库"           hiddenName=""  labelStyle="width:150"     paramsValue="COMPANY_BRANCH"     emptyText="请选择" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<textarea     fieldLabel="备注"           name=""        labelStyle="width:150" width="600" />
				    			</layoutpanel>	
				    		</listpanel>
			    		</items></form>
			    		
			    		<complexgrid id="warehousegrid" frame="true" height="200" deftbar="true" url="1" title="出库详情" itemsFun="warehousegridFun" />
			    		
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
<script type="text/javascript" src="<%=contextPath %>/warehouse/editorwarehouseout.js"></script>