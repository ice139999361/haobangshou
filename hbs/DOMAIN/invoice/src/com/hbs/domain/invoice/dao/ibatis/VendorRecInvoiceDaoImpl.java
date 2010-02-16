package com.hbs.domain.invoice.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.common.pojo.baseinfo.InvoiceInfo;
import com.hbs.domain.common.dao.baseinfo.InvoiceInfoDao;

/**
 * InvoiceInfoDao�ӿ�ʵ����.
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
    		logger.debug("����insertInvoiceInfo(InvoiceInfo), �������[" + invoiceInfo + "]");
    	}
        
       
        
    	Integer i = (Integer)getSqlMapClientTemplate().insert("InvoiceInfo_insertInvoiceInfo", invoiceInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertInvoiceInfo(InvoiceInfo), ����");
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
    		logger.debug("����deleteInvoiceInfo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("InvoiceInfo_deleteInvoiceInfo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteInvoiceInfo(String)");
		}
    }
    
    /**
     * update.
     * @param invoiceInfo invoiceInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateInvoiceInfo(InvoiceInfo invoiceInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateInvoiceInfo(InvoiceInfo), �������[" + invoiceInfo + "]");
		}
    	getSqlMapClientTemplate().update("InvoiceInfo_updateInvoiceInfo", invoiceInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateInvoiceInfo(InvoiceInfo)");
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
        	logger.debug("����findInvoiceInfo(InvoiceInfo), �������[" + pk + "]");
		}
        InvoiceInfo invoiceInfo = (InvoiceInfo) getSqlMapClientTemplate().queryForObject("InvoiceInfo_findInvoiceInfo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findInvoiceInfo(InvoiceInfo), ����[" + invoiceInfo + "]");
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
        	logger.debug("����listInvoiceInfo(InvoiceInfo), �������[" + invoiceInfo + "]");
		}
        List<InvoiceInfo> list = getSqlMapClientTemplate().queryForList("InvoiceInfo_listInvoiceInfo", invoiceInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listInvoiceInfo(InvoiceInfo), ����[" + list + "]");
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
        	logger.debug("����listInvoiceInfoCount(InvoiceInfo), �������[" + invoiceInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("InvoiceInfo_listInvoiceInfoCount", invoiceInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listInvoiceInfoCount(InvoiceInfo), ����[" + count + "]");
		}
        return count;
    }  
}
