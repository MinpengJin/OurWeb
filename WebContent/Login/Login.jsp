<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,java.awt.*,java.awt.image.*,javax.imageio.*"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cahce">
		<meta http-equiv="cache-control" content="no-cahce">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
				
		<link rel="stylesheet" type="text/css" href="/OurWeb/Login/Login.css">
		<title>教师考评系统</title>
		
		<script>
			function Refresh(img){
				img.src="/OurWeb/Images/VerificationCode.jsp?time="+new Date().getTime();
			}
		</script>
		
	</head>
	
	
	<body>
		<img alt="页面无法加载" src="/OurWeb/Images/NEU4.jpg" height="350px" width="100%">
		<div class="Title">
			<h1 class="TitleText">欢迎使用教师考评系统</h1>
		</div>
		<hr>
		<div class="Overall">
			<form class="Form" name="login" method="POST" action="/OurWeb/LoginHandle">
				<font style="color:red">
				<%
				if(request.getAttribute("Info")!=null&&request.getAttribute("Info")!=""){
					out.println(request.getAttribute("Info")); 
				}
				%>
				</font>
				<div class="Content1">
					用户名：<input class="UserInput" name="UserName" type="text"><br>
					 密  码：
					<input class="PasswordInput" name="Password" type="password"><br>
				</div>
				<div class="Content2">
					验证码：<input class="VerifiInput" name="VerifiCode" type="text">
					<img class="VerifiPic" alt="无法显示验证码" src="/OurWeb/Images/VerificationCode.jsp" 
					style="cursor:pointer;" onclick="Refresh(this)">
				</div><br>
				用户：<input class="Check" type="radio" checked="checked" name="sex" value="User">&nbsp;
				管理员：<input class="Check" type="radio" name="sex" value="Administrator"><br>
				<br>
				<input class="EnterButton" name="Submit" type="Submit" value="登入">
			</form>
		</div>
		<hr>
	</body>
</html>