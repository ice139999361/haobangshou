package com.hbs.customerorder.action.detail;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.hbs.common.utils.HumanReadableException;
import com.hbs.common.utils.ListDataUtil;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorPartNoInfo;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.vendorinfo.action.VendorPartNoInfoNormalAction;
import com.hbs.vendorinfo.manager.VendorPartNoInfoMgr;
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
			if(!this.findOrderDetail()) {
				return ERROR;
			}
			int i = mgr.purchaseConfirmDetailDelivery(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				setErrorReason("�ύ����");
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
	 * �ɹ���ͬ�ⶩ����ϸ���ڣ��ύ��ҵ��������
	 * @action.input orderDetail.*
	 * @action.input vendorDate
	 * @action.input memo ȡ��ԭ��
	 * @return
	 */
	public String doRefuseDelivery() {
		try {
			if(!findOrderDetail()) {
				return ERROR;
			}
			
			Date d = null;
			String s = this.getHttpServletRequest().getParameter("vendorDate");
			if(StringUtils.isNotEmpty(s)){	
				try {
					DateTimeFormatter fmt = DateTimeFormat.forPattern(ListDataUtil.DATEFORMAT);
					DateTime dt = fmt.parseDateTime(s);
					Calendar c = Calendar.getInstance();
					c.set(dt.getYear(), dt.getMonthOfYear() - 1, dt.getDayOfMonth());
					d = c.getTime();
				} catch (Exception e) {
					String str = "���ڸ�ʽ����";
					logger.debug(str + " " + s);
					setErrorReason(str);
					return ERROR;
				}
			}
			if(d != null)
				orderDetail.setPreDeliveryDate(d);
			else{
				// DONE: ʹ����һ������
				//orderDetail.setVerDeliveryDate(orderDetail.getPreDeliveryDate());
			}
			
			int i = mgr.purchaseRefuseDetailDelivery(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				setErrorReason("�ύ����");
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
				if(wInfo2 == null)
					continue;
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
			// ����Ϊ��null������jsonʱ�׳��쳣
			wInfo.setHouseSeqId(0);
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
	 * �ù�������ʱȡ��
	 * �¹�Ӧ�̶���ʱ�����ڽ��ͻ��������鰴�չ�Ӧ�̱���ָ�
	 * @action.input	operSeqId	���ָ�Ŀͻ���������id����,�ָ�
	 * @action.result	commCode	��Ӧ�̱��룬���ܲ�����
	 * @action.result	list	�ͻ����������б� List<CustOrderDetail>
	 * @action.result	leftOperSeqId	ʣ��Ŀͻ���������id List<String>
	 * @return
	 */
	@Deprecated
	public String doCheckOperSeqId() {
		try {
			String commCode = null;
			List<String> left = new Vector<String>();
			List<CustOrderDetail> details = new Vector<CustOrderDetail>();
			String[] ids = this.getHttpServletRequest().getParameter("operSeqId").split(",");
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
	 * ���ݹ�Ӧ�̱����ѯ�豸���Ŀͻ���������
	 * ����commCode��cpartno�����ۡ�˰�ʻ��ɹ�Ӧ�̵�������Ϣ���Ա�ǰ̨��ʾ����ȷ�Ĺ�Ӧ�̶�����Ϣ
	 * @action.input orderDetail.vendorCode
	 * @action.input orderDetail.*
	 * @action.result list List<CustOrderDetail>
	 * @return
	 */
	public String doListStockupByVendor() {
		try {
			if(orderDetail == null || StringUtils.isEmpty(orderDetail.getVendorCode())){
				logger.debug("����Ϊ�գ�");
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			orderDetail.setField("stateList", "'20','21'");
			List<CustOrderDetail> list = mgr.listCustOrderDetail(orderDetail); 
			VendorPartNoInfoMgr vpnMgr = (VendorPartNoInfoMgr)getBean(VendorPartNoInfoNormalAction.vendorPartNoInfoMgrName);
			for(Iterator<CustOrderDetail> it = list.iterator(); it.hasNext();) {
				CustOrderDetail o = it.next();
				int needAmount = o.getAmount() - isNull(o.getLockAmount(),0) - isNull(o.getDeliveryAmount(),0) - isNull(o.getOrderAmount(), 0);
				if(needAmount <= 0)
					it.remove();
				o.setAmount(needAmount);
				//��commCode��cpartno�����ۡ�˰�ʡ����ɹ�Ӧ�̵�������Ϣ
				VendorPartNoInfo vpn = new VendorPartNoInfo();
				vpn.setCommCode(o.getVendorCode());
				vpn.setPartNo(o.getPartNo());
				vpn.setState("0");
				vpn = vpnMgr.getVendorPartNoInfoByBizKey(vpn);
				if(vpn == null)
					throw new HumanReadableException("��Ӧ�����ϲ����ڣ���Ӧ��"+o.getVendorCode()+", ����˾���ϱ���"+o.getPartNo());
				o.setCommCode(vpn.getCommCode());
				o.setCpartNo(vpn.getCustPartNo());
				o.setPnDesc(vpn.getPnDesc());
				o.setCprice(vpn.getPrice());
				o.setCpriceTax(vpn.getPriceTax());
				o.setTaxRate(vpn.getPriceTax());
				o.setMoney(vpn.getPrice().multiply(new BigDecimal(needAmount)));
				
			}
			setResult("list", list);
			// DONE:CustOrderCgNormalAction.doListByVendor
			return SUCCESS;
		}catch(HumanReadableException hre){
			logger.error("catch HumanReadableException in doListStockupByVendor " + hre.getMessage());
			setErrorReason(hre.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("catch Exception in doListStockupByVendor", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * �������
	 * @action.input orderDetail.* key
	 * @action.input self.lockAmount	���β������ͻ������������
	 * @action.input common.lockAmount	���β���ͨ�ÿ����������
	 * @return
	 */
	public String doLockAmount(){
		try {
			logger.debug("begin doLockAmount");
			if(!findOrderDetail())
				return ERROR;
			
			if(CustOrderConstants.ORDER_STATE_20.equals(orderDetail.getState())){
				String s = "״̬����";
				setErrorReason(s);
				logger.debug(s + orderDetail.getState());
				return ERROR;
			}
				
			try{
				orderDetail.setSelfLockAmount(Integer.parseInt(this.getHttpServletRequest().getParameter("self.lockAmount")));
			}catch(Exception e){};
			try{
				orderDetail.setCommLockAmount(Integer.parseInt(this.getHttpServletRequest().getParameter("common.lockAmount")));
			}catch(Exception e){};
			int i = mgr.lockOrderDetailAmount(orderDetail, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), null);
			if(i != 0){
				String s;
				switch(i){
				case -1:
					s = "�޷��ҵ��ͻ�������ϸ";
					break;
				case -2:
					s = "�����������ڶ�����ϸ��������";
					break;
				default:
					s = "��������";
					break;
				}
				setErrorReason(s);
				logger.error(s + " ret = " + i);
				return ERROR;
			}
			logger.debug("end doLockAmount");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doLockAmount", e);
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
