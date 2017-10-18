<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--
${list }--%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>课程列表</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />


<script type="text/javascript" src="/static/js/jquery.js"></script>

<script type="text/javascript" src="/static/js/base.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/star.js"></script>
<script type="text/javascript" src="/static/js/file.js"></script>
<script src="/static/third/fine-uploader/fine-uploader.js"></script>

<script type="text/template" id="qq-template-manual-trigger">

<%@ include file="/include/template-uploader-single.jsp" %>

</script>
<%@include file="/include/editor.jsp" %>


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
				<h3 class="i_title">课程管理</h3>
				<c:set var="items" value="${list}" />
				<c:if test="${fn:length(items)>0}">
					<div class="search_area">
						<div class="search-left">关键字</div>
						<div class="search-right">
							<input  id="searchword" class="searchword" value="${searchword }"/>
							<input id="search-a" type="button" class="search-btns" value="搜索"/>
						</div>
					</div>
					<div class="look-target">
						<span class="datu viewBar"><i class="fa fa-picture-o"></i></span>
						<span class="liebiao"><i class="fa fa-list-ul"></i></span>
					</div>
					<div class="branch_shop_box view_bigimg"
						style="display: ${type==0?'block':'none'};">
						<c:forEach var="item" items="${items}">
							<dl>
								<a href="detail.action?courseId=${item.id}&type=1"
									target="_blank">
									<dt>
										<img src="${item.classImg}" alt="${item.className}" />
									</dt>
									<dd class="syllabus_list_block">
										<div class="syllabus_name">${item.className}</div>
										<div class="syllabus_difficulty">
											难度：
											<c:if test="${item.courseRate==1}">
												<i style="background-position: 0 -166px;"></i>
											</c:if>
											<c:if test="${item.courseRate==2}">
												<i style="background-position: 0 -125px;"></i>
											</c:if>
											<c:if test="${item.courseRate==3}">
												<i style="background-position: 0 -84px;"></i>
											</c:if>
											<c:if test="${item.courseRate==4}">
												<i style="background-position: 0 -41px;"></i>
											</c:if>
											<c:if test="${item.courseRate==5}">
												<i style="background-position: 0 -1px;"></i>
											</c:if>
										</div>
									</dd>
								</a>
							</dl>
						</c:forEach>
					</div>
					<div class="clear_float"></div>
					<div class="view_list" style="display: ${type==1?'block':'none'};">
						<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
							<tr>
								<th bgcolor="#f3f5f6" height="36">课程名称</th>
								<th bgcolor="#f3f5f6" height="36">图片</th>
								<th bgcolor="#f3f5f6" height="36">难度</th>
								<th bgcolor="#f3f5f6" height="36">操作</th>
							</tr>
                    <c:forEach var="item" items="${items}">
                        <tr>
                        <td class="syllabus_list_name"> <a
                        href="detail.action?courseId=${item.id}&type=1"
                        target="_blank">${item.className}</a>
                        </td> <td><img src="${item.classImg}" alt="" width="120" /></td> <td
                        class="star"> <c:if test="${item.courseRate==1}">
                        <i style="background-position: 0 -166px;"></i>
                    </c:if> <c:if test="${item.courseRate==2}">
                        <i style="background-position: 0 -125px;"></i>
                    </c:if> <c:if test="${item.courseRate==3}">
                        <i style="background-position: 0 -84px;"></i>
                    </c:if> <c:if test="${item.courseRate==4}">
                        <i style="background-position: 0 -41px;"></i>
                    </c:if> <c:if test="${item.courseRate==5}">
                        <i style="background-position: 0 -1px;"></i>
                    </c:if>
                        </td> <td class="syllabus_list_opera"> <shiro:hasPermission
                            name="course/detail:update">
                        <a href="detail.action?courseId=${item.id}&type=1" target="_blank">修改课程</a>
                    </shiro:hasPermission> <a href="detail.action?courseId=${item.id}&type=2" target="_blank">预约报表</a> <a
                        href="detail.action?courseId=${item.id}&type=3" target="_blank">评价</a></td></tr>

                    </c:forEach>
						</table>

					</div>
					<div>

					</div>
				</c:if>
				<!-- 分页 开始 -->
				<div class="pager">
					<c:set var="pageUrl"
						value="/course/list.action?pageNo=pageno&type=${type}"
						scope="request" />
					<jsp:include flush="true" page="/include/pager.jsp" />
				</div>
				<!-- 分页 结束 -->
	<shiro:hasPermission name="course:add">
		<div class="btns-area">
		<a href="#" class="create_syllabus">新建课程</a>
		</div>
	</shiro:hasPermission>
			</div>
		</div>
	</div>
	<div class="clear_float"></div>

	<!--底部：开始-->
	<%--
	<jsp:include page="/include/footer.jsp" flush="true">
		<jsp:param name="title" value="1" />
	</jsp:include>--%>
	<!--底部：结束-->


	<div class="create_syllabus_box closebox" style="display: none">
		<h3 class="create_syllabus_title">新建课程</h3>
		<div class="create_syllabus_cont">
			<ul>
				<li><span class="create_syllabus_item_name">名称</span> <input
					type="text" class="create_syllabus_input" /></li>
				<li><span class="create_syllabus_item_name">难度</span>
					<div id="xzw_starSys">
						<div id="xzw_starBox">
							<ul class="star" id="star">
								<li><a href="javascript:void(0)" title="1" class="one-star">1</a></li>
								<li><a href="javascript:void(0)" title="2"
									class="two-stars">2</a></li>
								<li><a href="javascript:void(0)" title="3"
									class="three-stars">3</a></li>
								<li><a href="javascript:void(0)" title="4"
									class="four-stars">4</a></li>
								<li><a href="javascript:void(0)" title="5"
									class="five-stars">5</a></li>
							</ul>
							<div class="current-rating" id="showb" title=""
								style="width: 24px;"></div>
						</div>
					</div></li>
				<li>
					<!-- 添加图片 --> <span class="create_syllabus_item_name">图片</span>
					<div id="view"></div>
					<div id="fine-uploader-manual-trigger" style="width: 700px;"></div>
					<input id="imgurl" type="hidden" value="" /> <%--
					
					<div id="preview"></div> 
					<input type="file" onchange="previewImage(this)" style="border: none" />--%>
				</li>
				<li><span class="create_syllabus_item_name">课程内容</span> <%--
				 <textarea
						id="elm1" name="elm1" class="xheditor" rows="12" cols="80"
						style="width: 442px"></textarea>--%> <textarea id="form-content"
						class="editor" cols="30" rows="10"  style="width:442px; height:40px;"></textarea></li>
				<li><span class="create_syllabus_item_name">&nbsp;</span> <em
					class="create_syllabus_qx close">取消</em> <em
					class="create_syllabus_fs">保存</em></li>
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
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


		// 新建课程
		$(".create_syllabus").on("click", function() {
			showShadow();
			$(".create_syllabus_box").show();
		});

		// 取消关闭弹框
		$(".close").click(function() {
			$(this).parents(".closebox").hide();
			$("#shade").remove();
		});

		// 视图列表 大图查看方式
		$('.datu').click(function() {
			//console.log($('.pager').children()[1]);
			$(".view_bigimg").show();
			$(".view_list").hide();
			$(this).addClass("viewBar");
			$(".liebiao").removeClass("viewBar");

			$('.pager').children().each(function() {
				var url = $(this).children('a').attr('href');
				if (url != null && url != '' && url.indexOf('&') >= 0) {
					url = url.split('&')[0] + "&type=0";
				}
				$(this).children('a').attr('href', url);
			});
		});

		$('.liebiao').click(function() {
			$(".view_list").show();
			$(".view_bigimg").hide();
			$(this).addClass("viewBar");
			$(".datu").removeClass("viewBar");

			$('.pager').children().each(function() {
				var url = $(this).children('a').attr('href');
				if (url != null && url != '' && url.indexOf('&') >= 0) {
					url = url.split('&')[0] + "&type=1";
				}
				$(this).children('a').attr('href', url);
			});
		});

		//难度 记录星星的个数
		$('#star a').click(function() {
			$('#showb').attr('title', $(this).attr('title'));
		});

		//新建课程
		$('.create_syllabus_fs').click(
				function() {

					//alert();
					if ($('.create_syllabus_input').val() == ''
							|| $('.editor').val() == ''
							|| $('#showb').attr('title') == '') {
						alert("数据不能为空");
						return false;
					}

					if ($('#imgurl').val() == '') {
						alert("图片上传不成功，请重试！");
						return false;
					}

					$.ajax({
								type : "post",
								data : {
									action : 'cre',
									name : $('.create_syllabus_input').val(),
									rate : $('#showb').attr('title'),
									img : $('#imgurl').val(),
									desc : $('.editor').val()
								},
								url : "/course/detail/create.action?r="
										+ Math.random(),
								dataType : "json",
								success : function(res) {
									if (res.status == 1) {
										alert('新建成功');
									} else {
										alert('新建失败');
									}
									window.location.reload();
								},
								error : function(res) {
									alert("网络异常，请稍后再试");
								}
							});

				});

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
							console.log("onUpload-----id:" + id + "----name:"
									+ name);
						},
						onSubmitted : function(id, name) {
							console.log("onSubmitted-----id:" + id
									+ "----name:" + name);
						},
						onComplete : function(id, name, responseJSON, maybeXhr) {
							console.log("onComplete-----id:" + id + "----name:"
									+ name);
							console.log("onComplete-----url:"
									+ responseJSON.url);
							//$("#view").append(responseJSON.url+"<br/>");
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

	});

	$("#searchword").focus(function(){
		 $(this).val('');
	});
	$('#search-a').click(function(){

		window.location.href="/course/list.action?searchword="+$('#searchword').val();

	});
</script>

</html>