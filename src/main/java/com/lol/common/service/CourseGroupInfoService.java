package com.lol.common.service;

import java.util.List;
import java.util.Map;

import com.lol.common.model.ds1.CourseGroupInfo;
import com.lol.common.model.ds1.CourseGroupInfoExample;

public interface CourseGroupInfoService {

	int countByExample(CourseGroupInfoExample example);
	
	int deleteByPrimaryKey(Integer id);
	
	int insert(CourseGroupInfo record);
	
	int insertSelective(CourseGroupInfo record);
	
	List<CourseGroupInfo> selectByExample(CourseGroupInfoExample example);
	
	CourseGroupInfo selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(CourseGroupInfo record);
	
	List<Map<String, Object>> getList(String sql,Object [] object);
	
}
