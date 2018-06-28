package com.OurWeb.service;

import com.OurWeb.entity.Announcement;
import com.OurWeb.entity.Teacher;
import com.OurWeb.entity.TeacherAchiv;
import com.OurWeb.entity.ThesisCategory;
import com.OurWeb.entity.ThesisSource;

public interface ITeacherService {
	
	//得到最新的公告
	Announcement getLatest();
	//得到过去的公告
	Announcement[] getPast();
	
	//得到教师成果
	TeacherAchiv[] getAchiv(int id);
	//得到教师当前贡献值
	int getCurrentWork(int id);
	
	//得到论文数量
	int getThesisNum();
	//得到论文的来源
	ThesisSource[] getSource();
	//获得论文对应的贡献值
	int getScore(int thesisId);
	//得到论文种类
	ThesisCategory[] getCategory();
	
	//提供教师登入服务
	Teacher loginTeacher(int id,String password);
	//寻找教师
	Teacher getTeacher(int id);
}
