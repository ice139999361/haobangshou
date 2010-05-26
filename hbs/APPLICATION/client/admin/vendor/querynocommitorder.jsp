<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>供应商未提交货订单</title>
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
			    				<textfield fieldLabel="供应商简称"           name="custOrder.commCode"  />
			    				<textfield fieldLabel="供应商编码"           name="custOrder.poNo"  />
			    				<textfield fieldLabel="供应商订单号"         name="" />
			    			</layoutpanel>
			    			<layoutpanel columnNum="6:.333,.10,.05,.15,.05,.3">
			    				<textfield fieldLabel="结算类型"           name="custOrder.commCode"  />
									<label fieldLabel="订单时间" />
									<label fieldLabel="从" labelSeparator="" />
									<datefield hideLabel="true" name="" format="Y-m-d" width="120" />
									<label fieldLabel="到" labelSeparator="" />
									<datefield hideLabel="true" name="" format="Y-m-d" width="120" />
			    			</layoutpanel>
			    		</queryform>
				    		
				    		
				    	<complexgrid id="querygrid" title="未发货订单列表" frame="true" page="true" root="data.list" url="xxx">
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
				    			<field name="l10" />
				    		</fields>
				    		<columns>
				    			<column header="供应商简称"   dataIndex="l1" />
				    			<column header="供应商编码"   dataIndex="l2" />
				    			<column header="供应商订单号" dataIndex="l3" />
				    			<column header="供应商物料"   dataIndex="l4" />
				    			<column header="公司物料"   dataIndex="l5" />
				    			<column header="物料描述"   dataIndex="l6" />
				    			<column header="交期"       dataIndex="l7" />
				    			<column header="订单数量"   dataIndex="l8" />
				    			<column header="已发货数量" dataIndex="l9" />
				    			<column header="特殊备注"   dataIndex="l10" />
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