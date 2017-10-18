package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.OpenLogMapper;
import com.lol.common.model.ds1.OpenLog;
import com.lol.common.model.ds1.OpenLogExample;
import com.lol.common.service.OpenLogService;
@Service("openLogService")
public class OpenLogServiceImpl implements OpenLogService {

	
	@Resource
	private  OpenLogMapper  openLogMapper;

	@Override
	public List<OpenLog> selectByExample(OpenLogExample example) {
		
		return openLogMapper.selectByExample(example);
	}

	@Override
	public int countByExample(OpenLogExample example) {
	
		return openLogMapper.countByExample(example);
	}

}
