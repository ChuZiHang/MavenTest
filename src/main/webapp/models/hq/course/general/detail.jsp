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
<title>课程详情</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>

<script type="text/javascript" src="/static/js/base.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
<script type="text/javascript" src="/static/js/star.js"></script>
<script src="/static/third/fine-uploader/fine-uploader.js"></script>
<script type="text/template" id="qq-template-manual-trigger">
<%@ include file="/include/template-uploader-single.jsp" %>
</script>
<%@include file="/include/editor.jsp" %>




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
		<jsp:param name="nvaIndex" value="15" />
		<jsp:param name="nvaParentIndex" value="14" />
	</jsp:include>
	<!--左侧导航：结束-->
			<div class="rightContent">
			<h3 class="i_title">课程详情</h3>
			<div class="province" style="height:25px;"></div>
			<div class="item_syllabus_list_tag">
				<c:set var="type" value="${type}" />
				<span class="${(type==1) ? 'item_syllabus_list_tag_lie':'noton'}"><a
					href="detail.action?courseId=${courseId }&type=1">课程信息</a></span> <span
					class="${(type==2) ? 'item_syllabus_list_tag_lie':'noton'}"><a
					href="detail.action?courseId=${courseId }&type=2">预约信息</a> </span> <span
					class="${(type==3) ? 'item_syllabus_list_tag_lie':'noton'}"><a
					href="detail.action?courseId=${courseId }&type=3">评价</a></span>
			</div>
			<c:choose>
				<%------------------------- 总店------------------ --%>
				<c:when test="${parentType==1}">
					<div class="syllabus_detail_cont">
						<div class="create_syllabus_cont syllabus_detail_show">
							<ul>
								<li><span class="create_syllabus_item_name">名称</span> <input
									type="text" class="create_syllabus_input"
									value="${courseInfo.className}"></li>
								<li><span class="create_syllabus_item_name">难度</span>
									<div id="xzw_starSys">
										<div id="xzw_starBox">

											<ul class="star" id="star">
												<li><a href="javascript:void(0)" title="1"
													class="one-star">1</a></li>
												<li><a href="javascript:void(0)" style="background:"
													title="2" class="two-stars">2</a></li>
												<li><a href="javascript:void(0)" title="3"
													class="three-stars">3</a></li>
												<li><a href="javascript:void(0)" title="4"
													class="four-stars">4</a></li>
												<li><a href="javascript:void(0)" title="5"
													class="five-stars">5</a></li>
											</ul>

											<div class="current-rating" id="showb"
												title="${courseInfo.courseRate}" style="width: 24px;"></div>
										</div>
									</div></li>

								<li style="height: 100%; overflow: hidden; clear: both;"><span
									class="create_syllabus_item_name">图片</span>
									<div id="preview" style="float: left;">
										<img src="${courseInfo.classImg }" alt="" />
									</div>
									<div id="view"></div>
									<div id="fine-uploader-manual-trigger" style="width: 700px;"></div>
									<input id="imgurl" value="${courseInfo.classImg }"
									type="hidden"></li>
								<li><span class="create_syllabus_item_name">课程内容</span> 
								<textarea id="form-content" class="editor" cols="30" rows="10"  style="width:442px; height:40px;">${courseInfo.classDesc}</textarea>
								
								<%-- 
								<textarea
										id="elm1" name="elm1" class="xheditor" rows="12" cols="80"
										style="width: 442px">${courseInfo.classDesc}</textarea>
										--%></li>
								<li id="${courseInfo.id }"><span
									class="create_syllabus_item_name">&nbsp;</span> <em
									class="create_syllabus_qx setrecord">修改记录</em> <shiro:hasPermission
										name="course/detail:update">
										<em class="create_syllabus_fs">保存修改</em>
									</shiro:hasPermission> <em class="create_syllabus_sc">删除课程</em></li>
							</ul>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<%------------------------- 分店------------------ --%>
					<div class="syllabus_detail_cont">
						<div class="create_syllabus_cont syllabus_detail_show">
							<ul>
								<li><span class="create_syllabus_item_name">名称</span> <input
									type="text" class="create_syllabus_input"
									value="${courseInfo.className}" disabled></li>
								<li><span class="create_syllabus_item_name">难度</span>
									<div id="xzw_starSys">
										<div id="xzw_starBox">
											<ul class="star" id="star">
												<li></li>
												<li></li>
												<li></li>
												<li></li>
												<li></li>
											</ul>
											<div class="current-rating" id="showb"
												title="${courseInfo.courseRate}" style="width: 120px;"></div>
										</div>
									</div></li>
								<li><span class="create_syllabus_item_name">图片</span>
									<div id="preview">
										<img src="${courseInfo.classImg}" alt="" />
									</div></li>
								<li><span class="create_syllabus_item_name">课程内容</span> <textarea
										id="elm1" name="elm1" class="xheditor" rows="12" cols="80"
										style="width: 442px" disabled>${courseInfo.classDesc}</textarea>
									<%@include file="/include/editor.jsp" %>
								    <script>
								    /** Simple customization with current options **/
							        $('#elm1').trumbowyg({
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
				</c:otherwise>
			</c:choose>
		</div>
		</div>
	</div>

	<div id="Popup" class="closebox">
		<div id="PopupText">确定要删除吗？分店已安排的课程也会被删除！</div>
		<div id="PopupBtn">
			<span class="PopupBtnyes">确定</span><span class="PopupBtnqx close">取消</span>
		</div>
	</div>




<div class="setRecordBox closebox">
    <h3 class="create_syllabus_title">修改记录</h3>
    <div class="setRecordBox_list">
        <ul id="testDiv">
            <c:forEach items="${logs }" var="bean" >
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


	<script>
		// 自定义竖滚动条
		$(function() {
			
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
			
			
			
			$('#testDiv').slimScroll({
				alwaysVisible : true
			});

			$('#star a').click(function() {
				$('#showb').attr('title', $(this).attr('title'));
			});

			// 修改记录弹出框
			$(".setrecord").click(function() {
				showShadow();
				$(".setRecordBox").show();
			});

			// 取消关闭弹框
			$(".close").click(function() {
				$(this).parents(".closebox").hide();
				$("#shade").remove();
			});

			//保存修改
			$('.create_syllabus_fs').click(function() {

				//alert();
				$.ajax({
					type : "post",
					data : {
						action : 'upd',
						courseId : $(this).parent().attr('id'),
						name : $('.create_syllabus_input').val(),
						rate : $('#showb').attr('title'),
						img : $('#imgurl').val(),
						desc : $('.editor').val()
					},
					url : "/course/detail/update.action?r=" + Math.random(),
					dataType : "json",
					success : function(res) {
						if (res.status == 1) {
							alert('修改成功');
						} else {
							alert('修改失败');
						}
						window.location.reload();
					},
					error : function(res) {
						alert("网络异常，请稍后再试");
					}
				});

			});

			var COURSE_ID = '';

			//点击删除按钮 弹出确认框
			$(".create_syllabus_sc").click(function() {
				showShadow();
				$("#Popup").show();
				COURSE_ID = $(this).parent().attr('id');
			});

			//删除课程
			$('.PopupBtnyes').click(function() {

				$.ajax({
					type : "post",
					data : {
						action : 'del',
						courseId : COURSE_ID
					},
					url : "/course/detail/update.action?r=" + Math.random(),
					dataType : "json",
					success : function(res) {
						if (res.status == 1) {
							//alert('删除成功');
						} else {
							alert('删除失败');
						}
						window.location.href = "list.action";
					},
					error : function(res) {
						alert("网络异常，请稍后再试");
					}
				});

			});

			//难度 五角星的回显
			var stars = [ '', '24px', '48px', '72px', '96px', '120px' ];
			$('#showb').css('width', stars[$('#showb').attr('title')]);

			//上传图片
			var manualUploader = new qq.FineUploader(
					{
						element : document
								.getElementById('fine-uploader-manual-trigger'),
						template : 'qq-template-manual-trigger',
						multiple : false,
						request : {
							/* endpoint: '/upload.action' */
							endpoint : '/uploads',
							params : {
								param1 : '测试参数'
							}
						},
						deleteFile : {
							enabled : true,
							forceConfirm : true,
							method : "POST",
							endpoint : "/uploads",
							params : {}
						},
						retry : {
							enableAuto : true
						},
						thumbnails : {
							placeholders : {
								waitingPath : '/static/third/fine-uploader/placeholders/waiting-generic.png',
								notAvailablePath : '/static/third/fine-uploader/placeholders/not_available-generic.png'
							}
						},
						validation : {
							allowedExtensions : [ 'jpeg', 'jpg', 'gif', 'png' ],
							sizeLimit : 2097152
						//2M 1024*1024*2
						},
						autoUpload : true,
						debug : true,
						messages : {
							typeError : "{file} has an invalid extension. Valid extension(s): {extensions}.",
							sizeError : "{file} is too large, maximum file size is {sizeLimit}.",
							minSizeError : "{file} is too small, minimum file size is {minSizeLimit}.",
							emptyError : "{file} is empty, please select files again without it.",
							noFilesError : "No files to upload.",
							tooManyItemsError : "Too many items ({netItems}) would be uploaded.  Item limit is {itemLimit}.",
							maxHeightImageError : "Image is too tall.",
							maxWidthImageError : "Image is too wide.",
							minHeightImageError : "Image is not tall enough.",
							minWidthImageError : "Image is not wide enough.",
							retryFailTooManyItems : "Retry failed - you have reached your file limit.",
							onLeave : "The files are being uploaded, if you leave now the upload will be canceled.",
							unsupportedBrowserIos8Safari : "Unrecoverable error - this browser does not permit file uploading of any kind due to serious bugs in iOS8 Safari.  Please use iOS8 Chrome until Apple fixes these issues."
						},
						callbacks : {
							onUpload : function(id, name) {
								console.log("onUpload-----id:" + id
										+ "----name:" + name);
							},
							onSubmitted : function(id, name) {
								console.log("onSubmitted-----id:" + id
										+ "----name:" + name);
							},
							onComplete : function(id, name, responseJSON,
									maybeXhr) {
								console.log("onComplete-----id:" + id
										+ "----name:" + name);
								console.log("onComplete-----url:"
										+ responseJSON.url);
								//$("#view").append(responseJSON.url+"<br/>");
								$('#preview').remove();
								//$('#preview').html("<span class='create_syllabus_item_name'>图片</span>");
								$('#imgurl').val(responseJSON.url);
							},
							onAllComplete : function(successful, failed) {
								console.log("onAllComplete-----successful:"
										+ successful + "----failed:" + failed);
								//manualUploader.reset();
							}
						}
					});

			qq(document.getElementById("trigger-upload")).attach("click",
					function() {
						manualUploader.uploadStoredFiles();
					});

		})
	</script>
</body>

</html>