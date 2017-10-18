package com.lol.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.WeixinTokenMapper;
import com.lol.common.model.ds1.WeixinToken;
import com.lol.common.model.ds1.WeixinTokenExample;
import com.lol.common.service.WeixinTokenService;

@Service("weixinTokenService")
public class WeixinTokenServiceImpl implements WeixinTokenService {

	@Resource
	private WeixinTokenMapper WeixinTokenMapper;
	
	@Resource
	private JdbcTemplate jdbcTemplateForDs1;
	
	@Override
	public List<WeixinToken> selectWeixinTokensByExample(WeixinTokenExample example) {
		return WeixinTokenMapper.selectByExample(example);
	}

	@Override
	public List<Map<String, Object>> selectWeixinTokensBySql(String sql, Object[] args) {
		return jdbcTemplateForDs1.queryForList(sql, args);
	}
	
	public int updateByPrimaryKeySelective(WeixinToken record) {
		return WeixinTokenMapper.updateByPrimaryKeySelective(record);
	}
	
	public int countByExample(WeixinTokenExample example) {
        return WeixinTokenMapper.countByExample(example);
    }
	
	@Override
	public int insert(WeixinToken record){
       return WeixinTokenMapper.insert(record);
	}
	
	@Override
	public int insertSelective(WeixinToken record){
       return WeixinTokenMapper.insertSelective(record);
    }

	@Override
	public WeixinToken selectByPrimaryKey(int id){
	   return WeixinTokenMapper.selectByPrimaryKey(id);
	}
	
}
