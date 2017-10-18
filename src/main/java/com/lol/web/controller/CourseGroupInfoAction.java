package com.lol.web.controller;

import java.io.IOException;
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
import org.springframework.web.servlet.ModelAndView;

import com.lol.common.Constants;
import com.lol.common.bean.BaseJson;
import com.lol.common.bean.CurrentUserInfo;
import com.lol.common.bean.LogConstants;
import com.lol.common.bean.MsgBuilder;
import com.lol.common.bean.MsgType;
import com.lol.common.bean.OrderfinishBean;
import com.lol.common.model.ds1.CoachInfo;
import com.lol.common.model.ds1.CoachInfoExample;
import com.lol.common.model.ds1.CourseGroupInfo;
import com.lol.common.model.ds1.CourseGroupInfoExample;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;
import com.lol.common.model.ds1.MemberInfo;
import com.lol.common.model.ds1.MemberInfoExample;
import com.lol.common.model.ds1.OrderConsumeLog;
import com.lol.common.model.ds1.OrderFinishLog;
import com.lol.common.model.ds1.OrderFinishLogExample;
import com.lol.common.model.ds1.OrderInfo;
import com.lol.common.model.ds1.OrderInfoExample;
import com.lol.common.model.ds1.ProductInfo;
import com.lol.common.model.ds1.ProductInfoExample;
import com.lol.common.model.ds1.ProductInfoExample.Criteria;
import com.lol.common.model.ds1.UserLog;
import com.lol.common.model.ds1.UserLogExample;
import com.lol.common.model.ds1.UserOrderLog;
import com.lol.common.model.ds1.UserProductLog;
import com.lol.common.plugin.Page;
import com.lol.common.plugin.Pager;
import com.lol.common.plugin.PagerService;
import com.lol.common.service.CoachInfoService;
import com.lol.common.service.CourseGroupInfoService;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.MemberService;
import com.lol.common.service.OrderConsumeLogService;
import com.lol.common.service.OrderFinishLogService;
import com.lol.common.service.OrderInfoService;
import com.lol.common.service.ProductInfoService;
import com.lol.common.service.UserLogService;
import com.lol.common.service.UserLoginService;
import com.lol.common.service.UserOrderLogService;
import com.lol.common.service.UserProductLogService;
import com.lol.common.util.IntUtil;
import com.lol.common.util.ParamUtils;
import com.lol.common.util.SequenceUtils;
import com.lol.web.JSTLFuncs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

/**
 * 收费团课
 * 
 * @author qiuwei
 *
 */
@Controller
@RequestMapping("/course")
public class CourseGroupInfoAction {

    private static final Logger logger = LoggerFactory.getLogger(CourseGroupInfoAction.class);

    @Resource
    private ProductInfoService productInfoService;

    @Resource
    private CourseGroupInfoService courseGroupInfoService;

    @Resource
    private UserLogService userLogService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private OrderFinishLogService orderFinishLogService;

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private UserProductLogService userProductLogService;

    @Resource
    private UserOrderLogService userOrderLogService;

    @Resource
    private CoachInfoService coachInfoService;
    
    @Resource
    private MemberService memberService;
    @Resource
    private CustInfoService  custInfoService;
    @Resource
    private OrderConsumeLogService  orderConsumeLogService;  
    

