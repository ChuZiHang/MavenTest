package com.lol.common.mapper.ds0;

import com.lol.common.model.ds0.InfoAreaEx;
import com.lol.common.model.ds0.InfoAreaExExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InfoAreaExMapper {
    int countByExample(InfoAreaExExample example);

    int deleteByExample(InfoAreaExExample example);

    int deleteByPrimaryKey(Double areaId);

    int insert(InfoAreaEx record);

    int insertSelective(InfoAreaEx record);

    List<InfoAreaEx> selectByExample(InfoAreaExExample example);

    InfoAreaEx selectByPrimaryKey(Double areaId);

    int updateByExampleSelective(@Param("record") InfoAreaEx record, @Param("example") InfoAreaExExample example);

    int updateByExample(@Param("record") InfoAreaEx record, @Param("example") InfoAreaExExample example);

    int updateByPrimaryKeySelective(InfoAreaEx record);

    int updateByPrimaryKey(InfoAreaEx record);
}