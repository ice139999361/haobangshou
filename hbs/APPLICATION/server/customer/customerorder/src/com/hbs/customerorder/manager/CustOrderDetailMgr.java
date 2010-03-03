/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;



import com.hbs.common.springhelper.BeanLocator;

import com.hbs.common.utils.OrderCalUtils;
import com.hbs.customer.common.utils.CustLogUtils;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.manager.helper.CustOrderState;
import com.hbs.customerorder.utils.CustOrderUtils;
import com.hbs.domain.customer.order.dao.CustOrderDetailDao;
import com.hbs.domain.customer.order.dao.CustomerOrderDao;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.customer.order.pojo.CustomerOrder;
import com.hbs.domain.vendor.vendorinfo.dao.VendorInfoDao;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.warehouse.manager.WarehouseMgr;

/**
 * @author Administrator
 *
 */
public class CustOrderDetailMgr {
	private static final Logger logger = Logger.getLogger(CustOrderDetailMgr.class);
	private static final String VENDORINFO_DAO ="vendorInfoDao";
	/**
	 * SPRING�������ǰ׺
	 */
	public static final String PRE_SPRING ="custOrder";
	/**
	 * ������Ϊҵ������
	 * ����ͻ�������ϸ����ʱ���ݣ����ݵ�״̬Ϊ
	 * 01---����������ϸ��ʱ����
	 * ����������ǿͻ��������ã��������־�������¼������־
	 * @param orderDetail
	 * @param staffId
	 * @param staffName
	 * @return
	 * @throws Exception
	 */
	public int saveTempOrderDetail(CustOrderDetail orderDetail,String staffId, String staffName) throws Exception{
		int ret =0;
		logger.debug("����ͻ�������ϸ����ʱ���ݣ�����Ĳ���Ϊ��" + orderDetail.toString());
		String period = orderDetail.getPeriod();
		if(null == period){
			CustOrderState orderState =(CustOrderState)BeanLocator.getInstance().getBean(PRE_SPRING + orderDetail.getPoNoType() + orderDetail.getSettlementType());
			orderDetail.setPeriod(orderState.getDetailPeriod(orderDetail));
		}
		//
		orderDetail.setMoney(OrderCalUtils.calOrderMoney(orderDetail.getCprice(), orderDetail.getIsTax(),orderDetail.getTaxRate(), orderDetail.getCpriceTax(),orderDetail.getContactFee(),orderDetail.getAmount()));
		orderDetail.setState(CustOrderConstants.ORDER_STATE_01);
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		cDetailDao.insertCustOrderDetail(orderDetail);
		//��¼������־
		if(null != staffId){			
			CustLogUtils.operLog(orderDetail.getStaffId(), orderDetail.getStaffName(), "����", "�ͻ�������ϸ", orderDetail.getLogBizKey(), null, null);
		}
		return ret;
	}
	
