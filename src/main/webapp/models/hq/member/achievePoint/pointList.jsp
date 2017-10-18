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
<title>积分成就列表</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>
<script type="text/javascript" src="/static/js/file.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
<script src="/static/third/fine-uploader/fine-uploader.js"></script>
<script type="text/template" id="qq-template-manual-trigger">
   <%@ include file="/include/template-uploader-single.jsp" %>
</script> 
</head>
<style>
	.b-table-con td{
		border-left:1px solid #e5e5e5;
		border-bottom:1px solid #e5e5e5;
		padding:8px;
	}
	.b-table-con{
		margin:25px auto; 
		border:1px solid #e5e5e5;
		border-left:none;
		border-bottom:none;
	}
	.create_syllabus_box{
		height:488px;
		margin-top:-244px;
	}
	.create_syllabus_cont {
		    padding: 0 15px;
		}
	.updateRo{
		background:#fff;
		width:460px;
		position:absolute;
		left:50%;
		top:50%;
		z-index:9999;
		margin-left:-230px;
		height:290px;
		margin-top:-145px;
	}
	.create_syllabus_cont ul li {
	    position: relative;
	    padding-left: 121px;
	    min-height: 32px;
	    margin: 8px 0;
	    padding-top: 3px;
	}
	.create_syllabus_item_name {
	    font-size: 12px;
	    display: block;
	    width: 108px;
	    height: 32px;
	    line-height: 32px;
	    position: absolute;
	    left: 0;
	    top: 4px;
	    color: #333;
	}
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
				<jsp:param name="nvaIndex" value="209" />
				<jsp:param name="nvaParentIndex" value="24" />
			</jsp:include>
			<!--左侧导航：结束-->
			<div class="rightContent">
	          
	            <div class="i_title">积分设置</div>
	            <div class="syllabus_branch_select search_area">
		            <span><a href="/achieve/list.action" style="color:#fff;">成就设置</a></span>
		        	<span class="on"><a href="#" style="color:#fff;">积分设置</a></span>
	            </div>
	            <div class="coach_list">
     		   		<table border='0' cellspacing="0" cellpadding="0" width="100%"  class="b-table-con">
						<tr >
							<th style="text-align: center" bgcolor="#f2f2f2" height="38">序号</th>
							<th style="text-align: center" bgcolor="#f2f2f2" height="38">事件名称</th>
							<th style="text-align: center" bgcolor="#f2f2f2" height="38">获得积分</th>
							<th style="text-align: center" bgcolor="#f2f2f2" height="38">操作</th>
						</tr>
						<c:forEach  var="point"  items="${point }">
							<input type="hidden" id="achieveId"  value="${point.id }" >
							<tr>
								<td height="30" align="center">${point.sort }</td>
								<td height="30" align="center">${point.action} </td>
								<td height="30" align="center">${point.value }</td>
								<td height="30" align="center">
									<span  id="${point.id }" class="achieve_update"  >修改</span>
									<span  id="${point.id }" class="achieve_delete"  >删除</span>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
		                <div class="btns-area" style="line-height:32px">
						 	<a href="javascript:void (0)" class="create_syllabus">添加规则</a>
						 </div>
		                </div>
				<div class="clear_float"></div>
			</div>
			<!-- 分页 开始 -->
		<div class="pager">
			<c:set var="pageUrl"
				value="point/list.action?pageNo=pageno"
				scope="request" />
			<jsp:include flush="true" page="/include/pager.jsp" />
		</div>
		<!-- 分页 结束 -->
		</div>
	<div class="outline_buyCard closebox" style="display: none;">
    <div class="outline-cont">
        <div class="outline-top"></div>
        <div class="outline-warp create_syllabus_cont" style="display: ;">
            <ul>
                <li>
                <span class="create_syllabus_item_name">序号</span>
                    <input type="number" id="sort" class="create_syllabus_input"  style="width:180px;" >
                </li>
                <li>
                	<span class="create_syllabus_item_name">选择事件</span>
                	<select name="userId" id="action">
			         <option value="" selected="selected">-请选择-</option>
                     <c:forEach var="map" items="${pointlist}">
                   <option value="${map.id }" selected="selected">${map.action}</option>
                     </c:forEach>
                </select>
                </li>
                <li class="create_syllabus_item_name">
                  <span class="create_syllabus_item_name">获得积分值</span>
                    <input type="number" id="value" class="create_syllabus_input"  style="width:180px;" >
                </li>
                
	        </div>

            </ul>
            <div class="" style="padding-top: 20px;clear: both; text-align: center">
                <em class="create_syllabus_qx close">取消</em>
                <em id="publish" class="create_syllabus_fs">保存</em>
            </div>
        </div>

    </div>
    <div class="updateRo  closebox" style="display: none;">
    <div class="outline-cont">
        <div class="outline-top"></div>
        <div class="outline-warp create_syllabus_cont" style="display: ;">
            <ul>
                <li>
                <span class="create_syllabus_item_name">序号</span>
                    <input type="number" id="sort1" class="create_syllabus_input"   style="width:180px;" >
                </li>
                <li>
                	<span class="create_syllabus_item_name">选择事件</span>
                	<select name="userId" id="action1">
			         <option value="" id="default" selected="selected">-请选择-</option>
                     <c:forEach var="map" items="${pointlist}">
                   <option value="${map.id }" selected="selected">${map.action}</option>
                     </c:forEach>
                </select>
                </li>
                <li class="create_syllabus_item_name">
                  <span class="create_syllabus_item_name">获得积分值</span>
                    <input type="number" id="value1" class="create_syllabus_input"  style="width:180px;" >
                </li>
	        </div>
            </ul>
            <div class="" style="padding-top: 20px;clear: both; text-align: center">
                <em class="create_syllabus_qx close">取消</em>
                <em id="submitlimt" class="create_syllabus_fs">保存</em>
            </div>
        </div>

    </div>
