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
 * Servlet implementation class Misjudgment
 */
@WebServlet("/Misjudgment")
public class Misjudgment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Misjudgment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//判断输入的错误类型
		String Info = "";
		String Error = "";
		HttpSession session = request.getSession();
		Error = (String) session.getAttribute("Error");
		if(Error.equals("NullError")) {
			Info = "用户名或密码不能为空，请重新登入！";
			request.setAttribute("Info", Info);
		}else if(Error.equals("ErrorOfVeri")) {
			Info = "验证码错误，请重新登入！";
			request.setAttribute("Info", Info);
		}else if(Error.equals("invalidUserName")) {
			Info = "用户名错误，请重新输入!";
			request.setAttribute("Info", Info);
		}else if(Error.equals("invalidPassword")) {
			Info = "密码错误，请重新输入!";
			request.setAttribute("Info", Info);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/Login/Login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
