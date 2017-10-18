package com.lol.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.lol.common.bean.BaseJson;
import com.lol.common.model.ds1.SmsAuthCode;
import com.lol.common.model.ds1.SmsAuthCodeExample;
import com.lol.common.service.SmsAuthCodeService;

public class SMSUtils {

    private static SmsAuthCodeService smsAuthCodeService = null;

    private static String appId = "8";

    private static String appSecret = "65BAEE55DEFEBA8EE8BC56EA9DA5D646";

    /**
     * 发送普通短信
     * 
     * @param mobile
     * @param content
     * @return BaseJson
     * @throws Exception
     */
    public static BaseJson sendMsg(String mobile, String content) {
        BaseJson baseJson = new BaseJson();
        String result = sendSMS(mobile, content);
        if("00".equals(result)) {
            baseJson.setStatus(1);
            baseJson.setMsg("发送成功");
        } else {
            baseJson.setStatus(0);
            baseJson.setMsg("发送失败");
        }
        return baseJson;
    }

    /**
     * 发送验证码短信
     * 
     * @param mobile
     * @param content
     * @return
     * @throws Exception
     */
    public static BaseJson sendAuthCode(String mobile) {
        BaseJson baseJson = new BaseJson();
        if(mobile.equals("")) {
            baseJson.setMsg("手机号不能为空，请填写验证码！");
            baseJson.setStatus(0);
            return baseJson;
        } else if(!mobile.matches("^1[3|4|5|7|8][0-9]{9}$")) {
            baseJson.setMsg("手机号码格式不正确，请填写正确的手机号码！");
            baseJson.setStatus(0);
            return baseJson;
        }
        String authCode = authCode();
        System.out.println(authCode);
        StringBuilder msg = new StringBuilder();
        msg.append("短信验证码：");
        msg.append(authCode);
        msg.append(",请您尽快登陆，切勿泄露或转发他人");

        String result = sendSMS(mobile, msg.toString());
        if("00".equals(result)) {
            baseJson.setStatus(1);
            baseJson.setMsg("发送成功");
            smsAuthCodeService = SpringUtils.getBean("smsAuthCodeService");
            SmsAuthCode smsAuthCode = new SmsAuthCode();
            smsAuthCode.setCustId(Long.parseLong(appId));
            smsAuthCode.setAuthCode(authCode);
            smsAuthCode.setMobile(mobile);
            smsAuthCode.setMsg(msg.toString());
            smsAuthCode.setSendTime(new Date());
            smsAuthCode.setProviderType(1);
            smsAuthCode.setSendStatus("1");
            smsAuthCodeService.insertSelective(smsAuthCode);
        } else {
            baseJson.setStatus(0);
            baseJson.setMsg("发送失败");
        }
        return baseJson;
    }

    /**
     * 校验验证码的有效性
     * 
     * @param mobile
     * @param content
     * @return {"status":"1","msg":"验证成功"}
     */
    public static BaseJson checkCode(String mobile, String code) {
        BaseJson baseJson = new BaseJson();
        if(mobile.equals("")) {
            baseJson.setMsg("手机号不能为空，请填写验证码！");
            baseJson.setStatus(0);
            return baseJson;
        } else if(!mobile.matches("^1[3|4|5|7|8][0-9]{9}$")) {
            baseJson.setMsg("手机号码格式不正确，请填写正确的手机号码！");
            baseJson.setStatus(0);
            return baseJson;
        } else if("".equals(code)) {
            baseJson.setMsg("验证码不能为空");
            baseJson.setStatus(0);
            return baseJson;
        }
        SmsAuthCodeExample example = new SmsAuthCodeExample();
        example.createCriteria().andMobileEqualTo(mobile)
            .andSendTimeGreaterThan(new Date(System.currentTimeMillis() - 30 * 60 * 1000));
        example.setOrderByClause(" id desc");
        List<SmsAuthCode> tAuthSmsList = smsAuthCodeService.selectByExample(example);
        if(tAuthSmsList != null && tAuthSmsList.size() > 0) {
            SmsAuthCode authSms = tAuthSmsList.get(0);
            if(code.equals(authSms.getAuthCode())) {
                baseJson.setStatus(1);
                baseJson.setMsg("验证成功");
            } else {
                baseJson.setStatus(0);
                baseJson.setMsg("验证码错误");
            }
        } else {
            baseJson.setStatus(0);
            baseJson.setMsg("验证码错误");
        }
        return baseJson;
    }

    /**
     * 调用短信接口
     * 
     * @param mobile
     * @param message
     * @return
     */
    private static String sendSMS(String mobile, String message) {
        String nonce = UUID.randomUUID().toString();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String sign = null;
        String result = null;
        DSHttpClient httpClient = new DSHttpClient();
        Map<String, String> parameters = new HashMap<String, String>();
        try {
            parameters.put("appId", appId);
            parameters.put("nonce", nonce);
            parameters.put("timestamp", timestamp);
            sign = EncryptUtils.getSHA1(appSecret, timestamp, nonce, appId);
            parameters.put("sign", sign);
            parameters.put("mobile", mobile);
            parameters.put("message", message);
            result = httpClient.post("http://admin.dong24.com/ajax/qidongjian/send.action", parameters);
            JSONObject json = JSONObject.parseObject(result);
            String status = json.getString("status");
            if("1".equals(status)) {
                result = "00";
            } else {
                result = "01";
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 验证码生成
     * 
     * @return string
     */
    private static String authCode() {
        String authCode = "";
        String key = "123456789";
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 4; i++) {
            sb.append(key.charAt((int)(Math.random() * key.length())));
        }
        authCode = sb.toString();
        return authCode;
    }

    public static void main(String[] args) {
        // 短信验证码：XXXX。请您尽快登陆，切勿泄露或转发他人。
        /*
         * BaseJson sendMsg = SMSUtils.sendAuthCode("15527314163"); System.out.println(sendMsg.getData());
         */
        // BaseJson msg = SMSUtils.checkCode("15527314163","5121");
        // System.out.println(msg.getStatus()); 4221
        try {
            String sendSMS = SMSUtils.sendSMS("15527314163", "测试一下下");
           // BaseJson sendAuthCode = SMSUtils.sendAuthCode("15527314163");
          //  BaseJson checkCode = SMSUtils.checkCode("15527314163", "8272");
          //  System.out.println(checkCode.getStatus());
        } catch(Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
