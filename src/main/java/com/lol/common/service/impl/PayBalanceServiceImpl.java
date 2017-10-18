package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.PayBalanceMapper;
import com.lol.common.model.ds1.PayBalance;
import com.lol.common.model.ds1.PayBalanceExample;
import com.lol.common.model.ds1.PayBalanceWithBLOBs;
import com.lol.common.service.PayBalanceService;

/**
 * 支付回调处理
 * 
 * @author cunxing
 *
 */
@Service("payBalanceService")
public class PayBalanceServiceImpl implements PayBalanceService {

    @Resource
    private PayBalanceMapper payBalanceMapper;

    @Override
    public int insertSelective(PayBalanceWithBLOBs record) {
        return payBalanceMapper.insertSelective(record);
    }

    @Override
    public List<PayBalanceWithBLOBs> selectByExampleWithBLOBs(PayBalanceExample example) {
        return payBalanceMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<PayBalance> selectByExample(PayBalanceExample example) {
        return payBalanceMapper.selectByExample(example);
    }

    @Override
    public PayBalanceWithBLOBs selectByPrimaryKey(Long id) {
        return payBalanceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(PayBalanceWithBLOBs record, PayBalanceExample example) {
        return payBalanceMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(PayBalanceWithBLOBs record) {
        return payBalanceMapper.updateByPrimaryKeySelective(record);
    }

}
