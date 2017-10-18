package com.lol.common.service;

import java.util.List;

import com.lol.common.model.ds0.SmsQueue;
import com.lol.common.model.ds0.SmsQueueExample;



public interface SmsQueueService {

	 int countByExample(SmsQueueExample example);
	 
	 List<SmsQueue> selectByExample(SmsQueueExample example);

	 SmsQueue selectByPrimaryKey(Long id);

}
