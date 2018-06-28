package com.OurWeb.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.OurWeb.dao.impl.AdminDaoImpl;
import com.OurWeb.entity.Teacher;


/**
 * Servlet implementation class FindById
 */
@WebServlet("/FindById")
public class FindById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindById() {
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
		
		String searchId = request.getParameter("searchId");
		AdminDaoImpl adminDao = new AdminDaoImpl();
		ArrayList<Teacher> teachers = adminDao.getTeachers();
		if(searchId!="") {
			boolean isExisted=false;
			int index=0;
			for(int i=0;i<teachers.size();i++) {
				if(teachers.get(i).getId()==Integer.parseInt(searchId)) {
					isExisted=true;
					index=i;
				}
			}
			if(isExisted) {
				if(index!=0) {
					Teacher temp = teachers.get(0);
					teachers.set(0, teachers.get(index));
					teachers.set(index, temp);
					request.setAttribute("Teachers", teachers);
				}
			}else {
				String Info="不存在该教师";
				request.setAttribute("Info", Info);
			}		
		}else {
			String Info="教师工号不能为空";
			request.setAttribute("Info", Info);
		}
		request.getRequestDispatcher("/WEB-INF/Administrator/Administrator.jsp").forward(request, response);
	}
	
}
