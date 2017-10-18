package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.CourseInfo;
import com.lol.common.model.ds1.CourseInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseInfoMapper {
    int countByExample(CourseInfoExample example);

    int deleteByExample(CourseInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseInfo record);

    int insertSelective(CourseInfo record);

    List<CourseInfo> selectByExampleWithBLOBs(CourseInfoExample example);

    List<CourseInfo> selectByExample(CourseInfoExample example);

    CourseInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseInfo record, @Param("example") CourseInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") CourseInfo record, @Param("example") CourseInfoExample example);

    int updateByExample(@Param("record") CourseInfo record, @Param("example") CourseInfoExample example);

    int updateByPrimaryKeySelective(CourseInfo record);

    int updateByPrimaryKeyWithBLOBs(CourseInfo record);

    int updateByPrimaryKey(CourseInfo record);
}