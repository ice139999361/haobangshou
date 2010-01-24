package com.hbs.domain.warehouse.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.warehouse.pojo.WarehouseRecInfo;
import com.hbs.domain.warehouse.dao.WarehouseRecInfoDao;

/**
 * WarehouseRecInfoDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class WarehouseRecInfoHisDaoImpl extends SqlMapClientDaoSupport implements WarehouseRecInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(WarehouseRecInfoHisDaoImpl.class);

    
    
    /**
     * insert.
     * @param warehouseRecInfo warehouseRecInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertWarehouseRecInfo(WarehouseRecInfo warehouseRecInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertWarehouseRecInfo(WarehouseRecInfo), �������[" + warehouseRecInfo + "]");
    	}
        
    	getSqlMapClientTemplate().insert("WarehouseRecInfo_insertWarehouseRecInfo", warehouseRecInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertWarehouseRecInfo(WarehouseRecInfo), ����");
		}
    
    }

    /**
     * delete.
     * @param warehouseRecInfo warehouseRecInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteWarehouseRecInfo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteWarehouseRecInfo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("WarehouseRecInfo_deleteWarehouseRecInfo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteWarehouseRecInfo(String)");
		}
    }
    
    /**
     * update.
     * @param warehouseRecInfo warehouseRecInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateWarehouseRecInfo(WarehouseRecInfo warehouseRecInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateWarehouseRecInfo(WarehouseRecInfo), �������[" + warehouseRecInfo + "]");
		}
    	getSqlMapClientTemplate().update("WarehouseRecInfo_updateWarehouseRecInfo", warehouseRecInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateWarehouseRecInfo(WarehouseRecInfo)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return warehouseRecInfo
     * @throws DataAccessException DataAccessException
     */
    public WarehouseRecInfo findWarehouseRecInfo(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findWarehouseRecInfo(WarehouseRecInfo), �������[" + pk + "]");
		}
        WarehouseRecInfo warehouseRecInfo = (WarehouseRecInfo) getSqlMapClientTemplate().queryForObject("WarehouseRecInfo_findWarehouseRecInfo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findWarehouseRecInfo(WarehouseRecInfo), ����[" + warehouseRecInfo + "]");
		}
        return warehouseRecInfo;
    }
    
    /**
     * list.
     * @param warehouseRecInfo warehouseRecInfo
     * @return warehouseRecInfo list
     * @throws DataAccessException DataAccessException
     */
    public List listWarehouseRecInfo(WarehouseRecInfo warehouseRecInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listWarehouseRecInfo(WarehouseRecInfo), �������[" + warehouseRecInfo + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("WarehouseRecInfo_listWarehouseRecInfo", warehouseRecInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listWarehouseRecInfo(WarehouseRecInfo), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param warehouseRecInfo warehouseRecInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listWarehouseRecInfoCount(WarehouseRecInfo warehouseRecInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listWarehouseRecInfoCount(WarehouseRecInfo), �������[" + warehouseRecInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("WarehouseRecInfo_listWarehouseRecInfoCount", warehouseRecInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listWarehouseRecInfoCount(WarehouseRecInfo), ����[" + count + "]");
		}
        return count;
    }  
}
