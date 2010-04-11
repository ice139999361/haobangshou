/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousereceive.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.OrderCalUtils;
import com.hbs.customerorder.manager.CustOrderDetailMgr;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;
import com.hbs.domain.warehouse.dao.WarehouseRecDetailDao;

import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;

import com.hbs.vendororder.manager.VendorOrderDetailMgr;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;
import com.hbs.warehouse.manager.WarehouseMgr;
import com.hbs.warehousereceive.manager.helper.WarehouseHelper;

/**
 * @author Administrator
 *
 */
public class WareHouseRecDetailMgr {
	private static final Logger logger = Logger.getLogger(WareHouseRecDetailMgr.class);
	
	/**
	 * ������ⵥ��ϸ������ʱ���жϿ����Ƿ���ڣ�������insert����
	 * ���� update������update������״̬������ 01 ��ʱ״̬���ܱ����޸�
	 * ���״̬Ϊȷ�ϣ���
	 * �����ȷ�ϲ���������Ҫִ�����²���
		 * �ж�����������ϸ���ڵĹ�Ӧ�̶�����ţ�
		 * ��ѯ��������ϸ���ж϶�����ϸ�Ķ�������
		 * 2---���汸���ɹ���
		 * 3---�ض��ͻ������ɹ���
		 * ����Ҫ���²ֿ��棬���¹�Ӧ�̶����ĵ�������
		 * 0----�ͻ��ɹ���
		 * �����Ҫ���²ֿ��棬���¹�Ӧ�̶����ĵ���������
		 * ����Ҫ���µ��ͻ�����������������ȥ��ִ���Զ����� 
	 * @param detail
	 * @param isflowRec �Ƿ��������ⵥͬʱ����
	 * @param content   �������
	 * @return  >= 0 �ɹ�  -1 ��״̬����ϸ�������޸�
	 * @throws Exception
	 */
	public int saveWareHouseRecDetail(WarehouseRecDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		String st =detail.getState();
		if(st.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){
			logger.debug("���湩Ӧ�����������ϸ������Ĳ���Ϊ:" + detail.toString());
		}else if(st.equals(WareHouseConstants.WAREHOUSE_REC_INFO_02)){
			logger.debug("ȷ�Ϲ�Ӧ�����������ϸ������Ĳ���Ϊ:" + detail.toString());
		}else{
			logger.debug("ȷ�Ϲ�Ӧ�����������ϸ,״̬Ϊȡ����������������Ĳ���Ϊ:" + detail.toString());
			return ret;
		}
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		//������
		detail.setCurrMoney(OrderCalUtils.calOrderMoney(detail.getPrice(), detail.getIsTax(), detail.getTaxRate(),detail.getPriceTax(),null, detail.getAmount()));
		//��������
		String period = detail.getPeriod();
		if(period == null){
			WarehouseHelper helper =(WarehouseHelper)BeanLocator.getInstance().getBean(WareHouseConstants.PRE_SPRING + detail.getPoNoType() + detail.getSettlementType());
			detail.setPeriod(helper.getPeriod(detail));
			detail.setFinancePeriod(detail.getPeriod());				
		}
		WarehouseRecDetail existDetail = detailDao.findWarehouseRecDetailByBizKey(detail);
		if(null == existDetail){//�����ڣ�insert������
			logger.debug("���ݿ����޴���ⵥ��ϸ��insert������");
			
			Integer id = detailDao.insertWarehouseRecDetail(detail);
			detail.setRecDetailSeqId(id);
			ret = id;
			WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), (detail.getState().equals(WareHouseConstants.WAREHOUSE_REC_INFO_02) ? "ȷ��" : "����"), "��ⵥ������ϸ", detail.getLogKey(), null, content);
		}else{//��update����
			String state = existDetail.getState();
			logger.debug("���ݿ��д�����ⵥ��ϸ��update��������ϸ״̬Ϊ��" + state);
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//״̬Ϊ��ʱ�������޸�
				
				detail.setRecDetailSeqId(existDetail.getRecDetailSeqId());
				detailDao.updateWarehouseRecDetail(detail);
				ret = detail.getRecDetailSeqId();
				WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), (detail.getState().equals(WareHouseConstants.WAREHOUSE_REC_INFO_02) ? "ȷ��" : "�޸�"), "��ⵥ������ϸ", detail.getLogKey(), null, content);
			}else{//״̬����ȷ���������޸�
				logger.debug("���ݿ��д�����ⵥ��ϸ��update������״̬����ȷ���������޸ģ�");
				ret = -1;
			}
			
		}
		/**
		 * �����ȷ�ϲ���������Ҫִ�����²���
		 * �ж�����������ϸ���ڵĹ�Ӧ�̶�����ţ�
		 * ��ѯ��������ϸ���ж϶�����ϸ�Ķ�������
		 * 2---���汸���ɹ���
		 * 3---�ض��ͻ������ɹ���
		 * ����Ҫ���²ֿ��棬���¹�Ӧ�̶����ĵ�������
		 * 0----�ͻ��ɹ���
		 * �����Ҫ���²ֿ��棬���¹�Ӧ�̶����ĵ���������
		 * ����Ҫ���µ��ͻ�����������������ȥ��ִ���Զ�����
		*/
		if(st.equals(WareHouseConstants.WAREHOUSE_REC_INFO_02)){
			logger.debug("��ⵥ��ϸ��⣬�����Ӧ�Ĺ�Ӧ�̲ɹ�����");
			//���¶��ڵĲɹ�����ϸ���ѵ�������
			processVendorOrderDetail( detail);
		}
		return ret;
	}
	private int processVendorOrderDetail(WarehouseRecDetail detail) throws Exception{
		int ret =0;
		VendorOrderDetail vOrderDetail = new VendorOrderDetail();
		//���ù�Ӧ�̱���
		vOrderDetail.setCommCode(detail.getVendorCode());
		//���ù�Ӧ�̵Ĳɹ���PONO
		vOrderDetail.setPoNo(detail.getRltPoNo());
		//���ù�Ӧ�̵����ϱ���
		vOrderDetail.setCpartNo(detail.getCpartNo());
		//���ñ���˾�����ϱ���
		vOrderDetail.setPartNo(detail.getPartNo());
		//���ù�Ӧ�̲ɹ����е����ⱸע
		vOrderDetail.setSpecDesc(detail.getSpecDesc());
		logger.debug("���ù�Ӧ�̲ɹ�����ϸ�����࣬�����ѵ������������õĲ���Ϊ��" + vOrderDetail.toString());
		VendorOrderDetailMgr vOrderDetailMgr =(VendorOrderDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.VENDOR_ORDER_DETAILMGR);
		ret = vOrderDetailMgr.updateOrderDetailByAmount(vOrderDetail);
		if(ret ==0 ){
			logger.debug("��Ӧ�̲ɹ�����ϸ�ѵ����������³ɹ���");
			logger.debug("��ѯ�ɹ������жϲɹ������ͣ�");
			VendorOrderDetail existOrderDetail = vOrderDetailMgr.getVendorOrderDetailByBizKey(vOrderDetail);
			String vPonoType = existOrderDetail.getPoNoType();
			
			String custCode = existOrderDetail.getCustCcode();
			String custPoNo = existOrderDetail.getRltOrderPoNo();
			String custSpecDesc = existOrderDetail.getSpecDesc();
			switch(Integer.parseInt(vPonoType)){
			case 0: //�ͻ���Ӧ�̲ɹ���
				logger.debug("��Ӧ�̲ɹ���Ϊ�ͻ�������");
				ret =processCustOrderDetail( detail, custCode, custPoNo, custSpecDesc);
				break;
			case 1: //�˻���
				logger.debug("��Ӧ�̲ɹ���Ϊ�˻�����");
				break;
			case 2: //���汸��
				logger.debug("��Ӧ�̲ɹ���Ϊ���汸��������");
				ret = processWarehouseInfo(detail,custCode);
				break;
				
			case 3: //�ض��ͻ�����				
				logger.debug("��Ӧ�̲ɹ���Ϊ�ض��ͻ�����������");
				ret = processWarehouseInfo(detail,custCode);
				break;
			default:
				throw new Exception("��ⵥ��Ӧ�Ĺ�Ӧ�̲ɹ����Ķ������Ͳ���ȷ���޷���������");
				
			}
			
		}else{			
			throw new Exception("��Ӧ���̲ɹ��������ѵ�������ʧ�ܣ�ʧ�ܴ��룺" + ret);
		}
		return ret;
	}
	/**
	 * ˽�к���������ͻ���������������²ֿ������Ϳ�����������������
	 * @param detail
	 * @param custCode
	 * @param custPoNo
	 * @param custSpecDesc
	 * @return
	 * @throws Exception
	 */
	private int processCustOrderDetail(WarehouseRecDetail detail,String custCode,String custPoNo,String custSpecDesc) throws Exception{
		int ret =0;
		CustOrderDetail orderDetail = new CustOrderDetail();
		//���ÿͻ�����
		orderDetail.setCommCode(custCode);
		//���ÿͻ�������pono
		orderDetail.setRltOrderPoNo(custPoNo);
		//�������ϱ��
		orderDetail.setPartNo(detail.getPartNo());
		//�������ⱸע
		orderDetail.setSpecDesc(custSpecDesc);
		CustOrderDetailMgr custDetailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.CUST_ORDER_DETAILMGR);
		CustOrderDetail existDetail = custDetailMgr.findCustOrderDetailByBizKey(orderDetail);
		//���ܵĲֿⱣ��Ŀ�������
		int iuseAmount =0;
		if(existDetail != null){
			int ineedLock = existDetail.getAmount() - existDetail.getLockAmount();
			if(ineedLock >= detail.getAmount()){//�ͻ���������Ҫ����������> �����ͻ�����
				ineedLock = detail.getAmount();
			}else{
				iuseAmount = detail.getAmount() - ineedLock;
			}
			orderDetail.setOperSeqId(existDetail.getOperSeqId());
			orderDetail.setSelfLockAmount(ineedLock);
			//�ͻ�����������������
			custDetailMgr.lockOrderDetailAmount(orderDetail, null, null, null);
			//�ֿ�����������
			WareHouseInfo wInfo = new WareHouseInfo();
			//���òֿ����ͣ��ܿ⻹������
			wInfo.setHouseType(detail.getHouseType());
			
			wInfo.setHouseType(WareHouseConstants.WAREHOUSE_USE_TYPE_1);
				
			//���ù�Ӧ�̱���
			wInfo.setVendorCode(detail.getVendorCode());
			//���ñ���˾���ϱ���
			wInfo.setPartNo(detail.getPartNo());
			//���ù�Ӧ�̵����ϱ���
			wInfo.setCpartNo(detail.getCpartNo());
			//�������ӵĿ��
			wInfo.setTotalAmount(detail.getAmount());
			wInfo.setUseAmount(iuseAmount);
			wInfo.setLockAmount(ineedLock);
			//���ÿͻ�����
			wInfo.setCustCode(custCode);
			WarehouseMgr whMgr= (WarehouseMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_MGR);
			ret = whMgr.saveLockWareHouseInfo(wInfo, null, null, null);
		}else{
			throw new Exception("���չؼ����޷��ҵ���Ӧ�Ŀͻ��������޷���⣬�ؼ���Ϊ��" + orderDetail.toString());
			
		}
		return ret;
	}
	/**
	 * ˽�к����������汸�����ض��ͻ���������������²ֿ������Ϳ�������
	 * @param detail
	 * @param custCode
	 * @return
	 * @throws Exception
	 */
	private int processWarehouseInfo(WarehouseRecDetail detail,String custCode) throws Exception{
		int ret =0;
		WareHouseInfo wInfo = new WareHouseInfo();
		//���òֿ����ͣ��ܿ⻹������
		wInfo.setHouseType(detail.getHouseType());
		//���òֿ���;
		String vPonoType = detail.getPoNoType();
		switch(Integer.parseInt(vPonoType)){
		case 0: //�ͻ���Ӧ�̲ɹ���
		case 1: //�˻���
		case 3: //�ض��ͻ�����
			wInfo.setHouseType(WareHouseConstants.WAREHOUSE_USE_TYPE_1);
			break;		
		case 2: //���汸��
			wInfo.setHouseType(WareHouseConstants.WAREHOUSE_USE_TYPE_2);
			break;	
			
		default:
			throw new Exception("��ⵥ��Ӧ�Ĳֿ���Ϣ�ֿ���;����ȷ���޷���������");
		}
		//���ù�Ӧ�̱���
		wInfo.setVendorCode(detail.getVendorCode());
		//���ñ���˾���ϱ���
		wInfo.setPartNo(detail.getPartNo());
		//���ù�Ӧ�̵����ϱ���
		wInfo.setCpartNo(detail.getCpartNo());
		//�������ӵĿ��
		wInfo.setTotalAmount(detail.getAmount());
		wInfo.setUseAmount(detail.getAmount());
		//���ÿͻ�����
		wInfo.setCustCode(custCode);
		WarehouseMgr whMgr= (WarehouseMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_MGR);
		ret = whMgr.saveInWareHouseInfo(wInfo);
		if(ret !=0){
			throw new Exception("��ⵥ��ϸ���¿����󣬴�����룺" + ret);
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
	public int saveWareHouseRecDetailList(List<WarehouseRecDetail> detailList,boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("����������ⵥ��ϸ����ϸ������Ϊ:" + detailList.size());
		for(WarehouseRecDetail detail : detailList){
			Integer i = saveWareHouseRecDetail(detail,isflowRec,content);
			logger.debug("����������ⵥ��ϸ,����Ľ��Ϊ��" + i);
		}
		return ret;
	}
	
	/**
	 * ȡ����ⵥ��ϸ�����ж�ȡ������ⵥ��ϸ�Ƿ���ڣ������ڣ�����-2
	 * ���ڣ��ж�״̬�Ƿ�Ϊ��ʱ״̬������״̬������ȡ����
	 * @param detail
	 * @param isflowRec �Ƿ��������¼
	 * @param content ȡ���������¼��־
	 * @return 0--�ɹ�  -1 ״̬����ȷ������ȡ�� -2 ȡ������ϸ������
	 * @throws Exception
	 */
	public int cancelWareHouseRecDetail(WarehouseRecDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("ȡ����ⵥ��ϸ��Ϣ,����Ĳ���Ϊ��" + detail.toString());
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		WarehouseRecDetail existDetail = detailDao.findWarehouseRecDetailByBizKey(detail);
		if(null != existDetail){//������ⵥ��ϸ
			String state = existDetail.getState();
			logger.debug("���ݿ��д�����ⵥ��ϸ����ϸ״̬Ϊ��" + state);
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//Ϊ��ʱ״̬������ȡ��
				existDetail.setState(WareHouseConstants.WAREHOUSE_REC_INFO_03);
				detailDao.updateWarehouseRecDetailByState(existDetail);
				WareHouseLogUtils.operLog(existDetail.getStaffId(), existDetail.getStaffName(), "ȡ��", "��ⵥ��ϸ", existDetail.getLogKey(), null, content);
			}else{//����ʱ״̬������ȡ��
				logger.debug("���ݿ��д�����ⵥ��ϸ����ϸ״̬�Ѿ�Ϊ����ʱ״̬��������ȡ��������");
				ret =-1;
			}
		}else{//��������ⵥ��ϸ���޷���ȡ��
			ret = -2;
		}
		return ret;
	}
	
	/**
	 * ����ȡ����ⵥ��ϸ�����õ���ȡ������
	 * @param detailList
	 * @param isflowRec �Ƿ������ⵥͬʱ����
	 * @param content  ȡ��˵������¼��־
	 * @return
	 * @throws Exception
	 */
	public int cancelWareHouseRecDetailList(List<WarehouseRecDetail> detailList,boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("����ȡ����ⵥ��ϸ����ϸ������Ϊ:" + detailList.size());
		for(WarehouseRecDetail detail : detailList){
			cancelWareHouseRecDetail(detail,isflowRec,content);			
		}
		return ret;
	}
	/**
	 * ��ͣ/���Ӧ����ⵥ��ϸ������Ĳ�������Ҫ�޸�ԭʼ״̬
	 * ��ϵͳ���������
	 * @param detail
	 * @param isflowRec
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int controlActiveState(WarehouseRecDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("��Ӧ����ⵥ��ϸ��ͣ/�������������Ĳ���Ϊ��" + detail.toString());
		String activeState = detail.getActiveState();
		if(activeState.equals(WareHouseConstants.WAREHOUSE_REC_ACTIVE)){
			detail.setActiveState(WareHouseConstants.WAREHOUSE_REC_PAUSE);
		}else{
			detail.setActiveState(WareHouseConstants.WAREHOUSE_REC_ACTIVE);
		}
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		detailDao.updateWarehouseRecDetailByActiveState(detail);
		WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), (activeState.equals(WareHouseConstants.WAREHOUSE_REC_ACTIVE) ? "����" : "��ͣ"), "��Ӧ����ⵥ", detail.getLogKey(), null, content);
		return ret;
	}
	/**
	  * ������ͣ/���Ӧ����ⵥ��ϸ������Ĳ�������Ҫ�޸�ԭʼ״̬
	 * ��ϵͳ���������
	 * @param detailList
	 * @param isflowRec
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int controlActiveStateList(List<WarehouseRecDetail> detailList,boolean isflowRec,String content) throws Exception{
		int ret =0;
		for(WarehouseRecDetail detail : detailList){
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
	public int adjustFinancePeriod(WarehouseRecDetail detail,String staffId,String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("���������Ӧ����ⵥ��ϸ���ڣ�����Ĳ���Ϊ��" + detail.toString());
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		detailDao.updateWarehouseRecDetailByFinancePeriod(detail);
		WareHouseLogUtils.operLog(staffId, staffName, "��������", "��Ӧ����ⵥ��ϸ", detail.getLogKey(), null, content);
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
	public int adjustFinancePeriodList(List<WarehouseRecDetail> detailList,String staffId,String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("���������Ӧ����ⵥ��ϸ�б����ڣ���ĿΪ��" + detailList.size());
		for(WarehouseRecDetail detail : detailList){
			adjustFinancePeriod( detail, staffId, staffName,  content);
		}
		return ret;
	}
	/**
	 * ���������Ѷ���,�����ⵥ��ϸ��
	 * �����Ѷ��˶���ⵥ���鲻����
	 * 
	 * @param detail
	 * @param staffId
	 * @param staffName
	 * @param content
	 * @return 0 �ɹ�  -1 �����Ѿ����ˣ�����Ҫ�ٶ�
	 * @throws Exception
	 */
	public int setFinanceState(WarehouseRecDetail detail,String staffId,String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("���������Ѷ��ˣ�����Ĳ���Ϊ��" + detail.toString());
		String financeState = detail.getFinanceState();
		if(financeState.equals(WareHouseConstants.WAREHOUSE_REC_FINANCE_STATE_1)){
			ret =-1;
			logger.debug("������Ӧ����ⵥ��ϸ�Ѿ����ˣ�����Ҫ�ٴ���!");
		}else{
			WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
			detail.setFinanceState(WareHouseConstants.WAREHOUSE_REC_FINANCE_STATE_1);
			detailDao.updateWarehouseRecDetailByFinanceState(detail);
			WareHouseLogUtils.operLog(staffId, staffName, "�������", "��Ӧ����ⵥ��ϸ", detail.getLogKey(), null, content);
			//������ϸȷ����ⵥ�Ĳ���״̬
//			WarehouseRecDetail rDetail = new WarehouseRecDetail();
//			rDetail.setRecPoNo(detail.getRecPoNo());
//			rDetail.setVendorCode(detail.getVendorCode());
//			rDetail.setState(WareHouseConstants.WAREHOUSE_REC_INFO_02);
//			List<WarehouseRecDetail> dList = detailDao.listWarehouseRecDetail(rDetail);
//			String infoFState = WareHouseConstants.WAREHOUSE_REC_FINANCE_STATE_1;
//			for(WarehouseRecDetail recDetail : dList){
//				String fstate = recDetail.getFinanceState();
//				if(fstate.equals(WareHouseConstants.WAREHOUSE_REC_FINANCE_STATE_0)){
//					infoFState = WareHouseConstants.WAREHOUSE_REC_FINANCE_STATE_0;
//					break;
//				}
//			}
//			WarehouseRecInfo recInfo = new WarehouseRecInfo();
//			recInfo.setRecPoNo(detail.getRecPoNo());
//			recInfo.setFinanceState(infoFState);
//			recInfo.setVendorCode(detail.getVendorCode());
//			WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
//			whrInfoDao.updateWarehouseRecInfoByFinanceState(recInfo);
		}
		return ret;
	}
	
	
	public int setFinanceStateList(List<WarehouseRecDetail> detailList,String staffId,String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("�������������Ѷ��ˣ�����Ϊ��" + detailList.size());
		for(WarehouseRecDetail recDetail : detailList){
			setFinanceState(recDetail, staffId, staffName,  content);
		}
		return ret;
	}
	/**
	 * ��ѯ������ⵥ��ϸ������������������sequence
	 * Ҳ���������ҵ��ؼ��֣�REC_PO_NO C_CODE SETTLEMENT_TYPE PART_NO C_PART_NO PO_NO_TYPE SPEC_DESC
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public WarehouseRecDetail getWarehouseRecDetail(WarehouseRecDetail detail) throws Exception{
		WarehouseRecDetail retDetail = null;
		logger.debug("��ѯ������ⵥ��ϸ������Ĳ���Ϊ��" +detail.toString());
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		retDetail = detailDao.findWarehouseRecDetailByBizKey(detail);
		return retDetail;
	}
	
	/**
	 * ��ѯ��ⵥ��ϸ�б�
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public List<WarehouseRecDetail> getWarehouseRecDetailList(WarehouseRecDetail detail) throws Exception{
		List<WarehouseRecDetail> retDetailList = null;
		logger.debug("��ѯ��ⵥ��ϸ�б�����Ĳ���Ϊ��" +detail.toString());
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		retDetailList = detailDao.listWarehouseRecDetail(detail);
		return retDetailList;
	}
	
	/**
	 * ���ݲ�ѯ��������ѯ������������ⵥ��ϸ����
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public Integer getWarehouseRecDetailCount(WarehouseRecDetail detail) throws Exception{
		Integer retCount = 0;
		logger.debug("��ѯ��ⵥ��ϸ�б�����������Ĳ���Ϊ��" +detail.toString());
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		retCount = detailDao.listWarehouseRecDetailCount(detail);
		return retCount;
	}
}
