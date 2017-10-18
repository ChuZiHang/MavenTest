package com.lol.common.task;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import com.lol.common.bean.LogConstants;
import com.lol.common.bean.MsgBuilder;
import com.lol.common.bean.MsgType;
import com.lol.common.model.ds1.CommentInfo;
import com.lol.common.model.ds1.CommentInfoExample;
import com.lol.common.model.ds1.CommentInfoWithBLOBs;
import com.lol.common.model.ds1.MemberPoint;
import com.lol.common.model.ds1.OrderInfo;
import com.lol.common.model.ds1.OrderInfoExample;
import com.lol.common.model.ds1.SysMessage;
import com.lol.common.service.CommentService;
import com.lol.common.service.MemberPointService;
import com.lol.common.service.MemberService;
import com.lol.common.service.OrderInfoService;
import com.lol.common.service.SysMessageService;
import com.lol.common.service.UserLoginService;
import com.lol.common.util.SMSUtils;

/**
 * 计划任务
 * 
 * @author cunxing
 *
 */
@Component
public class CountSyncTask {

    private Logger logger = LoggerFactory.getLogger(CountSyncTask.class);

    @Resource
    TransactionTemplate transactionTemplateForDs1;

    @Resource
    JdbcTemplate jdbcTemplateForDs1;

    @Resource
    SysMessageService sysMessageService;

    @Resource
    UserLoginService userLoginService;

    @Resource
    OrderInfoService orderInfoService;

    @Resource
    MemberService memberService;

    @Resource
    CommentService commentService;

    @Resource
    MemberPointService memberPointService;

    /**
     * 任务示例
     */
    @Scheduled(cron = "0 0 1 * * ?") // 一点钟执行一次
    public void testSync() {
        try {
            // logger.debug("----testSync----start-----");
            // System.out.println("--当前时间--:" + new Date());
            // logger.debug("----testSync--end-----");
        } catch(Exception e) {
            e.printStackTrace();
            // logger.error("------testSync---error-----", e);
        } finally {
            //
        }
    }

    /**
     * 收费团课已经预约多少人统计
     */
    @Scheduled(cron = "0 0/3 * * * ?") // 3分钟执行一次
    public void orderCount() {
        try {
            String sql = "SELECT o.`product_id`,COUNT(*),SUM(mem_price) AS order_price FROM order_info o WHERE o.`order_type` = ? "
                + "AND o.`status` = ? GROUP BY o.`product_id`";
            List<Map<String, Object>> queryForList =
                jdbcTemplateForDs1.queryForList(sql, new Object[]{LogConstants.PRODUCT_PRO_TYPE_GROUP, 2});

            List<Object[]> batchArgs = new ArrayList<Object[]>();
            String sql2 = "UPDATE product_info p SET p.`order_count` = ?,p.order_price =? WHERE p.`id` = ?";
            for(int i = 0; i < queryForList.size(); i++) {
                queryForList.get(i).get("product_id");
                batchArgs.add(new Object[]{queryForList.get(i).get("count(*)"), queryForList.get(i).get("order_price"),
                    queryForList.get(i).get("product_id")});
            }
            jdbcTemplateForDs1.batchUpdate(sql2, batchArgs);
            // logger.debug("----收费团课已经预约多少人统--end-------------------------------");
        } catch(Exception e) {
            e.printStackTrace();
            logger.error("------orderCount---error-----", e);
        } finally {
            //
        }
    }

    /**
     * 课程评分统计统计
     */
    @Scheduled(cron = "0 0/10 * * * ?") // 3分钟执行一次
    public void courseCoreCount() {
        try {
            String sql = "SELECT object_id,AVG(scores) FROM comment_info WHERE object_type = ? AND status = 0 GROUP BY object_id";
            List<Map<String, Object>> queryForList =
                jdbcTemplateForDs1.queryForList(sql, new Object[]{LogConstants.PRODUCT_PRO_TYPE_COURSE});
            List<Object[]> batchArgs = new ArrayList<Object[]>();
            String sql2 = "UPDATE course_info SET score = ? WHERE id = ?";
            for(int i = 0; i < queryForList.size(); i++) {
                BigDecimal d = (BigDecimal)queryForList.get(i).get("AVG(scores)");
                DecimalFormat df = new DecimalFormat("0.0");
                String result = df.format(d);
                batchArgs.add(new Object[]{result, queryForList.get(i).get("object_id")});
            }
            jdbcTemplateForDs1.batchUpdate(sql2, batchArgs);
            // logger.debug("----收费团课已经预约多少人统--end-------------------------------");
        } catch(Exception e) {
            e.printStackTrace();
            logger.error("------orderCount---error-----", e);
        } finally {
            //
        }
    }

