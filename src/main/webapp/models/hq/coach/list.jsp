<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
    <title>教练列表</title>
  <link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<link type="text/css" rel="stylesheet" href="/static/third/easyui/themes/default/validatebox.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/star.js"></script>
<script type="text/javascript" src="/static/js/file.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>
<script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
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
	<div class="head">
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
            <h3 class="i_title">教练管理</h3>
            <div class="province"></div>
            <div class="coach_list" >
              <c:set var="coachList" value="${coachList}" ></c:set>

			  <c:if test="${fn:length(coachList)>0}">    
                <c:forEach  items="${coachList}"  var="info">
                    <c:set  value="${info.id}"  var="id"/>
                    
                    <dl> 
                    <a  href="/coach/detail.action?id=${info.id}&coachType=${info.coachType}" target="_blank">
                    <dt>
                        <img src="${info.signImg }" alt="" />
                        <c:if test="${info.coachType==1 }" >
                        <span class="sijiao">
                            <img src="/static/images/sijiao.png" alt=""/>
                        </span>
                        </c:if>
                    </dt>
                    <dd>${info.name}</dd>
                    </a>
                 	</dl>
               	</c:forEach>
              </c:if> 	
           </div>
		    <div class="pager">
				<c:set var="pageUrl"
					value="/coach/list.action?pageNo=pageno"
					scope="request" />
				<jsp:include flush="true" page="/include/pager.jsp" />
			</div>
			<div class="btns-area">
				<a href="javascript:;" class="create_syllabus">添加教练</a>
			</div>
		  </div>
		</div>
      </div>
   </div>
   
<div class="create_syllabus_box closebox" style="display: none">
    <h3 class="create_syllabus_title">添加教练</h3>
    
    <div  id="testDiv">
    <div class="create_syllabus_cont">
        <ul>
            <li>
                <span class="create_syllabus_item_name">姓名</span>
                <input id="name" type="text"  class="create_syllabus_input" >
            </li>
            <li>
                <span class="create_syllabus_item_name">手机号</span>
                <input id="mobile" type="text"  class="create_syllabus_input" >
            </li>
            <div style="height:100%; overflow:hidden;">
	            <li style="height:100%; overflow:hidden;float:left; width:160px;">
	                <span class="create_syllabus_item_name">二维码</span>
		    		<div id="fine-uploader-manual-trigger1" style="width:300px;"></div>
		    		<input id="openImg" type="hidden" value=""/>
	            </li>
	            <li style="height:100%; overflow:hidden;float:left; width:160px;">
	                <span class="create_syllabus_item_name">照片</span>
	    			<div id="fine-uploader-manual-trigger" style="width:300px;"></div>
	    			<input id="signImg" type="hidden" value=""/>
	            </li>
	        </div>
            <li>
               <span class="create_syllabus_item_name">私教</span>
               <div class="tuikuan">
                    <font  style="margin-right:15px;"><input name="coachType"  value="1" type="radio" />是</font>
                    <font><input  name="coachType" value="0" type="radio"  checked/>否</font>
                 </div>
             </li>
              <li>
				<span class="create_syllabus_item_name">排序值</span>
				<input type="number" id="sort"  class="create_syllabus_input wid120" style="width:100px;" value=""  >
				（数值越小排位越前）
				</li>
             <div style="height:100%; overflow:hidden;">
	            <li  class="j_sijiao" style="float:left;">                     
	            <span class="create_syllabus_item_name">退款手续费</span> 
	            <input  id="fee" type="text" class="create_syllabus_input" style="width:120px;"  >%
	             </li>
	            <li class="j_sijiao"  style="float:left; margin-left:15px;">
	                <span class="create_syllabus_item_name">私教价格</span>
	                <input id="salePrice" type=""text"" class="create_syllabus_input"  style="width:120px;">元/小时
	            </li>
            </div>
            <li style="height: 100%; overflow: hidden;" class="j_sijiao">
                <span class="create_syllabus_item_name">服务分店</span>
                   <div class="showcoach">
                  <select id="sysCustId" name="select" class="joinbusiness" onscroll="true" style="height: 34px;
    											margin-left: 0px;" >
				     <option value="" selected="selected"   >请选择</option>
                     <c:forEach var="map" items="${custList}">
                   	 <option  value="${map.system_id}">${map.cust_name}</option>
                     </c:forEach>
				  </select>
                 </div>
           </li>
                
                <%-- <form id="form1" name="form1" method="post" action="">
				  <input id="userCustId" value="" name="textfield" data-id="" class="choosemerber"/>
				  <input id="userCustIds" type="hidden" name="userCustId"/>
				  <select id="sysCustId" name="select"  class="choosemerber" style="height: 34px;
    margin-left: 15px;" >
				     <option value="" selected="selected"   >请选择</option>
                     <c:forEach var="map" items="${custList}">
                   <option  value="${map.system_id}">${map.cust_name}</option>
                     </c:forEach>
				  </select>
				</form>  --%>
            <li>
                <span class="create_syllabus_item_name">教练介绍</span>
               <textarea id="form-content" class="editor" cols="30" rows="10"  style="width:442px; height:40px;" ></textarea>
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
	  		//编辑器内容为:$('#form-content').html();
	    </script>
            </li>
        </ul>
    </div>
    </div>
    <div class="create_card_btn">
    	<em class="create_syllabus_qx close">取消</em>
        <em id="submit" class="create_syllabus_fs">确定</em>
    </div>
