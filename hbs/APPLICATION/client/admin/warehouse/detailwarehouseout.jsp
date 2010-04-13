<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>出库单查看</title>
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
				    			<layoutpanel columnNum="2">
				    				<label    fieldLabel="出库单号"       name="warehouseSend.sendPoNo"                   labelStyle="width:150" />
				    				<label    fieldLabel="对应分公司"     name="warehouseSend.companyBranchDesc"                   labelStyle="width:150" />
				    				<label    fieldLabel="客户"           name="warehouseSend.custCode"  labelStyle="width:150" />
				    				<label    fieldLabel="联系电话"       name="warehouseSend.conTel"                   labelStyle="width:150" />
				    				<label    fieldLabel="邮编"           name="warehouseSend.receiveZip"                   labelStyle="width:150"   />
				    					
				    				<label    fieldLabel="出库日期"  	    name="warehouseSend.createDate"                   labelStyle="width:150"  format="Y-m-d" id="acCreateDate" />
				    				<label    fieldLabel="出库仓库"       name="warehouseSend.houseTypeDesc"                   labelStyle="width:150"    />
				    				<label    fieldLabel="收货人"         name="warehouseSend.receiveName"  labelStyle="width:150" />
				    				<label    fieldLabel="传真"           name="warehouseSend.conFax"                   labelStyle="width:150"  />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<label     fieldLabel="备注"          name="warehouseSend.sendDesc"                   labelStyle="width:150"  />
				    			</layoutpanel>	
				    		</listpanel>
			    		</items></form>
			    		
			    		<complexgrid id="warehousegrid" frame="true" height="200" deftbar="false" url="1" title="出库详情" itemsFun="warehousegridFun" editorFlag="false"/>
			    		
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
<script type="text/javascript" src="<%=contextPath %>/warehouse/detailwarehouseout.js"></script>