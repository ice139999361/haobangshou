package com.hbs.vendorinfo.action;

import org.apache.log4j.Logger;

import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.vendorinfo.manager.VendorInfoMgr;

/**
 * �����ɫ��Ӧ����ϢAction
 * @author xyf
 * @actions doAuditAgree doAuditDisAgree doList doGetInfo
 */
@SuppressWarnings("serial")
public class VendorInfoManagerAction extends BaseAction {
	
	/**
	 * Manager��
	 */
	static final String vendorInfoMgrName = "vendorInfoMgr";
		
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorInfoManagerAction.class);

    /**
	 * ������״̬��state��Ҫ�ַ�������Constants�����Ͳ�ƥ�䡣����Ҳ��ֱ�ۡ�
	 */
	static final String stateForAudit = "2";
	
	public static final String roleName = "cgmanager";
	
	VendorInfo vendorInfo;
	
	/**
	 * ��ȡ��Ӧ����Ϣ
	 * @return ��Ӧ����Ϣ
	 */
	public VendorInfo getVendorInfo(){ return vendorInfo;}
	
	/**
	 * ���ù�Ӧ����Ϣ
	 * @param a ��Ӧ����Ϣ
	 */
	public void setVendorInfo(VendorInfo a) { this.vendorInfo = a; }
	
	String auditDesc;
	
	/**
	 * ��ȡ�������
	 * @return �������
	 */
	public String getAuditDesc() { return auditDesc; }
	
	/**
	 * �����������
	 * @param a �������
	 */
	public void setAuditDesc(String a) { auditDesc = a; }
	
	/**
	 * ����
	 * @action.input 
	 *	vendorInfo.baseSeqId �� (vendorInfo.commCode + vendorInfo.state)
	 * @action.input audit	������� 0��������ͨ����1������ͨ��
	 * @action.input	auditDesc �������
	 * @return
	 */
	public String doAudit() {
		try {
			String audit = this.getHttpServletRequest().getParameter("audit");
			if(audit == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			if(audit.equals("0")) {
				return doAuditDisAgree();
			} else {
				return doAuditAgree();
			}
		} catch(Exception e) {
			logger.error("catch Exception in doAudit", e);
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}
	
	/**
	 * ����ͬ��
	 * @action.input 
	 *	vendorInfo.baseSeqId �� (vendorInfo.commCode + vendorInfo.state)
	 * @action.input
	 *	auditDesc
	 * @return
	 */
	public String doAuditAgree()
	{
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doAuditAgree");
			
			if(!VendorInfoUtil.checkKeyFields(vendorInfo))
			{
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(vendorInfoMgrName);
			getCustInfoValue(mgr);
			int ret = mgr.auditAgreeVendorInfo(vendorInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), auditDesc);
			if(ret != 0)
			{
				String s;
				switch(ret)
				{
				case 1:
					s = "�޴�״̬��";
					break;
				case 2:
					s = "״̬����ȷ��";
					break;
				default:
					s = "�������";
					logger.info(s + " ret=" + ret);
					break;
				}
				setErrorReason(s);
				return ERROR;
			}
			if (logger.isDebugEnabled())    logger.debug("end doAuditAgree");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doAuditAgree", e);
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}
	
	/**
	 * ������ͬ��
	 * @action.input 
	 *	vendorInfo.baseSeqId �� (vendorInfo.commCode + vendorInfo.state)
	 * @action.input
	 *	auditDesc
	 * @return
	 */
	public String doAuditDisAgree()
	{
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doAuditDisAgree");
			if(!VendorInfoUtil.checkKeyFields(vendorInfo))
			{
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(vendorInfoMgrName);
			getCustInfoValue(mgr);
			int ret = mgr.auditDisAgreeVendorInfo(vendorInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), auditDesc);
			if(ret != 0)
			{
				String s;
				switch(ret)
				{
				case 1:
					s = "�޴�״̬��";
					break;
				case 2:
					s = "״̬����ȷ��";
					break;
				default:
					s = "�������";
					logger.info(s + " ret=" + ret);
					break;
				}
				setErrorReason(s);
				return ERROR;
			}
			if (logger.isDebugEnabled())    logger.debug("end doAuditDisAgree");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doAuditDisAgree", e);
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}

	/**
	 * ��ѯ������mgr.getCustomerInfoList��
	 * @action.input vendorInfo.��ѯ����
	 * @action.result list���б� count������
	 * @return
	 */
	public String doList()
	{
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doList");
			if (vendorInfo == null) {
				vendorInfo = new VendorInfo();
			}
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(vendorInfoMgrName);
			setPagination(vendorInfo);
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeCustState, roleName));
			setResult("list", mgr.getVendorInfoList(vendorInfo));
			setTotalCount(mgr.getCustomerInfoCount(vendorInfo));
			setResult("count", getTotalCount());
			if (logger.isDebugEnabled())    logger.debug("end doList");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doList", e);
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}
	
	/**
	 * ��ѯ����������
	 * @action.input ��
	 * @action.result list���б� count������
	 * @return
	 */
	public String doListForAudit()
	{
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doListForAudit");
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(vendorInfoMgrName);
			vendorInfo = new VendorInfo();
			vendorInfo.setState(stateForAudit);
			setPagination(vendorInfo);
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeCustState, roleName));
			setResult("list", mgr.getVendorInfoList(vendorInfo));
			setTotalCount(mgr.getCustomerInfoCount(vendorInfo));
			setResult("count", getTotalCount());
			if (logger.isDebugEnabled())    logger.debug("end doListForAudit");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doListForAudit", e);
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}
	
	/**
	 * ��ȡ��Ӧ����ϸ��Ϣ
	 * @action.input 
	 *	vendorInfo.baseSeqId �� (vendorInfo.commCode + vendorInfo.state)
	 * @action.result vendorInfo.*
	 * @return
	 */
	public String doGetInfo()
	{
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doGetInfo");
			if(!VendorInfoUtil.checkKeyFields(vendorInfo))
			{
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(vendorInfoMgrName);
			getCustInfoValue(mgr);
			this.setResult("vendorInfo", vendorInfo);
			if (logger.isDebugEnabled())    logger.debug("end doGetInfo");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}

	/**
	 * ����vendorInfo�Ĳ�����Ϣ��ȡȫ����Ϣ
	 * @param mgr
	 * @throws Exception
	 */
	protected void getCustInfoValue(VendorInfoMgr mgr) throws Exception
	{
		vendorInfo = VendorInfoUtil.getVendorInfo(mgr, vendorInfo);
	}
}
