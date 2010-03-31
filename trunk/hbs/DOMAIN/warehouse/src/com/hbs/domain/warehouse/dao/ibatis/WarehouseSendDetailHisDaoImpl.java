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
    public Integer insertWarehouseSendDetail(WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertWarehouseSendDetail(WarehouseSendDetail), �������[" + warehouseSendDetail + "]");
    	}
       
    	Integer iRet =(Integer)getSqlMapClientTemplate().insert("WarehouseSendDetailHis_insertWarehouseSendDetail", warehouseSendDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertWarehouseSendDetail(WarehouseSendDetail), ����");
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
        getSqlMapClientTemplate().update("WarehouseSendDetailHis_deleteWarehouseSendDetail", pk);
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
    	getSqlMapClientTemplate().update("WarehouseSendDetailHis_updateWarehouseSendDetail", warehouseSendDetail);
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
        WarehouseSendDetail warehouseSendDetail = (WarehouseSendDetail) getSqlMapClientTemplate().queryForObject("WarehouseSendDetailHis_findWarehouseSendDetail", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findWarehouseSendDetail(WarehouseSendDetail), ����[" + warehouseSendDetail + "]");
		}
        return warehouseSendDetail;
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
        List<WarehouseSendDetail> list = getSqlMapClientTemplate().queryForList("WarehouseSendDetailHis_listWarehouseSendDetail", warehouseSendDetail);
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
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("WarehouseSendDetailHis_listWarehouseSendDetailCount", warehouseSendDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listWarehouseSendDetailCount(WarehouseSendDetail), ����[" + count + "]");
		}
        return count;
    }

	/* (non-Javadoc)
	 * @see com.hbs.domain.warehouse.dao.WarehouseSendDetailDao#findWarehouseSendDetailByBizKey(com.hbs.domain.warehouse.pojo.WarehouseSendDetail)
	 */
	public WarehouseSendDetail findWarehouseSendDetailByBizKey(
			WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.warehouse.dao.WarehouseSendDetailDao#updateWarehouseSendDetailByActiveState(com.hbs.domain.warehouse.pojo.WarehouseSendDetail)
	 */
	public void updateWarehouseSendDetailByActiveState(
			WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.warehouse.dao.WarehouseSendDetailDao#updateWarehouseSendDetailByFinancePeriod(com.hbs.domain.warehouse.pojo.WarehouseSendDetail)
	 */
	public void updateWarehouseSendDetailByFinancePeriod(
			WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.warehouse.dao.WarehouseSendDetailDao#updateWarehouseSendDetailByFinanceState(com.hbs.domain.warehouse.pojo.WarehouseSendDetail)
	 */
	public void updateWarehouseSendDetailByFinanceState(
			WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.warehouse.dao.WarehouseSendDetailDao#updateWarehouseSendDetailByState(com.hbs.domain.warehouse.pojo.WarehouseSendDetail)
	 */
	public void updateWarehouseSendDetailByState(
			WarehouseSendDetail warehouseSendDetail) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}  
}
