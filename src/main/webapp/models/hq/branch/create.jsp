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
	<head>
<title>启动健后台首页</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css"/>
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
 <script type="text/javascript" src="/static/js/left.js"></script>
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
		<jsp:param name="nvaIndex" value="29" />
		<jsp:param name="nvaParentIndex" value="23" />
	</jsp:include>
	<!--左侧导航：结束-->
		<div class="rightContent">
			<div class="i_title">
				新建分店页面</a>
			</div>
			<div class="syllabus_detail_cont">
				<div class="create_syllabus_cont syllabus_detail_show card_infor_show" style="width: 600px;">
					<form method="post" id="save">
					<ul>
						<li>
							<span class="create_syllabus_item_name">分店名称</span>
							<input name="id" type="hidden" class="create_syllabus_input" value="${custInfo.id }">
							<input name="name" type="text" class="create_syllabus_input" value="${custInfo.custName }">
						</li>
						<li>
							<span class="create_syllabus_item_name">所属省份</span>
							<select id="sysCustId" name="province" class="joinbusiness" onscroll="true" style="height: 34px;
													margin-left: 0px;">
						 <option value="${infoAreaExParent.areaId }" selected="selected">${infoAreaExParent.areaId>0?infoAreaExParent.areaName:"请选择" }</option>
						 <c:forEach  var="area" items="${areas }">
							<option class="item" value="${area.treeId }">${area.treeName }</option>
						 </c:forEach>


					  </select>
						</li>
						<li>
							<span class="create_syllabus_item_name">所属地区</span>
							<select id="sysCustId02" name="city" class="joinbusiness" onscroll="true" style="height: 34px;
													margin-left: 0px;">
						 <option value="${infoAreaEx.areaId }" selected="selected">${infoAreaEx.areaId>0?infoAreaEx.areaName:"请选择" }</option>
						 <c:forEach  var="area02" items="${areas02 }">
							<option class="item" value="${area02.treeId }">${area02.treeName }</option>
						 </c:forEach>
					  </select>
						</li>
						<li>
						<span class="create_syllabus_item_name">地址</span>
						<input type="text" id="days" name="address" class="create_syllabus_input wid120" style="width:100px;" value="${custInfo.address }">
						<em class="create_syllabus_input_p">经度</em>
						<input id="lon" name="lon" type="text" class="create_syllabus_input wid120" style="width:100px;" value="${custInfo.longitude }">
						<em class="create_syllabus_input_p">纬度</em>
						<input id="lat" name="lat" type="text" class="create_syllabus_input wid120" style="width:100px;" value="${custInfo.latitude }">
						</li>
						<li>
							<span class="create_syllabus_item_name">分店负责人</span>
							<input type="text" name="username" class="create_syllabus_input" value="${custInfo.linkMan }">
						</li>
						<li>
							<span class="create_syllabus_item_name">联系电话</span>
							<input type="text" name="mobile" class="create_syllabus_input" value="${custInfo.linkMobile }">
						</li>

						<c:choose>

						 <c:when test="${userLogin.id>0 }">
							 <li>
								<span class="create_syllabus_item_name">负责人账号</span>
								<input type="text" name="account" class="create_syllabus_input" disabled="true" value="${userLogin.username }">
							</li>
							<li>
								<span class="create_syllabus_item_name">密码</span>
								<input type="text" name="password" disabled="true" class="create_syllabus_input" value="******">
							</li>
						 </c:when>
						 <c:otherwise>

							 <li>
								<span class="create_syllabus_item_name">负责人账号</span>
								<input type="text" name="account" class="create_syllabus_input" value="${userLogin.username }">
							</li>
							<li>
								<span class="create_syllabus_item_name">密码</span>
								<input type="text" name="password" class="create_syllabus_input" value="">
							</li>

						 </c:otherwise>


							</c:choose>


					</ul>
					 </form>
				</div>
			</div>
			<div class="btns-area">
			<c:choose>
				<c:when test="${userLogin.id>0 }">
					<a href="####" id="record" class="close">修改记录</a>
					<a href="####" id="update">保存</a>
				</c:when>
				<c:otherwise>
					<a href="####" id="save">保存</a>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
    </div>
