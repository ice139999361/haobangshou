package com.hbs.domain.auth.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.auth.pojo.RoleResource;


/**
 * RoleResourceDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface RoleResourceDao {
    /**
     * insert.
     * @param roleResource roleResource
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertRoleResource(RoleResource roleResource) throws DataAccessException ;

    /**
     * delete.
     * @param roleResource roleResource
     * @throws DataAccessException DataAccessException
     */
    void deleteRoleResource(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param roleResource roleResource
     * @throws DataAccessException DataAccessException
     */
    void updateRoleResource(RoleResource roleResource) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return roleResource
     * @throws DataAccessException DataAccessException
     */
    RoleResource findRoleResource(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param roleResource roleResource
     * @return roleResource list
     * @throws DataAccessException DataAccessException
     */
    List<RoleResource> listRoleResource(RoleResource roleResource) throws DataAccessException ;
    
    /**
     * listCount.
     * @param roleResource roleResource
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listRoleResourceCount(RoleResource roleResource) throws DataAccessException ;
}
