/**
 * system ��hbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendororder.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.manager.syssequence.SysSequenceMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerorder.constants.CustOrderConstants;

import com.hbs.domain.vendor.order.dao.VendorOrderDao;
import com.hbs.domain.vendor.order.dao.VendorOrderDetailDao;
import com.hbs.domain.vendor.order.pojo.VendorOrder;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;
import com.hbs.vendor.common.utils.VendorLogUtils;
import com.hbs.vendororder.constants.VendorOrderConstants;
import com.hbs.vendororder.manager.helper.VendorOrderState;

public class VendorOrderMgr {

	private static final Logger logger = Logger.getLogger(VendorOrderMgr.class);
	
	/**
	 * ����������ʱ��Ӧ�̶��� ״̬Ϊ01
	 * �ж�PONO�����������PONO��˵�����µĹ�Ӧ�̶�����insert
	 * ����Ϊ���ڵ���ʱ��Ӧ�̶������޸�
	 * ������ڶ�����ϸ����ͬʱ�Զ�����ϸ������
	 * @param vOrder
	 * @param content ����˵��
	 * @return
	 * @throws Exception
	 */
	public int saveTempVendorOrder(VendorOrder vOrder ,String content) throws Exception{
		int ret =0;
		logger.debug("����������ʱ��Ӧ�̶��� ,����Ĳ���Ϊ��" + vOrder.toString());
		String poNo = vOrder.getPoNo();
		VendorOrderDao vOrderDao =(VendorOrderDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DAO);
		if(StringUtils.isEmpty(poNo)){//�µĹ�Ӧ�̶�����insert����
			//����ϵͳ������pono
			vOrder.setPoNo(SysSequenceMgr.getPoNo(SysSequenceMgr.V_ORDER_PONO));
			String period = vOrder.getPeriod();
			//�¶�������������
			if(StringUtils.isEmpty(period)){
				VendorOrderState orderState =(VendorOrderState)BeanLocator.getInstance().getBean(VendorOrderConstants.PRE_SPRING + vOrder.getPoNoType() + vOrder.getSettlementType());
				vOrder.setPeriod(orderState.getPeriod(vOrder));			
			}
			//���ö���״̬Ϊ�ɹ���ʱ����״̬
			vOrder.setState(VendorOrderConstants.VENDOR_ORDER_STATE_01);
			
			vOrderDao.insertVendorOrder(vOrder);
			
			VendorLogUtils.operLog(vOrder.getStaffId(), vOrder.getStaffName(), "����" , "��Ӧ�̶���", vOrder.getLogKey(), null, content);
			
		}else{//�Ѿ����ڵĹ�Ӧ�̶�������update����
			vOrderDao.updateVendorOrder(vOrder);			
			VendorLogUtils.operLog(vOrder.getStaffId(), vOrder.getStaffName(), "�޸�" , "��Ӧ�̶���", vOrder.getLogKey(), null, content);
		}
		//������ϸ����
		List<VendorOrderDetail> detailList = vOrder.getVendorOrderDetailList();
		if(null != detailList && detailList.size() > 0){//���ڶ�����ϸ
			for(VendorOrderDetail detail : detailList){
				detail.setPeriod(vOrder.getPeriod());
				detail.setPoNo(vOrder.getPoNo());
			}
			//���ö�����ϸ�����࣬���涩����ϸ
			VendorOrderDetailMgr detailMgr =(VendorOrderDetailMgr)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_MGR);
			detailMgr.saveTempOrderDetailList(detailList, true);
		}
		return ret;
	}
	
	/**
	 * �޸Ĺ�Ӧ����ʱ������״̬Ϊ01
	 * ����ͬ���������������ж��Ƿ����PONO������update ,������insert
	 * �������ύ��ʽ����ǰ�����޸ģ��ἰ�����޸�	
	 * @param vOrder
	 * @param content �޸�˵������¼��־
	 * @return
	 * @throws Exception
	 */
	public int updateTempVendorOrder(VendorOrder vOrder,String content) throws Exception{
		logger.debug("�޸Ĺ�Ӧ����ʱ���� ,����Ĳ���Ϊ��" + vOrder.toString());
		return saveTempVendorOrder(vOrder,content);
	}
	
	/**
	 * ȡ��������״̬��Ϊ03
	 * �ö����µ�������ϸ״̬ȫ��Ϊ03ȡ��״̬
	 * @param vOrder
	 * @param content ȡ��˵������¼��־
	 * @return
	 * @throws Exception
	 */
	public int cancelVendorOrder(VendorOrder vOrder ,String content) throws Exception{
		logger.debug("ȡ����Ӧ����ʱ���� ,����Ĳ���Ϊ��" + vOrder.toString());
		vOrder.setState(VendorOrderConstants.VENDOR_ORDER_STATE_03);
		updateVendorOrderByState(vOrder);
		//��������ϸ
		VendorOrderDetailMgr vDetailMgr = (VendorOrderDetailMgr)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_MGR);
		VendorOrderDetail vDetail = new VendorOrderDetail();
		vDetail.setCommCode(vOrder.getCommCode());
		vDetail.setPoNo(vOrder.getPoNo());
		vDetail.setState(vOrder.getState());
		Map<String,String> hm = new HashMap<String,String>();
		hm.put("state", VendorOrderConstants.VENDOR_ORDER_STATE_03);
		//���ö�����ϸ����ȡ�����ж�����ϸ
		vDetailMgr.cancelOrderDetail(vDetail, true, content);
		VendorLogUtils.operLog(vOrder.getStaffId(), vOrder.getStaffName(), "ȡ��" , "��Ӧ�̶���", vOrder.getLogKey(), null, content);
		return 0;
	}
	/**
	 * �ύ������
	 * ��Ӧ���ڶ���������״̬Ϊ04 ��ȷ�Ͻ���
	 * ��ӦԤ���Ѷ���������״̬Ϊ02 ��ȷ�϶���   ���ջ����
	 * @param vOrder
	 * @param content  �ύ˵�� ����¼��־
	 * @return
	 * @throws Exception
	 */
	public int commitVendorOrder(VendorOrder vOrder ,String content) throws Exception{
		int ret =0;
		logger.debug("�ύ��Ӧ����ʱ���� ,����Ĳ���Ϊ��" + vOrder.toString());
		VendorOrderState orderState =(VendorOrderState)BeanLocator.getInstance().getBean(VendorOrderConstants.PRE_SPRING + vOrder.getPoNoType() + vOrder.getSettlementType());
		String state = orderState.getCommitState(/*vOrder*/);
		vOrder.setState(state);		
		updateVendorOrderByState(vOrder);
		//��������ϸ
		List<VendorOrderDetail> detailList = vOrder.getVendorOrderDetailList();
		if(null != detailList && detailList.size() >0){
			VendorOrderDetailMgr vDetailMgr = (VendorOrderDetailMgr)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_MGR);
			for(VendorOrderDetail vDetail : detailList){
				vDetail.setState(vOrder.getState());
				if(vDetail.getVerDeliveryDate() == null){
					vDetail.setVerDeliveryDate(vDetail.getOrgDeliveryDate());
				}
				vDetailMgr.commitOrderDetail(vDetail, true, null);
			}
		}
