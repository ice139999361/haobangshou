/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.manager;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import com.hbs.common.springhelper.BeanLocator;

import com.hbs.customer.common.utils.CustLogUtils;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.manager.helper.CustOrderState;

import com.hbs.customerorder.utils.CustOrderUtils;

import com.hbs.domain.customer.order.dao.CustomerOrderDao;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.customer.order.pojo.CustomerOrder;
import com.hbs.domain.vendor.vendorinfo.dao.VendorInfoDao;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;

/**
 * @author Administrator
 *
 */
public class CustOrderMgr {
	private static final Logger logger = Logger.getLogger(CustOrderMgr.class);
	private static final String VENDORINFO_DAO ="vendorInfoDao";
	/**
	 * SPRING�������ǰ׺
	 */
	public static final String PRE_SPRING ="custOrder";
	
	/**
	 * ������Ϊҵ������
	 * ����ͻ���������ʱ���ݣ����ݵ�״̬Ϊ
	 * 01---�����ͻ�������ʱ���ݣ������������ݺͶ�����ϸ����
	 * 
	 * ���ڵ��ֶε�ֵ����Ҫ���ݽ������������ã�Ԥ����û�����ڸ���
	 * �ͻ�������������Ҫ���ж����ڵ������ʹ�ã�����û�û����������
	 * ��ϵͳ�ں�̨�Զ���������
	 * 
	 * ��Ԥ���ѵ����������������
	 * ����ͬʱ���ڶ������˻���
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public int saveTempCustomerOrder(CustomerOrder cOrder) throws Exception{
		logger.debug("����ͻ���������ʱ����,����Ĳ���Ϊ��" + cOrder.toString());
		String period = cOrder.getPeriod();
		if(null == period){
			CustOrderState orderState =(CustOrderState)BeanLocator.getInstance().getBean(PRE_SPRING + cOrder.getPoNoType() + cOrder.getSettlementType());
			cOrder.setPeriod(orderState.getPeriod(cOrder));			
		}
		cOrder.setState(CustOrderConstants.ORDER_STATE_01);
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		cOrderDao.insertCustomerOrder(cOrder);
		List<CustOrderDetail> orderDetailList = cOrder.getOrderDetailList();
		if(null != orderDetailList){
			for(CustOrderDetail orderDetail : orderDetailList){
				orderDetail.setPeriod(period);				
			}
			CustOrderDetailMgr detailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
			detailMgr.saveTempOrderDetailList(orderDetailList, null, null);
		}
		//log
		CustLogUtils.operLog(cOrder.getStaffId(), cOrder.getStaffName(), "����", "�ͻ�����", cOrder.getBizKey(), null, null);
		return 0;
	}
	/**
	 * 
	 * �޸Ŀͻ�����
	 * �ͻ��������޸Ĳ���ֻ����ҵ������
	 * 
	 * �������ύ��ʽ����ǰ�����޸ģ��ἰ�����޸�
	 * �����ڶ�����������������ͨ����Ҳ�����޸�
	 * 
	 * �����Ҫ�������Ʒ����ɹ���أ������޸ķ�Χ
	 * �������Ʒ��Ҫ�ͻ��Ͽ�
	 * �����Ʒ�Ĳ�������Ϊ����ȡ������������ͳһ����
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public int updateTempCustomerOrder(CustomerOrder cOrder) throws Exception{
		logger.debug("�޸Ŀͻ�����,����Ĳ���Ϊ��" + cOrder.toString());
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		cOrderDao.updateCustomerOrder(cOrder);
		List<CustOrderDetail> orderDetailList = cOrder.getOrderDetailList();
		if(null != orderDetailList){			
			CustOrderDetailMgr detailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
			detailMgr.updateTempOrderDetailList(orderDetailList, null);
		}
		//log
		CustLogUtils.operLog(cOrder.getStaffId(), cOrder.getStaffName(), "�޸�", "�ͻ�����", cOrder.getBizKey(), null, null);
		
		return 0;
	}
	
	/**
	 * �ύ�ͻ�����������ʽ���̣����̷�Ϊ��
	 * ������
	 * ���ڶ���  -- �ж��Ƿ񳬳����ڽ��������ɹ�
	 *               ����������������  state="50",����������
	 *               ����                   state="20"�����ɹ�����       
	 * Ԥ���ѣ��������� --������񲿣��ȴ�����ȷ��Ԥ����
	 *                Ԥ��0  ��state="20"�����ɹ�����
	 *                Ԥ����0  state="30"��������ȷ��Ԥ���� 
	 * Ԥ���ѣ������ --������� ���ȴ�����ȷ��Ԥ����
	 *                Ԥ��0  ��state="20"�����ɹ�����
	 *                Ԥ����0  state="30"��������ȷ��Ԥ����
	 * 
	 * �˻�����
	 *   ����ֿ⣬�ջ�
	 *      state =?
	 *  ���ݲ�ͬ�����̣�������ͬ�Ĵ���
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public int commitCustomerOrder(CustomerOrder cOrder) throws Exception{
		int ret =0;
		logger.debug("�ύ�ͻ�����,����Ĳ���Ϊ��" + cOrder.toString());
		CustOrderState orderState =(CustOrderState)BeanLocator.getInstance().getBean(PRE_SPRING + cOrder.getPoNoType() + cOrder.getSettlementType());
		String state = orderState.commitCustomerOrder(cOrder);
		cOrder.setState(state);
		ret = updateCustCustomerState(cOrder, true);
		//waittask
		WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
		if(state.equals(CustOrderConstants.ORDER_STATE_20)){
			waitTaskInfo.setStaffId(getVendorStaffId(cOrder.getVendorCode()));//������Ҫ���ݹ�Ӧ�̲��Ҷ�Ӧ�Ĳɹ�Ա
		}
		Map<String , String> hmParam = new HashMap<String,String>();
		hmParam.put("$staffName", cOrder.getStaffName());
		hmParam.put("$businessKey", cOrder.getWaitTaskBizKey());
		waitTaskInfo.setHmParam(hmParam);		
		CustOrderUtils.processCreateWaitTask(null,state, waitTaskInfo);
		//log
		CustLogUtils.operLog(cOrder.getStaffId(),cOrder.getStaffName(), "�ύ","�ͻ�����", cOrder.getLogBizKey(),null,null);
		return ret;
		
	}
	
	/**
	 * ȡ���ͻ�������ͬʱȡ���ö��������е���ϸ
	 * ȡ��״̬Ϊ��03
	 * ������ڴ��죬��������д���
	 * @param cOrder
	 * @param cancelContent  ȡ��ԭ�򣬼�¼����־��
	 * @return
	 * @throws Exception
	 */
	public int cancelCustOrder(CustomerOrder cOrder , String cancelContent) throws Exception{
		int ret =0;
		logger.debug("ȡ���ͻ�����,����Ĳ���Ϊ��" + cOrder.toString());
		cOrder.setState(CustOrderConstants.ORDER_STATE_03);
		ret = updateCustCustomerState(cOrder, true);
		//waittask
		CustOrderUtils.processDeleteWaitTask(cOrder.getBizKey());
		//log
		CustLogUtils.operLog(cOrder.getStaffId(),cOrder.getStaffName(), "ȡ��","�ͻ�����", cOrder.getLogBizKey(),null,cancelContent);
		
		return ret;
	}
		
