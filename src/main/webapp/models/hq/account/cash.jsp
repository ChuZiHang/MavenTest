<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
  <title>提现报表</title>
    <link type="text/css" rel="stylesheet" href="/static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/left.css"/>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/base.js"></script>
 <!--    <script type="text/javascript" src="/static/js/custom.js"></script> -->
    <script type="text/javascript" src="/static/js/star.js"></script>
    <script type="text/javascript" src="/static/js/left.js"></script>
    <script type="text/javascript" src="/static/js/file.js"></script>
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
					<jsp:param name="nvaIndex" value="33" />
			    <jsp:param name="nvaParentIndex" value="25" />
			</jsp:include> 
			<!--左侧导航：结束-->
			   <div class="rightContent">
                <h3 class="i_title">
                提现
    <div class="current-account">
    <input type="hidden" value="${custInfo.deposit}"  id="balanceTest"/>
    <p class="current-account-title">当前账户余额：<span>${custInfo.deposit/100}</span>元<%-- （可提现${custInfo.deposit/100}元） --%></p>
    <%-- <p>
        <input type="text" id="moneyText" placeholder="输入提现金额" class="txInput"/>
        <shiro:hasPermission name="account/settlement:cash">
        <a href="javascript:void(0)" id="withdrawscash" class="txBtn">提现</a>
        </shiro:hasPermission>
    </p> --%>
    </div>
                </h3>
                <div class="withBoxCheck">
                <c:choose>
                    <c:when test="${empty custInfo.alipayAccount && empty custInfo.bankAccount}"> 
                <div class="withBox" style="display: block;">
                    <div class="withBoxBind">
                        <h3>支付宝</h3>
                        <div><a href="#" class="bindAlipay">还未绑定支付宝，点击绑定</a></div>
                    </div>
                    <div class="withBoxBind">
                        <h3>银行卡</h3>
                        <div><a href="#" class="bindBank">还未绑定银行卡，点击绑定</a></div>
                    </div>
                </div>
                </c:when>
                
                <c:when test="${!empty custInfo.alipayAccount && empty custInfo.bankAccount}"> 
                <div class="withBox" style="display: block;" >
                    <div class="withBoxBind">
                        <h3>支付宝</h3>
                        <div style="line-height: 1.8;">
                            <p><span>&nbsp;</span></p>
                            <p><span>账号：${custInfo.alipayAccount}</span></p>
                            <p><span>真实姓名：${custInfo.alipayName}</span></p>
                        </div>
                        <div id="alipaypitchon" class="payTarget payTarget_alipay payTarget_on"></div>
                    </div>
                    <div class="withBoxBind">
                        <h3>银行卡</h3>
                        <div><a href="#" class="bindBank">还未绑定银行卡，点击绑定</a></div>
                    </div>
                </div>
                </c:when>
                
                  <c:when test="${empty custInfo.alipayAccount && !empty custInfo.bankAccount}"> 
                <div class="withBox" style="display: block;">
                    <div class="withBoxBind">
                        <h3>支付宝</h3>
                        <div><a href="#" class="bindAlipay">还未绑定支付宝，点击绑定</a></div>
                    </div>
                    <div class="withBoxBind">
                        <h3>银行卡</h3>
                        <div style="line-height: 1.8;">
                            <p><span>开户行：${custInfo.bankName}</span></p>
                            <p><span>账号：${custInfo.bankAccount}</span></p>
                            <p><span>真实姓名：${custInfo.bankAcname}</span></p>
                        </div>
                        <div id="bankpitchon" class="payTarget payTarget_bank payTarget_on"></div>
                    </div>
                </div>
                </c:when>
                <c:otherwise>
                <div class="withBox" style="display: block;">
                    <div class="withBoxBind">
                        <h3>支付宝</h3>
                        <div style="line-height: 1.8;">
                            <p><span>&nbsp;</span></p>
                            <p><span>账号：${custInfo.alipayAccount}</span></p>
                            <p><span>真实姓名：${custInfo.alipayName}</span></p>
                        </div>
                        <div id="alipaypitchon" class="payTarget payTarget_alipay payTarget_on"></div>
                    </div>
                    <div class="withBoxBind">
                        <h3>银行卡</h3>
                        <div style="line-height: 1.8;">
                            <p><span>开户行：${custInfo.bankName}</span></p>
                            <p><span>账号：${custInfo.bankAccount}</span></p>
                            <p><span>真实姓名：${custInfo.bankAcname}</span></p>
                        </div>
                        <div id="bankpitchon" class="payTarget payTarget_bank"></div>
                    </div>
                </div>
                </c:otherwise>
                 </c:choose> 
                <div class="withBoxTips">修改绑定信息请联系对接专业，或致电动网400-662-5170</div>
            </div>
                <div class="member_item_table">
                <table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
                    <tr>
                        <th bgcolor="#f3f5f6" height="36">提现人</th>
                        <th bgcolor="#f3f5f6" height="36">提现时间</th>
                        <th bgcolor="#f3f5f6" height="36">提现账户</th>
                        <th bgcolor="#f3f5f6" height="36">提现余额</th>
                        <th bgcolor="#f3f5f6" height="36">提现前余额</th>
                        <th bgcolor="#f3f5f6" height="36">手续费率</th>
                        <th bgcolor="#f3f5f6" height="36">手续费</th>
                        <th bgcolor="#f3f5f6" height="36">提现后余额</th>
                        <th bgcolor="#f3f5f6" height="36">提现状态</th>
                    </tr>
                    <c:forEach items="${beanList}" var="bean">
                        <tr>
                            <td>${bean.userId}</td>
                          <fmt:formatDate value="${bean.createDate}" var="createDate" pattern="yyyy-MM-dd"/>
                            <td>${createDate}</td>
                            <td>${bean.account}</td>
                            <td>${bean.money/100}元</td>
                            <td>${bean.curBalance/100}元</td>
                            <td>
                            <fmt:formatNumber type="number" value="${bean.payFee/bean.money*100}"  maxFractionDigits="2"/>%
                            </td>
                            <td>${bean.payFee/100}元</td>
                            <td>${(bean.curBalance-bean.money-bean.payFee)/100}元</td>
                            <td>
                            <c:if test="${bean.status == 0}">申请中</c:if>
                            <c:if test="${bean.status == 1}">已成功</c:if>
                            <c:if test="${bean.status == 2}">已退回</c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                </div>
                		<!-- 分页 开始 -->
				<div class="pager">
					<c:set var="pageUrl"
						value="/account/settlement/view.action?pageNo=pageno"
						scope="request" />
					<jsp:include flush="true" page="/include/pager.jsp" />
				</div>
			<!-- 分页 结束 -->
            <div class="clear_float"></div>
            <div class="btns-area">
    <shiro:hasPermission name="account/settlement:report">
        <a id="reportExecel" class="">导出数据</a>
    </shiro:hasPermission>
            </div>
        </div>
    </div>
