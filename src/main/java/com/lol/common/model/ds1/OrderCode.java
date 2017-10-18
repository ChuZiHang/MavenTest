package com.lol.common.model.ds1;

import java.io.Serializable;
import java.util.Date;

public class OrderCode implements Serializable {
    private Integer id;

    private Integer orderId;

    private Integer orderCode;

    private Date createTime;

    private Date finishTime;

    private String finishUser;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}