package com.lol.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.merchant.OrderSms;
import com.lol.common.model.merchant.OrderSmsExample;

public interface OrderSmsService {

    int countByExample(OrderSmsExample example);

    int deleteByExample(OrderSmsExample example);

    int insert(OrderSms record);

    int insertSelective(OrderSms record);

    List<OrderSms> selectByExample(OrderSmsExample example);

    int updateByExampleSelective(@Param("record") OrderSms record, @Param("example") OrderSmsExample example);

    int updateByExample(@Param("record") OrderSms record, @Param("example") OrderSmsExample example);

    OrderSms selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(OrderSms record);

}
