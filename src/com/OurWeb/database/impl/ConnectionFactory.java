package com.OurWeb.database.impl;
import java.sql.*;

public class ConnectionFactory {
	// �������ӵĹ�����
		public static Connection getConnection() {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//System.out.println("Success in loading MySQL Driver!");
			} catch (Exception e) {
				// TODO: handle exception
				//System.out.println("Error in loading MySQL Driver!");
				e.printStackTrace();
			}
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourweb?useUnicode=true&characterEncoding=utf-8&useSSL=false",
						"root","13958548266");
				//System.out.println("Success in connecting MySQL Server!");
			} catch (Exception e) {
				// TODO: handle exception
				//System.out.println("Error in connecting MySQL Server!");
				e.printStackTrace();
			}
			return con;
		}
}
//	private ConnectionFactory() {}
//	public static Connection getConnection() {
//		try {
//			Context ctx = new InitialContext();
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/JNDIname");
//			return ds.getConnection();
//		}catch (NamingException e) {
//			System.out.print("���ݿ�δ���������ӳ�δ���ã�");
//			return null;
//		}catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	public static Connection getConnection() {
//		try {
//			String JNDIname = (String) new javax.naming.InitialContext().lookup("java:comp/env/JNDIname");
//			return ConnectionFactory.getConnection(JNDIname);
//		} catch (NamingException e) {
//			System.out.print("���ݿ�δ���������ӳ�δ���ã�");
//		}
//		return null;
//	}
//	public static Connection getConnection(String JNDIname) {
//		try {
//			Context initCtx = new InitialContext();
//			Context envCtx = (Context) initCtx.lookup("java:comp/env");
//			DataSource ds = (DataSource) envCtx.lookup(JNDIname);
//			return ds.getConnection();
//		} catch (NamingException e1) {
//			System.out.print("���ݿ�δ���������ӳ�δ���ã�");
//			return null;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
