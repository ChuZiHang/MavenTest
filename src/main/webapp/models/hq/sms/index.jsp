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
<title>短信购买</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>
<script type="text/javascript" src="/static/js/file.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>



<style>
        .buyMess{
            background: #fff;
            margin: 0 25px;
            padding: 15px;
            height: 100%;
            overflow: hidden;
        }
        .buuMessTitle{
            padding: 15px 25px;
            font-size: 16px;
            color: #666;
            position: relative;
        }
        .buuMessTitle .buyrecord{
            position: absolute;
            right:25px;
            font-size: 12px;;
        }
        .buyMess dl{
            float: left;
            margin: 0 3px;
            border:1px solid #e5e5e5;
        }
        .buyMess dl dt img{
            display:block;
            width:200px;
        }
        .buyMess dl dd{
            height: 30px;
            padding: 12px 8px 0 8px;
        }
        .price{
            float: left;
            padding-top: 4px;
            color: #f8b500;
        }
        .buy{
            float: right;
        }
        .buy a{
            display: block;
            color: #fff;
            background: #f8b500;
            padding: 4px 30px;
            border-radius: 10px;
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
}
        
    </style>
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
				<jsp:param name="nvaIndex" value="81" />
				<jsp:param name="nvaParentIndex" value="79" />
			</jsp:include>
			<!--左侧导航：结束-->

			 <div class="rightContent">
			            <h3 class="buuMessTitle">
			                短信状态：${saasMerchantInfo.mcSmsStatus==0?"免费":"收费" }
<%-- 			                <span class="buyrecord" style="font-size: 19px;"><font color="#f8b500"  >短信状态：${saasMerchantInfo.mcSmsStatus==0?"免费":"收费" }</font></span> --%>
			                <span class="buyrecord" style="font-size: 19px;padding-right: 80px;"><font color="#f8b500"  >短信剩余数：${saasMerchantInfo.mcSmsCount }条</font></span>
			            </h3>
			            <div class="buyMess">
			                <dl id="1">
			                    <dt><img src="/static/images/500.jpg" alt=""/></dt>
			                    <dd>
			                        <span class="price">价格：40元</span>
			                        <span class="buy"><a href="javascript:;">购买</a></span>
			                    </dd>
			                </dl>
			                <dl id="2">
			                    <dt><img src="/static/images/1000.jpg" alt=""/></dt>
			                    <dd>
			                        <span class="price">价格：80元</span>
			                        <span class="buy"><a href="javascript:;">购买</a></span>
			                    </dd>
			                </dl>
			                <dl id="3">
			                    <dt><img src="/static/images/5000.jpg" alt=""/></dt>
			                    <dd>
			                        <span class="price">价格：400元</span>
			                        <span class="buy"><a href="javascript:;">购买</a></span>
			                    </dd>
			                </dl>
			                <dl id="4">
			                    <dt><img src="/static/images/10000.jpg" alt=""/></dt>
			                    <dd>
			                        <span class="price">价格：800元</span>
			                        <span class="buy"><a href="javascript:;">购买</a></span>
			                    </dd>
			                </dl>
			            </div>
			            <div class="clear_float"></div>
			        </div>
		</div>
	</div>
	
	<div class="merber_refund">
	<h4>请用支付宝扫描</h4>
		<img id="code" src="">
	</div>
</body>
<script type="text/javascript">
var ORDER_ID =0;

$('.buyMess dl').click(function(){
	
	$.ajax({cache : true,
		type : "POST",
		url : "/sms/code.action",
			data : {productId:$(this).attr('id')},
			dataType : "json",
			success : function(res) {
				console.log(res);
				if(res.status==0){
					$('.merber_refund').show();
					showShadow();
					hide();
					//var json = eval("(" + res.data + ")"); 
					$('#code').attr('src','http://www.lol.com/tool/createCode.jsp?w=280&url='+res.data.codeUrl);
					ORDER_ID = res.data.orderId;
					remainTime();  
				}else{
					alert("网络异常，请稍后再试！");
				}

			},
			error : function(res) {
				alert("网络异常，请稍后再试！");
			}
		});

});

var hide = function(){
	$('body').click(function(){
		hideShadow();
		$('.merber_refund').hide();
		window.location.reload();
	});
}

var i = 20;  
function remainTime(){  
    if(i==0){  
        location.href='/sms/orderlist.action';  
    }  
    check();
    i--;
    //console.log(i);
    setTimeout("remainTime()",5000);  
}  



function check(){
	$.ajax({cache : true,
		type : "POST",
		url : "/sms/ordertype.action",
			data : {orderId:ORDER_ID},
			dataType : "json",
			success : function(res) {
				console.log(res);
				if(res.status==0){
					 location.href='/sms/orderlist.action';  
				}

			},
			error : function(res) {
				alert("网络异常，请稍后再试！");
			}
		});
}




</script>
</html>