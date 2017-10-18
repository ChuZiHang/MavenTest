package com.lol.common;

/**
 * 常量参数
 * 
 * @author cunxing
 *
 */
public interface Constants {

    String ENCODING = "UTF-8";

    /**
     * redis库 分配
     */
    int REDIS_DBINDEX_MYBATIS = 7;// 基础数据bean映射缓存（即mybatis的二级缓存）

    int REDIS_DBINDEX_SHIRO = 8;// 本项目中用到的除mybatis二级缓存的其他缓存
    
    int REDIS_DBINDEX = 10;// 本项目中用到的除mybatis二级缓存的其他缓存
    
    

    /**
     * 系统应用ID 用于区分权限配置信息
     */
    int SYS_APP_ID = 2;// 1分销系统，参考ds0库中的sys_app表

    int CUST_TYPE_HQ = 1;// 1总部

    int CUST_TYPE_BRANCHE = 2;// 分店

    String DS0 = "ds0";

    String DS1 = "ds1";
    
    
    String AppID = "wxb54eee2b10bc7309";

    String AppSecret = "2a28a431b272b5df76a460c3fdcb3756";
    
    
    
    
    /**
     * 暂时写成常量方便“更改”
     */
    String SMS_SPORT_PHONE = "13161147663";
    
    
    

    
}
