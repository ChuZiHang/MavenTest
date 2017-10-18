<%@page import="org.apache.shiro.SecurityUtils"%>
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
<title>启动健后台首页</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css"/>
<script type="text/javascript" src="/static/js/jquery.js"></script>
<!-- <script type="text/javascript" src="/static/js/custom.js"></script> -->
 <script type="text/javascript" src="/static/js/left.js"></script>
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
				<jsp:param name="isFendian" value="1" />
			</jsp:include>
			<!--左侧导航：结束-->

			<div class="rightContent">
				<h3 class="i_title">分店列表</h3>
				<div class="province">
				<a href="/branch/list.action" class="on">全部</a>&nbsp;&nbsp;
				<c:forEach items="${areaMap}" var="map">
				<a href="/branch/list.action?areaId=${map.key}">${map.value}</a>&nbsp;&nbsp;                    
				</c:forEach>
				</div>
				<div class="branch_shop_list">
				<c:forEach var="bean" items="${custInfoList}">
					<dl>
						<dt class="custId"  data-id="${bean.id }" id="${bean.systemId }">
							<img  
							<c:choose>
							<c:when test="${empty bean.signimg}">
							 src="/static/images/moren_qidongjian.jpg"  
							</c:when>
							  <c:otherwise>
							  src="${bean.signimg}" 
                              </c:otherwise>
							</c:choose>
							alt="${bean.custName}" />
						</dt>
						<dd>
							<div class="branch_shop_name" title="${bean.custName}">${bean.custName}</div>
<!-- 							<div class="branch_shop_detail"> -->
<%-- 								<span class="address">${bean.address}</span> --%>
<!-- 								 <span class="times"> -->
<%-- 								  <fmt:formatDate value="${bean.createTime}" var="time" pattern="yyyy年MM月dd日"/>  --%>
<%-- 								${time} --%>
<!-- 								</span> -->
<!-- 							</div> -->
							<c:if test="${bean.status==0 }">
								<div class="branch_shop_detail"><span class="down" id="${bean.id}">下线</span></div>
							</c:if>
							<c:if test="${bean.status==1 }">
								<div class="branch_shop_detail"><span class="up" id="${bean.id}">上线</span></div>
							</c:if>
						</dd>
					</dl>
					</c:forEach>
					   <!-- 分页 开始 -->
				<div class="pager">
					<c:set var="pageUrl"
						value="/branch/list.action?areaId=${areaId}&pageNo=pageno"
						scope="request" />
					<jsp:include flush="true" page="/include/pager.jsp" />
				</div>
			<!-- 分页 结束 -->
				</div>
				<div class="btns-area">
					<a href="/branch/create.action" class="" target="_blank">新建分店</a>
				</div>
			</div>
		</div>
	</div>
	<div class="clear_float"></div>
<%-- 	<!--底部：开始-->
	<jsp:include page="/include/footer.jsp" flush="true">
		<jsp:param name="title" value="1" />
	</jsp:include>
	<!--底部：结束--> --%>
	
	
	
	<script type="text/javascript">
	$(".up").click(function(){
		var result =confirm("上线后将在前端显示该分店，确定要上线吗?");
		if(result){
		$.ajax({
			type :"get",
			url:"/branch/update.action",
			data:{custId:$(this).attr('id'),status:'0'},
	 		dataType : "text",
	 		cache : false,
	 		success : function(res) {
	 			var r = eval('('+res+')');
	 			if(r.status==1){
	 				alert("上线成功！");
	 			}else{
	 				alert("上线失败！");
	 			}
	 			window.location.reload();
				
			},
			error:function(res){
				alert("网络加载失败，请稍后再试！");
			}
		});
		}else{
			
		}
		
	})
	$(".down").click(function(){
		var result =confirm("下线后该分店将不在前端显示，确定要下线吗?");
		if(result){
		$.ajax({
			type :"get",
			url:"/branch/update.action",
			data:{custId:$(this).attr('id'),status:'1'},
	 		dataType : "text",
	 		cache : false,
	 		success : function(res) {
	 			var r = eval('('+res+')');
	 			if(r.status==1){
	 				alert("下线成功！");
	 			}else{
	 				alert("下线失败！");
	 			}
	 			window.location.reload();
				
			},
			error:function(res){
				
				alert("网络加载失败，请稍后再试！");
			}
		});
		
	}else{
		
	}
	})
	
	
	$('.custId').click(function(){
		window.open("/branch/create.action?id="+$(this).attr('data-id')+"&systemId="+$(this).attr('id'));
	});
	
	</script>

</body>
</html>