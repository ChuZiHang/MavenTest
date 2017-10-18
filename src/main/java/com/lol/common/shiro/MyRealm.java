package com.lol.common.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lol.common.Constants;
import com.lol.common.model.ds0.SysPermission;
import com.lol.common.model.ds0.SysPermissionExample;
import com.lol.common.model.ds0.SysRole;
import com.lol.common.model.ds0.SysRoleExample;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;
import com.lol.common.model.ds1.UserLogin;
import com.lol.common.model.ds1.UserLoginExample;
import com.lol.common.model.ds1.UserPermission;
import com.lol.common.model.ds1.UserPermissionExample;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.SysPermissionService;
import com.lol.common.service.SysRoleService;
import com.lol.common.service.UserLoginService;
import com.lol.common.service.UserPermissionService;

/**
 * 自定义Realm
 * 
 * @author cunxing
 *
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private UserPermissionService userPermissionService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private CustInfoService custInfoService;

    @Resource
    private SysPermissionService sysPermissionService;

    @Resource
    JdbcTemplate jdbcTemplateForDs1;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = findRoles(username);
        Set<String> permissions = findPermissions(username);
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        UserLoginExample example = new UserLoginExample();
        example.createCriteria().andUsernameEqualTo(username).andStatusEqualTo(0);
        List<UserLogin> users = userLoginService.selectByExample(example);
        if(users == null || users.size() == 0) {
            throw new UnknownAccountException();// 没找到帐号
        }
        UserLogin user = users.get(0);
        if(user.getIsLock() == 1) {
            throw new LockedAccountException(); // 帐号锁定
        }
        String password = user.getPassword();
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        authenticationInfo.setCredentialsSalt(new MySimpleByteSource(username + user.getSalt()));
        return authenticationInfo;
    }

    private Set<String> findRoles(String username) {
        Set<String> roles = new HashSet<String>();
        List<Map<String, Object>> roleIds = jdbcTemplateForDs1
            .queryForList("SELECT b.role_id FROM user_login a,user_role b WHERE a.id=b.user_id AND a.status=0 AND a.username=?", username);
        if(roleIds != null && roleIds.size() > 0) {
            List<Long> ids = new ArrayList<Long>();
            for(Map<String, Object> roleIdMap: roleIds) {
                ids.add((Long)roleIdMap.get("role_id"));
            }
            SysRoleExample sysRoleExample = new SysRoleExample();
            sysRoleExample.createCriteria().andIdIn(ids);
            List<SysRole> sysRoles = sysRoleService.selectByExample(sysRoleExample);
            for(SysRole sysRole: sysRoles) {
                roles.add(sysRole.getExpression());
            }
        }
        return roles;
    }

    private Set<String> findPermissions(String username) {
        Set<String> permissions = new HashSet<String>();
        UserLoginExample userExample = new UserLoginExample();
        userExample.createCriteria().andUsernameEqualTo(username).andStatusEqualTo(0);
        List<UserLogin> sysUsers = userLoginService.selectByExample(userExample);
        UserLogin sysUser = sysUsers.get(0);
        if(sysUser.getIsManager() == 1) {
            // 管理员
            CustInfoExample custInfoExample = new CustInfoExample();
            custInfoExample.createCriteria().andSystemIdEqualTo(sysUser.getCustId());
            List<CustInfo> custInfos = custInfoService.selectCustInfosByExample(custInfoExample);
            int parentType = custInfos.get(0).getParentType();
            SysPermissionExample permissionExample = new SysPermissionExample();
            permissionExample.createCriteria().andAppIdEqualTo(Constants.SYS_APP_ID).andTypeLike("%" + parentType + "%");
            List<SysPermission> sysPermissions = sysPermissionService.selectByExample(permissionExample);
            if(sysPermissions != null && sysPermissions.size() > 0) {
                for(SysPermission sysPermission: sysPermissions) {
                    permissions.add(sysPermission.getPermsResource() + ":" + sysPermission.getPermsOperate());
                    //System.out.println("realm perms--" + sysPermission.getPermsResource() + ":" + sysPermission.getPermsOperate());
                }
            }
        } else {
            // 用户直接关联的权限
            Set<Long> permissionIds = new HashSet<Long>();
            UserPermissionExample userPermissionExample = new UserPermissionExample();
            userPermissionExample.createCriteria().andUserIdEqualTo(sysUser.getId());
            List<UserPermission> sysUserPermissions = userPermissionService.selectByExample(userPermissionExample);
            if(sysUserPermissions != null && sysUserPermissions.size() > 0) {
                for(UserPermission sysUserPermission: sysUserPermissions) {
                    permissionIds.add(sysUserPermission.getPermissionId());
                }
            }
            // permission
            SysPermissionExample permissionExample = new SysPermissionExample();
            permissionExample.createCriteria().andIdIn(new ArrayList<Long>(permissionIds));
            List<SysPermission> sysPermissions = sysPermissionService.selectByExample(permissionExample);
            if(sysPermissions != null && sysPermissions.size() > 0) {
                for(SysPermission sysPermission: sysPermissions) {
                    permissions.add(sysPermission.getPermsResource() + ":" + sysPermission.getPermsOperate());
                    //System.out.println("realm perms--" + sysPermission.getPermsResource() + ":" + sysPermission.getPermsOperate());
                }
            }
        }
        return permissions;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
