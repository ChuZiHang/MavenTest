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
<title>购买记录</title>
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
				<jsp:param name="nvaIndex" value="82" />
				<jsp:param name="nvaParentIndex" value="79" />
			</jsp:include>
			<!--左侧导航：结束-->
        <div class="rightContent">
            <h3 class="i_title">
                短信购买记录
            </h3>
            <div class="buyMess" style="margin-top:25px;">
    <table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
        <tr>
            <th bgcolor="#f3f5f6" height="36">订单号</th>
            <th bgcolor="#f3f5f6" height="36">数量</th>
            <th bgcolor="#f3f5f6" height="36">时间</th>
            <th bgcolor="#f3f5f6" height="36">操作人</th>
        </tr>
    <c:forEach var="order" items="${datas }" >
        <tr>
        <td class="cardCont_rem">${order.id }</td>
        <td class="cardCont_rel">${order.productNum }</td>
        <td class="cardCont_buy"><fmt:formatDate value="${order.payDate }" type="date"
                                                   pattern="yyyy年MM月dd日 " /></td>
        <td class="cardCont_sta"> ${funcs:getCustNameByCustId(order.custId)}</td>
        </tr>
    </c:forEach>
    </table>
                   <!-- 分页 开始 -->
		<div class="pager">
			<c:set var="pageUrl"
				value="orderlist.action?pageNo=pageno"
				scope="request" />
			<jsp:include flush="true" page="/include/pager.jsp" />
		</div>
		<!-- 分页 结束 -->
            </div>
         
            <div class="clear_float"></div>
        </div>
    </div>
</div>
</body>
</html>

