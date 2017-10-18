
package com.lol.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lol.common.bean.BaseJson;
import com.lol.common.model.ds1.CoachInfo;
import com.lol.common.model.ds1.CommentInfoExample;
import com.lol.common.model.ds1.CommentInfoWithBLOBs;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;
import com.lol.common.model.ds1.MemberCard;
import com.lol.common.model.ds1.MemberCardExample;
import com.lol.common.model.ds1.MemberInfo;
import com.lol.common.model.ds1.MemberPauseInfo;
import com.lol.common.model.ds1.MemberPauseInfoExample;
import com.lol.common.model.ds1.OpenLog;
import com.lol.common.model.ds1.OpenLogExample;
import com.lol.common.model.ds1.OrderFinishLog;
import com.lol.common.model.ds1.OrderFinishLogExample;
import com.lol.common.model.ds1.OrderInfo;
import com.lol.common.model.ds1.OrderInfoExample;
import com.lol.common.model.ds1.ProductInfo;
import com.lol.common.model.ds1.ProductInfoExample;
import com.lol.common.model.ds1.UserLogin;
import com.lol.common.plugin.Page;
import com.lol.common.plugin.Pager;
import com.lol.common.plugin.PagerService;
import com.lol.common.service.CoachInfoService;
import com.lol.common.service.CommentService;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.MemberPauseInfoService;
import com.lol.common.service.MemberService;
import com.lol.common.service.OpenLogService;
import com.lol.common.service.OrderFinishLogService;
import com.lol.common.service.OrderInfoService;
import com.lol.common.service.ProductInfoService;
import com.lol.common.service.UserLoginService;
import com.lol.common.util.ParamUtils;
import com.lol.common.util.SMSUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 会员管理
 * 
 * @author bowen
 *
 */
@Controller
@RequestMapping("/member")
public class MemberAction {

    @Resource
    private CustInfoService custInfoService;

    @Resource
    private MemberService memberService;

    @Resource
    private ProductInfoService productInfoService;

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private CommentService commentService;

    @Resource
    private CoachInfoService coachInfoService;

    @Resource
    private MemberPauseInfoService memberPauseInfoService;

    @Resource
    private OrderFinishLogService orderFinishLogService;

    @Resource
    private OpenLogService openLogService;

    Logger logger = LoggerFactory.getLogger(MemberAction.class);

