Ext.namespace('ExtUx.widget');

ExtUx.widget.ComplexGrid = function(config) {
	// 处理配置数据
	this.__processConfig__(config);
	// 设置选择模式
	config.sm = new Ext.grid.CheckboxSelectionModel();
	
	ExtUx.widget.ComplexGrid.superclass.constructor.call(this, config);
};

Ext.extend(ExtUx.widget.ComplexGrid, Ext.grid.EditorGridPanel, {
	initComponent : function(){
		ExtUx.widget.ComplexGrid.superclass.initComponent.call(this);
	},
	__getDefData__: function() {
		if(!this.__defdata) {
			// 获取 fields 对象
			var _fields = new this.store.recordType().fields;
			
			// 创建默认的 defdata 对象
			var _defdata = {};
			_fields.each(function(item, index, itemsAll) {
				this[item.name] = "";
			}, _defdata);
			
			// 设置
			this.__defdata = _defdata;
		}
		
		return Ext.apply({}, this.__defdata);
	},
	__processConfig__: function(config) {
		// 设置表格的id
		this.__setGridId__(config);
		// 处理扩展控件部分
		this.__processItemsFun__(config);
		// 设置提交时需要的字段集
		this.__setSubmitFields__(config);
		// 设置 store 对象
		this.__setStore__(config);
		// 设置分页工具栏
		this.__setPaging__(config);
		// 设置列对象
		this.__setColumns__(config);
		// 设置tbar
		this.__setTBar__(config);
		//alert(config.root)
	},
	__setTBar__ : function(config) {
		// 如果不需要默认的 tbar
		if(!config.deftbar) return;
		
		// 构造默认的增加和删除方法
		config.tbar = [{
	      text		: "增加"
	     ,config	: config
	     ,handler : function() {
	     		var _grid 		 = Ext.getCmp(this.config.id);
	     		_grid.addData(_grid.__getDefData__());
	     	}
	  },{
	      text: "删除"
	     ,config	: config
	     ,handler : function() {
	     		Ext.getCmp(this.config.id).removeSelectRow();
	     	}
	  }];
	},
	__setSubmitFields__: function(config) {
		switch(typeof config.submitFields) {
			case "object":
				config.__submitFields = config.submitFields.value;
				break;
			case "string":
				config.__submitFields = config.submitFields;
				break;
			default:
				var tmp = [];
				Ext.each(config.fields, function(item, index, itemsAll) {
					tmp.push(item.name);
				}, tmp);
				config.__submitFields = tmp.join();
				break;
		}
		
		delete config.submitFields;
	},
	__setColumns__: function(config) {
		// 常量及属性
		var columnFields = ["id", "dataIndex", "header", "width", "editor", "align", "sortable", "renderer"];
		// 存储可编辑列对应的field Key
		var sbEditStore = new StringBuilder;
		// 要创建列的集合
		var _columns = [];
		
		// 对config所传来的 columns 进行处理
		Ext.each(config.columns, function(item, index, itemsAll) {
			// 创建列对象
			var column = {};
			// 设置列控件 id
			item.id = item.id || ExtConvertHelper.getUniqueStr("col-");
			// 设置列的基本属性
			this.__setColumnBasPro__(column, item, columnFields);
			// 如果 column 对象的 align 属性不存在则默认为 align: "center"
			if(!column.align) column.align = "center";
			
			if(item.isCheck) {
				column.width = 27;
				column = new Ext.grid.CheckboxSelectionModel(column);
			} else {
				// 处理渲染方法
				this.__processRenderer__(column);
				// 处理可编辑对象
				this.__processEditorColumn__(column, item, sbEditStore, columnFields);
			}
			
			// 添加列到集合
			_columns.push(column);
		}, this);
		
		
		
		// 重置列对象
		config.columns = _columns;
	},
	__processEditorColumn__: function(column, item, sbEditStore, columnFields) {
		// 如果不是编辑列
		if(!item.xtype || item.xtype == "column") return;
		// 删除不需要的属性
		ExtConvertHelper.deletePro(columnFields, item);
		// 修改editor组件宽度
		item.width = (column.width || 150) - 30;
		// 隐藏Label
		item.hideLabel = true;
		// 创建edit对象
		column.editor = ExtConvertHelper.createComponent(item);
	},
	__processRenderer__: function(column) {
		// 设置扩展的渲染方法
		this.__setExtRenderer__(column);
		// 设置应用开发的渲染方法
		column.__selfRenderer = eval(column.renderer) || null;
		// 控件的renderer方法
		column.renderer = function(val, metadata, record, rowIndex, colIndex, store) {
			// 获取表格控件
			var _grid = Ext.getCmp(store.gridId);
			// 获取列对象
			var column = _grid.getColumnByIndex(colIndex);
			// 扩展的渲染处理
			if(column.__extRenderer) val = column.__extRenderer.call(this, val, metadata, record, rowIndex, colIndex, store);
			// 应用的渲染处理
			if(column.__selfRenderer) val = column.__selfRenderer.call(this, val, metadata, record, rowIndex, colIndex, store);

			return val;
		}
	},
	__setExtRenderer__: function(column) {
		column.__extRenderer = null;
	},
	__setColumnBasPro__: function(column, proItem, columnFields) {
		Ext.each(columnFields, function(item, index, itemsAll) {
			// 如果该属性不为 undefined 则进行设置
			if(typeof proItem[item] != "undefined") column[item] = proItem[item];
		}, this);
	},
	__setPaging__: function(config) {
		// 如果不需要分页工具栏
		if(!config.page) return;
		
		// 设置分页工具栏
		config.bbar = new ExtUx.widget.XPagingToolbar({
			pageSize: 10,
      store: config.store,
      displayInfo: true
		});
		
		// 删除没有用的属性
		delete config.page;
	},
	__setStore__: function(config) {
		// 获取 fields 字段
		var fields = config.fields;
		
		// 组装 store 需要的 json
		var json = {
			 url    : SERVER_PATH + (config.url || eval((config.id + "Url")))
			,reader : new Ext.data.JsonReader({
				  root   : config.root
				 ,fields : fields
				 ,totalProperty : "totalCount"
			 })
		};
		// 如果需要排序
		if(config.sort) {
			json.sortInfo   = config.sort;
			json.remoteSort = true;
		}
		
		// 创建数据集合
		var store = new Ext.data.Store(json);
		// 删除没有意义的属性
		ExtConvertHelper.deletePro("fields,url,root,sort", config);

		// 数据集合关联 gridId
		store.gridId = config.id;
		// 返回数据集合
		config.store = store;
	},
	__processItemsFun__: function(config) {
		// 如果没有扩展
		if(!config.itemsFun) return;
		
		// 获取方法对象
		var itemsFun = eval(config.itemsFun);
		// 删除没用的属性
		delete config.itemsFun;
		// 调用扩展方法，获取 ComplexGridHelper 对象
		var cgh = itemsFun.call(this);
		
		// 处理 config 对象，加入扩展属性
		cgh.processConfig(config);
	},
	__setGridId__: function(config) {
		if(!config.id) config.id = ExtConvertHelper.getUniqueStr("grid-");
	},
	getColumnByIndex: function(index) {
		// 获取列对象
		var _cm = this.getColumnModel();
		// 返回需要的列对象
		return _cm.getColumnById(_cm.getColumnId(index));
	},
	/**
	 * 返回指定列的索引
	 * @param id 列的id
	 */
	getColumnIndexById: function(id) {
		// 获取表格列模型
		var columnModel = this.getColumnModel();
		// 返回列索引
		return columnModel.getIndexById(id);
	},
	removeSelectRow: function() {
		// 获取选种的Record集合
		var selectRecords = this.getSelectionModel().getSelections();
		// 获取store
		var store = this.store;
		// 删除
		for(var i = selectRecords.length - 1 ; i >= 0 ; i--) {
			store.remove(selectRecords[i]);
		}
	},
	// 删除所有空行
	removeEmptyRows: function() {
		// 获取grid控件 columnModel 属性
		var _cm = this.getColumnModel();
		// 获取列表的数据集
		var _store = this.store;
		
		for(var i = _store.getCount() - 1 ; i >= 0 ; i--) {
			var record = _store.getAt(i);
			var flag = false;
			for(var j = 0, count =  _cm.getColumnCount() ; j < count ; j++) {
				var d = record.get(_cm.getDataIndex(j));
				d = (typeof d == "string") ? d.trim() : d;
				if(d) break;
				if(j == (count - 1)) flag = true;
			}
			if(flag) _store.remove(record);
		}
	},
	// 获取需要提交给后台的字段集
	getSubmitFields: function() {
		return this.__submitFields;
	},
	// 返回列表中所有的数据
	getAllDatatToFormat: function(paramName, splitChar) {
		// 删除所有的空行
		this.removeEmptyRows();
		// 组装参量对象
		paramName += "=";
		// 获取store对象
		var _store = this.store;
		// 存放要提交的数据
		var _datas = [];
		
		// 组装数据
		for(var i = 0, count = _store.getCount() ; i < count ; i++) {
			var record = _store.getAt(i);
			var sb = [];
			Ext.each(this.getSubmitFields().split(","), function(item, index, itemsAll) {
				var _d = record.get(item);
				sb.push(_d);
			});
			_datas.push(paramName + sb.join(splitChar) + splitChar);
		}
		
		// 返回生成的数据
		return _datas.join("&");
	},
	// 添加数据到表格
	addData: function(data) {
		var _store 		= this.getStore();
    var _DataType = _store.recordType;
    
    var _dts = [];
    Ext.each(ExtConvertHelper.convertObj2Array(data), function(item, index, itemsAll) {
    	var _dt = new _DataType(item);
    	_dts.push(_dt);
    });
    
    var _editIndex = _store.getCount();
		this.stopEditing();
    _store.add(_dts);
    this.startEditing(_editIndex, 1);
	},
	// 表格验证方法，暂未实现
	isValid: function() { return true },
	style         : "margin:10px 0px 0px 5px",
	height        : 250,
	stripeRows    : true,
	collapsible   : true,
	clicksToEdit  : 1,
	titleCollapse : true
});
Ext.reg('complexgrid', ExtUx.widget.ComplexGrid);


