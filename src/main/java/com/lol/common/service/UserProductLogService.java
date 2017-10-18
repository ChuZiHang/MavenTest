package com.lol.common.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds1.UserProductLog;
import com.lol.common.model.ds1.UserProductLogExample;

public interface UserProductLogService {

	int countByExample(UserProductLogExample example);

    int deleteByExample(UserProductLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserProductLog record);

    int insertSelective(UserProductLog record);

    List<UserProductLog> selectByExample(UserProductLogExample example);

    UserProductLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserProductLog record, @Param("example") UserProductLogExample example);

    int updateByExample(@Param("record") UserProductLog record, @Param("example") UserProductLogExample example);

    int updateByPrimaryKeySelective(UserProductLog record);

    int updateByPrimaryKey(UserProductLog record);
    
    List<Map<String, Object>> selectBySql(String sql,Object[] args);
}
