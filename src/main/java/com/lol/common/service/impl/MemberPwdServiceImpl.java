package com.lol.common.service.impl;

import com.lol.common.mapper.ds1.MemberPwdMapper;
import com.lol.common.model.ds1.MemberPwd;
import com.lol.common.model.ds1.MemberPwdExample;
import com.lol.common.service.MemberPwdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhuwei on 16/9/5.
 */
@Service("memberPwdService")
public class MemberPwdServiceImpl implements MemberPwdService {

    @Resource
    MemberPwdMapper memberPwdMapper;

    @Override
    public List<MemberPwd> selectByExample(MemberPwdExample example) {

        return memberPwdMapper.selectByExample(example);
    }

    @Override
    public MemberPwd selectByPrimaryKey(int id) {
        return memberPwdMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(MemberPwd memberPwd) {
        return memberPwdMapper.insert(memberPwd);
    }

    @Override
    public int count(MemberPwdExample example) {
        return memberPwdMapper.countByExample(example);
    }

    @Override
    public int updateByPrimaryKeySelective(MemberPwd memberPwd) {
        return memberPwdMapper.updateByPrimaryKeySelective(memberPwd);
    }

    @Override
    public int updateByExample(MemberPwd record, MemberPwdExample example) {
        return memberPwdMapper.updateByExampleSelective(record, example);
    }
}
