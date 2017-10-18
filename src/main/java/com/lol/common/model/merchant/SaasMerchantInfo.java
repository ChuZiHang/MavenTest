package com.lol.common.model.merchant;

import java.io.Serializable;
import java.util.Date;

public class SaasMerchantInfo implements Serializable {
    private Integer mcId;

    private String mcName;

    private String mcDomain;

    private String mcLogo;

    private Date mcSTime;

    private Date mcETime;

    private Integer mcPayFee;

    private Integer mcSmsCount;

    private String mcCustName;

    private String mcLinkMan;

    private String mcCustDesc;

    private String mcLinkMobile;

    private String mcLinkQq;

    private String mcLinkEmail;

    private String mcLinkFax;

    private String mcAddress;

    private String mcAlipayAccount;

    private String mcAlipayName;

    private String mcBankProvince;

    private String mcBankCity;

    private String mcBankDistrict;

    private String mcBankAccount;

    private String mcBankName;

    private String mcBankAcname;

    private Integer mcStype;

    private Integer mcType;

    private Integer mcPId;

    private String mcSignimg;

    private String mcDbLink;

    private String mcLoginAccount;

    private String mcLoginPwd;

    private Integer mcCustType;

    private Integer mcAreaId;

    private Date mcCTime;

    private Integer mcStatus;

    private Double longitude;

    private Double latitude;

    private Integer mcSmsStatus;

    private static final long serialVersionUID = 1L;

    public Integer getMcId() {
        return mcId;
    }

    public void setMcId(Integer mcId) {
        this.mcId = mcId;
    }

    public String getMcName() {
        return mcName;
    }

    public void setMcName(String mcName) {
        this.mcName = mcName == null ? null : mcName.trim();
    }

    public String getMcDomain() {
        return mcDomain;
    }

    public void setMcDomain(String mcDomain) {
        this.mcDomain = mcDomain == null ? null : mcDomain.trim();
    }

    public String getMcLogo() {
        return mcLogo;
    }

    public void setMcLogo(String mcLogo) {
        this.mcLogo = mcLogo == null ? null : mcLogo.trim();
    }

    public Date getMcSTime() {
        return mcSTime;
    }

    public void setMcSTime(Date mcSTime) {
        this.mcSTime = mcSTime;
    }

    public Date getMcETime() {
        return mcETime;
    }

    public void setMcETime(Date mcETime) {
        this.mcETime = mcETime;
    }

    public Integer getMcPayFee() {
        return mcPayFee;
    }

    public void setMcPayFee(Integer mcPayFee) {
        this.mcPayFee = mcPayFee;
    }

    public Integer getMcSmsCount() {
        return mcSmsCount;
    }

    public void setMcSmsCount(Integer mcSmsCount) {
        this.mcSmsCount = mcSmsCount;
    }

    public String getMcCustName() {
        return mcCustName;
    }

    public void setMcCustName(String mcCustName) {
        this.mcCustName = mcCustName == null ? null : mcCustName.trim();
    }

    public String getMcLinkMan() {
        return mcLinkMan;
    }

    public void setMcLinkMan(String mcLinkMan) {
        this.mcLinkMan = mcLinkMan == null ? null : mcLinkMan.trim();
    }

    public String getMcCustDesc() {
        return mcCustDesc;
    }

    public void setMcCustDesc(String mcCustDesc) {
        this.mcCustDesc = mcCustDesc == null ? null : mcCustDesc.trim();
    }

    public String getMcLinkMobile() {
        return mcLinkMobile;
    }

    public void setMcLinkMobile(String mcLinkMobile) {
        this.mcLinkMobile = mcLinkMobile == null ? null : mcLinkMobile.trim();
    }

    public String getMcLinkQq() {
        return mcLinkQq;
    }

    public void setMcLinkQq(String mcLinkQq) {
        this.mcLinkQq = mcLinkQq == null ? null : mcLinkQq.trim();
    }

    public String getMcLinkEmail() {
        return mcLinkEmail;
    }

    public void setMcLinkEmail(String mcLinkEmail) {
        this.mcLinkEmail = mcLinkEmail == null ? null : mcLinkEmail.trim();
    }

    public String getMcLinkFax() {
        return mcLinkFax;
    }

    public void setMcLinkFax(String mcLinkFax) {
        this.mcLinkFax = mcLinkFax == null ? null : mcLinkFax.trim();
    }

