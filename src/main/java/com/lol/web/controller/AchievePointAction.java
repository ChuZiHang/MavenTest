package com.lol.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lol.common.bean.BaseJson;
import com.lol.common.model.ds1.MemberAchievementInfo;
import com.lol.common.model.ds1.MemberAchievementInfoExample;
import com.lol.common.model.ds1.MemberPointInfo;
import com.lol.common.model.ds1.MemberPointInfoExample;
import com.lol.common.model.ds1.UserLog;
import com.lol.common.plugin.Page;
import com.lol.common.plugin.Pager;
import com.lol.common.plugin.PagerService;
import com.lol.common.service.AchieveService;
import com.lol.common.service.PointService;
import com.lol.common.service.UserLogService;
import com.lol.common.util.ParamUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * 积分成就系统
 * 
 * @author xumao
 *
 */

@Controller
@RequestMapping("/achieve")
public class AchievePointAction {

    @Resource
    private AchieveService achieveService;

    @Resource
    private PointService pointService;

    @Resource
    private UserLogService userLogService;

    Logger logger = LoggerFactory.getLogger(AchievePointAction.class);

    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model) {
        try {
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
            MemberAchievementInfoExample example = new MemberAchievementInfoExample();
            MemberAchievementInfoExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(0);
            int pageCount = achieveService.countByExample(example);
            int pageSize = 8;
            int offSet = 4;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            example.setPage(pages);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
            example.setOrderByClause("  sort   asc");
            List<MemberAchievementInfo> achieve = achieveService.selectByExample(example);
            model.addAttribute("pager", page);
            model.addAttribute("achieve", achieve);

        } catch(Exception e) {
            logger.debug("AchievePointAction view is error:" + e.getMessage(), e);
        }

        return "models/hq/member/achievePoint/list";
    }

    @RequestMapping("/achieve/del")
    public void updateTure(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = ParamUtils.getIntParameter(request, "id");
            MemberAchievementInfo memberAchievementInfo = achieveService.selectByPrimaryKey(id);
            memberAchievementInfo.setStatus(1);
            int flag = achieveService.updateByPrimaryKeySelective(memberAchievementInfo);
            PrintWriter pw = response.getWriter();
            pw.print(flag);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/achieve/save")
    public void save(HttpServletRequest request, HttpServletResponse response) {
        int sort = ParamUtils.getIntParameter(request, "sort");
        int point = ParamUtils.getIntParameter(request, "point");
        String name = ParamUtils.getParameter(request, "name");
        String unobtain = ParamUtils.getParameter(request, "unobtain");
        String obtain = ParamUtils.getParameter(request, "obtain");
        MemberAchievementInfo memAchievementInfo = new MemberAchievementInfo();
        BaseJson baseJson = new BaseJson();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        try {
            memAchievementInfo.setSort(sort);
            memAchievementInfo.setName(name);
            memAchievementInfo.setObtain(obtain);
            memAchievementInfo.setPoint(point);
            memAchievementInfo.setUnobtain(unobtain);
            memAchievementInfo.setStatus(0);
            achieveService.insertSelective(memAchievementInfo);
            UserLog log = new UserLog();
            log.setLogType(0);
            log.setCreateTime(new Date());
            log.setLogAction(0);
            log.setLogMemo("成就新增");
            userLogService.insertSelective(log);
            baseJson.setStatus(0);
            baseJson.setMsg("操作成功");
            baseJson.setData(memAchievementInfo);
            baseJson.setData(log);
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

    @RequestMapping("/achieve/update")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        int sort = ParamUtils.getIntParameter(request, "sort");
        int point = ParamUtils.getIntParameter(request, "point");
        String name = ParamUtils.getParameter(request, "name");
        String unobtain = ParamUtils.getParameter(request, "unobtain");
        String obtain = ParamUtils.getParameter(request, "obtain");
        int id = ParamUtils.getIntParameter(request, "id");
        MemberAchievementInfo memAchievementInfo = achieveService.selectByPrimaryKey(id);
        BaseJson baseJson = new BaseJson();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        try {
            memAchievementInfo.setSort(sort);
            memAchievementInfo.setName(name);
            memAchievementInfo.setObtain(obtain);
            memAchievementInfo.setPoint(point);
            memAchievementInfo.setUnobtain(unobtain);
            achieveService.updateByPrimaryKeySelective(memAchievementInfo);
            UserLog log = new UserLog();
            log.setLogType(3);
            log.setCreateTime(new Date());
            log.setLogAction(0);
            log.setLogMemo("成就修改");
            userLogService.insertSelective(log);
            baseJson.setStatus(0);
            baseJson.setMsg("操作成功");
            baseJson.setData(memAchievementInfo);
            baseJson.setData(log);
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

    @RequestMapping("/point/list")
    public String pointList(HttpServletRequest request, Model model) {
        try {
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
            MemberPointInfoExample example = new MemberPointInfoExample();
            MemberPointInfoExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(0);
            int pageCount = pointService.countByExample(example);
            int pageSize = 8;
            int offSet = 4;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            example.setPage(pages);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
            example.setOrderByClause("  sort   asc");
            List<MemberPointInfo> point = pointService.selectByExample(example);
            MemberPointInfoExample example1 = new MemberPointInfoExample();
            MemberPointInfoExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andStatusEqualTo(1);
            List<MemberPointInfo> pointlist = pointService.selectByExample(example1);
            model.addAttribute("pager", page);
            model.addAttribute("point", point);
            model.addAttribute("pointlist", pointlist);

        } catch(Exception e) {
            logger.debug("AchievePointAction view is error:" + e.getMessage(), e);
        }

        return "models/hq/member/achievePoint/pointList";
    }

    @RequestMapping("/point/del")
    public void updatePoint(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = ParamUtils.getIntParameter(request, "id");
            MemberPointInfo memberPointInfo = pointService.selectByPrimaryKey(id);
            memberPointInfo.setStatus(1);
            int flag = pointService.updateByPrimaryKeySelective(memberPointInfo);
            PrintWriter pw = response.getWriter();
            pw.print(flag);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/point/save")
    public void pointSave(HttpServletRequest request, HttpServletResponse response) {
        int sort = ParamUtils.getIntParameter(request, "sort");
        int value = ParamUtils.getIntParameter(request, "value");
        int id = ParamUtils.getIntParameter(request, "id");
        MemberPointInfo memberPointInfo = pointService.selectByPrimaryKey(id);
        BaseJson baseJson = new BaseJson();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        try {
            memberPointInfo.setSort(sort);
            memberPointInfo.setValue(value);
            memberPointInfo.setStatus(0);
            pointService.updateByPrimaryKeySelective(memberPointInfo);
            UserLog log = new UserLog();
            log.setLogType(0);
            log.setCreateTime(new Date());
            log.setLogAction(0);
            log.setLogMemo("积分新增");
            userLogService.insertSelective(log);
            baseJson.setStatus(0);
            baseJson.setMsg("操作成功");
            baseJson.setData(memberPointInfo);
            baseJson.setData(log);
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

    @RequestMapping("/point/datail")
    public void pointDetail(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        BaseJson res = new BaseJson();
        try {
            int id = ParamUtils.getIntParameter(request, "id");
            MemberPointInfo pointInfo = pointService.selectByPrimaryKey(id);
            if(pointInfo != null) {
                res.setStatus(1);
                res.setData(pointInfo);
                res.setMsg("获取成功");
            } else {
                res.setStatus(0);
                res.setMsg("获取失败");
            }
            response.getWriter().print(gson.toJson(res));
        } catch(Exception e) {
            logger.error("AchievePointAction is Error:" + e.getMessage(), e);
        }
    }

    @RequestMapping("/achieve/datail")
    public void achieveDetail(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        BaseJson res = new BaseJson();
        try {
            int id = ParamUtils.getIntParameter(request, "id");
            MemberAchievementInfo achievementInfo = achieveService.selectByPrimaryKey(id);
            if(achievementInfo != null) {
                res.setStatus(1);
                res.setData(achievementInfo);
                res.setMsg("获取成功");
            } else {
                res.setStatus(0);
                res.setMsg("获取失败");
            }
            response.getWriter().print(gson.toJson(res));
        } catch(Exception e) {
            logger.error("AchievePointAction is Error:" + e.getMessage(), e);
        }
    }

}
