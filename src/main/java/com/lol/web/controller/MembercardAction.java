package com.lol.web.controller;

import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

import com.lol.common.Constants;
import com.lol.common.bean.BaseJson;
import com.lol.common.bean.CurrentUserInfo;
import com.lol.common.bean.LogConstants;
import com.lol.common.bean.MsgBuilder;
import com.lol.common.bean.MsgType;
import com.lol.common.cache.FieldCache;
import com.lol.common.model.ds1.CodeInfo;
import com.lol.common.model.ds1.CodeInfoExample;
import com.lol.common.model.ds1.CodeList;
import com.lol.common.model.ds1.CodeListExample;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;
import com.lol.common.model.ds1.MemberCard;
import com.lol.common.model.ds1.MemberInfo;
import com.lol.common.model.ds1.MemberInfoExample;
import com.lol.common.model.ds1.OrderConsumeLog;
import com.lol.common.model.ds1.OrderInfo;
import com.lol.common.model.ds1.OrderInfoExample;
import com.lol.common.model.ds1.ProductInfo;
import com.lol.common.model.ds1.ProductInfoExample;
import com.lol.common.model.ds1.ProductInfoExample.Criteria;
import com.lol.common.model.ds1.UserLog;
import com.lol.common.model.ds1.UserLogExample;
import com.lol.common.plugin.Page;
import com.lol.common.plugin.Pager;
import com.lol.common.plugin.PagerService;
import com.lol.common.service.CodeService;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.MemberService;
import com.lol.common.service.OrderConsumeLogService;
import com.lol.common.service.OrderInfoService;
import com.lol.common.service.ProductInfoService;
import com.lol.common.service.UserLogService;
import com.lol.common.service.UserLoginService;
import com.lol.common.util.IntUtil;
import com.lol.common.util.ParamUtils;
import com.lol.common.util.SMSUtils;
import com.lol.common.util.SequenceUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

/**
 * 项目首页
 * 
 * @author wenshang
 *
 */
@Controller
@RequestMapping("/membercard")
public class MembercardAction {

    @Resource
    UserLoginService userLoginService;

    @Resource
    ProductInfoService productInfoService;

    @Resource
    OrderInfoService orderInfoService;

    @Resource
    UserLogService userLogService;

    @Resource
    CustInfoService custInfoService;

    @Resource
    CodeService codeService;

    @Resource
    MemberService memberService;

    @Resource
    private OrderConsumeLogService orderConsumeLogService;

