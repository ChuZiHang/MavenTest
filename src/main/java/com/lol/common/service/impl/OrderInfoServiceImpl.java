package com.lol.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.OrderInfoMapper;
import com.lol.common.model.ds1.OrderInfo;
import com.lol.common.model.ds1.OrderInfoExample;
import com.lol.common.service.OrderInfoService;


@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {

	@Resource
	OrderInfoMapper orderInfoMapper;
	
	@Resource
	JdbcTemplate jdbcTemplateForDs1;
	

	@Override
	public int countByExample(OrderInfoExample example) {
		return orderInfoMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(OrderInfoExample example) {
		return orderInfoMapper.deleteByExample(example);
	}

	@Override
	public int insert(OrderInfo record) {
		return orderInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderInfo record) {
		return orderInfoMapper.insertSelective(record);
	}

	@Override
	public List<OrderInfo> selectByExample(OrderInfoExample example) {
		return orderInfoMapper.selectByExample(example);
	}

	@Override
	public int updateByExampleSelective(OrderInfo record, OrderInfoExample example) {
		return orderInfoMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(OrderInfo record, OrderInfoExample example) {
		return orderInfoMapper.updateByExample(record, example);
	}
	

	public int getCount(String sql,Object [] object) {
		List<Map<String, Object>> result = jdbcTemplateForDs1.queryForList(sql,object);
		int count = 0;
		for (Map<String, Object> map : result) {
			count = Integer.parseInt(map.get("count") + "");
		}
		return count;
	}
	
	
	
	@Override
	public List<OrderInfo> selectOrdersbyExample(OrderInfoExample example) {
		return orderInfoMapper.selectByExample(example);
	}

	@Override
	public List<Map<String, Object>> getOrdersAndMember(int custId,int parentCustId,int courseId,int status) {
		String sql = "SELECT o.*,m.member_logo,m.member_name,m.member_nick FROM order_info o ,member_info m WHERE o.sys_cust_id = ? AND o.parent_cust_id =? AND o.member_id = m.member_id AND o.product_id = ?";
		
		if(status>-1)
			sql+="and o.status = "+status;
		
		return jdbcTemplateForDs1.queryForList(sql,new Object[] {custId,parentCustId,courseId });
	}

	@Override
	public List<Map<String, Object>> selectBySql(String sql, Object[] args) {
		return jdbcTemplateForDs1.queryForList(sql, args);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderInfo record) {

		
		return orderInfoMapper.updateByPrimaryKeySelective(record);

	}

	@Override
	public OrderInfo selectByPrimaryKey(Long id) {
	
		return orderInfoMapper.selectByPrimaryKey(id);
	}


	@Override
	public List<Map<String, Object>> refund(long id) {
		
		return jdbcTemplateForDs1.queryForList("SELECT o.*,c.cust_name from  order_info o,cust_info c  WHERE o.id =?  AND o.sys_cust_id=c.id    and  o.`status`=2  "
				,new Object[]{id});
	}

	@Override
	public List<Map<String, Object>> getList(String sql, Object[] object) {
		List<Map<String, Object>> result = null;
		result = jdbcTemplateForDs1.queryForList(sql, object);

		return result;
	}

	public int getCountPage(String sqlCount) {
		List<Map<String, Object>> result = null;
		result = jdbcTemplateForDs1.queryForList(sqlCount);
		int count=0;
		for (Map<String, Object> map : result) {
			count = Integer.parseInt(map.get("count") + "");
		}
		
		return count;
	}

}

