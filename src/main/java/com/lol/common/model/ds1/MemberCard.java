package com.lol.common.model.ds1;

import java.io.Serializable;
import java.util.Date;

public class MemberCard implements Serializable {
    private Long id;

    private Long memberId;

    private Date startTime;

    private Date endTime;

    private Integer isActive;

    private Long productId;

    private Long orderId;

    private Integer sysCustId;

    private Integer parentCustId;

    private Date createDate;

    private Integer source;

    private Integer count;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getSysCustId() {
        return sysCustId;
    }

    public void setSysCustId(Integer sysCustId) {
        this.sysCustId = sysCustId;
    }

    public Integer getParentCustId() {
        return parentCustId;
    }

    public void setParentCustId(Integer parentCustId) {
        this.parentCustId = parentCustId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}