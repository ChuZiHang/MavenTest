package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds0.SysPermissionMapper;
import com.lol.common.model.ds0.SysPermission;
import com.lol.common.model.ds0.SysPermissionExample;
import com.lol.common.service.SysPermissionService;

@Service("sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService {

	@Resource
	SysPermissionMapper sysPermissionMapper;

	@Override
	public int countByExample(SysPermissionExample example) {
		return sysPermissionMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(SysPermissionExample example) {
		return sysPermissionMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sysPermissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysPermission record) {
		return sysPermissionMapper.insert(record);
	}

	@Override
	public int insertSelective(SysPermission record) {
		return sysPermissionMapper.insertSelective(record);
	}

	@Override
	public List<SysPermission> selectByExample(SysPermissionExample example) {
		return sysPermissionMapper.selectByExample(example);
	}

	@Override
	public SysPermission selectByPrimaryKey(Long id) {
		return sysPermissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(SysPermission record, SysPermissionExample example) {
		return sysPermissionMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(SysPermission record, SysPermissionExample example) {
		return sysPermissionMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(SysPermission record) {
		return sysPermissionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysPermission record) {
		return sysPermissionMapper.updateByPrimaryKey(record);
	}

}