	/**
	 * ��������ͻ�������ϸ����ʱ����
	 * @param orderDetailList
	 * @param staffId
	 * @param staffName
	 * @return
	 * @throws Exception
	 */
	public int saveTempOrderDetailList(List<CustOrderDetail> orderDetailList,String staffId, String staffName) throws Exception{
		int ret = 0;
		logger.debug("��������ͻ�������ϸ����ʱ���ݣ�����Ϊ��" + orderDetailList.size());
		for(CustOrderDetail orderDetail : orderDetailList){
			saveTempOrderDetail(orderDetail,staffId, staffName);
		}
		return ret;
	}
	/**
	 * ȡ���ö�����ϸ
	 * ȡ����״̬Ϊ��03
	 * ������ڴ��죬��������д���
	 * @param orderDetail  ������ϸ
	 * @param cancelContent  ȡ��ԭ��
	 * @return
	 * @throws Exception
	 */
	public int cancelOrderDetail(CustOrderDetail orderDetail, String cancelContent) throws Exception{
		int ret =0;
		logger.debug("ȡ���ö�����ϸ������Ĳ���Ϊ��" + orderDetail.toString());
		orderDetail.setState(CustOrderConstants.ORDER_STATE_03);
		Map<String,Object> hm = new HashMap<String,Object>();
		hm.put("state", CustOrderConstants.ORDER_STATE_03);
		orderDetail.setDynamicFields(hm);
		updateCustDetailByState(orderDetail);
		//waittask
		CustOrderUtils.processDeleteWaitTask(orderDetail.getBizKey());
		//log
		CustLogUtils.operLog(orderDetail.getStaffId(), orderDetail.getStaffName(), "ȡ��", "�ͻ�������ϸ", orderDetail.getLogBizKey(), null, cancelContent);
		
		
		return ret;
	}
	/**
	 * �ɹ�ȷ�϶�����ϸ�Ľ���
	 * ״̬Ϊ21
	 * ��ѯ�ͻ�������������ϸ��������ж�����ϸ���ڶ�ȷ�ϣ�
	 * ���޸Ŀͻ�����״̬Ϊ21
	 * ����ȷ�ϣ�ɾ������
	 * @param orderDetail ������ϸ
	 * @param confirmId  ȷ����ID
	 * @param confirmName  ȷ��������
	 * @param content  ȷ�����
	 * @return
	 * @throws Exception
	 */
	public int purchaseConfirmDetailDelivery(CustOrderDetail orderDetail,String confirmId,String confirmName, String content) throws Exception{
		int ret =0;
		logger.debug("�ɹ�ȷ�϶�����ϸ�Ľ��ڣ�����Ĳ���Ϊ��" + orderDetail.toString());
		Date deliveryDate = orderDetail.getVerDeliveryDate();
		if(null == deliveryDate){
			orderDetail.setVerDeliveryDate(orderDetail.getPreDeliveryDate());
		}
		orderDetail.setState(CustOrderConstants.ORDER_STATE_21);
		Map<String,Object> hm = new HashMap<String,Object>();
		hm.put("state", CustOrderConstants.ORDER_STATE_03);
		orderDetail.setDynamicFields(hm);
		updateCustDetailByState(orderDetail);
		//waittask
		CustOrderUtils.processDeleteWaitTask(orderDetail.getBizKey());
		//log
		CustLogUtils.operLog(confirmId, confirmName, "�ɹ�ȷ�Ͻ���", "�ͻ�������ϸ", orderDetail.getLogBizKey(), null, content);
		
		//����ͻ�������״̬
		boolean isOrderStateChange = true;
		List<CustOrderDetail> listDetail = getDetailState(orderDetail);
		if(null != listDetail && listDetail.size() >0){
			for(CustOrderDetail detail : listDetail){
				String state = detail.getState();
				if( !(state.equals(CustOrderConstants.ORDER_STATE_21))){
					isOrderStateChange = false;
					break;
				}
			}
		}
		if(isOrderStateChange){
			CustomerOrder cOrder = new CustomerOrder();
			cOrder.setCommCode(orderDetail.getCommCode());
			cOrder.setPoNo(orderDetail.getPoNo());
			cOrder.setPoNoType(orderDetail.getPoNoType());
			cOrder.setState(orderDetail.getState());
			CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
			cOrderDao.updateCustomerOrderByState(cOrder);
		}
		return ret;
	}

