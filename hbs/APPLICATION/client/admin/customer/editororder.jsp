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
				    				<autocomplete fieldLabel="客户编码"           name="custInfo.commCode"       labelStyle="width:150"/>
				    				<textfield    fieldLabel="客户订单号"         name=""                        labelStyle="width:150"/>
				    				<textfield    fieldLabel="选择联系人"         name=""                        labelStyle="width:150"/>
				    				<label        fieldLabel="对应分公司"         name=""                        labelStyle="width:150"/>
				    				<label        fieldLabel="电话"               name=""                        labelStyle="width:150"/>
				    				<label        fieldLabel="收货地址"           name=""                        labelStyle="width:150"/>
				    					
				    				
				    				<autocomplete fieldLabel="客户简称"           name=""                        labelStyle="width:150"/>
				    				<textfield    fieldLabel="客户订单日期"       name=""                        labelStyle="width:150"/>
				    				<textfield    fieldLabel="选择收货人"         name=""                        labelStyle="width:150"/>
				    				<label        fieldLabel="结算类型"           name=""                        labelStyle="width:150"/>
				    				<label        fieldLabel="传真"               name=""                        labelStyle="width:150"/>
				    				<label        fieldLabel="邮编"               name=""                        labelStyle="width:150"/>
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
			    				<column header="货品名称"       dataIndex="conAddress" />
			    				<column dataIndex="conZip"      xtype="autocomplete"      header="客户型号&lt;font color=red&gt;*&lt;/font&gt;"    />
			    				<column dataIndex="isPrimary"   xtype="autocomplete"      header="GLE型号&lt;font color=red&gt;*&lt;/font&gt;"     />
			    				<column header="描述"           dataIndex="isPrimary"  />
			    				<column header="单价"           dataIndex="isPrimary"  />
			    				<column header="税率"           dataIndex="isPrimary"  />
			    				<column dataIndex="isPrimary"   xtype="dictcombo"         header="是否含税交易&lt;font color=red&gt;*&lt;/font&gt;"/>
			    				<column dataIndex="isPrimary"   xtype="textfield"         header="数量&lt;font color=red&gt;*&lt;/font&gt;"        />
			    				<column header="金额"           dataIndex="isPrimary"  />
			    				<column dataIndex="isPrimary"   xtype="dictcombo"         header="交货日期&lt;font color=red&gt;*&lt;/font&gt;"    />
			    				<column dataIndex="isPrimary"   xtype="textfield"         header="特殊备注&lt;font color=red&gt;*&lt;/font&gt;"    />
			    				<column dataIndex="isPrimary"   xtype="textfield"         header="备注&lt;font color=red&gt;*&lt;/font&gt;"        />
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