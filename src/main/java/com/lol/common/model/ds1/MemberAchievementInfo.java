package com.lol.common.model.ds1;

import java.io.Serializable;

public class MemberAchievementInfo implements Serializable {
    private Integer id;

    private String name;

    private String unobtain;

    private String obtain;

    private Integer point;

    private Integer status;

    private Integer sort;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUnobtain() {
        return unobtain;
    }

    public void setUnobtain(String unobtain) {
        this.unobtain = unobtain == null ? null : unobtain.trim();
    }

    public String getObtain() {
        return obtain;
    }

    public void setObtain(String obtain) {
        this.obtain = obtain == null ? null : obtain.trim();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}