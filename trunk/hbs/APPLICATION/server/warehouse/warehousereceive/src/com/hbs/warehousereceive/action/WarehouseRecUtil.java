/**
 * 
 */
package com.hbs.warehousereceive.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hbs.common.action.FieldErr;
import com.hbs.domain.warehouse.pojo.WarehouseRecInfo;

/**
 * @author xyf
 *
 */
public class WarehouseRecUtil {

	public static boolean checkKeyFields(WarehouseRecInfo warehouseRec) {
		// TODO:WarehouseRecUtil.checkKeyFields
		return false;
	}

	@SuppressWarnings("unchecked")
	public static void processListData(WarehouseRecInfo warehouseRec,
			HttpServletRequest httpServletRequest, Map otherData) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public static List<FieldErr> checkInputFields(
			WarehouseRecInfo warehouseRec, Map otherData) {
		// TODO Auto-generated method stub
		return null;
	}

}
