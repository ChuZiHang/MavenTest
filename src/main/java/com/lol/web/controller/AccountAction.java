package com.lol.web.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lol.common.bean.CurrentUserInfo;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.OrderInfo;
import com.lol.common.model.ds1.OrderInfoExample;
import com.lol.common.model.ds1.PayDrawmoney;
import com.lol.common.model.ds1.PayDrawmoneyExample;
import com.lol.common.model.ds1.PayDrawmoneyExample.Criteria;
import com.lol.common.plugin.Page;
import com.lol.common.plugin.Pager;
import com.lol.common.plugin.PagerService;
import com.lol.common.service.AccountService;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.OrderInfoService;
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
@RequestMapping("/account")
public class AccountAction {

    @Resource
    UserLoginService userLoginService;

    @Resource
    CustInfoService custInfoService;

    @Resource
    AccountService accountService;

    @Resource
    OrderInfoService orderInfoService;

    @Resource
    JdbcTemplate jdbcTemplateForDs0;

    @Resource
    private DataSourceTransactionManager transactionManagerDs1;

    Logger logger = LoggerFactory.getLogger(AccountAction.class);

    @RequestMapping("/settlement/view")
    public String list(HttpServletRequest request, Model model) {
        try {
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo custInfo = currentUserInfo.getCustInfo();
            PayDrawmoneyExample example = new PayDrawmoneyExample();

            int pageCount = accountService.countByExample(example);
            int pageSize = 6;
            int offSet = 3;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);

            example.setPage(pages);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

            List<PayDrawmoney> beanList = accountService.selectByExample(example);

            // 查询银行
            String sql = "select bank_type from info_bank group by bank_type";
            List<Map<String, Object>> bankList = accountService.getList(sql, new Object[]{});
            model.addAttribute("pager", page);
            model.addAttribute("beanList", beanList);
            model.addAttribute("custInfo", custInfo);
            model.addAttribute("bankList", bankList);
        } catch(Exception e) {
            logger.debug("AccountAction view is error:" + e.getMessage(), e);
        }
        return "models/hq/account/cash";
    }

    // 绑定支付宝
    @RequestMapping("/settlement/alipay")
    public void detail(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        HashMap<String, Object> map = new HashMap<>();
        try {
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo custInfo = currentUserInfo.getCustInfo();
            String alipay = ParamUtils.getParameter(request, "alipay", "");
            String payName = ParamUtils.getParameter(request, "payName", "");
            if("".equals(payName) || "".equals(alipay)) {
                map.put("result", 0);
            } else {
                custInfo.setAlipayAccount(alipay);
                custInfo.setAlipayName(payName);
                int result = custInfoService.updateByPrimaryKeySelective(custInfo);
                map.put("result", result);
            }
            response.getWriter().print(gson.toJson(map));
        } catch(Exception e) {
            logger.debug("AccountAction alipay is error:" + e.getMessage(), e);
        }
    }

    // 查询省市
    @RequestMapping("/settlement/province")
    public void province(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        try {
            String bankType = ParamUtils.getParameter(request, "bankType", "");
            String sql = "select distinct province as province from info_bank where bank_type=? order by province";
            List<Map<String, Object>> list = accountService.getList(sql, new Object[]{bankType});
            response.getWriter().print(gson.toJson(list));
        } catch(Exception e) {
            logger.error("AccountAction bank is Error:" + e.getMessage(), e);
        }
    }

    // 查询城市
    @RequestMapping("/settlement/city")
    public void city(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        try {
            String bankType = ParamUtils.getParameter(request, "bankType", "");
            String bankProvince = ParamUtils.getParameter(request, "bankProvince", "");
            String sql = "select distinct city from info_bank where bank_type=? and province=? order by city";
            List<Map<String, Object>> list = accountService.getList(sql, new Object[]{bankType, bankProvince});
            response.getWriter().print(gson.toJson(list));
        } catch(Exception e) {
            logger.error("AccountAction bank is Error:" + e.getMessage(), e);
        }
    }

    // 查询银行名称
    @RequestMapping("/settlement/bankname")
    public void bankname(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        try {
            String bankType = ParamUtils.getParameter(request, "bankType", "");
            String bankCity = ParamUtils.getParameter(request, "bankCity", "");
            String sql = "select distinct bank_name from info_bank  where bank_type=? and city=? order by bank_name";
            List<Map<String, Object>> list = accountService.getList(sql, new Object[]{bankType, bankCity});
            response.getWriter().print(gson.toJson(list));
        } catch(Exception e) {
            logger.error("AccountAction bank is Error:" + e.getMessage(), e);
        }
    }

    // 绑定银行卡
    @RequestMapping("/settlement/bindbank")
    public void add(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        HashMap<String, Object> map = new HashMap<>();
        try {
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo custInfo = currentUserInfo.getCustInfo();
            String banktext = ParamUtils.getParameter(request, "banktext", "");
            String cityText = ParamUtils.getParameter(request, "cityText", "");
            String bankProvince = ParamUtils.getParameter(request, "bankProvince", "");
            String banknameText = ParamUtils.getParameter(request, "banknameText", "");
            String banknumber = ParamUtils.getParameter(request, "banknumber", "");
            String bankmanname = ParamUtils.getParameter(request, "bankmanname", "");
            custInfo.setBankType(banktext);
            custInfo.setBankProvince(bankProvince);
            custInfo.setBankCity(cityText);
            custInfo.setBankAccount(banknumber);
            custInfo.setBankName(banknameText);
            custInfo.setBankAcname(bankmanname);
            int result = custInfoService.updateByPrimaryKeySelective(custInfo);
            map.put("result", result);
            response.getWriter().print(gson.toJson(map));
        } catch(Exception e) {
            logger.error("AccountAction bank is Error:" + e.getMessage(), e);
        }
    }

    // 提现
    @RequestMapping("/settlement/cash")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        HashMap<String, Object> map = new HashMap<>();
        try {
            TransactionStatus transactionStatus = null;
            TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo custInfo = currentUserInfo.getCustInfo();
            // 提现金额
            String money = ParamUtils.getParameter(request, "money", "");
            // 提现方式 0支付宝 1银行卡
            int payType = ParamUtils.getIntParameter(request, "paytype");
            // 转换分结算
            int signum = new BigDecimal(money).multiply(new BigDecimal("100")).intValue();
            if(signum > custInfo.getDeposit()) {
                map.put("result", 2);
                response.getWriter().print(gson.toJson(map));
                return;
            }
            // 插入一条提现记录
            PayDrawmoney payDrawmoney = new PayDrawmoney();
            payDrawmoney.setCurBalance(custInfo.getDeposit());
            payDrawmoney.setMoney(signum);
            payDrawmoney.setCreateDate(new Date());
            payDrawmoney.setCustId(custInfo.getId());
            payDrawmoney.setUserId(currentUserInfo.getUserLogin().getRealname());
            payDrawmoney.setActualMoney(signum);
            if(payType == 0) {
                payDrawmoney.setAccount(custInfo.getAlipayAccount());
                payDrawmoney.setAccountName(custInfo.getAlipayName());
                payDrawmoney.setPayType(10002);
            } else {
                payDrawmoney.setAccount(custInfo.getBankAccount());
                payDrawmoney.setAccountName(custInfo.getBankAcname());
                payDrawmoney.setPayType(10004);
                payDrawmoney.setPayFee(custInfo.getGatFee());
                payDrawmoney.setBankName(custInfo.getBankName());
                payDrawmoney.setBankCity(custInfo.getBankCity());
                payDrawmoney.setBankType(custInfo.getBankType());
                payDrawmoney.setBankProvince(custInfo.getBankProvince());
            }
            payDrawmoney.setStatus(0);
            // 更新账户金额
            custInfo.setDeposit(new BigDecimal(custInfo.getDeposit()).subtract(new BigDecimal(signum)).intValue());
            // 开启事务保持数据操作一致性
            transactionStatus = transactionManagerDs1.getTransaction(transactionDefinition);
            custInfoService.updateByPrimaryKeySelective(custInfo);
            int result = accountService.insertSelective(payDrawmoney);
            transactionManagerDs1.commit(transactionStatus);
            map.put("result", result);
            response.getWriter().print(gson.toJson(map));
        } catch(Exception e) {
            logger.error("AccountAction cash is Error:" + e.getMessage(), e);
        }
    }

    // 提款报表
    @RequestMapping("/settlement/report")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        String[] hellName = {"提现人", "提现时间", "提现账户", "提现金额", "提现前余额", "手续费率", "手续费", "提现后余额", "提现状态"};
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        CustInfo custInfo = currentUserInfo.getCustInfo();
        PayDrawmoneyExample example = new PayDrawmoneyExample();
        Criteria createCriteria = example.createCriteria();
        createCriteria.andCustIdEqualTo(custInfo.getId());
        example.setOrderByClause("id desc");
        List<PayDrawmoney> beanList = accountService.selectByExample(example);
        HSSFWorkbook wb = exportReport(beanList, hellName, "提款报表");
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
            logger.error("AccountAction report is Error:" + e.getMessage(), e);
        }
    }

    public HSSFWorkbook exportReport(List<PayDrawmoney> list, String[] hellName, String str) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFDataFormat format = wb.createDataFormat();
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        /*font.setFontName("宋体");*/
       /* font.setFontHeightInPoints((short)12);// 设置字体大小
*/        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(str);
        sheet.setDefaultColumnWidth(20);
        sheet.setColumnWidth(2, 55 * 256);
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
        // String[] hellName ={"会员","手机号","购买时间","状态"};

        for(int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            PayDrawmoney payDrawmoney = list.get(i);
            HSSFCell createCell0 = row.createCell(0);
            createCell0.setCellValue(payDrawmoney.getUserId());
            createCell0.setCellStyle(style);
            HSSFCell createCell1 = row.createCell(1);
            createCell1.setCellValue(payDrawmoney.getCreateDate());
            createCell1.setCellStyle(styledate);
            String account = "";
            if(payDrawmoney.getPayType() == 10004) {
                account = payDrawmoney.getBankName() + "账户:" + payDrawmoney.getAccount();
            } else {
                account = "支付宝账户:" + payDrawmoney.getAccount();

            }
            HSSFCell createCell2 = row.createCell(2);
            createCell2.setCellValue(account);
            createCell2.setCellStyle(style);
            HSSFCell createCell3 = row.createCell(3);
            createCell3.setCellValue(new BigDecimal(payDrawmoney.getMoney()).divide(new BigDecimal(100)) + "元");
            createCell3.setCellStyle(style);
            HSSFCell createCell4 = row.createCell(4);
            createCell4.setCellValue(new BigDecimal(payDrawmoney.getCurBalance()).divide(new BigDecimal(100)) + "元");
            createCell4.setCellStyle(style);
            HSSFCell createCell5 = row.createCell(5);
            BigDecimal divide = new BigDecimal(payDrawmoney.getPayFee()).divide(new BigDecimal(payDrawmoney.getMoney()));
            String plainString = divide.multiply(new BigDecimal(100)).toPlainString();
            createCell5.setCellValue(plainString + "%");
            createCell5.setCellStyle(style);
            HSSFCell createCell6 = row.createCell(6);
            createCell6.setCellValue(payDrawmoney.getPayFee() + "元");
            createCell6.setCellStyle(style);
            HSSFCell createCell7 = row.createCell(7);
            BigDecimal subtract = new BigDecimal(payDrawmoney.getCurBalance()).subtract(new BigDecimal(payDrawmoney.getMoney()))
                .subtract(new BigDecimal(payDrawmoney.getPayFee())).divide(new BigDecimal(100));
            createCell7.setCellValue(subtract + "元");
            createCell7.setCellStyle(style);
            String statusName = "";
            if(payDrawmoney.getStatus() == 1) {
                statusName = "已成功";
            } else if(payDrawmoney.getStatus() == 2) {
                statusName = "已退回";
            } else {
                statusName = "申请中";
            }
            HSSFCell createCell8 = row.createCell(8);
            createCell8.setCellValue(statusName);
            createCell8.setCellStyle(style);
        }
        return wb;
    }

    // 退款报表
    @RequestMapping("/refund/report")
    public String refundReport(HttpServletRequest request, Model model) {
        try {

            String selectsql = "select system_id,cust_name from cust_info   where parent_type=2 order by id";
            List<Map<String, Object>> custList = orderInfoService.getList(selectsql, new Object[]{});
            int type = ParamUtils.getIntParameter(request, "type", -1);
            int sysCustId = ParamUtils.getIntParameter(request, "sysCustId");
            String custName = ParamUtils.getParameter(request, "custName", "");
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
            OrderInfoExample example = new OrderInfoExample();
            OrderInfoExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(5);
            if(type > -1) {
                criteria.andOrderTypeEqualTo(type);
            }
            if(sysCustId > 0) {
                criteria.andSysCustIdEqualTo(sysCustId);
            }

            int pageCount = orderInfoService.countByExample(example);
            int pageSize = 8;
            int offSet = 4;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            example.setPage(pages);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
            List<OrderInfo> reFund = orderInfoService.selectByExample(example);

            model.addAttribute("pager", page);
            model.addAttribute("reFund", reFund);
            model.addAttribute("custList", custList);
            model.addAttribute("custName", custName);
            model.addAttribute("type", type);
            model.addAttribute("sysCustId", sysCustId);
        } catch(Exception e) {
            logger.error("AccountAction report is Error:" + e.getMessage(), e);
        }
        return "models/hq/account/refund";
    }

}
