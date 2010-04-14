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
				    				<textfield    fieldLabel="出库单号"       name="warehouseSend.sendPoNo"        labelStyle="width:150" />
				    				<dictcombo    fieldLabel="对应分公司"           hiddenName="warehouseSend.companyBranch"  labelStyle="width:150"     paramsValue="COMPANY_BRANCH"     emptyText="请选择" />
				    				<autocomplete fieldLabel="客户"	url="/customerInfo/customerInfo!list.action"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"           name="warehouseSend.custCode"       labelStyle="width:150" id="acCommCode" />
				    				<textfield    fieldLabel="联系电话"       name="warehouseSend.conTel"        labelStyle="width:150" id="tconTel" />
				    				<textfield    fieldLabel="邮编"       name="warehouseSend.receiveZip"        labelStyle="width:150" id="treceiveZip"  />
				    					
				    				<datefield    fieldLabel="出库日期"  	    name="warehouseSend.createDate"        labelStyle="width:150"   id="acCreateDate"  format="Y-m-d" />
				    				<dictcombo    fieldLabel="出库仓库"           hiddenName="warehouseSend.houseType"  labelStyle="width:150"     paramsValue="WAREHOUSE_TYPE"     emptyText="请选择" />				    				
				    				<dictcombo fieldLabel="收货人" url="/customerInfo/custContactInfo!listContactInfo.action"  displayField="conName"  valueField="seqId"  root="data.contactInfoList"  name="warehouseSend.receiveName"       labelStyle="width:150" allowBlank="false" id="areceiveName" />
				    				
				    				<textfield    fieldLabel="传真"       name="warehouseSend.conFax"        labelStyle="width:150" id="tconFax" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<textarea     fieldLabel="收货地址"           name="warehouseSend.receiveAddress"        labelStyle="width:150" width="600" height="25" id="treceiveAddress" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<textarea     fieldLabel="备注"           name="warehouseSend.sendDesc"        labelStyle="width:150" width="600" />
				    			</layoutpanel>	
				    			<layoutpanel columnNum="1">
				    				<hidden name="warehouseSend.state " />
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