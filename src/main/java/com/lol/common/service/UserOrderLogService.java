package com.lol.common.service;

import java.util.List;

import com.lol.common.model.ds1.UserOrderLog;
import com.lol.common.model.ds1.UserOrderLogExample;

public interface UserOrderLogService {

	int countByExample(UserOrderLogExample example);
	
	int deleteByExample(UserOrderLogExample example);
	
	int deleteByPrimaryKey(Integer id);
	
	int insert(UserOrderLog record);

    int insertSelective(UserOrderLog record);
    
    List<UserOrderLog> selectByExample(UserOrderLogExample example);

    UserOrderLog selectByPrimaryKey(Integer id);
}
