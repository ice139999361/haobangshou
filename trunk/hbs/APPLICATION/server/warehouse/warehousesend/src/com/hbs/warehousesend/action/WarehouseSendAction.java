/**
 * 
 */
package com.hbs.warehousesend.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;

import com.hbs.common.utils.ListDataUtil;


import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehousesend.manager.WareHouseSendDetailMgr;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class WarehouseSendAction extends WarehouseSendBaseAction {
	private static final Logger logger = Logger.getLogger(WarehouseSendAction.class);

	
	private static final String detailListName = "settlementlist";
	private static final String detailListFields = "settlementlistFields";
	
	private static final String splitter = "\\|\\|;;";
	
	private static final String fieldNameSplitter = ",";
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
			boolean isNew = StringUtils.isEmpty(warehouseSend.getSendPoNo());
			int i = getMgr().saveWareHouseSendInfo(warehouseSend, null);
			if(i != 0) {
				logger.info("��ʱ����ʧ�ܣ�");
				setErrorReason("����ʧ�ܣ�");
				return ERROR;
			}
			if(isNew)
				setResult("sendPoNo", warehouseSend.getSendPoNo());
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
			boolean isNew = StringUtils.isEmpty(warehouseSend.getSendPoNo());
			int i = getMgr().corfirmWarehouseSendInfo(warehouseSend, null);
			if(i != 0) {
				logger.info("����ʧ�ܣ�");
				setErrorReason("����ʧ�ܣ�");
				return ERROR;
			}
			if(isNew)
				setResult("sendPoNo", warehouseSend.getSendPoNo());
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
	
	/**
	 * ����ĳ��ⵥ����
	 * @return
	 */
	public String doConfirmFinancePeriod(){
		try {
			logger.debug("begin doConfirmFinancePeriod");
			List<WarehouseSendDetail> list =processListData(this.getHttpServletRequest());
			String realFinancePeriod = this.getHttpServletRequest().getParameter("realfinancePeriod");
			logger.debug("realFinancePeriod=" + realFinancePeriod);
			if (list == null || list.size() ==0) {
				logger.info("û����Ҫ����ĳ��ⵥ��Ϣ��");
				setErrorReason("û����Ҫ����ĳ��ⵥ��Ϣ��");
				return ERROR;
			}
			WareHouseSendDetailMgr mgr = getMgrDetail();
			for(WarehouseSendDetail detail : list){
				String settlementtype = detail.getSettlementType();
				if(settlementtype.equals("1")){//���ڽ���
					detail.setFinancePeriod(realFinancePeriod);
					mgr.adjustFinancePeriod(detail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), null);
					
					//detail.setFinanceState("1");
					
					mgr.setFinanceState(detail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), null);
				}else{//�����ڽ���
					//detail.setFinanceState("1");
					
					mgr.setFinanceState(detail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), null);
				}
				
			}
			
			// DONE��doSave
			logger.debug("end doConfirmFinancePeriod");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doConfirmFinancePeriod", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}	
	}
	
	
	@SuppressWarnings("unchecked")
	private List<WarehouseSendDetail> processListData(
			HttpServletRequest request) {
		List<WarehouseSendDetail> listAdd = null;
		try {
			logger.debug("aaa = " + request.getParameterValues(detailListName));
			logger.debug("bbb = " + request.getParameter(detailListFields));
			List<WarehouseSendDetail> list = ListDataUtil.splitIntoList(WarehouseSendDetail.class, 
				request.getParameterValues(detailListName), 
				request.getParameter(detailListFields).split(fieldNameSplitter), 
				splitter);		
			
			listAdd= new ArrayList<WarehouseSendDetail>();
			if(list != null && list.size() >0){
				for(WarehouseSendDetail detail : list){
					if(StringUtils.isEmpty(detail.getFinanceState()) || detail.getFinanceState().equals("0")){
						
					    listAdd.add(detail);
					}
				}
			}
		} catch (Exception e) {
			logger.info("processListData����detailList����", e);
		}
		return listAdd;
	}
}
