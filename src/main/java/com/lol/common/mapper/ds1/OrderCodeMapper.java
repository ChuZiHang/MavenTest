package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.OrderCode;
import com.lol.common.model.ds1.OrderCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderCodeMapper {
    int countByExample(OrderCodeExample example);

    int deleteByExample(OrderCodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderCode record);

    int insertSelective(OrderCode record);

    List<OrderCode> selectByExample(OrderCodeExample example);

    OrderCode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderCode record, @Param("example") OrderCodeExample example);

    int updateByExample(@Param("record") OrderCode record, @Param("example") OrderCodeExample example);

    int updateByPrimaryKeySelective(OrderCode record);

    int updateByPrimaryKey(OrderCode record);
}