/**
 * system ：hbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerinfo.action;

import java.util.List;


import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.customerinfo.manager.CustContactMgr;

import com.hbs.domain.common.pojo.baseinfo.ContactInfo;

@SuppressWarnings("serial")
public class CustContactInfoAction extends BaseAction {
	
	private static final Logger logger = Logger
	.getLogger(CustContactInfoAction.class);
	
	private static final String CUST_CONTACT_MGR ="custContactMgr";
	String seqId;
	
	String custCode;

	public String getSeqId() {
		return seqId;
	}

	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	
	public String doGetContactInfo(){
		try {
			if (logger.isDebugEnabled())
				logger.debug("begin doGetContactInfo");
			if (null == seqId) {
				logger.info("参数为空！");
				setErrorReason("参数为空！");
				return ERROR;
			}			
			CustContactMgr mgr = (CustContactMgr)getBean(CUST_CONTACT_MGR);
			ContactInfo contactInfo = mgr.getContactInfo(seqId);			
				this.setResult("contactInfo", contactInfo);
				if (logger.isDebugEnabled())
					logger.debug("end doGetContactInfo");
				return SUCCESS;	
				
		} catch (Exception e) {
			logger.error("catch Exception in doGetContactInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	public String doListContactInfo(){
		try {
			if (logger.isDebugEnabled())
				logger.debug("begin doListContactInfo");
			if (null == custCode) {
				logger.info("参数为空！");
				setErrorReason("参数为空！");
				return ERROR;
			}
			ContactInfo contactInfo = new ContactInfo();
			contactInfo.setCommCode(custCode);
			contactInfo.setState("0");
			contactInfo.setConType("2");
			CustContactMgr mgr = (CustContactMgr)getBean(CUST_CONTACT_MGR);
			List<ContactInfo> list = mgr.listContactInfo(contactInfo);			
				this.setResult("contactInfoList", list);
				if (logger.isDebugEnabled())
					logger.debug("end doListContactInfo");
				return SUCCESS;	
				
		} catch (Exception e) {
			logger.error("catch Exception in doListContactInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
}
