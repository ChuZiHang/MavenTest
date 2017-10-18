package com.lol.common.cache;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lol.common.Constants;
import com.lol.common.cache.redis.JedisClient;
import com.lol.common.util.SpringUtils;

import redis.clients.jedis.Jedis;

/**
 * 通用字段缓存
 * 
 * @author cunxing
 *
 */
public class FieldCache {

    private static Logger logger = LoggerFactory.getLogger(FieldCache.class);

    private JdbcTemplate jdbcTemplate;

    /**
     * 根据数据库名称设置对应JdbcTemplate
     * 
     * @param dbName
     */
    public FieldCache(String dbName) {
        if(Constants.DS0.equals(dbName)) {
            jdbcTemplate = SpringUtils.getBean("jdbcTemplateForDs0");
        } else if(Constants.DS1.equals(dbName)) {
            jdbcTemplate = SpringUtils.getBean("jdbcTemplateForDs1");
        }
    }

    /**
     * Jedis自动关闭，适用于单次调用。
     * 
     * @param table
     * @param field
     * @return
     */
    public String get(String table, String field) {
        String fieldValue = null;
        Jedis jedis = JedisClient.getJedis(Constants.REDIS_DBINDEX);
        try {
            fieldValue = jedis.hget(table, field);
            if(fieldValue == null) {
                fieldValue = getFieldValue(table, field);
                jedis.hset(table, field, fieldValue);
            }
            return fieldValue;
        } catch(Exception e) {
            logger.error("字段缓存获取失败table[{}]filed[{}] ", table, field);
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * Jedis自行维护，适合循环列表中的调用。
     * 
     * @param jedis
     * @param table
     * @param field
     * @return
     */
    public String get(Jedis jedis, String table, String field) {
        String fieldValue = null;
        try {
            fieldValue = jedis.hget(table, field);
            if(fieldValue == null) {
                fieldValue = getFieldValue(table, field);
                jedis.hset(table, field, fieldValue);
            }
            return fieldValue;
        } catch(Exception e) {
            logger.error("字段缓存获取失败table[{}]filed[{}] ", table, field);
        }
        return null;
    }

    /**
     * 获取单个字段值
     * 
     * @param table
     * @param field
     *        格式field1:value1:field2
     * @return value2
     */
    private String getFieldValue(String table, String field) {
        try {
            String[] fields = field.split(":");
            StringBuilder sql = new StringBuilder();
            sql.append("select ").append(fields[2]).append(" from ").append(table);
            sql.append(" where ").append(fields[0]).append(" = ").append(fields[1]);
            Map<String, Object> map = jdbcTemplate.queryForMap(sql.toString());
            if(map.get(fields[2]) != null) {
                return map.get(fields[2]).toString();
            }
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
