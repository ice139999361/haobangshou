<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>入库单查询</title>
	<script type="text/javascript" src="<%=contextPath %>/warehouse/component/querywarehousein.js"></script>
</head>

<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
			    		<queryform gridId="querygrid" exportId="exportBtn">
			    			<layoutpanel columnNum="3">
			    				<textfield fieldLabel="入库单号"   name="warehouseRec.recPoNo"  />	
			    				<autocomplete fieldLabel="供应商" url="/vendorInfo/vendorInfo!listDict.action"  displayField="commCode"  valueField="commCode" queryParam="vendorInfo.commCode"     name="warehouseRec.vendorCode" />
			    				<dictcombo    fieldLabel="入库仓库"           hiddenName=""    paramsValue="WAREHOUSE_TYPE"     showText="请选择" />
			    			</layoutpanel>
			    			<layoutpanel columnNum="6:.11,.05,.15,.05,.18,.3">
			    				<label     fieldLabel="入库日期" />
			    				<label     fieldLabel="从"       labelSeparator=""/>
			    				<datefield hideLabel="true"      name=""          format="Y-m-d"  width="120" />
			    				<label     fieldLabel="到"       labelSeparator=""/>
			    				<datefield hideLabel="true"      name=""          format="Y-m-d"  width="120" />
			    				<dictcombo    fieldLabel="入库单状态"           hiddenName=""    paramsValue="WAREHOUSE_IN_STATE"     showText="请选择" />
			    			</layoutpanel>
			    		</queryform>
			    	
				    	<complexgrid id="querygrid" title="入库单列表" frame="true" page="true" root="data.list" url="/warehouseRec/warehouseRec!list.action" itemsFun="querygridFun" />
			    	<!-- service ext ui.  end. -->
			    	</items>
			    </panel>
			  </items>
			</viewport>
		</application>
	</xmp>
</body>
</html>
<script type="text/javascript" src="<%=contextPath %>/warehouse/querywarehousein.js"></script>