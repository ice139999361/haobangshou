/**
 * 
 */
package com.hbs.vendorinfo.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
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

			if(vendorPartNoInfo == null)
				vendorPartNoInfo = new VendorPartNoInfo();
			
			if(!checkCommonFields())
				return ERROR;
			
			setPagination(vendorPartNoInfo);
			VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
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

			fixCommCode();

			if(!checkCommonFields())
				return ERROR;

			List<FieldErr> errs = VendorPartNoInfoUtil.checkInputFields(vendorPartNoInfo);
			if(!errs.isEmpty())
			{
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			if(VendorPartNoInfoUtil.checkSetStaffId(vendorPartNoInfo))
				setMyId(true);
			
			VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
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
     * ��ȡ�ͻ����Ϲ�ϵ
     * @action.input	vendorPartNoInfo.seqId
     * @action.result	vendorPartNoInfo.*
     * @return
     */
   public String doGetInfo() {
    	try {
    		VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
    		if(vendorPartNoInfo == null || vendorPartNoInfo.getSeqId() == null) {
    			logger.info("��������");
				setErrorReason("��������");
				return ERROR;
    		}
    		vendorPartNoInfo = mgr.getVendorPartNoInfoByID(vendorPartNoInfo.getSeqId().toString());
    		if(!checkCommonFields())
    			return ERROR;;
    				
    		setResult("vendorPartNoInfo", vendorPartNoInfo);
    		return SUCCESS;
    	} catch(Exception e) {
    		logger.error("catch Exception in doGetInfo.", e);
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
			if(StringUtils.isEmpty(commCode))
			{
				logger.info("��Ӧ�̱���û����д��");
				setErrorReason("��Ӧ�̱���û����д��");
				return false;
			}
			
			//DONE�����Ʒ�Χ
			VendorInfoMgr vendormgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
			VendorInfo vendorInfo = new VendorInfo();
			vendorInfo.setCommCode(commCode);
			vendorInfo.setState("0");
			vendorInfo = vendormgr.getVendorInfo(vendorInfo, false);
			String id = getLoginStaff().getStaffId().toString();
			if(vendorInfo == null || !id.equals(vendorInfo.getStaffId()))
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
	
	protected void fixCommCode()
	{
		try {
			if(vendorPartNoInfo == null)
				return;
			if(StringUtils.isEmpty(vendorPartNoInfo.getCommCode()))
			{
				String s = this.getHttpServletRequest().getParameter("vendorInfo.commCode");
				if(StringUtils.isNotEmpty(s))
				{
					vendorPartNoInfo.setCommCode(s);
				}
			}
		} catch (Exception e) {
			logger.error("catch Exception in fixCommCode", e);
		}
	}
}
