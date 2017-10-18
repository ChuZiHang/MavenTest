package com.lol.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.CustInfoMapper;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;
import com.lol.common.service.CustInfoService;

@Service("custInfoService")
public class CustInfoServiceImpl implements CustInfoService {

	@Resource
	private CustInfoMapper custInfoMapper;
	
	@Resource
	private JdbcTemplate jdbcTemplateForDs1;
	
	@Override
	public List<CustInfo> selectCustInfosByExample(CustInfoExample example) {
		return custInfoMapper.selectByExample(example);
	}

	@Override
	public List<Map<String, Object>> selectCustInfosBySql(String sql, Object[] args) {
		return jdbcTemplateForDs1.queryForList(sql, args);
	}
	
	public int updateByPrimaryKeySelective(CustInfo record) {
		return custInfoMapper.updateByPrimaryKeySelective(record);
	}
	
	public int countByExample(CustInfoExample example) {
        return custInfoMapper.countByExample(example);
    }
	
	@Override
	public int insert(CustInfo record){
       return custInfoMapper.insert(record);
	}
	
	@Override
	public int insertSelective(CustInfo record){
       return custInfoMapper.insertSelective(record);
    }

	@Override
	public CustInfo selectByPrimaryKey(long id){
	   return custInfoMapper.selectByPrimaryKey(id);
	}
	
}
