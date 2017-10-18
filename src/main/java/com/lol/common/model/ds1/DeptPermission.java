package com.lol.common.model.ds1;

import java.io.Serializable;

public class DeptPermission implements Serializable {
    private Long deptId;

    private Long permissionId;

    private static final long serialVersionUID = 1L;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}