package com.OurWeb.dao;

import com.OurWeb.entity.Announcement;

public interface IAnnouncementDao {
	//�õ����й���
	Announcement[] getAnnouncements();
	//�õ���������
	int getNum();
}
