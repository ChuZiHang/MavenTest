package com.lol.common.shiro.cache;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import com.lol.common.Constants;
import com.lol.common.cache.redis.JedisClient;

import redis.clients.jedis.JedisPool;

/**
 * 自定义shiro缓存管理类
 * 
 * @author cunxing
 *
 */
public class RedisCacheManager extends AbstractCacheManager {

	private JedisPool jedisPool = JedisClient.getJedisPool();

	private int dbIndex = Constants.REDIS_DBINDEX_SHIRO;// Redis分区号

	public int getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}

	@Override
	protected Cache<Object, Object> createCache(String cacheName) throws CacheException {
		/*
		 * if (!cacheName.startsWith(keyPrefix)) { throw new
		 * IllegalArgumentException(
		 * "Cache argument cannot be null.Please use the name(" + keyPrefix +
		 * ") for a start"); }
		 */
		RedisCache<Object, Object> redisCache = new RedisCache<Object, Object>(cacheName, jedisPool, dbIndex);
		return redisCache;
	}

}
