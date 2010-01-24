Ext.namespace("ExtUx.ux");


/*
 * @class Ext.ux.ManagedIFrame
 * Version:  RC1 (Release Candidate 1)
 * Author: Doug Hendricks. doug[always-At]theactivegroup.com
 * Copyright 2007-2008, Active Group, Inc.  All rights reserved.
 *
 ************************************************************************************
 *   This file is distributed on an AS IS BASIS WITHOUT ANY WARRANTY;
 *   without even the implied warranty of MERCHANTABILITY or
 *   FITNESS FOR A PARTICULAR PURPOSE.
 ************************************************************************************

 License: ux.ManagedIFrame and ux.ManagedIFramePanel are licensed under the terms of
 the Open Source LGPL 3.0 license.  Commercial use is permitted to the extent
 that the code/component(s) do NOT become part of another Open Source or Commercially
 licensed development library or toolkit without explicit permission.

 Donations are welcomed: http://donate.theactivegroup.com

 License details: http://www.gnu.org/licenses/lgpl.html

 * <p> An Ext harness for iframe elements.

  Adds Ext.UpdateManager(Updater) support and a compatible 'update' method for
  writing content directly into an iFrames' document structure.

  * Usage:<br>
   * <pre><code>
   * // Harness it from an existing Iframe from markup
   * var i = new Ext.ux.ManagedIFrame("myIframe");
   * // Replace the iFrames document structure with the response from the requested URL.
   * i.load("http://myserver.com/index.php", "param1=1&amp;param2=2");
   * //Notes:  this is not the same as setting the Iframes src property !
   * // Content loaded in this fashion does not share the same document namespaces as it's parent --
   * // meaning, there (by default) will be no Ext namespace defined in it since the document is
   * // overwritten after each call to the update method, and no styleSheets.
  * </code></pre>
  * <br>
   * @cfg {Boolean/Object} autoCreate True to auto generate the IFRAME element, or a {@link Ext.DomHelper} config of the IFRAME to create
   * @cfg {String} html Any markup to be applied to the IFRAME's document content when rendered.
   * @cfg {Object} loadMask An {@link Ext.LoadMask} config or true to mask the iframe while using the update or setSrc methods (defaults to false).
   * @cfg {Object} src  The src attribute to be assigned to the Iframe after initialization (overrides the autoCreate config src attribute)
   * @constructor

    * @param {Mixed} el, Config object The iframe element or it's id to harness or a valid config object.

 */

Ext.ux.ManagedIFrame = function(){
    var args=Array.prototype.slice.call(arguments, 0)
        ,el = Ext.get(args[0])
        ,config = args[0];

    if(el && el.dom && el.dom.tagName == 'IFRAME'){
            config = args[1] || {};
    }else{
            config = args[0] || args[1] || {};
            el = config.autoCreate?
            Ext.get(Ext.DomHelper.append(document.body, Ext.apply({tag:'iframe', allowtransparency:true,src:(Ext.isIE&&Ext.isSecure)?Ext.SSL_SECURE_URL:''},config.autoCreate))):null;
    }

    if(!el || el.dom.tagName != 'IFRAME') return el;

    !!el.dom.name.length || (el.dom.name = el.dom.id); //make sure there is a valid frame name

    this.addEvents({
       /**
         * @event domready
         * Fires when the iFrame's Document(DOM) has reach a state where the DOM may be manipulated
         * @param {Ext.ux.ManagedIFrame} this
         * Note: This event is only available when overwriting the iframe document using the update method.
         */
        "domready"       : true,

       /**
         * @event documentloaded
         * Fires when the iFrame has reached a loaded/complete state.
         * @param {Ext.ux.ManagedIFrame} this
         */
        "documentloaded" : true
    });

    if(config.listeners){
        this.listeners=config.listeners;
        Ext.ux.ManagedIFrame.superclass.constructor.call(this);
    }

    Ext.apply(el,this);  // apply this class interface ( pseudo Decorator )

    el.addClass('x-managed-iframe');

    el.loadMask = Ext.apply({msg:'Loading..',msgCls:'x-mask-loading',maskEl:null, enabled:!!config.loadMask},config.loadMask);

    //Hook the Iframes loaded state handler
    var h= Ext.isIE?'onreadystatechange':'onload';
    el.dom[h] = el.dom[h]?
        el.dom[h].createSequence(el.loadHandler,el):
            el.loadHandler.createDelegate(el);

    if(config.src){
        el.setSrc(config.src);
    }else{
        el.src = el.dom.src||null;
        var content = config.html || config.content || false;

        if(content){
            el.update(content);
        }
    }

    return el;
};

