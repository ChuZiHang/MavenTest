package com.lol.common.mapper.ds0;

import com.lol.common.model.ds0.InfoBank;
import com.lol.common.model.ds0.InfoBankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InfoBankMapper {
    int countByExample(InfoBankExample example);

    int deleteByExample(InfoBankExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InfoBank record);

    int insertSelective(InfoBank record);

    List<InfoBank> selectByExample(InfoBankExample example);

    InfoBank selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InfoBank record, @Param("example") InfoBankExample example);

    int updateByExample(@Param("record") InfoBank record, @Param("example") InfoBankExample example);

    int updateByPrimaryKeySelective(InfoBank record);

    int updateByPrimaryKey(InfoBank record);
}