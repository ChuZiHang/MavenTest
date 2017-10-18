package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.OpenLog;
import com.lol.common.model.ds1.OpenLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpenLogMapper {
    int countByExample(OpenLogExample example);

    int deleteByExample(OpenLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OpenLog record);

    int insertSelective(OpenLog record);

    List<OpenLog> selectByExample(OpenLogExample example);

    OpenLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OpenLog record, @Param("example") OpenLogExample example);

    int updateByExample(@Param("record") OpenLog record, @Param("example") OpenLogExample example);

    int updateByPrimaryKeySelective(OpenLog record);

    int updateByPrimaryKey(OpenLog record);
}