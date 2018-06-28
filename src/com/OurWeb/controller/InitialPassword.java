package com.OurWeb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.OurWeb.dao.impl.AdminDaoImpl;


/**
 * Servlet implementation class InitialPassword
 */
@WebServlet("/InitialPassword")
public class InitialPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitialPassword() {
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

		request.setCharacterEncoding("utf-8");
		
		String items = request.getParameter("items");
		AdminDaoImpl adminDao = new AdminDaoImpl();
		for(int i=0;i < items.length();i++) {
			StringBuffer temp = new StringBuffer();
			while(items.charAt(i)!=',') {
				temp.append(items.charAt(i));
				i++;
			}
			adminDao.initialPassword(Integer.parseInt(temp.toString()));
		}
		request.getRequestDispatcher("/WEB-INF/Administrator/Administrator.jsp").forward(request, response);
	}

}
