package com.lol.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lol.common.Constants;
import com.lol.common.bean.BaseJson;
import com.lol.common.bean.CurrentUserInfo;
import com.lol.common.bean.MsgBuilder;
import com.lol.common.bean.MsgType;
import com.lol.common.model.ds1.CoachInfo;
import com.lol.common.model.ds1.CoachInfoExample;
import com.lol.common.model.ds1.CoursePriceExample;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;
import com.lol.common.model.ds1.MemberInfo;
import com.lol.common.model.ds1.MemberInfoExample;
import com.lol.common.model.ds1.OrderConsumeLog;
import com.lol.common.model.ds1.OrderInfo;
import com.lol.common.model.ds1.OrderInfoExample;
import com.lol.common.model.ds1.UserLog;
import com.lol.common.model.ds1.UserLogExample;
import com.lol.common.model.ds1.UserLogin;
import com.lol.common.plugin.Page;
import com.lol.common.plugin.Pager;
import com.lol.common.plugin.PagerService;
import com.lol.common.service.CoachInfoService;
import com.lol.common.service.CourseService;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.MemberService;
import com.lol.common.service.OrderConsumeLogService;
import com.lol.common.service.OrderInfoService;
import com.lol.common.service.UserLogService;
import com.lol.common.service.UserLoginService;
import com.lol.common.util.IntUtil;
import com.lol.common.util.ParamUtils;
import com.lol.common.util.SequenceUtils;
import com.lol.web.JSTLFuncs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

/**
 * 
 * 教练管理
 * 
 * @author xumao
 *
 */
@Controller
@RequestMapping("/coach")
public class CoachInfoAction {

    Logger logger = LoggerFactory.getLogger(CoachInfoAction.class);

    @Resource
    private CoachInfoService coachInfoService;

    @Resource
    private CustInfoService custInfoService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private UserLogService userLogService;

    @Resource
    private MemberService memberService;

    @Resource
    private CourseService courseService;

