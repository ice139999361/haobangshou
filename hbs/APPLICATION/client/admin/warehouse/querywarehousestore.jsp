<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>查询库存</title>
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
			    		<queryform gridId="querygrid" exportId="exportBtn">
			    			<layoutpanel columnNum="3">
			    				<dictcombo fieldLabel="仓库类型"         hiddenName="vInfo.houseType"  paramsValue="WAREHOUSE_TYPE"  showText="请选择" />
			    				<dictcombo fieldLabel="用途"             hiddenName="vInfo.houseUse"  paramsValue="WAREHOUSE_USE"  showText="请选择" />
			    					
			    				<textfield fieldLabel="公司物料编码"     name="vInfo.partNo"  />
			    				<textfield fieldLabel="供应商编码"       name="vInfo.vendorCode" />
			    				
			    				<textfield fieldLabel="供应商物料编码"   name="custOrder.cpartNo"  />	
			    				<textfield fieldLabel="客户编码"         name="vInfo.custCode" />
			    			</layoutpanel>
			    		</queryform>
			    	
				    	<complexgrid id="querygrid" title="库存列表" frame="true" page="true" root="data.list">
				    		<fields>
				    			<field name="houseTypeDesc" />
				    			<field name="houseUseDesc" />
				    			<field name="partNo" />
				    			<field name="vendorCode" />
				    			<field name="cpartNo" />
				    			<field name="custCode" />
				    			<field name="pnDesc" />
				    			<field name="totalAmount" />
				    			<field name="useAmount" />
				    		</fields>
				    		<columns>
				    			<column header="仓库类型"   dataIndex="houseTypeDesc" />
				    			<column header="用途"       dataIndex="houseUseDesc" />
				    			<column header="GLE P/N"    dataIndex="partNo" />
				    			<column header="供应商编码" dataIndex="vendorCode" />
				    			<column header="供应商 P/N" dataIndex="cpartNo" />
				    			<column header="客户编码"   dataIndex="custCode" />
				    			<column header="物料描述"   dataIndex="pnDesc" />
				    			<column header="库存数量"   dataIndex="totalAmount" />
				    			<column header="可用数量"   dataIndex="useAmount" />
				    		</columns>
				    	</complexgrid>

							<panel buttonAlign="center">
								<buttons>
				    			<button text="导出" id="exportBtn" />
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
<script type="text/javascript" src="<%=contextPath %>/warehouse/querywarehousestore.js"></script>