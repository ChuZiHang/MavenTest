package com.lol.common.mapper.merchant;

import com.lol.common.model.merchant.OrderSms;
import com.lol.common.model.merchant.OrderSmsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderSmsMapper {
    int countByExample(OrderSmsExample example);

    int deleteByExample(OrderSmsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderSms record);

    int insertSelective(OrderSms record);

    List<OrderSms> selectByExample(OrderSmsExample example);

    OrderSms selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderSms record, @Param("example") OrderSmsExample example);

    int updateByExample(@Param("record") OrderSms record, @Param("example") OrderSmsExample example);

    int updateByPrimaryKeySelective(OrderSms record);

    int updateByPrimaryKey(OrderSms record);
}