package com.lol.common.weixin;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.lol.common.Constants;
import com.lol.common.util.HttpUtils;

public class TokenAPI {

    /**
     * 获取access_token
     * 
     * @param appid
     * @param secret
     * @return
     */
    public static String getToken(String appid, String secret) {

        HttpUtils httpUtils = new HttpUtils(6000);
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("grant_type", "client_credential");
        parameters.put("appid", appid);
        parameters.put("secret", secret);

        String result = httpUtils.get("https://api.weixin.qq.com/cgi-bin/token", "utf-8", parameters);
        String access_token = "";
        if(result != null && result.contains("expires_in")) {
            access_token = JSON.parseObject(result).getString("access_token");
        }
        return access_token;

    }

    public static void main(String[] args) {

        System.out.println(TokenAPI.getToken(Constants.AppID, Constants.AppSecret));
    }

}
