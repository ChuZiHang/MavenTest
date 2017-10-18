
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
    <title>教练详情</title>
  <link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<link type="text/css" rel="stylesheet" href="/static/third/easyui/themes/default/validatebox.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>  
<script type="text/javascript" src="/static/third/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/static/js/jquery.validatebox.rules.js"></script> 
<script src="/static/third/fine-uploader/fine-uploader.js"></script>
<script type="text/template" id="qq-template-manual-trigger">
<%@ include file="/include/template-uploader-single.jsp" %>
</script>
<style>
	.buttons{
		width:190px;
	}
	.qq-upload-list{
		width:100px;
	}
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
		.create_syllabus_box{
		height:488px;
		margin-top:-244px;
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
			<jsp:param name="nvaIndex" value="19" />
			<jsp:param name="nvaParentIndex" value="18" />
		</jsp:include>
		<div class="rightContent">
			<h3 class="i_title">教练详情</h3>
			<div class="province" style="height:25px;"></div>
			<div class="item_syllabus_list_tag">
					<span  class="item_syllabus_list_tag_lie"><a href="#">教练信息</a></span>
					 <span ><a href="/coach/coachclass.action?id=${id}&coachType=${coachType}">上课信息</a></span>
					<span ><a href="/coach/coachcomment.action?id=${id}&coachType=${coachType}">评价</a></span>
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
								<span class="create_syllabus_item_name">姓名</span>
								<input id="name" type="text" class="create_syllabus_input wid320"  value="${coachInfo.name }">
							</li>
							<li>
								<span class="create_syllabus_item_name">手机号</span>
								<input id="mobile" type="text" class="create_syllabus_input wid320" value="${coachInfo.mobile }">
							</li>
							<div style="height:100%; overflow:hidden">
							<li style="height:100%; overflow:hidden;float:left; width:160px;">
							 <span class="create_syllabus_item_name">二维码图片</span>
								 <div id="preview1">
								<img src="${coachInfo.openImg}"  style="width:90px;height:90px">
								</div>
								<div id="fine-uploader-manual-trigger1" style="height:24px;"></div>
								<input id="openImg" type="hidden" value="${coachInfo.openImg}"/>
							</li>
							</div>
							<div style="height:100%; overflow:hidden">
						   <li style="height:100%; overflow:hidden;float:left; width:160px;">
								<span class="create_syllabus_item_name">照片</span>
								<div id="preview">
								<img src="${coachInfo.signImg}"  style="width:120px;height:60px">
								</div>
								<div id="fine-uploader-manual-trigger" ></div>
								<input id="signImg" type="hidden" value="${coachInfo.signImg}"/>
							</li>
							</div>
							<li>
						<span class="create_syllabus_item_name">私教</span>
						<div class="tuikuan">
							 <font style="margin-left:10px;"><input id="coachType" type="radio" name="coachType" <c:if test="${coachInfo.coachType == 1}">checked="checked"</c:if> value="1" />是</font>
							 <font>   <input id="coachType" type="radio" name="coachType" <c:if test="${coachInfo.coachType == 0}">checked="checked"</c:if> value="0"  /> 否</font>
						  </div>
						  </li>
						   <li>
								 <span class="create_syllabus_item_name">排序值</span>
								<input type="number" id="sort"  class="create_syllabus_input wid120" style="width:100px;" value="${coachInfo.sort }"  >（数值越小排位越前）
							</li>
						<li  class="j_sijiao">
						<span class="create_syllabus_item_name">退款手续费</span>
						<input  id="fee" type="text" class="create_syllabus_input wid320" value="${coachInfo.fee/100}" >%
						 </li>
						<li class="j_sijiao">
							<span class="create_syllabus_item_name">私教价格</span>
							<input id="salePrice" type="text" class="create_syllabus_input wid320"  value="${coachInfo.salePrice/100 }">元/小时
						</li>
						<li class="j_sijiao"  style="height:100%; overflow: hidden;">
							<span class="create_syllabus_item_name">服务分店</span>
							 <input id="useCustId" type="hidden" value="${coachInfo.useCustId }">
							 <div class="showcoach">
							 <c:forEach  items="${split }" var="uid">
								<p id="${uid }">${funcs:getCustNameByCustId(uid)}<i class='empty'></i></p>
							 </c:forEach>
						  <select id="sysCustId" name="select" class="joinbusiness" onscroll="true" style="height: 34px;
														margin-left: 0px;" >
							 <option value="" selected="selected"   >请选择</option>
							 <c:forEach var="map" items="${custList}">
							 <option  value="${map.system_id}">${map.cust_name}</option>
							 </c:forEach>
						  </select>
						 </div>

						   <%--  <form id="form1" name="form1" method="post" action="">
						  <input id="userCustId" name="textfield"  class="choosemerber" data-id=""  value="${funcs:getCustNameByCustIds(coachInfo.useCustId)}" readonly="readonly" />
						  <input id="userCustIds" type="hidden" name="userCustId" />
						  <select id="sysCustId" name="select"  class="choosemerber ch-bran">
							 <option value="" selected="selected" >请选择</option>
							 <c:forEach var="map" items="${custList}">
						   <option value="${map.system_id }"   >${map.cust_name}</option>
							 </c:forEach>
						  </select>
						</form> --%>
						</li>
						  <li>
							 <span class="create_syllabus_item_name">教练介绍</span>
							<textarea id="form-content" class="editor" cols="30" rows="10"  style="width:442px; height:40px;" >${coachInfo.proDesc }</textarea>
									<%@include file="/include/editor.jsp" %>
								<script>
								/** Simple customization with current options **/
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
								</script>
						 </li>
					  </ul>
				   </div>
				 </div>
			<div class="btns-area">
				<a href="javascript:void(0)" class="setrecord">修改记录</a>
				<a href="javascript:void(0)" id="submit">保存修改</a>
				<a href="javascript:void(0)" id="del">删除教练</a>
				<a href="javascript:void(0)" class="outline_card">线下购买</a>
			</div>
		</div>
</div>
<div id="setrecord" class="setRecordBox closebox">
    <h3 class="create_syllabus_title">修改记录</h3>
    <div class="setRecordBox_list">
        <ul id="testDiv">
            <c:forEach items="${userLogList }" var="logList">
            <li>
                <span class="setRecordBox_name">${logList.userId }</span>
                <span class="setRecordBox_time"><fmt:formatDate value="${logList.createTime }" type="date" pattern="yyyy年MM月dd日 HH:mm:ss " /></span>
                  <span class="setRecordBox_time">${logList.logMemo }</span>
            </li>
            </c:forEach>
        </ul>
    </div>
    <div class="closesetRecordBox"><span class="close">确定</span></div>
</div>
<div class="outline_buyCard closebox" style="display: none;">
    <div class="outline-cont">
        <div class="outline-top"></div>
        <div class="outline-warp create_syllabus_cont" style="display: ;">
            <ul>
                <li>
                    <span class="create_syllabus_item_name">手机号</span>
                    <input type="text" id="usemobile" class="create_syllabus_input"  style="width:180px;" placeholder="请输入会员手机号">
                    <a href="javascript:;" class="outGetYz">搜索</a>
                </li>
                <li style="height: 60px;padding-top: 15px;padding-bottom: 15px; line-height:60px; display: block;">
                    <img src="" alt="" id="memberLogo" data-id="" class="ajax-user">
                    <span   id="membername" style="margin-left: 15px;"></span>
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
	$('.setrecord').click(function(){
		showShadow();
		$('#setrecord').show();
		
	});
	
	$('.closesetRecordBox').click(function(){
		$('#setrecord').hide();
		$("#shade").remove();
	});
	
	$('.create_syllabus_qx').click(function(){
		
		$(".outline_buyCard").hide();
		$("#shade").remove();
	});
	
	
	
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
	   var  memberId=$("#memberLogo").attr("data-id");
		//alert(memberId);
		console.log(memberId);
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
   $(".outGetYz").click(function(){
	   var mobile=$("#usemobile").val();
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
//alert(arr);
$('.empty').on('click',function(){
	var pid = $(this).parents("").attr('id');
	if($.inArray(pid,arr) > -1){
		arr.splice($.inArray(pid,arr),1);
	}
	$(this).parent().remove();
	//console.log(arr);
});
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
	/* $("div p").each(function(){
		     var   useId= $(this).attr("id");
		    if($.inArray(useId, arr) == -1){
				 arr.push(useId); 
			 }
		    $('.empty').on('click',function(){
	    		if($.inArray(useId,arr) > -1){
	    			arr.splice($.inArray(useId,arr),1);
	    		}
	    		$(this).parent().remove();
	    		console.log(arr);
	    	})
		}); */
	
	$(".joinbusiness").change(function(){
		
		var id=$('#sysCustId').val();
		var name=$('#sysCustId').find("option:selected").text();
			
			if(id!=''){
		 if($.inArray(id, arr) == -1){
			 arr.push(id); 
		 }else{
         	alert("您已经选择了此分店!");
         	return;
         }
		var html="<p id='"+id+"'>"+ name +"<i class='empty'></i></p>";
	
		 $('.joinbusiness').before(html);
		
		$('.empty').on('click',function(){
    		var pid = $(this).parent("").attr('id');
    		//alert(pid);
    		if($.inArray(pid,arr) > -1){
    			arr.splice($.inArray(pid,arr),1);
    		}
    		$(this).parent().remove();
    		console.log(arr);
    	})
	}
    	
})
	/* $('#sysCustId').change(function(){
		//alert($('#sysCustId').val());
		//alert($('#sysCustId').find("option:selected").text())
		
		var cusIds=$('#sysCustId').val();
		//id值
		
		var custNames=$('#sysCustId').find("option:selected").text();
		//name直
		
    	var name=$('#userCustId').val();
		//展示name
		
		var id= $('#userCustIds').val();
		//展示id
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
	}) */
    $('#testDiv').slimScroll({
            alwaysVisible: true
        });
    $("#submit").click(function(){
    	//alert(arr);
		var id=$("#id").val();
		var name=$("#name").val();
		if($.trim(name).length == 0){
			alert("请输入教练姓名！");
			return;
		}
		var mobile=$("#mobile").val();
		if($.trim(mobile).length == 0){
			alert("请输入教练手机号！");
			return;
		}
		if(!/^1\d{10}$/.test(mobile)){
    	alert("手机号格式不正确");
    	return;
    }
		var openImg=$("#openImg").val();
		if($.trim(openImg).length == 0){
			alert("请上传教练二维码图片！");
			return;
		}
		var signImg=$("#signImg").val();
		if($.trim(signImg).length == 0){
			alert("请上传教练图片！");
			return;
		}
		var proDesc=$("#form-content").val();
		if($.trim(proDesc).length == 0){
			alert("请输入教练详情！");
			return;
		}
		var coachType= $("input[name='coachType']:checked").val();
		if(coachType==0){
			var fee=$("#fee").val();
			var salePrice=$("#salePrice").val();
			var useCustId=arr;
			
		}else{
			
		var fee=$("#fee").val();
		if($.trim(fee).length == 0){
			alert("请正确输入退款手续费！");
			
			return;
		}
		if(fee<=0){
			alert("请正确输入退款手续费！");
			return;
		}
		var salePrice=$("#salePrice").val();
		if($.trim(salePrice).length == 0){
			alert("请正确输入价格！");
			return;
		}
		if(salePrice<=0){
			alert("请正确输入价格！");
			return;
		}
		var useCustId=arr;
		if($.trim(useCustId).length == 0){
			alert("请选择分店！");
			return;
		}
		}
		
		$.ajax({
			type:'post',
		    data:{"id":id,"name":name,"mobile":mobile,"openImg":openImg,"signImg":signImg,
		    	"coachType":coachType,"fee":fee,"salePrice":salePrice,"useCustId":useCustId.join(","),
		    	"proDesc":proDesc,"sort":$("#sort").val()},
		    url:"/coach/update.action",
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
	$("#del").click(function(){
		var id=$("#id").val();
		$.ajax({
			type:'post',
		    data:{"id":id},
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
	
})

//sign
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
            	$('#preview').remove();
            	$('#signImg').val(responseJSON.url);
            },
            onAllComplete: function(successful, failed) {
            	console.log("onAllComplete-----successful:"+successful+"----failed:"+failed);
            	//manualUploader.reset();
            }
        }
    });

   
    
   //openImg 
    var manualUploader= new qq.FineUploader({
        element: document.getElementById('fine-uploader-manual-trigger1'),
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
            	$('#preview1').remove();
            	$('#openImg').val(responseJSON.url);
            },
            onAllComplete: function(successful, failed) {
            	console.log("onAllComplete-----successful:"+successful+"----failed:"+failed);
            	//manualUploader.reset();
            }
        }
    });

   
</script>
</body>
</html>				

				