package com.lol.common.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds1.OrderInfo;
import com.lol.common.model.ds1.OrderInfoExample;

public interface OrderInfoService {
	int countByExample(OrderInfoExample example);

	int deleteByExample(OrderInfoExample example);

	int insert(OrderInfo record);

	int insertSelective(OrderInfo record);

	List<OrderInfo> selectByExample(OrderInfoExample example);

	int updateByExampleSelective(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

	int updateByExample(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

	public int getCount(String sql, Object[] object);
	
	 OrderInfo selectByPrimaryKey(Long id);
	// 订单列表
	List<OrderInfo> selectOrdersbyExample(OrderInfoExample example);

	
	List<Map<String, Object>> selectBySql(String sql,Object[] args);
	
	// 获取课程缺课情况带用户姓名及头像(status = -1查所有)
	List<Map<String, Object>> getOrdersAndMember(int custId, int parentCustId, int courseId,int status);
	
	int updateByPrimaryKeySelective(OrderInfo record);

	//申请退款
	List<Map<String, Object>>  refund(long id);

	List<Map<String, Object>> getList(String sql, Object[] object);
   //获取退款总条数
	int getCountPage(String sqlCount);
	
}

