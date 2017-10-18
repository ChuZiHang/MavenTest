package com.lol.web.controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lol.common.bean.BaseJson;
import com.lol.common.cache.FieldCache;
import com.lol.common.model.ds1.AppInfo;
import com.lol.common.model.ds1.OrderFinishLog;
import com.lol.common.model.ds1.OrderInfo;
import com.lol.common.model.ds1.OrderInfoExample;
import com.lol.common.service.AppInfoService;
import com.lol.common.service.CoachInfoService;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.MemberService;
import com.lol.common.service.OrderFinishLogService;
import com.lol.common.service.OrderInfoService;
import com.lol.common.util.AES;
import com.lol.common.util.ParamUtils;
import com.lol.common.util.SignUtils;
import com.google.gson.GsonBuilder;

/**
 * 
 * 
 * @author bowen
 *
 */
@Controller
@RequestMapping("/api/order")
public class AppOrderAction {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private AppInfoService appInfoService;

    @Resource
    private CoachInfoService coachInfoService;

    @Resource
    private OrderFinishLogService orderFinishLogService;

    @Resource
    private MemberService memberService;

    @Resource
    private CustInfoService custInfoService;

    // 获取订单详情
    @RequestMapping("/detail")
    public void orderDetail(HttpServletRequest request, HttpServletResponse response) {
        // System.out.println("-----------------------token-----------------------------");

        String sign = ParamUtils.getParameter(request, "sign", "");
        String orderCode = ParamUtils.getParameter(request, "orderCode", "");
        String timeStamp = ParamUtils.getParameter(request, "timeStamp", "");
        int appId = ParamUtils.getIntParameter(request, "appId", 0);
        
//        System.out.println("-------sign----------"+sign);
//        System.out.println("-------sign----------"+orderCode);
//        System.out.println("-------sign----------"+timeStamp);
//        System.out.println("-------sign----------"+appId);
        

        AppInfo appInfo = appInfoService.selectByPrimaryKey(appId);
        String token = "";
        if(appInfo != null) {
            token = appInfo.getAppToken();
        }

        BaseJson res = new BaseJson();
        PrintWriter out = null;
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("orderCode", orderCode);
            params.put("timeStamp", timeStamp);
            params.put("appId", appId + "");
            String NMsign = SignUtils.NMSign(params, token);
            //System.out.println("-------sign----------"+NMsign);
            if(sign.equals(NMsign)) {
                AES aes = new AES(AES.AESKEY, AES.AesPublicKey);
                try {
                    orderCode = URLDecoder.decode(orderCode, "utf-8");
                } catch(UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String code = aes.decrypt(orderCode);
                OrderInfoExample example = new OrderInfoExample();
                OrderInfoExample.Criteria criteria = example.createCriteria();
                criteria.andOrderCodeEqualTo(code);
                criteria.andStatusEqualTo(2);
                List<OrderInfo> orderInfos = orderInfoService.selectByExample(example);
                OrderInfo orderInfo = new OrderInfo();
                if(orderInfos != null && orderInfos.size() > 0 && orderInfos.get(0).getRemainCount() != null
                    && orderInfos.get(0).getRemainCount() > 0) {
                    orderInfo = orderInfos.get(0);
                    Map<String, String> resMap = new HashMap<String, String>();

                    FieldCache fieldCache = new FieldCache("ds1");
                    resMap.put("orderType", orderInfo.getOrderType() + "");
                    resMap.put("orderId", orderInfo.getId() + "");
                    // 用户昵称
                    resMap.put("memberNick", memberService.selectByPrimaryKey(orderInfo.getMemberId()).getMemberNick());
                    // 分店名
                    resMap.put("custName", fieldCache.get("cust_info", "system_id:" + orderInfo.getSysCustId() + ":cust_name"));
                    // 课程名
                    String proudctId = "";
                    // String coachId = "";
                    if(orderInfo.getOrderType() == 12) {
                        proudctId = fieldCache.get("course_price", "id:" + orderInfo.getProductId() + ":course_id");
                        // coachId = fieldCache.get("course_price", "id:" + orderInfo.getProductId() + ":coach_id");
                        resMap.put("productName", fieldCache.get("course_info", "id:" + proudctId + ":class_name"));
                    } else if(orderInfo.getOrderType() == 13) {
                        // coachId = orderInfo.getProductId() + "";
                        resMap.put("coachName", coachInfoService.selectByPrimaryKey(orderInfo.getProductId()).getName());
                    } else {
                        // coachId = fieldCache.get("product_info", "id:" + proudctId + ":coach_id");
                        resMap.put("productName", fieldCache.get("product_info", "id:" + proudctId + ":card_name"));
                    }

                    if(orderInfo.getOrderType() != 13) {
                        // 开始时间
                        resMap.put("startTime", orderInfo.getStartTime().getTime() + "");
                        // 结束时间
                        resMap.put("endTime", orderInfo.getEndTime().getTime() + "");
                    }

                    // 订单次数
                    resMap.put("orderCount", orderInfo.getOrderCount() + "");
                    // 剩余次数
                    resMap.put("remainCount", orderInfo.getRemainCount() + "");
                    // 教练
                    // if(orderInfo.getOrderType() == 11) {
                    //
                    // }

                    res.setData(resMap);

                    res.setMsg("获取数据成功");
                    res.setStatus(1);
                } else {
                    res.setMsg("无效验证码");
                    res.setStatus(0);
                }
            } else {
                res.setMsg("数据错误");
                res.setStatus(0);
            }

            out = response.getWriter();

        } catch(Exception e1) {
            try {
                out = response.getWriter();
            } catch(IOException e) {
                e.printStackTrace();
            }
            res.setMsg("数据错误！");
            res.setStatus(0);
            e1.printStackTrace();

        }

        // 用Gson转为json

        out.print(new GsonBuilder().create().toJson(res));
    }

