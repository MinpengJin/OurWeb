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
import com.OurWeb.entity.TeacherAchiv;
import com.OurWeb.service.impl.TeacherServiceImpl;

/**
 * Servlet implementation class DeleteThesisServlet
 */
@WebServlet("/DeleteThesisServlet")
public class DeleteThesisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteThesisServlet() {
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
		try {
			//��õ�ǰ�����ʦ��Ϣ
			HttpSession session = request.getSession();
			Teacher tempTeacher = (Teacher)session.getAttribute("tempTeacher");
			
			TeacherServiceImpl teacherService = new TeacherServiceImpl();
			//������������
			String psw = request.getParameter("psw");
			
			if(psw.equals(tempTeacher.getPassword())) {
				//��ý�ʦ�ɹ���Ϣ
				TeacherAchiv[] achievements = teacherService.getAchiv(tempTeacher.getId());
				int achivNum = achievements.length;
				//�õ��û���Ҫɾ��������
				String items = request.getParameter("items");
				int score=teacherService.getCurrentWork(tempTeacher.getId());
				
				System.out.println("ɾ��ҳ�� -��ʦ��ǰ����ֵ"+score);
				
				int[] selectedId = new int[achivNum];
				int selectedNum = 0;
				for(int i=0;i<items.length();i++) {
					if(items.charAt(i)==',') {
						continue;
					}else {
						StringBuffer tempId = new StringBuffer();
						while(items.charAt(i)!=',') {
							tempId.append(items.charAt(i));
							i++;
						}
						selectedId[selectedNum]=Integer.parseInt(tempId.toString());
						for(int j=0;j<achivNum;j++) {
							if(selectedId[selectedNum]==achievements[j].getId()) {
								score-=achievements[j].getScore();
								
								System.out.println("ɾ��ҳ��-���Ĺ���ֵ"+score);
								
								break;
							}
						}
						selectedNum++;				
					}
				}
				
				System.out.println("ɾ��ҳ��-ɾ������ֵ"+score);
				
				//ɾ����ʦ-���ı��н�ʦ������
				ConnectDatabaseImpl conn = new ConnectDatabaseImpl();
				for(int i=0;i<selectedNum;i++) {
					String sql = "delete from teacher_thesis_info where teacher_id="
							+tempTeacher.getId()+" and thesis_id="+selectedId[i];
					conn.update(sql);
					String sql2 = "delete from thesis_info where id = "+selectedId[i];
					conn.update(sql2);
				}
				//���µ�ǰ�û��Ĺ���ֵ
				String sql3 = "update teacher set currentWork ="+score+" where id="+tempTeacher.getId();
				conn.update(sql3);
				conn.close();			
			}else {
				String Info="�����������������";
				request.setAttribute("Info", Info);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Teacher/Teacher.jsp");
			rd.forward(request, response);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