</body>
<script type="text/javascript">
$(function(){
	$(".achieve_delete").click(function(){
		var id=$(this).attr('id');
		 if(window.confirm('确定删除该积分规则么？')){
				
				$.ajax({
					type :"get",
					url:"/achieve/point/del.action",
					data:{id:$(this).attr('id')},
			 		dataType : "text",
			 		cache : false,
			 		success : function(res) {
			 		
			 			if(res=='1'){
			 				alert("删除成功！");
			 			}else{
			 				alert("删除失败！");
			 			}
			 			window.location.reload();
						
					},
					error:function(res){
						
						alert("网络加载失败，请稍后再试！");
					}
				});
				return true;
	    	}else{
	    		return false;
	    	} 
	})
	
	var ID = 0;
	$('.achieve_update').click(function(){
		$(".updateRo").show();
			showShadow();
			var id=$(this).attr('id');
			ID = id;
		$.ajax({
			type:'post',
		    data:{"id":id},
		    url:"/achieve/point/datail.action",
		    dataType:"json",
		    cache:false,
		    success:function(res) {
		    	console.log(res.data.action);
		    	if(res.status==1){
		    		var id=res.data.id;
		    		$("#default").text(res.data.action);
		    		$("#sort1").val(res.data.sort);
		    		$("#value1").val(res.data.value);
		    	}
 			},
			 error:function(resJson){
				 alert("系统异常，请稍后再试！");
			 }
		})
	})
	$("#submitlimt").click(function(){
		var sort=$("#sort1").val();
		//var id= $("#action1").find("option:selected").val();
		var value =$("#value1").val();


		$.ajax({
			type:'post',
		    data:{"id":ID,"sort":sort,"value":value},
		    url:"/achieve/point/save.action",
		    dataType:"json",
		    cache:false,
		    success:function(res) {
 				if(res.status == 0){
 					alert("操作成功！");
 					top.location.href="/achieve/point/list.action";
 				}else{
 					alert(res.msg);
 				}
 			},
			 error:function(resJson){
				 alert("系统异常，请稍后再试！");
			 }
		})
	})
	 $(".create_syllabus").click(function(){
        $(".outline_buyCard").show();
        showShadow();
    })
      $(".close").click(function(){
        $(this).parents(".closebox").hide();
        $("#shade").remove();
    });
    $("#publish").click(function(){
    	var sort=$("#sort").val();
    	if($.trim(sort).length == 0){
			alert("请输入排序值");
			return;
		}
    	var action= $("#action").find("option:selected").text();
    	if($.trim(action).length == 0){
			alert("请选择事件");
			return;
		}
    	var id= $("#action").find("option:selected").val();
   			
   		var value =$("#value").val();
   		if($.trim(value).length == 0){
			alert("请选择获得积分值");
			return;
		}
    	$.ajax({
			type:'post',
		    data:{"id":id,"sort":sort,"value":value,"action":action},
		    url:"/achieve/point/save.action",
		    dataType:"json",
		    cache:false,
		    success:function(res) {
 				if(res.status == 0){
 					alert("操作成功！");
 					top.location.href="/achieve/point/list.action";
 				}else{
 					alert(res.msg);
 				}
 			},
			 error:function(resJson){
				 alert("系统异常，请稍后再试！");
			 }
		})
  
    })
})


</script>
</html>