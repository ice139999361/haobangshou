<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<title>打印</title>
<script>
	//open(CONTEXT_PATH + "/print/" + urlPs.pageType + ".html", null, "width="+ screen.availWidth + ";height=" + screen.availHeight);
	//open(CONTEXT_PATH + "/print/" + urlPs.pageType + ".html");
	open(CONTEXT_PATH + "/print/" + urlPs.pageType + ".jsp");
</script>