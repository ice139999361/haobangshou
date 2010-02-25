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

import com.hbs.common.manager.syssequence.SysSequenceMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.warehouse.dao.WarehouseSendInfoDao;

import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;
import com.hbs.domain.warehouse.pojo.WarehouseSendInfo;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;


import com.hbs.warehousesend.manager.helper.WarehouseSendHelper;

/**
 * @author Administrator
 *
 */
public class WareHouseSendMgr {
	private static final Logger logger = Logger.getLogger(WareHouseSendMgr.class);
	/**
	 * ����ͻ�������Ϣ����Ϊ2�������
	 * ״̬Ϊ01 ˵���Ǳ�����ʱ������Ϣ
	 *          �жϿ����Ƿ���ڣ����ڣ���update����
	 *                          �����ڣ���insert����
	 *  ״̬Ϊ02 ��˵���û���û�б�����ʱ������Ϣ��ֱ���ύ������Ϣ
	 *  ��������ͻ�������ϸ������Ҫ����ͻ���ϸ��Ϣ
	 *  ������ڳ��ⵥ��ϸ���ر�ע�� ���ͻ��ض�������������ͨ�ÿ���������
	 * @param sendInfo  ������Ϣ
	 * @param content   ˵����Ϣ������־��
	 * @return
	 * @throws Exception
	 */
	public int saveWareHouseSendInfo(WarehouseSendInfo sendInfo,String content) throws Exception{
		int ret =0;
		String st = sendInfo.getState();
		String sendPoNo = sendInfo.getSendPoNo();
		if(st.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_01)){
			logger.debug("������ⵥ��Ϣ������Ĳ���Ϊ��" + sendInfo.toString());
		}else{
			logger.debug("ȷ�ϳ��ⵥ��Ϣ������Ĳ���Ϊ��" + sendInfo.toString());
		}
		WarehouseSendInfoDao whInfoDao = (WarehouseSendInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_INFO_DAO);
		//�������ڣ������ϳ���ʱ��Ϊ��׼����
		WarehouseSendHelper helper =(WarehouseSendHelper)BeanLocator.getInstance().getBean(WareHouseConstants.PRE_SPRING_SEND + sendInfo.getPoNoType() + sendInfo.getSettlementType());
		sendInfo.setPeriod(helper.getPeriod(sendInfo));
		if(StringUtils.isEmpty(sendPoNo) ){//�����ڳ������ĵ���,��������������
			//����ϵͳ�����ĳ�������
			sendInfo.setSendPoNo(SysSequenceMgr.getPoNo(WareHouseConstants.CUST_SEND_PONO));
			whInfoDao.insertWarehouseSendInfo(sendInfo);
			WareHouseLogUtils.operLog(sendInfo.getOperId(), sendInfo.getOperStaff(), (sendInfo.getState().equals(WareHouseConstants.WAREHOUSE_SEND_INFO_02) ? "ȷ��" : "����"), "�ͻ����ϳ���", sendInfo.getLogKey(), null, content);
		}else{//���ڳ������ĵ���
			//��ѯ��������Ϣ
			WarehouseSendInfo existInfo = whInfoDao.findWarehouseSendInfo(sendInfo);
			if(null == existInfo){//�޴˳�������Ϣ
				logger.debug("���ݴ���ĳ��ⵥ��Ϣ���ڿ����޷���ѯ��������ִ�в���!");
				ret = -2;
			}else{
				String state = existInfo.getState();
				if(state.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_01)){//״̬Ϊ��ʱ״̬�������޸�
					whInfoDao.updateWarehouseSendInfo(sendInfo);
					WareHouseLogUtils.operLog(sendInfo.getOperId(), sendInfo.getOperStaff(), (sendInfo.getState().equals(WareHouseConstants.WAREHOUSE_SEND_INFO_02) ? "ȷ��" : "�޸�"), "��Ӧ���������", sendInfo.getLogKey(), null, content);
				}else{//״̬Ϊ����ʱ״̬�������޸�
					logger.debug("���ݴ���ĳ��ⵥ��Ϣ����ѯ��״̬Ϊ��" + state +"�������޸Ĳ�����");
					ret =-1;
				}
			}
			
		}
		//�����������ϸ
		if(ret ==0){
			List<WarehouseSendDetail> detailList = sendInfo.getDetailList();
			if(null != detailList && detailList.size() >0){
				if(st.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){
					logger.debug("������ⵥ��Ϣ�����ⵥ�´��ڳ��ⵥ��ϸ��������ⵥ��ϸ������Ϊ��" + detailList.size());
				}else{
					logger.debug("ȷ�ϳ��ⵥ��Ϣ�����ⵥ�´��ڳ��ⵥ��ϸ��ȷ�ϳ��ⵥ��ϸ������Ϊ��" + detailList.size());
				}
				for(WarehouseSendDetail detail : detailList){
					detail.setState(sendInfo.getState());
					if(StringUtils.isEmpty(detail.getSendPoNo())){
						detail.setSendPoNo(sendInfo.getSendPoNo());
					}
				}
				WareHouseSendDetailMgr detailMgr = (WareHouseSendDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAILMGR);
				detailMgr.saveWareHouseSendDetailList(detailList, true, content);
			}
		}
		return ret;
	}
	/**
	 * ȡ�����ⵥ��ͬʱȡ���ó��ⵥ�µ�������ϸ
	 * �����жϸó��ⵥ�Ƿ���ڣ������ڷ���-2 ��ʾҪȡ������ⵥ������
	 * ���ڸó��ⵥ���жϳ��ⵥ��״̬��״̬Ϊ��ʱ������ȡ��
	 * ��������ȡ�����ⵥ
	 * @param sendInfo
	 * @param content ȡ�����
	 * @return
	 * @throws Exception
	 */
	public int cancelWarehouseSendInfo(WarehouseSendInfo sendInfo,String content) throws Exception{
		int ret =0;
		logger.debug("ȡ�����ⵥ����������Ĳ���Ϊ��" + sendInfo.toString());
		//���ݴ���Ĳ�������ѯ���ⵥ
		WarehouseSendInfoDao whInfoDao = (WarehouseSendInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_INFO_DAO);
		WarehouseSendInfo existInfo = whInfoDao.findWarehouseSendInfo(sendInfo);
		if(null != existInfo){//���ڳ��ⵥ
			String state = existInfo.getState();
			if(state.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_01)){//��ʱ״̬����ȡ��
				sendInfo.setState(WareHouseConstants.WAREHOUSE_SEND_INFO_03);
				whInfoDao.updateWarehouseSendInfoByState(sendInfo);
				WareHouseLogUtils.operLog(sendInfo.getOperId(), sendInfo.getOperStaff(),"ȡ��", "�ͻ����ϳ���", sendInfo.getLogKey(), null, content);
				//����ó��ⵥ�µ���ϸ
				List<WarehouseSendDetail> detailList = sendInfo.getDetailList();
				if(null != detailList && detailList.size() >0){
					logger.debug("ȡ�����ⵥ��Ϣ�����ⵥ�´��ڳ��ⵥ��ϸ��ȡ�����ⵥ��ϸ������Ϊ��" + detailList.size());
					WareHouseSendDetailMgr detailMgr = (WareHouseSendDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAILMGR);
					detailMgr.cancelWareHouseSendDetailList(detailList, true, content);
				}
			}else{//����ʱ״̬������ȡ��
				logger.debug("ȡ�����ⵥ��Ϣ,���ڵĳ��ⵥ״̬Ϊ��" + existInfo.getState() +"�޷���ȡ������");
				ret =-1;
			}
		}else{//���ⵥ�����ڣ��޷�ִ��ȡ������
			logger.debug("ȡ�����ⵥ��Ϣ,���ݴ���Ĳ������޷��ҵ���Ӧ�ĳ��ⵥ���޷�ȡ����");
			ret = -2;
		}
		return ret;
	}
	/**
	 * ȷ�ϳ��ⵥ��ͬʱȷ�ϸó��ⵥ�µ�������ϸ
	 * ��Ӧȷ�ϲ�����˵�����ⵥ��ϸ���ܵ�����������������ⵥ����
	 * @param sendInfo
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int corfirmWarehouseSendInfo(WarehouseSendInfo sendInfo,String content) throws Exception{
		int ret =0;
		//���ó��ⵥ��״̬Ϊȷ�ϣ����ñ��淽��ִ��
		logger.debug("ȷ�ϳ��ⵥ�������ó��ⵥ��״̬Ϊȷ�ϣ����ñ��淽��ִ�У�����Ϊ��" + sendInfo.toString());
		sendInfo.setState(WareHouseConstants.WAREHOUSE_SEND_INFO_02);
		ret = saveWareHouseSendInfo(sendInfo,content);
		return ret;
	}
	/**
	 * ���ⵥ����ͣ/�������������Ĳ�������Ҫ�޸�ԭʼ״̬
	   ��ͣ/���� ֻ��Գ��ⵥ ��ͬʱ�������иó��ⵥ���µ�������ϸ
	 * ��ϵͳ��������� 
	 * @param sendInfo
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int controlActiveState(WarehouseSendInfo sendInfo,String content) throws Exception{
		int ret =0;
		logger.debug("��Ӧ�̳��ⵥ��ͣ/�������������Ĳ���Ϊ��" + sendInfo.toString());
		String activeState = sendInfo.getActiveState();
		if(activeState.equals(WareHouseConstants.WAREHOUSE_SEND_ACTIVE)){
			sendInfo.setActiveState(WareHouseConstants.WAREHOUSE_SEND_PAUSE);
		}else{
			sendInfo.setActiveState(WareHouseConstants.WAREHOUSE_SEND_ACTIVE);
		}
		WarehouseSendInfoDao whInfoDao = (WarehouseSendInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_INFO_DAO);
		whInfoDao.updateWarehouseSendInfoByActiveState(sendInfo);
		//��¼������־
		WareHouseLogUtils.operLog(sendInfo.getOperId(), sendInfo.getOperStaff(), (activeState.equals(WareHouseConstants.WAREHOUSE_SEND_ACTIVE) ? "����" : "��ͣ"), "�ͻ����ϳ���", sendInfo.getLogKey(), null, content);
		//���ⵥ��ϸ����
		List<WarehouseSendDetail> detailList = sendInfo.getDetailList();
		if(null != detailList && detailList.size()>0){
			logger.debug("�ó��ⵥ�°�������ϸ��ĿΪ��" + detailList.size());
			WareHouseSendDetailMgr detailMgr = (WareHouseSendDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAILMGR);
			detailMgr.cancelWareHouseSendDetailList(detailList, true, content);
		}
		return ret;
	}
	/**
	 * ��ѯ�������ⵥ��Ϣ������Ϊ��SEND_PO_NO = #sendPoNo# AND C_CODE=#custCode#
	 * @param sendInfo
	 * @param isDetail  �Ƿ�ͬʱ��ѯ�����ⵥ��ϸ true ��  false ����
	 * @return
	 * @throws Exception
	 */
	public WarehouseSendInfo getWarehouseSendInfo(WarehouseSendInfo sendInfo ,boolean isDetail) throws Exception{
		WarehouseSendInfo retInfo = null;
		logger.debug("��ѯ�������ⵥ������Ĳ���Ϊ��" + sendInfo.toString());
		WarehouseSendInfoDao whInfoDao = (WarehouseSendInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_INFO_DAO);
		retInfo = whInfoDao.findWarehouseSendInfo(sendInfo);
		//������ⵥ��ϸ
		if(null != retInfo && isDetail){
			logger.debug("��Ҫ��ѯ���ⵥ��ϸ��");
			WareHouseSendDetailMgr detailMgr = (WareHouseSendDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAILMGR);
			WarehouseSendDetail detail = new WarehouseSendDetail();
			detail.setSendPoNo(sendInfo.getSendPoNo());
			detail.setCustCode(sendInfo.getCustCode());
			detail.setSettlementType(sendInfo.getSettlementType());
			detail.setPoNoType(sendInfo.getPoNoType());
			retInfo.setDetailList(detailMgr.listWarehouseSendDetail(detail));
		}
		return retInfo;
	}
	/**
	 * ���������������ѯ����ⵥ�б�
	 * @param sendInfo
	 * @return
	 * @throws Exception
	 */
	public List<WarehouseSendInfo> listWarehouseSendInfo(WarehouseSendInfo sendInfo ) throws Exception{
		logger.debug("��ѯ���ⵥ�б�����Ĳ���Ϊ��" + sendInfo.toString());
		WarehouseSendInfoDao whInfoDao = (WarehouseSendInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_INFO_DAO);
		return whInfoDao.listWarehouseSendInfo(sendInfo);
	}
	/**
	 * ���������������ѯ����ⵥ�б���Ŀ
	 * @param sendInfo
	 * @param isDetail
	 * @return
	 * @throws Exception
	 */
	public Integer listWarehouseSendInfoCount(WarehouseSendInfo sendInfo ,boolean isDetail) throws Exception{
		logger.debug("��ѯ���ⵥ�б�����������Ĳ���Ϊ��" + sendInfo.toString());
		WarehouseSendInfoDao whInfoDao = (WarehouseSendInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_INFO_DAO);
		return whInfoDao.listWarehouseSendInfoCount(sendInfo);
	}
}
