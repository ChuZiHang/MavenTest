<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
			<h3 class="i_title">会员卡详情</h3>
			<div class="province" style="height:25px;"></div>
			<div class="item_syllabus_list_tag">
				<span class="item_syllabus_list_tag_lie"><a href="#">会员卡信息</a></span>
				<shiro:hasPermission name="membercard:sell">
				<span><a href="/membercard/sell.action?id=${bean.id}">售卖信息</a></span>
				</shiro:hasPermission>
			</div>
			<div class="syllabus_detail_cont">
				<div class="create_syllabus_cont syllabus_detail_show card_infor_show">
				<input type="hidden" id="idcard" name="idcard" value="${bean.id}" >
						<ul>
							<li>
								<span class="create_syllabus_item_name">卡名称</span>
								<input id="cardName" name="cardName" type="text" class="create_syllabus_input wid320" value="${bean.cardName}">
							</li>
							<li>
								<span class="create_syllabus_item_name">时间</span>
								<input id="days"  name="days" type="text" class="create_syllabus_input wid120" style="width:100px;" value="${bean.days}">天
								<em class="create_syllabus_input_p">价格</em>
								<input id="salePrice" name="salePrice" type="text" class="create_syllabus_input wid120" style="width:100px;" value="${bean.salePrice/100}">元
							</li>
							<li>
								<span class="create_syllabus_item_name">购买规则</span>
								<div class="chooseshop">
									<span id="saleRule" class="chooseText">
									<c:if test="${bean.saleRule eq 0}">不限次</c:if>
									<c:if test="${bean.saleRule eq 1}">1次</c:if>
									<c:if test="${bean.saleRule eq 2}">2次</c:if>
								   </span>
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
								<div id="preview">
											<img src="${bean.signImg}" alt="" />
								</div>
							<div id="fine-uploader-manual-trigger" style="width:700px;"></div>
							<input id="openImg" type="hidden" value="${bean.signImg}"/>
							</li>
							 <li>
								<span class="create_syllabus_item_name">适用店铺</span>
								 <div class="chooseshop">
										   <c:choose>
										   <c:when test="${bean.useCustId ne '0'}">
										   <span class="chooseText" id="branchId" data-id="${bean.useCustId}">
										 ${custName}
										 </span>
										   </c:when>
										   <c:otherwise>
									<span class="chooseText" id="branchId" data-id="0">
																							  通用
									</span>
										   </c:otherwise>
										  </c:choose>
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
									<div  id="shoping"  <c:choose>
										 <c:when test="${bean.status eq 0}">
										 class="card_sta on"
			.							 </c:when>
										 <c:otherwise>
										 class="card_sta"
										 </c:otherwise>
										  </c:choose> >售卖中</div>
									<div id="xiajia" <c:choose>
										 <c:when test="${bean.status eq 1}">
										 class="card_sta on"
										 </c:when>
										 <c:otherwise>
										 class="card_sta"
										 </c:otherwise>
										  </c:choose>>已下架</div>
								</div>
							</li>
							 <li>
								 <span class="create_syllabus_item_name">排序值</span>
								<input type="number" id="sort"  name="days" class="create_syllabus_input wid120" style="width:100px;" value="${bean.sort }"  onChange="return check()">
							</li>
							<li>
								<span class="create_syllabus_item_name">支持退款</span>
								<div class="tuikuan">
									<em><i rel="1"
									<c:choose>
										 <c:when test="${bean.isRefund eq 0}">
										 class="on"
										 </c:when>
										 <c:otherwise>
										 class=""
										 </c:otherwise>
										  </c:choose>
									></i>是</em>
									<em><i rel="2" id="frel"
									  <c:choose>
										 <c:when test="${bean.isRefund eq 1}">
										 class="on"
										 </c:when>
										 <c:otherwise>
										 class=""
										 </c:otherwise>
										  </c:choose>
									></i>否</em>
									<em class="poundage"
									<c:choose>
										 <c:when test="${bean.isRefund eq 0}">
										  style="position: absolute; right: 0; top: 8px;"
										 </c:when>
										 <c:otherwise>
										  style="display:none;position: absolute; right: 0; top: -3px;"
										 </c:otherwise>
										  </c:choose>
									 >
										退款手续费<input id="fee" name="fee"  value="${bean.fee/100}" type="text" class="refund">%
									</em>
								</div>
							</li>
							<li class="j_sijiao"
							 <c:choose>
										 <c:when test="${bean.isRefund eq 0}">
										 style=""
										 </c:when>
										 <c:otherwise>
										 style="display: none"
										 </c:otherwise>
										  </c:choose>
							>
									 <span  class="create_syllabus_item_name">退款规则</span>
					   <div class="choosemerber">
							<span id="refundRule" class="chooseText">
							<c:choose>
										 <c:when test="${bean.refundRule eq 1}">
										 扣除已消费月份
										 </c:when>
										 <c:when test="${bean.refundRule eq 0}">
										 全额
										 </c:when>
										 <c:otherwise>
										 扣除已消费月份
										 </c:otherwise>
										  </c:choose>
							</span>
							<i></i>
							<div class="choosemerber_shop">
								<p>扣除已消费月份</p>
								<p>全额</p>
							</div>
						</div>
							<li>
								<span class="create_syllabus_item_name">月度费用</span>
								<input id="refundPrice" name="refundPrice" type="text" class="create_syllabus_input wid120" value="${bean.refundPrice/100}" style="border: 1px solid rgb(228, 228, 228);">元
							</li>
							<li>
								<span class="create_syllabus_item_name">会员卡介绍</span>
								<textarea id="form-content"
							class="editor" cols="30" rows="10"  style="width:442px; height:40px;">${bean.proDesc}</textarea>

						<%--         <textarea id="elm1" name="elm1" class="xheditor" rows="12" cols="80" style="width:320px; height:40px; padding: 6px;;">${bean.proDesc}</textarea>
	 --%>                        </li>
						</ul>
				</div>
			</div>
			<div class="btns-area">
				<a href="####" class="setrecord">操作日志</a>
				<shiro:hasPermission name="membercard:update">
					<a href="####" id="update">保存修改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="membercard:delete">
					<a href="####" id="delete">删除会员卡</a>
				</shiro:hasPermission>
			</div>
		</div>
	</div>
