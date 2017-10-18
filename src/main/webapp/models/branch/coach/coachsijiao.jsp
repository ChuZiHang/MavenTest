<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<%   int i=1; %>
<%   int j=1; %>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
    <title>私教售卖</title>
    <link type="text/css" rel="stylesheet" href="/static/css/base.css" />
    <link type="text/css" rel="stylesheet" href="/static/css/main.css" />
    <link type="text/css" rel="stylesheet" href="/static/css/left.css" />
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/left.js"></script>
    <!-- <script type="text/javascript" src="/static/js/custom.js"></script>  -->
    <script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
    <script type="text/javascript" src="/static/js/base.js"></script>
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
        <jsp:param name="nvaIndex" value="19" />
        <jsp:param name="nvaParentIndex" value="18" />
    </jsp:include>
    <!--左侧导航：结束-->
    <div class="rightContent">
            <h3 class="i_title">私教售卖</h3>
            <div class="province" style="height:25px;"></div>
            <div class="item_syllabus_list_tag">
                <span ><a href="/coach/detail.action?id=${id}&coachType=${coachType}">教练信息</a></span>
                <span ><a href="/coach/coachclass.action?id=${id}&coachType=${coachType}">上课信息</a></span>
                <span ><a href="/coach/coachcomment.action?id=${id}&coachType=${coachType}">评价</a></span>
                <span class="item_syllabus_list_tag_lie"><a href="#">私教售卖信息</a></span>
            </div>
            <div class="syllabus_detail_cont">
                <div class="signListbox" style="display: block">
                    <div class="sijiao_tab">
                       <c:choose>
                        <c:when test="${countOrders>0 }">
                        <span>
                            <p class="pab15">已售卖课时</p>
                            <p><em class="timenum">${countOrder }</em> <em class="timehour">小时</em></p>
                        </span>
                        <span>
                            <p class="pab15">已上课时</p>
                            <p><em class="timenum">${countReady }</em><em class="timehour">小时</em></p>
                        </span>
                        </c:when>
                         <c:otherwise>
                                <span>
                            <p class="pab15">已售卖课时</p>
                            <p><em class="timenum">0</em> <em class="timehour">小时</em></p>
                        </span>
                        <span>
                            <p class="pab15">已上课时</p>
                            <p><em class="timenum">0</em><em class="timehour">小时</em></p>
                        </span>
                            </c:otherwise>
                            </c:choose>
                    </div>
                    <ul>
                        <li>
                            <div class="sign_in">
                                <span class="sign-date">待上课</span>
                                <span class="sign-show">展开</span>
                            </div>
                            <div class="dsk_in_head">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
                                    <tr>
                                        <th bgcolor="#f3f5f6" height="36">会员</th>
                                        <th bgcolor="#f3f5f6" height="36">手机号</th>
                                        <th bgcolor="#f3f5f6" height="36">购买时间</th>
                                        <th bgcolor="#f3f5f6" height="36">上课地点</th>
                                        <th bgcolor="#f3f5f6" height="36">剩余时间</th>
                                        <th bgcolor="#f3f5f6" height="36">已上课时</th>
                                        <th bgcolor="#f3f5f6" height="36">状态</th>
                                    </tr>
                                    <c:forEach  var="course"  items="${commentCourse }">
                                        <tr>
                                        <td id="linkMan" class="dsk-merber-con">${course.linkMan }</td>
                                        <td id="linkPhone" class="dsk-phone-con">${course.linkPhone }</td>
                                        <td id="creteTime" class="dsk-buy-time-con"><fmt:formatDate value="${course.creteTime }" pattern="yyyy-MM-dd"/></td>
                                        <td id="custName" class="dsk-class-add-con">${funcs:getCustNameByCustId(course.sysCustId )}</td>
                                        <td id="remainCount" class="dsk-surplus-time-con">${course.remainCount }</td>
                                        <td id="already" class="dsk-class-time-con">${course.orderCount-course.remainCount}</td>
                                     <!--   <td  type="hidden"  id="id" value="${course.id }">
                                        <td  type="hidden"  id="refundMoney" value="${course.refundMoney }">
                                        <td  type="hidden"  id="memPrice" value="${course.memPrice }">
                                        <td  type="hidden"  id="actualMoney" value="${course.actualMoney }"> -->
                                        <td class="operacont"> <font class="j_tuikuan1"  color="blue">已支付</font></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <div class="btns-area"><a href="/coach/outPrepareExcel.action?infoid=${id}">导出数据</a></div>
                            </div>
                        </li>
                        <li>
                            <div class="sign_in">
                                <span class="sign-date">已完成</span>
                                <span class="sign-show">展开</span>
                            </div>
                            <div class="dsk_in_head">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
                                    <tr>
                                        <th bgcolor="#f3f5f6" height="36">会员</th>
                                        <th bgcolor="#f3f5f6" height="36">手机号</th>
                                        <th bgcolor="#f3f5f6" height="36">购买时间</th>
                                        <th bgcolor="#f3f5f6" height="36">上课地点</th>
                                        <th bgcolor="#f3f5f6" height="36">上课时</th>
                                    </tr>
                                <c:forEach  var="ready"  items="${commentReady }">
                                    <tr>
                                        <td class="ywc-merber-con">${ready.link_man }</td>
                                        <td class="ywc-phone-con">${ready.link_phone }</td>
                                        <td class="ywc-buy-time-con"><fmt:formatDate value="${ready.pay_time }" pattern="yyyy-MM-dd"/></td>
                                        <td class="ywc-class-add-con">${funcs:getCustNameByCustId(ready.sys_cust_id )}</td>
                                        <td class="ywc-surplus-time-con">
