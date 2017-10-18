package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.SysMessage;
import com.lol.common.model.ds1.SysMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMessageMapper {
    int countByExample(SysMessageExample example);

    int deleteByExample(SysMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysMessage record);

    int insertSelective(SysMessage record);

    List<SysMessage> selectByExample(SysMessageExample example);

    SysMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysMessage record, @Param("example") SysMessageExample example);

    int updateByExample(@Param("record") SysMessage record, @Param("example") SysMessageExample example);

    int updateByPrimaryKeySelective(SysMessage record);

    int updateByPrimaryKey(SysMessage record);
}