    /**
     * 收费团课总店分店
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/group/list")
    public String list(HttpServletRequest request, Model model) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        Integer parentType = currentUserInfo.getCustInfo().getParentType();
        if(parentType == 2) {
            return status_bmz(request, model);
        }
        return index(request, model);
    }

    /**
     * 收费团课列表
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/group")
    public String index(HttpServletRequest request, Model model) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        int parentCustId = currentUserInfo.getCustInfo().getParentId();

        int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);

        CourseGroupInfoExample example = new CourseGroupInfoExample();
        CourseGroupInfoExample.Criteria criteria = example.createCriteria();
        criteria.andParentCustIdEqualTo(parentCustId);

        int pageCount = courseGroupInfoService.countByExample(example);
        int pageSize = 8;
        int offSet = 3;
        Page pages = new Page();
        pages.setTotal(pageCount);
        pages.setLimit(pageSize);
        pages.setNo(currentPage);

        example.setPage(pages);
        Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

        List<CourseGroupInfo> beanList = courseGroupInfoService.selectByExample(example);

        model.addAttribute("pager", page);
        model.addAttribute("beanList", beanList);

        return "models/hq/course/group/index";
    }

    /**
     * 跳转详情页
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/group/toDetail")
    public ModelAndView toDetail(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        try {
            int id = ParamUtils.getIntParameter(request, "id");
            CourseGroupInfo courseGroupInfo = courseGroupInfoService.selectByPrimaryKey(id);
            // 查询日志
            UserLogExample example = new UserLogExample();
            UserLogExample.Criteria criteria = example.createCriteria();
            criteria.andLogTypeEqualTo(3);
            criteria.andObjectIdEqualTo((long)id);
            example.setOrderByClause("id desc");
            List<UserLog> userLogList = userLogService.selectByExample(example);

            model.put("userLogList", userLogList);
            model.put("courseGroupInfo", courseGroupInfo);
        } catch(Exception e) {
            logger.error("CourseGroupInfoAction.toDetail Error:" + e.getMessage(), e);
        }
        return new ModelAndView("models/hq/course/group/detail", "model", model);
    }

    /**
     * 删除
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/group/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        String memberName = currentUserInfo.getUserLogin().getRealname();
        int parentCustId = currentUserInfo.getCustInfo().getParentId();
        int sysCustId = currentUserInfo.getCustInfo().getSystemId();

        Gson gson = new GsonBuilder().create();
        BaseJson baseJson = new BaseJson();
        try {
            int id = ParamUtils.getIntParameter(request, "id");

            // 操作人操作时间
            UserLog log = new UserLog();
            log.setLogType(LogConstants.LOG_TYPE_COURESGRPUP);
            log.setCreateTime(new Date());
            log.setLogAction(LogConstants.LOG_ACTION_DELETE);
            log.setLogMemo(LogConstants.LOG_MEMO_GROUP_DELECT);
            log.setObjectId((long)id);
            log.setSysCustId(sysCustId);
            log.setParentCustId(parentCustId);
            log.setUserId(memberName);

            courseGroupInfoService.deleteByPrimaryKey(id);
            userLogService.insertSelective(log);

            baseJson.setStatus(0);
            baseJson.setMsg("操作成功");
        } catch(Exception e) {
            logger.error("CourseGroupInfoAction.delete Error:" + e.getMessage(), e);
            baseJson.setStatus(1);
            baseJson.setMsg("操作失败");
        }
        try {
            response.getWriter().print(gson.toJson(baseJson));
        } catch(IOException e) {
            logger.error("CourseGroupInfoAction.delete Error:" + e.getMessage(), e);
        }
    }

    /**
     * 更新修改
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/group/update")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        String memberName = currentUserInfo.getUserLogin().getRealname();
        int parentCustId = currentUserInfo.getCustInfo().getParentId();
        int sysCustId = currentUserInfo.getCustInfo().getSystemId();

        Gson gson = new GsonBuilder().create();
        BaseJson baseJson = new BaseJson();
        try {
            int id = ParamUtils.getIntParameter(request, "id");
            String courseName = ParamUtils.getParameter(request, "courseName");
            int peopleNum = ParamUtils.getIntParameter(request, "peopleNum");
            String salePrice = ParamUtils.getParameter(request, "salePrice", "0");
            int classCount = ParamUtils.getIntParameter(request, "classCount");
            String fee = ParamUtils.getParameter(request, "fee", "0");
            String memo = ParamUtils.getParameter(request, "memo");
            String signImg = ParamUtils.getParameter(request, "signImg");
            int isRefund = ParamUtils.getIntParameter(request, "isRefund");

            CourseGroupInfo courseGroupInfo = new CourseGroupInfo();
            courseGroupInfo.setId(id);
            courseGroupInfo.setCourseName(courseName);
            courseGroupInfo.setPeopleNum(peopleNum);
            courseGroupInfo.setSalePrice(IntUtil.mul(salePrice, "100"));
            courseGroupInfo.setClassCount(classCount);
            courseGroupInfo.setFee(IntUtil.mul(fee, "100"));
            courseGroupInfo.setProDesc(memo);
            courseGroupInfo.setSignImg(signImg);
            courseGroupInfo.setIsRefund(isRefund);

            // 操作人操作时间
            UserLog log = new UserLog();
            log.setLogType(LogConstants.LOG_TYPE_COURESGRPUP);
            log.setCreateTime(new Date());
            log.setLogAction(LogConstants.LOG_ACTION_MODIFY);
            log.setLogMemo(LogConstants.LOG_MEMO_GROUP_UPDATE);
            log.setObjectId((long)id);
            log.setSysCustId(sysCustId);
            log.setParentCustId(parentCustId);
            log.setUserId(memberName);

            courseGroupInfoService.updateByPrimaryKeySelective(courseGroupInfo);
            userLogService.insertSelective(log);

            baseJson.setStatus(0);
        } catch(Exception e) {
            logger.error("CourseGroupInfoAction.update Error:" + e.getMessage(), e);
            baseJson.setStatus(1);
            baseJson.setMsg("操作失败");
        }
        try {
            response.getWriter().print(gson.toJson(baseJson));
        } catch(IOException e) {
            logger.error("CourseGroupInfoAction.update Error:" + e.getMessage(), e);
        }
    }

    /**
     * 添加团课
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/group/add")
    public void add(HttpServletRequest request, HttpServletResponse response) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        int parentCustId = currentUserInfo.getCustInfo().getParentId();
        int sysCustId = currentUserInfo.getCustInfo().getSystemId();
        String memberName = currentUserInfo.getUserLogin().getRealname();

        Gson gson = new GsonBuilder().create();
        BaseJson baseJson = new BaseJson();
        try {
            String courseName = ParamUtils.getParameter(request, "courseName");
            int peopleNum = ParamUtils.getIntParameter(request, "peopleNum");
            String salePrice = ParamUtils.getParameter(request, "salePrice", "0");
            int classCount = ParamUtils.getIntParameter(request, "classCount");
            String fee = ParamUtils.getParameter(request, "fee", "0");
            String memo = ParamUtils.getParameter(request, "elm1");
            String signImg = ParamUtils.getParameter(request, "signImg");
            int isRefund = ParamUtils.getIntParameter(request, "isRefund");

            CourseGroupInfo courseGroupInfo = new CourseGroupInfo();
            courseGroupInfo.setCourseName(courseName);
            courseGroupInfo.setPeopleNum(peopleNum);
            courseGroupInfo.setSalePrice(IntUtil.mul(salePrice, "100"));
            courseGroupInfo.setClassCount(classCount);
            if(isRefund > 0) {
                courseGroupInfo.setFee(IntUtil.mul(fee, "100"));
            }
            courseGroupInfo.setProDesc(memo);
            courseGroupInfo.setSignImg(signImg);
            courseGroupInfo.setIsRefund(isRefund);
            courseGroupInfo.setParentCustId(parentCustId);

            // 操作人需要获取
            UserLog log = new UserLog();
            log.setLogType(LogConstants.LOG_TYPE_COURESGRPUP);
            log.setCreateTime(new Date());
            log.setLogAction(LogConstants.LOG_ACTION_ADD);
            log.setLogMemo(LogConstants.LOG_MEMO_GROUP_ADD);
            log.setUserId(memberName);
            log.setSysCustId(sysCustId);
            log.setParentCustId(parentCustId);

            courseGroupInfoService.insertSelective(courseGroupInfo);

            log.setObjectId((long)courseGroupInfo.getId());
            userLogService.insertSelective(log);
            baseJson.setStatus(0);
        } catch(Exception e) {
            logger.error("CourseGroupInfoAction.add Error:" + e.getMessage(), e);
            baseJson.setMsg("操作失败");
        }
        try {
            response.getWriter().print(gson.toJson(baseJson));
        } catch(IOException e) {
            logger.error("CourseGroupInfoAction.add Error:" + e.getMessage(), e);
        }
    }

    /**
     * 发布团课
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/group/publish")
    public void publish(HttpServletRequest request, HttpServletResponse response) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        int parentCustId = currentUserInfo.getCustInfo().getParentId();
        String memberName = currentUserInfo.getUserLogin().getRealname();

        Gson gson = new GsonBuilder().create();
        BaseJson baseJson = new BaseJson();
        try {
            String cardName = ParamUtils.getParameter(request, "cardName");
            int peopleNum = ParamUtils.getIntParameter(request, "peopleNum");
            String salePrice = ParamUtils.getParameter(request, "salePrice", "0");
            int classCount = ParamUtils.getIntParameter(request, "classCount");
            String fee = ParamUtils.getParameter(request, "fee", "0");
            String memo = ParamUtils.getParameter(request, "elm1");
            String signImg = ParamUtils.getParameter(request, "signImg");
            int isRefund = ParamUtils.getIntParameter(request, "isRefund");
            int sysCustId = ParamUtils.getIntParameter(request, "sysCustId");
            String coachId = ParamUtils.getParameter(request, "coachId");
            Date startTime = ParamUtils.getDateParameter(request, "control_date");
            Date endTime = ParamUtils.getDateParameter(request, "control_date2");

            ProductInfo productInfo = new ProductInfo();
            // 生成主键id
            long id = SequenceUtils.nextId();
            productInfo.setId(id);
            productInfo.setCardName(cardName);
            productInfo.setPeopleNum(peopleNum);
            productInfo.setSalePrice(IntUtil.mul(salePrice, "100"));
            productInfo.setClassCount(classCount);
            productInfo.setProDesc(memo);
            productInfo.setSignImg(signImg);
            productInfo.setIsRefund(isRefund);
            productInfo.setCreateTime(new Date());
            if(isRefund > 0) {
                productInfo.setFee(IntUtil.mul(fee, "100"));
                int mul = IntUtil.mul(fee, salePrice);
                productInfo.setRefundPrice(IntUtil.mul(mul + "", "100"));
            } else {
                productInfo.setRefundPrice(0);
            }
            productInfo.setCoachId(coachId);
            productInfo.setSysCustId(sysCustId);
            productInfo.setStartTime(startTime);
            productInfo.setEndTime(endTime);
            productInfo.setCourseStatus(LogConstants.PRODUCT_COURSE_STATUS_B);
            productInfo.setParentCustId(parentCustId);
            productInfo.setProType(LogConstants.PRODUCT_PRO_TYPE_GROUP);

            UserProductLog log = new UserProductLog();
            log.setLogType(LogConstants.LOG_TYPE_PRODUCT);
            log.setCreateTime(new Date());
            log.setLogAction(LogConstants.LOG_ACTION_ADD);
            log.setLogMemo(LogConstants.LOG_MEMO_PRODUCT_GROUP_ADD);
            log.setUserId(memberName);
            log.setSysCustId(sysCustId);
            log.setParentCustId(parentCustId);

            productInfoService.insertSelective(productInfo);
            log.setObjectId(productInfo.getId());
            userProductLogService.insertSelective(log);
            baseJson.setStatus(0);
        } catch(Exception e) {
            logger.error("CourseGroupInfoAction.publish Error:" + e.getMessage(), e);
            baseJson.setStatus(1);
            baseJson.setMsg("操作失败");
        }
        try {
            response.getWriter().print(gson.toJson(baseJson));
        } catch(IOException e) {
            logger.error("CourseGroupInfoAction.publish Error:" + e.getMessage(), e);
        }
    }

    /**
     * 团课状态--报名中
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/group/status_bmz")
    public String status_bmz(HttpServletRequest request, Model model) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        int custId = currentUserInfo.getCustInfo().getSystemId();
        int parentCustId = currentUserInfo.getCustInfo().getParentId();
        int parentType = currentUserInfo.getCustInfo().getParentType();

        int courseStatus = 0;
        int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
        ProductInfoExample example = new ProductInfoExample();
        Criteria criteria = example.createCriteria();
        criteria.andStartTimeGreaterThan(new Date());
        // criteria.andCourseStatusEqualTo(courseStatus);
        criteria.andProTypeEqualTo(11);
        if(parentType == 2) {// 分店
            criteria.andSysCustIdEqualTo(custId);
            int pageCount = productInfoService.countByExample(example);
            int pageSize = 8;
            int offSet = 3;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);

            example.setPage(pages);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

            /*
             * String sql = "SELECT c.`cust_name`,p.`id`,p.`start_time`,p.`sign_img`,p.`sys_cust_id`,p.`card_name`"+
             * " FROM cust_info c,product_info p WHERE  c.system_id = p.sys_cust_id AND p.`course_status` = 0 AND p.`pro_type` = 11 AND p.sys_cust_id = ? "
             * ;
             */

