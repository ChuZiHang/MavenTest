package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds0.SmsQueueMapper;
import com.lol.common.model.ds0.SmsQueue;
import com.lol.common.model.ds0.SmsQueueExample;
import com.lol.common.service.SmsQueueService;
@Service
public class SmsQueueServiceImpl implements SmsQueueService {

	@Resource
	private SmsQueueMapper smsQueueMapper;

	@Override
	public List<SmsQueue> selectByExample(SmsQueueExample example) {
		
		return smsQueueMapper.selectByExample(example);
	}

	@Override
	public SmsQueue selectByPrimaryKey(Long id) {
		
		return smsQueueMapper.selectByPrimaryKey(id);
	}

	@Override
	public int countByExample(SmsQueueExample example) {
		
		return smsQueueMapper.countByExample(example);
	}
	

}
