package com.lol.common.api;

import com.alibaba.fastjson.JSONObject;
import com.lol.common.util.HttpUtils;

/**
 * Created by zhuwei on 16/9/2.
 */
public class ZKNClient {

    private static final String URL = "http://139.196.48.18:8133/central";

    /**
     * 增加部门
     * 
     * @param code
     *        部门编号 1 启动键 2 访客
     * @param name
     *        部门名词
     * @param parentCode
     *        上级部门编号
     * @return C V
     *
     */
    public static String addDepartment(String code, String name, String parentCode) {
        String res = "";
        /**
         * { "sid":"third:PersDepartment:addDepartment", "username":"zkteco", "password":"zkeco", "data": { "code":"部门编号",
         * "name":"部门名称", "parent_code":"上级部门编号" } } {"ret":0,"data":""} ret：成功返回0，失败返回-1，其它见附录-异常字典。 data：为空字符串
         */

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sid", "third:PersDepartment:addDepartment");
        jsonObject.put("username", "zkteco");
        jsonObject.put("password", "zkeco");
        JSONObject data = new JSONObject();
        data.put("code", code);
        data.put("name", name);
        data.put("parent_code", parentCode);
        jsonObject.put("data", data);
        HttpUtils httpUtils = new HttpUtils();
        res = httpUtils.postJson(URL, "UTF-8", jsonObject.toJSONString());
        return res;
    }

    /**
     * 增加部门
     * 
     * @param code
     *        部门编号
     * @param name
     *        部门名词
     * @param parentCode
     *        上级部门编号
     * @return
     */
    public static String editDepartment(String code, String name, String parentCode) {
        String res = "";
        /**
         * { "sid":"third:PersDepartment:editDepartment", "username":"zkteco", "password":"zkeco", "data": { "code":"部门编号",
         * "name":"部门名称", "parent_code":"上级部门编号" } } {"ret":0,"data":""} ret：成功返回0，失败返回-1，其它见附录-异常字典。 data：为空字符串
         */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sid", "third:PersDepartment:editDepartment");
        jsonObject.put("username", "zkteco");
        jsonObject.put("password", "zkeco");
        JSONObject data = new JSONObject();
        data.put("code", code);
        data.put("name", name);
        data.put("parent_code", parentCode);
        jsonObject.put("data", data);

        HttpUtils httpUtils = new HttpUtils();
        res = httpUtils.postJson(URL, "UTF-8", jsonObject.toJSONString());

        return res;
    }

    public static String addPerson(String pin, String name, String cardNo, String deptCode, String personPwd, String gender) {
        String res = "";
        /**
         * { "sid":"third:PersPerson:addPerson", "username":"zkteco", "password":"zkeco", "data": { "pin":"人员编号", "name":"姓名",
         * "cardNo":"卡号", "deptCode":"部门编号", "personPwd":"密码", "gender":"性别" } } 性别：M（男），F（女） 人员编号和部门编号为必填项
         * 
         * {"ret":0,"data":""} ret：成功返回0，失败返回-1，其它见附录-异常字典。 data：为空字符串
         */

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sid", "third:PersPerson:addPerson");
        jsonObject.put("username", "zkteco");
        jsonObject.put("password", "zkeco");
        JSONObject data = new JSONObject();
        data.put("pin", pin);
        data.put("name", name);
        data.put("cardNo", cardNo);
        data.put("deptCode", deptCode);
        data.put("personPwd", personPwd);
        data.put("gender", gender);
        jsonObject.put("data", data);

        HttpUtils httpUtils = new HttpUtils();
        res = httpUtils.postJson(URL, "UTF-8", jsonObject.toJSONString());
        return res;
    }

