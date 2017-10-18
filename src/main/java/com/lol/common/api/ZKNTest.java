package com.lol.common.api;


/**
 * Created by zhuwei on 16/9/3.
 */
public class ZKNTest {
    @org.junit.Test
    public void addDepartmentTest(){
        System.out.println(ZKNClient.addDepartment("3","会员",""));
    }
}
