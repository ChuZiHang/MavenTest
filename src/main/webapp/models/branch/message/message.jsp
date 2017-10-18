<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>消息列表</title>
    <link type="text/css" rel="stylesheet" href="/static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/left.css"/>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/base.js"></script>
    <script type="text/javascript" src="/static/js/star.js"></script>
    <script type="text/javascript" src="/static/js/left.js"></script>
    <script type="text/javascript" src="/static/js/file.js"></script>
    <script type="text/javascript" src="/static/js/Calendar.js"></script>
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
			<jsp:param name="nvaIndex" value="40" />
			<jsp:param name="nvaParentIndex" value="0" />
		</jsp:include>
		<!--左侧导航：结束-->
        <div class="rightContent">
            <h3 class="i_title">消息</h3>
            <div class="message_list">
                <ul>
                <c:forEach items="${messageList}" var="bean">
                    <li>
                        <div class="notice">通知：<span class="yidu">已读</span></div>
                        <div class="noticeText">
                            <div class="noticeTextBox">
                               ${bean.message }
                            </div>
                            <div class="tag">
                            	<fmt:formatDate value="${bean.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </div>
                            <div class="messageshow"><span class="messopera">展开</span></div>
                        </div>
                    </li>
                   </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="clear_float"></div>
</body>
</html>