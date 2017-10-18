package com.lol.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lol.common.bean.CurrentUserInfo;
import com.lol.common.model.ds1.UserLogin;
import com.lol.common.model.ds1.UserLoginExample;

public interface UserLoginService {
	int countByExample(UserLoginExample example);

	int deleteByExample(UserLoginExample example);

	int deleteByPrimaryKey(Long id);

	int insert(UserLogin record);

	int insertSelective(UserLogin record);

	List<UserLogin> selectByExample(UserLoginExample example);

	UserLogin selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") UserLogin record, @Param("example") UserLoginExample example);

	int updateByExample(@Param("record") UserLogin record, @Param("example") UserLoginExample example);

	int updateByPrimaryKeySelective(UserLogin record);

	int updateByPrimaryKey(UserLogin record);

	void testTm();

	void lock(String userName, long time);

	List<UserLogin> getUserLoginByCustId(int custId);

	CurrentUserInfo getCurrentUserInfo();
}
