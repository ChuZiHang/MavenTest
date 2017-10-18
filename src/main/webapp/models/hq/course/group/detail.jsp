<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>收费团课</title>
    <link type="text/css" rel="stylesheet" href="/static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/left.css"/>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/base.js"></script>
    <script type="text/javascript" src="/static/js/star.js"></script>
    <script type="text/javascript" src="/static/js/file.js"></script>
    <script type="text/javascript" src="/static/js/left.js"></script>
    <script type="text/javascript" src="/static/js/Calendar.js"></script>
    <script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
    <script src="/static/third/fine-uploader/fine-uploader.js"></script>
    <script type="text/template" id="qq-template-manual-trigger">
		<%@ include file="/include/template-uploader-single.jsp" %>
	</script>
	<style>
	.push-syllabus{
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
            <jsp:param name="nvaIndex" value="17" />
            <jsp:param name="nvaParentIndex" value="14" />
        </jsp:include>
        <!--左侧导航：结束-->
        <div class="rightCont">
            <h3 class="i_title">收费团课</h3>
            <div class="province" style="height:25px;"></div>
            <div class="syllabus_detail_cont">
                <div class="create_syllabus_cont syllabus_detail_show">
                <form id="form" action="/course/group/update.action" method="post">
                <input name="id" type="hidden" value="${model.courseGroupInfo.id}">
                <ul>
                <li>
                <span class="create_syllabus_item_name">名称</span>
                <input id="courseName" name="courseName" type="text" class="create_syllabus_input wid320" value="${model.courseGroupInfo.courseName }">
                </li>
                <li>
                <span class="create_syllabus_item_name">限制人数</span>
                <input id="peopleNum" name="peopleNum" type="text" class="create_syllabus_input wid320" value="${model.courseGroupInfo.peopleNum }">
                </li>
                <li>
                <span class="create_syllabus_item_name">价格</span>
                <input id="salePrice" name="salePrice" type="text" class="create_syllabus_input wid320" value="${model.courseGroupInfo.salePrice/100 }">
                <span>元/人</span>
                </li>
                <li>
                <span class="create_syllabus_item_name">课程次数</span>
                <input id="classCount" name="classCount" type="text" class="create_syllabus_input wid320" value="${model.courseGroupInfo.classCount }">
                <span>人</span>
                </li>
                <li style="height:100%; overflow: hidden;">
                <span class="create_syllabus_item_name">图片</span>
                <div id="preview" style="float: left;"><img src="${model.courseGroupInfo.signImg }" alt=""/></div>
                <div id="view"></div>
                <div id="fine-uploader-manual-trigger" style="width:700px;"></div>
                <input id="signImg" name="signImg" value="${model.courseGroupInfo.signImg }" type="hidden">
                </li>
                <li>
                <span class="create_syllabus_item_name">支持退款</span>
                <div class="tuikuan">
                <c:if test="${model.courseGroupInfo.isRefund == 1}">
                    <em><i id="yes" rel="1" class="on"></i>是</em>
                    <em><i id="no" rel="2"></i>否</em>
                    <input id="isRefund" name="isRefund" value="${model.courseGroupInfo.isRefund }" type="hidden">
                    <em class="poundage">
                    退款手续费<input id="fee" name="fee" type="text" value="${model.courseGroupInfo.fee/100 }" class="refund">%
                    </em>
                </c:if>
                <c:if test="${model.courseGroupInfo.isRefund == 0}">
                    <em><i id="yes" rel="1"></i>是</em>
                    <em><i id="no" rel="2" class="on"></i>否</em>
                    <input id="isRefund" name="isRefund" value="${model.courseGroupInfo.isRefund }" type="hidden">
                    <em class="poundage" style="display: none;">
                    退款手续费<input id="fee" name="fee" type="text" value="" class="refund">%
                    </em>
                </c:if>
                </div>
                </li>
                <li>
                <span class="create_syllabus_item_name">课程内容</span>
                <textarea id="memo" name="memo" class="xheditor" style="width:442px; height:90px;">${model.courseGroupInfo.proDesc }</textarea>
                <%@include file="/include/editor.jsp" %>
                <script>
                /** Simple customization with current options **/
                $('#memo').trumbowyg({
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
                </form>
                </div>
            </div>
            <div class="btns-area">
                <a href="####" class="setrecord">修改记录</a>
                <shiro:hasPermission name="course/group:update">
                    <a href="####" id="submit" onclick="update(${model.courseGroupInfo.id})">保存修改</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="course/group:publish">
                    <a href="####" class="setfabu">发布</a>
                </shiro:hasPermission>
                <a href="####" class=" delsyllabus" onclick="del(${model.courseGroupInfo.id})">删除课程</a>
            </div>
        </div>

    </div>
</div>
<div class="setRecordBox closebox">
    <h3 class="create_syllabus_title">修改记录</h3>
    <div class="setRecordBox_list">
        <ul id="testDiv">
            <c:forEach items="${model.userLogList}" var="bean" >
            <li>
                <span class="setRecordBox_name">${bean.userId }</span>
                <span class="setRecordBox_time"><fmt:formatDate value="${bean.createTime }" pattern="yyyy年MM月dd日  HH:mm:ss"/></span>
                <span class="setRecordBox_time">${bean.logMemo }</span></li>
                <!-- <span class="setRecordBox_date">2016-06-25</span> -->
            </li>
            </c:forEach>
        </ul>
    </div>
    <div class="closesetRecordBox"><span class="close">确定</span></div>
</div>
<div class="push-syllabus closebox" style="display: none;">
    <h3 class="create_syllabus_title">发布课程</h3>
    <div id="testDiv2">
    <h3 class="create_syllabus_cont">
        <form id="form2" action="/course/group/publish.action" method="post">
        <ul>
            <li>
                <span class="create_syllabus_item_name">名称</span>
                <input id="cardName" name="cardName" type="text" value="${model.courseGroupInfo.courseName }" class="create_syllabus_input">
            </li>
            <div class="create_syllabus_cont_style2">
                <div>
                    <span>限制人数</span><input id="peopleNum1" name="peopleNum" type="text" value="${model.courseGroupInfo.peopleNum }" class="create_syllabus_input input60">
                </div>
                <div>
                    <span>价格</span><input id="salePrice1" name="salePrice" type="text" value="${model.courseGroupInfo.salePrice/100 }" class="create_syllabus_input input60" style="width:50px">
                	<span>元/人</span>
                </div>
                <div>
                    <span>课程次数</span><input id="classCount1" name="classCount" type="text" value="${model.courseGroupInfo.classCount }" class="create_syllabus_input input60" style="width:50px">
                	<span>次</span>
                </div>
            </div>
            <div class="clear_float"></div>
            <li style="height:100%; overflow: hidden;">
                <span class="create_syllabus_item_name">图片</span>
                <div id="preview1" style="float: left;"><img src="${model.courseGroupInfo.signImg }" alt=""/></div>
                <div id="view"></div>
				<div id="fine-uploader-manual-trigger1" style="width:700px;"></div>
                <input id="signImg1" name="signImg" value="${model.courseGroupInfo.signImg }" type="hidden">
            </li>
            <li>
                <span class="create_syllabus_item_name">支持退款</span>
                <div class="tuikuan">
                    <c:if test="${model.courseGroupInfo.isRefund == 1}">
	                    <em><i id="yes" rel="1" class="on"></i>是</em>
	                    <em><i id="no" rel="2"></i>否</em>
	                    <input id="isRefund" name="isRefund" value="${model.courseGroupInfo.isRefund }" type="hidden">
	                    <em class="poundage">
	                        退款手续费<input id="fee1" name="fee" type="text" value="${model.courseGroupInfo.fee/100 }" class="refund">%
	                    </em>
	                </c:if>
	                <c:if test="${model.courseGroupInfo.isRefund == 0}">
	                    <em><i id="yes" rel="1"></i>是</em>
	                    <em><i id="no" rel="2" class="on"></i>否</em>
	                    <input id="isRefund" name="isRefund" value="${model.courseGroupInfo.isRefund }" type="hidden">
	                    <em class="poundage" style="display: none;">
	                        退款手续费<input id="fee1" name="fee" type="text" value="" class="refund">%
	                    </em>
	                </c:if>
                </div>
            </li>
            <li>
                <span class="create_syllabus_item_name">分店</span>
                <div class="chooseshop">
                    <span class="chooseText"></span>
                    <input id="sysCustId" name="sysCustId" type="hidden">
                    <i></i>
                    <div id="chooseShop" class="choose_gev_shop">
                        <!-- <p>江南春店</p> -->
                    </div>
                </div>
                <div class="choose_gev_shop_times"><input name="control_date" type="text" id="control_date" size="10" maxlength="10" onClick="new Calendar().show(this);" readonly="readonly" /></div>
                <div class="choose_gev_shop_times">-</div>
                <div class="choose_gev_shop_times"><input name="control_date2" type="text" id="control_date2" size="10" maxlength="10" onClick="new Calendar().show(this);" readonly="readonly" /></div>
            </li>
            <li style="height: 100%;overflow: hidden;">
                <span class="create_syllabus_item_name">教练</span>
                <div class="showcoach">
                	<!-- <p>小李子<i></i>
                	</p> -->
                    <select class="joinbusiness" onscroll="true">
                    	<option value="">选择添加教练</option> 
                    </select>
                 </div>
            </li>
            <li>
                <span class="create_syllabus_item_name" title="课程内容">课程内容</span>
                <textarea id="elm11" name="elm1" class="xheditor" rows="12" cols="80" style="width:442px; height:90px;">${model.courseGroupInfo.proDesc }</textarea>
            	<%@include file="/include/editor.jsp" %>
					    <script>
					    /** Simple customization with current options **/
				        $('#elm11').trumbowyg({
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
        </form>
    </h3>
    </div>
    <div class="create_card_btn">
       <em class="create_syllabus_qx close">取消</em>
       <em id="publish" class="create_syllabus_fs">发布</em>
   </div>
</div>
<script>
    // 自定义竖滚动条
    $(function(){
        $('#testDiv').slimScroll({
            alwaysVisible: true
        });
        $('#testDiv2').slimScroll({
            alwaysVisible: true
        });
    });
    $(".close").click(function(){
        $(this).parents(".closebox").hide();
        $("#shade").remove();
    });

    $(".confirm").click(function(){
        $("#Popup").show();
        $(this).parents(".closebox").hide();
    });
  //新建收费团课是否支持退款
    $(".tuikuan i").click(function(){
        var rel=$(this).attr('rel');
        $(this).addClass("on").parent().siblings().find("i").removeClass("on");
        //alert(rel);
        if(rel == 1){
            $(".poundage").show();
            $(".j_sijiao").show();
            $(".tuikuan").children("#isRefund").val(1);
        }
        else{
            $(".poundage").hide();
            $(".j_sijiao").hide();
            $(".tuikuan").children("#isRefund").val(0);
        }
    });

 // 发布课程选择分店
    $(".chooseshop").click(function(){
        $(this).find(".choose_gev_shop").toggle();
    });
    $(".choosemerber").click(function(){
        $(".choosemerber_shop").toggle();
    });

    $(".choose_gev_shop p").click(function(){
        var text = $(this).text();
        $(this).parents(".chooseshop").find(".chooseText").text(text)
    })
    // 修改记录弹出框
    $(".setrecord").click(function(){
        showShadow();
        $(".setRecordBox").show();
    });
    //团课更新上传图片
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
              	$('#signImg').val(responseJSON.url);
              	$("#preview").children().remove();
              },
              onAllComplete: function(successful, failed) {
              	console.log("onAllComplete-----successful:"+successful+"----failed:"+failed);
              	//manualUploader.reset();
              }
          }
      });
    //发布上传图片
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
              	$('#signImg1').val(responseJSON.url);
              	$("#preview1").children().remove();
              },
              onAllComplete: function(successful, failed) {
              	console.log("onAllComplete-----successful:"+successful+"----failed:"+failed);
              	//manualUploader.reset();
              }
          }
      });

