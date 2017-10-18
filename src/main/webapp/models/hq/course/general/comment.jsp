<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>课程详情</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>

<script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
</head>
<body class="baseBgColor">
	<!--顶部：开始-->
	<jsp:include page="/include/header.jsp" flush="true">
		<jsp:param name="title" value="后台首页" />
	</jsp:include>
	<!--顶部：结束-->
	<div class="branch_wrap">
		<div class="branch_wrap_cen">
			<!--左侧导航：开始-->
			<jsp:include page="/include/nav_left.jsp" flush="true">
				<jsp:param name="nvaIndex" value="15" />
				<jsp:param name="nvaParentIndex" value="14" />
			</jsp:include>
			<!--左侧导航：结束-->
			<div class="rightContent">
				<h3 class="i_title">课程详情</h3>
				<div class="province" style="height:25px;"></div>
				<div class="item_syllabus_list_tag">
					<c:set var="type" value="${type}" />
					<span class="${(type==1) ? 'item_syllabus_list_tag_lie':'noton'}"><a
						href="detail.action?courseId=${courseId }&type=1">课程信息</a></span> <span
						class="${(type==2) ? 'item_syllabus_list_tag_lie':'noton'}"><a
						href="detail.action?courseId=${courseId }&type=2">预约信息</a> </span> <span
						class="${(type==3) ? 'item_syllabus_list_tag_lie':'noton'}"><a
						href="detail.action?courseId=${courseId }&type=3">评价</a></span>
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
								<c:forEach var="item" items="${comments}">
									<li>
									<shiro:hasPermission name="member:detail">
									<span class="commentHead" id="${item.member_id }" ><img src="${item.member_logo }" id="${item.member_id }" alt="" /></span>
									</shiro:hasPermission>
										<div>
											<div class="userinfor">
												<div class="usernick">
													<span class="nickname">${item.member_nick }</span><em
														class="score">${item.scores }分</em>
												</div>
												<div class="commentimes">${item.create_time }</div>
											</div>
											<div class="commentcont">${item.comment_con }</div>

											<c:choose>
												<c:when test="${item.reply!=null}">
													<div class="commentopera">
														<span>${item.reply}</span>
														<div class="opera_area">
														<shiro:hasPermission name="course/detail:reply">
															<a href="javascript:;" id="${item.id }"
																class="exchangecomment">修改回复</a> <a href="javascript:;"
																id="${item.id }" class="commentdel">删除</a>
																</shiro:hasPermission>
														</div>
													</div>

												</c:when>
												<c:otherwise>
													<div class="commentopera">
														<div class="opera_area">
														<shiro:hasPermission name="course/detail:reply">
															<a href="javascript:;" id="${item.id }"
																class="exchangecomment">回复</a> <a href="javascript:;"
																id="${item.id }" class="commentdel">删除</a>
																</shiro:hasPermission>
														</div>
													</div>

												</c:otherwise>
											</c:choose>

										</div></li>
								</c:forEach>
							</ul>
						</div>

					</div>
					<!-- 分页 开始 -->
					<div class="pager">
						<c:set var="pageUrl"
							value="/course/detail.action?courseId=${courseId }&type=3&pageNo=pageno"
							scope="request" />
						<jsp:include flush="true" page="/include/pager.jsp" />
					</div>
					<!-- 分页 结束 -->
				</div>
			</div>
	</div>
	<div id="Popup" class="closebox">
		<div id="PopupText">确定要删除么</div>
		<div id="PopupBtn">
			<span class="PopupBtnyes">确定</span><span class="PopupBtnqx close">取消</span>
		</div>
	</div>
	<div class="repaly closebox">
		<div>
			<textarea name="" id="" cols="30" rows="10" class="replayText"></textarea>
		</div>
		<div class="replaybtns">
			<span class="replayno close">取消</span><span class="replayyes">回复</span>
		</div>
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
		
		
		 // 取消关闭弹框
	    $(".close").click(function(){
	        $(this).parents(".closebox").hide();
	        $("#shade").remove();
	    });

		
		//点击头像跳转------------------
		
		$('.commentHead').click(function(){
			
			window.location.href="/member/detail.action?memberId="+$(this).attr('id');
			
		});
		
		
		
		

	});
</script>
</html>