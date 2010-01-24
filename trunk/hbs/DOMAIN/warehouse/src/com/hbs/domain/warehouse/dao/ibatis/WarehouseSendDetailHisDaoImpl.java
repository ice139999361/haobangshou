package com.hbs.domain.warehouse.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;
import com.hbs.domain.warehouse.dao.WarehouseSendDetailDao;

/**
 * WarehouseSendDetailDao接口实现类.
 * @author hbs
 *
 */
public class WarehouseSendDetailHisDaoImpl extends SqlMapClientDaoSupport implements WarehouseSendDetailDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(WarehouseSendDetailDaoImpl.class);

    
    
    /**
     * insert.
     * @param warehouseSendDetail warehouseSendDetail
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertWarehouseSendDetail(WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertWarehouseSendDetail(WarehouseSendDetail), 输入参数[" + warehouseSendDetail + "]");
    	}
       
    	getSqlMapClientTemplate().insert("WarehouseSendDetail_insertWarehouseSendDetail", warehouseSendDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertWarehouseSendDetail(WarehouseSendDetail), 返回");
		}
    	
    }

    /**
     * delete.
     * @param warehouseSendDetail warehouseSendDetail
     * @throws DataAccessException DataAccessException
     */
    public void deleteWarehouseSendDetail(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteWarehouseSendDetail(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("WarehouseSendDetail_deleteWarehouseSendDetail", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteWarehouseSendDetail(String)");
		}
    }
    
    /**
     * update.
     * @param warehouseSendDetail warehouseSendDetail
     * @throws DataAccessException DataAccessException
     */
    public void updateWarehouseSendDetail(WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateWarehouseSendDetail(WarehouseSendDetail), 输入参数[" + warehouseSendDetail + "]");
		}
    	getSqlMapClientTemplate().update("WarehouseSendDetail_updateWarehouseSendDetail", warehouseSendDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateWarehouseSendDetail(WarehouseSendDetail)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return warehouseSendDetail
     * @throws DataAccessException DataAccessException
     */
    public WarehouseSendDetail findWarehouseSendDetail(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findWarehouseSendDetail(WarehouseSendDetail), 输入参数[" + pk + "]");
		}
        WarehouseSendDetail warehouseSendDetail = (WarehouseSendDetail) getSqlMapClientTemplate().queryForObject("WarehouseSendDetail_findWarehouseSendDetail", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findWarehouseSendDetail(WarehouseSendDetail), 返回[" + warehouseSendDetail + "]");
		}
        return warehouseSendDetail;
    }
    
    /**
     * list.
     * @param warehouseSendDetail warehouseSendDetail
     * @return warehouseSendDetail list
     * @throws DataAccessException DataAccessException
     */
    public List listWarehouseSendDetail(WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listWarehouseSendDetail(WarehouseSendDetail), 输入参数[" + warehouseSendDetail + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("WarehouseSendDetail_listWarehouseSendDetail", warehouseSendDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listWarehouseSendDetail(WarehouseSendDetail), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param warehouseSendDetail warehouseSendDetail
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listWarehouseSendDetailCount(WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listWarehouseSendDetailCount(WarehouseSendDetail), 输入参数[" + warehouseSendDetail + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("WarehouseSendDetail_listWarehouseSendDetailCount", warehouseSendDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listWarehouseSendDetailCount(WarehouseSendDetail), 返回[" + count + "]");
		}
        return count;
    }  
}
