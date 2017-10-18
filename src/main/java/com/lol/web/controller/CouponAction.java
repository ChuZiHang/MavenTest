package com.lol.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lol.common.Constants;
import com.lol.common.bean.BaseJson;
import com.lol.common.model.ds1.CouponList;
import com.lol.common.model.ds1.CouponListExample;
import com.lol.common.model.ds1.CouponListWithBLOBs;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;
import com.lol.common.model.ds1.UserLogin;
import com.lol.common.model.ds1.WeixinToken;
import com.lol.common.plugin.Page;
import com.lol.common.plugin.Pager;
import com.lol.common.plugin.PagerService;
import com.lol.common.service.CouponInfoService;
import com.lol.common.service.CouponListService;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.MemberService;
import com.lol.common.service.OrderInfoService;
import com.lol.common.service.UserLoginService;
import com.lol.common.service.WeixinTokenService;
import com.lol.common.util.HttpUtils;
import com.lol.common.util.ParamUtils;
import com.lol.common.weixin.TokenAPI;
import com.lol.web.fineuploader.MultipartUploadParser;
import com.google.gson.GsonBuilder;

/**
 * 优惠券管理
 * 
 * @author bowen
 *
 */
@Controller
@RequestMapping("/coupon")
public class CouponAction {

    @Resource
    private MemberService memberService;

    @Resource
    private CouponInfoService couponInfoService;

    @Resource
    private CouponListService couponListService;

    @Resource
    private WeixinTokenService weixinTokenService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private CustInfoService custInfoService;

    @Resource
    private UserLoginService userLoginService;

    Logger logger = LoggerFactory.getLogger(CouponAction.class);

    @RequestMapping("/index")
    public String couponIndex(HttpServletRequest request, Model model) {

        try {
            String access_token = getToken();

            model.addAttribute("access_token", access_token);

            CustInfoExample example = new CustInfoExample();
            example.setOrderByClause(" system_id asc");
            example.createCriteria().andStatusEqualTo(0).andParentIdEqualTo(8);
            List<CustInfo> custInfos = custInfoService.selectCustInfosByExample(example);

            model.addAttribute("custInfos", custInfos);
        } catch(Exception e) {
            logger.error("couponIndex Error:" + e.getMessage(), e);
        }

        return "models/hq/coupon/index";

    }

