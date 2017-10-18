<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
  <title>优惠券详情</title>
    <link type="text/css" rel="stylesheet" href="/static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/left.css"/>
    <script type="text/javascript" src="/static/js/left.js"></script>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/base.js"></script>
    <script type="text/javascript" src="/static/js/custom.js"></script>
    <script type="text/javascript" src="/static/js/star.js"></script>
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
				<jsp:param name="nvaIndex" value="29" />
				<jsp:param name="nvaParentIndex" value="23" />
			</jsp:include>
			<!--左侧导航：结束-->
			<div class="rightContent">
				<h3 class="i_title">优惠券详情</h3>
				<div class="province" style="height:25px;"></div>
				<div class="member_item_table">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
					<tr>
						<th bgcolor="#f3f5f6" height="36">优惠券门店</th>
						<th bgcolor="#f3f5f6" height="36">服务电话</th>
						<th bgcolor="#f3f5f6" height="36">金额</th>
						<th bgcolor="#f3f5f6" height="36">数量</th>
						<th bgcolor="#f3f5f6" height="36">剩余数量</th>
						<th bgcolor="#f3f5f6" height="36">有效期</th>
						<th bgcolor="#f3f5f6" height="36">每人限领数</th>
						<th bgcolor="#f3f5f6" height="36">起始使用金额</th>
						<th bgcolor="#f3f5f6" height="36">生成时间</th>
						<th bgcolor="#f3f5f6" height="36">使用状态</th>
<!-- 						<th bgcolor="#f3f5f6" height="36">操作</th> -->
					</tr>
					<tr>
						<td>${funcs:getCustNameByCustId(couponList.sysCustId)}</td>
						<td>${couponList.servicePhone}</td>
						<td>${couponList.money/100}元</td>
						<td>${couponList.count}张</td>
						<td>${couponList.remainCount<1?0:couponList.remainCount}张</td>
						<td> ${couponList.term }天 </td>
						<td> ${limit }张 </td>
						<td> ${couponList.origin/100 }元</td>
						<fmt:formatDate value="${couponList.createTime}" var="createTime" pattern="yyyy年MM月dd日"/>
						<td>${createTime}</td>
						<td>${couponList.status==0?"使用中":"已停止领取" }</td>
<!-- 						<td> -->
<%-- 						<shiro:hasPermission name="membercard/pcode:listDelete"> --%>
<%-- 							<c:if test="${code.status eq '0'}"> <em><a href="javascript:void(0)" id="delete" data-code="${code.id}">全部作废</a></em></c:if> --%>
<%-- 						</shiro:hasPermission> --%>
<%-- 						<shiro:hasPermission name="membercard/pcode:report"> --%>
<%-- 							<em><a href="/membercard/pcode/report.action?codeId=${couponList.id}">导出</a></em> --%>
<%-- 						</shiro:hasPermission> --%>
<!-- 						</td> -->
					</tr>
				</table>
				</div>
				<div>
				</div>
				<div class="member_item_table">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
						<tr>
							<th bgcolor="#f3f5f6" height="36">产品类别</th>
							<th bgcolor="#f3f5f6" height="36">产品名称</th>
							<th bgcolor="#f3f5f6" height="36">订单原价</th>
							<th bgcolor="#f3f5f6" height="36">支付金额</th>
							<th bgcolor="#f3f5f6" height="36">优惠金额</th>
							<th bgcolor="#f3f5f6" height="36">使用时间</th>
							<th bgcolor="#f3f5f6" height="36">用户名</th>
							<th bgcolor="#f3f5f6" height="36">核销门店</th>
							<th bgcolor="#f3f5f6" height="36">支付状态</th>
						</tr>
						<c:forEach items="${orderAndCoupon}" var="coupon">
							<tr>
								<td class="exchange_con_duihuan">${coupon.order_type==10?"卡":(coupon.order_type==11?"团课":"私教")}</td>
								
								<c:choose>
									<c:when test="${coupon.order_type==13}">
										<td class="exchange_con_duihuan"> ${funcs:getCoachNameById(coupon.product_id)}</td>
									</c:when>
									<c:otherwise>
										<td class="exchange_con_duihuan">${funcs:getProductNameByProductId(coupon.product_id)}</td>
									</c:otherwise>
								</c:choose>
								
								<td class="exchange_con_duihuan">${coupon.sale_price*coupon.num/100}元</td>
								<td class="exchange_con_duihuan">${coupon.mem_price/100}元</td>
								<td class="exchange_con_duihuan">${couponList.money/100}元</td>
								<td class="exchange_con_duihuan"><fmt:formatDate value="${coupon.use_time }" type="date" pattern="yyyy年MM月dd日 " /></td>
								<td class="exchange_con_duihuan"> ${funcs:getmemberNameBymemberId(coupon.member_id)}</td>
								<td class="exchange_con_duihuan"> ${funcs:getCustNameByCustIds(coupon.sys_cust_id)}</td>
								<td class="exchange_con_duihuan">${coupon.status==2?"已支付":coupon.status==4?"已完成":"未支付" }</td>
<!-- 								<td class="exchange_con_zhuangtai"> -->
<%-- 								<c:if test="${codeInfo.status eq '0'}">有效</c:if> --%>
<%-- 								<c:if test="${codeInfo.status eq '1'}">已作废</c:if> --%>
<%-- 								<c:if test="${codeInfo.status eq '2'}">已兑换（ <fmt:formatDate value="${codeInfo.useTime}" type="date"/>）</c:if> --%>
								</td>
<!-- 								<td class="exchange_con_caozuo"> -->
<%-- 								<c:choose> --%>
<%-- 									<c:when test="${codeInfo.status eq '0'}"> --%>
<%-- 										<a <shiro:hasPermission name="membercard/pcode:delete"> onclick="deleteCodeInfo(${codeInfo.id},this)" </shiro:hasPermission> href="javascript:void(0)">作废</a> --%>
<%-- 									</c:when> --%>
<%-- 									<c:otherwise> --%>
<!-- 										&nbsp; -->
<%-- 									</c:otherwise> --%>
<%-- 								</c:choose> --%>
									</td>
								</tr>
							</c:forEach>
					</table>
				</div>
				<!-- 分页 开始 -->
				<div class="pager">
				<c:set var="pageUrl"
					   value="/membercard/pcode/detail.action?cardId=${couponList.id}&pageNo=pageno"
					   scope="request" />
				<jsp:include flush="true" page="/include/pager.jsp" />
				</div>
				<!-- 分页 结束 -->
	<div class="btns-area">
<%-- 	<a href="javascript:void(0)" onClick="del(${couponList.id});">删除作废</a> --%>
	</div>
			</div>
		</div>
</div>



</body>
</html>