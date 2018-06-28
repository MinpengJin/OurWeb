package com.OurWeb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.OurWeb.dao.ITeacherDao;
import com.OurWeb.database.impl.ConnectDatabaseImpl;
import com.OurWeb.entity.Teacher;

public class TeacherDaoImpl implements ITeacherDao {

	@Override
	public Teacher find(int id) {
		Teacher teacher = null;
		try {
			String sql = "select * from teacher where id = "+id;
			ConnectDatabaseImpl connect = new ConnectDatabaseImpl();
			ResultSet result = connect.query(sql);
			if(result.next()) {
				teacher = new Teacher();
				teacher.setId(id);
				teacher.setName(result.getString(2));
				teacher.setTitle(result.getString(3));
				teacher.setExamType(result.getString(4));
				teacher.setExamTime(result.getString(5));
				teacher.setPassword(result.getString(6));
				teacher.setTargetWork(result.getInt(7));
				teacher.setCurrentWork(result.getInt(8));
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacher;
	}

	@Override
	public Teacher find(int id, String password) {
		Teacher teacher = null;
		try {
			String sql = "select * from teacher where id = "+id +" and passWord = "+password;
			ConnectDatabaseImpl connect = new ConnectDatabaseImpl();
			ResultSet result = connect.query(sql);
			if(result.next()) {
				teacher = new Teacher();
				teacher.setId(id);
				teacher.setName(result.getString(2));
				teacher.setTitle(result.getString(3));
				teacher.setExamType(result.getString(4));
				teacher.setExamTime(result.getString(5));
				teacher.setPassword(result.getString(6));
				teacher.setTargetWork(result.getInt(7));
				teacher.setCurrentWork(result.getInt(8));
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacher;
	}

	@Override
	public int getCurrentWord(int id) {
		int currentWork=0;
		try {
			String sql = "select currentWork from teacher where id = "+id;
			ConnectDatabaseImpl conn = new ConnectDatabaseImpl();
			ResultSet result = conn.query(sql);
			result.next();
			currentWork = result.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentWork;
	}

}
