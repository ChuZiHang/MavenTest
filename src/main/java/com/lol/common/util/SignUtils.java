package com.lol.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by zhuwei on 16/8/2.
 */
public class SignUtils {

    public static String NMSign(Map<String, String> params, String key) {
        Map<String, String> sParaNew = paraFilter(params);// 过滤token
        String prestr = createLinkString(sParaNew); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        prestr = DigestUtils.md5Hex(prestr.getBytes()) + key;
        return DigestUtils.md5Hex(prestr.getBytes());
    }

    /**
     * 除去数组中的空值和签名参数
     * 
     * @param sArray
     *        签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if(sArray == null || sArray.size() <= 0) {
            return result;
        }

        for(String key: sArray.keySet()) {
            String value = String.valueOf(sArray.get(key));
            if(key.equalsIgnoreCase("token") || key.equalsIgnoreCase("token")) {
                continue;
            }
//            result.put(key, value.replaceAll("/", "%2F"));
            result.put(key, value);
        }

        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * 
     * @param params
     *        需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for(int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            prestr = prestr + key + "=" + value;
            // if(i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
            // prestr = prestr + key + "=" + value;
            // } else {
            // prestr = prestr + key + "=" + value + "&";
            // }
        }
       // System.out.println("-sign-" + prestr);
        return prestr;
    }

    public static void main(String[] args) {
       // String prestr = "appId=301appName=lolbasicInfo={\"beginTime\":\"2016-08-15 11:20:28\",\"businessPhone\":[\"4006625170\"],\"categoryA\":\"320\",\"categoryB\":\"952\",\"consultingPhone\":[\"4006675170\"],\"costPrice\":200,\"currentPrice\":300,\"endTime\":\"2016-08-15 11:20:28\",\"fullSales\":400,\"groupDownLimit\":1,\"groupUpLimit\":9000000,\"individualDownLimit\":1,\"individualUpLimit\":10000,\"isAllCountry\":\"2\",\"isHidden\":2,\"largeImageURL\":\"http://www.lol.com/image/venuesub/3429/1110271532423246.jpg\",\"lineStatus\":\"0\",\"listPrice\":400,\"offLineTime\":\"2016-08-15 11:20:28\",\"onLineCitys\":\"100010000\",\"onLineTime\":\"2016-08-15 11:20:28\",\"salePlatforms\":[0,1,2],\"searchWords\":[\"测试一\"],\"smallImageURL\":\"http://www.lol.com/image/venuesub/3429/1110271532423246.jpg\",\"topSale\":300}documentInfo={\"businessIntroduction\":[{\"title\":\"场馆图片\",\"url\":\"http://www.lol.com/image/venuesub/3429/1110271532423246.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956772055.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956967607.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956967731.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956968064.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956968428.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956968678.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956968811.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956969063.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956969346.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956969601.jpg\"}],\"businessName\":\"李宁·泰川羽毛球馆（西二旗店）\",\"clientTitle\":\"测试一\",\"consumerTips\":\"李宁·泰川羽毛球馆（西二旗店）\",\"groupPurchaseContent\":[{\"title\":\"场馆图片\",\"url\":\"http://www.lol.com/image/venuesub/3429/1110271532423246.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956772055.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956967607.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956967731.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956968064.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956968428.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956968678.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956968811.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956969063.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956969346.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956969601.jpg\"}],\"groupPurchaseDetail\":[{\"title\":\"场馆图片\",\"url\":\"http://www.lol.com/image/venuesub/3429/1110271532423246.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956772055.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956967607.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956967731.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956968064.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956968428.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956968678.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956968811.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956969063.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956969346.jpg\"},{\"title\":\"场馆图片\",\"url\":\"http://b2b.dong24.com/img/45423/1418956969601.jpg\"}],\"largeTitle\":\"测试一\",\"mediumTitle\":\"测试一\",\"shortTitle\":\"测试一\"}extendInfo={\"traffic\":\"地铁西二旗站换乘521或544路公交,京包路北站下车即到\"}timestamp=1471230016tpDealInfo={\"businessType\":\"1\",\"dealType\":\"110\",\"operationMode\":0,\"partnerId\":\"270\",\"partnerName\":\"动网\",\"partnerPhone\":\"4006675170\",\"partnerSrc\":\"lol\",\"serviceType\":\"1\",\"time\":\"2016-08-15 11:20:28\",\"tpDealId\":\"214234\"}tpPoiInfoList={\"tpPoiInfoList\":[{\"address\":\"海淀区京包路9号北清路和京包路交叉口往南1公里京包路东侧30米 公交：地铁西二旗站换乘521或544路公交,京包路北站下车即到。\",\"brand\":\"动网\",\"city\":\"100010000\",\"coordinateSource\":\"1\",\"district\":\"394\",\"frontPhoto\":\"http://www.lol.com/image/venuesub/3429/1110271532423246.jpg\",\"latitude\":40.088495,\"longitude\":116.295502,\"phoneList\":[\"4006675170\"],\"tpPoiId\":3146,\"tpPoiName\":\"李宁·泰川羽毛球馆（西二旗店）\"}]}"; // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
       // System.out.println("-1md5-"+DigestUtils.md5Hex(prestr.getBytes()));
        Map<String, String> hm = new HashMap<String,String>();
        
        
        hm.put("timeStamp", "1471340600922");
        hm.put("username", "qidongjian");
        hm.put("passowrd", "qidongjian");
        hm.put("appId", "8");


//   sign=6b6692f882832a918d33b465ed6e30c7

      String st =  SignUtils.NMSign(hm, "6b6692f882832a918d33b465ed6e30c7");
        
      //  System.out.println(st);
       // DigestUtils.md5Hex(data)
        
        
        
        
        //6b6692f882832a918d33b465ed6e30c7
        
        
        
//        
//        Map<String, String> hm = new HashMap<String, String>();
//        hm.put("abcc", "1ccccc");
//        hm.put("abbb", "bbbbbb");
//        hm.put("aaaa", "abbbb");
//        String prestr = createLinkString(hm);
//        System.out.println(prestr);
    }

}
