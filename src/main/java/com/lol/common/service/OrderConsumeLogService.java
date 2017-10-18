package com.lol.common.service;

import java.util.List;

import com.lol.common.model.ds1.OrderConsumeLog;
import com.lol.common.model.ds1.OrderConsumeLogExample;



public interface OrderConsumeLogService {

	 int insertSelective(OrderConsumeLog record);
	 
	  int countByExample(OrderConsumeLogExample example);
	  
	  List<OrderConsumeLog> selectByExample(OrderConsumeLogExample example);
}
