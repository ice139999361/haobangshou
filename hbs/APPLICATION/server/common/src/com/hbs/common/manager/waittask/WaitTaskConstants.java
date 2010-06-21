package com.hbs.common.manager.waittask;

import java.util.HashMap;
import java.util.Map;

public class WaitTaskConstants {
	private static Map<String , String > mustMap = new HashMap<String, String>();
	
	static {
		mustMap.put("ADJUSTMENT_001", "����comments������������Ҫ����,��������!");
		mustMap.put("CUSTOMER001", "����comments���ͻ�������Ҫ����,��������!");
		mustMap.put("CUSTOMER003", "����comments���ͻ�����������ͨ��,��������!");
		mustMap.put("CUSTOMER003", "����comments���ͻ�����������ͨ��,��������!");
		mustMap.put("CUST_ORDER_001", "����comments���ͻ��������������,��������!");
		mustMap.put("CUST_ORDER_002", "����comments���ͻ�������Ҫ����,��������!");
		mustMap.put("CUST_ORDER_003", "����comments���ͻ�������Ҫ����,��������!");
		mustMap.put("CUST_ORDER_004", "����comments���ͻ���������������ͨ��,��������!");
		mustMap.put("CUST_ORDER_005", "����comments���ͻ������ɹ���ͬ�⽻��,��������!");
		mustMap.put("CUST_ORDER_006", "����comments���ͻ�����ҵ���޸��˽���,��������!");
		mustMap.put("CUST_ORDER_007", "����comments���ͻ������������˻�,��������!");
		mustMap.put("CUST_ORDER_008", "����comments���ͻ���������ȷ��Ԥ����,��������!");
		mustMap.put("CUST_ORDER_009", "����comments���ͻ�����Ԥ��x%��ʣ����������δ�յ�ʣ���������ȷ���,��������!");
		mustMap.put("CUST_PARTNO_001", "����comments���ͻ�������Ҫ����,��������!");
		mustMap.put("CUST_PARTNO_003", "����comments���ͻ�����������ͨ��,��������!");
		mustMap.put("VENDOR001", "����comments����Ӧ��������Ҫ����,��������!");
		mustMap.put("VENDOR003", "����comments����Ӧ������������ͨ��,��������!");
		mustMap.put("VENDOR_PARTNO_001", "����comments����Ӧ��������Ҫ����,��������!");
		mustMap.put("VENDOR_PARTNO_003", "����comments����Ӧ������������ͨ��,��������!");
	}
	
	
	public static String getComments(String cfgID){
		return mustMap.get(cfgID);
	}
}
