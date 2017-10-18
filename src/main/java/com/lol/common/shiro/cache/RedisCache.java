package com.lol.common.shiro.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lol.common.util.SerializeUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;

/**
 * 自定义shiro缓存类
 * 
 * @author cunxing
 *
 * @param <K>
 * @param <V>
 */
public class RedisCache<K, V> implements Cache<K, V> {

	private static Logger logger = LoggerFactory.getLogger(RedisCache.class);

	private JedisPool jedisPool;

	private int dbIndex;

	private String cacheName = "shiro_cache";// 默认缓存名称

	public RedisCache(String cacheName, JedisPool jedisPool, int dbIndex) {
		this.jedisPool = jedisPool;
		this.cacheName = cacheName;
		this.dbIndex = dbIndex;
		//logger.debug("^^^^^^cacheName^^^^^^:" + cacheName);
	}

	@Override
	public void clear() {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			Set<byte[]> fieldSet = jedis.hkeys(getHKey());
			if (!CollectionUtils.isEmpty(fieldSet)) {
				byte[][] fields = new byte[fieldSet.size()][];
				int i = 0;
				for (byte[] field : fieldSet) {
					fields[i++] = field;
				}
				jedis.hdel(getHKey(), fields);
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		} finally {
			jedis.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
//		logger.debug("=====get====key:" + key.getClass().getName());
		Jedis jedis = null;
		V value = null;
		try {
			jedis = getJedis();
			byte[] fieldValue = jedis.hget(getHKey(), getHField(key));
			if (fieldValue != null) {
				value = (V) SerializeUtils.unserialize(fieldValue);
			}
			return value;
		} catch (Throwable t) {
			throw new CacheException(t);
		} finally {
			jedis.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			Set<byte[]> fieldSet = jedis.hkeys(getHKey());
			if (CollectionUtils.isEmpty(fieldSet)) {
				return Collections.emptySet();
			} else {
				Set<K> keys = new HashSet<K>();
				for (byte[] field : fieldSet) {
					keys.add((K) SerializeUtils.unserialize(field));
				}
				return keys;
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		} finally {
			jedis.close();
		}
	}

	@Override
	public V put(K key, V value) {
		Jedis jedis = null;
//		logger.debug("=====put====key:" + key.getClass().getName());
//		logger.debug("=====put====value:" + value.getClass().getName());
		try {
			jedis = getJedis();
			V previous = get(key);
			jedis.hset(getHKey(), getHField(key), SerializeUtils.serialize(value));
			return previous;
		} catch (Throwable t) {
			throw new CacheException(t);
		} finally {
			jedis.close();
		}
	}

	@Override
	public V remove(K key) throws CacheException {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			V previous = get(key);
			jedis.hdel(getHKey(), getHField(key));
			return previous;
		} catch (Throwable t) {
			throw new CacheException(t);
		} finally {
			jedis.close();
		}
	}

	@Override
	public int size() {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			Set<byte[]> fieldSet = jedis.hkeys(getHKey());
			return fieldSet.size();
		} catch (Throwable t) {
			throw new CacheException(t);
		} finally {
			jedis.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<V> values() {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			List<byte[]> fieldValues = jedis.hvals(getHKey());
			if (!CollectionUtils.isEmpty(fieldValues)) {
				List<V> values = new ArrayList<V>(fieldValues.size());
				for (byte[] value : fieldValues) {
					values.add((V) SerializeUtils.unserialize(value));
				}
				return Collections.unmodifiableList(values);
			} else {
				return Collections.emptyList();
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		} finally {
			jedis.close();
		}
	}

	private byte[] getHField(K key) {
		return SerializeUtils.serialize(key);
	}

	private byte[] getHKey() {
		return SafeEncoder.encode(cacheName);
	}

	public Jedis getJedis() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.select(dbIndex);
		} catch (Throwable t) {
			logger.debug("Redis,获取失败!\r\n" + t.getMessage());
			throw new CacheException(t);
		}
		return jedis;

	}

}
