package com.OurWeb.dao;

import com.OurWeb.entity.Announcement;

public interface IAnnouncementDao {
	//得到所有公告
	Announcement[] getAnnouncements();
	//得到公告数量
	int getNum();
}
