/**
 * 
 */
package com.hbs.vendorinfo.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorPartNoInfo;
import com.hbs.vendorinfo.manager.VendorInfoMgr;
import com.hbs.vendorinfo.manager.VendorPartNoInfoMgr;

/**
 * ��Ӧ�����Ϲ�ϵ��ͨ�û�Action
 * @author xyf
 * @actions doList doSave
 */
@SuppressWarnings("serial")
public class VendorPartNoInfoNormalAction extends BaseAction {

	/**
	 * Manager��
	 */
	static final String vendorPartNoInfoMgrName = "vendorPartNoInfoMgr";
		
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorPartNoInfoNormalAction.class);

    VendorPartNoInfo vendorPartNoInfo;
    public VendorPartNoInfo getVendorPartNoInfo() { return vendorPartNoInfo; }
    public void setVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) { this.vendorPartNoInfo = vendorPartNoInfo; }
    
    /**
     * ��ѯ��Ӧ�����Ϲ�ϵ���ж����û��Ƿ���Բ鿴��
 	 * @action.input vendorPartNoInfo.commCode + vendorPartNoInfo.��ѯ����
	 * @action.result list���б� count������
     * @return
     */
    public String doList()
    {
    	try
    	{
			if (logger.isDebugEnabled())    logger.debug("begin doList");

			if(!checkCommonFields())
				return ERROR;
			
			setPagination(vendorPartNoInfo);
			VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)BeanLocator.getInstance().getBean(vendorPartNoInfoMgrName);
			setResult("list", mgr.listVendorPartNoInfo(vendorPartNoInfo));
			setTotalCount(mgr.listVendorPartNoInfoCount(vendorPartNoInfo));
			setResult("count", getTotalCount());
			if (logger.isDebugEnabled())    logger.debug("end doList");
    		return SUCCESS;
    	}
    	catch(Exception e)
    	{
    		logger.error("catch Exception in doList.", e);
			setErrorReason("�ڲ�����");
			return ERROR;
    	}
    }
    
    /**
     * ���湩Ӧ�����Ϲ�ϵ
     * @action.input	vendorPartNoInfo.*
     * @return
     */
    public String doSave()
    {
    	try
    	{
			if (logger.isDebugEnabled())    logger.debug("begin doSave");

			if(!checkCommonFields())
				return ERROR;

			List<FieldErr> errs = VendorPartNoInfoUtil.checkInputFields(vendorPartNoInfo);
			if(errs.isEmpty())
			{
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			if(VendorPartNoInfoUtil.checkSetStaffId(vendorPartNoInfo))
				setMyId(true);
			
			VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)BeanLocator.getInstance().getBean(vendorPartNoInfoMgrName);
			int i = mgr.commitVendorPartNoInfo(vendorPartNoInfo);
			if(i != 0)
			{
				logger.info("�������");
				setErrorReason("�������");
				return ERROR;
			}
			this.setAlertMsg("�ύ�ɹ���");
			if (logger.isDebugEnabled())    logger.debug("end doSave");
    		return SUCCESS;
    	}
    	catch(Exception e)
    	{
    		logger.error("catch Exception in doSave.", e);
			setErrorReason("�ڲ�����");
			return ERROR;
    	}
    }
    
	/**
	 * ����STAFF��ϢΪ��ǰ�û���Ϣ
	 * @param setName �Ƿ������û�����Ϊtrueʱ����staffNameΪ��ǰ�û���staffName��Ϊfalseʱ����staffNameΪnull��
	 * �ڲ�ѯʱ��Ϊfalse����������ʱ��Ϊtrue��
	 * @throws Exception
	 */
	private void setMyId(boolean setName) throws Exception {
		vendorPartNoInfo.setStaffId(getLoginStaff().getStaffId().toString());
		vendorPartNoInfo.setStaffName(setName ? getLoginStaff().getStaffName() : null);
	}
	
	/**
	 * ��鹩Ӧ�̱����Ƿ���д�����ж��Ƿ���Ȩ�޲���������������⣬��������������ErrorReaseon��
	 * @return
	 */
	protected boolean checkCommonFields()
	{
		try{
			if(vendorPartNoInfo == null)
			{
				logger.info("��������");
				setErrorReason("��������");
				return false;
			}
			
			String commCode = vendorPartNoInfo.getCommCode();
			if(commCode == null || commCode.length() == 0)
			{
				logger.info("��Ӧ�̱���û����д��");
				setErrorReason("��Ӧ�̱���û����д��");
				return false;
			}
			
			//DONE�����Ʒ�Χ
			VendorInfoMgr vendormgr = (VendorInfoMgr)BeanLocator.getInstance().getBean(VendorInfoNormalAction.vendorInfoMgrName);
			VendorInfo custInfo = new VendorInfo();
			custInfo = vendormgr.getVendorInfo(custInfo, false);
			String id = getLoginStaff().getStaffId().toString();
			if(custInfo == null || custInfo.getStaffId() != id)
			{
				logger.info("��û��Ȩ�޷��ʣ�");
				setErrorReason("��û��Ȩ�޷��ʣ�");
				return false;
			}
			return true;
		}catch(Exception e){
			logger.error("catch Exception in checkCommonFields", e);
			setErrorReason("�ڲ�����");
			return false;
		}
	}
}
