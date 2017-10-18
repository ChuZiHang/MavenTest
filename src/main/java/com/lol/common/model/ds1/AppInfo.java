package com.lol.common.model.ds1;

import java.io.Serializable;

public class AppInfo implements Serializable {
    private Integer appId;

    private String appToken;

    private Integer appStatus;

    private static final long serialVersionUID = 1L;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken == null ? null : appToken.trim();
    }

    public Integer getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(Integer appStatus) {
        this.appStatus = appStatus;
    }
}