package com.lol.common.model.ds0;

import java.io.Serializable;

public class InfoAreaEx implements Serializable {
    private Double areaId;

    private String areaName;

    private String py;

    private String pysx;

    private String airport;

    private String airportM;

    private String station;

    private String stationM;

    private String mapCode;

    private String watherCode;

    private String postCode;

    private String phoneCode;

    private String path;

    private Integer treeType;

    private Integer pId;

    private static final long serialVersionUID = 1L;

    public Double getAreaId() {
        return areaId;
    }

    public void setAreaId(Double areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getPysx() {
        return pysx;
    }

    public void setPysx(String pysx) {
        this.pysx = pysx;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getAirportM() {
        return airportM;
    }

    public void setAirportM(String airportM) {
        this.airportM = airportM;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getStationM() {
        return stationM;
    }

    public void setStationM(String stationM) {
        this.stationM = stationM;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getWatherCode() {
        return watherCode;
    }

    public void setWatherCode(String watherCode) {
        this.watherCode = watherCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getTreeType() {
        return treeType;
    }

    public void setTreeType(Integer treeType) {
        this.treeType = treeType;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
}