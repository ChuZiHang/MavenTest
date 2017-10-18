package com.lol.common.service;

import java.util.List;

import com.lol.common.model.ds1.MemberAchievementInfo;
import com.lol.common.model.ds1.MemberAchievementInfoExample;

public interface AchieveService {
	int countByExample(MemberAchievementInfoExample example);

	int updateByPrimaryKeySelective(MemberAchievementInfo record);

	int insertSelective(MemberAchievementInfo record);

	List<MemberAchievementInfo> selectByExample(
			MemberAchievementInfoExample example);

	MemberAchievementInfo selectByPrimaryKey(Integer id);
}
