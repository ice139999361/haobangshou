package com.hbs.customerorder.action.detail;

import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.manager.WarehouseMgr;

@SuppressWarnings("serial")
public class CustOrderDetailCgNormalAction extends CustOrderDetailBaseAction {
	/**
	 * logger.
	 */
	protected static Logger logger = Logger.getLogger(CustOrderDetailCgNormalAction.class);

	/**
	 * �ɹ�ȷ�϶�����ϸ�Ľ���
	 * @action.input orderDetail.*
	 * @action.input memo ȡ��ԭ��
	 * @return
	 */
	public String doConfirmDelivery() {
		try {
			if(!checkKeyFields()) {
				setErrorReason("��������");
				return ERROR;
			}
			int i = mgr.purchaseConfirmDetailDelivery(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				setErrorReason("�ύ������");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmDelivery", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * �ɹ���ͬ�ⶩ����ϸ���ڣ��ύ��ҵ����������
	 * @action.input orderDetail.*
	 * @action.input memo ȡ��ԭ��
	 * @return
	 */
	public String doRefuseDelivery() {
		try {
			if(!checkKeyFields()) {
				setErrorReason("��������");
				return ERROR;
			}
			int i = mgr.purchaseRefuseDetailDelivery(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				setErrorReason("�ύ������");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doRefuseDelivery", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * �ɹ�Ա�鿴���Ͽ��
	 * @action.input orderDetail.operSeqId
	 * @action.result self.* ���ͻ����������Ϣ WareHouseInfo
	 * @action.result common.* ���汸�������Ϣ WareHouseInfo
	 * @action.result otherList �����ͻ����������Ϣ�б� List<WareHouseInfo>
	 * @return
	 */
	public String doGetStockInfo() {
		try {
			if(!findOrderDetail())
				return ERROR;

			WareHouseInfo wInfo = new WareHouseInfo();
			WarehouseMgr wmgr = (WarehouseMgr)getBean(WareHouseConstants.WAREHOUSE_INFO_MGR);
			//wInfo.setCpartNo(orderDetail.getCpartNo());
			wInfo.setPartNo(orderDetail.getPartNo());
			String commCode = orderDetail.getCommCode();
			boolean foundSelf = false;
			boolean foundCommon = false;
			List<WareHouseInfo> list = new Vector<WareHouseInfo>();
			for(WareHouseInfo wInfo2 : wmgr.listWareHouseInfo(wInfo)) {
				try{
					if(!WareHouseConstants.WAREHOUSE_INFO_STATE_0.equals(wInfo2.getState())
							||wInfo2.getUseAmount().intValue() <= 0
							)
						continue;
					if(WareHouseConstants.WAREHOUSE_USE_TYPE_1.equals(wInfo2.getHouseUse())) {
						// �ض��ͻ�����
						if(commCode.equals(wInfo2.getCustCode())) {
							// ���ͻ�����
							setResult("self", wInfo2);
							foundSelf = true;
						} else {
							// �����ͻ�����
							list.add(wInfo2);
						}
					} else {
						// ���汸��
						setResult("common", wInfo2);
						foundCommon = true;
					}
				} catch(Exception e) {
					logger.info("catch Exception in doGetStockInfo when check stock:" + wInfo2, e);
				}
			}
			setResult("otherList", list);
			
			//���û�п�棬�����ÿ��ÿ��Ϊ0
			wInfo.setUseAmount(0);
			wInfo.setTotalAmount(0);
			wInfo.setLockAmount(0);
			if(!foundSelf)
				setResult("self", wInfo);
			if(!foundCommon)
				setResult("common", wInfo);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doGetStockInfo", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * �¹�Ӧ�̶���ʱ�����ڽ��ͻ��������鰴�չ�Ӧ�̱���ָ�
	 * @action.input	operSeqId	���ָ�Ŀͻ���������id�����ֵ
	 * @action.result	commCode	��Ӧ�̱��룬���ܲ�����
	 * @action.result	list	�ͻ����������б� List<CustOrderDetail>
	 * @action.result	leftOperSeqId	ʣ��Ŀͻ���������id List<String>
	 * @return
	 */
	public String doCheckOperSeqId() {
		try {
			String commCode = null;
			List<String> left = new Vector<String>();
			List<CustOrderDetail> details = new Vector<CustOrderDetail>();
			String[] ids = this.getHttpServletRequest().getParameterValues("operSeqId");
			CustOrderDetail orderDetail2;
			for(String id : ids) {
				if(StringUtils.isEmpty(id))
					continue;
				try {
					orderDetail2 = mgr.findCustOrderDetailById(id);
					if(orderDetail2 == null)
						continue;
					
					// �жϿͻ����������Ƿ����µ�
					String state = orderDetail2.getState();
					if(!CustOrderConstants.ORDER_STATE_21.equals(state))
						continue;
					Integer i = orderDetail2.getAmount();
					if(i == null)
						continue;
					i -= isNull(orderDetail2.getDeliveryAmount(), 0);
					i -= isNull(orderDetail2.getLockAmount(), 0);
					if(i == null || i.compareTo(0) <= 0)
						continue;
					
					if(StringUtils.isEmpty(commCode)) {
						// ��û��ȷ����Ӧ��
						commCode = orderDetail2.getVendorCode();
					} else {
						// �Ѿ�ȷ���˹�Ӧ��
						if(!commCode.equals(orderDetail2.getVendorCode())) {
							left.add(id);
							continue;
						}
					}
					details.add(orderDetail2);
				} catch(Exception e) {
					logger.error("catch Exception in doCheckOperSeqId when checking " + id, e);
				}
			}
			setResult("list", details);
			setResult("leftOperSeqId", left);
			if(commCode != null)
				setResult("commCode", commCode);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doCheckOperSeqId", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * ���iΪnull���򷵻�v�����򷵻�i
	 * @param i	���жϵ�Integer
	 * @param v ȱʡ����ֵ
	 * @return ���iΪnull���򷵻�v�����򷵻�i
	 */
	private static Integer isNull(Integer i, int v) {
		if(i == null)
			return v;
		else
			return i;
	}
}