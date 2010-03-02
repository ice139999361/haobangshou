package com.hbs.domain.auth.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.auth.pojo.Resource;
import com.hbs.domain.auth.dao.ResourceDao;

/**
 * ResourceDao接口实现类.
 * @author hbs
 *
 */
public class ResourceDaoImpl extends SqlMapClientDaoSupport implements ResourceDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(ResourceDaoImpl.class);

    
    
    /**
     * insert.
     * @param resource resource
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public Integer insertResource(Resource resource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertResource(Resource), 输入参数[" + resource + "]");
    	}
        
    	Integer id =(Integer)getSqlMapClientTemplate().insert("Resource_insertResource", resource);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertResource(Resource), 返回[" + id + "]");
		}
    	return id;
    }

    /**
     * delete.
     * @param resource resource
     * @throws DataAccessException DataAccessException
     */
    public void deleteResource(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteResource(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("Resource_deleteResource", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteResource(String)");
		}
    }
    
    /**
     * update.
     * @param resource resource
     * @throws DataAccessException DataAccessException
     */
    public void updateResource(Resource resource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateResource(Resource), 输入参数[" + resource + "]");
		}
    	getSqlMapClientTemplate().update("Resource_updateResource", resource);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateResource(Resource)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return resource
     * @throws DataAccessException DataAccessException
     */
    public Resource findResource(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findResource(Resource), 输入参数[" + pk + "]");
		}
        Resource resource = (Resource) getSqlMapClientTemplate().queryForObject("Resource_findResource", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findResource(Resource), 返回[" + resource + "]");
		}
        return resource;
    }
    
    /**
     * list.
     * @param resource resource
     * @return resource list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<Resource> listResource(Resource resource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listResource(Resource), 输入参数[" + resource + "]");
		}
        List<Resource> list = getSqlMapClientTemplate().queryForList("Resource_listResource", resource);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listResource(Resource), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param resource resource
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listResourceCount(Resource resource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listResourceCount(Resource), 输入参数[" + resource + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("Resource_listResourceCount", resource);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listResourceCount(Resource), 返回[" + count + "]");
		}
        return count;
    }  
}
