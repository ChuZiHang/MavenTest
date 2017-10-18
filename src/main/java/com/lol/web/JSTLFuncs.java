package com.lol.web;

import java.util.List;

import com.lol.common.Constants;
import com.lol.common.bean.CurrentUserInfo;
import com.lol.common.bean.EasyUITree;
import com.lol.common.cache.FieldCache;
import com.lol.common.cache.FieldCacheManager;
import com.lol.common.cache.TableConstans;
import com.lol.common.cache.redis.JedisClient;
import com.lol.common.model.ds0.SysPermission;
import com.lol.common.model.ds0.SysPermissionExample;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.UserLogin;
import com.lol.common.service.SysPermissionService;
import com.lol.common.service.UserLoginService;
import com.lol.common.util.EasyUIUtils;
import com.lol.common.util.SerializeUtils;
import com.lol.common.util.SpringUtils;

import redis.clients.jedis.Jedis;

/**
 * 可供web端之间调用的方法(funcs.tld)
 * 
 * @author cunxing
 *
 */
public class JSTLFuncs {

    private static final String SYS_MENUS = "sys_menus_";

    private static final int SYS_MENUS_EXPIRE = 86400;// 24个小时

    /**
     * 获取当前系统菜单
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<EasyUITree> getMenus() {
        List<EasyUITree> menus = null;
        Jedis jedis = JedisClient.getJedis(Constants.REDIS_DBINDEX);
        try {
            SysPermissionService sysPermissionService = SpringUtils.getBean("sysPermissionService");
            UserLoginService userLoginService = SpringUtils.getBean("userLoginService");
            CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
            if(custInfo == null) {
                return null;
            }
            byte[] key = null;
            int parentType = custInfo.getParentType();
            if(parentType == Constants.CUST_TYPE_HQ) {
                key = SerializeUtils.serialize(SYS_MENUS + Constants.CUST_TYPE_HQ);
            } else {
                key = SerializeUtils.serialize(SYS_MENUS + Constants.CUST_TYPE_BRANCHE);
            }
            byte[] value = jedis.get(key);
            if(value != null) {
                menus = (List<EasyUITree>)SerializeUtils.unserialize(value);
            } else {
                SysPermissionExample example = new SysPermissionExample();
                example.createCriteria().andAppIdEqualTo(Constants.SYS_APP_ID).andStatusEqualTo(0)
                    .andTypeLike("%" + parentType + "%").andIsMenuEqualTo(1);
                example.setOrderByClause("priority asc");
                List<SysPermission> sysPermissions = sysPermissionService.selectByExample(example);
                menus = EasyUIUtils.buildTree(sysPermissions, "0");
                jedis.setex(key, SYS_MENUS_EXPIRE, SerializeUtils.serialize(menus));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return menus;
    }

    /**
     * 获取当前用户关联信息
     * 
     * @return
     */
    public static CurrentUserInfo getCurrentUserInfo() {
        UserLoginService userLoginService = SpringUtils.getBean("userLoginService");
        return userLoginService.getCurrentUserInfo();
    }

    /**
     * 获取当前用户UserLogin
     * 
     * @return
     */
    public static UserLogin getUserLogin() {
        return getCurrentUserInfo().getUserLogin();
    }

    /**
     * 获取当前用户CustInfo
     * 
     * @return
     */
    public static CustInfo getCustInfo() {
        return getCurrentUserInfo().getCustInfo();
    }

    /**
     * 获取ds0表字段缓存
     * 
     * @param table
     *        表名称
     * @param field
     *        条件字段名:条件字段值:结果字段名
     * @return 结果字段值
     */
    public static String ds0FieldValue(String table, String field) {
        FieldCache fieldCache = FieldCacheManager.createCache(Constants.DS0);
        return fieldCache.get(table, field);
    }

    /**
     * 获取ds0表字段缓存
     * 
     * @param jedis
     * @param table
     *        表名称
     * @param field
     *        条件字段名:条件字段值:结果字段名
     * @return 结果字段值
     */
    public static String ds0MultiFieldValue(Jedis jedis, String table, String field) {
        FieldCache fieldCache = FieldCacheManager.createCache(Constants.DS0);
        return fieldCache.get(jedis, table, field);
    }

    /**
     * 获取ds1表字段缓存
     * 
     * @param table
     *        表名称
     * @param field
     *        条件字段名:条件字段值:结果字段名
     * @return 结果字段值
     */
    public static String ds1FieldValue(String table, String field) {
        FieldCache fieldCache = FieldCacheManager.createCache(Constants.DS1);
        return fieldCache.get(table, field);
    }

    /**
     * 获取ds1表字段缓存
     * 
     * @param jedis
     * @param table
     *        表名称
     * @param field
     *        条件字段名:条件字段值:结果字段名
     * @return 结果字段值
     */
    public static String ds1MultiFieldValue(Jedis jedis, String table, String field) {
        FieldCache fieldCache = FieldCacheManager.createCache(Constants.DS1);
        return fieldCache.get(jedis, table, field);
    }

    /**
     * 根据用户id获取用户名
     * 
     * @param id
     * @return
     */
    public static String getUsernameById(Long id) {
        return ds1FieldValue(TableConstans.USER_LOGIN, "id:" + id + ":username");
    }

    /**
     * 根据用户id获取用户姓名
     * 
     * @param id
     * @return
     */
    public static String getRealnameById(Long id) {
        return ds1FieldValue(TableConstans.USER_LOGIN, "id:" + id + ":realname");
    }

