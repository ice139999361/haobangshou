package com.hbs.domain.common.pojo.base;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



/**
 * ��������DO����.
 * 
 *
 * 
 */
public class BaseDomain  {
	

	/** �������.	 */
	private static final int MAX_ROWS = 9999999;

	/**
	 * ��̬�ֶ�. ��ibatis�ļ��п��á�dynamicFields.xxx����ʽ��ȡ��̬�ֶ�ֵ
	 */
	protected Map<String,Object> dynamicFields = new HashMap<String,Object>();

	/** ��ʼ������mysql�����кŴ�0��ʼ��.*/
	private int start = 0;


    /**
	 * ������������������ý����У�ȱʡ����������������¼��.
	 */
	private int end = MAX_ROWS;

	/**
	 * �����ֶ�(��sp.spCode).
	 */
	private String sort;

	/**
	 * ����|����(��ASC).
	 */
	private String order;

	/**
	 * ���ö�̬�ֶ�ֵ.
	 * 
	 * @param fieldName
	 *            �ֶ�����
	 * @param value
	 *            �ֶ�ֵ
	 */
	public void setField(String fieldName, Object value) {
		// if (dynamicFields == null) {
		// dynamicFields = new HashMap();
		// }
		dynamicFields.put(fieldName, value);
	}

	/**
	 * ���ض�̬�ֶ�ֵ.
	 * 
	 * @param fieldName
	 *            �ֶ�����
	 * @return ����
	 */
	public Object getField(String fieldName) {
		if (dynamicFields == null) {
			return null;
		}
		return getDynamicFields().get(fieldName);
	}

	/**
	 * �����ֵ����ֵ.
	 * 
	 * @param encodeType
	 *            ����
	 * @param encodeKey
	 *            �ֵ����
	 * @return �ֵ����ֵ
	 */
//	public String getDict(String encodeType, String encodeKey) {
//		// �������ֵ������õ���ӦDO
//		DisnConfigEncode codeDict = CodeDictContext.getContext().getCodeDict(encodeType,
//				encodeKey);
//		if (codeDict != null) {
//			return codeDict.getEncodeValue();
//		} else {
//			return null;
//		}
//	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Map<String , Object> getDynamicFields() {
		if (dynamicFields != null && dynamicFields.size() > 0) {
			Set<String> set = dynamicFields.keySet();
			for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (dynamicFields.get(key)!=null && dynamicFields.get(key).getClass().isArray()) {
					Object[] objArr = (Object[])dynamicFields.get(key);
					if(objArr.length==1){
					dynamicFields.put(key,	((Object[]) dynamicFields.get(key))[0]);
					}
				}
			}
		}
		return dynamicFields;
	}

	public void setDynamicFields(Map<String,Object> dynamicFields) {
		this.dynamicFields = dynamicFields;
	}


    public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
