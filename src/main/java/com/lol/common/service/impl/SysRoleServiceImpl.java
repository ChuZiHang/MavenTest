package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds0.SysRoleMapper;
import com.lol.common.model.ds0.SysRole;
import com.lol.common.model.ds0.SysRoleExample;
import com.lol.common.service.SysRoleService;

@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

	@Resource
	SysRoleMapper sysRoleMapper;

	@Override
	public int countByExample(SysRoleExample example) {
		return sysRoleMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(SysRoleExample example) {
		return sysRoleMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sysRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysRole record) {
		return sysRoleMapper.insert(record);
	}

	@Override
	public int insertSelective(SysRole record) {
		return sysRoleMapper.insertSelective(record);
	}

	@Override
	public List<SysRole> selectByExample(SysRoleExample example) {
		return sysRoleMapper.selectByExample(example);
	}

	@Override
	public SysRole selectByPrimaryKey(Long id) {
		return sysRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(SysRole record, SysRoleExample example) {
		return sysRoleMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(SysRole record, SysRoleExample example) {
		return sysRoleMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(SysRole record) {
		return sysRoleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysRole record) {
		return sysRoleMapper.updateByPrimaryKey(record);
	}

}
