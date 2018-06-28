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
import com.OurWeb.entity.Teacher;

/**
 * Servlet implementation class PasswordServlet
 */
@WebServlet("/TeacherPswServlet")
public class TeacherPswServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherPswServlet() {
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
			Teacher tempTeacher = (Teacher)session.getAttribute("tempTeacher");
			String Info = "";
			
			String nowPassword = request.getParameter("nowPassword");
			String newPassword = request.getParameter("newPassword");
			String checkPassword = request.getParameter("checkPassword");
			
			if(nowPassword!=""&&nowPassword.equals(tempTeacher.getPassword())) {
				//�ж����������Ƿ�ƥ��
				if(newPassword!=""&&checkPassword!="") {
					if(newPassword.equals(checkPassword)) {
						ConnectDatabaseImpl connect = new ConnectDatabaseImpl();
						String sql = "update teacher set passWord ="+newPassword+" where id ="+tempTeacher.getId();
						connect.update(sql);
						Info = "�����޸ĳɹ���";
						request.setAttribute("Info", Info);
						connect.close();
					}else {
						Info = "�������벻ƥ�䣬����������!";
						request.setAttribute("Info", Info);
					}					
				}else {
					Info = "������Ϊ�գ����������룡";
					request.setAttribute("Info", Info);
				}
			}else {
				Info = "��ǰ���벻��ȷ������������!";
				request.setAttribute("Info", Info);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Teacher/Teacher.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
