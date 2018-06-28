package com.OurWeb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.OurWeb.dao.ITeacherAchivDao;
import com.OurWeb.database.IConnectDatabase;
import com.OurWeb.database.impl.ConnectDatabaseImpl;
import com.OurWeb.entity.TeacherAchiv;
import com.OurWeb.entity.ThesisCategory;
import com.OurWeb.entity.ThesisSource;

public class TeacherAchivDaoImpl implements ITeacherAchivDao {

	private TeacherAchiv[] achievements;
	private ThesisSource[] thesisSource;
	private ThesisCategory[] thesisCategory;
	
	private int getAchivNum(int id) {
		int number = 0;
		try {
			IConnectDatabase connect = new ConnectDatabaseImpl();
			String sql = "select teacher_thesis_info.author_order,thesis_info.*"+
					" from teacher_thesis_info,thesis_info where teacher_thesis_info.teacher_id ="+id
					+" and teacher_thesis_info.thesis_id = thesis_info.id";
			ResultSet result = connect.query(sql);
			while(result.next()) {
				number++;
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return number;
	}
	
	private int getThesisScrNum() {
		int number = 0;
		try {
			IConnectDatabase connect = new ConnectDatabaseImpl();
			String sql = "select thesis_scr_info.* from thesis_scr_info";
			ResultSet result = connect.query(sql);
			while(result.next()) {
				number++;
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return number;
	}
	
	private int getCategoryNum() {
		int number = 0;
		try {
			IConnectDatabase connect = new ConnectDatabaseImpl();
			String sql = "select thesis_cat_info.* from thesis_cat_info";
			ResultSet result = connect.query(sql);
			while(result.next()) {
				number++;
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return number;
	}
	
	
	
	@Override
	public TeacherAchiv[] getAchiv(int id) {
		try {
			int number = getAchivNum(id);
			if(number>0) {
				achievements = new TeacherAchiv[number];
				IConnectDatabase connect = new ConnectDatabaseImpl();
				String sql = "select teacher_thesis_info.author_order,thesis_info.*"+
						" from teacher_thesis_info,thesis_info where teacher_thesis_info.teacher_id ="+id
						+" and teacher_thesis_info.thesis_id = thesis_info.id";
				ResultSet result = connect.query(sql);
				int num = 0;
				while(result.next()) {
					TeacherAchiv achievement = new TeacherAchiv();
					achievement.setAuthorOrder(result.getInt(1));
					achievement.setId(result.getInt(2));
					achievement.setTitle(result.getString(3));
					achievement.setCategory(result.getString(4));					
					achievement.setSrcId(result.getInt(7));
					achievement.setPublishDate(result.getString(8));
					achievement.setScore(result.getInt(9));
					achievement.setThesisNum(result.getString(10));
					achievements[num] = achievement;
					num++;
				}			
				connect.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return achievements;
	}

	
	@Override
	public ThesisSource[] getSource() {
		try {
			int number = getThesisScrNum();
			if(number>0) {	
				thesisSource = new ThesisSource[number];
				IConnectDatabase connect = new ConnectDatabaseImpl();
				String sql = "select * from thesis_scr_info";
				ResultSet result = connect.query(sql);
				int num = 0;
				while(result.next()) {
					ThesisSource temp = new ThesisSource();
					temp.setId(result.getInt(1));
					temp.setName(result.getString(2));
					temp.setScore(result.getInt(3));
					thesisSource[num] = temp;
					num++;
				}
				connect.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thesisSource;
	}

	
	@Override
	public ThesisCategory[] getCategory() {
		try {
			int number = getCategoryNum();
			if(number>0) {	
				thesisCategory = new ThesisCategory[number];
				IConnectDatabase connect = new ConnectDatabaseImpl();
				String sql = "select * from thesis_cat_info";
				ResultSet result = connect.query(sql);
				int num = 0;
				while(result.next()) {
					ThesisCategory temp = new ThesisCategory();
					temp.setId(result.getInt(1));
					temp.setName(result.getString(2));
					thesisCategory[num] = temp;
					num++;
				}
				connect.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thesisCategory;
	}


	@Override
	public int thesisNum() {
		int number = 0;
		try {
			IConnectDatabase connect = new ConnectDatabaseImpl();
			String sql = "select * from thesis_info";
			ResultSet result = connect.query(sql);
			while(result.next()) {
				number++;
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return number;
	}
}
