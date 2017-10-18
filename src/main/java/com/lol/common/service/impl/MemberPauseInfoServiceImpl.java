package com.lol.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.MemberPauseInfoMapper;
import com.lol.common.model.ds1.MemberPauseInfo;
import com.lol.common.model.ds1.MemberPauseInfoExample;
import com.lol.common.service.MemberPauseInfoService;

@Service("MemberPauseInfoService")
public class MemberPauseInfoServiceImpl implements MemberPauseInfoService {

    @Resource
    private MemberPauseInfoMapper MemberPauseInfoMapper;

    @Resource
    private JdbcTemplate jdbcTemplateForDs1;

    @Override
    public int countByExample(MemberPauseInfoExample example) {
        return MemberPauseInfoMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(MemberPauseInfoExample example) {
        return MemberPauseInfoMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(int id) {
        return MemberPauseInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MemberPauseInfo record) {
        return MemberPauseInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(MemberPauseInfo record) {
        return MemberPauseInfoMapper.insertSelective(record);
    }

    @Override
    public List<MemberPauseInfo> selectByExample(MemberPauseInfoExample example) {
        return MemberPauseInfoMapper.selectByExample(example);
    }

    @Override
    public MemberPauseInfo selectByPrimaryKey(int id) {
        return MemberPauseInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(MemberPauseInfo record, MemberPauseInfoExample example) {
        return MemberPauseInfoMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(MemberPauseInfo record, MemberPauseInfoExample example) {
        return MemberPauseInfoMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(MemberPauseInfo record) {
        return MemberPauseInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MemberPauseInfo record) {
        return MemberPauseInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> queryForList(String sql, Object[] args) {
        return jdbcTemplateForDs1.queryForList(sql, args);
    }
    
    @Override
    public int executeSql(String sql, Object[] args) {
        return jdbcTemplateForDs1.update(sql, args);
    }

}
