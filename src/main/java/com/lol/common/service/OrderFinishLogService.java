package com.lol.common.service;

import java.util.List;

import com.lol.common.model.ds1.OrderFinishLog;
import com.lol.common.model.ds1.OrderFinishLogExample;

public interface OrderFinishLogService {

	int countByExample(OrderFinishLogExample example);
	
	int deleteByPrimaryKey(Integer id);
	
	int insert(OrderFinishLog record);
	
	List<OrderFinishLog> selectByExample(OrderFinishLogExample example);
	
	OrderFinishLog selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(OrderFinishLog record);
}
