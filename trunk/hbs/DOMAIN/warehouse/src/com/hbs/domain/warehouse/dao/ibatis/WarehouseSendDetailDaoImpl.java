package com.hbs.domain.warehouse.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;
import com.hbs.domain.warehouse.dao.WarehouseSendDetailDao;

/**
 * WarehouseSendDetailDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class WarehouseSendDetailDaoImpl extends SqlMapClientDaoSupport implements WarehouseSendDetailDao
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
    public Integer insertWarehouseSendDetail(WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertWarehouseSendDetail(WarehouseSendDetail), �������[" + warehouseSendDetail + "]");
    	}
      
    	Integer iRet =(Integer)getSqlMapClientTemplate().insert("WarehouseSendDetail_insertWarehouseSendDetail", warehouseSendDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertWarehouseSendDetail(WarehouseSendDetail), ����]");
		}
    	return iRet;
    }

    /**
     * delete.
     * @param warehouseSendDetail warehouseSendDetail
     * @throws DataAccessException DataAccessException
     */
    public void deleteWarehouseSendDetail(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteWarehouseSendDetail(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("WarehouseSendDetail_deleteWarehouseSendDetail", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteWarehouseSendDetail(String)");
		}
    }
    
    /**
     * update.
     * @param warehouseSendDetail warehouseSendDetail
     * @throws DataAccessException DataAccessException
     */
    public void updateWarehouseSendDetail(WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateWarehouseSendDetail(WarehouseSendDetail), �������[" + warehouseSendDetail + "]");
		}
    	getSqlMapClientTemplate().update("WarehouseSendDetail_updateWarehouseSendDetail", warehouseSendDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateWarehouseSendDetail(WarehouseSendDetail)");
		}
    }
    
    public void updateWarehouseSendDetailByState(WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateWarehouseSendDetail(WarehouseSendDetail), �������[" + warehouseSendDetail + "]");
		}
    	getSqlMapClientTemplate().update("WarehouseSendDetail_updateWarehouseSendDetailByState", warehouseSendDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateWarehouseSendDetail(WarehouseSendDetail)");
		}
    }
    
    public void updateWarehouseSendDetailByActiveState(WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateWarehouseSendDetail(WarehouseSendDetail), �������[" + warehouseSendDetail + "]");
		}
    	getSqlMapClientTemplate().update("WarehouseSendDetail_updateWarehouseSendDetailByActiveState", warehouseSendDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateWarehouseSendDetail(WarehouseSendDetail)");
		}
    }
    
    public void updateWarehouseSendDetailByFinanceState(WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateWarehouseSendDetail(WarehouseSendDetail), �������[" + warehouseSendDetail + "]");
		}
    	getSqlMapClientTemplate().update("WarehouseSendDetail_updateWarehouseSendDetailByFinanceState", warehouseSendDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateWarehouseSendDetail(WarehouseSendDetail)");
		}
    }
    
    public void updateWarehouseSendDetailByFinancePeriod(WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateWarehouseSendDetail(WarehouseSendDetail), �������[" + warehouseSendDetail + "]");
		}
    	getSqlMapClientTemplate().update("WarehouseSendDetail_updateWarehouseSendDetailByFinancePeriod", warehouseSendDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateWarehouseSendDetail(WarehouseSendDetail)");
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
        	logger.debug("����findWarehouseSendDetail(WarehouseSendDetail), �������[" + pk + "]");
		}
        WarehouseSendDetail warehouseSendDetail = (WarehouseSendDetail) getSqlMapClientTemplate().queryForObject("WarehouseSendDetail_findWarehouseSendDetail", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findWarehouseSendDetail(WarehouseSendDetail), ����[" + warehouseSendDetail + "]");
		}
        return warehouseSendDetail;
    }
    public WarehouseSendDetail findWarehouseSendDetailByBizKey(WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findWarehouseSendDetail(WarehouseSendDetail), �������[" + warehouseSendDetail + "]");
		}
        WarehouseSendDetail detail = (WarehouseSendDetail) getSqlMapClientTemplate().queryForObject("WarehouseSendDetail_findWarehouseSendDetailByBizKey", warehouseSendDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findWarehouseSendDetail(WarehouseSendDetail), ����[" + warehouseSendDetail + "]");
		}
        return detail;
    }
    /**
     * list.
     * @param warehouseSendDetail warehouseSendDetail
     * @return warehouseSendDetail list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<WarehouseSendDetail> listWarehouseSendDetail(WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listWarehouseSendDetail(WarehouseSendDetail), �������[" + warehouseSendDetail + "]");
		}
        List<WarehouseSendDetail> list = getSqlMapClientTemplate().queryForList("WarehouseSendDetail_listWarehouseSendDetail", warehouseSendDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listWarehouseSendDetail(WarehouseSendDetail), ����[" + list + "]");
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
        	logger.debug("����listWarehouseSendDetailCount(WarehouseSendDetail), �������[" + warehouseSendDetail + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("WarehouseSendDetail_listWarehouseSendDetailCount", warehouseSendDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listWarehouseSendDetailCount(WarehouseSendDetail), ����[" + count + "]");
		}
        return count;
    }  
}
