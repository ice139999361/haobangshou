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

				    	<complexgrid id="querygrid" title="客户信息列表" frame="true" page="true" root="data.list" url="/warehouseSend/warehouseSend!listDetail.action">
				    		<fields>
				    			<field name="custCode" />
				    			<field name="partNo" />
				    			<field name="custPartNo" />
				    			<field name="pnDesc" />
				    			<field name="sendPoNo" />
				    			<field name="specDesc" />
				    			<field name="amount" />
				    			<field name="curMoney" />				    			
				    			<field name="currMoney" />
				    			<field name="leftMoney" />
				    			<field name="invoiceDesc" />
				    			<field name="createTime" />	
				    			
									<!-- 转换字段，自动填充 -->
			    				<field name="poNo"  mapping="sendPoNo" />	
			    				<field name="poNoDate"    mapping="createTime" />
			    				<field name="ccode" mapping="custCode" />
			    				<field name="cpartNo"  mapping="custPartNo" /> 
			    				<field name="allMoney"  mapping="curMoney" />	    				
				    		</fields>
				    		
				    		<columns>
				    			<column header="客户编码"          dataIndex="custCode" />
				    			<column header="物料编号"      dataIndex="partNo" />
				    			<column header="发货单号"      dataIndex="sendPoNo" />
				    			<column header="发货数量"      dataIndex="amount" />
				    			<column header="发货金额"          dataIndex="curMoney" />
				    			<column header="本次开票金额"  dataIndex="currMoney" xtype="textfield" />
				    			<column header="剩余金额"      dataIndex="leftMoney" xtype="textfield" />
				    			<column header="发票备注"          dataIndex="invoiceDesc" xtype="textfield" />
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