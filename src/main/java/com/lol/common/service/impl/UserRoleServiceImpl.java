package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.UserRoleMapper;
import com.lol.common.model.ds1.UserRole;
import com.lol.common.model.ds1.UserRoleExample;
import com.lol.common.service.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

	@Resource
	UserRoleMapper userRoleMapper;

	@Override
	public int countByExample(UserRoleExample example) {
		return userRoleMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(UserRoleExample example) {
		return userRoleMapper.deleteByExample(example);
	}

	@Override
	public int insert(UserRole record) {
		return userRoleMapper.insert(record);
	}

	@Override
	public int insertSelective(UserRole record) {
		return userRoleMapper.insertSelective(record);
	}

	@Override
	public List<UserRole> selectByExample(UserRoleExample example) {
		return userRoleMapper.selectByExample(example);
	}

	@Override
	public int updateByExampleSelective(UserRole record, UserRoleExample example) {
		return userRoleMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(UserRole record, UserRoleExample example) {
		return userRoleMapper.updateByExample(record, example);
	}

}
