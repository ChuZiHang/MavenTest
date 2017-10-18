<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>会员详情</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/star.js"></script>
<script type="text/javascript" src="/static/js/file.js"></script>
<script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
<style>
	.stopUseCard{
		top: 20px;
	    right: 20px;
	    line-height: 32px;
	    position: absolute;
	    right: 19px;
	    top: 60px;
	    width: 106px;
	    height: 28px;
	    color: #fff;
	    font-size: 12px;
	    text-align: center;
	    cursor: pointer;
	    border-radius: 5px;
	    line-height: 28px;
        background: #c2c2c2;
	}
	.stopUseCard2{
		top: 20px;
	    right: 20px;
	}
	.stopUseCard3{
		top: 98px;
		right: 20px;
	}
</style>

<script type="text/javascript">
$(function(){
	$(".send_message_butn").click(function(){
		var isAppoint=$(this).attr('title')
		//alert(isAppoint)
		if(isAppoint==1){
			if(	window.confirm('开启约课功能后，该会员将不限制预约课程，确定吗？')){
			$.ajax({
				type :"get",
				url:"/member/updateTure.action",
				data:{id:$(this).attr('id'),isAppoint:$(this).attr('title')},
		 		dataType : "text",
		 		cache : false,
		 		success : function(res) {
		 		
		 			if(res=='1'){
		 				alert("修改成功！");
		 			}else{
		 				alert("修改失败！");
		 			}
		 			window.location.reload();
					
				},
				error:function(res){
					
					alert("网络加载失败，请稍后再试！");
				}
			});
			
			return true;
		}else{
			return false;
		}
	 }else{
		 if(window.confirm('关闭约课功能后，该会员将无法预约课程，确定吗？')){
	
			$.ajax({
				type :"get",
				url:"/member/updateTure.action",
				data:{id:$(this).attr('id'),isAppoint:$(this).attr('title')},
		 		dataType : "text",
		 		cache : false,
		 		success : function(res) {
		 		
		 			if(res=='1'){
		 				alert("修改成功！");
		 			}else{
		 				alert("修改失败！");
		 			}
		 			window.location.reload();
					
				},
				error:function(res){
					
					alert("网络加载失败，请稍后再试！");
				}
			});
			return true;
    	}else{
    		return false;
    	} 
	 }
	})
	
	
	$("#black").click(function(){
		var isBlack=$(this).attr('title')
		if(isBlack==1){
			if(	window.confirm('撤销黑名单后，该会员将回复正常，确定吗？')){
			$.ajax({
				type :"get",
				url:"/member/updateBlack.action",
				data:{id:$(this).attr('data-id'),isBlack:$(this).attr('title')},
		 		dataType : "text",
		 		cache : false,
		 		success : function(res) {
		 		
		 			if(res=='1'){
		 				alert("修改成功！");
		 			}else{
		 				alert("修改失败！");
		 			}
		 			window.location.reload();
					
				},
				error:function(res){
					
					alert("网络加载失败，请稍后再试！");
				}
			});
			
			return true;
		}else{
			return false;
		}
	 }else{
		 if(window.confirm('加入黑名单后，该会员将无法进行任何操作，确定吗？')){
	
			$.ajax({
				type :"get",
				url:"/member/updateBlack.action",
				data:{id:$(this).attr('data-id'),isBlack:$(this).attr('title')},
		 		dataType : "text",
		 		cache : false,
		 		success : function(res) {
		 		
		 			if(res=='1'){
		 				alert("修改成功！");
		 			}else{
		 				alert("修改失败！");
		 			}
		 			window.location.reload();
					
				},
				error:function(res){
					
					alert("网络加载失败，请稍后再试！");
				}
			});
			return true;
    	}else{
    		return false;
    	} 
	 }
		
		
	})
	
	
	
	
		
})

