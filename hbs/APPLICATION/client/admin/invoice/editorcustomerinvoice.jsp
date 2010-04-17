<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>客户发票新增</title>
	<script type="text/javascript" src="<%=contextPath %>/invoice/component/customerinvoicecommonpro.js"></script>
</head>

<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
			    		
			
			    		<queryform gridId="querygrid" itemsFun="addQueryformFun" />

				    	<complexgrid id="querygrid" title="客户信息列表" frame="true" page="true" root="data.list" url="/custOrder/custOrderScMgr!list.action">
				    		<fields>
				    			<field name="l1" />
				    			<field name="l2" />
				    			<field name="l3" />
				    			<field name="l4" />
				    			<field name="l5" />
				    			<field name="l6" />
				    		</fields>
				    		
				    		<columns>
				    			<column header="编码"          dataIndex="l1" />
				    			<column header="物料编号"      dataIndex="l2" />
				    			<column header="收货单号"      dataIndex="l2" />
				    			<column header="发货数量"      dataIndex="l3" />
				    			<column header="金额"          dataIndex="l5" />
				    			<column header="本次开票金额"  dataIndex="l6" xtype="textfield" />
				    			<column header="剩余金额"      dataIndex="l6" xtype="textfield" />
				    			<column header="发票"          dataIndex="l6" xtype="textfield" />
				    			<column header="操作"          dataIndex=""   id="operator"     width="120" />
				    		</columns>
				    	</complexgrid>
				    	
				    	<panel buttonAlign="center">
			    			<buttons>
			    				<button text="提交" id="submitBtn" />
			    				<button text="取消" id="backBtn"   />
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
<script type="text/javascript" src="<%=contextPath %>/invoice/editorcustomerinvoice.js"></script>