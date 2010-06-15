package com.hbs.warehouse.manager.warehouseaddr;

//import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;


public class WarehouseAddrMgr {
	//private static final Logger logger = Logger.getLogger(WarehouseAddrMgr.class);

	public static final String nameType = "WAREHOUSE_TYPE";
	public static final String addressType = "WAREHOUSE_ADDRESS";
	public static final String conNameType = "WAREHOUSE_CONTACT";
	public static final String zipType = "WAREHOUSE_ZIP";

	public static WarehouseAddrInfo getWarehouseAddrById(String id) throws Exception{
		WarehouseAddrInfo waInfo = new WarehouseAddrInfo();
		ConfigEncode ce = new ConfigEncode();
		ConfigEncode ce2;
		ce.setEncodeKey(id);
		ce.setEncodeType(nameType);
		ce2 = ConfigEncodeMgr.getConfigEncode(ce);
		if(ce2 != null)
			waInfo.setName(ce2.getEncodeValue());
		else
			return null;
		
		try{
			ce.setEncodeType(addressType);
			ce2 = ConfigEncodeMgr.getConfigEncode(ce);
			if(ce2 != null)
				waInfo.setAddress(ce2.getEncodeValue());
		}finally{
			
		}
		try{
			ce.setEncodeType(conNameType);
			ce2 = ConfigEncodeMgr.getConfigEncode(ce);
			if(ce2 != null)
				waInfo.setConName(ce2.getEncodeValue());
		}finally{
			
		}
		try{
			ce.setEncodeType(zipType);
			ce2 = ConfigEncodeMgr.getConfigEncode(ce);
			if(ce2 != null)
				waInfo.setZip(ce2.getEncodeValue());
		}finally{
			
		}
		return waInfo;
	}
	
	public static Collection<WarehouseAddrInfo> listWarehouseAddr() throws Exception{
		Map<String, WarehouseAddrInfo> map = new HashMap<String, WarehouseAddrInfo>();
		ConfigEncode ce = new ConfigEncode();
		ce.setEncodeType(nameType);
		for(ConfigEncode ce2 : ConfigEncodeMgr.getListConfigEncode(ce)){
			WarehouseAddrInfo wa = new WarehouseAddrInfo();
			wa.setId(ce2.getEncodeKey());
			wa.setName(ce2.getEncodeValue());
			map.put(ce2.getEncodeKey(), wa);
		}
		ce.setEncodeType(addressType);
		for(ConfigEncode ce2 : ConfigEncodeMgr.getListConfigEncode(ce)){
			WarehouseAddrInfo wa =map.get(ce2.getEncodeKey());
			if(wa != null)
				wa.setAddress(ce2.getEncodeValue());
		}
		ce.setEncodeType(conNameType);
		for(ConfigEncode ce2 : ConfigEncodeMgr.getListConfigEncode(ce)){
			WarehouseAddrInfo wa =map.get(ce2.getEncodeKey());
			if(wa != null)
				wa.setConName(ce2.getEncodeValue());
		}
		ce.setEncodeType(zipType);
		for(ConfigEncode ce2 : ConfigEncodeMgr.getListConfigEncode(ce)){
			WarehouseAddrInfo wa =map.get(ce2.getEncodeKey());
			if(wa != null)
				wa.setZip(ce2.getEncodeValue());
		}
		return map.values();
	}
}
