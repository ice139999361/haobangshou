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
				    			<layoutpanel columnNum="2">
				    				<textfield    fieldLabel="出库单号"       name=""        labelStyle="width:150" />
				    				<dictcombo    fieldLabel="对应分公司"           hiddenName=""  labelStyle="width:150"     paramsValue="COMPANY_BRANCH"     emptyText="请选择" />
				    				<autocomplete fieldLabel="客户" url="/customerInfo/customerInfo!list.action"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"     name="custInfo.commCode"       labelStyle="width:150" allowBlank="false" />
				    				<textfield    fieldLabel="联系电话"       name=""        labelStyle="width:150" />
				    				<textfield    fieldLabel="邮编"       name=""        labelStyle="width:150"   />
				    					
				    				<datefield    fieldLabel="出库日期"  	    name=""        labelStyle="width:150"     format="Y-m-d" />
				    				<dictcombo    fieldLabel="出库仓库"           hiddenName=""  labelStyle="width:150"     paramsValue="COMPANY_BRANCH"     emptyText="请选择" />
				    				<autocomplete fieldLabel="收货人" url="/customerInfo/customerInfo!list.action"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"     name="custInfo.commCode"       labelStyle="width:150" allowBlank="false" />
				    				<textfield    fieldLabel="传真"       name=""        labelStyle="width:150"  />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<textfield    fieldLabel="备注"           name=""        labelStyle="width:150" width="600" />
				    				<textarea     fieldLabel="备注"           name=""        labelStyle="width:150" width="600" />
				    			</layoutpanel>	
				    		</listpanel>
			    		</items></form>
			    		
			    		<complexgrid id="warehousegrid" frame="true" height="200" deftbar="false" url="1" title="出库详情" itemsFun="warehousegridFun" />
			    		
			    		<panel buttonAlign="center">
			    			<buttons>
			    				<button text="添加入库详情" id="addInfoBtn" />
			    				<button text="提交"         id="submitBtn" />
			    				<button text="保存"         id="saveBtn"   />
			    				<button text="打印"         id="printBtn" />
			    				<button text="取消"         id="backBtn"   />
			    			</buttons>
			    		</panel>
			    		
			    	<!-- service ext ui.  end. -->
			    	</items>
			    </panel>
			  </items>
			</viewport>
			<window id="selectWindow" title="查询客户订单" width="900" closeAction="hide">
				<items>
					<queryform gridId="querygrid">
	    			<layoutpanel columnNum="3">
	    				<textfield fieldLabel="客户编码"                   name="custOrder.commCode"  />
	    				<textfield fieldLabel="订单号"                     name="" />
	    				<textfield fieldLabel="客户物料编码"               name="custOrder.poNo"  />
	    					
	    				<textfield fieldLabel="公司物料编码"               name="" />
	    				<textfield fieldLabel="特殊备注（批次）"           name="" />
	    			</layoutpanel>
	    		</queryform>
	    		
	    		<complexgrid id="querygrid" buttonAlign="center" title="订单详情" frame="true" height="300" url="/custOrder/custOrderScMgr!list.action" page="true" root="data.list">
	    			<fields>
	    				<field name="l1" />
	    				<field name="l2" />
	    				<field name="l3" />
	    				<field name="l4" />
	    				<field name="l5" />
	    				<field name="l6" />
	    				<field name="l7" />
	    				<field name="l8" />
	    				<field name="l9" />
	    			</fields>
	    			<columns>
	    				<column header=""                   isCheck="true" />
	    				<column header="订单号"             dataIndex="l1" />
	    				<column header="公司 P/N"           dataIndex="l2" />
	    				<column header="客户 P/N"           dataIndex="l3" />
	    				<column header="物料描述"           dataIndex="l4" />
	    				<column header="特殊备注（批次）"   dataIndex="l5" />
	    				<column header="订单数量"           dataIndex="l6" />
	    				<column header="已出库数量"         dataIndex="l7" />
	    			</columns>
	    			<buttons>
	    				<button text="确定" id="wokBtn" />
	    				<button text="取消" id="wbackBtn"   />
	    			</buttons>
	    		</complexgrid>
				</items>
			</window>
		</application>
	</xmp>
</body>
</html>
<script type="text/javascript" src="<%=contextPath %>/warehouse/editorwarehouseout.js"></script>