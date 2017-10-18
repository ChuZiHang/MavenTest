package com.lol.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.UserProductLogMapper;
import com.lol.common.model.ds1.UserProductLog;
import com.lol.common.model.ds1.UserProductLogExample;
import com.lol.common.service.UserProductLogService;

@Service("userProductLogService")
public class UserProductLogServiceImpl implements UserProductLogService{

	@Resource
	private UserProductLogMapper userProductLogMapper;
	
	@Resource
	private JdbcTemplate jdbcTemplateForDs1;
	
	@Override
	public int countByExample(UserProductLogExample example) {
		return userProductLogMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(UserProductLogExample example) {
		return userProductLogMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userProductLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserProductLog record) {
		return userProductLogMapper.insert(record);
	}

	@Override
	public int insertSelective(UserProductLog record) {
		return userProductLogMapper.insertSelective(record);
	}

	@Override
	public List<UserProductLog> selectByExample(UserProductLogExample example) {
		return userProductLogMapper.selectByExample(example);
	}

	@Override
	public UserProductLog selectByPrimaryKey(Integer id) {
		return userProductLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(UserProductLog record,
			UserProductLogExample example) {
		return userProductLogMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(UserProductLog record,
			UserProductLogExample example) {
		return userProductLogMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(UserProductLog record) {
		return userProductLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserProductLog record) {
		return userProductLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Map<String, Object>> selectBySql(String sql, Object[] args) {
		return jdbcTemplateForDs1.queryForList(sql, args);
	}
	
	
}
