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
			    			<complexgrid id="customerstoregrid" title="客户库存信息" frame="true" height="100" url="/custOrder/custOrderScMgr!list.action" root="data.list">
				    			<fields>
				    				<field name="commCode" />
				    				<field name="cpartno" />
				    				<field name="l3" />
				    				<field name="l4" />
				    				<field name="l5" />
				    				<field name="l6" />
				    				<field name="l7" />
				    				<field name="l8" />
				    				<field name="l9" />
				    			</fields>
				    			<columns>
				    				<column header="客户编码"           dataIndex="commCode" />
				    				<column header="客户型号"           dataIndex="cpartno" />
				    				<column header="描述"               dataIndex="l3" />
				    				<column header="数量"               dataIndex="l4" />
				    				<column header="本客户锁定数量"     dataIndex="l5" />
				    				<column header="通用锁定数量"       dataIndex="l6" />
				    				<column header="需备货数量"         dataIndex="l7" />
				    			</columns>
				    		</complexgrid>
	    		
				    		<listpanel frame="true" title="库存操作" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="3:.2,.15,">
				    				<label fieldLabel="本客户库存"           name="custOrder.receiveAddress" id="selfstore" />
				    				<label fieldLabel="通用库存"             name="custOrder.receiveZip"	   id="commstore" />
				    				
				    				<textfield hideLabel="true" name="self.useAmount"   id="tsuserAmount" relate="selfstore|;&lt;=|;必须小于等于本客户库存量" vtype="commCheck" />
				    				<textfield hideLabel="true" name="common.useAmount" id="tcuserAmount" relate="commstore|;&lt;=|;必须小于等于通用库存量"   vtype="commCheck" />
				    					
				    				<button text="锁定" minWidth="80" id="sstockBtn" url="/success.action" cmpt="tsuserAmount" />
				    				<button text="锁定" minWidth="80" id="cstockBtn" url="/success.action" cmpt="tcuserAmount" />
				    			</layoutpanel>	
				    		</listpanel>
				    		   
				    		<complexgrid id="storeinfogrid" title="详细信息" frame="true" height="100" url="/custOrder/custOrderScMgr!list.action" root="data.list">
				    			<fields>
				    				<field name="custCode" />
				    				<field name="useAmount" />
				    			</fields>
				    			<columns>
				    				<column header="客户编码"           dataIndex="custCode"  width="250" />
				    				<column header="可用数量"           dataIndex="useAmount" width="250" />
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