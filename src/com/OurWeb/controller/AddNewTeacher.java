package com.OurWeb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.OurWeb.dao.IAdminDao;
import com.OurWeb.dao.impl.AdminDaoImpl;
import com.OurWeb.entity.Teacher;


/**
 * Servlet implementation class AddNewTeacher
 */
@WebServlet("/AddNewTeacher")
public class AddNewTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewTeacher() {
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
		request.setCharacterEncoding("UTF-8");
		
		String tchrName = request.getParameter("tchrName");
		int tchrId = Integer.parseInt(request.getParameter("tchrId"));
		String initialPass = request.getParameter("tchrId");
		String tchrTitle = request.getParameter("tchrTitle");
		String tchrExamType = request.getParameter("tchrExamType");
		String tchrExamTime = request.getParameter("tchrExamTime");
		String tchrTargetWork = request.getParameter("tchrTargetWork");
		int initialWork = 0;
		Teacher tchr = new Teacher();
		tchr.setId(tchrId);
		tchr.setName(tchrName);
		tchr.setPassword(initialPass);
		tchr.setTitle(tchrTitle);
		tchr.setExamType(tchrExamType);
		tchr.setTargetWork(Integer.parseInt(tchrTargetWork));
		tchr.setExamTime(tchrExamTime);
		tchr.setCurrentWork(initialWork);
		IAdminDao tchrDao = new AdminDaoImpl();
		int rows = tchrDao.add(tchr);
		if(rows > 0) {
			System.out.println("添加成功");
		}
		else {
			System.out.println("添加失败");
		}
		request.getRequestDispatcher("/WEB-INF/Administrator/Administrator.jsp").forward(request, response);
		
	}

}
