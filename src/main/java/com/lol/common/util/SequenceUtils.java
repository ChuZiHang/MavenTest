package com.lol.common.util;

import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lol.common.plugin.IdWorker;

/**
 * 序列码工具类
 * 
 * @author cunxing
 *
 */
public class SequenceUtils {

    /**
     * 随机数位移长度
     */
    private static long randomShift = 8L;

    /**
     * 随机数范围
     */
    private static long randomMask = -1L ^ (-1L << randomShift);

    /**
     * 序列生成器
     */
    private static IdWorker idWorker = new IdWorker(1, 0);

    /**
     * jdbc模板
     */
    private static JdbcTemplate jdbcTemplateForDs0 = SpringUtils.getBean("jdbcTemplateForDs0");

    /**
     * 获取序列Id
     * 
     * @return
     */
    public static long nextId() {
        return idWorker.nextId();
    }

    /**
     * 获取随机码
     * 
     * @return
     */
    public static long getCode() {
        long id = idWorker.nextId();
        long num = RandomUtils.nextLong(0, randomMask);
        id = id << randomShift | num;
        return id;
    }

    /**
     * 获取随机码(关联ID)
     * 
     * @param id
     * @return
     */
    public static long getCode(long id) {
        long num = RandomUtils.nextLong(0, randomMask);
        id = id << randomShift | num;
        return id;
    }

    /**
     * 还原关联ID
     * 
     * @param code
     * @return
     */
    public static long restoreCode(long code) {
        return code >> randomShift;
    }

    /**
     * 获取序列ID（MySQL函数方式）
     * 
     * @param name
     * @return
     */
    public static long getSeq(String name) {
        Map<String, Object> map = jdbcTemplateForDs0.queryForMap("SELECT seq_nextid('" + name + "') as val FROM DUAL;");
        if(map != null && !map.isEmpty()) {
            return (long)map.get("val");
        }
        return -1;
    }
}
