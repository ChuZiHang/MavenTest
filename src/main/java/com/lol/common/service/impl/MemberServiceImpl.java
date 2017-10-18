package com.lol.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.MemberCardMapper;
import com.lol.common.mapper.ds1.MemberInfoMapper;
import com.lol.common.model.ds1.MemberCard;
import com.lol.common.model.ds1.MemberCardExample;
import com.lol.common.model.ds1.MemberInfo;
import com.lol.common.model.ds1.MemberInfoExample;
import com.lol.common.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberInfoMapper memberInfoMapper;

    @Resource
    private MemberCardMapper memberCardMapper;

    @Resource
    private JdbcTemplate jdbcTemplateForDs1;

    @Override
    public List<Map<String, Object>> selectMemberBySql(String sql, Object[] args) {
        return jdbcTemplateForDs1.queryForList(sql, args);
    }

    @Override
    public List<MemberInfo> selectMemberByExample(MemberInfoExample example) {
        return memberInfoMapper.selectByExample(example);
    }

    @Override
    public MemberInfo selectByPrimaryKey(long memberId) {
        return memberInfoMapper.selectByPrimaryKey(memberId);
    }

    @Override
    public MemberCard selectMemberCardByPrimaryKey(long memberId) {

        return memberCardMapper.selectByPrimaryKey(memberId);
    }

    @Override
    public List<MemberCard> selectMemberCardsByExample(MemberCardExample example) {
        return memberCardMapper.selectByExample(example);
    }

    @Override
    public int countByExample(MemberCardExample example) {
        return memberCardMapper.countByExample(example);
    }

    @Override
    public int insertSelective(MemberInfo info) {
        return memberInfoMapper.insertSelective(info);
    }

    @Override
    public int insertCardSelective(MemberCard memberCard) {
        return memberCardMapper.insertSelective(memberCard);
    }

    @Override
    public int updateByPrimaryKeySelective(MemberInfo record) {

        return memberInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateCardByPrimaryKeySelective(MemberCard record) {
        return memberCardMapper.updateByPrimaryKeySelective(record);
    }

}
