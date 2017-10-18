package com.lol.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.CourseGroupInfoMapper;
import com.lol.common.model.ds1.CourseGroupInfo;
import com.lol.common.model.ds1.CourseGroupInfoExample;
import com.lol.common.service.CourseGroupInfoService;

@Service("courseGroupInfoService")
public class CourseGroupInfoServiceImpl implements CourseGroupInfoService{

	@Resource
	private CourseGroupInfoMapper courseGroupInfoMapper;
	
	@Resource
	private JdbcTemplate jdbcTemplateForDs1;
	
	@Override
	public int countByExample(CourseGroupInfoExample example) {
		// TODO Auto-generated method stub
		return courseGroupInfoMapper.countByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return courseGroupInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CourseGroupInfo record) {
		// TODO Auto-generated method stub
		return courseGroupInfoMapper.insert(record);
	}

	@Override
	public List<CourseGroupInfo> selectByExample(CourseGroupInfoExample example) {
		// TODO Auto-generated method stub
		return courseGroupInfoMapper.selectByExample(example);
	}

	@Override
	public CourseGroupInfo selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return courseGroupInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CourseGroupInfo record) {
		// TODO Auto-generated method stub
		return courseGroupInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Map<String, Object>> getList(String sql,Object [] object) {
		List<Map<String, Object>> result = jdbcTemplateForDs1.queryForList(sql,object);
		return result;
	}

	@Override
	public int insertSelective(CourseGroupInfo record) {
		// TODO Auto-generated method stub
		return courseGroupInfoMapper.insertSelective(record);
	}

	
}
