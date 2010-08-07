/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousesend.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.OrderCalUtils;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.customerorder.manager.CustOrderDetailMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;

import com.hbs.domain.warehouse.dao.WarehouseSendDetailDao;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;

import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;
import com.hbs.warehouse.manager.WarehouseMgr;
import com.hbs.warehousesend.manager.helper.WarehouseSendHelper;

/**
 * @author Administrator
 *
 */
public class WareHouseSendDetailMgr {
	private static final Logger logger = Logger.getLogger(WareHouseSendDetailMgr.class);
	
	/**
	 * ������ⵥ��ϸ������ʱ���жϿ����Ƿ���ڣ�������insert����
	 * ���� update������update������״̬������ 01 ��ʱ״̬���ܱ����޸�
	 * ���״̬Ϊȷ�ϣ���
	 * �����ȷ�ϲ���������Ҫִ�����²���
		 * �жϳ����������ϸ���ڵĿͻ�������ţ�
		 * ��ѯ���ͻ�������ϸ���ص��ע���η������ض��ͻ�����ͨ�ÿ��ı仯
		 * ���¿ͻ�������ϸ�е��ѷ��������ͷ�������ţ�
		 * ���¶�Ӧ�Ŀ�������������������
	 * @param detail
	 * @param isflowRec �Ƿ���������ⵥͬʱ����
	 * @param content   �������
	 * @return  >= 0 �ɹ�  -1 ��״̬����ϸ�������޸�
	 * @throws Exception
	 */
	public int saveWareHouseSendDetail(WarehouseSendDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		String st =detail.getState();
		if(st.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_01)){
			logger.debug("����ͻ������������ϸ������Ĳ���Ϊ:" + detail.toString());
		}else if(st.equals(WareHouseConstants.WAREHOUSE_REC_INFO_02)){
			logger.debug("ȷ�Ͽͻ�����������ϸ������Ĳ���Ϊ:" + detail.toString());
		}else{
			logger.debug("ȷ�Ϲ�Ӧ�̳���������ϸ,״̬Ϊȡ����������������Ĳ���Ϊ:" + detail.toString());
			return ret;
		}
		//������ϸ�ĳ�����
		detail.setCurMoney(OrderCalUtils.calOrderMoney(detail.getPrice(), detail.getIsTax(), detail.getTaxRate(),detail.getPriceTax(),detail.getContactFee(), detail.getAmount()));
		//��������
		String period = detail.getPeriod();
		if(StringUtils.isEmpty(period)){
			WarehouseSendHelper helper =(WarehouseSendHelper)BeanLocator.getInstance().getBean(WareHouseConstants.PRE_SPRING_SEND + detail.getPoNoType() + detail.getSettlementType());
			detail.setPeriod(helper.getPeriod(detail));
			detail.setFinancePeriod(detail.getPeriod());				
		}
		if(StringUtils.isEmpty(detail.getIsShowPrice())){
			CustomerInfoMgr custMgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean("customerInfoMgr");
			CustomerInfo cInfo = new CustomerInfo();
			cInfo.setCommCode(detail.getCustCode());
			cInfo.setState("0");
			cInfo = custMgr.getCustomerInfo(cInfo, false);
			if(cInfo != null){
				detail.setIsShowPrice(cInfo.getIsShowPrice());
			}
		}
		WarehouseSendDetailDao detailDao = (WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
		//��ѯ���ⵥ��ϸ,�ж��Ƿ����
		WarehouseSendDetail existDetail;
		if(detail.getSendSeqId() == null)
			existDetail = null;
		else
			//existDetail = detailDao.findWarehouseSendDetailByBizKey(detail);
			existDetail = detailDao.findWarehouseSendDetail(detail.getSendSeqId().toString());
		if(null == existDetail){//�����ڣ�insert������
			logger.debug("���ݿ����޴˳��ⵥ��ϸ��insert������");
			ret = detailDao.insertWarehouseSendDetail(detail);
			detail.setSendSeqId(ret);
			WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), (detail.getState().equals(WareHouseConstants.WAREHOUSE_SEND_INFO_02) ? "ȷ��" : "����"), "���ⵥ��ϸ", detail.getLogKey(), null, content);
		}else{//���ڳ��ⵥ��ϸ��
			//�ж�״̬
			String state = existDetail.getState();
			logger.debug("���ݿ��д��ڳ��ⵥ��ϸ��update��������ϸ״̬Ϊ��" + state);
			if(state.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_01)){//״̬Ϊ��ʱ�������޸�
				
				detail.setSendSeqId(existDetail.getSendSeqId());
				detailDao.updateWarehouseSendDetail(detail);
				ret = detail.getSendSeqId();
				WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), (detail.getState().equals(WareHouseConstants.WAREHOUSE_SEND_INFO_02) ? "ȷ��" : "�޸�"), "��ⵥ������ϸ", detail.getLogKey(), null, content);
			}else{//״̬����ȷ���������޸�
				logger.debug("���ݿ��д��ڳ��ⵥ��ϸ��update������״̬����ȷ���������޸ģ�");
				ret = -1;
			}
		}
		/**
		 * �����ȷ�ϲ���������Ҫִ�����²���
		 * �жϳ����������ϸ���ڵĿͻ�������ϸ��
		 * ��ѯ���ͻ�������ϸ���ص��ע���η������ض��ͻ�����ͨ�ÿ��ı仯
		 * ���¿ͻ�������ϸ�е��ѷ��������ͷ�������ţ�
		 * ���¶�Ӧ�Ŀ�������������������
		 */
		if(st.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_02)){
			logger.debug("���ⵥ��ϸ���⣬�����Ӧ�Ŀͻ�������");
			processCustOrderDetail(detail);
			logger.debug("���ⵥ��ϸ���⣬�����Ӧ�Ŀ����Ϣ��");
			processWarehouseInfo(detail);
		}
		return ret;
	}
	/**
	 * ���ݷ�������ϸ�������ͻ������ؼ���
	 * @param detail
	 * @return
	 */
	private CustOrderDetail createCustOrderDetail(WarehouseSendDetail detail){
		CustOrderDetail orderDetail = new CustOrderDetail();
		//���ñ��η�������
		orderDetail.setDeliveryAmount(detail.getAmount());
		//�ض��ͻ���淢������
		orderDetail.setSelfDeliveryAmount(detail.getSelfAmount());
		//ͨ�ÿ�淢������
		orderDetail.setCommDeliveryAmount(detail.getCommAmount());
		//���ñ��η��͵��ͻ�����
		orderDetail.setRltSendPoNo(detail.getSendPoNo());
		//���ÿͻ�����
		orderDetail.setCommCode(detail.getCustCode());
		//�ͻ��Ķ�����
		orderDetail.setPoNo(detail.getRltPoNo());
		//�������ϱ���
		orderDetail.setPartNo(detail.getPartNo());
		orderDetail.setSpecDesc(detail.getSpecDesc());
		
		orderDetail.setDeliveryHouseType(detail.getHouseType());
		
		return orderDetail;
	}
	/**
	 * ����ͻ������еķ����������������ͻ���淢����ͨ�ÿ��
	 * ͬʱ����ͻ������б��ͻ����������ͨ�ÿ������
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	private int processCustOrderDetail(WarehouseSendDetail detail) throws Exception{
		int ret =0;
		CustOrderDetail orderDetail = createCustOrderDetail(detail);		
		CustOrderDetailMgr orderDetailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.CUST_ORDER_DETAILMGR);
		ret = orderDetailMgr.deliveryOrderDetailAmount(orderDetail, null, null, null);
		return ret;
	}
	/**
	 * ����ֿ��еĿ�����������������ض��ͻ�����ͨ�ÿ��
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	private int processWarehouseInfo(WarehouseSendDetail detail) throws Exception{
		int ret =0;
		
		CustOrderDetail orderDetail = createCustOrderDetail(detail);		
		CustOrderDetailMgr orderDetailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.CUST_ORDER_DETAILMGR);
		CustOrderDetail existOrderDetail = orderDetailMgr.findCustOrderDetailByBizKey(orderDetail);
		if(null != existOrderDetail){
			//����������
			int iSelfLock = (detail.getSelfAmount() == null ? 0 : detail.getSelfAmount().intValue());
			int iCommLock = (detail.getCommAmount() == null ? 0 : detail.getCommAmount().intValue());
			//�ض��ͻ���淢������
			if(iSelfLock != 0){//�����ض��ͻ�������������
				WareHouseInfo wInfo = new WareHouseInfo();
				//���òֿ����� �ܿ⻹�������ֿ�
				wInfo.setHouseType(orderDetail.getDeliveryHouseType());
				//���òֿ���; ���汸�������ض�����
				wInfo.setHouseUse(WareHouseConstants.WAREHOUSE_USE_TYPE_1);
				//���ù�Ӧ�̱���
				wInfo.setVendorCode(existOrderDetail.getVendorCode());
				//�������ϱ���
				wInfo.setPartNo(existOrderDetail.getPartNo());
				//������������Ŀ(�ض��ͻ���棩
				wInfo.setTotalAmount(iSelfLock);
				wInfo.setLockAmount(iSelfLock);
				//���ÿͻ�����
				wInfo.setCustCode(existOrderDetail.getCommCode());
				WarehouseMgr whMgr = (WarehouseMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_MGR);
				whMgr.saveOutWareHouseInfo(wInfo);				
			}
			//����ͨ�ÿ�����������
			if(iCommLock != 0){//����ͨ�ÿ�����������
				WareHouseInfo wInfo = new WareHouseInfo();
				//���òֿ����� �ܿ⻹�������ֿ�
				wInfo.setHouseType(orderDetail.getDeliveryHouseType());
				//���òֿ���; ���汸�������ض�����
				wInfo.setHouseUse(WareHouseConstants.WAREHOUSE_USE_TYPE_2);
				//���ù�Ӧ�̱���
				wInfo.setVendorCode(existOrderDetail.getVendorCode());
				//�������ϱ���
				wInfo.setPartNo(existOrderDetail.getPartNo());
				//������������Ŀ(�ض��ͻ���棩
				wInfo.setTotalAmount(iCommLock);
				wInfo.setLockAmount(iCommLock);
				
				WarehouseMgr whMgr = (WarehouseMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_MGR);
				whMgr.saveOutWareHouseInfo(wInfo);				
			}
		}else{
			throw new Exception("���ⵥ��ϸ���⣬�����Ӧ�Ŀ����Ϣ! �޷��ҵ��ͻ�������");
		}
		return ret;
	}
	
	/**
	 * ����������ⵥ��ϸ�����õ������淽��
	 * @param detailList
	 * @param isflowRec  �Ƿ��������������
	 * @param content  ��ע����¼��־
	 * @return  0 �ɹ� 
	 * @throws Exception
	 */
	public int saveWareHouseSendDetailList(List<WarehouseSendDetail> detailList,boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("����������ⵥ��ϸ����ϸ������Ϊ:" + detailList.size());
		for(WarehouseSendDetail detail : detailList){
			Integer i = saveWareHouseSendDetail(detail,isflowRec,content);
			logger.debug("����������ⵥ��ϸ,����Ľ��Ϊ��" + i);
		}
		return ret;
	}
	/**
	 * ȡ�����ⵥ��ϸ�����ж�ȡ���ĳ��ⵥ��ϸ�Ƿ���ڣ������ڣ�����-2
	 * ���ڣ��ж�״̬�Ƿ�Ϊ��ʱ״̬������״̬������ȡ����
	 * @param detail
	 * @param isflowRec �Ƿ��������¼
	 * @param content ȡ���������¼��־
	 * @return 0--�ɹ�  -1 ״̬����ȷ������ȡ�� -2 ȡ������ϸ������
	 * @throws Exception
	 */
	public int cancelWareHouseSendDetail(WarehouseSendDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("ȡ�����ⵥ��ϸ��Ϣ,����Ĳ���Ϊ��" + detail.toString());
		WarehouseSendDetailDao detailDao = (WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
		WarehouseSendDetail existDetail = detailDao.findWarehouseSendDetailByBizKey(detail);
		if(null != existDetail){//���ڳ��ⵥ��ϸ
			String state = existDetail.getState();
			logger.debug("���ݿ��д��ڳ��ⵥ��ϸ����ϸ״̬Ϊ��" + state);
			if(state.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_01)){//Ϊ��ʱ״̬������ȡ��
				existDetail.setState(WareHouseConstants.WAREHOUSE_SEND_INFO_03);
				detailDao.updateWarehouseSendDetailByState(existDetail);
				WareHouseLogUtils.operLog(existDetail.getStaffId(), existDetail.getStaffName(), "ȡ��", "���ⵥ��ϸ", existDetail.getLogKey(), null, content);
			}else{//����ʱ״̬������ȡ��
				logger.debug("���ݿ��д��ڳ��ⵥ��ϸ����ϸ״̬�Ѿ�Ϊ����ʱ״̬��������ȡ��������");
				ret =-1;
			}
		}else{//��������ⵥ��ϸ���޷���ȡ��
			ret = -2;
		}
		return ret;
	}
	
	/**
	 * ����ȡ�����ⵥ��ϸ�����õ���ȡ������
	 * @param detailList
	 * @param isflowRec �Ƿ������ⵥͬʱ����
	 * @param content  ȡ��˵������¼��־
	 * @return
	 * @throws Exception
	 */
	public int cancelWareHouseSendDetailList(List<WarehouseSendDetail> detailList,boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("����ȡ�����ⵥ��ϸ����ϸ������Ϊ:" + detailList.size());
		for(WarehouseSendDetail detail : detailList){
			cancelWareHouseSendDetail(detail,isflowRec,content);			
		}
		return ret;
	}
	
	/**
	 * ��ͣ/���Ӧ�̳��ⵥ��ϸ������Ĳ�������Ҫ�޸�ԭʼ״̬
	 * ��ϵͳ���������
	 * @param detail
	 * @param isflowRec
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int controlActiveState(WarehouseSendDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("��Ӧ�̳��ⵥ��ϸ��ͣ/�������������Ĳ���Ϊ��" + detail.toString());
		String activeState = detail.getActiveState();
		if(activeState.equals(WareHouseConstants.WAREHOUSE_SEND_ACTIVE)){
			detail.setActiveState(WareHouseConstants.WAREHOUSE_SEND_PAUSE);
		}else{
			detail.setActiveState(WareHouseConstants.WAREHOUSE_SEND_ACTIVE);
		}
		WarehouseSendDetailDao detailDao = (WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
		detailDao.updateWarehouseSendDetailByActiveState(detail);
		WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), (activeState.equals(WareHouseConstants.WAREHOUSE_SEND_ACTIVE) ? "����" : "��ͣ"), "�ͻ����ⵥ", detail.getLogKey(), null, content);
		return ret;
	}
	/**
	  * ������ͣ/����ͻ����ⵥ��ϸ������Ĳ�������Ҫ�޸�ԭʼ״̬
	 * ��ϵͳ���������
	 * @param detailList
	 * @param isflowRec
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int controlActiveStateList(List<WarehouseSendDetail> detailList,boolean isflowRec,String content) throws Exception{
		int ret =0;
		for(WarehouseSendDetail detail : detailList){
			controlActiveState(detail ,  isflowRec, content);
		}
		return ret;
	}
	
	/**
	 * ����������ڣ�ǰ̨��Ҫ����2������
	 * 1����������Ϊ���ڽ���
	 * 2.����״̬Ϊδ����
	 * �����Ĳ������������
	 * @param detail
	 * @param staffId  ����ID
	 * @param staffName  ��������
	 * @param content   ˵������¼��־
	 * @return
	 * @throws Exception
	 */
	public int adjustFinancePeriod(WarehouseSendDetail detail,String staffId,String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("���������Ӧ�̳��ⵥ��ϸ���ڣ�����Ĳ���Ϊ��" + detail.toString());
		WarehouseSendDetailDao detailDao = (WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
		detailDao.updateWarehouseSendDetailByFinancePeriod(detail);
		WareHouseLogUtils.operLog(staffId, staffName, "��������", "�ͻ����ⵥ��ϸ", detail.getLogKey(), null, content);
		return ret;
	}
	/**
	 * ���������������ڣ�ǰ̨��Ҫ����2������
	 * 1����������Ϊ���ڽ���
	 * 2.����״̬Ϊδ����
	 * �����Ĳ������������
	 * @param detailList
	 * @param staffId  ����ID
	 * @param staffName  ��������
	 * @param content   ˵������¼��־
	 * @return
	 * @throws Exception
	 */
	public int adjustFinancePeriodList(List<WarehouseSendDetail> detailList,String staffId,String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("���������Ӧ����ⵥ��ϸ�б����ڣ���ĿΪ��" + detailList.size());
		for(WarehouseSendDetail detail : detailList){
			adjustFinancePeriod( detail, staffId, staffName,  content);
		}
		return ret;
	}
	
	
	/**
	 * ���������Ѷ���,��Գ��ⵥ��ϸ��
	 * �����Ѷ��˶Գ��ⵥ���鲻����
	 * 
	 * @param detail
	 * @param staffId
	 * @param staffName
	 * @param content
	 * @return 0 �ɹ�  -1 �����Ѿ����ˣ�����Ҫ�ٶ�
	 * @throws Exception
	 */
	public int setFinanceState(WarehouseSendDetail detail ,String staffId,String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("���������Ѷ��ˣ�����Ĳ���Ϊ��" + detail.toString());
		String financeState = detail.getFinanceState();
		if(financeState.equals(WareHouseConstants.WAREHOUSE_SEND_FINANCE_STATE_1)){
			ret =-1;
			logger.debug("������Ӧ�̳��ⵥ��ϸ�Ѿ����ˣ�����Ҫ�ٴ���!");
		}else{
			WarehouseSendDetailDao detailDao =(WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
			detail.setFinanceState(WareHouseConstants.WAREHOUSE_SEND_FINANCE_STATE_1);
			detailDao.updateWarehouseSendDetailByFinanceState(detail);
			WareHouseLogUtils.operLog(staffId, staffName, "�������", "�ͻ����ⵥ��ϸ", detail.getLogKey(), null, content);
		}
		return ret;
	}
	/**	 
	 * ��ѯ�������ⵥ��ϸ������������������sequence
	 * Ҳ���������ҵ��ؼ��֣�SEND_PO_NO C_CODE SETTLEMENT_TYPE PART_NO C_PART_NO PO_NO_TYPE SPEC_DESC
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public WarehouseSendDetail getWarehouseSendDetail(WarehouseSendDetail detail) throws Exception{
		WarehouseSendDetail retDetail = null;
		logger.debug("��ѯ�������ⵥ��ϸ������Ĳ���Ϊ��" + detail.toString());
		WarehouseSendDetailDao detailDao =(WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
		retDetail = detailDao.findWarehouseSendDetailByBizKey(detail);
		return retDetail;
	}
	/**
	 * ���������������ѯ���ⵥ��ϸ�б�
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public List<WarehouseSendDetail> listWarehouseSendDetail(WarehouseSendDetail detail) throws Exception{
		List<WarehouseSendDetail> detailList = null;
		logger.debug("��ѯ���ⵥ��ϸ�б�����Ĳ���Ϊ��" + detail.toString());
		WarehouseSendDetailDao detailDao =(WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
		detailList = detailDao.listWarehouseSendDetail(detail);
		return detailList;
	}
	/**
	 * �����������������ѯ���������ĳ�������ϸ������Ŀ
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public Integer listWarehouseSendDetailCount(WarehouseSendDetail detail) throws Exception{
		Integer retCount = 0;
		logger.debug("��ѯ���ⵥ��ϸ�б�����������Ĳ���Ϊ��" + detail.toString());
		WarehouseSendDetailDao detailDao =(WarehouseSendDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAIL_DAO);
		retCount = detailDao.listWarehouseSendDetailCount(detail);
		return retCount;
	}
}
