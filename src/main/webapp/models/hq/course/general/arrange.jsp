<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>课程安排</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
<link type="text/css" rel="stylesheet" href="/static/css/app.css" />
<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
<script type="text/javascript" src="/static/js/jquery.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>
<script type="text/javascript" src="/static/js/left.js"></script>
<style>
                .syweek a{
                    display: inline-block;
                    background: #f8b500;
                    color: #fff;
                    margin: 0 5px;
                    padding: 3px 15px;
                    border-radius: 5px;
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
				<jsp:param name="nvaIndex" value="16" />
				<jsp:param name="nvaParentIndex" value="14" />
			</jsp:include>
			<!--左侧导航：结束-->
			<div class="rightContent">
				<h3 class="i_title">课程安排</h3>
				<div class="syllabus_branch_select search_area">
					<c:forEach var="custInfo" items="${custInfos }">
						<c:choose>
							<c:when test="${custInfo.systemId==custId}">
								<span class="on" id="${custInfo.systemId }">${custInfo.custName }</span>
							</c:when>
							<c:otherwise>
								<span id="${custInfo.systemId }">${custInfo.custName }</span>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				<div class="syweek">
				自动生成
					<select id="select_id" class="${custId }" style="border: 1px solid #e5e5e5;height: 28px;border-radius: 3px;width: 130px;margin-left: 15px;">
                                    <option value="0" selected="">请选择</option>
                                    <option value="1">第2周</option>
                                    <option value="2">第3周</option>
                                    <option value="3">第4周</option>
                                    <option value="4">第5周</option>
                                    <option value="5">第6周</option>
                                    <option value="6">第7周</option>
                                    <option value="7">第8周</option>
                                    <option value="8">第9周</option>
                                    <option value="9">第10周</option>
                                </select>
				
					<c:if test="${afterDays>0 }">
                    	<a href="?custId=${custId }&afterDays=${afterDays-7 }">上一周</a>
                    </c:if>
                    	<a href="?custId=${custId }&afterDays=${afterDays+7 }">下一周</a>
                </div>
				</div>
				<div class="container">
					<div id="advanced">

						<div style="" class="block__list block__list_words weekSle">
							<div class="block__list-title">${dates[0] }</div>
							<ul id="advanced-1" class="${dates[0] }">
								<c:forEach var="item" items="${mapData[dates[0]] }">

									<li id="${item.id }" draggable="false" class=""
										style="transform: translate3d(0px, 0px, 0px);">${item.class_name }</li>

								</c:forEach>
							</ul>
						</div>

						<c:forEach var="i" begin="3" end="8" step="1">
							<div class="block__list block__list_words weekSle">
								<div class="block__list-title">${dates[i-2] }</div>
								<ul id="advanced-${i}" class="${dates[i-2] }">
									<c:forEach var="item" items="${mapData[dates[i-2]] }">

										<li id="${item.id }" draggable="false" class=""
											style="transform: translate3d(0px, 0px, 0px);">${item.class_name }</li>

									</c:forEach>

								</ul>
							</div>
						</c:forEach>

						<div class="block__list block__list_words selestsyllabus">
							<div class="block__list-title"></div>
							<ul id="advanced-2">
								<c:forEach var="course" items="${courses }">
									<li id="${course.id }">${course.className }</li>
								</c:forEach>
							</ul>
						</div>
						<div style="clear: both"></div>
					</div>
				</div>
				<div id="fillsyllabus" class="fillSya" style="display: none;">
					<form action="post" id="update">
						<ul>
							<li><span></span> <input id="updateId" name="updateId"
								type="hidden" /></li>
							<li><span></span> <input id="updateDate" name="updateDate"
								type="hidden" /></li>
							<li><span>时间</span> <input class="timestyle" type="text" id="updateStart" />-<input class="timestyle" type="text" id="updateEnd" />
<!-- 							<select id="updateStart" class="select_times"> -->
<!-- 								<option value="-1">开始时间</option> -->
<%-- 									<c:forEach var="i" begin="0" end="23"> --%>
<%-- 										<option value="${i }">${i }:00</option> --%>
<%-- 									</c:forEach> --%>
<!-- 							</select> - <select id="updateEnd" class="select_times"> -->
<!-- 							<option value="-1">结束时间</option> -->
<%-- 									<c:forEach var="i" begin="0" end="23"> --%>
<%-- 										<option value="${i }">${i }:00</option> --%>
<%-- 									</c:forEach> --%>
<!-- 							</select> -->
							
							</li>
							<li><span>教练</span>
							<select id="updateCoach" class="select_times">
									<option id="updateC" value="0">空</option>
<!-- 									<option value="0">空</option> -->
									<c:forEach var="coach" items="${coachs }">
										<option value="${coach.id}"> ${funcs:getCoachNameById(coach.id)}</option>
									</c:forEach>
							</select>
							 </li>
							<li><span>操厅</span> <input id="updateAddress" name="updateAddress"
								type="text" placeholder="教练" /></li>
							<li><span>限制人数</span> <input id="updateNum" name="updateNum"
								type="text" placeholder="限制人数" /></li>

							<li><span>&nbsp;</span>
								<div class="set_syllasbus">
									<a href="javascript:;" id="Evedelect" class="delete">删除</a> <a
										href="javascript:;" id="saveUpdate" class="change">保存</a>
								</div>
							</li>
							<a class="guanbi" href="javascript:;"><img src="/static/images/guanbi.png"></a>
						</ul>
					</form>
				</div>
				<div id="fillsyllabusitem" class="fillSya" style="display: none;">
					<form action="post" id="insert">
						<ul>
							<li><span></span> <input id="insertDate" name="insertDate"
								type="hidden" /></li>
							<li><span></span> <input id="insertCId" name="insertCId"
								type="hidden" /></li>
							<li>
							<span>时间</span>
							<input class="timestyle" type="text" id="insertStart" />-<input class="timestyle" type="text" id="insertEnd" />
							
<!-- 							 <select id="insertStart" class="select_times"> -->
<!-- 									<option value="-1">开始时间</option> -->
<%-- 									<c:forEach var="i" begin="0" end="23"> --%>
<%-- 										<option value="${i }">${i }:00</option> --%>
<%-- 									</c:forEach> --%>
<!-- 							</select> - <select id="insertEnd" class="select_times"> -->
<!-- 									<option value="-1">结束时间</option> -->
<%-- 									<c:forEach var="i" begin="0" end="23"> --%>
<%-- 										<option value="${i }">${i }:00</option> --%>
<%-- 									</c:forEach> --%>
<!-- 							</select> -->
							
							
							
							</li>
							<li><span>教练</span> 
							<select id="insertCoach" class="select_times">
									<option value="0">空</option>
									<c:forEach var="coach" items="${coachs }">
										<option value="${coach.id}"> ${funcs:getCoachNameById(coach.id)}</option>
									</c:forEach>
							</select>
							
							</li>
							<li><span>操厅</span> <input id="address" name="address" type="text" /></li>
							<li><span>限制人数</span> <input id="peopleNum" name="peopleNum" type="text" /></li>

							<li><span>&nbsp;</span>
								<div class="set_syllasbus">
									<a href="javascript:;" id="delete" class="delete">取消</a> <a
										href="javascript:;" id="saveInsert" class="change">保存</a>
								</div></li>
						</ul>
					</form>
				</div>
				<script type="text/javascript" src="/static/js/Sortable.js"></script>
				<script type="text/javascript" src="/static/js/ng-sortable.js"></script>
			</div>
		</div>
	</div>
	<script>
		$('.syllabus_branch_select span').click(

				function() {
					if ($(this).attr('class') != 'on') {
						$('.syllabus_branch_select span').each(function() {
							$(this).attr('class', '');
						});
						$(this).attr('class', 'on');

						window.location.href = "arrange.action?custId="
								+ $(this).attr('id');

					}
				});

		(function() {
			var console = window.console;

			if (!console.log) {
				console.log = function() {
					alert([].join.apply(arguments, ' '));
				};
			}

			[ {
				name : 'advanced',
				pull : true,
				put : true
			}, {
				name : 'advanced',
				pull : "clone",
				put : false
			}, {
				name : 'advanced',
				pull : true,
				put : true
			}, {
				name : 'advanced',
				pull : true,
				put : true
			}, {
				name : 'advanced',
				pull : true,
				put : true
			}, {
				name : 'advanced',
				pull : true,
				put : true
			}, {
				name : 'advanced',
				pull : true,
				put : true
			}, {
				name : 'advanced',
				pull : true,
				put : true
			} ].forEach(function(groupOpts, i) {
				Sortable.create(document.getElementById('advanced-' + (i + 1)),
						{
							sort : (i != 1),
							group : groupOpts,
							animation : 150
						});
			});

			Sortable.create(document.getElementById('handle-1'), {
				handle : '.drag-handle',
				animation : 150
			});

			angular
					.module('todoApp', [ 'ng-sortable' ])
					.controller('TodoController', [ '$scope', function($scope) {
						$scope.todos = [ {
							text : 'learn angular',
							done : true
						}, {
							text : 'build an angular app',
							done : false
						} ];

						$scope.addTodo = function() {
							$scope.todos.push({
								text : $scope.todoText,
								done : false
							});
							$scope.todoText = '';
						};

						$scope.remaining = function() {
							var count = 0;
							angular.forEach($scope.todos, function(todo) {
								count += todo.done ? 0 : 1;
							});
							return count;
						};

						$scope.archive = function() {
							var oldTodos = $scope.todos;
							$scope.todos = [];
							angular.forEach(oldTodos, function(todo) {
								if (!todo.done)
									$scope.todos.push(todo);
							});
						};
					} ])
					.controller(
							'TodoControllerNext',
							[
									'$scope',
									function($scope) {
										$scope.todos = [ {
											text : 'learn Sortable',
											done : true
										}, {
											text : 'use ng-sortable',
											done : false
										}, {
											text : 'Enjoy',
											done : false
										} ];

										$scope.remaining = function() {
											var count = 0;
											angular.forEach($scope.todos,
													function(todo) {
														count += todo.done ? 0
																: 1;
													});
											return count;
										};

										$scope.sortableConfig = {
											group : 'todo',
											animation : 150
										};
										'Start End Add Update Remove Sort'
												.split(' ')
												.forEach(
														function(name) {
															$scope.sortableConfig['on'
																	+ name] = console.log
																	.bind(
																			console,
																			name);
														});
									} ]);
		})();

		// Background
		document.addEventListener("DOMContentLoaded", function() {
			function setNoiseBackground(el, width, height, opacity) {
				var canvas = document.createElement("canvas");
				var context = canvas.getContext("2d");

				canvas.width = width;
				canvas.height = height;

				for (var i = 0; i < width; i++) {
					for (var j = 0; j < height; j++) {
						var val = Math.floor(Math.random() * 255);
						context.fillStyle = "rgba(" + val + "," + val + ","
								+ val + "," + opacity + ")";
						context.fillRect(i, j, 1, 1);
					}
				}

				el.style.background = "url(" + canvas.toDataURL("image/png")
						+ ")";
			}

			setNoiseBackground(document.getElementsByTagName('body')[0], 50,
					50, 0.02);
		}, false);
	</script>
	<script>
		$(function() {
			$('.weekSle li').click( function(e) {
				
								setDetail($(this).attr('id'));
								console.log($(this).attr('id'));
								var fillSyllaItem = document
										.getElementById("fillsyllabusitem");
								var fillSylla = document
										.getElementById("fillsyllabus");
								fillSylla.style.display = "none";
								fillSylla.style.display = "block";
								var entLeft = e.clientX;
								var entTop = e.clientY;
								if (entLeft >= 1149) {
									entLeft = 975;
								}
								fillSylla.style.left = entLeft + "px";
								fillSylla.style.top = entTop + "px";

								var Elent1 = this;
								var Evedelect = document
										.getElementById("Evedelect");

								Evedelect.onclick = function() {
									
									$.ajax({
										cache : true,
										type : "POST",
										url : "/course/arrange/delete.action",
										data : {
											id : $('#updateId').val()
										},
										dataType : "json",
										success : function(res) {
											console.log(res);
											if (res.status == 1) {
											
												
											} else {
												alert("网络异常，请稍后再试！");
											}

										},
										error : function(res) {
											alert("网络异常，请稍后再试！");
										}
									});
									
									
									Elent1.parentNode.removeChild(Elent1)
									fillSylla.style.display = "none";
								}
							})
		})

		var setDetail = function(id) {
			
			$.ajax({
				cache : true,
				type : "POST",
				url : "/course/arrange/courseprice.action",
				data : {id : id},
				dataType : "json",
				success : function(res) {
					console.log(res);
					//console.log("111111111");
					if (res.status == 1) {
						var data = res.data.coursePrice;
						
// 						var start = document.getElementById("updateStart");
// 						var end = document.getElementById("updateEnd");
						var coach = document.getElementById("updateCoach");
						
// 						var indexS = start.selectedIndex;
// 						var indexE = end.selectedIndex;
						var indexC = coach.selectedIndex;
						
						var startTime = new Date(data.startTime);
						var endTime =new Date(data.endTime);
						
						var minuteS ='0';
						var minuteE = '0';
						if(startTime.getMinutes()==0){
							minuteS = '00';
						}else{
							minuteS = startTime.getMinutes();
						}
						
						if(endTime.getMinutes()==0){
							minuteE = '00';
						}else{
							minuteE = endTime.getMinutes();
						}
						
						
						$('#updateStart').val(startTime.getHours()+":"+minuteS);
						$('#updateEnd').val(endTime.getHours()+":"+minuteE);
						
// 						start.options[indexS] = new Option(startTime+":00",startTime);  //更改对应的值
// 						start.options[indexS].selected = true;  //保持选中状态
// 						end.options[indexE] = new Option(endTime+":00",endTime);  //更改对应的值
// 						end.options[indexE].selected = true;  //保持选中状态
						//coach.options[indexC] = data.coachId;
						//coach.options[indexC].selected = true;
						
						$('#updateC').val(res.data.coachId);
						if(res.data.coachId>0){
							$('#updateC').text(res.data.coachName);
						}
						
						
						$('#updateId').val(id);
						$('#updateDate').val(new Date(data.endTime).toLocaleDateString());
						//$('#updateStart').val(new Date(data.startTime));
						//$('#updateEnd').val(new Date(data.endTime));

					//	console.log();
					//	$('#updateCoach').val(data.coachId);
						$('#updateAddress').val(data.address);
						$('#updateNum').val(data.peopleNum);

						
					} else {
						alert("网络异常，请稍后再试！");
					}

				},
				error : function(res) {
					alert("网络异常，请稍后再试！");
				}
			});
		}
	</script>
	<style>
.selestsyllabus {
	position: absolute;
	width: 918px;
	height: 100px;
	bottom: -100px;;
}

.selestsyllabus ul li {
	float: left;
}

#advanced-2 li {
	border:2px solid #60cd60;
	background: #f5f5f5;
	color: #60cd60;
}

