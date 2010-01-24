Ext.namespace("ExtUx.widget.portal");

ExtUx.widget.portal.MainPanel = Ext.extend(Ext.TabPanel, {
    plugins : new Ext.ux.TabCloseMenu(),
    loadPage : function(href, isNewOpen, tabTip){
        var tab = this.getActiveTab();
        //如果tab是不可关闭的，不能这个tab上再加载
        if (tab && !isNewOpen && tab.closable) {
            tab.setTitle("加载...");
            tab.setSrc(href);
        } else {
            var tabId = "mainpanel-"+href;
            var parentId = tab.id ;
            var tab = this.getComponent(tabId);
            if(tab){
                this.setActiveTab(tab);
                tab.setTitle("加载...");
                if(tabTip != null && (tabTip.indexOf("menu") == -1)) {
                    tab.parentId = parentId;
                }
                tab.setSrc(href);
            }
            else{
                Ext.QuickTips.init();
                var p = this.add(new Ext.ux.ManagedIframePanel( {
                        id : tabId,
                        title : '加载...',
                        tabTip : tabTip,
                        parentId : parentId,
                        defaultSrc : href,
                        loadMask : {
                            msg : '正在加载内容...'
                        },
                        listeners : {
                            documentloaded : function(iframe) {
                                var win=iframe.dom.contentWindow;
                                var doc=win.document;
                                if(doc.location.protocol!='about:') {
                                    var tl = doc.title || "无标题";
                                    this.setTitle(tl);
                                    if(this.tabTip) {
                                        var str = this.tabTip.split('->');
                                        var len = str.length;
                                        if(str[len - 1] == 'menu' && str[len - 2] == tl) {
                                            str.pop();
                                        } else {
                                            str[len - 1] = tl;
                                        }
                                        tl = str.join("->");
                                    }
                                    Ext.Element.fly("content__"+this.id).child('span.x-tab-strip-text', true).qtip = tl;
                                }
                            }
                        }
                    }));
            this.setActiveTab(p);
        }
       }
    },
    resumePage : function(src,defaultSrc,tabTip,parentId){
        var tab = this.getActiveTab();
            var tabId = "mainpanel-"+defaultSrc;
            var tab = this.getComponent(tabId);
            if(tab) {
                this.setActiveTab(tab);
                tab.setTitle("加载...");
                tab.setSrc(src);
            } else {
                Ext.QuickTips.init();
                var p = this.add(new Ext.ux.ManagedIframePanel({
                        id : tabId,
                        title : '加载...',
                        tabTip : tabTip,
                        parentId : parentId,
                        defaultSrc : defaultSrc,
                        loadMask : {
                            msg : '正在加载内容...'
                        },
                        listeners : {
                            documentloaded : function(iframe) {
                                var win=iframe.dom.contentWindow;
                                var doc=win.document;
                                
                                if(doc.location.protocol!='about:') {
                                    var tl = doc.title || "无标题";
                                    this.setTitle(tl);
                                    if(this.tabTip) {
                                        var str = this.tabTip.split('->');
                                        var len = str.length;
                                        if(str[len - 1] == 'menu' && str[len - 2] == tl) {
                                            str.pop();
                                        } else {
                                            str[len - 1] = tl;
                                        }
                                        tl = str.join("->");
                                    }
                                    Ext.Element.fly("content__"+this.id).child('span.x-tab-strip-text', true).qtip = tl;
                                }
                            }
                        }
                    }));
            this.setActiveTab(p);
            if(src != defaultSrc) {
                p.setTitle("加载...");
                p.setSrc(src);
            }
        }

    }
});

Ext.reg('mainpanel', ExtUx.widget.portal.MainPanel);