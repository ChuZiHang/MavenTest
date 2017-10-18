package com.lol.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.lol.common.mapper.ds1.UserPermissionMapper;
import com.lol.common.model.ds1.UserPermission;
import com.lol.common.model.ds1.UserPermissionExample;
import com.lol.common.service.UserPermissionService;

@Service("userPermissionService")
public class UserPermissionServiceImpl implements UserPermissionService {

    @Resource
    UserPermissionMapper userPermissionMapper;

    @Resource
    JdbcTemplate jdbcTemplateForDs1;

    @Resource
    TransactionTemplate transactionTemplateForDs1;

    @Override
    public List<UserPermission> selectByExample(UserPermissionExample example) {
        return userPermissionMapper.selectByExample(example);
    }

    @Override
    public void updateUserPermission(final long userId, final List<Long> permissionIds) {
        // 事务处理
        transactionTemplateForDs1.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                // 删除原有权限
                jdbcTemplateForDs1.update("delete from user_permission where user_id = ?", new Object[]{userId});
                // 批量添加新权限
                String sql = "insert into user_permission(user_id,permission_id)values(?,?)";
                List<Object[]> batchArgs = new ArrayList<Object[]>();
                for(Long permId: permissionIds) {
                    batchArgs.add(new Object[]{userId, permId});
                }
                jdbcTemplateForDs1.batchUpdate(sql, batchArgs);
            }
        });
    }

    @Override
    public int deleteByExample(UserPermissionExample example) {
        return userPermissionMapper.deleteByExample(example);
    }
}
