<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="referrer" content="no-referrer" /> 

<title>新建优惠券</title>
	<link type="text/css" rel="stylesheet" href="/static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/left.css"/>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/base.js"></script>
    <script type="text/javascript" src="/static/js/left.js"></script>
    <script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
</head>

<body>
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
            <h3 class="i_title">新建优惠券</h3>
            <div class="province" style="height:25px;"></div>
            <div class="syllabus_detail_cont">
                <div class="create_syllabus_cont syllabus_detail_show card_infor_show">
                    <ul>
                         <li >
                         <span class="create_syllabus_item_name">优惠券门店</span>
                        <select id="select_id" class="" style="border: 1px solid #e5e5e5;height: 28px;border-radius: 3px;width: 130px;margin-left: 15px;">
    <!--                                     <option value="0" selected="">请选择</option> -->

                                        <c:forEach var="cust" items="${custInfos }">

                                        <c:choose>
                                            <c:when test="${cust.systemId==cust.parentId }">
                                                <option value="${cust.systemId }" >通用</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${cust.systemId }">${cust.custName}</option>
                                            </c:otherwise>
                                        </c:choose>
                                        </c:forEach>
                                    </select>
                                    </li>
                        <li>
                            <span class="create_syllabus_item_name">服务电话</span>
                            <input id="mobile" type="text" class="create_syllabus_input" value="" style="width: 250px">
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">金额</span>
                            <input id="money" type="text" class="create_syllabus_input" value="" style="width: 50px">元
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">数量</span>
                            <input id="sum" type="text" class="create_syllabus_input" value="" style="width: 50px">张
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">有效期</span>
                            <input id="days" type="text" class="create_syllabus_input" value="" style="width: 50px"> 天
                        </li>
                         <li>
                            <span class="create_syllabus_item_name">每人限领数</span>
                            <input id="num" type="text" class="create_syllabus_input" value="" style="width:50px"> 张
                        </li>
                         <li>
                            <span class="create_syllabus_item_name">起始使用金额</span>
                            <input id="start_money" type="text" class="create_syllabus_input" value="" style="width:50px"> 元
                        </li>

                        <li>
                            <span class="create_syllabus_item_name">使用说明</span>
                            <textarea id="elm1" name="elm1" class="xheditor" rows="12" cols="80" style="width:442px"></textarea>
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">分享领券页</span>
                            <div id="share" class="tuikuan">
                            <input type="radio" name="share" value="yes" checked="true"  >可以 &nbsp &nbsp
                            <input type="radio" name="share" value="no"  >不可以
                            </div>
                        </li>
                        <li>
                            <span id="friend"  class="create_syllabus_item_name">赠送好友</span>
                            <div class="tuikuan">
                                <input type="radio" name="friend" value="yes" checked="true"  >可以 &nbsp &nbsp
                                 <input type="radio" name="friend" value="no"  >不可以
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
    <div class="btns-area">
        <a href="javascript:;" id="create" >保存卡券信息</a>
    </div>
        </div>
    </div>
</div>
</body>



<script type="text/javascript">
	
	$('#save').click(function(){
		var formData = new FormData($("#f")[0]);  
		//console.log(formData);
		$.ajax({
			type : "post",
			data :formData,
			url : "/coupon/uploadimg.action",
			contentType: false,
			processData: false,
			dataType:"json",
			success : function(res) {
				console.log(res);
				if(res.status==0){
					//var url="http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=";
					$('#logo').attr('src',res.data);
					
				}
				
				//window.location.reload();
			},
			error : function(res) {
				alert("网络异常，请稍后再试");
			}
		});
		
	});
	
	
	
	$('#create').click(function(){
		
		
		if($('#mobile').val()=='' || $('#money').val()=='' || $('#days').val()=='' || $('#num').val()==''||$('#sum').val()==''||$('#start_money').val()==''||$('#elm1').val()=='' ){
			alert('请认真填写数据');
			return false;
		}
		
		$.ajax({
			type :"get",
			url:"/coupon/create.action",
			data:{cust_id:$('#select_id option:selected').val(),service_phone:$('#mobile').val(),money:$('#money').val(),fixed_term:$('#days').val(),get_limit:$('#num').val(),sum:$('#sum').val(),start_money:$('#start_money').val(),default_detail:$('#elm1').val(),share:$("input[name='share']:checked").val(),friend:$("input[name='friend']:checked").val()},
	 		dataType : "json",
	 		cache : false,
	 		success : function(res) {
	 			console.log(res);
	 			
	 			if(res.errcode == 0){
	 				alert('新建成功！');
					window.location.href="list.action";
	 			}else{
	 				alert("新建失败，请稍后再试!");
	 			}
	 			
			},
			error:function(res){
				
				alert("网络加载失败，请稍后再试！");
			}
		});
		
		
		
	});
	
	

</script>

</html>


