<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<title>业务员管理</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
		<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
		<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
		<link type="text/css" rel="stylesheet" href="/static/third/easyui/themes/default/validatebox.css" />
		<script type="text/javascript" src="/static/js/jquery.js"></script>
		<script type="text/javascript" src="/static/js/base.js"></script>
		<script type="text/javascript" src="/static/js/left.js"></script>
		<script type="text/javascript" src="/static/third/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="/static/js/jquery.validatebox.rules.js"></script> 
		<script type="text/javascript">
			$(function(){
				$(".salesman-toggle").click(function(){
			        $(this).parent().siblings().slideToggle(function(){
			            if($(this).is(":hidden")){
			                $(this).siblings().css("background","#fff");
			                $(this).siblings().find(".salesman-toggle").text("展开");
			            }
			            else{
			                $(this).siblings().css("background","#f5f5f5");
			                $(this).siblings().find(".salesman-toggle").text("收起");
			            }
			        });
			    });
		
				$(".j-zong").click(function(){
			        var custId = $(this).attr("data-custId");
			        $("#custId").val(custId);
			        showShadow();
			        $(".joinsalesmanBox").show();
			    });
		
			 	// 取消关闭弹框
			    $(".create_syllabus_qx").click(function(){
			        $(this).parents(".closebox").hide();
			        $("#shade").remove();
			    });
			 	//提交
			    $(".create_syllabus_fs").click(function(){
			    	if($('#f1').form('enableValidation').form('validate')){
			    		$.ajax({
				    		type:"post",
				    		url:"/settings/user/add.action",
				    		data:$("#f1").serialize(),
				    		dataType:"json",
				    		cache:false,
				    		async:false,
				    		beforeSend:function(xhr){
				    			//
				    		},
				    		success:function(res){
				    			if(res === undefined){
				    				alert("操作失败请稍后再试");
				    			}else{
				    				if(res.status == 0){
				    					alert("操作成功！");
				    					var userlogin = res.data;
				    					var lastTime = userlogin.lastTime;
				    					if(userlogin.lastTime === undefined){
				    						lastTime = '';
				    					}
				    					var newli = '<li>'
				    					+ '<span class="w186">'+userlogin.username+'</span>'
				    					+ '<span class="w119">'+userlogin.realname+'</span>'
				    					+ '<span class="w199">'+userlogin.mobile+'</span>'
				    					+ '<span class="w199">'+lastTime+'</span>'
				    					+ '<span class="w179"><a href="/settings/user/detail.action?id='+userlogin.id+'" target="_blank">详情</a></span>'
				    					+ '</li>';
		                                $("#ul_"+userlogin.custId).append(newli);
		                                
				    					//关闭窗口
		                                $(".create_syllabus_qx").click();
				    				}else{
				    					alert(res.msg);
				    				}
				    			}
				    		},
				    		error:function(res){
				    			alert("系统异常，请稍后再试！");
				    		}
				    	});
			    	}
			    	
			        
			    });
			});
		</script>
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
					<jsp:param name="nvaIndex" value="35" />
					<jsp:param name="nvaParentIndex" value="26" />
				</jsp:include>
				<!--左侧导航：结束-->
				
				<c:set var="detailPremission" value="false"></c:set>
				<shiro:hasPermission name="settings/user:detail">
					<c:set var="detailPremission" value="true"></c:set>
				</shiro:hasPermission>
				
				<div class="rightContent">
		            <h3 class="i_title">业务员管理</h3>
		            <div class="salesmanBox">
		                <div class="salesman-tag" style="background: none">${custInfo.custName}
		                    <shiro:hasPermission name="settings/user:add">
		                    	<div class="joinsalesman j-zong" data-custId="${custInfo.systemId}">添加业务员</div>
		                	</shiro:hasPermission>
		                </div>
		                <div class="member_item_table">
							<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table" id="ul_${custInfo.systemId}">
								<tr>
									<th bgcolor="#f3f5f6" height="36">账号</th>
									<th bgcolor="#f3f5f6" height="36">姓名</th>
									<th bgcolor="#f3f5f6" height="36">手机号</th>
									<th bgcolor="#f3f5f6" height="36">最后登录时间</th>
									<th bgcolor="#f3f5f6" height="36">快捷操作</th>
								</tr>
								<c:forEach items="${userList}" var="user">
									<tr>
										<td>${user.username}</td>
										<td>${user.realname}</td>
										<td>${user.mobile}</td>
										<td><fmt:formatDate value="${user.lastTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td data-innerHTML-default="无">
										<c:if test="${detailPremission}">
											<a href="/settings/user/detail.action?id=${user.id}" target="_blank">详情</a>
										</c:if>
										</td>
									</tr>
								</c:forEach>
							</table>
		                </div>
		            </div>
		            <div class="clear_float"></div>
		        </div>
		    </div>
		</div>
		<div class="joinsalesmanBox closebox">
		    <h3 class="create_syllabus_title">添加业务员</h3>
		    <div class="fliiAlipayCont">
		        <form id="f1" name="f1" action="">
			    	<input id="custId" type="hidden" name="custId" />
			        <ul>
			            <li><span class="fliiAlipayCont-n">业务员账号：</span><input name="username" type="text" class="fliipayInout easyui-validatebox" data-options="required:true,validType:'username',err:err,missingMessage:'请输入业务员账号'"></li>
			            <li><span class="fliiAlipayCont-n">密码：</span><input id="password" name="password" type="password" class="fliipayInout easyui-validatebox" data-options="required:true,validType:'minLength[6]',err:err,missingMessage:'请输入密码'" maxlength="18"></li>
			            <li><span class="fliiAlipayCont-n">重复密码：</span><input name="repassword" type="password" class="fliipayInout easyui-validatebox" data-options="required:true,validType:'same[\'password\']',err:err,missingMessage:'请再次输入密码'" maxlength="18"></li>
			            <li><span class="fliiAlipayCont-n">业务员姓名：</span><input name="realname" type="text" class="fliipayInout easyui-validatebox" data-options="required:true,err:err,missingMessage:'请再次输入业务员姓名'" maxlength="12"></li>
			            <li><span class="fliiAlipayCont-n">手机号：</span><input name="mobile" type="text" class="fliipayInout easyui-validatebox" data-options="validType:'mobile',err:err"></li>
			            <li class="joinsaleBtn">
			                <a href="javascript:;" class="create_syllabus_qx">取消</a>
			                <a href="javascript:;" class="create_syllabus_fs">确定</a>
			            </li>
			        </ul>
			    </form>
		    </div>
		</div>
	</body>
</html>