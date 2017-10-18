<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
  <title>会员卡列表</title>
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
			<%-- <!--左侧导航：开始-->
		 	<jsp:include page="/include/nav_left.jsp" flush="true">
				<jsp:param name="nvaIndex" value="3" />
			</jsp:include> 
			<!--左侧导航：结束--> --%>

	<div class="branch_wrap_cen">
		<!--左侧导航：开始-->
		<jsp:include page="/include/nav_left.jsp" flush="true">
			<jsp:param name="nvaIndex" value="28" />
			<jsp:param name="nvaParentIndex" value="23" />
		</jsp:include>
		<!--左侧导航：结束-->
		<div class="rightContent">
			<h3 class="i_title">会员卡详情</h3>
			<div class="province" style="height:25px;"></div>
			<div class="item_syllabus_list_tag">
			<input type="hidden" id="cardid" value="${bean}"/>
				<shiro:hasPermission name="membercard:detail">
				<span><a href="/membercard/detail.action?id=${bean}">会员卡信息</a></span>
				 </shiro:hasPermission>
				<span class="item_syllabus_list_tag_lie"><a href="#">售卖信息</a></span>
				<div style="display:block; position:absolute; right:183px;top:0px;">
				分店：
				<select id="custid" >
					<c:if test="${sysCustId==0 }">
						<option value="0">全部</option>
					</c:if>
					
					<c:if test="${sysCustId!=0 }">
						<option value="${sysCustId }">${funcs:getCustNameByCustId(sysCustId)}</option>
						<option value="0">全部</option>
					</c:if>
					
					
					<c:forEach items="${custInfos }" var="custInfo">
						<c:choose>
							<c:when test="${custInfo.systemId==custInfo.parentId || custInfo.systemId==sysCustId }">
							
							</c:when>
							<c:otherwise>
							<option value="${custInfo.systemId }">${custInfo.custName}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
					时间：<input value="${startDate}" name="control_date" class="control_date" type="text" class="" id="start_date" placeholder="开始时间" size="10" maxlength="10" onclick="new Calendar().show(this);" readonly="readonly">
							<input value="${endDate }"  name="control_date" class="control_date"  type="text" class="" id="end_date" placeholder="结束时间" size="10" maxlength="10" onclick="new Calendar().show(this);" readonly="readonly">
				</div>

				<a  id="search">搜索</a>

			</div>
			<div class="member_item_table">
				<div class="sell_info">
				   <div>
					   <em>
						   <div class="statistics_title">已销售</div>
						   <div class="statistics_num"><span>${salesNumbers}</span>张</div>
					   </em>
					   <em>
						   <div class="statistics_title">使用中</div>
						   <div class="statistics_num"><span>${useNumbers}</span>张</div>
					   </em>
					   <em>
						   <div class="statistics_title">未使用</div>
						   <div class="statistics_num"><span>${unUseNumbers}</span>张</div>
					   </em>
					   <em>
						   <div class="statistics_title">已过期</div>
						   <div class="statistics_num"><span>${expireNumbers}</span>张</div>
					   </em>
					   <em>
						   <div class="statistics_title">已退款</div>
						   <div class="statistics_num"><span>${refundNumbers}</span>张</div>
					   </em>
				   </div>
				</div>
			</div>
			<div class="member_item_table">
				<table cellspacing="0" cellpadding="0" border="0" width="100%" class="lis-table">
					<tr class="card_table_title">
						<th bgcolor="#f3f5f6" height="36">会员</th>
						<th bgcolor="#f3f5f6" height="36">手机号</th>
						<th bgcolor="#f3f5f6" height="36">购买时间</th>
						<th bgcolor="#f3f5f6" height="36">状态</th>
						<th bgcolor="#f3f5f6" height="36">续费状态</th>
						<th bgcolor="#f3f5f6" height="36">店铺名称</th>
					</tr>
							<c:set var="now" value="<%=new java.util.Date()%>" />
							<c:forEach var="order" items="${orderList}">
							<tr>

								<td class="">${order.link_man}</td>
								<td class="">${order.link_phone}</td>
								<td class=""><fmt:formatDate value="${order.crete_time}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
								<td class="">
							<fmt:formatDate value="${now}" var="nowDate" pattern="yyyy-MM-dd HH:mm:ss"/>
							<fmt:formatDate value="${order.start_time}" var="startTime" pattern="yyyy-MM-dd HH:mm:ss"/>
							<fmt:formatDate value="${order.end_time}" var="endTime" pattern="yyyy-MM-dd HH:mm:ss"/>
							<fmt:parseDate value="${nowDate}" var="nowDateCard" pattern="yyyy-MM-dd HH:mm:ss"/>
							<fmt:parseDate value="${startTime}" var="startTimeCard" pattern="yyyy-MM-dd HH:mm:ss"/>
							<fmt:parseDate value="${endTime}" var="endTimeCard" pattern="yyyy-MM-dd HH:mm:ss"/>
							<c:choose>
							 <c:when test="${order.status eq 4}">
							已取消
							 </c:when>
							 <c:when test="${order.status eq 5}">
							已退款
							 </c:when>
							 <c:when test="${order.status eq 6}">
							退款申请中
							 </c:when>
							  <c:when test="${order.is_active eq 0}">
							未使用
							 </c:when>
							  <c:when test="${nowDateCard > endTimeCard}">
							已过期
							 </c:when>
							 <c:otherwise>
							 使用中
							 </c:otherwise>
							 </c:choose>
							 </td>
							 <td class=""> ${order.count==0?"暂未统计":(order.count==1?"新用户":"续费") }</td>
							 <td class=""> ${funcs:getCustNameByCustId(order.sys_cust_id)}</td>
						 </tr>
							</c:forEach>

				</table>
					   <!-- 分页 开始 -->
					<div class="pager">
						<c:set var="pageUrl"
							value="/membercard/sell.action?id=${bean}&startDate=${startDate}&endDate=${endDate}&sysCustId=${sysCustId }&pageNo=pageno"
							scope="request" />
						<jsp:include flush="true" page="/include/pager.jsp" />
					</div>
				<!-- 分页 结束 -->
			</div>
			<div class="btns-area">
			<shiro:hasPermission name="membercard:report">
				<a id="excel" class="excle">导出</a>
			</shiro:hasPermission>
			</div>
		</div>
    </div>
