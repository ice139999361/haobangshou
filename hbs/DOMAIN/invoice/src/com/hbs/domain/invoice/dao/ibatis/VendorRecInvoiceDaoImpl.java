package com.hbs.domain.invoice.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.common.pojo.baseinfo.InvoiceInfo;
import com.hbs.domain.common.dao.baseinfo.InvoiceInfoDao;

/**
 * InvoiceInfoDao接口实现类.
 * @author hbs
 *
 */
public class VendorRecInvoiceDaoImpl extends SqlMapClientDaoSupport implements InvoiceInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorRecInvoiceDaoImpl.class);

    
    
    /**
     * insert.
     * @param invoiceInfo invoiceInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public Integer insertInvoiceInfo(InvoiceInfo invoiceInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertInvoiceInfo(InvoiceInfo), 输入参数[" + invoiceInfo + "]");
    	}
        
       
        
    	Integer i = (Integer)getSqlMapClientTemplate().insert("InvoiceInfo_insertInvoiceInfo", invoiceInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertInvoiceInfo(InvoiceInfo), 返回");
		}
    	return i;
    }

    /**
     * delete.
     * @param invoiceInfo invoiceInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteInvoiceInfo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteInvoiceInfo(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("InvoiceInfo_deleteInvoiceInfo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteInvoiceInfo(String)");
		}
    }
    
    /**
     * update.
     * @param invoiceInfo invoiceInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateInvoiceInfo(InvoiceInfo invoiceInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateInvoiceInfo(InvoiceInfo), 输入参数[" + invoiceInfo + "]");
		}
    	getSqlMapClientTemplate().update("InvoiceInfo_updateInvoiceInfo", invoiceInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateInvoiceInfo(InvoiceInfo)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return invoiceInfo
     * @throws DataAccessException DataAccessException
     */
    public InvoiceInfo findInvoiceInfo(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findInvoiceInfo(InvoiceInfo), 输入参数[" + pk + "]");
		}
        InvoiceInfo invoiceInfo = (InvoiceInfo) getSqlMapClientTemplate().queryForObject("InvoiceInfo_findInvoiceInfo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findInvoiceInfo(InvoiceInfo), 返回[" + invoiceInfo + "]");
		}
        return invoiceInfo;
    }
    
    /**
     * list.
     * @param invoiceInfo invoiceInfo
     * @return invoiceInfo list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<InvoiceInfo> listInvoiceInfo(InvoiceInfo invoiceInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listInvoiceInfo(InvoiceInfo), 输入参数[" + invoiceInfo + "]");
		}
        List<InvoiceInfo> list = getSqlMapClientTemplate().queryForList("InvoiceInfo_listInvoiceInfo", invoiceInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listInvoiceInfo(InvoiceInfo), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param invoiceInfo invoiceInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listInvoiceInfoCount(InvoiceInfo invoiceInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listInvoiceInfoCount(InvoiceInfo), 输入参数[" + invoiceInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("InvoiceInfo_listInvoiceInfoCount", invoiceInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listInvoiceInfoCount(InvoiceInfo), 返回[" + count + "]");
		}
        return count;
    }  
}
