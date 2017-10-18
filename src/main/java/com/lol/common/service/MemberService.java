package com.lol.common.service;

import java.util.List;
import java.util.Map;

import com.lol.common.model.ds1.MemberCard;
import com.lol.common.model.ds1.MemberCardExample;
import com.lol.common.model.ds1.MemberInfo;
import com.lol.common.model.ds1.MemberInfoExample;

public interface MemberService {
	

	List<MemberInfo> selectMemberByExample(MemberInfoExample example);
	
	List<Map<String, Object>> selectMemberBySql(String sql, Object[] args);
	
	MemberInfo selectByPrimaryKey(long memberId);
	
	
	MemberCard selectMemberCardByPrimaryKey(long memberId);
	
	
	List<MemberCard> selectMemberCardsByExample(MemberCardExample example);
	
	
	int countByExample(MemberCardExample example);
	
	
	int insertSelective(MemberInfo info);

    int insertCardSelective(MemberCard memberCard);
    
    int updateByPrimaryKeySelective(MemberInfo record);
    
    int updateCardByPrimaryKeySelective(MemberCard record);
	
}
