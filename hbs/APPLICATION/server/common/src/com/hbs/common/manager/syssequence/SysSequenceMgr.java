/**
 * system ��hbs
 * desc:    �Զ�������Ӧ�̶������кţ��ͻ��ͻ������к� �Ĺ�����
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.manager.syssequence;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.dao.SysSequenceDao;
import com.hbs.domain.common.pojo.SysSequence;

public class SysSequenceMgr {

	
	private static final String SYS_SEQUENCE_DAO = "sysSequenceDao";
	
	private static final String STR_REPLACE = "0";
	
	//��Ӧ�̶������к�
	public static final String V_ORDER_PONO = "V_ORDER_PONO";
	//�ͻ��ͻ������к�
	public static final String C_SEND_PONO = "C_SEND_PONO";
	//�ͻ�����
	public static final String GC_CODE = "GC_CODE";
	//��Ӧ�̱���
	public static final String GV_CODE = "GV_CODE";
	
	/**
	 * ���ݴ�������ͣ��������к� "V_ORDER_PONO" "C_SEND_PONO"
	 * @param type
	 * @return   
	 */
	public static String getPoNo(String type){
		String ret = null;
		if(null != type ){
			SysSequenceDao seqDao =(SysSequenceDao)BeanLocator.getInstance().getBean(SYS_SEQUENCE_DAO);
			SysSequence seq = seqDao.findSysSequence(type);
			if(null != seq){//����������Ŀ
				StringBuffer sb = new StringBuffer(seq.getSeqPrefix());			
				String curStrDate = DateUtils.getCurFormatDate(seq.getSeqKey());
				if(curStrDate.equals(seq.getSeqMiddle())){					
					seq.setSeqValue(seq.getSeqValue().intValue()+1);
				}else{
					seq.setSeqMiddle(curStrDate);
					seq.setSeqValue(1);
				}
				seqDao.updateSysSequence(seq);
				sb.append(seq.getSeqMiddle());
				int iLength = seq.getSeqLength();
				int iValLen = seq.getSeqValue().toString().length();
				for(int i =0; i<iLength - iValLen; i++){
					sb.append(STR_REPLACE);
				}
				sb.append(seq.getSeqValue());
				ret = sb.toString();
			}
		}
		return ret;
	}
	/**
	 * ������Ӧ�� �ͻ�����ˮ�� "GC_CODE" "GV_CODE"
	 * @param type
	 * @return
	 */
	public static String getCode(String type){
		String ret = null;
		if(null != type ){
			SysSequenceDao seqDao =(SysSequenceDao)BeanLocator.getInstance().getBean(SYS_SEQUENCE_DAO);
			SysSequence seq = seqDao.findSysSequence(type);
			if(null != seq){//����������Ŀ
				StringBuffer sb = new StringBuffer(seq.getSeqPrefix());	
				seq.setSeqValue(seq.getSeqValue().intValue()+1);
				seqDao.updateSysSequence(seq);				
				int iLength = seq.getSeqLength();
				int iValLen = seq.getSeqValue().toString().length();
				for(int i =0; i<iLength - iValLen; i++){
					sb.append(STR_REPLACE);
				}
				sb.append(seq.getSeqValue());
				ret = sb.toString();
			}
		}
		return ret;
	}
	private SysSequenceMgr(){
		
	}
}
