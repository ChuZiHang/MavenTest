package com.lol.common.model.ds0;

import java.io.Serializable;
import java.util.Date;

public class SmsQueue implements Serializable {
    private Long id;

    private String mobile;

    private String msg;

    private Date sendTime;

    private String sendStatus;

    private String arriveStatus;

    private Integer providerId;

    private Integer providerType;

    private Integer sendResult;

    private Integer arriveResult;

    private Long custId;

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

    public String getArriveStatus() {
        return arriveStatus;
    }

    public void setArriveStatus(String arriveStatus) {
        this.arriveStatus = arriveStatus == null ? null : arriveStatus.trim();
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
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

    public Integer getArriveResult() {
        return arriveResult;
    }

    public void setArriveResult(Integer arriveResult) {
        this.arriveResult = arriveResult;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }
}