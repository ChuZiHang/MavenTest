package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.OrderConsumeLog;
import com.lol.common.model.ds1.OrderConsumeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderConsumeLogMapper {
    int countByExample(OrderConsumeLogExample example);

    int deleteByExample(OrderConsumeLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderConsumeLog record);

    int insertSelective(OrderConsumeLog record);

    List<OrderConsumeLog> selectByExample(OrderConsumeLogExample example);

    OrderConsumeLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderConsumeLog record, @Param("example") OrderConsumeLogExample example);

    int updateByExample(@Param("record") OrderConsumeLog record, @Param("example") OrderConsumeLogExample example);

    int updateByPrimaryKeySelective(OrderConsumeLog record);

    int updateByPrimaryKey(OrderConsumeLog record);
}