</div>
<div class="fliiAlipay changepassw closebox">
    <h3 class="create_syllabus_title">支付宝绑定</h3>
    <div class="fliiAlipayCont">
        <ul>
            <li><span class="fliiAlipayCont-n">支付宝账号：</span><input id="alipay" name="alipay" type="text" class="fliipayInout"/></li>
            <li><span class="fliiAlipayCont-n">真实姓名：</span><input name="payName" id="payName" type="text" class="fliipayInout"/></li>

        </ul>
        <div class="setauthBtn">
    <em href="javascript:void(0)" class="create_syllabus_qx close">取消</em>
    <em href="javascript:void(0)" class="create_syllabus_fs" id="summitAlipay">确定</em>
        </div>
    </div>
</div>
<div class="fliiBankpay closebox fliiBankpayCard">
    <h3 class="create_syllabus_title">银行卡绑定</h3>
    <div class="fliiBankpayCont">
        <ul>
            <li>
                <div class="member_item_list">
                    <div class="chooseshop">
                    <span id="banktext" class="chooseText">请选择银行</span>
                         <i></i>
                        <div id="bankType" class="choose_gev_shop" style="display:none;max-height: 200px;overflow-y: scroll;">
                            <c:forEach items="${bankList}" var="bank">
                              <c:forEach items="${bank}" var="map">
                            <p>${map.value}</p>
                              </c:forEach>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="member_item_list">
                    <div  class="chooseshop">
                    <span id="provinceText" class="chooseText">省直辖市自治区 </span>
                        <i id="provinceajax"></i>
                        <div id="province" class="choose_gev_shop" style="display:none;max-height: 200px;overflow-y: scroll;">
                        </div>
                    </div>
                </div>
                <div class="member_item_list">
                    <div class="chooseshop">
                    <span id="cityText" class="chooseText">市县区</span>
                         <i id="cityajax"></i>
                        <div id="city" class="choose_gev_shop" style="display:none;max-height: 200px;overflow-y: scroll;">
                        </div>
                    </div>
                </div>
            </li>
            <li class="shoudongshuru">
                <div class="member_item_list">
                    <div class="chooseshop" style="width: 258px;;">
                    <span id="banknameText" class="chooseText">开户行</span>
                      <i id="banknameajax"></i>
                        <div id="bankname" class="choose_gev_shop" style="width: 293px;display:none;max-height: 200px;overflow-y: scroll;">
                        </div>
                    </div>
                </div>
                <div class="member_item_list">
                    <div class="shoudong">手动输入</div>
                </div>
            </li>
            <li class="xuanzefenhang" style="display: none">
                <div class="member_item_list">
                    <input id="shoudong"  type="text" class="fliipayInout" style="width: 284px;;">
                </div>
                <div class="member_item_list">
                    <div class="zidong">选择开户行</div>
                </div>
            </li>
              <li>银行卡号：<input id="banknumber" name="banknumber" type="text" class="fliipayInout" style="width: 284px;"></li>
            <li>真实姓名：<input id="bankmanname" name="bankmanname" type="text" class="fliipayInout" style="width: 284px;"></li>
        </ul>
        <div class="setauthBtn">
            <em href="javascript:void(0)" class="create_syllabus_qx close">取消</em>
            <em href="javascript:void(0)" class="create_syllabus_fs" id="submitbank">确定</em>
        </div>
    </div>
