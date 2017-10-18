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
<title>会员列表</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>
<script type="text/javascript" src="/static/js/Calendar02.js"></script>

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
				<jsp:param name="nvaIndex" value="30" />
				<jsp:param name="nvaParentIndex" value="24" />
			</jsp:include>
			<!--左侧导航：结束-->
			<div class="rightContent">
				<h3 class="i_title">会员列表</h3>
				<div class="search_area" style="height:88px;">
					<div class="member_item_list">
						<span class="member_item_title">会员卡</span>
						<div class="chooseshop">
							<p class="checkCard" id="${defaultCardId }">${defaultCardName }</p>
							<i></i>
							<div class="choose_gev_shop" id="cardList" style="display: none;">
							<p id="0">全部</p>
								<c:forEach var="cardInfo" items="${memberCards }">
									<p id="${cardInfo.id }">${cardInfo.cardName }</p>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="member_item_list">
						<span class="member_item_title">分店</span>
						<div class="chooseshop">
							<p class="checkCust" id="${defaultCustId }">${defaultCustName }</p>
							<i></i>
							<div class="choose_gev_shop" id="custList" style="display: none;">
							<p id="0">全部</p>
								<c:forEach var="custInfo" items="${custinfos }">
									<p id="${custInfo.systemId }">${custInfo.custName }</p>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="member_item_list">
						<span class="member_item_title">状态</span>
						<div class="chooseshop">
							<p class="checkStatus" id="${defaultStatus }">${defaultStatusName }</p>
							<i></i>
							<div class="choose_gev_shop" id="statusList"
								style="display: none;">
								<p id="0">全部</p>
								<p id="1">已开通</p>
								<p id="2">已过期</p>
								<p id="3">已退款</p>
							</div>
						</div>
					</div>
					<div class="member_item_list">
						<span class="member_item_title">开始时间</span>
						<input type="text" name="startDate" value="${startDate }" id="startDate" onclick="new Calendar().show(this);"
                                   readonly="readonly">
					</div>
					<div class="member_item_list">
						<span class="member_item_title">结束时间</span>
						<input type="text" name="endDate" value="${endDate }" id="endDate" onclick="new Calendar().show(this);"
                                   readonly="readonly">
					</div>
					<div class="member_item_list" style="margin-top:8px">
					<span class="member_item_title">关键字</span>
						<input id="keyword" type="text" placeholder="请输入姓名或手机号"
							class="search" />
					</div>
					
					<div class="member_item_list" style="margin-top:8px;">
						<a href="javascript:;" class="searchBtns">搜索</a>
					</div>
				</div>
				<div class="member_item_table" style="clear:both">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
						<tr>
							<th bgcolor="#f3f5f6" height="36">会员</th>
							<th bgcolor="#f3f5f6" height="36">会员卡</th>
							<th bgcolor="#f3f5f6" height="36">状态</th>
							<th bgcolor="#f3f5f6" height="36">手机号</th>
							<th bgcolor="#f3f5f6" height="36">快捷操作</th>
						</tr>
	<c:choose>
		<c:when test="${defaultStatus==2 }">

			<c:forEach var="memberInfo" items="${memberInfos }">

				<c:if test="${memberInfo.value[0].able==0 }">
					<tr>
					<td class="member-user-header">
					<a href="detail.action?memberId=${memberInfo.key} " target="_blank">
					<img src="${memberInfo.value[0].member_logo}" alt="" width="30" height="30" style="border-radius:100%" />

					<c:choose>
						<c:when test="${fn:length(memberInfo.value[0].member_name) >6}">
							<c:out value="${fn:substring(memberInfo.value[0].member_name, 0, 6)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${memberInfo.value[0].member_name}" />
						</c:otherwise>
					</c:choose>

					</a>
					</td>
					<td>${funcs:getProductNameByProductId(memberInfo.value[0].product_id) }
					<c:if test="${fn:length(memberInfo.value)>1}">
						<i class="cards"> ${fn:length(memberInfo.value)} </i>
					</c:if>
					</td>
					<td>
					<fmt:formatDate value="${memberInfo.value[0].start_time }" type="date" pattern="yyyy年MM月dd日 " />-
					<fmt:formatDate value="${memberInfo.value[0].end_time }" type="date" pattern="yyyy年MM月dd日 " />
					</td>
					<td>${memberInfo.value[0].link_phone }</td>
					<td>
					<shiro:hasPermission name="member:detail">
						<a href="detail.action?memberId=${memberInfo.key }" target="_blank" class="member_detail">详情</a>
					</shiro:hasPermission>
					</td>
					</tr>
				</c:if>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach var="memberInfo" items="${memberInfos }">
				<tr>
				<td> <a href="detail.action?memberId=${memberInfo.key} " target="_blank">
				<img src="${memberInfo.value[0].member_logo}" alt="" width="30" height="30" style="border-radius:100%" />
				<c:choose>
					<c:when test="${fn:length(memberInfo.value[0].member_name) >6}">
						<c:out value="${fn:substring(memberInfo.value[0].member_name, 0, 6)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${memberInfo.value[0].member_name}" />
					</c:otherwise>
				</c:choose>
				<%-- 											${memberInfo.value[0].member_name } --%>
				</a>
				</td>
				<td>${funcs:getProductNameByProductId(memberInfo.value[0].product_id) }
				<c:if test="${fn:length(memberInfo.value)>1}">
					<i class="cards"> ${fn:length(memberInfo.value)} </i>
				</c:if>
				</td>
				<td class="zt"><fmt:formatDate value="${memberInfo.value[0].start_time }" type="date"
														 pattern="yyyy年MM月dd日 " />-<fmt:formatDate
					value="${memberInfo.value[0].end_time }" type="date"
					pattern="yyyy年MM月dd日 " />
				</td>
				<td class="sjh">${memberInfo.value[0].link_phone }</td>
				<td class="kjcz">
				<ul class="ull" style="display: none;">
				<c:forEach var="item" items="${memberInfo.value }">
					<li><span class="hyk">${funcs:getProductNameByProductId(item.product_id)}</span> <span class="zt"><fmt:formatDate
						value="${item.start_time }" type="date"
						pattern="yyyy年MM月dd日 " />-<fmt:formatDate
						value="${item.end_time }" type="date"
						pattern="yyyy年MM月dd日 " /></span>
					</li>
				</c:forEach>

				</ul> <shiro:hasPermission name="member:detail">
				<a href="detail.action?memberId=${memberInfo.key }"
				target="_blank"
				class="member_detail">详情</a>

			</shiro:hasPermission>
				<%--<shiro:hasPermission name="member:sendmsg">
                    <a href="javascript:;" class="send_mess">发短信</a>

                </shiro:hasPermission>
                --%>
				</td></tr>

			</c:forEach>
		</c:otherwise>
	</c:choose>
					</table>
				</div>
				<div class="clear_float"></div>
			</div>
			<!-- 分页 开始 -->
		<div class="pager">
			<c:set var="pageUrl"
				value="list.action?cardId=${defaultCardId }&cardName=${defaultCardName }&status=${defaultStatus }&key=${key }&custId=${defaultCustId }&custName=${defaultCustName}&startDate=${startDate }&endDate=${endDate }&pageNo=pageno"
				scope="request" />
			<jsp:include flush="true" page="/include/pager.jsp" />
		</div>
		<!-- 分页 结束 -->
		</div>
	</div>






	<div class="merber_refund closebox">
		<h3 class="create_syllabus_title">退款</h3>
		<div class="refund-apply-box">
			<ul class="">
				<li class="refund_header"><span><img
						src="../images/header.jpg" alt=""></span>姓名</li>
				<li><span>电话：</span>18611154787</li>
				<li><span>年卡：</span>年卡（2016年5月17日-2017年6月17日）</li>
				<li><span>购买价：</span>560元</li>
				<li><span>手续费：</span>30元</li>
				<li><span>退款规则：</span>扣除已消费月份（取整）</li>
				<li><span>月度费用：</span>5元</li>
				<li><span>建议金额：</span>500元</li>
				<li><span>实际退款：</span><input type="text" class="refund">
					元</li>
			</ul>
			<div class="refund_btns">
				<span class="cencle close">取消</span> <span class="confirm">申请退款</span>
			</div>
		</div>
	</div>
	<div id="Popup" class="closebox" style="display: none;">
		<div id="PopupText">退款成功</div>
		<div id="PopupBtn">
			<span class="PopupBtnqx close">确定</span>
		</div>
	</div>


	<div class="showcard" style="width: 410px; z-index: 999; display: none; top: 50%; margin-top: -100px;">
		<div class="member_item_table_title">
			<span class="hyk">会员卡</span> <span class="zt">状态</span>
		</div>
		<div class="member_item_table_cont2">
			<ul>


			</ul>
		</div>
		<div class="showcard-qx">
			<span>取消</span>
		</div>
	</div>

