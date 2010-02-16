/**
 * system ��hbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.invoice.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.common.dao.baseinfo.InvoiceInfoDao;
import com.hbs.domain.common.pojo.baseinfo.InvoiceInfo;
import com.hbs.invoice.constants.InvoiceConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;

public class SendInvoiceMgr {
	private static final Logger logger = Logger.getLogger(SendInvoiceMgr.class);
	
	/**
	 * ����ͻ���Ʊ��¼������������ǿͻ����ͻ���
	 * @param invoiceInfo
	 * @return >0 ����ɹ�
	 * @throws Exception
	 */
	public int saveSendInvoice(InvoiceInfo invoiceInfo) throws Exception{
		int ret =0;
		logger.debug("�����������Ʊ��¼��Ϣ������Ĳ���Ϊ��" + invoiceInfo.toString());
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.CUST_SEND_INVOICE_DAO);
		ret = dao.insertInvoiceInfo(invoiceInfo);
		//������־
		WareHouseLogUtils.operLog(invoiceInfo.getStaffId(), invoiceInfo.getStaffName(), "����", "�ͻ���Ʊ", invoiceInfo.getLogKey(), null, null);
		return ret;
	}
	/**
	 * �޸Ŀͻ���Ʊ��¼
	 * @param invoiceInfo
	 * @return 0  �ɹ�
	 * @throws Exception
	 */
	public int updateSendInvoice(InvoiceInfo invoiceInfo) throws Exception{
		int ret =0;
		logger.debug("�޸ĳ�������Ʊ��¼��Ϣ������Ĳ���Ϊ��" + invoiceInfo.toString());
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.CUST_SEND_INVOICE_DAO);
		dao.updateInvoiceInfo(invoiceInfo);
		//������־
		WareHouseLogUtils.operLog(invoiceInfo.getStaffId(), invoiceInfo.getStaffName(), "�޸�", "�ͻ���Ʊ", invoiceInfo.getLogKey(), null, null);
		return ret;
	}
	/**
	 * ɾ���ͻ���Ʊ��¼���Ȳ��ң������ڣ��׳��쳣
	 * @param seqId
	 * @return
	 * @throws Exception
	 */
	public int deleteSendInvoice(String seqId) throws Exception{
		int ret =0;
		logger.debug("ɾ����������Ʊ��¼��Ϣ������Ĳ���Ϊ��" + seqId);
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.CUST_SEND_INVOICE_DAO);
		InvoiceInfo existInfo = dao.findInvoiceInfo(seqId);
		if(existInfo != null){
			dao.deleteInvoiceInfo(seqId);
			//������־
			WareHouseLogUtils.operLog(existInfo.getStaffId(), existInfo.getStaffName(), "ɾ��", "�ͻ���Ʊ", existInfo.getLogKey(), existInfo.toString(), null);
		}else{
			throw new Exception("��Ҫɾ���Ŀͻ���Ʊ��Ϣ�����ڣ�����Ĳ���Ϊ��" + seqId);
		}
		return ret;
	}
	
	/**
	 * ����seqid���ҵ����ͻ���Ʊ��¼
	 * @param seqId
	 * @return
	 * @throws Exception
	 */
	public InvoiceInfo getSendInvoice(String seqId) throws Exception{
		InvoiceInfo ret = null;
		logger.debug("���ҵ�����������Ʊ��¼��Ϣ������Ĳ���Ϊ��" + seqId);
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.CUST_SEND_INVOICE_DAO);
		ret = dao.findInvoiceInfo(seqId);		
		return ret;
	}
	/**
	 * �����������������ѯ�ͻ���Ʊ�б��¼
	 * @param invoiceInfo
	 * @return
	 * @throws Exception
	 */
	public List<InvoiceInfo> getSendInvoiceList(InvoiceInfo invoiceInfo) throws Exception{
		List<InvoiceInfo> ret = null;
		logger.debug("��������������Ʊ��¼��Ϣ������Ĳ���Ϊ��" + invoiceInfo.toString());
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.CUST_SEND_INVOICE_DAO);
		ret = dao.listInvoiceInfo(invoiceInfo);
		return ret;
	}
	/**
	 * �����������������ѯ�ͻ���Ʊ�б��¼��
	 * @param invoiceInfo
	 * @return
	 * @throws Exception
	 */
	public int getSendInvoiceListCount(InvoiceInfo invoiceInfo) throws Exception{
		int ret =0;
		logger.debug("��������������Ʊ��¼��Ϣ��¼��������Ĳ���Ϊ��" + invoiceInfo.toString());
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.CUST_SEND_INVOICE_DAO);
		ret = dao.listInvoiceInfoCount(invoiceInfo);
		return ret;
	}
}
