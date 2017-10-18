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
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/star.js"></script>
<script type="text/javascript" src="/static/js/file.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>
<script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
<script src="/static/third/fine-uploader/fine-uploader.js"></script>
<script type="text/template" id="qq-template-manual-trigger">
 <%@ include file="/include/template-uploader.html" %>
</script> 

<script type="text/javascript">


var id='';

var num = 0;

 function selected(thisObj){
   var coachId=$(thisObj).attr("data-id");
   var name=$(thisObj).attr("data-name");
 	// alert(coachId);
   //	alert(name); 
	$(thisObj).toggleClass("addborder");

 }
</script>
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
					<h3 class="i_title">
						教练管理
					</h3>
					<div class="province"></div>
					<div class="coach_list" >
					  <c:set var="coachList" value="${coachList1}" ></c:set>

					  <c:if test="${fn:length(coachList)>0}">
						<c:forEach  items="${coachList}"  var="info">
							<c:set  value="${info.id}"  var="id"/>
							<dl>
							<a  href="/coach/detail.action?id=${info.id}&coachType=${info.coachType}" target="_blank" >
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
						<a href="#" class="create_syllabus">添加教练</a>
					</div>
				</div>
		</div>
      </div>
   </div>
   
<div class="create_syllabus_box closebox" style="display: none">
    <h3 class="create_syllabus_title">添加教练</h3>
    <div  id="testDiv">
    <div class="branch_caoch_cont">
        <div class="branch_coach_list">
             <c:set var="coachAll" value="${coachList2}" ></c:set>
			   <c:if test="${fn:length(coachAll)>0}">    
                <c:forEach  items="${coachAll}"  var="all">
                	  <dl> 
                    	<dt   class="cdd">
                    	<input  type="hidden"  id="id"  value="${all.id}">
                    	<input type="hidden" id="useCustId" value="${all.use_cust_id}">
                    	<c:forEach var="listId"  items="${coachList}"/>
                        <img src="${all.sign_img }" alt=""  data-id='${all.id}' data-name='${all.use_cust_id}' 
	               		 onclick="selected(this);"/> 
                        <c:if test="${all.coach_type==1 }">
                        <span class="sijiao">
                         <img src="/static/images/sijiao.png" alt=""/>
                        </span>
                        </c:if>
                    	</dt>
                    <dd>${all.name}</dd>
                    <!-- //如果是门店的就显示选中 -->
                 	</dl> 
	               	</c:forEach>
	              </c:if> 	 
        </div>
        </div>
        </div>
        <div style="clear: both; text-align: center">
            <em class="create_syllabus_qx close">取消</em>
            <em id="submit1"  class="create_syllabus_fs">确定</em>
        </div>
    </div>


</body>
<style>
.pager{
	clear:both;
	padding: 0;
	margin-bottom: 10px;
}
.branch_coach_list dl {
    float: left;
    margin: 0 10px 15px 8px;
    position: relative;
}
	.branch_coach_list dl dt img.addborder{
		border:1px solid #f00;
	}
	.branch_coach_list dl dt img {
    width: 142px;
    height:71px;
    border:1px solid #e5e5e5;
}

.dlcur {
    position: absolute;
    background: url("/static/images/huangse.png");
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;
    z-index: 1001;
}


</style>
<script type="text/javascript">
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
	$("#submit").click(function(){
		alert(1);
		var name=$("#name").val();
		var mobile=$("#mobile").val();
		var openImg=$("#openImg").val();
		var signImg=$("#signImg").val();
		var coachType= $("input[name='coachType']:checked").val();//0非私教1私教
		var fee=$("#fee").val();
		var salePrice=$("#salePrice").val();
		var sysCustId=$("#sysCustId").val();
		alert(sysCustId);
		var proDesc=$("#proDesc").val();
		
		$.ajax({
			type:'post',
		    data:{"name":name,"mobile":mobile,"openImg":openImg,"signImg":signImg,
		    	"coachType":coachType,"fee":fee,"salePrice":salePrice,"sysCustId":sysCustId,
		    	"proDesc":proDesc},
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
	$("#submit1").click(function(){
		var data=new  Array();
		$("img[class='addborder']").each(function(){
			var dataId=$(this).attr("data-id");
			if(dataId != undefined || dataId == ''){
    			data.push(dataId);
    		}
		});
		//根据id修改useCustId
     	$.ajax({
			 type:"post",
			 data:{"data":data.join(",")},
			 url:"/coach/andbranch.action",
			 dataType:"json",
			 cache:false,
			 beforeSend:function(xhr){
					//$("#loading").show();
			 },
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
		 });
	 
		});
})


</script>

</html>