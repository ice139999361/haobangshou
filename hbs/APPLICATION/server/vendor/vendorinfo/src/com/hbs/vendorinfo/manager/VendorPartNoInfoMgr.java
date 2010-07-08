/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendorinfo.manager;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;



import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.ExpireTimeUtil;
import com.hbs.domain.common.pojo.baseinfo.OperLog;


import com.hbs.domain.vendor.vendorinfo.dao.VendorPartNoInfoDao;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorPartNoInfo;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;
import com.hbs.vendor.common.constants.StateConstants;
import com.hbs.vendor.common.utils.VendorLogUtils;
import com.hbs.vendor.common.utils.VendorWaitTaskUtils;


public class VendorPartNoInfoMgr {

	private static final String VENDOR_PARTNOINFODAO ="vendorPartNoInfoDao";
	private static final Logger logger = Logger.getLogger(VendorPartNoInfoMgr.class);
	/**
	 * ���湩Ӧ����ʱ���Ϲ�ϵ��Ϣ��״̬Ϊ1
	 * @param vPartNoInfo
	 * @return 0--�ɹ�  1--�����ظ�����
	 * @throws Exception
	 */
	public int saveTempVendorPartNoInfo(VendorPartNoInfo vPartNoInfo)throws Exception{
		vPartNoInfo.setState(new Integer(StateConstants.STATE_1).toString());
		return insertVendorPartNoInfo(vPartNoInfo);
	}
	/**
	 * �������湩Ӧ����ʱ���Ϲ�ϵ��Ϣ��״̬Ϊ1
	 * @param vPartNoInfoList
	 * @return
	 * @throws Exception
	 */
	public int saveTempVendorPartNoInfoList(List<VendorPartNoInfo> vPartNoInfoList)throws Exception{
		int ret =0;
		for(VendorPartNoInfo vPartNoInfo : vPartNoInfoList){
			vPartNoInfo.setState(new Integer(StateConstants.STATE_1).toString());
			insertVendorPartNoInfo( vPartNoInfo);
		}
		return ret;		
	}
	/**
	  * �ύ��������,���ݵ�״̬Ϊ��ʱ״̬,����Ϊ�쵼�����ܾ���״̬,�ſ����ύ����
	 * ����״̬�޸ĵ�ͬʱ����Ҫ������֪ͨ
	 * @param vPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public int commitVendorPartNoInfo(VendorPartNoInfo vPartNoInfo) throws Exception{
		int ret =0;
		logger.debug("�ύ��Ӧ�����Ϲ�ϵ��Ϣ�б�����Ϊ��" + vPartNoInfo.toString());
		String state = vPartNoInfo.getState();
		if(StringUtils.isEmpty(state)){//������״̬������
			VendorPartNoInfoDao vPartNoInfoDao = (VendorPartNoInfoDao)BeanLocator.getInstance().getBean(VENDOR_PARTNOINFODAO);
			Integer i = vPartNoInfoDao.listVendorPartNoInfoCheckCount(vPartNoInfo);
			if(i >0){//�Ѿ�������ͬ�Ŀͻ����ϣ��������ύ
				throw new Exception("�Ѿ����ڹ�Ӧ��("+vPartNoInfo.getCommCode() + ")������(" +  vPartNoInfo.getCustPartNo()+")��Ϣ��" );
			}
		}
		VendorPartNoInfo existInfo = this.getVendorPartNoInfoByBizKey(vPartNoInfo);
		if(existInfo != null){
			//��ȡ�ύ���ݴ�״̬
			int iState = Integer.parseInt(existInfo.getState());
			if(iState == StateConstants.STATE_1 || iState == StateConstants.STATE_3){
				vPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
				ret = this.innerUpdateVendorPartNoInfo(vPartNoInfo, vPartNoInfo.getStaffId(), vPartNoInfo.getStaffName(), null);
			}else if(iState == StateConstants.STATE_0){
				vPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
				this.insertVendorPartNoInfo(vPartNoInfo);
			}
		}else{
			vPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
			this.insertVendorPartNoInfo(vPartNoInfo);
		}
		//���촦��
		
		if(ret == 0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
			WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
			Map<String , String> hmParam = new HashMap<String,String>();
			hmParam.put("$staffName", vPartNoInfo.getStaffName());
			hmParam.put("$commCode", vPartNoInfo.getCommCode());
			hmParam.put("$cpartNo", vPartNoInfo.getCustPartNo());
			waitTaskInfo.setHmParam(hmParam);
			waitTaskInfo.setBusinessKey(vPartNoInfo.getWaitTaskBizKey());
			VendorWaitTaskUtils.processDeleteWaitTask(vPartNoInfo.getWaitTaskBizKey());
			VendorWaitTaskUtils.processCreateWaitTask("VENDOR_PARTNO_001", null, waitTaskInfo);
			
		}
		return ret;
	}
	
	
	/**
	 * ����ͬ�⹩Ӧ����������
	 * @param vPartNoInfo
	 * @param auditId  ������ID
	 * @param auditName ����������
	 * @param auditDesc �������
	 * @return  0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
	 * @throws Exception
	 */
	public int auditAgreeCustPartNoInfo(VendorPartNoInfo vPartNoInfo, String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(vPartNoInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			vPartNoInfo.setState(new Integer(StateConstants.STATE_0).toString());
			ret = innerUpdateVendorPartNoInfo(vPartNoInfo,auditId,auditName,auditDesc);
			if(ret ==0){//�����Ѵ���֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", auditName);
				hmParam.put("$commCode", vPartNoInfo.getCommCode());
				hmParam.put("$cpartNo", vPartNoInfo.getCustPartNo());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setStaffId(vPartNoInfo.getStaffId());
				waitTaskInfo.setBusinessKey(vPartNoInfo.getWaitTaskBizKey());
				waitTaskInfo.setExpireTime(ExpireTimeUtil.getExpireTime("VENDOR_PARTNO_REMINDER_DAY"));
				VendorWaitTaskUtils.processDeleteWaitTask(vPartNoInfo.getWaitTaskBizKey());
				VendorWaitTaskUtils.processCreateWaitTask("VENDOR_PARTNO_002", null, waitTaskInfo);
				
				
			}
		}else{
			ret =2;//��ʾ�����ύ��״̬����ȷ
		}
		
		return 0;
	}
	
