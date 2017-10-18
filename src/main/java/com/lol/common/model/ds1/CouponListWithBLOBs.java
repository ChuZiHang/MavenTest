package com.lol.common.model.ds1;

import java.io.Serializable;

public class CouponListWithBLOBs extends CouponList implements Serializable {
    private String sendJson;

    private String resJson;

    private static final long serialVersionUID = 1L;

    public String getSendJson() {
        return sendJson;
    }

    public void setSendJson(String sendJson) {
        this.sendJson = sendJson == null ? null : sendJson.trim();
    }

    public String getResJson() {
        return resJson;
    }

    public void setResJson(String resJson) {
        this.resJson = resJson == null ? null : resJson.trim();
    }
}