</div>
<div class="setRecordBox closebox">
		<h3 class="create_syllabus_title">修改记录</h3>
		<div class="setRecordBox_list">
			<ul id="testDiv">
				<c:forEach var="log" items="${logList}">
					<li><span class="setRecordBox_name">${log.userId}</span> <span
						class="setRecordBox_time"><fmt:formatDate
								value="${log.createTime }" type="date"
								pattern="yyyy年MM月dd日 HH:mm:ss " /></span> <span
						class="setRecordBox_time">${log.logMemo }</span>
				</c:forEach>

			</ul>
		</div>
		<div class="closesetRecordBox">
			<span class="close">确定</span>
		</div>
	</div>
<script>
function  check(){
	var temp=/^\d+(\.\d+)?$/;
	 var sort = $("#sort").val();
	if(temp.test(sort)==false)
	 alert("输入错误");
	
}
    // 自定义竖滚动条
    $(function(){
        $('#testDiv').slimScroll({
            alwaysVisible: true
        });
        
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
        
     // 取消关闭弹框
        $(".close").click(function(){
            $(this).parents(".closebox").hide();
            $("#shade").remove();
        });
        
    		 
        $(".choosemerber_shop p").click(function(){
   	        var text = $(this).text();
   	        $(this).parents(".choosemerber").find(".chooseText").text(text);
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
        
        $(".chooseshop").click(function(){
            $(this).find(".choose_gev_shop").toggle();
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
   	    $("#update").click(function(){
   	    	if(bool){
           		bool=false;
           		submit();
           		bool=true;
           	}else{
           		alert("请勿重复提交,已保存请等待");
           	}
   	    });
    	
   	 $("#delete").click(function(){
   		 if(bool){
   		var idcard=$("#idcard").val();
   		$.ajax({
			 type:"post",
			 data:{"id":idcard},
			 url:"/membercard/delete.action",
			 dataType:"json",
			 cache:false,
			 beforeSend:function(xhr){
					//$("#loading").show();
			 },
			 success:function(res) {
				if(res.result == "1"){
					alert("删除成功！");
					bool=true;
					location.href="/membercard/list.action";
				}else{
					alert("删除失败！");
					bool=true;
				}
			 },
			 error:function(resJson){
				 alert("系统异常，请稍后再试！");
			 }
		 });
   		}else{
       		alert("请勿重复操作");
       	}
	 });
   	    
   	    
   	 	
   	 function submit(){
   		var openImg = $("#openImg").val();
   		if(openImg == ''){
	   		  alert("请选择图片");
	   		  return;
	   	  }
   		  var idcard=$("#idcard").val();
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
						 data:{"branchId":branchId,"openImg":openImg,"id":idcard,"cardName":cardName,"days":days,"salePrice":salePrice,"saleRule":saleRule,"fee":fee,"isRefund":isRefund,"refundRule":refundRule,"refundPrice":refundPrice,"proDesc":proDesc,"status":status,"sort":sort},
						 url:"/membercard/update.action",
						 dataType:"json",
						 cache:false,
						 beforeSend:function(xhr){
								//$("#loading").show();
						 },
						 success:function(res) {
							if(res.result == "1"){
								alert("修改成功！");
								//window.close();
								location.href="/membercard/list.action";
							}else{
								alert("修改失败！");
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
             	$('#preview').remove();
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