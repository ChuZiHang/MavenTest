package com.lol.web.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
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

import com.lol.common.bean.CurrentUserInfo;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.OrderInfo;
import com.lol.common.model.ds1.OrderInfoExample;
import com.lol.common.model.ds1.OrderInfoExample.Criteria;
import com.lol.common.plugin.Page;
import com.lol.common.plugin.Pager;
import com.lol.common.plugin.PagerService;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.OrderInfoService;
import com.lol.common.service.UserLoginService;
import com.lol.common.util.ParamUtils;
import com.lol.web.JSTLFuncs;

/**
 * 营业报表
 * 
 * @author qiuwei
 *
 */
@Controller
@RequestMapping("/account")
public class BusinessAction {

    Logger logger = LoggerFactory.getLogger(BusinessAction.class);

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private CustInfoService custInfoService;

    /**
     * 营业报表首页
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/business/report")
    public String list(HttpServletRequest request, Model model) {

        try {
            String data = ParamUtils.getParameter(request, "data");
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
            String startDate = ParamUtils.getParameter(request, "startDate");
            String endDate = ParamUtils.getParameter(request, "endDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(data == "" && "".equals(data)) {
                Date date = new Date();
                String date1 = sdf.format(date);
                data = date1 + " ~ " + date1;
            }
            int type = ParamUtils.getIntParameter(request, "type", 0);
            int custId = ParamUtils.getIntParameter(request, "custId", 0);
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            int sysCustId = currentUserInfo.getCustInfo().getSystemId();
            int parentId = currentUserInfo.getCustInfo().getParentId();
            int parentType = currentUserInfo.getCustInfo().getParentType();

            OrderInfoExample example = new OrderInfoExample();
            Criteria criteria = example.createCriteria();
            if(parentType == 1) {// 总店
                criteria.andParentCustIdEqualTo(parentId).andStatusGreaterThan(1);
                if(type > 0) {
                    criteria.andOrderTypeEqualTo(type);
                }
                if(custId > 0) {
                    criteria.andSysCustIdEqualTo(custId);
                }
                if(!"".equals(startDate)) {
                    criteria.andPayTimeGreaterThanOrEqualTo(sdf.parse(startDate));
                }
                if(!"".equals(endDate)) {
                    criteria.andPayTimeLessThanOrEqualTo(sdf.parse(endDate));
                }
            } else {
                criteria.andSysCustIdEqualTo(sysCustId).andStatusGreaterThan(1);
                if(type > 0) {
                    criteria.andOrderTypeEqualTo(type);
                }
                if(!"".equals(startDate)) {
                    criteria.andPayTimeGreaterThanOrEqualTo(sdf.parse(startDate));
                }
                if(!"".equals(endDate)) {
                    criteria.andPayTimeLessThanOrEqualTo(sdf.parse(endDate));
                }
            }

            List<Integer> values = Arrays.asList(new Integer[]{2, 4});
            criteria.andStatusIn(values);
            example.setOrderByClause("pay_time desc");

            List<OrderInfo> orderList1 = orderInfoService.selectOrdersbyExample(example);

            int totalMoney = 0;
            int totalRefund = 0;
            if(orderList1 != null && orderList1.size() > 0) {
                for(int i = 0; i < orderList1.size(); i++) {
                    totalMoney += (int)orderList1.get(i).getMemPrice();
                    if(orderList1.get(i).getRefundMoney() != null && !"".equals(orderList1.get(i).getRefundMoney())) {
                        totalRefund += (int)orderList1.get(i).getRefundMoney();
                        totalMoney = totalMoney - totalRefund;
                    }
                }
            }

            int pageCount = orderInfoService.countByExample(example);
            int pageSize = 30;
            int offSet = 5;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);

            example.setPage(pages);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

            List<OrderInfo> orderList = orderInfoService.selectOrdersbyExample(example);

            // 搜索类型回显
            String typeName = "";
            if(type == 10) {
                typeName = "卡";
            } else if(type == 11) {
                typeName = "收费团课";
            } else if(type == 12) {
                typeName = "课程";
            } else if(type == 13) {
                typeName = "私教";
            }
            model.addAttribute("data", data);
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
            model.addAttribute("orderList", orderList);
            model.addAttribute("typeName", typeName);
            model.addAttribute("pager", page);
            model.addAttribute("totalRefund", totalRefund);
            model.addAttribute("totalMoney", totalMoney);
            model.addAttribute("custId", custId);
            model.addAttribute("type", type);
            model.addAttribute("parentType", parentType);

        } catch(Exception e) {
            e.printStackTrace();
        }
        /*
         * try { //分页条件 int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
         * 
         * int type = ParamUtils.getIntParameter(request, "type", 0); Date date = ParamUtils.getDateParameter(request, "date"); int
         * custId = ParamUtils.getIntParameter(request, "custId", 0);
         * 
         * if(date == null || date.equals("") ){ date = new Date(); }
         * 
         * //日表 Date startDate = getStartDate(date); Date endDate = getEndDate(date);
         * 
         * model = getmodel(model, startDate, endDate, type, custId, currentPage); model.addAttribute("date", date); Date date2 =
         * new Date(); date2.setHours(date.getHours() - 14); date2.setYear(date.getYear()); date2.setMonth(date.getMonth());
         * date2.setDate(date.getDate()); model.addAttribute("date2", date2); SimpleDateFormat sdf = new
         * SimpleDateFormat("yyyy-MM-dd"); model.addAttribute("date3", sdf.format(date)); model.addAttribute("custId", custId);
         * model.addAttribute("type", type); } catch (Exception e) { logger.debug("AccountAction view is error:" + e.getMessage(),
         * e); }
         */
        return "models/hq/account/business1";
    }

    /**
     * 
     * 业务报表
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/business/sys")
    public String syslist(HttpServletRequest request, Model model) {
        try {
            String data = ParamUtils.getParameter(request, "data");
            String startDate = ParamUtils.getParameter(request, "startDate");
            String endDate = ParamUtils.getParameter(request, "endDate");
            int custId = ParamUtils.getIntParameter(request, "custId", 0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
            int parentCustId = custInfo.getParentId();
            int systemId = custInfo.getSystemId();
            int parentType = custInfo.getParentType();
            String sqlCust = " and o.parent_cust_id = " + parentCustId;
            if(parentType == 2) {
                sqlCust += " and o.sys_cust_id = " + systemId;
            }
            if(custId > 0) {
                sqlCust += " and o.sys_cust_id = " + custId;
            }
            if(data == "" && "".equals(data)) {
                Date startDt = new Date();
                endDate = sdf.format(startDt);
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_MONTH, -6);// 关键是这个7天前....
                startDate = sdf.format(c.getTime());
                data = startDate + "~" + endDate;
                model = getDate(model, startDate, endDate, custId);
            } else {
                model = getDate(model, startDate, endDate, custId);
            }
            // String sqlDate = "";
            String sqlOpen = "";
            String sqlPay = "";

            String sqlMember = " and  o.create_date  between  '" + startDate + " 00:00:00'   and  '" + endDate + " 23:59:59'";
            // sqlDate = " and o.create_time between '" + startDate + " 00:00:00' and '" + endDate + " 23:59:59'";
            sqlOpen = " and  o.open_time  between  '" + startDate + " 00:00:00'   and  '" + endDate + " 23:59:59'";
            sqlPay = " and  o.pay_time  between  '" + startDate + " 00:00:00'   and  '" + endDate + " 23:59:59'";

            // 新增会员
            List<Map<String, Object>> addMember = orderInfoService.selectBySql(
                "SELECT COUNT(1)  as num FROM member_card  o where o.is_active=1 and o.count = 1 " + sqlCust + sqlMember,
                new Object[]{});

            if(addMember != null && addMember.size() > 0) {
                int addMember1 = Integer.parseInt(addMember.get(0).get("num") + "");
                model.addAttribute("addMember", addMember1);
            }
            // 到店人数
            List<Map<String, Object>> openMember = orderInfoService
                .selectBySql("SELECT COUNT(1)  as num FROM open_log  o where  o.status=0 " + sqlCust + sqlOpen, new Object[]{});
            if(openMember != null && openMember.size() > 0) {
                int openMember1 = Integer.parseInt(openMember.get(0).get("num") + "");
                model.addAttribute("openMember", openMember1);
            }
            // 约课人数
            List<Map<String, Object>> booking = orderInfoService.selectBySql(
                "SELECT COUNT(1) AS num FROM order_info o,course_price b WHERE o.product_id = b.id AND b.end_time<now() and o.status in(2,4) AND o.order_type =12 "
                    + sqlCust + sqlPay,
                new Object[]{});
            if(booking != null && booking.size() > 0) {
                int booking1 = Integer.parseInt(booking.get(0).get("num") + "");
                model.addAttribute("booking", booking1);
            }
            // 到课人数
            List<Map<String, Object>> checkBooking = orderInfoService.selectBySql(
                "SELECT COUNT(1) AS num FROM order_info o,course_price b WHERE o.product_id = b.id AND b.end_time<now()  and o.status = 4 AND o.order_type =12 "
                    + sqlCust + sqlPay,
                new Object[]{});
            if(checkBooking != null && checkBooking.size() > 0) {
                int checkBooking1 = Integer.parseInt(checkBooking.get(0).get("num") + "");
                model.addAttribute("checkBooking", checkBooking1);
            }

            model.addAttribute("data", data);
            model.addAttribute("custId", custId);
            model.addAttribute("endDate", endDate);
            model.addAttribute("startDate", startDate);
            model.addAttribute("parentType", parentType);
        } catch(Exception e) {
            logger.debug("AccountAction view is error:" + e.getMessage(), e);
        }
        return "models/hq/account/sys";
    }

    private Model getDate(Model model, String startDate, String endDate, int custId) throws ParseException {

        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
        int parentCustId = custInfo.getParentId();
        int systemId = custInfo.getSystemId();
        int parentType = custInfo.getParentType();
        String sqlCust = " and o.parent_cust_id = " + parentCustId;
        if(parentType == 2) {
            sqlCust += " and o.sys_cust_id = " + systemId;
        }
        if(custId > 0) {
            sqlCust += " and o.sys_cust_id = " + custId;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        Date startDate1 = sdf.parse(startDate);
        Date endDate1 = sdf.parse(endDate);
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        List<Integer> list3 = new ArrayList<Integer>();
        List<Integer> list4 = new ArrayList<Integer>();
        Calendar c = Calendar.getInstance();
        c.setTime(startDate1);
        c.add(Calendar.DATE, -1);
        Date date = c.getTime();
        Map<String, Object> link = new LinkedHashMap<String, Object>();
        while(!date.equals(endDate1)) {
            List<Integer> list5 = new ArrayList<Integer>();
            c.setTime(date);
            c.add(Calendar.DATE, 1); // 日期加1天
            date = c.getTime();
            String format = sdf.format(date);
            String format1 = sdf1.format(date);
            int parseInt = Integer.parseInt(format1);
            list.add(parseInt);
            list5.add(parseInt);
            // 新增会员
            // "SELECT COUNT(1) as num FROM member_card o where o.is_active=1 and o.count = 1 " + sqlCust + sqlMember, new
            // Object[]{});
            List<Map<String, Object>> addMember = orderInfoService.selectBySql(
                "SELECT COUNT(1)  as num FROM member_card  o where o.is_active=1 and o.count = 1  and o.create_date  LIKE  '%"
                    + format + "%' " + sqlCust,
                new Object[]{});
            if(addMember != null && addMember.size() > 0) {
                int addMember1 = Integer.parseInt(addMember.get(0).get("num") + "");
                list1.add(addMember1);
                list5.add(addMember1);
            }
            // 到店人数
            List<Map<String, Object>> openMember = orderInfoService
                .selectBySql("SELECT COUNT(1)  as num FROM open_log  o where  o.status=0  and  o.open_time   LIKE  '%" + format
                    + "%' " + sqlCust, new Object[]{});
            if(openMember != null && openMember.size() > 0) {
                int openMember1 = Integer.parseInt(openMember.get(0).get("num") + "");
                list2.add(openMember1);
                list5.add(openMember1);
            }
            // 约课人数
            List<Map<String, Object>> booking = orderInfoService.selectBySql(
                "SELECT COUNT(1) AS num FROM order_info o,course_price b WHERE o.product_id = b.id AND b.end_time<now() and o.status in(2,4) AND o.order_type =12  and o.pay_time  LIKE  '%"
                    + format + "%' " + sqlCust,
                new Object[]{});
            if(booking != null && booking.size() > 0) {
                int booking1 = Integer.parseInt(booking.get(0).get("num") + "");
                list3.add(booking1);
                list5.add(booking1);
            }
            // 到课人数
            List<Map<String, Object>> checkBooking = orderInfoService.selectBySql(
                "SELECT COUNT(1) AS num FROM order_info o,course_price b WHERE o.product_id = b.id AND b.end_time<now()  and o.status = 4 AND o.order_type =12   and o.pay_time  LIKE  '%"
                    + format + "%' " + sqlCust,
                new Object[]{});
            if(checkBooking != null && checkBooking.size() > 0) {
                int checkBooking1 = Integer.parseInt(checkBooking.get(0).get("num") + "");
                list4.add(checkBooking1);
                list5.add(checkBooking1);
            }
            link.put(format, list5);
        }
        model.addAttribute("link", link);
        model.addAttribute("list", list);
        model.addAttribute("list1", list1);
        model.addAttribute("list2", list2);
        model.addAttribute("list3", list3);
        model.addAttribute("list4", list4);
        return model;
    }

    /**
     * 
     * 业务报表导出
     * 
     */

    @RequestMapping("/business/exportdate")
    public void exportDate(HttpServletRequest request, HttpServletResponse response) {
        try {
            String startDate = ParamUtils.getParameter(request, "startDate");
            String endDate = ParamUtils.getParameter(request, "endDate");
            int custId = ParamUtils.getIntParameter(request, "custId", 0);
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            int sysCustId = currentUserInfo.getCustInfo().getSystemId();
            int parentId = currentUserInfo.getCustInfo().getParentId();
            int parentType = currentUserInfo.getCustInfo().getParentType();
            String sqlCust = " and o.parent_cust_id = " + parentId;
            if(parentType == 2) {
                sqlCust += " and o.sys_cust_id = " + sysCustId;
            }
            if(custId > 0) {
                sqlCust += " and o.sys_cust_id = " + custId;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            Date startDate1 = sdf.parse(startDate);
            Date endDate1 = sdf.parse(endDate);
            Calendar c = Calendar.getInstance();
            c.setTime(startDate1);
            c.add(Calendar.DATE, -1);
            Date date1 = c.getTime();
            Map<String, List<Integer>> link = new LinkedHashMap<String, List<Integer>>();
            while(!date1.equals(endDate1)) {
                List<Integer> list5 = new ArrayList<Integer>();
                c.setTime(date1);
                c.add(Calendar.DATE, 1); // 日期加1天
                date1 = c.getTime();
                String format = sdf.format(date1);
                String format1 = sdf1.format(date1);
                int parseInt = Integer.parseInt(format1);
                list5.add(parseInt);
                // 新增会员
                List<Map<String, Object>> addMember = orderInfoService.selectBySql(
                    "SELECT COUNT(1)  as num FROM member_card  o where  o.is_active=1 and o.count = 1  and o.create_date  LIKE  '%"
                        + format + "%' " + sqlCust,
                    new Object[]{});
                if(addMember != null && addMember.size() > 0) {
                    int addMember1 = Integer.parseInt(addMember.get(0).get("num") + "");
                    list5.add(addMember1);
                }
                // 到店人数
                List<Map<String, Object>> openMember = orderInfoService
                    .selectBySql("SELECT COUNT(1)  as num FROM open_log  o where  o.status=0  and  o.open_time   LIKE  '%" + format
                        + "%' " + sqlCust, new Object[]{});
                if(openMember != null && openMember.size() > 0) {
                    int openMember1 = Integer.parseInt(openMember.get(0).get("num") + "");
                    list5.add(openMember1);
                }
                // 约课人数
                List<Map<String, Object>> booking = orderInfoService.selectBySql(
                    "SELECT COUNT(1) AS num FROM order_info o,course_price b WHERE o.product_id = b.id AND b.end_time<now() and o.status in(2,4) AND o.order_type =12  and o.pay_time  LIKE  '%"
                        + format + "%' " + sqlCust,
                    new Object[]{});
                if(booking != null && booking.size() > 0) {
                    int booking1 = Integer.parseInt(booking.get(0).get("num") + "");
                    list5.add(booking1);
                }
                // 到课人数
                List<Map<String, Object>> checkBooking = orderInfoService.selectBySql(
                    "SELECT COUNT(1) AS num FROM order_info o,course_price b WHERE o.product_id = b.id AND b.end_time<now()  and o.status = 4 AND o.order_type =12   and o.pay_time  LIKE  '%"
                        + format + "%' " + sqlCust,
                    new Object[]{});
                if(checkBooking != null && checkBooking.size() > 0) {
                    int checkBooking1 = Integer.parseInt(checkBooking.get(0).get("num") + "");
                    list5.add(checkBooking1);
                }
                link.put(format, list5);
            }

            String[] hellName = {"日期", "新增会员", "到店人数", "约课人数", "到课人数"};

            String title = "业务报表";
            // 设计表
            // 第一步，创建一个webbook，对应一个Excel文件
            @SuppressWarnings("resource")
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFDataFormat format = wb.createDataFormat();
            HSSFCellStyle style = wb.createCellStyle();
            HSSFFont font = wb.createFont();
            font.setFontName("宋体");
            font.setFontHeightInPoints((short)12);// 设置字体大小
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(title);
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
            for(int i = 0; i < hellName.length; i++) {
                cell = row.createCell(i);
                if(hellName[i].contains("时间")) {
                    cell.setCellValue(hellName[i]);
                    cell.setCellStyle(styledate);
                } else {
                    cell.setCellValue(hellName[i]);
                    cell.setCellStyle(style);
                }
            }
            // 第五步，写入实体数据 实际应用中这些数据从数据库得到
            // String[] hellName ={"订单号", "分店", "类型", "产品名称", "数量", "会员", "时间", "金额", "操作人"};
            // Collection<java.util.List<Integer>> values = link.values();
            List<List<Integer>> mapValuesList = new ArrayList<List<Integer>>(link.values());

            for(int i = 0; i < mapValuesList.size(); i++) {

                row = sheet.createRow(i + 1);

                row.createCell(0).setCellValue(mapValuesList.get(i).get(0));
                row.createCell(1).setCellValue(mapValuesList.get(i).get(1));
                row.createCell(2).setCellValue(mapValuesList.get(i).get(2));
                row.createCell(3).setCellValue(mapValuesList.get(i).get(3));
                row.createCell(4).setCellValue(mapValuesList.get(i).get(4));

            }
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

    /**
     * 营业报表首页周表
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/business/report_week")
    public String list_week(HttpServletRequest request, Model model) {
        try {
            // 分页条件
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);

            // 搜索条件 0 日表 1 周表 2 月表
            int type = ParamUtils.getIntParameter(request, "type", 0);
            Date date = ParamUtils.getDateParameter(request, "date");
            int custId = ParamUtils.getIntParameter(request, "custId", 0);

            if(date == null) {
                date = new Date();
            }
            // 周表
            Date startDate = getStartWeek(date);
            Date endDate = getEndWeek(date);

            model = getmodel(model, startDate, endDate, type, custId, currentPage);
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            model.addAttribute("date3", sdf.format(startDate));
            model.addAttribute("custId", custId);
            model.addAttribute("type", type);
        } catch(Exception e) {
            logger.debug("AccountAction view is error:" + e.getMessage(), e);
        }
        return "models/hq/account/business_week";
    }

    /**
     * 营业报表首页月表
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/business/report_month")
    public String list_month(HttpServletRequest request, Model model) {
        try {
            // 分页条件
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);

            // 搜索条件
            int type = ParamUtils.getIntParameter(request, "type", 0);
            Date date = ParamUtils.getDateParameter(request, "date");
            int custId = ParamUtils.getIntParameter(request, "custId", 0);

            if(date == null) {
                date = new Date();
            }
            // 月表
            Date startDate = getStartMonth(date);
            Date endDate = getEndMonth(date);

            model = getmodel(model, startDate, endDate, type, custId, currentPage);
            model.addAttribute("date", date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            model.addAttribute("date3", sdf.format(date));
            model.addAttribute("custId", custId);
            model.addAttribute("type", type);
        } catch(Exception e) {
            logger.debug("AccountAction view is error:" + e.getMessage(), e);
        }
        return "models/hq/account/business_month";
    }

    /**
     * 报表导出
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/business/export")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        try {
            String startDate = ParamUtils.getParameter(request, "startDate", "");
            String endDate = ParamUtils.getParameter(request, "endDate", "");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int type = ParamUtils.getIntParameter(request, "type", 0);
            int custId = ParamUtils.getIntParameter(request, "custId", 0);
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            int sysCustId = currentUserInfo.getCustInfo().getSystemId();
            int parentId = currentUserInfo.getCustInfo().getParentId();
            int parentType = currentUserInfo.getCustInfo().getParentType();

            OrderInfoExample example = new OrderInfoExample();
            Criteria criteria = example.createCriteria();
            if(parentType == 1) {// 总店
                criteria.andParentCustIdEqualTo(parentId).andStatusGreaterThan(1);
                if(type > 0) {
                    criteria.andOrderTypeEqualTo(type);
                }
                if(custId > 0) {
                    criteria.andSysCustIdEqualTo(custId);
                }
                if(!"".equals(startDate)) {
                    criteria.andPayTimeGreaterThanOrEqualTo(sdf.parse(startDate));
                }
                if(!"".equals(endDate)) {
                    criteria.andPayTimeLessThanOrEqualTo(sdf.parse(endDate));
                }
            } else {
                criteria.andSysCustIdEqualTo(sysCustId).andStatusGreaterThan(1);
                if(type > 0) {
                    criteria.andOrderTypeEqualTo(type);
                }
                if(!"".equals(startDate)) {
                    criteria.andPayTimeGreaterThanOrEqualTo(sdf.parse(startDate));
                }
                if(!"".equals(endDate)) {
                    criteria.andPayTimeLessThanOrEqualTo(sdf.parse(endDate));
                }
            }
            example.setOrderByClause("pay_time desc");

            List<OrderInfo> orderList = orderInfoService.selectOrdersbyExample(example);

            String[] hellName =
                {"订单号", "分店", "类型", "产品名称", "数量", "会员", "支付时间", "退款时间", "支付金额", "退款金额", "操作人", "营收", "支付方式", "优惠金额", "手机号"};
            // {"订单号", "分店", "类型", "产品名称", "数量", "会员", "时间", "金额", "操作人"};

            String title = "营收报表";
            // 设计表
            // 第一步，创建一个webbook，对应一个Excel文件
            @SuppressWarnings("resource")
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFDataFormat format = wb.createDataFormat();
            HSSFCellStyle style = wb.createCellStyle();
            HSSFFont font = wb.createFont();
            font.setFontName("宋体");
            font.setFontHeightInPoints((short)12);// 设置字体大小
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(title);
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
            for(int i = 0; i < hellName.length; i++) {
                cell = row.createCell(i);
                if(hellName[i].contains("时间")) {
                    cell.setCellValue(hellName[i]);
                    cell.setCellStyle(styledate);
                } else {
                    cell.setCellValue(hellName[i]);
                    cell.setCellStyle(style);
                }
            }
            // 第五步，写入实体数据 实际应用中这些数据从数据库得到
            // String[] hellName ={"订单号", "分店", "类型", "产品名称", "数量", "会员", "时间", "金额", "操作人"};

            for(int i = 0; i < orderList.size(); i++) {
                row = sheet.createRow(i + 1);
                OrderInfo orderInfo = orderList.get(i);
                row.createCell(0).setCellValue(orderInfo.getId() + "");
                row.createCell(1).setCellValue(JSTLFuncs.getCustNameByCustId(orderInfo.getSysCustId() + ""));
                String typeName = "";
                if(orderInfo.getOrderType() == 10) {
                    typeName = "卡";
                } else if(orderInfo.getOrderType() == 11) {
                    typeName = "收费团课";
                } else if(orderInfo.getOrderType() == 12) {
                    typeName = "课程";
                } else {
                    typeName = "私教";
                }
                row.createCell(2).setCellValue(typeName);// 判断
                String cardName = "";
                if(orderInfo.getOrderType() == 10) {
                    cardName = JSTLFuncs.getProductNameByProductId(orderInfo.getProductId() + "");
                } else if(orderInfo.getOrderType() == 11) {
                    cardName = JSTLFuncs.getProductNameByProductId(orderInfo.getProductId() + "");
                } else if(orderInfo.getOrderType() == 12) {
                    cardName = JSTLFuncs.getCourseNameByProductId(orderInfo.getProductId() + "");
                } else {
                    cardName = JSTLFuncs.getCoachNameById(orderInfo.getProductId());
                }
                row.createCell(3).setCellValue(cardName);
                row.createCell(4).setCellValue(orderInfo.getNum() + "");
                if(orderInfo.getLinkMan() != null) {
                    row.createCell(5).setCellValue(orderInfo.getLinkMan() + "");
                } else {
                    row.createCell(5).setCellValue("");
                }
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format2 = sdf1.format(orderInfo.getPayTime());
                row.createCell(6).setCellValue(format2);
                String payType = "";
                if(orderInfo.getPayType() != null) {
                    if(orderInfo.getPayType() == 0) {
                        payType = "网上";
                    } else if(orderInfo.getPayType() == 1) {
                        payType = "线下(现金)";
                    } else if(orderInfo.getPayType() == 2) {
                        payType = "线下(刷卡)";
                    } else if(orderInfo.getPayType() == 3) {
                        payType = "线下(支付宝)";
                    } else if(orderInfo.getPayType() == 4) {
                        payType = "兑换码";
                    }
                }
                row.createCell(12).setCellValue(payType);
                row.createCell(13)
                    .setCellValue(new BigDecimal(orderInfo.getMemPrice() - orderInfo.getSalePrice() * orderInfo.getNum())
                        .divide(new BigDecimal(100)) + "");
                if(orderInfo.getRefundTime() == null) {
                    row.createCell(7).setCellValue("");
                    row.createCell(8).setCellValue(new BigDecimal(orderInfo.getMemPrice()).divide(new BigDecimal(100)) + "");
                    row.createCell(9).setCellValue("");
                    row.createCell(10).setCellValue("");
                    row.createCell(11).setCellValue(new BigDecimal(orderInfo.getMemPrice()).divide(new BigDecimal(100)) + "");
                } else {
                    row.createCell(7).setCellValue(orderInfo.getRefundTime() + "");
                    row.createCell(8).setCellValue(new BigDecimal(orderInfo.getMemPrice()).divide(new BigDecimal(100)) + "");
                    row.createCell(9).setCellValue(orderInfo.getActualMoney() + "");
                    row.createCell(10).setCellValue(new BigDecimal(orderInfo.getActualMoney()).divide(new BigDecimal(100)) + "");
                    int a = orderInfo.getMemPrice();
                    int b = orderInfo.getActualMoney();
                    int c = a - b;
                    row.createCell(11).setCellValue(c / 100 + "");
                }
                row.createCell(14).setCellValue(orderInfo.getLinkPhone());
            }

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
        /*
         * int type = ParamUtils.getIntParameter(request, "type", 0); Date dateExport = ParamUtils.getDateParameter(request,
         * "date"); int custId = ParamUtils.getIntParameter(request, "custId", 0); if(dateExport == null){ dateExport = new Date();
         * } //日表 Date startDate = getStartDate(dateExport); Date endDate = getEndDate(dateExport); HSSFWorkbook wb =
         * getWb(startDate, endDate, type, custId); try { SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss"); String
         * formatdate = date.format(new Date()); // 设置导出报表的文件名和文件类型 response.setHeader("Content-disposition",
         * "attachment; filename=" + new String((formatdate).getBytes("gbk"), "iso8859-1") + ".xls"); OutputStream out =
         * response.getOutputStream(); wb.write(out); out.close(); } catch(Exception e) { e.printStackTrace(); }
         */
    }

    /**
     * 报表导出week
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/business/export_week")
    public void exportWeek(HttpServletRequest request, HttpServletResponse response) {
        int type = ParamUtils.getIntParameter(request, "type", 0);
        Date dateExport = ParamUtils.getDateParameter(request, "date");
        int custId = ParamUtils.getIntParameter(request, "custId", 0);
        if(dateExport == null) {
            dateExport = new Date();
        }
        // 周表
        Date startDate = getStartWeek(dateExport);
        Date endDate = getEndWeek(dateExport);
        HSSFWorkbook wb = getWb(startDate, endDate, type, custId);
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

    /**
     * 报表导出month
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/business/export_month")
    public void exportMonth(HttpServletRequest request, HttpServletResponse response) {
        int type = ParamUtils.getIntParameter(request, "type", 0);
        Date dateExport = ParamUtils.getDateParameter(request, "date");
        int custId = ParamUtils.getIntParameter(request, "custId", 0);
        if(dateExport == null) {
            dateExport = new Date();
        }
        // 月表
        Date startDate = getStartMonth(dateExport);
        Date endDate = getEndMonth(dateExport);
        HSSFWorkbook wb = getWb(startDate, endDate, type, custId);
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

    /**
     * 营业报表公共类
     * 
     * @param startDate
     * @param endDate
     * @param type
     * @param custId
     * @return
     */
    private HSSFWorkbook getWb(Date startDate, Date endDate, int type, int custId) {
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        int sysCustId = currentUserInfo.getCustInfo().getSystemId();
        int parentId = currentUserInfo.getCustInfo().getParentId();
        int parentType = currentUserInfo.getCustInfo().getParentType();

        String sql =
            "SELECT o.`id`,o.`order_type`,o.sys_cust_id,o.pay_type,o.`product_id`,o.`num`,o.`link_man`,o.`pay_time`,o.`refund_time`,o.`refund_user`,o.`mem_price`,o.`actual_money`"
                + "FROM order_info o " + " WHERE 1 = 1";
        if(parentType == 1) {// 总店
            sql += " and o.`parent_cust_id` = " + parentId;
            if(type != 0) {
                sql += " and o.`order_type` = " + type;
            }
            if(custId != 0) {
                sql += " and o.`sys_cust_id` = " + custId;
            }
            if(startDate != null && endDate != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // format.f
                sql += " and ( o.`pay_time` BETWEEN STR_TO_DATE('" + format.format(startDate)
                    + "','%Y-%m-%d %H:%i:%s') AND STR_TO_DATE('" + format.format(endDate) + "','%Y-%m-%d %H:%i:%s') )";
            }
        } else {
            sql += " and o.`sys_cust_id` = " + sysCustId;
            if(type != 0) {
                sql += " and o.`order_type` = " + type;
            }
            if(startDate != null && endDate != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // format.f
                sql += " and ( o.`pay_time` BETWEEN STR_TO_DATE('" + format.format(startDate)
                    + "','%Y-%m-%d %H:%i:%s') AND STR_TO_DATE('" + format.format(endDate) + "','%Y-%m-%d %H:%i:%s') )";
            }
        }
        List<Map<String, Object>> orderList = orderInfoService.getList(sql, new Object[]{});
        int totalMoney = 0;
        if(orderList.size() > 0) {
            for(int i = 0; i < orderList.size(); i++) {
                totalMoney += (int)orderList.get(i).get("mem_price");
            }
        }

        String[] hellName = {"订单号", "分店", "类型", "产品名称", "数量", "会员", "支付时间", "退款时间", "支付金额", "退款金额", "操作人", "营收", "支付方式"};
        // {"订单号", "分店", "类型", "产品名称", "数量", "会员", "时间", "金额", "操作人"};

        String title = "营业报表";
        // 设计表
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFDataFormat format = wb.createDataFormat();
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)12);// 设置字体大小
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(title);
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
        for(int i = 0; i < hellName.length; i++) {
            cell = row.createCell(i);
            if(hellName[i].contains("时间")) {
                cell.setCellValue(hellName[i]);
                cell.setCellStyle(styledate);
            } else {
                cell.setCellValue(hellName[i]);
                cell.setCellStyle(style);
            }
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到
        // String[] hellName ={"订单号", "分店", "类型", "产品名称", "数量", "会员", "时间", "金额", "操作人"};

        for(int i = 0; i < orderList.size(); i++) {
            row = sheet.createRow(i + 1);
            Map<String, Object> map = orderList.get(i);
            row.createCell(0).setCellValue(map.get("id") + "");
            row.createCell(1).setCellValue(JSTLFuncs.getCustNameByCustId(map.get("sys_cust_id") + ""));
            String typeName = "";
            if((int)map.get("order_type") == 10) {
                typeName = "卡";
            } else if((int)map.get("order_type") == 11) {
                typeName = "收费团课";
            } else if((int)map.get("order_type") == 12) {
                typeName = "课程";
            } else {
                typeName = "私教";
            }
            row.createCell(2).setCellValue(typeName);// 判断
            String cardName = "";
            if((int)map.get("order_type") == 10) {
                cardName = JSTLFuncs.getProductNameByProductId(map.get("product_id") + "");
            } else if((int)map.get("order_type") == 11) {
                cardName = JSTLFuncs.getProductNameByProductId(map.get("product_id") + "");
            } else if((int)map.get("order_type") == 12) {
                cardName = JSTLFuncs.getCourseNameByProductId(map.get("product_id") + "");
            } else {
                cardName = JSTLFuncs.getCoachNameById((long)map.get("product_id"));
            }
            row.createCell(3).setCellValue(cardName);
            row.createCell(4).setCellValue(map.get("num") + "");
            if(map.get("link_man") != null) {
                row.createCell(5).setCellValue(map.get("link_man") + "");
            } else {
                row.createCell(5).setCellValue("");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format2 = sdf.format(map.get("pay_time"));
            row.createCell(6).setCellValue(format2);
            String payType = "";
            if(map.get("pay_type") != null) {
                if((int)map.get("pay_type") == 0) {
                    payType = "网上";
                } else if((int)map.get("pay_type") == 1) {
                    payType = "线下(现金)";
                } else if((int)map.get("pay_type") == 2) {
                    payType = "线下(刷卡)";
                } else {
                    payType = "线下(支付宝)";
                }
            }
            row.createCell(12).setCellValue(payType);
            if(map.get("refund_time") == null) {
                row.createCell(7).setCellValue("");
                row.createCell(8).setCellValue(new BigDecimal((int)map.get("mem_price")).divide(new BigDecimal(100)) + "");
                row.createCell(9).setCellValue("");
                row.createCell(10).setCellValue("");
                row.createCell(11).setCellValue(new BigDecimal((int)map.get("mem_price")).divide(new BigDecimal(100)) + "");
            } else {
                row.createCell(7).setCellValue(map.get("refund_time") + "");
                row.createCell(8).setCellValue(new BigDecimal((int)map.get("mem_price")).divide(new BigDecimal(100)) + "");
                row.createCell(9).setCellValue(map.get("actual_money") + "");
                row.createCell(10).setCellValue(new BigDecimal((int)map.get("actual_money")).divide(new BigDecimal(100)) + "");
                int a = (int)map.get("mem_price");
                int b = (int)map.get("actual_money");
                int c = a - b;
                row.createCell(11).setCellValue(c / 100 + "");
            }
        }
        return wb;
    }

    /**
     * 营业报表公共类
     * 
     * @param model
     * @param startDate
     * @param endDate
     * @param type
     * @param custId
     * @param currentPage
     * @return
     */
    private Model getmodel(Model model, Date startDate, Date endDate, int type, int custId, int currentPage) {

        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        int sysCustId = currentUserInfo.getCustInfo().getSystemId();
        int parentId = currentUserInfo.getCustInfo().getParentId();
        int parentType = currentUserInfo.getCustInfo().getParentType();

        OrderInfoExample example = new OrderInfoExample();
        Criteria criteria = example.createCriteria();
        if(parentType == 1) {// 总店
            criteria.andParentCustIdEqualTo(parentId);
            if(type > 0) {
                criteria.andOrderTypeEqualTo(type);
            }
            if(custId > 0) {
                criteria.andSysCustIdEqualTo(custId);
            }
            if(startDate != null && endDate != null) {
                criteria.andPayTimeBetween(startDate, endDate);
            }
        } else {
            criteria.andSysCustIdEqualTo(sysCustId);
            if(type > 0) {
                criteria.andOrderTypeEqualTo(type);
            }
            if(startDate != null && endDate != null) {
                criteria.andPayTimeBetween(startDate, endDate);
            }
        }
        example.setOrderByClause("pay_time desc");

        List<OrderInfo> orderList1 = orderInfoService.selectOrdersbyExample(example);

        int totalMoney = 0;
        int totalRefund = 0;
        if(orderList1 != null && orderList1.size() > 0) {
            for(int i = 0; i < orderList1.size(); i++) {
                totalMoney += (int)orderList1.get(i).getMemPrice();
                if(orderList1.get(i).getRefundMoney() != null && !"".equals(orderList1.get(i).getRefundMoney())) {
                    totalRefund += (int)orderList1.get(i).getRefundMoney();
                    totalMoney = totalMoney - totalRefund;
                }
            }
        }

        int pageCount = orderInfoService.countByExample(example);
        int pageSize = 30;
        int offSet = 5;
        Page pages = new Page();
        pages.setTotal(pageCount);
        pages.setLimit(pageSize);
        pages.setNo(currentPage);

        example.setPage(pages);
        Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

        List<OrderInfo> orderList = orderInfoService.selectOrdersbyExample(example);

        // 搜索类型回显
        String typeName = "";
        if(type == 10) {
            typeName = "卡";
        } else if(type == 11) {
            typeName = "收费团课";
        } else if(type == 12) {
            typeName = "课程";
        } else if(type == 13) {
            typeName = "私教";
        }
        /*
         * String sql =
         * "SELECT o.`id`,o.`order_type`,o.sys_cust_id,o.pay_type,o.`product_id`,o.`num`,o.`link_man`,o.`pay_time`,o.`refund_time`,o.`refund_user`,o.`mem_price`,o.`actual_money`"
         * + "FROM order_info o "+ " WHERE 1 = 1  "; if(parentType == 1){//总店 sql += " and o.`parent_cust_id` = " + parentId;
         * if(type != 0){ sql += " and o.`order_type` = " + type; } if(custId != 0){ sql += " and o.`sys_cust_id` = " + custId; }
         * if(startDate != null && endDate != null){ SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         * //format.f sql += " and ( o.`pay_time` BETWEEN STR_TO_DATE('"+ format.format(startDate) +
         * "','%Y-%m-%d %H:%i:%s') AND STR_TO_DATE('"+ format.format(endDate) +"','%Y-%m-%d %H:%i:%s') )"; } }else{ sql +=
         * " and o.`sys_cust_id` = " + sysCustId; if(type != 0){ sql += " and o.`order_type` = " + type; } if(startDate != null &&
         * endDate != null){ SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //format.f sql +=
         * " and ( o.`pay_time` BETWEEN STR_TO_DATE('"+ format.format(startDate) +"','%Y-%m-%d %H:%i:%s') AND STR_TO_DATE('"+
         * format.format(endDate) +"','%Y-%m-%d %H:%i:%s') )"; } }
         * 
         * sql += " order by o.`pay_time` desc"; List<Map<String, Object>> orderList = orderInfoService.getList(sql,new Object[]{});
         * 
         * int totalMoney = 0; int totalRefund = 0; if(orderList.size() > 0){ for (int i = 0; i < orderList.size(); i++) {
         * totalMoney += (int) orderList.get(i).get("mem_price"); if(orderList.get(i).get("actual_money") != null &&
         * !"".equals(orderList.get(i).get("refund_money"))){ totalRefund += (int)orderList.get(i).get("actual_money"); totalMoney =
         * totalMoney - totalRefund; } } }
         * 
         * //搜索类型回显 String typeName = ""; if(type == 10){ typeName = "卡"; }else if(type == 11){ typeName = "收费团课"; }else if(type ==
         * 12){ typeName = "课程"; }else if(type == 13){ typeName = "私教"; }
         * 
         * String custName =""; if(custId > 0){ String sqlName = "SELECT cust_name FROM cust_info WHERE system_id = ?";
         * List<Map<String, Object>> list = orderInfoService.getList(sqlName,new Object[]{custId}); for (int i = 0; i < list.size();
         * i++) { custName = (String) list.get(i).get("cust_name"); } }
         */
        model.addAttribute("pager", page);
        model.addAttribute("totalRefund", totalRefund);
        model.addAttribute("totalMoney", totalMoney);
        model.addAttribute("typeName", typeName);
        // model.addAttribute("custName", custName);
        model.addAttribute("orderList", orderList);
        model.addAttribute("parentType", parentType);
        return model;
    }

    // 返回当天起始日期
    private Date getStartDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    // 返回当天结束日期
    private Date getEndDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);

        return calendar.getTime();
    }

    // 返回当周起始日期
    private Date getStartWeek(Date date) {
        int mondayPlus = getMondayPlus(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, mondayPlus);
        Date week = getStartDate(calendar.getTime());
        return week;
    }

    // 返回当周结束日期
    private Date getEndWeek(Date date) {

        int mondayPlus = getMondayPlus(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, mondayPlus + 6);
        Date week = getEndDate(calendar.getTime());

        return week;
    }

    // 返回当月起始日期
    private Date getStartMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date month = getStartDate(calendar.getTime());
        return month;
    }

    // 返回当月结束日期
    private Date getEndMonth(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date month = getEndDate(calendar.getTime());

        return month;
    }

    @SuppressWarnings("deprecation")
    private int getMondayPlus(Date date) {
        int day = date.getDay() + 1;
        if(day == 1) {
            return -6;
        } else {
            return 2 - day;
        }
    }

}
