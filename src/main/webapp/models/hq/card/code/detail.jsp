<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
  <title>会员卡列表</title>
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
				<h3 class="i_title">卡详情</h3>
				<div class="province" style="height:25px;"></div>
				<div class="item_syllabus_list_tag">
					<span class="item_syllabus_list_tag_lie"><a href="javascript:void(0)">${code.cardName}</a></span>
				</div>
				<div class="member_item_table">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
					<tr>
						<th bgcolor="#f3f5f6" height="36">生成时间</td>
						<th bgcolor="#f3f5f6" height="36">数量</th>
						<th bgcolor="#f3f5f6" height="36">有效期</th>
						<th bgcolor="#f3f5f6" height="36">使用状态</th>
						<th bgcolor="#f3f5f6" height="36">操作</th>
					</tr>
					<tr>
						<fmt:formatDate value="${code.createTime}" var="createTime" pattern="yyyy年MM月dd日"/>
						<td>${createTime}</td>
						<td>${code.num}</td>
						<td>
						<fmt:formatDate value="${code.startTime}" var="startTime" pattern="yyyy年MM月dd日"/>
						<em>开始：${startTime}</em>
						<fmt:formatDate value="${code.endTime}" var="endTime" pattern="yyyy年MM月dd日"/>
						<em>结束：${endTime}</em>
						</td>
						<td>使用:${code.useNum}<br/>作废:${code.cancelNum}</td>
						<td>
						<shiro:hasPermission name="membercard/pcode:listDelete">
							<c:if test="${code.status eq '0'}"> <em><a href="javascript:void(0)" id="delete" data-code="${code.id}">全部作废</a></em></c:if>
						</shiro:hasPermission>
						<shiro:hasPermission name="membercard/pcode:report">
							<em><a href="/membercard/pcode/report.action?codeId=${code.id}">导出</a></em>
						</shiro:hasPermission>
						</td>
					</tr>
				</table>
				</div>
				<div>
				</div>
				<div class="member_item_table">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
						<tr>
							<th bgcolor="#f3f5f6" height="36">兑换码</th>
							<th bgcolor="#f3f5f6" height="36">状态</th>
							<th bgcolor="#f3f5f6" height="36">操作</th>
						</tr>
						<c:forEach items="${listCodeInfo}" var="codeInfo">
							<tr>
								<td class="exchange_con_duihuan">${codeInfo.code}</td>
								<td class="exchange_con_zhuangtai">
								<c:if test="${codeInfo.status eq '0'}">有效</c:if>
								<c:if test="${codeInfo.status eq '1'}">已作废</c:if>
								<c:if test="${codeInfo.status eq '2'}">已兑换（ <fmt:formatDate value="${codeInfo.useTime}" type="date"/>）</c:if>
								</td>
								<td class="exchange_con_caozuo">
								<c:choose>
									<c:when test="${codeInfo.status eq '0'}">
										<a <shiro:hasPermission name="membercard/pcode:delete"> onclick="deleteCodeInfo(${codeInfo.id},this)" </shiro:hasPermission> href="javascript:void(0)">作废</a>
									</c:when>
									<c:otherwise>
										&nbsp;
									</c:otherwise>
								</c:choose>
									</td>
								</tr>
							</c:forEach>
					</table>
				</div>
				<!-- 分页 开始 -->
				<div class="pager">
				<c:set var="pageUrl"
					   value="/membercard/pcode/detail.action?codeId=${code.id}&pageNo=pageno"
					   scope="request" />
				<jsp:include flush="true" page="/include/pager.jsp" />
				</div>
				<!-- 分页 结束 -->
	<div class="btns-area">
	<a href="javascript:void(0)" onClick="del(${code.id});">删除作废</a>
	</div>
			</div>
		</div>
</div>
<script>
      $(function(){
     // 取消关闭弹框
        $(".close").click(function(){
            $(this).parents(".closebox").hide();
            $("#shade").remove();
        });
        
   		var bool=true;
   		
   	   $("#delete").click(function(){
   		 var codeId=$(this).attr("data-code");
 	    	if(bool){
         		bool=false;
         		deleteCode(codeId);
         		bool=true;
         	}else{
         		alert("请勿重复提交,已保存请等待");
         	}
 	    });
   	   
   	   function deleteCode(id){
   		  if(confirm("确定要全部作废吗?")){
   		$.ajax({
			 type:"post",
			 data:{"codeId":id},
			 url:"/membercard/pcode/listDelete.action",
			 dataType:"json",
			 cache:false,
			 beforeSend:function(xhr){
					//$("#loading").show();
			 },
			 success:function(res) {
				if(res.result == "1"){
					alert("作废成功！");
					location.href="/membercard/pcode/list.action";
				}else{
					alert("作废失败！");
				}
			 },
			 error:function(resJson){
				 alert("系统异常，请稍后再试！");
			 }
		 });
   		  }  
   	   }
      });
   var flag=true;
  function deleteCodeInfo(codeInfoId,th){
   	    	if(flag){
   	    		flag=false;
           		deletebol(codeInfoId,th);
           		flag=true;
           	}else{
           		alert("请勿重复提交,已保存请等待");
           	}
  }
  
  function deletebol(codeInfoId,th){
		 $.ajax({
			 type:"post",
			 data:{"codeInfoId":codeInfoId},
			 url:"/membercard/pcode/delete.action",
			 dataType:"json",
			 cache:false,
			 beforeSend:function(xhr){
					//$("#loading").show();
			 },
			 success:function(res) {
				if(res.result == "1"){
					 /* $(th).text('');
					 $(th).attr('onclick','');
					 $(th).text('已操作');
				     $(th).parent().prev().text('');
				     $(th).parent().prev().text('已作废');
				     alert("兑换码作废成功！"); */
					alert("兑换码作废成功！");
					location.href="/membercard/pcode/detail.action?codeId="+${code.id};
				}else{
					alert("兑换码作废失败！");
				}
			 },
			 error:function(resJson){
				 alert("系统异常，请稍后再试！");
			 }
		 });
}
 function    del(codeId){
	 $.ajax({
		 type:"post",
		 data:{"codeId":codeId},
		 url:"/membercard/pcode/del.action",
		 dataType:"json",
		 cache:false,
		 beforeSend:function(xhr){
				//$("#loading").show();
		 },
		 success:function(res) {
			 console.log(res)
			if(res.status == 1){
				alert("作废兑换码删除成功！");
				location.href="/membercard/pcode/detail.action?codeId="+${code.id};
			}else  if (res.status==0){
				alert("没有可删除作废兑换码");
			}else{
	    		alert("系统异常，请稍后再试！");
	    		window.location.reload();
	    	}
		 },
		 error:function(resJson){
			 console.log(0);
			 alert("系统异常，请稍后再试！");
		 }
	 })
 } 
</script>

</body>
</html>