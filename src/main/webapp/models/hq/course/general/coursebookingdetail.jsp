<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
					
							<div class="member_item_table">
								<table cellspacing="0" cellpadding="0" border="0" width="100%" class="lis-table">
									<tbody>
										<tr>
											<th bgcolor="#f3f5f6" height="36">用户名</th>
											<th bgcolor="#f3f5f6" height="36">手机号</th>
											<th bgcolor="#f3f5f6" height="36">预约时间</th>
											<th bgcolor="#f3f5f6" height="36">开始时间</th>
											<th bgcolor="#f3f5f6" height="36">结束时间</th>
											<th bgcolor="#f3f5f6" height="36">状态</th>
										</tr>
										<c:forEach items="${orders }" var="orderInfo">
											<tr>
												<td><a href="/member/detail.action?memberId=${orderInfo.memberId }" target="_blank"> ${funcs:getmemberNameBymemberId(orderInfo.memberId )}</a></td>
												<td>
												${orderInfo.linkPhone }
												</td>
												<td>
												<fmt:formatDate value="${orderInfo.creteTime }" type="date" pattern="yyyy年MM月dd日 " />
												</td>
												<td>
												<fmt:formatDate value="${orderInfo.startTime }" type="date" pattern="yyyy年MM月dd日 " />
												</td>
												<td>
												<fmt:formatDate value="${orderInfo.endTime }" type="date" pattern="yyyy年MM月dd日 " />
												</td>
												
												<td>
													<c:choose>
														<c:when test="${orderInfo.status==2 }">
															<c:if test="${orderInfo.endTime<now }">
																已旷课
															</c:if>
															<c:if test="${orderInfo.endTime>=now }">
																预约成功
															</c:if>
														
														</c:when>
														<c:when test="${orderInfo.status==3 }">
															已取消
														</c:when>
														<c:when test="${orderInfo.status==4 }">
															已完成
														</c:when>
													
													</c:choose>
													
												</td>
												
											</tr>
										
										</c:forEach>
											

									</tbody>
								</table>
							</div>

			</div>
			<div class="btns-area"><a href="javascript:;" id="${coursePriceId }" class="excle">导出</a></div>
		</div>
	</div>
	<div id="Popup">
		<div id="PopupText">您没有查看会员主页的权限</div>
		<div id="PopupBtn">
			<span class="PopupBtnyes">确定</span>
		</div>
	</div>
</body>

<script type="text/javascript">
	$('.itemSelectList li').click(
			function() {
				//alert($(this).attr('class'));
				window.location.href = "detail.action?type=2&courseId="
						+ $('.itemSelectList').attr('id') + "&custId="
						+ $(this).attr('id');
			});

	$('.PopupBtnyes').click(function() {
		//$('body').remove('#shade');
		hideShadow();
		$("#Popup").hide();
	});
	
	 // 点击头像查看个人中心
    $("#noper").click(function(){
       showShadow();
       $("#Popup").show();
    });

 // 课程详细选择框
    $(".itemSelect").click(function(){
        $(".itemSelectList").show();
        return false;
    });
	 
	

	$('.excle').click(function() {
		window.location.href = "/course/bookingdetail/outexcel.action?coursePriceId="+$(this).attr("id");
		});
</script>

</html>