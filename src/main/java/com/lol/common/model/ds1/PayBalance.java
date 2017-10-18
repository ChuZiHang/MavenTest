package com.lol.common.model.ds1;

import java.io.Serializable;
import java.util.Date;

public class PayBalance implements Serializable {
    private Long id;

    private Long parentId;

    private Long payServiceId;

    private String payServiceAccount;

    private String payServiceOrderId;

    private String productOrderId;

    private String productName;

    private Integer productNum;

    private Long productType;

    private Long custId;

    private String memberId;

    private String memberName;

    private String memberServiceAccount;

    private Date submitDate;

    private Date serviceCalledDate;

    private Date startCallbackDate;

    private Date lastCallbackDate;

    private Double payTax;

    private Long payAmount;

    private Long payedAmount;

    private Long invoicedAmount;

    private Integer payStatus;

    private Integer callbackStatus;

    private String extendParam;

    private Long serviceInvoicedAmount;

    private Integer callbackNum;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getPayServiceId() {
        return payServiceId;
    }

    public void setPayServiceId(Long payServiceId) {
        this.payServiceId = payServiceId;
    }

    public String getPayServiceAccount() {
        return payServiceAccount;
    }

    public void setPayServiceAccount(String payServiceAccount) {
        this.payServiceAccount = payServiceAccount;
    }

    public String getPayServiceOrderId() {
        return payServiceOrderId;
    }

    public void setPayServiceOrderId(String payServiceOrderId) {
        this.payServiceOrderId = payServiceOrderId;
    }

    public String getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(String productOrderId) {
        this.productOrderId = productOrderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Long getProductType() {
        return productType;
    }

    public void setProductType(Long productType) {
        this.productType = productType;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberServiceAccount() {
        return memberServiceAccount;
    }

    public void setMemberServiceAccount(String memberServiceAccount) {
        this.memberServiceAccount = memberServiceAccount;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Date getServiceCalledDate() {
        return serviceCalledDate;
    }

    public void setServiceCalledDate(Date serviceCalledDate) {
        this.serviceCalledDate = serviceCalledDate;
    }

    public Date getStartCallbackDate() {
        return startCallbackDate;
    }

    public void setStartCallbackDate(Date startCallbackDate) {
        this.startCallbackDate = startCallbackDate;
    }

    public Date getLastCallbackDate() {
        return lastCallbackDate;
    }

    public void setLastCallbackDate(Date lastCallbackDate) {
        this.lastCallbackDate = lastCallbackDate;
    }

    public Double getPayTax() {
        return payTax;
    }

    public void setPayTax(Double payTax) {
        this.payTax = payTax;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Long getPayedAmount() {
        return payedAmount;
    }

    public void setPayedAmount(Long payedAmount) {
        this.payedAmount = payedAmount;
    }

    public Long getInvoicedAmount() {
        return invoicedAmount;
    }

    public void setInvoicedAmount(Long invoicedAmount) {
        this.invoicedAmount = invoicedAmount;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getCallbackStatus() {
        return callbackStatus;
    }

    public void setCallbackStatus(Integer callbackStatus) {
        this.callbackStatus = callbackStatus;
    }

    public String getExtendParam() {
        return extendParam;
    }

    public void setExtendParam(String extendParam) {
        this.extendParam = extendParam;
    }

    public Long getServiceInvoicedAmount() {
        return serviceInvoicedAmount;
    }

    public void setServiceInvoicedAmount(Long serviceInvoicedAmount) {
        this.serviceInvoicedAmount = serviceInvoicedAmount;
    }

    public Integer getCallbackNum() {
        return callbackNum;
    }

    public void setCallbackNum(Integer callbackNum) {
        this.callbackNum = callbackNum;
    }
}