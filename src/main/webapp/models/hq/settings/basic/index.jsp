<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>基础信息</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<link type="text/css" rel="stylesheet" href="/static/third/easyui/themes/default/validatebox.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
	<script type="text/javascript" src="/static/third/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/static/js/jquery.validatebox.rules.js"></script> 
</head>
<body class="baseBgColor">
	<div class="head">
		<!--顶部：开始-->
		<jsp:include page="/include/header.jsp" flush="true">
			<jsp:param name="title" value="后台首页" />
		</jsp:include>
		<!--顶部：结束-->
	</div>
	<div class="branch_wrap">
		<div class="branch_wrap_cen">
			<!--左侧导航：开始-->
			<jsp:include page="/include/nav_left.jsp" flush="true">
				<jsp:param name="nvaIndex" value="34" />
				<jsp:param name="nvaParentIndex" value="26" />
			</jsp:include>
			<!--左侧导航：结束-->

			<div class="rightContent">
				<h3 class="i_title">设置</h3>
				<div class="province fw700 padl20">基础信息</div>
				<div class="basicsBox">
					<span>
						<p class="basics_infor_title">商户名称</p>
						<p class="basics_infor_cont">${custInfo.custName }</p>
					</span> <span>
						<p class="basics_infor_title">分店数量</p>
						<p class="basics_infor_cont">${custCount }</p>
					</span> <span>
						<p class="basics_infor_title">负责人</p>
						<p class="basics_infor_cont">${userLogin.realname }</p>
					</span> <span>
						<p class="basics_infor_title">联系电话</p>
						<p class="basics_infor_cont">${userLogin.mobile }</p>
					</span>
				</div>
				<%--
				<div class="terrace">
					是否进入其他平台分销： <span><i rel="1" class="on"></i>是</span> <span><i
						rel="2"></i>否</span>
				</div> --%>
				<div class="province fw700 padl20">
					当前账号 <span class="bindAlipay quit">修改密码</span>
				</div>
				<div class="basicsBox currlogin">
					<span>
						<p class="basics_infor_title">当前账号</p>
						<p class="basics_infor_cont">${userLogin.username }</p>
					</span> <span>
						<p class="basics_infor_title">业务员姓名</p>
						<p class="basics_infor_cont">${userLogin.realname }</p>
					</span>
				</div>
				<div class="clear_float"></div>
				<div class="btns-area"><a href="/logout"><div class="create_card">退出登录</div></a></div>
			</div>
		</div>
	</div>
	<%-- <div class="fliiAlipay closebox">
		<div class="fliiAlipayCont" id="${userLogin.id }">
			<ul>
				<li><span class="fliiAlipayCont-n">原密码：</span><input
					id="originalPassword" type="password" class="fliipayInout"></li>
				<li><span class="fliiAlipayCont-n">新密码：</span><input
					id="password01" type="password" class="fliipayInout"></li>
				<li><span class="fliiAlipayCont-n">重复新密码：</span><input
					id="password02" type="password" class="fliipayInout"></li>
				<li class="bindcomfig"><a href="javascript:;" class="close">确定</a></li>
			</ul>
		</div>
	</div> --%>
			<div class="fliiAlipay changepassw closebox">
		    <div class="fliiAlipayCont">
		    	<form id="f1" name="f1" action="">
		    		<input id="id" name="id" type="hidden" value="${userLogin.id}"/>
		        <ul>
		            <li><span class="fliiAlipayCont-n">原密码：</span><input id="originalPassword" name="originalPassword" type="text" class="fliipayInout easyui-validatebox" data-options="required:true,err:err,missingMessage:'请输入原密码'"></li>
		            <li><span class="fliiAlipayCont-n">密码：</span><input id="password" name="password" type="text" class="fliipayInout easyui-validatebox" data-options="required:true,validType:'minLength[6]',err:err,missingMessage:'请输入密码'" maxlength="18"></li>
		            <li><span class="fliiAlipayCont-n">重复密码：</span><input id="repassword" name="repassword" type="text" class="fliipayInout easyui-validatebox" data-options="required:true,validType:'same[\'password\']',err:err,missingMessage:'请再次输入密码'" maxlength="18"></li>
		        </ul>
	        </form>
					<div class="setauthBtn">
            <em class="create_syllabus_qx close">取消</em>
            <em class="create_syllabus_fs" id="setpassSubmit">确定</em>
					</div>
		    </div>
		</div>
</body>
<script type="text/javascript">
	// 提现支付宝绑定
	$(".bindAlipay").click(function() {
		showShadow();
		$(".fliiAlipay").show();
	});
	
 	// 取消关闭弹框
   $(".close").click(function(){
       $(this).parents(".closebox").hide();
       $("#shade").remove();
   });

 	// 修改密码提交
	$("#setpassSubmit").click(function(){
    	if($('#f1').form('enableValidation').form('validate')){
    		if($('#password').val() != $('#repassword').val()){
    			alert("重复新密码错误!");
    			return;
    		}
    		$.ajax({
	    		type:"post",
	    		url:"/settings/user/password/reset.action",
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
	    					$(".close").click();
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
</script>

</html>