</div>
<div class="setRecordBox closebox" >
    <h3 class="create_syllabus_title">修改记录</h3>
    <div class="setRecordBox_list">
        <ul id="testDiv">
            <c:forEach  items="${userLogList }" var="logList">
            <li>
                <span class="setRecordBox_name">${logList.userId }</span>
                <span class="setRecordBox_time"><fmt:formatDate value="${logList.createTime }" type="date" pattern="yyyy年MM月dd日 HH:mm:ss " /></span>
                <span class="setRecordBox_time">${logList.logMemo }</span>
            </li>
            </c:forEach>
            
        </ul>
    </div>
    <div class="closesetRecordBox"><span id="close" class="close">确定</span></div>
</div>
<script>
    $(function(){
    	 $('#testDiv').slimScroll({
    	        alwaysVisible: true
    	    });
        
    	
    	var areaDetais = null;
        $("#sysCustId").bind("change",function(){ 
        	$.ajax({
    			type :"get",
    			url:"/branch/areadetail.action",
    			data:{areaId:$(this).val()},
    	 		dataType : "text",
    	 		cache : false,
    	 		success : function(res) {
    	 			var resData = eval('(' + res + ')');
    	 			//console.log(resData);
    	 			areaDetais = resData.data;
    	 			var htmlStr="<option value='' selected='selected'>请选择</option>";
    	 			if(resData.status==1){
    	 				for(var key in resData.data){  
    	 				   htmlStr+="<option value='"+resData.data[key].seqid+"'>"+resData.data[key].areaName+"</option>"
    	 				}  
    	 				$('#sysCustId02').html(htmlStr);
    	 				addListner();
    	 				console.log(resData);
    	 			}else{
    	 				alert("获取失败！");
    	 			}
    			},
    			error:function(res){
    				
    				alert("网络加载失败，请稍后再试！");
    			}
    		});
        	
          }); 
        

        var addListner = function(){
        	
//         	$("#sysCustId02").bind("change",function(){ 
//         		console.log(areaDetais);
//         		console.log($(this).val());
//         		for(var key in areaDetais){ 
//         			if(areaDetais[key].seqid==$(this).val()){
//         				console.log(areaDetais[key].latitude);
//         				$('#lat').val(areaDetais[key].latitude);
//         				$('#lon').val(areaDetais[key].longitude);
//         			}
// 	 			}  
        		
//         	})
        	
        }

        
        
        $('.create_syllabus_fs').click(function(){
        	//console.log($('#save').serialize());
        	var items = $('#save').serializeArray();
        	for(var key in items){ 
        		if(items[key].value==''&&key>1){
        			alert('请认真填写参数！');
        			return false;
        		}
        	}
        	
        	var urlStr = $(this).attr('id')=='save'?'/branch/save.action':'/branch/updateCust.action'
        	//console.log(urlStr);
        			
        	$.ajax({
    			type :"get",
    			url:urlStr,
    			data:$('#save').serialize(),
    	 		dataType : "text",
    	 		cache : false,
    	 		success : function(res) {
    	 		console.log(res);
    	 		var r = eval('('+res+')');
    	 			if(r.status==1){
    	 				alert("保存成功！");
    	 			}else{
    	 				alert("保存失败！");
    	 			}
    	 			window.location.href="/branch/list.action";
    				
    			},
    			error:function(res){
    				
    				alert("网络加载失败，请稍后再试！");
    			}
    		});
        	
        });
        
        
        
        $('#record').click(function(){
        	$('.closebox').show();
        });
        
        $('#close').click(function(){
        	$('.closebox').hide();
        });
    })
</script>
</body>
</html>