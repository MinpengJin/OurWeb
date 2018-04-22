package com.OurWeb.Login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
/**
 * Servlet implementation class LoginHandle
 */
@WebServlet("/LoginHandle")
public class LoginHandle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginHandle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			request.setCharacterEncoding("UTF-8");
//			判断用户类型
			String Type = "";
			String Error = "";
			Type = request.getParameter("sex");

//			取得当前生成的验证码值
			String CheckedNum = "";
			HttpSession session = request.getSession();
			CheckedNum = (String) session.getAttribute("CheckedNum");
			
			if(request.getParameter("VerifiCode").equals(CheckedNum)){
				//验证码正确
				if(request.getParameter("Password")!="" && 
						request.getParameter("UserName")!=""){
//					密码和用户名不为空，连接数据库
					Class.forName("com.mysql.jdbc.Driver");
					Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourweb","root","13958548266");
					
//					取得当前用户输入的用户名和密码
					String password = request.getParameter("Password");
					int username = Integer.parseInt(request.getParameter("UserName"));	
//					教师登入
					if(Type.equals("Teacher")){
						String tempPassWord = "";
						boolean valid = false;
//						连接教师数据表，进行查询
						String sql = "select id,passWord from teacher";
						ResultSet result = connect.prepareStatement(sql).executeQuery();
						while(result.next()) {
							if(username == result.getInt(1)) {
								tempPassWord = result.getString(2);
								valid = true;
								break;
							}
						}
						if(valid) {
							if(tempPassWord.equals(password)) {
								request.setAttribute("ID", request.getParameter("UserName"));
								RequestDispatcher rd = request.getRequestDispatcher("/User/User.jsp");
								rd.forward(request, response);
							}else {
								Error = "invalidPassword";
								session.setAttribute("Error", Error);
								response.sendRedirect("/OurWeb/Misjudgment");
							}
						}else {
//							用户名错误
							Error = "invalidUserName";
							session.setAttribute("Error", Error);
							response.sendRedirect("/OurWeb/Misjudgment");
						}
					}else{
//						管理员登入
						String tempPassWord = "";
						boolean valid = false;
//						连接管理员数据表，进行查询
						String sql = "select id,passWord from administrator";
						ResultSet result = connect.prepareStatement(sql).executeQuery();
						while(result.next()) {
							if(username == result.getInt(1)) {
								tempPassWord = result.getString(2);
								valid = true;
								break;
							}
						}
						if(valid) {
							if(tempPassWord.equals(password)) {
								request.setAttribute("ID", request.getParameter("UserName"));
								RequestDispatcher rd = request.getRequestDispatcher("/User/User.jsp");
								rd.forward(request, response);
							}else {
								Error = "invalidPassword";
								session.setAttribute("Error", Error);
								response.sendRedirect("/OurWeb/Misjudgment");
							}
						}else {
//							用户名错误
							Error = "invalidUserName";
							session.setAttribute("Error", Error);
							response.sendRedirect("/OurWeb/Misjudgment");
						}
						RequestDispatcher rd = request.getRequestDispatcher("/Administrator/Administrator.jsp");
						rd.forward(request, response);
					}
				}
				else{
					
					System.out.println("用户名或密码为空");
					
					//用户名或密码为空
					Error = "NullError";
					session.setAttribute("Error", Error);
					response.sendRedirect("/OurWeb/Misjudgment");
				}
			}else{
				
				System.out.println("验证码错误");
				
				//验证码错误
				Error = "ErrorOfVeri";
				session.setAttribute("Error", Error);
				response.sendRedirect("/OurWeb/Misjudgment");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
