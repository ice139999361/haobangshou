Ext.namespace("ExtUx.widget.portal");

// 内容管理
ExtUx.widget.portal.ContentManage = function(portal,mainPanel){
    this.portal=portal;
    this.mainPanel=mainPanel;
};

ExtUx.widget.portal.ContentManage.prototype = {
    //如果createNew为true，则新开一个页签加载src。
    //否则在当前页签加载src
    switchContent:function(url, isNewOpen, tabTip){
        // 如果请求地址以 / 开头，则加入项目路径
        if(url.indexOf("/") == 0) url = CONTEXT_PATH + url;
        // 
        this.mainPanel.loadPage(url, isNewOpen, tabTip);
        // 设置当前访问的 URL
        this.__currentUrl__ = url;
    },
    getContent:function(){
        return this.__currentUrl__;
    },
    closeActiveTab:function(){
        var tab = this.mainPanel.getActiveTab();
        this.mainPanel.remove(tab);
    },
    closeTab:function(tab){
        this.mainPanel.remove(tab);
    },
    reloadTab:function(tabId){
        var tab = this.mainPanel.getItem(tabId);
        this.mainPanel.setActiveTab(tab);
        tab.iframe.dom.contentWindow.location.reload();
    },
    getActiveTab:function(){
        return this.mainPanel.getActiveTab();
    },
    getParentId:function(){
        return this.getActiveTab().parentId;
    },
    reflashGrid:function(gridId, tabId){

        if(!tabId) {
            tabId = this.getParentId();
        }
        this.closeActiveTab();
        var tab = this.mainPanel.getItem(tabId);
        if(tab) {
            this.mainPanel.setActiveTab(tab);
            var grid = tab.iframe.dom.contentWindow.Ext.getCmp(gridId);
            if(grid) {
                if(grid.bbar) {
                    var tmpBtns = grid.bbar.dom.getElementsByTagName("button");
                    tmpBtns[tmpBtns.length-1].click();
                } else {
                    grid.store.reload();
                }
            }
        }

    }
};