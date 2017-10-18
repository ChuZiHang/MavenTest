package com.lol.common.model.ds1;

import java.io.Serializable;
import java.util.Date;

public class OrderFinishLog implements Serializable {
    private Integer id;

    private Long orderId;

    private String orderCode;

    private Date finishTime;

    private String finishUser;

    private Integer status;

    private String finishMemo;

    private Long memberId;

    private Long infoId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getFinishUser() {
        return finishUser;
    }

    public void setFinishUser(String finishUser) {
        this.finishUser = finishUser == null ? null : finishUser.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFinishMemo() {
        return finishMemo;
    }

    public void setFinishMemo(String finishMemo) {
        this.finishMemo = finishMemo == null ? null : finishMemo.trim();
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }
}