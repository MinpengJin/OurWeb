package com.OurWeb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OurWeb.database.impl.ConnectDatabaseImpl;
import com.OurWeb.entity.Administrator;

/**
 * Servlet implementation class AdminPswServlet
 */
@WebServlet("/AdminPswServlet")
public class AdminPswServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPswServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			Administrator tempAdmin = (Administrator)session.getAttribute("tempAdmin");
			String Info = "";
			
			String nowPassword = request.getParameter("nowPassword");
			String newPassword = request.getParameter("newPassword");
			String checkPassword = request.getParameter("checkPassword");
			
			if(nowPassword!=""&&nowPassword.equals(tempAdmin.getPassword())) {
				//判断输入密码是否匹配
				if(newPassword!=""&&checkPassword!="") {
					if(newPassword.equals(checkPassword)) {
						ConnectDatabaseImpl connect = new ConnectDatabaseImpl();
						String sql = "update administrator set password ="+newPassword+" where id ="+tempAdmin.getId();
						connect.update(sql);
						Info = "密码修改成功！";
						request.setAttribute("Info", Info);
						connect.close();
					}else {
						Info = "输入密码不匹配，请重新输入!";
						request.setAttribute("Info", Info);
					}					
				}else {
					Info = "新密码为空，请重新输入！";
					request.setAttribute("Info", Info);
				}
			}else {
				Info = "当前密码不正确，请重新输入!";
				request.setAttribute("Info", Info);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Administrator/Administrator.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
