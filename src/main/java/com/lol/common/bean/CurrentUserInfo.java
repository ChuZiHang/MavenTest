package com.lol.common.bean;

import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.UserLogin;

/**
 * 当前用户相关信息
 * 
 * @author cunxing
 *
 */
public class CurrentUserInfo {

    private CustInfo custInfo;

    private UserLogin userLogin;

    public CustInfo getCustInfo() {
        return custInfo;
    }

    public void setCustInfo(CustInfo custInfo) {
        this.custInfo = custInfo;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

}
