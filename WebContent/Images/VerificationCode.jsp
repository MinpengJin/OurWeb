<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,java.awt.*,java.awt.image.*,javax.imageio.*"
    pageEncoding="UTF-8"%>
    
    <%
   	String path = request.getContextPath();
   	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cahce">
		<meta http-equiv="cache-control" content="no-cahce">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	
	<title>Insert title here</title>
	</head>
	<body>
		<%!
				//生成随机颜色
				public Color getColor(){
					Random random = new Random();
					int r = random.nextInt(256);
					int g = random.nextInt(256);
					int b = random.nextInt(256);
					return new Color(r,g,b);
				}
				//生成随机数
				public String getNum(){
					String str = "";
					Random random = new Random();
					for(int i = 0; i < 4; i++){
						str += random.nextInt(10);
					}
					return str;
				}
			%>·
			
			<%
				BufferedImage image = new BufferedImage(78,26,BufferedImage.TYPE_INT_RGB);
				Graphics g = image.getGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, 78, 26);
				//随机生成直线
				for(int i = 0; i < 20; i++){
					Random random = new Random();
					int x = random.nextInt(80);
					int y = random.nextInt(30);
					int x1 = random.nextInt(x + 10);
					int y1 = random.nextInt(y + 10);
					g.setColor(getColor());
					g.drawLine(x, y, x1, y1);
				}
				g.setFont(new Font("Microsoft YaHei",Font.BOLD,16));
				//产生4位随机数
				String CheckedNum = getNum();
				StringBuffer sbf = new StringBuffer();
				//数字之间插入空格
				for(int i = 0; i < CheckedNum.length(); i++){
					sbf.append(CheckedNum.charAt(i)+" ");
				}
				g.drawString(sbf.toString(), 15, 20);
				//将产生的验证码指定名字之后存储在session对象中
				session.setAttribute("CheckedNum", CheckedNum);
				ImageIO.write(image,"jpeg",response.getOutputStream());
				//清除缓存
				response.setHeader("Pragam","no-cache");
				response.setHeader("Catch-control", "no-store");
				response.setDateHeader("Expires", 0);
				
				out.clear();
				out = pageContext.pushBody();
			%>
			
	</body>
</html>