<%--                                         ${ready.order_count-ready.remain_count}--%><font class="j_xiangqing">1</font> 
                                        </td>
                                     <!--   <input type="hidden"  id="orderId"  value="${ready.id}"> -->

                                        </tr>
                                        <div class="signbox ">
                                        <em  >第<%=i++%>次&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${ready.finish_time }" type="date"  pattern="yyyy年MM月dd日    HH时mm分ss秒" /></em>
                                        </div>
                                </c:forEach>
                                </table>
                                <div class="btns-area"><a href="/coach/outCompletedExcel.action?infoid=${id}">导出数据</a></div>
                            </div>
                        </li>
                        <li>
                            <div class="sign_in">
                                <span class="sign-date">退款</span>
                                <span class="sign-show">展开</span>
                            </div>
                            <div class="dsk_in_head">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
                                    <tr>
                                        <th bgcolor="#f3f5f6" height="36" class="ywc-merber">会员</th>
                                        <th bgcolor="#f3f5f6" height="36" class="ywc-phone">手机号</th>
                                        <th bgcolor="#f3f5f6" height="36" class="ywc-buy-time">购买时间</th>
                                        <th bgcolor="#f3f5f6" height="36" class="ywc-class-add">上课地点</th>
                                        <th bgcolor="#f3f5f6" height="36" class="ywc-surplus-time">已上课时</th>
                                    </tr>
                                <c:forEach  var="refund"  items="${reFund }">
                                    <tr>
                                        <td class="ywc-merber-con">${refund.link_man }</td>
                                        <td class="ywc-phone-con">${refund.link_phone }</td>
                                        <td class="ywc-buy-time-con"><fmt:formatDate value="${refund.pay_time }" pattern="yyyy-MM-dd"/></td>
                                        <td class="ywc-class-add-con">${funcs:getCustNameByCustId(refund.sys_cust_id )}</td>
                                        <td class="ywc-surplus-time-con"> ${refund.order_count-refund.remain_count}<font class="j_xiangqing">详情</font></td>
                                    </tr>
                                    <div class="signbox ">
                                    <em >第<%=j++%>次&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${refund.finish_time }" type="date"  pattern="yyyy年MM月dd日    HH时mm分ss秒" /></em>
                                    </div>
                                </c:forEach>
                                </table>


                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="refund-apply closebox" style="display: none; height: 540px; margin-top: -270px;">
    <h3 class="create_syllabus_title">退款</h3>
    <div class="refund-apply-box wid360">
        <ul class="">
            <li><span>姓名：</span><em id="linkMan1">xiaolin</em></li>
            <li><span >电话：</span><em id="linkPhone1">187376432874</em></li>
            <li><span >购买时间：</span><em id="creteTime1">普拉提</em></li>
            <li><span>使用店铺：</span><em id="custName1">30</em></li>
            <li><span>已上课时：</span><em id="already1">290</em></li>
            <li><span>购买价：</span><em id="memPrice1">260</em></li>
            <li><span>剩余课时：</span><em id="remainCount1">260</em></li>
            <li><span>手续费：</span><em id="cha">260</em></li>
            <li><span>建议金额：</span><em id="refundMoney1">260</em></li>
            <li><input  type="hidden"  id="orderId1"  ></li>
            <li><span>实际退款：</span><input id="actualMoney1"  type="text" class="refund"> 元</li>
        </ul>
        <div class="refund_btns">
            <span class="create_syllabus_qx close">取消</span>
            <span class="confirm">申请退款</span>
        </div>
    </div>
