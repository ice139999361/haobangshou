Ext.data.Connection.prototype.doExportData = function(config) {
		// 获取 iframe
    var iFrame = Ice.getIFrame("fileDownIFrame");
    // 绑定导出配置
    iFrame.exportConfig = config;
    
    config.callback = function() {
    		// 获取需要的 iframe
    		var iFrame = Ice.getIFrame("fileDownIFrame");
    		var config = iFrame.exportConfig;
    		
    		// 创建 response 对象
        var response = {  // bogus response object
            responseText : '',
            responseXML : null
        };

        //response.argument = o ? o.argument : null;
        // 存放 iframe 中的 document 对象
				var _doc =  Ext.isIE ? iFrame.contentWindow.document : (iFrame.contentDocument || window.frames[id].document);
				// 填充 response 对象
				if(_doc && _doc.body) response.responseText = _doc.body.innerHTML;
        response.responseXML = (_doc && _doc.XMLDocument) ? _doc.XMLDocument : _doc;
				
        Ext.EventManager.removeListener(iFrame, 'load', config.callback, this);
        this.fireEvent("requestcomplete", this, response, config);
        
        Ext.callback(config.success,  config.scope, [response, config]);
        //Ext.callback(config.callback, config.scope, [config, true, response]);
    }
		
		// 添加 load 事件
    Ext.EventManager.on(iFrame, 'load', config.callback, this);
    // 将参数转为 string 串
    config.params = typeof config.params != "string" ? Ext.urlEncode(config.params) : config.params;
    
    // 组装 url 地址
    config.url += (config.url.indexOf("?") == -1 ? "?" : "&") + config.params;
    // 加载地址
    iFrame.src = config.url;
};


Ext.form.Action.Export = function(form, options){
    Ext.form.Action.Export.superclass.constructor.call(this, form, options);
};

Ext.extend(Ext.form.Action.Export, Ext.form.Action, {
    /**
    * @cfg {boolean} clientValidation Determines whether a Form's fields are validated
    * in a final call to {@link Ext.form.BasicForm#isValid isValid} prior to submission.
    * Pass <tt>false</tt> in the Form's submit options to prevent this. If not defined, pre-submission field validation
    * is performed.
    */
    type : 'submit',

    // private
    run : function(){
        var o = this.options;
        var method = this.getMethod();
        var isPost = method == 'POST';
        if(o.clientValidation === false || this.form.isValid()){
            Ext.Ajax.doExportData(Ext.apply(this.createCallback(o), {
                form:this.form.el.dom,
                url:o.url,
                method: method,
                params:isPost ? this.getParams() : null,
                isUpload: this.form.fileUpload
            }));

        }else if (o.clientValidation !== false){ // client validation failed
            this.failureType = Ext.form.Action.CLIENT_INVALID;
            this.form.afterAction(this, false);
        }
    },

    //此方法代表response成功返回信息，并不表示业务逻辑正确
    success : function(response){
        var result = this.processResponse(response);
        if(result === true || result.success){
            this.form.afterAction(this, true);
            return;
        }
        if(result.errors){
            this.form.markInvalid(result.errors);
            this.failureType = Ext.form.Action.SERVER_INVALID;
            Ext.Msg.alert('错误', result.data.msg);
        }
        this.form.afterAction(this, false);
    },

    // private
    handleResponse : function(response){
        if(this.form.errorReader){
            var rs = this.form.errorReader.read(response);
            var errors = [];
            if(rs.records){
                for(var i = 0, len = rs.records.length; i < len; i++) {
                    var r = rs.records[i];
                    errors[i] = r.data;
                }
            }
            if(errors.length < 1){
                errors = null;
            }
            return {
                success : rs.success,
                errors : errors
            };
        }
        return Ext.decode(response.responseText);
    }
});

// 增加Export Action
Ext.form.Action.ACTION_TYPES["export"] = Ext.form.Action.Export;

//给BasicForm增加ExportData方法
Ext.form.BasicForm.prototype.exportData = function(options){
        this.doAction('export', options);
        return this;
};