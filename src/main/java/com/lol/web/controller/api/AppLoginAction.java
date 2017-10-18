package com.lol.web.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lol.common.bean.BaseJson;
import com.lol.common.model.ds1.AppInfo;
import com.lol.common.model.ds1.UserLogin;
import com.lol.common.model.ds1.UserLoginExample;
import com.lol.common.service.AppInfoService;
import com.lol.common.service.UserLoginService;
import com.lol.common.util.AES;
import com.lol.common.util.ParamUtils;
import com.lol.common.util.SecretUtils;
import com.lol.common.util.SignUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * App 登录接口
 * 
 * @author qiuwei
 *
 */
@Controller
@RequestMapping("/api")
public class AppLoginAction {

    Logger logger = LoggerFactory.getLogger(AppLoginAction.class);

    @Resource
    private UserLoginService userLoginService;
    
    @Resource
    private AppInfoService appInfoService;


    @RequestMapping("/app/login")
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) {

        String sign = ParamUtils.getParameter(request, "sign", "");
        String username = ParamUtils.getParameter(request, "username", "");
        String password = ParamUtils.getParameter(request, "password", "");
        String timeStamp = ParamUtils.getParameter(request, "timeStamp", "");
        int appId = ParamUtils.getIntParameter(request, "appId",0);
        AppInfo appInfo = appInfoService.selectByPrimaryKey(appId);
        String token ="";
        if(appInfo!=null){
            token = appInfo.getAppToken();
        }
        Gson gson = new GsonBuilder().create();
        BaseJson baseJson = new BaseJson();

        try {       

            Map<String, String> params = new HashMap<String, String>();
            params.put("appId", appId+"");
            params.put("timeStamp", timeStamp);
            params.put("username", username);
            params.put("password", password);
            String nmSign = SignUtils.NMSign(params, token);

            if(sign.equals(nmSign)) {
                AES aes = new AES(AES.AESKEY, AES.AesPublicKey);
                username = aes.decrypt(username);
                password = aes.decrypt(password);

                if("".equals(username)) {
                    baseJson.setMsg("用户名不能为空!");
                    baseJson.setStatus(0);
                }
                if("".equals(password)) {
                    baseJson.setMsg("密码不能为空!");
                    baseJson.setStatus(0);
                }

                // 根据用户名查询
                UserLoginExample example = new UserLoginExample();
                //status=0是未删除
                example.createCriteria().andUsernameEqualTo(username).andStatusEqualTo(0);
                List<UserLogin> userList = userLoginService.selectByExample(example);

                if(userList == null || userList.size() <= 0) {
                    baseJson.setMsg("用户名不正确!");
                    baseJson.setStatus(0);
                } else {
                    String password2 = userList.get(0).getPassword();
                    String salt = userList.get(0).getSalt();
                    String saltPassword = SecretUtils.encryptPass(username, password, salt);
                    if(!password2.equals(saltPassword)) {
                        baseJson.setMsg("密码不正确!");
                        baseJson.setStatus(0);
                    } else {
                        UserLogin user = new UserLogin();
                        // user = userList.get(0);
                        user.setRealname(userList.get(0).getRealname());
                        user.setUsername(userList.get(0).getUsername());
                        user.setMobile(userList.get(0).getMobile());
                        user.setId(userList.get(0).getId());
                        baseJson.setMsg("登录成功!");
                        baseJson.setStatus(1);
                        baseJson.setData(user);
                    }
                }
            }else{
                baseJson.setMsg("sign有误!");
                baseJson.setStatus(0);
            }
        } catch(Exception e) {
            logger.error("AppLoginAction.checkLogin Error:" + e.getMessage(), e);
        }
        try {
            response.getWriter().print(gson.toJson(baseJson));
        } catch(Exception e) {
            logger.error("AppLoginAction.checkLogin Error:" + e.getMessage(), e);
        }
    }
}
