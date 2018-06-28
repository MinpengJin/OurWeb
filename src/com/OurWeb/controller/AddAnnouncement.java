package com.OurWeb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.OurWeb.dao.IAdminDao;
import com.OurWeb.dao.impl.AdminDaoImpl;
import com.OurWeb.database.impl.ConnectDatabaseImpl;

/**
 * Servlet implementation class AddAnnouncement
 */
@WebServlet("/AddAnnouncement")
public class AddAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAnnouncement() {
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
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("ann-title");
		String content = request.getParameter("ann-content");
		String publishTime = request.getParameter("publish-time");
		
		IAdminDao adminDao = new AdminDaoImpl();
		int id = adminDao.getAnnouncements().size()+1;
		ConnectDatabaseImpl conn = new ConnectDatabaseImpl();
		String sql = "insert into announcement (id,title,content,date) "
				+ "values("+id+",'"+title+"','"+content+"','"+publishTime+"')";
		String Info = "Ìí¼Ó³É¹¦£¡";
		request.setAttribute("Info", Info);
		conn.update(sql);
		request.getRequestDispatcher("/WEB-INF/Administrator/Administrator.jsp").forward(request, response);
	}

}
