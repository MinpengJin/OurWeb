<%@page import="com.OurWeb.service.impl.TeacherServiceImpl"%>
<%@page import="com.OurWeb.database.impl.ConnectDatabaseImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.OurWeb.entity.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="/OurWeb/Css/Teacher.css">
		<link rel="stylesheet" type="text/css" href="/OurWeb/jquery/jquery-ui-1.10.4.custom/css/base/jquery-ui-1.10.4.custom.min.css">
		
		<title>用户界面</title>
	
		<!-- 历史通知消息点击标题展开内容 -->
		<script src="/OurWeb/jquery/jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
		<script src="/OurWeb/jquery/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				
				//历史公告动画
				$(".his-content").slideUp();
				$(".history ul li").click(function(){
					var index = $(this).index();
					$("ul .history").find("li").eq(index).find(".his-content").slideToggle("slow");
				})
				
				//添加论文浮动窗口
				$('.add').click(function(){  
			        $('.popover-mask').fadeIn(100);  
			        $('.popover').slideDown(200);  
			    })  
			    $('.popover-head .close').click(function(){  
			        $('.popover-mask').fadeOut(100);  
			        $('.popover').slideUp(200);  
			    })  
			   
			    //删除确认浮动窗口
			    $('.delete').click(function(){
			    	$('.popover-mask2').fadeIn(100);
			    	$('.deleteConfirm').slideDown(200);
			    })
			    $('.confirm-head .close2').click(function(){
			    	$('.popover-mask2').fadeOut(100);
			    	$('.deleteConfirm').slideUp(200);
			    })
			    
			    /*
			    $('.source').change(function(){
			    	var score = $('.source').val();
			    	var selectedSource = $('.source option:selected').text();
			    })
			    */
		    	
			    //日期选择器
			    $(function() {
    				$( "#datepicker" ).datepicker();
  				});
			    
				//点击确认，删除数据
			    $('.checkbtn').click(function(){
			    	var s = document.getElementsByName("deleteItem");
			    	var items = '';
			    	for(var i=0;i<s.length;i++){
			    		if(s[i].checked){
			    			items += s[i].value+',';
			    		}
			    	}
			    	if(!items){
			    		alert("请选择要删除的数据！");
			    		return false;
			    	}
			    	var psw = $('.checkpsw').val();
			    	
			    	var form=document.forms[0];
			        form.action="DeleteThesisServlet?items="+items+"&psw="+psw;
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
   	 	else{
			
		}
        %>
        
	</head>
	
	<body>
		<%
		Teacher teacher = (Teacher) session.getAttribute("tempTeacher");
		int id = teacher.getId();
		TeacherServiceImpl teacherService = new TeacherServiceImpl();
		Teacher tempTeacher = teacherService.getTeacher(id);
		%>
		
		<div class="box">
		    <ul class="nav">
		    
		    
		    	<!-- 考核公告 -->
		        <li class="navI">
		            <input class="navI-radio" name="nav" type="radio" id="announcement">
		            <label class="navI-tit" for="announcement">考核公告</label>  
		            <div class="navI-txt">
			            <div class="background1">
			            	<div class="recent">
				            	<%
		            			Announcement latestAnno = teacherService.getLatest();
			            		out.print("<p class='rec-title'>");
			            		out.print(latestAnno.getTitle());
			            		out.print("</p>");
			            		out.print("<p class='rec-content'>");
			            		out.print(latestAnno.getContent());
			            		out.print("</p>");
			            		out.print("<p class='rec-time'>");
			            		out.print(latestAnno.getDate());
			            		out.print("</p>");
				            	%>
			            	</div>
			            	<div class="history">
			            		<ul>
			            			<%
			            			Announcement[] pastAnno = teacherService.getPast();
			            			int number = pastAnno.length;
			            			for(int i = 0;i < number;i++){
			            				out.print("<li>");
			            				out.print("<div class='his-title'>");
			            				out.print("<p class='p1'>");
			            				out.print(pastAnno[i].getTitle());
			            				out.print("</p>");
			            				out.print("<p class='p2'>");
			            				out.print(pastAnno[i].getDate());
			            				out.print("</p>");
			            				out.print("</div>");
			            				out.print("<div class='his-content'>");
			            				out.print(pastAnno[i].getContent());
			            				out.print("</div>");
			            				out.print("</li>");
			            			}
			            			%>
			            		</ul>
			            	</div>
			            </div>
	            	</div>        
		        </li>
		        
		        
		   		<!-- 个人成果管理 -->
		        <li class="navI">
		            <input class="navI-radio" name="nav" type="radio" id="result" checked>
		            <label class="navI-tit" for="result">个人成果管理</label>            
		            <div class="navI-txt ml1">
		            	<div class="background2">
		            	
			            	<!-- 教师基本信息模块 -->
			            	<div class="teacherInfo">
				            	<table>
				            		<caption>基本信息</caption>
				            		<tr>
					            		<td class="first"><font color="black">被考核人：</font><%=tempTeacher.getName() %></td>
					            		<td><font color="black">职称：</font><%=tempTeacher.getTitle() %></td>
				            			<td><font color="black">考核类型：</font><%=tempTeacher.getExamType() %></td>
				            			<td><font color="black">考核时间：</font><%=tempTeacher.getExamTime() %></td>
				            		</tr>
				            		<tr>
					            		<td class="first"><font color="black">目标考核结果：</font></td>
				            			<td><font color="black">实际考核结果：</font></td>
					            		<td><font color="black">目标工作量：</font><%=tempTeacher.getTargetWork() %></td>
				            			<td><font color="black">当前工作量：</font><%=tempTeacher.getCurrentWork() %></td>
				            		</tr>			            	
				            	</table>
			            	</div>
			            	
			            	<!-- 奖惩信息 -->
		            		<div class="rewardInfo">
			            		<p>奖惩信息</p><br>	
		            		</div>
		            		
			            	
			            	<!-- 成果上传 -->
			            	<a class="add" href="javascript:;"><img src="/OurWeb/Images/addition.png"></a>
			            	<a class='delete' href='javascript:;'><img src='/OurWeb/Images/trash.png'></a>
				            <div class="popover">
				            	<div class="popover-head">
				            		<p>添加成果</p>
				            		<a class="close" href="javascript:;"><img src="/OurWeb/Images/close.png"></a>  
				            	</div>
				            	<div class="popover-body">
				            		<form id="addThesis" action="/OurWeb/AddThesisServlet" method="post">
					            		<label class="label">论文题目：</label><input class="input title" type="text" name="thesisTitle"><br>
					            		<label class="label">收录情况：</label>
					            		<select class="source" name="source">
					            		<%
					            		ThesisSource[] thesisSource = teacherService.getSource();
					            		int scrNum = thesisSource.length;
					            		for(int i=0;i<scrNum;i++){
					            			out.print("<option value='");out.print(thesisSource[i].getName());out.print("'>");
					            			out.print(thesisSource[i].getName()+"("+thesisSource[i].getScore()+")");
					            			out.print("</option>");
					            		}
					            		%>
					            		</select>
					            		<label class="label l1">论文种类：</label>
					            		<select class="category" name="category">
					            		<%   
					            		ThesisCategory[] thesisCat = teacherService.getCategory();
					            		int catNum = thesisCat.length;
					            		for(int i=0;i<catNum;i++){
					            			out.print("<option value='");out.print(thesisCat[i].getName());out.print("'>");
					            			out.print(thesisCat[i].getName());out.print("</option>");
					            		}
					            		%>
					            		</select><br>
					            		<label class="label">收录编号：</label><input class="input" type="text" name="thesisNum"><br>
				            			<label class="label">第一作者：</label><input class="input" type="text" name="firstAuthor"><br>
				            			<label class="label">第二作者：</label><input class="input" type="text" name="secAuthor"><br>
				            			<label class="label">发表时间：</label><input class="input" id="datepicker" type="text" name="publichDate"><br>
				            			<input class="addThesis" type="submit" value="添加">
				            		</form>
				            	</div>
				            </div>
				            <div class="popover-mask"></div>

			            	<!-- 删除确认 -->
			            	<div class="deleteConfirm">
			            		<div class="confirm-head">
			            			<p>确认删除</p>
			            			<a class="close2" href="javascript:;"><img src="/OurWeb/Images/close.png"></a>
			            		</div>
			            		<div class="confirm-body">
			            			<label>请输入登入密码：</label>
			            			<input class="checkpsw" type="password"><br>
			            			<input class="checkbtn" type="button" value="确认删除">
			            		</div>
			            	</div>
			            	<div class="popover-mask2"></div>

				            <!-- 任务列表 -->
			            	<div class="upload">
				            	<div class="table-head">
				            		<table>
				            			<caption>任务列表</caption>
				            			<thead>
					            			<tr>
					            				<th class="head">论文编号</th>
					            				<th class="head">论文类别</th>
					            				<th class="head h1">论文题目</th>
					            				<th class="head">作者排名</th>
					            				<th class="head h2">发表时间</th>
					            				<th class="head">贡献值</th>
					            				<th class="head h3"></th>
					            			</tr>
				            			</thead>
				            		</table>
				            	</div>
				            	<div class="table-body">
				            		<table>
				            			<tbody>
				            			<% 
				            			TeacherAchiv[] achievements = teacherService.getAchiv(id);
				            			int achivNum = 0,dataNum = 0;
				            			if(achievements!=null){
					            			achivNum = achievements.length;
				            				while(dataNum < achivNum){
					            				out.print("<tr>");
					            				out.print("<td class='body'>");out.print(achievements[dataNum].getThesisNum());out.print("</td>");
					            				out.print("<td class='body'>");out.print(achievements[dataNum].getCategory());out.print("</td>");
					            				out.print("<td class='body b1'>");out.print(achievements[dataNum].getTitle());out.print("</td>");
					            				out.print("<td class='body'>");out.print(achievements[dataNum].getAuthorOrder());out.print("</td>");
					            				out.print("<td class='body b2'>");out.print(achievements[dataNum].getPublishDate());out.print("</td>");
					            				out.print("<td class='body'>");out.print(achievements[dataNum].getScore());out.print("</td>");
					            				out.print("<td class='body b3'>");
					            				out.print("<input type='checkbox' name='deleteItem' value='");
					            				out.print(achievements[dataNum].getId());out.print("'>");
					            				out.print("</td>");
					            				out.print("</tr>");
					            				dataNum++;
				            				}				            				
				            			}
				            			%>
				            			</tbody>
				            		</table>
				            	</div>
			            		&nbsp;&nbsp;&nbsp;
			            		<font color="lightskyblue">
			            		<%
			            		if(dataNum>0){
			            			String k = "共"+dataNum+"条上传数据";
			            			out.print(k);
			            		}else{
			            			out.print("当前没有上传数据");
			            		}
			            		%>
			            		</font>
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
		            	<input class="signoff" type="button" value="退出登录">
		            	<div class="background3">
				            <form class="changePassword" action="/OurWeb/TeacherPswServlet" method="post">
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