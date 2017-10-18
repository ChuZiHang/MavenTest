package com.lol.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds1.MemberPointInfo;
import com.lol.common.model.ds1.MemberPointInfoExample;

public interface PointService {
	int countByExample(MemberPointInfoExample example);

	int insertSelective(MemberPointInfo record);

	List<MemberPointInfo> selectByExample(MemberPointInfoExample example);

	MemberPointInfo selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") MemberPointInfo record,
			@Param("example") MemberPointInfoExample example);

	int updateByPrimaryKeySelective(MemberPointInfo record);
}
