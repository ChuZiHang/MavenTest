package com.lol.common.mapper.ds0;

import com.lol.common.model.ds0.SmsQueue;
import com.lol.common.model.ds0.SmsQueueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsQueueMapper {
    int countByExample(SmsQueueExample example);

    int deleteByExample(SmsQueueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsQueue record);

    int insertSelective(SmsQueue record);

    List<SmsQueue> selectByExample(SmsQueueExample example);

    SmsQueue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsQueue record, @Param("example") SmsQueueExample example);

    int updateByExample(@Param("record") SmsQueue record, @Param("example") SmsQueueExample example);

    int updateByPrimaryKeySelective(SmsQueue record);

    int updateByPrimaryKey(SmsQueue record);
}