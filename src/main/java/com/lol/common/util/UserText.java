package com.lol.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// 生成用户密码和“加密码”
public class UserText {

    public static void main(String[] args) {

        // String salt = SecretUtils.randomSalt();
        //
        // String pwd = SecretUtils.encryptPass("qidongjian", "qidongjian", salt);
        //
        // System.out.println(salt);
        // System.out.println(pwd);

        // ce6f7c9b5c4c28b6a6a4d6c94e31658a

        // String appId = "8";
        // String sign = "145199bf0d5b5a592b4339d4e4b6a4f7";
        // String timeStamp = "1471332654";
        // String token = "ce6f7c9b5c4c28b6a6a4d6c94e31658a";
        // String orderCode = "228998928916292899";
        // Map<String, String> params = new HashMap<String, String>();
        // params.put("orderCode", orderCode);
        // params.put("timeStamp", timeStamp);
        // params.put("appId", appId);
        // String NMsign = SignUtils.NMSign(params, token);
        //
        // String prestr = DigestUtils.md5Hex(NMsign.getBytes()) ;

        // int y,m,d,h,mi,s;
        // Calendar cal=Calendar.getInstance();
        // y=cal.get(Calendar.YEAR);
        // m=cal.get(Calendar.MONTH);
        // d=cal.get(Calendar.DATE);
        // h=cal.get(Calendar.HOUR_OF_DAY);
        // mi=cal.get(Calendar.MINUTE);
        // s=cal.get(Calendar.SECOND);
        // System.out.println("现在时刻是"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒");

        // System.out.println("2016-00-01 ee:ee:44".substring(0, 10));
        //
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // int y, m, d;
        // Calendar cal = Calendar.getInstance();
        // y = cal.get(Calendar.YEAR);
        // m = cal.get(Calendar.MONTH);
        // d = cal.get(Calendar.DATE);
        //
        // try {
        // System.out.println();
        // boolean b = format.parse(y + "-" + m + "-" + (d+3) + " 00:00:00").getTime()>new Date().getTime();
        // System.out.println(b);
        // } catch (ParseException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        //
        //
        //
        // Date date=new Date();//取时间
        // Calendar calendar = new GregorianCalendar();
        // calendar.setTime(date);
        // calendar.add(calendar.DATE,2);//把日期往后增加一天.整数往后推,负数往前移动
        // date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // String dateString = formatter.format(date);
        //
        // System.out.println(dateString);
        //
        // Date date1=new Date();//取时间
        // Calendar calendar1 = Calendar.getInstance();
        //
        // calendar1.setTime(date1);
        // calendar1.add(Calendar.DAY_OF_MONTH, +4);//+1今天的时间加一天
        // date1 = calendar1.getTime();
        //
        // dateString = formatter.format(date1);
        //
        // System.out.println(dateString);
        //
        // System.out.println(calendar1.get(Calendar.DAY_OF_WEEK));

        HttpUtils httpUtils = new HttpUtils();
        Map<String, String> parameters = new HashMap<String, String>();
        // appId=8&timeStamp=1471342313&orderId=2265776799092736&userLoginId=760314808217108480&sign=9ccc67d95a281446de94a7eaa8147549&token=ce6f7c9b5c4c28b6a6a4d6c94e31658a
//        parameters.put("appId", "8");
//        parameters.put("timeStamp", "1471342313");
//        parameters.put("orderId", "2265776799092736");
//        parameters.put("userLoginId", "760314808217108480");
//        parameters.put("sign", "9ccc67d95a281446de94a7eaa8147549");
//        parameters.put("token", "ce6f7c9b5c4c28b6a6a4d6c94e31658a");
//        String res =
//            httpUtils.getBodyAsString("http://text.lol.com:8084/api/order/update.action", "utf-8", parameters,"post");
        //appId=8&timeStamp=1471415565840&orderCode=/vZerXxEVU+/0Cdwh5wfCmTwty3jMMubLVGWXCq8WDQ=&sign=87d755906b2744639422b2b21f825682

        
        //timeStamp=1471484735&sign=c8b8763cc345b863c87963c936b6bdb1&orderCode=0dOEmvQSWFMzihR99putET2AlXaMzOtbOtL2nLnlV1o%3D 
        
        
//        parameters.put("appId", "8");
//      parameters.put("timeStamp", "1471484735");
//      parameters.put("orderCode", "0dOEmvQSWFMzihR99putET2AlXaMzOtbOtL2nLnlV1o%3D");
//     // parameters.put("userLoginId", "760314808217108480");
//      parameters.put("sign", "c8b8763cc345b863c87963c936b6bdb1");
//   //   parameters.put("token", "ce6f7c9b5c4c28b6a6a4d6c94e31658a");
//        
//        
//        String res =
//              httpUtils.getBodyAsString("http://feel.dong24.com/api/order/detail.action", "utf-8", parameters,"get");
        
//      String res;
//    try {
//        res = URLEncoder.encode("0dOEmvQSWFMzihR99putET2AlXaMzOtbOtL2nLnlV1o%3D", "utf-8");
//        System.out.println(res);
//        
//        res = URLEncoder.encode("0dOEmvQSWFMzihR99putET2AlXaMzOtbOtL2nLnlV1o=", "utf-8");
//        System.out.println(res);
//        res = URLDecoder.decode("MqLRzxN4WIFuLXFsilXnayNXtL%2FlWlsZjPnog%2BC289E%3D", "utf-8");
//        System.out.println(res);
//        AES aes = new AES(AES.AESKEY, AES.AesPublicKey);
//        System.out.println(aes.decrypt(res));
//    } catch(Exception e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//    }
        
        
        int [] num = new int [12];
        num[4]=12;
        for(int i = 0; i < num.length; i++) {
            System.out.println(num[i]);
        }
        
        //System.out.println(num);
      // run();
    }
    
    
    
    public static void run(){
        
     List<String> list = new ArrayList<String>();
     list.add("34567");
     list.add("12345");
     list.add("23456");
    
     list.add("45678");
     
     Iterator s =list.iterator();
     while(s.hasNext()) {
        System.out.println(s.next().toString());
    }
        
    }

}