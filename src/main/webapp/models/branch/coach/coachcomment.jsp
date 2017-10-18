
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
    <title>上课评价</title>
  <link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>  
	</head>
<body class="baseBgColor">
	<div class="head">
		<!--顶部：开始-->
		<jsp:include page="/include/header.jsp" flush="true">
			<jsp:param name="title" value="后台首页" />
		</jsp:include>
		<!--顶部：结束-->
<div class="branch_wrap">
    <div class="branch_wrap_cen">
	<!--左侧导航：开始-->
	<jsp:include page="/include/nav_left.jsp" flush="true">
		<jsp:param name="nvaIndex" value="19" />
		<jsp:param name="nvaParentIndex" value="18" />
	</jsp:include>
	<!--左侧导航：结束-->
	<div class="rightContent">
	<h3 class="i_title">教练评价</h3>
		<div class="province" style="height:25px;"></div>
			<div class="item_syllabus_list_tag">
			   <span ><a href="/coach/detail.action?id=${id}&coachType=${coachType}">教练信息</a></span>
				<span ><a href="/coach/coachclass.action?id=${id}&coachType=${coachType}">上课信息</a></span>
				<span  class="item_syllabus_list_tag_lie"><a href="#">评价</a></span>
				<c:choose>
				<c:when test="${coachType==1 }" >
				<span class="ss" ><a href="/coach/coachsijiao.action?id=${id}&coachType=${coachType}">私教售卖信息</a></span>
				</c:when>
				<c:otherwise></c:otherwise>
				</c:choose>
			</div>
			<div class="syllabus_detail_cont">
				<div class="statistics">
					<div class="userscore">
				   <c:choose>
								<c:when test="${pageCount>0}">

									平均评分：<span>${avg }</span><em>分</em>

								</c:when>
								<c:otherwise>
									暂时还没有评价
								</c:otherwise>
							</c:choose>
					</div>
					<div class="commentlist">
						<ul>
						 <c:forEach var="comments"  items="${comments}">
							<li>
								<span class="commentHead"><img src="${comments.member_logo  }" alt=""/></span>
								<div>
									<div class="userinfor">
										<div class="usernick"><span class="nickname">${comments.member_nick }</span><em class="score">${comments.scores }分</em></div>
										<div class="commentimes">${comments.create_time }</div>
									</div>
									<div class="commentcont">${comments.comment_con }</div>
										  <c:choose>
												<c:when test="${comments.reply!=null}">
													<div class="commentopera">
														<span>${comments.reply }</span>
														<div class="opera_area">
															<a href="javascript:;" id="${comments.id }"
																class="exchangecomment">修改回复</a> <a href="javascript:;"
																id="${comments.id }" class="commentdel">删除</a>
														</div>
													</div>
												</c:when>
												<c:otherwise>
													<div class="commentopera">
														<div class="opera_area">
															<a href="javascript:;" id="${comments.id }"
																class="exchangecomment">回复</a> <a href="javascript:;"
																id="${comments.id }" class="commentdel">删除</a>
														</div>
													</div>
												</c:otherwise>
											</c:choose>
									  </div>
							</li>
							 </c:forEach>
						</ul>
					</div>
				</div>
				<div class="pager">
						<c:set var="pageUrl"
							value="/coach/detail.action?id=${id }&coachType=${coachType}&type=3&pageNo=pageno"
							scope="request" />
						<jsp:include flush="true" page="/include/pager.jsp" />
					</div>
			</div>
		</div>
	</div>
</div>
<div id="Popup" class="closebox">
    <div id="PopupText">确定要删除么</div>
    <div id="PopupBtn"><span class="PopupBtnyes">确定</span><span class="PopupBtnqx close">取消</span></div>
</div>
<div class="repaly closebox">
    <div><textarea name="" id="" cols="30" rows="10" class="replayText"></textarea></div>
    <div class="replaybtns"><span class="replayno close">取消</span><span class="replayyes">回复</span></div>
</div>
</body>
<script type="text/javascript">
$(function() {

	var COMMENT_ID = 0;
	var COMMENT_CONTENT = '';

	$(".exchangecomment").click(function() {
		showShadow();
		$(".repaly").show();
		COMMENT_CONTENT = $(this).parent().parent().children('span').text();
		$(".replayText").val(COMMENT_CONTENT);
		COMMENT_ID = $(this).attr('id');

});
	//提交回复的修改
	$(".replayyes").click(function() {
		//判空
		if ($(".replayText").val() == '') {
			alert("回复不能为空");
			return false;
		}
		if ($(".replayText").val() == COMMENT_CONTENT) {
			alert("您没有作任何修改");
			$("#shade").remove();
			$(".repaly").hide();
			return false;
		}

		$.ajax({
			type : "post",
			data : {
				action : 'upd',
				commentId : COMMENT_ID,
				commentContent : $(".replayText").val()
			},
			url : "/course/comment/update.action?r=" + Math.random(),
			dataType : "json",
			success : function(res) {
				if (res.status == 1) {
				} else {
					alert('修改失败');
				}
				window.location.reload();
			},
			error : function(res) {
				alert("网络异常，请稍后再试");
			}
		});

	});

	//点击删除评论弹出确认框
	$(".commentdel").click(function() {
		$(this).parents("li").addClass("remove");
		showShadow();
		$("#Popup").show();
		COMMENT_ID = $(this).attr('id');

	});

	//删除评论
	$('.PopupBtnyes').click(function() {

		$.ajax({
			type : "post",
			data : {
				action : 'del',
				commentId : COMMENT_ID
			},
			url : "/course/comment/update.action?r=" + Math.random(),
			dataType : "json",
			success : function(res) {
				if (res.status == 1) {
				} else {
					alert('删除失败');
				}
				window.location.reload();
			},
			error : function(res) {
				alert("网络异常，请稍后再试");
			}
		});

	});
});	


</script>

</html>