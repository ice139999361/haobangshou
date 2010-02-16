package com.hbs.domain.common.dao.baseinfo;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.common.pojo.baseinfo.InvoiceInfo;


/**
 * InvoiceInfoDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface InvoiceInfoDao {
    /**
     * insert.
     * @param invoiceInfo invoiceInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    Integer insertInvoiceInfo(InvoiceInfo invoiceInfo) throws DataAccessException ;

    /**
     * delete.
     * @param invoiceInfo invoiceInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteInvoiceInfo(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param invoiceInfo invoiceInfo
     * @throws DataAccessException DataAccessException
     */
    void updateInvoiceInfo(InvoiceInfo invoiceInfo) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return invoiceInfo
     * @throws DataAccessException DataAccessException
     */
    InvoiceInfo findInvoiceInfo(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param invoiceInfo invoiceInfo
     * @return invoiceInfo list
     * @throws DataAccessException DataAccessException
     */
    List<InvoiceInfo> listInvoiceInfo(InvoiceInfo invoiceInfo) throws DataAccessException ;
    
    /**
     * listCount.
     * @param invoiceInfo invoiceInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listInvoiceInfoCount(InvoiceInfo invoiceInfo) throws DataAccessException ;
}
