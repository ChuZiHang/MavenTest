
package com.lol.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lol.common.bean.BaseJson;
import com.lol.common.cache.FieldCache;
import com.lol.common.model.ds1.CommentInfoWithBLOBs;
import com.lol.common.model.ds1.CourseInfo;
import com.lol.common.model.ds1.CourseInfoExample;
import com.lol.common.model.ds1.CoursePriceExample;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;
import com.lol.common.model.ds1.OrderInfo;
import com.lol.common.model.ds1.OrderInfoExample;
import com.lol.common.model.ds1.UserLogin;
import com.lol.common.model.ds1.UserProductLog;
import com.lol.common.model.ds1.UserProductLogExample;
import com.lol.common.plugin.Page;
import com.lol.common.plugin.Pager;
import com.lol.common.plugin.PagerService;
import com.lol.common.service.CommentService;
import com.lol.common.service.CourseService;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.OrderInfoService;
import com.lol.common.service.UserLoginService;
import com.lol.common.service.UserProductLogService;
import com.lol.common.util.ParamUtils;
import com.lol.common.util.SequenceUtils;
import com.lol.web.JSTLFuncs;
import com.google.gson.GsonBuilder;

/**
 * 课程管理
 * 
 * @author bowen
 *
 */
@Controller
@RequestMapping("/course")
public class CourseAction {

    @Resource
    private CustInfoService custInfoService;

    @Resource
    private CourseService courseService;

    @Resource
    private CommentService commentService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private UserProductLogService userProductLogService;

    Logger logger = LoggerFactory.getLogger(CourseAction.class);

    // 课程列表
    @RequestMapping("/list")
    public String classList(HttpServletRequest request, Model model) {

        String searchWord = ParamUtils.getParameter(request, "searchword", "");

        int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
        // 页面列表是纵排还是横排0为横，1为纵
        int type = ParamUtils.getIntParameter(request, "type", 0);

        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
        int SystemCustId = custInfo.getSystemId();
        int parentCustId = custInfo.getParentId();
        // 总店 0， 分店 1
        int parentType = custInfo.getParentType();

        CourseInfoExample example = new CourseInfoExample();
        CourseInfoExample.Criteria criteria = example.createCriteria();

        if(searchWord != null && searchWord.length() > 0) {
            criteria.andClassNameLike("%" + searchWord + "%");
        } else {

            if(parentType == 1) {
                criteria.andSysCustIdEqualTo(SystemCustId);
            } else if(parentType == 2) {
                List<Map<String, Object>> lists = courseService.selectCoursePriceBySql(
                    "SELECT course_id FROM course_price WHERE sys_cust_id =?  AND parent_cust_id = ? GROUP BY course_id ",
                    new Object[]{SystemCustId, parentCustId});
                List<Long> values = new ArrayList<Long>();
                for(Map<String, Object> map: lists) {
                    values.add(Long.parseLong(map.get("course_id") + ""));
                }
                if(values.size() > 0) {
                    criteria.andIdIn(values);
                }

            }

            searchWord = "请输入课程名";
        }
        // criteria.andParentCustIdEqualTo(parentCustId);
        criteria.andStatusEqualTo(0);

        example.setOrderByClause(" id desc");

        int pageCount = courseService.countByExample(example);
        int pageSize = 8;
        int offSet = 3;
        Page pages = new Page();
        pages.setTotal(pageCount);
        pages.setLimit(pageSize);
        pages.setNo(currentPage);
        example.setPage(pages);
        // int currentPage, int pageCount, int pageSize, int offSet
        Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

        List<CourseInfo> list = courseService.selectCourseListByExample(example);
        model.addAttribute("type", type);
        model.addAttribute("list", list);
        model.addAttribute("pager", page);
        model.addAttribute("searchword", searchWord);
        return "models/hq/course/general/list";
    }

