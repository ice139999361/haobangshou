<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>本公司物料查询</title>
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
			    			<layoutpanel columnNum="2:.33,.33">
			    				<textfield fieldLabel="物料编码"   name="partNo.partNo"  />	
			    				<textfield fieldLabel="描述"       name="partNo.pnDesc"  />	
			    			</layoutpanel>
			    		</queryform>
			    	
				    	<complexgrid id="querygrid" title="本公司物料列表" frame="true" page="true" autoExpandColumn="cpnDesc" root="data.list" url="/customerInfo/custPartNoInfoMgr!list.action" >
				    		<fields>
				    			<field name="partNo"   />
				    			<field name="taxPrice" />
				    			<field name="price"    />
				    			<field name="clsCode"  />
				    			<field name="pnDesc"   />
				    			<field name="clsCodeDesc" />
				    		</fields>
				    		<columns>
				    			<column header="物料编码"   dataIndex="partNo"      id="cpartNo"  />
				    			<column header="含税价格"   dataIndex="taxPrice"    />
				    			<column header="价格"       dataIndex="price"       />
				    			<column header="所属类别"   dataIndex="clsCodeDesc" />
				    			<column header="物料描述"   dataIndex="cpnDesc"     id="cpnDesc"  />
				    			<column header="操作"       dataIndex=""            id="operator" width="180" />
				    		</columns>
				    	</complexgrid>
			    	<!-- service ext ui.  end. -->
			    	</items>
			    </panel>
			  </items>
			</viewport>
		</application>
	</xmp>
</body>
</html>
<script type="text/javascript" src="<%=contextPath %>/materiel/querymateriel.js"></script>