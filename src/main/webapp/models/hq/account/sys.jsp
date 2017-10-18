<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>业务报表</title>
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<script type="text/javascript" src="/static/js/moment.js"></script>
<script type="text/javascript" src="/static/js/esl.js"></script>
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
<script type="text/javascript" src="/static/js/jquery.slimscroll.js"></script>
<script type="text/javascript" src="/static/third/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="/static/css/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css" media="all" href="/static/css/daterangepicker-1.3.7.css" />
<link href="/static/css/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="/static/js/daterangepicker-1.3.7.js"></script>
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
				<jsp:param name="nvaIndex" value="174" />
				<jsp:param name="nvaParentIndex" value="157" />
			</jsp:include>
			<!--左侧导航：结束-->
			
			<div class="rightContent">
				<div class="search_area">
					<div class="business_select">
						<c:if test="${parentType == 1 }">
						<span class="create_syllabus_item_name">分店</span>
						<div class="chooseshop">
							<c:choose>
								<c:when test="${custId == 0}">
									<span  id="fendian1" class="chooseText">全部</span>
									<input id="sysCustId" name="sysCustId" class="hidden" type="hidden" value="0">
								</c:when>
								<c:when test="${custId != 0}">
									<span  id="fendian1" class="chooseText">${funcs:getCustNameByCustId(custId )}</span>
									<input id="sysCustId" name="sysCustId" class="hidden" type="hidden" value="${custId}">
								</c:when>
							</c:choose>
							<i></i>
							<div id="chooseShop" class="choose_gev_shop">
								<!-- <p>江南春店</p> -->
							</div>
						</div>
					</c:if>
					</div>
					<div class="control-group" style="float: left;padding-left: 49px;position: relative; ">
							<div class="controls">
							<span class="business_select_title">日期：</span>
								<div id="reportrange" class="pull-left dateRange" style="width:200px;height:33px;"  >
									<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
									<span id="searchDateRange" data-start="${startDate}" data-end="${endDate}">${data }</span>
									<b class="caret"></b>
								</div>
							 <button class="search-btns" id="search-b">搜索</button>
							</div>
					  </div>
				</div>
				<div class="member_item_table basicsBox">
					<span>
						<p class="basics_infor_title">新增会员</p>
						<p class="basics_infor_cont">${addMember}</p>
					</span> <span>
						<p class="basics_infor_title">到店人数</p>
						<p class="basics_infor_cont">${openMember}</p>
					</span> <span>
						<p class="basics_infor_title">约课人数</p>
						<p class="basics_infor_cont">${ booking}</p>
					</span> <span>
						<p class="basics_infor_title">到课人数</p>
						<p class="basics_infor_cont">${checkBooking}</p>
					</span>
				</div>
				 <div id="main" style="height:400px;;padding:15px; margin:25px; background:#ffffff;"></div>
            	<div class="clear_float"></div>
				<div class="member_item_table">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
				<tr>
					<th bgcolor="#f3f5f6" height="36">日期</th>
					<th bgcolor="#f3f5f6" height="36">新增会员</th>
					<th bgcolor="#f3f5f6" height="36">到店人数</th>
					<th bgcolor="#f3f5f6" height="36">约课人数</th>
					<th bgcolor="#f3f5f6" height="36">到课人数</th>
				</tr>
				<c:forEach var="item" items="${link}">
					<tr>
					<td>${item.key}</td>
					<td>${item.value[1]}</td>
					<td>${item.value[2]}</td>
					<td>${item.value[3]}</td>
					<td>${item.value[4]}</td>
				   </tr>
				</c:forEach>
				</table>
				</div>
				<div class="btns-area">
					<a href="javascript:void (0)" id="exportdate">导出报表</a>
				</div>
	</div>
	</div>
	</div>
</body>
<style>
.el-table{
	background:#fff;
	border-top:1px solid #e5e5e5;
	border-right:1px solid #e5e5e5;
	clear:both;
	margin:40px 0;
}
.el-table th{
	background:#f3f5f6;
	border-left:1px solid #e5e5e5;
	border-bottom:1px solid #e5e5e5;
	text-align:center;
	padding:8px;
}
.el-table td{
	padding:8px;
	border-left:1px solid #e5e5e5;
	border-bottom:1px solid #e5e5e5;
	text-align: center;
}
</style>
<script type="text/javascript">
require.config({  
    paths: {  
        echarts:'/static/js/echarts' ,
        'echarts/chart/bar' : '/static/js/echarts-map',
        'echarts/chart/line': '/static/js/echarts-map'
    }  
});  
require(
        [
            'echarts',
            'echarts/chart/line',
            'echarts/chart/bar'
        ], 
        function  (ec) {
        	var chart = document.getElementById('main');  
            var echart = ec.init(chart);  
              
            echart.showLoading({  
                text: '正在努力加载中...'  
            }); 
        	var  list=[];
        	var  list11=[];
        	var  list22=[];
        	var  list33=[];
        	var  list44=[];
        	if(${list!=null}){
        	var  x=${list};
        	for(var i=0;i<x.length;i++){
        		list[i]=x[i];
        	}
        	}
        	if(${list1!=null}){
        	var list1=${list1};
        	for(var i=0;i<list1.length;i++){
        		list11[i]=list1[i]
        	}
        	}
        	if(${list2!=null}){
        	var list2=${list2};
        	for(var i=0;i<list2.length;i++){
        		list22[i]=list2[i];
        	}
        	}
        	if(${list3!=null}){
        	var list3=${list3};
        	for(var i=0;i<list3.length;i++){
        		list33[i]=list3[i];
        	}
        	}
        	if(${list4!=null}){
        	var list4=${list4};
        	for(var i=0;i<list4.length;i++){
        		list44[i]=list4[i];
        	}
        	}
     

	    
	    var option={
	    		title : {
			        text: '会员走势',
			    },
	         tooltip : {
	             trigger: 'axis'
	         },
	         legend: {
	             data:['新增会员','到店人数','约课人数','到课人数']
	         },
	         toolbox: {
	             show : true,
	             feature : {
	                 mark : {show: false},
	                 dataView : {show: true, readOnly: false},
	                 magicType : {show: true, type: ['line', 'bar']},
	                 restore : {show: false},
	                 saveAsImage : {show: true}
	             }
	         },
	         calculable : true,
	         xAxis : [
	             {
	                 type : 'category',
	                 data : list
	             }
	         ],
	         yAxis : [
	             {
	                 type : 'value',
	                 boundaryGap : true,
	                 splitArea : {show : true},
	                 boundaryGap: [0.2, 0.2]
	             }
	         ],
	         series : [
	             {
	                 name:'新增会员',
	                 type:'line',
	                 data:list11
	             
	             },
	             {
	                 name:'到店人数',
	                 type:'line',
	                 data:list22
	         	},
	         	 {
	                name:'约课人数',
	                type:'line',
	                data:list33
	            
	            },
	            {
	                name:'到课人数',
	                type:'line',
	                data:list44
	            
	            }
	         ]
	         
	    };
	    echart.setOption(option);  
        echart.hideLoading();  
	}
  ) 
    	
    

 	
