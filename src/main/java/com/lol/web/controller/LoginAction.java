package com.lol.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.UnknownSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lol.common.util.ParamUtils;

@Controller
public class LoginAction {
	
	Logger logger = LoggerFactory.getLogger(LoginAction.class);
	
	/**
	 * shiro认证失败后的处理
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String reLoginForm(HttpServletRequest request, Model model) {
		
        String excptionName = (String)request.getAttribute("shiroLoginFailure");
        ParamUtils.showParams(request, "login");
        String error = null;
        if(UnknownAccountException.class.getName().equals(excptionName)) {
            error = "用户不存在！";
        } else if(UnknownSessionException.class.getName().equals(excptionName)) {
            error = "会话已过期，请重新登录！";
        } else if(IncorrectCredentialsException.class.getName().equals(excptionName)) {
            error = "密码不正确！";
        }else if(ExcessiveAttemptsException.class.getName().equals(excptionName)){
        	error = "密码错误已经超过3次，您的账号已被锁定！";
        }else if(LockedAccountException.class.getName().equals(excptionName)){
        	error = "您的账号已被锁定,请联系管理员处理。";
        }else if(excptionName != null) {
            error = "系统异常，请稍后再试！";
        }
        model.addAttribute("error", error);
        return "login";
    }

}