.weekSle {
	width: 14.27%;
	float: left;
	background: #fff;
}

.weekSle ul {
	border: 1px solid #e5e5e5;
	height:430px;
	background: #fff;
	border-left: none;
}
.syweek{
	margin: 0 25px 25px 25px;
	float: left;
	padding-top: 10px;
	}
#fillsyllabus, #fillsyllabusitem {
	position: absolute;
	background: #fff;
	padding: 10px;
	border-radius: 10px;
	border: 1px solid #e5e5e5;;
}

#fillsyllabus ul li {
	padding-left: 60px;
	position: relative;
}

#fillsyllabus ul li span {
	display: block;
	position: absolute;
	left: 0;
	font-size: 12px;
	top: 9px;
}

#fillsyllabus ul li input {
	height: 30px;
	line-height: 30px;
	border: 1px solid #e5e5e5;
	width: 200px;
	margin-bottom: 8px;
	padding-left: 10px;
}

#fillsyllabusitem ul li {
	padding-left: 60px;
	position: relative;
}

#fillsyllabusitem ul li span {
	display: block;
	position: absolute;
	left: 0;
	font-size: 12px;
	top: 9px;
}

#fillsyllabusitem ul li input {
	height: 30px;
	line-height: 30px;
	border: 1px solid #e5e5e5;
	width: 200px;
	margin-bottom: 8px;
	padding-left: 10px;
}

