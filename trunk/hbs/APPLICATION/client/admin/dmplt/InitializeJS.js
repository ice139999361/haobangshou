{
	new Ext.KeyMap(Ext.getBody(), {
	    key: Ext.EventObject.BACKSPACE,
	    fn: function() {
	    	if(Ext.EventObject.getTarget().tagName != "INPUT")
	    	{
	    			Ext.EventObject.stopEvent();
	    	}
	    	else if(Ext.EventObject.getTarget().readOnly)
	    	{
	    			Ext.EventObject.stopEvent();
	    	}
	    },
	    scope: this
	});
}