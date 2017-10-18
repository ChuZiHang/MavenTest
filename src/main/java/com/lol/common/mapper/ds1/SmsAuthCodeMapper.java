package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.SmsAuthCode;
import com.lol.common.model.ds1.SmsAuthCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsAuthCodeMapper {
    int countByExample(SmsAuthCodeExample example);

    int deleteByExample(SmsAuthCodeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsAuthCode record);

    int insertSelective(SmsAuthCode record);

    List<SmsAuthCode> selectByExample(SmsAuthCodeExample example);

    SmsAuthCode selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsAuthCode record, @Param("example") SmsAuthCodeExample example);

    int updateByExample(@Param("record") SmsAuthCode record, @Param("example") SmsAuthCodeExample example);

    int updateByPrimaryKeySelective(SmsAuthCode record);

    int updateByPrimaryKey(SmsAuthCode record);
}