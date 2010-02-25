<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>供应商P/N对照查询</title>
	<script type="text/javascript" src="<%=contextPath %>/vendor/component/pncommonpro.js"></script>
</head>

<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
			    		<queryform gridId="querygrid" exportId="exportBtn" itemsFun="queryformFun" />
				    		
				    	<complexgrid id="querygrid" title="客户P/N对照列表" frame="true" page="true" root="data.list" itemsFun="complexgridFun" />
			    		
			    		<panel buttonAlign="center">
			    			<buttons>
			    				<button text="删除" id="deleteBtn" />
			    				<button text="恢复" id="reBtn"   />
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
<script type="text/javascript" src="<%=contextPath %>/vendor/pnquery.js"></script>