package com.lol.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lol.common.bean.CurrentUserInfo;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;
import com.lol.common.model.ds1.SysMessage;
import com.lol.common.model.ds1.SysMessageExample;
import com.lol.common.model.ds1.SysMessageExample.Criteria;
import com.lol.common.plugin.Page;
import com.lol.common.plugin.Pager;
import com.lol.common.plugin.PagerService;
import com.lol.common.service.CodeService;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.SysMessageService;
import com.lol.common.service.UserLoginService;
import com.lol.common.util.ParamUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 项目首页
 * 
 * @author wenshang
 *
 */
@Controller
@RequestMapping("/message")
public class MessageAction {

    @Resource
    UserLoginService userLoginService;

    @Resource
    SysMessageService sysMessageService;

    @Resource
    CustInfoService custInfoService;

    @Resource
    CodeService codeService;

    Logger logger = LoggerFactory.getLogger(MessageAction.class);

    // 消息列表
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model) {
        try {
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo custInfo = currentUserInfo.getCustInfo();
            CustInfoExample custInfoExample = new CustInfoExample();
            custInfoExample.createCriteria().andParentIdEqualTo(custInfo.getParentId()).andParentTypeEqualTo(2);
            // 分店列表
            List<CustInfo> custInfoList = custInfoService.selectCustInfosByExample(custInfoExample);

            SysMessageExample sysMessageExample = new SysMessageExample();
            sysMessageExample.createCriteria().andPidEqualTo(0).andSysCustIdEqualTo(0).andParentIdEqualTo(custInfo.getParentId());
            int pageCount = sysMessageService.countByExample(sysMessageExample);
            int pageSize = 10;
            int offSet = 5;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            sysMessageExample.setOrderByClause(" id desc");
            sysMessageExample.setPage(pages);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
            List<SysMessage> listMessage = sysMessageService.selectByExample(sysMessageExample);
            HashMap<SysMessage, List<SysMessage>> mapMessage = new LinkedHashMap<SysMessage, List<SysMessage>>();
            for(int i = 0; i < listMessage.size(); i++) {
                SysMessage me = listMessage.get(i);
                SysMessageExample example = new SysMessageExample();
                example.createCriteria().andPidNotEqualTo(0).andSysCustIdNotEqualTo(0).andPidEqualTo(me.getId());
                List<SysMessage> branchMessage = sysMessageService.selectByExample(example);
                mapMessage.put(me, branchMessage);
            }
            model.addAttribute("pager", page);
            model.addAttribute("custInfoList", custInfoList);
            model.addAttribute("mapMessage", mapMessage);
        } catch(Exception e) {
            logger.debug("CardAction pcodeList is error:" + e.getMessage(), e);
        }
        return "models/hq/message/index";
    }

    // 发送消息
    @RequestMapping("/add")
    public void Add(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        HashMap<String, Object> map = new HashMap<>();
        try {
            String data = ParamUtils.getParameter(request, "data");
            String message = ParamUtils.getParameter(request, "message", "");
            String[] dataId = data.split(",");

            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo custInfo = currentUserInfo.getCustInfo();
            SysMessage parentMessage = new SysMessage();
            parentMessage.setCreateTime(new Date());
            parentMessage.setMessage(message);
            parentMessage.setParentId(custInfo.getParentId());
            String sql = "insert into sys_message(create_time,message,parent_id) values (?,?,?)";
            int pid = sysMessageService.insertAndGetKey(sql, parentMessage);
            int result = 0;
            for(int i = 0; i < dataId.length; i++) {
                if(!"".equals(dataId[i])) {
                    SysMessage sysMessage = new SysMessage();
                    sysMessage.setCreateTime(new Date());
                    sysMessage.setMessage(message);
                    sysMessage.setSysCustId(Integer.parseInt(dataId[i]));
                    sysMessage.setPid(pid);
                    result = sysMessageService.insertSelective(sysMessage);
                }
            }
            map.put("result", result);
            response.getWriter().print(gson.toJson(map));
        } catch(Exception e) {
            logger.error("cardAction pcodeAdd is Error:" + e.getMessage(), e);
        }
    }

    /**
     * 消息列表
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/branchlist")
    public String branchList(HttpServletRequest request, Model model) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        Integer parentType = currentUserInfo.getCustInfo().getParentType();
        Integer systemId = currentUserInfo.getCustInfo().getSystemId();
        try {
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);

            SysMessageExample example = new SysMessageExample();
            Criteria criteria = example.createCriteria();

            if(parentType == 2) {// 分部
                criteria.andSysCustIdEqualTo(systemId);

                int pageCount = sysMessageService.countByExample(example);
                int pageSize = 6;
                int offSet = 3;
                Page pages = new Page();
                pages.setTotal(pageCount);
                pages.setLimit(pageSize);
                pages.setNo(currentPage);

                example.setPage(pages);
                Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
                example.setOrderByClause("create_time DESC");
                List<SysMessage> messageList = sysMessageService.selectByExample(example);
                sysMessageService.updateMessageStatus(messageList);

                model.addAttribute("pager", page);
                model.addAttribute("messageList", messageList);
            }
        } catch(Exception e) {
            logger.error("MessageAction.branchlist Error:" + e.getMessage(), e);
        }
        return "models/branch/message/message";
    }

}