</div>
</body>

<script type="text/javascript">
var arr = new Array();
$(function(){
	  $('#testDiv').slimScroll({
          alwaysVisible: true
      });

	$(".create_syllabus").on("click",function(){
	    showShadow();
	    $(".create_syllabus_box").show();
	})

    // 取消关闭弹框
    $(".close").click(function(){
        $(this).parents(".closebox").hide();
        $("#shade").remove();
    });
	//添加門店
	$(".joinbusiness").change(function(){
		var id=$('#sysCustId').val();
		var name=$('#sysCustId').find("option:selected").text();
			//alert(id);
			if(id!=''){
		 if($.inArray(id, arr) == -1){
			 arr.push(id); 
		 }else{
         	alert("您已经选择了此教练!");
         	return;
         }
		var html="<p id='"+id+"'>"+ name +"<i class='empty'></i></p>";
	
		 $('.joinbusiness').before(html);
		
		$('.empty').on('click',function(){
    		var pid = $(this).parents("").attr('id');
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
		
		
		var custNames=$('#sysCustId').find("option:selected").text();
		
		
    	var name=$("#userCustId").val();
		
		
		
		var id= $('#userCustIds').val();
		if(id.indexOf(cusIds) >= 0){
			return;
		}else{
			if(id == ''){
				name=custNames
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
	var val = $("input[name='coachType']:checked").val();
	if(val ==0){
		$(".j_sijiao").hide();
	}else{
		$("j_sijiao").show();
	}
	$(".tuikuan").click(function(){
		var val2 = $("input[name='coachType']:checked").val();
		if(val2 == 0){
			$(".j_sijiao").hide();
		}else{
			$(".j_sijiao").show();
		}
	})
	
	$("#submit").click(function(){
		
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
		var coachType= $("input[name='coachType']:checked").val();//0非私教1私教
		if(coachType==0){
			var fee=$("#fee").val();
			var salePrice=$("#salePrice").val();
			var useCustId= arr;
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
			alert("请选择门店！");
			return;
		}
		}
		$.ajax({
			type:'post',
		    data:{"name":name,"mobile":mobile,"openImg":openImg,"signImg":signImg,
		    	"coachType":coachType,"fee":fee,"salePrice":salePrice,"useCustId":useCustId.join(","),
		    	"proDesc":proDesc,"sort":$('#sort').val()},
		    url:"/coach/save.action",
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
            	$('#signImg').val(responseJSON.url);
            },
            onAllComplete: function(successful, failed) {
            	console.log("onAllComplete-----successful:"+successful+"----failed:"+failed);
            	//manualUploader.reset();
            }
        }
    });

    var manualUploader1 = new qq.FineUploader({
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
            	$('#openImg').val(responseJSON.url);
            },
            onAllComplete: function(successful, failed) {
            	console.log("onAllComplete-----successful:"+successful+"----failed:"+failed);
            	//manualUploader.reset();
            }
        }
    });

  
    
</script>


</html>