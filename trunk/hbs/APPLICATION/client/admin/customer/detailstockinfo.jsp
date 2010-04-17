<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>查看库存信息</title>
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
			    			<complexgrid id="orderdetailgrid" title="客户订单详情信息" frame="true" height="100" url="/custOrder/custOrderScMgr!list.action" root="data.list">
				    			<fields>
				    				<field name="commCode" />
				    				<field name="cpartNo" />
				    				<field name="partNo" />
				    				<field name="pnDesc" />
				    				<field name="amount" />
				    				<field name="deliveryAmount" />
				    				<field name="selfLockAmount" />
				    				<field name="commLockAmount" />
				    				<field name="needAmount" />
				    				<field name="vendorCode" />
				    				<field name="poNo" />
				    			</fields>
				    			<columns>
				    				<column header="客户编码"           dataIndex="commCode" />
				    				<column header="客户型号"           dataIndex="cpartNo" />
				    				<column header="本公司型号"			dataIndex="partNo" />
				    				<column header="描述"               dataIndex="pnDesc" />
				    				<column header="数量"               dataIndex="amount" />
				    				<column header="已发送数量"         dataIndex="deliveryAmount" />
				    				<column header="本客户锁定数量"     dataIndex="selfLockAmount" />
				    				<column header="通用锁定数量"       dataIndex="commLockAmount" />
				    				<column header="需备货数量"         dataIndex="needAmount" />
				    				<column header="供应商编码"         dataIndex="vendorCode" />
				    			</columns>
				    		</complexgrid>
	    		
				    		<listpanel frame="true" title="库存操作" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2:.2,.15,">
				    				<label fieldLabel="本客户库存"           name="self.useAmount" id="selfstore" />
				    				<label fieldLabel="通用库存"             name="common.useAmount"	   id="commstore" />
				    				
				    				<textfield hideLabel="true" name="self.lockAmount"   id="tsuserAmount" relate="selfstore|;&lt;=|;必须小于等于本客户库存量" vtype="commCheck" />
				    				<textfield hideLabel="true" name="common.lockAmount" id="tcuserAmount" relate="commstore|;&lt;=|;必须小于等于通用库存量"   vtype="commCheck" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1" buttonAlign="center">
				    					<button text="锁定" minWidth="80" id="sstockBtn" url="/custOrderDetail/orderDetailCg!lockAmount.action" />
				    			</layoutpanel>	
				    		</listpanel>
				    		   
				    		<complexgrid id="storeinfogrid" title="其他客户库存" frame="true" height="100" url="/">
				    			<fields>
				    				<field name="custCode" />
				    				<field name="useAmount" />
				    				<field name="houseType" />
				    				<field name="vendorCode" />
				    			</fields>
				    			<columns>
				    				<column header="客户编码"           dataIndex="custCode"  width="250" />
				    				<column header="可用数量"           dataIndex="useAmount" width="250" />
				    				<column header="仓库"           dataIndex="houseTypeDesc" width="250" />
				    				<column header="供应商编码"           dataIndex="vendorCode" width="250" />
				    				<column header="操作"               dataIndex=""          id="operator" width="150" />
				    			</columns>
				    		</complexgrid>
				    			
			    		</items></form>
			    		
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
<script type="text/javascript" src="<%=contextPath %>/customer/detailstockinfo.js"></script>