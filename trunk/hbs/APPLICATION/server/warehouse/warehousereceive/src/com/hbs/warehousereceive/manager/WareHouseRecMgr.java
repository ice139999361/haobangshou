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
import com.hbs.domain.warehouse.dao.WarehouseRecInfoDao;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;
import com.hbs.domain.warehouse.pojo.WarehouseRecInfo;

import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;
import com.hbs.warehousereceive.manager.helper.WarehouseHelper;

/**
 * @author Administrator
 *
 */
public class WareHouseRecMgr {
	private static final Logger logger = Logger.getLogger(WareHouseRecMgr.class);
	
	/**
	 * ������ⵥ��������ϸ��
	 * �����ж��Ƿ���ڣ������ڣ���insert����
	 * ����update���������״̬����ȷ���򷵻�-1
	 * @param whrInfo
	 * @param content
	 * @return 0  �ɹ�  -1 ״̬����ȷ����������
	 * @throws Exception
	 */
	public int saveWareHouseRecInfo(WarehouseRecInfo whrInfo,String content) throws Exception{
		int ret =0;
		String st = whrInfo.getState();
		if(st.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){
			logger.debug("������ⵥ��Ϣ������Ĳ���Ϊ��" + whrInfo.toString());
		}else{
			logger.debug("ȷ����ⵥ��Ϣ������Ĳ���Ϊ��" + whrInfo.toString());
		}
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		//�������ڣ������ϵ���ʱ��Ϊ��׼����
		WarehouseHelper helper =(WarehouseHelper)BeanLocator.getInstance().getBean(WareHouseConstants.PRE_SPRING + whrInfo.getPoNoType() + whrInfo.getSettlementType());
		whrInfo.setPeriod(helper.getPeriod(whrInfo));
		//���ݴ���Ĳ�������ҵ��������ѯ�Ƿ������ⵥ
		WarehouseRecInfo existInfo = whrInfoDao.findWarehouseRecInfo(whrInfo);
		if(null == existInfo){//�����ڣ���Ҫinsert����			
			//whrInfo.setState(WareHouseConstants.WAREHOUSE_REC_INFO_01);
			whrInfoDao.insertWarehouseRecInfo(whrInfo);
			WareHouseLogUtils.operLog(whrInfo.getOperId(), whrInfo.getOperStaff(), (whrInfo.getState().equals(WareHouseConstants.WAREHOUSE_REC_INFO_02) ? "ȷ��" : "����"), "��Ӧ���������", whrInfo.getLogKey(), null, content);

		}else{//����,���޸Ĳ���
			//�ж���ⵥ״̬
			String state = existInfo.getState();
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//�����޸�
				whrInfoDao.updateWarehouseRecInfo(whrInfo);
				WareHouseLogUtils.operLog(whrInfo.getOperId(), whrInfo.getOperStaff(), (whrInfo.getState().equals(WareHouseConstants.WAREHOUSE_REC_INFO_02) ? "ȷ��" : "�޸�"), "��Ӧ���������", whrInfo.getLogKey(), null, content);
			}else{//״̬����ȷ�������޸�
				ret = -1;
			}
		}
		if(ret ==0){//������ⵥ��ϸ
			List<WarehouseRecDetail> detailList = whrInfo.getDetailList();
			if(null != detailList){//��ⵥ��ϸ����
				if(st.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){
					logger.debug("������ⵥ��Ϣ����ⵥ�´�����ⵥ��ϸ��������ⵥ��ϸ������Ϊ��" + detailList.size());
				}else{
					logger.debug("ȷ����ⵥ��Ϣ����ⵥ�´�����ⵥ��ϸ��ȷ����ⵥ��ϸ������Ϊ��" + detailList.size());
				}
				for(WarehouseRecDetail detail : detailList){
					detail.setState(whrInfo.getState());
				}
				WareHouseRecDetailMgr detailMgr = (WareHouseRecDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAILMGR);
				detailMgr.saveWareHouseRecDetailList(detailList, true, content);
			}
		}
		