    // 课程详情
    @RequestMapping("/detail")
    public String classDetail(HttpServletRequest request, Model model) {

        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();

        int SystemCustId = custInfo.getSystemId();
        int parentCustId = custInfo.getParentId();

        if(parentCustId == 0) {
            parentCustId = SystemCustId;
        }

        // 总店：0，分店：1
        int parentType = custInfo.getParentType();

        int type = ParamUtils.getIntParameter(request, "type");
        String courseId = ParamUtils.getParameter(request, "courseId", "0");

        // 1.详情修改
        // 2.预约信息
        // 3.评价
        String typeStr = "detail";
        if(type == 1) {

            CourseInfo courseInfo = courseService.selectCourseInfoByCourseId(Long.parseLong(courseId));
            model.addAttribute("courseInfo", courseInfo);

            // 日志

            UserProductLogExample example = new UserProductLogExample();
            UserProductLogExample.Criteria criteria = example.createCriteria();
            criteria.andObjectIdEqualTo(Long.parseLong(courseId));

            example.setOrderByClause("id desc");
            List<UserProductLog> logs = userProductLogService.selectByExample(example);

            // List<Map<String, Object>> logs = userProductLogService.selectBySql("SELECT
            // b.realname,a.log_action,a.log_memo,a.create_time FROM user_log a,user_login b WHERE a.user_id = b.id AND a.object_id
            // = ?", new Object[]{courseId});
            model.addAttribute("logs", logs);

        } else if(type == 2) {

            int custId = ParamUtils.getIntParameter(request, "custId", 0);

            if(parentType == 2) {
                custId = SystemCustId;
            }

            CustInfoExample custExample = new CustInfoExample();

            CustInfoExample.Criteria custCriteria = custExample.createCriteria();
            custCriteria.andParentIdEqualTo(parentCustId);
            custCriteria.andParentTypeEqualTo(2);
            List<CustInfo> custs = custInfoService.selectCustInfosByExample(custExample);

            if(0 != custId) {
                custCriteria.andSystemIdEqualTo(custId);
            }

            String defaultCustName = "全部";
            for(CustInfo c: custs) {
                if(c.getSystemId() == custId) {
                    defaultCustName = c.getCustName();
                    break;
                }
            }

            CoursePriceExample cpExample = new CoursePriceExample();
            CoursePriceExample.Criteria criteria = cpExample.createCriteria();
            criteria.andEndTimeLessThanOrEqualTo(new Date());
            criteria.andCourseIdEqualTo(Long.parseLong(courseId));
            criteria.andStatusEqualTo(0);

            String sqlCust = " and a.parent_cust_id = " + parentCustId;
            if(0 != custId) {
                criteria.andSysCustIdEqualTo(custId);
                sqlCust += " and a.sys_cust_id = " + custId;
            }
            criteria.andParentCustIdEqualTo(parentCustId);

            // 已上课数
            int alreadyCount = courseService.selectCoursePriceCountByCourseId(cpExample);

            // 预约总人数
            List<Map<String, Object>> bookingCount = orderInfoService.selectBySql(
                "SELECT COUNT(1) AS num FROM order_info a,course_price b WHERE a.product_id = b.id AND b.end_time<now() and b.course_id=? and a.status in(2,4) AND a.order_type =12 "
                    + sqlCust,
                new Object[]{courseId});

            // 验证人数
            List<Map<String, Object>> checkCount = orderInfoService.selectBySql(
                "SELECT COUNT(1) AS num FROM order_info a,course_price b WHERE a.product_id = b.id AND b.end_time<now() and b.course_id=? and a.status = 4 AND a.order_type =12 "
                    + sqlCust,
                new Object[]{courseId});

            // 限制人数总和
            List<Map<String, Object>> limitCount = orderInfoService.selectBySql(
                "SELECT SUM(a.people_num) as num FROM course_price a WHERE a.end_time<now() and a.course_id = ? " + sqlCust,
                new Object[]{courseId});

            // 预约率：预约人数总和➗课程限制人数总和
            float bookingRate = 0;
            if(bookingCount != null && limitCount != null && bookingCount.size() > 0 && limitCount.get(0).get("num") != null) {
                bookingRate = Float.parseFloat(bookingCount.get(0).get("num") + "")
                    / Float.parseFloat(limitCount.get(0).get("num") == null ? "0" : limitCount.get(0).get("num") + "") * 100;
            }

            // 到课率：验证人数总和➗预约人数总和
            float checkRate = 0;
            if(checkCount != null && bookingCount != null && checkCount.size() > 0
                && !"0".equals(bookingCount.get(0).get("num") + "")) {
                checkRate = Float.parseFloat(checkCount.get(0).get("num") + "")
                    / Float.parseFloat(bookingCount.get(0).get("num") + "") * 100;
            }

            // ------------具体情况统计----------------
            List<Map<String, Object>> items = custInfoService.selectCustInfosBySql(
                "SELECT a.id,b.system_id,b.cust_name,a.people_num,a.start_time,a.end_time FROM course_price a,cust_info b WHERE a.end_time<now() and a.course_id = ? and a.status = 0 AND a.sys_cust_id = b.system_id "
                    + sqlCust,
                new Object[]{courseId});
            // System.out.println("--------------items-----------------sql----SELECT
            // a.id,b.system_id,b.cust_name,a.people_num,a.start_time,a.end_time FROM course_price a,cust_info b WHERE a.course_id =
            // "+courseId+" AND a.sys_cust_id = b.system_id "+ sqlCust);
            List<Map<String, Object>> datas = items;
            List<Map<String, Object>> item = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
            for(int i = 0; i < items.size(); i++) {

                int itemBooking = 0, itemCheck = 0;
                item = orderInfoService.selectBySql(
                    "SELECT a.status,COUNT(a.id) as c FROM order_info a,course_price b WHERE a.product_id = b.id AND b.id=? AND a.sys_cust_id = ? AND a.order_type =12 AND a.parent_cust_id = ? GROUP BY a.status",
                    new Object[]{items.get(i).get("id"), items.get(i).get("system_id"), parentCustId});

                // System.out.println("SELECT a.status,COUNT(a.id) as c FROM order_info a,course_price b WHERE a.product_id = b.id
                // AND b.id="+items.get(i).get("id")+" AND a.sys_cust_id = "+items.get(i).get("system_id")+" AND a.order_type =12
                // AND a.parent_cust_id = "+parentCustId+" GROUP BY a.status");

                if(item.size() > 0) {

                    for(int j = 0; j < item.size(); j++) {
                        if("2".equals(item.get(j).get("status") + "")) {
                            itemBooking += Integer.parseInt(item.get(j).get("c") + "");
                        }

                        if("4".equals(item.get(j).get("status") + "")) {
                            itemBooking += Integer.parseInt(item.get(j).get("c") + "");
                            itemCheck = Integer.parseInt(item.get(j).get("c") + "");
                        }
                    }

                    users = new ArrayList<Map<String, Object>>();
                    if(itemCheck < itemBooking) {
                        users = orderInfoService.selectBySql(
                            "SELECT b.member_id,b.member_name,b.member_nick,b.member_logo  FROM order_info a,member_info b WHERE a.product_id = ?  AND a.end_time < now() AND a.sys_cust_id = ? AND a.parent_cust_id = ? AND a.order_type =12 and a.member_id = b.member_id and a.status =2",
                            new Object[]{items.get(i).get("id"), items.get(i).get("system_id"), parentCustId});
                    }
                    datas.get(i).put("users", users);
                    datas.get(i).put("itemBooking", itemBooking);
                    datas.get(i).put("itemCheck", itemCheck);

                }

            }

            model.addAttribute("custs", custs);
            model.addAttribute("alreadyCount", alreadyCount);
            model.addAttribute("allCount", checkCount.get(0).get("num"));
            model.addAttribute("bookingRate", (float)Math.round(bookingRate * 10) / 10);
            model.addAttribute("checkRate", (float)Math.round(checkRate * 10) / 10);
            model.addAttribute("defaultCustName", defaultCustName);
            model.addAttribute("defaultCustId", custId);
            model.addAttribute("datas", datas);

            typeStr = "booking";

        } else if(type == 3) {

            float avg = courseService.getScoureAvg(Long.parseLong(courseId));
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);

            int pageSize = 10;
            int offSet = 5;
            int pageCount = commentService.getCountCommentAndMember(SystemCustId, Long.parseLong(courseId));
            List<Map<String, Object>> comments =
                commentService.getCommentAndMember(SystemCustId, Long.parseLong(courseId), currentPage, pageSize);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
            model.addAttribute("pageCount", pageCount);
            model.addAttribute("pager", page);
            model.addAttribute("avg", avg);
            model.addAttribute("comments", comments);
            typeStr = "comment";

        }