    /**
     * 
     * 会员到期提醒短信
     * 
     */
    @Scheduled(cron = "0 0 12 * * ?") // 每天中午12点执行一次
    public void memberCheck() {
        try {
            // 查询出到期的会员
            String sql = "select c.end_time,i.member_name,i.link_phone FROM member_card as c ,member_info  i "
                + "where c.end_time = (select max(c.end_time) from member_card where c.member_id=member_id)   and   DATEDIFF(c.end_time,NOW())=3   and  i.member_id=c.member_id  and  c.is_active=1";
            List<Map<String, Object>> queryForList = jdbcTemplateForDs1.queryForList(sql, new Object[]{});
            for(int i = 0; i < queryForList.size(); i++) {

                String linkPhone = (String)queryForList.get(i).get("link_phone");
                String content = "【启动健健身中心】尊敬的启动健会员您好，您的会员卡还有3天将到期，为保证会员卡的正常使用，请您登录启动健公众号进行及时续卡，感谢您对启动健的支持，如有疑问可致电：85583111";
                SMSUtils.sendMsg(linkPhone, content);
            }

        } catch(Exception e) {
            e.printStackTrace();
            logger.error("----memberCheck", e);
        }
    }

    /**
     * 
     * 会员旷课3次发送给分店发送通知
     * 
     * select o.link_man ,COUNT(o.link_man) as cou,o.link_phone,o.member_id from order_info o ,course_price c where
     * c.id=o.product_id and o.`status`=2 and o.order_type=12 and c.end_time<NOW() GROUP BY o.link_man having count(o.link_man) >= 3
     */
    @Scheduled(cron = "0 0 9 * * ?") // 每天10点执行一次
    public void memberCut() {
        try {
            List<String> tmp = new ArrayList<String>();
            Set<Integer> s = new HashSet<Integer>();
            String sql = "SELECT i.link_man  ,i.end1,i.member_id ,i.parent_cust_id "
                + "from  (select o.link_man ,COUNT(o.link_man) as cou,o.link_phone,o.member_id , MAX(o.end_time) as end1,o.parent_cust_id  "
                + "from  order_info o  ,course_price c  WHERE  c.id=o.product_id  and   o.`status`=2   "
                + "and o.order_type=12  and   o.end_time<NOW() GROUP BY o.link_man  having count(o.link_man) =3) i "
                + " where  i.end1>DATE_FORMAT(date_sub(current_date(),interval 1 day),'%Y-%m-%d 00:00:00')";
            List<Map<String, Object>> queryForList = jdbcTemplateForDs1.queryForList(sql, new Object[]{});

            if(queryForList != null && queryForList.size() > 0) {
                for(int i = 0; i < queryForList.size(); i++) {
                    String memberName = (String)queryForList.get(i).get("link_man");
                    int parentId = (Integer)queryForList.get(i).get("parent_cust_id");
                    tmp.add(memberName.toString());
                    s.add(parentId);
                }
                MsgBuilder msgBuilder = new MsgBuilder();
                msgBuilder.setType(MsgType.MEMBER_CUT);
                String msg = msgBuilder.create(new Object[]{StringUtils.join(tmp, ",")});

                final SysMessage sys = new SysMessage();
                sys.setCreateTime(new Date());
                sys.setMessage(msg);
                Iterator<Integer> iterator = s.iterator();
                while(iterator.hasNext()) {
                    sys.setParentId((Integer)iterator.next());
                }

                String sql1 = "insert into sys_message(create_time,message,parent_id) values (?,?,?)";
                int pid = sysMessageService.insertAndGetKey(sql1, sys);
                String sql2 = "select system_id from cust_info  where  parent_type=2 and  status=0";
                List<Map<String, Object>> query = jdbcTemplateForDs1.queryForList(sql2, new Object[]{});
                for(int i = 0; i < query.size(); i++) {
                    if(!"".equals(query.get(i).get("system_id"))) {
                        int result = 0;
                        SysMessage sysMessage = new SysMessage();
                        sysMessage.setCreateTime(new Date());
                        sysMessage.setMessage(msg);
                        sysMessage.setSysCustId(Integer.parseInt(String.valueOf(query.get(i).get("system_id"))));
                        sysMessage.setPid(pid);
                        result = sysMessageService.insertSelective(sysMessage);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            logger.error("----memberCut", e);
        }
    }

    /**
     * 积分统计
     */
    @Scheduled(cron = "0 0 11 * * ?") // 每天中午11点执行一次
    public void pointSync() {
        try {

            MemberPoint record = new MemberPoint();
            // ------------------购买产品---------
            // 一天前
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, -1);

            OrderInfoExample exampleOrder = new OrderInfoExample();
            // 只查卡，团课，私教
            List<Integer> values = Arrays.asList(new Integer[]{10, 11, 13});
            exampleOrder.createCriteria().andPayTimeBetween(calendar.getTime(), new Date()).andStatusEqualTo(2)
                .andOrderTypeIn(values);
            List<OrderInfo> buyProduct = orderInfoService.selectByExample(exampleOrder);
            for(int i = 0; i < buyProduct.size(); i++) {
                record = new MemberPoint();
                OrderInfo o = buyProduct.get(i);
                switch(o.getOrderType()) {
                    case 10:
                        record.setMemberId(o.getMemberId());
                        record.setPointId(10);
                        record.setProductId(o.getProductId());
                        record.setOrderId(o.getId());
                        record.setStatus(0);
                        record.setCreateTime(new Date());

                        break;
                    case 11:
                        record.setMemberId(o.getMemberId());
                        record.setPointId(11);
                        record.setProductId(o.getProductId());
                        record.setOrderId(o.getId());
                        record.setStatus(0);
                        record.setCreateTime(new Date());

                        break;
                    case 13:
                        record.setMemberId(o.getMemberId());
                        record.setPointId(13);
                        record.setProductId(o.getProductId());
                        record.setOrderId(o.getId());
                        record.setStatus(0);
                        record.setCreateTime(new Date());

                        break;

                    default:
                        break;
                }

                memberPointService.insertSelective(record);

            }

            // ------------------验证课程以及旷课---------

            OrderInfoExample exampleCourse = new OrderInfoExample();
            // 查已支付和已完成
            List<Integer> valuesCourse = Arrays.asList(new Integer[]{2, 4});
            exampleCourse.createCriteria().andEndTimeBetween(calendar.getTime(), new Date()).andStatusIn(valuesCourse)
                .andOrderTypeEqualTo(12);
            List<OrderInfo> courses = orderInfoService.selectByExample(exampleCourse);
            for(int i = 0; i < courses.size(); i++) {
                record = new MemberPoint();
                OrderInfo o = courses.get(i);
                switch(o.getStatus()) {
                    case 2:
                        record.setMemberId(o.getMemberId());
                        record.setPointId(15);
                        record.setProductId(o.getProductId());
                        record.setOrderId(o.getId());
                        record.setStatus(0);
                        record.setCreateTime(new Date());
                        break;
                    case 4:
                        record.setMemberId(o.getMemberId());
                        record.setPointId(14);
                        record.setProductId(o.getProductId());
                        record.setOrderId(o.getId());
                        record.setStatus(0);
                        record.setCreateTime(new Date());
                        break;

                    default:
                        break;
                }
                memberPointService.insertSelective(record);
            }

            // ------------------评论---------

            CommentInfoExample exampleComment = new CommentInfoExample();
            exampleComment.createCriteria().andCreateTimeBetween(calendar.getTime(), new Date());
            List<CommentInfoWithBLOBs> comments = commentService.selectCommentInfosByExample(exampleComment);

            for(int i = 0; i < comments.size(); i++) {
                record = new MemberPoint();
                CommentInfo c = comments.get(i);
                record.setMemberId(c.getMemberId());
                record.setPointId(16);
                record.setStatus(0);
                record.setProductId(c.getObjectId());
                record.setCreateTime(new Date());
                memberPointService.insertSelective(record);
            }

        } catch(Exception e) {
            e.printStackTrace();
            logger.error("------pointSync---error-----", e);
        } finally {
            //
        }
    }

}