package com.OurWeb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.OurWeb.dao.IAnnouncementDao;
import com.OurWeb.database.impl.ConnectDatabaseImpl;
import com.OurWeb.entity.Announcement;

public class AnnouncementDaoImpl implements IAnnouncementDao {

	Announcement[] Announcements;
	
	@Override
	public Announcement[] getAnnouncements() {		
		try {
			int number = getNum();
			Announcements = new Announcement[number];
			ConnectDatabaseImpl connect = new ConnectDatabaseImpl();
			String sql = "select * from announcement";
			ResultSet result = connect.query(sql);
			int num = 0;
			while(result.next() && num<number) {
				Announcement announcement = new Announcement();
				announcement.setTitle(result.getString(2));
				announcement.setContent(result.getString(3));
				announcement.setDate(result.getString(4));
				Announcements[num] = announcement;
				num++;
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Announcements;
	}

	@Override
	public int getNum() {
		int number = 0;
		try {
			ConnectDatabaseImpl connect = new ConnectDatabaseImpl();
			String sql = "select * from announcement";
			ResultSet result = connect.query(sql);
			while(result.next()) {
				number++;
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return number;
	}

}
