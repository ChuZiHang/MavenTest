package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds0.SysAuthRuleMapper;
import com.lol.common.model.ds0.SysAuthRule;
import com.lol.common.model.ds0.SysAuthRuleExample;
import com.lol.common.service.SysAuthRuleService;

@Service("sysAuthRuleService")
public class SysAuthRuleServiceImpl implements SysAuthRuleService {

	@Resource
	SysAuthRuleMapper sysAuthRuleMapper;

	@Override
	public int countByExample(SysAuthRuleExample example) {
		return sysAuthRuleMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(SysAuthRuleExample example) {
		return sysAuthRuleMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sysAuthRuleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysAuthRule record) {
		return sysAuthRuleMapper.insert(record);
	}

	@Override
	public int insertSelective(SysAuthRule record) {
		return sysAuthRuleMapper.insertSelective(record);
	}

	@Override
	public List<SysAuthRule> selectByExample(SysAuthRuleExample example) {
		return sysAuthRuleMapper.selectByExample(example);
	}

	@Override
	public SysAuthRule selectByPrimaryKey(Long id) {
		return sysAuthRuleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(SysAuthRule record, SysAuthRuleExample example) {
		return sysAuthRuleMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(SysAuthRule record, SysAuthRuleExample example) {
		return sysAuthRuleMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(SysAuthRule record) {
		return sysAuthRuleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysAuthRule record) {
		return sysAuthRuleMapper.updateByPrimaryKey(record);
	}

}
