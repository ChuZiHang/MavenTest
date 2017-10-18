<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<shiro:user>
	<c:redirect url="index.jsp"></c:redirect>
</shiro:user>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>启动键后台登录</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/custom.js"></script>

</head>
<body class="l_boby">
	<div class="loginwarp">
		<img src="/static/images/bg.png" alt="" class="logoimg"/>
		<div class="loginwarp-right">
			<div class="loginbox">
				<form action="/login.action" id="loginform" method="post">
					<ul>
						<li><c:choose>
								<c:when test="${fn:length(error) > 0}">
									<div class="err-tip" id="tips">${error}</div>
								</c:when>
								<c:otherwise>
									<div class="err-tip" id="tips" style="display: none;"></div>
								</c:otherwise>
							</c:choose></li>

						<li><input type="text" name="username" class="inputstyleone"
							id="identity" placeholder="账号" /></li>
						<li><input type="password" name="password"
							class="inputstyleone" id="password" placeholder="密码" /></li>


						<li class="rember"><input type="checkbox" name="rememberMe"
							value="true">&nbsp;&nbsp;记住密码</li>
						<li><a href="javascript:void(0)" class="loginBtn"
							id="loginbutn">点击登录</a></li>

					</ul>
				</form>
			</div>
			<!-- <div class="login_logo">
				<img src="/static/images/logo.png" alt="" />
			</div> -->
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {

		var tips = document.getElementById("tips");
		//  账号验证
		function identity(identity) {
			if (identity == '') {
				tips.style.display = "block";
				tips.innerText = "请输入账号";
				return false;
			}
			return true;
		}
		// 密码验证
		function password(pw) {
			var pa = /^(\w){6,20}$/;
			if (pw == '密码' || pw == '') {
				tips.style.display = "block";
				tips.innerText = "请输入密码";
				return false;
			}
			if (!pa.test(pw)) {
				tips.style.display = "block";
				tips.innerText = "请输入正确密码";
				return false;
			}
			return true;
		}

		var clogin = document.getElementById("loginbutn");
		clogin.onclick = function() {
			var identityText = document.getElementById("identity").value;
			var passwordText = document.getElementById("password").value;
			if (!identity(identityText) || !password(passwordText)) {
				return false;
			} else {
				// window.location.href = "index.html"
				document.getElementById("loginform").submit();
			}
		}
		
	   $(document).keypress(function(e) {  
		    // 回车键事件  
		       if(e.which == 13) {  
		    	   clogin.click();
		       }  
		   }); 
	});
</script>

</html>