            /* List<Map<String, Object>> beanList = courseGroupInfoService.getList(sql,new Object[]{custId}); */
            List<ProductInfo> beanList = productInfoService.selectByExample(example);
            model.addAttribute("beanList", beanList);
            model.addAttribute("pager", page);

            return "models/branch/course/group/status_bmz";
        } else {
            criteria.andParentCustIdEqualTo(parentCustId);
            int pageCount = productInfoService.countByExample(example);
            int pageSize = 8;
            int offSet = 3;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);

            example.setPage(pages);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

            /*
             * String sql = "SELECT c.`cust_name`,p.`id`,p.`start_time`,p.`sign_img`,p.`sys_cust_id`,p.`card_name`"+
             * " FROM cust_info c,product_info p WHERE  c.system_id = p.sys_cust_id AND p.`course_status` = 0 AND p.`pro_type` = 11"
             * ;
             */

            /* List<Map<String, Object>> beanList = courseGroupInfoService.getList(sql,new Object[]{}); */
            List<ProductInfo> beanList = productInfoService.selectByExample(example);
            model.addAttribute("beanList", beanList);
            model.addAttribute("pager", page);

            return "models/hq/course/group/status_bmz";
        }
    }

    /**
     * 团课状态--进行中--已完成
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/group/status")
    public String status(HttpServletRequest request, Model model) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        int custId = currentUserInfo.getCustInfo().getSystemId();
        int parentCustId = currentUserInfo.getCustInfo().getParentId();
        int parentType = currentUserInfo.getCustInfo().getParentType();

        int courseStatus = ParamUtils.getIntParameter(request, "courseStatus");
        int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);

        ProductInfoExample example = new ProductInfoExample();
        Criteria criteria = example.createCriteria();
        if(courseStatus == 1) {
            criteria.andStartTimeLessThanOrEqualTo(new Date());
            criteria.andEndTimeGreaterThanOrEqualTo(new Date());
        } else {
            criteria.andEndTimeLessThan(new Date());
        }
        // criteria.andCourseStatusEqualTo(courseStatus);
        criteria.andProTypeEqualTo(11);
        if(parentType == 2) {// 分店
            criteria.andSysCustIdEqualTo(custId);
            int pageCount = productInfoService.countByExample(example);
            int pageSize = 8;
            int offSet = 3;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);

            example.setPage(pages);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

            /*
             * String sql = "SELECT c.`cust_name`,p.`id`,p.`start_time`,p.`sign_img`,p.`sys_cust_id`,p.`card_name`"+
             * " FROM cust_info c,product_info p WHERE  c.system_id = p.sys_cust_id AND p.`course_status` = ? AND p.`pro_type` = 11 AND p.sys_cust_id = ? "
             * ;
             */

            /* List<Map<String, Object>> beanList = courseGroupInfoService.getList(sql,new Object[]{courseStatus,custId}); */
            List<ProductInfo> beanList = productInfoService.selectByExample(example);

            model.addAttribute("pager", page);
            model.addAttribute("courseStatus", courseStatus);
            model.addAttribute("beanList", beanList);

            return "models/branch/course/group/status";
        } else {
            criteria.andParentCustIdEqualTo(parentCustId);
            int pageCount = productInfoService.countByExample(example);
            int pageSize = 8;
            int offSet = 3;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);

            example.setPage(pages);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

            /*
             * String sql = "SELECT c.`cust_name`,p.`id`,p.`start_time`,p.`sign_img`,p.`sys_cust_id`,p.`card_name`"+
             * " FROM cust_info c,product_info p WHERE  c.system_id = p.sys_cust_id AND p.`course_status` = ? AND p.`pro_type` = 11"
             * ;
             */

            /* List<Map<String, Object>> beanList = courseGroupInfoService.getList(sql,new Object[]{courseStatus}); */
            List<ProductInfo> beanList = productInfoService.selectByExample(example);

            model.addAttribute("pager", page);
            model.addAttribute("courseStatus", courseStatus);
            model.addAttribute("beanList", beanList);

            return "models/hq/course/group/status";
        }
    }

    /**
     * 跳转产品详情页--报名中
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/group/toProDetail_bmz")
    public String toProDetail_bmz(HttpServletRequest request, Model model) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        int parentType = currentUserInfo.getCustInfo().getParentType();
        try {
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
            long id = ParamUtils.getLongParameter(request, "id");

            // 报名人信息
            OrderInfoExample example = new OrderInfoExample();
            OrderInfoExample.Criteria criteria = example.createCriteria();
            criteria.andProductIdEqualTo(id);
            criteria.andOrderTypeEqualTo(LogConstants.PRODUCT_PRO_TYPE_GROUP);
            example.setOrderByClause(" pay_time desc");
            int pageCount = orderInfoService.countByExample(example);
            int pageSize = 10;
            int offSet = 3;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);

            example.setPage(pages);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

            List<OrderInfo> orderList = orderInfoService.selectByExample(example);
            
            ProductInfo productInfo = productInfoService.selectByPrimaryKey(id);
            List<String> coachList = null;
            if(productInfo != null) {
                String coachIds = productInfo.getCoachId();
                coachList = new ArrayList<String>();
                String[] split = coachIds.split(",");
                for(int i = 0; i < split.length; i++) {
                    coachList.add(split[i]);
                }
            }

           /* String sql =
                "SELECT o.`id`,o.`link_man`,o.`link_phone`,o.`pay_time`,o.`mem_price`,o.`refund_money`,o.`status`,p.`card_name`,m.`member_name`,m.`member_logo` "
                    + " FROM order_info o,product_info p,member_info m WHERE o.`product_id` = p.`id` AND o.`order_type` = ?  AND o.`member_id` = m.`member_id` AND o.`product_id` = ? AND o.`status` = ?";
            List<Map<String, Object>> orderList =
                orderInfoService.getList(sql, new Object[]{LogConstants.PRODUCT_PRO_TYPE_GROUP, id, 2});*/
            model.addAttribute("coachList", coachList);
            model.addAttribute("pager", page);
            model.addAttribute("orderList", orderList);
            model.addAttribute("productInfo", productInfo);
        } catch(Exception e) {
            logger.error("CourseGroupInfoAction.toProDetail Error:" + e.getMessage(), e);
        }
        if(parentType == 2) {// 分店
            return "models/branch/course/group/prodetail_bmz";
        } else {
            return "models/hq/course/group/prodetail_bmz";
        }
    }

    /**
     * 跳转产品详情页--进行中--已完成
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/group/toProDetail")
    public String toProDetail(HttpServletRequest request, Model model) {
        try {
            long id = ParamUtils.getLongParameter(request, "id");
            ProductInfo productInfo = productInfoService.selectByPrimaryKey(id);
            List<String> coachList = null;
            if(productInfo != null) {
                String coachIds = productInfo.getCoachId();
                coachList = new ArrayList<String>();
                String[] split = coachIds.split(",");
                for(int i = 0; i < split.length; i++) {
                    coachList.add(split[i]);
                }
            }

            String sql = "SELECT a.`id`,a.`link_man`,a.`link_phone`,a.`pay_time`,b.`finish_time` "
                + "FROM order_info a LEFT JOIN order_finish_log b ON a.`id` = b.`order_id` "
                + "WHERE a.`product_id` =?  AND a.`status` = ? ORDER BY b.`finish_time` ASC";
            List<Map<String, Object>> list = orderInfoService.getList(sql, new Object[]{id, 2});
            OrderfinishBean order = null;
            Map<OrderfinishBean, List<Date>> map1 = new HashMap<OrderfinishBean, List<Date>>();
            List<Date> timeList = null;
            for(Map<String, Object> map: list) {
                order = new OrderfinishBean();
                order.setId((Long)map.get("id"));
                order.setLinkMan((String)map.get("link_man"));
                order.setLinkPhone((String)map.get("link_phone"));
                order.setPayTime((Date)map.get("pay_time"));
                if(!map1.containsKey(order)) {
                    timeList = new ArrayList<Date>();
                }
                timeList.add((Date)map.get("finish_time"));
                map1.put(order, timeList);
            }

            // 签到信息
            // Date startTime = productInfo.getStartTime();
            // Date endTime = productInfo.getEndTime();
            Long pId = productInfo.getId();
            OrderFinishLogExample example2 = new OrderFinishLogExample();
            OrderFinishLogExample.Criteria criteria2 = example2.createCriteria();
            // criteria2.andFinishTimeBetween(startTime, endTime);
            criteria2.andInfoIdEqualTo(pId);
            List<OrderFinishLog> dateList = orderFinishLogService.selectByExample(example2);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            Map<String, List<OrderFinishLog>> map = new HashMap<String, List<OrderFinishLog>>();
            List<OrderFinishLog> oList = null;
            for(OrderFinishLog orderFinishLog: dateList) {
                Date finishTime = orderFinishLog.getFinishTime();
                String format = sdf.format(finishTime);
                oList = new ArrayList<OrderFinishLog>();
                if(!map.containsKey(format)) {
                    oList.add(orderFinishLog);
                    map.put(format, oList);
                }
            }

            /*
             * SELECT finish_time FROM order_finish_log WHERE finish_time BETWEEN '2016-07-05 11:39:48' AND '2016-07-20 11:39:49'
             * ORDER BY finish_time DESC"; List<Map<String, Object>> signList = courseGroupInfoService.getList(sql,new
             * Object[]{startTime,endTime});
             */
            model.addAttribute("map1", map1);
            model.addAttribute("map", map);
            model.addAttribute("dateList", dateList);
            // model.addAttribute("userList", userList);
            model.addAttribute("productInfo", productInfo);
            model.addAttribute("coachList", coachList);
        } catch(Exception e) {
            logger.error("CourseGroupInfoAction.toProDetail Error:" + e.getMessage(), e);
        }
        return "models/hq/course/group/prodetail";
    }

    /**
     * 
     * 教练选择
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/group/coachList")
    public void sportList(HttpServletRequest request, HttpServletResponse response) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        int parentCustId = currentUserInfo.getCustInfo().getParentId();
        Map<String, Object> map = new HashMap<String, Object>();
        Gson gson = new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING)
            .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        try {
            CoachInfoExample example = new CoachInfoExample();
            com.lol.common.model.ds1.CoachInfoExample.Criteria createCriteria = example.createCriteria();
            createCriteria.andParentCustIdEqualTo(parentCustId);
            createCriteria.andStatusEqualTo(0);
            List<CoachInfo> coachList = coachInfoService.selectByExample(example);
            /*
             * String sql = "SELECT c.`name`,c.`id` FROM coach_info c WHERE c.`parent_cust_id` = ?"; List<Map<String, Object>>
             * coachList = courseGroupInfoService.getList(sql,new Object[]{parentCustId});
             */
            map.put("coachList", coachList);
            response.getWriter().print(gson.toJson(map));
        } catch(Exception e) {
            logger.error("courseGroupInfoServiceAction.coachList Error:" + e.getMessage(), e);
        }
    }

    /**
     * 
     * 分店选择
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/group/subbranchList")
    public void subbranchList(HttpServletRequest request, HttpServletResponse response) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        int parentCustId = currentUserInfo.getCustInfo().getParentId();

        Map<String, Object> map = new HashMap<String, Object>();
        Gson gson = new GsonBuilder().create();
        try {
            String sql =
                "SELECT c.`cust_name`,c.`id`,c.`system_id` FROM cust_info c WHERE c.`parent_id` = ? AND c.`parent_type` = ?";
            List<Map<String, Object>> subbranchList = courseGroupInfoService.getList(sql, new Object[]{parentCustId, 2});
            map.put("subbranchList", subbranchList);
            response.getWriter().print(gson.toJson(map));
        } catch(Exception e) {
            logger.error("courseGroupInfoServiceAction.subbranchList Error:" + e.getMessage(), e);
        }
    }

    /**
     * 产品更新修改
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/group/updateDetail")
    public void updateDetail(HttpServletRequest request, HttpServletResponse response) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        int parentCustId = currentUserInfo.getCustInfo().getParentId();
        String memberName = currentUserInfo.getUserLogin().getRealname();

        Gson gson = new GsonBuilder().create();
        BaseJson baseJson = new BaseJson();
        try {
            long id = ParamUtils.getLongParameter(request, "id");
            String cardName = ParamUtils.getParameter(request, "cardName");
            int peopleNum = ParamUtils.getIntParameter(request, "peopleNum");
            String salePrice = ParamUtils.getParameter(request, "salePrice", "0");
            int classCount = ParamUtils.getIntParameter(request, "classCount");
            String fee = ParamUtils.getParameter(request, "fee", "0");
            String memo = ParamUtils.getParameter(request, "memo");
            String signImg = ParamUtils.getParameter(request, "signImg");
            int isRefund = ParamUtils.getIntParameter(request, "isRefund");
            int sysCustId = ParamUtils.getIntParameter(request, "sysCustId");
            String coachId = ParamUtils.getParameter(request, "coachId");
            Date startTime = ParamUtils.getDateParameter(request, "control_date");
            Date endTime = ParamUtils.getDateParameter(request, "control_date2");
            String refundPrice = ParamUtils.getParameter(request, "refundPrice", "0");
            int sort = ParamUtils.getIntParameter(request, "sort",0);

            ProductInfo productInfo = new ProductInfo();

            productInfo.setId(id);
            productInfo.setCardName(cardName);
            productInfo.setPeopleNum(peopleNum);
            productInfo.setSalePrice(IntUtil.mul(salePrice, "100"));
            productInfo.setClassCount(classCount);
            productInfo.setProDesc(memo);
            productInfo.setSignImg(signImg);
            productInfo.setIsRefund(isRefund);
            if(isRefund > 0) {
                productInfo.setFee(IntUtil.mul(fee, "100"));
            }
            productInfo.setRefundPrice(IntUtil.mul(refundPrice, "100"));
            productInfo.setCoachId(coachId);
            productInfo.setSysCustId(sysCustId);
            productInfo.setStartTime(startTime);
            productInfo.setEndTime(endTime);
            productInfo.setCourseStatus(0);
            productInfo.setSort(sort);

            UserProductLog log = new UserProductLog();
            log.setLogType(LogConstants.LOG_TYPE_PRODUCT);
            log.setCreateTime(new Date());
            log.setLogAction(LogConstants.LOG_ACTION_MODIFY);
            log.setLogMemo(LogConstants.LOG_MEMO_PRODUCT_GROUP_MODIFY);
            log.setUserId(memberName);
            log.setSysCustId(sysCustId);
            log.setParentCustId(parentCustId);

            productInfoService.updateByPrimaryKeySelective(productInfo);
            userProductLogService.insertSelective(log);

            baseJson.setStatus(0);
        } catch(Exception e) {
            logger.error("CourseGroupInfoAction.updateDetail Error:" + e.getMessage(), e);
            baseJson.setMsg("操作失败");
        }
        try {
            response.getWriter().print(gson.toJson(baseJson));
        } catch(IOException e) {
            logger.error("CourseGroupInfoAction.updateDetail Error:" + e.getMessage(), e);
        }
    }

    /**
     * 退款修改
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/group/refund")
    public void refund(HttpServletRequest request, HttpServletResponse response) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        String memberName = currentUserInfo.getUserLogin().getRealname();
        int parentCustId = currentUserInfo.getCustInfo().getParentId();
        int sysCustId = currentUserInfo.getCustInfo().getSystemId();

        Gson gson = new GsonBuilder().create();
        BaseJson baseJson = new BaseJson();
        try {
            long id = ParamUtils.getLongParameter(request, "id");
            int actualMoney = ParamUtils.getIntParameter(request, "actualMoney");

            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setId(id);
            orderInfo.setActualMoney(actualMoney);
            orderInfo.setStatus(6);

            UserOrderLog log = new UserOrderLog();
            log.setCreateTime(new Date());
            log.setLogAction(LogConstants.LOG_ACTION_REFUND);// 退款
            log.setLogMemo(LogConstants.LOG_MEMO_ORDER_GROUP_MODIFY);
            log.setLogType(LogConstants.LOG_TYPE_PRODUCT);
            log.setObjectId(id);
            log.setParentCustId(parentCustId);
            log.setSysCustId(sysCustId);
            log.setUserId(memberName);

            orderInfoService.updateByPrimaryKeySelective(orderInfo);
            baseJson.setStatus(0);
        } catch(Exception e) {
            logger.error("CourseGroupInfoAction.refund Error:" + e.getMessage(), e);
            baseJson.setMsg("操作失败");
        }
        try {
            response.getWriter().print(gson.toJson(baseJson));
        } catch(IOException e) {
            logger.error("CourseGroupInfoAction.refund Error:" + e.getMessage(), e);
        }
    }

    /**
     * 
     * 签到次数
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/group/signList")
    public void signList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        Gson gson = new GsonBuilder().create();
        try {
            String orderId = ParamUtils.getParameter(request, "orderId");

            String sql = "SELECT finish_time FROM order_finish_log WHERE order_id = ? ORDER BY finish_time ASC";
            List<Map<String, Object>> signList = courseGroupInfoService.getList(sql, new Object[]{orderId});
            map.put("signList", signList);
            response.getWriter().print(gson.toJson(map));
        } catch(Exception e) {
            logger.error("courseGroupInfoServiceAction.signList Error:" + e.getMessage(), e);
        }
    }

    /**
     * 
     * 签到人员(不用了)
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/group/signUserList")
    public void signUserList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        Gson gson = new GsonBuilder().create();
        try {
            Date date = ParamUtils.getDateParameter(request, "date");
            long pId = ParamUtils.getLongParameter(request, "pId");

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date date1 = new Date(calendar.getTimeInMillis());

            /*
             * OrderFinishLogExample example = new OrderFinishLogExample(); OrderFinishLogExample.Criteria criteria =
             * example.createCriteria(); criteria.andInfoIdEqualTo(pId); criteria.andFinishTimeBetween(date, date1);
             * List<OrderFinishLog> signUserList = orderFinishLogService.selectByExample(example);
             */

            String sql = "SELECT o.`finish_time`,m.`member_name`,m.`member_logo` FROM order_finish_log o,member_info m "
                + " WHERE  o.`member_id` = m.`member_id`  AND  o.`info_id` = ?  AND ( o.`finish_time` BETWEEN STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s') AND STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s') )"
                + " ORDER BY finish_time DESC";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<Map<String, Object>> signUserList =
                courseGroupInfoService.getList(sql, new Object[]{pId, format.format(date), format.format(date1)});
            map.put("signUserList", signUserList);
            response.getWriter().print(gson.toJson(map));
        } catch(Exception e) {
            logger.error("courseGroupInfoServiceAction.signList Error:" + e.getMessage(), e);
        }
    }

 // 查看是否为老用户
    @RequestMapping("/line/checkuser")
    public void lineCheckuser(HttpServletRequest request, HttpServletResponse response) {
    	Gson gson = new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING)
				.setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();
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
            }else{
                res.setStatus(0);
                res.setMsg("获取失败");
            }
            response.getWriter().print(gson.toJson(res));
        } catch(Exception e) {
            logger.error("cardAction is Error:" + e.getMessage(), e);
        }

    }
    //线下购买团课
    @RequestMapping("/group/getOrder")
    public void getOrder(HttpServletRequest request, HttpServletResponse response) {
        Gson gson =
            new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        BaseJson baseJson = new BaseJson();
        
		        try {
		        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
				CustInfo custInfo = currentUserInfo.getCustInfo();
				int parentCustId = custInfo.getParentId();
		        int sysCustId = custInfo.getSystemId(); 
            	String memberId = ParamUtils.getParameter(request, "memberId");
            	Long id = ParamUtils.getLongParameter(request, "id");
            	String  mobile=ParamUtils.getParameter(request, "mobile");
                int payType = ParamUtils.getIntParameter(request, "payType", 0);
                ProductInfo productInfo = productInfoService.selectByPrimaryKey(id);
                MemberInfo memberInfo = memberService.selectByPrimaryKey(Long.parseLong(memberId));
                // 生成订单id
                long orderId = SequenceUtils.nextId();

                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId(orderId);
                orderInfo.setOrderCode(SequenceUtils.getCode(orderId)+"");;
                orderInfo.setOrderType(LogConstants.PRODUCT_PRO_TYPE_GROUP);
                orderInfo.setProductId(id);
                orderInfo.setCreteTime(new Date());
                orderInfo.setMemPrice(productInfo.getSalePrice());
                orderInfo.setRefundMoney(0);
                orderInfo.setLinkMan(memberInfo.getMemberName());
                orderInfo.setLinkPhone(mobile);
                orderInfo.setMemberId(Long.parseLong(memberId));
                orderInfo.setStatus(2);
                orderInfo.setPayType(payType);
                orderInfo.setPayTime(new  Date());
                orderInfo.setParentCustId(parentCustId);
                orderInfo.setSysCustId(sysCustId);
                orderInfo.setSalePrice(productInfo.getSalePrice());
                orderInfo.setStartTime(productInfo.getStartTime());
                orderInfo.setEndTime(productInfo.getEndTime());
                orderInfo.setOrderCount(productInfo.getClassCount());
                orderInfo.setRemainCount(productInfo.getClassCount());
                orderInfo.setNum(1);
                
                SimpleDateFormat sft = new SimpleDateFormat("yyyy年MM月dd日 ");
                MsgBuilder msgBuilder = new MsgBuilder();
                msgBuilder.setType(MsgType.COURSEGROUP);
                orderInfo.setSendContent(msgBuilder.create(new Object[]{
                    JSTLFuncs.getCustNameByCustId(productInfo.getSysCustId()+""), 
                    productInfo.getCardName(),
                    sft.format(productInfo.getStartTime()),
                    sft.format(productInfo.getEndTime()),
                    orderInfo.getOrderCode(),
                    Constants.SMS_SPORT_PHONE}));
                orderInfoService.insertSelective(orderInfo);
                baseJson.setStatus(0);
                baseJson.setMsg("操作成功");
                baseJson.setData(orderId);
                if(baseJson.getStatus()==0){
    				CustInfo custInfo1=new  CustInfo();
    				CustInfoExample  example=new CustInfoExample();
    				CustInfoExample.Criteria criteria=example.createCriteria();
    				criteria.andSystemIdEqualTo(parentCustId);
    				List<CustInfo> custInfos = custInfoService.selectCustInfosByExample(example);
    				for(CustInfo   custinfo:custInfos  ){
    					Integer deposit = custinfo.getDeposit();
    					Long id2 = custinfo.getId();
    					custInfo1.setDeposit(deposit+productInfo.getSalePrice());
    					custInfo1.setId(id2);
    					custInfoService.updateByPrimaryKeySelective(custInfo1);
    					OrderConsumeLog   consum=new OrderConsumeLog();
    					consum.setOrderId(id);
    					consum.setDeposit(deposit);
    					consum.setCreateTime(new Date());
    					consum.setPayMoney(productInfo.getSalePrice());
    					consum.setPayType(0);
    					consum.setParentCustId(parentCustId);
    					consum.setIsOnline(1);
    					orderConsumeLogService.insertSelective(consum);
    				}
    			}
        } catch(Exception e) {
            logger.error("CourseGroupInfoAction.getOrder Error:" + e.getMessage(), e);
            baseJson.setStatus(1);
            baseJson.setMsg("操作失败");
        }
        try {
            response.getWriter().print(gson.toJson(baseJson));
        } catch(IOException e) {
            logger.error("CourseGroupInfoAction.getOrder Error:" + e.getMessage(), e);
        }

    }
    
}
