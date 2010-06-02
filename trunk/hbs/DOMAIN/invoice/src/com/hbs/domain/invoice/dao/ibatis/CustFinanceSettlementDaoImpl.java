package com.hbs.domain.invoice.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.hbs.domain.invoice.dao.FinanceSettlementDao;
import com.hbs.domain.invoice.pojo.FinanceSettlement;


/**
 * FinanceSettlementDao接口实现类.
 * @author hbs
 *
 */
public class CustFinanceSettlementDaoImpl extends SqlMapClientDaoSupport implements FinanceSettlementDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustFinanceSettlementDaoImpl.class);

    
    
    /**
     * insert.
     * @param financeSettlement financeSettlement
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertFinanceSettlement(FinanceSettlement financeSettlement) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertFinanceSettlement(FinanceSettlement), 输入参数[" + financeSettlement + "]");
    	}
        
       
    	getSqlMapClientTemplate().insert("CustFinanceSettlement_insertFinanceSettlement", financeSettlement);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertFinanceSettlement(FinanceSettlement), 返回");
		}
    	
    }

    
    
    /**
     * update.
     * @param financeSettlement financeSettlement
     * @throws DataAccessException DataAccessException
     */
    public void updateFinanceSettlement(FinanceSettlement financeSettlement) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateFinanceSettlement(FinanceSettlement), 输入参数[" + financeSettlement + "]");
		}
    	getSqlMapClientTemplate().update("CustFinanceSettlement_updateFinanceSettlement", financeSettlement);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateFinanceSettlement(FinanceSettlement)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return financeSettlement
     * @throws DataAccessException DataAccessException
     */
    public FinanceSettlement findFinanceSettlement(FinanceSettlement financeSettlement) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findFinanceSettlement(FinanceSettlement), 输入参数[" + financeSettlement + "]");
		}
        FinanceSettlement financeSettle = (FinanceSettlement) getSqlMapClientTemplate().queryForObject("CustFinanceSettlement_findFinanceSettlement", financeSettlement);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findFinanceSettlement(FinanceSettlement), 返回[" + financeSettlement + "]");
		}
        return financeSettle;
    }
    
    /**
     * list.
     * @param financeSettlement financeSettlement
     * @return financeSettlement list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<FinanceSettlement> listFinanceSettlement(FinanceSettlement financeSettlement) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listFinanceSettlement(FinanceSettlement), 输入参数[" + financeSettlement + "]");
		}
        List<FinanceSettlement> list = getSqlMapClientTemplate().queryForList("CustFinanceSettlement_listFinanceSettlement", financeSettlement);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listFinanceSettlement(FinanceSettlement), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param financeSettlement financeSettlement
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listFinanceSettlementCount(FinanceSettlement financeSettlement) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listFinanceSettlementCount(FinanceSettlement), 输入参数[" + financeSettlement + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("CustFinanceSettlement_listFinanceSettlementCount", financeSettlement);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listFinanceSettlementCount(FinanceSettlement), 返回[" + count + "]");
		}
        return count;
    }



}
