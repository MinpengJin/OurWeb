package com.OurWeb.dao;

import java.util.ArrayList;

import com.OurWeb.entity.Administrator;
import com.OurWeb.entity.Announcement;
import com.OurWeb.entity.Teacher;

public interface IAdminDao {
	public int add(Teacher tchr);  // ����µĽ̹���Ϣ
	public int del(int id);  // ɾ����ʦ
	public Teacher findById(int id);  // ���ݽ̹��Ų��ҽ�ʦ
	public int initialPassword(int id);  // ��ʼ������
	public ArrayList<Teacher> getTeachers();//������н�ʦ��Ϣ
	public ArrayList<Announcement> getAnnouncements();//������й�����Ϣ
	public Administrator findAdmin(int id,String password);//���ҹ���Ա
	public void delAnno(int id);//ɾ������
}
