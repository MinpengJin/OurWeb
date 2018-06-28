package com.OurWeb.dao;

import com.OurWeb.entity.TeacherAchiv;
import com.OurWeb.entity.ThesisCategory;
import com.OurWeb.entity.ThesisSource;

public interface ITeacherAchivDao {
	
	//获得论文来源的内容
	ThesisSource[] getSource();
	
	//获得论文类别的内容
	ThesisCategory[] getCategory();
	
	//从数据库得到教师的成果
	TeacherAchiv[] getAchiv(int id);
	
	//获得当前数据库论文数量
	int thesisNum();
}
