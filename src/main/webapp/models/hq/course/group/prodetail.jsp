<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>课程详情进行中</title>
    <link type="text/css" rel="stylesheet" href="/static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/left.css"/>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/base.js"></script>
    <script type="text/javascript" src="/static/js/left.js"></script>
    <script type="text/javascript" src="/static/js/star.js"></script>
    <script type="text/javascript" src="/static/js/file.js"></script>
    <script type="text/javascript" src="/static/js/Calendar.js"></script>
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
            <jsp:param name="nvaIndex" value="17" />
            <jsp:param name="nvaParentIndex" value="14" />
        </jsp:include>
        <!--左侧导航：结束-->
        <div class="rightContent">
            <h3 class="i_title">教练详情</h3>
            <div class="province" style="height:25px;"></div>
            <div class="item_syllabus_list_tag">
                <span class="item_syllabus_list_tag_lie"><a href="javascript:void(0)">课程信息</a></span>
                <span><a href="javascript:void(0)">报名人信息</a></span>
                <span><a href="javascript:void(0)">签到信息</a></span>
            </div>
            <div class="syllabus_detail_cont">
                <div class="create_syllabus_cont syllabus_detail_show">
                    <ul>
                        <li>
                            <input type="hidden" id="pId" value="${productInfo.id }">
                            <span class="create_syllabus_item_name">名称</span>
                            <input type="text" class="create_syllabus_input" value="${productInfo.cardName }" disabled>
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">限制人数</span>
                            <input type="text" class="create_syllabus_input" value="${productInfo.peopleNum }" disabled>
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">价格</span>
                            <input type="text" class="create_syllabus_input" value="${productInfo.salePrice/100 }" disabled>
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">支持退款</span>
                            <div class="tuikuan">
                                <c:if test="${productInfo.isRefund == 1}">
                                    <em><i id="yes" rel="1" class="on"></i>是</em>
                                    <em><i id="no" rel="2"></i>否</em>
                                    <em class="poundage">
                                        退款手续费<input id="fee" name="fee" type="text" value="${productInfo.fee/100 }" class="refund">%
                                    </em>
                                </c:if>
                                <c:if test="${productInfo.isRefund == 0}">
                                    <em><i id="yes" rel="1"></i>是</em>
                                    <em><i id="no" rel="2" class="on"></i>否</em>
                                    <em class="poundage" style="display: none;">
                                        退款手续费<input id="fee" name="fee" type="text" value="" class="refund">%
                                    </em>
                                </c:if>
                            </div>
                        </li>
                        <li style="height:100%; overflow: hidden;">
                            <span class="create_syllabus_item_name">图片</span>
                            <div id="preview" style="float: left;"><img src="${productInfo.signImg }" height="120" alt=""/></div>
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">课程次数</span>
                            <input type="text" class="create_syllabus_input" value="${productInfo.classCount }" disabled>
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">分店</span>
                            <div class="chooseshop">
                               <c:out value="${funcs:getCustNameByCustId(productInfo.sysCustId)}"></c:out>
                            </div>
                            <div class="choose_gev_shop_times"><input name="control_date" type="text" id="control_date" value='<fmt:formatDate value="${productInfo.startTime }" pattern="yyyy-MM-dd"></fmt:formatDate>' size="10" maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" disabled></div>
                            <div class="choose_gev_shop_times">-</div>
                            <div class="choose_gev_shop_times"><input name="control_date2" type="text" id="control_date2" value='<fmt:formatDate value="${productInfo.endTime }" pattern="yyyy-MM-dd"></fmt:formatDate>' size="10" maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" disabled></div>
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">教练</span>
                            <div class="showcoach">
                                <c:forEach items="${coachList }" var="cid">
                                    <p id="${cid }">${funcs:getCoachNameById(cid)} <i></i></p>
                                </c:forEach>
                            </div>
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">课程内容</span>
                            <textarea id="memo" class="xheditor" style="width:442px; height:90px;" disabled>${productInfo.proDesc }
                            </textarea>
                            <%@include file="/include/editor.jsp" %>
                            <script>
                            /** Simple customization with current options **/
                            $('#memo').trumbowyg({
                                lang: 'fr',
                                closable: true,
                                mobile: true,
                                fixedBtnPane: true,
                                fixedFullWidth: true,
                                semantic: true,
                                resetCss: true,
                                autoAjustHeight: true,
                                autogrow: true
                            });
                                //编辑器内容为:$('#form-content').html();
                            </script>
                        </li>
                    </ul>
                </div>
                <div class="joinerInfor" style="display: none;">
                    <div class="joinerInforTitle">
                        <span class="viptitle">会员</span>
                        <span class="teltitle">手机号</span>
                        <span class="jointimetitle">报名时间</span>
                        <span class="operatitle">签到次数</span>
                    </div>
                    <div class="joinerInforCont" >
                        <ul id="iii">
                            <c:forEach items="${map1}" var="mymap1" >
                                <li>
                                    <span id="linkMan" class="vipcont">${mymap1.key.linkMan }</span>
                                    <span id="linkPhone" class="telcont">${mymap1.key.linkPhone }</span>
                                    <span id="applyTime" class="jointimecont"><fmt:formatDate value="${mymap1.key.payTime }" pattern="yyyy年MM月dd日 HH:mm"/></span>
                                    <span class="operacont">${fn:length(mymap1.value)}<font id="xiangqing" class="j_xiangqing">详情</font></span>
                                </li>
                                <div id="detail" class='sign' style="display: none">
                                    <c:forEach items="${mymap1.value }" var="time" varStatus="st">
                                        <em>第${st.index +1}次：<fmt:formatDate value="${time }" pattern="yyyy年MM月dd日 HH:mm"/></em>
                                    </c:forEach>
                                </div>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="signListbox">
                    <ul>
                         <c:forEach items="${map}" var="mymap" >
                        <li>
                            <div class="sign_in">
                                <span class="sign-date"><c:out value="${mymap.key}" /></span>
                                <c:set value="${mymap.value}" var="userList"></c:set>
                                <span class="sign-show">展开</span>
                                <span class="sign-mer"><c:out value="${fn:length(mymap.value)}" />人签到</span>
                            </div>
                            <div class="sign_in_head">
                                <c:forEach items="${mymap.value }" var="orderFinishLog">
                                    <span>
                                        <em class="sign_in_header"><img src="<c:out value='${funcs:getmemberImgBymemberId(orderFinishLog.memberId) }'></c:out>" alt=""/></em>
                                        <em>
                                            <p><c:out value="${funcs:getmemberNameBymemberId(orderFinishLog.memberId) }"></c:out></p>
                                            <p><fmt:formatDate value="${orderFinishLog.finishTime }" pattern="yyyy年MM月dd日 HH:mm"></fmt:formatDate></p>
                                        </em>
                                    </span>
                                </c:forEach>
                            </div>
                        </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
