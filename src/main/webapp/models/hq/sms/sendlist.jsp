<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>短信记录</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
</head>
<body class="baseBgColor">
	<div class="head">
		<!--顶部：开始-->
		<jsp:include page="/include/header.jsp" flush="true">
			<jsp:param name="title" value="后台首页" />
		</jsp:include>
		<!--顶部：结束-->
	</div>
	<div class="branch_wrap">
		<div class="branch_wrap_cen">
			<!--左侧导航：开始-->
			<jsp:include page="/include/nav_left.jsp" flush="true">
				<jsp:param name="nvaIndex" value="80" />
				<jsp:param name="nvaParentIndex" value="79" />
			</jsp:include>
			<!--左侧导航：结束-->
	 <div class="rightContent">
            <h3 class="i_title">短信记录</h3>		
			 <div class="rightContent">
            <div class="member_item_table" style="margin-top:25px;">
                <table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
                    <tr>
                        <th bgcolor="#f3f5f6" height="36" width="10%">手机号</th>
                        <th bgcolor="#f3f5f6" height="36" width="20%">发送时间</th>
                        <th bgcolor="#f3f5f6" height="36" width="10%">发送状态</th>
                        <th bgcolor="#f3f5f6" height="36" width="10%">商户ID</th>
                        <th bgcolor="#f3f5f6" height="36" width="50%">短信内容</th>
                    </tr>
                    <c:forEach  var="list" items="${list }">
                        <tr>
                            <td>${list.mobile }</td>
                            <td><fmt:formatDate value="${list.sendTime }" pattern="yyyy-MM-dd  HH:mm"/></td>
                            <td>${list.sendStatus}</td>
                            <td>${funcs:getCustNameByCustId(list.custId )}</td>
                            <td>${list.msg }</td>
                        </tr>
                    </c:forEach>
                </table>
                <div style="text-align:left;font-size:14px; padding-top:15px;">共计:<font color="#f8b500"  >${pageCount }</font>条</div>
            </div>
 					<!-- 分页 开始 -->
						<div class="pager">
							<c:set var="pageUrl"
								value="/sendMessage/send.action?pageNo=pageno"
								scope="request" />
							<jsp:include flush="true" page="/include/pager.jsp" />
						</div>
					<!-- 分页 结束 -->
            <div class="clear_float"></div>
         </div>
       </div>   