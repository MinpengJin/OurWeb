package com.OurWeb.dao;

import com.OurWeb.entity.*;

public interface ITeacherDao {
	
	//���ݽ�ʦ���Ų����û�
	Teacher find(int id);
	
	//���ݹ��ź�������ҽ�ʦ
	Teacher find(int id,String password);
	
	//��ý�ʦ��ǰ����ֵ
	int getCurrentWord(int id);
}
