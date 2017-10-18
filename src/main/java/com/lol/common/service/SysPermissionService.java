package com.lol.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds0.SysPermission;
import com.lol.common.model.ds0.SysPermissionExample;

public interface SysPermissionService {
	int countByExample(SysPermissionExample example);

	int deleteByExample(SysPermissionExample example);

	int deleteByPrimaryKey(Long id);

	int insert(SysPermission record);

	int insertSelective(SysPermission record);

	List<SysPermission> selectByExample(SysPermissionExample example);

	SysPermission selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") SysPermission record, @Param("example") SysPermissionExample example);

	int updateByExample(@Param("record") SysPermission record, @Param("example") SysPermissionExample example);

	int updateByPrimaryKeySelective(SysPermission record);

	int updateByPrimaryKey(SysPermission record);
}