	/**
	 * �ɹ���ͬ�ⶩ����ϸ���ڣ��ύ��ҵ��������
	 * ״̬��Ϊ04   ��ҵ������
	 * ͬʱ�޸Ŀͻ�����״̬Ϊ��
	 * 04   ��ҵ������
	 * ��ҵ�񷢳�����
	 * @param orderDetail
	 * @param staffId
	 * @param staffName
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int purchaseRefuseDetailDelivery(CustOrderDetail orderDetail,String confirmId, String confirmName, String content) throws Exception{
		int ret =0;
		logger.debug("�ɹ���ͬ�ⶩ����ϸ���ڣ�����Ĳ���Ϊ��" + orderDetail.toString());
		orderDetail.setState(CustOrderConstants.ORDER_STATE_04);
		Map<String,Object> hm = new HashMap<String,Object>();
		hm.put("state", CustOrderConstants.ORDER_STATE_03);
		orderDetail.setDynamicFields(hm);
		updateCustDetailByState(orderDetail);
		//����ͻ�����״̬
		CustomerOrder cOrder = new CustomerOrder();
		cOrder.setCommCode(orderDetail.getCommCode());
		cOrder.setPoNo(orderDetail.getPoNo());
		cOrder.setPoNoType(orderDetail.getPoNoType());
		cOrder.setState(orderDetail.getState());
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		cOrderDao.updateCustomerOrderByState(cOrder);
		//log			
		CustLogUtils.operLog(confirmId, confirmName, "�ɹ��ܾ�����", "�ͻ�������ϸ", orderDetail.getLogBizKey(), null, content);
		
		//waittask			
		WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
		waitTaskInfo.setStaffId(orderDetail.getStaffId());//������Ҫ���ݿͻ����Ҷ�Ӧ��ҵ������
		Map<String , String> hmParam = new HashMap<String,String>();
		hmParam.put("$staffName", confirmName);
		hmParam.put("$businessKey", cOrder.getWaitTaskBizKey());
		waitTaskInfo.setHmParam(hmParam);		
		CustOrderUtils.processCreateWaitTask("CUST_ORDER_005",null, waitTaskInfo);		
		return ret;
	}
	
	/**
	 * ҵ���ύ�����Ľ��ڣ��ύ���ɹ�����
	 * ״̬��Ϊ20  ���ɹ�����
	 * ��ɹ�������
	 * @param orderDetail
	 * @param staffId
	 * @param staffName
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int salesConfirmDetailDelivery(CustOrderDetail orderDetail, String content) throws Exception{
		int ret =0;
		logger.debug("ҵ���ύ�����Ľ��ڣ�����Ĳ���Ϊ��" + orderDetail.toString());
		orderDetail.setState(CustOrderConstants.ORDER_STATE_20);
		Map<String,Object> hm = new HashMap<String,Object>();
		hm.put("state", CustOrderConstants.ORDER_STATE_03);
		orderDetail.setDynamicFields(hm);
		updateCustDetailByState(orderDetail);
		//order state deal
		CustomerOrder cOrder = new CustomerOrder();
		cOrder.setCommCode(orderDetail.getCommCode());
		cOrder.setPoNo(orderDetail.getPoNo());
		cOrder.setPoNoType(orderDetail.getPoNoType());
		cOrder.setState(orderDetail.getState());
		CustomerOrderDao cOrderDao =(CustomerOrderDao)BeanLocator.getInstance().getBean("customerOrderDao");
		cOrderDao.updateCustomerOrderByState(cOrder);
		//log
		CustLogUtils.operLog(orderDetail.getStaffId(), orderDetail.getStaffName(), "ҵ���ύ����", "�ͻ�������ϸ", orderDetail.getLogBizKey(), null, content);
			
		//waittask
		WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
		waitTaskInfo.setStaffId(getVendorStaffId(orderDetail.getCommCode()));//������Ҫ���Ҳɹ���ԱID
		Map<String , String> hmParam = new HashMap<String,String>();
		hmParam.put("$staffName", orderDetail.getStaffName());
		hmParam.put("$businessKey", orderDetail.getWaitTaskBizKey());
		waitTaskInfo.setHmParam(hmParam);		
		CustOrderUtils.processCreateWaitTask("CUST_ORDER_006",null, waitTaskInfo);			
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
	 * ����������ϸ�Ŀ�棬���ڶ�����ϸ����������������⣬
	 * ����һ���־��ǶԲֿ��������������ֿ���������ڲֿⲿ�ֵ���DAOʵ��
	 * �������������˹�����������Ҳ�����ǲɹ����ʱ�����ݹ����Ŀͻ��������Զ��������ͻ�������
	 * �����������������������Ƚϣ����������ͬ����״̬Ϊ70������
	 * ����״̬Ϊ71���ֱ���
	 * ������״̬���������������״̬Ϊ21������	 
	 * @param orderDetail  ��������������Ҫǰ̨���㣬�ɺ�̨���㴦��
	 * @param staffId
	 * @param staffName
	 * @param content
	 * @return 0  �ɹ�  -1 �޷��ҵ��ͻ�������ϸ -2 �����������ڶ�����ϸ��������
	 * @throws Exception
	 */
	public int lockOrderDetailAmount(CustOrderDetail orderDetail,String staffId, String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("�ͻ�������ϸ������������������ ����Ĳ���Ϊ��" + orderDetail.toString());
		//����ҵ��������ѯ�ͻ�������ϸ
		CustOrderDetail  existDetail = findCustOrderDetailByBizKey(orderDetail);
		if(existDetail != null){//�ͻ�������ϸ���ڣ�ִ�п����������
			logger.debug("�ͻ�������ϸ����������������,��ѯ�ͻ�������ϸΪ��" + existDetail.toString());
			int iAmount = existDetail.getAmount();  //��������
			int iLockAmount = existDetail.getLockAmount(); //����������
			int iselLock = existDetail.getSelfLockAmount(); //�ͻ�������������
			int icommLock = existDetail.getCommLockAmount();//���汸����������
			int idelivery = existDetail.getDeliveryAmount();//�Ѿ�����������
			//���α��ͻ������������
			int oselfLock = (orderDetail.getSelfLockAmount() == null ? 0 : orderDetail.getSelfLockAmount().intValue());
			//����ͨ�ÿ����������
			int ocommLock = (orderDetail.getCommLockAmount()== null ? 0 :orderDetail.getCommLockAmount().intValue());
			
			int isavelockAmount = iLockAmount + oselfLock + ocommLock;
			if(isavelockAmount + idelivery > iAmount){//����������  + �ѷ����������ڶ�����ϸ��������
				logger.debug("�������������+�Ѵ��ڵ��������� + �ѷ������� �����ڶ�����ϸ��������������ִ������������");
				//ret = -2;
				throw new Exception("�������������+�Ѵ��ڵ��������� + �ѷ������� �����ڶ�����ϸ��������������ִ������������");
				
			}else{
				logger.debug("ִ������������");
				if(isavelockAmount == iAmount){//���ѱ���
					//���ݶ�����ϸ�Ľ��㷽ʽ������������ϸ��״̬
					String settlementType = orderDetail.getSettlementType();
					int isType = Integer.parseInt(settlementType);
					String state = CustOrderConstants.ORDER_STATE_70;
					switch(isType){
					case 1: //���ڽ��㣬״̬Ϊ���ѱ���
					case 2: //Ԥ��X%,ʣ���������
						 state = CustOrderConstants.ORDER_STATE_70;
						 break;
					case 3://Ԥ��X%ʣ������
						state = CustOrderConstants.ORDER_STATE_31;
						//�˴���������
						break;
					}
					orderDetail.setState(state);
				}else{//���ֱ���
					orderDetail.setState(CustOrderConstants.ORDER_STATE_71);
				}
				orderDetail.setOperSeqId(existDetail.getOperSeqId());
				orderDetail.setLockAmount(isavelockAmount);
				orderDetail.setSelfLockAmount(iselLock + oselfLock);
				orderDetail.setCommLockAmount(ocommLock + icommLock);
				ret = updateCustDetailAmount(orderDetail);
			}
		}else{//�ͻ�������ϸ������ ������-1
			logger.debug("��������Ĳ����޷��ҵ���Ӧ�Ŀͻ�������ϸ���޷�ִ������������");
			//ret = -1;
			throw new Exception("��������Ĳ����޷��ҵ���Ӧ�Ŀͻ�������ϸ���޷�ִ������������");
		}
		//����ֿⲿ�ֵ��������
		if(staffId != null && ret == 0){
			logger.debug("����ֿ������������ɲ�����Ա���𣬷����� " + staffId + staffName );
			WarehouseMgr whMgr =(WarehouseMgr)BeanLocator.getInstance().getBean(CustOrderConstants.WAREHOUSE_INFO_MGR);
			int detailSelock = (orderDetail.getSelfLockAmount() == null ? 0 :orderDetail.getSelfLockAmount().intValue());
			if( detailSelock != 0){
				logger.debug("�����ض��ͻ�������������������Ϊ��" + detailSelock);
				WareHouseInfo whrInfo = new WareHouseInfo();
				//���òֿ�����
				whrInfo.setHouseType(orderDetail.getDeliveryHouseType());
				//���ñ�������
				whrInfo.setHouseUse(CustOrderConstants.WAREHOUSE_INFO_HOUSE_USE_1);
				//���ù�Ӧ�̱���
				whrInfo.setVendorCode(orderDetail.getVendorCode());
				//���ù�˾���ϱ���
				whrInfo.setPartNo(orderDetail.getPartNo());
				whrInfo.setCustCode(orderDetail.getCommCode());
				//������������
				whrInfo.setLockAmount(detailSelock);
				whrInfo.setUseAmount(-detailSelock);
				whMgr.saveLockWareHouseInfo(whrInfo, null, null, null);
			}
			int commLock = ( orderDetail.getCommLockAmount() == null ? 0 : orderDetail.getCommLockAmount().intValue());
			if(commLock != 0){
				logger.debug("���ڳ��汸����������������Ϊ��" + commLock);
				WareHouseInfo whrInfo = new WareHouseInfo();
				//���òֿ�����
				whrInfo.setHouseType(orderDetail.getDeliveryHouseType());
				//���ñ�������
				whrInfo.setHouseUse(CustOrderConstants.WAREHOUSE_INFO_HOUSE_USE_2);
				//���ù�Ӧ�̱���
				whrInfo.setVendorCode(orderDetail.getVendorCode());
				//���ù�˾���ϱ���
				whrInfo.setPartNo(orderDetail.getPartNo());				
				//������������
				whrInfo.setLockAmount(commLock);
				whrInfo.setUseAmount(-commLock);
				whMgr.saveLockWareHouseInfo(whrInfo, null, null, null);
			}
		}
		//log
		if(staffId != null){
		CustLogUtils.operLog(orderDetail.getStaffId(), orderDetail.getStaffName(), "�������", "�ͻ�������ϸ", orderDetail.getLogBizKey(), null, content);
		}
		return ret;
	}
	/**
	 * ����ȷ�϶�����ϸ��Ԥ��x%�������
	 * ״̬�д�����ȷ��31 �ı�Ϊ70���ѱ���
	 * @param orderDetail
	 * @param auditId
	 * @param auditName
	 * @param auditContents
	 * @return
	 * @throws Exception
	 */
	public int financeConfirmDetailFee(CustOrderDetail orderDetail,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("����ȷ�϶�����ϸ��Ԥ��x%�������������Ĳ���Ϊ��" + orderDetail.toString());
		String state = orderDetail.getState();
		if((CustOrderConstants.ORDER_STATE_31).equals(state)){
			orderDetail.setState(CustOrderConstants.ORDER_STATE_70);
			ret = updateCustDetailByState(orderDetail);
			//�˴��������־
		}else{
			logger.debug("�˶�����ϸ��״̬����ȷ���޷�ȷ��Ԥ��x%���������");
			throw new Exception("�˶�����ϸ��״̬����ȷ���޷�ȷ��Ԥ��x%���������");
		}
		return ret;
	}
	/**
	 * �������붩����ϸ��Ԥ��x%�����������δ������
	 * ״̬�д�����ȷ��31 �ı�Ϊ33 ���쵼����
	 * @param orderDetail
	 * @param auditId
	 * @param auditName
	 * @param auditContents
	 * @return
	 * @throws Exception
	 */
	public int financeApplyDetailFee(CustOrderDetail orderDetail,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("�������붩����ϸ��Ԥ��x%�����������δ������������Ĳ���Ϊ��" + orderDetail.toString());
		String state = orderDetail.getState();
		if((CustOrderConstants.ORDER_STATE_31).equals(state)){
			orderDetail.setState(CustOrderConstants.ORDER_STATE_33);
			ret = updateCustDetailByState(orderDetail);
			//�˴��������־
			//�˴������
		}else{
			logger.debug("�˶�����ϸ��״̬����ȷ���޷�����Ԥ��x%�����������δ��������");
			throw new Exception("�˶�����ϸ��״̬����ȷ���޷�����Ԥ��x%�����������δ��������");
		}
		return ret;
	}
	/**
	 * �쵼����ͬ�ⶩ����ϸ��Ԥ��x%�����������δ������
	 * ״̬�д��쵼����33 �ı�Ϊ70 ���ѱ���
	 * @param orderDetail
	 * @param auditId
	 * @param auditName
	 * @param auditContents
	 * @return
	 * @throws Exception
	 */
	public int auditAgreeDetailFee(CustOrderDetail orderDetail,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("�쵼����ͬ�ⶩ����ϸ��Ԥ��x%�����������δ������������Ĳ���Ϊ��" + orderDetail.toString());
		String state = orderDetail.getState();
		if((CustOrderConstants.ORDER_STATE_33).equals(state)){
			orderDetail.setState(CustOrderConstants.ORDER_STATE_70);
			ret = updateCustDetailByState(orderDetail);
			//�˴��������־
			//�˴������
		}else{
			logger.debug("�˶�����ϸ��״̬����ȷ���޷�ִ���쵼����ͬ��Ԥ��x%�����������δ��������");
			throw new Exception("�˶�����ϸ��״̬����ȷ���޷�ִ���쵼����ͬ��Ԥ��x%�����������δ��������");
		}
		return ret;
	}
	/**
	 * �쵼����ͬ�ⶩ����ϸ��Ԥ��x%�����������δ������
	 * ״̬�д��쵼����33 �ı�Ϊ31 ������ȷ�� 
	 * @param orderDetail
	 * @param auditId
	 * @param auditName
	 * @param auditContents
	 * @return
	 * @throws Exception
	 */
	public int auditDisAgreeDetailFee(CustOrderDetail orderDetail,String auditId, String auditName,String auditContents) throws Exception{
		int ret =0;
		logger.debug("�쵼������ͬ�ⶩ����ϸ��Ԥ��x%�����������δ������������Ĳ���Ϊ��" + orderDetail.toString());
		String state = orderDetail.getState();
		if((CustOrderConstants.ORDER_STATE_33).equals(state)){
			orderDetail.setState(CustOrderConstants.ORDER_STATE_31);
			ret = updateCustDetailByState(orderDetail);
			//�˴��������־
			//�˴������
		}else{
			logger.debug("�˶�����ϸ��״̬����ȷ���޷�ִ���쵼������ͬ��Ԥ��x%�����������δ��������");
			throw new Exception("�˶�����ϸ��״̬����ȷ���޷�ִ���쵼������ͬ��Ԥ��x%�����������δ��������");
		}
		return ret;
	}
	
