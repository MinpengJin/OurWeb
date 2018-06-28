package com.OurWeb.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.OurWeb.dao.IAdminDao;
import com.OurWeb.database.impl.ConnectionFactory;
import com.OurWeb.entity.Administrator;
import com.OurWeb.entity.Announcement;
import com.OurWeb.entity.Teacher;

public class AdminDaoImpl implements IAdminDao{
	
	@Override
	public int add(Teacher tchr) {
		// TODO Auto-generated method stub
		int rows = 0;
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pre = null; 
		try {
			System.out.println(tchr.getExamType());
			String sql = "insert into teacher(id,name,title,examType,examTime,passWord,targetWork,currentWork) values(?,?,?,?,?,?,?,?)";
			pre = con.prepareStatement(sql);
			pre.setInt(1, tchr.getId());
			pre.setString(2, tchr.getName());
			pre.setString(3, tchr.getTitle());
			pre.setString(4, tchr.getExamType());
			pre.setString(5, tchr.getExamTime());
			pre.setString(6, tchr.getPassword());
			pre.setInt(7, tchr.getTargetWork());
			pre.setInt(8, tchr.getCurrentWork());
			rows = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pre != null) {
					pre.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	@Override
	public Teacher findById(int id) {
		// TODO Auto-generated method stub
		Teacher tchr = null;
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT pass, name FROM teacher_info WHERE id = ? LIMIT 1";
			pre = con.prepareStatement(sql);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			while(rs.next()) {
				tchr = new Teacher();
				tchr.setId(id);
				System.out.println("&&" + rs.getString(1));
				tchr.setPassword(rs.getString(1));;
				tchr.setName(rs.getString(2));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pre != null) {
					pre.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return tchr;
	}

	@Override
	public int initialPassword(int id) {
		// TODO Auto-generated method stub
		int rows = 0;
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pre = null; 
		try {
			String initialPsw = Integer.toString(id);
			String sql = "UPDATE teacher SET passWord ="+initialPsw +" WHERE id ="+id;
			pre = con.prepareStatement(sql);
			rows = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pre != null) {
					pre.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	@Override
	public int del(int id) {
		// TODO Auto-generated method stub
		int rows = 0;
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pre = null; 
		try {
			String sql = "DELETE FROM teacher WHERE id ="+id;
			pre = con.prepareStatement(sql);
			rows = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pre != null) {
					pre.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}
	
	
	@Override
	public ArrayList<Teacher> getTeachers() {
		ArrayList<Teacher> allTchrs = new ArrayList<>();
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from teacher";
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
			while(rs.next()) {
				Teacher tchr = new Teacher();
				tchr.setId(rs.getInt(1));
				tchr.setName(rs.getString(2));
				tchr.setTitle(rs.getString(3));
				tchr.setExamType(rs.getString(4));
				tchr.setExamTime(rs.getString(5));
				tchr.setPassword(rs.getString(6));
				tchr.setTargetWork(rs.getInt(7));
				tchr.setCurrentWork(rs.getInt(8));
				allTchrs.add(tchr);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pre != null) {
					pre.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return allTchrs;
	}

	@Override
	public ArrayList<Announcement> getAnnouncements() {
		ArrayList<Announcement> allAnn = new ArrayList<>();
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from announcement";
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
			while(rs.next()) {
				Announcement temp = new Announcement();
				temp.setId(rs.getInt(1));
				temp.setTitle(rs.getString(2));
				temp.setContent(rs.getString(3));
				temp.setDate(rs.getString(4));
				allAnn.add(temp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pre != null) {
					pre.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return allAnn;
	}
	
	@Override
	public void delAnno(int id) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pre = null; 
		try {
			String sql = "delete from announcement where id ="+id;
			pre  = con.prepareStatement(sql);
			pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pre != null) {
					pre.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	private Administrator temp;
	@Override
	public Administrator findAdmin(int id, String password) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			String sql = "select * from administrator where id="+id+" and password="+password;
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
			if(rs.next()) {
				temp = new Administrator();
				temp.setId(rs.getInt(1));
				temp.setName(rs.getString(2));
				temp.setPassword(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pre != null) {
					pre.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

}
