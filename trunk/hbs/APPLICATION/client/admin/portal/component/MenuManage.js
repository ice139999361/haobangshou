Ext.namespace("ExtUx.widget.portal");
/**
 * 菜单管理器
 */
ExtUx.widget.portal.MenuManage=function(portal, menu){
    this.portal=portal;
    this.menu=menu;
};
ExtUx.widget.portal.MenuManage.prototype={
    locate:function(url){
			this.menu.locate(url);
    },
    setHandler:function(handler){
			this.menu.setHandler(handler);
    },
    refresh:function(){
        this.menu.refresh();
    }
};