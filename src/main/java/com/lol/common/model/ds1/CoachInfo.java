package com.lol.common.model.ds1;

import java.io.Serializable;
import java.util.Date;

public class CoachInfo implements Serializable {
    private Long id;

    private String name;

    private String signImg;

    private String openImg;

    private String imgs;

    private Integer sysCustId;

    private String useCustId;

    private Integer status;

    private Integer parentCustId;

    private Integer isRefund;

    private String userId;

    private Date createTime;

    private Integer fee;

    private Integer saleRule;

    private Integer refundRule;

    private Integer refundPrice;

    private Integer salePrice;

    private Integer isSend;

    private String sendContent;

    private String memo;

    private String mobile;

    private Integer coachType;

    private Integer sort;

    private String proDesc;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSignImg() {
        return signImg;
    }

    public void setSignImg(String signImg) {
        this.signImg = signImg == null ? null : signImg.trim();
    }

    public String getOpenImg() {
        return openImg;
    }

    public void setOpenImg(String openImg) {
        this.openImg = openImg == null ? null : openImg.trim();
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs == null ? null : imgs.trim();
    }

    public Integer getSysCustId() {
        return sysCustId;
    }

    public void setSysCustId(Integer sysCustId) {
        this.sysCustId = sysCustId;
    }

    public String getUseCustId() {
        return useCustId;
    }

    public void setUseCustId(String useCustId) {
        this.useCustId = useCustId == null ? null : useCustId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getParentCustId() {
        return parentCustId;
    }

    public void setParentCustId(Integer parentCustId) {
        this.parentCustId = parentCustId;
    }

    public Integer getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(Integer isRefund) {
        this.isRefund = isRefund;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getSaleRule() {
        return saleRule;
    }

    public void setSaleRule(Integer saleRule) {
        this.saleRule = saleRule;
    }

    public Integer getRefundRule() {
        return refundRule;
    }

    public void setRefundRule(Integer refundRule) {
        this.refundRule = refundRule;
    }

    public Integer getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(Integer refundPrice) {
        this.refundPrice = refundPrice;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent == null ? null : sendContent.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getCoachType() {
        return coachType;
    }

    public void setCoachType(Integer coachType) {
        this.coachType = coachType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc == null ? null : proDesc.trim();
    }
}