	/**
	 * ��������ͨ���������������Ŀͻ��������������Ϊͨ��
	 * ��״̬50����������������Ϊ20�����ɹ�����
	 * ��Ҫ��������ɹ�
	 * @param cOrder  �ͻ����������Բ�����������ϸ��ϵͳ�Զ�������ϸ״̬
	 * @param auditId ������ID
	 * @param auditName  ����������
	 * @param auditContents  �������
	 * @return
	 * @throws Exception
	 */
	public int auditAgreeCustOrder(CustomerOrder cOrder,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("��������ͨ���������������Ŀͻ�����,����Ĳ���Ϊ��" + cOrder.toString());
		if(cOrder.getState().equals(CustOrderConstants.ORDER_STATE_50)){
			cOrder.setState(CustOrderConstants.ORDER_STATE_20);
			ret = updateCustCustomerState(cOrder, true);
			//waittask			
			WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
			if(cOrder.getState().equals(CustOrderConstants.ORDER_STATE_20)){
				waitTaskInfo.setStaffId(getVendorStaffId(cOrder.getVendorCode()));//������Ҫ���ݹ�Ӧ�̲��Ҷ�Ӧ�Ĳɹ�Ա
			}
			Map<String , String> hmParam = new HashMap<String,String>();
			hmParam.put("$staffName", auditName);
			hmParam.put("$businessKey", cOrder.getWaitTaskBizKey());
			waitTaskInfo.setHmParam(hmParam);		
			CustOrderUtils.processCreateWaitTask("CUST_ORDER_003",null, waitTaskInfo);
			//log
			CustLogUtils.operLog(auditId,auditName, "����ͨ��","�ͻ�����", cOrder.getLogBizKey(),null,auditContents);
			
		}else{
			//ret =2;
			throw new Exception("����Ķ���״̬Ϊ�Ǵ��������޷�ִ�в�����");
		}
		return ret;
	}
	