    // 会员列表(普通和搜索都走这里)
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model) {

        int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
        long cardId = ParamUtils.getLongParameter(request, "cardId", 0);
        String cardName = "";
        cardName = ParamUtils.getParameter(request, "cardName", "");
        int custId = ParamUtils.getIntParameter(request, "custId", 0);
        String custName = "";
        custName = ParamUtils.getParameter(request, "custName", "");
        int status = ParamUtils.getIntParameter(request, "status", 0);
        String key = ParamUtils.getParameter(request, "key", "");

        String startDate = ParamUtils.getParameter(request, "startDate", "");
        String endDate = ParamUtils.getParameter(request, "endDate", "");

        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
        int parentCustId = custInfo.getParentId();

        // 获取会员卡种类

        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andParentCustIdEqualTo(parentCustId);
        criteria.andProTypeEqualTo(10);
        criteria.andStatusEqualTo(0);
        List<ProductInfo> memberCards = productInfoService.selectByExample(example);
        model.addAttribute("memberCards", memberCards);
        // 获取所有分店
        CustInfoExample example2 = new CustInfoExample();
        CustInfoExample.Criteria custCriteria = example2.createCriteria();
        custCriteria.andParentTypeEqualTo(2).andStatusEqualTo(0);
        List<CustInfo> custinfos = custInfoService.selectCustInfosByExample(example2);
        model.addAttribute("custinfos", custinfos);
        // ============会员列表==============
        //
        // String sql =
        // "SELECT a.member_id,a.member_name,a.link_phone,a.member_logo,b.start_time,b.end_time,b.product_id FROM member_info a
        // ,member_card b ,order_info c WHERE a.member_id = b.member_id and a.member_id = c.member_id and c.order_type =10 and
        // c.status not in(0,1,3) AND a.parent_cust_id ="
        // + parentCustId;

        /*
         * String sql =
         * "SELECT a.member_id,a.member_name,a.link_phone,a.member_logo,b.start_time,b.end_time,b.product_id FROM member_info a ,member_card b LEFT JOIN order_info c ON b.product_id= c.product_id AND c.order_type =10 WHERE a.member_id = b.member_id  AND a.parent_cust_id = "
         * + parentCustId;
         */

        String sqlStatus02 = "";

        String sql =
            "SELECT * FROM  (SELECT a.member_id,a.start_time,a.end_time,a.product_id,b.sys_cust_id,b.status FROM member_card a LEFT JOIN order_info b ON a.order_id= b.id AND b.order_type =10 and b.status=2 ) c ,member_info d WHERE c.member_id = d.member_id AND d.parent_cust_id = "
                + parentCustId;

        if(cardId > 0) {
            sql += " and c.product_id =" + cardId;
        }
        if(custId > 0) {
            sql += " and  c.sys_cust_id =" + custId;
        }

        // 这个时间段搜索的是卡结束时间
        if("".equals(startDate) && "".equals(endDate)) {
            // status =0为全部，1为已开通，2为已过期,3为已退款
            if(status == 0) {
                // sql += " and b.status not in(0,1)";
            } else if(status == 1) {
                sql += " AND  c.end_time >= now()";
            } else if(status == 2) {

                // sql += " AND c.end_time < now()";

                // 为了实现一人两张卡，一张过期，一张在使用，搜索过期，此人不出现
                // sql by 张存星
                sql =
                    "SELECT d.member_id,d.member_logo,d.member_name,d.link_phone,b.able,b.product_id,b.start_time,b.end_time FROM member_info AS d,(SELECT c.member_id,c.product_id,c.start_time,c.end_time,SUM(CASE WHEN c.end_time>NOW() THEN 1 ELSE 0 END) able FROM member_card c GROUP BY c.member_id) AS b WHERE d.member_id=b.member_id and b.able=0";

            } else if(status == 3) {
                sql += " AND c.status =5 ";
            }
        } else {
            sql += " and '" + startDate + "'<c.end_time and c.end_time <'" + endDate + " 23:59:59'";

        }
        if(key.length() > 0) {
            sql += " and (d.link_phone like '%" + key + "%' or d.member_name like '%" + key + "%')";
        }

        if(status != 2) {
            sql += " order by c.member_id,c.end_time desc";
        }

        List<Map<String, Object>> memberInfos01 = memberService.selectMemberBySql(sql, new Object[]{});

        // map<id,card集合>
        TreeMap<String, Object> memberInfos02 = new TreeMap<String, Object>();

        List<Object> cards = new ArrayList<Object>();
        String flagId = "";
        for(int i = 0; i < memberInfos01.size(); i++) {

            if(!flagId.equals(memberInfos01.get(i).get("member_id").toString())) {
                // 判断是否为第一个
                if(flagId.length() > 0) {
                    memberInfos02.put(flagId, cards);
                }

                flagId = memberInfos01.get(i).get("member_id").toString();
                cards = new ArrayList<Object>();
            }

            cards.add(memberInfos01.get(i));

            // 最后一个
            if(i == (memberInfos01.size() - 1)) {
                memberInfos02.put(flagId, cards);
            }
        }

        // ============共有参数==============

        long defaultCardId = 0;
        String defaultCardName = "全部";
        if(cardId != 0) {
            defaultCardId = cardId;
            defaultCardName = cardName;
        }
        int defaultCustId = 0;
        String defaultCustName = "全部";
        if(custId != 0) {
            defaultCustId = custId;
            defaultCustName = custName;
        }

        String statusNames[] = {"全部", "已开通", "已过期", "已退款"};

        // -------------分页--------------

        int pageCount = memberInfos02.size();
        int pageSize = 10;
        int offSet = 3;
        Page pages = new Page();
        pages.setTotal(pageCount);
        pages.setLimit(pageSize);
        pages.setNo(currentPage);
        example.setPage(pages);
        // int currentPage, int pageCount, int pageSize, int offSet
        Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
        model.addAttribute("pager", page);

        // map<id,card集合>
        TreeMap<String, Object> memberInfos03 = new TreeMap<String, Object>();
        Iterator it = memberInfos02.keySet().iterator();

        // 分页
        int flag = (currentPage - 1) * pageSize;
        for(int i = 0; it.hasNext(); i++) {
            if(i >= (flag + pageSize)) {
                break;
            }

            String mapKey = it.next().toString();
            if(i >= flag) {
                memberInfos03.put(mapKey, memberInfos02.get(mapKey));
            }
        }

        model.addAttribute("memberInfos", memberInfos03);
        model.addAttribute("key", key);
        model.addAttribute("defaultStatus", status);
        model.addAttribute("defaultStatusName", statusNames[status]);
        model.addAttribute("defaultCardId", defaultCardId);
        model.addAttribute("defaultCardName", defaultCardName);
        model.addAttribute("defaultCustId", defaultCustId);
        model.addAttribute("defaultCustName", defaultCustName);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "models/hq/member/list";
    }

    // 会员详情
    @RequestMapping("/detail")
    public String detail(HttpServletRequest request, Model model) {

        long memberId = ParamUtils.getLongParameter(request, "memberId", 0);
        int detailType = ParamUtils.getIntParameter(request, "detailType", 1);
        int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);

        if(detailType == 1) {

            MemberCardExample example = new MemberCardExample();
            MemberCardExample.Criteria criteria = example.createCriteria();
            criteria.andMemberIdEqualTo(memberId);
            // List<MemberCard> memberCards = memberService.selectMemberCardsByExample(example);

            // 分页
            int pageCount = memberService.countByExample(example);
            int pageSize = 6;
            int offSet = 3;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            example.setPage(pages);
            // int currentPage, int pageCount, int pageSize, int offSet
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
            model.addAttribute("pager", page);

            // ============获取会员的会员卡==============
            // OrderInfoExample exampleCard = new OrderInfoExample();
            // OrderInfoExample.Criteria criteriaCard = exampleCard.createCriteria();
            // criteriaCard.andMemberIdEqualTo(memberId);
            // criteriaCard.andOrderTypeEqualTo(10);
            // // 除去新订单和未支付
            // List<Integer> values = new ArrayList<Integer>();
            // Collections.addAll(values, new Integer[]{0, 1});
            // criteriaCard.andStatusNotIn(values);
            //
            // // 分页
            // int pageCount = orderInfoService.countByExample(exampleCard);
            // int pageSize = 6;
            // int offSet = 3;
            // Page pages = new Page();
            // pages.setTotal(pageCount);
            // pages.setLimit(pageSize);
            // pages.setNo(currentPage);
            // exampleCard.setPage(pages);
            // // int currentPage, int pageCount, int pageSize, int offSet
            // Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
            // List<OrderInfo> cards = orderInfoService.selectByExample(exampleCard);

            // model.addAttribute("cards", memberCards);

            String sql =
                "SELECT a.start_time,a.end_time,a.order_id,a.is_active, a.product_id,b.pay_time,b.sale_price,b.status,b.refund_money,b.actual_money,b.refund_time,b.sys_cust_id FROM member_card a LEFT JOIN order_info b  ON a.order_id= b.id WHERE a.member_id = ? limit "
                    + (currentPage - 1) * pageSize + ",6";
            List<Map<String, Object>> cards = memberService.selectMemberBySql(sql, new Object[]{memberId});

            // System.out.println("------------cards------------------"+sql);
            model.addAttribute("cards", cards);

        } else if(detailType == 2) {

            // ============获取会员的课程==============
            OrderInfoExample exampleCourse = new OrderInfoExample();
            OrderInfoExample.Criteria criteriaCourse = exampleCourse.createCriteria();
            criteriaCourse.andMemberIdEqualTo(memberId);
            criteriaCourse.andOrderTypeEqualTo(12);

            // 分页
            int pageCount = orderInfoService.countByExample(exampleCourse);
            int pageSize = 6;
            int offSet = 3;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            exampleCourse.setPage(pages);
            // int currentPage, int pageCount, int pageSize, int offSet
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

            List<OrderInfo> courses = orderInfoService.selectByExample(exampleCourse);
            model.addAttribute("courses", courses);
            model.addAttribute("pager", page);

            // 会员的统计数据
            // 旷课数 = 今天之前的预约（依然显示为2）
            // 今天之前的预约（2）,取消（3），上课数（4）
            List<Map<String, Object>> count01 = orderInfoService.selectBySql(
                "SELECT STATUS,COUNT(STATUS) AS num FROM order_info WHERE STATUS IN (2,3,4)  AND order_type = 12 AND member_id = ? GROUP BY STATUS ",
                new Object[]{memberId});
            // 旷课数（2）
            List<Map<String, Object>> count02 = orderInfoService.selectBySql(
                "SELECT COUNT(1) AS num FROM order_info WHERE STATUS =2 AND end_time<NOW() AND  order_type = 12 AND member_id = ? ",
                new Object[]{memberId});

            // 预约总人数=今天之前的上课数+预约数+取消预约人数
            int booking = 0, check = 0, cancel = 0, absent = 0, allBooking = 0;

            for(Map<String, Object> m: count01) {
                if("2".equals(m.get("status").toString())) {
                    allBooking = Integer.parseInt(m.get("num").toString());
                }
                if("3".equals(m.get("status").toString())) {
                    cancel = Integer.parseInt(m.get("num").toString());
                    booking += cancel;
                }
                if("4".equals(m.get("status").toString())) {
                    check = Integer.parseInt(m.get("num").toString());
                    booking += check;
                }
            }

            booking += Integer.parseInt(count02.get(0).get("num").toString());
            model.addAttribute("booking", booking);
            model.addAttribute("check", check);
            model.addAttribute("cancel", cancel);
            model.addAttribute("absent", count02.get(0).get("num").toString());

        } else if(detailType == 3) {

            // ============获取会员的评论==============
            int custId = ParamUtils.getIntParameter(request, "custId", 0);
            CommentInfoExample exampleComment = new CommentInfoExample();
            CommentInfoExample.Criteria criteriaComment = exampleComment.createCriteria();
            criteriaComment.andMemberIdEqualTo(memberId);
            criteriaComment.andStatusEqualTo(0);
            if(custId > 0) {
                criteriaComment.andSysCustIdEqualTo(custId);
            }

            // 分页
            int pageCount = commentService.countByExample(exampleComment);
            int pageSize = 6;
            int offSet = 3;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            exampleComment.setPage(pages);
            // int currentPage, int pageCount, int pageSize, int offSet
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

            List<CommentInfoWithBLOBs> comments = commentService.selectCommentInfosByExample(exampleComment);
            model.addAttribute("comments", comments);
            model.addAttribute("pager", page);

            // 获取此用户在几家店参加了团课
            List<Map<String, Object>> custIds = orderInfoService.selectBySql(
                "SELECT sys_cust_id FROM order_info WHERE member_id =?  AND order_type = 11 GROUP BY sys_cust_id",
                new Object[]{memberId});
            model.addAttribute("custIds", custIds);
            model.addAttribute("custId", custId);

        } else if(detailType == 4) {

            int custId = ParamUtils.getIntParameter(request, "custId", 0);

            // ============获取会员的团课==============
            OrderInfoExample exampleGroup = new OrderInfoExample();
            OrderInfoExample.Criteria criteriaGroup = exampleGroup.createCriteria();
            criteriaGroup.andMemberIdEqualTo(memberId);
            List<Integer> values = new ArrayList<Integer>();
            values.add(2);
            values.add(3);
            values.add(4);
            values.add(5);
            values.add(6);
            criteriaGroup.andStatusIn(values);
            if(custId > 0) {
                criteriaGroup.andSysCustIdEqualTo(custId);
            }
            criteriaGroup.andOrderTypeEqualTo(11);

            // 分页
            int pageCount = orderInfoService.countByExample(exampleGroup);
            int pageSize = 6;
            int offSet = 3;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            exampleGroup.setPage(pages);
            // int currentPage, int pageCount, int pageSize, int offSet
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

            List<OrderInfo> groups = orderInfoService.selectByExample(exampleGroup);
            model.addAttribute("groups", groups);
            model.addAttribute("pager", page);

            // 获取此用户在几家店参加了团课
            List<Map<String, Object>> custIds = orderInfoService.selectBySql(
                "SELECT sys_cust_id FROM order_info WHERE member_id =?  AND order_type = 11 GROUP BY sys_cust_id",
                new Object[]{memberId});
            model.addAttribute("custIds", custIds);
            model.addAttribute("custId", custId);

        } else if(detailType == 5) {
            int custId = ParamUtils.getIntParameter(request, "custId", 0);
            // ============获取会员的私教==============
            OrderInfoExample exampleCoach = new OrderInfoExample();
            OrderInfoExample.Criteria criteriaCoach = exampleCoach.createCriteria();
            criteriaCoach.andMemberIdEqualTo(memberId);
            if(custId > 0) {
                criteriaCoach.andSysCustIdEqualTo(custId);
            }
            criteriaCoach.andStatusEqualTo(2);

            criteriaCoach.andOrderTypeEqualTo(13);

            // 分页
            int pageCount = orderInfoService.countByExample(exampleCoach);
            int pageSize = 6;
            int offSet = 3;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            exampleCoach.setPage(pages);
            // int currentPage, int pageCount, int pageSize, int offSet
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

            List<OrderInfo> coachs = orderInfoService.selectByExample(exampleCoach);
            model.addAttribute("coachs", coachs);
            model.addAttribute("pager", page);

            // 获取此用户在几家店购买了私教
            List<Map<String, Object>> custIds = orderInfoService.selectBySql(
                "SELECT sys_cust_id FROM order_info WHERE member_id =?  AND order_type = 13 GROUP BY sys_cust_id",
                new Object[]{memberId});
            model.addAttribute("custIds", custIds);
            model.addAttribute("custId", custId);
        } else if(detailType == 6) {
            OpenLogExample example = new OpenLogExample();
            OpenLogExample.Criteria criteria = example.createCriteria();
            criteria.andMemberIdEqualTo(memberId);
            example.setOrderByClause("open_time  desc");
            // 分页
            int pageCount = openLogService.countByExample(example);
            int pageSize = 10;
            int offSet = 5;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            example.setPage(pages);
            // int currentPage, int pageCount, int pageSize, int offSet
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
            model.addAttribute("pager", page);
            List<OpenLog> openlog = openLogService.selectByExample(example);
            model.addAttribute("openlog", openlog);
        }

        // =========获取会员信息=========
        MemberInfo memberInfo = memberService.selectByPrimaryKey(memberId);
        model.addAttribute("memberInfo", memberInfo);
        MemberCardExample example = new MemberCardExample();
        example.setOrderByClause("end_time desc");
        MemberCardExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(memberId);

        List<MemberCard> selectByExample = memberService.selectMemberCardsByExample(example);
        if(selectByExample != null) {
            for(int i = 0; i < selectByExample.size(); i++) {
                long productId = selectByExample.get(0).getProductId();
                Date startTime = selectByExample.get(0).getStartTime();
                Date endTime = selectByExample.get(0).getEndTime();
                model.addAttribute("productId", productId);
                model.addAttribute("startTime", startTime);
                model.addAttribute("endTime", endTime);
            }
        }
        // =========共有信息===========
        model.addAttribute("memberId", memberId);
        model.addAttribute("now", new Date());
        model.addAttribute("detailType", detailType);
        return "models/hq/member/detail";
    }

    // 获取团课签到信息 或私教上课信息
    @RequestMapping("/detail/sign")
    public void groupSign(HttpServletRequest request, HttpServletResponse response) {

        long orderId = ParamUtils.getLongParameter(request, "orderId", 0);
        BaseJson res = new BaseJson();

        PrintWriter out = null;
        try {
            OrderFinishLogExample example = new OrderFinishLogExample();
            OrderFinishLogExample.Criteria criteria = example.createCriteria();
            criteria.andOrderIdEqualTo(orderId);
            List<OrderFinishLog> logs = orderFinishLogService.selectByExample(example);

            res.setStatus(1);
            res.setData(logs);

            out = response.getWriter();
        } catch(IOException e1) {
            logger.debug("MemberAction is error:" + e1.getMessage(), e1);
        }

        // 用Gson转为json
        out.print(new GsonBuilder().create().toJson(res));
    }

    // 发送消息
    @RequestMapping("/detail/sendmsg")
    public void sendmsg(HttpServletRequest request, HttpServletResponse response) {
        String mobile = ParamUtils.getParameter(request, "mobile", "");
        String content = ParamUtils.getParameter(request, "content", "");
        BaseJson res = new BaseJson();

        PrintWriter out = null;
        try {

            res = SMSUtils.sendMsg(mobile, content);
            out = response.getWriter();
        } catch(IOException e1) {
            logger.debug("MemberAction is error:" + e1.getMessage(), e1);
        }

        // 用Gson转为json
        out.print(new GsonBuilder().create().toJson(res));
    }

    // 私教上课和团课的具体信息 通过id查订单表和相关的表
    @RequestMapping("/coach/detail")
    public void coachDetail(HttpServletRequest request, HttpServletResponse response) {
        long id = ParamUtils.getLongParameter(request, "id", 0);
        BaseJson res = new BaseJson();

        PrintWriter out = null;
        try {

            Map<String, Object> map = new HashMap<String, Object>();
            OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(id);
            // 手续费费率
            int fee = 0;
            // 收费团课
            if(orderInfo.getOrderType() == 11) {
                ProductInfo pInfo = productInfoService.selectByPrimaryKey(orderInfo.getProductId());
                fee = pInfo.getFee();
                // 私教
            } else if(orderInfo.getOrderType() == 13) {

                CoachInfo cInfo = coachInfoService.selectByPrimaryKey(orderInfo.getProductId());
                fee = cInfo.getFee();
            }

            map.put("orderInfo", orderInfo);
            map.put("fee", fee);
            res.setData(map);

            out = response.getWriter();
        } catch(IOException e1) {
            try {
                out = response.getWriter();
            } catch(IOException e) {
                logger.debug("MemberAction is error:" + e.getMessage(), e);
            }
            logger.debug("MemberAction is error:" + e1.getMessage(), e1);
        }

        // 用Gson转为json
        out.print(new GsonBuilder().create().toJson(res));
    }

    // 私教退款和团课退款
    @RequestMapping("/coach/refund")
    public void refund(HttpServletRequest request, HttpServletResponse response) {
        long id = ParamUtils.getLongParameter(request, "id", 0);
        // 实际退款
        int money = ParamUtils.getIntParameter(request, "money", 0);
        // 建议退款
        int refund = ParamUtils.getIntParameter(request, "refund", 0);
        BaseJson res = new BaseJson();

        PrintWriter out = null;
        try {

            OrderInfo record = new OrderInfo();
            record.setId(id);
            record.setActualMoney(money * 100);
            record.setRefundMoney(refund * 100);
            record.setStatus(5);
            record.setRefundTime(new Date());
            record.setRefundUser(userLoginService.getCurrentUserInfo().getUserLogin().getRealname());
            int status = orderInfoService.updateByPrimaryKeySelective(record);

            res.setStatus(status);
            out = response.getWriter();
        } catch(IOException e1) {
            logger.debug("MemberAction is error:" + e1.getMessage(), e1);
        }

        // 用Gson转为json
        out.print(new GsonBuilder().create().toJson(res));
    }

    // 修改关闭约课功能
    @RequestMapping("/updateTure")
    public void updateTure(HttpServletRequest request, HttpServletResponse response) {
        long id = ParamUtils.getLongParameter(request, "id");
        int isAppoint = ParamUtils.getIntParameter(request, "isAppoint");
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(id);
        if(isAppoint == 0) {
            memberInfo.setIsAppoint(1);
        } else {
            memberInfo.setIsAppoint(0);
        }
        int flag = memberService.updateByPrimaryKeySelective(memberInfo);
        try {
            PrintWriter pw = response.getWriter();
            pw.print(flag);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // 永久停卡 既加入和撤销黑名单
    @RequestMapping("/updateBlack")
    public void updateBlack(HttpServletRequest request, HttpServletResponse response) {
        long id = ParamUtils.getLongParameter(request, "id");
        int isBlack = ParamUtils.getIntParameter(request, "isBlack");
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(id);
        if(isBlack == 0) {
            memberInfo.setBlacklistStatus(1);
        } else {
            memberInfo.setBlacklistStatus(0);
        }
        int flag = memberService.updateByPrimaryKeySelective(memberInfo);
        try {
            PrintWriter pw = response.getWriter();
            pw.print(flag);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // 停卡列表
    @RequestMapping("/pauselist")
    public String pauseList(HttpServletRequest request, Model model) {

        try {
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);

            MemberPauseInfoExample example = new MemberPauseInfoExample();
            example.setOrderByClause(" status asc");
            List<Integer> values = Arrays.asList(new Integer[]{0, 2});
            example.createCriteria().andStatusIn(values);
            // 分页
            int pageCount = memberPauseInfoService.countByExample(example);
            int pageSize = 100;
            int offSet = 5;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            example.setPage(pages);
            // int currentPage, int pageCount, int pageSize, int offSet
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

            List<MemberPauseInfo> infos = memberPauseInfoService.selectByExample(example);

            model.addAttribute("infos", infos);
        } catch(Exception e) {
            logger.debug("pauselist is error:" + e.getMessage(), e);
        }
        return "models/hq/member/pauselist";
    }

    // 手动停卡插入的界面
    @RequestMapping("/pauseinsert")
    public String pauseinsert(HttpServletRequest request, Model model) {

        try {

            long memberId = ParamUtils.getLongParameter(request, "memberId", 0);
            MemberInfo memberInfo = memberService.selectByPrimaryKey(memberId);
            MemberCardExample memberCardExample1 = new MemberCardExample();
            memberCardExample1.setOrderByClause(" end_time desc");
            memberCardExample1.createCriteria().andMemberIdEqualTo(memberInfo.getMemberId())
                .andEndTimeGreaterThanOrEqualTo(new Date());
            // List<MemberCard> memberCardList1 = memberService.selectMemberCardsByExample(memberCardExample1);
            // boolean isEndTimeDays = false;
            // if(memberCardList1 != null && memberCardList1.size() > 0) {
            // isEndTimeDays = memberCardList1.get(0).getEndTime().getTime() - new Date().getTime() > 86400000 * 45;
            // }

            MemberPauseInfoExample example = new MemberPauseInfoExample();
            example.setOrderByClause(" end_date desc");
            example.createCriteria().andMemberIdEqualTo(memberId);
            List<MemberPauseInfo> pauseInfos = memberPauseInfoService.selectByExample(example);

            model.addAttribute("memberInfo", memberInfo);
            model.addAttribute("pauseInfos", pauseInfos);
            // model.addAttribute("isEndTimeDays", isEndTimeDays);
        } catch(Exception e) {
            logger.debug("pauselist is error:" + e.getMessage(), e);
        }
        return "models/hq/member/pauseinsert";
    }

    // 修改停卡状态
    @RequestMapping("/updatePause")
    public void updatePause(HttpServletRequest request, HttpServletResponse response) {
        int id = ParamUtils.getIntParameter(request, "id");
        int status = ParamUtils.getIntParameter(request, "status");
        String memberId = ParamUtils.getParameter(request, "memberId", "0");
        Gson gson = new GsonBuilder().create();
        BaseJson baseJson = new BaseJson();
        baseJson.setStatus(1);
        baseJson.setMsg("操作失败");
        PrintWriter out = null;
        try {
            UserLogin user = userLoginService.getCurrentUserInfo().getUserLogin();
            out = response.getWriter();
            MemberInfo m = memberService.selectByPrimaryKey(Long.parseLong(memberId));
            // 如果是同意，则在卡表中加时间
            if(status == 1) {
                MemberCardExample example = new MemberCardExample();
                example.setOrderByClause(" end_time desc");
                example.createCriteria().andMemberIdEqualTo(Long.parseLong(memberId));
                List<MemberCard> cards = memberService.selectMemberCardsByExample(example);

                if(cards != null && cards.size() > 0) {

                    MemberPauseInfo pauseInfo = memberPauseInfoService.selectByPrimaryKey(id);

                    // 修改数据
                    MemberCard mc = cards.get(0);

                    // date = new date();// 取时间
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(mc.getEndTime());
                    calendar.add(calendar.DATE,
                        (int)((pauseInfo.getEndDate().getTime() - pauseInfo.getStartDate().getTime()) / 86400000));

                    mc.setEndTime(calendar.getTime());
                    memberService.updateCardByPrimaryKeySelective(mc);

                    SMSUtils.sendMsg(m.getLinkPhone(), "尊敬的会员，您的停卡申请已经通过。详情请致电：0513-85580016");

                }
            } else if(status == 2) {

                SMSUtils.sendMsg(m.getLinkPhone(), "尊敬的会员，您的停卡申请没有通过。详情请致电：0513-85580016");

            }

            MemberPauseInfo record = new MemberPauseInfo();
            record.setId(id);
            record.setStatus(status);
            record.setOperationUser(user.getRealname());
            memberPauseInfoService.updateByPrimaryKeySelective(record);
            baseJson.setStatus(0);
            baseJson.setMsg("操作成功");

        } catch(IOException e) {
            try {
                baseJson.setStatus(1);
                baseJson.setMsg("操作失败");
                out = response.getWriter();
            } catch(IOException e1) {
                e1.printStackTrace();
            }
            logger.debug("updatePause is error:" + e.getMessage(), e);
        }

        out.print(gson.toJson(baseJson));
    }

    // 手动选择时间停卡
    @RequestMapping("/manualPause")
    public void manualPause(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        BaseJson baseJson = new BaseJson();
        PrintWriter out = null;
        try {
            out = response.getWriter();

            String memberId = ParamUtils.getParameter(request, "memberId", "0");
            String startDate = ParamUtils.getParameter(request, "startDate");
            String endDate = ParamUtils.getParameter(request, "endDate");
            String content = ParamUtils.getParameter(request, "content");

            UserLogin user = userLoginService.getCurrentUserInfo().getUserLogin();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDateTime = sdf.parse(startDate);
            Date endDateTime = sdf.parse(endDate);

            // 停卡时间大于今天
            boolean flagToday = startDateTime.getTime() > new Date().getTime();
            // 结束到开始时间段不能大于30天
            boolean flag = (endDateTime.getTime() - startDateTime.getTime()) < 86400000L * 30;

            if(flag && flagToday && !startDate.equals(endDate)) {
                MemberPauseInfo record = new MemberPauseInfo();
                record.setStartDate(startDateTime);
                record.setEndDate(endDateTime);
                record.setStatus(3);
                record.setCreateDate(new Date());
                record.setMemberId(Long.parseLong(memberId));
                record.setOperationUser(user.getRealname());
                record.setContent(content);
                memberPauseInfoService.insertSelective(record);

                // 会员卡加时间
                MemberCardExample example = new MemberCardExample();
                example.setOrderByClause(" end_time desc");
                example.createCriteria().andMemberIdEqualTo(Long.parseLong(memberId));
                List<MemberCard> cards = memberService.selectMemberCardsByExample(example);

                MemberCard mc = cards.get(0);

                // date = new date();// 取时间
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(mc.getEndTime());
                calendar.add(calendar.DATE, (int)((endDateTime.getTime() - startDateTime.getTime()) / 86400000));

                mc.setEndTime(calendar.getTime());
                memberService.updateCardByPrimaryKeySelective(mc);

                baseJson.setStatus(0);
                baseJson.setMsg("操作成功");

            } else {
                baseJson.setStatus(1);
                baseJson.setMsg("停卡区间大于30天或开始时间小于今天！");
            }

        } catch(Exception e) {
            try {
                baseJson.setStatus(1);
                baseJson.setMsg("操作失败");
                out = response.getWriter();
            } catch(IOException e1) {
                e1.printStackTrace();
            }
            logger.debug("updatePause is error:" + e.getMessage(), e);
        }

        out.print(gson.toJson(baseJson));
    }
}