</body>
<script type="text/javascript">
	$(".chooseshop").click(function() {
		$(this).find(".choose_gev_shop").toggle();
	});

	$("#cardList p").click(function() {
		$('.checkCard').attr('id', $(this).attr('id'));

		$('.checkCard').text($(this).text());
	})
	$("#custList p").click(function() {
		$('.checkCust').attr('id', $(this).attr('id'));

		$('.checkCust').text($(this).text());
	})
	$("#statusList p").click(function() {
		$('.checkStatus').attr('id', $(this).attr('id'));
		$('.checkStatus').text($(this).text());
	})

	$('.searchBtns').click( function() {
				//console.log($('.checkCard').attr('id'));
				//console.log($('.checkCust').attr('id'));
				window.location.href = "list.action?cardId="
						+ $('.checkCard').attr('id') + "&cardName="
						+ $('.checkCard').text() + "&status="
						+ $('.checkStatus').attr('id') + "&key="
						+ $('#keyword').val()+"&custId="+$('.checkCust').attr('id')
						+"&custName="+$('.checkCust').text()+"&startDate="+$('#startDate').val()+"&endDate="+$('#endDate').val();
			});

	// 会员管理发送短信弹出框
	$(".send_mess").click(function() {
		showShadow();
		$('.messText').val('');
		$(".sendMessage").show();
	});

	// 文本框字符字数
	$(".messText").on("keyup", function() {
		var str = $(this).val();
		var len = str.length;
		if (len >= 57) {
			$(this).parents(".groupMessageBox").find(".fillNumber").text(57);
			$(this).val(str.substring(0, 57))
		} else {
			$(this).parents(".groupMessageBox").find(".fillNumber").text(len);
		}
	});

	// 取消关闭弹框
	$(".close").click(function() {
		$(this).parents(".closebox").hide();
		$("#shade").remove();
	});

	$('.cards').click( function() {
		//console.log('cards');
		console.log($(this).parent().parent().children('.kjcz').children('ul').html());
				//console.log($(this).parent().parent().children('.kjcz').children('ul').html());
				$('.member_item_table_cont2 ul').html($(this).parent().parent().children('.kjcz').children('ul').html());
				$('.showcard').show();
				showShadow();

	});

	$('.showcard-qx').click(function() {
		$('.showcard').hide();
		hideShadow();

	});
</script>
</html>