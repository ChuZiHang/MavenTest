package com.lol.common.model.ds1;

import java.io.Serializable;

public class PayBalanceWithBLOBs extends PayBalance implements Serializable {
    private String userRemark;

    private String sysRemark;

    private static final long serialVersionUID = 1L;

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getSysRemark() {
        return sysRemark;
    }

    public void setSysRemark(String sysRemark) {
        this.sysRemark = sysRemark;
    }
}