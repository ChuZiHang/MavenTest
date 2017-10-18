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
  <title>营收报表</title>
    <link type="text/css" rel="stylesheet" href="/static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/left.css"/>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/base.js"></script>
    <script type="text/javascript" src="/static/js/left.js"></script>
    <script type="text/javascript" src="/static/js/star.js"></script>
    <script type="text/javascript" src="/static/js/file.js"></script>
    <script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
    <script type="text/javascript" src="/static/third/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" media="all" href="/static/css/daterangepicker-bs3.css" />
	<link rel="stylesheet" type="text/css" media="all" href="/static/css/daterangepicker-1.3.7.css" />
	<link href="/static/css/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <script type="text/javascript" src="/static/js/moment.js"></script>
    <script type="text/javascript" src="/static/js/daterangepicker-1.3.7.js"></script>
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
			<jsp:param name="nvaParentIndex" value="157" />
		</jsp:include>
		<!--左侧导航：结束-->
		   <div class="rightContent">
				<h3 class="i_title">营收报表</h3>
				<div class="search_area">
					<div class="business_select">
						<c:if test="${parentType == 1 }">
						<span class="member_item_title">分店</span>
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
					<div class="control-group" style="float: left;padding-left: 49px;position: relative; ">
						<div class="controls">
								<span class="business_select_title">日期：</span>
								<div id="reportrange" class="pull-left dateRange" style="width:300px;height:33px;">
									<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
									<span id="searchDateRange" data-start="${startDate}" data-end="${endDate}">${data }</span>
									<b class="caret"></b>
								</div>
							</div>
					</div>
				<div class="business_select">
					<button class="business_select_title" id="search" style="background: #60cd60;color: #fff;border: none;font-size: 14px;padding: 5px 25px;width: 90px;text-align: center;height: 34px;top: 0;border-radius: 3px;">搜索</button>
				</div>
				<div class="clear_float"></div>
			</div>
				<div class="member_item_table">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
					<tr>
						<th bgcolor="#f3f5f6" height="36">分店</th>
						<th bgcolor="#f3f5f6" height="36">类型</th>
						<th bgcolor="#f3f5f6" height="36">产品名称</th>
						<th bgcolor="#f3f5f6" height="36">数量</th>
						<th bgcolor="#f3f5f6" height="36">支付金额</th>
						<th bgcolor="#f3f5f6" height="36">优惠金额</th>
						<th bgcolor="#f3f5f6" height="36">营收</th>
						<th bgcolor="#f3f5f6" height="36">会员</th>
						<th bgcolor="#f3f5f6" height="36">订单号</th>
						<th bgcolor="#f3f5f6" height="36">支付方式</th>
						<th bgcolor="#f3f5f6" height="36">支付时间</th>
					</tr>
					<c:forEach items="${orderList}" var="order">
							<tr>
								<td>${funcs:getCustNameByCustId(order.sysCustId )}</td>
								<td align="center">
									<c:if test="${order.orderType == 10}">卡</c:if>
									<c:if test="${order.orderType == 11}">收费团课</c:if>
									<c:if test="${order.orderType == 12}">课程</c:if>
									<c:if test="${order.orderType == 13}">私教</c:if>
								</td>
								<td>
								<div style="width:60px;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;" title="<c:if test='${order.orderType == 10}'>${funcs:getProductNameByProductId(order.productId +'')}</c:if><c:if test='${order.orderType == 11}'>${funcs:getProductNameByProductId(order.productId +'')}(${funcs:getCoachNameByProductId(order.productId +'')})  </c:if><c:if test='${order.orderType == 12}'>${funcs:getCourseNameByProductId(order.productId +'')}</c:if><c:if test='${order.orderType == 13}'>${funcs:getCoachNameById(order.productId )}</c:if>">
									<c:if test="${order.orderType == 10}">${funcs:getProductNameByProductId(order.productId +"")}</c:if>
									<c:if test="${order.orderType == 11}">${funcs:getProductNameByProductId(order.productId +"")}(${funcs:getCoachNameByProductId(order.productId +"")})  </c:if>
									<c:if test="${order.orderType == 12}">${funcs:getCourseNameByProductId(order.productId +"")}</c:if>
									<c:if test="${order.orderType == 13}">${funcs:getCoachNameById(order.productId )}</c:if>
								</div>
								</td>
								<td align="center">${order.num }</td>
								<td align="center">${order.memPrice/100 }元</td>
								
								<%--   请注意： 因为上级急于上线，优惠金额用    （单价*数量-实付金额）而成，没有在controller里面联表查数据库 --%>
								
								<td align="center">${(order.memPrice- order.salePrice*order.num)/100 }元</td>
							  <%--   <td align="center">${order.actualMoney/100 }元</td> --%>
								<td align="center">${order.memPrice/100 - order.actualMoney/100}元</td>
								<td><div style="width:60px;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;" title="${order.linkMan }">${order.linkMan }</div></td>
								<td align="center">${order.id }</td>
								 <td align="center">
									<c:if test="${order.payType == 0}">网上</c:if>
									<c:if test="${order.payType == 1}">线下(现金)</c:if>
									<c:if test="${order.payType == 2}">线下(刷卡)</c:if>
									<c:if test="${order.payType == 3}">线下(支付宝)</c:if>
									<c:if test="${order.payType == 4}">兑换码</c:if>
								</td>
								<td align="center"><fmt:formatDate value="${order.payTime}" pattern="yyyy-MM-dd HH:mm"/></td>
							   <%--  <td align="center"><fmt:formatDate value="${order.refundTime}" pattern="yyyy-MM-dd HH:mm"/></td>
								<c:choose>
										<c:when test="${!  empty order.refundUser}"><td align="center">${order.refundUser }</td></c:when>
										<c:otherwise><td align="center">无</td></c:otherwise>
								</c:choose> --%>
							</tr>
					 </c:forEach>
				</table>
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<th>
						<div style="text-align:left; padding:15px;font-size: 14px;">总收入：${totalMoney/100}元</div>
						</th>
					</tr>
				</table>
				</div>
	<!-- 分页 开始 -->
	<div class="pager">
	<c:set var="pageUrl"
		   value="/account/business/report.action?pageNo=pageno&&type=${type }&custId=${custId }&startDate=${startDate}&endDate=${endDate }&data=${data }"
		   scope="request" />
	<jsp:include flush="true" page="/include/pager.jsp" />
	</div>
	<!-- 分页 结束 -->
	<div class="btns-area">
		<a href="javascript:void(0)" id="exportdd">导出报表</a>
	</div>
			</div>
	</div>
