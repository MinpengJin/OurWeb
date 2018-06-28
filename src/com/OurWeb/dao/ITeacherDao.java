package com.OurWeb.dao;

import com.OurWeb.entity.*;

public interface ITeacherDao {
	
	//根据教师工号查找用户
	Teacher find(int id);
	
	//根据工号和密码查找教师
	Teacher find(int id,String password);
	
	//获得教师当前贡献值
	int getCurrentWord(int id);
}
