package com.lol.common.service;

import java.util.List;
import java.util.Map;

import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;

public interface CustInfoService {

	// 获取所有分店
	List<CustInfo> selectCustInfosByExample(CustInfoExample example);
	
	List<Map<String, Object>> selectCustInfosBySql(String sql,Object[] args);
	
	public int updateByPrimaryKeySelective(CustInfo record);
	
	public int countByExample(CustInfoExample example);
	
	 public int insert(CustInfo record) ;
	 
	 public int insertSelective(CustInfo record) ;
	 
	 public CustInfo selectByPrimaryKey(long id);

}
