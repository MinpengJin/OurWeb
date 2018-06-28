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
import com.OurWeb.entity.ThesisSource;
import com.OurWeb.service.ITeacherService;
import com.OurWeb.service.impl.TeacherServiceImpl;

/**
 * Servlet implementation class AddThesisServlet
 */
@WebServlet("/AddThesisServlet")
public class AddThesisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddThesisServlet() {
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
			
			String firstAuthor = request.getParameter("firstAuthor");
			String secAuthor = request.getParameter("secAuthor");
			//��ȡ��������
			int author_order = 0;
			HttpSession session = request.getSession();
			Teacher tempTeacher = (Teacher) session.getAttribute("tempTeacher");
			if(firstAuthor.equals(tempTeacher.getName())) {
				author_order=1;
			}else if(secAuthor.equals(tempTeacher.getName())){
				author_order=2;
			}
			
			if(author_order>0) {
				String title = request.getParameter("thesisTitle");
				String category = request.getParameter("category");
				String publichDate = request.getParameter("publichDate");		
				int score = 0,sourceId = 0;
				String thesisNum = request.getParameter("thesisNum");
				
				
				//��ȡ��ǰ���Ķ�Ӧ�Ĺ���ֵ��id
				String source = request.getParameter("source");
				ITeacherService teacherService = new TeacherServiceImpl();
				ThesisSource[] thesisSource = teacherService.getSource();
				int scrNum = thesisSource.length;
				for(int i=0;i<scrNum;i++) {
					if(source.equals(thesisSource[i].getName())) {
						score=thesisSource[i].getScore();
						sourceId=thesisSource[i].getId();
						break;
					}
				}
				
				//System.out.println("���ҳ��-��ʦ��ǰ����ֵ"+tempTeacher.getCurrentWork());
				//System.out.println("���ҳ��-���Ĺ���ֵ"+score);
				
				int thesisId = teacherService.getThesisNum()+1;
				//��ø��º�Ĺ���ֵ
				int totalScore = teacherService.getCurrentWork(tempTeacher.getId())+score;
				
				//System.out.println("���ҳ��-��Ӻ���ֵ"+totalScore);
				
				ConnectDatabaseImpl conn = new ConnectDatabaseImpl();
				//�����ı��в�������
				String sql1 = "insert into thesis_info(id,title,category,first_author,sec_author,src_id,"
						+ "publish_date,score,thesis_num) values("+thesisId+",'"+title+"','"+category+"','"
						+firstAuthor+"','"+secAuthor+"',"+sourceId+",'"+publichDate+"',"
						+score+",'"+thesisNum+"')";
				conn.update(sql1);		
				//������ʦ�����ĵ�����
				String sql2 = "insert into teacher_thesis_info(teacher_id,thesis_id,author_order) "
						+ "values("+tempTeacher.getId()+","+thesisId+","+author_order+")";
				conn.update(sql2);
				//���½�ʦ��ǰ����ֵ
				String sql3 = "update teacher set currentWork="+totalScore+" where id="+tempTeacher.getId();
				conn.update(sql3);
				conn.close();			
			}else {
				String Info="��ǰ�û��������������У�������¼��";
				request.setAttribute("Info", Info);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Teacher/Teacher.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
