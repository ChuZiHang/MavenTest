
package com.lol.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.lol.common.model.ds0.SmsQueue;
import com.lol.common.model.ds0.SmsQueueExample;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.merchant.OrderSms;
import com.lol.common.model.merchant.OrderSmsExample;
import com.lol.common.model.merchant.ProductSms;
import com.lol.common.model.merchant.ProductSmsExample;
import com.lol.common.model.merchant.SaasMerchantInfo;
import com.lol.common.model.merchant.SaasMerchantInfoExample;
import com.lol.common.plugin.Page;
import com.lol.common.plugin.Pager;
import com.lol.common.plugin.PagerService;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.OrderSmsService;
import com.lol.common.service.ProductSmsService;
import com.lol.common.service.SaasMerchantInfoService;
import com.lol.common.service.SmsQueueService;
import com.lol.common.service.UserLoginService;
import com.lol.common.service.UserProductLogService;
import com.lol.common.util.HttpUtils;
import com.lol.common.util.ParamUtils;
import com.lol.common.util.SequenceUtils;
import com.google.gson.GsonBuilder;

/**
 * 短信管理
 * 
 * @author bowen
 *
 */
@Controller
@RequestMapping("/sms")
public class SmsAction {

    @Resource
    private CustInfoService custInfoService;

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private UserProductLogService userProductLogService;

    @Resource
    private ProductSmsService productSmsService;

    @Resource
    private OrderSmsService orderSmsService;

    @Resource
    private SmsQueueService smsQueueService;
    
    @Resource
    private SaasMerchantInfoService saasMerchantInfoService;

    Logger logger = LoggerFactory.getLogger(SmsAction.class);

    // 首页
    @RequestMapping("/index")
    public String buy(HttpServletRequest request, Model model) {

//        ProductSmsExample example = new ProductSmsExample();
//        ProductSmsExample.Criteria criteria = example.createCriteria();
//        criteria.andStatusEqualTo(0);
//        List<ProductSms> datas = productSmsService.selectByExample(example);
//        model.addAttribute("datas", datas);
        
        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
        long sysCustId = custInfo.getSystemId();
        SaasMerchantInfo saasMerchantInfo = saasMerchantInfoService.selectByPrimaryKey(Integer.parseInt(sysCustId+""));
        model.addAttribute("saasMerchantInfo", saasMerchantInfo);
        return "models/hq/sms/index";
    }

    // 短信购买
    @RequestMapping("/code")
    public void code(HttpServletRequest request, HttpServletResponse response) {

        long productId = ParamUtils.getLongParameter(request, "productId", 0);
        // int type = ParamUtils.getIntParameter(request, "type", 0);

        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
        long sysCustId = custInfo.getSystemId();
        BaseJson res = new BaseJson();

        PrintWriter out = null;
        try {

            ProductSms productSms = productSmsService.selectByPrimaryKey(productId);

            OrderSms record = new OrderSms();
            long orderId = SequenceUtils.nextId();
            record.setId(orderId);
            record.setCustId(sysCustId);
            record.setProductId(productId);
            record.setProductNum(productSms.getCount());
            record.setPayMoney(productSms.getPrice());
            record.setState(0);
            //默认支付宝
            record.setPayType(1);
            orderSmsService.insertSelective(record);

            out = response.getWriter();
            HttpUtils httpUtils = new HttpUtils();
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("orderId", orderId + "");
            String result = "";
            // if(type == 0) {
            // result = httpUtils.get("http://www.lol.com/api/common/smspay/weixinQr.jsp", "utf-8", parameters);
            // } else {
            result = httpUtils.get("http://www.lol.com/api/common/smspay/alipayQr.jsp", "utf-8", parameters);

            HashMap<String,String> hm = new GsonBuilder().create().fromJson(result, HashMap.class);
            hm.put("orderId",orderId+"");
            // }
            if("".equals(result) || result == null) {
                res.setMsg("下单失败");
                res.setStatus(1);
            } else {
                OrderSms orderSms = new OrderSms();
                orderSms.setId(orderId);
                orderSms.setCodeUrl(hm.get("codeUrl"));
                orderSmsService.updateByPrimaryKeySelective(orderSms);
                res.setMsg("下单成功");
                res.setStatus(0);
                res.setData(hm);
            }

        } catch(IOException e1) {
            try {
                out = response.getWriter();
            } catch(IOException e) {
                res.setMsg("下单失败");
                res.setStatus(1);
                e.printStackTrace();
            }
            res.setMsg("下单失败");
            res.setStatus(1);
            e1.printStackTrace();
        }

        // 用Gson转为json
        out.print(new GsonBuilder().create().toJson(res));

    }

    // 付款情况
    @RequestMapping("/ordertype")
    public void ordertype(HttpServletRequest request, HttpServletResponse response) {
        
        BaseJson res = new BaseJson();
        long orderId = ParamUtils.getLongParameter(request,"orderId",0);
        
        PrintWriter out = null;
        try {
           OrderSms orderSms = orderSmsService.selectByPrimaryKey(orderId);
           if(orderSms.getState()==1){
               res.setMsg("付款成功！");
               res.setStatus(0);
           }else{
               res.setMsg("付款失败");
               res.setStatus(1); 
           }
            out = response.getWriter();
        } catch(IOException e) {
            res.setMsg("付款失败");
            res.setStatus(1);
            try {
                out = response.getWriter();
            } catch(IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
       

        // 用Gson转为json
        out.print(new GsonBuilder().create().toJson(res));

    }

    // 购买列表
    @RequestMapping("/orderlist")
    public String orderList(HttpServletRequest request, Model model) {
        int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
        long sysCustId = custInfo.getSystemId();
        
        

        OrderSmsExample example = new OrderSmsExample();
        OrderSmsExample.Criteria criteria = example.createCriteria();
        criteria.andCustIdEqualTo(sysCustId);
        criteria.andStateEqualTo(1);
        
        // -------------分页--------------

        int pageCount = orderSmsService.countByExample(example);
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
        
        List<OrderSms> datas = orderSmsService.selectByExample(example);
        

        model.addAttribute("datas", datas);
        return "models/hq/sms/orderlist";
    }

    // 已发送的短信列表
    @RequestMapping("/smslist")
    public String smsList(HttpServletRequest request, Model model) {
        CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
        long sysCustId = custInfo.getSystemId();
        SmsQueueExample example = new SmsQueueExample();
        SmsQueueExample.Criteria criteria = example.createCriteria();
        criteria.andCustIdEqualTo(sysCustId);
        List<SmsQueue> datas = smsQueueService.selectByExample(example);

        model.addAttribute("datas", datas);

        return "models/hq/sms/smslist";
    }

}