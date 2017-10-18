package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.OrderFinishLog;
import com.lol.common.model.ds1.OrderFinishLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderFinishLogMapper {
    int countByExample(OrderFinishLogExample example);

    int deleteByExample(OrderFinishLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderFinishLog record);

    int insertSelective(OrderFinishLog record);

    List<OrderFinishLog> selectByExample(OrderFinishLogExample example);

    OrderFinishLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderFinishLog record, @Param("example") OrderFinishLogExample example);

    int updateByExample(@Param("record") OrderFinishLog record, @Param("example") OrderFinishLogExample example);

    int updateByPrimaryKeySelective(OrderFinishLog record);

    int updateByPrimaryKey(OrderFinishLog record);
}