$(".item_syllabus_list_tag span").click(function(){
    var index = $(this).index();
    $(this).addClass("item_syllabus_list_tag_lie").siblings().removeClass("item_syllabus_list_tag_lie");
    if(index == 0){
        $(".syllabus_detail_show").show();
        $(".joinerInfor").hide();
        $(".signListbox").hide();
    }
    else if(index == 1){
        $(".syllabus_detail_show").hide();
        $(".joinerInfor").show();
        $(".signListbox").hide();
    }
    else{
        $(".syllabus_detail_show").hide();
        $(".joinerInfor").hide();
        $(".signListbox").show();
    }
});

$('.operacont').click(function(){
	
	var div = $(this).parent().next('div');
	if(div.css("display")=='none'){//如果show是隐藏的
		div.css("display","block");//show的display属性设置为block（显示）
	}else{//如果show是显示的
		div.css("display","none");//show的display属性设置为none（隐藏）
	}
});


</script>
<script type="text/javascript">
$(".sign-show").click(function(){
	
	var date = $(this).prev().text();
	var pId = $("#pId").val();
	var div = $(this).parent().next();
	//alert(date);
    $(this).parent().siblings().slideToggle("100",function(){
        if($(this).is(":hidden")){
            $(this).siblings().find(".sign-show").text("展开");
        }
        else{
            $(this).siblings().find(".sign-show").text("收起");
        }
    });
})
</script>
</html>