    public String getMcAddress() {
        return mcAddress;
    }

    public void setMcAddress(String mcAddress) {
        this.mcAddress = mcAddress == null ? null : mcAddress.trim();
    }

    public String getMcAlipayAccount() {
        return mcAlipayAccount;
    }

    public void setMcAlipayAccount(String mcAlipayAccount) {
        this.mcAlipayAccount = mcAlipayAccount == null ? null : mcAlipayAccount.trim();
    }

    public String getMcAlipayName() {
        return mcAlipayName;
    }

    public void setMcAlipayName(String mcAlipayName) {
        this.mcAlipayName = mcAlipayName == null ? null : mcAlipayName.trim();
    }

    public String getMcBankProvince() {
        return mcBankProvince;
    }

    public void setMcBankProvince(String mcBankProvince) {
        this.mcBankProvince = mcBankProvince == null ? null : mcBankProvince.trim();
    }

    public String getMcBankCity() {
        return mcBankCity;
    }

    public void setMcBankCity(String mcBankCity) {
        this.mcBankCity = mcBankCity == null ? null : mcBankCity.trim();
    }

    public String getMcBankDistrict() {
        return mcBankDistrict;
    }

    public void setMcBankDistrict(String mcBankDistrict) {
        this.mcBankDistrict = mcBankDistrict == null ? null : mcBankDistrict.trim();
    }

    public String getMcBankAccount() {
        return mcBankAccount;
    }

    public void setMcBankAccount(String mcBankAccount) {
        this.mcBankAccount = mcBankAccount == null ? null : mcBankAccount.trim();
    }

    public String getMcBankName() {
        return mcBankName;
    }

    public void setMcBankName(String mcBankName) {
        this.mcBankName = mcBankName == null ? null : mcBankName.trim();
    }

    public String getMcBankAcname() {
        return mcBankAcname;
    }

    public void setMcBankAcname(String mcBankAcname) {
        this.mcBankAcname = mcBankAcname == null ? null : mcBankAcname.trim();
    }

    public Integer getMcStype() {
        return mcStype;
    }

    public void setMcStype(Integer mcStype) {
        this.mcStype = mcStype;
    }

    public Integer getMcType() {
        return mcType;
    }

    public void setMcType(Integer mcType) {
        this.mcType = mcType;
    }

    public Integer getMcPId() {
        return mcPId;
    }

    public void setMcPId(Integer mcPId) {
        this.mcPId = mcPId;
    }

    public String getMcSignimg() {
        return mcSignimg;
    }

    public void setMcSignimg(String mcSignimg) {
        this.mcSignimg = mcSignimg == null ? null : mcSignimg.trim();
    }

    public String getMcDbLink() {
        return mcDbLink;
    }

    public void setMcDbLink(String mcDbLink) {
        this.mcDbLink = mcDbLink == null ? null : mcDbLink.trim();
    }

    public String getMcLoginAccount() {
        return mcLoginAccount;
    }

    public void setMcLoginAccount(String mcLoginAccount) {
        this.mcLoginAccount = mcLoginAccount == null ? null : mcLoginAccount.trim();
    }

    public String getMcLoginPwd() {
        return mcLoginPwd;
    }

    public void setMcLoginPwd(String mcLoginPwd) {
        this.mcLoginPwd = mcLoginPwd == null ? null : mcLoginPwd.trim();
    }

    public Integer getMcCustType() {
        return mcCustType;
    }

    public void setMcCustType(Integer mcCustType) {
        this.mcCustType = mcCustType;
    }

    public Integer getMcAreaId() {
        return mcAreaId;
    }

    public void setMcAreaId(Integer mcAreaId) {
        this.mcAreaId = mcAreaId;
    }

    public Date getMcCTime() {
        return mcCTime;
    }

    public void setMcCTime(Date mcCTime) {
        this.mcCTime = mcCTime;
    }

    public Integer getMcStatus() {
        return mcStatus;
    }

    public void setMcStatus(Integer mcStatus) {
        this.mcStatus = mcStatus;
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

    public Integer getMcSmsStatus() {
        return mcSmsStatus;
    }

    public void setMcSmsStatus(Integer mcSmsStatus) {
        this.mcSmsStatus = mcSmsStatus;
    }
}