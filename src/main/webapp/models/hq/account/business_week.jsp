<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
  <title>营业报表</title>
    <link type="text/css" rel="stylesheet" href="/static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/left.css"/>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/base.js"></script>
    <script type="text/javascript" src="/static/js/left.js"></script>
    <script type="text/javascript" src="/static/js/star.js"></script>
    <script type="text/javascript" src="/static/js/file.js"></script>
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
				<jsp:param name="nvaIndex" value="31" />
				<jsp:param name="nvaParentIndex" value="25" />
			</jsp:include> 
			<!--左侧导航：结束-->

			   <div class="rightContent">
            <h3 class="i_title">营业报表</h3>
            <div class="province"></div>
            <div class="busuness_title">
                <div class="member_detail_tag">
                    <span><em><a href="/account/business/report.action">日表</a></em></span>
                    <span><em><a href="/account/business/report_month.action">月表</a></em></span>
                    <span class="on"><em class="bordernone "><a href="/account/business/report_week.action">周表</a></em></span>
                </div>
                <div class="busuness_title_con">
                    <span class="joinbusiness_right"><img id="right" src="/static/images/yingyebaobiao-youjiantou.png" alt=""/></span>
                    <span id="myDate"><fmt:formatDate value="${startDate }" pattern="yyyy年MM月dd日"/> -- <fmt:formatDate value="${endDate}" pattern="yyyy年MM月dd日"/></span>
                    <input id="curDate" value="${startDate }" type="hidden">
                    <a href="/account/business/report_week.action"><div class="joinbusiness_today">当周</div></a>
                    <span class="joinbusiness_left"><img id="left" src="/static/images/yingyebaobiao-zuojiantou.png" alt=""/></span>
                </div>
            </div>
            <div class="padt20">
                <div class="business_select">
                    <c:if test="${parentType == 1 }">
                    <span class="create_syllabus_item_name">分店</span>
                	<div class="chooseshop">
                		<c:choose>
                			<c:when test="${custId == 0}">
                				<span  id="fendian1" class="chooseText">全部</span>
	                    		<input id="sysCustId" name="sysCustId" class="hidden" type="hidden" value="0">
                			</c:when>
                			<c:when test="${custId != 0}">
                				<span  id="fendian1" class="chooseText">${funcs:getCustNameByCustId(custId )}</span>
	                    		<input id="sysCustId" name="sysCustId" class="hidden" type="hidden" value="${custId}">
                			</c:when>
                		</c:choose>
	                    <i></i>
	                    <div id="chooseShop" class="choose_gev_shop">
	                        <!-- <p>江南春店</p> -->
	                    </div>
                	</div>
                </c:if>
                </div>

                 <div class="business_select">
                    <span class="business_select_title">类型：</span>
					<div class="chooseshop">
						<c:choose>
							<c:when test="${type == 0 }">
								<span id="leixing1" class="chooseText">全部</span>
                    			<input class="hidden" id="type" type="hidden" value="0">
							</c:when>
							<c:when test="${type != 0 }">
								<span id="leixing1" class="chooseText">${typeName} </span>
                    			<input class="hidden" id="type" type="hidden" value="${type }">
							</c:when>
						</c:choose>
                    	<i></i>
                    	<div class="choose_gev_shop">
	                        <p id="0">全部</p>
	                        <p id="10">卡</p>
	                        <p id="11">收费团课</p>
	                        <p id="12">课程</p>
	                        <p id="13">私教</p>
                    	</div>
                	</div> 
                </div>
                <div class="business_select">
                <button class="business_select_title" id="search" style="background: #f8b500;color: #fff;border: none;font-size: 14px;padding: 5px 25px;width: 90px;text-align: center;height: 34px;top: 0;border-radius: 3px;">搜索</button>
                </div>
                <div class="amount">
                    <em>总收入：${totalMoney/100}元</em>
                    <!-- <em>总支出：5000</em> -->
                </div>
                <div class="export">导出报表</div>
            </div>
            <div class="business_cont" style="overflow: scroll;">
                <div class="business_cont_title" style="width:1348px;">
                    <span class="wid118">分店</span>
                    <span class="wid98">类型</span>
                    <span class="wid98">产品名称</span>
                    <span class="wid58">数量</span>
                    <span class="wid78">支付金额</span>
                    <span class="wid78">退款金额</span>
                    <span class="wid78">营收</span>
                    <span class="wid98">会员</span>
                    <span class="wid98" style="width:180px;">订单号</span>
                    <span class="wid98">支付方式</span>
                    <span class="wid138">支付时间</span>
                    <span class="wid138">退款时间</span>
                    <span class="wid78">操作人</span>
                </div>
                <div class="business_cont_content" style="width:1348px;">
                    <ul>
                      <c:forEach items="${orderList}" var="order">
                        <li>
                            <span class="wid117">${funcs:getCustNameByCustId(order.sysCustId )}</span>
                            <span class="wid97">
                            	<c:if test="${order.orderType == 10}">卡</c:if>
                            	<c:if test="${order.orderType == 11}">收费团课</c:if>
                            	<c:if test="${order.orderType == 12}">课程</c:if>
                            	<c:if test="${order.orderType == 13}">私教</c:if>
                            </span>
                            <span class="wid97">
                                <c:if test="${order.orderType == 10}">${funcs:getProductNameByProductId(order.productId +"")}</c:if>
                            	<c:if test="${order.orderType == 11}">${funcs:getProductNameByProductId(order.productId +"")}(${funcs:getCoachNameByProductId(order.productId +"")})  </c:if>
                            	<c:if test="${order.orderType == 12}">${funcs:getCourseNameByProductId(order.productId +"")}</c:if>
                            	<c:if test="${order.orderType == 13}">${funcs:getCoachNameById(order.productId )}</c:if></span>
                            <span class="wid57">${order.num }</span>
                            <span class="wid77">${order.memPrice/100 }元</span>
                            <span class="wid77">${order.actualMoney/100 }元</span>
                            <span class="wid77">${order.memPrice/100 - order.actualMoney/100}元</span>
                            <span class="wid97">${order.linkMan }</span>
                            <span class="wid96" style="width:178px;">${order.id }</span>
                             <span class="wid97">
                            	<c:if test="${order.payType == 0}">网上</c:if>
                            	<c:if test="${order.payType == 1}">线下(现金)</c:if>
                            	<c:if test="${order.payType == 2}">线下(刷卡)</c:if>
                            	<c:if test="${order.payType == 3}">线下(支付宝)</c:if>
                            </span>
                            <span class="wid137"><fmt:formatDate value="${order.payTime}" pattern="yyyy-MM-dd HH:mm"/></span>
                            <span class="wid137"><fmt:formatDate value="${order.refundTime}" pattern="yyyy-MM-dd HH:mm"/></span>
                            <c:choose>
                            		<c:when test="${!  empty order.refundUser}"><span class="wid77">${order.refundUser }</span></c:when>
                           			<c:otherwise><span class="wid77">无</span></c:otherwise>
                            </c:choose>
                            
                        </li>
                      </c:forEach>
                    </ul>
                </div>
                <!-- 分页 开始 -->
				<div class="pager">
					<c:set var="pageUrl"
						value="/account/business/report_week.action?pageNo=pageno&date=${date3 }&type=${type }&custId=${custId }"
						scope="request" />
					<jsp:include flush="true" page="/include/pager.jsp" />
				</div>
				<!-- 分页 结束 -->
            </div>
            <div class="clear_float"></div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
