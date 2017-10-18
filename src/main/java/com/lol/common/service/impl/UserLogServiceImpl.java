
package com.lol.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.UserLogMapper;
import com.lol.common.model.ds1.UserLog;
import com.lol.common.model.ds1.UserLogExample;
import com.lol.common.service.UserLogService;

@Service("userLogService")
public class UserLogServiceImpl implements UserLogService{

	@Resource
	private UserLogMapper userLogMapper;
	
	@Resource
	private JdbcTemplate jdbcTemplateForDs1;

	
	@Override
	public int countByExample(UserLogExample example) {
		// TODO Auto-generated method stub
		return userLogMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(UserLogExample example) {
		// TODO Auto-generated method stub
		return userLogMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserLog record) {
		// TODO Auto-generated method stub
		return userLogMapper.insert(record);
	}

	@Override
	public int insertSelective(UserLog record) {
		// TODO Auto-generated method stub
		return userLogMapper.insertSelective(record);
	}

	@Override
	public List<UserLog> selectByExample(UserLogExample example) {
		// TODO Auto-generated method stub
		return userLogMapper.selectByExample(example);
	}

	@Override
	public UserLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(UserLog record, UserLogExample example) {
		// TODO Auto-generated method stub
		return userLogMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(UserLog record, UserLogExample example) {
		// TODO Auto-generated method stub
		return userLogMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(UserLog record) {
		// TODO Auto-generated method stub
		return userLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserLog record) {
		// TODO Auto-generated method stub
		return userLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Map<String, Object>> selectBySql(String sql, Object[] args) {
		return jdbcTemplateForDs1.queryForList(sql, args);
	}

}
