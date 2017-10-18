<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="funcs" uri="http://java.sun.com/jsp/jstl/myfunctions"%>
<jsp:useBean id="jspConstants" class="com.lol.common.JspConstants" />
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<title>业务员详情</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link type="text/css" rel="stylesheet" href="/static/css/base.css" />
		<link type="text/css" rel="stylesheet" href="/static/css/main.css" />
		<link type="text/css" rel="stylesheet" href="/static/css/left.css" />
		<link type="text/css" rel="stylesheet" href="/static/third/easyui/themes/default/validatebox.css" />
		<script type="text/javascript" src="/static/js/jquery.js"></script>
		<script type="text/javascript" src="/static/js/base.js"></script>
		<script type="text/javascript" src="/static/js/left.js"></script>
		<script type="text/javascript" src="/static/third/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="/static/js/jquery.validatebox.rules.js"></script> 
		<script type="text/javascript">
			$(function(){
		
			    $(".bindAlipay").click(function(){
			        showShadow();
			        $(".fliiAlipay").show();
			    });
			    
			    $("#deleteSubmit").click(function(){
			    	if(confirm("确定删除")){
			    		var id = $(this).attr("data-userId");
			    		$.ajax({
				    		type:"post",
				    		url:"/settings/user/del.action",
				    		data:{"id":id},
				    		dataType:"json",
				    		cache:false,
				    		async:false,
				    		beforeSend:function(xhr){
				    			//
				    		},
				    		success:function(res){
				    			if(res === undefined){
				    				alert("操作失败请稍后再试");
				    			}else{
				    				if(res.status == 0){
				    					alert("操作成功！");
				    					top.location.href="/settings/user/list.action";
				    				}else{
				    					alert(res.msg);
				    				}
				    			}
				    		},
				    		error:function(res){
				    			alert("系统异常，请稍后再试！");
				    		}
				    	});
			    	}
			    });
			    
			    $("#setpassSubmit").click(function(){
			    	if($('#f1').form('enableValidation').form('validate')){
			    		if($('#password').val() != $('#repassword').val()){
			    			alert("重复新密码错误!");
			    			return;
			    		}
			    		$.ajax({
				    		type:"post",
				    		url:"/settings/user/password/reset.action",
				    		data:$("#f1").serialize(),
				    		dataType:"json",
				    		cache:false,
				    		async:false,
				    		beforeSend:function(xhr){
				    			//
				    		},
				    		success:function(res){
				    			if(res === undefined){
				    				alert("操作失败请稍后再试");
				    			}else{
				    				if(res.status == 0){
				    					alert("操作成功！");
				    					$(".close").click();
				    				}else{
				    					alert(res.msg);
				    				}
				    			}
				    		},
				    		error:function(res){
				    			alert("系统异常，请稍后再试！");
				    		}
				    	});
			    	}
			    });
			    
				$("#selecedAll").click(function(){
					var toggle = $(this).attr("data-toggle");
					if(toggle == "off"){
						$("i").addClass('on');
						$(this).attr("data-toggle","on");
					}else{
						$("i").removeClass('on');
						$(this).attr("data-toggle","off");
					}
					
				});
			    
			    $(".changeauthority").click(function(){
			        $.ajax({
			    		type:"post",
			    		url:"/settings/user/permission/list.action",
			    		data:{'id':'${user.id}'},
			    		dataType:"json",
			    		cache:false,
			    		async:false,
			    		beforeSend:function(xhr){
			    			//
			    		},
			    		success:function(res){
			    			if(res === undefined){
			    				alert("操作失败请稍后再试");
			    			}else{
			    				if(res.status == 0){
			    					var node1 = res.data.permissions;
			    					if(node1 === undefined){
			    						$("#permsTree").html("未获取到数据！");
			    						return;
			    					}
			    					var selecedAll;
			    					var html = "";
			    					for(var x=0;x<node1.length;x++){
			    						html += '<div class="changeauthority_select_box_line_ul w100">';
			    						if(node1[x].checked){
			    							html += '<div class="select_title"><i data-permId="'+node1[x].id+'" class="on"></i>'+node1[x].text+'</div>';
	    								}else{
			    							html += '<div class="select_title"><i data-permId="'+node1[x].id+'"></i>'+node1[x].text+'</div>';
			    							selecedAll = 'off';
	    								}
			    						
			    						var node2=node1[x].children;
			    						if(node2 != undefined && node2.length>0){
				    						html += '<div class="select_ul">';
			    							for(var y=0;y<node2.length;y++){
					    						html += '<ul>';
					    						if(node2[y].checked){
					    							html += '<li class="select_sub_title"><i data-permId="'+node2[y].id+'" data-parentId="'+node2[y].attributes['parentId']+'" class="on"></i>'+node2[y].text+'</li>';
			    								}else{
					    							html += '<li class="select_sub_title"><i data-permId="'+node2[y].id+'" data-parentId="'+node2[y].attributes['parentId']+'"></i>'+node2[y].text+'</li>';
			    								}
					    						
					    						var node3 = node2[y].children;
					    						if(node3 != undefined && node3.length>0){
						    						html += '<li>';
							    					html += '<div>';
					    							for(var z=0;z<node3.length;z++){
					    								if(node3[z].checked){
							    							html += '<p><i data-permId="'+node3[z].id+'" data-parentId="'+node3[z].attributes['parentId']+'" class="on"></i>'+node3[z].text+'</p>';
					    								}else{
							    							html += '<p><i data-permId="'+node3[z].id+'" data-parentId="'+node3[z].attributes['parentId']+'"></i>'+node3[z].text+'</p>';
							    							selecedAll = 'off';
					    								}
						    						}
							    					html += '</div>';
							    					html += '</li>';
					    						}
					    						html += '</ul>';
				    						}
					    					html += '</div>';
			    						}
			    						html += '</div>';
			    					}
	                                $("#permsTree").html(html);
	                                
	                                var custs = res.data.subCusts;
			    					if(custs != undefined){
			    						var custhtml = '';
			    						for(var i=0;i<custs.length;i++){
			    							if(custs[i].checked){
			    								custhtml += '<li><i data-subCustId="'+custs[i].id+'" class="on"></i>'+custs[i].text+'</li>';
			    							}else{
			    								custhtml += '<li><i data-subCustId="'+custs[i].id+'"></i>'+custs[i].text+'</li>';
			    								selecedAll = 'off';
			    							}
			    						}
			    						$("#subCust").html(custhtml);
			    					}
			    					
			    					$("i").click(function(){
			    						//如果是一二级节点，更新所属的子节点。
			    						if($(this).parent().hasClass('select_title') || $(this).parent().hasClass('select_sub_title')){
			    							if($(this).hasClass('on')){
			    								$(this).parent().next().find('i').removeClass('on');
			    							}else{
			    								$(this).parent().next().find('i').addClass('on');
			    							}
			    						}
			    						//切换
			    	                	$(this).toggleClass('on');
			    	                	//如果被选中时，同时选中父级权限。
										if($(this).hasClass('on')){
											var parentId = $(this).attr('data-parentId');
											if(parentId != undefined){
												var parentIds = parentId.split('/');
					    						parentIds.forEach(function (item, index) {
					    						    console.log(item);
					    							$('i[data-permId="'+item+'"]').addClass('on');
					    						});
											}
			    						}
			    	                	
			    	                });
			    					
			    					if(selecedAll == 'off'){
			    						$("#selecedAll").attr("data-toggle", "off");
			    					}
	                                
	                                showShadow();
	            			        $(".changeauthorityBox").show();
			    				}else{
			    					alert(res.msg);
			    				}
			    			}
			    		},
			    		error:function(res){
			    			alert("系统异常，请稍后再试！");
			    		}
			    	});
			    });
			    
			 	// 取消关闭弹框
			    $(".close").click(function(){
			        $(this).parents(".closebox").hide();
			        $("#shade").remove();
			    });
			 	//提交
			    $("#permSubmit").click(function(){
			    	var permSelected = new Array();
			    	var subCustSelected = new Array();
			    	$("i[class='on']").each(function(){
			    		var permId = $(this).attr("data-permId");
			    		var subCustId = $(this).attr("data-subCustId");
			    		if(permId != undefined){
			    			permSelected.push(permId);
			    		}
						if(subCustId != undefined){
							subCustSelected.push(subCustId);
			    		}
			    	});
			    	
			    	$.ajax({
			    		type:"post",
			    		url:"/settings/user/permission/update.action",
			    		data:{'id':'${user.id}','permSelected':JSON.stringify(permSelected),'subCustSelected':JSON.stringify(subCustSelected)},
			    		dataType:"json",
			    		cache:false,
			    		async:false,
			    		beforeSend:function(xhr){
			    			//
			    		},
			    		success:function(res){
			    			if(res === undefined){
			    				alert("操作失败请稍后再试");
			    			}else{
			    				if(res.status == 0){
			    					alert("操作成功！");
	                                $(".close").click();
			    				}else{
			    					alert(res.msg);
			    				}
			    			}
			    		},
			    		error:function(res){
			    			alert("系统异常，请稍后再试！");
			    		}
			    	});
			        
			    });
			});
		</script>
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
					<jsp:param name="nvaIndex" value="35" />
					<jsp:param name="nvaParentIndex" value="26" />
				</jsp:include>
				<!--左侧导航：结束-->
				<div class="rightContent">
					<h3 class="i_title">业务员详情</h3>
					<div class="province" style="height:25px;"></div>
					<div class="member_item_table">
						<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
							<tr>
								<th bgcolor="#f3f5f6" height="36">账号</th>
								<th bgcolor="#f3f5f6" height="36">姓名</th>
								<th bgcolor="#f3f5f6" height="36">手机号</th>
								<th bgcolor="#f3f5f6" height="36">最后登录时间</th>
								<th bgcolor="#f3f5f6" height="36">操作</th>
							</tr>
							<tr>
								<td>${user.username}</td>
								<td>${user.realname}</td>
								<td>${user.mobile}</td>
								<td><fmt:formatDate value="${user.lastTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td data-innerHTML-default="无">
									<shiro:hasPermission name="settings/user/password:reset">
										<a href="javascript:;" class="bindAlipay">修改密码</a>
									</shiro:hasPermission>
									<c:if test="${user.isManager != 1}">
										<shiro:hasPermission name="settings/user/permission:list">
											<a href="javascript:;" class="changeauthority">修改权限</a>
										</shiro:hasPermission>
									</c:if>
								</td>
							</tr>
						</table>
					</div>
					<div class="province" style="height:25px;"></div>
					<div class="member_item_table">
						<table cellpadding="0" cellspacing="0" border="0" width="100%" class="lis-table">
							<tr>
								<th bgcolor="#f3f5f6" height="36">时间</th>
								<th bgcolor="#f3f5f6" height="36">动作</th>
							</tr>
							<c:forEach items="${userLogs}" var="userLog">
								<tr>
									<td><fmt:formatDate value="${userLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>${userLog.logMemo}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div class="btns-area">
						<shiro:hasPermission name="settings/user:del">
							<c:if test="${user.isManager != 1}">
								<a class="delete" href="javascript:void(0)" id="deleteSubmit" data-userId="${user.id}">删除账号</a>
							</c:if>
						</shiro:hasPermission>
					</div>
				</div>
		    </div>
		</div>
		<div class="fliiAlipay joinsalesmanBox closebox">
		    <div class="fliiAlipayCont">
		    	<form id="f1" name="f1" action="">
		    		<input id="id" name="id" type="hidden" value="${user.id}"/>
			        <ul>
			            <li><span class="fliiAlipayCont-n">原密码：</span><input id="originalPassword" name="originalPassword" type="text" class="fliipayInout easyui-validatebox" data-options="required:true,err:err,missingMessage:'请输入原密码'"></li>
			            <li><span class="fliiAlipayCont-n">密码：</span><input id="password" name="password" type="text" class="fliipayInout easyui-validatebox" data-options="required:true,validType:'minLength[6]',err:err,missingMessage:'请输入密码'" maxlength="18"></li>
			            <li><span class="fliiAlipayCont-n">重复密码：</span><input id="repassword" name="repassword" type="text" class="fliipayInout easyui-validatebox" data-options="required:true,validType:'same[\'password\']',err:err,missingMessage:'请再次输入密码'" maxlength="18"></li>
			        </ul>
		        </form>
		        <div class="setauthBtn">
	                <em class="create_syllabus_qx close">取消</em>
	                <em class="create_syllabus_fs" id="setpassSubmit">确定</em>
	            </div>
		    </div>
		</div>
		<div class="changeauthorityBox closebox">
		    <div class="changeauthority_title">权限设置</div>
		    <div class="changeauthority_infor">
		        <span>账号：${user.username}</span>
		        <span>姓名：${user.realname}</span>
		        <span>电话：${user.mobile}</span>
		    </div>
		    <div class="changeauthority_select">
		        <div class="changeauthority_select_name">
		            <span>*</span>
		            <c:choose>
		            	<c:when test="${custInfo.parentType eq jspConstants.CUST_TYPE_HQ}">总部</c:when>
		            	<c:otherwise>${custInfo.custName}</c:otherwise>
		            </c:choose>
		            <div class="allchoose" id="selecedAll" data-toggle="on">全选</div>
		        </div>
		        <div class="changeauthority_select_box">
		            <div class="changeauthority_select_box_line" id="permsTree"></div>
		            
		            <div class="fendianshezhi">
		                <ul id="subCust">
		                    
		                </ul>
		            </div>
		            <div class="setauthBtn">
		                <em class="create_syllabus_qx close">取消</em>
		                <em class="create_syllabus_fs" id="permSubmit">确定</em>
		            </div>
		        </div>
		    </div>
		</div>
	</body>
</html>