    @RequestMapping("/uploadimg")
    public void memberOpenDoorKey(HttpServletRequest request, HttpServletResponse response) {

        BaseJson res = new BaseJson();
        PrintWriter out = null;
        try {
            out = response.getWriter();
            File repository = (File)request.getServletContext().getAttribute("javax.servlet.context.tempdir");
            MultipartUploadParser mup = new MultipartUploadParser(request, repository, request.getServletContext());

            HttpUtils httpUtils = new HttpUtils(6000);

            List<FileItem> files = mup.getFiles();

            Map<String, String> parameters = mup.getParams();

            File nfile = new File(repository.getPath() + "/temp.jpg");

            writeFile(files.get(0).getInputStream(), nfile, null);

            String result = httpUtils.postFile("https://api.weixin.qq.com/cgi-bin/media/uploadimg", "utf-8", parameters, nfile);

            if(result.contains("\"errcode\":40001")) {
                // 获取access_token
                String access_token = TokenAPI.getToken(Constants.AppID, Constants.AppSecret);
                // 存入数据库
                WeixinToken record = new WeixinToken();
                record.setAppId(Constants.AppID);
                record.setAccessToken(access_token);
                record.setCreateDate(new Date());
                weixinTokenService.insertSelective(record);

                parameters.put("access_token", access_token);
                result = httpUtils.postFile("https://api.weixin.qq.com/cgi-bin/media/uploadimg", "utf-8", parameters, nfile);

            }
            JSONObject json = JSONObject.parseObject(result);
            if(result.contains("url\":")) {
                res.setData(json.get("url"));
            }

            // res.setData(result);
        } catch(Exception e) {
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

    private File writeFile(InputStream in, File out, Long expectedFileSize) throws IOException {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(out);

            IOUtils.copy(in, fos);

            if(expectedFileSize != null) {
                Long bytesWrittenToDisk = out.length();
                if(!expectedFileSize.equals(bytesWrittenToDisk)) {

                    out.delete();
                    throw new IOException(String.format("Unexpected file size mismatch. Actual bytes %s. Expected bytes %s.",
                        bytesWrittenToDisk, expectedFileSize));
                }
            }

            return out;
        } catch(Exception e) {
            throw new IOException(e);
        } finally {
            IOUtils.closeQuietly(fos);
        }
    }

    @RequestMapping("/create")
    public void createCoupon(HttpServletRequest request, HttpServletResponse response) {

        try {
            UserLogin user = userLoginService.getCurrentUserInfo().getUserLogin();
            int cust_id = ParamUtils.getIntParameter(request, "cust_id", 0);
            String default_detail = ParamUtils.getParameter(request, "default_detail", "");
            String service_phone =
                new String(ParamUtils.getParameter(request, "service_phone", "").getBytes("iso-8859-1"), "utf-8");
            String money = ParamUtils.getParameter(request, "money", "0");
            int get_limit = ParamUtils.getIntParameter(request, "get_limit", 1);
            int fixed_term = ParamUtils.getIntParameter(request, "fixed_term", 1);
            int start_money = ParamUtils.getIntParameter(request, "start_money", 0);
            int quantity = ParamUtils.getIntParameter(request, "sum", 50000);
            String share = ParamUtils.getParameter(request, "share", "");
            String friend = ParamUtils.getParameter(request, "friend", "");

            boolean can_share = "yes".equals(share) ? true : false;
            boolean can_give_friend = "yes".equals(friend) ? true : false;

            String brand_name = "启动健时尚健身";
            String title = money + "元优惠券";
            String notice = "线下使用时向服务员出示此券";
            String description = "不可与其他优惠同享";
            String color = "Color070";
            // int quantity = 500000;
            String logo_url =
                "http://mmbiz.qpic.cn/mmbiz_jpg/e44w6twWic9QWudGlkYvgh9pF4b3kFFSme4ichXZYib802liaMaqUE4Dsz88H3Dmhmk1UcEFBlxOC35ia8DI0k8nRYQ/0";
            // =====================
            // 三家门店都可以用
            JSONArray location_id_list = new JSONArray();
            String sub_title = "通用";
            if(9 == cust_id) {
                location_id_list.add(404356592);
                sub_title = "方天大厦店";
            } else if(10 == cust_id) {
                location_id_list.add(463844260);
                sub_title = "源泰楼店";
            } else if(11 == cust_id) {
                location_id_list.add(463844139);
                sub_title = "启动健旗舰店";
            } else {
                location_id_list.add(463844260);
                location_id_list.add(463844139);
                location_id_list.add(404356592);
            }

            // =====================================
            JSONObject date_info = new JSONObject();
            date_info.put("type", "DATE_TYPE_FIX_TERM");
            // 领取后多少天内有效
            date_info.put("fixed_term", fixed_term);
            // 0为马上开始
            date_info.put("fixed_begin_term", 0);

            // ==========================================
            // 默认生成50000张//统计领券存在误差，生成时加10张来平误差
            JSONObject sku = new JSONObject();
            sku.put("quantity", quantity+10);

            JSONObject base_info = new JSONObject();
            base_info.put("location_id_list", location_id_list);
            base_info.put("date_info", date_info);
            base_info.put("sku", sku);

            base_info.put("logo_url", logo_url);
            base_info.put("brand_name", brand_name);
            // 生成的类型，默认为二维码加数字
            base_info.put("code_type", "CODE_TYPE_QRCODE");
            base_info.put("title", title);

            base_info.put("sub_title", sub_title);

            base_info.put("color", color);
            base_info.put("notice", notice);
            // 服务电话
            base_info.put("service_phone", service_phone);
            // 使用须知

            base_info.put("description", description);

            // 每人领券数量
            base_info.put("get_limit", get_limit);
            base_info.put("bind_openid", false);
            // 卡券领取页面是否可分享
            base_info.put("can_share", can_share);
            // 赠送给朋友
            base_info.put("can_give_friend", can_give_friend);

            // base_info.put("custom_url_name", "点此购买");
            // base_info.put("custom_url", "http://feel.lol.com/membercard/weixin/list.action");

            base_info.put("center_title", "立即使用");
            // base_info.put("center_sub_title", "要使用");
            base_info.put("center_url", "http://feel.lol.com/membercard/weixin/list.action");

            // base_info.put("custom_url_sub_title", "去网站使用");
            // base_info.put("promotion_url_name", "更多优惠");
            // base_info.put("promotion_url", "http://www.qq.com");

            // ==============================================
            JSONObject general_coupon = new JSONObject();
            // 优惠说明
            general_coupon.put("default_detail", default_detail);
            general_coupon.put("base_info", base_info);

            JSONObject card = new JSONObject();
            card.put("card_type", "GENERAL_COUPON");
            card.put("general_coupon", general_coupon);
            // ==============================================
            JSONObject json = new JSONObject();
            json.put("card", card);

            HttpUtils httpUtils = new HttpUtils(6000);

            String res =
                httpUtils.postJson("https://api.weixin.qq.com/card/create?access_token=" + getToken(), "utf-8", json.toString());

            if(res.contains("access_token is invalid")) {
                res = httpUtils.postJson("https://api.weixin.qq.com/card/create?access_token=" + createToken(), "utf-8",
                    json.toString());
            }

            // 新建成功后，写入到数据库中
            // {"errcode":0,"errmsg":"ok","card_id":"pDCe0w1FgQpigcF6rSVQZhiL-jtc"}
            CouponListWithBLOBs record = new CouponListWithBLOBs();
            record.setBrandName(brand_name);

            record.setCount(quantity);
            record.setRemainCount(quantity);
            record.setTerm(fixed_term);
            record.setCreateTime(new Date());
            record.setDescription(description);
            record.setSendJson(json.toString());
            record.setResJson(res);
            record.setLogoUrl(logo_url);
            record.setMoney((int)(Double.parseDouble(money) * 100));
            record.setNotice(notice);
            record.setOrigin(start_money * 100);
            record.setServicePhone(service_phone);
            record.setTitle(title);
            record.setSysCustId(cust_id);
            record.setInsertUser(user.getRealname());
            if(res.contains("\"errcode\":0")) {
                String card_id = JSONObject.parseObject(res).getString("card_id");
                record.setCardId(card_id);
                record.setStatus(0);
                // 创建微信二维码
                // String erWeiMaUrl = createErWeiMa(card_id);

                // System.out.println("erweima===========" + erWeiMaUrl);
            } else {
                // 建卡失败
                record.setStatus(2);
            }
            couponListService.insertSelective(record);

            response.getWriter().print(res);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model) {

        // 此段代码为从微信获取能用的优惠券
        /*
         * try {
         * 
         * JSONArray status_list = new JSONArray(); // 通过审核 status_list.add("CARD_STATUS_VERIFY_OK"); // 在公众平台投放过的卡券
         * status_list.add("CARD_STATUS_DISPATCH");
         * 
         * JSONObject json = new JSONObject(); // offset 起始的位置 // count 查多少条数据 json.put("offset", 0); json.put("count", 50);
         * json.put("status_list", status_list);
         * 
         * // ===========获取token===========
         * 
         * HttpUtils httpUtils = new HttpUtils(6000); String res =
         * httpUtils.postJson("https://api.weixin.qq.com/card/batchget?access_token=" + getToken(), "utf-8", json.toString());
         * 
         * if(res.contains("access_token is invalid or not latest hint")) { res =
         * httpUtils.postJson("https://api.weixin.qq.com/card/batchget?access_token=" + createToken(), "utf-8", json.toString()); }
         * 
         * model.addAttribute("data", JSONObject.parseObject(res));
         * 
         * } catch(Exception e) { logger.error("couponlist Error:" + e.getMessage(), e); }
         */

        try {

            CouponListExample example = new CouponListExample();
            example.setOrderByClause(" id desc");
            example.createCriteria();

            // 分页
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
            int pageCount = couponListService.countByExample(example);
            int pageSize = 10;
            int offSet = 3;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            example.setPage(pages);
            // int currentPage, int pageCount, int pageSize, int offSet
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
            List<CouponList> couponList = couponListService.selectByExample(example);
            model.addAttribute("couponList", couponList);
            model.addAttribute("pager", page);

        } catch(Exception e) {
            logger.error("couponlist Error:" + e.getMessage(), e);
        }
        return "models/hq/coupon/list";
    }

    @RequestMapping("/detail")
    public String detail(HttpServletRequest request, Model model) {

        try {

            String cardId = ParamUtils.getParameter(request, "cardId", "");

            // 卡券信息
            CouponListExample example = new CouponListExample();
            example.createCriteria().andCardIdEqualTo(cardId);
            List<CouponListWithBLOBs> couponLists = couponListService.selectByExampleWithBLOBs(example);
            CouponListWithBLOBs couponList =
                couponLists != null && couponLists.size() > 0 ? couponLists.get(0) : new CouponListWithBLOBs();

            // 解析json获取数据
            String jsonStr = couponLists.get(0).getSendJson();
            // 每人领取限制
            String get_limit = JSONObject.parseObject(jsonStr).getJSONObject("card").getJSONObject("general_coupon")
                .getJSONObject("base_info").getString("get_limit");

            // 兑换情况
            List<Map<String, Object>> orderAndCoupon = orderInfoService.selectBySql(
                "SELECT a.order_type,a.product_id,a.mem_price,a.sale_price,a.status,a.num,a.member_id,a.sys_cust_id,b.use_time  FROM order_info a,coupon_info b WHERE a.id = b.order_id and b.card_id=? order by b.use_time desc ",
                new Object[]{cardId});
            model.addAttribute("limit", get_limit);
            model.addAttribute("couponList", couponList);
            model.addAttribute("orderAndCoupon", orderAndCoupon);

        } catch(Exception e) {
            logger.error("couponlist Error:" + e.getMessage(), e);
        }
        return "models/hq/coupon/detail";
    }

    @RequestMapping("/couponDelete")
    public void update(HttpServletRequest request, HttpServletResponse response) {

        try {
            UserLogin user = userLoginService.getCurrentUserInfo().getUserLogin();
            int id = ParamUtils.getIntParameter(request, "id", 0);
            String card_id = ParamUtils.getParameter(request, "cardId", "");
            JSONObject json = new JSONObject();
            json.put("card_id", card_id);
            if(id > 0 && card_id.length() > 0) {
                // 发请求修改微信卡券
                HttpUtils httpUtils = new HttpUtils(6000);
                String result = httpUtils.postJson("https://api.weixin.qq.com/card/delete?access_token=" + getToken(), "utf-8",
                    json.toString());
                if(result.contains("access_token is invalid or not latest hint")) {
                    result = httpUtils.postJson("https://api.weixin.qq.com/card/delete?access_token=" + createToken(), "utf-8",
                        json.toString());
                }

                // 更改数据库
                if(result.contains("\"errcode\":0")) {
                    CouponListWithBLOBs record = new CouponListWithBLOBs();
                    record.setId(id);
                    record.setStatus(1);
                    record.setDeleteUser(user.getRealname());
                    couponListService.updateByPrimaryKeySelective(record);
                }
                response.getWriter().print(result);
            }

        } catch(Exception e) {
            logger.error("couponDelete Error:" + e.getMessage(), e);
        }

    }

    public String getToken() throws Exception {

        String access_token = "";

        List<Map<String, Object>> tokens = weixinTokenService.selectWeixinTokensBySql(
            "SELECT MAX(create_date) as cdate,access_token FROM weixin_token WHERE app_id = ?", new Object[]{Constants.AppID});

        if(tokens != null && tokens.size() > 0) {

            Date d = (Date)tokens.get(0).get("cdate");
            access_token = tokens.get(0).get("access_token").toString();

            // 判断token两小时过期
            if(new Date().getTime() - d.getTime() < 7200000) {
                access_token = createToken();
            }

        }

        return access_token;

    }

    public String createToken() throws Exception {
        // 获取access_token
        String access_token = TokenAPI.getToken(Constants.AppID, Constants.AppSecret);
        // 存入数据库
        WeixinToken record = new WeixinToken();
        record.setAppId(Constants.AppID);
        record.setAccessToken(access_token);
        record.setCreateDate(new Date());
        weixinTokenService.insertSelective(record);

        return access_token;

    }

    // 生成二维码

    public String createErWeiMa(String card_id) {

        // {
        // "action_name": "QR_CARD",
        // "expire_seconds": 1800,
        // "action_info": {
        // "card": {
        // "card_id": "pFS7Fjg8kV1IdDz01r4SQwMkuCKc",
        // "code": "198374613512",
        // "openid": "oFS7Fjl0WsZ9AMZqrI80nbIq8xrA",
        // "is_unique_code": false ,
        // "outer_str":"12b"
        // }
        // }
        // }

        String res = "";
        try {

            HttpUtils httpUtils = new HttpUtils(6000);

            JSONObject action_info = new JSONObject();
            JSONObject cardJson = new JSONObject();
            cardJson.put("card_id", card_id);
            cardJson.put("is_unique_code", false);
            cardJson.put("outer_str", "12b");

            action_info.put("card", cardJson);

            JSONObject jsonErWeiMa = new JSONObject();
            jsonErWeiMa.put("action_name", "QR_CARD");
            jsonErWeiMa.put("expire_seconds", 1800);
            jsonErWeiMa.put("action_info", action_info);

            String resErWeiMa = httpUtils.postJson("https://api.weixin.qq.com/card/qrcode/create?access_token=" + getToken(),
                "utf-8", jsonErWeiMa.toString());
            if(resErWeiMa.contains("access_token is invalid")) {
                resErWeiMa = httpUtils.postJson("https://api.weixin.qq.com/card/qrcode/create?access_token=" + createToken(),
                    "utf-8", jsonErWeiMa.toString());
            }

            if(resErWeiMa.contains("\"errcode\": 0,\"errmsg\": \"ok\"")) {
                res = JSONObject.parseObject(resErWeiMa).getString("show_qrcode_url");
            }

        } catch(Exception e) {
            logger.error("couponCreateErWeiMa Error:" + e.getMessage(), e);
        }

        return res;
    }

    @RequestMapping("/couponAdd")
    public void couponAdd(HttpServletRequest request, HttpServletResponse response) {

        try {

            int num = ParamUtils.getIntParameter(request, "num", 10);
            String card_id = ParamUtils.getParameter(request, "cardId", "");
            JSONObject json = new JSONObject();
            json.put("card_id", card_id);
            json.put("increase_stock_value", num);

            if(card_id.length() > 0) {
                // 添加卡券数量
                HttpUtils httpUtils = new HttpUtils(6000);
                String result = httpUtils.postJson("https://api.weixin.qq.com/card/modifystock?access_token=" + getToken(), "utf-8",
                    json.toString());
                if(result.contains("access_token is invalid or not latest hint")) {
                    result = httpUtils.postJson("https://api.weixin.qq.com/card/modifystock?access_token=" + createToken(), "utf-8",
                        json.toString());
                }

                response.getWriter().print(result);
            }

        } catch(Exception e) {
            logger.error("couponDelete Error:" + e.getMessage(), e);
        }

    }
}