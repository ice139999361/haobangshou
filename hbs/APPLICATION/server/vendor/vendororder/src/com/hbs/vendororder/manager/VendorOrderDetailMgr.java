/**
 * system ��hbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendororder.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.IntegerUtils;
import com.hbs.common.utils.OrderCalUtils;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.domain.customer.order.dao.CustOrderDetailDao;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.vendor.order.dao.VendorOrderDao;
import com.hbs.domain.vendor.order.dao.VendorOrderDetailDao;

import com.hbs.domain.vendor.order.pojo.VendorOrder;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;
import com.hbs.vendor.common.utils.VendorLogUtils;
import com.hbs.vendororder.constants.VendorOrderConstants;
import com.hbs.vendororder.manager.helper.VendorOrderState;

public class VendorOrderDetailMgr {
	
	private static final Logger logger = Logger.getLogger(VendorOrderDetailMgr.class);
	
	/**
	 * ���������Ķ�����ϸ���вɹ�����
	 * @param detail  ������ϸ
	 * @param isflowOrder �Ƿ��Ǹ���������һ��
	 * @return >0 �ɹ�
	 * @throws Exception
	 */
	public int saveTempOrderDetail(VendorOrderDetail detail,boolean isflowOrder) throws Exception{
		int ret =0;
		logger.debug("���涩����ϸ������Ĳ���Ϊ��" + detail.toString());
		Integer operSeqId = detail.getOperSeqId();
		detail.setMoney(OrderCalUtils.calOrderMoney(detail.getCprice(), detail.getIsTax(), detail.getTaxRate(), detail.getCpriceTax(),null,detail.getAmount()));
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		if(null == operSeqId){//���������кţ���ʾ������
			detail.setState(VendorOrderConstants.VENDOR_ORDER_STATE_01);
			operSeqId = vDetailDao.insertVendorOrderDetail(detail);
			detail.setOperSeqId(operSeqId);
			ret = operSeqId;
			logger.debug("���涩����ϸ��������operSeqId��������������������operSeqId=" + ret);
			if(!isflowOrder){//���Ǹ����������ύ����¼������־
				VendorLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), "����" , "��Ӧ�̶�����ϸ", detail.getLogKey(), null, null);
			}
		}else{//�������кţ���Ҫ���޸Ĵ���
			logger.debug("���涩����ϸ������operSeqId , �޸Ĳ���!");
			updateTempOrderDetail(detail,isflowOrder,null);
			ret = detail.getOperSeqId();
		}
		return ret;
	}
	
	/**
	 * �������ֶ�����ϸ
	 * @param detailList  ��ϸ�б�
	 * @param isflowOrder  �Ƿ��Ǹ���������һ��
	 * @return
	 * @throws Exception
	 */
	public int saveTempOrderDetailList(List<VendorOrderDetail> detailList , boolean isflowOrder) throws Exception{
		int ret =0;
		logger.debug("���涩����ϸ�б��б������Ϊ��" + detailList.size());
		for(VendorOrderDetail vDetail : detailList){
			saveTempOrderDetail(vDetail,isflowOrder);
		}
		return ret;
	}
	/**
	 * �޸���ʱ������ϸ���ɲɹ�����
	 * @param detail  ������ϸ
	 * @param isflowOrder  �Ƿ��Ǹ���������һ���޸�
	 * @param content  �޸�˵������¼��־
	 * @return
	 * @throws Exception
	 */
	public int updateTempOrderDetail(VendorOrderDetail detail,boolean isflowOrder , String content) throws Exception{
		int ret =0;
		logger.debug("�޸���ʱ������ϸ������Ĳ���Ϊ��" + detail.toString());
		detail.setMoney(OrderCalUtils.calOrderMoney(detail.getCprice(), detail.getIsTax(), detail.getTaxRate(),detail.getCprice(),null, detail.getAmount()));
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		vDetailDao.updateVendorOrderDetail(detail);
		if(!isflowOrder){//���Ǹ����������ύ����¼������־
			VendorLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), "�޸�" , "��Ӧ�̶�����ϸ", detail.getLogKey(), null, content);
		}
		return ret;
	}
	/**
	 * �����޸���ʱ������ϸ���ɲɹ�����
	 * @param detailList ������ϸ�б�
	 * @param isflowOrder �Ƿ��Ǹ���������һ��
	 * @param content  �޸�˵������¼��־
	 * @return
	 * @throws Exception
	 */
	public int updateTempOrderDetailList(List<VendorOrderDetail> detailList , boolean isflowOrder,String content) throws Exception{
		int ret =0;
		logger.debug("�����޸Ķ�����ϸ�б��б������Ϊ��" + detailList.size());
		for(VendorOrderDetail vDetail : detailList){
			updateTempOrderDetail(vDetail,isflowOrder,content);
		}
		return ret;
	}
	/**
	 * ȡ��������ϸ��״̬��Ϊ03
	 ** ���ֻ��operSeqid�����ǵ�������
	 * �������commCode��poNo �������ж�����ϸ������״̬
	 * @param detail
	 * @param isflowOrder �Ƿ����������ͬʱȡ��
	 * @param content  ȡ��˵��,��¼��־
	 * @return
	 * @throws Exception
	 */
	public int cancelOrderDetail(VendorOrderDetail detail,boolean isflowOrder , String content) throws Exception{
		int ret =0;
		logger.debug("ȡ��������ϸ������Ĳ���Ϊ��" + detail.toString());
			if(!isflowOrder){//���������������������Ҫ����״̬Ϊ03
				detail.setState(VendorOrderConstants.VENDOR_ORDER_STATE_03);
			}
			ret = updateOrderDetailByState(detail);
			//���������־
			if(!isflowOrder){//����������������Ҫ��¼������־
				VendorLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), "ȡ��" , "��Ӧ�̶�����ϸ", detail.getLogKey(), null, content);
			}
		return ret;
	}
	/**
	 * ȡ��������ϸ�б�״̬��Ϊ03
	 * @param detailList
	 * @param isflowOrder �Ƿ����������ͬʱȡ��
	 * @param content ȡ��˵��,��¼��־
	 * @return
	 * @throws Exception
	 */
	public int cancelOrderDetailList(List<VendorOrderDetail> detailList , boolean isflowOrder,String content) throws Exception{
		logger.debug("����ȡ��������ϸ�б��б������Ϊ��" + (detailList == null ? 0 : detailList.size()));
		if(null != detailList && detailList.size() >0){
			
			for(VendorOrderDetail detail : detailList){
				cancelOrderDetail(detail,isflowOrder,content);
			}
		}
		return 0;
	}
	/**
	  * �ύ������ϸ��
	 * ��Ӧ���ڶ�����ϸ������״̬Ϊ04 ��ȷ�Ͻ���
	 * ��ӦԤ���Ѷ�����ϸ������״̬Ϊ02 ��ȷ�϶���   ���ջ����
	 * �����ύ�Ĺ�Ӧ�̶�������������Կͻ�����������Ҫ�������ͻ�
	 * ���������¹����Ĳɹ�����
	 * @param detail
	 * @param isflowOrder �Ƿ����������ͬʱ�ύ
	 * @param content  �ύ˵������¼��־
	 * @return
	 * @throws Exception
	 */
	public int commitOrderDetail(VendorOrderDetail detail,boolean isflowOrder , String content) throws Exception{
		int ret =0;
		logger.debug("�ύ������ϸ������Ĳ���Ϊ��" + detail.toString());
		if(!isflowOrder){//���������������������Ҫ����״̬Ϊ02
			//detail.setState(VendorOrderConstants.VENDOR_ORDER_STATE_02);
			VendorOrderState orderState =(VendorOrderState)BeanLocator.getInstance().getBean(VendorOrderConstants.PRE_SPRING + detail.getPoNoType() + detail.getSettlementType());
			String state = orderState.getCommitState();
			detail.setState(state);
		}
		ret = updateOrderDetailByState(detail);
		//����ͻ����������Ĳɹ�����
		logger.debug("�ύ������ϸ������ͻ����������Ĳɹ����ţ�");
		String poNoType = detail.getPoNoType();
		if(null != poNoType && poNoType.equals(VendorOrderConstants.VENDOR_PO_NO_TYPE_0)){//�ɹ��������ͻ�����
			CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.CUST_ORDER_DETAIL_DAO);
			String[] strPoNo = detail.getRltOrderPoNo().split(",");
			if(null != strPoNo && strPoNo.length >0){
				for(String pono  : strPoNo){
					CustOrderDetail cDetail = new CustOrderDetail();
					cDetail.setCommCode(detail.getCustCcode());
					cDetail.setPoNo(pono);
					cDetail.setPartNo(detail.getPartNo());
					cDetail.setSpecDesc(detail.getSpecDesc());
//					cDetail = cDetailDao.findCustOrderDetailByBizKey(cDetail);
//					if(null != cDetail){
//						String rltPoNo = cDetail.getRltOrderPoNo();
//						if(null == rltPoNo){
							cDetail.setRltOrderPoNo(pono);
//						}else{
//							cDetail.setRltOrderPoNo(rltPoNo +"," + pono);
//						}
						cDetailDao.updateCustOrderDetailByRltPoNo(cDetail);
//					}
				}
			}
		}
		//���������־
		if(!isflowOrder){//����������������Ҫ��¼������־
			VendorLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), "�ύ" , "��Ӧ�̶�����ϸ", detail.getLogKey(), null, content);
		}
		return ret;
	}
	/**
	 * �ɹ���������⣬�������״̬
	 * �ɹ���ϸ���µ���������״̬���������60��ȫ�����61��
	 * ͬʱ�жϲɹ�����״̬���������60��ȫ�����61�� 
	 * @param detail
	 * @return 0--�ɹ�  2--���µ���Ŀ����ȷ  1 �޴˲ɹ�����ϸ
	 * @throws Exception
	 */
	public int updateOrderDetailByAmount(VendorOrderDetail detail) throws Exception{
		logger.debug("�ύ������ϸ���ɹ���������⣬�������״̬������Ĳ���Ϊ��" + detail.toString());
		int ret =0;
		int delmount = IntegerUtils.intValue(detail.getDeliveryAmount());
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		VendorOrderDao vDao =(VendorOrderDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DAO);
		VendorOrderDetail tempDetail = vDetailDao.findVendorOrderDetailByBizKey(detail);
		if(null != tempDetail){
			int iMount = IntegerUtils.intValue(tempDetail.getAmount());
			int iDelivMount = IntegerUtils.intValue(tempDetail.getDeliveryAmount());
			int updateAmount = delmount + iDelivMount;
			logger.debug("�ύ������ϸ,����������Ϊ��" + iMount +"�Ѿ��������Ϊ��" + iDelivMount + "�����������Ϊ��" + IntegerUtils.intValue(detail.getDeliveryAmount()));
			if(updateAmount > iMount){//���µ���Ŀ����
				//ret =2;
				logger.debug("�ύ������ϸ,���µ����벻��ȷ������������ < �Ѿ�������� + �����������");
				throw new Exception("�ύ������ϸ,���µ����벻��ȷ������������ < �Ѿ�������� + �����������");
			}else if(updateAmount < iMount){//�������
				logger.debug("�ύ������ϸ,���µ����룬���������� < �Ѿ�������� + ����������� ,������⣡");
				tempDetail.setState(VendorOrderConstants.VENDOR_ORDER_STATE_60);
				tempDetail.setDeliveryAmount(updateAmount);
				vDetailDao.updateVendorOrderDetailAmount(tempDetail);
				//���²ɹ���״̬Ϊ�������
				VendorOrder vOrder = new VendorOrder();
				vOrder.setCommCode(detail.getCommCode());
				vOrder.setPoNo(detail.getPoNo());
				vOrder.setState(VendorOrderConstants.VENDOR_ORDER_STATE_60);
				vDao.updateVendorOrderByState(vOrder);
			}else{//ȫ����⣬�����²ɹ�����ϸ�⣬����Ҫ���²ɹ���
				logger.debug("�ύ������ϸ,���µ����룬���������� = �Ѿ�������� + �������������ȫ����⣡");
				tempDetail.setState(VendorOrderConstants.VENDOR_ORDER_STATE_61);
				tempDetail.setDeliveryAmount(updateAmount);
				vDetailDao.updateVendorOrderDetailAmount(tempDetail);
				
				logger.debug("�ύ������ϸ,���¶���״̬");
				List<VendorOrderDetail> detailList = vDetailDao.listVendorOrderDetail(detail);
				boolean isAll = true;
				if(null != detailList && detailList.size()>0){
					VendorOrder vOrder = new VendorOrder();
					vOrder.setCommCode(detail.getCommCode());
					vOrder.setPoNo(detail.getPoNo());
					for(VendorOrderDetail vDetail : detailList){
						String state = vDetail.getState();
						if(state.equals(VendorOrderConstants.VENDOR_ORDER_STATE_60)){
							isAll =false;
							break;
						}
					}
					vOrder.setState( isAll ? VendorOrderConstants.VENDOR_ORDER_STATE_61 :VendorOrderConstants.VENDOR_ORDER_STATE_60);
					vDao.updateVendorOrderByState(vOrder);
				}
			}
		}else{//�޴˲ɹ�������ϸ��Ϣ
			logger.debug("�ύ������ϸ,�����޴˶�����ϸ��Ϣ�����ܸ��£�");
			//ret =1;
			throw new Exception("�ύ������ϸ,�����޴˶�����ϸ��Ϣ�����ܸ��£�");
		}
		
		
		return ret;
	}
	
	/**
	 *ȷ�����ڶ�����ϸ�Ľ��ڣ�ֻ�����ڶ�����ϸ��Ч
	 *���ڶ�����״̬��04��������ȷ�ϣ���Ϊ 02ȷ�϶����������
	 * @param vDetail
	* @param isflowOrder �Ƿ����������ͬʱ�ύ
	 * @param content  ˵������¼��־
	 * @return 0  �ɹ�   1  ״̬����ȷ
	 * @throws Exception
	 */
	public int confirmOrderDetailDelivery(VendorOrderDetail vDetail,
			boolean isflowOrder, String content) throws Exception {
		int ret = 0;
		logger.debug("ȷ�����ڶ�����ϸ�Ľ���,����Ĳ���Ϊ��" + vDetail.toString());
		String state = vDetail.getState();
		if (VendorOrderConstants.VENDOR_ORDER_STATE_04.equals(state)) {
			vDetail.setState(VendorOrderConstants.VENDOR_ORDER_STATE_02);

			if (vDetail.getVerDeliveryDate() == null) {
				vDetail.setVerDeliveryDate(vDetail.getOrgDeliveryDate());
			}
			updateOrderDetailByState(vDetail);
		}else{
			//ret =1;
			throw new Exception("ȷ�����ڶ�����ϸ�Ľ���,״̬����ȷ��");
		}
		//���������־
		if(!isflowOrder){//����������������Ҫ��¼������־
			VendorLogUtils.operLog(vDetail.getStaffId(), vDetail.getStaffName(), "����ȷ��" , "��Ӧ�̶�����ϸ", vDetail.getLogKey(), null, content);
		}
		return ret;
	}
	/**
	 * �����Ļ״̬���ƣ�������Ϊ�ɹ���Ա
	 * ǰ̨����ʱ����Ҫ�ı�״̬���ɺ�̨�жϸı�
	 * @param detail
	 * @param isflowOrder �Ƿ����������ͬʱ����
	 * @param content  ��ͣ ����ԭ�򣬼�¼��־
	 * @return
	 * @throws Exception
	 */
	public int controlActiveState(VendorOrderDetail detail,boolean isflowOrder,String content) throws Exception{
		int ret =0;
		logger.debug("�����Ļ״̬����,����Ĳ���Ϊ��" + detail.toString());
		if(!isflowOrder){
			String activeState = detail.getActiveState();
			if(VendorOrderConstants.VENDOR_ORDER_ACTIVE_STATE.equals(activeState)){
				detail.setActiveState(VendorOrderConstants.VENDOR_ORDER_PAUSE_STATE);
			}else{
				detail.setActiveState(VendorOrderConstants.VENDOR_ORDER_ACTIVE_STATE);
			}
		}
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		vDetailDao.updateVendorOrderDetailByActiveState(detail);
		//��¼������־
		if(!isflowOrder){
			VendorLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), ((detail.getActiveState()).equals(CustOrderConstants.ORDER_ACTIVE_STATE) ? "����" : "��ͣ") , "��Ӧ�̶�����ϸ", detail.getLogKey(), null, content);
		}
		return ret;
	}
	
	/**
	 * ���ݶ�����ϸ������ѯ������ϸ
	 * @param operSeqId
	 * @return
	 * @throws Exception
	 */
	public VendorOrderDetail getVendorOrderDetailById(String operSeqId) throws Exception{
		logger.debug("���ݶ�����ϸ������ѯ������ϸ,����Ĳ���Ϊ��" + operSeqId);
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		return vDetailDao.findVendorOrderDetailById(operSeqId);
	}
	/**
	 *����ҵ��������ѯ������ϸ
	 *ҵ������������
	 *  commCode poNo cpartNo partNo specDesc
	 * @param vendorOrderDetail
	 * @return
	 * @throws Exception
	 */
	public VendorOrderDetail getVendorOrderDetailByBizKey(VendorOrderDetail vendorOrderDetail) throws Exception{
		logger.debug("����ҵ��������ѯ������ϸ,����Ĳ���Ϊ��" + vendorOrderDetail.toString());
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		return vDetailDao.findVendorOrderDetailByBizKey(vendorOrderDetail);
	}
	
	/**
	 * ���ݲ�ѯ��������ѯ�����з��������Ķ�����ϸ�б�֧�ַ�ҳ
	 * @param vendorOrderDetail
	 * @return
	 * @throws Exception
	 */
	public List<VendorOrderDetail> getVendorOrderDetailList(VendorOrderDetail vendorOrderDetail) throws Exception{
		logger.debug("���ݲ�ѯ��������ѯ�����з��������Ķ�����ϸ�б�,����Ĳ���Ϊ��" + vendorOrderDetail.toString());
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		return vDetailDao.listVendorOrderDetail(vendorOrderDetail);
	}
	
	/**
	 * ���ݲ�ѯ��������ѯ�����з��������Ķ�����ϸ����
	 * @param vendorOrderDetail
	 * @return
	 * @throws Exception
	 */
	public Integer getVendorOrderDetailCount(VendorOrderDetail vendorOrderDetail) throws Exception{
		logger.debug("���ݲ�ѯ��������ѯ�����з��������Ķ�����ϸ����,����Ĳ���Ϊ��" + vendorOrderDetail.toString());
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		return vDetailDao.listVendorOrderDetailCount(vendorOrderDetail);
	}
	/**
	 * ˽�к��������¶�����ϸ״̬
	 * ���ֻ��operSeqid�����ǵ�������
	 * �������commCode��poNo �������ж�����ϸ������״̬
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	private int updateOrderDetailByState(VendorOrderDetail detail) throws Exception{
		int ret =0;
		VendorOrderDetailDao vDetailDao =(VendorOrderDetailDao)BeanLocator.getInstance().getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_DAO);
		vDetailDao.updateVendorOrderDetailByState(detail);
		return ret;
	}
}
