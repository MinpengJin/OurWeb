package com.OurWeb.service;

import com.OurWeb.entity.Announcement;
import com.OurWeb.entity.Teacher;
import com.OurWeb.entity.TeacherAchiv;
import com.OurWeb.entity.ThesisCategory;
import com.OurWeb.entity.ThesisSource;

public interface ITeacherService {
	
	//�õ����µĹ���
	Announcement getLatest();
	//�õ���ȥ�Ĺ���
	Announcement[] getPast();
	
	//�õ���ʦ�ɹ�
	TeacherAchiv[] getAchiv(int id);
	//�õ���ʦ��ǰ����ֵ
	int getCurrentWork(int id);
	
	//�õ���������
	int getThesisNum();
	//�õ����ĵ���Դ
	ThesisSource[] getSource();
	//������Ķ�Ӧ�Ĺ���ֵ
	int getScore(int thesisId);
	//�õ���������
	ThesisCategory[] getCategory();
	
	//�ṩ��ʦ�������
	Teacher loginTeacher(int id,String password);
	//Ѱ�ҽ�ʦ
	Teacher getTeacher(int id);
}
