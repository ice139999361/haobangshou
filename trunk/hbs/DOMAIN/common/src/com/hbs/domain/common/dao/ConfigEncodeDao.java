package com.hbs.domain.common.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.common.pojo.ConfigEncode;


/**
 * ConfigEncodeDao½Ó¿Ú.
 * @author yangzj
 *
 */
public interface ConfigEncodeDao {
  
   
    /**
     * find.
     * @param id id
     * @return configEncode
     * @throws DataAccessException DataAccessException
     */
    ConfigEncode findConfigEncode(ConfigEncode configEncode) throws DataAccessException ;
    
    /**
     * list.
     * @param configEncode configEncode
     * @return configEncode list
     * @throws DataAccessException DataAccessException
     */
    List<ConfigEncode> listConfigEncode(ConfigEncode configEncode) throws DataAccessException ;
    
    
}
