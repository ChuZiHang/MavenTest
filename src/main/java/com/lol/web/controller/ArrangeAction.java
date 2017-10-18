
package com.lol.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

import com.lol.common.bean.BaseJson;
import com.lol.common.cache.FieldCache;
import com.lol.common.model.ds1.CoachInfo;
import com.lol.common.model.ds1.CoachInfoExample;
import com.lol.common.model.ds1.CourseInfo;
import com.lol.common.model.ds1.CourseInfoExample;
import com.lol.common.model.ds1.CoursePrice;
import com.lol.common.model.ds1.CoursePriceExample;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;
import com.lol.common.model.ds1.UserLogin;
import com.lol.common.service.CoachInfoService;
import com.lol.common.service.CourseService;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.UserLoginService;
import com.lol.common.util.ParamUtils;
import com.lol.common.util.SequenceUtils;
import com.google.gson.GsonBuilder;

/**
 * 课程管理
 * 
 * @author bowen
 *
 */

// 课程安排
@Controller
@RequestMapping("/course")
public class ArrangeAction {

    @Resource
    private CustInfoService custInfoService;

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private CourseService courseService;

    @Resource
    private CoachInfoService coachInfoService;

    Logger logger = LoggerFactory.getLogger(ArrangeAction.class);

    @RequestMapping("/arrange")
    public String arrange(HttpServletRequest request, Model model) {

        int custId = ParamUtils.getIntParameter(request, "custId", 0);
        int afterDays = ParamUtils.getIntParameter(request, "afterDays", 0);
        // 总店 0 ，分店 1
        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
        int parentCustId = custInfo.getParentId();
        int parentType = custInfo.getParentType();

        // ===============获取所有分店信息，分店就获取自己的信息============
        CustInfoExample custExample = new CustInfoExample();
        CustInfoExample.Criteria custCriteria = custExample.createCriteria();
        List<CustInfo> custInfos = new ArrayList<CustInfo>();
        if(parentType == 2) {
            custInfos.add(custInfo);
        } else if(parentType == 1) {
            custCriteria.andParentIdEqualTo(custInfo.getSystemId());
            custCriteria.andParentTypeEqualTo(2);
            custInfos = custInfoService.selectCustInfosByExample(custExample);
        }
        model.addAttribute("custInfos", custInfos);

        // ===============获取店铺可选课程============

        CourseInfoExample courseExample = new CourseInfoExample();
        CourseInfoExample.Criteria courseCriteria = courseExample.createCriteria();
        courseCriteria.andParentCustIdEqualTo(parentCustId);
        courseCriteria.andStatusEqualTo(0);
        List<CourseInfo> courses = courseService.selectCourseListByExample(courseExample);
        model.addAttribute("courses", courses);

        // =============获取日期与星期===================

        Date date02 = new Date();// 取时间
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date01 = new Date();

        String dateStr = "";
        List<String> dates = new ArrayList<String>();
        String weeks[] = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        for(int i = 0; i < 7; i++) {
            calendar.setTime(date02);
            calendar.add(Calendar.DAY_OF_MONTH, i + afterDays);// +1今天的时间加一天
            date01 = calendar.getTime();
            dateStr = fmt.format(date01);
            dates.add(weeks[calendar.get(Calendar.DAY_OF_WEEK) - 1] + " (" + dateStr + ")");
        }
        model.addAttribute("dates", dates);

        // =============获取选中的分店的课程===================
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int y, m, d;
        Calendar cal = Calendar.getInstance();
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DATE);

        CoursePriceExample example = new CoursePriceExample();
        CoursePriceExample.Criteria criteria = example.createCriteria();

        if(parentType == 2 || (parentType == 1 && custId == 0)) {
            if(custInfos.size() > 0) {
                custId = custInfos.get(0).getSystemId();
            }
        }

        criteria.andSysCustIdEqualTo(custId);
        Date date = new Date();
        try {
            // 今天0点
            date = format.parse(y + "-" + (m + 1) + "-" + d + " 00:00:00");
        } catch(ParseException e) {
            logger.debug("ArrangeAction is error:" + e.getMessage(), e);
        }

