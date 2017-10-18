package com.lol.common.cache.redis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lol.common.Constants;
import com.lol.common.util.SerializeUtils;

import redis.clients.jedis.Jedis;

/**
 * 使用4号redis库
 * 
 * @author cunxing
 *
 */
public class RedisCache implements Cache {

    private static Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private String id;

    private final int EXPIRE_TIME = 3600;// 3600秒 =1小时

    public RedisCache(String id) {
        this.id = id;
        logger.debug("MybatisRedisCache:id=" + id);
    }

    @Override
    public void clear() {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.flushDB();
        } catch(Exception e) {
            logger.error("Mybatis缓存清除失败.", e);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Object getObject(Object key) {
        // logger.debug("===getObject(Object key)===name:" + key.getClass().getName());
        // logger.debug("===getObject(Object key)===key:" + key.hashCode());
        Jedis jedis = null;
        byte[] cacheKey = null;
        Object value = null;
        try {
            jedis = getJedis();
            cacheKey = SerializeUtils.serialize(key.hashCode());
            if(jedis.exists(cacheKey)) {
                value = SerializeUtils.unserialize(jedis.get(cacheKey));
            }
        } catch(Exception e) {
            logger.error("Mybatis获取缓存对象失败.", e);
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    @Override
    public int getSize() {
        Jedis jedis = null;
        Long size = 0l;
        try {
            jedis = getJedis();
            size = jedis.dbSize();
        } catch(Exception e) {
            logger.error("Mybatis缓存长度获取失败.", e);
        } finally {
            jedis.close();
        }
        return size.intValue();
    }

    @Override
    public void putObject(Object key, Object value) {
        // logger.debug("===putObject(Object key, Object value)===name:" + key.getClass().getName());
        // logger.debug("===putObject(Object key, Object value)===key:" + key.hashCode());
        Jedis jedis = null;
        byte[] cacheKey = null;
        try {
            jedis = getJedis();
            cacheKey = SerializeUtils.serialize(key.hashCode());
            jedis.setex(cacheKey, EXPIRE_TIME, SerializeUtils.serialize(value));
        } catch(Exception e) {
            logger.error("Mybatis获取缓存对象失败.", e);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Object removeObject(Object key) {
        Jedis jedis = null;
        Object result = null;
        byte[] cacheKey = null;
        try {
            jedis = getJedis();
            cacheKey = SerializeUtils.serialize(key.hashCode());
            result = jedis.expire(cacheKey, 0);
        } catch(Exception e) {
            logger.error("Mybatis获取缓存对象失败.", e);
        } finally {
            jedis.close();
        }
        return result;
    }

    private Jedis getJedis() {
        return JedisClient.getJedis(Constants.REDIS_DBINDEX_MYBATIS);
    }
}
