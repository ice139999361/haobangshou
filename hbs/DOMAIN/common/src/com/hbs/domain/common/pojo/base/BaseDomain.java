package com.hbs.domain.common.pojo.base;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



/**
 * 所有领域DO基类.
 * 
 *
 * 
 */
public class BaseDomain  {
	

	/** 最大行数.	 */
	private static final int MAX_ROWS = 9999999;

	/**
	 * 动态字段. 在ibatis文件中可用“dynamicFields.xxx”方式读取动态字段值
	 */
	protected Map<String,Object> dynamicFields = new HashMap<String,Object>();

	/** 起始行数（mysql物理行号从0开始）.*/
	private int start = 0;


    /**
	 * 结束行数（如果不设置结束行，缺省查所有满足条件记录）.
	 */
	private int end = MAX_ROWS;

	/**
	 * 排序字段(例sp.spCode).
	 */
	private String sort;

	/**
	 * 正序|反序(例ASC).
	 */
	private String order;

	/**
	 * 设置动态字段值.
	 * 
	 * @param fieldName
	 *            字段名称
	 * @param value
	 *            字段值
	 */
	public void setField(String fieldName, Object value) {
		// if (dynamicFields == null) {
		// dynamicFields = new HashMap();
		// }
		dynamicFields.put(fieldName, value);
	}

	/**
	 * 返回动态字段值.
	 * 
	 * @param fieldName
	 *            字段名称
	 * @return 对象
	 */
	public Object getField(String fieldName) {
		if (dynamicFields == null) {
			return null;
		}
		return getDynamicFields().get(fieldName);
	}

	/**
	 * 返回字典编码值.
	 * 
	 * @param encodeType
	 *            分类
	 * @param encodeKey
	 *            字典编码
	 * @return 字典编码值
	 */
//	public String getDict(String encodeType, String encodeKey) {
//		// 从数据字典容器得到相应DO
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