	/**
	 * ������ͬ�⹩Ӧ����������
	 * @param vPartNoInfo
	 * @param auditId  ������ID
	 * @param auditName  ����������
	 * @param auditDesc  �������
	 * @return 0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
	 * @throws Exception
	 */
	public int auditDisAgreeCustPartNoInfo(VendorPartNoInfo vPartNoInfo,String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(vPartNoInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			vPartNoInfo.setState(new Integer(StateConstants.STATE_3).toString());
			ret = innerUpdateVendorPartNoInfo(vPartNoInfo,auditId,auditName,auditDesc);
			if(ret ==0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", auditName);
				hmParam.put("$commCode", vPartNoInfo.getCommCode());
				hmParam.put("$cpartNo", vPartNoInfo.getCustPartNo());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setStaffId(vPartNoInfo.getStaffId());
				waitTaskInfo.setBusinessKey(vPartNoInfo.getWaitTaskBizKey());
				VendorWaitTaskUtils.processDeleteWaitTask(vPartNoInfo.getWaitTaskBizKey());
				VendorWaitTaskUtils.processCreateWaitTask("VENDOR_PARTNO_003", null, waitTaskInfo);
				
			}
		}else{
			ret =2;//��ʾ�����ύ��״̬����ȷ
		}
		
		return 0;
	}
	
