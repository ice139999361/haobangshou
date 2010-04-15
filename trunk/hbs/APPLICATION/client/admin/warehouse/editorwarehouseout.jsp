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
				    				<dictcombo    fieldLabel="出库仓库"           hiddenName="warehouseSend.houseType"  labelStyle="width:150"     paramsValue="WAREHOUSE_TYPE"     emptyText="请选择" id="acHouseType"/>				    				
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
				    				<hidden name="warehouseSend.financeState " />
				    				<hidden name="warehouseSend.activeState " />
				    				<hidden name="warehouseSend.operId " />
				    				<hidden name="warehouseSend.operStaff " />
				    				<hidden name="warehouseSend.poNoType " />
				    				<hidden name="warehouseSend.settlementType " />
				    				<hidden name="warehouseSend.shortName " />				    				
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
	    				<textfield fieldLabel="客户编码"                   name="orderDetail.commCode"  />
	    				<textfield fieldLabel="客户订单号"                     name="orderDetail.poNo" />
	    				<textfield fieldLabel="客户物料编码"               name="orderDetail.cpartNo"  />
	    					
	    				<textfield fieldLabel="公司物料编码"               name="orderDetail.partNo" />
	    				<textfield fieldLabel="特殊备注（批次）"           name="orderDetail.specDesc" />
	    			</layoutpanel>
	    		</queryform>
	    		
	    		<complexgrid id="querygrid" buttonAlign="center" title="客户订单详情" frame="true" height="300" url="/custOrderDetail/orderDetail!listDetail.action" page="true" root="data.list">
	    			<fields>
	    				<field name="commCode" />
	    				<field name="shortName" />
	    				<field name="poNoType" />
	    				<field name="settlementType" />
	    				<field name="poNo" />
	    				<field name="cpartNo" />
	    				<field name="partNo" />
	    				<field name="pnDesc" />
	    				<field name="cprice" />
	    				<field name="cpriceTax" />
	    				<field name="isTax" />
	    				<field name="taxRate" />
	    				<field name="specDesc" />
	    				<field name="commDesc" />
	    				<field name="amount" />
	    				<field name="money" />
	    				<field name="deliveryAmount" />
	    				<field name="selfDeliveryAmount" />
	    				<field name="commDeliveryAmount" />
	    				<field name="deliveryHouseType" />
	    				<field name="lockAmount" />
	    				<field name="selfLockAmount" />
	    				<field name="commLockAmount" />
	    				<field name="orgDeliveryDate" />
	    				<field name="preDeliveryDate" />
	    				<field name="verDeliveryDate" />
	    				<field name="period" />
	    				<field name="rltOrderPoNo" />
	    				<field name="vendorCode" />	    				
	    				<field name="stateDesc" />	    				
	    				<field name="contactFee" />
	    				
	    				
	    				<!-- 转换字段，自动填充 -->
	    				<field name="rltPoNo"  mapping="poNo" />	
	    				<field name="price"    mapping="cprice" />
	    				<field name="priceTax" mapping="cpriceTax" />	
	    				<field name="cgstate"  mapping="stateDesc" />   
	    			</fields>
	    			<columns>
	    				<column header=""                   isCheck="true" />
	    				<column header="客户订单号"         dataIndex="poNo" />
	    				<column header="公司 P/N"           dataIndex="partNo" />
	    				<column header="客户 P/N"           dataIndex="cpartNo" />
	    				<column header="物料描述"           dataIndex="pnDesc" />
	    				<column header="特殊备注（批次）"   dataIndex="specDesc" />
	    				<column header="订单数量"           dataIndex="amount" />
	    				<column header="已出库数量"         dataIndex="deliveryAmount" />
	    				<column header="明细状态"         dataIndex="stateDesc" />
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