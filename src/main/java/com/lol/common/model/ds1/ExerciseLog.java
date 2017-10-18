package com.lol.common.model.ds1;

import java.io.Serializable;
import java.util.Date;

public class ExerciseLog implements Serializable {
    private Long id;

    private Long memberId;

    private String ryxId;

    private Double distance;

    private Double cal;

    private Double speed;

    private Double runTime;

    private String deviceId;

    private Date loginTime;

    private Date endTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getRyxId() {
        return ryxId;
    }

    public void setRyxId(String ryxId) {
        this.ryxId = ryxId == null ? null : ryxId.trim();
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getCal() {
        return cal;
    }

    public void setCal(Double cal) {
        this.cal = cal;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getRunTime() {
        return runTime;
    }

    public void setRunTime(Double runTime) {
        this.runTime = runTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}