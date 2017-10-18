package com.lol.common.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds1.MemberPointInfo;
import com.lol.common.model.ds1.MemberPointInfoExample;

public interface MemberPointInfoService {

    int countByExample(MemberPointInfoExample example);

    int insert(MemberPointInfo record);

    int insertSelective(MemberPointInfo record);

    List<MemberPointInfo> selectByExample(MemberPointInfoExample example);

    int updateByExampleSelective(@Param("record") MemberPointInfo record, @Param("example") MemberPointInfoExample example);

    int updateByExample(@Param("record") MemberPointInfo record, @Param("example") MemberPointInfoExample example);

    MemberPointInfo selectByPrimaryKey(int id);

    public int updateByPrimaryKeySelective(MemberPointInfo record);

    List<Map<String, Object>> queryForList(String sql, Object[] args);
}