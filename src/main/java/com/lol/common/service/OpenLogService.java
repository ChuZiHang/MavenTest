package com.lol.common.service;

import java.util.List;

import com.lol.common.model.ds1.OpenLog;
import com.lol.common.model.ds1.OpenLogExample;

public interface OpenLogService {

	  List<OpenLog> selectByExample(OpenLogExample example);
	  int countByExample(OpenLogExample example);
}
