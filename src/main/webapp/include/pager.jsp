<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- ��ҳ��ʼ -->



<c:if test="${pager.totalPages > 1}">

<c:if test="${pager.previous!=0}">
   <span style="width: 60px;"><a href="${fn:replace(pageUrl,'pageno',1) }" >�� ҳ</a></span>
</c:if>

<c:if test="${pager.previous!=0}">
   <span style="width: 80px;"><a href="${fn:replace(pageUrl,'pageno',pager.previous) }" >��һҳ</a></span>
</c:if>
<c:forEach var="x" begin="${pager.listBegin}" end="${pager.listEnd}" varStatus="var">
  <c:if test="${pager.currentPage == x}">
     <span class="on"><a>${x}</a></span>
  </c:if>
  <c:if test="${pager.currentPage !=x}">
     <span><a href="${fn:replace(pageUrl,'pageno',x) }" >${x}</a></span>
  </c:if>
</c:forEach>
<c:if test="${pager.next!=0}">
   <span style="width: 80px;"><a href="${fn:replace(pageUrl,'pageno',pager.next) }" >��һҳ</a></span>
</c:if>

<c:if test="${pager.next!=0}">
   <span style="width: 60px;"><a href="${fn:replace(pageUrl,'pageno',pager.totalPages) }" >β ҳ</a></span>
</c:if>
</c:if>

<!--��ҳ����-->

<%--
<c:choose>
	<c:when test="${model.cateEnName!=null}">
		<c:set var="pageUrl" value="/${model.newsTypeTag}/${model.cateEnName}/list_pageNO.html" scope="request"/>
	</c:when>
	<c:otherwise>
		<c:set var="pageUrl" value="/${model.newsTypeTag}/list_pageNO.html" scope="request"/>
	</c:otherwise>
</c:choose>
--%>
