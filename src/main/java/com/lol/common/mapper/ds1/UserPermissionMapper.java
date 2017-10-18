package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.UserPermission;
import com.lol.common.model.ds1.UserPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPermissionMapper {
    int countByExample(UserPermissionExample example);

    int deleteByExample(UserPermissionExample example);

    int insert(UserPermission record);

    int insertSelective(UserPermission record);

    List<UserPermission> selectByExample(UserPermissionExample example);

    int updateByExampleSelective(@Param("record") UserPermission record, @Param("example") UserPermissionExample example);

    int updateByExample(@Param("record") UserPermission record, @Param("example") UserPermissionExample example);
}