	/**
	 * ����ȷ���˿ͻ�������Ԥ������ɹ�����
	 * ״̬��30��������ȷ�ϣ���Ϊ 20�����ɹ�����
	 * @param cOrder  �ͻ�����
	 * @param auditId   ����ID
	 * @param auditName  ��������
	 * @param auditContents  ȷ�����
	 * @return
	 * @throws Exception
	 */
	public int financeAgreeCustOrder(CustomerOrder cOrder,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("����ȷ���˿ͻ�������Ԥ����,����Ĳ���Ϊ��" + cOrder.toString());
		if(cOrder.getState().equals(CustOrderConstants.ORDER_STATE_30)){
			cOrder.setState(CustOrderConstants.ORDER_STATE_20);
			ret = updateCustCustomerState(cOrder, true);
			//waittask			
			WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
			if(cOrder.getState().equals(CustOrderConstants.ORDER_STATE_20)){
				waitTaskInfo.setStaffId(getVendorStaffId(cOrder.getVendorCode()));//������Ҫ���ݹ�Ӧ�̲��Ҷ�Ӧ�Ĳɹ�Ա
			}
			Map<String , String> hmParam = new HashMap<String,String>();
			hmParam.put("$staffName", auditName);
			hmParam.put("$businessKey", cOrder.getWaitTaskBizKey());
			waitTaskInfo.setHmParam(hmParam);		
			CustOrderUtils.processCreateWaitTask("CUST_ORDER_007",null, waitTaskInfo);
			//log
			CustLogUtils.operLog(auditId,auditName, "ȷ��Ԥ����","�ͻ�����", cOrder.getLogBizKey(),null,auditContents);
			
		}else{
			//ret =2;
			throw new Exception("����Ķ���״̬Ϊ�Ǵ�����ȷ�ϣ��޷�ִ�в�����");
		}
		return ret;
	}
	/**
	 * ���ݿͻ���Ϣ�еĹ�Ӧ�̱��룬��ѯ��Ӧ�̶�Ӧ�ı���˾�Ĳɹ�Ա
	 * @param vendorCode
	 * @return
	 * @throws Exception
	 */
	private String getVendorStaffId(String vendorCode) throws Exception{
		String retStr = null;
		VendorInfoDao vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		VendorInfo vInfo = new VendorInfo();
		vInfo.setCommCode(vendorCode);
		vInfo.setState("0");
		VendorInfo retInfo = vInfoDao.findVendorInfoByBase(vInfo);
		if(null != retInfo){
			retStr = retInfo.getStaffId();
		}else{
			throw new Exception("�޷���ѯ����Ӧ�̱���Ϊ��" + vendorCode +"��Ϣ��");
		}
		return retStr;
	}
	/**
	 * �����˻ش�����Ԥ����ȷ�Ͽͻ���������ҵ��������
	 * ״̬��30��������ȷ�ϣ���Ϊ 39�������˵���
	 * ϵͳ�п��������ֳ������Ѿ��µ��������ڲ�������¶���ʧЧ
	 * �����˻غ�ҵ���������ȡ���ͻ�����
	 * @param cOrder  �ͻ�����
	 * @param auditId   ����ID
	 * @param auditName  ��������
	 * @param auditContents  �˵����
	 * @return
	 * @throws Exception
	 */
	public int financeDisAgreeCustOrder(CustomerOrder cOrder,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("�����˻ش�����Ԥ����ȷ�Ͽͻ�����,����Ĳ���Ϊ��" + cOrder.toString());
		if(cOrder.getState().equals(CustOrderConstants.ORDER_STATE_30)){
			cOrder.setState(CustOrderConstants.ORDER_STATE_39);
			ret = updateCustCustomerState(cOrder, true);
			//waittask			
			WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
			waitTaskInfo.setStaffId(cOrder.getStaffId());//������Ҫ���ݿͻ����Ҷ�Ӧ��ҵ������
			Map<String , String> hmParam = new HashMap<String,String>();
			hmParam.put("$staffName", auditName);
			hmParam.put("$businessKey", cOrder.getWaitTaskBizKey());
			waitTaskInfo.setHmParam(hmParam);		
			CustOrderUtils.processCreateWaitTask("CUST_ORDER_009",null, waitTaskInfo);
			//log
			CustLogUtils.operLog(auditId,auditName, "�����˵�","�ͻ�����", cOrder.getLogBizKey(),null,auditContents);
			
		}else{
			//ret =2;
			throw new Exception("����Ķ���״̬Ϊ�Ǵ�����ȷ�ϣ��޷�ִ�в�����");
		}
		return ret;
	}
	
