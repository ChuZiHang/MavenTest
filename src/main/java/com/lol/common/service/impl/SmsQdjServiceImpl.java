package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.SmsQdjMapper;
import com.lol.common.model.ds1.SmsQdj;
import com.lol.common.model.ds1.SmsQdjExample;
import com.lol.common.service.SmsQdjService;

@Service("smsQdjService")
public class SmsQdjServiceImpl implements SmsQdjService {

    @Resource
    SmsQdjMapper SmsQdjMapper;

    @Override
    public int countByExample(SmsQdjExample example) {
        return SmsQdjMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(SmsQdjExample example) {
        return SmsQdjMapper.deleteByExample(example);
    }

    @Override
    public int insert(SmsQdj record) {
        return SmsQdjMapper.insert(record);
    }

    @Override
    public int insertSelective(SmsQdj record) {
        return SmsQdjMapper.insertSelective(record);
    }

    @Override
    public List<SmsQdj> selectByExample(SmsQdjExample example) {
        return SmsQdjMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(SmsQdj record, SmsQdjExample example) {
        return SmsQdjMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(SmsQdj record, SmsQdjExample example) {
        return SmsQdjMapper.updateByExample(record, example);
    }

    @Override
    public SmsQdj selectByPrimaryKey(long id) {
        return SmsQdjMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SmsQdj record) {
        return SmsQdjMapper.updateByPrimaryKeySelective(record);
    }

    public int deleteByPrimaryKey(long id) {
        return SmsQdjMapper.deleteByPrimaryKey(id);
    }

}
