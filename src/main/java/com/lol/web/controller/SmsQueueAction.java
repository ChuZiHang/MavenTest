package com.lol.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lol.common.model.ds0.SmsQueue;
import com.lol.common.model.ds0.SmsQueueExample;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.plugin.Page;
import com.lol.common.plugin.Pager;
import com.lol.common.plugin.PagerService;
import com.lol.common.service.SmsQueueService;
import com.lol.common.service.UserLoginService;
import com.lol.common.util.ParamUtils;



@Controller
@RequestMapping("/sendMessage")
public class SmsQueueAction {

	@Resource
	private  SmsQueueService  smsQueueService;
	@Resource
	private UserLoginService userLoginService;
	@RequestMapping("/send")
	public String  list(HttpServletRequest request, Model model){
		int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);
		CustInfo custInfo = userLoginService.getCurrentUserInfo().getCustInfo();
		long sysCustId = custInfo.getSystemId();
		try {
			SmsQueueExample example = new SmsQueueExample();
			SmsQueueExample.Criteria criteria = example.createCriteria();
			criteria.andCustIdEqualTo(sysCustId);
			example.setOrderByClause("id  desc");
			int pageCount = smsQueueService.countByExample(example);
			int pageSize = 10;
			int offSet = 5;
			Page pages = new Page();
			pages.setTotal(pageCount);
			pages.setLimit(pageSize);
			pages.setNo(currentPage);
			example.setPage(pages);
			Pager page = PagerService.getPager(currentPage, pageCount,
					pageSize, offSet);
			List<SmsQueue> list = smsQueueService.selectByExample(example);
			model.addAttribute("list", list);
			model.addAttribute("pager", page);
			model.addAttribute("pageCount", pageCount);

		}catch(Exception e){
			e.printStackTrace();
		}
		return  "/models/hq/sms/sendlist";
	}
}
