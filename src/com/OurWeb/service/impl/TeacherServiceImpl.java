package com.OurWeb.service.impl;

import com.OurWeb.dao.IAnnouncementDao;
import com.OurWeb.dao.ITeacherAchivDao;
import com.OurWeb.dao.ITeacherDao;
import com.OurWeb.dao.impl.AnnouncementDaoImpl;
import com.OurWeb.dao.impl.TeacherAchivDaoImpl;
import com.OurWeb.dao.impl.TeacherDaoImpl;
import com.OurWeb.entity.Announcement;
import com.OurWeb.entity.Teacher;
import com.OurWeb.entity.TeacherAchiv;
import com.OurWeb.entity.ThesisCategory;
import com.OurWeb.entity.ThesisSource;
import com.OurWeb.service.ITeacherService;

public class TeacherServiceImpl implements ITeacherService {
	
	private ITeacherDao teacherDao = new TeacherDaoImpl();
	private ITeacherAchivDao teacherAchivDao = new TeacherAchivDaoImpl();
	
	private IAnnouncementDao announcementDao = new AnnouncementDaoImpl();
	private Announcement[] announcements = announcementDao.getAnnouncements();

	
	@Override
	public Teacher loginTeacher(int id, String password) {
		return teacherDao.find(id, password);
	}
	
	@Override
	public Teacher getTeacher(int id) {
		return teacherDao.find(id);
	}
	
	@Override
	public int getCurrentWork(int id) {
		return teacherDao.getCurrentWord(id);
	}

	@Override
	public TeacherAchiv[] getAchiv(int id) {
		return teacherAchivDao.getAchiv(id);
	}

	
	@Override
	public Announcement getLatest() {
		int number = announcementDao.getNum()-1;
		return announcements[number];
	}

	@Override
	public Announcement[] getPast() {
		int number = announcementDao.getNum()-1;
		Announcement[] pastAnno = new Announcement[number];
		int num1 = 0;
		int num2 = 0;
		while(num2<number) {
			pastAnno[num2] = announcements[num1];
			num2++;
			num1++;
		}
		return pastAnno;
	}

	@Override
	public ThesisSource[] getSource() {
		return teacherAchivDao.getSource();
	}
	
	@Override
	public int getScore(int thesisId) {
		int score=0;
		ThesisSource[] thesisSource = getSource();
		int len = thesisSource.length;
		for(int i=0;i<len;i++) {
			if(thesisId==thesisSource[i].getId()) {
				score = thesisSource[i].getScore();
			}
		}
		return score;
	}

	@Override
	public int getThesisNum() {
		return teacherAchivDao.thesisNum();
	}

	@Override
	public ThesisCategory[] getCategory() {
		return teacherAchivDao.getCategory();
	}


}
