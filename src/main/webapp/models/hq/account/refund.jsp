
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
  <title>退款报表</title>
    <link type="text/css" rel="stylesheet" href="/static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/left.css" />
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/base.js"></script>
    <!-- <script type="text/javascript" src="/static/js/custom.js"></script> -->
    <script type="text/javascript" src="/static/js/star.js"></script>
    <script type="text/javascript" src="/static/js/left.js"></script>
    <script type="text/javascript" src="/static/js/file.js"></script>
    <script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
</head>
<script type="text/javascript">
function  conditionQuery(){
	
	var sysCustId=$("#sysCustId").find("option:selected").val();
	var type=$("#type").find("option:selected").val();
	if(sysCustId!=='' && sysCustId !=null && sysCustId!=undefined){
		sysCustId=encodeURI(encodeURI(sysCustId));
		}
	if(sysCustId=='请选择'){
		sysCustId='';
	} 
	var type = $("#type").find("option:selected").val();
	window.location.href="/account/refund/report.action?type="+type+"&sysCustId="+sysCustId;
}

</script>
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
				<jsp:param name="nvaIndex" value="32" />
					    <jsp:param name="nvaParentIndex" value="25" />
			</jsp:include> 
			<!--左侧导航：结束-->
	  <div class="rightContent">
            <h3 class="i_title">退款报表</h3>
            <div class="search_area">
                <div class="member_item_list">
                    <span class="member_item_title">分店</span>
                        <select id="sysCustId" name="select"  class="itemSelect" >
						    <option value="" selected="selected"  >-请选择-</option>
		                     <c:forEach var="map" items="${custList}">
		                   <option value="${map.system_id}" ${sysCustId eq  map.system_id
		                   ? "selected" : "" } >${map.cust_name}</option>
		                     </c:forEach>
				  </select>
                </div>
                <div class="member_item_list">
                    <span class="member_item_title">类型</span>
                    <select id="type" class="itemSelect"   >
			        	<option value="-1" ${type eq -1 ? "selected" :""}>-请选择-</option>
			         	<option value="10" ${type eq 10 ? "selected" :""}>卡</option>
			            <option value="11"  ${type eq 11 ? "selected" :""}>收费团课</option>
			           	<option value="12" ${type eq 12 ? "selected" :""}>课程</option>
			           	<option value="13" ${type eq 13 ? "selected" :""}>私教</option>
        			</select>
                </div>
                  <a href="javascript:;" class="txBtn"  id="pushButton" data-custid="115798" onclick="conditionQuery()" >搜索</a>
            </div>
            <div class="member_item_table">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
					<tr>
						<th bgcolor="#f3f5f6" height="36">订单号</th>
						<th bgcolor="#f3f5f6" height="36">分店</th>
						<th bgcolor="#f3f5f6" height="36">类型</th>
						<th bgcolor="#f3f5f6" height="36">产品号</th>
						<th bgcolor="#f3f5f6" height="36">数量</th>
						<th bgcolor="#f3f5f6" height="36">会员</th>
						<th bgcolor="#f3f5f6" height="36">购买时间</th>
						<th bgcolor="#f3f5f6" height="36">退款时间</th>
						<th bgcolor="#f3f5f6" height="36">操作人</th>
						<th bgcolor="#f3f5f6" height="36">建议金额</th>
						<th bgcolor="#f3f5f6" height="36">实际金额</th>
						<th bgcolor="#f3f5f6" height="36">操作</th>
					</tr>

							 <c:forEach  var="refund"  items="${reFund }">
							<tr>
								<td id="id" class="w98">${refund.id}</td>
								<td id="custName" class="w119">${funcs:getCustNameByCustId(refund.sysCustId )}</td>
								<td id="orderType" class="w109">${refund.orderType eq "10" ? "卡" :(refund.orderType eq "11" ? "收费团课" : (refund.orderType eq "12"  ? "课程" : "私教"))}</td>
								<td id="productId" class="w109">
									<c:if test="${refund.orderType == 10}">${funcs:getProductNameByProductId(refund.productId +"")} </c:if>
									<c:if test="${refund.orderType == 11}">${funcs:getProductNameByProductId(refund.productId +"")}</c:if>
									<c:if test="${refund.orderType == 12}">${funcs:getCourseNameByProductId(order.product_id +"")}</c:if>
									<c:if test="${refund.orderType == 13}">${funcs:getCoachNameById(refund.productId )}</c:if></td>
								<td id="num" class="w59">${refund.num }</td>
								<td id="linkMan"  class="w99">${refund.linkMan }</td>
								<td id="creteTime" class="w149"><fmt:formatDate value="${refund.creteTime }" pattern="yyyy-MM-dd"/></td>
								<td id="refundTime" class="w149"><fmt:formatDate value="${refund.refundTime }" pattern="yyyy-MM-dd"/></td>
								<td id="refundUser"  class="w79">${refund.refundUser }</td>
								<td id="refundMoney"   class="w89"  ><c:if test="${! empty refund.refundMoney}">${refund.refundMoney/100 }</c:if></td>
								<td id="actualMoney"  class="w89">${refund.actualMoney/100 }</td>
								<input  type="hidden"  id="linkPhone" value="${refund.linkPhone }">
								<input  type="hidden"  id="memPrice" value="${refund.memPrice }" >
								<input  type="hidden"  id="remainCount" value="${refund.remainCount }">
								<input  type="hidden"  id="orderCount" value="${refund.orderCount }">
								 <input  type="hidden"  id="orderId" value="${refund.id }">
								<c:if test="${refund.status == 6 }"><td class="operacont"><font class="j_tuikuan"  color="blue">退款</font></td></c:if>
								<c:if test="${refund.status == 5 }"><td class="operacont">已退款</td></c:if>
							</tr>
							</c:forEach>

				</table>
            </div>
 					<!-- 分页 开始 -->
						<div class="pager">
							<c:set var="pageUrl"
								value="/account/refund/report.action?pageNo=pageno&type=${type}"
								scope="request" />
							<jsp:include flush="true" page="/include/pager.jsp" />
						</div>
					<!-- 分页 结束 -->
            <div class="clear_float"></div>
        </div>
    </div>
