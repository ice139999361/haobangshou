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

import com.hbs.common.utils.ExpireTimeUtil;
import com.hbs.customer.common.utils.CustLogUtils;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.utils.CustOrderUtils;
import com.hbs.domain.adjust.dao.AdjustInfoDao;
import com.hbs.domain.adjust.pojo.AdjustInfo;

import com.hbs.domain.waittask.pojo.WaitTaskInfo;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.warehouse.manager.WarehouseMgr;


public class AdjustMgr {

	private static final Logger logger = Logger.getLogger(AdjustMgr.class);
	
	
	/**
	 * �ύ�������룬�������ύ���޸ĺ��ύ���������ڣ�
	 * �޸ĺ��ύ��sequence�����ύû��sequence
	 * @param adjustInfo
	 * @return > 0 �ύ�ɹ���-1 ��治��������ĵ������� -2 ����еĵ������ϲ��������
	 * @throws Exception
	 */
	public int saveAdjustInfo(AdjustInfo adjustInfo) throws Exception{
		int ret =0;
		logger.debug("�����������棡�������ϢΪ��" + adjustInfo.toString());
		//��ȡ����ĵ�������		
		int iMount = adjustInfo.getApplyAmount();
		//��Ͽ����Ϣ
		WarehouseMgr whMgr = (WarehouseMgr)BeanLocator.getInstance().getBean(CustOrderConstants.WAREHOUSE_INFO_MGR);
		WareHouseInfo wObject = createFromWInfoObject(adjustInfo , true);
		logger.debug("��ѯ�����Ϣ������Ĳ�ѯ����Ϊ��" + wObject.toString());
		//�Ӳֿ��л�ȡ�������ͻ��Ŀ���������ж��Ƿ��������
		WareHouseInfo wInfo = getWareHouseInfo(whMgr , wObject);
		if(wInfo != null){
			int iUseMount = wInfo.getUseAmount();
			if(iMount > iUseMount){
				logger.debug("����еı����������������С����Ҫ�ĵ����������޷�ִ�е�����");
				ret = -2;
			}else{//�����������������룬�ٱ��������Ϣ�����쵼����
				wObject.setHouseSeqId(wInfo.getHouseSeqId());
				wObject.setLockAmount(iMount);
				wObject.setUseAmount(-iMount);
				lockWareHouseInfo(whMgr,wObject);
				
				//���������Ϣ���ȴ�����
				AdjustInfoDao justDao =(AdjustInfoDao)BeanLocator.getInstance().getBean(CustOrderConstants.ADJUST_INFO_DAO);
				if(adjustInfo.getApplySeqId() != null){//�޸ĵ���
					adjustInfo.setAuditAgree(null);
					adjustInfo.setAuditContent(null);
					adjustInfo.setAuditStaffId(null);
					adjustInfo.setAuditStaffName(null);
					justDao.updateAdjustInfo(adjustInfo);
					ret = adjustInfo.getApplySeqId().intValue();
				}else{//��������
					ret = justDao.insertAdjustInfo(adjustInfo);
				}
				adjustInfo.setApplySeqId(ret);
				//�������
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();				
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", adjustInfo.getStaffName());
				hmParam.put("$partNo", adjustInfo.getPartNo());
				waitTaskInfo.setHmParam(hmParam);	
				waitTaskInfo.setBusinessKey(adjustInfo.getBizKey());
				CustOrderUtils.processCreateWaitTask("ADJUSTMENT_001",null, waitTaskInfo);
				//������־
				CustLogUtils.operLog(adjustInfo.getStaffId(), adjustInfo.getStaffName(), "�ύ", "��������", adjustInfo.getLogBizKey(), null, adjustInfo.getApplyContent());
			}
		}else{
			logger.debug("����в������ܹ��������Ŀ����Ϣ���޷�ִ�е���!");
			ret = -1;
		}
		return ret;
	}
	/**
	 * ����ͬ�⣬�ȴӱ��������⣬�ٵ�����⣬��󱣴�������Ϣ��
	 * @param adjustInfo
	 * @return 0 �ɹ�
	 * @throws Exception
	 */
	public int agreeAdjustInfo(AdjustInfo adjustInfo) throws Exception{
		int ret =0;
		logger.debug("����ͬ�����������Ĳ���Ϊ��" + adjustInfo.toString());
		//��Ͽ����Ϣ
		WarehouseMgr whMgr = (WarehouseMgr)BeanLocator.getInstance().getBean(CustOrderConstants.WAREHOUSE_INFO_MGR);
		
		//�ӱ�����������
		WareHouseInfo wFromObject = createFromWInfoObject(adjustInfo , true);
		wFromObject.setLockAmount(adjustInfo.getApplyAmount().intValue());
		wFromObject.setTotalAmount(adjustInfo.getApplyAmount().intValue());
		whMgr.saveOutWareHouseInfo(wFromObject);		
		//�������
		WareHouseInfo wToObject = createFromWInfoObject(adjustInfo , false);
		wToObject.setTotalAmount(adjustInfo.getApplyAmount().intValue());
		wToObject.setUseAmount(adjustInfo.getApplyAmount().intValue());
		whMgr.saveInWareHouseInfo(wToObject);
		//���������Ϣ
		AdjustInfoDao justDao =(AdjustInfoDao)BeanLocator.getInstance().getBean(CustOrderConstants.ADJUST_INFO_DAO);
		justDao.updateAdjustInfo(adjustInfo);
		//����
		WaitTaskInfo waitTaskInfo = new WaitTaskInfo();				
		Map<String , String> hmParam = new HashMap<String,String>();
		waitTaskInfo.setStaffId(adjustInfo.getStaffId());
		hmParam.put("$staffName", adjustInfo.getAuditStaffName());
		hmParam.put("$partNo", adjustInfo.getPartNo());
		waitTaskInfo.setHmParam(hmParam);
		waitTaskInfo.setBusinessKey(adjustInfo.getBizKey());
		waitTaskInfo.setExpireTime(ExpireTimeUtil.getExpireTime("ADJUST_REMINDER_DAY"));
		CustOrderUtils.processCreateWaitTask("ADJUSTMENT_002",null, waitTaskInfo);
		//��־
		CustLogUtils.operLog(adjustInfo.getAuditStaffId(), adjustInfo.getAuditStaffName(), "����ͬ��", "��������", adjustInfo.getLogBizKey(), null, adjustInfo.getAuditContent());
		return ret;
	}
	/**
	 * ������ͬ��������������Ŀ�����
	 * @param adjustInfo
	 * @return >0 �ɹ�
	 * @throws Exception
	 */
	public int disAgreeAdjustInfo(AdjustInfo adjustInfo) throws Exception{
		int ret =0;
		logger.debug("������ͬ�����������Ĳ���Ϊ��" + adjustInfo.toString());
		//��Ͽ����Ϣ
		WarehouseMgr whMgr = (WarehouseMgr)BeanLocator.getInstance().getBean(CustOrderConstants.WAREHOUSE_INFO_MGR);
		
		//�ӱ�����������
		WareHouseInfo wFromObject = createFromWInfoObject(adjustInfo , true);
		wFromObject.setLockAmount(- adjustInfo.getApplyAmount().intValue());
		wFromObject.setUseAmount(adjustInfo.getApplyAmount().intValue());
		whMgr.saveLockWareHouseInfo(wFromObject, null, null, null);
		
		//���������Ϣ
		AdjustInfoDao justDao =(AdjustInfoDao)BeanLocator.getInstance().getBean(CustOrderConstants.ADJUST_INFO_DAO);
		justDao.updateAdjustInfo(adjustInfo);
		ret = adjustInfo.getApplySeqId();
		//����
		WaitTaskInfo waitTaskInfo = new WaitTaskInfo();				
		Map<String , String> hmParam = new HashMap<String,String>();
		waitTaskInfo.setStaffId(adjustInfo.getStaffId());
		hmParam.put("$staffName", adjustInfo.getAuditStaffName());
		hmParam.put("$partNo", adjustInfo.getPartNo());
		waitTaskInfo.setHmParam(hmParam);	
		waitTaskInfo.setBusinessKey(adjustInfo.getBizKey());
		waitTaskInfo.setExpireTime(ExpireTimeUtil.getExpireTime("ADJUST_REMINDER_DAY"));
		CustOrderUtils.processCreateWaitTask("ADJUSTMENT_003",null, waitTaskInfo);
		//��־
		CustLogUtils.operLog(adjustInfo.getAuditStaffId(), adjustInfo.getAuditStaffName(), "������ͬ��", "��������", adjustInfo.getLogBizKey(), null, adjustInfo.getAuditContent());
		return ret;
	}
	/**
	 * ����sequence����,��ѯ������Ϣ
	 * @param id  ����
	 * @return
	 * @throws Exception
	 */
	public AdjustInfo getAdjustInfo(String id) throws Exception{
		AdjustInfoDao justDao =(AdjustInfoDao)BeanLocator.getInstance().getBean(CustOrderConstants.ADJUST_INFO_DAO);
		return justDao.findAdjustInfo(id);
	}
	/**
	 * ��ѯ���������ĵ��� ,֧�ַ�ҳ
	 * @param adjustInfo
	 * @return
	 * @throws Exception
	 */
	public List<AdjustInfo> getAdjustInfoList(AdjustInfo adjustInfo) throws Exception{
		AdjustInfoDao justDao =(AdjustInfoDao)BeanLocator.getInstance().getBean(CustOrderConstants.ADJUST_INFO_DAO);
		return justDao.listAdjustInfo(adjustInfo);
	}
	
