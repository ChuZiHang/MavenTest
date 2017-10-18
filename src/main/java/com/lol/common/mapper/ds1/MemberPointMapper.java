package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.MemberPoint;
import com.lol.common.model.ds1.MemberPointExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberPointMapper {
    int countByExample(MemberPointExample example);

    int deleteByExample(MemberPointExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberPoint record);

    int insertSelective(MemberPoint record);

    List<MemberPoint> selectByExample(MemberPointExample example);

    MemberPoint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberPoint record, @Param("example") MemberPointExample example);

    int updateByExample(@Param("record") MemberPoint record, @Param("example") MemberPointExample example);

    int updateByPrimaryKeySelective(MemberPoint record);

    int updateByPrimaryKey(MemberPoint record);
}