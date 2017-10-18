package com.lol.web.controller.api;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lol.common.Constants;
import com.lol.common.bean.BaseJson;
import com.lol.common.cache.FieldCache;
import com.lol.common.cache.FieldCacheManager;
import com.lol.common.cache.TableConstans;
import com.lol.common.model.ds1.AppInfo;
import com.lol.common.model.ds1.OrderInfo;
import com.lol.common.model.ds1.PayBalanceWithBLOBs;
import com.lol.common.service.AppInfoService;
import com.lol.common.service.OrderInfoService;
import com.lol.common.service.PayBalanceService;
import com.lol.common.util.ParamUtils;
import com.lol.common.util.SequenceUtils;
import com.lol.common.util.SignUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/pay")
public class PayAction {

    Logger logger = LoggerFactory.getLogger(PayAction.class);

    @Resource
    PayBalanceService payBalanceService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private AppInfoService appInfoService;

    @RequestMapping("/submit")
    public void submit(HttpServletRequest request, HttpServletResponse response) {
        long orderId = ParamUtils.getLongParameter(request, "orderId");
        int appId = ParamUtils.getIntParameter(request, "appId");
        String timestamp = ParamUtils.getParameter(request, "timestamp");
        String sign = ParamUtils.getParameter(request, "sign");

        Gson gson = new GsonBuilder().create();
        BaseJson baseJson = new BaseJson();
        baseJson.setStatus(1);
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("orderId", orderId + "");
            params.put("appId", appId + "");
            params.put("timestamp", timestamp);

            AppInfo appInfo = appInfoService.selectByPrimaryKey(appId);
            String verifySign = SignUtils.NMSign(params, appInfo.getAppToken());
            if(sign.equals(verifySign)) {
                OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderId);

                FieldCache fieldCache = FieldCacheManager.createCache(Constants.DS1);

                long custId = orderInfo.getParentCustId();
                String memberId = String.valueOf(orderInfo.getMemberId());
                String memberName = fieldCache.get(TableConstans.MEMBER_INFO, "member_id:" + memberId + ":member_name");
                long serviceId = 11004L;
                String productOrderId = String.valueOf(orderId);
                long orderAmount = orderInfo.getMemPrice();
                
                int productNum = orderInfo.getNum();
                long productType = orderInfo.getOrderType();
                String extendParam = orderInfo.getProductId() + "";// 暂时传产品ID
                
                String productName = "";
                if(productType == 13){
                    productName = fieldCache.get(TableConstans.COACH_INFO, "id:'" + orderInfo.getProductId() + "':name");// 类型-产品ID
                }else{
                    productName = fieldCache.get(TableConstans.PRODUCT_INFO, "id:" + orderInfo.getProductId() + ":card_name");// 类型-产品ID
                }

                // 支付记录
                PayBalanceWithBLOBs payBalance = new PayBalanceWithBLOBs();
                payBalance.setId(SequenceUtils.nextId());
                payBalance.setParentId(0l);

                payBalance.setPayServiceAccount("weixin@lol.com");// 暂时动网
                payBalance.setPayServiceId(serviceId);
                payBalance.setProductOrderId(productOrderId);
                payBalance.setProductName(productName);
                payBalance.setProductNum(productNum);

                // 此处计算需要向支付宝支付的金额
                double tax = 0;
                tax = orderAmount * 6 / 1000.d;
                tax = Math.ceil(tax);
                payBalance.setPayTax(tax);

                // 收款方
                payBalance.setCustId(custId);
                // 付款方
                payBalance.setMemberId(memberId);
                payBalance.setMemberName(memberName);
                payBalance.setSubmitDate(new Date());
                payBalance.setPayAmount(orderAmount);
                payBalance.setPayedAmount(0l);
                payBalance.setPayTax(tax);
                payBalance.setPayStatus(0);
                payBalance.setInvoicedAmount(0l);
                payBalance.setServiceInvoicedAmount(0l);
                payBalance.setExtendParam(extendParam);
                payBalance.setProductType(productType);
                int num = payBalanceService.insertSelective(payBalance);
                if(num == 1) {
                    baseJson.setStatus(0);
                    baseJson.setData(payBalance);
                } else {
                    baseJson.setMsg("支付单创建失败");
                }
            } else {
                baseJson.setMsg("签名错误");
            }

        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            baseJson.setMsg("系统异常");
        }
        try {
            response.getWriter().print(gson.toJson(baseJson));
        } catch(IOException e) {
            logger.error(e.getMessage(), e);
        }

    }

}
