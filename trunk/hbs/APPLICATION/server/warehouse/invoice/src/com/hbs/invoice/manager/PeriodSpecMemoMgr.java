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


import com.hbs.domain.invoice.dao.PeriodSpecMemoDao;
import com.hbs.domain.invoice.pojo.PeriodSpecMemo;
import com.hbs.invoice.constants.InvoiceConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;

public class PeriodSpecMemoMgr {
	private static final Logger logger = Logger.getLogger(PeriodSpecMemoMgr.class);
	
	/**
	 * ����������ڽ��㱸ע��Ϣ
	 * @param specMemo
	 * @return =0 ����ɹ�
	 * @throws Exception
	 */
	public int saveSepcMemo(PeriodSpecMemo specMemo) throws Exception{
		int ret =0;
		logger.debug("����������ڽ��㱸ע��Ϣ������Ĳ���Ϊ��" + specMemo.toString());
		PeriodSpecMemoDao dao = (PeriodSpecMemoDao)BeanLocator.getInstance().getBean(InvoiceConstants.PERIOD_SPEC_MEMO_DAO);
		dao.insertPeriodSpecMemo(specMemo);
		//������־
		WareHouseLogUtils.operLog(specMemo.getStaffId(), specMemo.getStaffName(), "����", "���㱸ע", specMemo.getLogKey(), null, null);
		return ret;
	}
	/**
	 * �޸Ĳ������ڽ��㱸ע��Ϣ
	 * @param specMemo
	 * @return 0  �ɹ�
	 * @throws Exception
	 */
	public int updateSepcMemo(PeriodSpecMemo specMemo) throws Exception{
		int ret =0;
		logger.debug("�޸ĳ�������Ʊ��¼��Ϣ������Ĳ���Ϊ��" + specMemo.toString());
		PeriodSpecMemoDao dao = (PeriodSpecMemoDao)BeanLocator.getInstance().getBean(InvoiceConstants.PERIOD_SPEC_MEMO_DAO);
		dao.updatePeriodSpecMemo(specMemo);
		//������־
		WareHouseLogUtils.operLog(specMemo.getStaffId(), specMemo.getStaffName(), "�޸�", "���㱸ע", specMemo.getLogKey(), null, null);
		return ret;
	}
	/**
	 * ɾ���������ڽ��㱸ע��Ϣ���Ȳ��ң������ڣ��׳��쳣
	 * @param commCode
	 * @return
	 * @throws Exception
	 */
	public int deleteSepcMemo(String commCode) throws Exception{
		int ret =0;
		logger.debug("ɾ���������ڽ��㱸ע��Ϣ������Ĳ���Ϊ��" + commCode);
		PeriodSpecMemoDao dao = (PeriodSpecMemoDao)BeanLocator.getInstance().getBean(InvoiceConstants.PERIOD_SPEC_MEMO_DAO);
		PeriodSpecMemo specMemo = dao.findPeriodSpecMemo(commCode);
		if(specMemo != null){
			dao.deletePeriodSpecMemo(commCode);
			//������־
			WareHouseLogUtils.operLog(specMemo.getStaffId(), specMemo.getStaffName(), "ɾ��", "���㱸ע", specMemo.getLogKey(), specMemo.toString(), null);
		}else{
			throw new Exception("��Ҫɾ���Ĳ������ڽ��㱸ע��Ϣ�����ڣ�����Ĳ���Ϊ��" + commCode);
		}
		return ret;
	}
	
	/**
	 * ����commCode���ҵ����������ڽ��㱸ע��Ϣ
	 * @param commCode
	 * @return
	 * @throws Exception
	 */
	public PeriodSpecMemo getSepcMemo(String commCode) throws Exception{
		PeriodSpecMemo ret = null;
		logger.debug("���ҵ����������ڽ��㱸ע��Ϣ������Ĳ���Ϊ��" + commCode);
		PeriodSpecMemoDao dao = (PeriodSpecMemoDao)BeanLocator.getInstance().getBean(InvoiceConstants.PERIOD_SPEC_MEMO_DAO);
		ret = dao.findPeriodSpecMemo(commCode);		
		return ret;
	}
	/**
	 * �����������������ѯ�������ڽ��㱸ע��Ϣ
	 * @param specMemo
	 * @return
	 * @throws Exception
	 */
	public List<PeriodSpecMemo> getSepcMemoList(PeriodSpecMemo specMemo) throws Exception{
		List<PeriodSpecMemo> ret = null;
		logger.debug("��������������Ʊ��¼��Ϣ������Ĳ���Ϊ��" + specMemo.toString());
		PeriodSpecMemoDao dao = (PeriodSpecMemoDao)BeanLocator.getInstance().getBean(InvoiceConstants.PERIOD_SPEC_MEMO_DAO);
		ret = dao.listPeriodSpecMemo(specMemo);
		return ret;
	}
	/**
	 * �����������������ѯ�������ڽ��㱸ע��Ϣ��¼��
	 * @param specMemo
	 * @return
	 * @throws Exception
	 */
	public int getSepcMemoListCount(PeriodSpecMemo specMemo) throws Exception{
		int ret =0;
		logger.debug("���Ҳ������ڽ��㱸ע��Ϣ��¼��������Ĳ���Ϊ��" + specMemo.toString());
		PeriodSpecMemoDao dao = (PeriodSpecMemoDao)BeanLocator.getInstance().getBean(InvoiceConstants.PERIOD_SPEC_MEMO_DAO);
		ret = dao.listPeriodSpecMemoCount(specMemo);
		return ret;
	}
}
