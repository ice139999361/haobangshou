/**
 * 
 */
package com.hbs.customerinfo.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.customerinfo.manager.CustPartNoInfoMgr;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;

/**
 * �ͻ����Ϲ�ϵ��ͨ�û�Action
 * @author xyf
 * @actions doList doSave
 */
@SuppressWarnings("serial")
public class CustPartNoInfoNormalAction extends BaseAction {

	/**
	 * Manager��
	 */
	public static final String custPartNoInfoMgrName = "custPartNoInfoMgr";
		
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustPartNoInfoNormalAction.class);

    CustPartNoInfo custPartNoInfo;
    public CustPartNoInfo getCustPartNoInfo() { return custPartNoInfo; }
    public void setCustPartNoInfo(CustPartNoInfo custPartNoInfo) { this.custPartNoInfo = custPartNoInfo; }
    
    CustomerInfo custInfo;
    
    /**
     * �ͻ����ϱ��
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
     * ��ѯ�ͻ����Ϲ�ϵ���ж����û��Ƿ���Բ鿴��
 	 * @action.input custPartNoInfo.commCode + custPartNoInfo.��ѯ����
	 * @action.result list���б� count������
     * @return
     */
    public String doList()
    {
    	try
    	{
			if (logger.isDebugEnabled())    logger.debug("begin doList");

			if(custPartNoInfo == null)
				custPartNoInfo = new CustPartNoInfo();
			
			if(!checkCommonFields())
				return ERROR;

			setPagination(custPartNoInfo);
			CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
			setResult("list", mgr.listCustPartNoInfo(custPartNoInfo));
			setTotalCount(mgr.listCustPartNoInfoCount(custPartNoInfo));
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
     * ����ͻ����Ϲ�ϵ
     * @action.input	custPartNoInfo.*
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

			//custPartNoInfo.setVendorCode(custInfo.getVendorCode());

			List<FieldErr> errs = CustPartNoInfoUtil.checkInputFields(custPartNoInfo);
			if(!errs.isEmpty())
			{
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			if(CustPartNoInfoUtil.checkSetStaffId(custPartNoInfo))
				setMyId(true);
			//custPartNoInfo.setCreateDate(new Date());
			
			CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
			int i = mgr.commitCustPartNoInfo(custPartNoInfo);
			if(i == -1)
			{
				logger.info("�Ѿ������޸Ĵ��������ݣ�");
				setErrorReason("�Ѿ������޸Ĵ��������ݣ�");
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
     * ��ȡ�ͻ����Ϲ�ϵ
     * @action.input	custPartNoInfo.seqId
     * @action.result	custPartNoInfo.*
     * @return
     */
   public String doGetInfo() {
    	try {
    		CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
    		if(custPartNoInfo == null || custPartNoInfo.getSeqId() == null) {
    			logger.info("��������");
				setErrorReason("��������");
				return ERROR;
    		}
    		custPartNoInfo = mgr.getCustPartNoInfoByID(custPartNoInfo.getSeqId().toString());
//    		if(!checkCommonFields())
//    			return ERROR;;
    		if(null != custPartNoInfo){
	    		//��ȡ�ͻ���Ϣ
	    		CustomerInfoMgr custmgr = (CustomerInfoMgr)getBean(CustomerInfoNormalAction.custInfoMgrName);
	    		CustomerInfo custInfo = new CustomerInfo();
				custInfo.setCommCode(custPartNoInfo.getCommCode());
				custInfo.setState("0");
				custInfo = custmgr.getCustomerInfo(custInfo, false);
				setResult("custInfo", custInfo);
				
    		}else{
    			logger.info("�޶�Ӧ�Ŀͻ�������Ϣ��");
				setErrorReason("�޶�Ӧ�Ŀͻ�������Ϣ��");
				return ERROR;
    		}
    		setResult("custPartNoInfo", custPartNoInfo);
    		return SUCCESS;
    	} catch(Exception e) {
    		logger.error("catch Exception in doGetInfo.", e);
			setErrorReason("�ڲ�����");
			return ERROR;
    	}
    }
   
   /**
    * ��ȡ�ͻ����Ϲ�ϵ add by yangzj
    * @action.input	  �ͻ����ϱ�Ż򱾹�˾���ϱ��
    * @action.result	custPartNoInfo.*
    * @return
    */
  public String doGetInfoDict() {
   	try {
   		
   		if(cpartNo == null && partNo == null) {
   			logger.info("doGetInfoDict() ������������޷���ȡ��");
				setErrorReason("�޷���ȡ�ͻ����Ϲ�ϵ���գ�");
				return ERROR;
   		}else{
   			logger.debug("doGetInfoDict(),����Ĳ���Ϊ��partno=" + partNo +"cpartno=" + cpartNo);
   		}
   		CustPartNoInfo cPartNoInfo = new CustPartNoInfo();
   		cPartNoInfo.setCustPartNo(cpartNo);
   		cPartNoInfo.setPartNo(partNo);
   		cPartNoInfo.setState("0");
   		CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
   		custPartNoInfo = mgr.getCustPartNoInfoByBizKey(cPartNoInfo);   		
   		setResult("custPartNoInfo", custPartNoInfo);
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
	   			logger.info("doGetInfoDict() ������������޷���ȡ��");
					setErrorReason("�޷���ȡ�ͻ����Ϲ�ϵ���գ�");
					return ERROR;
	   		}else{
	   			logger.debug("doGetInfoDict(),����Ĳ���Ϊ��partno=" + partNo +"cpartno=" + cpartNo);
	   		}
	   		CustPartNoInfo cPartNoInfo = new CustPartNoInfo();
	   		cPartNoInfo.setCustPartNo(cpartNo);
	   		cPartNoInfo.setPartNo(partNo);
	   		cPartNoInfo.setState("0");
	   		
	   		CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
			setResult("list", mgr.listCustPartNoInfo(cPartNoInfo));
			//setTotalCount(mgr.listCustPartNoInfoCount(cPartNoInfo));
			//setResult("count", getTotalCount());
	   		return SUCCESS;
	   	} catch(Exception e) {
	   		logger.error("catch Exception in doGetInfo.", e);
				setErrorReason("�ڲ�����");
				return ERROR;
	   	}
	   }
   /**
    * ��ȡ�ͻ����Ϲ�ϵ
    * @action.input	custPartNoInfo.commCode + custPartNoInfo.custPartNo + custPartNoInfo.state�����stateû����д����Ĭ��state=0
    * @action.result	custPartNoInfo.*
    * @return
    */
  public String doGetInfoByBizKey() {
   	try {
   		CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
   		if(custPartNoInfo == null || custPartNoInfo.getSeqId() == null || StringUtils.isEmpty(custPartNoInfo.getCustPartNo())) {
   			logger.info("��������");
				setErrorReason("��������");
				return ERROR;
   		}
   		if(StringUtils.isEmpty(custPartNoInfo.getState()))
   			custPartNoInfo.setState("0");
   		
   		custPartNoInfo = mgr.getCustPartNoInfoByBizKey(custPartNoInfo);
   		
   		if(!checkCommonFields())
   			return ERROR;;
   				
   		setResult("custPartNoInfo", custPartNoInfo);
   		return SUCCESS;
   	} catch(Exception e) {
   		logger.error("catch Exception in doGetInfo.", e);
			setErrorReason("�ڲ�����");
			return ERROR;
   	}
   }
  

  	/**
  	 * ��ȡ���ϵ���ʷ�۸�䶯����ͨ��������ȡ������ʷ��¼
  	 * @action.input custPartNoInfo.* partNo custPartNo commCode
  	 * @action.result list	List<OperLog>
  	 * @return
  	 */
  	public String doGetPriceChange(){
  		try {
	   		CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
	   		if(custPartNoInfo == null || custPartNoInfo.getPartNo() == null || custPartNoInfo.getCommCode() == null || StringUtils.isEmpty(custPartNoInfo.getCustPartNo())) {
	   			logger.info("��������");
					setErrorReason("��������");
					return ERROR;
	   		}
	   		if(StringUtils.isEmpty(custPartNoInfo.getState()))
	   			custPartNoInfo.setState("0");
	   		
	   		setResult("list", mgr.getPartNoChange(custPartNoInfo));
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
		custPartNoInfo.setStaffId(getLoginStaff().getStaffId().toString());
		custPartNoInfo.setStaffName(setName ? getLoginStaff().getStaffName() : null);
	}
	
	/**
	 * ���ͻ������Ƿ���д�����ж��Ƿ���Ȩ�޲���������������⣬��������������ErrorReaseon��
	 * @return
	 */
	protected boolean checkCommonFields()
	{
		try{
			if(custPartNoInfo == null)
			{
				logger.info("��������");
				setErrorReason("��������");
				return false;
			}
			
			String commCode = custPartNoInfo.getCommCode();
			if(StringUtils.isEmpty(commCode))
			{
				logger.info("�ͻ�����û����д��");
				setErrorReason("�ͻ�����û����д��");
				return false;
			}
			
			//DONE�����Ʒ�Χ
			CustomerInfoMgr custmgr = (CustomerInfoMgr)getBean(CustomerInfoNormalAction.custInfoMgrName);
			custInfo = new CustomerInfo();
			custInfo.setCommCode(commCode);
			custInfo.setState("0");
			custInfo = custmgr.getCustomerInfo(custInfo, false);
			if(custInfo == null) {
				logger.info("�ͻ��������");
				setErrorReason("�ͻ��������");
				return false;
			}
			String id = getLoginStaff().getStaffId().toString();
			if(custInfo == null || (!id.equals(custInfo.getStaffId()) && !id.equals(custInfo.getAssStaffId())))
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
			if(custPartNoInfo == null)
				return;
			if(StringUtils.isEmpty(custPartNoInfo.getCommCode()))
			{
				String s = this.getHttpServletRequest().getParameter("custInfo.commCode");
				if(StringUtils.isNotEmpty(s))
				{
					custPartNoInfo.setCommCode(s);
				}
			}
		} catch (Exception e) {
			logger.error("catch Exception in fixCommCode", e);
		}
	}
}
