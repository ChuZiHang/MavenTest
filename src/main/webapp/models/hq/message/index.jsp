<%@page import="org.apache.shiro.SecurityUtils"%>
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
<title>启动健后台首页</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css"/>
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/jquery.js"></script>
<!-- <script type="text/javascript" src="/static/js/custom.js"></script> -->
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>
</head>
<body class="baseBgColor">

	<!--顶部：开始-->
	<jsp:include page="/include/header.jsp" flush="true">
		<jsp:param name="title" value="消息" />
	</jsp:include>
	<!--顶部：结束-->

	<div class="branch_wrap">
		<div class="branch_wrap_cen">
			<!--左侧导航：开始-->
			<jsp:include page="/include/nav_left.jsp" flush="true">
				<jsp:param name="nvaIndex" value="0" />
			</jsp:include>
			<!--左侧导航：结束-->



			<div class="rightContent">
				 <h3 class="i_title">消息
				  <shiro:hasPermission name="message:add">
				 <div class="send_message_btn">发送通知</div>
				 </shiro:hasPermission>
				 </h3>
            <div class="message_list">
                <ul>
                <c:forEach items="${mapMessage}"  var="message">
                    <li>
                        <div class="notice">通知：</div>
                        <div class="noticeText">
                            <div class="noticeTextBox">
                            ${message.key.message}
                             </div>
                            <div class="tag">
                            <c:forEach items="${message.value}" var="bean">
                            <c:if test="${bean.status == 1 }"><span>${funcs:getCustNameByCustId(bean.sysCustId)}已读</span></c:if>
                            <c:if test="${bean.status == 0 }"><span class="read">${funcs:getCustNameByCustId(bean.sysCustId)}未读</span></c:if>
                            </c:forEach>
                            </div>
                            <div class="messageshow"><span class="messopera">展开</span></div>
                        </div>
                    </li>
                    </c:forEach>
                </ul>
                    <!-- 分页 开始 -->
				<div class="pager">
					<c:set var="pageUrl"
						value="/message/list.action?pageNo=pageno"
						scope="request" />
					<jsp:include flush="true" page="/include/pager.jsp" />
				</div>
			<!-- 分页 结束 -->
            </div>
        </div>
    </div>
</div>
<div class="clear_float"></div>
<div class="messagebox closebox">
    <div class="messagetitle">发送通知</div>
    <div class="messageContent">通知内容</div>
    <div><textarea name="message" id="message"  cols="30" rows="10"  class="noticeFillText">请输入文字</textarea></div>
    <div class="messageContent">选择店铺</div>
    <div class="chooseshopmess">
        <span class="allshop"><i></i>全部</span>
        <c:forEach items="${custInfoList}" var="custinfo">
        <span ><i data-id="${custinfo.systemId}" ></i>${custinfo.custName}</span>
        </c:forEach>
    </div>
    <div class="clear_float"></div>
    <div class="messageBtn">
        <span class="sendMessCancel close">取消</span>
        <span id="submit" class="sendMessConfirm">确定</span>
    </div>
</div>
</body>
<script>
    $(function(){
    	
    	   $(".chooseshopmess span i").click(function(){
    	        if($(this).hasClass('rpass')){
    	            $(this).removeClass('rpass')
    	        }
    	        else{
    	            $(this).addClass('rpass')
    	        }
    	    });
    	
    	//全选
        $('.allshop').click(function(){
            if($('.allshop').hasClass('rpass')){
                $(this).removeClass('rpass')
                $('.chooseshopmess span i').removeClass('rpass')
            }
            else{
                $(this).addClass('rpass')
                $('.chooseshopmess span i').addClass('rpass')
            }
        });

    	 //   发送消息弹框
        $(".send_message_btn").click(function(){
            showShadow();
            $(".messagebox").show();
        });
    	 
        // 取消关闭弹框
        $(".close").click(function(){
            $(this).parents(".closebox").hide();
            $("#shade").remove();
        });
    	
   		var bool=true;
   		count=0;
   		
   	  $("#message").click(function(){
   		  if(count == 0){
   		$("#message").text('');
   		count=1;}
   	  });
   		
   	  
   	$(".noticeTextBox").each(function() {
        var noticeHeight = $(this).outerHeight();
        if (noticeHeight > 57) {
            $(this).css("height", 57);
            $(this).parents(".noticeText").find(".messopera").show();
        }
        var mydd = $('.messopera');
        mydd.each(function () {
            var num = 0;
            $(this).click(function () {
                if (num == 0) {
                    $(this).parents(".noticeText").find(".noticeTextBox").css("height", "auto");
                    $(this).text("收起");
                    num++;
                }
                else {
                    $(this).parents(".noticeText").find(".noticeTextBox").css("height", 57);
                    $(this).text("展开");
                    num = 0;
                }
            })
        })
    });

   		
   		
   	    $("#submit").click(function(){
   	    	if(bool){
           		bool=false;
           		submit();
           		bool=true;
           	}else{
           		alert("请勿重复提交,已保存请等待");
           	}
   	    });
    		
    		
   	 function submit(){
	   	  var message = $("#message").val();
	      if(message == '' || message == '请输入文字'){
	   		  alert("请填写消息内容");
	   		  return;
	   	  }
	    	var data = new Array();
	    	$("i[class='rpass']").each(function(){
	    		var dataId = $(this).attr("data-id");
	    		if(dataId != undefined || dataId == ''){
	    			data.push(dataId);
	    		}
	    	});
	    	if(data.length == 0){
	    		alert("请选择分店发送信息")
	    		return;
	    	}
     	$.ajax({
			 type:"post",
			 data:{"message":message,"data":data.join(",")},
			 url:"/message/add.action",
			 dataType:"json",
			 cache:false,
			 beforeSend:function(xhr){
					//$("#loading").show();
			 },
			 success:function(res) {
				if(res.result == 1){
					alert("消息发送成功！");
					location.href="/message/list.action";
				}else{
					alert("消息发送失败！");
				}
			 },
			 error:function(resJson){
				 alert("系统异常，请稍后再试！");
			 }
		 });
		
         }
   	 
        	 
   	 
   	 
        
    });

</script>
</html>