package com.lol.common.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.lol.common.mapper.ds1.SysMessageMapper;
import com.lol.common.model.ds1.SysMessage;
import com.lol.common.model.ds1.SysMessageExample;
import com.lol.common.service.SysMessageService;

@Service("sysMessageService")
public class SysMessageServiceImpl implements SysMessageService{

    @Resource
    private SysMessageMapper sysMessageMapper;
    
    @Resource
    TransactionTemplate transactionTemplateForDs1;
    
    @Resource
    JdbcTemplate jdbcTemplateForDs1;
    
    @Override
    public int countByExample(SysMessageExample example) {
        return sysMessageMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(SysMessageExample example) {
        return sysMessageMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysMessageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysMessage record) {
        return sysMessageMapper.insert(record);
    }

    @Override
    public int insertSelective(SysMessage record) {
        return sysMessageMapper.insertSelective(record);
    }

    @Override
    public List<SysMessage> selectByExample(SysMessageExample example) {
        return sysMessageMapper.selectByExample(example);
    }

    @Override
    public SysMessage selectByPrimaryKey(Integer id) {
        return sysMessageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysMessage record) {
        return sysMessageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysMessage record) {
        return sysMessageMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByExampleSelective(SysMessage record, SysMessageExample example) {
        return sysMessageMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(SysMessage record, SysMessageExample example) {
        return sysMessageMapper.updateByExample(record, example);
    }

    @Override
    public void updateMessageStatus(final List<SysMessage> messageList) {
        // 事务处理
        transactionTemplateForDs1.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                
                String sql = "UPDATE sys_message s SET s.`status` = 1 WHERE s.`id` = ?";
                List<Object[]> batchArgs = new ArrayList<Object[]>();
                
                for(int i = 0; i < messageList.size(); i++) {
                    batchArgs.add(new Object[]{messageList.get(i).getId()});
                }
                jdbcTemplateForDs1.batchUpdate(sql, batchArgs);
            }
        });
    }

    public  int insertAndGetKey(final String sql, final SysMessage bean) {  
        KeyHolder keyHolder = new GeneratedKeyHolder();  
        jdbcTemplateForDs1.update(new PreparedStatementCreator() {  
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
                //String sql_sms = "INSERT INTO sys_message(create_time,message,parent_id) VALUES (?,?,?)";   
                   PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
                   ps.setDate(1, new java.sql.Date(bean.getCreateTime().getTime()));  
                   ps.setString(2, bean.getMessage());  
                   ps.setInt(3, bean.getParentId());  
                   return ps;  
            }  
        }, keyHolder);  
        int generatedId = keyHolder.getKey().intValue();   
        return generatedId;  
    }
    
}
