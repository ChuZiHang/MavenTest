package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.DeptPermission;
import com.lol.common.model.ds1.DeptPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptPermissionMapper {
    int countByExample(DeptPermissionExample example);

    int deleteByExample(DeptPermissionExample example);

    int insert(DeptPermission record);

    int insertSelective(DeptPermission record);

    List<DeptPermission> selectByExample(DeptPermissionExample example);

    int updateByExampleSelective(@Param("record") DeptPermission record, @Param("example") DeptPermissionExample example);

    int updateByExample(@Param("record") DeptPermission record, @Param("example") DeptPermissionExample example);
}