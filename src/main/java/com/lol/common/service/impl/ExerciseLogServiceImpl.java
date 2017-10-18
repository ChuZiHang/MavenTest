package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.ExerciseLogMapper;
import com.lol.common.model.ds1.ExerciseLog;
import com.lol.common.model.ds1.ExerciseLogExample;
import com.lol.common.service.ExerciseLogService;

@Service("exerciseLogService")
public class ExerciseLogServiceImpl implements ExerciseLogService {

    @Resource
    ExerciseLogMapper ExerciseLogMapper;

    @Override
    public int countByExample(ExerciseLogExample example) {
        return ExerciseLogMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ExerciseLogExample example) {
        return ExerciseLogMapper.deleteByExample(example);
    }

    @Override
    public int insert(ExerciseLog record) {
        return ExerciseLogMapper.insert(record);
    }

    @Override
    public int insertSelective(ExerciseLog record) {
        return ExerciseLogMapper.insertSelective(record);
    }

    @Override
    public List<ExerciseLog> selectByExample(ExerciseLogExample example) {
        return ExerciseLogMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(ExerciseLog record, ExerciseLogExample example) {
        return ExerciseLogMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(ExerciseLog record, ExerciseLogExample example) {
        return ExerciseLogMapper.updateByExample(record, example);
    }

    @Override
    public ExerciseLog selectByPrimaryKey(long id) {
        return ExerciseLogMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ExerciseLog record) {
        return ExerciseLogMapper.updateByPrimaryKeySelective(record);
    }

    public int deleteByPrimaryKey(long id) {
        return ExerciseLogMapper.deleteByPrimaryKey(id);
    }

}