    /**
     * 根据用户名获取用户姓名
     * 
     * @param username
     * @return
     */
    public static String getRealnameByUsername(String username) {
        return ds1FieldValue(TableConstans.USER_LOGIN, "username:" + username + ":realname");
    }

    /**
     * 根据Id获取分店名称
     * 
     * @param id
     * @return
     */
    public static String getCoachNameById(Long id) {
        return ds1FieldValue(TableConstans.COACH_INFO, "id:" + id + ":name");
    }
    
    /**
     * 根据Id获取分店名称
     * 
     * @param id
     * @return
     */
    public static String getCoachImgById(Long id) {
        return ds1FieldValue(TableConstans.COACH_INFO, "id:" + id + ":sign_img");
    }
    

    /**
     * 根据Id获取产品名称
     * 
     * @param id
     * @return
     */
    public static String getProductNameById(Long id) {
        return ds1FieldValue(TableConstans.PRODUCT_INFO, "id:" + id + ":card_name");
    }

    /**
     * 根据custId获取用户姓名
     * 
     * @param username
     * @return
     */
    public static String getCustNameByCustId(String custId) {
        return ds1FieldValue(TableConstans.CUST_INFO, "system_id:'" + custId + "':cust_name");
    }

    /**
     * 根据custId字符串获取用户姓名
     * 
     * @param username
     * @return
     */

    public static String getCustNameByCustIds(String custId) {
        String value = "";
        if(custId.length() > 0) {
            if(custId.contains(",")) {
                String[] split = custId.split(",");
                Jedis jedis = JedisClient.getJedis(Constants.REDIS_DBINDEX);
                for(int i = 0; i < split.length; i++) {
                    String name = ds1MultiFieldValue(jedis, TableConstans.CUST_INFO, "system_id:'" + split[i] + "':cust_name");
                    if(i == split.length - 1) {
                        value += name;
                    } else {
                        value = name + ",";
                    }
                }
                jedis.close();
            } else {
                return ds1FieldValue(TableConstans.CUST_INFO, "system_id:'" + custId + "':cust_name");
            }
        } else {
            return value;
        }
        return value;
    }
    /**
     * 根据productId字符串获取教练姓名
     * 
     * @param username
     * @return
     */

    public static String getCoachNameByProductId(String productId) {
    	String coachId = ds1FieldValue(TableConstans.PRODUCT_INFO, "id:" + productId + ":coach_id");
        String value = "";
        if(coachId.length() > 0) {
           if(coachId.contains(",")) {
                String[] split = coachId.split(",");
                Jedis jedis = JedisClient.getJedis(Constants.REDIS_DBINDEX);
                for(int i = 0; i < split.length; i++) {
                    String name = ds1MultiFieldValue(jedis, TableConstans.COACH_INFO, "id:'" + split[i] + "':name");
                    if(i == split.length - 1) {
                        value += name;
                    } else {
                        value = name + ",";
                    }
                }
                jedis.close();
                
            } else {
                return ds1FieldValue(TableConstans.COACH_INFO, "id:'" + coachId + "':name");
            }
        } else {
            return value;
       }
          return value;
    }
    /**
     * 根据productId字符串获取教练姓名
     * 
     * @param username
     * @return
     */

    public static String getCourseNameByProductId(String productId) {
    	String courseId = ds1FieldValue(TableConstans.COURSE_PRICE, "id:" + productId + ":course_id");
        String value = "";
        if(courseId != null && !"".equals(courseId.trim())) {
        	String className = ds1FieldValue(TableConstans.COURSE_INFO, "id:" + courseId + ":class_name");
        	value=className;
        } else {
            return value;
        }
        return value;
    }
    /**
     * 根据productId获取产品名
     * 
     * @param username
     * @return
     */
    public static String getProductNameByProductId(String productId) {
        return ds1FieldValue(TableConstans.PRODUCT_INFO, "id:'" + productId + "':card_name");
    }

    /**
     * 根据productId获取产品名
     * 
     * @param username
     * @return
     */
    public static String getProductImgByProductId(String productId) {
        return ds1FieldValue(TableConstans.PRODUCT_INFO, "id:'" + productId + "':sign_img");
    }

    /**
     * 根据courseId获取课程名字
     * 
     * @param
     * @return
     */
    public static String getCourseNameByCourseId(String courseId) {
        return ds1FieldValue(TableConstans.COURSE_INFO, "id:'" + courseId + "':class_name");
    }

    /**
     * 根据courseId获取img
     * 
     * @param
     * @return
     */
    public static String getCourseImgByCourseId(String courseId) {
        return ds1FieldValue(TableConstans.COURSE_INFO, "id:'" + courseId + "':class_img");
    }

    /**
     * 根据memberId获取Logo
     * 
     * @param
     * @return
     */
    public static String getmemberImgBymemberId(String memberId) {
        return ds1FieldValue(TableConstans.MEMBER_INFO, "member_id:'" + memberId + "':member_logo");
    }

    /**
     * 根据memberId获取姓名
     * 
     * @param
     * @return
     */
    public static String getmemberNameBymemberId(String memberId) {
        return ds1FieldValue(TableConstans.MEMBER_INFO, "member_id:'" + memberId + "':member_name");
    }

    /**
     * 根据memberId获取姓名
     * 
     * @param
     * @return
     */
    public static String getCourseIdByCoursePriceId(String priceId) {
        return ds1FieldValue(TableConstans.COURSE_PRICE, "id:'" + priceId + "':course_id");
    }

}
