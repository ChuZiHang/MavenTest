package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.DeptPermissionMapper;
import com.lol.common.model.ds1.DeptPermission;
import com.lol.common.model.ds1.DeptPermissionExample;
import com.lol.common.service.DeptPermissionService;

@Service("deptPermissionService")
public class DeptPermissionServiceImpl implements DeptPermissionService {

	@Resource
	DeptPermissionMapper deptPermissionMapper;

	@Override
	public int countByExample(DeptPermissionExample example) {
		return deptPermissionMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(DeptPermissionExample example) {
		return deptPermissionMapper.deleteByExample(example);
	}

	@Override
	public int insert(DeptPermission record) {
		return deptPermissionMapper.insert(record);
	}

	@Override
	public int insertSelective(DeptPermission record) {
		return deptPermissionMapper.insertSelective(record);
	}

	@Override
	public List<DeptPermission> selectByExample(DeptPermissionExample example) {
		return deptPermissionMapper.selectByExample(example);
	}

	@Override
	public int updateByExampleSelective(DeptPermission record, DeptPermissionExample example) {
		return deptPermissionMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(DeptPermission record, DeptPermissionExample example) {
		return deptPermissionMapper.updateByExample(record, example);
	}

}
