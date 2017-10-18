package com.lol.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedHashMap;
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

import com.lol.common.Constants;
import com.lol.common.bean.BaseJson;
import com.lol.common.bean.CurrentUserInfo;
import com.lol.common.cache.FieldCache;
import com.lol.common.cache.FieldCacheManager;
import com.lol.common.cache.TableConstans;
import com.lol.common.cache.redis.JedisClient;
import com.lol.common.model.ds0.InfoArea;
import com.lol.common.model.ds0.InfoAreaEx;
import com.lol.common.model.ds0.InfoAreaExample;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;
import com.lol.common.model.ds1.UserLogExample;
import com.lol.common.model.ds1.UserLoginExample;
import com.lol.common.model.ds1.CustInfoExample.Criteria;
import com.lol.common.model.ds1.UserLog;
import com.lol.common.model.ds1.UserLogin;
import com.lol.common.model.merchant.SaasMerchantInfo;
import com.lol.common.model.merchant.SaasMerchantInfoExample;
import com.lol.common.plugin.Page;
import com.lol.common.plugin.Pager;
import com.lol.common.plugin.PagerService;
import com.lol.common.service.CustInfoService;
import com.lol.common.service.InfoAreaExService;
import com.lol.common.service.InfoAreaService;
import com.lol.common.service.SaasMerchantInfoService;
import com.lol.common.service.UserLogService;
import com.lol.common.service.UserLoginService;
import com.lol.common.util.ParamUtils;
import com.lol.common.util.SecretUtils;
import com.lol.common.util.SequenceUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import redis.clients.jedis.Jedis;

/**
 * 项目首页
 * 
 * @author wenshang
 *
 */
@Controller
@RequestMapping("/branch")
public class BranchAction {

    @Resource
    UserLoginService userLoginService;

    @Resource
    CustInfoService custInfoService;

    @Resource
    InfoAreaService infoAreaService;

    @Resource
    InfoAreaExService infoAreaExService;
    
    @Resource
    UserLogService   userLogService;
    
    @Resource
    SaasMerchantInfoService saasMerchantInfoService;

    Logger logger = LoggerFactory.getLogger(BranchAction.class);

