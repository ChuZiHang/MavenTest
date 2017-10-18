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
  <title>兑换码列表</title>
    <link type="text/css" rel="stylesheet" href="/static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/left.css"/>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/base.js"></script>
    <script type="text/javascript" src="/static/js/left.js"></script>
  <!--  <script type="text/javascript" src="/static/js/custom.js"></script> -->
    <script type="text/javascript" src="/static/js/star.js"></script>
    <script type="text/javascript" src="/static/js/Calendar.js"></script>
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
				<jsp:param name="nvaIndex" value="29" />
			    <jsp:param name="nvaParentIndex" value="23" />
			</jsp:include> 
			<!--左侧导航：结束-->

			   <div class="rightContent">
            <h3 class="i_title">兑换码列表</h3>
                <div class="search_area">
                <div class="search-list-item">
					<div class="search-left">状态</div>
					<div class="search-right"><select id="codestatus"  class="jh">
						<option value="-1">
						全部
						</option>
						<option <c:if test="${codestatus eq 0}">selected="selected"</c:if>  value="0">
						有效
						</option>
						<option <c:if test="${codestatus eq 1}">selected="selected"</c:if> value="1">
						作废
						</option>
						</select>
					</div>
                </div>
				<div class="search-list-item">
					<div class="search-left">卡类型</div>
					<div class="search-right">
						<input id="codeName" value="${codeName}" type="text" class="searchword"/>
						<input type="button" onclick="search();" value="搜索" class="search-btns"/>
					</div>
				</div>
                </div>
                   <div class="cardList">
	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
		<tr class="exchange_title">
			<th bgcolor="#f3f5f6" height="36">卡类型</th>
			<th bgcolor="#f3f5f6" height="36">生成时间</th>
			<th bgcolor="#f3f5f6" height="36">数量</th>
			<th bgcolor="#f3f5f6" height="36">有效期</th>
			<th bgcolor="#f3f5f6" height="36">使用状态</th>
			<th bgcolor="#f3f5f6" height="36">操作</th>
		</tr>
	<c:forEach var="code" items="${codeCard}">
		<tr>
		<td>${code.id}${code.cardName}</td>
		<fmt:formatDate value="${code.createTime}" var="createTime" pattern="yyyy年MM月dd日"/>
		<td>${createTime}</td>
		<td>${code.num}</td>
		<td>
		<fmt:formatDate value="${code.startTime}" var="startTime" pattern="yyyy年MM月dd日"/>
		<em>开始：${startTime}</em>
		<fmt:formatDate value="${code.endTime}" var="endTime" pattern="yyyy年MM月dd日"/>
		<br />
		<em>结束：${endTime}</em>
		</td>
		<td>使用:${code.useNum}<br/>作废:${code.cancelNum}</td>
		<td>
		<shiro:hasPermission name="membercard/pcode:listDelete">
			<c:if test="${code.status eq '0'}">    <em><a href="javascript:void(0)"  onclick="deleteCode(this)"  data-code="${code.id}">全部作废</a></em></c:if>
		</shiro:hasPermission>
		<shiro:hasPermission name="membercard/pcode:detail">
			<em><a target="_Blank" href="/membercard/pcode/detail.action?codeId=${code.id}">详情</a></em>
		</shiro:hasPermission>
		<shiro:hasPermission name="membercard/pcode:report">
			<em><a href="/membercard/pcode/report.action?codeId=${code.id}">导出</a></em>
		</shiro:hasPermission>
		</td>
		</tr>
	</c:forEach>
	</table>
                     <!-- 分页 开始 -->
 				<div class="pager">
					<c:set var="pageUrl"
						value="/membercard/pcode/list.action?codestatus=${codestatus}&codeName=${codeName}&pageNo=pageno"
						scope="request" />
					<jsp:include flush="true" page="/include/pager.jsp" />
				</div>
				</div> 
			<!-- 分页 结束 -->
            <div class="clear_float"></div>
			<div class="btns-area">
				<shiro:hasPermission name="membercard/pcode:add">
					<a href="javascript:void(0)" class="create_card">生成兑换码</a>
				</shiro:hasPermission>
			</div>
        </div>
    </div>
</div>
<div class="create_card_cont closebox" style="display: none">
    <h3 class="create_syllabus_title">生成兑换码</h3>
    <div class="create_syllabus_cont">
        <ul>
            <li class="j_sijiao">
                <span class="create_syllabus_item_name">会员卡类型</span>
                <div class="choosemerber">
                   <span id="cardId" data-id="" class="chooseText"></span>
                     <i></i>
                    <div class="choosemerber_shop">
                      <c:forEach var="card" items="${cardList}">
                      <p id="${card.id}">${card.cardName}</p>
                      </c:forEach>
                    </div>
                </div>
            </li>
            <li>
                <span class="create_syllabus_item_name">生成数量</span>
                <input id="num" name="num" type="text" class="create_syllabus_input wid320" value="">
            </li>
            <li>
                <span class="create_syllabus_item_name">用户兑换数</span>
                <input id="allow" name="allow" type="text" class="create_syllabus_input wid320" value="">
            </li>
            <li>
                <span class="create_syllabus_item_name">有效期</span>
                <div><input name="control_date" type="text" id="control_date" class="start_date" onclick="new Calendar().show(this);" readonly="readonly" style="border: 1px solid rgb(228, 228, 228);"></div>
                <div><input name="control_date" type="text" id="control_date" class="end_date" onclick="new Calendar().show(this);" readonly="readonly" style="border: 1px solid rgb(228, 228, 228);"></div>
            </li>
            <li>
                <span class="create_syllabus_item_name">&nbsp;</span>
                <em class="create_syllabus_qx close">取消</em>
                <em id="submit" class="create_syllabus_fs">确定</em>
            </li>
        </ul>
    </div>
