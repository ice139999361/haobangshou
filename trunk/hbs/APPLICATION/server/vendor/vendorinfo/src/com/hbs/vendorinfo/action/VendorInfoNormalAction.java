/**
 * �ͻ���ϢAction
 */
package com.hbs.vendorinfo.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.vendorinfo.manager.VendorContactMgr;
import com.hbs.vendorinfo.manager.VendorInfoMgr;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;

/**
 * ��ͨ��ɫ��Ӧ����ϢAction
 * @author xyf
 * @actions doList doGetInfo doSaveTemp doSave doDelete
 */
@SuppressWarnings("serial")
public class VendorInfoNormalAction extends BaseAction {

	/**
	 * Manager��
	 */
	public static final String vendorInfoMgrName = "vendorInfoMgr";

	/**
	 * logger.
	 */
	private static final Logger logger = Logger
			.getLogger(VendorInfoNormalAction.class);

	public static final String roleName = "cgnormal";
	VendorInfo vendorInfo;

	/**
	 * ��ȡ��Ӧ����Ϣ
	 * @return ��Ӧ����Ϣ
	 */
	public VendorInfo getVendorInfo() {
		return vendorInfo;
	}

	/**
	 * ���ù�Ӧ����Ϣ
	 * @param a ��Ӧ����Ϣ
	 */
	public void setVendorInfo(VendorInfo a) {
		this.vendorInfo = a;
	}