//发布课程选择分店
$(".chooseshop").click(function(){
    $(this).find(".choose_gev_shop").toggle();
});
$(".choosemerber").click(function(){
    $(".choosemerber_shop").toggle();
});

$(".choose_gev_shop p").click(function(){
    var text = $(this).text();
    var id = $(this).attr("id");
    $(this).parents(".chooseshop").find(".chooseText").text(text)
    $(this).parents(".chooseshop").find(".hidden").val(id);
});
//查询
$("#search").click(function(){
	var type = $("#type").val();
	var custId = $("#sysCustId").val();
	var date = $("#curDate").val();
	
	var date1 = new Date(date);
	date1.setHours(date1.getHours() + 8);
	//console.log(date1.toJSON());
	if($.trim(type).length == 0){
		type1 = 0;
	}
	if($.trim(custId).length == 0){
		custId = 0;
	}
	location.href="/account/business/report_week.action?type="+type+"&custId="+custId+"&date="+date1.toJSON();
});
//导出execel
$(".export").click(function(){
	var type = $("#type").val();
	var custId = $("#sysCustId").val();
	var date = $("#curDate").val();
	
	var date1 = new Date(date);
	date1.setHours(date1.getHours() + 8);
	//console.log(date1.toJSON());
	if($.trim(type).length == 0){
		type1 = 0;
	}
	if($.trim(custId).length == 0){
		custId = 0;
	}
	location.href="/account/business/export_week.action?type="+type+"&custId="+custId+"&date="+date1.toJSON();
});
</script>
<script type="text/javascript">
$(function() {
	//分店列表
	$.ajax({
		 type:"post",
		 url:"/course/group/subbranchList.action",
		 dataType:"json",
		 cache:false,
		 success:function(res) {
			 $('#chooseShop').append('<p id="0">'+"全部"+'</p>');
			for(var i=0;i<res.subbranchList.length;i++){
					$('#chooseShop').append('<p id="'+res.subbranchList[i].system_id+'">'+res.subbranchList[i].cust_name+'</p>');
			};
			
			$(".choose_gev_shop p").click(function(){
	            var name = $(this).text();
	            var id = $(this).attr("id");
	            $(this).parents(".chooseshop").find(".chooseText").text(name);
	            $(this).parents(".chooseshop").find(".hidden").val(id);
	            //$("#sysCustId").val(id);
	        })
		 },
		 error:function(resJson){
			 alert("系统异常，请稍后再试！");
		 }
	});
})
</script>
<script type="text/javascript">
	
	/* var myDate = new Date();
	var w = myDate.getDay();
	var n = (w == 0 ? 7 : w) - 1;
	myDate.setDate(myDate.getDate() - n);
	var y1 = myDate.getFullYear();//年
	var m1 = myDate.getMonth() + 1;//月
	var d1 = myDate.getDate();//日
	$("#curDate").val(myDate);
	myDate.setDate(myDate.getDate()+6);
	var y2 = myDate.getFullYear();//年
	var m2 = myDate.getMonth() + 1;//月
	var d2 = myDate.getDate();//日
	var showDate = y1+"年"+m1+"月"+d1+"日  --"+y2+"年"+m2+"月"+d2+"日 " ;
	$("#myDate").text(showDate); */
	
	
	$("#right").click(function(){
		var curDate = $("#curDate").val();
		var fff = new Date(Date.parse(curDate.replace(/-/g,   "/")));
		var preDate = new Date(fff.getTime() - 24*60*60*1000*7);  //后一周
		$("#curDate").val(preDate.getFullYear()+"-"+(preDate.getMonth()+1)+"-"+preDate.getDate());
		var y1 = preDate.getFullYear();//年
		var m1 = preDate.getMonth() + 1;//月
		var d1 = preDate.getDate();//日
		preDate.setDate(preDate.getDate()+6);
		var y2 = preDate.getFullYear();//年
		var m2 = preDate.getMonth() + 1;//月
		var d2 = preDate.getDate();//日
		if(d1 < 10){
			d1 = "0"+d1;
		}
		if(m1 < 10){
			m1 = "0"+m1;
		}
		if(d2 < 10){
			d2 = "0"+d2;
		}
		if(m2 < 10){
			m2 = "0"+m2;
		}
		var showDate = y1+"年"+m1+"月"+d1+"日  -- "+y2+"年"+m2+"月"+d2+"日 " ;
		$("#myDate").text(showDate);
	});
	 $("#left").click(function(){
		var curDate = $("#curDate").val();
		var fff = new Date(Date.parse(curDate.replace(/-/g,   "/")));
		var preDate = new Date(fff.getTime() + 24*60*60*1000*7);  //前一天
		$("#curDate").val(preDate.getFullYear()+"-"+(preDate.getMonth()+1)+"-"+preDate.getDate());
		var y1 = preDate.getFullYear();//年
		var m1 = preDate.getMonth() + 1;//月
		var d1 = preDate.getDate();//日
		preDate.setDate(preDate.getDate()+6);
		var y2 = preDate.getFullYear();//年
		var m2 = preDate.getMonth() + 1;//月
		var d2 = preDate.getDate();//日
		if(d1 < 10){
			d1 = "0"+d1;
		}
		if(m1 < 10){
			m1 = "0"+m1;
		}
		if(d2 < 10){
			d2 = "0"+d2;
		}
		if(m2 < 10){
			m2 = "0"+m2;
		}
		var showDate = y1+"年"+m1+"月"+d1+"日  -- "+y2+"年"+m2+"月"+d2+"日 " ;
		$("#myDate").text(showDate);
		
	});
	
</script>
</html>