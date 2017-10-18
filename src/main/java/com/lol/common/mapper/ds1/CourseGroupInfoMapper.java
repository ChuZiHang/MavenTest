package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.CourseGroupInfo;
import com.lol.common.model.ds1.CourseGroupInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseGroupInfoMapper {
    int countByExample(CourseGroupInfoExample example);

    int deleteByExample(CourseGroupInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseGroupInfo record);

    int insertSelective(CourseGroupInfo record);

    List<CourseGroupInfo> selectByExampleWithBLOBs(CourseGroupInfoExample example);

    List<CourseGroupInfo> selectByExample(CourseGroupInfoExample example);

    CourseGroupInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseGroupInfo record, @Param("example") CourseGroupInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") CourseGroupInfo record, @Param("example") CourseGroupInfoExample example);

    int updateByExample(@Param("record") CourseGroupInfo record, @Param("example") CourseGroupInfoExample example);

    int updateByPrimaryKeySelective(CourseGroupInfo record);

    int updateByPrimaryKeyWithBLOBs(CourseGroupInfo record);

    int updateByPrimaryKey(CourseGroupInfo record);
}