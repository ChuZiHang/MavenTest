package com.lol.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lol.common.Constants;
import com.lol.common.cache.redis.JedisClient;
import com.lol.common.model.ds0.InfoArea;
import com.lol.common.model.ds0.InfoAreaExample;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;
import com.lol.common.model.ds1.UserLogin;
import com.lol.common.model.ds1.UserLoginExample;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.InfoAreaService;
import com.lol.common.service.UserLoginService;
import com.lol.common.util.SpringUtils;

import redis.clients.jedis.Jedis;

/**
 * 字段缓存管理类
 * 
 * @author cunxing
 *
 */
public class FieldCacheManager {

    private static Logger logger = LoggerFactory.getLogger(FieldCacheManager.class);

    private static final int EXPIRE = 86400;// 24个小时

    private static final String REFRESH_KEY = "is_refresh_field_cache";// 是否刷新字段缓存标志

    private static Map<String, FieldCache> cacheMap = new HashMap<String, FieldCache>();
    // 初始化
    static {
        reloadCache();
    }

    /**
     * 加载全局缓存
     */
    public static void reloadCache() {
        Jedis jedis = JedisClient.getJedis(Constants.REDIS_DBINDEX);
        try {
            InfoAreaService infoAreaService = SpringUtils.getBean("infoAreaService");
            List<InfoArea> infoAreas = infoAreaService.selectByExample(new InfoAreaExample());
            updateInfoArea(jedis, infoAreas);
        } catch(Exception e) {
            logger.error("字段缓存初始化异常:" + e.getMessage(), e);
        } finally {
            jedis.close();
        }
    }

    /**
     * 根据当前用户加载相关的表字段缓存
     */
    public static void reloadFieldCache() {
        Jedis jedis = JedisClient.getJedis(Constants.REDIS_DBINDEX);
        try {
            if(jedis.exists(REFRESH_KEY)) {
                return;
            }
            jedis.setex(REFRESH_KEY, EXPIRE, "1");
            UserLoginService userLoginService = SpringUtils.getBean("userLoginService");
            CustInfoService custInfoService = SpringUtils.getBean("custInfoService");
            CustInfo currentCustInfo = userLoginService.getCurrentUserInfo().getCustInfo();
            if(currentCustInfo == null) {
                return;
            }
            int custId = currentCustInfo.getSystemId();
            int parentType = currentCustInfo.getParentType();
            if(parentType == Constants.CUST_TYPE_HQ) {
                CustInfoExample custInfoExample = new CustInfoExample();
                custInfoExample.createCriteria().andParentIdEqualTo(custId);
                List<CustInfo> custInfos = custInfoService.selectCustInfosByExample(custInfoExample);
                updateCustInfo(jedis, custInfos);
                List<Integer> custIds = new ArrayList<Integer>();
                for(CustInfo custInfo: custInfos) {
                    custIds.add(custInfo.getSystemId());
                }
                UserLoginExample userLoginExample = new UserLoginExample();
                userLoginExample.createCriteria().andCustIdIn(custIds);
                List<UserLogin> userLogins = userLoginService.selectByExample(userLoginExample);
                updateUserLogin(jedis, userLogins);
            } else {
                UserLoginExample userLoginExample = new UserLoginExample();
                userLoginExample.createCriteria().andCustIdEqualTo(custId);
                List<UserLogin> userLogins = userLoginService.selectByExample(userLoginExample);
                updateUserLogin(jedis, userLogins);
            }
        } catch(Exception e) {
            logger.error("字段缓存加载异常:" + e.getMessage(), e);
        } finally {
            jedis.close();
        }
    }

    /**
     * 更新user_login表
     * 
     * @param jedis
     * @param userLogins
     */
    private static void updateUserLogin(Jedis jedis, List<UserLogin> userLogins) {
        for(UserLogin userLogin: userLogins) {
            jedis.hset(TableConstans.USER_LOGIN, "id:" + userLogin.getId() + ":username", userLogin.getUsername());
            jedis.hset(TableConstans.USER_LOGIN, "id:" + userLogin.getId() + ":realname", userLogin.getRealname());
            jedis.hset(TableConstans.USER_LOGIN, "username:'" + userLogin.getUsername() + "':id",
                String.valueOf(userLogin.getId()));
            jedis.hset(TableConstans.USER_LOGIN, "username:'" + userLogin.getUsername() + "':realname", userLogin.getRealname());
        }
    }

    /**
     * 更新cust_info表
     * 
     * @param jedis
     * @param custInfo
     */
    private static void updateCustInfo(Jedis jedis, List<CustInfo> custInfos) {
        for(CustInfo custInfo: custInfos) {
            jedis.hset(TableConstans.CUST_INFO, "system_id:" + custInfo.getId() + ":cust_name", custInfo.getCustName());
        }
    }

    private static void updateInfoArea(Jedis jedis, List<InfoArea> infoAreas) {
        for(InfoArea infoArea: infoAreas) {
            jedis.hset(TableConstans.INFO_AREA, "tree_id:" + infoArea.getTreeId() + ":tree_name", infoArea.getTreeName());
        }
    }

    /**
     * 创建缓存
     * 
     * @param dbName
     * @return
     */
    public static FieldCache createCache(String dbName) {
        FieldCache fieldCache = cacheMap.get(dbName);
        if(fieldCache == null) {
            fieldCache = new FieldCache(dbName);
            cacheMap.put(dbName, fieldCache);
        }
        return fieldCache;
    }
}
