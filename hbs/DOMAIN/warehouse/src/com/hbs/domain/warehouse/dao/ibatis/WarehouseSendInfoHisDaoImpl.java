package com.hbs.domain.warehouse.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.warehouse.pojo.WarehouseSendInfo;
import com.hbs.domain.warehouse.dao.WarehouseSendInfoDao;

/**
 * WarehouseSendInfoDao接口实现类.
 * @author hbs
 *
 */
public class WarehouseSendInfoHisDaoImpl extends SqlMapClientDaoSupport implements WarehouseSendInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(WarehouseSendInfoHisDaoImpl.class);

    
    
    /**
     * insert.
     * @param warehouseSendInfo warehouseSendInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertWarehouseSendInfo(WarehouseSendInfo warehouseSendInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertWarehouseSendInfo(WarehouseSendInfo), 输入参数[" + warehouseSendInfo + "]");
    	}
        
    	getSqlMapClientTemplate().insert("WarehouseSendInfo_insertWarehouseSendInfo", warehouseSendInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertWarehouseSendInfo(WarehouseSendInfo), 返回");
		}
    	
    }

    /**
     * delete.
     * @param warehouseSendInfo warehouseSendInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteWarehouseSendInfo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteWarehouseSendInfo(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("WarehouseSendInfo_deleteWarehouseSendInfo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteWarehouseSendInfo(String)");
		}
    }
    
    /**
     * update.
     * @param warehouseSendInfo warehouseSendInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateWarehouseSendInfo(WarehouseSendInfo warehouseSendInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateWarehouseSendInfo(WarehouseSendInfo), 输入参数[" + warehouseSendInfo + "]");
		}
    	getSqlMapClientTemplate().update("WarehouseSendInfo_updateWarehouseSendInfo", warehouseSendInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateWarehouseSendInfo(WarehouseSendInfo)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return warehouseSendInfo
     * @throws DataAccessException DataAccessException
     */
    public WarehouseSendInfo findWarehouseSendInfo(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findWarehouseSendInfo(WarehouseSendInfo), 输入参数[" + pk + "]");
		}
        WarehouseSendInfo warehouseSendInfo = (WarehouseSendInfo) getSqlMapClientTemplate().queryForObject("WarehouseSendInfo_findWarehouseSendInfo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findWarehouseSendInfo(WarehouseSendInfo), 返回[" + warehouseSendInfo + "]");
		}
        return warehouseSendInfo;
    }
    
    /**
     * list.
     * @param warehouseSendInfo warehouseSendInfo
     * @return warehouseSendInfo list
     * @throws DataAccessException DataAccessException
     */
    public List listWarehouseSendInfo(WarehouseSendInfo warehouseSendInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listWarehouseSendInfo(WarehouseSendInfo), 输入参数[" + warehouseSendInfo + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("WarehouseSendInfo_listWarehouseSendInfo", warehouseSendInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listWarehouseSendInfo(WarehouseSendInfo), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param warehouseSendInfo warehouseSendInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listWarehouseSendInfoCount(WarehouseSendInfo warehouseSendInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listWarehouseSendInfoCount(WarehouseSendInfo), 输入参数[" + warehouseSendInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("WarehouseSendInfo_listWarehouseSendInfoCount", warehouseSendInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listWarehouseSendInfoCount(WarehouseSendInfo), 返回[" + count + "]");
		}
        return count;
    }

	/* (non-Javadoc)
	 * @see com.hbs.domain.warehouse.dao.WarehouseSendInfoDao#findWarehouseSendInfo(com.hbs.domain.warehouse.pojo.WarehouseSendInfo)
	 */
	public WarehouseSendInfo findWarehouseSendInfo(
			WarehouseSendInfo warehouseSendInfo) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}  
}
