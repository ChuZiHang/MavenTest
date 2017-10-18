package com.lol.common.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds1.MemberPoint;
import com.lol.common.model.ds1.MemberPointExample;

public interface MemberPointService {

    int countByExample(MemberPointExample example);

    int insert(MemberPoint record);

    int insertSelective(MemberPoint record);

    List<MemberPoint> selectByExample(MemberPointExample example);

    int updateByExampleSelective(@Param("record") MemberPoint record, @Param("example") MemberPointExample example);

    int updateByExample(@Param("record") MemberPoint record, @Param("example") MemberPointExample example);

    MemberPoint selectByPrimaryKey(int id);

    public int updateByPrimaryKeySelective(MemberPoint record);
    
    List<Map<String, Object>> queryForList(String sql, Object[] args);


}