	/**
	 * ����������ͨ���������������Ŀͻ��������������Ϊ��ͨ��
	 * ��״̬50����������������Ϊ52������������ͨ���� 
	 * ��Ҫ�������ҵ������
	 * @param cOrder �ͻ����������Բ�����������ϸ��ϵͳ�Զ�������ϸ״̬
	 * @param auditId  ������ID
	 * @param auditName ����������
	 * @param auditContents ������ͨ��ԭ��
	 * @return
	 * @throws Exception
	 */
	public int auditDisAgreeCustOrder(CustomerOrder cOrder,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("����������ͨ���������������Ŀͻ�����,����Ĳ���Ϊ��" + cOrder.toString());
		if(cOrder.getState().equals(CustOrderConstants.ORDER_STATE_50)){
			cOrder.setState(CustOrderConstants.ORDER_STATE_52);
			ret = updateCustCustomerState(cOrder, true);
			
			//waittask			
			WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
			waitTaskInfo.setStaffId(cOrder.getStaffId());//������Ҫ���ݿͻ����Ҷ�Ӧ��ҵ��Ա
			Map<String , String> hmParam = new HashMap<String,String>();
			hmParam.put("$staffName", auditName);
			hmParam.put("$businessKey", cOrder.getWaitTaskBizKey());
			waitTaskInfo.setHmParam(hmParam);		
			CustOrderUtils.processCreateWaitTask("CUST_ORDER_004",null, waitTaskInfo);
			
			//log
			CustLogUtils.operLog(auditId,auditName, "������ͨ��","�ͻ�����", cOrder.getLogBizKey(),null,auditContents);
			
		}else{
			//ret =2;
			throw new Exception("����Ķ���״̬Ϊ�Ǵ������������޷�ִ�в�����");
		}
		return ret;
	}
	
	
	/**
	 * �����Ļ״̬���ƣ�������Ϊҵ������
	 * ǰ̨����ʱ����Ҫ�ı�״̬���ɺ�̨�жϸı�
	 * ������״̬��Ϊ��ͣʱ��ȡ�����д���
	 * @param cOrder
	 * @param operContents  ����˵������¼��־
	 * @return
	 * @throws Exception
	 */
	public int controlActiveState(CustomerOrder cOrder,String operContents) throws Exception{
		int ret =0;
		logger.debug("�����Ļ״̬����,����Ĳ���Ϊ��" + cOrder.toString());
		String activeState = cOrder.getActiveState();
		if((CustOrderConstants.ORDER_ACTIVE_STATE).equals(activeState)){
			cOrder.setActiveState(CustOrderConstants.ORDER_PAUSE_STATE);
		}else{
			cOrder.setActiveState(CustOrderConstants.ORDER_ACTIVE_STATE);
		}
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		cOrderDao.updateCustomerOrderByActiveState(cOrder);
		List<CustOrderDetail> orderDetailList = cOrder.getOrderDetailList();
		if(null != orderDetailList){
			for(CustOrderDetail orderDetail : orderDetailList){
				orderDetail.setActiveState(cOrder.getActiveState());
			}
			CustOrderDetailMgr detailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
			detailMgr.controlActiveStateList(orderDetailList, null);
		}
		//waittask
		if((CustOrderConstants.ORDER_PAUSE_STATE).equals(activeState)){
			CustOrderUtils.processDeleteWaitTask(cOrder.getBizKey());
		}
		//log
		CustLogUtils.operLog(cOrder.getStaffId(),cOrder.getStaffName(), ((cOrder.getActiveState()).equals(CustOrderConstants.ORDER_ACTIVE_STATE) ? "����" : "��ͣ"),"�ͻ�����", cOrder.getLogBizKey(),null,operContents);
		
		return ret;
	}
	
