
package com.lol.common.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds1.UserLog;
import com.lol.common.model.ds1.UserLogExample;

public interface UserLogService {

	int countByExample(UserLogExample example);

    int deleteByExample(UserLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserLog record);

    int insertSelective(UserLog record);

    List<UserLog> selectByExample(UserLogExample example);

    UserLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserLog record, @Param("example") UserLogExample example);

    int updateByExample(@Param("record") UserLog record, @Param("example") UserLogExample example);

    int updateByPrimaryKeySelective(UserLog record);

    int updateByPrimaryKey(UserLog record);
    
    public List<Map<String, Object>> selectBySql(String sql, Object[] args);
}

