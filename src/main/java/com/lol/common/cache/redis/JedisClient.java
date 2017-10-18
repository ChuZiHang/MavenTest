package com.lol.common.cache.redis;

import com.lol.common.Constants;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 管理redis类
 * 
 * @author cunxing
 *
 */
public class JedisClient {

    private static JedisPool pool = null;

    public static final int EXPIRE_TIME = 2 * 60 * 60;

    static {
        poolInit();
    }

    /**
     * 初始化线程池
     */
    private static synchronized void poolInit() {

        if(pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            // 设置jedis最多连接数
            config.setMaxTotal(1000);
            // 设置最大阻塞时间，毫秒数
            config.setMaxWaitMillis(15000);
            // 设置最多空闲连接个数
            config.setMaxIdle(100);

            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            config.setTestWhileIdle(true);

            // 创建连接池 ip port

           pool = new JedisPool(config, "10.8.8.61", 6379);
         //    pool = new JedisPool(config, "14.17.112.61", 6379);

//            pool = new JedisPool(config, "10.0.0.21", 6379);
        }
    }

    public static JedisPool getJedisPool() {
        return pool;
    }

    /**
     * 获取一个Jedis对象并选择指定DB
     * 
     * @param dbIndex
     *        默认0-15（参考具体redis服务配置）
     * @return
     */
    public static Jedis getJedis(int dbIndex) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.select(dbIndex);
        } catch(Exception e) {
            System.err.println("Redis,获取失败!\r\n" + e.getMessage());
        }
        return jedis;

    }

    public static Jedis getJedis(String host, int port) {
        return new Jedis(host, port);
    }

    public static void main(String[] args) {
        Jedis jedis = JedisClient.getJedis(Constants.REDIS_DBINDEX);
        jedis.flushDB();
        System.out.println(jedis.dbSize());
        jedis.close();
    }

}
