<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>本公司物料录入</title>
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
				    		<listpanel frame="true" title="本公司物料信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<textfield fieldLabel="物料编码"   name="partNo.partNo"            labelStyle="width:150" vtype="commCheck" checkUrl="/partNo/partNo!checkPartNo.action" allowBlank="false" id="partNo" />
				    				<!--
				    				<textfield fieldLabel="含税价格"   name="partNo.taxPrice"          labelStyle="width:150" />
				    					-->
				    				<combotree fieldLabel="所属类别"   passName="partNo.clsCode"       labelStyle="width:150" emptyText="请选择"  dataUrl="/partNo/pClass!all.action" root="data.list" id="cmbClsCode" />
				    				<!--
				    				<textfield fieldLabel="价格"       name="partNo.price"             labelStyle="width:150" />
				    				-->
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="1">
				    				<textarea  fieldLabel="物料描述"   name="partNo.pnDesc"            labelStyle="width:150" width="600" height="90" />
				    			</layoutpanel>
				    		</listpanel>
			    		</items></form>
			    		
			    		<panel buttonAlign="center">
			    			<buttons>
			    				<button text="新增" id="addBtn"   />
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
<script type="text/javascript" src="<%=contextPath %>/materiel/editormateriel.js"></script>