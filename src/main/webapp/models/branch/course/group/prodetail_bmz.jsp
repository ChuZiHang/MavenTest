<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>收费团课</title>
    <link type="text/css" rel="stylesheet" href="/static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/left.css"/>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/left.js"></script>
    <script type="text/javascript" src="/static/js/base.js"></script>
    <script type="text/javascript" src="/static/js/star.js"></script>
    <script type="text/javascript" src="/static/js/file.js"></script>
    <script type="text/javascript" src="/static/js/Calendar.js"></script>
    <script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
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
        <div class="rightContent">
            <h3 class="i_title">收费团课</h3>
            <div class="province" style="height:25px;"></div>
            <div class="item_syllabus_list_tag">
                <span class="item_syllabus_list_tag_lie"><a href="javascript:void(0)">课程信息</a></span>
                <span><a href="javascript:void(0)">报名人信息</a></span>
            </div>
            <div class="syllabus_detail_cont">
                <div class="create_syllabus_cont syllabus_detail_show">
                    <ul>
                        <li>
                            <input type="hidden" id="pId" value="${productInfo.id }">
                            <span class="create_syllabus_item_name">名称</span>
                            <input type="text" class="create_syllabus_input wid320" value="${productInfo.cardName }" disabled>
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">限制人数</span>
                            <input type="text" class="create_syllabus_input wid320" value="${productInfo.peopleNum }" disabled>
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">价格</span>
                            <input type="text" class="create_syllabus_input wid320" value="${productInfo.salePrice/100 }" disabled>
                            <span>元/次</span>
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">课程次数</span>
                            <input type="text" class="create_syllabus_input wid320" value="${productInfo.classCount }" disabled>
                            <span>次</span>
                        </li>
                         <li>
								<span class="create_syllabus_item_name">排序值</span>
								<input type="number" id="sort" name="sort" class="create_syllabus_input wid120" style="width:100px;" value="${productInfo.sort }"  >（数值越小排位越前）
							</li>
                        <li>
                            <span class="create_syllabus_item_name">支持退款</span>
                            <div class="tuikuan">
                                <c:if test="${productInfo.isRefund == 1}">
                                    <em><i id="yes" rel="1" class="on"></i>是</em>
                                    <em><i id="no" rel="2"></i>否</em>
                                    <em class="poundage">
                                        退款手续费<input id="fee" name="fee" type="text" value="${productInfo.fee/100 }" class="refund">%
                                    </em>
                                </c:if>
                                <c:if test="${productInfo.isRefund == 0}">
                                    <em><i id="yes" rel="1"></i>是</em>
                                    <em><i id="no" rel="2" class="on"></i>否</em>
                                    <em class="poundage" style="display: none;">
                                        退款手续费<input id="fee" name="fee" type="text" value="" class="refund">%
                                    </em>
                                </c:if>
                            </div>
                        </li>
                        <li style="height:100%; overflow: hidden;">
                            <span class="create_syllabus_item_name">图片</span>
                            <div id="preview" style="float: left;"><img src="${productInfo.signImg }" height="120" alt=""/></div>
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">分店</span>
                            <div class="chooseshop">
                               <c:out value="${funcs:getCustNameByCustId(productInfo.sysCustId)}"></c:out>
                            </div>
                            <div class="choose_gev_shop_times"><input name="control_date" type="text" id="control_date" value='<fmt:formatDate value="${productInfo.startTime }" pattern="yyyy-MM-dd"></fmt:formatDate>' size="10" maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" disabled></div>
                            <div class="choose_gev_shop_times">-</div>
                            <div class="choose_gev_shop_times"><input name="control_date2" type="text" id="control_date2" value='<fmt:formatDate value="${productInfo.endTime }" pattern="yyyy-MM-dd"></fmt:formatDate>' size="10" maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" disabled></div>
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">教练</span>
                            <div class="showcoach">
                                <c:forEach items="${coachList }" var="cid">
                                    <p id="${cid }">${funcs:getCoachNameById(cid)}<i class='empty'></i></p>
                                </c:forEach>
                            </div>
                        </li>
                        <li>
                            <span class="create_syllabus_item_name">课程内容</span>
                            <textarea id="memo" name="memo" class="xheditor" style="width:442px; height:90px;">${productInfo.proDesc }</textarea>
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
                </div>
                <div class="joinerInfor" style="display: none;">
                    <div class="joinerInforTitle">
                        <span class="viptitle">会员</span>
                        <span class="teltitle">手机号</span>
                        <span class="jointimetitle">报名时间</span>
                        <!-- <span class="operatitle">操作</span> -->
                    </div>
                    <div class="joinerInforCont" >
                        <ul>
                            <c:forEach items="${orderList}" var="bean" >
                                <li>
                                    <span id="linkMan" class="vipcont">${fn:substring(bean.linkMan, 0, 12)}</span>
                                    <span id="linkPhone" class="telcont">${bean.linkPhone }</span>
                                    <span id="applyTime" class="jointimecont">&nbsp;<fmt:formatDate value="${bean.payTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                    <%-- <input id="productId" type="hidden" value="${bean.card_name }">
                                    <input id="memPrice" type="hidden" value="${bean.mem_price/100 }">
                                    <input id="refundMoney" type="hidden" value="${bean.refund_money/100 }">
                                    <input id="memberName" type="hidden" value="${bean.member_name }">
                                    <input id="memberLogo" type="hidden" value="${bean.member_logo }">
                                    <input id="uId" type="hidden" value="${bean.id }"> --%>
                                    <%-- <c:if test="${bean.status == '0' }"><span class="operacont">新订单</span></c:if>
                                    <c:if test="${bean.status == '1' }"><span class="operacont">待支付</span></c:if>
                                    <shiro:hasPermission name="course/group:refund">
                                        <c:if test="${bean.status == '2' }"><span class="operacont"><font class="j_tuikuan">退款</font></span></c:if>
                                    </shiro:hasPermission>
                                    <c:if test="${bean.status == '3' }"><span class="operacont">取消</span></c:if>
                                    <c:if test="${bean.status == '4' }"><span class="operacont">已完成</span></c:if>
                                    <c:if test="${bean.status == '5' }"><span class="operacont"><font class="j_tuikuan">已退款</font></span></c:if>
                                    <c:if test="${bean.status == '6' }"><span class="operacont">申请退款中</span></c:if> --%>

                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <!-- 分页 开始 -->
                        <div class="pager">
                            <c:set var="pageUrl"
                                value="/course/group/toProDetail_bmz.action?pageNo=pageno&type=${type}&id=${productInfo.id }"
                                scope="request" />
                            <jsp:include flush="true" page="/include/pager.jsp" />
                        </div>
                    <!-- 分页 结束 -->
                </div>
            </div>
            <div class="btns-area">
                <a href="#" class="outline_card">线下购买</a>
            </div>
        </div>
    </div>
