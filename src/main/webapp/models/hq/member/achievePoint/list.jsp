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
						height:520px;
						margin-top:-260px;
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
					.qq-thumbnail-selector{
						height:80px;
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
			<div class="title" style="position: relative;">
				<div class="i_title">积分设置</div>
	            <div class="syllabus_branch_select search_area">
		            <span  class="on"><a href="#" style="color:#fff;">成就设置</a></span>
		        	<span ><a href="/achieve/point/list.action" style="color:#fff;">积分设置</a></span>
	            </div>
     		  </div>
     		  <div class="coach_list">
     		   <table border='0' cellspacing="0" cellpadding="0" width="100%"  class="b-table-con">
						<tr>
		                     <th style="text-align: center" bgcolor="#f2f2f2" height="38">序号</th>
		                     <th style="text-align: center" bgcolor="#f2f2f2" height="38">成就名称</th>
		                     <th style="text-align: center" bgcolor="#f2f2f2" height="38">所需积分</th>
		                     <th style="text-align: center" bgcolor="#f2f2f2" height="38">成就积分未获得</th>
		                     <th style="text-align: center" bgcolor="#f2f2f2" height="38">成就积分已获得</th>
		                     <th style="text-align: center" bgcolor="#f2f2f2" height="38">操作</th>
		                    
		                </tr>
		                
		                         <c:forEach  var="achieve"  items="${achieve }">
     		   					<input  type="hidden" id="achieveId"  value="${achieve.id }" >
		                    <tr >
		                            <td height="30" align="center">${achieve.sort }</td>
		                            <td height="30" align="center">${achieve.name} </td>
		                            <td height="30" align="center">${achieve.point }</td>
		                            <td height="30" align="center"><img src="${achieve.unobtain}" width="50" ></td>
		                            <td height="30" align="center"><img src="${achieve.obtain }" width="50" ></td>
		                            <td height="30" align="center">
										<span  id="${achieve.id}" class="achieve_update"  >修改</span>
										<span  id="${achieve.id }" class="achieve_delete"  >删除</span>
		                            </td>
		                  </tr>
		                        </c:forEach>
		                </table>
			 </div>
			 <div class="btns-area" style="line-height:32px">
			 	<a href="javascript:void (0)" class="create_syllabus">添加成就</a>
			 </div>
		      </div>
				<div class="clear_float"></div>
			</div>
			<!-- 分页 开始 -->
		<div class="pager">
			<c:set var="pageUrl"
				value="list.action?pageNo=pageno"
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
                <span class="create_syllabus_item_name">成就名称</span>
                    <input type="text" id="name" class="create_syllabus_input"  style="width:180px;" >
                </li>
                <li class="create_syllabus_item_name">
                  <span class="create_syllabus_item_name">所需积分</span>
                    <input type="number" id="point" class="create_syllabus_input"  style="width:180px;" >
                </li>
                <div style="height:100%; overflow:hidden;">
	            <li style="height:100%; overflow:hidden;float:left; width:300px;">
	                <span class="create_syllabus_item_name">徽章样式未获取</span>
		    		<div id="fine-uploader-manual-trigger1" style="width:300px;"></div>
		    		<input id="unobtain" type="hidden" value=""/>
	            </li>
	            <li style="height:100%; overflow:hidden;float:left; width:300px;">
	                <span class="create_syllabus_item_name">徽章样式已获取</span>
	    			<div id="fine-uploader-manual-trigger" style="width:300px;"></div>
	    			<input id="obtain" type="hidden" value=""/>
	            </li>
	        </div>

            </ul>
            <div class="" style="padding-top: 20px;clear: both; text-align: center">
                <em class="create_syllabus_qx close">取消</em>
                <em id="publish" class="create_syllabus_fs">保存</em>
            </div>
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
                    <input type="number" id="sort1" class="create_syllabus_input"  style="width:180px;" >
                </li>
                <li>
                <span class="create_syllabus_item_name">成就名称</span>
                    <input type="text" id="name1" class="create_syllabus_input"  style="width:180px;" >
                </li>
                <li class="create_syllabus_item_name">
                  <span class="create_syllabus_item_name">所需积分</span>
                    <input type="number" id="point1" class="create_syllabus_input"  style="width:180px;" >
                </li>
                <div style="height:100%; overflow:hidden;">
	            <li style="height:100%; overflow:hidden;float:left; width:300px;">
	                <span class="create_syllabus_item_name">徽章样式未获取</span>
	                 <div id="preview">
                        <img src=""  style="width:120px;height:60px">
			    	    </div>
		    		<div id="fine-uploader-manual-trigger2" style="width:300px;"></div>
		    		<input id="unobtain1" type="hidden" value=""/>
	            </li>
	            <li style="height:100%; overflow:hidden;float:left; width:300px;">
	                <span class="create_syllabus_item_name">徽章样式已获取</span>
	                 <div id="preview1">
						<img src=""  style="width:90px;height:90px">
			    		</div>
	    			<div id="fine-uploader-manual-trigger3" style="width:300px;"></div>
	    			<input id="obtain1" type="hidden" value=""/>
	            </li>
	        </div>
			<input  type="hidden"  id="achi">
            </ul>
            <div class="" style="padding-top: 20px;clear: both; text-align: center">
                <em class="create_syllabus_qx close">取消</em>
                <em id="submitlim" class="create_syllabus_fs">保存</em>
            </div>
        </div>

    </div>
</div>
</body>
<script type="text/javascript">
$(function(){
	$(".achieve_delete").click(function(){
		var id=$(this).attr('id');
		 if(window.confirm('确定删除该成就么？')){
				$.ajax({
					type :"get",
					url:"/achieve/achieve/del.action",
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
	 $(".create_syllabus").click(function(){
        $(".outline_buyCard").show();
        showShadow();
    })
    $('.achieve_update').click(function(){
		$(".updateRo").show();
			showShadow();
			var id=$(this).attr('id');
		$.ajax({
			type:'post',
		    data:{"id":id},
		    url:"/achieve/achieve/datail.action",
		    dataType:"json",
		    cache:false,
		    success:function(res) {
		    	if(res.status==1){
		    		var id=res.data.id;
		    		$("#sort1").val(res.data.sort);
		    		$("#name1").val(res.data.name);
		    		$("#point1").val(res.data.point);
		    		$("#unobtain1").val(res.data.unobtain);
		    		$("#obtain1").val(res.data.obtain);
		    		$("#preview").children("img").attr("src",res.data.unobtain);
		    		$("#preview1").children("img").attr("src",res.data.obtain);
		    		$("#achi").val(id);
		    	}
 			},
			 error:function(resJson){
				 alert("系统异常，请稍后再试！");
			 }
		})
	})
	$("#submitlim").click(function(){
		
		var sort=$("#sort1").val();
		var name=$("#name1").val();
		var point=$("#point1").val();
		var unobtain=$("#unobtain1").val();
    	var obtain=$("#obtain1").val();
    	var id=$("#achi").val();
    	$.ajax({
			type:'post',
		    data:{"sort":sort,"name":name,"point":point,"unobtain":unobtain,
		    	"obtain":obtain,"id":id},
		    url:"/achieve/achieve/update.action",
		    dataType:"json",
		    cache:false,
		    success:function(res) {
 				if(res.status == 0){
 					alert("操作成功！");
 					top.location.href="/achieve/list.action";
 				}else{
 					alert(res.msg);
 				}
 			},
			 error:function(resJson){
				 alert("系统异常，请稍后再试！");
			 }
		})
    	
		
	})
      $(".close").click(function(){
        $(this).parents(".closebox").hide();
        $("#shade").remove();
    });
    $("#publish").click(function(){
    	var sort=$("#sort").val();
    	if($.trim(sort).length == 0){
			alert("请输入序号");
			return;
		}
    	var name=$("#name").val();
    	if($.trim(name).length == 0){
			alert("请输入成就名称");
			return;
		}
    	var point=$("#point").val();
    	if($.trim(point).length == 0){
			alert("请输入成就积分");
			return;
		}
    	var unobtain=$("#unobtain").val();
    	var obtain=$("#obtain").val();
    	$.ajax({
			type:'post',
		    data:{"sort":sort,"name":name,"point":point,"unobtain":unobtain,
		    	"obtain":obtain},
		    url:"/achieve/achieve/save.action",
		    dataType:"json",
		    cache:false,
		    success:function(res) {
 				if(res.status == 0){
 					alert("操作成功！");
 					top.location.href="/achieve/list.action";
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
            	$('#unobtain').val(responseJSON.url);
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
            	$('#obtain').val(responseJSON.url);
            },
            onAllComplete: function(successful, failed) {
            	console.log("onAllComplete-----successful:"+successful+"----failed:"+failed);
            	//manualUploader.reset();
            }
        }
    });
  //上传图片
    var manualUploader = new qq.FineUploader({
        element: document.getElementById('fine-uploader-manual-trigger2'),
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
            	$('#unobtain1').val(responseJSON.url);
            },
            onAllComplete: function(successful, failed) {
            	console.log("onAllComplete-----successful:"+successful+"----failed:"+failed);
            	//manualUploader.reset();
            }
        }
    });

    var manualUploader1 = new qq.FineUploader({
        element: document.getElementById('fine-uploader-manual-trigger3'),
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
            	$('#obtain1').val(responseJSON.url);
            },
            onAllComplete: function(successful, failed) {
            	console.log("onAllComplete-----successful:"+successful+"----failed:"+failed);
            	//manualUploader.reset();
            }
        }
    });
</script>
</html>