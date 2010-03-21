/**
 * 
 */
package com.hbs.vendorinfo.action;

import org.apache.log4j.Logger;

import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.vendorinfo.manager.VendorInfoMgr;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class VendorInfoCwNormalAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(VendorInfoCwNormalAction.class);
	
	public static final String roleName = "cwnormal";
	
	VendorInfo vendorInfo;
	
	public VendorInfo getVendorInfo() {
		return vendorInfo;
	}
	
	public void setVendorInfo(VendorInfo a) {
		this.vendorInfo = a;
	}
	
	/**
	 * ��ѯ
	 * @action.input vendorInfo.��ѯ����
	 * @action.result list���б� count������
	 * @return
	 */
	public String doList() {
		try {
			if (logger.isDebugEnabled())
				logger.debug("begin doList");
			if (vendorInfo == null) {
				vendorInfo = new VendorInfo();
			}
			setPagination(vendorInfo);
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
			vendorInfo.setState("0");
			setResult("list", mgr.getVendorInfoList(vendorInfo));
			setTotalCount(mgr.getCustomerInfoCount(vendorInfo));
			setResult("count", getTotalCount());
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeCustState, roleName));
			if (logger.isDebugEnabled())
				logger.debug("end doList");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doList.", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ��ȡ��Ӧ����ϸ��Ϣ
	 * @action.input 
	 *	vendorInfo.baseSeqId �� vendorInfo.commCode
	 * @action.result vendorInfo.*
	 * @return
	 */
	public String doGetInfo()
	{
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doGetInfo");
			if(!VendorInfoUtil.checkKeyFields2(vendorInfo))
			{
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			vendorInfo.setState("0");
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
			getVendorInfoValue(mgr);
			if(!"0".equals(vendorInfo.getState())){
				logger.info("״̬����");
				setErrorReason("״̬����");
				return ERROR;
			}
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
	 * ��������
	 * @action.input 
	 *	vendorInfo.baseSeqId �� vendorInfo.commCode
	 * @action.input memo ˵��
	 * @return
	 */
	public String doLock(){
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doLock");
			if(!VendorInfoUtil.checkKeyFields2(vendorInfo))
			{
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			vendorInfo.setState("0");
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
			int i = mgr.lockVendorInfo(vendorInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doLock", e);
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}

	/**
	 * �������
	 * @action.input 
	 *	vendorInfo.baseSeqId �� vendorInfo.commCode
	 * @action.input memo ˵��
	 * @return
	 */
	public String doUnlock(){
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doUnlock");
			if(!VendorInfoUtil.checkKeyFields2(vendorInfo))
			{
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			vendorInfo.setState("0");
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
			int i = mgr.unlockVendorInfo(vendorInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doUnlock", e);
			setErrorReason("�ڲ�����");
            return ERROR;
		}
	}

	protected String getMemo() {
		return this.getHttpServletRequest().getParameter("memo");
	}
	
	/**
	 * ����vendorInfo�Ĳ�����Ϣ��ȡȫ����Ϣ
	 * @param mgr
	 * @throws Exception
	 */
	protected void getVendorInfoValue(VendorInfoMgr mgr) throws Exception
	{
		vendorInfo = VendorInfoUtil.getVendorInfo(mgr, vendorInfo);
	}
}
