
package com.lol.common.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lol.common.model.ds1.CoachInfo;
import com.lol.common.model.ds1.CoachInfoExample;
import com.lol.common.model.ds1.CoursePriceExample;

public interface CoachInfoService {

    int insertSelective(CoachInfo record);

    CoachInfo selectByPrimaryKey(long id);

    List<CoachInfo> selectByExample(CoachInfoExample example);

    int countByExample(CoachInfoExample example);

    public List<Map<String, Object>> getList(String selectsql);

    int updateByPrimaryKeySelective(CoachInfo coachInfo);

    // 获取教练的评论分数
    float getScoureAvg(long id, int custId);

    // 获取教练评论总条数
    int getCountCommentAndMember(long id);

    // 获取教练评论人和会员信息
    List<Map<String, Object>> getCommentAndMember(long id, int currentPage, int pageSize);

    // 售卖条数
    public int getCountOrders(long id);

    // 以售卖课时
    int getCountOrder(long id);

    // 已上课课时
    int getCountReady(long id);

    // 待上课
    List<Map<String, Object>> getCommentCourse(long id);

    // 已完成
    List<Map<String, Object>> getCommentReady(long id);

    // 退款会员
    List<Map<String, Object>> getCommentRefund(long id);

    // 以上课数
    int selectCountNum(CoursePriceExample example);

    int selectAllCountNum(CoursePriceExample cpExample);

    HSSFWorkbook exportReport(List<Map<String, Object>> datas, String[] hellName, String string);

    HSSFWorkbook exportContent(List<Map<String, Object>> datas, String[] args, String title);

    List<Map<String, Object>> selectBySql(String sql, Object[] args);

}
