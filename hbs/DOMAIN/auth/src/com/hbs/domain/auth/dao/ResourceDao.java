package com.hbs.domain.auth.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.auth.pojo.Resource;


/**
 * ResourceDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface ResourceDao {
    /**
     * insert.
     * @param resource resource
     * @return id
     * @throws DataAccessException DataAccessException
     */
    Integer insertResource(Resource resource) throws DataAccessException ;

    /**
     * delete.
     * @param resource resource
     * @throws DataAccessException DataAccessException
     */
    void deleteResource(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param resource resource
     * @throws DataAccessException DataAccessException
     */
    void updateResource(Resource resource) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return resource
     * @throws DataAccessException DataAccessException
     */
    Resource findResource(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param resource resource
     * @return resource list
     * @throws DataAccessException DataAccessException
     */
    List<Resource> listResource(Resource resource) throws DataAccessException ;
    
    /**
     * listCount.
     * @param resource resource
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listResourceCount(Resource resource) throws DataAccessException ;
}
