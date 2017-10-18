package com.lol.common.model.ds0;

import java.io.Serializable;

public class SysPermission implements Serializable {
    private Long id;

    private String type;

    private String name;

    private String path;

    private String description;

    private String permsResource;

    private String permsOperate;

    private String permsData;

    private Integer isMenu;

    private String icon;

    private String target;

    private String parentId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermsResource() {
        return permsResource;
    }

    public void setPermsResource(String permsResource) {
        this.permsResource = permsResource;
    }

    public String getPermsOperate() {
        return permsOperate;
    }

    public void setPermsOperate(String permsOperate) {
        this.permsOperate = permsOperate;
    }

    public String getPermsData() {
        return permsData;
    }

    public void setPermsData(String permsData) {
        this.permsData = permsData;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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