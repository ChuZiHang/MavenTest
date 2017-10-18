package com.lol.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.SmsAuthCodeMapper;
import com.lol.common.model.ds1.SmsAuthCode;
import com.lol.common.model.ds1.SmsAuthCodeExample;
import com.lol.common.service.SmsAuthCodeService;
@Service("smsAuthCodeService")
public class SmsAuthCodeServiceImpl implements SmsAuthCodeService {


	@Resource
	private SmsAuthCodeMapper smsAuthCodeMapper;

	@Resource
	private JdbcTemplate jdbcTemplateForDs1;

	@Override
	public int insertSelective(SmsAuthCode record) {
		return smsAuthCodeMapper.insertSelective(record);
	}

	@Override
	public List<SmsAuthCode> selectByExample(SmsAuthCodeExample example) {
		return smsAuthCodeMapper.selectByExample(example);
	}

	@Override
	public int countByExample(SmsAuthCodeExample example) {
		return smsAuthCodeMapper.countByExample(example);
	}

	@Override
	public List<Map<String, Object>> getList(String sql,Object [] object) {
			return jdbcTemplateForDs1.queryForList(sql, object);
		}

	
}
