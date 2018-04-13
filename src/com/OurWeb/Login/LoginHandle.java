package com.OurWeb.Login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		String username = "";
		String password = "";
		String CheckedNum = "";
		String Type = "";
		String Error = "";
		
		request.setCharacterEncoding("UTF-8");
		
		Type = request.getParameter("sex");
		HttpSession session = request.getSession();
		CheckedNum = (String) session.getAttribute("CheckedNum");
		if(request.getParameter("VerifiCode")!=null
				&& request.getParameter("VerifiCode").equals(CheckedNum)){
			//验证码正确
			if(request.getParameter("UserName")!=null){
				password = request.getParameter("Password");
			}
			if(request.getParameter("Password")!=null){
				username = request.getParameter("UserName");				
			}
			if("20154327".equals(username)&&"20154327".equals(password)){
				//判断用户类型
				if(Type.equals("User")){
					RequestDispatcher rd = request.getRequestDispatcher("/User/User.jsp");
					rd.forward(request, response);
				}else{
					RequestDispatcher rd = request.getRequestDispatcher("/Administrator/Administrator.jsp");
					rd.forward(request, response);
				}
			}else{
				//用户名或密码错误
				Error = "ErrorOfPass";
				session.setAttribute("Error", Error);
				response.sendRedirect("/OurWeb/Misjudgment");
			}
		}else{
			//验证码错误
			Error = "ErrorOfVeri";
			session.setAttribute("Error", Error);
			response.sendRedirect("/OurWeb/Misjudgment");
		}
	}

}
