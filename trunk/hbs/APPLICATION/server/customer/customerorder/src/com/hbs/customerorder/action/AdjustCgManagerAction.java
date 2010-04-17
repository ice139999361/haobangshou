/**
 * 
 */
package com.hbs.customerorder.action;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.domain.adjust.pojo.AdjustInfo;

/**
 * 采购经理调货action
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class AdjustCgManagerAction extends AdjustBaseAction {
	private static final Logger logger = Logger.getLogger(AdjustCgManagerAction.class);

	@Override
	protected boolean getIsManager() {
		return true;
	}

	@Override
	public String getRoleName() {
		return "cgmanager";
	}

	/**
	 * 审批
	 * @action.input adjustInfo.applySeqId
	 * @action.input adjustInfo.auditAgree	0---同意  1---不同意
	 * @action.input adjustInfo.auditContent
	 * @action.input audit	审批结果 0：审批不通过；1：审批通过
	 * @action.input	auditDesc 审批意见
	 * @return
	 */
	public String doAudit(){
		try {
			logger.debug("begin doAudit");
			if (adjustInfo == null || adjustInfo.getApplySeqId() == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			
			AdjustInfo adjustInfo2 = adjustInfo;
			adjustInfo = getMgr().getAdjustInfo(adjustInfo2.getApplySeqId().toString());
			if(adjustInfo == null){
				String s = "id " + adjustInfo2.getApplySeqId() + " 错误！";
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			adjustInfo.setAuditAgree(getHttpServletRequest().getParameter("audit"));
			adjustInfo.setAuditContent(getHttpServletRequest().getParameter("auditDesc"));
			adjustInfo.setAuditDate(adjustInfo2.getAuditDate());
			adjustInfo.setAuditStaffId(adjustInfo2.getAuditStaffId());
			adjustInfo.setAuditStaffName(adjustInfo2.getAuditStaffName());
			adjustInfo2 = null;
			
			if(StringUtils.isEmpty(adjustInfo.getAuditStaffId())){
				adjustInfo.setAuditStaffId(getLoginStaff().getStaffId().toString());
				adjustInfo.setAuditStaffName(getLoginStaff().getStaffName());
			}
			if(adjustInfo.getAuditDate() == null)
				adjustInfo.setAuditDate(new Date());
			if(StringUtils.isEmpty(adjustInfo.getAuditAgree())){
				String s = "审批结果没有填写！";
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			int i;
			if("1".equals(adjustInfo.getAuditAgree()))
				i = getMgr().agreeAdjustInfo(adjustInfo);
			else
				i = getMgr().disAgreeAdjustInfo(adjustInfo);
			if(i != 0){
				String s = "审批失败！";
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			logger.debug("end doAudit");
			return SUCCESS;
		}catch(Exception e){
			logger.error("catch Exception in doAudit", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
}
