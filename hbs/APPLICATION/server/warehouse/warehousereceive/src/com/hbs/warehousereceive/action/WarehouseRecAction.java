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
	 * ��ʱ����
	 * @action.input	warehouseRec.*
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String doSaveTemp() {
		try {
			logger.debug("begin doSaveTemp");
			if (warehouseRec == null) {
				logger.info("��������");
				setErrorReason("��������");
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
				logger.info("��ʱ����ʧ�ܣ�");
				setErrorReason("����ʧ�ܣ�");
				return ERROR;
			}
			logger.debug("end doSaveTemp");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doSaveTemp", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * �ύ
	 * @action.input	warehouseRec.*
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String doSave() {
		try {
			logger.debug("begin doSave");
			if (warehouseRec == null) {
				logger.info("��������");
				setErrorReason("��������");
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
				logger.info("����ʧ�ܣ�");
				setErrorReason("����ʧ�ܣ�");
				return ERROR;
			}
			
			// DONE��doSave
			logger.debug("end doSave");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doSave", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * �л�ActiveState
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
					String s = "��ͣ������ʧ�ܣ�";
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
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * ȡ����ⵥ
	 * @action.input warehouseRec.*
	 * @action.input memo
	 * @return
	 */
	public String doCancel() {
		try {
			logger.debug("begin doCancel");
			if(!WarehouseRecUtil.checkKeyFields(warehouseRec)){
				logger.debug("����Ϊ�գ�");
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			int i = getMgr().cancelWareHouseRecInfo(warehouseRec, this.getHttpServletRequest().getParameter("memo"));
			if(i != 0){
				String s;
				switch(i){
				case -1:
					s = "״̬����ȡ��ʧ�ܣ�";
					logger.error(s);
					setErrorReason(s);
					break;
				case -2:
					s = "��������";
					logger.error(s);
					setErrorReason(s);
					break;
				default:
					s = "ȡ��ʧ�ܣ�";
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
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
}
