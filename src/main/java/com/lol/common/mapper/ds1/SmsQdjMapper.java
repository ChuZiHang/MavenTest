package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.SmsQdj;
import com.lol.common.model.ds1.SmsQdjExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsQdjMapper {
    int countByExample(SmsQdjExample example);

    int deleteByExample(SmsQdjExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsQdj record);

    int insertSelective(SmsQdj record);

    List<SmsQdj> selectByExample(SmsQdjExample example);

    SmsQdj selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsQdj record, @Param("example") SmsQdjExample example);

    int updateByExample(@Param("record") SmsQdj record, @Param("example") SmsQdjExample example);

    int updateByPrimaryKeySelective(SmsQdj record);

    int updateByPrimaryKey(SmsQdj record);
}