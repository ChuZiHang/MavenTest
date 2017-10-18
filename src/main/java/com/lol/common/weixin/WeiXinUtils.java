package com.lol.common.weixin;

import java.util.Date;

import com.lol.common.Constants;
import com.lol.common.cache.redis.JedisClient;
import com.lol.common.model.ds1.WeixinToken;
import com.lol.common.service.WeixinTokenService;
import com.lol.common.util.SpringUtils;

import redis.clients.jedis.Jedis;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.token.Token;

public class WeiXinUtils {

    // 获取token
    public static String getToken() {
        Jedis jedis = JedisClient.getJedis(Constants.REDIS_DBINDEX);
        String access_token = jedis.get("access_token");
        jedis.close();
        if(access_token == null || access_token.length() <= 0) {
            access_token = createToken();
        }

        return access_token;
    }

    // 从微信服务器获取一次token
    public static String createToken() {
        // 获取access_token
        Token token = TokenAPI.token(Constants.AppID, Constants.AppSecret);
        String access_token = token.getAccess_token();

        // 存入数据库
        WeixinToken record = new WeixinToken();
        record.setAppId(Constants.AppID);
        record.setAccessToken(access_token);
        record.setCreateDate(new Date());
        WeixinTokenService weixinTokenService = SpringUtils.getBean("weixinTokenService");
        weixinTokenService.insertSelective(record);

        // 存入缓存
        Jedis jediss = JedisClient.getJedis(Constants.REDIS_DBINDEX);
        jediss.setex("access_token", 7200, access_token);
        jediss.close();

        return access_token;
    }

}
