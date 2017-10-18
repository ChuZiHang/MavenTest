package com.lol.common.service;

import java.util.List;
import java.util.Map;

import com.lol.common.model.ds1.CommentInfoExample;
import com.lol.common.model.ds1.CommentInfoWithBLOBs;

public interface CommentService {
	
	//获取课程的评论及评论人的相关信息
	List<Map<String, Object>>  getCommentAndMember(int custId,long courseId,int currentPage,int pageSize);
	
	//获取课程评论的总条数
	int getCountCommentAndMember(int custId,long courseId);
	
	//修改评论内容,删除把 status 改为 1
	int updateCommentReplyByPrimaryKeySelective(CommentInfoWithBLOBs comment);

	
	List<CommentInfoWithBLOBs> selectCommentInfosByExample(CommentInfoExample example);
	
	int countByExample(CommentInfoExample example);
	
}
