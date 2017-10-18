package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.UserOrderLog;
import com.lol.common.model.ds1.UserOrderLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserOrderLogMapper {
    int countByExample(UserOrderLogExample example);

    int deleteByExample(UserOrderLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserOrderLog record);

    int insertSelective(UserOrderLog record);

    List<UserOrderLog> selectByExample(UserOrderLogExample example);

    UserOrderLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserOrderLog record, @Param("example") UserOrderLogExample example);

    int updateByExample(@Param("record") UserOrderLog record, @Param("example") UserOrderLogExample example);

    int updateByPrimaryKeySelective(UserOrderLog record);

    int updateByPrimaryKey(UserOrderLog record);
}