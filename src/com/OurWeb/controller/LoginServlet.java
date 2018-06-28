package com.OurWeb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OurWeb.dao.IAdminDao;
import com.OurWeb.dao.impl.AdminDaoImpl;
import com.OurWeb.entity.Administrator;
import com.OurWeb.entity.Teacher;
import com.OurWeb.service.ITeacherService;
import com.OurWeb.service.impl.TeacherServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		// TODO Auto-generated method stub
		try {
			
			request.setCharacterEncoding("UTF-8");
			
			boolean isBack = false;
			String Info = "";
			
			//判断用户类型
			String Type = "";
			Type = request.getParameter("sex");

			//取得当前生成的验证码值
			String CheckedNum = "";
			HttpSession session = request.getSession();
			CheckedNum = (String) session.getAttribute("CheckedNum");
			
			if(request.getParameter("VerifiCode").equals(CheckedNum)){
				//验证码正确
				if(request.getParameter("Password")!="" && request.getParameter("UserName")!=""){
					
					//取得当前教师输入的用户名和密码
					String password = request.getParameter("Password");
					int id = Integer.parseInt(request.getParameter("UserName"));	
					//教师登入
					if(Type.equals("Teacher")){
						ITeacherService teacher = new TeacherServiceImpl();
						Teacher tempTeacher = teacher.loginTeacher(id, password);
						if(tempTeacher == null) {
							Info = "用户名或密码错误，请重新输入!";
							isBack = true;
						}else {
							session.setAttribute("tempTeacher", tempTeacher);
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Teacher/Teacher.jsp");
							rd.forward(request, response);	
						}
					}else{
						//管理员登入
						IAdminDao adminDao = new AdminDaoImpl();
						Administrator admin = adminDao.findAdmin(id, password);
						if(admin == null) {
							Info = "用户名或密码错误，请重新输入！";
							isBack = true;
						}else {
							session.setAttribute("tempAdmin", admin);
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Administrator/Administrator.jsp");
							rd.forward(request, response);
						}
					}
				}
				else{
					//用户名或密码为空
					Info = "用户名或密码不能为空，请重新登入！";
					isBack = true;
				}
			}else{
				//验证码错误
				Info = "验证码错误，请重新登入！";
				isBack = true;
			}
			if(isBack) {
				request.setAttribute("Info", Info);
				RequestDispatcher rd = request.getRequestDispatcher("/Login/Login.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
