package com.lol.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.MemberPointInfoMapper;
import com.lol.common.model.ds1.MemberPointInfo;
import com.lol.common.model.ds1.MemberPointInfoExample;
import com.lol.common.service.MemberPointInfoService;

@Service("MemberPointInfoService")
public class MemberPointInfoServiceImpl implements MemberPointInfoService {

    @Resource
    private MemberPointInfoMapper MemberPointInfoMapper;

    @Resource
    private JdbcTemplate jdbcTemplateForDs1;

    @Override
    public int countByExample(MemberPointInfoExample example) {
        return MemberPointInfoMapper.countByExample(example);
    }

    @Override
    public int insert(MemberPointInfo record) {
        return MemberPointInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(MemberPointInfo record) {
        return MemberPointInfoMapper.insertSelective(record);
    }

    @Override
    public List<MemberPointInfo> selectByExample(MemberPointInfoExample example) {
        return MemberPointInfoMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(MemberPointInfo record, MemberPointInfoExample example) {
        return MemberPointInfoMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(MemberPointInfo record, MemberPointInfoExample example) {
        return MemberPointInfoMapper.updateByExample(record, example);
    }

    @Override
    public MemberPointInfo selectByPrimaryKey(int id) {
        return MemberPointInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MemberPointInfo record) {
        return MemberPointInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Map<String, Object>> queryForList(String sql, Object[] args) {
        return jdbcTemplateForDs1.queryForList(sql, args);
    }

}
