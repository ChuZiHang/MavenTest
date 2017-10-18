package com.lol.common.service;

import java.util.List;

import com.lol.common.model.ds1.UserPermission;
import com.lol.common.model.ds1.UserPermissionExample;

public interface UserPermissionService {

    List<UserPermission> selectByExample(UserPermissionExample example);

    void updateUserPermission(long userId, List<Long> permissionIds);

    int deleteByExample(UserPermissionExample example);
}
