package com.lol.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds0.SysRole;
import com.lol.common.model.ds0.SysRoleExample;

public interface SysRoleService {
	int countByExample(SysRoleExample example);

	int deleteByExample(SysRoleExample example);

	int deleteByPrimaryKey(Long id);

	int insert(SysRole record);

	int insertSelective(SysRole record);

	List<SysRole> selectByExample(SysRoleExample example);

	SysRole selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

	int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

	int updateByPrimaryKeySelective(SysRole record);

	int updateByPrimaryKey(SysRole record);
}
