package com.lol.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.CommentInfoMapper;
import com.lol.common.model.ds1.CommentInfoExample;
import com.lol.common.model.ds1.CommentInfoWithBLOBs;
import com.lol.common.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentInfoMapper commentInfoMapper;

	@Resource
	private JdbcTemplate jdbcTemplateForDs1;

	@Override
	public List<Map<String, Object>> getCommentAndMember(int custId,long courseId, int currentPage, int pageSize) {
		int start = (currentPage - 1) * pageSize;
		
		//String custSql = custId!=parentCustId && custId!=0? "and c.sys_cust_id ="+custId : "";
		
		return jdbcTemplateForDs1.queryForList(
				"select c.*,m.member_id,m.member_nick,m.member_logo from comment_info c,member_info m where c.member_id=m.member_id and c.status=0 and c.object_id =? and (c.parent_cust_id = ? or c.sys_cust_id = ? ) ORDER BY id DESC LIMIT ?,? ",
				new Object[] { courseId,custId,custId,start, pageSize });
	}

	
	@Override
	public int getCountCommentAndMember(int custId,long courseId) {
		
		List<Map<String, Object>> datas = jdbcTemplateForDs1.queryForList(
				"select count(c.id) as totals from comment_info c,member_info m where c.member_id=m.member_id and c.status=0 and c.object_id =? and (c.parent_cust_id = ? or c.sys_cust_id = ?) ",
				new Object[] { courseId,custId,custId});
		String totals = datas != null && datas.size() > 0 ? datas.get(0).get("totals") + "" : "0";
		return Integer.parseInt(totals);
	}

	@Override
	public int updateCommentReplyByPrimaryKeySelective(CommentInfoWithBLOBs comment) {
		return commentInfoMapper.updateByPrimaryKeySelective(comment);
	}


	@Override
	public List<CommentInfoWithBLOBs> selectCommentInfosByExample(CommentInfoExample example) {
		return commentInfoMapper.selectByExampleWithBLOBs(example);
	}


	@Override
	public int countByExample(CommentInfoExample example) {
		return commentInfoMapper.countByExample(example);
	}

	
	
	

}
