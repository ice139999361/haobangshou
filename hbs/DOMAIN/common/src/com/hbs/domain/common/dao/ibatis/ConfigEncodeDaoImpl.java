package com.hbs.domain.common.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.dao.ConfigEncodeDao;

/**
 * ConfigEncodeDao接口实现类.
 * @author yangzj
 *
 */
public class ConfigEncodeDaoImpl extends SqlMapClientDaoSupport implements ConfigEncodeDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(ConfigEncodeDaoImpl.class);

   
    
    /**
     * find.
     * @param id id
     * @return configEncode
     * @throws DataAccessException DataAccessException
     */
    public ConfigEncode findConfigEncode(ConfigEncode cEncode) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findConfigEncode(ConfigEncode), 输入参数[" + cEncode + "]");
		}
        ConfigEncode configEncode = (ConfigEncode) getSqlMapClientTemplate().queryForObject("ConfigEncode_findConfigEncode", cEncode);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findConfigEncode(ConfigEncode), 返回[" + configEncode + "]");
		}
        return configEncode;
    }
    
    /**
     * list.
     * @param configEncode configEncode
     * @return configEncode list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<ConfigEncode> listConfigEncode(ConfigEncode configEncode) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listConfigEncode(ConfigEncode), 输入参数[" + configEncode + "]");
		}
        List<ConfigEncode> list = getSqlMapClientTemplate().queryForList("ConfigEncode_listConfigEncode", configEncode);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listConfigEncode(ConfigEncode), 返回[" + list + "]");
		}
        return list;
    }  
    
   
}
