<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<jsp:useBean id="jspConstants" class="com.lol.common.JspConstants" />
<!-- 判断是否登录 -->
<shiro:user>
	<!-- 登录后根据商户类型跳转到对应请求 -->
	<c:set var="custInfo" value="${funcs:getCustInfo()}" />
	
	<c:if test="${custInfo.parentType eq jspConstants.CUST_TYPE_HQ}">
		<shiro:hasPermission name="account/business:sys">
			<c:redirect url="/account/business/sys.action"></c:redirect>
		</shiro:hasPermission>
		<c:redirect url="/branch/list.action"></c:redirect>
	</c:if>
	<c:if test="${custInfo.parentType eq jspConstants.CUST_TYPE_BRANCHE}">
		<c:redirect url="/message/branchlist.action"></c:redirect>
	</c:if>
</shiro:user>
<!-- 未登录跳转到登录页面 -->
<c:redirect url="/login.action"></c:redirect>