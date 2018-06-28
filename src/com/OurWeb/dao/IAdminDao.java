package com.OurWeb.dao;

import java.util.ArrayList;

import com.OurWeb.entity.Administrator;
import com.OurWeb.entity.Announcement;
import com.OurWeb.entity.Teacher;

public interface IAdminDao {
	public int add(Teacher tchr);  // 添加新的教工信息
	public int del(int id);  // 删除教师
	public Teacher findById(int id);  // 根据教工号查找教师
	public int initialPassword(int id);  // 初始化密码
	public ArrayList<Teacher> getTeachers();//获得所有教师信息
	public ArrayList<Announcement> getAnnouncements();//获得所有公告信息
	public Administrator findAdmin(int id,String password);//查找管理员
	public void delAnno(int id);//删除公告
}
