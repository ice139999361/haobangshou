Ext.Panel.prototype.__setSyncSize__ = function() {
	var flag = true;
	for(var i = 1, datas = panelSyncSizeFlagIds.split(","), count = datas.length ; i < count ; i++) {
		var cmp = Ext.getCmp(item);
		if(!cmp) return;
		if(!cmp.getEl()) return;
		if(cmp.getEl().dom.innerHTML.indexOf(this.id) != -1) {
			flag = false;
			break;
		}
	}
	
	if(flag) setTimeout("Ext.getCmp('" + this.id + "').syncSize()", 0);
	this.un("afterrender", this.__setSyncSize__);
};

Ext.Panel.prototype.syncSize = function () {
  var tw = this.getEl().dom.firstChild.offsetWidth;
  //if(!this.frame) tw -= 16;
	delete this.lastSize;

	

	this.setSize(this.autoWidth ? undefined : tw, this.autoHeight ? undefined : this.getResizeEl().getHeight());
  //this.setSize(this.autoWidth ? undefined : this.getResizeEl().getWidth(), this.autoHeight ? undefined : this.getResizeEl().getHeight());
  return this;
};

Ext.Panel.prototype.__initComponent__ = Ext.Panel.prototype.initComponent;

Ext.Panel.prototype.initComponent = function() {
	// 因为 IE 会出问题，所以需要此方法进行转换，属于 ext 的 bug
	this.on("afterrender", this.__setSyncSize__);
	this.__initComponent__();
};