        model.addAttribute("parentType", parentType);
        model.addAttribute("type", type);
        model.addAttribute("courseId", courseId);

        return "models/hq/course/general/" + typeStr;
    }

    // 课程详情修改 和 删除（该status为1）
    @RequestMapping("/detail/update")
    public void updateDetail(HttpServletRequest request, HttpServletResponse response) {

        String action = ParamUtils.getParameter(request, "action", "");
        long id = ParamUtils.getLongParameter(request, "courseId", 0);
        CourseInfo courseInfo = new CourseInfo();
        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
        UserLogin user = userLoginService.getCurrentUserInfo().getUserLogin();
        BaseJson res = new BaseJson();
        UserProductLog record = new UserProductLog();
        int status = 0;
        if("upd".equals(action)) {
            String name = ParamUtils.getParameter(request, "name", "");
            int rate = ParamUtils.getIntParameter(request, "rate", 0);
            String img = ParamUtils.getParameter(request, "img", "");
            String desc = ParamUtils.getParameter(request, "desc", "");

            courseInfo.setId(id);
            courseInfo.setClassName(name);
            courseInfo.setCourseRate(rate);
            courseInfo.setClassImg(img);
            courseInfo.setClassDesc(desc);

            record.setLogAction(2);
            record.setLogMemo("课程修改");

            status = courseService.updateCourseInfoByPrimaryKeySelective(courseInfo);
        } else if("del".equals(action)) {

            courseService.deleteCourseInfoByCourseId(id);
            // courseInfo.setId(id);
            // courseInfo.setStatus(1);
            status = 1;
            record.setLogAction(1);
            record.setLogMemo("课程删除");
        }

        record.setUserId(user.getRealname());
        record.setSysCustId(custInfo.getSystemId());
        record.setParentCustId(custInfo.getParentId());
        record.setLogType(2);
        record.setObjectId(Long.parseLong(id + ""));
        record.setCreateTime(new Date());
        userProductLogService.insertSelective(record);

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

    // 课程新建
    @RequestMapping("/detail/create")
    public void updateCreate(HttpServletRequest request, HttpServletResponse response) {

        String action = ParamUtils.getParameter(request, "action", "");
        CourseInfo courseInfo = new CourseInfo();

        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
        UserLogin user = userLoginService.getCurrentUserInfo().getUserLogin();

        BaseJson res = new BaseJson();

        long courseId = SequenceUtils.nextId();
        if("cre".equals(action)) {
            String name = ParamUtils.getParameter(request, "name", "");
            int rate = ParamUtils.getIntParameter(request, "rate", 0);
            String img = ParamUtils.getParameter(request, "img", "");
            String desc = ParamUtils.getParameter(request, "desc", "");

            courseInfo.setId(courseId);
            courseInfo.setClassName(name);
            courseInfo.setCourseRate(rate);
            courseInfo.setClassImg(img);
            courseInfo.setClassDesc(desc);
            // SecurityUtils.getSubject().getSession().getAttribute("cust_id");

            courseInfo.setSysCustId(custInfo.getSystemId());
            courseInfo.setParentCustId(custInfo.getParentId());

            courseInfo.setStatus(0);
            courseInfo.setCreateTime(new Date());
        }

        int status = courseService.createCourseInfo(courseInfo);
        res.setStatus(status);

        // 操作日志
        UserProductLog record = new UserProductLog();
        record.setLogAction(0);
        record.setLogMemo("新建课程");
        record.setUserId(user.getRealname());
        record.setSysCustId(custInfo.getSystemId());
        record.setParentCustId(custInfo.getParentId());
        record.setLogType(2);
        record.setObjectId(courseId);
        record.setCreateTime(new Date());
        userProductLogService.insertSelective(record);

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch(IOException e1) {
            logger.debug("CourseAction is error:" + e1.getMessage(), e1);
        }

        // 用Gson转为json
        out.print(new GsonBuilder().create().toJson(res));

    }

    // 课程预约等数据 -----表格导出
    @RequestMapping("/booking/outexcel")
    public void bookingOutExcel(HttpServletRequest request, HttpServletResponse response) {

        String courseId = ParamUtils.getParameter(request, "courseId", "0");

        /** **************上线注意修改 ********************/

        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
        int custId = ParamUtils.getIntParameter(request, "custId", 0);
        // int parentCustId = custInfo.getParentId();
        int sysCustId = custInfo.getSystemId();
        // int parentType = custInfo.getParentType();

        String sqlCust = "";
        if(0 != custId) {
            sqlCust += " and a.sys_cust_id = " + custId;
        } else {
            sqlCust += " and (a.sys_cust_id = " + sysCustId + " or a.parent_cust_id =" + sysCustId + ") ";
        }

        /** **************上线注意修改 *********************/

        // ------------具体情况统计----------------

        List<Map<String, Object>> items = custInfoService.selectCustInfosBySql(
            "SELECT a.id,b.system_id,b.cust_name,a.people_num,a.start_time,a.end_time FROM course_price a,cust_info b WHERE a.end_time<now() and a.course_id = ? AND a.sys_cust_id = b.system_id "
                + sqlCust,
            new Object[]{courseId});
        List<Map<String, Object>> datas = items;
        List<Map<String, Object>> item = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < items.size(); i++) {

            int itemBooking = 0, itemCheck = 0;
            item = orderInfoService.selectBySql(
                "SELECT a.status,COUNT(a.id) as c FROM order_info a,course_price b WHERE b.end_time < now() and a.product_id = b.id AND b.id=? AND a.sys_cust_id = ? AND a.order_type =12  GROUP BY a.status",
                new Object[]{items.get(i).get("id"), items.get(i).get("system_id")});

            if(item.size() > 0) {

                for(int j = 0; j < item.size(); j++) {
                    if("2".equals(item.get(j).get("status") + "")) {
                        itemBooking += Integer.parseInt(item.get(j).get("c") + "");
                    }

                    if("4".equals(item.get(j).get("status") + "")) {
                        itemBooking += Integer.parseInt(item.get(j).get("c") + "");
                        itemCheck = Integer.parseInt(item.get(j).get("c") + "");
                    }
                }

                users = new ArrayList<Map<String, Object>>();
                if(itemCheck < itemBooking) {
                    users = orderInfoService.selectBySql(
                        "SELECT b.member_id,b.member_name,b.member_nick,b.member_logo  FROM order_info a,member_info b WHERE a.product_id = ? AND  a.end_time < now() AND a.sys_cust_id = ?  and a.member_id = b.member_id AND a.order_type =12 and a.status =2",
                        new Object[]{items.get(i).get("id"), items.get(i).get("system_id")});

                }
                datas.get(i).put("users", users);
                datas.get(i).put("itemBooking", itemBooking);
                datas.get(i).put("itemCheck", itemCheck);

            }

        }

        String[] args = {"课程名", "分店", "开始时间", "结束时间", "限制人数", "预约人数", "验证人数", "旷课人数"};

        // HSSFWorkbook wb = courseService.exportReport(datas, hellName, "课程预约验证报表");

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFDataFormat format = wb.createDataFormat();
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)12);// 设置字体大小
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("课程预约验证报表");
        sheet.setDefaultColumnWidth(20);
        // 第三步，在sheet中添加表头第0行
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle styledate = wb.createCellStyle();
        styledate.setDataFormat(format.getFormat("yyyy年m月d日"));
        styledate.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
        styledate.setFont(font);
        // 创建一个居中格式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
        style.setFont(font);
        // 第五步创建列开始写表头的每一列的标题
        HSSFCell cell = null;
        for(int i = 0; i < args.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(args[i]);
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到
        // String[] hellName = { "课程名", "分店", "开始时间", "结束时间", "限制人数", "预约人数",
        // "验证人数", "旷课人" };

        FieldCache fieldCache = new FieldCache("ds1");
        String className = fieldCache.get("course_info", "id:" + courseId + ":class_name");
        for(int i = 0; i < datas.size(); i++) {
            row = sheet.createRow(i + 1);
            Map<String, Object> map = datas.get(i);

            row.createCell(0).setCellValue(className);

            row.createCell(1).setCellValue(map.get("cust_name") + "");

            row.createCell(2).setCellValue(map.get("start_time") + "");
            row.createCell(3).setCellValue(map.get("end_time") + "");
            row.createCell(4).setCellValue(map.get("people_num") + "");
            row.createCell(5).setCellValue(map.get("itemBooking") != null ? map.get("itemBooking") + "" : "");
            row.createCell(6).setCellValue(map.get("itemCheck") != null ? map.get("itemCheck") + "" : "");
            // users=[{member_id=2, member_name=小二黑测试, member_nick=犹豫的小二黑,
            // member_logo=/static/images/jia.png}],
            List<Map<String, Object>> us = (List<Map<String, Object>>)map.get("users");
            if(us != null) {
                row.createCell(7).setCellValue(us.size());
            }else{
                row.createCell(7).setCellValue(0);
            }

        }

        try {
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
            String formatdate = date.format(new Date());
            // 设置导出报表的文件名和文件类型
            response.setHeader("Content-disposition",
                "attachment; filename=" + new String((formatdate).getBytes("gbk"), "iso8859-1") + ".xls");
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.close();
        } catch(Exception e) {
            logger.debug("CourseAction is error:" + e.getMessage(), e);
        }

    }

    // 单个课程预约完成旷课器详情 -----表格导出
    @RequestMapping("/bookingdetail/outexcel")
    public void bookingDetailOutExcel(HttpServletRequest request, HttpServletResponse response) {

        long coursePriceId = ParamUtils.getLongParameter(request, "coursePriceId", 0);

        OrderInfoExample example = new OrderInfoExample();
        example.setOrderByClause(" status desc");
        example.createCriteria().andProductIdEqualTo(coursePriceId);
        List<OrderInfo> datas = orderInfoService.selectByExample(example);

        String[] args = {"用户ID", "用户名","手机号", "预约时间", "开始时间", "结束时间", "状态"};

        // HSSFWorkbook wb = courseService.exportReport(datas, hellName, "课程预约验证报表");

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFDataFormat format = wb.createDataFormat();
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)12);// 设置字体大小
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("课程预约验证报表");
        sheet.setDefaultColumnWidth(20);
        // 第三步，在sheet中添加表头第0行
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle styledate = wb.createCellStyle();
        styledate.setDataFormat(format.getFormat("yyyy年m月d日"));
        styledate.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
        styledate.setFont(font);
        // 创建一个居中格式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
        style.setFont(font);
        // 第五步创建列开始写表头的每一列的标题
        HSSFCell cell = null;
        for(int i = 0; i < args.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(args[i]);
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到
        // String[] hellName = { "课程名", "分店", "开始时间", "结束时间", "限制人数", "预约人数",
        // "验证人数", "旷课人" };

        // FieldCache fieldCache = new FieldCache("ds1");
        // String className = fieldCache.get("course_info", "id:" + courseId + ":class_name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String status = "";
        Date now = new Date();
        for(int i = 0; i < datas.size(); i++) {
            row = sheet.createRow(i + 1);
            OrderInfo orderInfo = datas.get(i);

            row.createCell(0).setCellValue(orderInfo.getMemberId() + "");
            row.createCell(1).setCellValue(JSTLFuncs.getmemberNameBymemberId(orderInfo.getMemberId() + ""));
            row.createCell(2).setCellValue(orderInfo.getLinkPhone());
            row.createCell(3).setCellValue(sdf.format(orderInfo.getCreteTime()));
            row.createCell(4).setCellValue(sdf.format(orderInfo.getStartTime()));
            row.createCell(5).setCellValue(sdf.format(orderInfo.getEndTime()));

            if(orderInfo.getStatus() == 2) {
                status = orderInfo.getEndTime().getTime() < now.getTime() ? "已旷课" : "预约成功";
            } else if(orderInfo.getStatus() == 3) {
                status = "已取消";
            } else if(orderInfo.getStatus() == 4) {
                status = "已完成";
            }

            row.createCell(6).setCellValue(status);
           

        }

        try {
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
            String formatdate = date.format(new Date());
            // 设置导出报表的文件名和文件类型
            response.setHeader("Content-disposition",
                "attachment; filename=" + new String((formatdate).getBytes("gbk"), "iso8859-1") + ".xls");
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.close();
        } catch(Exception e) {
            logger.debug("CourseAction is error:" + e.getMessage(), e);
        }

    }

    // 评论回复 和 删除评论（该status为1）
    @RequestMapping("/comment/update")
    public void updateComment(HttpServletRequest request, HttpServletResponse response) {
        String action = ParamUtils.getParameter(request, "action", "");
        String id = ParamUtils.getParameter(request, "commentId", "");
        CommentInfoWithBLOBs comment = new CommentInfoWithBLOBs();

        BaseJson res = new BaseJson();

        if("upd".equals(action)) {
            String reply = ParamUtils.getParameter(request, "commentContent", "");
            comment.setId(Integer.parseInt(id));
            comment.setReply(reply);
            comment.setReplyTime(new Date());
        } else if("del".equals(action)) {
            comment.setId(Integer.parseInt(id));
            comment.setStatus(1);
            comment.setReplyTime(new Date());
        }

        int status = commentService.updateCommentReplyByPrimaryKeySelective(comment);

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

    @RequestMapping("/coursebookingdetail")
    public String coursebookingdetail(HttpServletRequest request, Model model) {

        String coursePriceId = ParamUtils.getParameter(request, "coursePriceId", "0");
        try {

            OrderInfoExample example = new OrderInfoExample();
            example.setOrderByClause(" status desc");
            example.createCriteria().andProductIdEqualTo(Long.parseLong(coursePriceId));
            List<OrderInfo> orders = orderInfoService.selectByExample(example);

            model.addAttribute("coursePriceId", coursePriceId);
            model.addAttribute("now", new Date());
            model.addAttribute("orders", orders);
        } catch(Exception e) {
            logger.error("coursebookingdetail Error:" + e.getMessage(), e);
        }

        return "models/hq/course/general/coursebookingdetail";

    }

}