	/**
	 * �޸Ķ�����ϸ���ѷ��������������ѷ�����������ͬʱ��������������
	 
	 * ���������������������ͬ���򶩵���ϸΪ61ȫ������������Ϊ60���ַ���
	 * ������״̬Ϊ���ַ�����ȫ������
	 
	 * @param orderDetail  ���������ļ����ɺ�̨����
	 * @param staffId
	 * @param staffName
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int deliveryOrderDetailAmount(CustOrderDetail orderDetail,String staffId, String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("�޸Ķ�����ϸ���ѷ����������� ,����Ĳ���Ϊ��" + orderDetail.toString());
		//���οͻ���淢������
		int iselfdeliveryAmount = (orderDetail.getSelfDeliveryAmount() == null ? 0 : orderDetail.getSelfDeliveryAmount().intValue());
		//����ͨ�ÿ�淢������
		int icommdeliveryAmount =(orderDetail.getCommDeliveryAmount() == null ? 0 : orderDetail.getCommDeliveryAmount().intValue());
		//�����ܷ�������
		int ideliveryAmount = iselfdeliveryAmount + icommdeliveryAmount;
		
		//�жϷ������� == ���ͻ���������+ ͨ�ÿ�淢������
		CustOrderDetail  existDetail = findCustOrderDetailByBizKey(orderDetail);
		if(null != existDetail){//������ϸ����
			logger.debug("�޸Ķ�����ϸ���ѷ�����������,��ѯ�ͻ�������ϸΪ��" + existDetail.toString());
			orderDetail.setOperSeqId(existDetail.getOperSeqId());
			int ieAmount =(existDetail.getDeliveryAmount() == null ? 0 : existDetail.getDeliveryAmount().intValue());
			int ieselfAmount = (existDetail.getSelfDeliveryAmount() == null ? 0 : existDetail.getSelfDeliveryAmount().intValue());
			int iecommAmount =(existDetail.getCommDeliveryAmount() == null ? 0 : existDetail.getCommDeliveryAmount().intValue());
			int ieLockAmount = (existDetail.getLockAmount() == null ? 0 : existDetail.getLockAmount().intValue());
			int ieselfLock =(existDetail.getSelfLockAmount() == null ? 0 : existDetail.getSelfLockAmount().intValue());
			int iecommLock =(existDetail.getCommLockAmount() == null ? 0 : existDetail.getCommLockAmount().intValue());
			if( iselfdeliveryAmount > ieselfLock){//�ͻ����ͻ���淢����  > ���ͻ���������
				throw new Exception("�ͻ����ͻ���淢����  > ���ͻ���������,�޷�ִ�в�����");
			}
			if(icommdeliveryAmount > iecommLock){//ͨ�ÿ�淢����  > ������ͨ�ÿ����
				throw new Exception("ͨ�ÿ�淢����  > ������ͨ�ÿ����,�޷�ִ�в�����");
			}
			int  tempAmount = ieAmount + ideliveryAmount;
			if(tempAmount <=  existDetail.getAmount()){
				if(tempAmount == existDetail.getAmount()){
					orderDetail.setState(CustOrderConstants.ORDER_STATE_61);
				}else{
					orderDetail.setState(CustOrderConstants.ORDER_STATE_60);
				}
				//��������
				orderDetail.setDeliveryAmount(tempAmount);
				orderDetail.setSelfDeliveryAmount(iselfdeliveryAmount + ieselfAmount);
				orderDetail.setCommDeliveryAmount(icommdeliveryAmount + iecommAmount);
				//����������ȥ������
				orderDetail.setLockAmount(ieLockAmount - ideliveryAmount);
				orderDetail.setSelfLockAmount(ieselfLock - iselfdeliveryAmount);
				orderDetail.setCommLockAmount(iecommLock - icommdeliveryAmount);
				ret = updateCustDetailAmount(orderDetail);
			}else{//�����������ڶ�������
				throw new Exception("�޸Ķ�����ϸ���ѷ��������������������Ķ�����ϸ�����������ڶ����������޷�ִ�У�");
			}
		}else{//������ϸ������
			throw new Exception("�޸Ķ�����ϸ���ѷ��������������������Ķ�����ϸ�����ڣ�");
		}
		//log
		//operLog(staffId,staffName, "������������", orderDetail.getBizKey(),content);
		return ret;
	}
	
	/**
	 * �޸���ʱ״̬�Ķ�����ϸ����ҵ�������޸�
	 * @param orderDetail	
	 * @param content  �޸�˵��
	 * @return
	 * @throws Exception
	 */
	public int updateTempOrderDetail(CustOrderDetail orderDetail, String content) throws Exception{
		int ret =0;
		logger.debug("�޸���ʱ״̬�Ķ�����ϸ ,����Ĳ���Ϊ��" + orderDetail.toString());
		orderDetail.setMoney(OrderCalUtils.calOrderMoney(orderDetail.getCprice(), orderDetail.getIsTax(),orderDetail.getTaxRate(), orderDetail.getCpriceTax(),orderDetail.getContactFee(), orderDetail.getAmount()));
		orderDetail.setState(CustOrderConstants.ORDER_STATE_01);
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		cDetailDao.updateCustOrderDetail(orderDetail);
		//��¼������־
		
			CustLogUtils.operLog(orderDetail.getStaffId(), orderDetail.getStaffName(), "�޸�", "�ͻ�������ϸ", orderDetail.getLogBizKey(), null, content);
		
		return ret;
	}
	/**
	 * �����޸Ķ�����ϸ����ҵ�������޸�
	 * @param orderDetailList
	 * @param content  �޸�˵��
	 * @return
	 * @throws Exception
	 */
	public int updateTempOrderDetailList(List<CustOrderDetail> orderDetailList,String content) throws Exception{
		int ret = 0;
		logger.debug(" �����޸Ķ�����ϸ ,����Ϊ��" + orderDetailList.size());
		for(CustOrderDetail orderDetail : orderDetailList){
			updateTempOrderDetail(orderDetail, content);
		}
		return ret;
	}
	
