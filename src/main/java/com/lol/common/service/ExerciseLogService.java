package com.lol.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds1.ExerciseLog;
import com.lol.common.model.ds1.ExerciseLogExample;

public interface ExerciseLogService {

    int countByExample(ExerciseLogExample example);

    int deleteByExample(ExerciseLogExample example);

    int insert(ExerciseLog record);

    int insertSelective(ExerciseLog record);

    List<ExerciseLog> selectByExample(ExerciseLogExample example);

    int updateByExampleSelective(@Param("record") ExerciseLog record, @Param("example") ExerciseLogExample example);

    int updateByExample(@Param("record") ExerciseLog record, @Param("example") ExerciseLogExample example);

    ExerciseLog selectByPrimaryKey(long id);

    public int updateByPrimaryKeySelective(ExerciseLog record);

    public int deleteByPrimaryKey(long id);

}
