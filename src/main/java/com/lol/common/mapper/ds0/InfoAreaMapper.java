package com.lol.common.mapper.ds0;

import com.lol.common.model.ds0.InfoArea;
import com.lol.common.model.ds0.InfoAreaExample;
import com.lol.common.model.ds0.InfoAreaWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InfoAreaMapper {
    int countByExample(InfoAreaExample example);

    int deleteByExample(InfoAreaExample example);

    int deleteByPrimaryKey(Integer treeId);

    int insert(InfoAreaWithBLOBs record);

    int insertSelective(InfoAreaWithBLOBs record);

    List<InfoAreaWithBLOBs> selectByExampleWithBLOBs(InfoAreaExample example);

    List<InfoArea> selectByExample(InfoAreaExample example);

    InfoAreaWithBLOBs selectByPrimaryKey(Integer treeId);

    int updateByExampleSelective(@Param("record") InfoAreaWithBLOBs record, @Param("example") InfoAreaExample example);

    int updateByExampleWithBLOBs(@Param("record") InfoAreaWithBLOBs record, @Param("example") InfoAreaExample example);

    int updateByExample(@Param("record") InfoArea record, @Param("example") InfoAreaExample example);

    int updateByPrimaryKeySelective(InfoAreaWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InfoAreaWithBLOBs record);

    int updateByPrimaryKey(InfoArea record);
}