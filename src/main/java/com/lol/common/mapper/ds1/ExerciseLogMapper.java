package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.ExerciseLog;
import com.lol.common.model.ds1.ExerciseLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExerciseLogMapper {
    int countByExample(ExerciseLogExample example);

    int deleteByExample(ExerciseLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExerciseLog record);

    int insertSelective(ExerciseLog record);

    List<ExerciseLog> selectByExample(ExerciseLogExample example);

    ExerciseLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExerciseLog record, @Param("example") ExerciseLogExample example);

    int updateByExample(@Param("record") ExerciseLog record, @Param("example") ExerciseLogExample example);

    int updateByPrimaryKeySelective(ExerciseLog record);

    int updateByPrimaryKey(ExerciseLog record);
}