//		VendorOrderDetailMgr vDetailMgr = (VendorOrderDetailMgr)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_MGR);
//		VendorOrderDetail vDetail = new VendorOrderDetail();
//		vDetail.setCommCode(vOrder.getCommCode());
//		vDetail.setPoNo(vOrder.getPoNo());
//		vDetail.setState(vOrder.getState());
//		Map<String,String> hm = new HashMap<String,String>();
//		hm.put("state", VendorOrderConstants.VENDOR_ORDER_STATE_03);
//		//���ö�����ϸ���񣬶�����ϸ
//		vDetailMgr.commitOrderDetail(vDetail, true, content);
		VendorLogUtils.operLog(vOrder.getStaffId(), vOrder.getStaffName(), "�ύ" , "��Ӧ�̶���", vOrder.getLogKey(), null, content);
		return ret;
	}
	/**
	 *ȷ�����ڶ����Ľ��ڣ�ֻ�����ڶ�����Ч
	 *���ڶ�����״̬��04��������ȷ�ϣ���Ϊ 02ȷ�϶����������
	 * @param vOrder
	 * @return   0  �ɹ�  
	 * @throws Exception
	 */
	public int confirmOrderDelivery(VendorOrder vOrder,String content) throws Exception{
		int ret =0;
		logger.debug("ȷ�����ڶ����Ľ��� ,����Ĳ���Ϊ��" + vOrder.toString());
		String state = vOrder.getState();
		if(VendorOrderConstants.VENDOR_ORDER_STATE_04.equals(state)){
			vOrder.setState(VendorOrderConstants.VENDOR_ORDER_STATE_02);
			updateVendorOrderByState(vOrder);
			//��������ϸ
			List<VendorOrderDetail> detailList = vOrder.getVendorOrderDetailList();
			if(null != detailList && detailList.size() >0){
				VendorOrderDetailMgr vDetailMgr = (VendorOrderDetailMgr)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_MGR);
				for(VendorOrderDetail vDetail : detailList){
					vDetail.setState(vOrder.getState());
					if(vDetail.getVerDeliveryDate() == null){
						vDetail.setVerDeliveryDate(vDetail.getOrgDeliveryDate());
					}
					vDetailMgr.confirmOrderDetailDelivery(vDetail,true,null);
				}
				
			}
			VendorLogUtils.operLog(vOrder.getStaffId(), vOrder.getStaffName(), "����ȷ��" , "��Ӧ�̶���", vOrder.getLogKey(), null, content);
		}else{
			//ret =1;
			throw new Exception("ȷ�����ڶ����Ľ���,״̬����ȷ���޷�ȷ�Ͻ��ڣ�");
		}
		return ret;
	}
	
	/**
	  * �����Ļ״̬���ƣ�������Ϊ�ɹ���Ա
	 * ǰ̨����ʱ����Ҫ�ı�״̬���ɺ�̨�жϸı�
	 * @param vOrder
	 * @param content  ��ͣ ����ԭ�� ��¼��־
	 * @return
	 * @throws Exception
	 */
	public int controlActiveState(VendorOrder vOrder ,String content)throws Exception{
		int ret =0;
		logger.debug("�����Ļ״̬���� ,����Ĳ���Ϊ��" + vOrder.toString());
		String activeState = vOrder.getActiveState();
		if(VendorOrderConstants.VENDOR_ORDER_ACTIVE_STATE.equals(activeState)){
			vOrder.setActiveState(VendorOrderConstants.VENDOR_ORDER_PAUSE_STATE);
		}else{
			vOrder.setActiveState(VendorOrderConstants.VENDOR_ORDER_ACTIVE_STATE);
		}
		VendorOrderDao vOrderDao =(VendorOrderDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DAO);
		vOrderDao.updateVendorOrderByActiveState(vOrder);
		//��������ϸ
		VendorOrderDetailMgr vDetailMgr = (VendorOrderDetailMgr)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_MGR);
		VendorOrderDetail vDetail = new VendorOrderDetail();
		vDetail.setCommCode(vOrder.getCommCode());
		vDetail.setPoNo(vOrder.getPoNo());
		vDetail.setActiveState(vOrder.getActiveState());
		
		//���ö�����ϸ����ȡ�����ж�����ϸ
		vDetailMgr.controlActiveState(vDetail, true, content);
		VendorLogUtils.operLog(vOrder.getStaffId(), vOrder.getStaffName(), ((vOrder.getActiveState()).equals(CustOrderConstants.ORDER_ACTIVE_STATE) ? "����" : "��ͣ") , "��Ӧ�̶���", vOrder.getLogKey(), null, content);
		return ret;
	}
	/**
	 * ����������ѯ��Ӧ�̶���
	 * @param commCode  ��Ӧ�̱���
	 * @param poNo  ������
	 * @param isDetail  �Ƿ����������ϸ
	 * @return
	 * @throws Exception
	 */
	public VendorOrder getVendorOrder(String commCode,String poNo,boolean isDetail) throws Exception{
		logger.debug("����������ѯ��Ӧ�̶��� ,����Ĳ���Ϊ��" + commCode +" ;" + poNo);
		VendorOrder vOrder = new VendorOrder();
		VendorOrderDao vOrderDao =(VendorOrderDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DAO);
		vOrder.setCommCode(commCode);
		vOrder.setPoNo(poNo);
		vOrder = vOrderDao.findVendorOrder(vOrder);
		if(null != vOrder && isDetail){
			VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
			VendorOrderDetail vDetail = new VendorOrderDetail();
			vDetail.setCommCode(commCode);
			vDetail.setPoNo(poNo);
			vOrder.setVendorOrderDetailList(vDetailDao.listVendorOrderDetail(vDetail));
		}
		return vOrder;
	}
	
	/**
	 * ���ݲ�ѯ��������ѯ��Ӧ�̶����б�
	 * @param vOrder
	 * @return
	 * @throws Exception
	 */
	public List<VendorOrder> getVendorOrderList(VendorOrder vOrder) throws Exception{
		logger.debug("���ݲ�ѯ��������ѯ��Ӧ�̶����б� ,����Ĳ���Ϊ��" + vOrder.toString());
		VendorOrderDao vOrderDao =(VendorOrderDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DAO);
		return vOrderDao.listVendorOrder(vOrder);
	}
	/**
	 * ���ݲ�ѯ��������ѯ��Ӧ�̶�����Ŀ
	 * @param vOrder
	 * @return
	 * @throws Exception
	 */
	public Integer getVendorOrderCount(VendorOrder vOrder) throws Exception{
		logger.debug("���ݲ�ѯ��������ѯ��Ӧ�̶�����Ŀ ,����Ĳ���Ϊ��" + vOrder.toString());
		VendorOrderDao vOrderDao =(VendorOrderDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DAO);
		return vOrderDao.listVendorOrderCount(vOrder);
	}
	/**
	 * ˽�к��������¶���״̬
	 * @param vOrder
	 * @return
	 * @throws Exception
	 */
	private int updateVendorOrderByState(VendorOrder vOrder) throws Exception{
		int ret =0;
		VendorOrderDao vOrderDao =(VendorOrderDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DAO);
		vOrderDao.updateVendorOrderByState(vOrder);		
		return ret;
	}
}
