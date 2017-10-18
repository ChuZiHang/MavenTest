package com.lol.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.PayDrawmoneyMapper;
import com.lol.common.model.ds1.PayDrawmoney;
import com.lol.common.model.ds1.PayDrawmoneyExample;
import com.lol.common.service.AccountService;



@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Resource
	private PayDrawmoneyMapper payDrawmoneyMapper;

	@Resource
	private JdbcTemplate jdbcTemplateForDs0;

	@Override
	public int insertSelective(PayDrawmoney record) {
		return payDrawmoneyMapper.insertSelective(record);
	}

	@Override
	public List<PayDrawmoney> selectByExample(PayDrawmoneyExample example) {
		return payDrawmoneyMapper.selectByExample(example);
	}

	@Override
	public int countByExample(PayDrawmoneyExample example) {
		return payDrawmoneyMapper.countByExample(example);
	}

	@Override
	public List<Map<String, Object>> getList(String sql,Object [] object) {
			return jdbcTemplateForDs0.queryForList(sql, object);
		}

	
	}

	


