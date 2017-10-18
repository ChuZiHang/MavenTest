package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.CoursePrice;
import com.lol.common.model.ds1.CoursePriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CoursePriceMapper {
    int countByExample(CoursePriceExample example);

    int deleteByExample(CoursePriceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CoursePrice record);

    int insertSelective(CoursePrice record);

    List<CoursePrice> selectByExample(CoursePriceExample example);

    CoursePrice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CoursePrice record, @Param("example") CoursePriceExample example);

    int updateByExample(@Param("record") CoursePrice record, @Param("example") CoursePriceExample example);

    int updateByPrimaryKeySelective(CoursePrice record);

    int updateByPrimaryKey(CoursePrice record);
}