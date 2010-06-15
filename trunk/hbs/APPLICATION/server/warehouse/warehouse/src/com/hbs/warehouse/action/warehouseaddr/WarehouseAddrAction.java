package com.hbs.warehouse.action.warehouseaddr;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.warehouse.manager.warehouseaddr.WarehouseAddrInfo;
import com.hbs.warehouse.manager.warehouseaddr.WarehouseAddrMgr;

@SuppressWarnings("serial")
public class WarehouseAddrAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(WarehouseAddrAction.class);
	
	private WarehouseAddrInfo warehouseAddr;
	
	public WarehouseAddrInfo getWarehouseAddr() {
		return warehouseAddr;
	}

	public void setWarehouseAddr(WarehouseAddrInfo warehouseAddr) {
		this.warehouseAddr = warehouseAddr;
	}

	/**
	 * 获取列表
	 * @action.input 不需要参数
	 * @action.result list 类型:Collection<WarehouseAddrInfo>
	 * @return
	 */
	public String doList(){
		try{
			Collection<WarehouseAddrInfo> l = WarehouseAddrMgr.listWarehouseAddr();
			this.setTotalCount(l.size());
			logger.debug("list size=" + l.size());
			setResult("list", l);
			setResult("count", l.size());
			return SUCCESS;
		}catch(Exception e){
			logger.error("catch Exception in doList", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 获取单项信息
	 * @action.input warehouseAddr.id
	 * @action.result warehouseAddr.* 类型:WarehouseAddrInfo
	 * @return
	 */
	public String doGetInfo(){
		try{
			if(warehouseAddr == null || StringUtils.isEmpty(warehouseAddr.getId())){
				logger.error("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			setResult("warehouseAddr", WarehouseAddrMgr.getWarehouseAddrById(warehouseAddr.getId()));
			return SUCCESS;
		}catch(Exception e){
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
}
