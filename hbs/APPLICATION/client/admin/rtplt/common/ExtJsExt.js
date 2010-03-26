Ext.BLANK_IMAGE_URL = Ext.isIE6||Ext.isIE7||Ext.isAir?CONTEXT_PATH+"/ext/images/default/s.gif":"data:image/gif;base64,R0lGODlhAQABAID/AMDAwAAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==";
// 更改错误展示方式
Ext.form.Field.prototype.msgTarget = 'side';
// 更改日期控件默认的宽度
Ext.form.DateField.prototype.width = 123;

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

// 扩展 label 的 setValue 方法
Ext.form.Label.prototype.setValue = Ext.form.Label.prototype.setText;
// 扩展 getValue 属性
Ext.form.Label.prototype.getValue = function() { return this.getEl().dom.innerText; }

// 备份 formPanel 的 initComponent 方法
Ext.form.FormPanel.prototype._initComponent = Ext.form.FormPanel.prototype.initComponent;
// 重写 formPanel 的 initComponent 方法
Ext.form.FormPanel.prototype.initComponent = function() {
	this._initComponent();
	this.getForm().formPanel = this;
};

// 增加Export Action
Ext.form.Action.ACTION_TYPES["export"] = Ext.form.Action.Export;
// 备份 basicForm 的 setValues 方法
Ext.form.BasicForm.prototype._setValues = Ext.form.BasicForm.prototype.setValues;
//alert(Ext.form.BasicForm.prototype.findField)
Ext.apply(Ext.form.BasicForm.prototype, {
	 //给BasicForm增加ExportData方法
	 exportData: function(options) {
		 	this.doAction('export', options);
		 	return this;
	 }
	,findField: function(id) {
			var field = this.items.get(id);
			if(!Ext.isObject(field)) {
				this.items.each(function(f) {
					if(f.isFormField && (f.dataIndex == id || f.id == id || f.getName() == id)) {
						field = f;
						return false;
					}
				})
			}
			
			// 扩展代码部分
			if(!Ext.isObject(field)) {
				var formPanel = this.formPanel;
				Ext.each(formPanel.findByType("label"), function(f) {
					if(f.dataIndex == id || f.id == id || f.name == id) {
						field = f;
						return false;
					}
				});
			}

			return field || null;
	 }
	,setValues: function(values) {
			values = this.parseData(values);
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


Ext.ux.form.ItemSelector.prototype.imagePath = CONTEXT_PATH + "/ext/ux/images";
Ext.apply(Ext.ux.form.MultiSelect.prototype, {
	initComponent: function() {
	    Ext.ux.form.MultiSelect.superclass.initComponent.call(this);
	
			if(this.url) {
				// 定义数据结构
				var RecordType = new Ext.data.Record.create((this.record ? this.record.split(",") : [this.valueField, this.displayField]));
				// 创建数据容器
				this.store = new Ext.data.Store({
					proxy: new Ext.data.HttpProxy({url: SERVER_PATH + this.url}),
					reader: new Ext.data.JsonReader({
						root: this.root
					}, RecordType)
				});
				
				// 设置参数
				for(var i = 0, _paramsName = this.paramsName.split(","), _paramsValue = this.paramsValue.split(","), count = _paramsName.length ; i < count ; i++) {
					this.store.baseParams[_paramsName[i]] = _paramsValue[i];
				}
				
				this.store.load();
			} else {
				if(Ext.isString(this.store)) this.store = eval(this.store);
		    if(Ext.isArray(this.store)){
		        this.store = new Ext.data.ArrayStore({
	              fields: [this.valueField, this.displayField],
	              data: this.store
	          });
		    } else {
		        this.store = Ext.StoreMgr.lookup(this.store);
		    }
	  	}
	
	    this.addEvents({
	        'dblclick' : true,
	        'click' : true,
	        'change' : true,
	        'drop' : true
	    });
	},
	valueField: "value",
	displayField: "text",
	root: "data.list",
	paramsName: "",
	paramsValue: "",
	record: null,
	url: ""
});
