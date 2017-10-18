<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<jsp:useBean id="jspConstants" class="com.lol.common.JspConstants" />

<div class="leftNavigation">
<%-- 
	<div class="flexNav">
		<i></i>
	</div>--%>
	<c:set var="leftNavIco_fd" value="leftNavIco_fd"></c:set>
	<ul class="navtree">
		<c:set var="custInfo" value="${funcs:getCustInfo()}" />
		<c:if test="${custInfo.parentType eq jspConstants.CUST_TYPE_HQ}">
			<c:choose>
				<c:when test="${param.isFendian eq 1}">
					<li data-url="/branch/list.action">
						<div class="navtreeTitle">
							<i class="leftNavIco_fd_open" data-tag="on"></i>分店列表
						</div>
					</li>
				</c:when>
				<c:otherwise>
					<li data-url="/branch/list.action">
						<div class="navtreeTitle">
							<i class="leftNavIco_fd"></i>分店列表
						</div>
					</li>
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:set var="menus" value="${funcs:getMenus()}" />
		<c:if test="${fn:length(menus)>0}">
			<c:forEach var="menu" items="${menus}">
				<shiro:hasPermission name="${menu.attributes['permission']}">
					<li <c:if test="${fn:length(menu.attributes['path'])>0}">data-url="${menu.attributes['path']}"</c:if>>
						<div class="navtreeTitle">
							<c:choose>
								<c:when test="${menu.id eq param.nvaParentIndex}">
									<i class="${menu.iconCls}_open" data-tag="on"></i><c:out value="${menu.text}" />
								</c:when>
								<c:otherwise>
									<i class="${menu.iconCls}"></i><c:out value="${menu.text}" />
								</c:otherwise>
							</c:choose>
							
							<c:if test="${menu.iconCls ne leftNavIco_fd}">
								<em></em>
							</c:if>
						</div> 
						<c:set var="citems" value="${menu.children}" /> 
						<c:if test="${fn:length(citems)>0}">
							<div class="childNav" ${menu.id} <c:if test="${menu.id eq param.nvaParentIndex}">style="display:block;"</c:if>>							
								<c:forEach var="citem" items="${citems}">
									<shiro:hasPermission name="${citem.attributes['permission']}">
										<p>
											<a href="${citem.attributes['path']}"  <c:if test="${citem.id eq param.nvaIndex}">style="color:#fff;"</c:if>>${citem.text}</a>
										</p>
									</shiro:hasPermission>
								</c:forEach>
							</div>
						</c:if>
					</li>
				</shiro:hasPermission>
			</c:forEach>
		</c:if>
	</ul>
</div>