</script>
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
					<jsp:param name="nvaIndex" value="30" />
					<jsp:param name="nvaParentIndex" value="24" />
				</jsp:include>
				<!--左侧导航：结束-->
				<div class="rightContent">
					<h3 class="i_title">会员中心</h3>
					<div class="memberHeader">
					<span class="memberHeader_img"><img
						src="${memberInfo.memberLogo }" alt="" /></span>
					<ul class="member_user_info_list">
						<li>${memberInfo.memberName }</li>
						 <li class="cardtype">${funcs:getProductNameByProductId(productId) }
						 				<c:if test="${startTime != null}">
											<fmt:formatDate value="${startTime}" type="date"
												pattern="yyyy年MM月dd日 " />
											-
											<fmt:formatDate value="${endTime }" type="date"
												pattern="yyyy年MM月dd日 " />
										</c:if></li>
						<li class="carddate">${memberInfo.linkPhone }</li>
					</ul>
					<c:choose>
						<c:when test="${memberInfo.isAppoint==1 }"><span id="${memberId}"  title="${memberInfo.isAppoint}" class="send_message_butn member_detail_mebtn">开启约课功能</span></c:when>
						<c:otherwise><span  id="${memberId}" title="${memberInfo.isAppoint}"  class="send_message_butn member_detail_mebtn open">关闭约课功能</span></c:otherwise>
					</c:choose>
					<shiro:hasPermission name="member:updateBlack">
					
					<c:choose>
						<c:when test="${memberInfo.blacklistStatus==0 }">
							<span data-id="${memberId}" id="black" title="${memberInfo.blacklistStatus}" class="black stopUseCard">永久停卡</span>
						</c:when>
						<c:otherwise>
							<span  data-id="${memberId}" id="black" title="${memberInfo.blacklistStatus}"  class="black stopUseCard stopUseCard" style="background:#f8b500">撤销永久停卡</span>
						</c:otherwise>
					</c:choose>
					
					</shiro:hasPermission>
					
					<shiro:hasPermission name="member:manualPause">
					<a href="/member/pauseinsert.action?memberId=${memberInfo.memberId }"><span  class="black stopUseCard stopUseCard3" >手动停卡</span></a>
					</shiro:hasPermission>
				</div>
					<div class="member_item_table mat25">
	<div class="member_detail_tag">
	<span class="${detailType==1?'on':'' }"><em><a
	href="detail.action?memberId=${memberInfo.memberId}&detailType=1">会员卡</a></em></span>
	<span class="${detailType==2?'on':'' }"><em><a
	href="detail.action?memberId=${memberInfo.memberId}&detailType=2">课程</a></em></span>
	<span class="${detailType==3?'on':'' }"><em><a
	href="detail.action?memberId=${memberInfo.memberId}&detailType=3">评价</a></em></span>
	<span class="${detailType==4?'on':'' }"><em><a
	href="detail.action?memberId=${memberInfo.memberId}&detailType=4">收费团课</a></em></span>
	<span class="${detailType==5?'on':'' }"><em
	class="bordernone"><a
	href="detail.action?memberId=${memberInfo.memberId}&detailType=5">私教</a></em></span>
	<span class="${detailType==6?'on':'' }"><em
	class="bordernone"><a
	href="detail.action?memberId=${memberInfo.memberId}&detailType=6">到店记录</a></em></span>
	<c:if test="${detailType==3 || detailType==4||detailType==5 }">
		<div class="itemSelect comment_select">
		<c:choose>
			<c:when test="${custId==0 }">
				<em class="itemSelectText">全部</em>
			</c:when>
			<c:otherwise>
				<em class="itemSelectText">${funcs:getCustNameByCustId(custId)}</em>
			</c:otherwise>
		</c:choose>

		<div class="zhankai"></div>
		<div class="itemSelectList" style="display: none;">
		<ul>
		<li id="0">全部</li>
		<c:forEach var="item" items="${custIds}">
			<li id="${item.sys_cust_id }">${funcs:getCustNameByCustId(item.sys_cust_id)}</li>
		</c:forEach>
		</ul>
		</div>
		</div>
	</c:if>
	</div>

	<%----------------会员卡----------------%>
	<c:if test="${detailType==1 }">
		<div class="member_detail_con">
		<ul>

		<c:forEach var="card" items="${cards }">
			<li>
			<div class="merber_list_img">
			<img src="${funcs:getProductImgByProductId(card.product_id)}" alt="" />
			</div>
			<div class="merber_list_card">
			<font class="fw700 cor0">${funcs:getProductNameByProductId(card.product_id)}  &nbsp;&nbsp;&nbsp;${funcs:getCustNameByCustId(card.sys_cust_id)}</font>
			<c:if test="${card.start_time != null}">
				<fmt:formatDate value="${card.start_time}" type="date"
								pattern="yyyy年MM月dd日 " />
				-
				<fmt:formatDate value="${card.end_time }" type="date"
								pattern="yyyy年MM月dd日 " />
			</c:if>

			</div>
			<div class="merber_list_buy">
			<c:if test="${card.pay_time!=null }">
				<font class="fw700 cor0">${card.sale_price/100 }元</font>
				<fmt:formatDate value="${card.pay_time }" type="date"
								pattern="yyyy年MM月dd日 " />
				购买

			</c:if>
			</div>
			<div class="merber_list_state">

			<em class="merber_list_state_cl">
			<c:choose>
				<c:when test="${card.is_active==1}">
					${now>card.end_time?'已过期':'使用中' }
				</c:when>

				<c:otherwise>
					<c:if test="${card.status==5}">
						已退款
					</c:if>
					<c:if test="${card.status==6}">
						退款中
					</c:if>
					<c:if test="${card.status==3}">
						已取消
					</c:if>
					<c:if test="${card.status==2}">
						未激活
						<em id="${card.order_id}" class="member_tuikuan member_det_refund">退款</em>
					</c:if>
					<c:if test="${empty card.status}">
						未激活
						<p style="font-size:1px;">兑换码兑换，不能退款</p>
					</c:if>

				</c:otherwise>
			</c:choose>
			</em>

			<c:if test="${card.status==6}">
				<em class="member_det_refund">${card.actual_money}</em>
				<em class="member_det_refund">退款时间：<fmt:formatDate
					value="${card.refund_time }" type="date"
					pattern="yyyy年MM月dd日  HH:mm:ss" /></em>
			</c:if>
			</div>
			</li>

		</c:forEach>
		</ul>
		</div>

	</c:if>

	<%----------------课程----------------%>
	<c:if test="${detailType==2 }">
		<div class="member_detail_con">
		<div class="member_syllabus_title">
		<span>预约课程次数：${booking }</span> <span>上课次数：${check }</span> <span>取消预约次数：${cancel }</span>
		<span>旷课次数：${absent }</span>
		</div>
		<ul>
		<c:forEach var="course" items="${courses }">

			<c:set var="courseId" value="${funcs:getCourseIdByCoursePriceId(course.productId)}" />
			<li>
			<div class="merber_list_img">
			<img src="${funcs:getCourseImgByCourseId(courseId)}"
			alt="" />
			</div>
			<div class="merber_list_card">
			<font class="fw700 cor0">${funcs:getCourseNameByCourseId(course.productId)}</font>
			<fmt:formatDate value="${course.startTime }" type="date"
							pattern="yyyy年MM月dd日  HH:mm:ss" />
			-
			<fmt:formatDate value="${course.endTime }" type="date"
							pattern="HH:mm:ss" />
			</div>
			<div class="merber_list_yy">
			<font class="fw700 cor0">${funcs:getCustNameByCustId(course.sysCustId)}</font>预约时间：
			<fmt:formatDate value="${course.creteTime }" type="date"
							pattern="yyyy年MM月dd日  HH:mm:ss" />
			</div>
			<div class="merber_list_yz"></div>
			<div class="merber_list_state2">
			<em class="merber_list_state_cl"> <c:choose>
			<c:when test="${course.status==2 && course.endTime<now}">
				已旷课
			</c:when>
			<c:otherwise>
				<c:if test="${course.status==2 }">
					已预约
				</c:if>
				<c:if test="${course.status==3}">
					已取消
				</c:if>
				<c:if test="${course.status==4}">
					已完成
				</c:if>
			</c:otherwise>
		</c:choose>
			</em>
			</div>
			</li>
		</c:forEach>
		</ul>
		</div>
	</c:if>

	<%----------------评价----------------%>
	<c:if test="${detailType==3 }">
		<div class="member_comment_cont">
		<div class="commentlist member_cont_li">
		<ul>
		<c:forEach var="comment" items="${comments}">
			<li><span class="member_commentHead"> <c:choose>
			<c:when test="${comment.objectType==12 }">
				<img
				src="${funcs:getCourseImgByCourseId(comment.objectId )}"
				alt="" height="84" width="128">
			</c:when>
			<c:otherwise>
				<img
				src="${funcs:getProductImgByProductId(comment.objectId)}"
				alt="" height="84" width="128">
			</c:otherwise>
		</c:choose>


			</span>
			<div>
			<div class="userinfor">
			<div class="usernick">
			<span class="nickname">
			<c:choose>
				<c:when test="${comment.objectType==12 }">
					${funcs:getCourseNameByCourseId(comment.objectId )}
				</c:when>
				<c:otherwise>
					${funcs:getProductNameByProductId(comment.objectId )}
				</c:otherwise>
			</c:choose>



			</span> <em class="score">${comment.scores }分</em>
			</div>
			<div class="commentimes">
			<fmt:formatDate value="${comment.createTime }" type="date"
							pattern="yyyy年MM月dd日  HH:mm:ss" />
			</div>
			</div>
			<div class="commentcont">${comment.commentCon }</div>
			<div class="commentopera">
			<c:if test="${fn:length(comment.reply) > 0 }">
				<span>${comment.reply }</span>
			</c:if>

			</div>
			</div>
			<div class="opera_area">
			<a href="javascript:;" id="${comment.id }"
			class="exchangecomment">回复</a><a href="javascript:;"
			id="${comment.id }" class="commentdel">删除</a>
			</div></li>

		</c:forEach>

		</ul>
		</div>
		</div>

	</c:if>

	<%----------------团课----------------%>
	<c:if test="${detailType==4 }">
		<div class="member_detail_con">
		<ul>
		<c:forEach var="group" items="${ groups}">
			<li>
			<div class="merber_list_img">
			<img src="${funcs:getProductImgByProductId(group.productId)}"
			alt="" />
			</div>
			<div class="merber_list_card">
			<font class="fw700 cor0">
			${funcs:getProductNameByProductId(group.productId)}</font>
			<fmt:formatDate value="${group.startTime }" type="date"
							pattern="yyyy年MM月dd日  HH:mm:ss" />
			-
			<fmt:formatDate value="${group.endTime }" type="date"
							pattern="yyyy年MM月dd日  HH:mm:ss" />
			</div>
			<div class="merber_list_yy">
			<font class="fw700 cor0">${group.salePrice/100 }元</font>
			</div>
			<div class="merber_list_yz">
			<font class="fw700 cor0">
			${funcs:getCustNameByCustId(group.sysCustId)} </font> 报名时间：
			<fmt:formatDate value="${group.creteTime }" type="date"
							pattern="yyyy年MM月dd日  HH:mm:ss" />
			</div>
			<div class="merber_list_state2">

			<c:choose>

				<c:when test="${group.status==5}">
					<em class="merber_list_state_cl">已退款</em>
					<em class="member_det_refund">退款${group.actualMoney/100}元</em>
					<em class="member_det_refund">退款时间：<fmt:formatDate
						value="${group.refundTime }" type="date"
						pattern="yyyy年MM月dd日  " /></em>
				</c:when>

				<c:when test="${group.status==6}">
					<em class="merber_list_state_cl">退款申请中</em>
				</c:when>

				<c:otherwise>
					<c:if test="${group.status==2}">
						<c:if test="${group.startTime>now }">
							<em class="merber_list_state_cl">未开始</em>
							<em id="${group.id}"
							class="member_tuikuan member_det_refund">退款</em>
						</c:if>
						<c:if test="${group.startTime<now &&  group.endTime>now}">
							<em class="merber_list_state_cl">进行中</em>
							<em class="merber_list_state_qd">签到${group.orderCount-group.remainCount }次</em>
							<em class="member_det_refund qiandao" id="${group.id}">签到详情</em>
						</c:if>

					</c:if>

					<c:if test="${group.status==4 || group.endTime<now}">
						<em class="merber_list_state_cl">已完成</em>
					</c:if>
				</c:otherwise>
			</c:choose>
			</div>
			<div class="sign"
			style="display: none; float: right; margin-top: 8px"></div>
			</li>
		</c:forEach>
		</ul>
		</div>

	</c:if>


	<%----------------私教----------------%>
	<c:if test="${detailType==5 }">
		<div class="member_detail_con">
		<ul>

		<c:forEach var="coach" items="${coachs }">
			<c:if test="${coach.status==2||coach.status==3||coach.status==4||coach.status==5||coach.status==6 }">
				<li>
				<div class="merber_list_img">
				<img src="${funcs:getCoachImgById(coach.productId)}"
				alt="" />
				</div>
				<div class="merber_list_card">
				<p class="member_coach_price">
				<span class="member_coach_price_name">

				${funcs:getCoachNameById(coach.productId)} </span> <span
				class="member_coach_price_num">${coach.salePrice/100 }元</span>
				</p>
				<p class="member_coach_time">
				购买时间：
				<fmt:formatDate value="${coach.creteTime }" type="date"
								pattern="yyyy年MM月dd日  HH:mm:ss" />
				</p>
				</div>
				<div class="merber_list_buy">
				<div>
				<p class="member_coach_price">
				<span class="member_coach_price_name">上课地点</span>
				</p>
				<p class="member_coach_time">

				${funcs:getCustNameByCustId(coach.sysCustId)}</p>
				</div>
				<div>
				<p class="member_coach_price">
				<span class="member_coach_price_name">剩余课时</span>
				</p>
				<p class="member_coach_time">${coach.remainCount }</p>
				</div>
				<div>
				<p class="member_coach_price">
				<span class="member_coach_price_name">已上课时</span>
				</p>
				<p class="member_coach_time">${coach.orderCount-coach.remainCount }</p>
				</div>
				</div>
				<div class="merber_list_state">
				<c:choose>
					<c:when test="${coach.status==5}">
						<em class="merber_list_state_cl">已退款</em>
						<em class="j_xiangqing member_det_refund" id="${coach.id }">上课详情</em>
						<em class="member_det_refund">退款${coach.actualMoney/100}元</em>
						<em class="member_det_refund">退款时间：<fmt:formatDate
							value="${coach.refundTime }" type="date"
							pattern="yyyy年MM月dd日  " /></em>
					</c:when>
					<c:when test="${coach.status==6}">
						<em class="merber_list_state_cl">退款申请中</em>
					</c:when>
					<c:otherwise>
						<c:if test="${coach.remainCount>0 }">
							<em class="merber_list_state_cl">待上课</em>
							<em id="${coach.id}"
							class="member_tuikuan member_det_refund">退款</em>
							<em class="j_xiangqing member_det_refund" id="${coach.id }">上课详情</em>

						</c:if>

						<c:if test="${coach.remainCount==0 }">
							<em class="merber_list_state_cl">已完成</em>
							<em class="j_xiangqing member_det_refund" id="${coach.id }">上课详情</em>
						</c:if>


					</c:otherwise>
				</c:choose>

				</div>
				</li>
				<div class="sign " style="float: right; margin-top: 8px">

				</div>
			</c:if>
		</c:forEach>
		</ul>
		</div>
	</c:if>
	<%----------------到店记录----------------%>
	<c:if test="${detailType==6}">
		<style>
		.b-table-con td{
		border-left:1px solid #e5e5e5;
		border-bottom:1px solid #e5e5e5;
		padding:8px;
		}
		.b-table-con{
		margin:25px auto;
		border:1px solid #e5e5e5;
		border-left:none;
		border-bottom:none;
		}
		</style>
		<div class="member_detail_con">
		<c:choose>
			<c:when   test="${openlog.size()>0 }">
				<table border='0' cellspacing="0" cellpadding="0" width="80%"  class="b-table-con">
				<tr >
				<th style="text-align: center" bgcolor="#f2f2f2" height="38">门店</th>
				<th style="text-align: center" bgcolor="#f2f2f2" height="38">会员</th>
				<th style="text-align: center" bgcolor="#f2f2f2" height="38">会员ID</th>
				<th style="text-align: center" bgcolor="#f2f2f2" height="38">开门时间</th>
				<th style="text-align: center" bgcolor="#f2f2f2" height="38">开门状态</th>

				</tr>

				<c:forEach  var="openlog"  items="${openlog }">
					<tr >
					<td height="30" align="center">${funcs:getCustNameByCustId(openlog.sysCustId+"")}</td>
					<td height="30" align="center">${funcs:getmemberNameBymemberId(openlog.memberId+"")}</td>
					<td height="30" align="center">${openlog.memberId }</td>
					<td height="30" align="center"><fmt:formatDate value="${openlog.openTime }" pattern="yyyy-MM-dd   HH:mm:ss"/></td>
					<td height="30" align="center">
					<c:if test="${openlog.status  eq  0}">开门成功</c:if>
					<c:if test="${openlog.status  eq  1}">开门失败</c:if>
					</td>
					</tr>
				</c:forEach>
				</table>
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
		</div>
	</c:if>
	</div>
				</div>

			</div>
		</div>
		<!-- 分页 开始 -->
		<div class="pager">
			<c:set var="pageUrl"
				value="detail.action?pageNo=pageno&detailType=${detailType}&memberId=${memberInfo.memberId}"
				scope="request" />
			<jsp:include flush="true" page="/include/pager.jsp" />
		</div>
		<!-- 分页 结束 -->

		<!--底部：开始-->
		<%-- 		<jsp:include page="/include/footer.jsp" flush="true"> --%>
		<%-- 			<jsp:param name="title" value="1" /> --%>
		<%-- 		</jsp:include> --%>
		<!--底部：结束-->

	</div>

	<div class="repaly closebox">
		<div>
			<textarea name="" id="" cols="30" rows="10" class="replayText"></textarea>
		</div>
		<div class="replaybtns">
			<span class="replayno close">取消</span><span class="replayyes">回复</span>
		</div>
	</div>

	<div class="sendMessage closebox">
		
		<div class="groupMessageBox">
			<div class="member_user_info">
				<span class="member_user_info_head"><img
					src="${memberInfo.memberLogo }" alt="" /></span>
				<ul class="member_user_info_list">
					<li>用户名</li>
					<%-- 					<li class="cardtype"><fmt:formatDate --%>
					<%-- 							value="${memberInfo.startTime }" type="date" --%>
					<%-- 							pattern="yyyy年MM月dd日 " /> - <fmt:formatDate --%>
					<%-- 							value="${memberInfo.endTime }" type="date" pattern="yyyy年MM月dd日 " /></li> --%>
					<li id="mobile" class="carddate">${memberInfo.linkPhone }</li>
				</ul>
			</div>
			<div class="fillMessage">
				短信内容（每条短信可输入57字，可输入标点符号和空格） <span class="fillMessageTips">
					已输入 <em class="fillNumber">0</em> 字
				</span>
			</div>
			<div>
				<textarea name="" class="messText" cols="30" rows="10"
					style="width: 706px; height: 180px;"></textarea>
			</div>
			<div class="groundmessbtn">
				<em class="create_syllabus_qx close">取消</em> <em
					class="create_syllabus_fs">发送</em>
			</div>
		</div>
	</div>




	<div class="merber_refund closebox">
		<h3 class="create_syllabus_title">退款</h3>
		<div class="refund-apply-box">
			<ul class="">
				<li class="refund_header"><span><img
						src="${memberInfo.memberLogo }" alt=""></span></li>
				<li><span>姓名：</span>${memberInfo.memberName }</li>
				<li><span>电话：</span>${memberInfo.linkPhone }</li>
				<%-- 				<li><span>时间：</span> <fmt:formatDate --%>
				<%-- 						value="${memberInfo.startTime }" type="date" --%>
				<%-- 						pattern="yyyy年MM月dd日 " /> - <fmt:formatDate --%>
				<%-- 						value="${memberInfo.endTime }" type="date" pattern="yyyy年MM月dd日 " /></li> --%>
				<li id="rPrice"></li>
				<li id="rFee"></li>
				<c:if test="${detailType!=4}">
					<li><span>退款规则：</span>扣除已消费月份（取整）</li>
					<li id="rMonth"></li>
				</c:if>
				<li id="rRefund"></li>
				<li><span>实际退款：</span><input type="text" class="refund">
					元</li>
			</ul>
			<div class="refund_btns">
				<span class="cencle close">取消</span> <span class="confirm" id="">申请退款</span>
			</div>
		</div>
	</div>
	<div id="Popup" class="closebox" style="display: none;">
		<div id="PopupText">删除成功</div>
		<div id="PopupBtn">
			<span class="PopupBtnqx close">确定</span>
		</div>
	</div>
