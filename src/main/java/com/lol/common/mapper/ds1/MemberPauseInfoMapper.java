package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.MemberPauseInfo;
import com.lol.common.model.ds1.MemberPauseInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberPauseInfoMapper {
    int countByExample(MemberPauseInfoExample example);

    int deleteByExample(MemberPauseInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberPauseInfo record);

    int insertSelective(MemberPauseInfo record);

    List<MemberPauseInfo> selectByExample(MemberPauseInfoExample example);

    MemberPauseInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberPauseInfo record, @Param("example") MemberPauseInfoExample example);

    int updateByExample(@Param("record") MemberPauseInfo record, @Param("example") MemberPauseInfoExample example);

    int updateByPrimaryKeySelective(MemberPauseInfo record);

    int updateByPrimaryKey(MemberPauseInfo record);
}