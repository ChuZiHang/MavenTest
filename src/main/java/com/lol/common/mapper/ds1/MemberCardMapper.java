package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.MemberCard;
import com.lol.common.model.ds1.MemberCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberCardMapper {
    int countByExample(MemberCardExample example);

    int deleteByExample(MemberCardExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberCard record);

    int insertSelective(MemberCard record);

    List<MemberCard> selectByExample(MemberCardExample example);

    MemberCard selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberCard record, @Param("example") MemberCardExample example);

    int updateByExample(@Param("record") MemberCard record, @Param("example") MemberCardExample example);

    int updateByPrimaryKeySelective(MemberCard record);

    int updateByPrimaryKey(MemberCard record);
}