</body>
<script type="text/javascript">

	//会员管理列表签到信息展示隐藏
	$(".qiandao")
			.click(
					function() {

						var one = $(this);

						$.ajax({
									cache : true,
									type : "POST",
									url : "/member/detail/sign.action",
									data : {
										orderId : $(this).attr('id')
									},
									dataType : "json",
									success : function(res) {
										console.log(res);
										if (res.data.length > 0) {
											var html = "";
											for (var i = 0; i < res.data.length; i++) {
												html += "<em>第"
														+ (i + 1)
														+ "次："
														+ new Date(
																res.data[i]['finishTime'])
																.toLocaleDateString()
														+ "</em>";
											}
											one.parents("li").find(".sign")
													.html(html);
											one.parents("li").find(".sign")
													.toggle();
										}

									},
									error : function(res) {

									}
								});

					});

	// 添加签到详细信息展示
	$(".j_xiangqing")
			.on(
					"click",
					function() {

						var one = $(this);

						$
								.ajax({
									cache : true,
									type : "POST",
									url : "/member/detail/sign.action",
									data : {
										orderId : $(this).attr('id')
									},
									dataType : "json",
									success : function(res) {
										console.log(res);
										if (res.data.length > 0) {
											var html = "";
											for (var i = 0; i < res.data.length; i++) {
												html += "<em>第"
														+ (i + 1)
														+ "次："
														+ new Date(
																res.data[i]['finishTime'])
																.toLocaleDateString()
														+ "</em>";
											}
											one.parents("li").next().html(html);
											one.parents("li").next().toggle();

										}

									},
									error : function(res) {

									}
								});

					});

	// 课程详细选择框
	$(".itemSelect").click(function() {
		$(".itemSelectList").show();
		return false;
	});

	// 课程详细选择框选择列表文字
	$(".itemSelectList li").click(function() {
		var itemSelectListText = $(this).text();
		$(".itemSelectList").hide();
		$(".itemSelectText").text(itemSelectListText);

		var url = '';
		$('.member_detail_tag span').each(function() {
			if ($(this).attr('class') == 'on') {
				url = $(this).children('em').children('a').attr('href');
			}
		});

		window.location.href = url + '&custId=' + $(this).attr('id');
		return false;
	});

	var COMMENT_ID = 0;
	var COMMENT_CONTENT = '';

	$(".exchangecomment").click(
			function() {
				showShadow();
				$(".repaly").show();
				COMMENT_CONTENT = $(this).parent().parent().find(
						'.commentopera span').text();
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
		COMMENT_ID = $(this).attr('id');

		console.log("----id----"+COMMENT_ID);
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
					alert("删除成功");
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

	//删除评论
// 	$('.PopupBtn').click(function() {

// 		$.ajax({
// 			type : "post",
// 			data : {
// 				action : 'del',
// 				commentId : COMMENT_ID
// 			},
// 			url : "/course/comment/update.action?r=" + Math.random(),
// 			dataType : "json",
// 			success : function(res) {
// 				if (res.status == 1) {
// 				} else {
// 					alert('删除失败');
// 				}
// 				window.location.reload();
// 			},
// 			error : function(res) {
// 				alert("网络异常，请稍后再试");
// 			}
// 		});

// 	});

	// 取消关闭弹框
	$(".close").click(function() {
		$(this).parents(".closebox").hide();
		$("#shade").remove();
	});

	// 文本框字符字数
	$(".messText").on("keyup", function() {
		var str = $(this).val();
		var len = str.length;
		if (len >= 57) {
			$(this).parents(".groupMessageBox").find(".fillNumber").text(57);
			$(this).val(str.substring(0, 57))
		} else {
			$(this).parents(".groupMessageBox").find(".fillNumber").text(len);
		}
	});

	// 会员管理发送短信弹出框
	$(".send_mess").click(function() {
		showShadow();
		$('.messText').val('');
		$(".sendMessage").show();
	});

	var REFUND = 0;
	// 会员管理退款弹出框
	$(".member_tuikuan")
			.click(
					function() {

						console.log($(this).attr('id'));

						$
								.ajax({
									type : "post",
									data : {
										id : $(this).attr('id'),
									},
									url : "/member/coach/detail.action?r="
											+ Math.random(),
									dataType : "json",
									success : function(res) {
										console.log(res);
										var data = res.data.orderInfo;
										var fee = res.data.fee;
										$('#rPrice')
												.append(
														'<span>购买价：</span>'
																+ (data.salePrice / 100)
																+ "元");

										console.log(data.salePrice);
										console.log(data.orderCount==undefined);
										console.log(data.remainCount);
										var refund=0;
										if(data.orderCount!=undefined){
											refund = parseInt(data.salePrice) / parseInt(data.orderCount) * parseInt(data.remainCount);
										}else{
											refund = parseInt(data.salePrice);
										}
										
										$('#rFee').append('<span>手续费：</span>'
																+ Math.round(refund / 100 * 0.3)
																+ "元");

										REFUND = Math.round(refund * (1 - fee / 100));
										$('#rRefund').append(
												'<span>建议退款：</span>'
														+ (REFUND / 100) + '元');

										$('.confirm').attr('id', data.id);
									},
									error : function(res) {
										alert("网络异常，请稍后再试");
									}
								});

						showShadow();
						$(".merber_refund").show();
					});

	// 确定退款
	$(".confirm").click(function() {

		$.ajax({
			type : "post",
			data : {
				id : $(this).attr('id'),
				//实际退款
				money : $('.refund').val(),
				//建议退款
				refund : REFUND
			},
			url : "/member/coach/refund.action?r=" + Math.random(),
			dataType : "json",
			success : function(res) {
				console.log(res);
				if (res.status == 1) {
					alert('退款成功！');
					window.location.reload();
				}

			},
			error : function(res) {
				alert("网络异常，请稍后再试");
			}
		});

		$(this).parents(".closebox").hide();
		$("#shade").remove();
	});

	// 会员管理退款弹出框
	$(".create_syllabus_fs").click(function() {
		if ($('.messText').val() == '') {
			alert('发送内容不能为空');
			return false;
		}

		if ($('#mobile').text() == '') {
			alert('系统错误，请稍后再试');
			return false;
		}

		var one = $(this);

		$.ajax({
			type : "post",
			data : {
				mobile : $('#mobile').text(),
				content : $('.messText').val()
			},
			url : "/member/detail/sendmsg.action?r=" + Math.random(),
			dataType : "json",
			success : function(res) {
				console.log(res);
				if (res.status == 1) {
					alert('发送成功！');
				} else {
					alert("网络异常，请稍后再试");
				}
				one.parents(".closebox").hide();
				$("#shade").remove();
			},
			error : function(res) {
				alert("网络异常，请稍后再试");
			}
		});

	});
</script>


</html>
