package com.lol.common.service;

import java.util.List;
import java.util.Map;

import com.lol.common.model.ds1.CodeInfo;
import com.lol.common.model.ds1.CodeInfoExample;
import com.lol.common.model.ds1.CodeList;
import com.lol.common.model.ds1.CodeListExample;

public interface CodeService {
	

	public List<Map<String, Object>> selectcodeBySql(String sql, Object[] args);

	public List<CodeInfo> selectCodeInfoByExample(CodeInfoExample example);

	public CodeInfo selectCodeInfoByPrimaryKey(int id);
	
	public int insertCodeInfo(CodeInfo codeInfo);
	
	public int countCodeInfo(CodeInfoExample example);
	
	public List<CodeList> selectCodeByExample(CodeListExample example);

	public CodeList selectCodeByPrimaryKey(int id);
	
	public int insertCode(CodeList codeList) ;
	
	public int countCodeList(CodeListExample example);
	
	public int updateCodeInfoByPrimaryKeySelective(CodeInfo record);
	
	public int updateCodeInfoByExample(CodeInfo record,CodeInfoExample example);

	public int updateCodeByPrimaryKeySelective(CodeList record);

	public  int insertAndGetKey(final String sql, final CodeList bean);
}