	/**
	 * ��ѯ���޶��Լ��ܹ���Ĺ�Ӧ����Ϣ������mgr.getCustomerInfoListByUser��
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
			setMyId(false);
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(vendorInfoMgrName);
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
	 * ��ʱ���湩Ӧ����Ϣ
	 * @action.input vendorInfo.*
	 * @action.result	seqId	insert��id�����û��insert��������û����һ�
	 * @return
	 */
	public String doSaveTemp() {
		try {
			if (logger.isDebugEnabled())
				logger.debug("begin doSaveTemp");

			if (vendorInfo == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}

			vendorInfo.setState("1");
			VendorInfoUtil.processListData(vendorInfo, this.getHttpServletRequest());
			List<FieldErr> errs = VendorInfoUtil.checkInputFields(vendorInfo);
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			if (VendorInfoUtil.checkSetStaffId(vendorInfo))
				setMyId(true);

			VendorInfoMgr mgr = (VendorInfoMgr)getBean(vendorInfoMgrName);

			VendorInfo info2 = mgr.getVendorInfo(vendorInfo, false);
			int ret;
			if (info2 != null)
				ret = mgr.updateCustomerInfo(vendorInfo);
			else
				ret = mgr.saveTempVendorInfo(vendorInfo);
			
			if (ret < 0) {
				logger.info("��ʱ�������");
				setErrorReason("��ʱ�������");
				return ERROR;
			}
			if(ret > 0) {
				this.setResult("seqId", ret);
				if (logger.isDebugEnabled()) logger.debug("seqId="+ret);
			}
			setResult("state", "1");
			this.setAlertMsg("��ʱ����ɹ���");
			if (logger.isDebugEnabled())
				logger.debug("end doSaveTemp");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doSaveTemp", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * �����û���Ϣ�����ڲ�ͬ��״̬�����в�ͬ�Ĳ���
	 * @action.input vendorInfo.*
	 * @action.result	seqId	insert��id�����û��insert��������û����һ�
	 * @return
	 */
	public String doSave() {
		try {
			if (logger.isDebugEnabled())
				logger.debug("begin doSave");

			if (vendorInfo == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}

			if (StringUtils.isEmpty(vendorInfo.getState()))
				vendorInfo.setState("2");
			if (VendorInfoUtil.checkSetStaffId(vendorInfo))
				setMyId(true);
			VendorInfoUtil.processListData(vendorInfo, this
					.getHttpServletRequest());
			List<FieldErr> errs = VendorInfoUtil.checkInputFields(vendorInfo);
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}

			VendorInfoMgr mgr = (VendorInfoMgr)getBean(vendorInfoMgrName);

			VendorInfo info2 = mgr.getVendorInfo(vendorInfo, false);
			int ret;
			if (info2 != null)
				ret = mgr.updateCustomerInfo(vendorInfo);
			else {
				vendorInfo.setState("1");
				vendorInfo.setCreditRate("3");
				ret = mgr.commitVendorInfo(vendorInfo);
			}
			if (ret < 0) {
				String s;
				switch (ret) {
				case -1:
					s = "�޴�״̬��";
					break;
				case -2:
					s = "״̬����ȷ��";
					break;
				default:
					s = "�������";
					logger.info(s + " ret=" + ret);
					break;
				}
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			if(ret > 0) {
				this.setResult("seqId", ret);
				if (logger.isDebugEnabled()) logger.debug("seqId="+ret);
			}
			setResult("state", "2");
			this.setAlertMsg("�ύ�ɹ���");
			if (logger.isDebugEnabled())
				logger.debug("end doSave");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doSave", e);
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
	public String doGetInfo() {
		try {
			if (logger.isDebugEnabled())
				logger.debug("begin doGetInfo");
			if (!VendorInfoUtil.checkKeyFields(vendorInfo)) {
				logger.info("����Ϊ�գ�");
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			setMyId(false);
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(vendorInfoMgrName);
			vendorInfo = VendorInfoUtil.getVendorInfo(mgr, vendorInfo);
			String id = getLoginStaff().getStaffId().toString();
			if(
					StringUtils.isNotEmpty(id) &&
					id.equals(vendorInfo.getStaffId())
				)
			{
				this.setResult("vendorInfo", vendorInfo);
				if (logger.isDebugEnabled())
					logger.debug("end doGetInfo");
				return SUCCESS;
			}
			this.setErrorReason("Ȩ�޴���");
			return ERROR;
		} catch (Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ɾ��������ͨ��������
	 * 
	 * @action.input vendorInfo.baseSeqId �� (vendorInfo.commCode + vendorInfo.state)
	 * @return
	 */
	public String doDelete() {
		try {
			if (logger.isDebugEnabled())
				logger.debug("begin doGetInfo");
			if (!VendorInfoUtil.checkKeyFields(vendorInfo)) {
				logger.info("����Ϊ�գ�");
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			try {
				if (3 != Integer.parseInt(vendorInfo.getState())) {
					logger.info("״̬����ȷ��");
					setErrorReason("״̬����ȷ��");
					return ERROR;
				}
			} catch (Exception e) {
				logger.info("״̬����ȷ��");
				setErrorReason("״̬����ȷ��");
				return ERROR;
			}
			setMyId(false);
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(vendorInfoMgrName);
			vendorInfo = mgr.getVendorInfo(vendorInfo, true);
			if (vendorInfo == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			int i = mgr.deleteVendorInfo(vendorInfo,
					getHttpServletRequest().getParameter("delDesc"));
			switch (i) {
			case 0:
				this.setAlertMsg("ɾ���ɹ���");
				return SUCCESS;
			case 2:
				logger.info("״̬����ȷ��");
				setErrorReason("״̬����ȷ��");
				return ERROR;
			default:
				logger.info("ɾ������");
				setErrorReason("ɾ������");
				return ERROR;
			}
		} catch (Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ��ȡ��ʽ�����е��ջ�����Ϣ
	 * @action.input	custInfo.baseSeqId �� (custInfo.commCode + custInfo.state)
	 * @action.result list���б� count������
	 * @return
	 */
	public String doGetConsigneeList()
	{
		try
		{
			if (logger.isDebugEnabled())
				logger.debug("begin doGetConsigneeList");
			setResult("list", getPersonList("2"));
			logger.debug("end doGetConsigneeList");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doGetConsigneeList", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * ��ȡ��ʽ�����е���ϵ����Ϣ
	 * @action.input	custInfo.baseSeqId �� (custInfo.commCode + custInfo.state)
	 * @action.result list���б� count������
	 * @return
	 */
	public String doGetContactList()
	{
		try
		{
			if (logger.isDebugEnabled())
				logger.debug("begin doGetContactList");
			setResult("list", getPersonList("1"));
			logger.debug("end doGetContactList");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doGetContactList", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * ��ȡ��ʽ�����е���ϵ����Ϣ��doGetConsigneeList��doGetContactList�ľ����������
	 * @param type ��ϵ�����1����ϵ�ˣ�2���ջ���
	 * @return
	 */
	protected List<ContactInfo> getPersonList(String type) throws Exception
	{
		if (!VendorInfoUtil.checkKeyFields(vendorInfo)) {
			logger.info("����Ϊ�գ�");
			setErrorReason("����Ϊ�գ�");
			return null;
		}
		VendorContactMgr mgr = (VendorContactMgr)getBean("vendorContactMgr");
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setState("0");
		contactInfo.setConType(type);
		Integer id = vendorInfo.getBaseSeqId();
		if(id != null)
			contactInfo.setBaseSeqId(id.toString());
		else
			contactInfo.setCommCode(vendorInfo.getCommCode());
		
		return mgr.listContactInfo(contactInfo);
	}
	
	/**
	 * ����seqId��ȡ��ϵ����Ϣ
	 * @action.input seqId
	 * @action.result contactInfo.*
	 * @return
	 */
	public String doGetContactInfoById() {
		try {
			String s = this.getHttpServletRequest().getParameter("seqId");
			logger.debug("begin doGetContactInfoById " + s);
			if(StringUtils.isEmpty(s)) {
				logger.info("����Ϊ�գ�");
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			VendorContactMgr mgr = (VendorContactMgr)getBean("vendorContactMgr");
			setResult("contactInfo", mgr.getContactInfo(s));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doGetContactInfoById", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * ����STAFF��ϢΪ��ǰ�û���Ϣ
	 * 
	 * @param setName �Ƿ������û�����
	 * 	Ϊtrueʱ����staffNameΪ��ǰ�û���staffName��Ϊfalseʱ����staffNameΪnull��
	 * 	�ڲ�ѯʱ��Ϊfalse����������ʱ��Ϊtrue��
	 * @throws Exception
	 */
	protected void setMyId(boolean setName) throws Exception {
		vendorInfo.setStaffId(getLoginStaff().getStaffId().toString());
		vendorInfo.setStaffName(setName ? getLoginStaff().getStaffName() : null);
	}

}
