package com.lol.common.model.ds1;

import java.io.Serializable;
import java.util.Date;

public class OrderInfo implements Serializable {
    private Long id;

    private Integer orderType;

    private Long productId;

    private Date creteTime;

    private Integer memPrice;

    private Integer salePrice;

    private Date payTime;

    private Long payId;

    private String linkMan;

    private String linkPhone;

    private Long memberId;

    private Integer status;

    private Integer refundMoney;

    private Integer actualMoney;

    private Date refundTime;

    private String refundUser;

    private String orderMemo;

    private String innerMemo;

    private Date applyTime;

    private Integer sysCustId;

    private Integer parentCustId;

    private Date startTime;

    private Date endTime;

    private Integer orderCount;

    private Integer remainCount;

    private Date finishTime;

    private String finishUser;

    private Integer num;

    private String sendContent;

    private String orderCode;

    private Integer payType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getCreteTime() {
        return creteTime;
    }

    public void setCreteTime(Date creteTime) {
        this.creteTime = creteTime;
    }

    public Integer getMemPrice() {
        return memPrice;
    }

    public void setMemPrice(Integer memPrice) {
        this.memPrice = memPrice;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan == null ? null : linkMan.trim();
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone == null ? null : linkPhone.trim();
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Integer refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Integer getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(Integer actualMoney) {
        this.actualMoney = actualMoney;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getRefundUser() {
        return refundUser;
    }

    public void setRefundUser(String refundUser) {
        this.refundUser = refundUser == null ? null : refundUser.trim();
    }

    public String getOrderMemo() {
        return orderMemo;
    }

    public void setOrderMemo(String orderMemo) {
        this.orderMemo = orderMemo == null ? null : orderMemo.trim();
    }

    public String getInnerMemo() {
        return innerMemo;
    }

    public void setInnerMemo(String innerMemo) {
        this.innerMemo = innerMemo == null ? null : innerMemo.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
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

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(Integer remainCount) {
        this.remainCount = remainCount;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent == null ? null : sendContent.trim();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}