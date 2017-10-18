package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.MemberPointInfo;
import com.lol.common.model.ds1.MemberPointInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberPointInfoMapper {
    int countByExample(MemberPointInfoExample example);

    int deleteByExample(MemberPointInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberPointInfo record);

    int insertSelective(MemberPointInfo record);

    List<MemberPointInfo> selectByExample(MemberPointInfoExample example);

    MemberPointInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberPointInfo record, @Param("example") MemberPointInfoExample example);

    int updateByExample(@Param("record") MemberPointInfo record, @Param("example") MemberPointInfoExample example);

    int updateByPrimaryKeySelective(MemberPointInfo record);

    int updateByPrimaryKey(MemberPointInfo record);
}