</script>
<script type="text/javascript">
$(function() {
	$(".chooseshop").click(function(){
	    $(this).find(".choose_gev_shop").toggle();
	});
	$(".choosemerber").click(function(){
	    $(".choosemerber_shop").toggle();
	});
	//时间插件
    $('.daterange span').html(moment().startOf('day').format('YYYY-MM-DD') + '~ ' + moment().format('YYYY-MM-DD'));
	$('#reportrange').daterangepicker(
		{
			//startDate: moment().subtract('hours',1),
			//endDate: moment(),
			//minDate: '01/01/2012',	//最小时间
			maxDate : moment(), //最大时间 
			dateLimit : {
				days : 120
			}, //起止时间的最大间隔
			showDropdowns : true,
			showWeekNumbers : false, //是否显示第几周
			timePicker : true, //是否显示小时和分钟
			timePickerIncrement : 60, //时间的增量，单位为分钟
			timePicker12Hour : false, //是否使用12小时制来显示时间
			ranges : {
                   '最近7日': [moment().subtract('days', 6), moment()],
                   '最近15日': [moment().subtract('days', 14), moment()],
                   '最近30日': [moment().subtract('days', 29), moment()]
			},
			opens : 'right', //日期选择框的弹出位置
			buttonClasses : [ 'btn btn-default' ],
			applyClass : 'btn-small btn-primary blue',
			cancelClass : 'btn-small',
			format : 'YYYY-MM-DD ', //控件中from和to 显示的日期格式
			separator : ' to ',
			locale : {
				applyLabel : '确定',
				cancelLabel : '取消',
				fromLabel : '起始时间',
				toLabel : '结束时间',
				customRangeLabel : '自定义',
				daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
				monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',
						'七月', '八月', '九月', '十月', '十一月', '十二月' ],
				firstDay : 1
			}
		}, function(start, end, label) {//格式化日期显示框
               
               	$('#reportrange span').html(start.format('YYYY-MM-DD') + ' ~ ' + end.format('YYYY-MM-DD'));
             });
	 $("#search-b").click(function(){
   	
   		var custId = $("#sysCustId").val();
   		var data=$("#searchDateRange").text();
   		var array=data.split("~");
   		var startDate=array[0].trim();
   		var endDate=array[1].trim();
   		if($.trim(custId).length == 0){
   			custId = 0;
   		} 
   		location.href="/account/business/sys.action?custId="+custId+"&startDate="+startDate+"&endDate="+endDate+"&data="+data;
   	});
		//导出execel
   	$("#exportdate").click(function(){
   		var custId = $("#sysCustId").val();
   		var data=$("#searchDateRange").text();
   		var array=data.split("~");
   		var startDate=array[0].trim();
   		var endDate=array[1].trim();
   		if($.trim(custId).length == 0){
   			custId = 0;
   		}
   		location.href="/account/business/exportdate.action?custId="+custId+"&startDate="+startDate+"&endDate="+endDate;
   	});
	//分店列表
	$.ajax({
		 type:"post",
		 url:"/course/group/subbranchList.action",
		 dataType:"json",
		 cache:false,
		 success:function(res) {
			 $('#chooseShop').append('<p id="0">'+"全部"+'</p>');
			for(var i=0;i<res.subbranchList.length;i++){
					$('#chooseShop').append('<p id="'+res.subbranchList[i].system_id+'">'+res.subbranchList[i].cust_name+'</p>');
			};
			
			$(".choose_gev_shop p").click(function(){
	            var name = $(this).text();
	            var id = $(this).attr("id");
	            $(this).parents(".chooseshop").find(".chooseText").text(name);
	            $(this).parents(".chooseshop").find(".hidden").val(id);
	        })
		 },
		 error:function(resJson){
			 alert("系统异常，请稍后再试！");
		 }
	});
})
</script>
</html>