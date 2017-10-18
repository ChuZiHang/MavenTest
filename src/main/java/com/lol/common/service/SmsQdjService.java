package com.lol.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds1.SmsQdj;
import com.lol.common.model.ds1.SmsQdjExample;

public interface SmsQdjService {

    int countByExample(SmsQdjExample example);

    int deleteByExample(SmsQdjExample example);

    int insert(SmsQdj record);

    int insertSelective(SmsQdj record);

    List<SmsQdj> selectByExample(SmsQdjExample example);

    int updateByExampleSelective(@Param("record") SmsQdj record, @Param("example") SmsQdjExample example);

    int updateByExample(@Param("record") SmsQdj record, @Param("example") SmsQdjExample example);

    SmsQdj selectByPrimaryKey(long id);

    public int updateByPrimaryKeySelective(SmsQdj record);

    public int deleteByPrimaryKey(long id);

}