</div>
<div class="refund-apply closebox">
    <h3 class="create_syllabus_title">退款</h3>
    <div class="refund-apply-box wid360">
        <ul class="">
            <li class="refund_header"><span><img src="/static/images/header.jpg" alt=""/></span><em id="linkMan1">姓名</em></li>
            <li><span>电话：</span><em id="linkPhone1">18611154787</em></li>
            <li><span>课程：</span><em id="productId1">普拉提初级</em></li>
            <li><span>手续费：</span><em id="cha">30</em></li>
            <li><span>购买价：</span><em id="memPrice1">290</em></li>
            <li><span>建议金额：</span><em id="refundMoney1">260</em></li>
            <input id="uId1" type="hidden">
            <input id="pId" type="hidden">
            <li><span>实际退款：</span><input id="actualMoney" name="actualMoney" type="text" class="refund"/> 元</li>
        </ul>
        <div class="refund_btns">
            <span class="cencle close">取消</span>
            <span class="confirm">申请退款</span>
        </div>
    </div>
</div>
<div id="Popup" class="closebox">
    <div id="PopupText">退款成功</div>
    <div id="PopupBtn"><span class="PopupBtnqx close">确定</span></div>
</div>
<div class="outline_buyCard closebox" style="display: none;">
    <div class="outline-cont">
        <div class="outline-top"></div>
        <div class="outline-warp create_syllabus_cont" style="display: ;">
            <ul>
                <li>
                    <span class="create_syllabus_item_name">手机号</span>
                    <input type="text" id="usemobile" class="create_syllabus_input" value="" style="width:180px;" placeholder="请输入会员手机号">
                    <a href="javascript:;" class="outGetYz">搜索</a>
                </li>
                <li style="height: 60px;padding-top: 15px;padding-bottom: 15px; line-height:60px; display: block;">
                    <img src="" alt="" id="memberlogo" data-id="" class="ajax-user">
                    <span id="membername" style="margin-left: 15px;"></span>
                </li>
                <li style="line-height: 35px;" class="buy_coach_i">
                    <span class="create_syllabus_item_name">团课名称</span>
                             <em>${funcs:getProductNameByProductId(productInfo.id+'') }  </em>
                           <em>  ${productInfo.salePrice/100}元</em>
                </li>
                <li style="line-height: 35px;">
                    <span class="create_syllabus_item_name">付款方式</span>
                    <select name="" id="payType"  style="margin-right: 15px;">
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
<script type="text/javascript">
$(function(){
    $('#testDiv').slimScroll({
           alwaysVisible: true
       });

   $(".outline_card").click(function(){
       $(".outline_buyCard").show();
       showShadow();
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
	    url:"/course/line/checkuser.action",
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
   $("#publish").click(function(){
      var  id=$("#pId").val();
      var  mobile=$("#usemobile").val();
      var  payType=$("#payType").find("option:selected").val();
      var memberId=$("#memberlogo").attr("data-id");
     // alert(memberId)
       if($.trim(mobile).length == 0){
           alert("请输入手机号！");
           return;
       }
       $.ajax({
           type:'post',
           data:{"id":id,"mobile":mobile,"payType":payType,"memberId":memberId},
           url:"/course/group/getOrder.action",
           dataType:"json",
           cache:false,
           success:function(res) {
                if(res === undefined){
                       alert("操作失败请稍后再试");
                   }else{
                       if(res.status == 0){
                           alert("操作成功！");
                          // top.location.href="/account/business/report.action";
                          window.location.reload();
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
</body>
<script type="text/javascript">
$(".syllabus_detail_tag span").click(function(){
    var index = $(this).index();
    $(this).addClass("on").siblings().removeClass("on");
    if(index == 0){
        $(".syllabus_detail_show").show();
        $(".joinerInfor").hide();
        $(".signListbox").hide();
    }
    else if(index == 1){
        $(".syllabus_detail_show").hide();
        $(".joinerInfor").show();
        $(".signListbox").hide();
    }
    else{
        $(".syllabus_detail_show").hide();
        $(".joinerInfor").hide();
        $(".signListbox").show();
    }
});
//新建收费团课是否支持退款
$(".tuikuan i").click(function(){
    var rel=$(this).attr('rel');
    $(this).addClass("on").parent().siblings().find("i").removeClass("on");
    //alert(rel);
    if(rel == 1){
        $(".poundage").show();
        $(".j_sijiao").show();
    }
    else{
        $(".poundage").hide();
        $(".j_sijiao").hide();
    }
});
//团课退款 弹出框
$(".j_tuikuan").click(function(){
    $(".refund-apply").show();
    showShadow();
    
    var linkMan = $(this).parent().parent().children('#linkMan').text();
    var linkPhone = $(this).parent().parent().children('#linkPhone').text();
    var productId = $(this).parent().parent().children('#productId').val();
    var memPrice = $(this).parent().parent().children('#memPrice').val();
    var refundMoney = $(this).parent().parent().children('#refundMoney').val();
    var uId = $(this).parent().parent().children('#uId').val();
    var memberName = $(this).parent().parent().children('#memberName').val();
    var memberLogo = $(this).parent().parent().children('#memberLogo').val();
	
    $("#linkMan1").text(memberName);
    $("#linkPhone1").text(linkPhone);
    $("#productId1").text(productId);
    $("#memPrice1").text(memPrice);
    $("#refundMoney1").text(refundMoney);
    $("#uId1").val(uId);
    $("#pId").val(pId);
    $("#img").attr(src,memberLogo);
    $("#cha").text(memPrice-refundMoney);
});
$(".close").click(function(){
    $(this).parents(".closebox").hide();
    $("#shade").remove();
});
$(".confirm").click(function(){
	var actualMoney = $("#actualMoney").val();
	if($.trim(actualMoney).length == 0){
		alert("请填写退款金额!");
		return;
	}
	var id = $("#uId1").val();
	var pId = $("#pId").val();	
	//退款
    $.ajax({
	   type: "post",
	   url: "/course/group/refund.action",
	   data: {actualMoney:actualMoney,id:id},
	   dataType : "json",
	   cache : false,
	   success: function(res){
		   if(res === undefined){
				alert("操作失败请稍后再试");
			}else{
				if(res.status == 0){
					 $(".confirm").click(function(){
			    	        $("#Popupp").show();
			    	        $(this).parents(".closebox").hide();
			    	  });
			    	 location.href="/course/toProDetail_bmz.action?id="+pId;
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
</html>