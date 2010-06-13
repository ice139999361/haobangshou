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
	public static final String vendorPartNoInfoMgrName = "vendorPartNoInfoMgr";
		
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorPartNoInfoNormalAction.class);

    VendorPartNoInfo vendorPartNoInfo;
    public VendorPartNoInfo getVendorPartNoInfo() { return vendorPartNoInfo; }
    public void setVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) { this.vendorPartNoInfo = vendorPartNoInfo; }
    
    //VendorInfo vendorInfo;
    
    /**
     * ��Ӧ�����ϱ��
     */
    private String cpartNo;
    /**
     * ����˾���ϱ��
     */
    private String partNo;
    
    public String getCpartNo() {
		return cpartNo;
	}
	public void setCpartNo(String cpartNo) {
		this.cpartNo = cpartNo;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

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
			
			if(!checkListFields())
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
			if(i == -1)
			{
				logger.info("���ύ������Ϣ���ڵȴ����������������������޸ģ�");
				setErrorReason("���ύ������Ϣ���ڵȴ����������������������޸ģ�");
				return ERROR;
			}else if( i == 0 ){
				this.setAlertMsg("�ύ�ɹ���");
				if (logger.isDebugEnabled())    logger.debug("end doSave");
	    		return SUCCESS;
			}else{
				logger.info("����ʧ�ܣ�");
				setErrorReason("����ʧ�ܣ�");
				return ERROR;
			}
    	}
    	catch(Exception e)
    	{
    		logger.error("catch Exception in doSave.", e);
			setErrorReason("�ڲ�����");
			return ERROR;
    	}
    }
    
    /**
     * ��ȡ��Ӧ�����Ϲ�ϵ
     * @action.input	vendorPartNoInfo.seqId
     * @action.result	vendorPartNoInfo.*
     * @return
     */
   public String doGetInfo() {
    	try {
    		if (logger.isDebugEnabled())    logger.debug("begin doGetInfo()");
    		VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
    		if(vendorPartNoInfo == null || vendorPartNoInfo.getSeqId() == null) {
    			logger.info("��������");
				setErrorReason("��������");
				return ERROR;
    		}else{
    			logger.debug("doGetInfo(),����Ĳ���Ϊ��" + vendorPartNoInfo.toString());
    		}
    		vendorPartNoInfo = mgr.getVendorPartNoInfoByID(vendorPartNoInfo.getSeqId().toString());
    		//���ﲻ��Ҫ����˰�
    		//getVendorPartNoInfoByID����nullʱҲ���Է���ʧ�ܡ�
//    		if(!checkCommonFields())
//    			return ERROR;;
    		if(null != vendorPartNoInfo){
    			VendorInfoMgr vendormgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
    			VendorInfo vendorInfo = new VendorInfo();
    			vendorInfo.setCommCode(vendorPartNoInfo.getCommCode());
    			vendorInfo.setState("0");
    			vendorInfo = vendormgr.getVendorInfo(vendorInfo, false);
				setResult("vendorInfo", vendorInfo);
				
    		}else{
    			logger.info("�޶�Ӧ�Ĺ�Ӧ��������Ϣ��");
				setErrorReason("�޶�Ӧ�Ĺ�Ӧ��������Ϣ��");
				return ERROR;
    		}		
    		setResult("vendorPartNoInfo", vendorPartNoInfo);
    		return SUCCESS;
    	} catch(Exception e) {
    		logger.error("catch Exception in doGetInfo.", e);
			setErrorReason("�ڲ�����");
			return ERROR;
    	}
    }
 
   /**
    * ��ȡ��Ӧ�����Ϲ�ϵ add by xyf
    * @action.input	  ��Ӧ�����ϱ�Ż򱾹�˾���ϱ��
    * @action.result	vendorPartNoInfo.*
    * @return
    */
  public String doGetInfoDict() {
   	try {
   		
   		if(cpartNo == null && partNo == null) {
   			logger.info("doGetInfoDict() ������������޷���ȡ��");
				setErrorReason("�޷���ȡ��Ӧ�����Ϲ�ϵ���գ�");
				return ERROR;
   		}else{
   			logger.debug("doGetInfoDict(),����Ĳ���Ϊ��partno=" + partNo +" cpartno=" + cpartNo);
   		}
   		VendorPartNoInfo cPartNoInfo = new VendorPartNoInfo();
   		cPartNoInfo.setCustPartNo(cpartNo);
   		cPartNoInfo.setPartNo(partNo);
   		cPartNoInfo.setState("0");
   		VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
   		vendorPartNoInfo = mgr.getVendorPartNoInfoByBizKey(cPartNoInfo);   		
   		setResult("vendorPartNoInfo", vendorPartNoInfo);
   		return SUCCESS;
   	} catch(Exception e) {
   		logger.error("catch Exception in doGetInfo.", e);
			setErrorReason("�ڲ�����");
			return ERROR;
   	}
   }
    
  public String doListDict() {
	   	try {
	   		
	   		if(cpartNo == null && partNo == null) {
	   			logger.info("doListDict() ������������޷���ȡ��");
					setErrorReason("�޷���ȡ��Ӧ�����Ϲ�ϵ���գ�");
					return ERROR;
	   		}else{
	   			logger.debug("doListDict(),����Ĳ���Ϊ��partno=" + partNo +"cpartno=" + cpartNo);
	   		}
	   		VendorPartNoInfo cPartNoInfo = new VendorPartNoInfo();
	   		cPartNoInfo.setCustPartNo(cpartNo);
	   		cPartNoInfo.setPartNo(partNo);
	   		cPartNoInfo.setState("0");
	   		
	   		VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
			setResult("list", mgr.listVendorPartNoInfo(cPartNoInfo));
			//setTotalCount(mgr.listCustPartNoInfoCount(cPartNoInfo));
			//setResult("count", getTotalCount());
	   		return SUCCESS;
	   	} catch(Exception e) {
	   		logger.error("catch Exception in doListDict.", e);
				setErrorReason("�ڲ�����");
				return ERROR;
	   	}
	   }
   /**
    * ��ȡ��Ӧ�����Ϲ�ϵ
    * @action.input	vendorPartNoInfo.commCode + vendorPartNoInfo.custPartNo + vendorPartNoInfo.state�����stateû����д����Ĭ��state=0
    * @action.result	vendorPartNoInfo.*
    * @return
    */
  public String doGetInfoByBizKey() {
   	try {
   		VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
   		if(vendorPartNoInfo == null || vendorPartNoInfo.getSeqId() == null || StringUtils.isEmpty(vendorPartNoInfo.getCustPartNo())) {
   			logger.info("��������");
				setErrorReason("��������");
				return ERROR;
   		}
   		if(StringUtils.isEmpty(vendorPartNoInfo.getState()))
   			vendorPartNoInfo.setState("0");
   		
   		vendorPartNoInfo = mgr.getVendorPartNoInfoByBizKey(vendorPartNoInfo);
   		
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
 	 * ��ȡ���ϵ���ʷ�۸�䶯����ͨ��������ȡ������ʷ��¼
 	 * @action.input vendorPartNoInfo.* partNo custPartNo commCode
 	 * @action.result list	List<OperLog>
 	 * @return
 	 */
 	public String doGetPriceChange(){
 		try {
	   		VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
	   		if(vendorPartNoInfo == null || vendorPartNoInfo.getPartNo() == null || vendorPartNoInfo.getCommCode() == null || StringUtils.isEmpty(vendorPartNoInfo.getCustPartNo())) {
	   			logger.info("��������");
					setErrorReason("��������");
					return ERROR;
	   		}
	   		if(StringUtils.isEmpty(vendorPartNoInfo.getState()))
	   			vendorPartNoInfo.setState("0");
	   		
	   		setResult("list", mgr.getPartNoChange(vendorPartNoInfo));
	   		return SUCCESS;
 		} catch(Exception e) {
 			logger.error("catch Exception in doGetPriceChange.", e);
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
				logger.info("�������������������Ĺ�Ӧ��������Ϣ��");
				setErrorReason("�������������������Ĺ�Ӧ��������Ϣ��");
				return false;
			}
			
			String commCode = vendorPartNoInfo.getCommCode();
			String shortName = vendorPartNoInfo.getShortName();
			if(StringUtils.isEmpty(commCode) && StringUtils.isEmpty(shortName))
			{
				logger.info("��Ӧ�̱������û����д��");
				setErrorReason("��Ӧ�̱������û����д��");
				return false;
			}
			
			//DONE�����Ʒ�Χ
			VendorInfoMgr vendormgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
			VendorInfo vendorInfo = new VendorInfo();
			vendorInfo.setCommCode(commCode);
			vendorInfo.setShortName(shortName);
			vendorInfo.setState("0");
			vendorInfo = vendormgr.getVendorInfo(vendorInfo, false);
			if(vendorInfo == null) {
				logger.info("��Ӧ�̱������");
				setErrorReason("�޶�Ӧ�Ĺ�Ӧ����Ϣ��");
				return false;
			}
			String id = getLoginStaff().getStaffId().toString();
			
			if( !id.equals(vendorInfo.getStaffId()))
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
	protected boolean checkListFields()
	{
		try{
			if(vendorPartNoInfo == null)
			{
				vendorPartNoInfo = new VendorPartNoInfo();
				String id = getLoginStaff().getStaffId().toString();
				vendorPartNoInfo.setField("operId", id);
				return true;
			}else{
				String commCode = vendorPartNoInfo.getCommCode();
				String shortName = vendorPartNoInfo.getShortName();
				if(StringUtils.isEmpty(commCode) && StringUtils.isEmpty(shortName))
				{
					String id = getLoginStaff().getStaffId().toString();
					vendorPartNoInfo.setField("operId", id);
					return true;
				}else{
					VendorInfoMgr vendormgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
					VendorInfo vendorInfo = new VendorInfo();
					vendorInfo.setCommCode(commCode);
					vendorInfo.setShortName(shortName);
					vendorInfo.setState("0");
					vendorInfo = vendormgr.getVendorInfo(vendorInfo, false);
					if(vendorInfo == null) {
						logger.info("����д�Ĺ�Ӧ�̱��벻���ڣ�����¼�빩Ӧ����Ϣ��");
						setErrorReason("����д�Ĺ�Ӧ�̱��벻���ڣ�����¼�빩Ӧ����Ϣ��");
						return false;
					}
					String id = getLoginStaff().getStaffId().toString();
					
					if( !id.equals(vendorInfo.getStaffId()))
					{
						logger.info("����ѯ�Ĺ�Ӧ�̲�����������û��Ȩ�޷��ʣ�");
						setErrorReason("����ѯ�Ĺ�Ӧ�̲�����������û��Ȩ�޷��ʣ�");
						return false;
					}
				}
			}
			
			return true;
		}catch(Exception e){
			logger.error("catch Exception in checkListFields", e);
			setErrorReason("�ڲ�����");
			return false;
		}
	}
	protected void fixCommCode()
	{
//		try {
//			if(vendorPartNoInfo == null)
//				return;
//			if(StringUtils.isEmpty(vendorPartNoInfo.getCommCode()))
//			{
//				String s = this.getHttpServletRequest().getParameter("vendorInfo.commCode");
//				if(StringUtils.isNotEmpty(s))
//				{
//					vendorPartNoInfo.setCommCode(s);
//				}
//			}
//		} catch (Exception e) {
//			logger.error("catch Exception in fixCommCode", e);
//		}
	}
}
