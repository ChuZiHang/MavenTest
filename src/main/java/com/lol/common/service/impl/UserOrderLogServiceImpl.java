package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.UserOrderLogMapper;
import com.lol.common.model.ds1.UserOrderLog;
import com.lol.common.model.ds1.UserOrderLogExample;
import com.lol.common.service.UserOrderLogService;

@Service("userOrderLogService")
public class UserOrderLogServiceImpl implements UserOrderLogService{

	@Resource
	private UserOrderLogMapper userOrderLogMapper;
	
	@Override
	public int countByExample(UserOrderLogExample example) {
		// TODO Auto-generated method stub
		return userOrderLogMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(UserOrderLogExample example) {
		// TODO Auto-generated method stub
		return userOrderLogMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userOrderLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserOrderLog record) {
		// TODO Auto-generated method stub
		return userOrderLogMapper.insert(record);
	}

	@Override
	public int insertSelective(UserOrderLog record) {
		// TODO Auto-generated method stub
		return userOrderLogMapper.insertSelective(record);
	}

	@Override
	public List<UserOrderLog> selectByExample(UserOrderLogExample example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserOrderLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
