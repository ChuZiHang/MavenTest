package com.lol.common.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.lol.common.mapper.ds1.CommentInfoMapper;
import com.lol.common.mapper.ds1.CourseInfoMapper;
import com.lol.common.mapper.ds1.CoursePriceMapper;
import com.lol.common.model.ds1.CourseInfo;
import com.lol.common.model.ds1.CourseInfoExample;
import com.lol.common.model.ds1.CoursePrice;
import com.lol.common.model.ds1.CoursePriceExample;
import com.lol.common.service.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseInfoMapper courseInfoMapper;

	@Resource
	private CommentInfoMapper commentInfoMapper;

	@Resource
	private CoursePriceMapper coursePriceMapper;

	@Resource
	private JdbcTemplate jdbcTemplateForDs1;
	
	@Resource
	private TransactionTemplate transactionTemplateForDs1;


	@Override
	public List<CourseInfo> selectCourseListByExample(CourseInfoExample example) {
		return courseInfoMapper.selectByExample(example);
	}

	@Override
	public int countByExample(CourseInfoExample example) {
		return courseInfoMapper.countByExample(example);
	}

	@Override
	public CourseInfo selectCourseInfoByCourseId(long courseId) {
		return courseInfoMapper.selectByPrimaryKey(courseId);
	}

	@Override
	public int createCourseInfo(CourseInfo info) {
		return courseInfoMapper.insertSelective(info);
	}

	@Override
	public int updateCourseInfoByPrimaryKeySelective(CourseInfo info) {
		return courseInfoMapper.updateByPrimaryKeySelective(info);
	}
	
	@Override
    public void deleteCourseInfoByCourseId(final long CourseId) {
	    
	    //事务
	    transactionTemplateForDs1.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                jdbcTemplateForDs1.update("UPDATE course_info SET STATUS = 1 WHERE id=?", new Object[]{CourseId});
                jdbcTemplateForDs1.update("UPDATE course_price SET STATUS = 1 WHERE course_id = ?", new Object[]{CourseId});
            }
        });
    }
    
	
	

	@Override
	public float getScoureAvg(long id) {
		List<Map<String, Object>> datas = jdbcTemplateForDs1.queryForList(
				"select AVG(scores) AS savg from comment_info where status=0 and object_id = ? group by object_id ",
				new Object[] { id });
		String avg = datas.size() > 0 ? datas.get(0).get("savg") + "" : "5";
		return Float.parseFloat(avg);
	}

	@Override
	public int selectCoursePriceCountByCourseId(CoursePriceExample example) {
		return coursePriceMapper.countByExample(example);
	}

	@Override
	public List<Map<String, Object>> selectCoursePriceBySql(String sql, Object[] args) {
		return jdbcTemplateForDs1.queryForList(sql, args);
	}
	
	@Override
	public CoursePrice selectCoursePriceByPrimaryKey(long id) {
		return coursePriceMapper.selectByPrimaryKey(id);
	}
	


	@Override
	public int insertCoursePriceSelective(CoursePrice record) {
		return coursePriceMapper.insertSelective(record);
	}

	@Override
	public List<CoursePrice> selectCoursePricesByExample(CoursePriceExample example) {
		return coursePriceMapper.selectByExample(example);
	}

	@Override
	public int updateCoursePriceByPrimaryKeySelective(CoursePrice record) {
		return coursePriceMapper.updateByPrimaryKeySelective(record);
	}

    @Override
    public void insertCoursePrices(final List<CoursePrice> coursePrices,final int weekStatus) {
        
        // 事务处理
        transactionTemplateForDs1.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                for(int i = 0; i < coursePrices.size(); i++) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    CoursePrice c = coursePrices.get(i);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(c.getStartTime());
                    cal.add(Calendar.DATE, 7*weekStatus);
                    String startDate = sdf.format(cal.getTime()) ;
                    cal.setTime(c.getEndTime());
                    cal.add(Calendar.DATE, 7*weekStatus);
                    String endDate = sdf.format(cal.getTime());
                    jdbcTemplateForDs1.execute("INSERT INTO course_price ("
                        + "start_time,end_time,course_id,sale_price,address,people_num,crete_time,user_id,status,coach_id,sys_cust_id,parent_cust_id"
                        + ") VALUES ("
                        + "STR_TO_DATE('"+startDate+"','%Y-%m-%d %H:%i:%s'),STR_TO_DATE('"+endDate+"','%Y-%m-%d %H:%i:%s'),"+c.getCourseId()+","+c.getSalePrice()+",'"+c.getAddress()+"',"
                            +c.getPeopleNum()+",now(),"+c.getUserId()+","+c.getStatus()+","+c.getCoachId()+","
                                +c.getSysCustId()+","+c.getParentCustId()+")");
                    
                }
            }
        });
    }

}
