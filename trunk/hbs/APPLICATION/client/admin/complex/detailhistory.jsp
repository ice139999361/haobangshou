<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>历史变更查看</title>
</head>

<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
				    	<complexgrid autoExpandColumn="operContent" storeAutoLoad="true" id="querygrid" title="历史变更列表" frame="true" root="data.list">
				    		<fields>
			    				<field name="operTime" />
			    				<field name="staffName" />
			    				<field name="operContent" />
			    			</fields>
			    			 
			    			<columns>
			    				<column header="操作时间" 		dataIndex="operTime"    width="150" />
			    				<column header="操作人" 			dataIndex="staffName"   width="150" />
			    				<column header="变动信息"     dataIndex="operContent" id="operContent" />
			    			</columns>
				    	</complexgrid>
				    	
				    	<panel buttonAlign="center">
			    			<buttons>
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
<script type="text/javascript" src="<%=contextPath %>/complex/detailhistory.js"></script>