	/**
	 * ����ҵ��������ѯ�ͻ�������boolean�����Ƿ�ͬʱ��ѯ��������ϸ
	 * @param cOrder ������ѯ������comCode �� poNo��
	 * @param isDetail  �Ƿ��ѯ��������ϸ  true ��ѯ  false ����ѯ
	 * @return
	 * @throws Exception
	 */
	public CustomerOrder findCustomerOrderByBizKey(CustomerOrder cOrder, boolean isDetail)throws Exception{
		CustomerOrder retOrder = null;
		logger.debug("����ҵ��������ѯ�ͻ�����,����Ĳ���Ϊ��" + cOrder.toString());
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		retOrder = cOrderDao.findCustomerOrder(cOrder);
		if(isDetail){
			CustOrderDetailMgr detailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
			CustOrderDetail orderDetail = new CustOrderDetail();
			orderDetail.setCommCode(cOrder.getCommCode());
			orderDetail.setPoNo(cOrder.getPoNo());
			retOrder.setOrderDetailList(detailMgr.listCustOrderDetail(orderDetail));
		}
		return retOrder;
	}
	/**
	 * ���ݲ�ѯ��������ѯ�����б�֧�ַ�ҳ��ѯ
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public List<CustomerOrder> listCustomerOrder(CustomerOrder cOrder) throws Exception{
		List<CustomerOrder> retList = null;
		logger.debug("���ݲ�ѯ��������ѯ�����б�,����Ĳ���Ϊ��" + cOrder.toString());
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		retList = cOrderDao.listCustomerOrder(cOrder);
		return retList;
	}
	
	/**
	 * ���ݲ�ѯ��������ѯ���������Ķ�������
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public Integer listCustomerOrderCount(CustomerOrder cOrder) throws Exception{
		Integer retI = null;
		logger.debug("���ݲ�ѯ��������ѯ���������Ķ�������,����Ĳ���Ϊ��" + cOrder.toString());
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		retI = cOrderDao.listCustomerOrderCount(cOrder);
		return retI;
	}
	/**
	 * ���¿ͻ�����״̬��������ϸ״̬��Ҫ����boolean�����ж��Ƿ����
	 * ������ֻ���¿ͻ�����״̬���������Բ�����
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	private int updateCustCustomerState(CustomerOrder cOrder, boolean isDetail) throws Exception{
		int ret =0;
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		cOrderDao.updateCustomerOrderByState(cOrder);
		if(isDetail){
			CustOrderDetailMgr detailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
			CustOrderDetail orderDetail = new CustOrderDetail();
			orderDetail.setCommCode(cOrder.getCommCode());
			orderDetail.setPoNo(cOrder.getPoNo());
			orderDetail.setState(cOrder.getState());
			Map<String,Object> hm = new HashMap<String,Object>();
			hm.put("state", CustOrderConstants.ORDER_STATE_03);
			orderDetail.setDynamicFields(hm);
			detailMgr.updateCustDetailByState(orderDetail);
		}
		return ret;
	}
	
}
