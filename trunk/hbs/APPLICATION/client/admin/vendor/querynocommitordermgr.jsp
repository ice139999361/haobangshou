<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>供应商未交货订单明细</title>
</head>

<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
			    		<queryform gridId="querygrid">
			    			<layoutpanel columnNum="3">
			    				<textfield fieldLabel="供应商简称"           name="vorderDetail.shortName"  />
			    				<textfield fieldLabel="供应商编码"           name="vorderDetail.commCode"  />
			    				<textfield fieldLabel="供应商订单号"         name="vorderDetail.poNo" />
			    			</layoutpanel>
			    			<layoutpanel columnNum="6:.333,.10,.05,.15,.05,.3">
									<dictcombo   fieldLabel="结算类型"               hiddenName="vorderDetail.settlementType"            labelStyle="width:150" paramsValue="SETTLEMENT_TYPE" emptyText="请选择" id="vSettlementType" />
			    				
									<label fieldLabel="交货时间" />
									<label fieldLabel="从" labelSeparator="" />
									<datefield hideLabel="true" name="vorderDetail.dynamicFields.likeBegainTime" format="Y-m-d" width="120" />
									<label fieldLabel="到" labelSeparator="" />
									<datefield hideLabel="true" name="vorderDetail.dynamicFields.likeEndTime" format="Y-m-d" width="120" />
			    			</layoutpanel>
			    		</queryform>
				    		
				    		
				    	<complexgrid id="querygrid" title="未发货订单列表" frame="true" page="true" root="data.list" url="/vendorOrderDetail/orderDetailCg!listNoCommitMgr.action">
				    		<fields>
				    			<field name="shortName" />
				    			<field name="commCode" />
				    			<field name="poNo" />
				    			<field name="cpartNo" />
				    			<field name="partNo" />
				    			<field name="pnDesc" />
				    			<field name="verDeliveryDate" />
				    			<field name="amount" />
				    			<field name="deliveryAmount" />
				    			<field name="specDesc" />
				    		</fields>
				    		<columns>
				    			<column header="供应商简称"   dataIndex="shortName" />
				    			<column header="供应商编码"   dataIndex="commCode" />
				    			<column header="供应商订单号" dataIndex="poNo" />
				    			<column header="供应商物料"   dataIndex="cpartNo" />
				    			<column header="公司物料"   dataIndex="partNo" />
				    			<column header="物料描述"   dataIndex="pnDesc" />
				    			<column header="交期"       dataIndex="verDeliveryDate" />
				    			<column header="订单数量"   dataIndex="amount" />
				    			<column header="已交货数量" dataIndex="deliveryAmount" />
				    			<column header="特殊备注"   dataIndex="specDesc" />
				    		</columns>
				    	</complexgrid>
			    	<!-- service ext ui.  end. -->
			    	</items>
			    </panel>
			  </items>
			</viewport>
		</application>
	</xmp>
</body>
</html>
<script type="text/javascript" src="<%=contextPath %>/vendor/querynocommitorder.js"></script>