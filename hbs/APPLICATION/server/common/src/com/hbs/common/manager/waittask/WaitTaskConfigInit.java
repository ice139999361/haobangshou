package com.hbs.common.manager.waittask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.waittask.dao.WaitTaskConfigInfoDao;
import com.hbs.domain.waittask.pojo.WaitTaskConfigInfo;

/**
 * �������ó�ʼ��
 * 
 * @author yangzj
 * 
 */
public class WaitTaskConfigInit {
	/**
	 * �������û���MAP
	 */
	private static Map<String, WaitTaskConfigInfo> hm = null;

	/**
	 * ��־����
	 */
	private static final Logger logger = Logger
			.getLogger(WaitTaskConfigInit.class);

	private static final String WAITTASKCONFIGINFODAO = "waitTaskConfigInfoDao";

	/**
	 * ˽�й��캯������ֹ�ⲿʵ����
	 */
	private WaitTaskConfigInit(){
		
	}
	
	/**
	 * ���ش����������ݿ����ݣ���������
	 */
	@SuppressWarnings("unchecked")
	private static void init() {
		logger.info("����������û������ݼ���!");
		try {
			WaitTaskConfigInfoDao waitTaskConfigInfoDao = (WaitTaskConfigInfoDao) BeanLocator
					.getInstance().getBean(WAITTASKCONFIGINFODAO);
			if (null != waitTaskConfigInfoDao) {
				List<WaitTaskConfigInfo> list = waitTaskConfigInfoDao
						.listWaitTaskConfigInfo();
				if (null != list && list.size() > 0) {
					Map<String, WaitTaskConfigInfo> tempMap = new HashMap<String, WaitTaskConfigInfo>();
					for (WaitTaskConfigInfo info : list) {
						tempMap.put(info.getConfigId(), info);
					}
					hm = tempMap;
				} else {
					logger.error("����������û������ݼ���!,���ݿ��������ݣ������ô�������!");
				}

			} else {
				logger.error("����������û������ݼ���!,�޷���ȡ�����ȡʵ������鿴spring����!");
			}
		} catch (Exception e) {
			logger.error("�������û������ݼ���ʧ��! ", e);
		}
	}
	
	
	
	/**
	 * ����configid����ȡ�����������Ŀ
	 * @param configId
	 * @return
	 */
	public static WaitTaskConfigInfo getWaitTaskConfigInfo(String configId){
		if(null == hm){
			init();
		}		
		return hm.get(configId);
	}

}
