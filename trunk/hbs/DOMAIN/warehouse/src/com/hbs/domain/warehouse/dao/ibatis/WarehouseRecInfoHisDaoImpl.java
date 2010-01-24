package com.hbs.domain.warehouse.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.warehouse.pojo.WarehouseRecInfo;
import com.hbs.domain.warehouse.dao.WarehouseRecInfoDao;

/**
 * WarehouseRecInfoDao接口实现类.
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
    		logger.debug("进入insertWarehouseRecInfo(WarehouseRecInfo), 输入参数[" + warehouseRecInfo + "]");
    	}
        
    	getSqlMapClientTemplate().insert("WarehouseRecInfo_insertWarehouseRecInfo", warehouseRecInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertWarehouseRecInfo(WarehouseRecInfo), 返回");
		}
    
    }

    /**
     * delete.
     * @param warehouseRecInfo warehouseRecInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteWarehouseRecInfo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteWarehouseRecInfo(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("WarehouseRecInfo_deleteWarehouseRecInfo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteWarehouseRecInfo(String)");
		}
    }
    
    /**
     * update.
     * @param warehouseRecInfo warehouseRecInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateWarehouseRecInfo(WarehouseRecInfo warehouseRecInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateWarehouseRecInfo(WarehouseRecInfo), 输入参数[" + warehouseRecInfo + "]");
		}
    	getSqlMapClientTemplate().update("WarehouseRecInfo_updateWarehouseRecInfo", warehouseRecInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateWarehouseRecInfo(WarehouseRecInfo)");
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
        	logger.debug("进入findWarehouseRecInfo(WarehouseRecInfo), 输入参数[" + pk + "]");
		}
        WarehouseRecInfo warehouseRecInfo = (WarehouseRecInfo) getSqlMapClientTemplate().queryForObject("WarehouseRecInfo_findWarehouseRecInfo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findWarehouseRecInfo(WarehouseRecInfo), 返回[" + warehouseRecInfo + "]");
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
        	logger.debug("进入listWarehouseRecInfo(WarehouseRecInfo), 输入参数[" + warehouseRecInfo + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("WarehouseRecInfo_listWarehouseRecInfo", warehouseRecInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listWarehouseRecInfo(WarehouseRecInfo), 返回[" + list + "]");
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
        	logger.debug("进入listWarehouseRecInfoCount(WarehouseRecInfo), 输入参数[" + warehouseRecInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("WarehouseRecInfo_listWarehouseRecInfoCount", warehouseRecInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listWarehouseRecInfoCount(WarehouseRecInfo), 返回[" + count + "]");
		}
        return count;
    }  
}