    // 欢迎页面
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model) {
        LinkedHashMap<Object, Object> areaMap = new LinkedHashMap<>();
        Jedis jedis = JedisClient.getJedis(Constants.REDIS_DBINDEX);
        try {
            int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
            int areaId = ParamUtils.getIntParameter(request, "areaId");
            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo custInfo = currentUserInfo.getCustInfo();
            FieldCache fieldCache = FieldCacheManager.createCache(Constants.DS0);
            String sql = "SELECT c.area_id FROM cust_info c where c.parent_id=?  GROUP BY c.area_id";
            List<Map<String, Object>> result = custInfoService.selectCustInfosBySql(sql, new Object[]{custInfo.getParentId()});
            for(Map<String, Object> map: result) {
                if(map.get("area_id") != null) {
                    int area_id = Integer.parseInt(map.get("area_id") + "");
                    String areaName = fieldCache.get(jedis, TableConstans.INFO_AREA, "tree_id:" + area_id + ":tree_name");
                    if(areaName == null || "".equals(areaName)) {
                        continue;
                    } else {
                        areaMap.put(area_id, areaName);
                    }
                } else {
                    continue;
                }
            }
            CustInfoExample custInfoExample = new CustInfoExample();
            Criteria criteria = custInfoExample.createCriteria();
            criteria.andParentIdEqualTo(custInfo.getParentId()).andParentTypeEqualTo(2);
            if(areaId != 0) {
                criteria.andAreaIdEqualTo(areaId);
            }
            int pageCount = custInfoService.countByExample(custInfoExample);
            int pageSize = 12;
            int offSet = 5;
            Page pages = new Page();
            pages.setTotal(pageCount);
            pages.setLimit(pageSize);
            pages.setNo(currentPage);
            custInfoExample.setPage(pages);
            Pager page = PagerService.getPager(currentPage, pageCount, pageSize, offSet);
            List<CustInfo> custInfoList = custInfoService.selectCustInfosByExample(custInfoExample);

            model.addAttribute("pager", page);
            model.addAttribute("areaId", areaId);
            model.addAttribute("areaMap", areaMap);
            model.addAttribute("custInfoList", custInfoList);
        } catch(Exception e) {
            logger.debug("#####################Index####################", e);
        } finally {
            jedis.close();
        }
        return "models/hq/branch/index";
    }

    // 创建新店
    @RequestMapping("/create")
    public String create(HttpServletRequest request, Model model) {
        int systemId = ParamUtils.getIntParameter(request, "systemId", 0);
        long id = ParamUtils.getLongParameter(request, "id", 0);

        InfoAreaExample example = new InfoAreaExample();
        example.createCriteria().andParentidEqualTo(100);
        List<InfoArea> areas = infoAreaService.selectByExample(example);

        model.addAttribute("areas", areas);
        if(systemId > 0) {
            CustInfo custInfo = custInfoService.selectByPrimaryKey(id);
            model.addAttribute("custInfo", custInfo);

            InfoAreaEx infoAreaEx = infoAreaExService.selectByPrimaryKey(Double.parseDouble(custInfo.getAreaId() + ""));
            model.addAttribute("infoAreaEx", infoAreaEx);

            String areaId = (custInfo.getAreaId() + "").substring(0, 5);
            InfoAreaEx infoAreaExParent = infoAreaExService.selectByPrimaryKey(Double.parseDouble(areaId));
            model.addAttribute("infoAreaExParent", infoAreaExParent);

            InfoAreaExample exampleInfo = new InfoAreaExample();
            String value1 = areaId + "00";
            String value2 = (Integer.parseInt(areaId) + 1) + "00";

            exampleInfo.createCriteria().andTreeTypeEqualTo(104).andTreeIdBetween(Integer.parseInt(value1),
                Integer.parseInt(value2));
            List<InfoArea> areas02 = infoAreaService.selectByExample(exampleInfo);
            model.addAttribute("areas02", areas02);

            // 账号用户名
            List<UserLogin> userLogins = userLoginService.getUserLoginByCustId(systemId);
            UserLogin userLogin = new UserLogin();
            if(userLogins != null && userLogins.size() > 0) {
                userLogin = userLogins.get(0);
            }
            //查询日志
            UserLogExample example1 = new UserLogExample();
    		UserLogExample.Criteria criteria = example1.createCriteria();
    		criteria.andLogTypeEqualTo(3);
    		criteria.andObjectIdEqualTo(id);
    		List<UserLog> userLogList = userLogService.selectByExample(example1);
    		model.addAttribute("userLogList", userLogList);
            model.addAttribute("userLogin", userLogin);
        }

        return "models/hq/branch/create";
    }

    // 保存新店
    @RequestMapping("/save")
    public void save(HttpServletRequest request, HttpServletResponse response) {
    	 
		  
        // new String(request.getParameter("something").getBytes("ISO-8859-1"),"utf-8")；
        try {
            String name = ParamUtils.getParameter(request, "name", "");
            int city = ParamUtils.getIntParameter(request, "city", 0);
            String address = ParamUtils.getParameter(request, "address", "");
            double lon = ParamUtils.getDoubleParameter(request, "lon", 0);
            double lat = ParamUtils.getDoubleParameter(request, "lat", 0);
            String username = ParamUtils.getParameter(request, "username", "");
            String mobile = ParamUtils.getParameter(request, "mobile", "");
            String account = ParamUtils.getParameter(request, "account", "");
            String password = ParamUtils.getParameter(request, "password", "");

            CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
            CustInfo custInfo = currentUserInfo.getCustInfo();
            UserLogin userLogin = currentUserInfo.getUserLogin();
            Gson gson = new GsonBuilder().create();
            BaseJson res = new BaseJson();

            List<Map<String, Object>> custInfos =
                custInfoService.selectCustInfosBySql("SELECT MAX(system_id) AS cust_max FROM cust_info", new Object[]{});
            int custId = 0;
            if(custInfos != null && custInfos.size() > 0) {
                custId = Integer.parseInt(custInfos.get(0).get("cust_max").toString()) + 1;
            }

            String salt = SecretUtils.randomSalt();
            String saltPassword = SecretUtils.encryptPass(account, password, salt);
            long id = SequenceUtils.nextId();

            UserLogin recordUser = new UserLogin();
            recordUser.setId(id);
            recordUser.setUsername(account);
            recordUser.setPassword(saltPassword);
            recordUser.setIsManager(1);
            recordUser.setRealname(username);
            recordUser.setMobile(mobile);
            recordUser.setCustId(custId);
            recordUser.setIsLock(0);
            recordUser.setSalt(salt);
            recordUser.setCreateTime(new Date());
            userLoginService.insertSelective(recordUser);

            CustInfo record = new CustInfo();
            record.setCustType(custInfo.getCustType());
            record.setCustName(name);
            record.setAreaId(city);
            record.setAddress(address);
            record.setLongitude(lon);
            record.setLatitude(lat);
            record.setLinkMan(username);
            record.setLinkMobile(mobile);
            record.setSystemId(custId);
            record.setCreateTime(new Date());
            record.setParentId(custInfo.getParentId());
            record.setStatus(1);
            record.setParentType(2);
            
            custInfoService.insertSelective(record);
            
            SaasMerchantInfo saasMerchantInfo = new SaasMerchantInfo();
            saasMerchantInfo.setMcId(custId);
            saasMerchantInfo.setMcLoginAccount(account);
            saasMerchantInfo.setMcLoginPwd(password);
            saasMerchantInfo.setMcLinkMobile(mobile);
            saasMerchantInfo.setMcLinkMan(username);
            saasMerchantInfo.setMcCustName(name);
            saasMerchantInfo.setMcName(name);
            saasMerchantInfo.setMcCustType(3);
            saasMerchantInfo.setMcAreaId(city);
            saasMerchantInfo.setMcAddress(address);
            saasMerchantInfo.setLatitude(lat);
            saasMerchantInfo.setLongitude(lon);
            saasMerchantInfo.setMcCTime(new Date());
            saasMerchantInfo.setMcStatus(0);
            saasMerchantInfoService.insertSelective(saasMerchantInfo);
            
            
          
            UserLog  log=new UserLog();
            log.setLogType(0);
            log.setUserId(userLogin.getUsername());
            log.setCreateTime(new Date());
            log.setLogAction(0);
            log.setLogMemo("新建分店");
            log.setObjectId(id);
            log.setSysCustId(custInfo.getSystemId());
            userLogService.insertSelective(log);
            
            res.setStatus(1);
            res.setMsg("操作成功");
            // res.setData(userLogin);
            response.getWriter().print(gson.toJson(res));

        } catch(Exception e) {
            logger.error("branchAction is Error:" + e.getMessage(), e);
        }

    }
    
    
    
    // 修改店铺
    @RequestMapping("/updateCust")
    public void updateCust(HttpServletRequest request, HttpServletResponse response) {
    	CurrentUserInfo currentUserInfo = userLoginService.getCurrentUserInfo();
	    CustInfo currentCustInfo = currentUserInfo.getCustInfo();
	    UserLogin userLogin = currentUserInfo.getUserLogin();
        try{
        long id = ParamUtils.getLongParameter(request, "id", 0);
        String name = ParamUtils.getParameter(request, "name", "");
        double city = ParamUtils.getDoubleParameter(request, "city");
        //System.out.println("city+++++++++++============================="+city);
        String address = ParamUtils.getParameter(request, "address", "");
        double lon = ParamUtils.getDoubleParameter(request, "lon", 0);
        double lat = ParamUtils.getDoubleParameter(request, "lat", 0);
        String username = ParamUtils.getParameter(request, "username", "");
        String mobile = ParamUtils.getParameter(request, "mobile", "");
        
        CustInfo record = new CustInfo();
        record.setId(id);
        record.setCustName(name);
        record.setAreaId((int)city);
        record.setAddress(address);
        record.setLongitude(lon);
        record.setLatitude(lat);
        record.setLinkMan(username);
        record.setLinkMobile(mobile);
        custInfoService.updateByPrimaryKeySelective(record);
        
        CustInfo custInfo = custInfoService.selectByPrimaryKey(id);
        
        SaasMerchantInfo saasMerchantInfo = new SaasMerchantInfo();
        saasMerchantInfo.setMcLinkMobile(mobile);
        saasMerchantInfo.setMcName(name);
        saasMerchantInfo.setMcCustType(3);
        saasMerchantInfo.setMcAreaId((int)city);
        saasMerchantInfo.setMcAddress(address);
        saasMerchantInfo.setLatitude(lat);
        saasMerchantInfo.setLongitude(lon);
        SaasMerchantInfoExample example = new SaasMerchantInfoExample();
        example.createCriteria().andMcIdEqualTo(custInfo.getSystemId());
        saasMerchantInfoService.updateByExampleSelective(saasMerchantInfo, example);
        
        UserLogin recordUser = new UserLogin();
        recordUser.setRealname(username);
        recordUser.setMobile(mobile);
        UserLoginExample ueExample = new UserLoginExample();
        ueExample.createCriteria().andCustIdEqualTo(custInfo.getSystemId()).andIsManagerEqualTo(1);
        userLoginService.updateByExampleSelective(recordUser, ueExample);
        
        UserLog  log=new UserLog();
        log.setLogType(3);
        log.setUserId(userLogin.getUsername());
        log.setCreateTime(new Date());
        log.setLogAction(2);
        log.setLogMemo("修改分店");
        log.setObjectId(id);
        log.setSysCustId(currentCustInfo.getSystemId());
        userLogService.insertSelective(log);
        
        Gson gson = new GsonBuilder().create();
        BaseJson res = new BaseJson();
        res.setStatus(1);
        res.setMsg("操作成功");
        // res.setData(userLogin);
        response.getWriter().print(gson.toJson(res));
        
        }catch(Exception e) {
            logger.error("branchAction is Error:" + e.getMessage(), e);
        }
        
        
    }
    
    
    

    // 地区详情
    @RequestMapping("/areadetail")
    public void getAreaDetail(HttpServletRequest request, HttpServletResponse response) {

        int areaId = ParamUtils.getIntParameter(request, "areaId", 0);
        PrintWriter out = null;
        BaseJson res = new BaseJson();
        try {

            InfoAreaExample example = new InfoAreaExample();
            String value1 = areaId + "00";
            String value2 = (areaId + 1) + "00";

            example.createCriteria().andTreeTypeEqualTo(104).andTreeIdBetween(Integer.parseInt(value1), Integer.parseInt(value2));
            List<InfoArea> areaDetails = infoAreaService.selectByExample(example);
            res.setData(areaDetails);
            res.setStatus(1);
            out = response.getWriter();

        } catch(Exception e) {
            try {
                out = response.getWriter();
            } catch(IOException e1) {
                logger.error("branchAction is Error:" + e.getMessage(), e1);
            }
            logger.error("branchAction is Error:" + e.getMessage(), e);
        }

        // 用Gson转为json
        out.print(new GsonBuilder().create().toJson(res));
    }

    // 上线或者下线
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response) {

        long custId = ParamUtils.getLongParameter(request, "custId", 0);
        int status = ParamUtils.getIntParameter(request, "status", 0);
        PrintWriter out = null;
        BaseJson res = new BaseJson();
        try {

            CustInfo record = new CustInfo();
            record.setId(custId);
            record.setStatus(status);
            custInfoService.updateByPrimaryKeySelective(record);
            
            CustInfo custInfo = custInfoService.selectByPrimaryKey(custId);
            
            SaasMerchantInfo saasMerchantInfo = new SaasMerchantInfo();
            saasMerchantInfo.setMcStatus(status);
            SaasMerchantInfoExample example = new SaasMerchantInfoExample();
            example.createCriteria().andMcIdEqualTo(custInfo.getSystemId());
            saasMerchantInfoService.updateByExampleSelective(saasMerchantInfo, example);
            
            res.setStatus(1);
            out = response.getWriter();

        } catch(Exception e) {
            try {
                out = response.getWriter();
            } catch(IOException e1) {
                logger.error("branchAction is Error:" + e.getMessage(), e1);
            }
            logger.error("branchAction is Error:" + e.getMessage(), e);
        }

        // 用Gson转为json
        out.print(new GsonBuilder().create().toJson(res));
    }

}