</div>
<div id="Popup" class="closebox">
    <div id="PopupText">退款成功</div>
    <div id="PopupBtn"><span class="PopupBtnqx close">确定</span></div>
</div>
</body>
<script type="text/javascript">
//  团课退款流程弹出框
$(".j_tuikuan").click(function(){
    $(".refund-apply").show();
    showShadow();
    
    var id = $(this).parent().parent().children('#id').val();
	var linkMan=$(this).parent().parent().children("#linkMan").text();
	var linkPhone=$(this).parent().parent().children("#linkPhone").text();
	var creteTime=$(this).parent().parent().children("#creteTime").text();
	var custName=$(this).parent().parent().children("#custName").text();
	var already=$(this).parent().parent().children("#already").text();
	var memPrice=$(this).parent().parent().children("#memPrice").val();
	var remainCount=$(this).parent().parent().children("#remainCount").text();
	var refundMoney=$(this).parent().parent().children("#refundMoney").val();
	$("#orderId1").val(id);
	$("#linkMan1").text(linkMan);
	$("#linkPhone1").text(linkPhone);
	$("#creteTime1").text(creteTime);
	$("#custName1").text(custName);
	$("#already1").text(already);
	$("#memPrice1").text(memPrice);
	$("#remainCount1").text(remainCount);
	$("#cha").text(refundMoney-actualMoney);
	$("#refundMoney1").text(refundMoney);
	
	
});
//修改实际金额
$(".confirm").click(function(){
	var actualMoney=$("#actualMoney1").val();
	var orderid=$("#orderId1").val();
	$.ajax({
		url:'/coach/updateRefund.action',
		data:{"orderid":orderid,"actualMoney":actualMoney},
		type:'post',
		dataType:"json",
	    cache:false,
	    success:function(res) {
			if(res == "1"){
				$("#Popup").show();
			    $(this).parents(".closebox").hide();
				
			}else{
				alert("修改失败！");
			}
		 },
		 error:function(resJson){
			 alert("系统异常，请稍后再试！");
		 }
	})
})
// 取消关闭弹框
$(".close").click(function(){
    $(this).parents(".closebox").hide();
    $("#shade").remove();
});



// 添加签到详细信息展示
$(".j_xiangqing").on("click",function(){
    $(this).parents("li").next("div").toggle();
});

// 会员管理列表签到信息展示隐藏
$(".qiandao").click(function(){
    $(this).parents("li").find(".sign").toggle();
});

// 签到信息展开查看签到人
$(".sign-show").click(function(){
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