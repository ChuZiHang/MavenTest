package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.CoachInfo;
import com.lol.common.model.ds1.CoachInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CoachInfoMapper {
    int countByExample(CoachInfoExample example);

    int deleteByExample(CoachInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CoachInfo record);

    int insertSelective(CoachInfo record);

    List<CoachInfo> selectByExampleWithBLOBs(CoachInfoExample example);

    List<CoachInfo> selectByExample(CoachInfoExample example);

    CoachInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CoachInfo record, @Param("example") CoachInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") CoachInfo record, @Param("example") CoachInfoExample example);

    int updateByExample(@Param("record") CoachInfo record, @Param("example") CoachInfoExample example);

    int updateByPrimaryKeySelective(CoachInfo record);

    int updateByPrimaryKeyWithBLOBs(CoachInfo record);

    int updateByPrimaryKey(CoachInfo record);
}