		return ret;
	}
	/**
	 * ȡ����ⵥ��ͬʱȡ������ⵥ�µ�������ⵥ��ϸ
	 * �����ж���ⵥ�Ƿ���ڣ������ڣ�����-2
	 * ���� �ж�״̬�Ƿ�Ϊ��ʱ״̬��������ʱ״̬��������ȡ��
	 * ����ʱ״̬��ȡ����ⵥ
	 * @param whrInfo
	 * @param content  ȡ�����
	 * @return 0--�ɹ�  -1 --״̬����ȷ -2 --��ⵥ������
	 * @throws Exception
	 */
	public int cancelWareHouseRecInfo(WarehouseRecInfo whrInfo,String content) throws Exception{
		int ret =0;
		logger.debug("ȡ����ⵥ��Ϣ������Ĳ���Ϊ��" + whrInfo.toString());
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		//���ݴ���Ĳ�������ҵ��������ѯ�Ƿ������ⵥ
		WarehouseRecInfo existInfo = whrInfoDao.findWarehouseRecInfo(whrInfo);
		if(null != existInfo){//����
			String state = existInfo.getState();
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//����ȡ��
				existInfo.setState(WareHouseConstants.WAREHOUSE_REC_INFO_03);
				existInfo.setOperId(whrInfo.getOperId());
				existInfo.setOperStaff(whrInfo.getOperStaff());
				whrInfoDao.updateWarehouseRecInfoByState(existInfo);
				WareHouseLogUtils.operLog(existInfo.getOperId(), existInfo.getOperStaff(), "ȡ��", "��Ӧ���������", existInfo.getLogKey(), null, content);
				
				//��ⵥ��ϸ
				List<WarehouseRecDetail> detailList = whrInfo.getDetailList();
				WareHouseRecDetailMgr detailMgr = (WareHouseRecDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAILMGR);
				if(null == detailList){//��ⵥ��ϸ����ǰ̨�п���û�д��б����ݣ���ȡ
					WarehouseRecDetail detail = new WarehouseRecDetail();
					detail.setRecPoNo(whrInfo.getRecPoNo());
					detail.setVendorCode(whrInfo.getVendorCode());
					detailList = detailMgr.getWarehouseRecDetailList(detail);
				}
				if(null != detailList && detailList.size() >0){//��ⵥ��ϸ����
					logger.debug("ȡ����ⵥ��Ϣ����ⵥ�´�����ⵥ��ϸ��ȡ����ⵥ��ϸ������Ϊ��" + detailList.size());					
					detailMgr.cancelWareHouseRecDetailList(detailList, true, content);
				}
			}else{//״̬����ȷ������ȡ��
				logger.debug("ȡ����ⵥ��Ϣ,���ڵ���ⵥ״̬Ϊ��" + existInfo.getState() +"�޷���ȡ������");
				ret =-1;
			}
			
		}else{//Ҫȡ������ⵥ������
			logger.debug("ȡ����ⵥ��Ϣ,���ݴ���Ĳ������޷��ҵ���Ӧ����ⵥ���޷�ȡ����");
			ret = -2;
		}
		
		return ret;
	}
	
	/**
	 * ȷ�Ϲ�Ӧ����ⵥ��ͬʱȷ����ⵥ��ϸ
	 * ����ȷ����˵����ⵥ��ϸ���ܶ������������������ⵥͬʱ����
	 * @param whrInfo
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int corfirmWareHouseRecInfo(WarehouseRecInfo whrInfo,String content) throws Exception{

		//����״̬Ϊȷ��
		whrInfo.setState(WareHouseConstants.WAREHOUSE_REC_INFO_02);
		
		return saveWareHouseRecInfo(whrInfo,content);
	}
	/**
	 * ��ⵥ����ͣ/�������������Ĳ�������Ҫ�޸�ԭʼ״̬
	 * ��ϵͳ���������
	 * @param whrInfo
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int controlActiveState(WarehouseRecInfo whrInfo,String content) throws Exception{
		int ret =0;
		logger.debug("��Ӧ����ⵥ��ͣ/�������������Ĳ���Ϊ��" + whrInfo.toString());
		String activeState = whrInfo.getActiveState();
		if(activeState.equals(WareHouseConstants.WAREHOUSE_REC_ACTIVE)){
			whrInfo.setActiveState(WareHouseConstants.WAREHOUSE_REC_PAUSE);
		}else{
			whrInfo.setActiveState(WareHouseConstants.WAREHOUSE_REC_ACTIVE);
		}
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		whrInfoDao.updateWarehouseRecInfoByActiveState(whrInfo);
		WareHouseLogUtils.operLog(whrInfo.getOperId(), whrInfo.getOperStaff(), (activeState.equals(WareHouseConstants.WAREHOUSE_REC_ACTIVE) ? "����" : "��ͣ"), "��Ӧ���������", whrInfo.getLogKey(), null, content);
		//��ⵥ��ϸ
		List<WarehouseRecDetail> detailList = whrInfo.getDetailList();
		if(null != detailList && detailList.size() >0){//��ⵥ��ϸ����
			logger.debug("��Ӧ����ⵥ��ͣ/�������! ��ⵥ��ϸ����Ϊ��" + detailList.size());
			WareHouseRecDetailMgr detailMgr = (WareHouseRecDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAILMGR);
			detailMgr.cancelWareHouseRecDetailList(detailList, true, content);
		}
		return ret;
	}
	/**
	 * ��ȡ������ⵥ��Ϣ  REC_PO_NO = #recPoNo# AND C_CODE=#vendorCode#
	 * @param whrInfo
	 * @param isDetail �Ƿ���Ҫ��ϸ
	 * @return
	 * @throws Exception
	 */
	public WarehouseRecInfo getWarehouseRecInfo(WarehouseRecInfo whrInfo , boolean isDetail) throws Exception{
		WarehouseRecInfo retInfo = null;
		logger.debug("��ѯ������ⵥ������Ĳ���Ϊ��" + whrInfo.toString());
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		retInfo = whrInfoDao.findWarehouseRecInfo(whrInfo);
		if(isDetail && retInfo != null){
			logger.debug("��Ҫ��ѯ��ⵥ��ϸ��");
			WareHouseRecDetailMgr detailMgr = (WareHouseRecDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAILMGR);
			WarehouseRecDetail detail = new WarehouseRecDetail();
			detail.setRecPoNo(retInfo.getRecPoNo());
			detail.setVendorCode(retInfo.getVendorCode());
			detail.setSettlementType(retInfo.getSettlementType());
			detail.setPoNoType(retInfo.getPoNoType());
			retInfo.setDetailList(detailMgr.getWarehouseRecDetailList(detail));
		}
		return retInfo;
	}
	
	/**
	 * ���������������ѯ��ⵥ�б�
	 * @param whrInfo
	 * @return
	 * @throws Exception
	 */
	public List<WarehouseRecInfo> listWarehouseRecInfo(WarehouseRecInfo whrInfo) throws Exception{
		logger.debug("��ѯ��ⵥ�б�����Ĳ���Ϊ��" + whrInfo.toString());
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		return whrInfoDao.listWarehouseRecInfo(whrInfo);
	}
	/**
	 * �����������������ѯ������������ⵥ��Ŀ
	 * @param whrInfo
	 * @return
	 * @throws Exception
	 */
	public Integer listWarehouseRecInfoCount(WarehouseRecInfo whrInfo) throws Exception{
		logger.debug("��ѯ��ⵥ�б�����������Ĳ���Ϊ��" + whrInfo.toString());
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		return whrInfoDao.listWarehouseRecInfoCount(whrInfo);
	}
}
