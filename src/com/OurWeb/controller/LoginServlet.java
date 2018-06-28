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
			
			//�ж��û�����
			String Type = "";
			Type = request.getParameter("sex");

			//ȡ�õ�ǰ���ɵ���֤��ֵ
			String CheckedNum = "";
			HttpSession session = request.getSession();
			CheckedNum = (String) session.getAttribute("CheckedNum");
			
			if(request.getParameter("VerifiCode").equals(CheckedNum)){
				//��֤����ȷ
				if(request.getParameter("Password")!="" && request.getParameter("UserName")!=""){
					
					//ȡ�õ�ǰ��ʦ������û���������
					String password = request.getParameter("Password");
					int id = Integer.parseInt(request.getParameter("UserName"));	
					//��ʦ����
					if(Type.equals("Teacher")){
						ITeacherService teacher = new TeacherServiceImpl();
						Teacher tempTeacher = teacher.loginTeacher(id, password);
						if(tempTeacher == null) {
							Info = "�û����������������������!";
							isBack = true;
						}else {
							session.setAttribute("tempTeacher", tempTeacher);
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Teacher/Teacher.jsp");
							rd.forward(request, response);	
						}
					}else{
						//����Ա����
						IAdminDao adminDao = new AdminDaoImpl();
						Administrator admin = adminDao.findAdmin(id, password);
						if(admin == null) {
							Info = "�û���������������������룡";
							isBack = true;
						}else {
							session.setAttribute("tempAdmin", admin);
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Administrator/Administrator.jsp");
							rd.forward(request, response);
						}
					}
				}
				else{
					//�û���������Ϊ��
					Info = "�û��������벻��Ϊ�գ������µ��룡";
					isBack = true;
				}
			}else{
				//��֤�����
				Info = "��֤����������µ��룡";
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