	/**
	 * ��ѯ���������ĵ�������
	 * @param adjustInfo
	 * @return
	 * @throws Exception
	 */
	public Integer getAdjustInfoListCount(AdjustInfo adjustInfo) throws Exception{
		AdjustInfoDao justDao =(AdjustInfoDao)BeanLocator.getInstance().getBean(CustOrderConstants.ADJUST_INFO_DAO);
		return justDao.listAdjustInfoCount(adjustInfo);
	}
	/**
	 * ���ݵ�����Ϣ����ѯ������
	 * @param wObject
	 * @return
	 * @throws Exception
	 */
	private WareHouseInfo getWareHouseInfo(WarehouseMgr whMgr,WareHouseInfo wObject) throws Exception{
		WareHouseInfo ret = null;		
		ret = whMgr.getWareHouseInfoByBizKey(wObject);
		logger.debug("��ѯ���Ľ��Ϊ��" + ret == null ? null : ret.toString());
		return ret;
		
	}
	
	private void lockWareHouseInfo(WarehouseMgr whMgr,WareHouseInfo wObject)throws Exception{
		whMgr.saveLockWareHouseInfo(wObject, null, null, null);
	}
	
	/**
	 * ���ݵ�����Ϣ�����������Ϣ
	 * @param adjustInfo
	 * @param isFrom true ����������  false  ������
	 * @return
	 */
	private WareHouseInfo createFromWInfoObject(AdjustInfo adjustInfo , boolean isFrom){
		WareHouseInfo wInfo = new WareHouseInfo();
		//�ֿ�λ��
		wInfo.setHouseType(adjustInfo.getHouseType());
		//�ֿ�����
		wInfo.setHouseUse(CustOrderConstants.WAREHOUSE_INFO_HOUSE_USE_1);
		//���ù�Ӧ�̴���
		wInfo.setVendorCode(adjustInfo.getVendorCode());
		//�������ϱ��
		wInfo.setPartNo(adjustInfo.getPartNo());
		//���ÿͻ�
		if(isFrom){
			wInfo.setCustCode(adjustInfo.getFromCustCode());
		}else{
			wInfo.setCustCode(adjustInfo.getToCustCode());
		}
		return wInfo;
	}
	
	
}
