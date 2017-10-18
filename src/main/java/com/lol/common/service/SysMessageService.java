package com.lol.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds1.SysMessage;
import com.lol.common.model.ds1.SysMessageExample;



public interface SysMessageService {

    int countByExample(SysMessageExample example);

    int deleteByExample(SysMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysMessage record);

    int insertSelective(SysMessage record);

    List<SysMessage> selectByExample(SysMessageExample example);

    SysMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMessage record);

    int updateByPrimaryKey(SysMessage record);
    
    int updateByExampleSelective(@Param("record") SysMessage record, @Param("example") SysMessageExample example);

    int updateByExample(@Param("record") SysMessage record, @Param("example") SysMessageExample example);
    
    void updateMessageStatus(List<SysMessage> messageList);
    
    public  int insertAndGetKey(final String sql, final SysMessage bean);
    
}
