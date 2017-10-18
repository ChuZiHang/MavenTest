
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
    <title>上课信息</title>
  <link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>  
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
			<jsp:param name="nvaIndex" value="19" />
			<jsp:param name="nvaParentIndex" value="18" />
		</jsp:include>
		<!--左侧导航：结束-->
		<div class="rightContent">
			<h3 class="i_title">教练上课信息</h3>
			<div class="province" style="height:25px;"></div>
			<div class="item_syllabus_list_tag">
				<span ><a href="/coach/detail.action?id=${id}&coachType=${coachType}">教练信息</a></span>
				<span  class="item_syllabus_list_tag_lie"><a href="#">上课信息</a></span>
				<span ><a href="/coach/coachcomment.action?id=${id}&coachType=${coachType}">评价</a></span>
				<c:choose>
				<c:when test="${coachType==1 }" >
				<span class="ss"><a href="/coach/coachsijiao.action?id=${id}&coachType=${coachType}">私教售卖信息</a></span>
				</c:when>
				<c:otherwise></c:otherwise>
				</c:choose>
			</div>
			<div class="syllabus_detail_cont" style="height:260px; overflow:visible;">
				<div class="statistics">
					<div class="itemSelect">
					   <input type="hidden" id="coachType" value="${coachType}">
						   <c:choose>
								<%--------总店 ------------%>
								<c:when test="${parentType==1 }">
									<span class="itemSelectText" id="${defaultCustId }">${defaultCustName }</span>
									<div class="zhankai"></div>
									<div class="itemSelectList" id=${id }>
										<ul>
											<li id="0">全部</li>
											<c:forEach var="custinfo" items="${custs }">
												<li id="${custinfo.systemId }">${custinfo.custName }</li>
											</c:forEach>
										</ul>
									</div>

								</c:when>
								<%--------分店 ------------%>
								<c:otherwise>
									<span class="itemSelectText" id="${defaultCustId }">${defaultCustName }</span>
									<div class="itemSelectList" id=${id }></div>
								</c:otherwise>
							</c:choose>
						</div>

						<c:choose>
							<c:when test="${alreadyCount>0 }">
								<div class="syllabus_detail_count">
									<ul>
										<li>
											<p class="statistics_title">已上课数</p>
											<p class="statistics_num">
												<span>${alreadyCount }</span>节
											</p>
										</li>
										<li>
											<p class="statistics_title">上课总人数</p>
											<p class="statistics_num">
												<span>${allCount }</span>人
											</p>
										</li>
										<li>
											<p class="statistics_title">预约率</p>
											<p class="statistics_num">
												<span>${bookingRate }</span>%
											</p>
										</li>
										<li>
											<p class="statistics_title">到课率</p>
											<p class="statistics_num">
												<span>${checkRate }</span>%
											</p>
										</li>
									</ul>
								</div>

							</c:when>
							<c:otherwise>
								<div class="syllabus_detail_count">
									<ul>
										<li></li>
										<li>
											<p class="statistics_num">暂无数据</p>
										</li>
									</ul>
								</div>
							</c:otherwise>
						</c:choose>

					</div>
				</div>
			<c:if test="${alreadyCount>0 }">
			<div class="member_item_table">
				<div class="statistics">
					<table cellspacing="0" cellpadding="0" border="0" width="100%" class="lis-table">
						<tbody>
						<tr>
							<th bgcolor="#f3f5f6" height="36">分店</th>
							<th bgcolor="#f3f5f6" height="36">日期</th>
							<th bgcolor="#f3f5f6" height="36">预约人数/限制人数</th>
							<th bgcolor="#f3f5f6" height="36">验证人数</th>
							<th bgcolor="#f3f5f6" height="36">旷课人</th>
						</tr>
					<c:forEach var="data" items="${datas}">
						<tr>
							<td class="text-left">${funcs:getCustNameByCustId(data.system_id )}</td>
							<td align="center"><fmt:formatDate value="${data.end_time }" type="date" pattern="yyyy年MM月dd日 HH:mm:ss " /></td>
							<td align="center">${data.itemBooking>0?data.itemBooking:0}/${data.people_num>0?data.people_num:0}</td>
							<td align="center">${data.itemCheck>0?data.itemCheck:0}</td>
							<td align="center">
								<font class="joiner" >
									<c:choose>

														<c:when test="${fn:length(data.users)>0 }">
															<c:set var="memInfoPer" value="0"></c:set>
															<shiro:hasPermission name="member:detail">
																<c:set var="memInfoPer" value="1"></c:set>
															</shiro:hasPermission>

															<c:forEach var="user" items="${data.users}">
																<c:if test="${memInfoPer==1}">
																	<a href="/member/detail.action?memberId=${user.member_id }" target="_blank"><img src="${user.member_logo }" alt="" /></a>
																</c:if>

																<c:if test="${memInfoPer==0}">
																	<a href="javascript:;" id="noper"><img src="${user.member_logo }" alt="" /></a>
																</c:if>

															</c:forEach>
														</c:when>
														<c:otherwise>
															0
														</c:otherwise>
													</c:choose>
										</font>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			</c:if>
			<input  id="coachType"  type="hidden" >
		</div>
		<div class="btns-area">
			<a href="javascript:void(0)" class="excle">导出</a>
		</div>
    </div>
</div>
<div id="Popup">
    <div id="PopupText">您没有查看会员主页的权限</div>
    <div id="PopupBtn"><span class="PopupBtnyes">确定</span></div>
</div>
</div>
</body>
<script type="text/javascript">
$(function(){
	
$('.itemSelectList li').click(
		function() {
			//alert($(this).attr('class'));
			window.location.href = "/coach/coachclass.action?id="
					+ $('.itemSelectList').attr('id') + "&custId="
					+ $(this).attr('id')+"&coachType="+${coachType};
		});

$('.PopupBtnyes').click(function() {
	//$('body').remove('#shade');
	hideShadow();
	$("#Popup").hide();
});
$(".excle").click(function(){
	window.location.href="/coach/outexcel.action?id="
		+ $('.itemSelectList').attr('id')
		+ "&custId="
		+ $('.itemSelectText').attr('id');
})
})
</script>
</html>				

