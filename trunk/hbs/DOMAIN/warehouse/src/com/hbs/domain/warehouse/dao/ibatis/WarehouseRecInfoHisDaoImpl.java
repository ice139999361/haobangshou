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
        
    	getSqlMapClientTemplate().insert("WarehouseRecInfoHis_insertWarehouseRecInfo", warehouseRecInfo);
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
        getSqlMapClientTemplate().update("WarehouseRecInfoHis_deleteWarehouseRecInfo", pk);
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
    	getSqlMapClientTemplate().update("WarehouseRecInfoHis_updateWarehouseRecInfo", warehouseRecInfo);
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
        WarehouseRecInfo warehouseRecInfo = (WarehouseRecInfo) getSqlMapClientTemplate().queryForObject("WarehouseRecInfoHis_findWarehouseRecInfo", pk);
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
    @SuppressWarnings("unchecked")
	public List<WarehouseRecInfo> listWarehouseRecInfo(WarehouseRecInfo warehouseRecInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listWarehouseRecInfo(WarehouseRecInfo), 输入参数[" + warehouseRecInfo + "]");
		}
        List<WarehouseRecInfo> list = getSqlMapClientTemplate().queryForList("WarehouseRecInfoHis_listWarehouseRecInfo", warehouseRecInfo);
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
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("WarehouseRecInfoHis_listWarehouseRecInfoCount", warehouseRecInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listWarehouseRecInfoCount(WarehouseRecInfo), 返回[" + count + "]");
		}
        return count;
    }

	/* (non-Javadoc)
	 * @see com.hbs.domain.warehouse.dao.WarehouseRecInfoDao#findWarehouseRecInfo(com.hbs.domain.warehouse.pojo.WarehouseRecInfo)
	 */
	public WarehouseRecInfo findWarehouseRecInfo(
			WarehouseRecInfo warehouseRecInfo) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.warehouse.dao.WarehouseRecInfoDao#updateWarehouseRecInfoByActiveState(com.hbs.domain.warehouse.pojo.WarehouseRecInfo)
	 */
	public void updateWarehouseRecInfoByActiveState(
			WarehouseRecInfo warehouseRecInfo) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.warehouse.dao.WarehouseRecInfoDao#updateWarehouseRecInfoByFinanceState(com.hbs.domain.warehouse.pojo.WarehouseRecInfo)
	 */
	public void updateWarehouseRecInfoByFinanceState(
			WarehouseRecInfo warehouseRecInfo) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.warehouse.dao.WarehouseRecInfoDao#updateWarehouseRecInfoByState(com.hbs.domain.warehouse.pojo.WarehouseRecInfo)
	 */
	public void updateWarehouseRecInfoByState(WarehouseRecInfo warehouseRecInfo)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}  
}
