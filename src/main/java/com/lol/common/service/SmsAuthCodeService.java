package com.lol.common.service;

import java.util.List;
import java.util.Map;

import com.lol.common.model.ds1.SmsAuthCode;
import com.lol.common.model.ds1.SmsAuthCodeExample;

public interface SmsAuthCodeService {
	
	
	 int insertSelective(SmsAuthCode record);
	 
	 List<SmsAuthCode> selectByExample(SmsAuthCodeExample example);
	 
	 int countByExample(SmsAuthCodeExample example);
	 
	 public List<Map<String, Object>> getList(String sql,Object [] object);

	 
}