        List<Map<String, Object>> coursePrinces = courseService.selectCoursePriceBySql(
            "SELECT a.start_time,a.id,b.class_name FROM course_price a ,course_info b WHERE a.status=0 and a.course_id = b.id AND a.start_time >= ? AND a.sys_cust_id = ?",
            new Object[]{date, custId});

        Map<String, List<Object>> mapData = new HashMap<String, List<Object>>();
        List<Object> listData = null;
        for(int i = 0; i < dates.size(); i++) {
            listData = new ArrayList<Object>();
            for(int j = 0; j < coursePrinces.size(); j++) {
                if(dates.get(i).substring(4, 14).equals(coursePrinces.get(j).get("start_time").toString().substring(0, 10))) {
                    listData.add(coursePrinces.get(j));
                }
            }
            mapData.put(dates.get(i), listData);
        }

        model.addAttribute("mapData", mapData);
        // 获取教练
        CoachInfoExample exampleCoach = new CoachInfoExample();
        CoachInfoExample.Criteria criteriaCoach = exampleCoach.createCriteria();
        criteriaCoach.andUseCustIdLike("%," + custId + ",%");
        criteriaCoach.andStatusEqualTo(0);
        List<CoachInfo> coachs = coachInfoService.selectByExample(exampleCoach);
        model.addAttribute("coachs", coachs);

