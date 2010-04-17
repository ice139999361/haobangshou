/**
 * 
 */
package com.hbs.invoice.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.common.pojo.baseinfo.InvoiceInfo;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public abstract class InvoiceBaseAction extends BaseAction {

	protected InvoiceInfo invoice;

	public InvoiceInfo getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceInfo invoice) {
		this.invoice = invoice;
	}
	
	protected abstract Logger getLogger();
	
	/**
	 * ����id��ȡ��Ʊ��Ϣ�������ʵ���mgr
	 * @return
	 * @throws Exception
	 */
	protected abstract InvoiceInfo getInvoiceByIdAbstract() throws Exception;
	
	/**
	 * ��ѯ��Ʊ�������ʵ���mgr
	 * @throws Exception
	 */
	protected abstract List<InvoiceInfo> listInvoiceAbstract() throws Exception;
	
	/**
	 * ��ѯ��Ʊ�����������ʵ���mgr
	 * @throws Exception
	 */
	protected abstract int listInvoiceCountAbstract() throws Exception;

	/**
	 * ������Ʊ
	 * @return
	 * @throws Exception
	 */
	protected abstract int saveInvoiceAbstract() throws Exception;

	/**
	 * ���·�Ʊ
	 * @return
	 * @throws Exception
	 */
	protected abstract int updateInvoiceAbstract() throws Exception;

	/**
	 * ɾ����Ʊ
	 * @return
	 * @throws Exception
	 */
	protected abstract int deleteInvoiceAbstract() throws Exception;

	/**
	 * �����Ƿ����ڲ�ʹ�á�true���鿴���е����ύ���ݣ���������ʱ���ݣ���false���鿴�Լ������ݣ�����staffId��
	 * @return
	 */
	protected abstract boolean getIsManager();

	/**
	 * ���ؽ�ɫ��
	 * @return
	 */
	public abstract String getRoleName();
	
	/**
	 * ��ȡ��Ʊ��Ϣ
	 * @return
	 * @throws Exception
	 */
	protected InvoiceInfo getInvoiceInfo() throws Exception {
		if(invoice == null || !InvoiceUtil.checkKeyFields(invoice)){
			getLogger().debug("����Ϊ�գ�");
			setErrorReason("����Ϊ�գ�");
			return null;
		}
		InvoiceInfo invoice2 = null;
		if(invoice.getInvoiceSeqId() != null)
			invoice2 = getInvoiceByIdAbstract();
		else
			invoice2 = getInvoiceByKey();
		
		return invoice2;
	}
	
	/**
	 * ���ݹؼ��ֶλ�ȡ��Ʊ��Ϣ�������ʵ���mgr
	 * @return
	 * @throws Exception
	 */
	protected InvoiceInfo getInvoiceByKey() throws Exception {
		List<InvoiceInfo> list = listInvoiceAbstract();
		if(list == null || list.size() <= 1){
			getLogger().info("û���ҵ�");
			setErrorReason("û���ҵ�");
			return null;
		}
		invoice = list.get(0);
		return getInvoiceByIdAbstract();
	}

	/**
	 * ��ȡ��Ʊ��Ϣ
	 * @action.input invoice.*
	 * @action.result invoice.*
	 * @return
	 */
	public String doGetInfo() {
		try {
			invoice = getInvoiceInfo();
			if(invoice == null)
				return ERROR;
			else if(!getIsManager() && !invoice.getStaffId().equals(getLoginStaff().getStaffId().toString())) {
				getLogger().debug("Ȩ�޴���");
				setErrorReason("Ȩ�޴���");
				return ERROR;
			}
			setResult("invoice", invoice);
			
			return SUCCESS;
		} catch (Exception e) {
			getLogger().error("catch Exception in doGetInfo", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ��ѯ
	 * @action.input invoice.��ѯ����
	 * @action.result list���б� count������
	 * @return
	 */
	public String doList() {
		try {
			getLogger().debug("begin doList");
			if(invoice == null)
				invoice = new InvoiceInfo();
			if(getIsManager())
				invoice.setField("noState01", true);
			else
				setMyId(false);
			setPagination(invoice);
			setResult("list", listInvoiceAbstract());
			setTotalCount(listInvoiceCountAbstract());
			setResult("count", getTotalCount());
			//setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeWarehouseSendState, getRoleName()));
			getLogger().debug("end doList");
			return SUCCESS;
		} catch(Exception e) {
			getLogger().error("catch Exception in doList", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ����
	 * @action.input invoice.*
	 * @return
	 */
	public String doSave() {
		try {
			getLogger().debug("begin doSave");
			if (invoice == null) {
				getLogger().info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			if(StringUtils.isEmpty(invoice.getStaffId()))
				setMyId(true);
			if(invoice.getCreateTime() == null)
				invoice.setCreateTime(new Date());
			
			List<FieldErr> errs = InvoiceUtil.checkInputFields(invoice, null);
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				getLogger().info(s);
				setErrorReason(s);
				return ERROR;
			}
			
			int i;
			if(invoice.getInvoiceSeqId() == null){
				i = saveInvoiceAbstract();
				if(i > 0){
					invoice.setInvoiceSeqId(i);
					setResult("seqId", i);
					i = 0;
				}else if(i == 0)
					i = -999;
			}else
				i = updateInvoiceAbstract();
			
			if(i != 0) {
				String s = "����ʧ�ܣ�";
				getLogger().info(s + " i=" + i);
				setErrorReason(s);
				return ERROR;
			}
			
			// DONE��doSave
			getLogger().debug("end doSave");
			return SUCCESS;
		} catch(Exception e) {
			getLogger().error("catch Exception in doSave", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}		
	}
	
	/**
	 * ɾ����Ʊ
	 * @action.input invoice.*
	 * @return
	 */
	public String doDelete() {
		getLogger().debug("����ɾ����Ʊ����");
		try{
			if(!InvoiceUtil.checkKeyFields(invoice)){
				getLogger().info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			int i = deleteInvoiceAbstract();
			if(i != 0) {
				getLogger().info("ɾ��ʧ�ܣ�");
				setErrorReason("ɾ��ʧ�ܣ�");
				return ERROR;
			}
			return SUCCESS;
		} catch(Exception e) {
			getLogger().error("catch Exception in doDelete", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}	
	}
	
	protected void setMyId(boolean setName) throws Exception {
		invoice.setStaffId(getLoginStaff().getStaffId().toString());
		if(setName)
			invoice.setStaffName(getLoginStaff().getStaffName());
	}
}