	/**
	 * ��ȡ�ͻ����ڶ������ڵ������
	 * @param orderDetail
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getTotalMoneyByPeriod(CustOrderDetail orderDetail) throws Exception{
		BigDecimal ret = new BigDecimal(0);
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		List<CustOrderDetail> listDetail = cDetailDao.listtotalMoneyByPeriod(orderDetail);
		if(null != listDetail  && listDetail.size() >0){
			for(CustOrderDetail detail : listDetail){
				int ipoNoType = Integer.parseInt(detail.getPoNoType());
				switch(ipoNoType){
					case 0:
						ret.add(detail.getMoney());
						break;
					case 1:
						ret.subtract(detail.getMoney());
				}
			}
		}
		return ret;
	}
	
	public int updateCustDetailByState(CustOrderDetail orderDetail) throws Exception{
		int ret =0;
		logger.debug("�޸Ķ�����ϸ ,����Ĳ���Ϊ��" + orderDetail.toString());
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		cDetailDao.updateCustOrderDetailByState(orderDetail);
		return ret;
	}
	
	public int updateCustDetailAmount(CustOrderDetail orderDetail) throws Exception{
		int ret =0;
		logger.debug("�޸Ķ�����ϸ�������� ,����Ĳ���Ϊ��" + orderDetail.toString());
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		cDetailDao.updateCustOrderDetailAmount(orderDetail);
		return ret;
	}
	
	public CustOrderDetail findCustOrderDetailById(String id) throws Exception{
		logger.debug("����������ѯ������ϸ ,����Ĳ���Ϊ��" + id);
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		return cDetailDao.findCustOrderDetailById(id);
	}
	
	public CustOrderDetail findCustOrderDetailByBizKey(CustOrderDetail orderDetail) throws Exception{
		logger.debug("����ҵ��������ѯ������ϸ ,����Ĳ���Ϊ��" + orderDetail.toString());
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		return cDetailDao.findCustOrderDetailByBizKey(orderDetail);
	}
	
	public int controlActiveState(CustOrderDetail orderDetail,String content) throws Exception{
		int ret =0;
		logger.debug("������ϸ�״̬ ,����Ĳ���Ϊ��" + orderDetail.toString());
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		cDetailDao.updateCustOrderDetailByActiveState(orderDetail);
					
			CustLogUtils.operLog(orderDetail.getStaffId(), orderDetail.getStaffName(), ((orderDetail.getActiveState()).equals(CustOrderConstants.ORDER_ACTIVE_STATE) ? "����" : "��ͣ"), "�ͻ�������ϸ", orderDetail.getLogBizKey(), null, content);
		
		return ret;
	}
	
	public int controlActiveStateList(List<CustOrderDetail> orderDetailList,String content) throws Exception{
		int ret =0;
		logger.debug("������ϸ�״̬ ,����Ĳ���Ϊ��" + orderDetailList.size());
		for(CustOrderDetail orderDetail : orderDetailList){
			controlActiveState(orderDetail,content);
		}
		return ret;
	}
	
	public List<CustOrderDetail> listCustOrderDetail(CustOrderDetail orderDetail) throws Exception{
		List<CustOrderDetail> retList = null;
		logger.debug("����������ѯ������ϸ ,����Ĳ���Ϊ��" + orderDetail.toString());
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		retList = cDetailDao.listCustOrderDetail(orderDetail);
		return retList;
		
	}
	
	public Integer listCustOrderDetailCount(CustOrderDetail orderDetail) throws Exception{
		Integer iRet = 0;
		logger.debug("����������ѯ������ϸ���� ,����Ĳ���Ϊ��" + orderDetail.toString());
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		iRet = cDetailDao.listCustOrderDetailCount(orderDetail);
		return iRet;
	}
	
	private List<CustOrderDetail> getDetailState(CustOrderDetail orderDetail) throws Exception{
		CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
		return cDetailDao.listCustOrderDetailState(orderDetail);
	}
	
}
