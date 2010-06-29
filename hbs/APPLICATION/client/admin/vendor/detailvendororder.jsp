<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>供应商收货明细</title>
</head>

<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
				    	<complexgrid id="querygrid" height="500" title="供应商收货明细列表" storeAutoLoad="true" frame="true" root="data.list" url="/invoice/VendorSettlement!listDetail.action?roleType=${param.roleType}&amp;settlement.commCode=${param.commCode}&amp;settlement.summery=${param.summery}&amp;settlement.settlementType=${param.settlementType}">
				    		<fields>
				    			<field name="recPoNo" />
				    			<field name="cpartNo" />
				    			<field name="partNo" />
				    			<field name="pnDesc" />
				    			<field name="price" />
				    			<field name="taxRate" />
				    			<field name="amount" />
				    			<field name="curMoney" />				    			
				    		</fields>
				    		<columns>
								<column header="收货单号"   dataIndex="recPoNo" />
				    			<column header="供应商物料"   dataIndex="cpartNo" />
				    			<column header="公司物料"   dataIndex="partNo" />
				    			<column header="物料描述" dataIndex="pnDesc" />
				    			<column header="物料单价"   dataIndex="price" />				    			
								<column header="税率"   dataIndex="taxRate" />
				    			<column header="数量"   dataIndex="amount" />
				    			<column header="金额"       dataIndex="curMoney" />				    			
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
<script type="text/javascript" src="<%=contextPath %>/vendor/detailvendororder.js"></script>