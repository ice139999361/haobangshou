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
// 备份 basicForm 的 setValues 方法
Ext.form.BasicForm.prototype._setValues = Ext.form.BasicForm.prototype.setValues;

Ext.apply(Ext.form.BasicForm.prototype, {
	 //给BasicForm增加ExportData方法
	 exportData: function(options) {
		 	this.doAction('export', options);
		 	return this;
	 }
	,setValues: function(values) {
			values = this.parseData(values);
			alert(Ext.util.JSON.encode(values));
			this._setValues(values);
	 }
	,parseData: function(object, cacheobj, newobject) {
			// 如果是 Array 对象则直接反回
			if(object instanceof Array) return object;
			// 创建新的容器对象
			if(!newobject) newobject = {};
			// 组装 cacheobj.key 对象
			if(!cacheobj) cacheobj = {key: "", superkey: "", value: null};
			if(cacheobj.key) cacheobj.key += ".";
			// 缓存当前 key
			var currkey = cacheobj.key;

			for(var key in object) {
				// 组装 cacheobj 对象
				cacheobj.key += key;
				cacheobj.value = object[key];
				
				// 如果对象是 object 继续
				if(typeof object[key] == "object") this.parseData(object[key], cacheobj, newobject);
				
				// 如果值为默认值则进入下一次
				if(cacheobj.value == "-9)^5-123xq!") {
					cacheobj.key = currkey;
					continue;
				}
				// 将组装好的值加入容器中
				newobject[cacheobj.key] = cacheobj.value;
				// 将 cacheobj.key 还原
				cacheobj.key = currkey;
				// 设置 cacheobj.value
				cacheobj.value = "-9)^5-123xq!";
			}
			
			return newobject;
	 }
});