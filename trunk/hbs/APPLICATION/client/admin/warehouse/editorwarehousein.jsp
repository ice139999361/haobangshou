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
				    				<textfield    fieldLabel="入库单号"       id="txtRecPoNo" name="warehouseRec.recPoNo"        labelStyle="width:150"     emptyText="供应商的送货单号" allowBlank="false" />
				    				<datefield    fieldLabel="供应商单据日期" name="warehouseRec.poNoDate"    id="acPoNoDate"    labelStyle="width:150"     emptyText="送货单的日期" format="Y-m-d" />
				    				<!--<textfield    fieldLabel="供应商"      id="txtVendorCode"   name="warehouseRec.vendorCode"       labelStyle="width:150"  />
									 -->
				    				<autocomplete fieldLabel="供应商" url="/vendorInfo/vendorInfo!listDict.action"  displayField="commCode"  valueField="commCode" queryParam="vendorInfo.commCode"     name="warehouseRec.vendorCode"       labelStyle="width:150" allowBlank="false" id="acCommCode" />


				    				<!--<datefield    fieldLabel="入库日期"  	    name="warehouseRec.operTime"        labelStyle="width:150"     format="Y-m-d" id="acOperTime" />-->
				    				<datefield    fieldLabel="到货日期"  	    name="warehouseRec.applyDate"        labelStyle="width:150"     format="Y-m-d" id="acApplyDate" allowBlank="false" />

				    				<dictcombo    fieldLabel="仓库" id="aWarehouseType" url="/warehouse/warehouseAddr!list.action" record="id,name,conName,address,zip,isPrimary"	root="data.list"	valueField="id"	displayField="name"          hiddenName="warehouseRec.houseType"  labelStyle="width:150"     emptyText="请选择" />

				    			</layoutpanel>
								<layoutpanel columnNum="1">
				    				<textarea     fieldLabel="收货地址" id="aAddress"           name="warehouseSend.receiveAddress"        labelStyle="width:150" width="600" height="25"/>
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<textarea     fieldLabel="备注"           name="warehouseRec.receiveDesc"        labelStyle="width:150" width="600" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<hidden name="warehouseRec.shortName" id="hidShortName" />
				    				<hidden name="warehouseRec.settlementType" id="hidSettlementType" />
				    				<hidden name="warehouseRec.poNoType" />
									<hidden name="warehouseRec.activeState" />
									<hidden name="warehouseRec.state" />
									<hidden name="warehouseRec.operId" />
									<hidden name="warehouseRec.operStaff" />
									<hidden name="warehouseRec.financeState" />
				    			</layoutpanel>
				    		</listpanel>
			    		</items></form>

			    		<complexgrid id="warehousegrid" frame="true" height="200" deftbar="false" url="1" title="入库详情" itemsFun="warehousegridFun" />

			    		<panel buttonAlign="center">
			    			<buttons>
			    				<button text="添加入库详情" id="addInfoBtn" />
			    				<button text="提交"         id="submitBtn" />
			    				<button text="保存"         id="saveBtn"   />
			    				<button text="取消"         id="backBtn"   />
			    			</buttons>
			    		</panel>

			    	<!-- service ext ui.  end. -->
			    	</items>
			    </panel>
			  </items>
			</viewport>
			<window id="selectWindow" title="查询供应商采购单" width="900" closeAction="hide">
				<items>
					<queryform gridId="querygrid">
	    			<layoutpanel columnNum="3">
	    				<textfield fieldLabel="供应商编码"           name="orderDetail.commCode" id="acVendorCode" readOnly="yes" />
	    				<textfield fieldLabel="供应商 P/N"           name="orderDetail.cpartNo" />
	    				<textfield fieldLabel="供应商PO/N"           name="orderDetail.poNo"  />

	    				<textfield fieldLabel="公司 P/N"             name="orderDetail.partNo" />
	    				<textfield fieldLabel="特殊备注"             name="orderDetail.specDesc" />
	    			</layoutpanel>
	    		</queryform>

	    		<complexgrid id="querygrid" buttonAlign="center" title="供应商订单详情" frame="true" height="300" url="/vendorOrderDetail/orderDetail!listDetail.action" page="true" root="data.list">
	    			<fields>
	    				<field name="poNo" />
	    				<field name="partNo" />
	    				<field name="cpartNo" />
	    				<field name="pnDesc" />
	    				<field name="specDesc" />
	    				<field name="amount" />
	    				<field name="deliveryAmount" />
	    				<field name="taxRate" />
	    				<field name="isTax" />
	    				<field name="cprice" />
	    				<field name="cpriceTax" />
	    				<field name="settlementType" />
	    				<field name="specDesc" />
	    				<field name="poNoType" />
	    				<field name="activeState" />
	    				<field name="stateDesc" />

	    				<!-- 转换字段，自动填充 -->
	    				<field name="orderSeqId" mapping="operSeqId" />
	    				<field name="rltPoNo"  mapping="poNo" />
	    				<field name="price"    mapping="cprice" />
	    				<field name="priceTax" mapping="cpriceTax" />
	    				<field name="cgstate"  mapping="stateDesc" />
	    			</fields>
	    			<columns>
	    				<column header=""                   isCheck="true" />
	    				<column header="采购单号"           dataIndex="poNo" />
	    				<column header="公司 P/N"           dataIndex="partNo" />
	    				<column header="供应商 P/N"         dataIndex="cpartNo" />
	    				<column header="物料描述"           dataIndex="pnDesc" />
	    				<column header="特殊备注（批次）"   dataIndex="specDesc" />
	    				<column header="采购数量"           dataIndex="amount" />
	    				<column header="已入库数量"         dataIndex="deliveryAmount" />
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
<script type="text/javascript" src="<%=contextPath %>/warehouse/editorwarehousein.js"></script>