    Logger logger = LoggerFactory.getLogger(MembercardAction.class);

    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model) {
        try {
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo custInfo = currentUserInfo.getCustInfo();
            // 查询分店
            CustInfoExample custInfoExample = new CustInfoExample();
            custInfoExample.createCriteria().andParentIdEqualTo(custInfo.getParentId()).andParentTypeEqualTo(2);
            int pageCount = custInfoService.countByExample(custInfoExample);
            int pageSize = 9;
            int offSet = 5;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            custInfoExample.setOrderByClause(" id desc");
            custInfoExample.setPage(pages);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
            List<CustInfo> custInfoList = custInfoService.selectCustInfosByExample(custInfoExample);
            // 查询通用所属会员卡
            ProductInfoExample example = new ProductInfoExample();
            Criteria createCriteria = example.createCriteria();
            createCriteria.andProTypeEqualTo(10).andUseCustIdEqualTo("0").andParentCustIdEqualTo(custInfo.getParentId())
                .andStatusEqualTo(0);
            example.setOrderByClause(" sort  asc,create_time desc");
            List<ProductInfo> beanList = productInfoService.selectByExample(example);
            // 查询分店的卡
            // 组装分店列表下面的卡列表
            HashMap<CustInfo, List<ProductInfo>> hashMap = new LinkedHashMap<CustInfo, List<ProductInfo>>();
            for(int i = 0; i < custInfoList.size(); i++) {
                CustInfo info = custInfoList.get(i);
                ProductInfoExample examplebranch = new ProductInfoExample();
                Criteria criteria = examplebranch.createCriteria();
                criteria.andProTypeEqualTo(10).andUseCustIdNotEqualTo("0").andSysCustIdEqualTo(info.getSystemId())
                    .andStatusEqualTo(0);
                List<ProductInfo> branchList = productInfoService.selectByExample(examplebranch);
                hashMap.put(info, branchList);
            }
            // 查询所有的卡
            ProductInfoExample example1 = new ProductInfoExample();
            Criteria createCriteria1 = example1.createCriteria();
            createCriteria1.andProTypeEqualTo(10).andParentCustIdEqualTo(custInfo.getParentId()).andStatusEqualTo(0);
            List<ProductInfo> cardList = productInfoService.selectByExample(example);

            model.addAttribute("cardList", cardList);
            model.addAttribute("beanList", beanList);
            model.addAttribute("custInfoList", custInfoList);
            model.addAttribute("custInfoCard", hashMap);
            model.addAttribute("pager", page);
        } catch(Exception e) {
            logger.debug("CardAction list is error:" + e.getMessage(), e);
        }
        return "models/hq/card/vipcard/list";
    }

    @RequestMapping("/detail")
    public String detail(HttpServletRequest request, Model model) {
        try {
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo custInfo = currentUserInfo.getCustInfo();
            long id = ParamUtils.getLongParameter(request, "id");
            CustInfoExample custInfoExample = new CustInfoExample();
            custInfoExample.createCriteria().andParentIdEqualTo(custInfo.getParentId()).andParentTypeEqualTo(2);
            List<CustInfo> custInfoList = custInfoService.selectCustInfosByExample(custInfoExample);
            ProductInfo bean = productInfoService.selectByPrimaryKey(id);
            UserLogExample example = new UserLogExample();
            example.createCriteria().andLogTypeEqualTo(0).andObjectIdEqualTo(id);
            List<UserLog> logList = userLogService.selectByExample(example);
            String useCustId = bean.getUseCustId();
            if(!"0".equals(useCustId.trim())) {
                CustInfoExample custInfoEx = new CustInfoExample();
                custInfoEx.createCriteria().andSystemIdEqualTo(Integer.parseInt(useCustId.trim()));
                List<CustInfo> info = custInfoService.selectCustInfosByExample(custInfoEx);
                model.addAttribute("custName", info.get(0).getCustName());
            }
            model.addAttribute("bean", bean);
            model.addAttribute("logList", logList);
            model.addAttribute("custInfoList", custInfoList);
        } catch(Exception e) {
            logger.debug("CardAction detail is error:" + e.getMessage(), e);
        }
        return "models/hq/card/vipcard/detail";
    }

    @RequestMapping("/sell")
    public String sell(HttpServletRequest request, Model model) {
        try {
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
            long id = ParamUtils.getLongParameter(request, "id");
            String startDate = ParamUtils.getParameter(request, "startDate");
            String endDate = ParamUtils.getParameter(request, "endDate");
            int sysCustId = ParamUtils.getIntParameter(request, "sysCustId", 0);
            CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
            int SystemCustId = custInfo.getSystemId();
            int parentCustId = custInfo.getParentId();

            // 总店：1，分店：2
            int parentType = custInfo.getParentType();
            String sqlCust = " and o.parent_cust_id = " + parentCustId;
            if(parentType != 1) {
                sqlCust += " and o.sys_cust_id = " + SystemCustId;
            }
            if(sysCustId > 0 && !sqlCust.contains("sys_cust_id")) {
                sqlCust += " and o.sys_cust_id = " + sysCustId;
            }

            String sqllist =
                "SELECT m.`end_time`,m.`start_time`,m.`is_active`,m.`count` ,o.`crete_time`,o.`link_man`,o.`link_phone`,o.`status`,o.`sys_cust_id`"
                    + " FROM order_info o ,member_card m " + "WHERE o.`product_id`=? " + " AND o.`id`=m.`order_id` "
                    + " AND o.`status` > '1'" + sqlCust;
            String sqlcount = "SELECT count(1) count" + " FROM order_info o ,member_card m " + "WHERE o.`product_id`=? "
                + " AND o.`id`=m.`order_id` " + " AND o.`status` > '1'" + sqlCust;
            if(!"".equals(startDate) && startDate != null && !"".equals(endDate) && endDate != null) {

                sqllist += " AND o.`crete_time` BETWEEN '" + startDate + " :00:00:00' AND '" + endDate + " 23:59:59'";
                sqlcount += " AND o.`crete_time` BETWEEN '" + startDate + " :00:00:00' AND '" + endDate + " 23:59:59'";
            }
            sqllist += " order by o.id desc";
            int pageCount = orderInfoService.getCount(sqlcount, new Object[]{id});
            int pageSize = 10;
            int offSet = 5;
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
            sqllist += " limit ?,?";
            int end = currentPage * pageSize;
            int start = end - pageSize;
            System.out.println("==========sqllist====="+sqllist+"|id="+id+"|start="+start+"|||pageSize="+pageSize);
            
//            System.out.println("==========sqllist====="+id);
//            System.out.println("==========sqllist====="+start);
//            System.out.println("==========sqllist====="+pageSize);
            List<Map<String, Object>> orderList = orderInfoService.getList(sqllist, new Object[]{id, start, pageSize});
            String sql = "";
            // FIXME 修改产品卡的表添加基础数据
            // 已销售
            sql =
                "SELECT COUNT(1) count FROM order_info o ,member_card m WHERE o.product_id=? AND o.`id`=m.`order_id` AND o.status >='2' AND o.status <>'3' "
                    + sqlCust;
            int salesNumbers = orderInfoService.getCount(sql, new Object[]{id});
            // 使用中
            sql =
                "SELECT COUNT(1) count FROM order_info o ,member_card m WHERE o.product_id=?  AND o.`id`=m.`order_id` AND m.end_time > NOW()  AND (o.status='2' or o.status='4') "
                    + sqlCust;
            int useNumbers = orderInfoService.getCount(sql, new Object[]{id});
            // 未使用
            sql =
                "SELECT COUNT(1) count FROM order_info o ,member_card m WHERE o.product_id=? AND o.`id`=m.`order_id` AND  m.`is_active`='0'  AND (o.status='2' or o.status='4')"
                    + sqlCust;
            int unUseNumbers = orderInfoService.getCount(sql, new Object[]{id});
            // 已过期
            sql =
                "SELECT COUNT(1) count FROM  order_info o ,member_card m WHERE o.product_id=? AND o.`id`=m.`order_id` AND  NOW() > m.end_time  AND  m.`is_active`='1' AND (o.status='2' or o.status='4')"
                    + sqlCust;
            int expireNumbers = orderInfoService.getCount(sql, new Object[]{id});
            // 已退款
            sql = "SELECT COUNT(1) count FROM order_info o WHERE o.product_id=? AND o.status='5'" + sqlCust;
            int refundNumbers = orderInfoService.getCount(sql, new Object[]{id});

            CustInfoExample exampleCust = new CustInfoExample();
            exampleCust.createCriteria().andParentIdEqualTo(8).andStatusEqualTo(0);
            List<CustInfo> custInfos = custInfoService.selectCustInfosByExample(exampleCust);

            model.addAttribute("refundNumbers", refundNumbers);
            model.addAttribute("expireNumbers", expireNumbers);
            model.addAttribute("unUseNumbers", unUseNumbers);
            model.addAttribute("useNumbers", useNumbers);
            model.addAttribute("salesNumbers", salesNumbers);
            model.addAttribute("bean", id);
            model.addAttribute("orderList", orderList);
            model.addAttribute("pager", page);
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
            model.addAttribute("sysCustId", sysCustId);
            model.addAttribute("custInfos", custInfos);
        } catch(Exception e) {
            logger.debug("CardAction sell is error:" + e.getMessage(), e);
        }
        return "models/hq/card/vipcard/sell";
    }

    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        HashMap<String, Object> map = new HashMap<>();
        CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
        CustInfo custInfo = currentUserInfo.getCustInfo();
        try {
            String cardName = ParamUtils.getParameter(request, "cardName", "");
            String openImg = ParamUtils.getParameter(request, "openImg", "");
            int status = ParamUtils.getIntParameter(request, "status");
            int saleRule = ParamUtils.getIntParameter(request, "saleRule");
            int days = ParamUtils.getIntParameter(request, "days");
            String fee = ParamUtils.getParameter(request, "fee", "0");
            int isRefund = ParamUtils.getIntParameter(request, "isRefund");
            int refundRule = ParamUtils.getIntParameter(request, "refundRule");
            String refundPrice = ParamUtils.getParameter(request, "refundPrice", "0");
            String proDesc = ParamUtils.getParameter(request, "proDesc", "");
            String salePrice = ParamUtils.getParameter(request, "salePrice", "0");
            int branchId = ParamUtils.getIntParameter(request, "branchId");
            int sort = ParamUtils.getIntParameter(request, "sort");

            // 生成主键id
            long id = SequenceUtils.nextId();
            ProductInfo productInfo = new ProductInfo();
            productInfo.setId(id);
            productInfo.setSignImg(openImg);
            productInfo.setCardName(cardName);
            productInfo.setSalePrice(IntUtil.mul(salePrice, "100"));
            productInfo.setSaleRule(saleRule);
            productInfo.setDays(days);
            productInfo.setFee(IntUtil.mul(fee, "100"));
            productInfo.setIsRefund(isRefund);
            productInfo.setRefundPrice(IntUtil.mul(refundPrice, "100"));
            productInfo.setRefundRule(refundRule);
            productInfo.setProDesc(proDesc);
            productInfo.setCreateTime(new Date());
            productInfo.setProType(10);
            productInfo.setStatus(status);
            productInfo.setParentCustId(custInfo.getParentId());
            productInfo.setSort(sort);
            if(branchId == 0) {
                productInfo.setSysCustId(custInfo.getSystemId());
                productInfo.setUseCustId("0");
            } else {
                productInfo.setSysCustId(branchId);
                productInfo.setUseCustId(branchId + "");
            }

            // 新增一条记录
            UserLog log = new UserLog();
            log.setLogType(LogConstants.LOG_TYPE_PRODUCT);
            log.setUserId(userLoginService.getCurrentUserInfo().getUserLogin().getRealname());
            log.setCreateTime(new Date());
            log.setLogAction(LogConstants.LOG_ACTION_ADD);
            log.setLogMemo(LogConstants.LOG_CARD_ADD);
            log.setObjectId(id);
            log.setSysCustId(custInfo.getSystemId());
            int result = productInfoService.insertSelective(productInfo);
            userLogService.insertSelective(log);
            map.put("result", result);
            response.getWriter().print(gson.toJson(map));
        } catch(Exception e) {
            logger.error("cardAction add is Error:" + e.getMessage(), e);
        }
    }

    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        HashMap<String, Object> map = new HashMap<>();
        try {
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo custInfo = currentUserInfo.getCustInfo();
            String cardName = ParamUtils.getParameter(request, "cardName", "");
            String openImg = ParamUtils.getParameter(request, "openImg", "");
            int status = ParamUtils.getIntParameter(request, "status");
            long id = ParamUtils.getLongParameter(request, "id");
            int saleRule = ParamUtils.getIntParameter(request, "saleRule");
            int days = ParamUtils.getIntParameter(request, "days");
            String fee = ParamUtils.getParameter(request, "fee", "0");
            int isRefund = ParamUtils.getIntParameter(request, "isRefund");
            int refundRule = ParamUtils.getIntParameter(request, "refundRule");
            String refundPrice = ParamUtils.getParameter(request, "refundPrice", "0");
            String proDesc = ParamUtils.getParameter(request, "proDesc", "");
            String salePrice = ParamUtils.getParameter(request, "salePrice", "0");
            int branchId = ParamUtils.getIntParameter(request, "branchId");
            int sort = ParamUtils.getIntParameter(request, "sort");
            ProductInfo productInfo = new ProductInfo();
            productInfo.setId(id);
            productInfo.setCardName(cardName);
            productInfo.setSalePrice(IntUtil.mul(salePrice, "100"));
            productInfo.setSaleRule(saleRule);
            productInfo.setDays(days);
            productInfo.setFee(IntUtil.mul(fee, "100"));
            productInfo.setIsRefund(isRefund);
            productInfo.setRefundPrice(IntUtil.mul(refundPrice, "100"));
            productInfo.setRefundRule(refundRule);
            productInfo.setProDesc(proDesc);
            productInfo.setCreateTime(new Date());
            productInfo.setSignImg(openImg);
            productInfo.setProType(10);
            productInfo.setStatus(status);
            productInfo.setSort(sort);
            if(branchId == 0) {
                productInfo.setSysCustId(custInfo.getSystemId());
                productInfo.setUseCustId("0");
            } else {
                productInfo.setSysCustId(branchId);
                productInfo.setUseCustId(branchId + "");
            }
            // 新增一条记录
            UserLog log = new UserLog();
            log.setLogType(LogConstants.LOG_TYPE_PRODUCT);
            log.setUserId(userLoginService.getCurrentUserInfo().getUserLogin().getRealname());
            log.setCreateTime(new Date());
            log.setLogAction(LogConstants.LOG_ACTION_MODIFY);
            log.setLogMemo(LogConstants.LOG_CARD_MODIFY);
            log.setObjectId(id);
            log.setSysCustId(userLoginService.getCurrentUserInfo().getCustInfo().getSystemId());
            int result = productInfoService.updateByPrimaryKeySelective(productInfo);
            userLogService.insertSelective(log);
            map.put("result", result);
            response.getWriter().print(gson.toJson(map));
        } catch(Exception e) {
            logger.error("cardAction update is Error:" + e.getMessage(), e);
        }
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        HashMap<String, Object> map = new HashMap<>();
        try {
            long id = ParamUtils.getLongParameter(request, "id");
            ProductInfo productInfo = new ProductInfo();
            productInfo.setId(id);
            productInfo.setStatus(2);
            // 新增一条记录
            UserLog log = new UserLog();
            log.setLogType(LogConstants.LOG_TYPE_PRODUCT);
            log.setUserId(userLoginService.getCurrentUserInfo().getUserLogin().getRealname());
            log.setCreateTime(new Date());
            log.setLogAction(LogConstants.LOG_ACTION_DELETE);
            log.setLogMemo(LogConstants.LOG_CARD_DELETE);
            log.setObjectId(id);
            log.setSysCustId(userLoginService.getCurrentUserInfo().getCustInfo().getSystemId());
            int result = productInfoService.updateByPrimaryKeySelective(productInfo);
            userLogService.insertSelective(log);
            map.put("result", result);
            response.getWriter().print(gson.toJson(map));
        } catch(Exception e) {
            logger.error("cardAction delete is Error:" + e.getMessage(), e);
        }
    }

    @RequestMapping("/report")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        String[] hellName = {"会员", "手机号", "购买时间", "状态", "续费状态", "店铺名称"};
        // String date = ParamUtils.getParameter(request, "date","");
        long id = ParamUtils.getLongParameter(request, "id");
        String startDate = ParamUtils.getParameter(request, "startDate", "");
        String endDate = ParamUtils.getParameter(request, "endDate", "");
        int sysCustId = ParamUtils.getIntParameter(request, "sysCustId", 0);
        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
        int SystemCustId = custInfo.getSystemId();
        int parentCustId = custInfo.getParentId();

        // 总店：1，分店：2
        int parentType = custInfo.getParentType();
        String sqlCust = " and o.parent_cust_id = " + parentCustId;
        if(parentType != 1) {
            sqlCust += " and o.sys_cust_id = " + SystemCustId;
        }
        if(sysCustId > 0 && !sqlCust.contains("sys_cust_id")) {
            sqlCust += " and o.sys_cust_id = " + sysCustId;
        }

        String sqllist =
            "SELECT m.`end_time`,m.`start_time`,m.`is_active`,m.`count`  ,o.`crete_time`,o.`link_man`,o.`link_phone`,o.`status`,o.`sys_cust_id`"
                + " FROM order_info o ,member_card m " + "WHERE o.`product_id`=? " + " AND o.`id`=m.`order_id` "
                + " AND o.`status` > '1'" + sqlCust;
        if(!"".equals(startDate) && startDate != null && !"".equals(endDate) && endDate != null) {

            sqllist += " AND o.`crete_time` BETWEEN '" + startDate + " 00:00:00' AND '" + endDate + " 23:59:59'";
        }
        sqllist += " order by o.id desc";
        List<Map<String, Object>> orderList = orderInfoService.getList(sqllist, new Object[]{id});
        HSSFWorkbook wb = exportReport(orderList, hellName, "会员卡售卖表");
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
            logger.error("cardAction report is Error:" + e.getMessage(), e);
        }
    }

    // 兑换码列表
    @RequestMapping("/pcode/list")
    public String pcodeList(HttpServletRequest request, Model model) {
        try {
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
            int codestatus = ParamUtils.getIntParameter(request, "codestatus", -1);
            String codeName = ParamUtils.getParameter(request, "codeName", "");
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo custInfo = currentUserInfo.getCustInfo();
            ProductInfoExample example = new ProductInfoExample();
            Criteria createCriteria = example.createCriteria();
            createCriteria.andProTypeEqualTo(10).andStatusEqualTo(0).andParentCustIdEqualTo(custInfo.getParentId());
            List<ProductInfo> cardList = productInfoService.selectByExample(example);
            CodeListExample exampleCodeList = new CodeListExample();
            com.lol.common.model.ds1.CodeListExample.Criteria criteria = exampleCodeList.createCriteria();
            criteria.andParentIdEqualTo(custInfo.getParentId());
            if(!"".equals(codeName) && codeName != null && codeName.trim().length() > 0) {
                codeName = URLDecoder.decode(codeName, "UTF-8");
                criteria.andCardNameLike("%" + codeName + "%");
            }
            if(codestatus > -1) {
                criteria.andStatusEqualTo(codestatus);
            }
            int pageCount = codeService.countCodeList(exampleCodeList);
            int pageSize = 10;
            int offSet = 5;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            exampleCodeList.setPage(pages);
            exampleCodeList.setOrderByClause(" id desc");
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
            // 查询会员卡兑换码列表
            List<CodeList> branchList = codeService.selectCodeByExample(exampleCodeList);
            model.addAttribute("pager", page);
            model.addAttribute("cardList", cardList);
            model.addAttribute("codeCard", branchList);
            model.addAttribute("codestatus", codestatus);
            model.addAttribute("codeName", codeName);
        } catch(Exception e) {
            logger.debug("CardAction pcodeList is error:" + e.getMessage(), e);
        }
        return "models/hq/card/code/list";
    }

    // 兑换码详情
    @RequestMapping("/pcode/detail")
    public String pcodeDetail(HttpServletRequest request, Model model) {
        try {
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
            int codeId = ParamUtils.getIntParameter(request, "codeId");

            CodeInfoExample codeInfoExample = new CodeInfoExample();
            List<Integer> values = new ArrayList<Integer>();
            values.add(0);
            values.add(2);
            codeInfoExample.createCriteria().andParentIdEqualTo(codeId).andAdStatusEqualTo(0).andStatusIn(values);
            int pageCount = codeService.countCodeInfo(codeInfoExample);
            int pageSize = 10;
            int offSet = 5;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            codeInfoExample.setPage(pages);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

            CodeList code = codeService.selectCodeByPrimaryKey(codeId);
            List<CodeInfo> listCodeInfo = codeService.selectCodeInfoByExample(codeInfoExample);

            model.addAttribute("pager", page);
            model.addAttribute("code", code);
            model.addAttribute("listCodeInfo", listCodeInfo);
        } catch(Exception e) {
            logger.debug("CardAction pcodeList is error:" + e.getMessage(), e);
        }
        return "models/hq/card/code/detail";
    }

    // 生成兑换码
    @RequestMapping("/pcode/add")
    public void pcodeAdd(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        HashMap<String, Object> map = new HashMap<>();
        try {
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo custInfo = currentUserInfo.getCustInfo();
            Date startDate = ParamUtils.getDateParameter(request, "startDate");
            Date endDate = ParamUtils.getDateParameter(request, "endDate");
            int num = ParamUtils.getIntParameter(request, "num");
            int allow = ParamUtils.getIntParameter(request, "allow", 0);
            String cardName = ParamUtils.getParameter(request, "cardName");
            long cardId = ParamUtils.getLongParameter(request, "cardId");
            CodeList codeList = new CodeList();
            codeList.setCreateTime(new Date());
            codeList.setUserId(currentUserInfo.getUserLogin().getId() + "");
            codeList.setStartTime(startDate);
            codeList.setEndTime(endDate);
            codeList.setNum(num);
            codeList.setCardId(cardId);
            codeList.setCardName(cardName);
            codeList.setParentId(custInfo.getParentId());
            codeList.setAllowNum(allow);
            String sql =
                "insert into code_list(create_time,start_time,end_time,num,card_id,card_name,parent_id,allow_num) values (?,?,?,?,?,?,?,?)";
            int id = codeService.insertAndGetKey(sql, codeList);
            int result = 0;
            Random random = new Random();
            for(int i = 0; i < num; i++) {
                StringBuilder code = new StringBuilder();
                code.append(String.format("%05d", id));
                code.append(String.format("%03d", i));
                code.append(String.format("%04d", random.nextInt(9999)));
                CodeInfo codeInfo = new CodeInfo();
                codeInfo.setCode(code.toString());
                codeInfo.setParentId(id);
                codeInfo.setCreateTime(new Date());
                int j = codeService.insertCodeInfo(codeInfo);
                result += j;
            }
            map.put("result", result);
            response.getWriter().print(gson.toJson(map));
        } catch(Exception e) {
            logger.error("cardAction pcodeAdd is Error:" + e.getMessage(), e);
        }
    }

    // 兑换码批次作废
    @RequestMapping("/pcode/listDelete")
    public void pcodeListDelete(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        HashMap<String, Object> map = new HashMap<>();
        try {
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            int codeId = ParamUtils.getIntParameter(request, "codeId");
            CodeList code = codeService.selectCodeByPrimaryKey(codeId);
            code.setId(codeId);
            code.setStatus(1);
            code.setUserId(currentUserInfo.getUserLogin().getId() + "");
            code.setCancelUser(currentUserInfo.getUserLogin().getUsername());
            code.setCancelTime(new Date());
            CodeInfoExample codeInfoExample = new CodeInfoExample();
            codeInfoExample.createCriteria().andParentIdEqualTo(codeId).andStatusEqualTo(0);
            List<CodeInfo> listCodeInfo = codeService.selectCodeInfoByExample(codeInfoExample);
            code.setCancelNum(listCodeInfo.size() + code.getCancelNum());
            int result = codeService.updateCodeByPrimaryKeySelective(code);
            for(int i = 0; i < listCodeInfo.size(); i++) {
                CodeInfo codeInfo = listCodeInfo.get(i);
                if(codeInfo.getStatus() == 0) {
                    codeInfo.setCancelTime(new Date());
                    codeInfo.setUserId(currentUserInfo.getUserLogin().getId() + "");
                    codeInfo.setStatus(1);
                    codeService.updateCodeInfoByPrimaryKeySelective(codeInfo);
                }

            }
            codeService.updateCodeByPrimaryKeySelective(code);
            map.put("result", result);
            response.getWriter().print(gson.toJson(map));
        } catch(Exception e) {
            logger.error("cardAction listdelete is Error:" + e.getMessage(), e);
        }
    }

    // 兑换码单个作废
    @RequestMapping("/pcode/delete")
    public void pcodeDelete(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        HashMap<String, Object> map = new HashMap<>();
        try {
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            int codeInfoId = ParamUtils.getIntParameter(request, "codeInfoId");
            CodeInfo codeInfo = codeService.selectCodeInfoByPrimaryKey(codeInfoId);
            CodeList codeList = codeService.selectCodeByPrimaryKey(codeInfo.getParentId());
            codeList.setCancelNum(codeList.getCancelNum() + 1);
            codeInfo.setCancelTime(new Date());
            codeInfo.setUserId(currentUserInfo.getUserLogin().getId() + "");
            codeInfo.setStatus(1);
            codeService.updateCodeByPrimaryKeySelective(codeList);
            int result = codeService.updateCodeInfoByPrimaryKeySelective(codeInfo);
            map.put("result", result);
            response.getWriter().print(gson.toJson(map));
        } catch(Exception e) {
            logger.error("cardAction delete is Error:" + e.getMessage(), e);
        }
    }

    // 兑换码execel
    @RequestMapping("/pcode/report")
    public void pcodeReport(HttpServletRequest request, HttpServletResponse response) {
        String[] hellName = {"兑换码", "状态"};
        int codeId = ParamUtils.getIntParameter(request, "codeId");
        CodeInfoExample codeInfoExample = new CodeInfoExample();
        codeInfoExample.createCriteria().andParentIdEqualTo(codeId);
        List<CodeInfo> listCodeInfo = codeService.selectCodeInfoByExample(codeInfoExample);
        HSSFWorkbook wb = exportCodeReport(listCodeInfo, hellName, "兑换码详细");
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
            logger.error("cardAction report is Error:" + e.getMessage(), e);
        }
    }

    public HSSFWorkbook exportCodeReport(List<CodeInfo> list, String[] string, String str) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFDataFormat format = wb.createDataFormat();
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)12);// 设置字体大小
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(str);
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
        for(int i = 0; i < string.length; i++) {
            cell = row.createCell(i);
            if(string[i].contains("时间")) {
                cell.setCellValue(string[i]);
                cell.setCellStyle(styledate);
            } else {
                cell.setCellValue(string[i]);
                cell.setCellStyle(style);
            }
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到
        for(int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            CodeInfo codeInfo = list.get(i);
            HSSFCell createCell0 = row.createCell(0);
            createCell0.setCellValue(codeInfo.getCode());
            createCell0.setCellStyle(style);
            String statusName = "";
            if(codeInfo.getStatus() == 0) {
                statusName = "有效";
            } else if(codeInfo.getStatus() == 2) {
                statusName = "已兑换";
            } else {
                statusName = "已作废";
            }
            HSSFCell createCell1 = row.createCell(1);
            createCell1.setCellValue(statusName);
            createCell1.setCellStyle(style);
        }
        return wb;
    }

    public HSSFWorkbook exportReport(List<Map<String, Object>> list, String[] string, String str) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFDataFormat format = wb.createDataFormat();
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)12);// 设置字体大小
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(str);
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
        for(int i = 0; i < string.length; i++) {
            cell = row.createCell(i);
            if(string[i].contains("时间")) {
                cell.setCellValue(string[i]);
                cell.setCellStyle(styledate);
            } else {
                cell.setCellValue(string[i]);
                cell.setCellStyle(style);
            }
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到
        // String[] hellName ={"会员","手机号","购买时间","状态"};
        if(list != null && list.size() > 0) {
            String custName = "";
            FieldCache fieldCache = new FieldCache("ds1");
            for(int i = 0; i < list.size(); i++) {
                row = sheet.createRow(i + 1);
                Map<String, Object> orderInfo = list.get(i);
                HSSFCell createCell0 = row.createCell(0);
                createCell0.setCellValue(orderInfo.get("link_man") + "");
                createCell0.setCellStyle(style);
                HSSFCell createCell1 = row.createCell(1);
                createCell1.setCellValue(orderInfo.get("link_phone") + "");
                createCell1.setCellStyle(style);
                HSSFCell createCell2 = row.createCell(2);
                createCell2.setCellValue((orderInfo.get("crete_time") + "").substring(0, 10));
                createCell2.setCellStyle(styledate);
                String statusName = "";
                Date endtime = (Date)orderInfo.get("end_time");
                if(Integer.parseInt(orderInfo.get("status") + "") == 5) {
                    statusName = "已退款";
                } else if(Integer.parseInt(orderInfo.get("status") + "") == 4) {
                    statusName = "已取消";
                } else if(Integer.parseInt(orderInfo.get("status") + "") == 6) {
                    statusName = "退款申请中";
                } else if(Integer.parseInt(orderInfo.get("is_active") + "") == 0) {
                    statusName = "未使用";
                } else if(new Date().getTime() > endtime.getTime()) {
                    statusName = "已过期";
                } else {
                    statusName = "使用中";
                }
                HSSFCell createCell3 = row.createCell(3);
                createCell3.setCellValue(statusName);
                createCell3.setCellStyle(styledate);
                HSSFCell createCell4 = row.createCell(4);
                int countCard = Integer.parseInt(orderInfo.get("count").toString());
                String countStr = countCard == 0 ? "暂未统计" : (countCard == 1 ? "新会员" : "续费");
                createCell4.setCellValue(countStr);
                createCell4.setCellStyle(style);
                HSSFCell createCell5 = row.createCell(5);

                try {
                    custName = fieldCache.get("cust_info", "system_id:" + orderInfo.get("sys_cust_id") + ":cust_name");
                } catch(Exception e) {
                    logger.debug("CardAction fieldCache is error:" + e.getMessage(), e);
                }

                createCell5.setCellValue(custName);
                createCell5.setCellStyle(style);
            }
        }
        return wb;
    }

    // 线下购卡
    @RequestMapping("/line/create")
    public void lineCreate(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        BaseJson res = new BaseJson();

        try {
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();

            // createType 1.新会员；2.老会员；3.实体卡
            int createType = ParamUtils.getIntParameter(request, "createType", 0);
            String mobile = ParamUtils.getParameter(request, "mobile", "");
            String verCode = ParamUtils.getParameter(request, "verCode", "");
            String userName = ParamUtils.getParameter(request, "name", "");
            long cardId = ParamUtils.getLongParameter(request, "cardType", 0);
            int payType = ParamUtils.getIntParameter(request, "payType", 0);

            CustInfo custInfo = currentUserInfo.getCustInfo();
            int parentCustId = custInfo.getParentId();
            int custId = custInfo.getSystemId();
            String custName = custInfo.getCustName();
            if(createType == 1) {

                // =========== 验证验证码 ===========
                if(SMSUtils.checkCode(mobile, verCode).getStatus() == 1) {

                    long memberId = SequenceUtils.nextId();
                    // 注册新用户
                    MemberInfo info = new MemberInfo();
                    info = new MemberInfo();
                    info.setMemberName(userName);
                    info.setCreateTime(new Date());
                    info.setMemberId(memberId);
                    info.setMemberNick(userName);
                    info.setLinkPhone(mobile);
                    info.setParentCustId(parentCustId);
                    info.setSysCustId(custId);
                    info.setSource(1);
                    memberService.insertSelective(info);

                    // ======= 订单添加记录 ===========
                    // 查询卡的信息
                    ProductInfo productInfo = productInfoService.selectByPrimaryKey(cardId);
                    // 判断卡的售卖规则0 不限制 1 每人一次 2每人两次
                    int saleRule = productInfo.getSaleRule();
                    if(saleRule != 0) {
                        OrderInfoExample orderInfoExample = new OrderInfoExample();
                        orderInfoExample.createCriteria().andStatusEqualTo(2).andLinkPhoneEqualTo(mobile).andOrderTypeEqualTo(10)
                            .andProductIdEqualTo(cardId);
                        List<OrderInfo> orderInfoList = orderInfoService.selectByExample(orderInfoExample);
                        if(orderInfoList.size() >= saleRule) {
                            res.setStatus(3);
                            res.setMsg("超过了购卡规则的限购次数,请选择其他会员卡购买");
                            response.getWriter().print(gson.toJson(res));
                            return;
                        }
                    }

                    long orderId = SequenceUtils.nextId();
                    OrderInfo orderInfo = new OrderInfo();
                    orderInfo.setId(orderId);
                    orderInfo.setOrderCode(SequenceUtils.getCode(orderId) + "");
                    orderInfo.setOrderType(10);
                    orderInfo.setProductId(cardId);
                    orderInfo.setCreteTime(new Date());
                    orderInfo.setStatus(2);// 0新订单 1待支付2.已支付
                    orderInfo.setLinkMan(userName);
                    orderInfo.setSalePrice(productInfo.getSalePrice());
                    orderInfo.setMemPrice(productInfo.getSalePrice());
                    orderInfo.setNum(1);
                    orderInfo.setPayTime(new Date());
                    orderInfo.setParentCustId(parentCustId);
                    if(!"0".equals(productInfo.getUseCustId())) {
                        orderInfo.setSysCustId(productInfo.getSysCustId());
                    } else {
                        orderInfo.setSysCustId(Integer.valueOf(custId));
                    }
                    orderInfo.setLinkPhone(mobile);
                    orderInfo.setMemberId(memberId);
                    orderInfo.setPayType(payType);
                    MsgBuilder msgBuilder = new MsgBuilder();
                    msgBuilder.setType(MsgType.MEMBERCARD);
                    String msg = msgBuilder.create(
                        new Object[]{custName, productInfo.getCardName(), productInfo.getDays(), Constants.SMS_SPORT_PHONE});
                    orderInfo.setSendContent(msg);

                    // 下单
                    orderInfoService.insertSelective(orderInfo);
                    res.setStatus(0);
                    res.setMsg("操作成功");
                    res.setData(orderId);
                    // ========= 订单支付记录 ===========
                    if(res.getStatus() == 0) {
                        CustInfo custInfo1 = new CustInfo();
                        CustInfoExample example = new CustInfoExample();
                        CustInfoExample.Criteria criteria = example.createCriteria();
                        criteria.andSystemIdEqualTo(parentCustId);
                        List<CustInfo> custInfos = custInfoService.selectCustInfosByExample(example);
                        for(CustInfo custinfo: custInfos) {
                            Integer deposit = custinfo.getDeposit();
                            Long id2 = custinfo.getId();
                            custInfo1.setDeposit(deposit + productInfo.getSalePrice());
                            custInfo1.setId(id2);
                            custInfoService.updateByPrimaryKeySelective(custInfo1);
                            OrderConsumeLog consum = new OrderConsumeLog();
                            consum.setOrderId(orderId);
                            consum.setDeposit(deposit);
                            consum.setCreateTime(new Date());
                            consum.setPayMoney(productInfo.getSalePrice());
                            consum.setPayType(0);
                            consum.setParentCustId(parentCustId);
                            consum.setIsOnline(1);
                            orderConsumeLogService.insertSelective(consum);
                        }
                    }
                    // ========= 订单支付记录 ===========
                    // ========= 会员卡表添加记录 ===========
                    MemberCard memberCard = new MemberCard();
                    memberCard.setMemberId(memberId);
                    memberCard.setProductId(cardId);
                    memberCard.setSource(1);
                    ProductInfo card = productInfoService.selectByPrimaryKey(cardId);
                    memberCard.setParentCustId(card.getParentCustId());
                    memberCard.setSysCustId(card.getSysCustId());
                    memberCard.setIsActive(0);
                    memberCard.setOrderId(orderId);
                    memberService.insertCardSelective(memberCard);
                    // ========= 会员表添加记录 ===========

                }

            } else if(createType == 2) {
                // ======= 订单添加记录 ===========
                long memberId = ParamUtils.getLongParameter(request, "memberId", 0);

                // 查询卡的信息
                ProductInfo productInfo = productInfoService.selectByPrimaryKey(cardId);
                // 判断卡的售卖规则0 不限制 1 每人一次 2每人两次
                int saleRule = productInfo.getSaleRule();
                if(saleRule != 0) {
                    OrderInfoExample orderInfoExample = new OrderInfoExample();
                    orderInfoExample.createCriteria().andStatusEqualTo(2).andLinkPhoneEqualTo(mobile).andOrderTypeEqualTo(10)
                        .andProductIdEqualTo(cardId);
                    List<OrderInfo> orderInfoList = orderInfoService.selectByExample(orderInfoExample);
                    if(orderInfoList.size() >= saleRule) {
                        res.setStatus(3);
                        res.setMsg("超过了购卡规则的限购次数,请选择其他会员卡购买");
                        response.getWriter().print(gson.toJson(res));
                        return;
                    }
                }

                long orderId = SequenceUtils.nextId();
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId(orderId);
                orderInfo.setOrderCode(SequenceUtils.getCode(orderId) + "");
                orderInfo.setOrderType(10);
                orderInfo.setProductId(cardId);
                orderInfo.setCreteTime(new Date());
                orderInfo.setStatus(2);// 0新订单 1待支付2.已支付
                orderInfo.setLinkMan(userName);
                orderInfo.setPayTime(new Date());
                orderInfo.setSalePrice(productInfo.getSalePrice());
                orderInfo.setMemPrice(productInfo.getSalePrice());
                orderInfo.setNum(1);
                orderInfo.setParentCustId(parentCustId);
                if(!"0".equals(productInfo.getUseCustId())) {
                    orderInfo.setSysCustId(productInfo.getSysCustId());
                } else {
                    orderInfo.setSysCustId(Integer.valueOf(custId));
                }
                orderInfo.setLinkPhone(mobile);

                orderInfo.setMemberId(memberId);
                orderInfo.setPayType(payType);
                MsgBuilder msgBuilder = new MsgBuilder();
                msgBuilder.setType(MsgType.MEMBERCARD);
                String msg = msgBuilder
                    .create(new Object[]{custName, productInfo.getCardName(), productInfo.getDays(), Constants.SMS_SPORT_PHONE});
                orderInfo.setSendContent(msg);

                // 下单
                orderInfoService.insertSelective(orderInfo);
                res.setStatus(0);
                res.setMsg("操作成功");
                res.setData(orderId);
                // ========= 订单支付记录 ===========
                if(res.getStatus() == 0) {
                    CustInfo custInfo1 = new CustInfo();
                    CustInfoExample example = new CustInfoExample();
                    CustInfoExample.Criteria criteria = example.createCriteria();
                    criteria.andSystemIdEqualTo(parentCustId);
                    List<CustInfo> custInfos = custInfoService.selectCustInfosByExample(example);
                    for(CustInfo custinfo: custInfos) {
                        Integer deposit = custinfo.getDeposit();
                        Long id2 = custinfo.getId();
                        custInfo1.setDeposit(deposit + productInfo.getSalePrice());
                        custInfo1.setId(id2);
                        custInfoService.updateByPrimaryKeySelective(custInfo1);
                        OrderConsumeLog consum = new OrderConsumeLog();
                        consum.setOrderId(orderId);
                        consum.setDeposit(deposit);
                        consum.setCreateTime(new Date());
                        consum.setPayMoney(productInfo.getSalePrice());
                        consum.setPayType(0);
                        consum.setParentCustId(parentCustId);
                        consum.setIsOnline(1);
                        orderConsumeLogService.insertSelective(consum);
                    }
                }
                // ========= 订单支付记录 ===========
                // ========= 会员卡表添加记录 ===========
                MemberCard memberCard = new MemberCard();
                memberCard.setMemberId(memberId);
                memberCard.setProductId(cardId);
                memberCard.setSource(1);
                ProductInfo card = productInfoService.selectByPrimaryKey(cardId);
                memberCard.setParentCustId(card.getParentCustId());
                memberCard.setSysCustId(card.getSysCustId());
                memberCard.setIsActive(0);
                memberCard.setOrderId(orderId);
                memberService.insertCardSelective(memberCard);

            } else if(createType == 3) {
                double dealmoney = ParamUtils.getDoubleParameter(request, "dealmoney", 0);

                // ======= 订单添加记录 ===========
                // 查询卡的信息
                ProductInfo productInfo = productInfoService.selectByPrimaryKey(cardId);

                // System.out.println("---------cardId---------------"+dealmoney);
                // System.out.println("---------productInfo---------------"+cardId);

                long orderId = SequenceUtils.nextId();
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId(orderId);
                orderInfo.setOrderCode(SequenceUtils.getCode(orderId) + "");
                orderInfo.setOrderType(10);
                orderInfo.setProductId(cardId);
                orderInfo.setPayTime(new Date());
                orderInfo.setMemPrice(productInfo.getSalePrice());
                orderInfo.setCreteTime(new Date());
                orderInfo.setStatus(2);// 0新订单 1待支付2.已支付
                // orderInfo.setLinkMan("线下购卡");
                orderInfo.setSalePrice(productInfo.getSalePrice());
                orderInfo.setMemPrice((int)(dealmoney * 100));
                orderInfo.setNum(1);
                orderInfo.setParentCustId(productInfo.getParentCustId());
                if(!"0".equals(productInfo.getUseCustId())) {
                    orderInfo.setSysCustId(productInfo.getSysCustId());
                } else {
                    orderInfo.setSysCustId(Integer.valueOf(custId));
                }
                orderInfo.setStartTime(productInfo.getCreateTime());
                orderInfo.setEndTime(productInfo.getEndTime());
                orderInfo.setPayType(payType);
                MsgBuilder msgBuilder = new MsgBuilder();
                msgBuilder.setType(MsgType.MEMBERCARD);
                String msg = msgBuilder
                    .create(new Object[]{custName, productInfo.getCardName(), productInfo.getDays(), Constants.SMS_SPORT_PHONE});
                orderInfo.setSendContent(msg);

                // 下单
                orderInfoService.insertSelective(orderInfo);
                res.setStatus(0);
                res.setMsg("操作成功");
                res.setData(orderId);
                // ========= 订单支付记录 ===========
                if(res.getStatus() == 0) {
                    CustInfo custInfo1 = new CustInfo();
                    CustInfoExample example = new CustInfoExample();
                    CustInfoExample.Criteria criteria = example.createCriteria();
                    criteria.andSystemIdEqualTo(parentCustId);
                    List<CustInfo> custInfos = custInfoService.selectCustInfosByExample(example);
                    for(CustInfo custinfo: custInfos) {
                        Integer deposit = custinfo.getDeposit();
                        Long id2 = custinfo.getId();
                        custInfo1.setDeposit(deposit + productInfo.getSalePrice());
                        custInfo1.setId(id2);
                        custInfoService.updateByPrimaryKeySelective(custInfo1);
                        OrderConsumeLog consum = new OrderConsumeLog();
                        consum.setOrderId(orderId);
                        consum.setDeposit(deposit);
                        consum.setCreateTime(new Date());
                        consum.setPayMoney(productInfo.getSalePrice());
                        consum.setPayType(0);
                        consum.setParentCustId(parentCustId);
                        consum.setIsOnline(1);
                        orderConsumeLogService.insertSelective(consum);
                    }
                }
                // ========= 订单支付记录 ===========
            }

            response.getWriter().print(gson.toJson(res));
        } catch(Exception e) {
            logger.error("cardAction is Error:" + e.getMessage(), e);
        }
    }

    // 线下购卡获取验证码
    @RequestMapping("/line/vercode")
    public void lineVercode(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        BaseJson res = new BaseJson();
        try {
            String mobile = ParamUtils.getParameter(request, "mobile", "");
            MemberInfoExample example = new MemberInfoExample();
            MemberInfoExample.Criteria criteria = example.createCriteria();
            criteria.andLinkPhoneEqualTo(mobile);
            // 先搜索这个手机号是否为老用户
            List<MemberInfo> memberInfos = memberService.selectMemberByExample(example);
            if(memberInfos != null && memberInfos.size() > 0) {
                // 是则返回已经是老用户，不返回验证码
                res.setStatus(2);
                res.setMsg("此用户已注册！");
            } else {
                // 不是则返回验证码
                if(SMSUtils.sendAuthCode(mobile).getStatus() == 1) {
                    res.setStatus(0);
                    res.setMsg("验证码发送成功！");
                } else {
                    res.setStatus(1);
                    res.setMsg("验证码发送失败！");
                }
            }

            response.getWriter().print(gson.toJson(res));
        } catch(Exception e) {
            logger.error("cardAction is Error:" + e.getMessage(), e);
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

    // 卡列表
    @RequestMapping("/line/cardlist")
    public void lineCardList(HttpServletRequest request, HttpServletResponse response) {

        Gson gson = new GsonBuilder().create();
        BaseJson res = new BaseJson();
        try {
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo custInfo = currentUserInfo.getCustInfo();
            int custId = custInfo.getSystemId();

            ProductInfoExample example = new ProductInfoExample();
            ProductInfoExample.Criteria criteria = example.createCriteria();
            criteria.andSysCustIdEqualTo(custId);
            criteria.andStatusEqualTo(10);
            criteria.andStatusEqualTo(0);
            List<ProductInfo> productInfos = productInfoService.selectByExample(example);
            if(productInfos != null && productInfos.size() > 0) {
                res.setStatus(1);
                res.setData(productInfos);
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

    // 删除作废兑换码
    @RequestMapping("/pcode/del")
    public void del(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().create();
        BaseJson res = new BaseJson();
        try {
            int codeId = ParamUtils.getIntParameter(request, "codeId");
            CodeInfoExample example = new CodeInfoExample();
            CodeInfoExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(1).andParentIdEqualTo(codeId).andAdStatusEqualTo(0);
            List<CodeInfo> List = codeService.selectCodeInfoByExample(example);
            if(List.size() > 0 && List != null) {
                for(CodeInfo code: List) {
                    Integer id = code.getId();
                    CodeInfo codeInfo = codeService.selectCodeInfoByPrimaryKey(id);
                    codeInfo.setAdStatus(1);
                    codeService.updateCodeInfoByPrimaryKeySelective(codeInfo);
                    res.setStatus(1);
                    res.setMsg("修改成功");
                }
            } else {
                res.setStatus(0);
                res.setMsg("修改失败");
            }
            response.getWriter().print(gson.toJson(res));
        } catch(Exception e) {
            logger.error("cardAction delete is Error:" + e.getMessage(), e);
        }
    }
}