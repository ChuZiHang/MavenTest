package com.lol.common.model.ds1;

import java.io.Serializable;
import java.util.Date;

public class CustInfo implements Serializable {
    private Long id;

    private String custName;

    private String signimg;

    private Integer custType;

    private Integer areaId;

    private String linkMan;

    private String custDesc;

    private String linkMobile;

    private String linkQq;

    private String linkEmail;

    private String linkFax;

    private String address;

    private Date createTime;

    private Integer orderCount;

    private Long orderMoney;

    private Integer saleCount;

    private Long saleMoney;

    private Integer deposit;

    private Integer status;

    private Integer parentId;

    private Integer systemId;

    private String alipayAccount;

    private String alipayName;

    private String bankProvince;

    private String bankCity;

    private String bankDistrict;

    private String bankAccount;

    private String bankName;

    private String bankAcname;

    private Integer prodNum;

    private Integer interfaceMode;

    private String logo;

    private Integer isFinish;

    private Integer moneyMode;

    private Integer isPush;

    private Integer isB2b;

    private Integer isGmoney;

    private Integer payFee;

    private Integer gatFee;

    private String pushMobile;

    private String pushEmail;

    private Integer currencyType;

    private String businessInfo;

    private Integer sportType;

    private Integer parentType;

    private String bankType;

    private Double longitude;

    private Double latitude;

    private String uiDesc;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getSignimg() {
        return signimg;
    }

    public void setSignimg(String signimg) {
        this.signimg = signimg == null ? null : signimg.trim();
    }

    public Integer getCustType() {
        return custType;
    }

    public void setCustType(Integer custType) {
        this.custType = custType;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan == null ? null : linkMan.trim();
    }

    public String getCustDesc() {
        return custDesc;
    }

    public void setCustDesc(String custDesc) {
        this.custDesc = custDesc == null ? null : custDesc.trim();
    }

    public String getLinkMobile() {
        return linkMobile;
    }

    public void setLinkMobile(String linkMobile) {
        this.linkMobile = linkMobile == null ? null : linkMobile.trim();
    }

    public String getLinkQq() {
        return linkQq;
    }

    public void setLinkQq(String linkQq) {
        this.linkQq = linkQq == null ? null : linkQq.trim();
    }

    public String getLinkEmail() {
        return linkEmail;
    }

    public void setLinkEmail(String linkEmail) {
        this.linkEmail = linkEmail == null ? null : linkEmail.trim();
    }

    public String getLinkFax() {
        return linkFax;
    }

    public void setLinkFax(String linkFax) {
        this.linkFax = linkFax == null ? null : linkFax.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Long getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Long orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Long getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(Long saleMoney) {
        this.saleMoney = saleMoney;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount == null ? null : alipayAccount.trim();
    }

    public String getAlipayName() {
        return alipayName;
    }

    public void setAlipayName(String alipayName) {
        this.alipayName = alipayName == null ? null : alipayName.trim();
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince == null ? null : bankProvince.trim();
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity == null ? null : bankCity.trim();
    }

    public String getBankDistrict() {
        return bankDistrict;
    }

    public void setBankDistrict(String bankDistrict) {
        this.bankDistrict = bankDistrict == null ? null : bankDistrict.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankAcname() {
        return bankAcname;
    }

    public void setBankAcname(String bankAcname) {
        this.bankAcname = bankAcname == null ? null : bankAcname.trim();
    }

    public Integer getProdNum() {
        return prodNum;
    }

    public void setProdNum(Integer prodNum) {
        this.prodNum = prodNum;
    }

    public Integer getInterfaceMode() {
        return interfaceMode;
    }

    public void setInterfaceMode(Integer interfaceMode) {
        this.interfaceMode = interfaceMode;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    public Integer getMoneyMode() {
        return moneyMode;
    }

    public void setMoneyMode(Integer moneyMode) {
        this.moneyMode = moneyMode;
    }

    public Integer getIsPush() {
        return isPush;
    }

    public void setIsPush(Integer isPush) {
        this.isPush = isPush;
    }

    public Integer getIsB2b() {
        return isB2b;
    }

    public void setIsB2b(Integer isB2b) {
        this.isB2b = isB2b;
    }

    public Integer getIsGmoney() {
        return isGmoney;
    }

    public void setIsGmoney(Integer isGmoney) {
        this.isGmoney = isGmoney;
    }

    public Integer getPayFee() {
        return payFee;
    }

    public void setPayFee(Integer payFee) {
        this.payFee = payFee;
    }

    public Integer getGatFee() {
        return gatFee;
    }

    public void setGatFee(Integer gatFee) {
        this.gatFee = gatFee;
    }

    public String getPushMobile() {
        return pushMobile;
    }

    public void setPushMobile(String pushMobile) {
        this.pushMobile = pushMobile == null ? null : pushMobile.trim();
    }

    public String getPushEmail() {
        return pushEmail;
    }

    public void setPushEmail(String pushEmail) {
        this.pushEmail = pushEmail == null ? null : pushEmail.trim();
    }

    public Integer getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(Integer currencyType) {
        this.currencyType = currencyType;
    }

    public String getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo == null ? null : businessInfo.trim();
    }

    public Integer getSportType() {
        return sportType;
    }

    public void setSportType(Integer sportType) {
        this.sportType = sportType;
    }

    public Integer getParentType() {
        return parentType;
    }

    public void setParentType(Integer parentType) {
        this.parentType = parentType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType == null ? null : bankType.trim();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getUiDesc() {
        return uiDesc;
    }

    public void setUiDesc(String uiDesc) {
        this.uiDesc = uiDesc == null ? null : uiDesc.trim();
    }
}