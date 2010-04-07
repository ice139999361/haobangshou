<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>入库单查看</title>
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
				    				<label    fieldLabel="入库单号"       name="warehouseRec.recPoNo"        labelStyle="width:150"      />
				    				<label    fieldLabel="供应商单据日期" name="warehouseRec.poNoDate"      id="acPoNoDate"  labelStyle="width:150"  format="Y-m-d" />
				    				<label    fieldLabel="供应商"         name="warehouseRec.vendorCode"       labelStyle="width:150"  />
				    				
				    				<label    fieldLabel="入库日期"  	    name="warehouseRec.operTime"        labelStyle="width:150"     format="Y-m-d" id="acOperTime" />
				    				<label    fieldLabel="到货日期"  	    name="warehouseRec.applyDate"        labelStyle="width:150"    format="Y-m-d" id="acApplyDate"  />
				    				<label    fieldLabel="仓库"           name="warehouseRec.houseTypeDesc"        labelStyle="width:150"    />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<label     fieldLabel="备注"           name="warehouseRec.receiveDesc"        labelStyle="width:150" />
				    			</layoutpanel>	
				    		</listpanel>
			    		</items></form>
			    		
			    		<complexgrid id="warehousegrid" frame="true" height="200" deftbar="false" url="1" title="入库详情" itemsFun="warehousegridFun" editorFlag="false" />
			    		
			    		<panel buttonAlign="center">
			    			<buttons>
			    				<button text="取消"         id="backBtn"   />
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
<script type="text/javascript" src="<%=contextPath %>/warehouse/detailwarehousein.js"></script>