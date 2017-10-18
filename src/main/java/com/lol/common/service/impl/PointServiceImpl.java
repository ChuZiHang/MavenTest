package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.MemberPointInfoMapper;
import com.lol.common.model.ds1.MemberPointInfo;
import com.lol.common.model.ds1.MemberPointInfoExample;
import com.lol.common.service.PointService;
@Service
public class PointServiceImpl implements PointService {

	@Resource
	private MemberPointInfoMapper memberPointInfoMapper;
	@Override
	public int countByExample(MemberPointInfoExample example) {
		return memberPointInfoMapper.countByExample(example);
	}

	@Override
	public int insertSelective(MemberPointInfo record) {
		return memberPointInfoMapper.insertSelective(record);
	}

	@Override
	public List<MemberPointInfo> selectByExample(MemberPointInfoExample example) {
		return memberPointInfoMapper.selectByExample(example);
	}

	@Override
	public MemberPointInfo selectByPrimaryKey(Integer id) {
		return memberPointInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(MemberPointInfo record,
			MemberPointInfoExample example) {
		return memberPointInfoMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(MemberPointInfo record) {
		return memberPointInfoMapper.updateByPrimaryKeySelective(record);
	}

}
