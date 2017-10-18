package com.lol.common.model.ds1;

import java.io.Serializable;
import java.util.Date;

public class SmsQdj implements Serializable {
    private Long id;

    private String mobile;

    private String authCode;

    private String msg;

    private Date sendTime;

    private String sendStatus;

    private Integer providerType;

    private Integer sendResult;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode == null ? null : authCode.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    public Integer getProviderType() {
        return providerType;
    }

    public void setProviderType(Integer providerType) {
        this.providerType = providerType;
    }

    public Integer getSendResult() {
        return sendResult;
    }

    public void setSendResult(Integer sendResult) {
        this.sendResult = sendResult;
    }
}