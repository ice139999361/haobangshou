/**
 * 
 */
package com.hbs.warehousereceive.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.warehouse.common.constants.WareHouseConstants;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class WarehouseRecAction extends WarehouseRecBaseAction {
	private static final Logger logger = Logger.getLogger(WarehouseRecAction.class);

	@Override
	protected boolean getIsManager() {
		return false;
	}

	@Override
	public String getRoleName() {
		return "cknormal";
	}

	/**
	 * 临时保存
	 * @action.input	warehouseRec.*
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String doSaveTemp() {
		try {
			logger.debug("begin doSaveTemp");
			if (warehouseRec == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			
			warehouseRec.setState(WareHouseConstants.WAREHOUSE_REC_INFO_01);
			if(StringUtils.isEmpty(warehouseRec.getOperId()))
				setMyId(true);
			if(warehouseRec.getOperTime() == null)
				warehouseRec.setOperTime(new Date());
			if(StringUtils.isEmpty(warehouseRec.getPoNoType()))
				warehouseRec.setPoNoType("0");
			
			Map otherData = new HashMap();
			
			WarehouseRecUtil.processListData(warehouseRec, this.getHttpServletRequest(), otherData);
			List<FieldErr> errs = WarehouseRecUtil.checkInputFields(warehouseRec, otherData);
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			int i = getMgr().saveWareHouseRecInfo(warehouseRec, null);
			if(i != 0) {
				logger.info("临时保存失败！");
				setErrorReason("保存失败！");
				return ERROR;
			}
			logger.debug("end doSaveTemp");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doSaveTemp", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 提交
	 * @action.input	warehouseRec.*
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String doSave() {
		try {
			logger.debug("begin doSave");
			if (warehouseRec == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			if(StringUtils.isEmpty(warehouseRec.getState()) || warehouseRec.getState().equals(WareHouseConstants.WAREHOUSE_REC_INFO_01))
				warehouseRec.setState(WareHouseConstants.WAREHOUSE_REC_INFO_02);
			if(StringUtils.isEmpty(warehouseRec.getOperId()))
				setMyId(true);
			if(warehouseRec.getOperTime() == null)
				warehouseRec.setOperTime(new Date());
			if(StringUtils.isEmpty(warehouseRec.getPoNoType()))
				warehouseRec.setPoNoType("0");

			Map otherData = new HashMap();
			
			WarehouseRecUtil.processListData(warehouseRec, this.getHttpServletRequest(), otherData);
			List<FieldErr> errs = WarehouseRecUtil.checkInputFields(warehouseRec, otherData);
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			int i = getMgr().corfirmWareHouseRecInfo(warehouseRec, null);
			if(i != 0) {
				logger.info("保存失败！");
				setErrorReason("保存失败！");
				return ERROR;
			}
			
			// DONE：doSave
			logger.debug("end doSave");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doSave", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 切换ActiveState
	 * @action.input warehouseRec.*
	 * @action.input memo
	 * @return
	 */
	public String doControlActiveState() {
		try {
			logger.debug("begin doActiveState");
			if(getWarehouseRecByKey(true)){
				int i = getMgr().controlActiveState(warehouseRec, this.getHttpServletRequest().getParameter("memo"));
				if(i != 0){
					String s = "暂停、激活失败！";
					logger.error(s + " ret=" + i);
					setErrorReason(s);
					return ERROR;
				}
			}else{
				return ERROR;
			}	
			logger.debug("end doActiveState");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doActiveState", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 取消入库单
	 * @action.input warehouseRec.*
	 * @action.input memo
	 * @return
	 */
	public String doCancel() {
		try {
			logger.debug("begin doCancel");
			if(!WarehouseRecUtil.checkKeyFields(warehouseRec)){
				logger.debug("参数为空！");
				setErrorReason("参数为空！");
				return ERROR;
			}
			int i = getMgr().cancelWareHouseRecInfo(warehouseRec, this.getHttpServletRequest().getParameter("memo"));
			if(i != 0){
				String s;
				switch(i){
				case -1:
					s = "状态错误，取消失败！";
					logger.error(s);
					setErrorReason(s);
					break;
				case -2:
					s = "参数错误！";
					logger.error(s);
					setErrorReason(s);
					break;
				default:
					s = "取消失败！";
					logger.error(s + " ret=" + i);
					setErrorReason(s);
					break;
				}
				return ERROR;
			}
			logger.debug("end doCancel");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doCancel", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
}
