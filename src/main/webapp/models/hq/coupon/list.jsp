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
  <title>优惠券列表</title>
    <link type="text/css" rel="stylesheet" href="/static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/left.css"/>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/base.js"></script>
    <script type="text/javascript" src="/static/js/left.js"></script>
  <!--  <script type="text/javascript" src="/static/js/custom.js"></script> -->
    <script type="text/javascript" src="/static/js/star.js"></script>
    <script type="text/javascript" src="/static/js/Calendar.js"></script>
</head>
           <style>
                	.search2{
                		padding: 15px 0;
    					height: 24px;
                	}
                	.jh{
                	    border-radius: 3px;
					    line-height: 24px;
					    height: 24px;
					    border: 1px solid #e5e5e5;
					    padding: 0 16px
                	}
                	.j_in{
                		width: 160px;
					    height: 22px;
					    border: 1px solid #e5e5e5;
					    border-radius: 3px;
                	}
                	.j_bt{
                		    background: #f8b500;
						    color: #fff;
						    padding: 2px 15px;
						    border: none;
						    line-height: 18px;
						    border-radius: 3px;
                	}
                	
                	   h4{
        text-align: center;
        }
      .merber_refund {
    width: 280px;
    height: 280px;
    z-index: 1010;
    position: absolute;
    left: 50%;
    top: 50%;
    margin-left:-85px;
    margin-top: -160px;
    display: none;
                </style>
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
				<jsp:param name="nvaIndex" value="201" />
			    <jsp:param name="nvaParentIndex" value="200" />
			</jsp:include> 
			<!--左侧导航：结束-->

			   <div class="rightContent">
            		<h3 class="i_title">优惠券列表</h3>
	<div class="province" style="height:25px;"></div>
                	${data.errcode}
                   <div class="cardList">
	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
		<tr>
			<th bgcolor="#f3f5f6" height="36" width="10%">分店名称</th>
			<th bgcolor="#f3f5f6" height="36" width="13%">生成时间</th>
			<th bgcolor="#f3f5f6" height="36" width="10%">金额</th>
			<th bgcolor="#f3f5f6" height="36" width="10%">起始使用金额</th>
			<th bgcolor="#f3f5f6" height="36" width="7%">剩余数量</th>
			<th bgcolor="#f3f5f6" height="36" width="5%">有效期</th>
			<th bgcolor="#f3f5f6" height="36" width="35%">优惠券地址（复制到微信中打开或分享）</th>
			<th bgcolor="#f3f5f6" height="36" width="10%">操作</th>
		</tr>
		<c:forEach var="code" items="${couponList}">
			<tr>
			<td>
			<c:choose>
				<c:when test="${code.sysCustId==8 }">
					通用
				</c:when>
				<c:otherwise>
					${funcs:getCustNameByCustId(code.sysCustId)}
				</c:otherwise>
			</c:choose>
			</td>
			<fmt:formatDate value="${code.createTime}" var="createTime" pattern="yyyy年MM月dd日"/>
			<td>${createTime}</td>
			<td>${code.money/100}元</td>
			<td>${code.origin/100}元</td>
			<td>${code.remainCount<1?0:code.remainCount}张</td>
			<td>${code.term}天</td>

			<td>http://feel.lol.com/coupon/weixin/index.action?cardId=${code.cardId}</td>
			<%-- 	                            <span class="exchange_cont_syzt">使用:${code.useNum}<br/>作废:${code.cancelNum}</span> --%>
			<td>
			<%-- 	                            <img src="http://www.lol.com/tool/createCode.jsp?w=260&url=http://feel.lol.com/coupon/weixin/index.action?cardId=${code.cardId}" alt=""/> --%>

			<em>
			<c:if test="${code.status eq '0'}">
				<a href="javascript:void(0)" data-id="${code.id }"  class="delete"  data-code="${code.cardId}">停止领取</a>
				<a href="javascript:;" id="${code.cardId}" class="erweima">二维码</a>
			</c:if>


			<a href="/coupon/detail.action?cardId=${code.cardId}" id="detail">详情</a>
			</em>

			</td>
			</tr>
		</c:forEach>
	</table>


				   </div>
                     <!-- 分页 开始 -->
 				<div class="pager">
					<c:set var="pageUrl"
						value="/coupon/list.action?pageNo=pageno"
						scope="request" />
					<jsp:include flush="true" page="/include/pager.jsp" />
				</div>
				<div class="btns-area"><a href="index.action" target="_blank" >新建优惠券</a></div>
			<!-- 分页 结束 -->
            <div class="clear_float"></div>
        </div>
    </div>
</div>
<div class="merber_refund">
	<h4>请用微信扫描</h4>
		<img  id="code" src="" alt=""/>
	</div>

<script>
    $(function(){
    	var showFlag = false;
    	
		$('body').click(function(){
			if(showFlag){
				hideShadow();
				$('.merber_refund').hide();
				window.location.reload();	
			}
		});

    	$(".erweima").click(function(){
    		$("#code").attr("src","http://www.lol.com/tool/createCode.jsp?w=260&url=http://feel.lol.com/coupon/weixin/index.action?cardId="+$(this).attr("id"));
    		$('.merber_refund').show();
			showShadow();
			showFlag = true;
			return false;
    	});
    	
    	
        
        // 取消关闭弹框
           $(".close").click(function(){
               $(this).parents(".closebox").hide();
               $("#shade").remove();
           });
    	
           $(".chooseshop").click(function(){
               $(this).find(".choose_gev_shop").toggle();
           });
    	
    	
           $(".choosemerber").click(function(){
               $(".choosemerber_shop").toggle();
           });
    	
           
           $(".choosemerber_shop p").click(function(){
      	        var text = $(this).text();
      	        var id = $(this).attr("id");
      	     
      	        $(this).parents(".choosemerber").find(".chooseText").text(text);
      	        $(this).parents(".choosemerber").find(".chooseText").attr("data-id",id);
       	  }); 
           
           
           
           var bool=true;
      		
      	    $("#submit").click(function(){
      	    	if(bool){
              		bool=false;
              		submit();
              		bool=true;
              	}else{
              		alert("请勿重复提交,已保存请等待");
              	}
      	    });
      	    
      	    
      	  $(".delete").click(function(){
          	
   		   if(confirm("确定要停止此优惠券的领取吗?")){
   	   		var id=$(this).attr("data-id");
   	   		var cardId=$(this).attr("data-code");
   	   		$.ajax({
   				 type:"post",
   				 data:{"cardId":cardId,"id":id},
   				 url:"/coupon/couponDelete.action",
   				 dataType:"json",
   				 cache:false,
   				 beforeSend:function(xhr){
   						//$("#loading").show();
   				 },
   				 success:function(res) {
   					if(res.errcode == 0){
   						alert("停止成功！");
   					}else{
   						alert("停止失败！");
   					}
   					window.location.reload();
   				 },
   				 error:function(resJson){
   					 alert("系统异常，请稍后再试！");
   				 }
   			 });
   		   }
       	
       });
      	    
           
    });
    	
    	
</script>

</body>

</html>