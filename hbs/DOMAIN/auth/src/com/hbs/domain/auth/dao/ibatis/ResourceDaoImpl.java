package com.hbs.domain.auth.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.auth.pojo.Resource;
import com.hbs.domain.auth.dao.ResourceDao;

/**
 * ResourceDao�ӿ�ʵ����.
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
    		logger.debug("����insertResource(Resource), �������[" + resource + "]");
    	}
        
    	Integer id =(Integer)getSqlMapClientTemplate().insert("Resource_insertResource", resource);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertResource(Resource), ����[" + id + "]");
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
    		logger.debug("����deleteResource(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("Resource_deleteResource", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteResource(String)");
		}
    }
    
    /**
     * update.
     * @param resource resource
     * @throws DataAccessException DataAccessException
     */
    public void updateResource(Resource resource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateResource(Resource), �������[" + resource + "]");
		}
    	getSqlMapClientTemplate().update("Resource_updateResource", resource);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateResource(Resource)");
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
        	logger.debug("����findResource(Resource), �������[" + pk + "]");
		}
        Resource resource = (Resource) getSqlMapClientTemplate().queryForObject("Resource_findResource", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findResource(Resource), ����[" + resource + "]");
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
        	logger.debug("����listResource(Resource), �������[" + resource + "]");
		}
        List<Resource> list = getSqlMapClientTemplate().queryForList("Resource_listResource", resource);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listResource(Resource), ����[" + list + "]");
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
        	logger.debug("����listResourceCount(Resource), �������[" + resource + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("Resource_listResourceCount", resource);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listResourceCount(Resource), ����[" + count + "]");
		}
        return count;
    }  
}
