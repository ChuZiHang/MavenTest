package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.merchant.SaasMerchantInfoMapper;
import com.lol.common.model.merchant.SaasMerchantInfo;
import com.lol.common.model.merchant.SaasMerchantInfoExample;
import com.lol.common.service.SaasMerchantInfoService;

@Service("saasMerchantInfoService")
public class SaasMerchantInfoServiceImpl implements SaasMerchantInfoService {

    @Resource
    SaasMerchantInfoMapper saasMerchantInfoMapper;

    @Override
    public int countByExample(SaasMerchantInfoExample example) {
        return saasMerchantInfoMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(SaasMerchantInfoExample example) {
        return saasMerchantInfoMapper.deleteByExample(example);
    }

    @Override
    public int insert(SaasMerchantInfo record) {
        return saasMerchantInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(SaasMerchantInfo record) {
        return saasMerchantInfoMapper.insertSelective(record);
    }

    @Override
    public List<SaasMerchantInfo> selectByExample(SaasMerchantInfoExample example) {
        return saasMerchantInfoMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(SaasMerchantInfo record, SaasMerchantInfoExample example) {
        return saasMerchantInfoMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(SaasMerchantInfo record, SaasMerchantInfoExample example) {
        return saasMerchantInfoMapper.updateByExample(record, example);
    }

    @Override
    public SaasMerchantInfo selectByPrimaryKey(int id) {
        return saasMerchantInfoMapper.selectByPrimaryKey(id);
    }

}
