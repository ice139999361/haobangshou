/**
 * 
 */
package com.hbs.customerorder.action;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.domain.adjust.pojo.AdjustInfo;

/**
 * �ɹ��������action
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
	 * ����
	 * @action.input adjustInfo.applySeqId
	 * @action.input adjustInfo.auditAgree	0---ͬ��  1---��ͬ��
	 * @action.input adjustInfo.auditContent
	 * @action.input audit	������� 0��������ͨ����1������ͨ��
	 * @action.input	auditDesc �������
	 * @return
	 */
	public String doAudit(){
		try {
			logger.debug("begin doAudit");
			if (adjustInfo == null || adjustInfo.getApplySeqId() == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			
			AdjustInfo adjustInfo2 = adjustInfo;
			adjustInfo = getMgr().getAdjustInfo(adjustInfo2.getApplySeqId().toString());
			if(adjustInfo == null){
				String s = "id " + adjustInfo2.getApplySeqId() + " ����";
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
				String s = "�������û����д��";
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
				String s = "����ʧ�ܣ�";
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			logger.debug("end doAudit");
			return SUCCESS;
		}catch(Exception e){
			logger.error("catch Exception in doAudit", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
}