</div>
</body>
<style>
.form-group {position: relative;width:140px;}
.form-group-txt{height:32px;line-height:32px;padding:0 10px;}
.form-group-select {/*padding-left: 1px;*/}
.form-control,
.simulation-input {
    width: 130px;
    line-height: 16px;
    font-size: 14px;
    color: #4b555b;
    background: none;
    outline: none;
    border: 1px solid #d3dcdd;
    background-color: #fff;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    -ms-box-sizing: border-box;
    box-sizing: border-box;
    padding: 7px 8px;
    *padding-left: 0;
    *padding-right: 0;
    *text-indent: 8px;
    *float: left;
    transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s ease-in-out 0s;
}
.el-table{
	background:#fff;
	border-top:1px solid #e5e5e5;
	border-right:1px solid #e5e5e5;
	clear:both;
	
	margin:25px 0;
}
.el-table th{
	background:#f3f5f6;
	border-left:1px solid #e5e5e5;
	border-bottom:1px solid #e5e5e5;
	text-align:center;
}
.el-table td{
	padding:5px;
	border-left:1px solid #e5e5e5;
	border-bottom:1px solid #e5e5e5;
}
</style>
<script type="text/javascript">
		$(document).ready(function (){
			
					//时间插件
				     $('.daterange span').html(moment().subtract('hours',1).format('YYYY-MM-DD HH:mm:ss') + '~ ' + moment().format('YYYY-MM-DD HH:mm:ss'));
		
					$('#reportrange').daterangepicker(
							{
								//startDate: moment().subtract('hours',1),
								//endDate: moment(),
								//minDate: '01/01/2012',	//最小时间
								maxDate : moment(), //最大时间 
								dateLimit : {
									days : 120
								}, //起止时间的最大间隔
								showDropdowns : true,
								showWeekNumbers : false, //是否显示第几周
								timePicker : true, //是否显示小时和分钟
								timePickerIncrement : 60, //时间的增量，单位为分钟
								timePicker12Hour : false, //是否使用12小时制来显示时间
								ranges : {
									//'最近1小时': [moment().subtract('hours',1), moment()],
									'今日': [moment().startOf('day'), moment()],
				                    '昨日': [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],
				                    '最近7日': [moment().subtract('days', 6), moment()],
				                    '最近30日': [moment().subtract('days', 29), moment()]
								},
								opens : 'right', //日期选择框的弹出位置
								buttonClasses : [ 'btn btn-default' ],
								applyClass : 'btn-small btn-primary blue',
								cancelClass : 'btn-small',
								format : 'YYYY-MM-DD ', //控件中from和to 显示的日期格式
								separator : ' to ',
								locale : {
									applyLabel : '确定',
									cancelLabel : '取消',
									fromLabel : '起始时间',
									toLabel : '结束时间',
									customRangeLabel : '自定义',
									daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
									monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',
											'七月', '八月', '九月', '十月', '十一月', '十二月' ],
									firstDay : 1
								}
							}, function(start, end, label) {//格式化日期显示框
				                
			                 	$('#reportrange span').html(start.format('YYYY-MM-DD HH:mm:ss') + ' ~ ' + end.format('YYYY-MM-DD HH:mm:ss'));
			               });

				//设置日期菜单被选项  --开始--
		      	/*   var dateOption ;
		      	  if("${riqi}"=='day') {
						dateOption = "今日";
		          }else if("${riqi}"=='yday') {
						dateOption = "昨日";
		          }else if("${riqi}"=='week'){
						dateOption ="最近7日";
		          }else if("${riqi}"=='month'){
						dateOption ="最近30日";
		          }else if("${riqi}"=='year'){
						dateOption ="最近一年";
		          }else{
						dateOption = "自定义";
		          }
		      	   $(".daterangepicker").find("li").each(function (){
						if($(this).hasClass("active")){
							$(this).removeClass("active");
						}
						if(dateOption==$(this).html()){
							$(this).addClass("active");
						}
		          }); */
			      	   //设置日期菜单被选项  --结束--
		      	 $("#search").click(function(){
		      		var type = $("#type").val();
		      		var custId = $("#sysCustId").val();
		      		var data=$("#searchDateRange").text();
		      		var array=data.split("~");
		      		var startDate=array[0].trim();
		      		var endDate=array[1].trim();
		      	
		      		if($.trim(type).length == 0){
		      			type1 = 0;
		      		}
		      		if($.trim(custId).length == 0){
		      			custId = 0;
		      		}
		      		location.href="/account/business/report.action?type="+type+"&custId="+custId+"&startDate="+startDate+"&endDate="+endDate+"&data="+data;
		      	});
		      	//导出execel
		      	$("#exportdd").click(function(){
		      		var type = $("#type").val();
		      		var custId = $("#sysCustId").val();
		      		var data=$("#searchDateRange").text();
		      		var array=data.split("~");
		      		var startDate=array[0].trim();
		      		var endDate=array[1].trim();
		      		if($.trim(type).length == 0){
		      			type1 = 0;
		      		}
		      		if($.trim(custId).length == 0){
		      			custId = 0;
		      		}
		      		location.href="/account/business/export.action?type="+type+"&custId="+custId+"&startDate="+startDate+"&endDate="+endDate;
		      	});
		})
		//查询

	</script>
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
</html>