	/**
	 * �޸Ĺ�Ӧ��������Ϣ���޸�ǰ��״̬���ܲ�ͬ����Ҫ����Դ�
	 * �޸�ǰ״̬Ϊ1 ������ʱ�������޸�
	 * �޸�ǰ״̬Ϊ0 ������ʽ�������޸ģ�ֱ���ύ�쵼����
	 * �޸�ǰ״̬Ϊ3 ����������ͨ���������޸ģ�ֱ���ύ�쵼����
		//����״̬�������޸Ĳ��� 
	 * @param vPartNoInfo
	 * @return 0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
	 * @throws Exception
	 */
	public int updateVendorPartNoInfo(VendorPartNoInfo vPartNoInfo) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(vPartNoInfo.getState());
		//״̬Ϊ1 ������ʱ�������޸�
		//״̬Ϊ0 ������ʽ�������޸ģ�ֱ���ύ�쵼����
		//״̬Ϊ3 ����������ͨ���������޸ģ�ֱ���ύ�쵼����
		//����״̬�������޸Ĳ���
		switch(iState) {
		case 1:
			ret = innerUpdateVendorPartNoInfo(vPartNoInfo,vPartNoInfo.getStaffId(),vPartNoInfo.getStaffName(),null);
			break;
		case 3:
			vPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = innerUpdateVendorPartNoInfo(vPartNoInfo,vPartNoInfo.getStaffId(),vPartNoInfo.getStaffName(),null);
			if(ret == 0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", vPartNoInfo.getStaffName());
				hmParam.put("$commCode", vPartNoInfo.getCommCode());
				hmParam.put("$cpartNo", vPartNoInfo.getCustPartNo());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setBusinessKey(vPartNoInfo.getWaitTaskBizKey());
				VendorWaitTaskUtils.processDeleteWaitTask(vPartNoInfo.getWaitTaskBizKey());
				VendorWaitTaskUtils.processCreateWaitTask("VENDOR_PARTNO_001", null, waitTaskInfo);
			}
			break;
		case 0:
			vPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = insertVendorPartNoInfo(vPartNoInfo);
			if(ret == 0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", vPartNoInfo.getStaffName());
				hmParam.put("$commCode", vPartNoInfo.getCommCode());
				hmParam.put("$cpartNo", vPartNoInfo.getCustPartNo());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setBusinessKey(vPartNoInfo.getWaitTaskBizKey());
				VendorWaitTaskUtils.processDeleteWaitTask(vPartNoInfo.getWaitTaskBizKey());
				VendorWaitTaskUtils.processCreateWaitTask("VENDOR_PARTNO_001", null, waitTaskInfo);
			}
			break;
			
		default:
			ret =2;
		}
		
