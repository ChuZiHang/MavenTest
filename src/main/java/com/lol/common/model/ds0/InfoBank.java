package com.lol.common.model.ds0;

import java.io.Serializable;

public class InfoBank implements Serializable {
    private Integer id;

    private String province;

    private String city;

    private String bankType;

    private String bankName;

    private String bankCitycode;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCitycode() {
        return bankCitycode;
    }

    public void setBankCitycode(String bankCitycode) {
        this.bankCitycode = bankCitycode;
    }
}