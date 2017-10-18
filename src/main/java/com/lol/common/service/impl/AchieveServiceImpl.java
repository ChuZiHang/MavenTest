package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.MemberAchievementInfoMapper;
import com.lol.common.model.ds1.MemberAchievementInfo;
import com.lol.common.model.ds1.MemberAchievementInfoExample;
import com.lol.common.service.AchieveService;
@Service
public class AchieveServiceImpl implements AchieveService {

	@Resource
	private MemberAchievementInfoMapper memberAchievementInfoMapper;
	@Override
	public int updateByPrimaryKeySelective(MemberAchievementInfo record) {
		return memberAchievementInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertSelective(MemberAchievementInfo record) {
		return memberAchievementInfoMapper.insertSelective(record);
	}

	@Override
	public List<MemberAchievementInfo> selectByExample(
			MemberAchievementInfoExample example) {
		return memberAchievementInfoMapper.selectByExample(example);
	}

	@Override
	public MemberAchievementInfo selectByPrimaryKey(Integer id) {
		return memberAchievementInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int countByExample(MemberAchievementInfoExample example) {
		return memberAchievementInfoMapper.countByExample(example) ;
	}

}