#fillsyllabusitem {
	display: block;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -147px;
	margin-top: -110px;
	z-index: 1014;
}

.set_syllasbus a {
	display: block;
	float: left;
	color: #fff;
	font-size: 12px;
	text-decoration: none;
	width: 96px;
	line-height: 38px;
	text-align: center;
	margin-right: 8px;
	border-radius: 5px;;
}

.set_syllasbus a.delete {
	background: #f00;
}

.set_syllasbus a.change {
	background: #f8b500;
}

.select_times {
	background: #fff;
	height: 30px;
	line-height: 30px;
	border: 1px solid #e5e5e5;
	width: 100px;
	margin-bottom: 8px;
	padding-left: 10px;
}


#fillsyllabus ul li input.timestyle {width: 80px;}
#fillsyllabusitem ul li input.timestyle {width: 80px;}

</style>
	<script>
		$(function() {

			$('#saveInsert').click(function() {
				var flag = true;
				$('#insert input').each(function() {
					if ($(this).val() == '') {
						alert("数据不能为空");
						flag = false;
						return false;
					}
				});

				if (!flag) {
					return false;
				}
				
				
								var insertStart = $('#insertStart').val();
								var insertEnd = $('#insertEnd').val();
								
								console.log(insertStart.indexOf(':'));
								
								if(insertStart.indexOf(':')>0 || insertStart.indexOf('：')>0 ){
								}else{
									alert("请按标准填写时间！例：08:30或18:00");
									return false;
								}
								
								if(insertEnd.indexOf(':')>0 || insertEnd.indexOf('：')>0 ){
								}else{
									alert("请按标准填写时间！例：08:30或18:00");
									return false;
								}
								
								
								var startArr = null;
								var endArr = null;
								if(insertStart.indexOf(':')>0){
									startArr = insertStart.split(":");
								}
								if(insertEnd.indexOf(':')>0){
									endArr = insertEnd.split(":");
								}
								
								if(insertStart.indexOf('：')>0){
									startArr = insertStart.split("：");
								}
								if(insertEnd.indexOf('：')>0){
									endArr = insertEnd.split("：");
								}
								
								
								
								//var reg = new RegExp("^\d{n}$");
								 var regu =/^[0-9]*$/;
								 var reg = new RegExp(regu);
								
							    if(!reg.test(startArr[0]) || !reg.test(startArr[1]) || !reg.test(endArr[0]) || !reg.test(endArr[1])){
							        alert("请输入数字!");
							        return false;
							    }
							    
							    
							    insertStart =  startArr[0]+":"+startArr[1];
							    insertEnd = endArr[0]+":"+endArr[1];
								
// 								if(start>=end || start<0 || end<0){
// 									alert('请选择正确的时间');
// 									return false;
// 								}
								

								

								var custId = 0;
								$('.syllabus_branch_select span').each(
										function() {
											if ($(this).hasClass('on')) {
												custId = $(this).attr('id');
											}
										});

								$.ajax({cache : true,
										type : "POST",
										url : "/course/arrange/insert.action?custId="
													+ custId
													+ "&startTime="
													+ insertStart
													+ "&endTime="
													+ insertEnd
													+"&coachId="
													+$('#insertCoach').val(),
											data : $('#insert').serialize(),
											dataType : "json",
											success : function(res) {
												console.log(res);
												if (res.status == 1) {
													//$("#shade").remove();
													//$('#saveInsert').parents(".fillSya").hide();
													$('#insertStart').val('');
													$('#insertEnd').val('');
													$('#insertCoach').val('');
													$('#address').val('');
													$('#peopleNum').val('');
													
													
													window.location.reload();
												}
											},
											error : function(res) {

											}
										});

							});

			$('#saveUpdate').click(function() {
				
// 				var start = $('#updateStart').val()-0;
// 				var end = $('#updateEnd').val()-0;
// 				if(start>=end||start<0||end<0){
// 					alert('请选择正确的时间');
// 					return false;
// 				}
				

				var updateStart = $('#updateStart').val();
				var updateEnd = $('#updateEnd').val();
				
				if(updateStart.indexOf(':')>0 || updateStart.indexOf('：')>0 ){
				}else{
					alert("请按标准填写时间！例：08:30或18:00");
					return false;
				}
				
				if(updateEnd.indexOf(':')>0 || updateEnd.indexOf('：')>0 ){
				}else{
					alert("请按标准填写时间！例：08:30或18:00");
					return false;
				}
				
				
				var startArr = null;
				var endArr = null;
				if(updateStart.indexOf(':')>0){
					startArr = updateStart.split(":");
				}
				if(updateEnd.indexOf(':')>0){
					endArr = updateEnd.split(":");
				}
				
				if(updateStart.indexOf('：')>0){
					startArr = updateStart.split("：");
				}
				if(updateEnd.indexOf('：')>0){
					endArr = updateEnd.split("：");
				}
				
				
				
				//var reg = new RegExp("^\d{n}$");
				 var regu =/^[0-9]*$/;
				 var reg = new RegExp(regu);
				
			    if(!reg.test(startArr[0]) || !reg.test(startArr[1]) || !reg.test(endArr[0]) || !reg.test(endArr[1])){
			        alert("请输入数字!");
			        return false;
			    }
			    
			    
			    updateStart =  startArr[0]+":"+startArr[1];
			    updateEnd = endArr[0]+":"+endArr[1];
				
				
				console.log($('#updateStart').val());
				
				$.ajax({
					cache : true,
					type : "POST",
					url : "/course/arrange/update.action?startTime="
							+ updateStart
							+ "&endTime="
							+ updateEnd
							+ "&updateCoach="
							+ $('#updateCoach').val(),
					data : $('#update').serialize(),
					dataType : "json",
					success : function(res) {
						console.log(res.status);
						if (res.status == 1) {
							window.location.reload();
						}else{
							alert('网络异常，请稍后再试！');
						}
					},
					error : function(res) {

					}
				});
			});

			$(".delete").click(function() {
				$("#shade").remove();
				$(this).parents(".fillSya").hide();
				window.location.reload();
			})
			
			$(".guanbi").click(function(){
            $("#shade").hide();
            $(this).parents(".fillSya").hide();
        })
        

		})
		

		
		$("#select_id").change(function(){
			
			if($(this).val()==0){
				return false;
			}
			var sdate=new Date();
			var edate= new Date();
			
			var stimems=sdate.getTime()+($(this).val()*7*24*60*60*1000);
			sdate.setTime(stimems);
			var startDate=sdate.getFullYear()+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
			
			var etimems=edate.getTime()+($(this).val()*7*24*60*60*1000+7*24*60*60*1000);
			edate.setTime(etimems);
			var endDate = edate.getFullYear()+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
			
			
			if(confirm("确定要生成"+startDate+"至"+endDate+"的课程安排吗？")){
				
				$.ajax({
					cache : true,
					type : "POST",
					url : "/course/arrange/build.action",
					data : {syscustid:$('#select_id').attr('class'),weekstatus:$(this).val()},
					dataType : "json",
					success : function(res) {
					
						console.log(res);
						if(res.status==1){
							alert("同步成功！");
							window.location.reload();
						}
						

					},
					error : function(res) {

					}
				});

				
		     }else{
		    	 
		 
		    }
			
			//alert($(this).val());
		
		});
	</script>

</body>
</html>