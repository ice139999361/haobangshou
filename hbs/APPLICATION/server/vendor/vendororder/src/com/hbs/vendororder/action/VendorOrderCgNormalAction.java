package com.hbs.vendororder.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.vendororder.constants.VendorOrderConstants;
import com.hbs.vendororder.manager.VendorOrderMgr;

@SuppressWarnings("serial")
public class VendorOrderCgNormalAction extends VendorOrderBaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(VendorOrderCgNormalAction.class);

	@Override
	protected boolean getIsManager() {
		return false;
	}

	@Override
	public String getRoleName() {
		return "cgnormal";
	}

	/**
	 * ��ʱ���湩Ӧ�̶���
	 * @action.input	vendorOrder.*
	 * @action.result	poNo	����ʱ���ض�����
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String doSaveTemp() {
		try {
			logger.debug("begin doSaveTemp");
			if (vendorOrder == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			
			if(StringUtils.isEmpty(vendorOrder.getPoNoType()))
				vendorOrder.setPoNoType(VendorOrderConstants.VENDOR_PO_NO_TYPE_0);
			if(StringUtils.isEmpty(vendorOrder.getActiveState()))
				vendorOrder.setActiveState(VendorOrderConstants.VENDOR_ORDER_ACTIVE_STATE);
			vendorOrder.setState(VendorOrderConstants.VENDOR_ORDER_STATE_01);
			if(vendorOrder.getCreateTime() == null)
				vendorOrder.setCreateTime(new Date());
			if(StringUtils.isEmpty(vendorOrder.getStaffId()))
			{
				setMyId(true);
			}
			
			// DONE:listdata�����
			Map otherData = new HashMap();
			if(!VendorOrderUtil.checkCommCode(vendorOrder, otherData)) {
				logger.info("��Ӧ�̱������");
				setErrorReason("��Ӧ�̱������");
				return ERROR;
			}
			VendorOrderUtil.processListData(vendorOrder, this.getHttpServletRequest(), otherData);
			List<FieldErr> errs = VendorOrderUtil.checkInputFields(vendorOrder, otherData);
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			
			VendorOrderMgr mgr = getMgr();
			boolean isNew = StringUtils.isEmpty(vendorOrder.getPoNo());
			int i = mgr.saveTempVendorOrder(vendorOrder, "");
			if(i != 0) {
				logger.info("��ʱ����ʧ�ܣ�");
				setErrorReason("����ʧ�ܣ�");
				return ERROR;
			}
			if(isNew) {
				logger.info("doSaveTemp new poNo = " + vendorOrder.getPoNo());
				setResult("poNo", vendorOrder.getPoNo());
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
	 * ��ʽ���湩Ӧ�̶���
	 * @action.input	vendorOrder.*
	 * @action.result	poNo	����ʱ���ض�����
	 * @return
	 */
	public String doSave() {
		try {
			logger.debug("begin doSave");
			if (vendorOrder == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			boolean isNew = StringUtils.isEmpty((vendorOrder.getPoNo()));
			boolean isTemp = VendorOrderConstants.VENDOR_ORDER_STATE_01.equals(vendorOrder.getState());
			// ֻ���¼�¼��״̬Ϊ��ʱ�������ȷ
			if(isNew || isTemp) {
				// ��ִ����ʱ����
				String ret = doSaveTemp();
				if(ret.equals(SUCCESS)) {
					// �ٻ�ȡ���ݣ���ʽ�ύ
					VendorOrderMgr mgr = getMgr();
					vendorOrder = mgr.getVendorOrder(vendorOrder.getCommCode(), vendorOrder.getPoNo(), true);
					int i = mgr.commitVendorOrder(vendorOrder, null);
					if(i != 0) {
						logger.info("�ύʧ�ܣ�");
						setErrorReason("�ύʧ�ܣ�");
						return ERROR;
					} else {
						// �ɹ�
					}
				} else {
					return ret;
				}
			} else {
				logger.info("״̬����");
				setErrorReason("״̬����");
				return ERROR;
			}
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
	 * @action.input vendorOrder.*
	 * @action.input memo
	 * @return
	 */
	public String doControlActiveState() {
		try {
			
			if(!findVendorOrder()) {
				return ERROR;
			}
			setMyId(true);
			VendorOrderMgr mgr = getMgr();
			int i = mgr.controlActiveState(vendorOrder, this.getHttpServletRequest().getParameter("memo"));
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doControlActiveState", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ȡ������
	 * @action.input vendorOrder.*
	 * @action.input memo
	 * @return
	 */
	public String doCancel() {
		try {
			if(vendorOrder == null
					|| StringUtils.isEmpty(vendorOrder.getCommCode()) 
					|| StringUtils.isEmpty(vendorOrder.getPoNo())) {
				logger.debug("����Ϊ�գ�");
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			setMyId(true);
			VendorOrderMgr mgr = getMgr();
			int i = mgr.cancelVendorOrder(vendorOrder, this.getHttpServletRequest().getParameter("memo"));
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doCancel", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
}
