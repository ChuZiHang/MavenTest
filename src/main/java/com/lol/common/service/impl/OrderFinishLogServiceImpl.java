package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.OrderFinishLogMapper;
import com.lol.common.model.ds1.OrderFinishLog;
import com.lol.common.model.ds1.OrderFinishLogExample;
import com.lol.common.service.OrderFinishLogService;

@Service("orderFinishLogService")
public class OrderFinishLogServiceImpl implements OrderFinishLogService{

	@Resource
	private OrderFinishLogMapper orderFinishLogMapper;
	
	@Override
	public int countByExample(OrderFinishLogExample example) {
		// TODO Auto-generated method stub
		return orderFinishLogMapper.countByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return orderFinishLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderFinishLog record) {
		// TODO Auto-generated method stub
		return orderFinishLogMapper.insert(record);
	}

	@Override
	public List<OrderFinishLog> selectByExample(OrderFinishLogExample example) {
		// TODO Auto-generated method stub
		return orderFinishLogMapper.selectByExample(example);
	}

	@Override
	public OrderFinishLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return orderFinishLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderFinishLog record) {
		// TODO Auto-generated method stub
		return orderFinishLogMapper.updateByPrimaryKeySelective(record);
	}

}