        model.addAttribute("afterDays", afterDays);
        model.addAttribute("custId", custId);
        return "models/hq/course/general/arrange";
    }

    // 添加课程
    @RequestMapping("/arrange/insert")
    public void insert(HttpServletRequest request, HttpServletResponse response) {
        long courseId = ParamUtils.getLongParameter(request, "insertCId", 0);
        int custId = ParamUtils.getIntParameter(request, "custId", 0);
        String startTime = ParamUtils.getParameter(request, "startTime", "");
        String endTime = ParamUtils.getParameter(request, "endTime", "");
        long coachId = ParamUtils.getLongParameter(request, "coachId", 0);
        int peopleNum = ParamUtils.getIntParameter(request, "peopleNum", 0);

        String address = ParamUtils.getParameter(request, "address", "");
        String insertDate = ParamUtils.getParameter(request, "insertDate", "");

        insertDate = insertDate.substring(4, 14);

        UserLogin user = userLoginService.getCurrentUserInfo().getUserLogin();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        CoursePrice record = new CoursePrice();
        BaseJson res = new BaseJson();
        try {
            record.setId(SequenceUtils.nextId());
            record.setCourseId(courseId);
            record.setSysCustId(custId);
            FieldCache fc = new FieldCache("ds1");
            String parentCustId = fc.get("cust_info", "system_id:" + custId + ":parent_id");
            record.setParentCustId(Integer.parseInt(parentCustId));
            record.setPeopleNum(peopleNum);
            record.setSalePrice(0);
            record.setStartTime(format.parse(insertDate + " " + startTime + ":00"));
            record.setEndTime(format.parse(insertDate + " " + endTime + ":00"));
            record.setCoachId(coachId);
            record.setAddress(address);
            record.setStatus(0);
            record.setUserId(user.getId() + "");
            record.setCreteTime(new Date());
            int status = courseService.insertCoursePriceSelective(record);
            res.setStatus(status);
        } catch(Exception e) {
            logger.debug("ArrangeAction is error:" + e.getMessage(), e);
        }

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch(IOException e1) {
            e1.printStackTrace();
        }

        // 用Gson转为json
        out.print(new GsonBuilder().create().toJson(res));
    }

    // 修改课程
    @RequestMapping("/arrange/update")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        long updateId = ParamUtils.getLongParameter(request, "updateId", 0);
        long updateCoach = ParamUtils.getLongParameter(request, "updateCoach", 0);
        int updateNum = ParamUtils.getIntParameter(request, "updateNum", 0);
        String updateAddress = ParamUtils.getParameter(request, "updateAddress", "");
        String updateDate = ParamUtils.getParameter(request, "updateDate", "");
        updateDate = updateDate.replaceAll("\\/", "\\-");
        String startTime = ParamUtils.getParameter(request, "startTime", "");
        String endTime = ParamUtils.getParameter(request, "endTime", "");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        CoursePrice record = new CoursePrice();
        BaseJson res = new BaseJson();
        PrintWriter out = null;
        try {
            record.setStartTime(format.parse(updateDate + " " + startTime + ":00"));
            record.setEndTime(format.parse(updateDate + " " + endTime + ":00"));
            record.setCoachId(updateCoach);
            record.setAddress(updateAddress);
            record.setPeopleNum(updateNum);
            record.setId(updateId);
            int status = courseService.updateCoursePriceByPrimaryKeySelective(record);
            res.setStatus(status);
            out = response.getWriter();
        } catch(Exception e) {
            logger.debug("ArrangeAction is error:" + e.getMessage(), e);
        }

        // 用Gson转为json
        out.print(new GsonBuilder().create().toJson(res));
    }

    // 修改课程
    @RequestMapping("/arrange/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        long id = ParamUtils.getLongParameter(request, "id", 0);

        CoursePrice record = new CoursePrice();
        BaseJson res = new BaseJson();
        PrintWriter out = null;
        try {
            record.setId(id);
            record.setStatus(1);

            int status = courseService.updateCoursePriceByPrimaryKeySelective(record);
            res.setStatus(status);
            out = response.getWriter();
        } catch(Exception e) {
            logger.debug("ArrangeAction is error:" + e.getMessage(), e);
        }

        // 用Gson转为json
        out.print(new GsonBuilder().create().toJson(res));
    }

    // 设置后的课程详情
    @RequestMapping("/arrange/courseprice")
    public void courseprice(HttpServletRequest request, HttpServletResponse response) {
        long id = ParamUtils.getLongParameter(request, "id", 0);

        BaseJson res = new BaseJson();
        PrintWriter out = null;
        try {
            CoursePrice coursePrice = courseService.selectCoursePriceByPrimaryKey(id);
            CoachInfo coachInfo = null;
            res.setStatus(1);
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("coursePrice", coursePrice);
            if(coursePrice != null) {
                coachInfo = coachInfoService.selectByPrimaryKey(coursePrice.getCoachId());
                if(coachInfo != null) {
                    map.put("coachName", coachInfo.getName());
                    map.put("coachId", coachInfo.getId() + "");
                }
            }

            res.setData(map);
            out = response.getWriter();
        } catch(IOException e1) {
            try {
                res.setStatus(0);
                out = response.getWriter();
            } catch(IOException e) {
                logger.debug("ArrangeAction is error:" + e.getMessage(), e);
            }
            logger.debug("ArrangeAction is error:" + e1.getMessage(), e1);
        }

        // 用Gson转为json
        out.print(new GsonBuilder().create().toJson(res));
    }

    //
    @RequestMapping("/arrange/build")
    public void build(HttpServletRequest request, HttpServletResponse response) {
        int sysCustId = ParamUtils.getIntParameter(request, "syscustid", 0);
        int weekStatus = ParamUtils.getIntParameter(request, "weekstatus", 1);
        // String id = ParamUtils.getParameter(request, "commentId", "");

        BaseJson res = new BaseJson();

        Calendar todayStart = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
        int status = 0;

        try {
            Date startDate = sdf.parse(sdf.format(todayStart.getTime()));
            CoursePriceExample example = new CoursePriceExample();
            example.createCriteria().andSysCustIdEqualTo(sysCustId).andStartTimeGreaterThan(startDate).andStatusEqualTo(0);
            final List<CoursePrice> coursePrices = courseService.selectCoursePricesByExample(example);
            courseService.insertCoursePrices(coursePrices, weekStatus);
            status = 1;

        } catch(ParseException e) {
            status = 0;

            e.printStackTrace();
        }

        if(status == 1) {
            res.setMsg("成功");
        } else {
            res.setMsg("失败");
        }

        res.setStatus(status);

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch(IOException e1) {
            logger.debug("CourseAction is error:" + e1.getMessage(), e1);
        }

        // 用Gson转为json
        out.print(new GsonBuilder().create().toJson(res));
    }

}