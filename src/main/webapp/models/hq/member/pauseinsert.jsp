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
<title>课程详情</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/Calendar02.js"></script>

<script type="text/javascript" src="/static/js/base.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
<script type="text/javascript" src="/static/js/star.js"></script>
<script src="/static/third/fine-uploader/fine-uploader.js"></script>
<script type="text/template" id="qq-template-manual-trigger">
<%@ include file="/include/template-uploader-single.jsp" %>
</script>
<%@include file="/include/editor.jsp" %>




</head>
<body class="baseBgColor">

	<!--顶部：开始-->
	<jsp:include page="/include/header.jsp" flush="true">
		<jsp:param name="title" value="后台首页" />
	</jsp:include>
	<!--顶部：结束-->
	<div class="branch_wrap">
		<div class="branch_wrap_cen">
	
					<div class="syllabus_detail_cont">
						<div class="create_syllabus_cont syllabus_detail_show">
							<ul>
								<li>注意：停卡操作不可逆，操作请谨慎！开始时间不得小于今天，停卡时间段不得大于30天。</li>
								

								<li><span class="create_syllabus_item_name">开始时间：</span>
									<input type="text" name="startDate" id="startDate" onclick="new Calendar().show(this);" readonly="readonly">
								</li>
								<li><span class="create_syllabus_item_name">结束时间：</span>
									<input type="text" name="endDate" id="endDate" onclick="new Calendar().show(this);" readonly="readonly">

								</li>
								<li><span class="create_syllabus_item_name">停卡原因</span>
									<input type="text" id="content" class="create_syllabus_input" >
								</li>

								<li id="${memberInfo.memberId }">
									<em class="create_syllabus_fs">保存修改</em>
								</li>
							</ul>
						</div>
					</div>
				

		</div>
		</div>
				<div class="member_item_table" style="clear:both">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
						<tbody><tr>
							<th bgcolor="#f3f5f6" height="36">会员</th>
							<th bgcolor="#f3f5f6" height="36">开始时间</th>
							<th bgcolor="#f3f5f6" height="36">结束时间</th>
							<th bgcolor="#f3f5f6" height="36">状态</th>
							<th bgcolor="#f3f5f6" height="36">原因</th>
							<th bgcolor="#f3f5f6" height="36">操作人</th>
						</tr>
				<c:forEach items="${pauseInfos }" var="item">
					<tr>
						<td> 
						${memberInfo.memberName }
						</td>
						<td class="zt">
						<fmt:formatDate value="${item.startDate }" type="date" pattern="yyyy年MM月dd日 " />
						</td>
						<td class="sjh">
						<fmt:formatDate value="${item.endDate }" type="date" pattern="yyyy年MM月dd日 " />
						</td>
						<td class="kjcz">
						<c:choose>
							<c:when test="${item.status==0 }">
								申请中
							</c:when>
							<c:when test="${item.status==1 }">
								申请通过
							</c:when>
							<c:when test="${item.status==2 }">
								申请取消
							</c:when>
							<c:when test="${item.status==3 }">
								手动停卡
							</c:when>
							<c:otherwise>
								永久停卡
							</c:otherwise>
						
						</c:choose>
						</td>
						<td class="kjcz">
						${item.content }
						</td>
						<td class="kjcz">
						${item.operationUser}
						</td>
						
					</tr>
				</c:forEach>
					</tbody></table>
				</div>



	<script>

			//保存修改
			$('.create_syllabus_fs').click(function() {


				if($("#startDate").val()==""||$("#endDate").val()==""||$("#content").val()==""){
					alert("请认真填写数据！");
					return false;
				}

				$.ajax({
					type : "post",
					data : {
						memberId : "${memberInfo.memberId}",
						startDate : $("#startDate").val(),
						endDate : $("#endDate").val(),
						content : $("#content").val(),
					},
					url : "/member/manualPause.action?r=" + Math.random(),
					dataType : "json",
					success : function(res) {
						console.log(res);
						if (res.status == 0) {
							alert('修改成功');
						} else {
							alert('修改失败');
						}
						
						$("#startDate").val("");
						$("#endDate").val("");
						window.location.reload();
					},
					error : function(res) {
						alert("网络异常，请稍后再试");
					}
				});

			});





	</script>
</body>

</html>