</script>
<script type="text/javascript">

	//删除
	function del(id){
		 if(window.confirm("确定删除吗")){
			  //alert("确定");
			 $.ajax({
			   type: "post",
			   url: "/course/group/delete.action",
			   data: {id:id},
			   dataType : "json",
			   success: function(res){
				   if(res === undefined){
	    				alert("操作失败请稍后再试");
	    			}else{
	    				if(res.status == 0){
	    					alert("操作成功！");
	    					location.href="/course/group.action";
	    				}else{
	    					alert(res.msg);
	    				}
	    			}	
			   },
			   error:function(res){
  					alert("网络加载失败，请稍后再试！");
  				}
			});
             return true;
         }else{
             //alert("取消");
             return false;
         }
		
			
	};
	
	//表单提交(修改)
	function update(id){
		var courseName = $("#courseName").val();
		if($.trim(courseName).length == 0){
			alert("请填写课程名称!");
			return;
		}
		var peopleNum = $("#peopleNum").val();
		if($.trim(peopleNum).length == 0){
			alert("请填写限制人数!");
			return;
		}
		var salePrice = $("#salePrice").val();
		if($.trim(salePrice).length == 0){
			alert("请填写零售价!");
			return;
		}
		var classCount = $("#classCount").val();
		if($.trim(classCount).length == 0){
			alert("请填写课程次数!");
			return;
		}
		var signImg = $("#signImg").val();
		if($.trim(signImg).length == 0){
			alert("请上传图片!");
			return;
		}
		var isRefund = $("#isRefund").val();
		var fee = $("#fee").val();
		if($.trim(isRefund).length == 0){
			alert("请选择是否支持退款!");
			return;
		}
		if(isRefund == 1){
			if($.trim(fee).length == 0){
				alert("请填写退款手续费!");
				return;
			}
		}
		var memo = $("#memo").val();
		if($.trim(memo).length == 0){
			alert("请填写课程内容!");
			return;
		}
		$.ajax({
			   type: "post",
			   url: "/course/group/update.action",
			   data: $("#form").serialize(),
			   dataType : "json",
			   cache : false,
			   success: function(res){
				   if(res === undefined){
	    				alert("操作失败请稍后再试");
	    			}else{
	    				if(res.status == 0){
	    					alert("操作成功！");
	    					location.href="/course/group.action";
	    				}else{
	    					alert(res.msg);
	    				}
	    			}
			   },
			   error:function(res){
  					alert("网络加载失败，请稍后再试！");
  				}
		});
	};
	
	//表单提交(发布)
	$("#publish").click(function(){
		var cardName = $("#cardName").val();
		if($.trim(cardName).length == 0){
			alert("请填写课程名称!");
			return;
		}
		var peopleNum = $("#peopleNum1").val();
		if($.trim(peopleNum).length == 0){
			alert("请填写限制人数!");
			return;
		}
		var salePrice = $("#salePrice1").val();
		if($.trim(salePrice).length == 0){
			alert("请填写零售价!");
			return;
		}
		var classCount = $("#classCount1").val();
		if($.trim(classCount).length == 0){
			alert("请填写课程次数!");
			return;
		}
		var signImg = $("#signImg1").val();
		if($.trim(signImg).length == 0){
			alert("请上传图片!");
			return;
		}
		var isRefund =  $(".tuikuan").children("#isRefund").val();
		//alert(isRefund);
		var fee = $("#fee1").val();
		if($.trim(isRefund).length == 0){
			alert("请选择是否支持退款!");
			return;
		}
		if(isRefund == 1){
			if($.trim(fee).length == 0){
				alert("请填写退款手续费!");
				return;
			}
		}
		
		var sysCustId = $("#sysCustId").val();
		if($.trim(sysCustId).length == 0){
			alert("请选择发布分店!");
			return;
		}
		var date1 = $("#control_date").val();
		if($.trim(date1).length == 0){
			alert("请选择团课开始时间!");
			return;
		}
		var date2 = $("#control_date2").val();
		if($.trim(date2).length == 0){
			alert("请选择团课结束时间!");
			return;
		}
		if((new Date()) - (new Date(date1)) >= 0){
			alert("请正确选择时间!");
			return false;
		}
		if((new Date(date1)) -(new Date(date2)) >= 0){
			alert("请正确选择时间!");
			return false;
		}
		var coachIds = arr.join(","); 
		if($.trim(coachIds).length == 0){
			alert("请选择团课教练!");
			return;
		}
		var memo = $("#elm11").val();
		if($.trim(memo).length == 0){
			alert("请填写课程内容!");
			return;
		}
		$.ajax({
			   type: "post",
			   url: "/course/group/publish.action",
			   data: {cardName:cardName,peopleNum:peopleNum,salePrice:salePrice,classCount:classCount,signImg:signImg,
				   isRefund:isRefund,fee:fee,sysCustId:sysCustId,control_date:date1,control_date2:date2,elm1:memo,coachId:coachIds},
			   dataType : "json",
			   cache : false,
			   success: function(res){
				   if(res === undefined){
	    				alert("操作失败请稍后再试");
	    		   }else{
	    				if(res.status == 0){
	    					alert("操作成功！");
	    					location.href="/course/group.action";
	    				}else{
	    					alert(res.msg);
	    				}
	    		    }
			   },
			   error:function(res){
						alert("网络加载失败，请稍后再试！");
					}
			});
	});

