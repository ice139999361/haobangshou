<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<%
	String logo = "hbs";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=contextPath %>/portal/css/theme.css"/>
	<title></title>
	<script src="./component/TopPanel.js"></script>
	<script src="./component/BottomPanel.js"></script>
	<script src="./component/ContentManage.js"></script>
	<script src="./component/MenuManage.js"></script>
	<script src="./component/MainPanel.js"></script>
</head>

<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="border">
			  <items>
			    <toppanel region="north" height="65" logoImage="<%=contextPath %>/portal/images/logo/<%=css %>/<%=logo %>.png">
						<tool id="themeImage" image="<%=contextPath %>/portal/images/theme<%=css %>.png" handler="themehandler"/>
						<tool image="<%=contextPath %>/portal/images/help<%=css %>.png" handler="helphandler"/>
				  	<tool image="<%=contextPath %>/portal/images/esc<%=css %>.png" handler="eschandler"/>
			    </toppanel>
					<treemenu id="menu" width="170" region="west" split="true" title=" " collapseMode="mini" collapsible="true" frame="true">
						<menutreeloader  displayIcon="false" dataUrl="<%=MENU_PATH%>" preloadChildren="true"/>
						<root xtype="asynctreenode" expanded="true" id="root" singleClickExpand="true" text="HBS"/>
					</treemenu>
					<mainpanel activeTab="0" id="content" region="center" enableTabScroll="true">
			    	<items>
			    		<iframepanel id="homepage" closable="false" title="待处理事项" defaultSrc="<%=MAIN_PATH %>"/>
			    	</items>
			    </mainpanel>
			    <bottompanel region="south" height="20" title="版权所有 xxxxxxxxxxxx"  url="http://www.hbs.com" />
			  </items>
			</viewport>
		</application>
	</xmp>
</body>
</html>
<script type="text/javascript">
	// 退出系统的方法
	function eschandler() {
		Ext.Msg.minWidth = 200;
		Ext.Msg.confirm("提示", "您确定要退出系统？", function(btn) {
			if(btn == "no") return;
			
			// 关闭页面
			window.opener = null;
			window.close();
		});
	}
	
	// 初始化页面
	var app = new ExtUx.widget.Application({xmpid: "config"});
	// 与 window 进行关联
	window.portal = app;
	
	// 渲染页面
	app.onReady(function() {
		// 初始化 Portal 框架页面
		function init() {
			// 初始化内容管理
			this.contentMgr = new ExtUx.widget.portal.ContentManage(this, Ext.getCmp("content"));
			// 初始化菜单控件
			this.menuMgr = new ExtUx.widget.portal.MenuManage(this, Ext.getCmp("menu"));
			// 设置菜单的单击事件
			this.menuMgr.setHandler(function(node) {
				app.contentMgr.switchContent(node.url, true, node.tabTip);
			});
		}
		
		// 调用页面初始化方法，作用域为 app
		init.apply(this);		
	});
</script>