<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
    <script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
     <script src="/static/third/fine-uploader/fine-uploader.js"></script>
	    <script type="text/template" id="qq-template-manual-trigger">
        <%@ include file="/include/template-uploader-single.jsp" %>
    	</script>
    	<%@include file="/include/editor.jsp" %>
</head>
<style>
#form-content {
				max-height: 250px;
				height: 250px;
				background-color: white;
				border-collapse: separate; 
				border: 1px solid rgb(204, 204, 204); 
				padding: 4px; 
				box-sizing: content-box; 
				-webkit-box-shadow: rgba(0, 0, 0, 0.0745098) 0px 1px 1px 0px inset; 
				box-shadow: rgba(0, 0, 0, 0.0745098) 0px 1px 1px 0px inset;
				border-top-right-radius: 3px; border-bottom-right-radius: 3px;
				border-bottom-left-radius: 3px; border-top-left-radius: 3px;
				overflow: scroll;
				outline: none;
			}
.outGet{
	display: inline-block;
    width: 120px;
    background: #f8b500;
    height: 32px;
    line-height: 32px;
    border-radius: 5px;
    text-align: center;
    color: #fff;
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
				<jsp:param name="nvaIndex" value="28" />
			    <jsp:param name="nvaParentIndex" value="23" />
			</jsp:include> 
			<!--左侧导航：结束-->

			   <div class="rightContent">
            <h3 class="i_title">会员卡列表</h3>
            <div class="province fw700">通用</div>
            <div class="cardList">
             <c:forEach items="${beanList}" var="bean">
                <dl>
                    <a href="/membercard/detail.action?id=${bean.id}"  target="_blank">
                    <dt><img src="${bean.signImg}" alt=""/></dt>
                    <dd>
                        <span class="card-user-date">时间：${bean.days} 天</span>
                        <span class="card_st_01">
                        <c:if test="${bean.status eq 0}">售卖中</c:if>
                        <c:if test="${bean.status eq 1}">已下架</c:if>
                        <c:if test="${bean.status eq 2}">已删除</c:if>
                        </span>
                    </dd>
                    </a>
                </dl>
              </c:forEach>
            </div>
            <c:forEach var="custInfoCard" items="${custInfoCard}">
            <c:if test="${fn:length(custInfoCard.value)>0}">
            <div class="province fw700">${custInfoCard.key.custName}</div>
            <div class="cardList">
             <c:forEach var="card" items="${custInfoCard.value}">
                <dl>
                    <a <shiro:hasPermission name="membercard:detail">  href="/membercard/detail.action?id=${card.id}"  </shiro:hasPermission> target="_blank">
                        <dt><img src="${card.signImg}" alt=""/></dt>
                        <dd>
                            <span class="card-user-date">时间：${card.days}天</span>
                            <span class="card_st_01">
                              <c:if test="${card.status eq 0}">售卖中</c:if>
                             <c:if test="${card.status eq 1}">已下架</c:if>
                              <c:if test="${card.status eq 2}">已删除</c:if>
                            </span>
                        </dd>
                    </a>
                    
                </dl>
                </c:forEach>
            </div>
            </c:if>
            </c:forEach>
            
            <div class="clear_float"></div>
                 	   <!-- 分页 开始 -->
				<div class="pager">
					<c:set var="pageUrl"
						value="/membercard/list.action?pageNo=pageno"
						scope="request" />
					<jsp:include flush="true" page="/include/pager.jsp" />
				</div>
			<!-- 分页 结束 -->
			<div class="btns-area">
				<shiro:hasPermission name="membercard:unline">
					<a href="javascript:void(0)" class="outline_card">线下购买</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="membercard:add">
					<a href="javascript:void(0)" class="create_card">新建会员卡</a>
				</shiro:hasPermission>
			</div>
        </div>
    </div>
</div>
<div class="create_card_cont closebox" style="display: none">
    <h3 class="create_syllabus_title">新建会员卡</h3>
    <div id="testDiv">
        <div class="create_syllabus_cont">
            <ul>
                <li>
                    <span class="create_syllabus_item_name">卡名称</span>
                    <input id="cardName" name="cardName" type="text" class="create_syllabus_input wid320" value="">
                </li>
                <li>
                    <span class="create_syllabus_item_name">时间</span>
                    <input type="text" id="days"  name="days" class="create_syllabus_input wid120" style="width:100px;" value=""> 天
                    <em class="create_syllabus_input_p">价格</em>
                    <input id="salePrice" name="salePrice" type="text" class="create_syllabus_input wid120"  style="width:100px;" value=""> 元
                </li>
                <li>
                    <span class="create_syllabus_item_name">购买规则</span>
                    <div class="chooseshop">
                        <span id="saleRule" class="chooseText">不限次</span>
                        <i></i>
                        <div class="choose_gev_shop">
                            <p>不限次</p>
                            <p>1次</p>
                            <p>2次</p>
                        </div>
                    </div>
                </li>
                <li style="height:100%; overflow:hidden">
                    <span class="create_syllabus_item_name">图片</span>
			    		 <div id="view"></div>
			    		<div id="fine-uploader-manual-trigger" style="width:700px;"></div>
			    		<input id="openImg" type="hidden" value=""/>
                </li>
                <li>
                    <span class="create_syllabus_item_name">适用店铺</span>
                     <div class="chooseshop" >
                        <span class="chooseText" id="branchId" data-id="0">通用</span>
                        <i></i>
                        <div class="choose_gev_shop">
                            <p id="0">通用</p>
                            <c:forEach var="custInfo" items="${custInfoList}">
                            <p id="${custInfo.systemId}">${custInfo.custName}</p>
                            </c:forEach>
                        </div>
                    </div>
                </li>
                <li>
                    <span class="create_syllabus_item_name">状态</span>
                    <div class="create_card_state">
                        <div id="shoping" class="card_sta on">售卖中</div>
                        <div id="xiajia" class="card_sta">已下架</div>
                    </div>
                </li>
                <li> 
                     <span class="create_syllabus_item_name">排序值</span>
                    <input type="number" id="sort"  name="days" class="create_syllabus_input wid120" style="width:100px;" value=""  onChange="return check()">
                </li>
                <li>
                    <span class="create_syllabus_item_name">支持退款</span>
                    <div class="tuikuan">
                        <em><i id="trel" rel="1"></i>是</em>
                        <em><i id="frel" rel="2" class="on"></i>否</em>
                        <em class="poundage" style="display:none;position: absolute; right: 0; top:8px;">
                            退款手续费<input type="text" id="fee" name="fee" class="refund">%
                        </em>
                    </div>
                </li>
                <li class="j_sijiao" style="display: none">
                    <span  class="create_syllabus_item_name">退款规则</span>
                <div class="choosemerber">
                        <span id="refundRule" class="chooseText">扣除已消费月份</span>
                        <i></i>
                        <div class="choosemerber_shop">
                            <p>扣除已消费月份</p>
                            <p>全额</p>
                        </div>
                    </div>
                
                </li>
                <li>
                    <span class="create_syllabus_item_name">月度费用</span>
                    <input type="text" id="refundPrice" name="refundPrice" class="create_syllabus_input wid120" value="" style="border: 1px solid rgb(228, 228, 228);">
                </li>
                <li>
                    <span class="create_syllabus_item_name">会员卡介绍</span>
                  <!--   <textarea id="elm1" name="elm1" class="xheditor" rows="12" cols="80" style="width:320px; height:40px; padding: 6px;;"></textarea>
 -->                <textarea id="form-content"
						class="editor" cols="30" rows="10"  style="width:442px; height:40px;"></textarea>
                </li>
            </ul>
        </div>
    </div>
    <div class="create_card_btn">
        <em class="create_syllabus_qx close">取消</em>
        <em id="submit" class="create_syllabus_fs">确定</em>
    </div>
</div>
<div class="outline_buyCard closebox" style="display: none;">
	<div class="outline-top">
		<span class="on">新会员购卡</span>
		<span>老会员购卡</span>
		<span>实体卡</span>
	</div>
	<div class="outline-cont">
		<div class="outline-warp create_syllabus_cont" style="display: ;">
			<ul >
				<li>
                	<span class="create_syllabus_item_name">手机号</span>
                	<input type="text" id="usemobile" class="create_syllabus_input" value="" style="width: 320px;">
            	</li>
            	<li>
                	<span class="create_syllabus_item_name">验证码</span>
                	<input type="text"  id="verCode" class="create_syllabus_input" value="" style="width:180px;">
                	<input type="button" id="yzbtn"  class="outGet" value="免费获取验证码" />
            	</li>
            	<li>
                	<span class="create_syllabus_item_name">姓名</span>
                	<input type="text" id="memberName"  class="create_syllabus_input" value="" style="width:180px;">
            	</li>
            	<li style="line-height: 35px;">
                	<span class="create_syllabus_item_name">会员卡类型</span>
	                	<select name="" id="cardType" class="joinbusiness" style="margin-right: 15px;">
		                	<c:forEach  items="${cardList }"  var="cardlist">
			                		<option value="${cardlist.id }">${cardlist.cardName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${cardlist.salePrice/100}元</option>
		                	</c:forEach>
	                	</select> 
            	</li>
            	<li style="line-height: 35px;">
                	<span class="create_syllabus_item_name">付款方式</span>
                	<select name="" id="payType" class="joinbusiness" style="margin-right: 15px;">
                        <option value="1">刷卡</option>
                        <option value="2">现金</option>
                        <option value="3">支付宝</option>
                    </select>
            	</li>
            	
			</ul>
			<div class="" style="padding-top: 20px;">
         		<em class="create_syllabus_qx close">取消</em>
         		<em id="publish" class="create_syllabus_fs">下单</em>
    		</div>
		</div>
		<div class="outline-warp create_syllabus_cont" style="display: none;">
			<ul >
            	<li>
                    <span class="create_syllabus_item_name">手机号</span>
                    <input type="text" id="usemobile1" class="create_syllabus_input" value="${memberinfo.linkPhone }" style="width:180px;" placeholder="请输入会员手机号">
                    <a href="javascript:;" class="outGetYz">搜索</a>
                </li>
                <li style="height: 60px;padding-top: 15px;padding-bottom: 15px; line-height:60px; display: block;">
                    <img src="" alt="" id="memberLogo" data-id="" class="ajax-user">
                    <span id="membername" style="margin-left: 15px;"></span>
                </li>
            	<li style="line-height: 35px;">
                	<span class="create_syllabus_item_name">会员卡类型</span>
                	<select name="" id="cardType2" class="joinbusiness" style="margin-right: 15px;">
		                	<c:forEach  items="${cardList }"  var="cardlist">
			                		<option value="${cardlist.id }">${cardlist.cardName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${cardlist.salePrice/100}元</option>
		                	</c:forEach>
	                	</select> 
            	</li>
            	<li class="errTips" style="display:none;">
                <div class="errTipsText"></div>
            	</li>
            	<li style="line-height: 35px;">
                	<span class="create_syllabus_item_name">付款方式</span>
                	<select name="" id="payType2" class="joinbusiness" style="margin-right: 15px;">
                        <option value="1">刷卡</option>
                        <option value="2">现金</option>
                        <option value="3">支付宝</option>
                    </select> 
            	</li>
            	
			</ul>
			<div class="" style="padding-top: 20px;">
         		<em class="create_syllabus_qx close">取消</em>
         		<em id="publish2" class="create_syllabus_fs">下单</em>
    		</div>
		</div>
		<div class="outline-warp create_syllabus_cont" style="display: none;">
			<ul >

            	<li style="line-height: 35px;">
                	<span class="create_syllabus_item_name">会员卡类型</span>
                	<select name="" id="cardType3" class="joinbusiness" style="margin-right: 15px;">
		                	<c:forEach  items="${cardList }"  var="cardlist">
			                		<option value="${cardlist.id }">${cardlist.cardName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${cardlist.salePrice/100}元</option>
		                	</c:forEach>
	                	</select> 
            	</li>
            	<li style="line-height: 35px;">
                	<span class="create_syllabus_item_name">付款方式</span>
                	<select name="" id="payType3" class="joinbusiness" style="margin-right: 15px;">
                        <option value="1">刷卡</option>
                        <option value="2">现金</option>
                        <option value="3">支付宝</option>
                    </select>
            	</li>
            	<li>
                	<span class="create_syllabus_item_name">实际收款</span>
                	<input type="text" id="actualMoney"  class="create_syllabus_input" value="" style="width: 320px;">
            	</li>
			</ul>
			<div class="" style="padding-top: 20px;">
				<em class="create_syllabus_qx close">取消</em>
         		<em id="publish3" class="create_syllabus_fs">下单</em>
			</div>
		</div>
	</div>
</div>
<script>
var wait=60;
function time(o) {
    if (wait == 0) {
        o.removeAttribute("disabled");
        o.style="border:1px solid #f8b500;color:#f8b500";
        o.value="重新发送";
        wait = 60;
    } else {
        o.setAttribute("disabled", true);
        o.style="border:1px solid #e4e4e4;color:#999";
        o.value= wait + "S";
        wait--;
        setTimeout(function() {
                    time(o)
                },
                1000);
    }
}
document.getElementById("yzbtn").onclick=function(){
	
	
	var theOne = this;
	$(theOne).attr("disabled", true);
	var mobile=$('#usemobile').val();
	if(mobile == ''){
		alert('手机号码错误');
		return false;
	}
	
	if(!/^1[3|4|5|7|8][0-9]{9}$/.test(mobile)){
		alert('手机号码错误');
		return false;
	}
	$('.errTips').attr("style",'display:none;');
		$.ajax({
		 type:"post",
		 data:{"mobile":mobile},
		 url:"/membercard/line/vercode.action",
		 dataType:"json",
		 cache:false,
		 beforeSend:function(xhr){
				//$("#loading").show();
		 },
		 success:function(res) {
		 
				if(res.status == 0){
					time(theOne);
				}else if(res.status == 2){
					alert('此用户已注册！');
					window.location.reload();
				}else{
					alert('网络异常，请稍后再试！');
					window.location.reload();
				}
		},
		 error:function(resJson){
			 alert("系统异常，请稍后再试！");
		 }
	 });
	}



	$(function(){
		var $me  = $(this) , tab = $(".outline-top span");
		$(".outline-top span").click(function(){
			var _index = $(this).index();
			//alert(_index)
			$(".outline-cont .outline-warp").eq(_index).show().siblings().hide();
			$(this).addClass("on").siblings().removeClass("on")
		})
		  $(".outGetYz").click(function(){
			  var mobile=$("#usemobile1").val();
			  if(mobile==''){
				  alert('请认真填写手机号！');
				  return false;
			  }
			  $.ajax({
					type:'post',
				    data:{"mobile":mobile},
				    url:"/membercard/line/checkuser.action",
				    dataType:"json",
				    cache:false,
				    success:function(res) {
				    	if(res.status==1){
				    		 var logo =	res.data.memberLogo;
							    
							    $('.ajax-user').attr('src',logo);
							    $('.ajax-user').attr('data-id',res.data.memberId);
							    $('#membername').text(res.data.memberNick);
				    	}else if(res.status==0){
				    		alert("此用户未注册！");
				    	}else{
				    		alert("系统异常，");
				    		window.location.reload();
				    	}
				   
				    
				    //	console.log(res);

				 		},
					 error:function(resJson){
						 alert("系统异常，请稍后再试！");
					 }
				})
			  
			  
			  
			  
// 			   var mobile=$("#usemobile1").val();
// 			   window.location.href="/membercard/list.action?mobile="+mobile;
		   });
		
		 $("#publish").click(function(){
			   var  mobile=$("#usemobile").val();
			   var  verCode=$("#verCode").val();
			   var  name=$("#memberName").val();
			   var  cardType=$("#cardType").find("option:selected").val();
			   var  payType=$("#payType").find("option:selected").val();
			   var  createType=1;
			   if(mobile==null){
			    	if(!/^1[3|4|5|7|8][0-9]{9}$/.test(mobile)){
			    		$('.errTips').attr("style",'');
						$('.errTips').text('');
						$('.errTips').text('手机号码错误');
			    	return false;
			    	}
					if(code == ''){
						$('.errTips').attr("style",'');
						$('.errTips').text('');
						$('.errTips').text('请填写验证码');
			    		return false;
			    	}
				}
				if(mobile == ''){
					$('.errTips').attr("style",'');
					$('.errTips').text('');
					$('.errTips').text('请填写手机号码');
		    		return false;
		    	}
				if(name == ''){
					$('.errTips').attr("style",'');
					$('.errTips').text('');
					$('.errTips').text('请填写姓名');
		    		return false;
		    	}
				$.ajax({
					type:'post',
				    data:{"mobile":mobile,"verCode":verCode,"name":name,"cardType":cardType,"payType":payType,"createType":createType},
				    url:"/membercard/line/create.action",
				    dataType:"json",
				    cache:false,
				    success:function(res) {
				    	 if(res === undefined){
				 				alert("操作失败请稍后再试");
				 			}else{
				 				if(res.status == 0){
				 					alert("操作成功！");
				 					//top.location.href="/account/business/report.action";
				 					window.location.reload();
				 				}else{
				 					alert(res.msg);
				 				}
				 			}
				 		},
					 error:function(resJson){
						 alert("系统异常，请稍后再试！");
					 }
				})
		   })
		 $("#publish2").click(function(){
			   var  cardType=$("#cardType2").find("option:selected").val();
			   var  mobile=$("#usemobile1").val();
			   var  payType=$("#payType2").find("option:selected").val();
			   var  memberId=$("#memberLogo").attr("data-id");
			   var  createType=2;
				if($.trim(mobile).length == 0){
					alert("请输入手机号！");
					return;
				}
				$.ajax({
					type:'post',
				    data:{"cardType":cardType,"mobile":mobile,"payType":payType,"memberId":memberId,"createType":createType,"name":$("#membername").text()},
				    url:"/membercard/line/create.action",
				    dataType:"json",
				    cache:false,
				    success:function(res) {
				    	
				    	 if(res === undefined){
				 				alert("操作失败请稍后再试");
				 			}else{
				 				if(res.status == 0){
				 					alert("操作成功！");
				 					window.location.reload();
				 					//top.location.href="/account/business/report.action";
				 				}else{
				 					alert(res.msg);
				 				}
				 			}
				 		},
					 error:function(resJson){
						 alert("系统异常，请稍后再试！");
					 }
				})
		   })
		 $("#publish3").click(function(){
			   var  cardType=$("#cardType3").find("option:selected").val();
			   var  payType=$("#payType3").find("option:selected").val();
			   var  dealmoney=$("#actualMoney").val();
			   var  createType=3;
				if($.trim(actualMoney).length == 0){
					alert("请输入收款金额！");
					return;
				}
				$.ajax({
					type:'post',
				    data:{"cardType":cardType,"dealmoney":dealmoney,"payType":payType,"createType":createType},
				    url:"/membercard/line/create.action",
				    dataType:"json",
				    cache:false,
				    success:function(res) {
				    	 if(res === undefined){
				 				alert("操作失败请稍后再试");
				 			}else{
				 				if(res.status == 0){
				 					alert("操作成功！");
				 					//top.location.href="/account/business/report.action";
				 					window.location.reload();
				 				}else{
				 					alert(res.msg);
				 				}
				 			}
				 		},
					 error:function(resJson){
						 alert("系统异常，请稍后再试！");
					 }
				})
		   })
	})
</script>
<script>
function  check(){
	var temp=/^\d+(\.\d+)?$/;
	 var sort = $("#sort").val();
	if(temp.test(sort)==false)
	 alert("输入错误");
	
}
    // 自定义竖滚动条
    $(function(){
    	
        $('#form-content').trumbowyg({
            lang: 'fr',
            closable: true,
            mobile: true,
            fixedBtnPane: true,
            fixedFullWidth: true,
            semantic: true,
            resetCss: true,
            autoAjustHeight: true,
            autogrow: true
        });
    	
        $('#testDiv').slimScroll({
            alwaysVisible: true
        });
        $(".outline_card").click(function(){
        	$(".outline_buyCard").show();
        	showShadow();
        })
        // 新建会员卡弹框
        $(".create_card").click(function(){
            showShadow();
            $(".create_card_cont").show();
        });
        
        //  团课退款流程弹出框
        $(".j_tuikuan").click(function(){
            $(".refund-apply").show();
            showShadow();
        });
        
        $(".chooseshop").click(function(){
            $(this).find(".choose_gev_shop").toggle();
        });
        $(".choosemerber").click(function(){
            $(".choosemerber_shop").toggle();
        });
        
        //  新建收费团课是否支持退款
        $(".tuikuan i").click(function(){
            var rel=$(this).attr('rel');
            $(this).addClass("on").parent().siblings().find("i").removeClass("on");
            //alert(rel);
            if(rel == 1){
                $(".poundage").show();
                $(".j_sijiao").show();
            }
            else{
                $(".poundage").hide();
                $(".j_sijiao").hide();
            }
        });
        
     // 取消关闭弹框
        $(".close").click(function(){
            $(this).parents(".closebox").hide();
            $("#shade").remove();
        });
        
        $("#xiajia").click(function(){
            var xiajia=$("#xiajia").attr('class');
            if(xiajia == 'card_sta on'){
          	  $("#shoping").attr('class','card_sta');
            }else{
          	  $("#shoping").attr('class','card_sta');
          	  $("#xiajia").attr('class','card_sta on');
            }
            
          });
          
          $("#shoping").click(function(){
          	var shoping=$("#shoping").attr('class');
              if(shoping == 'card_sta on'){
            	  $("#xiajia").attr('class','card_sta');
              }else{
                $("#xiajia").attr('class','card_sta');
            	  $("#shoping").attr('class','card_sta on');
              } 
     	    });
         
        $(".choosemerber_shop p").click(function(){
   	        var text = $(this).text();
   	        $(this).parents(".choosemerber").find(".chooseText").text(text);
   	    }); 
        $(".choose_gev_shop p").click(function(){
   	        var text = $(this).text();
   	        var id = $(this).attr("id");
   	        $(this).parents(".chooseshop").find(".chooseText").text(text);
   	        $(this).parents(".chooseshop").find(".chooseText").attr("data-id",id);
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
    		
    		
   	 function submit(){
   		
   	      var openImg = $("#openImg").val();
	   	  if(openImg == ''){
	   		  alert("请选择图片");
	   		  return;
	   	  }
  	   	  var cardName = $("#cardName").val();
	   	  if(cardName == ''){
	   		  alert("请填写卡名称");
	   		  return;
	   	  }
	   	  var days = $("#days").val();
	     var type = /^[1-9]*[1-9][0-9]*$/;
        if (!type.test(days)) {
            alert("请输入正确的时间!");
            return;
        }
	      var salePrice = $("#salePrice").val();
	      if(!/^[0-9]+(.[0-9]{1,2})?$/.test(salePrice)){
	    	  alert("请输入正确的价格!");
	            return;
			}
	   	  var saleRule = $("#saleRule").text();
	      if(saleRule == '不限次'){
	       		saleRule='0';
	       	 }else if(saleRule == '1次'){
	       			saleRule='1';
	       	 }else{
	       			saleRule='2';
	       	 }
	      var branchId = $("#branchId").attr("data-id");
	      var status='0';
	      var xiajia=$("#xiajia").attr('class');
          if(xiajia == 'card_sta on'){
        	  status='1';
          }
	      
	      var frel=$("#frel").attr('class');
	      var isRefund=1;
	      var fee=0;
	      if (frel == "on") { 
	    	}else{ 
       	     fee = $("#fee").val();
       	     if(fee == ''){
       	    	alert("请填写退款手续费!");
	            return; 
       	     }
       	     if( fee != ''){
		       	  if(!/^([1-9][\d]{0,8}|0)(\.[\d]{1,2})?$/.test(fee) || fee >= 100 || fee == 0){
			    	  alert("请输入正确的退款手续费!");
			            return;
					}
       	        }
	       	 isRefund=0;
	       	  var refundRule = $("#refundRule").text();
	       	  if(refundRule == '扣除已消费月份'){
	       		refundRule='1';
	       	  }else{
	       		refundRule='0';
	       	  }
	       	  
	    }	  
	       	  var refundPrice = $("#refundPrice").val();
	       	if(!/^([1-9][\d]{0,8}|0)(\.[\d]{1,2})?$/.test(refundPrice)){
		    	  alert("请输入正确的月度费用!");
		            return;
				}
	       	  
	          var proDesc = $("#form-content").val();
	          if(proDesc == ''){
	        	  alert("请填写会员卡介绍");
	        	  return;
	          }
	       	  var  sort=$("#sort").val();
	       	if(sort == ''){
	        	  alert("请填写会员卡排序值");
	        	  return;
	          }
	       
	       		 $.ajax({
						 type:"post",
						 data:{"branchId":branchId,"openImg":openImg,"cardName":cardName,"days":days,"salePrice":salePrice,"saleRule":saleRule,"fee":fee,"isRefund":isRefund,"refundRule":refundRule,"refundPrice":refundPrice,"proDesc":proDesc,"status":status,"sort":sort},
						 url:"/membercard/add.action",
						 dataType:"json",
						 cache:false,
						 beforeSend:function(xhr){
								//$("#loading").show();
						 },
						 success:function(res) {
							if(res.result == "1"){
								alert("保存成功！");
								location.href="/membercard/list.action";
							}else{
								alert("保存失败！");
							}
						 },
						 error:function(resJson){
							 alert("系统异常，请稍后再试！");
						 }
					 });
		
         }
   	 
        
   	 
   //上传图片
     var manualUploader = new qq.FineUploader({
         element: document.getElementById('fine-uploader-manual-trigger'),
         template: 'qq-template-manual-trigger',
         multiple: false,
         request: {
             /* endpoint: '/upload.action' */
             endpoint: '/uploads',
       	params: {
           	param1: '测试参数'
           }
         },
         deleteFile: {
             enabled: true,
             forceConfirm: true,
             method: "POST",
             endpoint: "/uploads",
             params: {}
         },
         retry: {
            enableAuto: true
         },
         thumbnails: {
             placeholders: {
                 waitingPath: '/static/third/fine-uploader/placeholders/waiting-generic.png',
                 notAvailablePath: '/static/third/fine-uploader/placeholders/not_available-generic.png'
             }
         },
         validation: {
         	allowedExtensions: ['jpeg','jpg','gif','png'],
         	sizeLimit: 2097152 //2M 1024*1024*2
         },
         autoUpload: true,
         debug: true,
         messages: {
             typeError: "{file} has an invalid extension. Valid extension(s): {extensions}.",
             sizeError: "{file} is too large, maximum file size is {sizeLimit}.",
             minSizeError: "{file} is too small, minimum file size is {minSizeLimit}.",
             emptyError: "{file} is empty, please select files again without it.",
             noFilesError: "No files to upload.",
             tooManyItemsError: "Too many items ({netItems}) would be uploaded.  Item limit is {itemLimit}.",
             maxHeightImageError: "Image is too tall.",
             maxWidthImageError: "Image is too wide.",
             minHeightImageError: "Image is not tall enough.",
             minWidthImageError: "Image is not wide enough.",
             retryFailTooManyItems: "Retry failed - you have reached your file limit.",
             onLeave: "The files are being uploaded, if you leave now the upload will be canceled.",
             unsupportedBrowserIos8Safari: "Unrecoverable error - this browser does not permit file uploading of any kind due to serious bugs in iOS8 Safari.  Please use iOS8 Chrome until Apple fixes these issues."
         },
         callbacks: {
             onUpload: function (id, name) {
             	console.log("onUpload-----id:"+id+"----name:"+name);
             },
             onSubmitted: function (id, name) {
             	console.log("onSubmitted-----id:"+id+"----name:"+name);
             },
             onComplete: function(id, name, responseJSON, maybeXhr) {
             	console.log("onComplete-----id:"+id+"----name:"+name);
             	console.log("onComplete-----url:"+responseJSON.url);
             	//$("#view").append(responseJSON.url+"<br/>");
             	$('#imgurl').val(responseJSON.url);
             	$('#openImg').val(responseJSON.url);
             	
             },
             onAllComplete: function(successful, failed) {
             	console.log("onAllComplete-----successful:"+successful+"----failed:"+failed);
             	//manualUploader.reset();
             }
         }
     });

     qq(document.getElementById("trigger-upload")).attach("click", function() {
         manualUploader.uploadStoredFiles();
     });
    });
</script>

</body>
</html>