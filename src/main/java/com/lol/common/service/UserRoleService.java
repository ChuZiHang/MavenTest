package com.lol.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds1.UserRole;
import com.lol.common.model.ds1.UserRoleExample;

public interface UserRoleService {
	int countByExample(UserRoleExample example);

	int deleteByExample(UserRoleExample example);

	int insert(UserRole record);

	int insertSelective(UserRole record);

	List<UserRole> selectByExample(UserRoleExample example);

	int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

	int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);
}