</div>
<div class="refund-apply closebox" style="height: 540px; margin-top: -270px;">
    <h3 class="create_syllabus_title">退款</h3>
    <div class="refund-apply-box wid360">
        <ul class=""  >
            <li><span>姓名：</span><em id="linkMan1">xiaolin</em></li>
            <li><span >电话：</span><em id="linkPhone1">187376432874</em></li>
            <li><span >购买时间：</span><em id="creteTime1">普拉提</em></li>
            <li><span>使用店铺：</span><em id="custName1">30</em></li>
            <li><span>已上课时：</span><em id="already1">290</em></li>
            <li><span>购买价：</span><em id="memPrice1">260</em></li>
            <li><span>剩余课时：</span><em id="remainCount1">260</em></li>
            <li><span>手续费：</span><em id="cha">260</em></li>
            <li><span>建议金额：</span><em id="refundMoney1">260</em></li>
            <li><input  type="hidden"  id="orderId1"  ></li>
            <li><span>实际退款：</span><input id="actualMoney1" type="text" class="refund"> 元</li>
        </ul>
        <div class="refund_btns">
            <span class="create_syllabus_qx close">取消</span>
            <span class="confirm">申请退款</span>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
$(function(){
//取消关闭弹框
$(".close").click(function(){
    $(this).parents(".closebox").hide();
    $("#shade").remove();
});
$(".j_tuikuan").click(function(){
	$(".refund-apply").show();
   showShadow();
    var id = $(this).parent().parent().children('#id').text();
	var linkMan=$(this).parent().parent().children("#linkMan").text();
	var linkPhone=$(this).parent().parent().children("#linkPhone").val();
	var creteTime=$(this).parent().parent().children("#creteTime").text();
	var custName=$(this).parent().parent().children("#custName").text();
	var orderCount=$(this).parent().parent().children("#orderCount").val();
	var memPrice=$(this).parent().parent().children("#memPrice").val();
	var remainCount=$(this).parent().parent().children("#remainCount").val();
	var refundMoney=$(this).parent().parent().children("#refundMoney").text();
	var actualMoney=$(this).parent().parent().children("#actualMoney").val();
	
	if(memPrice==null){
		memPrice=0;
	}
	if(remainCount==null){
		remainCount=0;
	}
	$("#orderId1").val(id);
	$("#linkMan1").text(linkMan);
	$("#linkPhone1").text(linkPhone);
	$("#creteTime1").text(creteTime);
	$("#custName1").text(custName);
	$("#already1").text(orderCount-remainCount);
	$("#memPrice1").text(memPrice);
	$("#remainCount1").text(remainCount);
	$("#cha").text(refundMoney-actualMoney);
	$("#refundMoney1").text(refundMoney);
});
$(".confirm").click(function(){
	var actualMoney=$("#actualMoney1").val();
	var orderid=$("#orderId1").val();
	
	$.ajax({
		url:'/coach/updateRefund.action',
		data:{"orderid":orderid,"actualMoney":actualMoney},
		type:'post',
		dataType:"json",
	    cache:false,
	    success:function(res) {
	    	 if(res === undefined){
	 				alert("操作失败请稍后再试");
	 			}else{
	 				if(res.status == 0){
	 					alert("操作成功！");
	 					top.location.href="/account/refund/report.action";
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
</script>

</html>		

			