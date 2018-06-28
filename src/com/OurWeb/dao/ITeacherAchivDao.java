package com.OurWeb.dao;

import com.OurWeb.entity.TeacherAchiv;
import com.OurWeb.entity.ThesisCategory;
import com.OurWeb.entity.ThesisSource;

public interface ITeacherAchivDao {
	
	//���������Դ������
	ThesisSource[] getSource();
	
	//���������������
	ThesisCategory[] getCategory();
	
	//�����ݿ�õ���ʦ�ĳɹ�
	TeacherAchiv[] getAchiv(int id);
	
	//��õ�ǰ���ݿ���������
	int thesisNum();
}
