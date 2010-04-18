/**
 * 
 */
package com.hbs.warehousereceive.action;

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
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;

import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehousereceive.manager.WareHouseRecDetailMgr;


/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class WarehouseRecAction extends WarehouseRecBaseAction {
	private static final Logger logger = Logger.getLogger(WarehouseRecAction.class);
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
	private Integer recDetailSeqId;
	
	public Integer getRecDetailSeqId() {
		return recDetailSeqId;
	}

	public void setRecDetailSeqId(Integer recDetailSeqId) {
		this.recDetailSeqId = recDetailSeqId;
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
			setMyId(true);
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
	
	/**
	 * 取消入库单明细操作
	 * @return
	 */
	public String doCancelDetail() {
		try {
			logger.debug("begin doCancelDetail");
			
			if(null == this.recDetailSeqId){
				logger.debug("参数为空！");
				setErrorReason("参数为空！");
				return ERROR;				
			}else{
				WarehouseRecDetail detail =new  WarehouseRecDetail();
				detail.setRecDetailSeqId(this.recDetailSeqId);
				int i = getMgrDetail().cancelWareHouseRecDetail(detail, false,  this.getHttpServletRequest().getParameter("memo"));
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
				logger.debug("end doCancelDetail");
				return SUCCESS;
			}			
			
		} catch(Exception e) {
			logger.error("catch Exception in doCancelDetail", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 财务的入库单对账
	 * @return
	 */
	public String doConfirmFinancePeriod(){
		try {
			logger.debug("begin doConfirmFinancePeriod");
			List<WarehouseRecDetail> list =processListData(this.getHttpServletRequest());
			String realFinancePeriod = this.getHttpServletRequest().getParameter("realfinancePeriod");
			logger.debug("realFinancePeriod=" + realFinancePeriod);
			if (list == null || list.size() ==0) {
				logger.info("没有需要处理的入库单信息！");
				setErrorReason("没有需要处理的入库单信息！");
				return ERROR;
			}
			WareHouseRecDetailMgr mgr = getMgrDetail();
			for(WarehouseRecDetail detail : list){
				String settlementtype = detail.getSettlementType();
				if(settlementtype.equals("1")){//账期结算
					detail.setFinancePeriod(realFinancePeriod);
					mgr.adjustFinancePeriod(detail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), null);
					
					//detail.setFinanceState("1");
					
					mgr.setFinanceState(detail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), null);
				}else{//非账期结算
					//detail.setFinanceState("1");
					
					mgr.setFinanceState(detail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), null);
				}
				
			}
			
			// DONE：doSave
			logger.debug("end doConfirmFinancePeriod");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doConfirmFinancePeriod", e);
			setErrorReason("内部错误");
			return ERROR;
		}	
	}
	
	
	@SuppressWarnings("unchecked")
	private List<WarehouseRecDetail> processListData(
			HttpServletRequest request) {
		List<WarehouseRecDetail> listAdd = null;
		try {
			logger.debug("aaa = " + request.getParameterValues(detailListName));
			logger.debug("bbb = " + request.getParameter(detailListFields));
			List<WarehouseRecDetail> list = ListDataUtil.splitIntoList(WarehouseRecDetail.class, 
				request.getParameterValues(detailListName), 
				request.getParameter(detailListFields).split(fieldNameSplitter), 
				splitter);		
			
			listAdd= new ArrayList<WarehouseRecDetail>();
			if(list != null && list.size() >0){
				for(WarehouseRecDetail detail : list){
					if(StringUtils.isEmpty(detail.getFinanceState()) || detail.getFinanceState().equals("0")){
						
					    listAdd.add(detail);
					}
				}
			}
		} catch (Exception e) {
			logger.info("processListData处理detailList出错", e);
		}
		return listAdd;
	}
}
