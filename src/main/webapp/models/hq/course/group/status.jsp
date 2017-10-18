<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>收费团课</title>
    <link type="text/css" rel="stylesheet" href="/static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/left.css"/>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/base.js"></script>
    <script type="text/javascript" src="/static/js/left.js"></script>
    <script type="text/javascript" src="/static/js/star.js"></script>
    <script type="text/javascript" src="/static/js/file.js"></script>

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
			<jsp:param name="nvaIndex" value="17" />
			<jsp:param name="nvaParentIndex" value="14" />
		</jsp:include>
		<!--左侧导航：结束-->
        <div class="rightContent">
            <h3 class="i_title">
                收费团课
            </h3>
            <div class="province" style="height: 40px;"></div>
            <div class="item_syllabus_list">
                <div class="item_syllabus_list_tag">
                    <span class="">
                        <a href="/course/group.action"><font color="#333">列表</font></a>
                    </span>
                    <div>
                        <span><a href="/course/group/status_bmz.action">报名中</a></span>
                        <c:if test="${courseStatus == 1}">
	                        <span class="item_syllabus_list_tag_lie"><a href="/course/group/status.action?courseStatus=1">进行中</a></span>
	                        <span><a href="/course/group/status.action?courseStatus=2">已完成</a></span>
                        </c:if>
                        <c:if test="${courseStatus == 2}">
	                        <span><a href="/course/group/status.action?courseStatus=1">进行中</a></span>
	                        <span class="item_syllabus_list_tag_lie"><a href="/course/group/status.action?courseStatus=2"><font>已完成</font></a></span>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="item_syllabus_list_show branch_shop_box ">
                <c:forEach items="${beanList}" var="bean" >
                <dl>
                    <a href="/course/group/toProDetail.action?id=${bean.id }" target="_blank">
                    <dt><img src="${bean.signImg }" alt="${bean.cardName }"></dt>
                    <dd class="syllabus_list_block">
                        <input type="hidden" id="sysCustId" value="${bean.sysCustId }">
                        <p id="sysCustName"><c:out value="${funcs:getCustNameByCustId(bean.sysCustId)}"></c:out></p>
                        <p>
                            <em>${bean.cardName }</em>
                            <em><fmt:formatDate value="${bean.startTime }" pattern="yyyy-MM-dd"/></em>
                        </p>
                    </dd>
                    </a>
                </dl>
                </c:forEach>
            </div>
            <!-- 分页 开始 -->
				<div class="pager">
					<c:set var="pageUrl"
						value="/course/group/status.action?pageNo=pageno&type=${type}&courseStatus=${courseStatus }"
						scope="request" />
					<jsp:include flush="true" page="/include/pager.jsp" />
				</div>
			<!-- 分页 结束 -->
        </div>
        </div>
    </div>
</div>
<div class="clear_float"></div>
<!-- <div class="foot">
    <div class="footer">本底部是不存在的，为预防产品小人反复无常，特来占位  <br >----技术部无名英雄</div>
</div> -->
</body>
</html>