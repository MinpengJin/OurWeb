<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="/OurWeb/User/User.css">
		<title>用户界面</title>

	</head>
	<body>
		<div class="box">
		    <ul class="nav">
		        <li class="navI">
		            <input class="navI-radio" name="nav" type="radio" id="kc" checked>
		            <label class="navI-tit" for="kc">考核公告</label>  
		            <div class="navI-txt">
		            
	            	</div>        
		        </li>
		        
		        <li class="navI">
		            <input class="navI-radio" name="nav" type="radio" id="xx">
		            <label class="navI-tit" for="xx">个人成果管理</label>            
		            <div class="navI-txt ml1">
		            
		            	<div class="teacherInfo">
			            	<table class="table">
			            		<tr>
				            		<td class="Info_text1">被考核人：张三</td>
				            		<td class="Info_text2">职称：教授</td>
			            			<td class="Info_text2">考核类型：教学科研型</td>
			            			<td class="Info_text2">考核时间：2018/1/1</td>
			            		</tr>
			            	</table>
			            	<table class="table">
			            		<tr>
				            		<td class="Info_text1">目标考核结果：</td>
				            		<td class="Info_text2">目标工作量：</td>
			            			<td class="Info_text2">当前工作量：</td>
			            		</tr>			            	
			            	</table>
			            	<h2 class="rewardTitle">奖惩信息：</h2>
		            		<div class="rewardInfo">
		            			
		            		</div>
		            	</div>
		            	
			            <img class="add" src="/OurWeb/Images/addition.png">
			            
		            	<div class="upload">
		            	
		            	</div>
		            	
	            	</div> 
		        </li>
		        
		        <li class="navI">
		            <input class="navI-radio" name="nav" type="radio" id="jn">
		            <label class="navI-tit" for="jn">账户管理</label>            
		            <div class="navI-txt ml2">
	            	</div>
		        </li>
		    </ul>   
		</div>
	</body>
</html>