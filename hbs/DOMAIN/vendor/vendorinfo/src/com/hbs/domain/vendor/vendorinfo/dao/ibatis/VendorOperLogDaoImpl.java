package com.hbs.domain.vendor.vendorinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.common.pojo.baseinfo.OperLog;
import com.hbs.domain.common.dao.baseinfo.OperLogDao;

/**
 * OperLogDao接口实现类.
 * @author hbs
 *
 */
public class VendorOperLogDaoImpl extends SqlMapClientDaoSupport implements OperLogDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorOperLogDaoImpl.class);

    
    
    /**
     * insert.
     * @param operLog operLog
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertOperLog(OperLog operLog) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertOperLog(OperLog), 输入参数[" + operLog + "]");
    	}
        
    	getSqlMapClientTemplate().insert("Vendor_OperLog_insertOperLog", operLog);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertOperLog(OperLog), 返回");
		}
    
    }


    /**
     * find.
     * @param id id
     * @return operLog
     * @throws DataAccessException DataAccessException
     */
    public OperLog findOperLog(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findOperLog(OperLog), 输入参数[" + pk + "]");
		}
        OperLog operLog = (OperLog) getSqlMapClientTemplate().queryForObject("Vendor_OperLog_findOperLog", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findOperLog(OperLog), 返回[" + operLog + "]");
		}
        return operLog;
    }
    
    /**
     * list.
     * @param operLog operLog
     * @return operLog list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<OperLog> listOperLog(OperLog operKey) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listOperLog(OperLog), 输入参数[" + operKey + "]");
		}
        List<OperLog> list = getSqlMapClientTemplate().queryForList("Vendor_OperLog_listOperLog", operKey);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listOperLog(OperLog), 返回[" + list + "]");
		}
        return list;
    }  
    
 
}
