package com.lol.common.service;

import java.util.List;
import java.util.Map;

import com.lol.common.model.ds1.PayDrawmoney;
import com.lol.common.model.ds1.PayDrawmoneyExample;

public interface AccountService {
	
	
	 int insertSelective(PayDrawmoney record);
	 
	 List<PayDrawmoney> selectByExample(PayDrawmoneyExample example);
	 
	 int countByExample(PayDrawmoneyExample example);
	 
	 public List<Map<String, Object>> getList(String sql,Object [] object);

	 
}
