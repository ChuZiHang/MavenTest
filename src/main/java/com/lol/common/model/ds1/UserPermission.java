package com.lol.common.model.ds1;

import java.io.Serializable;

public class UserPermission implements Serializable {
    private Long userId;

    private Long permissionId;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}