</div>
<script>
    $(function(){
        
     // 取消关闭弹框
        $(".close").click(function(){
            $(this).parents(".closebox").hide();
            $("#shade").remove();
        });
        
        $(".chooseshop").click(function(){
            $(this).find(".choose_gev_shop").toggle();
        });
        $(".choosemerber").click(function(){
            $(".choosemerber_shop").toggle();
        });
     
     
        // 新建会员卡弹框
        $(".create_card").click(function(){
            showShadow();
            $(".create_card_cont").show();
        });
         
        $(".choosemerber_shop p").click(function(){
   	        var text = $(this).text();
   	        var id = $(this).attr("id");
   	     
   	        $(this).parents(".choosemerber").find(".chooseText").text(text);
   	        $(this).parents(".choosemerber").find(".chooseText").attr("data-id",id);
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
	   	  var cardName = $("#cardId").text();
	      var cardId = $("#cardId").attr("data-id");
	      if(cardName == '' || cardId == ''){
	   		  alert("请选择卡类型");
	   		  return;
	   	  }
   		
	   	  var num = $("#num").val();
	   	 if(num == ''){
	   		  alert("请填写兑换码数量");
	   		  return;
	   	  }
	   	 
	   	 var allow = $("#allow").val();
	   	 if(allow == ''){
	   		  alert("请填写允许用户兑换数量");
	   		  return;
	   	  }
	   	 
	       if (!/^([1-9][\d]{0,2}|0)?$/.test(num)) {
            alert("请填写正确的兑换码数量且小于1000!");
            return;
           }
   	      var startDate = $(".start_date").val();
  	   	  var endDate = $(".end_date").val();
     		if($.trim(startDate).length == 0){
     			alert("请填写兑换码有效开始日期！");
     			return;
     		}
     		if($.trim(endDate).length == 0){
     			alert("请填写兑换码有效结束日期");
     			return;
     		}
     		 startTime = toDate(startDate);
     		 endTime= toDate(endDate);
     		if(startTime.getTime() > endTime.getTime()){
     			alert("有效开始日期不能大于有效结束日期");
     			return;
     		}
   	      if(new Date(startDate.replace("-","/")) < new Date().setHours(0, 0, 0, 0)){
			alert("有效开始日期不能是过去时间段！");
			return;
		  }
	       		 $.ajax({
						 type:"post",
						 data:{"startDate":startDate,"endDate":endDate,"num":num,"cardName":cardName,"cardId":cardId,"allow":allow},
						 url:"/membercard/pcode/add.action",
						 dataType:"json",
						 cache:false,
						 beforeSend:function(xhr){
								//$("#loading").show();
						 },
						 success:function(res) {
							if(res.result == num){
								alert("兑换码生成成功！");
								location.href="/membercard/pcode/list.action";
							}else{
								alert("兑换码生成失败！");
							}
						 },
						 error:function(resJson){
							 alert("系统异常，请稍后再试！");
						 }
					 });
		
         }
   	 
        
   	 function toDate(str){
    	 var sd = str.substring(0, 10).split('-');
            return new Date(sd[0],sd[1],sd[2]);
            }   	 
   	 
   	 
   	 
   	 
        
    });
    
    function search(){
    	var codestatus = $("#codestatus").val();
    	var codeName = $("#codeName").val();
    	if(codeName !== null &&  codeName !== '' && codeName !== undefined){
    		//get请求的中文进行编码
            keyword=encodeURI(encodeURI(codeName));
    	}
    	
    	location.href="/membercard/pcode/list.action?codestatus="+codestatus+"&codeName="+codeName;

    }
    
	   function deleteCode(th){
		   if(confirm("确定要全部作废吗?")){
	   		var id=$(th).attr("data-code");
	   		$.ajax({
				 type:"post",
				 data:{"codeId":id},
				 url:"/membercard/pcode/listDelete.action",
				 dataType:"json",
				 cache:false,
				 beforeSend:function(xhr){
						//$("#loading").show();
				 },
				 success:function(res) {
					if(res.result == "1"){
						alert("作废成功！");
						location.href="/membercard/pcode/list.action";
					}else{
						alert("作废失败！");
					}
				 },
				 error:function(resJson){
					 alert("系统异常，请稍后再试！");
				 }
			 });
		   }
	   	   }
</script>

</body>

</html>