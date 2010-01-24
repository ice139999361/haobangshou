package com.hbs.domain.waittask.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.waittask.pojo.WaitTaskConfigInfo;


/**
 * WaitTaskConfigInfoDao½Ó¿Ú.
 * @author sims autoCoder1.0
 *
 */
public interface WaitTaskConfigInfoDao {
   
    /**
     * list.

     * @return waitTaskConfigInfo list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	List listWaitTaskConfigInfo() throws DataAccessException ;
    
    /**
     * find.
     * @param id id
     * @return waitTaskConfigInfo
     * @throws DataAccessException DataAccessException
     */
    WaitTaskConfigInfo findWaitTaskConfigInfo(String id) throws DataAccessException ;
}
