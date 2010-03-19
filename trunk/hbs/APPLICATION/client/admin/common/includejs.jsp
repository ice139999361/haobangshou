<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 获取项目根路径
	String contextPath = request.getContextPath();
	// 获取项目样式路径
	//String css = (String)request.getSession().getAttribute("css");
	String css  = "blue";
	
	// 服务器路径
	// final String  SERVER_PATH = contextPath + "/test";
	final String  SERVER_PATH = "/server";
	// 菜单的地址
	final String  MENU_PATH = contextPath + "/config/menu/admin.json";
	// 系统首页
	final String  MAIN_PATH = contextPath + "/portal/homepage.jsp";
%>

<script type="text/javascript">
	// 定义要用到的公共常量变量
	// 项目根路径
	var CONTEXT_PATH = "<%=contextPath %>";
	// 服务器路径
	SERVER_PATH = "<%=SERVER_PATH %>";
	// - 
	
	// 添加需要用到的样式表
	document.writeln('<link rel="stylesheet" type="text/css" href="<%=contextPath %>/ext/css/ext-all.css" />');
	document.writeln('<link rel="stylesheet" type="text/css" href="<%=contextPath %>/rtplt/css/rtplt.css"/>');
	document.writeln('<link rel="stylesheet" type="text/css" href="<%=contextPath %>/dmplt/css/dmplt.css"/>');
	document.writeln('<link rel="stylesheet" type="text/css" href="<%=contextPath %>/ext/css/common.css"/>');
	document.writeln('<link rel="stylesheet" type="text/css" href="<%=contextPath %>/ext/css/xtheme-<%=css %>.css"/>');
	document.writeln('<link rel="stylesheet" type="text/css" href="<%=contextPath %>/ext/ux/css/ux-all.css"/>');
	
	
	// 添加 ExtJs 组件的 JavaScript 代码
	document.writeln('<script type="text/javascript" src="<%=contextPath %>/ext/ext-base.js"><\/script>');
	document.writeln('<script type="text/javascript" src="<%=contextPath %>/ext/ext-all-debug.js"><\/script>');
	document.writeln('<script type="text/javascript" src="<%=contextPath %>/ext/Panel.js"><\/script>');
	document.writeln('<script type="text/javascript" src="<%=contextPath %>/ext/ext-lang-zh_CN.js"><\/script>');
	document.writeln('<script type="text/javascript" src="<%=contextPath %>/ext/ux/ux-all.js"><\/script>');
	
	// 添加 Rtplt 组件的 JavaScript 代码
	document.writeln('<script type="text/javascript" src="<%=contextPath %>/rtplt/rtpltjs-all.js"><\/script>');
	
	// 添加应用级别的帮助工具类
	document.writeln('<script type="text/javascript" src="<%=contextPath %>/common/HBSConvertHelper.js"><\/script>');
	
	// 添加公共校验脚本
	document.writeln('<script type="text/javascript" src="<%=contextPath %>/common/VType.js"><\/script>');
	
	// 添加 Rtplt 组件的 JavaScript 代码
	document.writeln('<script type="text/javascript" src="<%=contextPath %>/dmplt/dmpltjs-all.js"><\/script>');
</script>
