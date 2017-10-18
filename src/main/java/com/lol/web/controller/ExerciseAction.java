
package com.lol.web.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.lol.common.model.ds1.ExerciseLog;
import com.lol.common.model.ds1.ExerciseLogExample;
import com.lol.common.model.ds1.MemberInfo;
import com.lol.common.model.ds1.MemberInfoExample;
import com.lol.common.plugin.Page;
import com.lol.common.plugin.Pager;
import com.lol.common.plugin.PagerService;
import com.lol.common.service.ExerciseLogService;
import com.lol.common.service.MemberService;
import com.lol.common.util.ParamUtils;
import com.lol.web.JSTLFuncs;

/**
 * 运动记录
 * 
 * @author bowen
 *
 */
@Controller
@RequestMapping("/exercise")
public class ExerciseAction {

    @Resource
    private ExerciseLogService exerciseLogService;

    @Resource
    private MemberService memberService;

    Logger logger = LoggerFactory.getLogger(ExerciseAction.class);

    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model) {

        String mobile = ParamUtils.getParameter(request, "mobile", "");
        int currentPage = ParamUtils.getIntParameter(request, "pageNo", 1);

        ExerciseLogExample example = new ExerciseLogExample();

        example.setOrderByClause(" id desc");
        ExerciseLogExample.Criteria criteria = example.createCriteria();

        // 搜索中有手机号
        if(mobile.length() > 0) {
            MemberInfoExample exampleMember = new MemberInfoExample();
            exampleMember.createCriteria().andLinkPhoneEqualTo(mobile);
            List<MemberInfo> members = memberService.selectMemberByExample(exampleMember);
            if(!members.isEmpty()) {
                criteria.andMemberIdEqualTo(members.get(0).getMemberId());
            }
        }

        int pageCount = exerciseLogService.countByExample(example);
        int pageSize = 20;
        int offSet = 3;
        Page pages = new Page();
        pages.setTotal(pageCount);
        pages.setLimit(pageSize);
        pages.setNo(currentPage);
        example.setPage(pages);
        // int currentPage, int pageCount, int pageSize, int offSet
        Pager pager = PagerService.getPager(currentPage, pageCount, pageSize, offSet);

        List<ExerciseLog> logs = exerciseLogService.selectByExample(example);

        model.addAttribute("logs", logs);
        model.addAttribute("pager", pager);
        return "models/hq/exercise/list";
    }

    // 单个课程预约完成旷课器详情 -----表格导出
    @RequestMapping("/outexcel")
    public void bookingDetailOutExcel(HttpServletRequest request, HttpServletResponse response) {

        String mobile = ParamUtils.getParameter(request, "mobile", "");

        ExerciseLogExample example = new ExerciseLogExample();

        example.setOrderByClause(" id desc");
        ExerciseLogExample.Criteria criteria = example.createCriteria();

        // 搜索中有手机号
        if(mobile.length() > 0) {
            MemberInfoExample exampleMember = new MemberInfoExample();
            exampleMember.createCriteria().andLinkPhoneEqualTo(mobile);
            List<MemberInfo> members = memberService.selectMemberByExample(exampleMember);
            if(!members.isEmpty()) {
                criteria.andMemberIdEqualTo(members.get(0).getMemberId());
            }
        }

        List<ExerciseLog> datas = exerciseLogService.selectByExample(example);

        String[] args = {"用户ID", "用户名", "登录时间", "结束时间", "速度(千米/小时)", "距离(千米)", "卡路里(千卡)", "运动时长(秒)"};

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFDataFormat format = wb.createDataFormat();
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)12);// 设置字体大小
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("会员运动数据报表");
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
        for(int i = 0; i < args.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(args[i]);
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i = 0; i < datas.size(); i++) {
            row = sheet.createRow(i + 1);
            ExerciseLog log = datas.get(i);

            row.createCell(0).setCellValue(log.getMemberId() + "");
            row.createCell(1).setCellValue(JSTLFuncs.getmemberNameBymemberId(log.getMemberId() + ""));
            row.createCell(2).setCellValue(log.getLoginTime()==null?"暂无数据":sdf.format(log.getLoginTime()));
            row.createCell(3).setCellValue(log.getEndTime()==null?"暂无数据":sdf.format(log.getEndTime()));
            row.createCell(4).setCellValue(log.getSpeed()==null?"暂无数据":log.getSpeed()+"");
            row.createCell(5).setCellValue(log.getDistance()==null?"暂无数据":log.getDistance()+"");
            row.createCell(6).setCellValue(log.getCal()==null?"暂无数据":log.getCal()+"");
//            row.createCell(7).setCellValue(log.getRunTime()==null?"暂无数据":String .format("%.1f",log.getRunTime()/60)+"");
            row.createCell(7).setCellValue(log.getRunTime()==null?"暂无数据":log.getRunTime()+"");

        }

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
            logger.debug("exercise is error:" + e.getMessage(), e);
        }

    }
}