package com.lol.common.model.ds0;

import java.io.Serializable;

public class SysAuthRule implements Serializable {
    private Long id;

    private String filterPath;

    private String filterChain;

    private Integer priority;

    private Integer appId;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilterPath() {
        return filterPath;
    }

    public void setFilterPath(String filterPath) {
        this.filterPath = filterPath;
    }

    public String getFilterChain() {
        return filterChain;
    }

    public void setFilterChain(String filterChain) {
        this.filterChain = filterChain;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}