    @Resource
    private OrderConsumeLogService orderConsumeLogService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model) {

        int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        CustInfo currentCustInfo = currentUserInfo.getCustInfo();
        int custId = currentCustInfo.getSystemId();
        int parentType = currentCustInfo.getParentType();
        try {
            if(parentType == Constants.CUST_TYPE_HQ) {
                String selectsql = "select system_id,cust_name from cust_info  where  parent_type=2 order by id";
                List<Map<String, Object>> custList = coachInfoService.getList(selectsql);
                CoachInfoExample example = new CoachInfoExample();
                CoachInfoExample.Criteria criteria = example.createCriteria();
                criteria.andStatusEqualTo(0);
                criteria.andParentCustIdEqualTo(custId);
                example.setOrderByClause("coach_type  desc");
                int pageCount = coachInfoService.countByExample(example);
                int pageSize = 10;
                int offSet = 4;
                Page pages = new Page();
                pages.setTotal(pageCount);
                pages.setLimit(pageSize);
                pages.setNo(currentPage);
                example.setPage(pages);
                Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
                List<CoachInfo> coachList = coachInfoService.selectByExample(example);
                model.addAttribute("coachList", coachList);
                model.addAttribute("pager", page);
                model.addAttribute("custList", custList);
            } else {
                CoachInfoExample example = new CoachInfoExample();
                CoachInfoExample.Criteria criteria = example.createCriteria();
                criteria.andStatusEqualTo(0);
                example.setOrderByClause("coach_type  desc");
                // List<CoachInfo> coachList2 = coachInfoService.selectByExample(example);
                if(!"".equals(custId)) {
                    criteria.andUseCustIdLike("%," + String.valueOf(custId) + ",%");
                }
                int pageCount = coachInfoService.countByExample(example);

                int pageSize = 10;
                int offSet = 4;
                Page pages = new Page();
                pages.setTotal(pageCount);
                pages.setLimit(pageSize);
                pages.setNo(currentPage);
                example.setPage(pages);
                Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
                List<CoachInfo> coachList1 = coachInfoService.selectByExample(example);
                String selectsql1 = "SELECT c.id,c.name,c.sign_img,c.use_cust_id,c.coach_type "
                    + "from  coach_info c where STATUS=0   order by coach_type desc ";
                List<Map<String, Object>> coachList2 = coachInfoService.getList(selectsql1);
                model.addAttribute("coachList1", coachList1);
                model.addAttribute("coachList2", coachList2);
                model.addAttribute("pager", page);
            }
        } catch(Exception e) {
            logger.error("CoachInfoAction.toQuality Error:" + e.getMessage(), e);
        }

        if(parentType == Constants.CUST_TYPE_HQ) {
            return "models/hq/coach/list";
        } else {
            return "models/branch/coach/list";
        }
    }

    @RequestMapping("/save")
    public void save(HttpServletRequest request, HttpServletResponse response) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        CustInfo currentCustInfo = currentUserInfo.getCustInfo();
        UserLogin userLogin = currentUserInfo.getUserLogin();
        int custId = currentCustInfo.getSystemId();

        int coachType = ParamUtils.getIntParameter(request, "coachType");
        String fee = ParamUtils.getParameter(request, "fee");
        int mul = 0;
        if(fee != "" && !"".equals(fee)) {
            mul = IntUtil.mul(fee, "100");
        } else {
            mul = 0;
        }
        String salePrice = ParamUtils.getParameter(request, "salePrice");
        int sale = 0;
        if(salePrice != "" && !"".equals(salePrice)) {
            sale = IntUtil.mul(salePrice, "100");
        } else {
            sale = 0;
        }
        String useCustId = ParamUtils.getParameter(request, "useCustId", "");
        if(useCustId == "" && "".equals(useCustId)) {
            useCustId = "";
        } else {
            useCustId = ',' + useCustId + ',';
        }
        // System.out.println(useCustId);
        String name = ParamUtils.getParameter(request, "name", "");
        String mobile = ParamUtils.getParameter(request, "mobile", "");
        String openImg = ParamUtils.getParameter(request, "openImg", "");
        String signImg = ParamUtils.getParameter(request, "signImg", "");
        String proDesc = ParamUtils.getParameter(request, "proDesc", "");
        int sort = ParamUtils.getIntParameter(request, "sort", 0);
        
        BaseJson baseJson = new BaseJson();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        // 生成主键id
        long id = SequenceUtils.nextId();
        try {
            CoachInfo coachInfo = new CoachInfo();
            coachInfo.setId(id);
            coachInfo.setName(name);
            coachInfo.setMobile(mobile);
            coachInfo.setOpenImg(openImg);
            coachInfo.setSignImg(signImg);
            coachInfo.setCoachType(coachType);
            coachInfo.setFee(mul);
            coachInfo.setSalePrice(sale);
            coachInfo.setUseCustId(useCustId);
            coachInfo.setProDesc(proDesc);
            coachInfo.setStatus(0);
            coachInfo.setCreateTime(new Date());
            coachInfo.setParentCustId(custId);
            coachInfo.setSysCustId(custId);
            coachInfo.setSort(sort);

            // 新增一条记录
            UserLog log = new UserLog();
            log.setLogType(0);
            log.setUserId(userLogin.getUsername());
            log.setCreateTime(new Date());
            log.setLogAction(0);
            log.setLogMemo("总店教练新增");
            log.setObjectId(id);
            log.setSysCustId(currentCustInfo.getSystemId());
            coachInfoService.insertSelective(coachInfo);
            userLogService.insertSelective(log);
            baseJson.setStatus(0);
            baseJson.setMsg("操作成功");
            baseJson.setData(coachInfo);
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

    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        CustInfo currentCustInfo = currentUserInfo.getCustInfo();
        UserLogin userLogin = currentUserInfo.getUserLogin();
        long id = ParamUtils.getLongParameter(request, "id");
        int coachType = ParamUtils.getIntParameter(request, "coachType");
        int sort = ParamUtils.getIntParameter(request, "sort", 0);
        String fee = ParamUtils.getParameter(request, "fee");
        int mul = 0;
        if(fee != "" && !"".equals(fee)) {
            mul = IntUtil.mul(fee, "100");
        } else {
            mul = 0;
        }
        String salePrice = ParamUtils.getParameter(request, "salePrice");
        int sale = 0;
        if(salePrice != "" && !"".equals(salePrice)) {
            sale = IntUtil.mul(salePrice, "100");
        } else {
            sale = 0;
        }
        String useCustId = ParamUtils.getParameter(request, "useCustId", "");
        if(useCustId == "" && "".equals(useCustId)) {
            useCustId = "";
        } else {
            useCustId = "," + useCustId + ',';
        }
        String name = ParamUtils.getParameter(request, "name", "");
        String mobile = ParamUtils.getParameter(request, "mobile", "");
        String openImg = ParamUtils.getParameter(request, "openImg", "");
        String signImg = ParamUtils.getParameter(request, "signImg", "");
        String proDesc = ParamUtils.getParameter(request, "proDesc", "");

        BaseJson baseJson = new BaseJson();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        try {
            CoachInfo coachInfo = new CoachInfo();
            coachInfo.setId(id);
            coachInfo.setName(name);
            coachInfo.setMobile(mobile);
            coachInfo.setOpenImg(openImg);
            coachInfo.setSignImg(signImg);
            coachInfo.setCoachType(coachType);
            coachInfo.setFee(mul);
            coachInfo.setSalePrice(sale);
            coachInfo.setUseCustId(useCustId);
            coachInfo.setProDesc(proDesc);
            coachInfo.setSort(sort);
            // 修改一条记录
            UserLog log = new UserLog();
            log.setLogType(3);
            log.setUserId(userLogin.getUsername());
            log.setCreateTime(new Date());
            log.setLogAction(2);
            log.setLogMemo("总店教练修改");
            log.setObjectId(id);
            log.setSysCustId(currentCustInfo.getSystemId());
            coachInfoService.updateByPrimaryKeySelective(coachInfo);
            userLogService.insertSelective(log);
            baseJson.setStatus(0);
            baseJson.setMsg("操作成功");
            baseJson.setData(coachInfo);
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

    @RequestMapping("/detail") // 1.详情
    public String detail(HttpServletRequest request, Model model) {
        try {
            long id = ParamUtils.getLongParameter(request, "id");

            int coachType = ParamUtils.getIntParameter(request, "coachType");
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo currentCustInfo = currentUserInfo.getCustInfo();
            int parentType = currentCustInfo.getParentType();

            String selectsql = "select system_id,cust_name from cust_info  where  parent_type=2 order by id";
            List<Map<String, Object>> custList = coachInfoService.getList(selectsql);
            CoachInfo coachInfo = coachInfoService.selectByPrimaryKey(id);
            String useCustId = coachInfo.getUseCustId();
            if(useCustId != "") {
                String substring = useCustId.substring(1, useCustId.length() - 1);
                String[] split = substring.split(",");
                model.addAttribute("split", split);
            }
            // 查询日志
            UserLogExample example = new UserLogExample();
            UserLogExample.Criteria criteria = example.createCriteria();
            criteria.andLogTypeEqualTo(3);
            criteria.andObjectIdEqualTo(id);
            List<UserLog> userLogList = userLogService.selectByExample(example);
            model.addAttribute("coachInfo", coachInfo);
            model.addAttribute("userLogList", userLogList);
            model.addAttribute("custList", custList);
            model.addAttribute("id", id);
            model.addAttribute("coachType", coachType);
            if(parentType == Constants.CUST_TYPE_HQ) {
                return "models/hq/coach/coachdetail";
            } else {
                return "models/branch/coach/coachdetail";
            }
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "models/branch/coach/coachdetail";
    }

    @RequestMapping("/coachclass") // 3.评价
    public String coachclass(HttpServletRequest request, Model model) {
        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
        int SystemCustId = custInfo.getSystemId();
        int parentCustId = custInfo.getParentId();

        if(parentCustId == 0) {
            parentCustId = SystemCustId;
        }
        // 总店：0，分店：1
        int parentType = custInfo.getParentType();
        try {
            long id = ParamUtils.getLongParameter(request, "id");
            int coachType = ParamUtils.getIntParameter(request, "coachType");
            // CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            // CustInfo currentCustInfo = currentUserInfo.getCustInfo();
            // int custId = currentCustInfo.getSystemId();
            // int parentType = currentCustInfo.getParentType();
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
            criteria.andCoachIdEqualTo(id);
            String sqlCust = " and a.parent_cust_id = " + parentCustId;
            if(0 != custId) {
                criteria.andSysCustIdEqualTo(custId);
                sqlCust += " and a.sys_cust_id = " + custId;
            }
            criteria.andParentCustIdEqualTo(parentCustId);

            // 总上课
            criteria.andEndTimeLessThanOrEqualTo(new Date());
            // 已上课
            int alreadyCount = coachInfoService.selectCountNum(cpExample);
            // 预约总人数
            List<Map<String, Object>> bookingCount = orderInfoService.selectBySql(
                "SELECT COUNT(1) AS num FROM order_info a ,course_price c WHERE a.product_id =c.id and  c.coach_id= ?  and a.status in(2,4) AND a.order_type =12 AND c.end_time <= NOW()"
                    + sqlCust,
                new Object[]{id});
            // 验证人数
            List<Map<String, Object>> checkCount = orderInfoService.selectBySql(
                "SELECT COUNT(1) AS num FROM order_info a ,course_price c WHERE a.product_id =c.id and  c.coach_id= ? and a.status = 4 AND a.order_type =12 AND c.end_time <= NOW()"
                    + sqlCust,
                new Object[]{id});
            // 限制人数
            List<Map<String, Object>> limitCount = orderInfoService.selectBySql(
                "SELECT SUM(a.people_num) as num FROM course_price a WHERE a.coach_id = ? AND a.end_time <= NOW()" + sqlCust,
                new Object[]{id});

            // 预约率
            float bookingRate = 0;
            if(bookingCount != null && limitCount != null && bookingCount.size() > 0 && limitCount.get(0).get("num") != null) {
                bookingRate = Float.parseFloat(bookingCount.get(0).get("num") + "")
                    / Float.parseFloat(limitCount.get(0).get("num") == null ? "0" : limitCount.get(0).get("num") + "") * 100;
            }
            // 到课率
            float checkRate = 0;
            if(checkCount != null && bookingCount != null && checkCount.size() > 0
                && !"0".equals(bookingCount.get(0).get("num") + "")) {
                checkRate = Float.parseFloat(checkCount.get(0).get("num") + "")
                    / Float.parseFloat(bookingCount.get(0).get("num") + "") * 100;
            }
            // 详单
            List<Map<String, Object>> items = custInfoService.selectCustInfosBySql(
                "SELECT a.id,b.system_id,b.cust_name,a.people_num,a.start_time,a.end_time FROM course_price a,cust_info b WHERE a.coach_id = ? AND a.sys_cust_id = b.system_id "
                    + sqlCust,
                new Object[]{id});

            List<Map<String, Object>> datas = items;
            List<Map<String, Object>> item = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
            for(int i = 0; i < items.size(); i++) {

                int itemBooking = 0, itemCheck = 0;
                item = orderInfoService.selectBySql(
                    "SELECT a.status,COUNT(a.id) as c FROM order_info a,course_price b WHERE a.product_id = b.id AND b.id=? AND a.sys_cust_id = ? AND a.order_type =12  GROUP BY a.status",
                    new Object[]{items.get(i).get("id"), items.get(i).get("system_id")});

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
                            "SELECT b.member_id,b.member_name,b.member_nick,b.member_logo  FROM order_info a,member_info b WHERE a.product_id = ?  AND a.sys_cust_id = ?  AND a.order_type =12 and a.member_id = b.member_id and a.status =2",
                            new Object[]{items.get(i).get("id"), items.get(i).get("system_id")});
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
            model.addAttribute("id", id);
            model.addAttribute("coachType", coachType);
            model.addAttribute("parentType", parentType);
            model.addAttribute("datas", datas);

        } catch(Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "models/branch/coach/coachclass";
    }

    @RequestMapping("/coachcomment") // 3.评价
    public String coachcomment(HttpServletRequest request, Model model) {
        try {
            long id = ParamUtils.getLongParameter(request, "id");
            int coachType = ParamUtils.getIntParameter(request, "coachType");
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo currentCustInfo = currentUserInfo.getCustInfo();
            int custId = currentCustInfo.getSystemId();
            float avg = coachInfoService.getScoureAvg(id, custId);
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);

            int pageSize = 10;
            int offSet = 5;
            int pageCount = coachInfoService.getCountCommentAndMember(id);
            List<Map<String, Object>> comments = coachInfoService.getCommentAndMember(id, currentPage, pageSize);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
            model.addAttribute("pageCount", pageCount);
            model.addAttribute("pager", page);
            model.addAttribute("avg", avg);
            model.addAttribute("comments", comments);
            model.addAttribute("id", id);
            model.addAttribute("coachType", coachType);
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "models/branch/coach/coachcomment";
    }

    @RequestMapping("/coachsijiao") // 4.私教
    public String coachsijiao(HttpServletRequest request, Model model) {
        try {
            long id = ParamUtils.getLongParameter(request, "id");
            int coachType = ParamUtils.getIntParameter(request, "coachType");
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo currentCustInfo = currentUserInfo.getCustInfo();
            int custId = currentCustInfo.getSystemId();
            int parentType = currentCustInfo.getParentType();
            // 售卖条数
            int countOrders = coachInfoService.getCountOrders(id);
            // 以售卖课时
            int countOrder = coachInfoService.getCountOrder(id);
            // 已上课时
            int countReady = coachInfoService.getCountReady(id);
            // 待上课
            OrderInfoExample example = new OrderInfoExample();
            OrderInfoExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(2).andRemainCountGreaterThan(0).andProductIdEqualTo(id).andOrderTypeEqualTo(13);
            if(parentType == Constants.CUST_TYPE_HQ) {
                criteria.andParentCustIdEqualTo(custId);
            } else {
                criteria.andSysCustIdEqualTo(custId);
            }
            List<OrderInfo> commentCourse = orderInfoService.selectByExample(example);
            // 已完成
            String selectSql1 =
                "SELECT o.link_man,o.order_count,o.remain_count,o.link_phone,o.pay_time,o.sys_cust_id,f.finish_time from  order_info  o  ,order_finish_log f  where  o.id=f.order_id  and  f.info_id=?";

            List<Map<String, Object>> commentReady = orderInfoService.getList(selectSql1, new Object[]{id});
            model.addAttribute("commentReady", commentReady);

            // 退款会员

            String selectSql2 =
                "SELECT o.link_man,o.order_count,o.remain_count,o.link_phone,o.pay_time,o.sys_cust_id,f.finish_time from  order_info  o  ,order_finish_log f  where  o.id=f.order_id and o.`status`=5 and  f.info_id=?";
            List<Map<String, Object>> reFund = orderInfoService.getList(selectSql2, new Object[]{id});
            model.addAttribute("commentCourse", commentCourse);
            model.addAttribute("reFund", reFund);
            model.addAttribute("countOrders", countOrders);
            model.addAttribute("countOrder", countOrder);
            model.addAttribute("countReady", countReady);
            model.addAttribute("id", id);
            model.addAttribute("coachType", coachType);
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "models/branch/coach/coachsijiao";
    }

    // 已完成
    @RequestMapping("/outPrepareExcel")
    public void outPrepareExcel(HttpServletRequest request, HttpServletResponse response) {
        long id = ParamUtils.getLongParameter(request, "infoid");

        // 已完成
        String selectSql =
            "SELECT id,link_phone,member_id,pay_time,sys_cust_id,product_id,remain_count FROM order_info WHERE  STATUS =2 AND remain_count >0 AND product_id=?";

        List<Map<String, Object>> datas = orderInfoService.getList(selectSql, new Object[]{id});

        // String[] hellName = {"教练名", "分店", "开始时间", "结束时间", "限制人数", "预约人数", "验证人数", "旷课人"};

        String[] hellName = {"教练名", "分店名", "支付时间", "剩余次数", "用户名", "手机号", "订单号"};

        HSSFWorkbook wb = coachInfoService.exportContent(datas, hellName, "教练未上课报表");

        // exportReport(datas, hellName, "教练预约验证报表");
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
            e.printStackTrace();
        }

    }

    // 已完成
    @RequestMapping("/outCompletedExcel")
    public void outCompletedExcel(HttpServletRequest request, HttpServletResponse response) {
        long id = ParamUtils.getLongParameter(request, "infoid");

        // 已完成
        String selectSql =
            "SELECT o.id,o.link_phone,o.member_id,o.pay_time,o.sys_cust_id,o.product_id,f.finish_time FROM order_info o,order_finish_log f WHERE o.id=f.order_id and f.info_id=?";

        List<Map<String, Object>> datas = orderInfoService.getList(selectSql, new Object[]{id});

        // String[] hellName = {"教练名", "分店", "开始时间", "结束时间", "限制人数", "预约人数", "验证人数", "旷课人"};

        String[] hellName = {"教练名", "分店名", "支付时间", "验证时间", "用户名", "手机号", "订单号"};

        HSSFWorkbook wb = coachInfoService.exportContent(datas, hellName, "教练验证报表");

        // exportReport(datas, hellName, "教练预约验证报表");
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
            e.printStackTrace();
        }

    }

    @RequestMapping("/del")
    public void del(HttpServletRequest request, HttpServletResponse response) {
        BaseJson baseJson = new BaseJson();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        try {

            long id = ParamUtils.getLongParameter(request, "id");
            String useCustId = ParamUtils.getParameter(request, "useCustId", "");
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo currentCustInfo = currentUserInfo.getCustInfo();
            UserLogin userLogin = currentUserInfo.getUserLogin();
            int custId = currentCustInfo.getSystemId();
            String custId1 = String.valueOf(custId);
            int parentType = currentCustInfo.getParentType();
            CoachInfo coachInfo = new CoachInfo();
            coachInfo.setId(id);
            if(parentType == Constants.CUST_TYPE_HQ) {
                coachInfo.setStatus(2);
                coachInfoService.updateByPrimaryKeySelective(coachInfo);
                baseJson.setStatus(0);
                baseJson.setMsg("操作成功");
                baseJson.setData(coachInfo);
            } else {
                String[] split = useCustId.substring(1, useCustId.length() - 1).split(",");
                List<String> list1 = new ArrayList<String>();
                List<String> list2 = new ArrayList<String>();
                for(String useCust: split) {
                    list1.add(useCust);
                    if(!useCust.equals(custId1)) {
                        list2.add(useCust);
                    }
                    if(list2 != null && !"".equals("")) {
                        useCustId = "," + StringUtils.join(list2, ",") + ",";;
                    } else {
                        useCustId = "";
                    }
                }
                coachInfo.setUseCustId(useCustId);
                coachInfoService.updateByPrimaryKeySelective(coachInfo);
                baseJson.setStatus(0);
                baseJson.setMsg("操作成功");
                baseJson.setData(coachInfo);
            }
            // 删除一条记录
            UserLog log = new UserLog();
            log.setLogType(2);
            log.setUserId(userLogin.getUsername());
            log.setCreateTime(new Date());
            log.setLogAction(2);
            log.setLogMemo("总店教练删除");
            log.setObjectId(id);
            log.setSysCustId(currentCustInfo.getSystemId());
            userLogService.insertSelective(log);
            baseJson.setStatus(0);
            baseJson.setMsg("操作成功");
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

    @RequestMapping("/updateRefund")
    public void listRefund(HttpServletRequest request, HttpServletResponse response) {
        BaseJson baseJson = new BaseJson();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        try {
            long orderid = ParamUtils.getLongParameter(request, "orderid");
            int actualMoney = ParamUtils.getIntParameter(request, "actualMoney");
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setId(orderid);
            orderInfo.setActualMoney(actualMoney);
            orderInfo.setStatus(5);
            orderInfo.setRefundTime(new Date());
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo currentCustInfo = currentUserInfo.getCustInfo();
            UserLogin userLogin = currentUserInfo.getUserLogin();
            String userName = userLogin.getUsername();
            orderInfo.setRefundUser(userName);
            // 删除一条记录
            UserLog log = new UserLog();
            log.setLogType(2);
            log.setUserId(userName);
            log.setCreateTime(new Date());
            log.setLogAction(2);
            log.setLogMemo("订单退款");
            log.setObjectId(orderid);
            log.setSysCustId(currentCustInfo.getSystemId());
            orderInfoService.updateByPrimaryKeySelective(orderInfo);
            userLogService.insertSelective(log);
            baseJson.setStatus(0);
            baseJson.setMsg("操作成功");
            baseJson.setData(orderInfo);
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

    @RequestMapping("/outexcel")
    public void outexcel(HttpServletRequest request, HttpServletResponse response) {
        long id = ParamUtils.getLongParameter(request, "id");

        /** **************上线注意修改 ********************/

        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
        int custId = ParamUtils.getIntParameter(request, "custId", 0);
        int sysCustId = custInfo.getSystemId();

        String sqlCust = "";
        if(0 != custId) {
            sqlCust += " and a.sys_cust_id = " + custId;
        } else {
            sqlCust += " and (a.sys_cust_id = " + sysCustId + " or a.parent_cust_id =" + sysCustId + ") ";
        }

        List<Map<String, Object>> items = custInfoService.selectCustInfosBySql(
            "SELECT a.id,a.coach_id,b.system_id,b.cust_name,a.people_num,a.start_time,a.end_time FROM course_price a,cust_info b WHERE a.coach_id = ? AND a.sys_cust_id = b.system_id "
                + sqlCust,
            new Object[]{id});

        List<Map<String, Object>> datas = items;
        List<Map<String, Object>> item = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < items.size(); i++) {

            int itemBooking = 0, itemCheck = 0;
            item = orderInfoService.selectBySql(
                "SELECT a.status,COUNT(a.id) as c FROM order_info a,course_price b WHERE a.product_id = b.id AND b.id=? AND a.sys_cust_id = ? AND a.order_type =12  GROUP BY a.status",
                new Object[]{items.get(i).get("id"), items.get(i).get("system_id")});

            // System.out.println("SELECT a.status,COUNT(a.id) as c FROM order_info a,course_price b WHERE a.product_id = b.id AND
            // b.id="+items.get(i).get("id")+" AND a.sys_cust_id = "+items.get(i).get("system_id")+" AND a.order_type =12 AND
            // a.parent_cust_id = "+parentCustId+" GROUP BY a.status");

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
                        "SELECT b.member_id,b.member_name,b.member_nick,b.member_logo  FROM order_info a,member_info b WHERE a.product_id = ?  AND a.sys_cust_id = ? AND a.order_type =12 and a.member_id = b.member_id and a.status =2",
                        new Object[]{items.get(i).get("id"), items.get(i).get("system_id")});
                }
                datas.get(i).put("users", users);
                datas.get(i).put("itemBooking", itemBooking);
                datas.get(i).put("itemCheck", itemCheck);

            }

        }

        String[] hellName = {"教练名", "分店", "开始时间", "结束时间", "限制人数", "预约人数", "验证人数", "旷课人"};

        HSSFWorkbook wb = coachInfoService.exportReport(datas, hellName, "教练预约验证报表");
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
            e.printStackTrace();
        }

    }

    @RequestMapping("/manager")
    public String manager(HttpServletRequest request, Model model) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        CustInfo currentCustInfo = currentUserInfo.getCustInfo();
        int custId = currentCustInfo.getSystemId();
        int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
        long custInfoId = ParamUtils.getLongParameter(request, "custInfoId", 0);
        String selectsql = "select system_id,cust_name from cust_info  where  parent_type=2 order by id";
        List<Map<String, Object>> custList = coachInfoService.getList(selectsql);
        int systemId = 0;
        if(custList != null && custList.size() > 0) {
            for(int x = 0; x < custList.size(); x++) {
                systemId = Integer.parseInt(custList.get(0).get("system_id").toString());
            }
        }
        if(custInfoId == 0) {
            custInfoId = systemId;
        }
        CoachInfoExample example = new CoachInfoExample();
        CoachInfoExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(0);
        criteria.andParentCustIdEqualTo(custId);
        example.setOrderByClause("coach_type  desc");
        // List<CoachInfo> coachAll = coachInfoService.selectByExample(example);
        if(!"".equals(custInfoId) && custInfoId > 0) {
            criteria.andUseCustIdLike("%," + String.valueOf(custInfoId) + ",%");
        }
        int pageCount = coachInfoService.countByExample(example);
        int pageSize = 8;
        int offSet = 4;
        Page pages = new Page();
        pages.setTotal(pageCount);
        pages.setLimit(pageSize);
        pages.setNo(currentPage);
        example.setPage(pages);
        Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
        List<CoachInfo> coachList = coachInfoService.selectByExample(example);
        String selectsql1 = "SELECT c.id,c.name,c.sign_img,c.use_cust_id,c.coach_type "
            + "from  coach_info c where STATUS=0   order by coach_type desc  ";
        List<Map<String, Object>> coachAll = coachInfoService.getList(selectsql1);
        model.addAttribute("coachList", coachList);
        model.addAttribute("custList", custList);
        model.addAttribute("custInfoId", custInfoId);
        model.addAttribute("coachAll", coachAll);
        model.addAttribute("pager", page);

        return "models/hq/coach/coachmanager";

    }

    @RequestMapping("/andcoach")
    public void andcoach(HttpServletRequest request, HttpServletResponse response) {
        BaseJson baseJson = new BaseJson();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        long custInfoId = ParamUtils.getLongParameter(request, "custInfoId");

        String id = ParamUtils.getParameter(request, "data", "");
        try {
            String[] ids = id.split(",");
            for(int i = 0; i < ids.length; i++) {
                CoachInfo key = coachInfoService.selectByPrimaryKey(Long.parseLong(ids[i]));
                String useCustIds = key.getUseCustId();
                long coachId = key.getId();
                if(useCustIds != null && !"".equals(useCustIds)) {
                    if(useCustIds.contains(String.valueOf(custInfoId))) {
                        useCustIds = useCustIds + "";
                    } else {

                        useCustIds = useCustIds + String.valueOf(custInfoId) + ",";
                    }
                } else {
                    useCustIds = "," + String.valueOf(custInfoId) + ",";
                }

                CoachInfo coachInfo = new CoachInfo();
                coachInfo.setUseCustId(useCustIds);
                coachInfo.setId(coachId);
                coachInfoService.updateByPrimaryKeySelective(coachInfo);
                baseJson.setStatus(0);
                baseJson.setMsg("操作成功");
                baseJson.setData(coachInfo);
            }

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

    @RequestMapping("/andbranch")
    public void andbranch(HttpServletRequest request, HttpServletResponse response) {
        BaseJson baseJson = new BaseJson();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        CustInfo currentCustInfo = currentUserInfo.getCustInfo();
        int custId = currentCustInfo.getSystemId();
        String id = ParamUtils.getParameter(request, "data", "");
        try {
            String[] ids = id.split(",");
            for(int i = 0; i < ids.length; i++) {
                CoachInfo key = coachInfoService.selectByPrimaryKey(Long.parseLong(ids[i]));
                String useCustIds = key.getUseCustId();
                long coachId = key.getId();
                if(useCustIds != null && !"".equals(useCustIds)) {
                    if(useCustIds.contains(String.valueOf(custId))) {
                        useCustIds = useCustIds + "";
                    } else {

                        useCustIds = useCustIds + String.valueOf(custId) + ",";
                    }
                } else {
                    useCustIds = "," + String.valueOf(custId) + ",";
                }

                CoachInfo coachInfo = new CoachInfo();
                coachInfo.setUseCustId(useCustIds);
                coachInfo.setId(coachId);
                coachInfoService.updateByPrimaryKeySelective(coachInfo);
                baseJson.setStatus(0);
                baseJson.setMsg("操作成功");
                baseJson.setData(coachInfo);
            }

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

    // 线下购卡
    @RequestMapping("/line/create")
    public void lineCreate(HttpServletRequest request, HttpServletResponse response) {
        BaseJson baseJson = new BaseJson();
        Gson gson = new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING)
            .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        try {
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo custInfo = currentUserInfo.getCustInfo();
            int parentCustId = custInfo.getParentId();
            int sysCustId = custInfo.getSystemId();
            String memberId = ParamUtils.getParameter(request, "memberId");
            long productId = ParamUtils.getLongParameter(request, "id");
            String mobile = ParamUtils.getParameter(request, "mobile");
            int payType = ParamUtils.getIntParameter(request, "payType", 0);
            int num = ParamUtils.getIntParameter(request, "num");

            MemberInfo primaryKey = memberService.selectByPrimaryKey(Long.valueOf(memberId));
            CoachInfo coachInfo = coachInfoService.selectByPrimaryKey(productId);
            long id = SequenceUtils.nextId();
            long code = SequenceUtils.getCode(id);
            String custName = "";
            custName = JSTLFuncs.getCustNameByCustId(String.valueOf(sysCustId));
            MsgBuilder msgBuilder = new MsgBuilder();
            msgBuilder.setType(MsgType.COACH);
            String msg = msgBuilder.create(new Object[]{coachInfo.getName(), num, custName, code, Constants.SMS_SPORT_PHONE});
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setPayTime(new Date());
            orderInfo.setStatus(2);
            orderInfo.setPayType(payType);
            orderInfo.setId(id);
            orderInfo.setOrderCode(code + "");
            orderInfo.setCreteTime(new Date());
            orderInfo.setSendContent(msg);
            orderInfo.setProductId(productId);
            orderInfo.setNum(num);
            orderInfo.setSalePrice(coachInfo.getSalePrice());
            orderInfo.setSysCustId(sysCustId);
            orderInfo.setOrderType(13);
            orderInfo.setParentCustId(parentCustId);
            orderInfo.setOrderCount(num);
            orderInfo.setRemainCount(num);
            orderInfo.setMemPrice(coachInfo.getSalePrice() * num);
            orderInfo.setMemberId(Long.valueOf(memberId));
            orderInfo.setLinkMan(primaryKey.getMemberName());
            orderInfo.setLinkPhone(mobile);
            orderInfoService.insertSelective(orderInfo);
            baseJson.setStatus(0);
            baseJson.setMsg("操作成功");
            baseJson.setData(orderInfo);
            if(baseJson.getStatus() == 0) {
                CustInfo custInfo1 = new CustInfo();
                CustInfoExample example = new CustInfoExample();
                CustInfoExample.Criteria criteria = example.createCriteria();
                criteria.andSystemIdEqualTo(parentCustId);
                List<CustInfo> custInfos = custInfoService.selectCustInfosByExample(example);
                for(CustInfo custinfo: custInfos) {
                    Integer deposit = custinfo.getDeposit();
                    Long id2 = custinfo.getId();
                    custInfo1.setDeposit(deposit + coachInfo.getSalePrice() * num);
                    custInfo1.setId(id2);
                    custInfoService.updateByPrimaryKeySelective(custInfo1);
                    OrderConsumeLog consum = new OrderConsumeLog();
                    consum.setOrderId(id);
                    consum.setDeposit(deposit);
                    consum.setCreateTime(new Date());
                    consum.setPayMoney(coachInfo.getSalePrice() * num);
                    consum.setPayType(0);
                    consum.setParentCustId(parentCustId);
                    consum.setIsOnline(1);
                    orderConsumeLogService.insertSelective(consum);
                }
            }
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

    // 查看是否为老用户
    @RequestMapping("/line/checkuser")
    public void lineCheckuser(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING)
            .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        BaseJson res = new BaseJson();
        try {
            String mobile = ParamUtils.getParameter(request, "mobile", "");

            MemberInfoExample example = new MemberInfoExample();
            MemberInfoExample.Criteria criteria = example.createCriteria();
            criteria.andLinkPhoneEqualTo(mobile);
            List<MemberInfo> memberInfos = memberService.selectMemberByExample(example);
            if(memberInfos != null && memberInfos.size() > 0) {
                res.setStatus(1);
                res.setData(memberInfos.get(0));
                res.setMsg("获取成功");
            } else {
                res.setStatus(0);
                res.setMsg("获取失败");
            }
            response.getWriter().print(gson.toJson(res));
        } catch(Exception e) {
            logger.error("cardAction is Error:" + e.getMessage(), e);
        }

    }

}