</script>
<script type="text/javascript">
var arr = new Array();
$(function() {
	//团课列表发布框
	$(".setfabu").click(function(){
	    showShadow();
	    $(".push-syllabus").show();
	    
      	//分店列表
        $.ajax({
			 type:"post",
			 url:"/course/group/subbranchList.action",
			 dataType:"json",
			 cache:false,
			 success:function(res) {
        		for(var i=0;i<res.subbranchList.length;i++){
  					$('#chooseShop').append('<p id="'+res.subbranchList[i].system_id+'">'+res.subbranchList[i].cust_name+'</p>');
        		}
        		$(".choose_gev_shop p").click(function(){
                    var name = $(this).text();
                    var id1 = $(this).attr("id");
                    $(this).parents(".chooseshop").find(".chooseText").text(name);
                    $(this).parents(".chooseshop").find("#sysCustId").val(id1);
                })
			 },
			 error:function(resJson){
				 alert("系统异常，请稍后再试！");
			 }
		});
      	
      //教练列表
        $.ajax({
			 type:"post",
			 url:"/course/group/coachList.action",
			 dataType:"json",
			 cache:false,
			 success:function(res) {
        		for(var i=0;i<res.coachList.length;i++){
  					$('.joinbusiness').append('<option value="'+res.coachList[i].id+'">'+res.coachList[i].name+'</option>');
        		}
        		$('.joinbusiness').change(function(e){ 
        			var id = $(this).children('option:selected').val();
                    var name = $(this).children('option:selected').text();
                    if(name == "选择添加教练"){
                    	return;
                    }
                    if($.inArray(id, arr) == -1){
                    	arr.push(id);
                    }else{
                    	alert("您已经选择了此教练!");
                    	return;
                    }
                    var html = "<p id='"+id+"'>"+ name +"<i class='empty'></i></p>";
                    $('.joinbusiness').before(html);
                    //console.log(arr);
                	$('.empty').on('click',function(){
                		var pid = $(this).parents("").attr('id');
                		if($.inArray(pid,arr) > -1){
                			arr.splice($.inArray(pid,arr),1);
                		}
                		$(this).parent().remove();
                		//console.log(arr);
                	})
        		})
			 },
			 error:function(resJson){
				 alert("系统异常，请稍后再试！");
			 }
		});
	});
})
</script>
</body>
</html>