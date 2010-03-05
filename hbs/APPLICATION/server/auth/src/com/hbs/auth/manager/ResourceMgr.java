package com.hbs.auth.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.auth.dao.ResourceDao;
import com.hbs.domain.auth.pojo.Resource;

/**
 * 
 * @author tony.chen
 *
 */


public class ResourceMgr {
	
	private static final String RESOURCE_DAO_NAME = "resourceDao";
	private static final Logger logger = Logger.getLogger(ResourceMgr.class);
	
	public Integer insertResource(Resource resource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking insertResource(Resource resource): parameter=" + resource.toString());
    	}
		ResourceDao resourceDao = (ResourceDao)BeanLocator.getInstance().getBean(RESOURCE_DAO_NAME);
		Integer ret = resourceDao.insertResource(resource);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking insertResource(Resource resource)");
    	}
		return ret;
	}

    /**
     * delete.
     * @param resource resource
     * @throws DataAccessException DataAccessException
     */
	public void deleteResource(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking deleteResource(String id): parameter=" + id);
    	}
		ResourceDao resourceDao = (ResourceDao)BeanLocator.getInstance().getBean(RESOURCE_DAO_NAME);
		resourceDao.deleteResource(id);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking deleteResource(String id)");
    	}
	}
    
    /**
     * update.
     * @param resource resource
     * @throws DataAccessException DataAccessException
     */
	public void updateResource(Resource resource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking updateResource(Resource resource): parameter=" + resource.toString());
    	}
		ResourceDao resourceDao = (ResourceDao)BeanLocator.getInstance().getBean(RESOURCE_DAO_NAME);
		resourceDao.updateResource(resource);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking updateResource(Resource resource)");
    	}
	}

    /**
     * find.
     * @param id id
     * @return resource
     * @throws DataAccessException DataAccessException
     */
	public Resource findResource(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking findResource(String id): parameter=" + id);
    	}
		ResourceDao resourceDao = (ResourceDao)BeanLocator.getInstance().getBean(RESOURCE_DAO_NAME);
		Resource ret = resourceDao.findResource(id);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking findResource(String id)");
    	}
		return ret;
	}
    
    /**
     * list.
     * @param resource resource
     * @return resource list
     * @throws DataAccessException DataAccessException
     */
	public List<Resource> listResource(Resource resource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking listResource(Resource resource): parameter=" + resource.toString());
    	}
		ResourceDao resourceDao = (ResourceDao)BeanLocator.getInstance().getBean(RESOURCE_DAO_NAME);
		List<Resource> ret = resourceDao.listResource(resource);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking listResource(Resource resource)");
    	}
		return ret;
	}
    
    /**
     * listCount.
     * @param resource resource
     * @return list count
     * @throws DataAccessException DataAccessException
     */
	public Integer listResourceCount(Resource resource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking listResourceCount(Resource resource): parameter=" + resource.toString());
    	}
		ResourceDao resourceDao = (ResourceDao)BeanLocator.getInstance().getBean(RESOURCE_DAO_NAME);
		Integer ret = resourceDao.listResourceCount(resource);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking listResourceCount(Resource resource)");
    	}
		return ret;
	}
}
