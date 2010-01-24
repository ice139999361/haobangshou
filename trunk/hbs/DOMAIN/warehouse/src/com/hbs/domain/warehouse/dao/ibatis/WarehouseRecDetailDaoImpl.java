package com.hbs.domain.warehouse.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;
import com.hbs.domain.warehouse.dao.WarehouseRecDetailDao;

/**
 * WarehouseRecDetailDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class WarehouseRecDetailDaoImpl extends SqlMapClientDaoSupport implements WarehouseRecDetailDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(WarehouseRecDetailDaoImpl.class);

    
    
    /**
     * insert.
     * @param warehouseRecDetail warehouseRecDetail
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertWarehouseRecDetail(WarehouseRecDetail warehouseRecDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertWarehouseRecDetail(WarehouseRecDetail), �������[" + warehouseRecDetail + "]");
    	}
        
    	getSqlMapClientTemplate().insert("WarehouseRecDetail_insertWarehouseRecDetail", warehouseRecDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertWarehouseRecDetail(WarehouseRecDetail), ����");
		}
    	
    }

    /**
     * delete.
     * @param warehouseRecDetail warehouseRecDetail
     * @throws DataAccessException DataAccessException
     */
    public void deleteWarehouseRecDetail(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteWarehouseRecDetail(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("WarehouseRecDetail_deleteWarehouseRecDetail", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteWarehouseRecDetail(String)");
		}
    }
    
    /**
     * update.
     * @param warehouseRecDetail warehouseRecDetail
     * @throws DataAccessException DataAccessException
     */
    public void updateWarehouseRecDetail(WarehouseRecDetail warehouseRecDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateWarehouseRecDetail(WarehouseRecDetail), �������[" + warehouseRecDetail + "]");
		}
    	getSqlMapClientTemplate().update("WarehouseRecDetail_updateWarehouseRecDetail", warehouseRecDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateWarehouseRecDetail(WarehouseRecDetail)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return warehouseRecDetail
     * @throws DataAccessException DataAccessException
     */
    public WarehouseRecDetail findWarehouseRecDetail(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findWarehouseRecDetail(WarehouseRecDetail), �������[" + pk + "]");
		}
        WarehouseRecDetail warehouseRecDetail = (WarehouseRecDetail) getSqlMapClientTemplate().queryForObject("WarehouseRecDetail_findWarehouseRecDetail", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findWarehouseRecDetail(WarehouseRecDetail), ����[" + warehouseRecDetail + "]");
		}
        return warehouseRecDetail;
    }
    
    /**
     * list.
     * @param warehouseRecDetail warehouseRecDetail
     * @return warehouseRecDetail list
     * @throws DataAccessException DataAccessException
     */
    public List listWarehouseRecDetail(WarehouseRecDetail warehouseRecDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listWarehouseRecDetail(WarehouseRecDetail), �������[" + warehouseRecDetail + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("WarehouseRecDetail_listWarehouseRecDetail", warehouseRecDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listWarehouseRecDetail(WarehouseRecDetail), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param warehouseRecDetail warehouseRecDetail
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listWarehouseRecDetailCount(WarehouseRecDetail warehouseRecDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listWarehouseRecDetailCount(WarehouseRecDetail), �������[" + warehouseRecDetail + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("WarehouseRecDetail_listWarehouseRecDetailCount", warehouseRecDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listWarehouseRecDetailCount(WarehouseRecDetail), ����[" + count + "]");
		}
        return count;
    }  
}
