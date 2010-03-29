<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<script type="text/javascript" src="<%=contextPath %>/vendor/component/ordercommonpro.js"></script>
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
				    			<layoutpanel columnNum="3:.3,.2,.5">
				    				<dictcombo    fieldLabel="供应商"	url=""  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"           name="custOrder.commCode"       labelStyle="width:150" />
				    				<button       text="查询客户订单" id="sxkhddBtn" />
				    				<textfield    fieldLabel="订单编号"         name="custOrder.poNo"          readOnly="true"              labelStyle="width:150"/>
				    			</layoutpanel>
				    			<layoutpanel columnNum="2">
				    				<label        fieldLabel="开单日期"                                 labelStyle="width:150"/>
				    				<label        fieldLabel="联系人"                                   labelStyle="width:150"/>
				    				<label        fieldLabel="传真"                                     labelStyle="width:150"/>
				    						
				    				<label        fieldLabel="对应分公司"                               labelStyle="width:150"/>
				    				<label        fieldLabel="联系电话"                                 labelStyle="width:150"/>
				    				<label        fieldLabel="月结方式"                                 labelStyle="width:150"/>
				    			</layoutpanel>
				    		</listpanel>
			    		</items></form>
			    		
			    		<complexgrid id="ordergrid" frame="true" height="300" deftbar="true" url="1" title="订单详情" itemsFun="ordergridFun" />
			    		
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
			<window id="selectWindow" title="查询客户订单" width="900" closeAction="hide">
				<items>
					<queryform gridId="querygrid">
	    			<layoutpanel columnNum="3">
	    				<textfield fieldLabel="客户编码"           name="custOrder.commCode"  />
	    				<textfield fieldLabel="客户 P/N"           name="" />
	    				<textfield fieldLabel="客户订单号"         name="custOrder.poNo"  />
	    					
	    				<textfield fieldLabel="本公司 P/N"         name="" />
	    				<textfield fieldLabel="特殊备注"           name="" />
	    			</layoutpanel>
	    		</queryform>
	    		
	    		<complexgrid id="querygrid" buttonAlign="center" title="订单详情" itemsFun="orderquerygridFun"  frame="true" height="300" url="/custOrder/custOrderScMgr!list.action" page="true" root="data.list">
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
<script type="text/javascript" src="<%=contextPath %>/vendor/editororder.js"></script>