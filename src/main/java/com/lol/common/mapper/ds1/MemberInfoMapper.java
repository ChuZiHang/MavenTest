package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.MemberInfo;
import com.lol.common.model.ds1.MemberInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberInfoMapper {
    int countByExample(MemberInfoExample example);

    int deleteByExample(MemberInfoExample example);

    int deleteByPrimaryKey(Long memberId);

    int insert(MemberInfo record);

    int insertSelective(MemberInfo record);

    List<MemberInfo> selectByExample(MemberInfoExample example);

    MemberInfo selectByPrimaryKey(Long memberId);

    int updateByExampleSelective(@Param("record") MemberInfo record, @Param("example") MemberInfoExample example);

    int updateByExample(@Param("record") MemberInfo record, @Param("example") MemberInfoExample example);

    int updateByPrimaryKeySelective(MemberInfo record);

    int updateByPrimaryKey(MemberInfo record);
}