</div>
<script>


    // 自定义竖滚动条
    $(function(){
    	
    	
        $('#testDiv').slimScroll({
            alwaysVisible: true
        });
        
      /*   $('#control_date').click(function() {
        	  var idcard=$("#idcard").val();
       	   var time= $("#control_date").val();
       	   alert("ceshi"+idcard+"时间：---"+time)
        }); */
       
        $('#search').click(function() {
        	  var date=$("#control_date").val();
              var  startDate=$("#start_date").val();
              var  endDate=$("#end_date").val();

              var stringTimeStart = startDate+" 0:0:0";
              var stringTimeEnd = endDate+" 23:59:59";
              var timestamp1 = Date.parse(new Date(stringTimeStart));
              var timestamp2 = Date.parse(new Date(stringTimeEnd));
             
//               console.log(stringTimeStart + "的时间戳为：" + timestamp1);
//               console.log(stringTimeEnd + "的时间戳为：" + timestamp2);
              
      		if(timestamp1 > timestamp2){
      			alert("开始日期不能大于结束日期");
      			return;
      		} 
           var id=$("#cardid").val();
           location.href="/membercard/sell.action?id="+id+"&startDate="+startDate+"&endDate="+endDate+"&sysCustId="+$("#custid").val();
        });
        
        $('#excel').click(function(){
        	 var id = $("#cardid").val();
        	 var  startDate=$("#start_date").val();
             var  endDate=$("#end_date").val();
        	location.href="/membercard/report.action?id="+id+"&startDate="+startDate+"&endDate="+endDate+"&sysCustId="+$("#custid").val();
        });
    		 
        $(".choosemerber_shop p").click(function(){
   	        var text = $(this).text();
   	        $(this).parents(".choosemerber").find(".chooseText").text(text);
   	    }); 
        
        
   		var bool=true;
   	    $("#update").click(function(){
   	    	if(bool){
           		bool=false;
           		submit();
           		bool=true;
           	}else{
           		alert("请勿重复提交,已保存请等待");
           	}
   	    });
   	    
   	 $("#delete").click(function(){
   		var idcard=$("#idcard").val();
   		$.ajax({
			 type:"post",
			 data:{"id":idcard},
			 url:"/membercard/delete.action",
			 dataType:"json",
			 cache:false,
			 beforeSend:function(xhr){
					//$("#loading").show();
			 },
			 success:function(res) {
				if(res.result == "1"){
					alert("删除成功！");
					location.href="/membercard/list.action";
				}else{
					alert("删除失败！");
				}
			 },
			 error:function(resJson){
				 alert("系统异常，请稍后再试！");
			 }
		 });
	 });
   	    
   	    
   	 	
   	 function submit(){
   		  var idcard=$("#idcard").val();
	   	  var cardName = $("#cardName").val();
	   	  var days = $("#days").val();
	      var salePrice = $("#salePrice").val();
	   	  var saleRule = $("#saleRule").text();
	      if(saleRule == '不限次'){
	       		saleRule='0';
	       	 }else if(saleRule == '1次'){
	       			saleRule='1';
	       	 }else{
	       			saleRule='2';
	       	 }
	      
	      var frel=$("#frel").attr('class');
	      var isRefund=1;
	      var fee=0;
	      if (frel == "on") { 
	    	}else{ 
       	     fee = $("#fee").val();
	       	 isRefund=0;
	       	  var refundRule = $("#refundRule").text();
	       	  if(refundRule == '扣除已消费月份'){
	       		refundRule='1';
	       	  }else{
	       		refundRule='0';
	       	  }
	       	  
	    }	  
	       	  var refundPrice = $("#refundPrice").val();
	       	  
	          var proDesc = $("#elm1").val();
	       	  
	       		 $.ajax({
						 type:"post",
						 data:{"id":idcard,"cardName":cardName,"days":days,"salePrice":salePrice,"saleRule":saleRule,"fee":fee,"isRefund":isRefund,"refundRule":refundRule,"refundPrice":refundPrice,"proDesc":proDesc},
						 url:"/membercard/update.action",
						 dataType:"json",
						 cache:false,
						 beforeSend:function(xhr){
								//$("#loading").show();
						 },
						 success:function(res) {
							if(res.result == "1"){
								alert("修改成功！");
								location.href="/membercard/list.action";
							}else{
								alert("修改失败！");
							}
						 },
						 error:function(resJson){
							 alert("系统异常，请稍后再试！");
						 }
					 });
		
         }
        
        
    })
</script>

</body>
</html>