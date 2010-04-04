/**
 * 
 */
package com.hbs.warehousereceive.action;

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
}
