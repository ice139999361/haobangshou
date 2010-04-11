<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<script type="text/javascript" src="<%=contextPath %>/warehouse/component/warehouseincommonpro.js"></script>
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
				    		<listpanel frame="true" title="入库基本信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<textfield    fieldLabel="入库单号"       id="txtRecPoNo" name="warehouseRec.recPoNo"        labelStyle="width:150"     emptyText="供应商的送货单号" />
				    				<datefield    fieldLabel="供应商单据日期" name="warehouseRec.poNoDate"    id="acPoNoDate"    labelStyle="width:150"     emptyText="送货单的日期" format="Y-m-d" />
				    				<textfield    fieldLabel="供应商"      id="txtVendorCode"   name="warehouseRec.vendorCode"       labelStyle="width:150"  />
				    				<!--<autocomplete fieldLabel="供应商" url="/customerInfo/customerInfo!list.action"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"     name="custInfo.commCode"       labelStyle="width:150" allowBlank="false" id="acCommCode" />
				    				 -->
				    				
				    				<datefield    fieldLabel="入库日期"  	    name="warehouseRec.operTime"        labelStyle="width:150"     format="Y-m-d" id="acOperTime" />
				    				<datefield    fieldLabel="到货日期"  	    name="warehouseRec.applyDate"        labelStyle="width:150"     format="Y-m-d" id="acApplyDate"/>
				    				
				    				<dictcombo    fieldLabel="仓库"           hiddenName="warehouseRec.houseType"  labelStyle="width:150"     paramsValue="COMPANY_BRANCH"     emptyText="请选择" />
				    				
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<textarea     fieldLabel="备注"           name="warehouseRec.receiveDesc"        labelStyle="width:150" width="600" />
				    			</layoutpanel>	
				    			<layoutpanel columnNum="1">				    				
				    				<hidden name="warehouseRec.shortName" />
				    				<hidden name="warehouseRec.settlementType" />
				    				<hidden name="warehouseRec.poNoType" />						    							    				
				    			</layoutpanel>
				    		</listpanel>
			    		</items></form>
			    		
			    		<complexgrid id="warehousegrid" frame="true" height="200" deftbar="false" url="1" title="入库详情" itemsFun="warehousegridFun" >
				    		<fields>
				    				<field name="taxRate" />
				    				<field name="isTax" />	
				    				<field name="priceTax" />
				    				<field name="price" />	
				    				<field name="rltPoNo" />
				    				<field name="recDetailSeqId" />
				    				<field name="poNoType" />			
				    				<field name="activeState" />
				    				<field name="state" />		    							    					    				
				    		</fields>				    		
			    		</complexgrid>
			    		<panel buttonAlign="center">
			    			<buttons>
			    				<button text="添加入库详情" id="addInfoBtn" />
			    				<button text="提交"         id="submitBtn" />
			    				<button text="保存"         id="saveBtn"   />
			    				<button text="取消"         id="backBtn"   />
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
	    					
	    				<textfield fieldLabel="公司 P/N"         name="" />
	    				<textfield fieldLabel="特殊备注"           name="" />
	    			</layoutpanel>
	    		</queryform>
	    		
	    		<complexgrid id="querygrid" buttonAlign="center" title="订单详情" frame="true" height="300" url="/custOrder/custOrderScMgr!list.action" page="true" root="data.list">
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
	    			</fields>
	    			<columns>
	    				<column header=""                   isCheck="true" />
	    				<column header="采购单号"           dataIndex="l1" />
	    				<column header="公司 P/N"           dataIndex="l2" />
	    				<column header="供应商 P/N"         dataIndex="l3" />
	    				<column header="物料描述"           dataIndex="l4" />
	    				<column header="特殊备注（批次）"   dataIndex="l5" />
	    				<column header="采购数量"           dataIndex="l6" />
	    				<column header="已入库数量"         dataIndex="l7" />
	    			</columns>
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
<script type="text/javascript" src="<%=contextPath %>/warehouse/editorwarehousein.js"></script>