		return ret;
	}
	/**
	 * * �ϳ���Ӧ���������ݣ�ֻ����������ͨ����״̬�������зϳ�����
	 * @param vPartNoInfo
	 * @param delDesc   �ϳ�ԭ��
	 * @return
	 * @throws Exception
	 */
	public int deleteVendorPartNoInfo(VendorPartNoInfo vPartNoInfo,String delDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(vPartNoInfo.getState());
		switch(iState){
		case 3:
		case 0:
		case 2:
		case 1:
			vPartNoInfo.setState(new Integer(StateConstants.STATE_4).toString());
			ret = innerUpdateVendorPartNoInfo(vPartNoInfo,vPartNoInfo.getStaffId(),vPartNoInfo.getStaffName(),delDesc);
			logger.debug("����ù�Ӧ�����ϵĴ���");
			VendorWaitTaskUtils.processDeleteWaitTask(vPartNoInfo.getWaitTaskBizKey());
			break;
		default:
			ret =2;
		}
		return ret;
	}
	
	/**
	 * ��ȡ���ϵ���ʷ�۸�䶯����ͨ��������ȡ������ʷ��¼
	 * @param vPartNoInfo �� partNo custPartNo commCode �������ֶ�����־�ؼ��֣��ش��� 
	 * @return
	 * @throws Exception
	 */
	public List<OperLog> getPartNoChange(VendorPartNoInfo vPartNoInfo) throws Exception{
		OperLog logKey = new OperLog();
		logKey.setOperKey(vPartNoInfo.getLogBizKey()+"��������");
		
		return VendorLogUtils.getLogList(logKey);
	}
	/**
	 * �������Ϲ�ϵ���ж��Ƿ������ͬҵ��ؼ��ֵ����ݴ��ڣ�������ڣ���update����
	 * @param vPartNoInfo	
	 * @return 0--�ɹ�  
	 * @throws Exception
	 */
	private int insertVendorPartNoInfo(VendorPartNoInfo vPartNoInfo) throws Exception{
		int ret =0;
		VendorPartNoInfoDao vPartNoInfoDao = (VendorPartNoInfoDao)BeanLocator.getInstance().getBean(VENDOR_PARTNOINFODAO);
		//��ѯ���Ϲ�ϵ���жϲ���������Ƿ��Ѿ�����
		VendorPartNoInfo tempInfo = new VendorPartNoInfo();
		tempInfo.setCommCode(vPartNoInfo.getCommCode());
		tempInfo.setCustPartNo(vPartNoInfo.getCustPartNo());
		tempInfo.setPartNo(vPartNoInfo.getPartNo());
		tempInfo.setState(vPartNoInfo.getState());
		
		tempInfo = vPartNoInfoDao.findVendorPartNoInfoByBizKey(tempInfo);
		//Integer seqID =0;
		if(null == tempInfo){//������
			vPartNoInfoDao.insertVendorPartNoInfo(vPartNoInfo);
			//��¼��־			
			VendorLogUtils.operLog(vPartNoInfo.getStaffId(), vPartNoInfo.getStaffName(), "����", "���϶��չ�ϵ��Ϣ", vPartNoInfo.getLogBizKey(), vPartNoInfo.getLogContent(), null);
		}else{
			ret = -1;
		}
		
		
		return ret;
	}
	
	private String getIsPriceChange(VendorPartNoInfoDao vPartNoInfoDao,VendorPartNoInfo vPartNoInfo)throws Exception{
		String ret = "0";
		if(vPartNoInfo.getIsPriceChange().equals("1")){
			ret ="1";
		}else{
			VendorPartNoInfo existInfo = vPartNoInfoDao.findVendorPartNoInfoByBizKey(vPartNoInfo);
			if(existInfo != null){
				BigDecimal existPrice = existInfo.getPrice();
				BigDecimal vPrice = vPartNoInfo.getPrice();
				if(existPrice.compareTo(vPrice) != 0){
					ret ="1";
				}
			}
		}
		return ret;
	}
	
	/**
	 * ������Ϣ
	 * @param vPartNoInfo
	 * @param staffId
	 * @param staffName
	 * @param otherInfo
	 * @return
	 * @throws Exception
	 */
	private int innerUpdateVendorPartNoInfo(VendorPartNoInfo vPartNoInfo,String staffId,String staffName,String otherInfo)throws Exception{
		int ret =0;
		int state = Integer.parseInt(vPartNoInfo.getState());
		VendorPartNoInfoDao vPartNoInfoDao = (VendorPartNoInfoDao)BeanLocator.getInstance().getBean(VENDOR_PARTNOINFODAO);
		String strLogType = null;
		switch (state){
		case 0:  //����ͨ��,��ɾ�������,ͬʱɾ������������,����δ��
			String seqId = vPartNoInfo.getSeqId().toString();
			vPartNoInfo.setIsPriceChange(getIsPriceChange(vPartNoInfoDao,vPartNoInfo));
			vPartNoInfoDao.deleteVendorPartNoInfoByBizKey(vPartNoInfo);
			vPartNoInfoDao.insertVendorPartNoInfo(vPartNoInfo);			
			vPartNoInfoDao.deleteVendorPartNoInfoByID(seqId);
			strLogType = "��������";
			break;
		case 1://û���ύ�������޸�		
			vPartNoInfoDao.updateVendorPartNoInfo(vPartNoInfo);
			strLogType = "�޸���ʱ����";
			break;
		case 2://�ύ�����޸�
			vPartNoInfoDao.updateVendorPartNoInfo(vPartNoInfo);
			strLogType = "�ύ��ʱ����";
			break;
		case 3://������ͨ������ֻ�޸�״̬
			vPartNoInfoDao.updateVendorPartNoInfoByState(vPartNoInfo);
			strLogType = "������ͨ������";
			break;
		case 4://��������ֻ�޸�״̬			
			//vPartNoInfoDao.updateVendorPartNoInfoByState(vPartNoInfo);
			vPartNoInfoDao.deleteVendorPartNoInfoByID(vPartNoInfo.getSeqId().toString());
			strLogType = "��������";
			break;
		case 5://��������ֻ�޸�״̬
			vPartNoInfoDao.updateVendorPartNoInfoByState(vPartNoInfo);
			strLogType = "��������";
			break;
		default:
			ret =1;
		}
		if(null != staffName){			
			VendorLogUtils.operLog(staffId, staffName, strLogType, "��Ӧ��������Ϣ",(state ==0 ? vPartNoInfo.getLogBizKey()+strLogType : vPartNoInfo.getLogBizKey()), vPartNoInfo.getLogContent(), otherInfo);
		}
		return ret;
	}
	
	/**
	 * ��������
	 * @param vPartNoInfoList
	 * @param staffId
	 * @param staffName
	 * @param otherInfo
	 * @return
	 * @throws Exception
	 */
	public int updateVendorPartNoInfoList(List<VendorPartNoInfo> vPartNoInfoList)throws Exception{
		int ret =0;
		for(VendorPartNoInfo vPartNoInfo : vPartNoInfoList){
			updateVendorPartNoInfo( vPartNoInfo);
		}
		
		return ret;
	}
	
	
	/**
	 * ��������id��ȡ��Ϣ
	 * @param seqId
	 * @return
	 * @throws Exception
	 */
	public VendorPartNoInfo getVendorPartNoInfoByID(String seqId)throws Exception{
		VendorPartNoInfoDao vPartNoInfoDao = (VendorPartNoInfoDao)BeanLocator.getInstance().getBean(VENDOR_PARTNOINFODAO);
		return vPartNoInfoDao.findVendorPartNoInfoByID(seqId);
	}
	
	/**
	 * ����ҵ��������ȡ��Ϣ
	 * @param vPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public VendorPartNoInfo getVendorPartNoInfoByBizKey(VendorPartNoInfo vPartNoInfo) throws Exception{
		VendorPartNoInfoDao vPartNoInfoDao = (VendorPartNoInfoDao)BeanLocator.getInstance().getBean(VENDOR_PARTNOINFODAO);
		return vPartNoInfoDao.findVendorPartNoInfoByBizKey(vPartNoInfo);
	}
	
	/**
	 * ��ȡ�б���Ϣ
	 * @param vPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public List<VendorPartNoInfo> listVendorPartNoInfo(VendorPartNoInfo vPartNoInfo) throws Exception{
		VendorPartNoInfoDao vPartNoInfoDao = (VendorPartNoInfoDao)BeanLocator.getInstance().getBean(VENDOR_PARTNOINFODAO);
		return vPartNoInfoDao.listVendorPartNoInfo(vPartNoInfo);
	}
	
	/**
	 * ��ȡ�ܼ�¼��
	 * @param vPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public Integer listVendorPartNoInfoCount(VendorPartNoInfo vPartNoInfo) throws Exception{
		VendorPartNoInfoDao vPartNoInfoDao = (VendorPartNoInfoDao)BeanLocator.getInstance().getBean(VENDOR_PARTNOINFODAO);
		return vPartNoInfoDao.listVendorPartNoInfoCount(vPartNoInfo);
	}

}