ComplexGridHelper = function() {
	// 本类属性均属于私有属性
	  
	this.sort    = null;														// 排序字段
	this.fields  = null;														// 字段
	this.columns = null;														// 列集合
	this.submitFields = null;												// 提交时需要数据的集合
}

ComplexGridHelper.prototype = {
	 setSort: function(field, direction) {
	 		// 如果排序对象不存在则新建
	 		if(!this.sort) this.sort = {};
	 		
	 		// 设置排序属性
	 		this.sort.field = field;
	 		this.sort.direction = direction;
	 		
	 		// 返回对象本身
	 		return this;
	 }
	,appendField: function(name, mapping) {
			// 如果 fields 对象不存在则新建
			if(!this.fields) this.fields = [];
			
			// 创建 field 对象
			var field = {"name": name};
			// 如果 mapping 存在，则设置
			if(mapping) field.mapping = mapping;
			// 添加 field 对象到 fields 中
			this.fields.push(field);
			
			// 返回对象本身
	 		return this;
	 }
	,appendColumn: function(column) {
			// 如果 columns 对象不存在则新建
			if(!this.columns) this.columns = [];
			
			// 添加 column 到 columns 对象中
			this.columns.push(column);
			
			// 返回对象本身
	 		return this;
	 }
	,setSubmitFields: function(submitFields) {
			this.submitFields = submitFields;
	 }
	 
	 
	 // 以下方法均为私有方法
	 
	,processSort: function(config) {
			// 如果 config 对象中 sort 对象存在则跳出
			if(config.sort) return this;
			// 如果本类中 sort 对象不存在则跳出
			if(!this.sort) return this;
			
			// 设置 config 的排序属性
			config.sort = this.sort;
			
			// 返回对象本身
	 		return this;
	 }
	,processFields: function(config) {
			// 如果本类的 fields 对象不存在则跳出
			if(!this.fields) return this;
			
			// 如果 config 中的 fields 不存在
			if(!config.fields) {
				// 设置 config 的 fields 属性
				config.fields = this.fields;
			} else {
				// 设置 config 的 fields 属性, 两个数组对象融合
				config.fields = config.fields.concat(this.fields);
			}
			
			// 返回对象本身
	 		return this;
	 }
	,processColumns: function(config) {
			// 如果本类的 columns 对象不存在则跳出
			if(!this.columns) return this;
			
			// 如果 config 中的 columns 不存在
			if(!config.columns) {
				// 设置 config 的 columns 属性
				config.columns = this.columns;
			} else {
				// 设置 config 的 fields 属性, 两个数组对象融合
				if(config.itemsAlign == "after") config.columns = this.columns.concat(config.columns);
				else config.columns = config.columns.concat(this.columns);
			}
			
			// 返回对象本身
	 		return this;
	 }
	,processSubmitFields: function(config) {
			// 如果 config 对象中 submitFields 对象存在则跳出
			if(config.submitFields) return this;
			// 如果本类中 submitFields 对象不存在则跳出
			if(!this.submitFields) return this;
			
			// 设置 config 的 submitFields 属性
			config.submitFields = this.submitFields;
			
			// 返回对象本身
	 		return this;
	 }
	,processConfig: function(config) {
			// 处理 sort 对象
			this.processSort(config);
			// 处理 fields 对象
			this.processFields(config);
			// 处理 columns 对象
			this.processColumns(config);
			// 处理 submitFields 对象
			this.processSubmitFields(config);
			
			// 返回对象本身
			return this;
	 }
};