</div>
<script>
    // 自定义竖滚动条
    $(function(){
    	
        $('#testDiv').slimScroll({
            alwaysVisible: true
        });
        
        
        $(".chooseshop").click(function(){
            $(this).find(".choose_gev_shop").toggle();
        });
        $(".choosemerber").click(function(){
            $(".choosemerber_shop").toggle();
        });
        
        
        // 取消关闭弹框
        $(".close").click(function(){
            $(this).parents(".closebox").hide();
            $("#shade").remove();
        });
        
        
        // 提现支付宝绑定
        $(".bindAlipay").click(function(){
            showShadow();
            $(".fliiAlipay").show();
        });
        
        // 提现银行卡绑定
        $(".bindBank").click(function(){
            showShadow();
            $(".fliiBankpay").show();
        });


        // 提现银行卡手动填写分行
        $(".shoudong").click(function(){
            $(".xuanzefenhang").show();
            $(".shoudongshuru").hide();
        });
       // 提现银行卡手动选择分行
        $(".zidong").click(function(){
            $(".xuanzefenhang").hide();
            $(".shoudongshuru").show();
        });
        
       //提现选择绑定支付宝还是银行卡
         $("#bankpitchon").click(function(){
   	        $(this).attr("class",'payTarget payTarget_bank payTarget_on');
   	        $("#alipaypitchon").attr("class",'payTarget payTarget_alipay'); 
   	    });
       
         $("#alipaypitchon").click(function(){
        	 $(this).attr("class",'payTarget payTarget_alipay payTarget_on');
        	 $("#bankpitchon").attr("class",'payTarget payTarget_bank'); 
    	    });
       
       //点击选择文字
         $(".choose_gev_shop p").click(function(){
   	        var text = $(this).text();
   	        $(this).parents(".chooseshop").find(".chooseText").text(text);
   	    }); 
        
       //绑定支付宝
        $("#summitAlipay").click(function(){
        	var alipay = $("#alipay").val();
        	var payName = $("#payName").val();
        	
        	if(alipay == '' || payName==''){
        		alert("请填写绑定的账号和姓名");
        		return;
        	}
		         $.ajax({
				 type:"post",
				 data:{"alipay":alipay,"payName":payName},
				 url:"/account/settlement/alipay.action",
				 dataType:"json",
				 cache:false,
				 beforeSend:function(xhr){
						//$("#loading").show();
				 },
				 success:function(res) {
					if(res.result == "1"){
						alert("绑定成功！");
						location.href="/account/settlement/view.action";
					}else{
						alert("绑定失败！");
					}
				 },
				 error:function(resJson){
					 alert("系统异常，请稍后再试！");
				 }
				});
        });
        
       
        //导出execel
        $("#reportExecel").click(function(){
     	location.href="/account/settlement/report.action";
       });
       
        //提现
        $("#withdrawscash").click(function(){
          	var alipayclass=$("#alipaypitchon").attr('class');
        	var bankclass=$("#bankpitchon").attr('class');
        	var paytype='0';
          if(alipayclass == 'payTarget payTarget_alipay' && bankclass == 'payTarget payTarget_bank'){
        	alert('请选择提现到账的方式');
        	return;
          }
        	
          if(typeof(alipayclass) == "undefined" && typeof(bankclass) == "undefined"){
        	  alert('请绑定支付宝或者银行卡');
        	  return;
          }
          
          if(alipayclass == "undefined" && typeof(bankclass) == "undefined"){
        	  alert('请绑定支付宝或者银行卡');
        	  return;
          }
          
          
          if( alipayclass == "payTarget payTarget_alipay payTarget_on"){
        	  paytype='0';
          }else{
        	  paytype='1';
          }
          
          
        	//提现金额
        	var moneyText = $("#moneyText").val();
        	  if(!/^([1-9][\d]{0,8}|0)(\.[\d]{1,2})?$/.test(moneyText)){
   	    	  alert("请输入正确的提现金额!");
   	            return;
   			} 
        	 //当前账户余额
        	var balanceTest = $("#balanceTest").val();
        	
        	if(moneyText*100 > balanceTest){
        		alert("输入的提现金额超过了当前余额");
        		return;
        	}
		         $.ajax({
				 type:"post",
				 data:{"money":moneyText,"paytype":paytype},
				 url:"/account/settlement/cash.action",
				 dataType:"json",
				 cache:false,
				 beforeSend:function(xhr){
						//$("#loading").show();
				 },
				 success:function(res) {
					if(res.result == 1){
						alert("提现成功！请等待财务确认");
						location.href="/account/settlement/view.action";
					}else if(res.result == 2){
						alert("输入提现金额大于当前账户余额，请重新输入");
						
					}else{
						alert("提现失败！");
					}
				 },
				 error:function(resJson){
					 alert("系统异常，请稍后再试！");
				 }
				}); 
        });
       
       
       
       
        //根据银行名称选择省市县
        $("#provinceajax").click(function(){
        	 var banktext = $("#banktext").text();
        	
        	if(banktext == '请选择银行' || banktext == ''){
        		alert("请选择银行");
        		return;
        	}
        	
		         $.ajax({
				 type:"post",
				 data:{"bankType":banktext},
				 url:"/account/settlement/province.action",
				 dataType:"json",
				 cache:false,
				 beforeSend:function(xhr){
						//$("#loading").show();
				 },
				 success:function(res) {
					 $("#province").html('');
					
					 var html='';
	                    for(var i=0; i<res.length; i++)
	                    {
	                     html+='<p>'+res[i].province+'</p>'
	                    }
	                    $('#province').html(html);
	                    
	                    $(".choose_gev_shop p").click(function(){
	               	        var text = $(this).text();
	               	        $(this).parents(".chooseshop").find(".chooseText").text(text);
	               	    }); 
					 
				 },
				 error:function(resJson){
					 alert("系统异常，请稍后再试！");
				 }
				});
        });
       
       
        //根据省市县名称选择城市
        $("#cityajax").click(function(){
        	 var banktext = $("#banktext").text();
        	 var bankProvince = $("#provinceText").text();
        	
        	 if(banktext == '请选择银行' || banktext == ''){
         		alert("请选择银行");
         		return;
         	}
        	if(provinceText == '省直辖市自治区' || provinceText == ''){
        		alert("请选择省直辖市自治区");
        		return;
        	}
        	
		         $.ajax({
				 type:"post",
				 data:{"bankType":banktext,"bankProvince":bankProvince},
				 url:"/account/settlement/city.action",
				 dataType:"json",
				 cache:false,
				 beforeSend:function(xhr){
						//$("#loading").show();
				 },
				 success:function(res) {
					 $("#city").html('');
					
					 var html='';
	                    for(var i=0; i<res.length; i++)
	                    {
	                     html+='<p>'+res[i].city+'</p>'
	                    }
	                    $('#city').html(html);
	                    
	                    $(".choose_gev_shop p").click(function(){
	               	        var text = $(this).text();
	               	        $(this).parents(".chooseshop").find(".chooseText").text(text);
	               	    }); 
					 
				 },
				 error:function(resJson){
					 alert("系统异常，请稍后再试！");
				 }
				});
        });
       

        
        
        
        //根据城市选择支行
        $("#banknameajax").click(function(){
        	 var banktext = $("#banktext").text();
        	 var cityText = $("#cityText").text();
        	 
        	
        	if(banktext == '请选择银行' || banktext == ''){
         		alert("请选择银行");
         		return;
         	}
        	if(cityText == '市县区' || cityText == ''){
        		alert("请选择市县区");
        		return;
        	}
		         $.ajax({
				 type:"post",
				 data:{"bankType":banktext,"bankCity":cityText},
				 url:"/account/settlement/bankname.action",
				 dataType:"json",
				 cache:false,
				 beforeSend:function(xhr){
						//$("#loading").show();
				 },
				 success:function(res) {
					 $("#bankname").html('');
					
					 var html='';
	                    for(var i=0; i<res.length; i++)
	                    {
	                     html+='<p>'+res[i].bank_name+'</p>'
	                    }
	                    $('#bankname').html(html);
	                    
	                    $(".choose_gev_shop p").click(function(){
	               	        var text = $(this).text();
	               	        $(this).parents(".chooseshop").find(".chooseText").text(text);
	               	    }); 
					 
				 },
				 error:function(resJson){
					 alert("系统异常，请稍后再试！");
				 }
				});
        });
        
        
        
        var bool=true;
        $("#submitbank").click(function(){
		        if(bool){
		        	bool=false;
		        	submit();
		        	bool=true;
		          }
        });
        
       
        
      
        
        function submit(){
        	
       	 var banktext = $("#banktext").text();
    	 var cityText = $("#cityText").text();
    	 var bankProvince = $("#provinceText").text();
    	 var banknameText = $("#banknameText").text();
    	 var banknumber = $("#banknumber").val();
    	 var bankmanname = $("#bankmanname").val();
    	 var shoudongbankname = $("#shoudong").val();
    	 
    	if(banktext == '请选择银行' || banktext == ''){
     		alert("请选择银行");
     		return;
     	}
    	if(provinceText == '省直辖市自治区' || provinceText == ''){
    		alert("请选择省直辖市自治区");
    		return;
    	}
    	if(cityText == '市县区' || cityText == ''){
    		alert("请选择市县区");
    		return;
    	}
    	
    	
    	if(bankmanname == ''){
    		alert("请输入真实姓名");
    		return;
    	}
     	if(!/^[0-9]*[1-9][0-9]*$/.test(banknumber)){
    	  alert("请输入正确的银行卡号!");
            return;
		}
       if(!shoudongbankname == ''){
    	   if(confirm( '请确认手动输入开户行名称')){
    		   banknameText =shoudongbankname;
    	   }
    	}else{
    		if(banknameText == '开户行' || banknameText == ''){
        		alert("请选择开户行或者手动输入");
        		return;
        	}
    	}
     	
   		 $.ajax({
				 type:"post",
				 data:{"banktext":banktext,"cityText":cityText,"bankProvince":bankProvince,"banknameText":banknameText,"banknumber":banknumber,"bankmanname":bankmanname},
				 url:"/account/settlement/bindbank.action",
				 dataType:"json",
				 cache:false,
				 beforeSend:function(xhr){
						//$("#loading").show();
				 },
				 success:function(res) {
					if(res.result == "1"){
						alert("绑定成功！");
						location.href="/account/settlement/view.action";
					}else{
						alert("绑定失败！");
					}
				 },
				 error:function(resJson){
					 alert("系统异常，请稍后再试！");
				 }
         });
  		
      }       
       
});       
</script>
</body>
</html>