package com.hbs.domain.vendor.vendorinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.common.pojo.baseinfo.OperLog;
import com.hbs.domain.common.dao.baseinfo.OperLogDao;

/**
 * OperLogDao�ӿ�ʵ����.
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
    		logger.debug("����insertOperLog(OperLog), �������[" + operLog + "]");
    	}
        
    	getSqlMapClientTemplate().insert("Vendor_OperLog_insertOperLog", operLog);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertOperLog(OperLog), ����");
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
        	logger.debug("����findOperLog(OperLog), �������[" + pk + "]");
		}
        OperLog operLog = (OperLog) getSqlMapClientTemplate().queryForObject("Vendor_OperLog_findOperLog", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findOperLog(OperLog), ����[" + operLog + "]");
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
        	logger.debug("����listOperLog(OperLog), �������[" + operKey + "]");
		}
        List<OperLog> list = getSqlMapClientTemplate().queryForList("Vendor_OperLog_listOperLog", operKey);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listOperLog(OperLog), ����[" + list + "]");
		}
        return list;
    }  
    
 
}
