package com.hbs.domain.auth.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.auth.pojo.Action;


/**
 * ActionDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface ActionDao {
    /**
     * insert.
     * @param action action
     * @return id
     * @throws DataAccessException DataAccessException
     */
    Integer insertAction(Action action) throws DataAccessException ;

    /**
     * delete.
     * @param action action
     * @throws DataAccessException DataAccessException
     */
    void deleteAction(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param action action
     * @throws DataAccessException DataAccessException
     */
    void updateAction(Action action) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return action
     * @throws DataAccessException DataAccessException
     */
    Action findAction(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param action action
     * @return action list
     * @throws DataAccessException DataAccessException
     */
    List<Action> listAction(Action action) throws DataAccessException ;
    
    /**
     * listCount.
     * @param action action
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listActionCount(Action action) throws DataAccessException ;
}
