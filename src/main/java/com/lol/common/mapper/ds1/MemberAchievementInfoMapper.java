package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.MemberAchievementInfo;
import com.lol.common.model.ds1.MemberAchievementInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberAchievementInfoMapper {
    int countByExample(MemberAchievementInfoExample example);

    int deleteByExample(MemberAchievementInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberAchievementInfo record);

    int insertSelective(MemberAchievementInfo record);

    List<MemberAchievementInfo> selectByExample(MemberAchievementInfoExample example);

    MemberAchievementInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberAchievementInfo record, @Param("example") MemberAchievementInfoExample example);

    int updateByExample(@Param("record") MemberAchievementInfo record, @Param("example") MemberAchievementInfoExample example);

    int updateByPrimaryKeySelective(MemberAchievementInfo record);

    int updateByPrimaryKey(MemberAchievementInfo record);
}