    public static String delByPin(String pin) {
        String res = "";
        /**
         * { "sid":"third:PersPerson:delByPin", "username":"zkteco", "password":"zkeco", "data": { "pin":"人员编号" } }
         * 
         * 性别：M（男），F（女） 人员编号和部门编号为必填项
         * 
         * {"ret":0,"data":""} ret：成功返回0，失败返回-1，其它见附录-异常字典。 data：为空字符串
         */

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sid", "third:PersPerson:delByPin");
        jsonObject.put("username", "zkteco");
        jsonObject.put("password", "zkeco");
        JSONObject data = new JSONObject();
        data.put("pin", pin);
        jsonObject.put("data", data);
        HttpUtils httpUtils = new HttpUtils();
        res = httpUtils.postJson(URL, "UTF-8", jsonObject.toJSONString());
        return res;
    }

    public static String addPersonLevel(String pins, String levelIds) {
        String res = "";
        /**
         * { "sid":"third:AccLevel:addPersonLevel", "username":"zkteco", "password":"zkeco", "data": { "levelIds":"id1,id2,...",
         * "pins":"pin1,pin2,..." } } levelIds：权限的id,多个id用逗号“，”隔开,如”1,2,3”。 pins：人员的pin,多个pin用逗号“，”隔开,如”1,2,3”。
         * 
         * 
         * {"ret":0,"data":""} ret：成功返回0，失败返回-1，其它见附录-异常字典。 data：为空字符串
         */

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sid", "third:AccLevel:addPersonLevel");
        jsonObject.put("username", "zkteco");
        jsonObject.put("password", "zkeco");
        JSONObject data = new JSONObject();
        data.put("pins", pins);
        data.put("levelIds", levelIds);
        jsonObject.put("data", data);
        HttpUtils httpUtils = new HttpUtils();
        res = httpUtils.postJson(URL, "UTF-8", jsonObject.toJSONString());

        return res;
    }

    public static String immeDelPersonLevel(String pins, String levelIds) {
        String res = "";
        /**
         * { "sid":"third:AccLevel:immeDelPersonLevel", "username":"zkteco", "password":"zkeco", "data": { "levelIds":"id1,id2,...",
         * "pins":"pin1,pin2,..." } } levelIds：权限的id,多个id用逗号“，”隔开,如”1,2,3”。 pins：人员的pin,多个pin用逗号“，”隔开,如”1,2,3”。
         * 
         * 
         * {"ret":0,"data":""} ret：成功返回0，失败返回-1，其它见附录-异常字典。 data：为空字符串
         */

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sid", "third:AccLevel:immeDelPersonLevel");
        jsonObject.put("username", "zkteco");
        jsonObject.put("password", "zkeco");
        JSONObject data = new JSONObject();
        data.put("pins", pins);
        data.put("levelIds", levelIds);
        jsonObject.put("data", data);
        HttpUtils httpUtils = new HttpUtils();
        res = httpUtils.postJson(URL, "UTF-8", jsonObject.toJSONString());

        return res;
    }

    public static boolean AddPersonAndLevel(String pin, String name, String cardNo, String deptCode, String personPwd, String gender) {
        String res = addPerson(pin, name, cardNo, deptCode, personPwd, gender);
        res = addPersonLevel(pin, "1");
        JSONObject object = JSONObject.parseObject(res);
        if(object.getString("ret").equals("0"))
            return true;
        return false;
    }

    public static void main(String[] args) {
        // System.out.println(ZKNClient.addDepartment("3","会员",""));
        System.out.println("****************************");
//        System.out.println(ZKNClient.editDepartment("3", "会员", ""));
        System.out.println("****************************");
//        System.out.println(ZKNClient.delByPin("322"));

        System.out.println("****************************");
//        System.out.println(ZKNClient.addPerson("322", "朱伟", "333", "3", "34333", "M"));
        // System.out.println(ZKNClient.delByPin("322"));
//        System.out.println(ZKNClient.addPersonLevel("3","1"));
        System.out.println(AddPersonAndLevel("322", "朱伟", "333", "3", "34333", "M"));
    }
}
