<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
    <title>教练详情</title>
  <link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>  
  <script src="/static/third/fine-uploader/fine-uploader.js"></script>
<script type="text/template" id="qq-template-manual-trigger">
<%@ include file="/include/template-uploader-single.jsp" %>
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
			<jsp:param name="nvaIndex" value="19" />
			<jsp:param name="nvaParentIndex" value="18" />
		</jsp:include>
		<div class="rightContent">
			<h3 class="i_title">教练详情</h3>
			<div class="province" style="height:25px;"></div>
			<div class="item_syllabus_list_tag">
					<span class="item_syllabus_list_tag_lie"><a href="#">教练信息</a></span>
					<span><a href="/coach/coachclass.action?id=${id}&coachType=${coachType}">上课信息</a></span>
					<span><a href="/coach/coachcomment.action?id=${id}&coachType=${coachType}">评价</a></span>
					<c:choose>
					<c:when test="${coachType==1 }" >
					<span class="ss" ><a href="/coach/coachsijiao.action?id=${id}&coachType=${coachType}">私教售卖信息</a></span>
					</c:when>
					<c:otherwise></c:otherwise>
					</c:choose>
				</div>
			<div class="syllabus_detail_cont">
					<div class="create_syllabus_cont syllabus_detail_show">
						<ul>
							<li>
							<input type="hidden" value="${coachInfo.id }" id="id">
							 <input type="hidden" value="${coachInfo.useCustId }" id="useCustId">
								<span class="create_syllabus_item_name">姓名</span>
								<input id="name" type="text" class="create_syllabus_input" value="${coachInfo.name }">
							</li>
							<li>
								<span class="create_syllabus_item_name">手机号</span>
								<input id="mobile" type="text" class="create_syllabus_input" value="${coachInfo.mobile }">
							</li>
							 <li style="height:100%; overflow:hidden">
							 <span class="create_syllabus_item_name">二维码图片</span>
								 <div id="preview1">
								<img src="${coachInfo.openImg}"  style="width:90px;height:90px">
								</div>
								<%-- <div id="fine-uploader-manual-trigger1" style="height:24px;"></div>
								<input id="openImg" type="hidden" value="${coachInfo.openImg}"/> --%>
							</li>
							<li style="height:100%; overflow:hidden">
								<span class="create_syllabus_item_name">照片</span>
								<div id="preview">
								<img src="${coachInfo.signImg}"  style="width:120px;height:60px">
								</div>
								<%-- <div id="fine-uploader-manual-trigger" ></div>
								<input id="signImg" type="hidden" value="${coachInfo.signImg}"/> --%>
							</li>
						  <li>
						<span class="create_syllabus_item_name">私教</span>
						<div class="tuikuan">
							 <font><input id="coachType" type="radio" name="coachType" <c:if test="${coachInfo.coachType == 1}">checked="checked"</c:if> value="1" checked/> 是</font>
							 <font style="margin-left:10px;"><input id="coachType" type="radio" name="coachType" <c:if test="${coachInfo.coachType == 0}">checked="checked"</c:if> value="0"/> 否</font>
						  </div>
						  </li>
						   <li>
								 <span class="create_syllabus_item_name">排序值</span>
								<input type="number" id="sort"  class="create_syllabus_input wid120" style="width:100px;" value="${coachInfo.sort }"  >（数值越小排位越前）
							</li>
						<li  class="j_sijiao">
						<span class="create_syllabus_item_name">退款手续费</span>
						<input  id="fee" type="number" class="create_syllabus_out" value="${coachInfo.fee/100}" >%
						 </li>
						<li class="j_sijiao">
							<span class="create_syllabus_item_name">私教价格</span>
							<input id="salePrice" type="number" class="create_syllabus_out"  value="${coachInfo.salePrice/100 }" >元
						</li>
						<li class="j_sijiao">
							<span class="create_syllabus_item_name">服务分店</span>
							<input id="useCustId" type="hidden" value="${coachInfo.useCustId }">
							 <div class="showcoach">
							 <c:forEach  items="${split }" var="uid">
								<p id="${uid }">${funcs:getCustNameByCustId(uid)}<i class='empty'></i></p>
							 </c:forEach>
						  <%-- <select id="sysCustId" name="select"  class="choosemerber ch-bran">
							 <option value="" selected="selected" >请选择</option>
							 <c:forEach var="map" items="${custList}">
						   <option value="${map.system_id }"   >${map.cust_name}</option>
							 </c:forEach>

						  </select> --%>
							</div>
						</form>
						</li>
						  <li>
							 <span class="create_syllabus_item_name">教练介绍</span>
							 <textarea id="proDesc" name="elm1" class="xheditor" rows="12" cols="80" style="width:442px">${coachInfo.proDesc }</textarea>
							 <%@include file="/include/editor.jsp" %>
								<script>
								/** Simple customization with current options **/
								$('#proDesc').trumbowyg({
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
									//编辑器内容为:$('#form-content').html();
								</script>
						 </li>
						<li>
						   <span class="create_syllabus_item_name">&nbsp;</span>
						</li>
					  </ul>
				   </div>
				 </div>
			<div class="btns-area">
				<a  href="javascript:void(0)"  id="sysDel">从分店移除</a>
				<a href="javascript:void(0)" class="outline_card">线下购买</a>
			</div>
		</div>
    </div>
</div>
<div class="outline_buyCard closebox" style="display: none;">
    <div class="outline-cont">
        <div class="outline-top"></div>
        <div class="outline-warp create_syllabus_cont" style="display: ;">
            <ul>
                <li>
                    <span class="create_syllabus_item_name">手机号</span>
                    <input type="text" id="usemobile" class="create_syllabus_input"  style="width:180px;" placeholder="请输入会员手机号">
                    <a href="#" class="outGetYz">搜索</a>
                </li>
                <li style="height: 60px;padding-top: 15px;padding-bottom: 15px; line-height:60px; display: block;">
                    <img src="" alt="" id="memberLogo" data-id="" class="ajax-user">
                    <span id="membername" style="margin-left: 15px;"></span>
                </li>
                <li style="line-height: 35px;" class="buy_coach_i">
                     <span class="create_syllabus_item_name">教练</span>
                    <span style="margin-right:25px"> ${funcs:getCoachNameById(coachInfo.id) }</span>
                     	<i  onclick="numDec()">-</i>
				        <em id="quantity">1</em>
				        <i   onclick="numAdd()">+</i>${(coachInfo.salePrice)/100}元
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

    </div>
</div>
<style>
.create_syllabus_out {
    line-height: 32px;
    border: 1px solid #e4e4e4;
    width: 400px;
    padding-left: 10px;
}
#fine-uploader-manual-trigger1 .qq-upload-success img{
	width:90px;
	height:90px;
}
#fine-uploader-manual-trigger .qq-upload-success img{
	width:90px;
	height:45px;
}
.buy_coach_i i{
    width: 20px;
    height: 20px;
    line-height: 20px;
    border: 1px solid #e5e5e5;
    text-align: center;
    display: inline-block;
    cursor: pointer;
}
.buy_coach_i em{
    width:80px;
    height: 20px;
    line-height: 20px;
    border: 1px solid #e5e5e5;
    text-align: center;
    display: inline-block;
}
</style>
<script type="text/javascript">
$(function(){
    $('#testDiv').slimScroll({
        alwaysVisible: true
    });
    $(".outline_card").click(function(){
        $(".outline_buyCard").show();
        showShadow();
    })
   $("#publish").click(function(){
	   var  id=$("#id").val();
	   var  num= $('#quantity').text();
	   var  mobile=$("#usemobile").val();
	   var  payType=$("#payType").find("option:selected").val();
	   var memberId=$("#memberLogo").attr("data-id");
		//alert(memberId);
		if($.trim(mobile).length == 0){
			alert("请输入手机号！");
			
			return;
		}
		$.ajax({
			type:'post',
		    data:{"id":id,"num":num,"mobile":mobile,"payType":payType,"memberId":memberId},
		    url:"/coach/line/create.action",
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
   $(".outGetYz").click(function(){
	   var mobile=$("#usemobile").val();
	   if(mobile==''){
			  alert('请认真填写手机号！');
			  return false;
		  }
	   $.ajax({
			type:'post',
		    data:{"mobile":mobile},
		    url:"/coach/line/checkuser.action",
		    dataType:"json",
		    cache:false,
		    success:function(res) {
		    	if(res.status==1){
		    	 	 var logo =	res.data.memberLogo;
				    $('.ajax-user').attr('src',logo);
				    $('.ajax-user').attr('data-id',res.data.memberId);
				    $('#membername').text(res.data.memberNick); 
				    
				   	console.log(res);
		    	}else  if(res.status==0){
		    		alert('此用户未注册会员')
		    	}else{
		    		alert('网络异常，请稍后再试！');
		    	}
		    },
			 error:function(resJson){
				 alert("系统异常，请稍后再试！");
			 }
		})
   });
    
})
/*商品数量+1*/
function numAdd(){
    var num_add = parseInt($("#quantity").text())+1;
    if($("#quantity").text()==""){
        num_add = 1;
    }
    $("#quantity").text(num_add);
  
}

/*商品数量-1*/
function numDec(){
    var num_dec = parseInt($("#quantity").text())-1;
    if(num_dec<1){
        //购买数量必须大于或等于1
        alert("商品数量不能小于1");
        
    }else{
        $("#quantity").text(num_dec);
        
    }
}

</script>
<script>
var arr = new Array();
var useCustId = $("#useCustId").val();
var useCustId1=useCustId.substring(1, useCustId.length-1);
arr=useCustId1.split(",");
$(function(){
	var val = $("input[name='coachType']:checked").val();
	if(val ==0){
		$(".j_sijiao").hide();
		$('.ss').hide();
	}else{
		$(".j_sijiao").show();
		$(".ss").show();
	}
	$(".tuikuan").click(function(){
		var val2 = $("input[name='coachType']:checked").val();
		if(val2 == 0){
			$(".j_sijiao").hide();
			$('.ss').hide();
		}else{
			$(".j_sijiao").show();
			$(".ss").show();
		}
	})
$('#sysCustId').change(function(){
		//alert($('#sysCustId').val());
		//alert($('#sysCustId').find("option:selected").text())
		
		var cusIds=$('#sysCustId').val();
		
		
		var custNames=$('#sysCustId').find("option:selected").text();
		
		
    	var name=$('#userCustId').val();
		
		
		var id= $('#userCustIds').val();
		if(id.indexOf(cusIds) >= 0){
			return;
		}else{
			if(id == ''){
				name=custNames;
				$("#userCustId").val(name);
				id=cusIds;
				$('#userCustIds').val(id);
				
			}else{
				name=name+","+custNames;
				 $("#userCustId").val(name);
				id=id+","+cusIds;
				 $('#userCustIds').val(id);
			}
		}
	})
    // 自定义竖滚动条
    
        $('#testDiv').slimScroll({
            alwaysVisible: true
        });
    $("#sysDel").click(function(){
    		var id=$("#id").val();
    		var useCustId=$("#useCustId").val();
    		$.ajax({
    			type:'post',
    		    data:{"id":id,"useCustId":useCustId},
    		    url:"/coach/del.action",
    		    dataType:"json",
    		    cache:false,
    		    success:function(res) {
    		    	 if(res === undefined){
    		 				alert("操作失败请稍后再试");
    		 			}else{
    		 				if(res.status == 0){
    		 					alert("操作成功！");
    		 					top.location.href="/coach/list.action";
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
    	
});


</script>
</body>
</html>				


				