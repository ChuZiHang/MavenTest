package com.lol.common.service;

import java.util.List;
import java.util.Map;

import com.lol.common.model.ds1.CourseInfo;
import com.lol.common.model.ds1.CourseInfoExample;
import com.lol.common.model.ds1.CoursePrice;
import com.lol.common.model.ds1.CoursePriceExample;

public interface CourseService {

    // 获取课程
    List<CourseInfo> selectCourseListByExample(CourseInfoExample example);

    // 获取课程总数
    int countByExample(CourseInfoExample example);

    // 获取单个课程详情
    CourseInfo selectCourseInfoByCourseId(long courseId);

    // 修改单个课程详情
    int createCourseInfo(CourseInfo info);

    // 修改单个课程详情
    int updateCourseInfoByPrimaryKeySelective(CourseInfo info);

    // 获取课程的评论分数
    float getScoureAvg(long courseId);

    int insertCoursePriceSelective(CoursePrice record);

    List<CoursePrice> selectCoursePricesByExample(CoursePriceExample example);

    CoursePrice selectCoursePriceByPrimaryKey(long id);

    int updateCoursePriceByPrimaryKeySelective(CoursePrice record);

    // 获取此课程次数
    int selectCoursePriceCountByCourseId(CoursePriceExample example);

    List<Map<String, Object>> selectCoursePriceBySql(String sql, Object[] args);

    void deleteCourseInfoByCourseId(final long CourseId);

    //批量插入课程安排
    void insertCoursePrices(final List<CoursePrice> coursePrices,final int weekStatus);
}
