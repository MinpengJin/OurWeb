<%@page import="com.OurWeb.entity.Administrator"%>
<%@page import="com.OurWeb.entity.Announcement"%>
<%@page import="com.OurWeb.dao.IAdminDao"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.OurWeb.entity.Teacher"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.OurWeb.dao.impl.AdminDaoImpl"%>
<%@page import="com.OurWeb.service.impl.TeacherServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="/OurWeb/Css/Administrator.css">
		<link rel="stylesheet" type="text/css" href="/OurWeb/jquery/jquery-ui-1.10.4.custom/css/base/jquery-ui-1.10.4.custom.min.css">
		<title>管理员</title>
		<script src="/OurWeb/jquery/jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
		<script src="/OurWeb/jquery/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				
				var searchId = $("input#searchId");
			  	
				searchId.focus(function(){
					$(this).css("background-color", "#cccccc");
				})
				
				searchId.blur(function(){
					$(this).css("background-color", "#fff");
				})
				//判断输入工号是否合法
				$("form #searchForm").submit(function(e){
					if(!/^\d{8}$/.test(searchId.val())){
						$("span#searchMess").html("输入的工号不合法！");
						searchId.val("");
						searchId.focus();
						e.preventDefault();
					};
				})
				//历史公告动画
				$(".his-content").slideUp();
				$(".alterAnn ul li").click(function(){
					var index = $(this).index();
					$("ul .alterAnn").find("li").eq(index).find(".his-content").slideToggle("slow");
				})		
				//日期选择器
		    	$(function() {
					$( "#datepicker" ).datepicker();
				});
				//查找教师
				$("table#table-body tr:nth-child(1)").css("background","#229922")
				$('.findTchr').click(function(){
					var id = $("#searchId").val();	
					var form = document.forms[0];
					form.action="FindById?searchId="+id;
					form.submit();
				})
				
				//删除所选公告
				$('.deleteAnno').click(function(){
					var s = document.getElementsByName("selectedAnno");
					var items='';
					for(var i=0;i<s.length;i++){
						if(s[i].checked){
							items+=s[i].value+',';
						}
					}
					if(!items){
						alert("请选择要删除的公告");
						return false;
					}
					var form = document.forms[0];
					form.action="DeleteAnnouncement?items="+items;
					form.submit();
				})
				
				//删除所选教师
				$('.deleteAll').click(function(){
					var s = document.getElementsByName("selectedItem");
					var items = '';
					for(var i=0;i<s.length;i++){
						if(s[i].checked){
							items += s[i].value+',';
						}
					}
					if(!items){
						alert("请选择要删除的教师");
						return false;
					}
					//跳转到删除教师页面处理
					var form = document.forms[0];
					form.action="DeleteTeacher?items="+items;
					form.submit();
				})
				
				//初始化所选教师
				$('.initialAll').click(function(){
					var s = document.getElementsByName("selectedItem");
					var items = '';
					for(var i=0;i<s.length;i++){
						if(s[i].checked){
							items += s[i].value+',';
						}
					}
					if(!items){
						alert("请选择要删除的数据");
						return false;
					}
					//跳转到初始化教师页面进行处理 
					var form = document.forms[0];
					form.action="InitialPassword?items="+items;
					form.submit();
				})
				
				$(".signoff").click(function(){
					window.location.href="/OurWeb/Login/Login.jsp";
				})
			})
		</script>
		<%
   	 	/*显示错误信息*/
   	 	if(request.getAttribute("Info")!=null&&request.getAttribute("Info")!=""){
   	 		String Info = (String) request.getAttribute("Info");
   	 	%>
   	 	<script type="text/javascript">
   	 		alert("<%=Info%>");
   	 	</script>
   	 	<% 
		}
		%>
		
	</head>
	<body>	
	<div class="box">
	    <ul class="nav">
	    	<!-- 发布公告 -->
	        <li class="navI">
	            <input class="navI-radio" name="nav" type="radio" id="announcement">
	            <label class="navI-tit" for="announcement">管理公告信息</label>  
	            <div class="navI-txt">
		            <div class="background1">
		            	<form class="addAnn" method="post" action="/OurWeb/AddAnnouncement">
			            	<label class="l1">标题：</label><input class="ann-title" name="ann-title" type="text">
			            	<label class="l2">日期：</label><input class="publish-time" name="publish-time" type="text"><br>
			            	<input class="ann-content" name="ann-content" type="text">
			            	<input class="add-button" type="submit" value="添加">
		            	</form>
		            	
		            	<a class="deleteAnno" href="javascript:;"><img src="/OurWeb/Images/trash.png"></a>
		            	<div class="alterAnn">
		            		<ul>
	            			<%
	            			IAdminDao adminDao = new AdminDaoImpl();
           					ArrayList<Announcement> allAnn;
           					allAnn = adminDao.getAnnouncements();
	            			for(int i = 0;i < allAnn.size();i++){
	            				out.print("<li>");
	            				out.print("<div class='his-title'>");
	            				out.print("<p class='p1'>");
	            				out.print(allAnn.get(i).getTitle());
	            				out.print("</p>");
	            				out.print("<p class='p2'>");
	            				out.print(allAnn.get(i).getDate());
	            				out.print("</p>");
	            				//out.print("<a class='pulldown' href='javascript:;'><img s='/OurWeb/Images/pulldown.png'></a>");
	            				out.print("<input type='checkbox' class='selectedAnno' name='selectedAnno' value='");
								out.print(allAnn.get(i).getId());out.print("'>");
	            				out.print("</div>");
	            				out.print("<div class='his-content'>");
	            				out.print(allAnn.get(i).getContent());
	            				out.print("</div>");
	            				out.print("</li>");
	            			}
	            			%>
		            		</ul>
		            	</div>
		            </div>
            	</div>        
	        </li>
	        
	        
	   		<!-- 管理教师信息 -->
	        <li class="navI">
	            <input class="navI-radio" name="nav" type="radio" id="result" checked>
	            <label class="navI-tit" for="result">管理教师信息</label>            
	            <div class="navI-txt ml1">
	            	<div class="background2">
		            	<div class="addTeacher">
		            		<div class="title"> 添加教师
								<span id="tchrInfoMess"> ${requestScope.tchrInfoMess }</span>
							</div>
							<form name="tchrInfoForm" id="tchrInfoForm" method="post" action="/OurWeb/AddNewTeacher">
								<label class="InfoLabel l1" for="tchrName">姓名：</label>
								<input type="text" name="tchrName" id="tchrName" value="${requestScope.tchrName }"/>
								<label class="InfoLabel l2" for="tchrTargetWork">目标工作量：</label>
								<input type="text" id="tchrTargetWork" name="tchrTargetWork">
								<select name="tchrExamType">
									<option value="科研教学型">科研教学型</option>
									<option value="教学科研型">教学科研型</option>
									<option value="教学型">教学型</option>
								</select><br>
								<label class="InfoLabel l3" for="tchrId">工号：</label>
								<input type="text" name="tchrId" id="tchrId" value="${requestScope.tchrId }"/>
								<label class="InfoLabel l4" for="tchrExamTime">考核时间：</label>
								<input type="text" id="datepicker" name="tchrExamTime">
								<select name="tchrTitle">
									<option value="教授">教授</option>
									<option value="副教授">副教授</option>
								</select><br>
								<input type="submit" name="tchrInfoSubmit" id="tchrInfoSubmit" value="添加">
							</form>
		            	</div>
         				<div class="changeInfo">
							<span id = "searchMess"> ${ requestScope.searchMess } </span>
							<div id="searchForm">
							   	<a class="deleteAll" href='javascript:;'><img src="/OurWeb/Images/trash.png"></a>
							   	<a class="initialAll" href='javascript:;'><img src="/OurWeb/Images/initial.png"></a>
								<label for="searchId">教师工号：</label>
								<input type="text" name="searchId" id="searchId" value = "${ requestScope.searchId }"/>
								<input class="findTchr" type="button" name="searchButton" value="查找">
							</div>
							<div class="table-head">
						   		<table>
						  			<caption>教师列表</caption>
						  			<thead>
							   			<tr>
							   				<th class="head">工号</th>
							   				<th class="head">姓名</th>
							   				<th class="head">职称</th>
							   				<th class="head">考核类型</th>
							   				<th class="head">考核时间</th>
							   				<th class="head">当前工作量</th>
							   				<th class="head h3"></th>
							   			</tr>
						  			</thead>
						  		</table>
					  		</div>
					  		<div class="table-body">
						  		<table id="table-body">
						   			<tbody>
						   			<% 
						   			ArrayList<Teacher> teachers;
						   			if(request.getAttribute("Teachers")!=null){
						   				teachers=(ArrayList<Teacher>)request.getAttribute("Teachers");
						   			}else{
							   			teachers = adminDao.getTeachers();
						   			}
						   			for(int i=0;i<teachers.size();i++){
						   				out.print("<tr>");
										out.print("<td class='body'>");out.print(teachers.get(i).getId());out.print("</td>");
										out.print("<td class='body'>");out.print(teachers.get(i).getName());out.print("</td>");
										out.print("<td class='body'>");out.print(teachers.get(i).getTitle());out.print("</td>");
										out.print("<td class='body'>");out.print(teachers.get(i).getExamType());out.print("</td>");
										out.print("<td class='body'>");out.print(teachers.get(i).getExamTime());out.print("</td>");
										out.print("<td class='body'>");out.print(teachers.get(i).getCurrentWork());out.print("</td>");
										out.print("<td class='body b3'>");
										out.print("<input type='checkbox' name='selectedItem' value='");
										out.print(teachers.get(i).getId());out.print("'>");
										out.print("</td>");
										out.print("</tr>");
						   			}
						   			%>
						   			</tbody>
						   		</table>
						   	</div>
			            	</div>
			            	
		            	</div>
	            	</div> 
		        </li>
		        
		        
		        <!-- 账号管理 -->
		        <li class="navI">
		            <input class="navI-radio" name="nav" type="radio" id="managment">
		            <label class="navI-tit" for="managment">账号管理</label>            
		            <div class="navI-txt ml2">
		            	<!-- 获取修改密码 -->
		            	<div class="background3">
		            	<input class="signoff" type="button" value="退出登录">
		            	<%
		            	Administrator tempAdmin =(Administrator) session.getAttribute("tempAdmin");
		            	int id = tempAdmin.getId();
		            	%>
				            <form class="changePassword" action="/OurWeb/PasswordServlet" method="post">
					           	 用户名：<input class="container con1" type="text" name="id" value=<%=id %> readonly><br>
					           	 当前密码：<input class="container con2" id="nowPassword" type="password" name="nowPassword"><br>
					           	 新密码：<input class="container con3" id="newPassword" type="password" name="newPassword"><br>
					           	 确认密码：<input class="container con4" id="checkPassword" type="password" name="checkPassword"><br>
					           	 <input class="change" type="submit" value="修改密码"><br>
				            </form>
		            	</div>
	            	</div>
		        </li>	        
		    </ul>   
		</div>
	</body>
</html>