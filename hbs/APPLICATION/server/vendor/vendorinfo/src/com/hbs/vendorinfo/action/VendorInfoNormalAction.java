/**
 * �ͻ���ϢAction
 */
package com.hbs.vendorinfo.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.vendorinfo.manager.VendorInfoMgr;

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
	 * ��ѯ���޶��Լ��ܹ����Ĺ�Ӧ����Ϣ������mgr.getCustomerInfoListByUser��
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
			VendorInfoMgr mgr = (VendorInfoMgr) BeanLocator.getInstance()
					.getBean(vendorInfoMgrName);
			setResult("list", mgr.getVendorInfoList(vendorInfo));
			setTotalCount(mgr.getCustomerInfoCount(vendorInfo));
			setResult("count", getTotalCount());
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
	 * ��ʱ�����û���Ϣ
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

			VendorInfoMgr mgr = (VendorInfoMgr) BeanLocator.getInstance()
					.getBean(vendorInfoMgrName);

			VendorInfo info2 = mgr.getVendorInfo(vendorInfo, false);
			int ret;
			if (info2 != null)
				ret = mgr.updateCustomerInfo(vendorInfo);
			else
				ret = mgr.saveTempVendorInfo(vendorInfo);
			
			if (ret < 0) {
				logger.info("��ʱ���������");
				setErrorReason("��ʱ���������");
				return ERROR;
			}
			if(ret > 0) {
				this.setResult("seqId", ret);
				if (logger.isDebugEnabled()) logger.debug("seqId="+ret);
			}
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

			if (vendorInfo.getState() == null || vendorInfo.getState() == "")
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

			VendorInfoMgr mgr = (VendorInfoMgr) BeanLocator.getInstance()
					.getBean(vendorInfoMgrName);

			VendorInfo info2 = mgr.getVendorInfo(vendorInfo, false);
			int ret;
			if (info2 != null)
				ret = mgr.updateCustomerInfo(vendorInfo);
			else {
				vendorInfo.setState("1");
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
					s = "���������";
				}
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			if(ret > 0) {
				this.setResult("seqId", ret);
				if (logger.isDebugEnabled()) logger.debug("seqId="+ret);
			}
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
			VendorInfoMgr mgr = (VendorInfoMgr) BeanLocator.getInstance()
					.getBean(vendorInfoMgrName);
			vendorInfo = VendorInfoUtil.getVendorInfo(mgr, vendorInfo);
			this.setResult("vendorInfo", vendorInfo);
			if (logger.isDebugEnabled())
				logger.debug("end doGetInfo");
			return SUCCESS;
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
			VendorInfoMgr mgr = (VendorInfoMgr) BeanLocator.getInstance()
					.getBean(vendorInfoMgrName);
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
				return SUCCESS;
			case 2:
				logger.info("״̬����ȷ��");
				setErrorReason("״̬����ȷ��");
				return ERROR;
			default:
				logger.info("ɾ��������");
				setErrorReason("ɾ��������");
				return ERROR;
			}
		} catch (Exception e) {
			logger.error("catch Exception in doGetInfo", e);
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
		vendorInfo.setStaffId(getLoginStaff().getStaffId());
		vendorInfo.setStaffName(setName ? getLoginStaff().getStaffName() : null);
	}

}