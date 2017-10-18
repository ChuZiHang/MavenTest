package com.lol.common.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds1.MemberPauseInfo;
import com.lol.common.model.ds1.MemberPauseInfoExample;

public interface MemberPauseInfoService {

    int countByExample(MemberPauseInfoExample example);

    int deleteByExample(MemberPauseInfoExample example);

    int deleteByPrimaryKey(int id);

    int insert(MemberPauseInfo record);

    int insertSelective(MemberPauseInfo record);

    List<MemberPauseInfo> selectByExample(MemberPauseInfoExample example);

    MemberPauseInfo selectByPrimaryKey(int id);

    int updateByExampleSelective(@Param("record") MemberPauseInfo record, @Param("example") MemberPauseInfoExample example);

    int updateByExample(@Param("record") MemberPauseInfo record, @Param("example") MemberPauseInfoExample example);

    int updateByPrimaryKeySelective(MemberPauseInfo record);

    int updateByPrimaryKey(MemberPauseInfo record);
    
    List<Map<String, Object>> queryForList(String sql, Object[] args);

    int executeSql(String sql, Object[] args);
    
    
}