Ext.extend(Ext.ux.ManagedIFrame , Ext.util.Observable,
  {
        /**
    * Sets the embedded Iframe src property.

    * @param {String/Function} url (Optional) A string or reference to a Function that returns a URI string when called
    * @param {Boolean} discardUrl (Optional) If not passed as <tt>false</tt> the URL of this action becomes the default SRC attribute for
    * this iframe, and will be subsequently used in future setSrc calls (emulates autoRefresh by calling setSrc without params).
    * Note:  invoke the function with no arguments to refresh the iframe based on the current defaultSrc value.
   */
  setSrc : function(url, discardUrl){
        var src = url || this.src || (Ext.isIE&&Ext.isSecure?Ext.SSL_SECURE_URL:'');
        this.showMask();
        this._windowContext = null;
        (function(){
              var s = typeof src == 'function'?src()||'':src;
              if(Ext.isOpera)this.dom.src="";
              this.dom.src = s;
        }).defer(100,this);
        if(discardUrl !== true){ this.src = src; }
  },
  //Private: script removal RegeXp
  scriptRE  : /(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)/gi
  ,
      /*
    Write(replacing) string content into the IFrames document structure
   * @param {String} content The new content
       * @param {Boolean} loadScripts (optional) true to also render and process embedded scripts
       * @param {Function} callback (optional) Callback when update is complete.
      */
  update : function(content,loadScripts,callback){

      loadScripts = loadScripts || this.getUpdateManager().loadScripts || false;

      this._windowContext = false;
      content = Ext.DomHelper.markup(content||'');

      var doc = this.getDocument();
      if(doc){
          this._inUpdate = true;
          this.showMask();

          doc.open();
          doc.write(loadScripts===true ? content:content.replace(this.scriptRE , ""));

           //create an 'eval'able context for the iframe and this.execScript
          doc.write ('<script type="text/javascript">(function(){'+
                     "var MSIE/*@cc_on =1@*/;"+ // IE sniff
                     "parent.Ext.get('"+this.dom.id +"')._windowContext=MSIE?this:{eval:function(s){return eval(s);}}"+
                      "})();<\/script>" );
          doc.close();

          if(!!content.length){
               this.checkDOM(false,callback);
          } else if(callback){
              callback();
          }
      }
      return this;
  },
  _windowContext : null,
  /*
    Return the Iframes document object
  */
  getDocument:function(){
      return this.getWindow().document;
  },

  /*
   Return the Iframes window object
  */
  getWindow:function(){
      var dom= this.dom;
      return dom?dom.contentWindow||window.frames[dom.name]:window;
  },

  /*
   Print the contents of the Iframes (if we own the document)
  */
  print:function(){
      try{
          var win = this.getWindow();
          if(Ext.isIE){win.focus();}
          win.print();
      } catch(ex){
          throw 'print exception: ' + (ex.description || ex);
      }
  },
  //private
  destroy:function(){
      this.removeAllListeners();
      if(this.dom){
           //unHook the Iframes loaded state handlers
           this.dom.onreadystatechange=null;
           this.dom.onload            =null;

          //IE Iframe cleanup
           if(this.dom.src){
              this.dom.src = 'javascript:false';
           }
           Ext.removeNode(this.dom);
      }
      this._windowContext = null;
      Ext.apply(this.loadMask,{masker :null ,maskEl : null});
  }
  /*
  Execute a javascript code block(string) within the context of the Iframes window object.
  * @param {String} block A valid ('eval'able) script source block.
  * <p> Note: execScript will only work after a successful update (document.write);
  */
  ,execScript: function(block){
      if(this._windowContext){
          return this._windowContext.eval( block );
      } else {
          throw 'execScript:no script context';
      }
  }

  ,loadMask: {msg:'Loading..',msgCls:'x-mask-loading',maskEl:null, enabled:false}

  //Private
  ,showMask: function(msg,msgCls,forced){
        if(this.loadMask && (this.loadMask.enabled || forced)){
            var lmask = this.loadMask;     //Wrap the Iframe if no masking ELement is available.
             lmask.masker || (lmask.masker = Ext.get(lmask.maskEl||this.dom.parentNode||this.wrap({tag:'div',style:{position:'relative'}})));
             //lmask.masker.mask(msg||lmask.msg , msgCls||lmask.msgCls );
             lmask.masker.mask.defer(lmask.defer||150,lmask.masker,[msg||lmask.msg , msgCls||lmask.msgCls] );
         }
     }
  //Private
  ,hideMask: function(forced){
         var tlm = this.loadMask;
         if(tlm && tlm.masker && (tlm.enabled || forced) &&
                (forced || !!this.dom.src.length || this._inUpdate)){
             tlm.masker.unmask.defer(tlm.defer||150,tlm.masker);
         }
  }

  /* Private
    Evaluate the Iframes readyState/load event to determine its 'load' state,
    and raise the 'documentloaded' event when applicable.
  */
  ,loadHandler : function(e){
       var rstatus = this.dom.readyState || e.type;
       switch(rstatus){
          case 'loading':
             this.showMask();
             break;
          case 'load':
          case 'complete':
             this.hideMask();
          	//页面加载完成后触发 documentloaded 事件 added by haomingli in 2008-12-24
             this.fireEvent("documentloaded",this);
             
             this._inUpdate = false;
             break;
          default:
      }
  }
  /* Private
    Poll the Iframes document structure to determine DOM ready state,
    and raise the 'domready' event when applicable.
  */
  ,checkDOM : function(win,callback){
      //initialise the counter
      var n = 0
          ,win = win||this.getWindow()
          ,manager = this;
      var t =function(){  //DOM polling

          var domReady=false;
          //if DOM methods are supported, and the body element exists
          //(using a double-check including document.body, for the benefit of older moz builds [eg ns7.1]
          //in which getElementsByTagName('body')[0] is undefined, unless this script is in the body section)

          domReady  = (win.document && typeof win.document.getElementsByTagName != 'undefined'
              && ( win.document.getElementsByTagName('body')[0] != null || win.document.body != null ));
          //if the timer has reached 70 (timeout after ~10.5 seconds)
          //in practice, shouldn't take longer than 7 iterations [in kde 3
          //in second place was IE6, which takes 2 or 3 iterations roughly 5% of the time]
          if(n++ < 70 && !domReady)
          {
              //try again
              t.defer(50);
              return;
          }
          if(callback)callback();
          manager.fireEvent("domready",manager); //fallback
      };
      t();
  }
});