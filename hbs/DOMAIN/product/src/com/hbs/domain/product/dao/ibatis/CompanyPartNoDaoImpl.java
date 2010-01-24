package com.hbs.domain.product.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.product.pojo.CompanyPartNo;
import com.hbs.domain.product.dao.CompanyPartNoDao;

/**
 * CompanyPartNoDao接口实现类.
 * @author hbs
 *
 */
public class CompanyPartNoDaoImpl extends SqlMapClientDaoSupport implements CompanyPartNoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CompanyPartNoDaoImpl.class);

    
    
    /**
     * insert.
     * @param companyPartNo companyPartNo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertCompanyPartNo(CompanyPartNo companyPartNo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertCompanyPartNo(CompanyPartNo), 输入参数[" + companyPartNo + "]");
    	}
        
     
        
    	getSqlMapClientTemplate().insert("CompanyPartNo_insertCompanyPartNo", companyPartNo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertCompanyPartNo(CompanyPartNo), 返回");
		}
    	
    }

    /**
     * delete.
     * @param companyPartNo companyPartNo
     * @throws DataAccessException DataAccessException
     */
    public void deleteCompanyPartNo(String partNo)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteCompanyPartNo(String), 输入参数[" + partNo + "]");
		}
        getSqlMapClientTemplate().update("CompanyPartNo_deleteCompanyPartNo", partNo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteCompanyPartNo(String)");
		}
    }
    
    /**
     * update.
     * @param companyPartNo companyPartNo
     * @throws DataAccessException DataAccessException
     */
    public void updateCompanyPartNo(CompanyPartNo companyPartNo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateCompanyPartNo(CompanyPartNo), 输入参数[" + companyPartNo + "]");
		}
    	getSqlMapClientTemplate().update("CompanyPartNo_updateCompanyPartNo", companyPartNo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateCompanyPartNo(CompanyPartNo)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return companyPartNo
     * @throws DataAccessException DataAccessException
     */
    public CompanyPartNo findCompanyPartNo(String partNo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findCompanyPartNo(CompanyPartNo), 输入参数[" + partNo + "]");
		}
        CompanyPartNo companyPartNo = (CompanyPartNo) getSqlMapClientTemplate().queryForObject("CompanyPartNo_findCompanyPartNo", partNo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findCompanyPartNo(CompanyPartNo), 返回[" + companyPartNo + "]");
		}
        return companyPartNo;
    }
    
    /**
     * list.
     * @param companyPartNo companyPartNo
     * @return companyPartNo list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<CompanyPartNo> listCompanyPartNo(CompanyPartNo companyPartNo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listCompanyPartNo(CompanyPartNo), 输入参数[" + companyPartNo + "]");
		}
        List<CompanyPartNo> list = getSqlMapClientTemplate().queryForList("CompanyPartNo_listCompanyPartNo", companyPartNo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listCompanyPartNo(CompanyPartNo), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param companyPartNo companyPartNo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listCompanyPartNoCount(CompanyPartNo companyPartNo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listCompanyPartNoCount(CompanyPartNo), 输入参数[" + companyPartNo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("CompanyPartNo_listCompanyPartNoCount", companyPartNo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listCompanyPartNoCount(CompanyPartNo), 返回[" + count + "]");
		}
        return count;
    }  
}