    // 验证
    @RequestMapping("/update")
    public void updateDetail(HttpServletRequest request, HttpServletResponse response) {

        String sign = ParamUtils.getParameter(request, "sign", "");
        long orderId = ParamUtils.getLongParameter(request, "orderId", 0);
        String timeStamp = ParamUtils.getParameter(request, "timeStamp", "");
        String userLoginId = ParamUtils.getParameter(request, "userLoginId", "");
        int appId = ParamUtils.getIntParameter(request, "appId", 0);
        AppInfo appInfo = appInfoService.selectByPrimaryKey(appId);
        String token = "";
        if(appInfo != null) {
            token = appInfo.getAppToken();
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("orderId", orderId + "");
        params.put("timeStamp", timeStamp);
        params.put("userLoginId", userLoginId);
        params.put("appId", appId + "");
        params.put("token", token);
        String NMsign = SignUtils.NMSign(params, token);

        BaseJson res = new BaseJson();
        res.setStatus(0);
        res.setMsg("验证失败");

        PrintWriter out = null;

        try {
            out = response.getWriter();

            if(sign.equals(NMsign)) {

                OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderId);
                if(orderId > 0 && orderInfo.getRemainCount() != null && (orderInfo.getRemainCount() - 1) >= 0) {

                    if((orderInfo.getRemainCount() - 1) <= 0) {
                        orderInfo.setStatus(4);
                        orderInfo.setFinishTime(new Date());
                    }
                    orderInfo.setRemainCount(orderInfo.getRemainCount() - 1);

                    orderInfoService.updateByPrimaryKeySelective(orderInfo);

                    OrderFinishLog orderFinishLog = new OrderFinishLog();

                    orderFinishLog.setFinishUser(userLoginId);
                    orderFinishLog.setInfoId(orderInfo.getProductId());
                    orderFinishLog.setOrderId(orderId);
                    orderFinishLog.setOrderCode(orderInfo.getOrderCode());
                    // orderFinishLog.setFinishMemo(finishMemo);
                    orderFinishLog.setMemberId(orderInfo.getMemberId());
                    orderFinishLog.setStatus(0);
                    orderFinishLog.setFinishTime(new Date());

                    orderFinishLogService.insert(orderFinishLog);
                    res.setStatus(1);
                    res.setMsg("验证成功");
                }

            }

        } catch(IOException e) {
            res.setStatus(0);
            res.setMsg("验证失败");
            e.printStackTrace();
        }

        // 用Gson转为json
        out.print(new GsonBuilder().create().toJson(res));

    }

}