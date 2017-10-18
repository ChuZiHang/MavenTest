package com.lol.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.MemberPointMapper;
import com.lol.common.model.ds1.MemberPoint;
import com.lol.common.model.ds1.MemberPointExample;
import com.lol.common.service.MemberPointService;

@Service("memberPointService")
public class MemberPointServiceImpl implements MemberPointService {

    @Resource
    private MemberPointMapper memberPointMapper;
    
    @Resource
    private JdbcTemplate jdbcTemplateForDs1;

    @Override
    public int countByExample(MemberPointExample example) {
        return memberPointMapper.countByExample(example);
    }

    @Override
    public int insert(MemberPoint record) {
        return memberPointMapper.insert(record);
    }

    @Override
    public int insertSelective(MemberPoint record) {
        return memberPointMapper.insertSelective(record);
    }

    @Override
    public List<MemberPoint> selectByExample(MemberPointExample example) {
        return memberPointMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(MemberPoint record, MemberPointExample example) {
        return memberPointMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(MemberPoint record, MemberPointExample example) {
        return memberPointMapper.updateByExample(record, example);
    }

    @Override
    public MemberPoint selectByPrimaryKey(int id) {
        return memberPointMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MemberPoint record) {
        return memberPointMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Map<String, Object>> queryForList(String sql, Object[] args) {
        return jdbcTemplateForDs1.queryForList(sql, args);
    }



}
