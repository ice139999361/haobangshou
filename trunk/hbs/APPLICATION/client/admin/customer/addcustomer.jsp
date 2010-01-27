<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新客户信息录入</title>
	<script type="text/javascript" src="<%=contextPath %>/customer/common/CommonPro.js"></script>
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
				    		<listpanel frame="true" title="客户基本信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<textfield fieldLabel="客户简称" 							 name="" labelStyle="width:150" allowBlank="false"/>
				    				<textfield fieldLabel="公司中文名称" 					 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="客户公司地址" 					 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="性质" 									 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="客户公司网址" 					 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="客户纳税人识别号" 	 		 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="客户信用度" 						 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="客户的重要程度" 				 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="客户结算类型" 					 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="销售人员ID" 						 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="客户备注" 							 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="合同费" 								 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="对应的业务部助理ID" 		 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="发货单是否显示单价" 		 name="" labelStyle="width:150" />
				    				
				    				
				    				<textfield fieldLabel="英文简称" 							 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="公司英文名称" 					 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="公司英文地址" 					 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="规模" 									 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="客户法人代表" 					 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="对应的分公司或分支机构" name="" labelStyle="width:150" />
				    				<textfield fieldLabel="信用等级描述" 					 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="客户重要程度描述" 			 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="客户结算币种" 					 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="销售人员名字" 					 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="供应商编码" 						 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="交易税率" 							 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="对应的业务部助理姓名" 	 name="" labelStyle="width:150" />
				    			</layoutpanel>
				    		</listpanel>
				    		
				    		<listpanel frame="true" title="客户结算信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<textfield fieldLabel="结算方式描述" 					 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="账期类型" 							 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="账期的起始日" 					 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="账期的结算日" 					 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="提醒设置" 							 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="提醒市场人员催款" 			 name="" labelStyle="width:150" />
				    				
				    				
				    				<textfield fieldLabel="结算币种描述" 					 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="客户的账期设置" 				 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="账期的对账日" 					 name="" labelStyle="width:150" />
				    				<textfield fieldLabel="客户账期的最大交易金额" name="" labelStyle="width:150" />
				    				<textfield fieldLabel="预付百分比" 						 name="" labelStyle="width:150" />
				    			</layoutpanel>
				    		</listpanel>
			    		</items></form>
			    		
			    		<complexgrid id="contactgrid" title="客户联系人信息" itemsFun="contactFun" itemsAlign="after" frame="true" height="200" deftbar="true" url="1">
			    			<fields>
			    				<field name="l11" />
			    			</fields>
			    			
			    			<columns>
			    				<column header="是否是主联系人" dataIndex="l11" xtype="textfield"/>
			    			</columns>
			    			
			    			<submitFields value="l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11" />
			    		</complexgrid>
			    		
			    		<complexgrid id="consigneegrid" title="客户收货人信息" itemsFun="contactFun" itemsAlign="after" frame="true" height="200" deftbar="true" url="1">
			    			<fields>
			    				<field name="l11" />
			    				<field name="l12" />
			    				<field name="l13" />
			    			</fields>
			    			
			    			<columns>
			    				<column header="收货地址" 			dataIndex="l11" xtype="textfield"/>
			    				<column header="收货邮编" 			dataIndex="l12" xtype="textfield"/>
			    				<column header="是否是主收货人" dataIndex="l13" xtype="textfield"/>
			    			</columns>
			    		</complexgrid>
			    		
			    		<complexgrid id="custbankgrid" title="客户银行信息" itemsFun="custbankFun" frame="true" height="200" deftbar="true" url="1" />
			    		
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
<script type="text/javascript" src="<%=contextPath %>/customer/addcustomer.js"></script>