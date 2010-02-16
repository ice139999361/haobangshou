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

public class RecInvoiceMgr {
private static final Logger logger = Logger.getLogger(RecInvoiceMgr.class);
	
	/**
	 * ���湩Ӧ�̷�Ʊ��¼������������ǹ�Ӧ�̵��ͻ���
	 * @param invoiceInfo
	 * @return >0 ����ɹ�
	 * @throws Exception
	 */
	public int saveRecInvoice(InvoiceInfo invoiceInfo) throws Exception{
		int ret =0;
		logger.debug("�����ջ�����Ʊ��¼��Ϣ������Ĳ���Ϊ��" + invoiceInfo.toString());
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.VENDOR_REC_INVOICE_DAO);
		ret = dao.insertInvoiceInfo(invoiceInfo);
		//������־
		WareHouseLogUtils.operLog(invoiceInfo.getStaffId(), invoiceInfo.getStaffName(), "����", "��Ӧ�̷�Ʊ", invoiceInfo.getLogKey(), null, null);
		return ret;
	}
	/**
	 * �޸Ĺ�Ӧ�̷�Ʊ��¼
	 * @param invoiceInfo
	 * @return 0  �ɹ�
	 * @throws Exception
	 */
	public int updateRecInvoice(InvoiceInfo invoiceInfo) throws Exception{
		int ret =0;
		logger.debug("�޸��ջ�����Ʊ��¼��Ϣ������Ĳ���Ϊ��" + invoiceInfo.toString());
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.VENDOR_REC_INVOICE_DAO);
		dao.updateInvoiceInfo(invoiceInfo);
		//������־
		WareHouseLogUtils.operLog(invoiceInfo.getStaffId(), invoiceInfo.getStaffName(), "�޸�", "��Ӧ�̷�Ʊ", invoiceInfo.getLogKey(), null, null);
		return ret;
	}
	/**
	 * ɾ����Ӧ�̷�Ʊ��¼���Ȳ��ң������ڣ��׳��쳣
	 * @param seqId
	 * @return
	 * @throws Exception
	 */
	public int deleteRecInvoice(String seqId) throws Exception{
		int ret =0;
		logger.debug("ɾ���ջ�����Ʊ��¼��Ϣ������Ĳ���Ϊ��" + seqId);
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.VENDOR_REC_INVOICE_DAO);
		InvoiceInfo existInfo = dao.findInvoiceInfo(seqId);
		if(existInfo != null){
			dao.deleteInvoiceInfo(seqId);
			//������־
			WareHouseLogUtils.operLog(existInfo.getStaffId(), existInfo.getStaffName(), "ɾ��", "��Ӧ�̷�Ʊ", existInfo.getLogKey(), existInfo.toString(), null);
		}else{
			throw new Exception("��Ҫɾ���Ĺ�Ӧ�̷�Ʊ��Ϣ�����ڣ�����Ĳ���Ϊ��" + seqId);
		}
		return ret;
	}
	
	/**
	 * ����seqid���ҵ�����Ӧ�̷�Ʊ��¼
	 * @param seqId
	 * @return
	 * @throws Exception
	 */
	public InvoiceInfo getRecInvoice(String seqId) throws Exception{
		InvoiceInfo ret = null;
		logger.debug("���ҵ����ջ�����Ʊ��¼��Ϣ������Ĳ���Ϊ��" + seqId);
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.VENDOR_REC_INVOICE_DAO);
		ret = dao.findInvoiceInfo(seqId);		
		return ret;
	}
	/**
	 * �����������������ѯ��Ӧ�̷�Ʊ�б��¼
	 * @param invoiceInfo
	 * @return
	 * @throws Exception
	 */
	public List<InvoiceInfo> getRecInvoiceList(InvoiceInfo invoiceInfo) throws Exception{
		List<InvoiceInfo> ret = null;
		logger.debug("�������ջ�����Ʊ��¼��Ϣ������Ĳ���Ϊ��" + invoiceInfo.toString());
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.VENDOR_REC_INVOICE_DAO);
		ret = dao.listInvoiceInfo(invoiceInfo);
		return ret;
	}
	/**
	 * �����������������ѯ��Ӧ�̷�Ʊ�б��¼��
	 * @param invoiceInfo
	 * @return
	 * @throws Exception
	 */
	public int getRecInvoiceListCount(InvoiceInfo invoiceInfo) throws Exception{
		int ret =0;
		logger.debug("�������ջ�����Ʊ��¼��Ϣ��¼��������Ĳ���Ϊ��" + invoiceInfo.toString());
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.VENDOR_REC_INVOICE_DAO);
		ret = dao.listInvoiceInfoCount(invoiceInfo);
		return ret;
	}
}
