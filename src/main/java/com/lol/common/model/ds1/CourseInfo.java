package com.lol.common.model.ds1;

import java.io.Serializable;
import java.util.Date;

public class CourseInfo implements Serializable {
    private Long id;

    private String className;

    private String classImg;

    private Integer courseRate;

    private Date createTime;

    private Integer status;

    private Integer sysCustId;

    private Integer parentCustId;

    private Float score;

    private String classDesc;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getClassImg() {
        return classImg;
    }

    public void setClassImg(String classImg) {
        this.classImg = classImg == null ? null : classImg.trim();
    }

    public Integer getCourseRate() {
        return courseRate;
    }

    public void setCourseRate(Integer courseRate) {
        this.courseRate = courseRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc == null ? null : classDesc.trim();
    }
}