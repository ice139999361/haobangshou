<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
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
				    		<listpanel frame="true" title="订单基本信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<textfield fieldLabel="客户编码"           name="custInfo.commCode"       labelStyle="width:150" allowBlank="false"/>
				    				<textfield fieldLabel="客户的采购单号"     name=""       labelStyle="width:150" allowBlank="false"/>
				    				<textfield fieldLabel="客户的采购单日期"   name=""       labelStyle="width:150" allowBlank="false"/>
				    				<textfield fieldLabel="选择收货人"         name=""       labelStyle="width:150" allowBlank="false"/>
				    			</layoutpanel>
				    		</listpanel>
			    		</items></form>
			    		
			    		<complexgrid id="ordergrid" frame="true" height="200" deftbar="true" url="1">
			    			<fields>
			    				<field name="conAddress" />
			    				<field name="conZip" />
			    				<field name="isPrimary" />
			    			</fields>
			    			
			    			<columns>
			    			  <column isCheck="true"          dataIndex="seqId"         />
			    				<column header="客户P/N"   dataIndex="conAddress" xtype="textfield" />
			    				<column header="本公司P/N" dataIndex="conZip"     xtype="textfield" />
			    				<column header="数量"      dataIndex="isPrimary"  xtype="textfield" />
			    				<column header="交货日期"  dataIndex="isPrimary"  xtype="textfield" />
			    				<column header="特殊备注"  dataIndex="isPrimary"  xtype="textfield" />
			    				<column header="备注"      dataIndex="isPrimary"  xtype="textfield" />
			    			</columns>
			    			
			    			<submitFields value="seqId,conName,conDuty,conTel,conMobile,conFax,conMail,conQq,conMsn,conOther,conAddress,conZip,isPrimary" />
			    		</complexgrid>
			    		
			    		<panel buttonAlign="center">
			    			<buttons>
			    				<button text="提交" id="submitBtn" />
			    				<button text="保存" id="saveBtn"   />
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
<script type="text/javascript" src="<%=contextPath %>/customer/editororder.js"></script>