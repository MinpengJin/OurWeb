package com.OurWeb.entity;


public class Teacher {

	private int id;				//����
	private String name;		//����
	private String title;		//ְ��
	private String examType;	//��������
	private String examTime;		//����ʱ��
	private String password;	//����
	private int targetWork;		//Ŀ�깤����
	private int currentWork;	//��ǰ������
	
	public int getCurrentWork() {
		return currentWork;
	}
	public void setCurrentWork(int currentWork) {
		this.currentWork = currentWork;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getTargetWork() {
		return targetWork;
	}
	public void setTargetWork(int targetWork) {
		this.targetWork = targetWork;
	}
	
	
}
