<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="/OurWeb/User/User.css">
		<title>用户界面</title>
		
		<!-- 历史通知消息点击标题展开内容 -->
		<script src="/OurWeb/jquery/jquery-1.10.2.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$(".his-content").slideUp();
			})
			$(document).ready(function(){
				$(".history ul li").click(function(){
					var index = $(this).index();
					$("ul .history").find("li").eq(index).find(".his-content").slideToggle("slow");
				})
			})
		</script>
		
   	 	<%
   	 	/*判断密码是否修改成功*/
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
		/*连接数据库*/
		String temp = (String)request.getAttribute("ID");
		int id = Integer.parseInt(temp);
		Class.forName("com.mysql.jdbc.Driver");
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourweb","root","13958548266");
		
		/*查找教师数据表*/
		String sql = "select * from teacher where id =" + id;
		ResultSet result = connect.prepareStatement(sql).executeQuery();
		/*查找公告数据表*/
		String sql2 = "select * from announcement";
		ResultSet result2 = connect.prepareStatement(sql2).executeQuery();
		/*查找教师成果数据表*/
		String sql3 = "select teacher_achievement.number,achievement.*"+
		" from teacher_achievement,achievement where teacher_achievement.teacher_id ="+id
		+" and teacher_achievement.achievement_id=achievement.id";
		ResultSet result3 = connect.prepareStatement(sql3).executeQuery();
		
		/*获取教师数据表数据*/
		String name,title,examType,passWord;
		int targetWork;
		result.next();
		name = result.getString(2);
		title = result.getString(3);
		examType = result.getString(4);
		java.util.Date date = result.getDate(5);
		passWord = result.getString(6);
		targetWork = result.getInt(7);
		
		%>
		
		<div class="box">
		    <ul class="nav">
		    
		    
		    	<!-- 考核公告 -->
		        <li class="navI">
		            <input class="navI-radio" name="nav" type="radio" id="announcement" checked>
		            <label class="navI-tit" for="announcement">考核公告</label>  
		            <div class="navI-txt">
			            <div class="background1">
			            	<div class="recent">
				            	<%
				            	result2.next();
			            		out.print("<p class='rec-title'>");
			            		out.print(result2.getString(2));
			            		out.print("</p>");
			            		out.print("<p class='rec-content'>");
			            		out.print(result2.getString(3));
			            		out.print("</p>");
			            		out.print("<p class='rec-time'>");
			            		out.print(result2.getString(4));
			            		out.print("</p>");
				            	%>
			            	</div>
			            	<div class="history">
			            		<ul>
			            			<%
			            			while(result2.next()){
			            				out.print("<li>");
			            				out.print("<div class='his-title'>");
			            				out.print("<p class='p1'>");
			            				out.print(result2.getString(2));
			            				out.print("</p>");
			            				out.print("<p class='p2'>");
			            				out.print(result2.getString(4));
			            				out.print("</p>");
			            				out.print("</div>");
			            				out.print("<div class='his-content'>");
			            				out.print(result2.getString(3));
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
		            <input class="navI-radio" name="nav" type="radio" id="result">
		            <label class="navI-tit" for="result">个人成果管理</label>            
		            <div class="navI-txt ml1">
		            	<div class="background2">
		            	
			            	<!-- 教师基本信息模块 -->
			            	<div class="teacherInfo">
				            	<table>
				            		<caption>基本信息</caption>
				            		<tr>
					            		<td class="first"><font color="black">被考核人：</font><%=name %></td>
					            		<td><font color="black">职称：</font><%=title %></td>
				            			<td><font color="black">考核类型：</font><%=examType %></td>
				            			<td><font color="black">考核时间：</font><%=date %></td>
				            		</tr>
				            		<tr>
					            		<td class="first"><font color="black">目标考核结果：</font></td>
				            			<td><font color="black">实际考核结果：</font></td>
					            		<td><font color="black">目标工作量：</font><%=targetWork %></td>
				            			<td><font color="black">当前工作量：</font></td>
				            		</tr>			            	
				            	</table>
			            	</div>
			            	
			            	<!-- 奖惩信息 -->
		            		<div class="rewardInfo">
			            		<p>奖惩信息</p><br>
		            			
		            		</div>
			            	
			            	<!-- 成果上传模块 -->
				            <img class="add" src="/OurWeb/Images/addition.png">
			            	<div class="upload">
				            	<div class="table-head">
				            		<table>
				            			<caption>任务列表</caption>
				            			<thead>
					            			<tr>
					            				<th class="head"></th>
					            				<th class="head">任务类别</th>
					            				<th class="head h1">子类别</th>
					            				<th class="head h2">成果内容</th>
					            				<th class="head">级别</th>
					            				<th class="head">数量</th>
					            				<th class="head h3">贡献值</th>
					            			</tr>
				            			</thead>
				            		</table>
				            	</div>
				            	<div class="table-body">
				            		<table>
				            			<tbody>
				            			<%
				            			int num = 7;
				            			int dataNum = 0;
			            				while(result3.next()){
				            				out.print("<tr>");
				            				out.print("<td class='body'>");out.print("</td>");
				            				out.print("<td class='body'>");out.print(result3.getString(3));out.print("</td>");
				            				out.print("<td class='body b1'>");out.print(result3.getString(4));out.print("</td>");
				            				out.print("<td class='body b2'>");out.print(result3.getString(5));out.print("</td>");
				            				out.print("<td class='body'>");out.print(result3.getString(6));out.print("</td>");
				            				out.print("<td class='body'>");out.print(result3.getInt(1));out.print("</td>");
				            				out.print("<td class='body b3'>");out.print(result3.getInt(7));out.print("</td>");
				            				out.print("</tr>");
				            				dataNum++;
			            				}
				            			connect.close();
				            			%>
				            			</tbody>
				            		</table>
				            	</div>
			            		&nbsp;&nbsp;&nbsp;
			            		<font color="lightskyblue">
			            		<%
			            		if(dataNum>0){
			            			String k = "从1到"+dataNum+",共"+dataNum+"条上传数据";
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
		            	<div class="background3">
				            <form class="changePassword" action="/OurWeb/chagePassword" method="post">
					           	 用户名：<input class="container con1" type="text" name="id" value=<%=temp %>><br>
					           	 当前密码：<input class="container con2" type="text" name="nowPassword" value=<%=passWord %>><br>
					           	 新密码：<input class="container con3" type="password" name="newPassword"><br>
					           	 确认密码：<input class="container con4" type="password" name="checkPassword"><br>
					           	 <input class="change" type="submit" value="修改密码"><br>
				            </form>
		            	</div>
	            	</div>
		        </li>
		        
		    </ul>   
		</div>
		
	</body>
</html>