<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<jsp:useBean id="jspConstants" class="com.lol.common.JspConstants" />
<div class="head">
	<div class="header">
		<h3>启动健</h3>
		<div class="headerbar">
			<ul>
				<c:set var="custInfo" value="${funcs:getCustInfo()}" />
				<c:if test="${custInfo.parentType eq jspConstants.CUST_TYPE_HQ}">
					<shiro:hasPermission name="message:list">
						<li><i class="message"></i><a href="/message/list.action">信息</a></li>
					</shiro:hasPermission>
				</c:if>
				<c:if test="${custInfo.parentType eq jspConstants.CUST_TYPE_BRANCHE}">
					<li><i class="message"></i><a href="/message/branchlist.action">信息</a></li>
				</c:if>
				<li><i><shiro:principal /></i></li>
				<li><i><a href="/logout">退出</a></i></li>
			</ul>
		</div>
	</div>
</div>