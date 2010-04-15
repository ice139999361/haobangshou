/**
 * 
 */
package com.hbs.warehousesend.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;

import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;
import com.hbs.warehouse.common.constants.WareHouseConstants;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class WarehouseSendAction extends WarehouseSendBaseAction {
	private static final Logger logger = Logger.getLogger(WarehouseSendAction.class);

	@Override
	protected boolean getIsManager() {
		return false;
	}

	@Override
	public String getRoleName() {
		return "cknormal";
	}

	private Integer sendSeqId;
	
	/**
	 * @return the sendSeqId
	 */
	public Integer getSendSeqId() {
		return sendSeqId;
	}

	/**
	 * @param sendSeqId the sendSeqId to set
	 */
	public void setSendSeqId(Integer sendSeqId) {
		this.sendSeqId = sendSeqId;
	}

	/**
	 * ��ʱ����
	 * @action.input	warehouseSend.*
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String doSaveTemp() {
		try {
			logger.debug("begin doSaveTemp");
			if (warehouseSend == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			
			warehouseSend.setState(WareHouseConstants.WAREHOUSE_REC_INFO_01);
			if(StringUtils.isEmpty(warehouseSend.getOperId()))
				setMyId(true);
			if(warehouseSend.getCreateDate() == null)
				warehouseSend.setCreateDate(new Date());
			if(StringUtils.isEmpty(warehouseSend.getPoNoType()))
				warehouseSend.setPoNoType("0");
			
			Map otherData = new HashMap();
			
			WarehouseSendUtil.processListData(warehouseSend, this.getHttpServletRequest(), otherData);
			List<FieldErr> errs = WarehouseSendUtil.checkInputFields(warehouseSend, otherData);
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			int i = getMgr().saveWareHouseSendInfo(warehouseSend, null);
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
	 * @action.input	warehouseSend.*
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String doSave() {
		try {
			logger.debug("begin doSave");
			if (warehouseSend == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			if(StringUtils.isEmpty(warehouseSend.getState()) || warehouseSend.getState().equals(WareHouseConstants.WAREHOUSE_REC_INFO_01))
				warehouseSend.setState(WareHouseConstants.WAREHOUSE_REC_INFO_02);
			if(StringUtils.isEmpty(warehouseSend.getOperId()))
				setMyId(true);
			if(warehouseSend.getCreateDate() == null)
				warehouseSend.setCreateDate(new Date());
			if(StringUtils.isEmpty(warehouseSend.getPoNoType()))
				warehouseSend.setPoNoType("0");

			Map otherData = new HashMap();
			
			WarehouseSendUtil.processListData(warehouseSend, this.getHttpServletRequest(), otherData);
			List<FieldErr> errs = WarehouseSendUtil.checkInputFields(warehouseSend, otherData);
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			int i = getMgr().corfirmWarehouseSendInfo(warehouseSend, null);
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
	 * @action.input warehouseSend.*
	 * @action.input memo
	 * @return
	 */
	public String doControlActiveState() {
		try {
			logger.debug("begin doActiveState");
			if(getWarehouseSendByKey(true)){
				int i = getMgr().controlActiveState(warehouseSend, this.getHttpServletRequest().getParameter("memo"));
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
	 * ȡ�����ⵥ
	 * @action.input warehouseSend.*
	 * @action.input memo
	 * @return
	 */
	public String doCancel() {
		try {
			logger.debug("begin doCancel");
			if(!WarehouseSendUtil.checkKeyFields(warehouseSend)){
				logger.debug("����Ϊ�գ�");
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			setMyId(true);
			int i = getMgr().cancelWarehouseSendInfo(warehouseSend, this.getHttpServletRequest().getParameter("memo"));
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
	
	/**
	 * ȡ�����ⵥ��ϸ����
	 * @return
	 */
	public String doCancelDetail() {
		try {
			logger.debug("begin doCancelDetail");
			
			if(null == this.sendSeqId){
				logger.debug("����Ϊ�գ�");
				setErrorReason("����Ϊ�գ�");
				return ERROR;				
			}else{
				WarehouseSendDetail detail =new  WarehouseSendDetail();
				detail.setSendSeqId(sendSeqId);
				int i = getMgrDetail().cancelWareHouseSendDetail(detail,  false,  this.getHttpServletRequest().getParameter("memo"));
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
				logger.debug("end doCancelDetail");
				return SUCCESS;
			}			
			
		} catch(Exception e) {
			logger.error("catch Exception in doCancelDetail", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
		
		
	}
}
