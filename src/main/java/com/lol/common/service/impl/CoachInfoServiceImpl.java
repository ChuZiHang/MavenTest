
package com.lol.common.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.CoachInfoMapper;
import com.lol.common.mapper.ds1.CoursePriceMapper;
import com.lol.common.model.ds1.CoachInfo;
import com.lol.common.model.ds1.CoachInfoExample;
import com.lol.common.model.ds1.CoursePriceExample;
import com.lol.common.service.CoachInfoService;
import com.lol.web.JSTLFuncs;

@Service("coachInfoService")
public class CoachInfoServiceImpl implements CoachInfoService {

    @Resource
    CoachInfoMapper coachInfoMapper;

    @Resource
    CoursePriceMapper coursePriceMapper;

    @Resource
    private JdbcTemplate jdbcTemplateForDs1;

    @Override
    public int insertSelective(CoachInfo coachInfo) {

        return coachInfoMapper.insertSelective(coachInfo);
    }

    @Override
    public CoachInfo selectByPrimaryKey(long id) {

        return coachInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CoachInfo> selectByExample(CoachInfoExample example) {

        return coachInfoMapper.selectByExample(example);
    }

    @Override
    public int countByExample(CoachInfoExample example) {

        return coachInfoMapper.countByExample(example);
    }

    @Override
    public List<Map<String, Object>> getList(String sql) {
        List<Map<String, Object>> result = null;
        result = jdbcTemplateForDs1.queryForList(sql);
        return result;
    }

    @Override
    public int updateByPrimaryKeySelective(CoachInfo coachInfo) {

        return coachInfoMapper.updateByPrimaryKeySelective(coachInfo);
    }

    // 平均评价分
    @Override
    public float getScoureAvg(long id, int custId) {
        List<Map<String, Object>> datas = jdbcTemplateForDs1.queryForList(
            "select AVG(c.scores) AS savg from comment_info c where c.status=0 and c.object_id =? and c.sys_cust_id=?  ",
            new Object[]{id, custId});
        String avg = datas.get(0).get("savg") != null && datas.size() > 0 ? datas.get(0).get("savg") + "" : "5";
        return Float.parseFloat(avg);
    }

    // 分页
    @Override
    public int getCountCommentAndMember(long id) {
        List<Map<String, Object>> datas = jdbcTemplateForDs1.queryForList(
            " select count(c.id) as totals from comment_info c where c.status=0 and c.object_id=?     ", new Object[]{id});
        String totals = datas != null && datas.size() > 0 ? datas.get(0).get("totals") + "" : "0";
        return Integer.parseInt(totals);
    }

    @Override
    public List<Map<String, Object>> getCommentAndMember(long id, int currentPage, int pageSize) {
        int start = (currentPage - 1) * pageSize;
        return jdbcTemplateForDs1.queryForList(
            "select c.*,m.member_id,m.member_nick,m.member_logo from comment_info c,member_info m where c.member_id=m.member_id and c.status=0 and c.object_id =?  ORDER BY id DESC LIMIT ?,? ",
            new Object[]{id, start, pageSize});
    }

    @Override
    public int getCountOrders(long id) {
        List<Map<String, Object>> datas = jdbcTemplateForDs1.queryForList(
            "select count(num) as totals from  order_info where  product_id=? and `status` in  (2,4) ", new Object[]{id});
        String totals = datas != null && datas.size() > 0 ? datas.get(0).get("totals") + "" : "0";
        return Integer.parseInt(totals);
    }

    @Override
    public int getCountOrder(long id) {
        List<Map<String, Object>> datas = jdbcTemplateForDs1.queryForList(
            "SELECT SUM(num) as totals from  order_info where  product_id=? and `status` in  (2,4) ", new Object[]{id});
        String totals = datas.get(0).get("totals") != null && datas.size() > 0 ? datas.get(0).get("totals") + "" : "0";
        return Integer.parseInt(totals);
    }

    @Override
    public int getCountReady(long id) {
        List<Map<String, Object>> datas =
            jdbcTemplateForDs1.queryForList("SELECT SUM(1) as totals FROM order_finish_log   where  info_id=?  ", new Object[]{id});
        String totals = datas.get(0).get("totals") != null && datas.size() > 0 ? datas.get(0).get("totals") + "" : "0";
        return Integer.parseInt(totals);
    }

    @Override
    public List<Map<String, Object>> getCommentCourse(long id) {
        return jdbcTemplateForDs1.queryForList(
            "SELECT o.*,(o.end_time-NOW()) AS fin_time,(o.order_count-o.remain_count)  AS  already,c.cust_name from  order_info o,cust_info c  WHERE o.product_id=?  and  o.`status`=2  and  o.remain_count>0  AND o.sys_cust_id=c.system_id",
            new Object[]{id});
    }

    @Override
    public List<Map<String, Object>> getCommentReady(long id) {

        return jdbcTemplateForDs1.queryForList(
            "SELECT o.link_man,o.link_phone,o.crete_time ,(o.order_count-o.remain_count)  AS  already,c.cust_name from  order_info o,cust_info c  WHERE o.product_id=?   and  o.`status`=4  and o.sys_cust_id=c.system_id",
            new Object[]{id});
    }

    @Override
    public List<Map<String, Object>> getCommentRefund(long id) {

        return jdbcTemplateForDs1.queryForList(
            "SELECT o.link_man,o.link_phone,o.crete_time, o.num ,c.cust_name from  order_info o,cust_info c  WHERE o.product_id=?  and  o.`status`=5  and  o.sys_cust_id=c.system_id",
            new Object[]{id});
    }

    @Override
    public int selectCountNum(CoursePriceExample example) {

        return coursePriceMapper.countByExample(example);
    }

    @Override
    public int selectAllCountNum(CoursePriceExample cpExample) {
        return coursePriceMapper.countByExample(cpExample);
    }

    @Override
    public HSSFWorkbook exportReport(List<Map<String, Object>> datas, String[] args, String title) {
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
        for(int i = 0; i < args.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(args[i]);
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到
        // String[] hellName = { "课程名", "分店", "开始时间", "结束时间", "限制人数", "预约人数",
        // "验证人数", "旷课人" };

        for(int i = 0; i < datas.size(); i++) {
            row = sheet.createRow(i + 1);
            Map<String, Object> map = datas.get(i);
            row.createCell(0).setCellValue(JSTLFuncs.getCoachNameById((long)map.get("coach_id")));

            row.createCell(1).setCellValue(map.get("cust_name") + "");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format1 = sdf.format(map.get("start_time"));
            String format2 = sdf.format(map.get("end_time"));
            row.createCell(2).setCellValue(format1);
            row.createCell(3).setCellValue(format2);
            row.createCell(4).setCellValue(map.get("people_num") + "");
            row.createCell(5).setCellValue(map.get("itemBooking") != null ? map.get("itemBooking") + "" : "");
            row.createCell(6).setCellValue(map.get("itemCheck") != null ? map.get("itemCheck") + "" : "");
            // users=[{member_id=2, member_name=小二黑测试, member_nick=犹豫的小二黑,
            // member_logo=/static/images/jia.png}],
            List<Map<String, Object>> users = (List<Map<String, Object>>)map.get("users");
            StringBuffer stringBuffer = new StringBuffer();
            if(users != null) {
                for(int j = 0; j < users.size(); j++) {
                    stringBuffer.append(users.get(j).get("member_name") + ";");
                }
            }

            row.createCell(7).setCellValue(stringBuffer.toString());

        }
        return wb;
    }

    @Override
    public List<Map<String, Object>> selectBySql(String sql, Object[] args) {

        return jdbcTemplateForDs1.queryForList(sql, args);
    }

    @Override
    public HSSFWorkbook exportContent(List<Map<String, Object>> datas, String[] args, String title) {
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
        for(int i = 0; i < args.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(args[i]);
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到

      //  {"教练名", "分店名", "支付时间", "验证时间", "用户名", "手机号", "订单号"};
        
        //o.link_phone,o.member_id,o.pay_time,o.sys_cust_id,o.product_id,f.finish_time

        for(int i = 0; i < datas.size(); i++) {
            row = sheet.createRow(i + 1);
            Map<String, Object> map = datas.get(i);
            row.createCell(0).setCellValue(JSTLFuncs.getCoachNameById((long)map.get("product_id")));

            row.createCell(1).setCellValue(JSTLFuncs.getCustNameByCustId(map.get("sys_cust_id")+""));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format1 = sdf.format(map.get("pay_time"));
            String format2 = "";
            if(map.get("remain_count")!=null &&  map.get("remain_count").toString().length()>0){
                format2 = map.get("remain_count").toString();
            }else{
                format2 = sdf.format(map.get("finish_time"));   
            }
            
            row.createCell(2).setCellValue(format1);
            row.createCell(3).setCellValue(format2);
            row.createCell(4).setCellValue(JSTLFuncs.getmemberNameBymemberId(map.get("member_id")+""));
            row.createCell(5).setCellValue(map.get("link_phone")+"");
            row.createCell(6).setCellValue(map.get("id")+"");
            // users=[{member_id=2, member_name=小二黑测试, member_nick=犹豫的小二黑,
            // member_logo=/static/images/jia.png}],
            // List<Map<String, Object>> users = (List<Map<String, Object>>)map.get("users");
            // StringBuffer stringBuffer = new StringBuffer();
            // if(users != null) {
            // for(int j = 0; j < users.size(); j++) {
            // stringBuffer.append(users.get(j).get("member_name") + ";");
            // }
            // }
            //
            // row.createCell(7).setCellValue(stringBuffer.toString());

        }
        return wb;
    }

}
