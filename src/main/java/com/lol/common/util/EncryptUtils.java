package com.lol.common.util;

import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 签名工具类
 * 
 * @author cunxing
 *
 */
public class EncryptUtils {

    /**
     * 动网API签名规则
     * 
     * @param token
     * @param timestamp
     * @param nonce
     * @param encrypt
     * @return
     * @throws Exception
     */
    public static String getSHA1(String appId, String appSecret, String timestamp, String nonce) throws Exception {
        String[] array = new String[]{appId, appSecret, timestamp, nonce};
        StringBuffer buffer = new StringBuffer();
        // 字符串排序
        Arrays.sort(array);
        for(int i = 0; i < 4; i++) {
            buffer.append(array[i]);
        }
        String result = buffer.toString();
        return DigestUtils.sha1Hex(result);
    }

    /**
     * 生成AppSecret
     * 
     * @param appId
     * @return
     */
    public static String generateAppSecret(String appId) {
        String timestamp = System.currentTimeMillis() + "";
        String nonce = UUID.randomUUID().toString();
        return DigestUtils.md5Hex(appId + timestamp + nonce).toUpperCase();
    }

    public static void main(String[] args) {
        try {
            String str = "city_code=510100&ind_id=1&page_number=1&page_size=10&partner_id=20160006&timestamp=2016-04-26 18:36:5991063F5FF1D452EAF396533356E353BE";

            System.out.println(DigestUtils.md5Hex(str).toUpperCase());
//            System.out.println(getSHA1("247640", "8D36F9E70356360470A32EF16226A165", "1457605781", "481770821"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
