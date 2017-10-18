package com.lol.common.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.CodeInfoMapper;
import com.lol.common.mapper.ds1.CodeListMapper;
import com.lol.common.model.ds1.CodeInfo;
import com.lol.common.model.ds1.CodeInfoExample;
import com.lol.common.model.ds1.CodeList;
import com.lol.common.model.ds1.CodeListExample;
import com.lol.common.service.CodeService;

@Service("codeService")
public class CodeServiceImpl implements CodeService {

	@Resource
	private CodeInfoMapper codeInfoMapper;

	@Resource
	private CodeListMapper codeListMapper;
	
	@Resource
	private JdbcTemplate jdbcTemplateForDs1;

	public List<Map<String, Object>> selectcodeBySql(String sql, Object[] args) {
		return jdbcTemplateForDs1.queryForList(sql, args);
	}

	public List<CodeInfo> selectCodeInfoByExample(CodeInfoExample example) {
		return codeInfoMapper.selectByExample(example);
	}

	public CodeInfo selectCodeInfoByPrimaryKey(int id) {
		return codeInfoMapper.selectByPrimaryKey(id);
	}
	
	public int insertCodeInfo(CodeInfo codeInfo) {
		return codeInfoMapper.insertSelective(codeInfo);
	}
	
	public int countCodeInfo(CodeInfoExample example) {
		return codeInfoMapper.countByExample(example);
	}
	
	
	public List<CodeList> selectCodeByExample(CodeListExample example) {
		return codeListMapper.selectByExample(example);
	}

	public CodeList selectCodeByPrimaryKey(int id) {
		return codeListMapper.selectByPrimaryKey(id);
	}
	
	public int insertCode(CodeList codeList) {
		return codeListMapper.insertSelective(codeList);
	}
	
	
	public int countCodeList(CodeListExample example) {
		return codeListMapper.countByExample(example);
	}
	
	public int updateCodeInfoByPrimaryKeySelective(CodeInfo record) {
		return codeInfoMapper.updateByPrimaryKeySelective(record);
	}
	
	public int updateCodeInfoByExample(CodeInfo record,CodeInfoExample example) {
		return codeInfoMapper.updateByExample(record, example);
	}

	public int updateCodeByPrimaryKeySelective(CodeList record) {
		return codeListMapper.updateByPrimaryKeySelective(record);
	}
	//插入一条数据返回主键ID
	public  int insertAndGetKey(final String sql, final CodeList bean) {  
	    KeyHolder keyHolder = new GeneratedKeyHolder();  
	    jdbcTemplateForDs1.update(new PreparedStatementCreator() {  
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
	              
	            //String sql_sms = "insert into code_list(create_time,start_time,end_time,num,card_id,card_name,parent_id) values (?,?,?,?,?,?,?)";   
	               PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
	               ps.setDate(1, new java.sql.Date(bean.getCreateTime().getTime()));  
	               ps.setDate(2, new java.sql.Date(bean.getStartTime().getTime()));  
	               ps.setDate(3, new java.sql.Date(bean.getEndTime().getTime()));  
	               ps.setInt(4, bean.getNum());  
	               ps.setLong(5,bean.getCardId());  
	               ps.setString(6, bean.getCardName());  
	               ps.setLong(7, bean.getParentId());  
	               ps.setInt(8, bean.getAllowNum());
	               return ps;  
	        }  
	    }, keyHolder);  
	    int generatedId = keyHolder.getKey().intValue();   
	    return generatedId;  
	} 
	
}
