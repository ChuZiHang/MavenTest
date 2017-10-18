package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.merchant.OrderSmsMapper;
import com.lol.common.model.merchant.OrderSms;
import com.lol.common.model.merchant.OrderSmsExample;
import com.lol.common.service.OrderSmsService;

@Service("orderSmsService")
public class OrderSmsServiceImpl implements OrderSmsService {

    @Resource
    OrderSmsMapper orderSmsMapper;

    @Override
    public int countByExample(OrderSmsExample example) {
        return orderSmsMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(OrderSmsExample example) {
        return orderSmsMapper.deleteByExample(example);
    }

    @Override
    public int insert(OrderSms record) {
        return orderSmsMapper.insert(record);
    }

    @Override
    public int insertSelective(OrderSms record) {
        return orderSmsMapper.insertSelective(record);
    }

    @Override
    public List<OrderSms> selectByExample(OrderSmsExample example) {
        return orderSmsMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderSms record) {
        return orderSmsMapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateByExampleSelective(OrderSms record, OrderSmsExample example) {
        return orderSmsMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(OrderSms record, OrderSmsExample example) {
        return orderSmsMapper.updateByExample(record, example);
    }

    @Override
    public OrderSms selectByPrimaryKey(long id) {
        return orderSmsMapper.selectByPrimaryKey(id);
    }

}
