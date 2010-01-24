package com.hbs.domain.customer.customerinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;
import com.hbs.domain.customer.customerinfo.dao.CustPartNoInfoDao;

/**
 * CustPartNoInfoDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class CustPartNoInfoDaoImpl extends SqlMapClientDaoSupport implements CustPartNoInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustPartNoInfoDaoImpl.class);

    
    
    /**
     * insert.
     * @param custPartNoInfo custPartNoInfo
     
     * @throws DataAccessException DataAccessException
     */
    public int insertCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertCustPartNoInfo(CustPartNoInfo), �������[" + custPartNoInfo + "]");
    	}
        
        
    	int id = (Integer)getSqlMapClientTemplate().insert("CustPartNoInfo_insertCustPartNoInfo", custPartNoInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertCustPartNoInfo(CustPartNoInfo), ����");
		}
    	return id;
    }

    /**
     * delete.
     * @param custPartNoInfo custPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteCustPartNoInfoByID(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteCustPartNoInfo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("CustPartNoInfo_deleteCustPartNoInfoByID", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteCustPartNoInfo(String)");
		}
    }
    
    /**
     * delete.
     * @param custPartNoInfo custPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteCustPartNoInfoByBizKey(CustPartNoInfo custPartNoInfo)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteCustPartNoInfo(String), �������[" + custPartNoInfo + "]");
		}
        getSqlMapClientTemplate().update("CustPartNoInfo_deleteCustPartNoInfoByBizKey", custPartNoInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteCustPartNoInfo(String)");
		}
    }
    
    /**
     * update.
     * @param custPartNoInfo custPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateCustPartNoInfo(CustPartNoInfo), �������[" + custPartNoInfo + "]");
		}
    	getSqlMapClientTemplate().update("CustPartNoInfo_updateCustPartNoInfo", custPartNoInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateCustPartNoInfo(CustPartNoInfo)");
		}
    }
    
    /**
     * update.
     * @param custPartNoInfo custPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateCustPartNoInfoByState(CustPartNoInfo custPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateCustPartNoInfo(CustPartNoInfo), �������[" + custPartNoInfo + "]");
		}
    	getSqlMapClientTemplate().update("CustPartNoInfo_updateCustPartNoInfoByState", custPartNoInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateCustPartNoInfo(CustPartNoInfo)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return custPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    public CustPartNoInfo findCustPartNoInfoByBizKey(CustPartNoInfo custPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findCustPartNoInfo(CustPartNoInfo), �������[" + custPartNoInfo + "]");
		}
        CustPartNoInfo cPartNoInfo = (CustPartNoInfo) getSqlMapClientTemplate().queryForObject("CustPartNoInfo_findCustPartNoInfoByBizKey", custPartNoInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findCustPartNoInfo(CustPartNoInfo), ����[" + cPartNoInfo + "]");
		}
        return cPartNoInfo;
    }
    
    /**
     * find.
     * @param id id
     * @return custPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    public CustPartNoInfo findCustPartNoInfoByID(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findCustPartNoInfo(CustPartNoInfo), �������[" + pk + "]");
		}
        CustPartNoInfo custPartNoInfo = (CustPartNoInfo) getSqlMapClientTemplate().queryForObject("CustPartNoInfo_findCustPartNoInfoByID", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findCustPartNoInfo(CustPartNoInfo), ����[" + custPartNoInfo + "]");
		}
        return custPartNoInfo;
    }
    
    /**
     * list.
     * @param custPartNoInfo custPartNoInfo
     * @return custPartNoInfo list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<CustPartNoInfo> listCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listCustPartNoInfo(CustPartNoInfo), �������[" + custPartNoInfo + "]");
		}
        List<CustPartNoInfo> list = getSqlMapClientTemplate().queryForList("CustPartNoInfo_listCustPartNoInfo", custPartNoInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listCustPartNoInfo(CustPartNoInfo), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param custPartNoInfo custPartNoInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listCustPartNoInfoCount(CustPartNoInfo custPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listCustPartNoInfoCount(CustPartNoInfo), �������[" + custPartNoInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("CustPartNoInfo_listCustPartNoInfoCount", custPartNoInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listCustPartNoInfoCount(CustPartNoInfo), ����[" + count + "]");
		}
        return count;
    }  
}
