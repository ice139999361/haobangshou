<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>客户发票管理</title>
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
			    		<panel>
			    			<buttons>
			    				<button text="新增" id="addBtn" />
			    			</buttons>
			    		</panel>
			    	
			    		<queryform gridId="querygrid" itemsFun="queryformFun" />

				    	<complexgrid id="querygrid" title="发票信息列表" frame="true" page="true" root="data.list" url="/custOrder/custOrderScMgr!list.action" itemsAlign="after" itemsFun="querygridFun">
				    		<columns>
				    			<column header="操作"      dataIndex=""  id="operator" width="170" />
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
<script type="text/javascript" src="<%=contextPath %>/invoice/customerinvoicemanager.js"></script>