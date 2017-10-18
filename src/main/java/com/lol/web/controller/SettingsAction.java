package com.lol.web.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lol.common.Constants;
import com.lol.common.bean.BaseJson;
import com.lol.common.bean.CurrentUserInfo;
import com.lol.common.bean.EasyUITree;
import com.lol.common.model.ds0.SysPermission;
import com.lol.common.model.ds0.SysPermissionExample;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;
import com.lol.common.model.ds1.UserLog;
import com.lol.common.model.ds1.UserLogExample;
import com.lol.common.model.ds1.UserLogin;
import com.lol.common.model.ds1.UserLoginExample;
import com.lol.common.model.ds1.UserPermission;
import com.lol.common.model.ds1.UserPermissionExample;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.SysPermissionService;
import com.lol.common.service.UserLogService;
import com.lol.common.service.UserLoginService;
import com.lol.common.service.UserPermissionService;
import com.lol.common.util.EasyUIUtils;
import com.lol.common.util.ParamUtils;
import com.lol.common.util.SecretUtils;
import com.lol.common.util.SequenceUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

/**
 * 系统设置
 * 
 * @author cunxing
 *
 */
@Controller
@RequestMapping("/settings")
public class SettingsAction {

    Logger logger = LoggerFactory.getLogger(SettingsAction.class);

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private UserPermissionService userPermissionService;

    @Resource
    private SysPermissionService sysPermissionService;

    @Resource
    private CustInfoService custInfoService;

    @Resource
    private UserLogService userLogService;

