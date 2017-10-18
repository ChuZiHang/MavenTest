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
<title>申请列表</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>

<script type="text/javascript" src="/static/js/file.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
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
				<jsp:param name="nvaIndex" value="204" />
				<jsp:param name="nvaParentIndex" value="24" />
			</jsp:include>
			<!--左侧导航：结束--> 
			<div class="rightContent">
				<h3 class="i_title">停卡申请列表</h3>
<!-- 				<div class="search_area"> -->
<!-- 					<div class="member_item_list"> -->
<!-- 						<span class="member_item_title">会员卡</span> -->
<!-- 						<div class="chooseshop"> -->
<%-- 							<p class="checkCard" id="${defaultCardId }">${defaultCardName }</p> --%>
<!-- 							<i></i> -->
<!-- 							<div class="choose_gev_shop" id="cardList" style="display: none;"> -->
<!-- 							<p id="0">全部</p> -->
<%-- 								<c:forEach var="cardInfo" items="${memberCards }"> --%>
<%-- 									<p id="${cardInfo.id }">${cardInfo.cardName }</p> --%>
<%-- 								</c:forEach> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="member_item_list"> -->
<!-- 						<span class="member_item_title">分店</span> -->
<!-- 						<div class="chooseshop"> -->
<%-- 							<p class="checkCust" id="${defaultCustId }">${defaultCustName }</p> --%>
<!-- 							<i></i> -->
<!-- 							<div class="choose_gev_shop" id="custList" style="display: none;"> -->
<!-- 							<p id="0">全部</p> -->
<%-- 								<c:forEach var="custInfo" items="${custinfos }"> --%>
<%-- 									<p id="${custInfo.systemId }">${custInfo.custName }</p> --%>
<%-- 								</c:forEach> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="member_item_list"> -->
<!-- 						<span class="member_item_title">状态</span> -->
<!-- 						<div class="chooseshop"> -->
<%-- 							<p class="checkStatus" id="${defaultStatus }">${defaultStatusName }</p> --%>
<!-- 							<i></i> -->
<!-- 							<div class="choose_gev_shop" id="statusList" -->
<!-- 								style="display: none;"> -->
<!-- 								<p id="0">全部</p> -->
<!-- 								<p id="1">已开通</p> -->
<!-- 								<p id="2">已过期</p> -->
<!-- 								<p id="3">已退款</p> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="member_item_list"> -->
<!-- 					<span class="member_item_title">关键字</span> -->
<!-- 						<input id="keyword" type="text" placeholder="请输入姓名或手机号" -->
<!-- 							class="search" /> -->
<!-- 					</div> -->
<!-- 					<div class="member_item_list"> -->
<!-- 						<a href="javascript:;" class="searchBtns">搜索</a> -->
<!-- 					</div> -->
<!-- 				</div> -->


				<div class="member_item_table" style="clear:both">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
						<tr>
							<th bgcolor="#f3f5f6" height="36">用户id</th>
							<th bgcolor="#f3f5f6" height="36">用户名</th>
							<th bgcolor="#f3f5f6" height="36">开始时间</th>
							<th bgcolor="#f3f5f6" height="36">结束时间</th>
							<th bgcolor="#f3f5f6" height="36">操作</th>
						</tr>

		

			<c:forEach var="info" items="${infos }">

			
					<tr>
					<td class="member-user-header">${info.memberId }</td>
					<td>${funcs:getmemberNameBymemberId(info.memberId) } </td>

					
					<td>
					<fmt:formatDate value="${info.startDate }" type="date" pattern="yyyy年MM月dd日 " />
					</td>
					<td>
					<fmt:formatDate value="${info.endDate }" type="date" pattern="yyyy年MM月dd日 " />
					</td>
					<td>
					<c:choose>
						<c:when test="${info.status==1 }">
							<a href="javascript:;" >已通过</a>
						</c:when>
						<c:when test="${info.status==2 }">
							<a href="javascript:;" >已取消</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:;" id="${info.id }" data-id="${info.memberId }" class="cancel">取消</a>
							<a href="javascript:;" id="${info.id }" data-id="${info.memberId }" class="sub">通过</a>
						</c:otherwise>
					</c:choose>
					

					</td>
					</tr>
				
			</c:forEach>
		
	
					</table>
				</div>
				<div class="clear_float"></div>
			</div>
		<!-- 分页 开始 -->
		<div class="pager">
			<c:set var="pageUrl" value="pauselist.action?pageNo=pageno" scope="request" />
			<jsp:include flush="true" page="/include/pager.jsp" />
		</div>
		<!-- 分页 结束 -->
		</div>
		
	</div>
	
</body>
<script type="text/javascript">

$(".cancel").click(function(){
	$.ajax({
		type :"get",
		url:"/member/updatePause.action",
		data:{id:$(this).attr('id'),status:2,memberId:$(this).attr('data-id')},
 		dataType : "json",
 		cache : false,
 		success : function(res) {

 			alert(res.msg);
			window.location.reload();
		},
		error:function(res){
			
			alert("网络加载失败，请稍后再试！");
		}
	});
});


$(".sub").click(function(){
	$.ajax({
		type :"get",
		url:"/member/updatePause.action",
		data:{id:$(this).attr('id'),status:1,memberId:$(this).attr('data-id')},
 		dataType : "json",
 		cache : false,
 		success : function(res) {
 			alert(res.msg);
			//window.location.reload();
			console.log(res);
		},
		error:function(res){
			alert("网络加载失败，请稍后再试！");
		}
	});
});

</script>
</html>