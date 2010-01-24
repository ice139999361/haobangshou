package com.hbs.domain.customer.customerinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;
import com.hbs.domain.customer.customerinfo.dao.CustPartNoInfoDao;

/**
 * CustPartNoInfoDao接口实现类.
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
    		logger.debug("进入insertCustPartNoInfo(CustPartNoInfo), 输入参数[" + custPartNoInfo + "]");
    	}
        
        
    	int id = (Integer)getSqlMapClientTemplate().insert("CustPartNoInfo_insertCustPartNoInfo", custPartNoInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertCustPartNoInfo(CustPartNoInfo), 返回");
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
    		logger.debug("进入deleteCustPartNoInfo(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("CustPartNoInfo_deleteCustPartNoInfoByID", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteCustPartNoInfo(String)");
		}
    }
    
    /**
     * delete.
     * @param custPartNoInfo custPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteCustPartNoInfoByBizKey(CustPartNoInfo custPartNoInfo)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteCustPartNoInfo(String), 输入参数[" + custPartNoInfo + "]");
		}
        getSqlMapClientTemplate().update("CustPartNoInfo_deleteCustPartNoInfoByBizKey", custPartNoInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteCustPartNoInfo(String)");
		}
    }
    
    /**
     * update.
     * @param custPartNoInfo custPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateCustPartNoInfo(CustPartNoInfo), 输入参数[" + custPartNoInfo + "]");
		}
    	getSqlMapClientTemplate().update("CustPartNoInfo_updateCustPartNoInfo", custPartNoInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateCustPartNoInfo(CustPartNoInfo)");
		}
    }
    
    /**
     * update.
     * @param custPartNoInfo custPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateCustPartNoInfoByState(CustPartNoInfo custPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateCustPartNoInfo(CustPartNoInfo), 输入参数[" + custPartNoInfo + "]");
		}
    	getSqlMapClientTemplate().update("CustPartNoInfo_updateCustPartNoInfoByState", custPartNoInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateCustPartNoInfo(CustPartNoInfo)");
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
        	logger.debug("进入findCustPartNoInfo(CustPartNoInfo), 输入参数[" + custPartNoInfo + "]");
		}
        CustPartNoInfo cPartNoInfo = (CustPartNoInfo) getSqlMapClientTemplate().queryForObject("CustPartNoInfo_findCustPartNoInfoByBizKey", custPartNoInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findCustPartNoInfo(CustPartNoInfo), 返回[" + cPartNoInfo + "]");
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
        	logger.debug("进入findCustPartNoInfo(CustPartNoInfo), 输入参数[" + pk + "]");
		}
        CustPartNoInfo custPartNoInfo = (CustPartNoInfo) getSqlMapClientTemplate().queryForObject("CustPartNoInfo_findCustPartNoInfoByID", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findCustPartNoInfo(CustPartNoInfo), 返回[" + custPartNoInfo + "]");
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
        	logger.debug("进入listCustPartNoInfo(CustPartNoInfo), 输入参数[" + custPartNoInfo + "]");
		}
        List<CustPartNoInfo> list = getSqlMapClientTemplate().queryForList("CustPartNoInfo_listCustPartNoInfo", custPartNoInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listCustPartNoInfo(CustPartNoInfo), 返回[" + list + "]");
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
        	logger.debug("进入listCustPartNoInfoCount(CustPartNoInfo), 输入参数[" + custPartNoInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("CustPartNoInfo_listCustPartNoInfoCount", custPartNoInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listCustPartNoInfoCount(CustPartNoInfo), 返回[" + count + "]");
		}
        return count;
    }  
}