    /**
     * 商户信息
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/shop/info")
    public String shopInfo(HttpServletRequest request, Model model) {
        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
        UserLogin userLogin = userLoginService.getCurrentUserInfo().getUserLogin();

        CustInfoExample example = new CustInfoExample();
        CustInfoExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(custInfo.getParentId());
        List<CustInfo> custInfos = custInfoService.selectCustInfosByExample(example);

        model.addAttribute("custCount", custInfos.size() - 1);
        model.addAttribute("custInfo", custInfo);
        model.addAttribute("userLogin", userLogin);
        return "models/hq/settings/basic/index";
    }

    /**
     * 业务员列表
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/user/list")
    public String list(HttpServletRequest request, Model model) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        int parentType = currentUserInfo.getCustInfo().getParentType();
        int custId = currentUserInfo.getCustInfo().getSystemId();
        if(parentType == 1) {// 总店
            // 总店用户放到map第一条
            Map<CustInfo, List<UserLogin>> users = new LinkedHashMap<CustInfo, List<UserLogin>>();
            List<UserLogin> hqUserLogins = userLoginService.getUserLoginByCustId(custId);
            users.put(currentUserInfo.getCustInfo(), hqUserLogins);
            CustInfoExample custInfoExample = new CustInfoExample();
            custInfoExample.createCriteria().andStatusEqualTo(0).andParentIdEqualTo(custId);
            custInfoExample.setOrderByClause("id asc");
            List<CustInfo> custInfos = custInfoService.selectCustInfosByExample(custInfoExample);
            if(custInfos != null) {
                for(CustInfo custInfo: custInfos) {
                    // 总部用户已经在第一条
                    if(custId != custInfo.getSystemId()) {
                        List<UserLogin> userLogins = userLoginService.getUserLoginByCustId(custInfo.getSystemId());
                        users.put(custInfo, userLogins);
                    }
                }
            }
            model.addAttribute("users", users);
            return "models/hq/settings/user/index";
        } else {// 分店
            List<UserLogin> userLogins = userLoginService.getUserLoginByCustId(custId);
            model.addAttribute("custInfo", currentUserInfo.getCustInfo());
            model.addAttribute("userList", userLogins);
            return "models/branch/settings/user/index";
        }
    }

    /**
     * 用户详情
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/user/detail")
    public String detail(HttpServletRequest request, Model model) {
        long id = ParamUtils.getLongParameter(request, "id");
        UserLogin userLogin = userLoginService.selectByPrimaryKey(id);
        CustInfoExample custInfoExample = new CustInfoExample();
        custInfoExample.createCriteria().andSystemIdEqualTo(userLogin.getCustId());
        List<CustInfo> custInfos = custInfoService.selectCustInfosByExample(custInfoExample);
        CustInfo custInfo = custInfos.get(0);
        UserLogExample userLogExample = new UserLogExample();
        userLogExample.createCriteria().andUserIdEqualTo(userLogin.getUsername());
        List<UserLog> userLogs = userLogService.selectByExample(userLogExample);
        model.addAttribute("user", userLogin);
        model.addAttribute("custInfo", custInfo);
        model.addAttribute("userLogs", userLogs);
        return "models/hq/settings/user/detail";
    }

    /**
     * 添加用户
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/user/add")
    public void add(HttpServletRequest request, HttpServletResponse response) {
        String username = ParamUtils.getParameter(request, "username");
        String realname = ParamUtils.getParameter(request, "realname");
        String password = ParamUtils.getParameter(request, "password");
        String mobile = ParamUtils.getParameter(request, "mobile");
        String email = ParamUtils.getParameter(request, "email");
        Integer custId = ParamUtils.getIntParameter(request, "custId", 0);
        Long deptId = ParamUtils.getLongParameter(request, "deptId", 0);
        BaseJson baseJson = new BaseJson();
        Gson gson = new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING)
            .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        try {
            UserLoginExample userLoginExample = new UserLoginExample();
            userLoginExample.createCriteria().andStatusEqualTo(0).andUsernameEqualTo(username);
            List<UserLogin> userLogins = userLoginService.selectByExample(userLoginExample);
            if(userLogins != null && userLogins.size() > 0) {
                baseJson.setStatus(1);
                baseJson.setMsg("该用户名已存在");
            } else {
                String salt = SecretUtils.randomSalt();
                String saltPassword = SecretUtils.encryptPass(username, password, salt);
                UserLogin userLogin = new UserLogin();
                long id = SequenceUtils.nextId();
                userLogin.setId(id);
                userLogin.setUsername(username);
                userLogin.setRealname(realname);
                userLogin.setPassword(saltPassword);
                userLogin.setMobile(mobile);
                userLogin.setEmail(email);
                userLogin.setCustId(custId);
                userLogin.setDeptId(deptId);
                userLogin.setCreateTime(new Date());
                userLogin.setSalt(salt);
                userLoginService.insertSelective(userLogin);
                baseJson.setStatus(0);
                baseJson.setMsg("操作成功");
                baseJson.setData(userLogin);
            }
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            baseJson.setStatus(1);
            baseJson.setMsg("操作失败");
        }
        try {
            logger.debug(gson.toJson(baseJson));
            response.getWriter().print(gson.toJson(baseJson));
        } catch(IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 删除用户
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/user/del")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        long id = ParamUtils.getLongParameter(request, "id", 0);
        Gson gson = new GsonBuilder().create();
        BaseJson baseJson = new BaseJson();
        baseJson.setStatus(1);
        try {
            // 删除用户
            UserLogin record = new UserLogin();
            record.setId(id);
            record.setStatus(1);
            userLoginService.updateByPrimaryKeySelective(record);
            // 删除对应权限
            UserPermissionExample userPermissionExample = new UserPermissionExample();
            userPermissionExample.createCriteria().andUserIdEqualTo(id);
            userPermissionService.deleteByExample(userPermissionExample);
            baseJson.setStatus(0);
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            baseJson.setMsg("操作失败");
        }
        try {
            response.getWriter().print(gson.toJson(baseJson));
        } catch(IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 修改密码
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/user/password/reset")
    public void passwordReset(HttpServletRequest request, HttpServletResponse response) {
        long id = ParamUtils.getLongParameter(request, "id", 0);
        String originalPassword = ParamUtils.getParameter(request, "originalPassword");
        String password = ParamUtils.getParameter(request, "password");
        Gson gson = new GsonBuilder().create();
        BaseJson baseJson = new BaseJson();
        baseJson.setStatus(1);
        try {
            UserLogin userLogin = userLoginService.selectByPrimaryKey(id);
            if(userLogin.getPassword()
                .equals(SecretUtils.encryptPass(userLogin.getUsername(), originalPassword, userLogin.getSalt()))) {
                String salt = SecretUtils.randomSalt();
                String saltPassword = SecretUtils.encryptPass(userLogin.getUsername(), password, salt);
                UserLogin record = new UserLogin();
                record.setId(id);
                record.setPassword(saltPassword);
                record.setSalt(salt);
                userLoginService.updateByPrimaryKeySelective(record);
                baseJson.setStatus(0);
            } else {
                baseJson.setMsg("原密码错误！");
            }
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            baseJson.setMsg("操作失败");
        }
        try {
            response.getWriter().print(gson.toJson(baseJson));
        } catch(IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取权限列表
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/user/permission/list")
    public void permissionList(HttpServletRequest request, HttpServletResponse response) {
        long id = ParamUtils.getLongParameter(request, "id");
        Gson gson = new GsonBuilder().create();
        BaseJson baseJson = new BaseJson();
        baseJson.setStatus(1);
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            UserLogin userLogin = userLoginService.selectByPrimaryKey(id);

            List<Long> hasPerms = new ArrayList<Long>();
            UserPermissionExample userPermissionExample = new UserPermissionExample();
            userPermissionExample.createCriteria().andUserIdEqualTo(id);
            List<UserPermission> userPermissions = userPermissionService.selectByExample(userPermissionExample);
            if(userPermissions != null && userPermissions.size() > 0) {
                for(UserPermission userPermission: userPermissions) {
                    hasPerms.add(userPermission.getPermissionId());
                }
            }
            CustInfoExample custInfoExample = new CustInfoExample();
            custInfoExample.createCriteria().andSystemIdEqualTo(userLogin.getCustId());
            List<CustInfo> custInfos = custInfoService.selectCustInfosByExample(custInfoExample);
            CustInfo custInfo = custInfos.get(0);
            int parentType = custInfo.getParentType();
            SysPermissionExample example = new SysPermissionExample();
            example.createCriteria().andAppIdEqualTo(Constants.SYS_APP_ID).andStatusEqualTo(0).andTypeLike("%" + parentType + "%");
            example.setOrderByClause("priority asc");
            List<SysPermission> sysPermissions = sysPermissionService.selectByExample(example);
            List<EasyUITree> permissions = EasyUIUtils.buildCheckedTree(sysPermissions, hasPerms, "0");
            map.put("permissions", permissions);

            if(parentType == Constants.CUST_TYPE_HQ) {
                String subCustIds = userLogin.getSubCustIds();
                List<String> hasSubCustIds = new ArrayList<String>();
                if(subCustIds != null && subCustIds.length() > 0) {
                    hasSubCustIds = Arrays.asList(subCustIds.split(","));
                }
                CustInfoExample custInfoExample2 = new CustInfoExample();
                custInfoExample2.createCriteria().andStatusEqualTo(0).andParentIdEqualTo(userLogin.getCustId()).andSystemIdNotEqualTo(userLogin.getCustId());
                List<CustInfo> subCustInfos = custInfoService.selectCustInfosByExample(custInfoExample2);
                List<EasyUITree> subCusts = EasyUIUtils.buildCheckedSubCustTree(subCustInfos, hasSubCustIds);
                map.put("subCusts", subCusts);
            }
            baseJson.setStatus(0);
            baseJson.setData(map);
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            baseJson.setStatus(1);
            baseJson.setMsg("操作失败");
        }
        try {
            response.getWriter().print(gson.toJson(baseJson));
        } catch(IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 修改权限
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/user/permission/update")
    public void permissionUpdate(HttpServletRequest request, HttpServletResponse response) {
        long userId = ParamUtils.getLongParameter(request, "id");
        String permSelected = ParamUtils.getParameter(request, "permSelected");
        String subCustSelected = ParamUtils.getParameter(request, "subCustSelected");
        String subCustIds = subCustSelected.replaceAll("(\\[|\\]|\")", "");
        BaseJson baseJson = new BaseJson();
        baseJson.setStatus(1);
        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<List<Long>>() {
        }.getType();
        try {
            List<Long> permissionIds = gson.fromJson(permSelected, type);
            userPermissionService.updateUserPermission(userId, permissionIds);
            UserLogin userLogin = new UserLogin();
            userLogin.setId(userId);
            userLogin.setSubCustIds(subCustIds);
            userLoginService.updateByPrimaryKeySelective(userLogin);
            baseJson.setStatus(0);
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            baseJson.setStatus(1);
            baseJson.setMsg("操作失败");
        }
        try {
            response.getWriter().print(gson.toJson(baseJson));
        } catch(IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
