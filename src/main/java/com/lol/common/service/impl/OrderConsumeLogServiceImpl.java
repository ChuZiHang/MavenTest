package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.OrderConsumeLogMapper;
import com.lol.common.model.ds1.OrderConsumeLog;
import com.lol.common.model.ds1.OrderConsumeLogExample;
import com.lol.common.service.OrderConsumeLogService;
@Service("orderConsumeLogService")
public class OrderConsumeLogServiceImpl implements OrderConsumeLogService {

	@Resource
	private OrderConsumeLogMapper  orderConsumeLogMapper;
	@Override
	public int insertSelective(OrderConsumeLog record) {
		
		return orderConsumeLogMapper.insertSelective(record);
	}

	@Override
	public int countByExample(OrderConsumeLogExample example) {
		return orderConsumeLogMapper.countByExample(example);
	}

	@Override
	public List<OrderConsumeLog> selectByExample(OrderConsumeLogExample example) {
		return orderConsumeLogMapper.selectByExample(example);
	}

}
