Ext.namespace("ExtUx.ux");

/*
  * @class Ext.ux.ManagedIFramePanel
  * Version:  RC1
  *     Adds unsupportedText property to render an element/text indicating lack of Iframe support
  *     Improves el visibility/display support when hiding panels (FF does not reload iframe if using visibility mode)
  *     Adds custom renderer definition to autoLoad config.
  * Version:  0.16
  *     fixed (inherited)panel destroy bugs and iframe cleanup. (now, no orphans/leaks for IE).
  *     added loadMask.enabled = (true/false) toggle
  *     Requesting the Panel.getUpdater now returns the Updater for the Iframe.
  *     MIP.load modified to load content into panel.iframe (rather than panel.body)
  * Version:  0.15
  *     enhanced loadMask.maskEl support to support panel element names ie: 'body, bwrap' etc
  * Version:  0.13
  *     Added loadMask support and refactored domready/documentloaded events
  * Version:  0.11
  *     Made Panel state-aware.
  * Version:  0.1
  * Author: Doug Hendricks 12/2007 doug[always-At]theactivegroup.com
  *
  *
 */
 Ext.ux.ManagedIframePanel = Ext.extend(Ext.Panel, {

    /**
    * Cached Iframe.src url to use for refreshes. Overwritten every time setSrc() is called unless "discardUrl" param is set to true.
    * @type String/Function (which will return a string URL when invoked)
     */
    defaultSrc  :null,
    bodyStyle:{width:'100%',height:'100%'},
    /**
    * @cfg {String/Object} iframeStyle
    * Custom CSS styles to be applied to the ux.ManagedIframe element in the format expected by {@link Ext.Element#applyStyles}
    * (defaults to {overflow:'auto'}).
    */
    iframeStyle : {overflow:'auto'},
    loadMask    : false,
    animCollapse: false,
    autoScroll  : false,
    closable    : true, /* set True by default in the event a site times-out while loadMasked */
    ctype       : "Ext.ux.ManagedIframePanel",

    /**
    *@cfg {String/Object} unsupportedText Text (or Ext.DOMHelper config) to display within the rendered iframe tag to indicate the frame is not supported
    */
    unsupportedText : {tag:'span'
                      ,cls:'x-error-noframes'
                      ,html:'Inline frames are NOT enabled\/supported by your browser.'
    },

    initComponent : function(){

        var unsup = false;
        if(this.unsupportedText){
            unsup =typeof this.unsupportedText == 'object'? {children:[this.unsupportedText]}:{html:this.unsupportedText};
        }

        this.bodyCfg ||
           (this.bodyCfg =
               {tag:'div'
               ,cls:'x-panel-body'
               ,children:[Ext.apply({tag:'iframe',allowtransparency:true,
                           frameBorder  :0,
                           cls          :'x-managed-iframe',
                           id :"iframe-"+this.id,
                           style        :{width:'100%',height:'100%'}
                          },unsup)
                          ]
           });

         Ext.ux.ManagedIframePanel.superclass.initComponent.call(this);
         this.addEvents({documentloaded:true, domready:true});

         if(this.defaultSrc){
            this.on('render', this.setSrc.createDelegate(this,[this.defaultSrc],0), this, {single:true});
        }
    },

      // private
    beforeDestroy : function(){

        if(this.rendered){

             if(this.tools){
                for(var k in this.tools){
                      Ext.destroy(this.tools[k]);
                }
             }

             if(this.header && this.headerAsText){
                var s;
                if( s=this.header.child('span')) s.remove();
                this.header.update('');
             }


             Ext.each(['iframe','header','topToolbar','bottomToolbar','footer','loadMask','body','bwrap'],
                function(elName){
                  if(this[elName]){

                    if(typeof this[elName].destroy == 'function'){
                         this[elName].destroy();
                    } else { Ext.destroy(this[elName]); }

                    this[elName] = null;
                    delete this[elName];
                  }
             },this);
        }

        Ext.ux.ManagedIframePanel.superclass.beforeDestroy.call(this);
    },
    onDestroy : function(){
        //Yes, Panel.super (Component), since we're doing Panel cleanup beforeDestroy instead.
        Ext.Panel.superclass.onDestroy.call(this);
    },
    // private
    onRender : function(ct, position){
        Ext.ux.ManagedIframePanel.superclass.onRender.call(this, ct, position);

        if(this.iframe = this.body.child('iframe.x-managed-iframe')){

            // Set the Visibility Mode for el, bwrap for collapse/expands/hide/show
            Ext.each(
                [this[this.collapseEl],this.el]
                ,function(el){
                     el.setVisibilityMode(Ext.Element[this.hideMode.toUpperCase()] || 1).originalDisplay = (this.hideMode != 'display'?'visible':'block');
            },this);

            if(this.loadMask){
                this.loadMask = Ext.apply({enabled:true,maskEl:this.body},this.loadMask);

             }
            this.iframe = new Ext.ux.ManagedIFrame(this.iframe, {loadMask:this.loadMask});
            this.loadMask = this.iframe.loadMask;
            this.iframe.ownerCt = this;

            this.relayEvents(this.iframe, ["documentloaded","domready"]);
            if(this.iframeStyle){
                   this.iframe.applyStyles(this.iframeStyle);
            }

            this.getUpdater().showLoadIndicator = !this.loadMask.enabled;


        }
    },
        // private
    afterRender : function(container){
        var html = this.html;
        delete this.html;
        Ext.ux.ManagedIframePanel.superclass.afterRender.call(this);
        if(html && this.iframe){
            this.iframe.update(typeof html == 'object' ? Ext.DomHelper.markup(html) : html);
        }

    },
    /**
    * Sets the embedded Iframe src property.
    * @param {String/Function} url (Optional) A string or reference to a Function that returns a URI string when called
    * @param {Boolean} discardUrl (Optional) If not passed as <tt>false</tt> the URL of this action becomes the default URL for
    * this panel, and will be subsequently used in future setSrc calls.
    * Note:  invoke the function with no arguments to refresh the iframe based on the current defaultSrc value.
    */
    setSrc : function(url, discardUrl){
         var src = url || this.defaultSrc || (Ext.isIE&&Ext.isSecure?Ext.SSL_SECURE_URL:'');
         if(this.rendered && this.iframe){
              this.iframe.setSrc(src,discardUrl);
           }
         if(discardUrl !== true){ this.defaultSrc = src; }
         this.saveState();
         return this;
    },

    //Make it state-aware
    getState: function(){
         return Ext.apply(Ext.ux.ManagedIframePanel.superclass.getState.call(this) || {},
             {defaultSrc  :(typeof this.defaultSrc == 'function')?this.defaultSrc():this.defaultSrc});
    },
    /**
     * Get the {@link Ext.Updater} for this panel's iframe/or body. Enables you to perform Ajax-based document replacement of this panel's iframe document.
     * @return {Ext.Updater} The Updater
     */
    getUpdater : function(){
        return this.rendered?(this.iframe||this.body).getUpdater():false;
    },
     /**
      * Loads this panel's iframe immediately with content returned from an XHR call.
      * @param {Object/String/Function} config A config object containing any of the following options:
    <pre><code>
    panel.load({
        url: "your-url.php",
        params: {param1: "foo", param2: "bar"}, // or a URL encoded string
        callback: yourFunction,
        scope: yourObject, // optional scope for the callback
        discardUrl: false,
        nocache: false,
        text: "Loading...",
        timeout: 30,
        scripts: false,
        renderer:{render:function(el, response, updater, callback){....}}  //optional custom renderer
    });
    </code></pre>
         * The only required property is url. The optional properties nocache, text and scripts
         * are shorthand for disableCaching, indicatorText and loadScripts and are used to set their
         * associated property on this panel Updater instance.
         * @return {Ext.Panel} this
         */
    load : function(loadCfg){
         var um;
         if(um = this.getUpdater()){
            if (loadCfg && loadCfg.renderer) {
                 um.setRenderer(loadCfg.renderer);
                 delete loadCfg.renderer;
            }
            um.update.apply(um, arguments);
         }
         return this;
    }
     // private
    ,doAutoLoad : function(){
        this.load(
            typeof this.autoLoad == 'object' ?
                this.autoLoad : {url: this.autoLoad});
    }
    // private
    ,onShow : function(){
        this.body.setVisible(true);
        Ext.ux.ManagedIframePanel.superclass.onShow.call(this);
    }

    // private
    ,onHide : function(){
        this.body.setVisible(false);
        Ext.ux.ManagedIframePanel.superclass.onHide.call(this);
    }
});

Ext.reg('iframepanel', Ext.ux.ManagedIframePanel);

Ext.ux.ManagedIframePortlet = Ext.extend(Ext.ux.ManagedIframePanel, {
	anchor: '100%',
	frame:true,
	collapseEl:'bwrap',
	collapsible:true,
	draggable:true,
	cls:'x-portlet'
});
Ext.reg('iframeportlet', Ext.ux.ManagedIframePortlet);